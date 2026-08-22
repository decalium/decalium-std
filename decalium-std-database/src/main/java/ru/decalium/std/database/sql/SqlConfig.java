package ru.decalium.std.database.sql;

import com.zaxxer.hikari.HikariConfig;

public interface SqlConfig {

    enum DatabaseType {


        SQLITE("org.sqlite.JDBC"),
        H2("org.h2.Driver"),
        MARIADB("com.mysql.cj.jdbc.Driver");

        private final String driverClassName;

        DatabaseType(String driverClassName) {
            this.driverClassName = driverClassName;
        }

        public String driverClassName() {
            return driverClassName;
        }
    }

    DatabaseType databaseType();

    String host();

    String username();

    String password();

    String database();

    HikariPool poolSettings();


    interface HikariPool {
        void apply(HikariConfig config);
    }


}
