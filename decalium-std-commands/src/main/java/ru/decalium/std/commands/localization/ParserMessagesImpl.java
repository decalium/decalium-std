package ru.decalium.std.commands.localization;

import org.incendo.cloud.caption.Caption;
import ru.decalium.std.adventure.message.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public final class ParserMessagesImpl implements ParserMessages {

    private final Map<Caption, Message> messageMap;

    public ParserMessagesImpl(Map<Caption, Message> messageMap) {
        this.messageMap = messageMap;
    }

    @Override
    public Optional<Message> get(Caption caption) {
        return Optional.ofNullable(messageMap.get(caption));
    }

    public Map<Caption, Message> messageMap() {
        return messageMap;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ParserMessagesImpl that = (ParserMessagesImpl) object;
        return Objects.equals(messageMap, that.messageMap);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(messageMap);
    }

    @Override
    public String toString() {
        return "ParserMessagesImpl{" +
                "messageMap=" + messageMap +
                '}';
    }

    static class Builder implements ParserMessages.Builder {

        private final Map<Caption, Message> map = new HashMap<>();

        @Override
        public ParserMessages.Builder caption(Caption caption, Message message) {
            map.put(caption, message);
            return this;
        }

        @Override
        public ParserMessages build() {
            return new ParserMessagesImpl(new HashMap<>(map));
        }
    }
}
