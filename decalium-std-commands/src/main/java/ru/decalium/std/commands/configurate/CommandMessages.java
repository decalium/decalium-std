package ru.decalium.std.commands.configurate;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;


import java.util.Arrays;

import static ru.decalium.std.adventure.message.Message.parsed;
import ru.decalium.std.adventure.message.Message;

@ConfigSerializable
public class CommandMessages {


    public Message noPermission = parsed("У вас нет права на эту команду");

    public Message onlyPlayersCanDoThis = parsed("Эта команда доступна только игрокам.");

    public Message commandNotFound = parsed("Неизвестная команда.");

    public Message invalidSyntax = parsed("Неверный синтаксис команды.");




}
