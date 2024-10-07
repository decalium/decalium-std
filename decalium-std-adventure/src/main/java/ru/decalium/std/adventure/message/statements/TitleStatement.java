package ru.decalium.std.adventure.message.statements;

import net.kyori.adventure.audience.Audience;

import net.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.TitlePart;
import net.kyori.adventure.util.Ticks;
import org.jetbrains.annotations.Nullable;
import ru.decalium.std.adventure.message.Statement;
import ru.decalium.std.adventure.message.TextMessage;
import ru.decalium.std.adventure.message.parser.ArgQueue;
import ru.decalium.std.adventure.message.parser.ParseException;
import ru.decalium.std.adventure.message.parser.StatementFactory;

public record TitleStatement(@Nullable TextMessage title,
                             @Nullable TextMessage subtitle,
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
            TextMessage title = queue.pop().readText();
            TextMessage subtitle = null;
            if(queue.hasNext()) {
                subtitle = queue.pop().readText();
            }
            Title.Times times = readTimes(queue);
            return new TitleStatement(title, subtitle, times);
        };
    }

    public static StatementFactory subtitleFactory() {
        return queue -> {
            TextMessage subtitle = queue.pop().readText();
            Title.Times times = readTimes(queue);
            return new TitleStatement(null, subtitle, times);
        };
    }
}
