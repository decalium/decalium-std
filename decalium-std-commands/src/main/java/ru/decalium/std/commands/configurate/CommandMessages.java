package ru.decalium.std.commands.configurate;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import ru.decalium.action.message.Message;
import ru.decalium.action.message.impl.ActionParser;

import java.util.Arrays;

@ConfigSerializable
public class CommandMessages {

    private static final ActionParser PARSER = new ActionParser(MiniMessage.miniMessage());

    public Message noPermission = message("У вас нет права на эту команду");

    public Message onlyPlayersCanDoThis = message("Эта команда доступна только игрокам.");

    public Message commandNotFound = message("Неизвестная команда.");

    public Message invalidSyntax = message("Неверный синтаксис команды.");


    private static Message message(String... messages) {
        return Message.create(PARSER.parse(Arrays.asList(messages)));
    }

}
