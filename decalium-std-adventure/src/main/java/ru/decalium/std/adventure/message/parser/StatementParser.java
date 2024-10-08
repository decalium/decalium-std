package ru.decalium.std.adventure.message.parser;

import com.google.common.base.Splitter;
import net.kyori.adventure.text.minimessage.MiniMessage;
import ru.decalium.std.adventure.message.Statement;
import ru.decalium.std.adventure.message.TextMessage;
import ru.decalium.std.adventure.message.statements.ChatStatement;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StatementParser {

    public static final StatementParser DEFAULT_PARSER = new StatementParser(MiniMessage.miniMessage(), StatementRegistry.DEFAULT);

    private static final Pattern STATEMENT_PATTERN = Pattern.compile("\\[([a-zA-Z\\d_]+)](.+)");

    private static final Splitter SPLITTER = Splitter.on(Pattern.compile(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"));

    private final MiniMessage miniMessage;
    private final StatementRegistry statementRegistry;

    public StatementParser(MiniMessage miniMessage, StatementRegistry statementRegistry) {
        this.miniMessage = miniMessage;
        this.statementRegistry = statementRegistry;
    }

    private Statement parseStatement(String string) {
        Matcher matcher = STATEMENT_PATTERN.matcher(string);
        if (!matcher.matches()) {
            return new ChatStatement(string, miniMessage);
        }
        String type = matcher.group(1).toLowerCase();
        StatementFactory factory = statementRegistry.factory(type).orElse(null);
        if(factory == null) return new ChatStatement(string, miniMessage);
        Deque<Arg> args = new ArrayDeque<>();
        for(String s : SPLITTER.split(matcher.group(2))) {
            args.add(new Arg(s.strip(), this.miniMessage));
        }
        try {
            return factory.create(new ArgQueue(args));
        } catch (ParseException e) {
            return new ChatStatement(string, miniMessage);
        }
    }

    public ParsedStatement parse(String string) {
        return new ParsedStatement(string, parseStatement(string));
    }
}
