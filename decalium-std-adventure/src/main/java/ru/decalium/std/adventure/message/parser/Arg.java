package ru.decalium.std.adventure.message.parser;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.ParsingException;
import net.kyori.adventure.text.minimessage.internal.parser.ParsingExceptionImpl;
import ru.decalium.std.adventure.message.TextMessage;


public final class Arg {

    private final String value;
    private final MiniMessage miniMessage;

    public Arg(String value, MiniMessage miniMessage) {
        this.value = value;
        this.miniMessage = miniMessage;
    }

    public int readInt() throws ParseException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid integer: " + this.value, e);
        }
    }

    public float readFloat() throws ParseException {
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid float: " + this.value, e);
        }
    }

    public String readString() {
        return this.value;
    }

    public Key readKey() throws ParseException {
        if(!Key.parseable(this.value)) {
            throw new ParseException("Invalid key: " + this.value);
        }
        return Key.key(this.value);
    }

    public TextMessage readText() {
        return TextMessage.message(this.value, this.miniMessage);
    }
}
