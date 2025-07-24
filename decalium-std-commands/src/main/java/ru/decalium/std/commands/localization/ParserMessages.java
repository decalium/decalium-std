package ru.decalium.std.commands.localization;

import org.incendo.cloud.caption.Caption;
import org.incendo.cloud.caption.CaptionVariable;
import org.incendo.cloud.exception.parsing.ParserException;
import ru.decalium.std.adventure.message.Message;

import java.util.Optional;

public interface ParserMessages {

    Optional<Message> get(Caption caption);

    default Optional<Message> getFormatted(Caption caption, CaptionVariable... variables) {
        return get(caption).map(message -> message.with(new CaptionVariableResolver(variables)));
    }

    default Optional<Message> getFormatted(ParserException exception) {
        return getFormatted(exception.errorCaption(), exception.captionVariables());
    }

    static Builder builder() {
        return new ParserMessagesImpl.Builder();
    }

    interface Builder {

        Builder caption(Caption caption, Message message);

        default Builder caption(Caption caption, String message) {
            return caption(caption, Message.parsed(message));
        }

        ParserMessages build();
    }
}
