package ru.decalium.std.adventure.message.parser;

import ru.decalium.std.adventure.message.statements.ActionBarStatement;
import ru.decalium.std.adventure.message.statements.ChatStatement;
import ru.decalium.std.adventure.message.statements.SoundStatement;
import ru.decalium.std.adventure.message.statements.TitleStatement;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class StatementRegistry {

    public static final StatementRegistry DEFAULT = builder()
                    .factory("chat", ChatStatement.factory())
                    .factory("sound", SoundStatement.factory())
                    .factory("title", TitleStatement.factory())
                    .factory("subtitle", TitleStatement.subtitleFactory())
                    .factory("action", ActionBarStatement.factory())
                    .factory("actionbar", ActionBarStatement.factory())
                    .build();


    private final Map<String, StatementFactory> factories;

    public StatementRegistry(Map<String, StatementFactory> factories) {
        this.factories = factories;
    }

    public Optional<StatementFactory> factory(String name) {
        return Optional.ofNullable(factories.get(name));
    }

    public static StatementRegistry.Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Map<String, StatementFactory> factories = new HashMap<>();

        Builder() {}

        public Builder factory(String name, StatementFactory factory) {
            this.factories.put(name, factory);
            return this;
        }

        public Builder all(StatementRegistry registry) {
            this.factories.putAll(registry.factories);
            return this;
        }

        public StatementRegistry build() {
            return new StatementRegistry(Map.copyOf(factories));
        }
    }
}
