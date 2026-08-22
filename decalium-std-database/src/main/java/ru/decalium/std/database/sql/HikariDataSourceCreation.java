package ru.decalium.std.database.sql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.File;
import java.nio.file.Path;
import java.text.MessageFormat;

public class HikariDataSourceCreation {


    private final SqlConfig sqlConfig;
    private final File dataFolder;
    private final String name;

    public HikariDataSourceCreation(SqlConfig sqlConfig, File dataFolder, String name) {
        this.sqlConfig = sqlConfig;
        this.dataFolder = dataFolder;
        this.name = name;
    }


    public HikariDataSource create() {
        HikariConfig config = new HikariConfig();
        config.setPoolName(name + "Pool");
        setupConnection(config);
        setupPooling(config);
        return new HikariDataSource(config);
    }

    private void setupConnection(HikariConfig config) {

        String url, username, password;
        Path path = dataFolder.toPath();
        switch (sqlConfig.databaseType()) {
            case H2 -> {
                url = MessageFormat.format("jdbc:h2:file:./{0};mode=MySQL", path.resolve(name));
                username = "sa";
                password = "";
            }
            case SQLITE -> {
                url = MessageFormat.format("jdbc:sqlite:{0}", path.resolve(name+".sqlite.db"));
                username = null;
                password = null;
            }
            case MARIADB -> {
                url = MessageFormat.format("jdbc:mysql://{0}/{1}?useSSL=false", sqlConfig.host(), sqlConfig.database());
                username = sqlConfig.username();
                password = sqlConfig.password();
            }
            default -> throw new IllegalStateException("Unsupported database type");
        }
        config.setDriverClassName(sqlConfig.databaseType().driverClassName());
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
    }

    private void setupPooling(HikariConfig config) {
        sqlConfig.poolSettings().apply(config);
        config.setInitializationFailTimeout(-1);
    }


}
