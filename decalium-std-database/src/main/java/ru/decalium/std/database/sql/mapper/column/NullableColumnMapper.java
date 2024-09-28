package ru.decalium.std.database.sql.mapper.column;

import org.jdbi.v3.core.mapper.ColumnMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface NullableColumnMapper<T> extends ColumnMapper<T> {

    T mapNotNull(ResultSet r, int columnNumber, StatementContext ctx) throws SQLException;

    @Override
    default T map(ResultSet r, int columnNumber, StatementContext ctx) throws SQLException {
        if(r.getObject(columnNumber) == null) return null;
        return mapNotNull(r, columnNumber, ctx);
    }

    static <T> NullableColumnMapper<T> nullable(ColumnMapper<T> mapper) {
        return mapper::map;
    }
}
