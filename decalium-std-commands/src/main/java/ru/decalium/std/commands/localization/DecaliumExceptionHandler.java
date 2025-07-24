package ru.decalium.std.commands.localization;

import net.kyori.adventure.audience.Audience;
import org.incendo.cloud.annotations.exception.ExceptionHandler;
import org.incendo.cloud.exception.*;
import org.incendo.cloud.exception.parsing.ParserException;
import ru.decalium.std.commands.configurate.CommandMessages;

public class DecaliumExceptionHandler {

    private final CommandMessages messages;
    private final ParserMessages parserMessages;

    public DecaliumExceptionHandler(CommandMessages messages, ParserMessages parserMessages) {
        this.messages = messages;
        this.parserMessages = parserMessages;
    }

    @ExceptionHandler(ArgumentParseException.class)
    public void argumentParseFailed(Audience sender, ArgumentParseException exception) {
        if(exception.getCause() instanceof ParserException ex) {
            var message = parserMessages.getFormatted(ex).orElse(null);
            if(message != null) {
                message.send(sender);
                return;
            }
        }
        messages.invalidSyntax.send(sender);
    }

    @ExceptionHandler(InvalidCommandSenderException.class)
    public void invalidAudience(Audience sender) {
        messages.onlyPlayersCanDoThis.send(sender);
    }

    @ExceptionHandler(InvalidSyntaxException.class)
    public void invalidSyntax(Audience sender, InvalidSyntaxException exception) {
        messages.invalidSyntax.send(sender);
    }

    @ExceptionHandler(NoPermissionException.class)
    public void noPermission(Audience sender, NoPermissionException exception) {
        messages.noPermission.send(sender);
    }

    @ExceptionHandler(NoSuchCommandException.class)
    public void noSuchCommand(Audience sender, NoSuchCommandException exception) {
        messages.commandNotFound.send(sender);
    }
}
