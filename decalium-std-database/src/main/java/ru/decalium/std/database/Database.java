package ru.decalium.std.database;

import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import ru.decalium.std.database.sql.AsyncJdbi;
import ru.decalium.std.database.sql.HikariDataSourceCreation;
import ru.decalium.std.database.sql.JdbiCreation;
import ru.decalium.std.database.sql.SqlConfig;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

public final class Database {

    private final AsyncJdbi jdbi;
    private final HikariDataSource dataSource;

    private Database(AsyncJdbi jdbi, HikariDataSource dataSource) {
        this.jdbi = jdbi;
        this.dataSource = dataSource;
    }

    public AsyncJdbi asyncJdbi() {
        return this.jdbi;
    }

    public HikariDataSource dataSource() {
        return this.dataSource;
    }

    public void close() {
        this.jdbi.close();
        this.dataSource.close();
    }

    public static Builder builder(SqlConfig config) {
        return new Builder(config);
    }

    public static class Builder {
        private ClassLoader classLoader;
        private File dataFolder;
        private String name;
        private SqlConfig config;

        private Logger logger;
        private ExecutorService executor;

        private Builder(SqlConfig config) {
            this.config = config;
        }

        public Builder dataFolder(File dataFolder) {
            this.dataFolder = dataFolder;
            return this;
        }

        public Builder classLoader(ClassLoader classLoader) {
            this.classLoader = classLoader;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder logger(Logger logger) {
            this.logger = logger;
            return this;
        }

        public Builder executor(ExecutorService service) {
            this.executor = service;
            return this;
        }

        public Database build() {
            Database database = buildNoMigrations();
            Flyway flyway = Flyway.configure(classLoader)
                    .dataSource(database.dataSource)
                    .baselineVersion("0")
                    .locations("classpath:db/migration")
                    .baselineOnMigrate(true)
                    .validateOnMigrate(true).load();
            flyway.repair();
            flyway.migrate();

            return database;
        }

        public Database buildNoMigrations() {
            Objects.requireNonNull(classLoader);
            Objects.requireNonNull(dataFolder);
            Objects.requireNonNull(name);
            Objects.requireNonNull(executor);
            Objects.requireNonNull(logger);
            HikariDataSource source = new HikariDataSourceCreation(this.config, this.dataFolder, this.name).create();
            Jdbi jdbi = new JdbiCreation(source).create();

            return new Database(new AsyncJdbi(executor, jdbi, logger), source);

        }

    }
}
