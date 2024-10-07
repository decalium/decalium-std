package ru.decalium.std.adventure.message.parser;

import ru.decalium.std.adventure.message.Statement;

public interface StatementFactory {

    Statement create(ArgQueue queue) throws ParseException;
}
