package ru.decalium.std.adventure.message;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

record TextMessageImpl(String value, MiniMessage miniMessage) implements TextMessage {
    @Override
    public @NotNull Component asComponent() {
        return miniMessage.deserialize(this.value);
    }

    @Override
    public TextMessage with(TagResolver tagResolver) {
        return new Container(this.value, this.miniMessage, TagResolver.builder().resolver(tagResolver));
    }

    @Override
    public TextMessage with(String key, Tag tag) {
        return new Container(this.value, this.miniMessage, TagResolver.builder().tag(key, tag));
    }

    @Override
    public TextMessage with(Collection<? extends TagResolver> resolvers) {
        return new Container(this.value, this.miniMessage, TagResolver.builder().resolvers(resolvers));
    }

    public record Container(String value, MiniMessage miniMessage, TagResolver.Builder builder) implements TextMessage {

        @Override
        public @NotNull Component asComponent() {
            return miniMessage.deserialize(value, builder.build());
        }

        @Override
        public TextMessage with(TagResolver tagResolver) {
            builder.resolver(tagResolver);
            return this;
        }

        @Override
        public TextMessage with(String key, Tag tag) {
            builder.tag(key, tag);
            return this;
        }

        @Override
        public TextMessage with(Collection<? extends TagResolver> resolvers) {
            builder.resolvers(resolvers);
            return this;
        }
    }
}
