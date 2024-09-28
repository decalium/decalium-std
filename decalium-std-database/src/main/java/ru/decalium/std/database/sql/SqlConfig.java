package ru.decalium.std.database.sql;

import com.zaxxer.hikari.HikariConfig;

public interface SqlConfig {

    boolean mysqlEnabled();

    String host();

    String username();

    String password();

    String database();

    HikariPool poolSettings();


    interface HikariPool {
        void apply(HikariConfig config);
    }


}
