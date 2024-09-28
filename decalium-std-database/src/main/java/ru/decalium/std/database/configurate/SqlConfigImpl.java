package ru.decalium.std.database.configurate;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;


import com.zaxxer.hikari.HikariConfig;
import ru.decalium.std.database.sql.SqlConfig;

@ConfigSerializable
public class SqlConfigImpl implements SqlConfig {

    private boolean mysqlEnabled = false;
    private String host = "localhost";
    private String username = "gepron1x";
    private String password = "123";
    private String database = "mydb";

    private HikariPoolImpl hikariPoolSettings = new HikariPoolImpl();


    @Override
    public boolean mysqlEnabled() {
        return this.mysqlEnabled;
    }

    @Override
    public String host() {
        return this.host;
    }

    @Override
    public String username() {
        return this.username;
    }

    @Override
    public String password() {
        return this.password;
    }

    @Override
    public String database() {
        return this.database;
    }

    @Override
    public HikariPool poolSettings() {
        return this.hikariPoolSettings;
    }

    @ConfigSerializable
    public static class HikariPoolImpl implements HikariPool {

        private int maxPoolSize = 6;
        private int minimumIdle = 10;
        private int maxLifeTime = 1800000;
        private int connectionTimeout = 5000;

        @Override
        public void apply(HikariConfig config) {
            config.setMaximumPoolSize(maxPoolSize);
            config.setMinimumIdle(minimumIdle);
            config.setMaxLifetime(maxLifeTime);
            config.setConnectionTimeout(connectionTimeout);
        }


    }
}

