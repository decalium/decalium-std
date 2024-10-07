package ru.decalium.std.adventure.message.parser;

import org.jetbrains.annotations.Nullable;

import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Queue;

public final class ArgQueue {

    private final Deque<Arg> arguments;

    public ArgQueue(Deque<Arg> arguments) {
        this.arguments = arguments;
    }

    public Arg pop() throws ParseException {
        try {
            return arguments.pop();
        } catch(NoSuchElementException e) {
            throw new ParseException("No elements left to pop", e);
        }
    }

    public Optional<Arg> peek() {
        return Optional.ofNullable(arguments.peek());
    }

    public boolean hasNext() {
        return !this.arguments.isEmpty();
    }
}
