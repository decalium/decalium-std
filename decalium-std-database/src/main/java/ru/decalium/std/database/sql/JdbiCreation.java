package ru.decalium.std.database.sql;

import com.zaxxer.hikari.HikariDataSource;
import org.jdbi.v3.core.Jdbi;
import ru.decalium.std.database.sql.argument.InstantArgumentFactory;
import ru.decalium.std.database.sql.argument.UUIDArgumentFactory;
import ru.decalium.std.database.sql.mapper.column.UUIDMapper;

import java.time.Instant;
import java.util.UUID;

public class JdbiCreation {

    private final HikariDataSource source;

    public JdbiCreation(HikariDataSource source) {
        this.source = source;
    }


    public Jdbi create() {
        Jdbi jdbi = Jdbi.create(source);
        jdbi.registerArgument(new UUIDArgumentFactory())
                .registerArgument(new InstantArgumentFactory());

        jdbi.registerColumnMapper(UUID.class, new UUIDMapper())
                .registerColumnMapper(Instant.class, (r, n, ctx) -> r.getTimestamp(n).toInstant()); // instant
        return jdbi;
    }

}
