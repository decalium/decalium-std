package ru.decalium.std.adventure.message.statements;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import ru.decalium.std.adventure.message.Statement;
import ru.decalium.std.adventure.message.parser.StatementFactory;


public record SoundStatement(Sound sound) implements Statement {

    @Override
    public void send(Audience audience, TagResolver resolver) {
        audience.playSound(sound);
    }

    public static StatementFactory factory() {
        return queue -> {
            Key key = queue.pop().readKey();
            float volume = 1f;
            float pitch = 1f;
            if(queue.hasNext()) volume = queue.pop().readFloat();
            if(queue.hasNext()) pitch = queue.pop().readFloat();
            return new SoundStatement(Sound.sound(key, Sound.Source.MASTER, volume, pitch));
        };
    }
}
