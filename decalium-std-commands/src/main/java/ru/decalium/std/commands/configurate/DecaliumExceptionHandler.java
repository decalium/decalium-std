package ru.decalium.std.commands.configurate;

import net.kyori.adventure.audience.Audience;
import org.incendo.cloud.annotations.exception.ExceptionHandler;
import org.incendo.cloud.exception.*;

public class DecaliumExceptionHandler {

    private final CommandMessages messages;

    public DecaliumExceptionHandler(CommandMessages messages) {
        this.messages = messages;
    }

    @ExceptionHandler(ArgumentParseException.class)
    public void argumentParseFailed(Audience sender, ArgumentParseException exception) {
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
