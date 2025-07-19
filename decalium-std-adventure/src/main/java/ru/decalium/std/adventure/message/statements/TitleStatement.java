package ru.decalium.std.adventure.message.statements;

import net.kyori.adventure.audience.Audience;

import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.TitlePart;
import net.kyori.adventure.util.Ticks;
import org.jetbrains.annotations.Nullable;
import ru.decalium.std.adventure.message.Statement;
import ru.decalium.std.adventure.message.MiniString;
import ru.decalium.std.adventure.message.parser.ArgQueue;
import ru.decalium.std.adventure.message.parser.ParseException;
import ru.decalium.std.adventure.message.parser.StatementFactory;

public record TitleStatement(@Nullable MiniString title,
                             @Nullable MiniString subtitle,
                             @Nullable Title.Times times) implements Statement {


    @Override
    public void send(Audience audience, TagResolver resolver) {
        if(times != null) audience.sendTitlePart(TitlePart.TIMES, times);
        if(title != null) audience.sendTitlePart(TitlePart.TITLE, title.asComponent(resolver));
        if(subtitle != null) audience.sendTitlePart(TitlePart.SUBTITLE, subtitle.asComponent(resolver));
    }

    private static @Nullable Title.Times readTimes(ArgQueue queue) throws ParseException {
        Title.Times times = null;
        if(queue.hasNext()) {
            int fadeIn = queue.pop().readInt();
            int stay = queue.pop().readInt();
            int fadeOut = queue.pop().readInt();
            times = Title.Times.times(Ticks.duration(fadeIn), Ticks.duration(stay), Ticks.duration(fadeOut));
        }
        return times;
    }

    public static StatementFactory factory() {
        return queue -> {
            MiniString title = queue.pop().readMiniString();
            MiniString subtitle = null;
            if(queue.hasNext()) {
                subtitle = queue.pop().readMiniString();
            }
            Title.Times times = readTimes(queue);
            return new TitleStatement(title, subtitle, times);
        };
    }

    public static StatementFactory subtitleFactory() {
        return queue -> {
            MiniString subtitle = queue.pop().readMiniString();
            Title.Times times = readTimes(queue);
            return new TitleStatement(null, subtitle, times);
        };
    }
}
