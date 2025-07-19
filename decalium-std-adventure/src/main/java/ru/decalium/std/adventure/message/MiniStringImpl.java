package ru.decalium.std.adventure.message;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

record MiniStringImpl(String value, MiniMessage miniMessage) implements MiniString {
    @Override
    public @NotNull Component asComponent() {
        return miniMessage.deserialize(this.value);
    }

    @Override
    public MiniString with(TagResolver tagResolver) {
        return new Container(this.value, this.miniMessage, TagResolver.builder().resolver(tagResolver));
    }

    @Override
    public MiniString with(String key, Tag tag) {
        return new Container(this.value, this.miniMessage, TagResolver.builder().tag(key, tag));
    }

    @Override
    public MiniString with(Collection<? extends TagResolver> resolvers) {
        return new Container(this.value, this.miniMessage, TagResolver.builder().resolvers(resolvers));
    }

    public record Container(String value, MiniMessage miniMessage, TagResolver.Builder builder) implements MiniString {

        @Override
        public @NotNull Component asComponent() {
            return miniMessage.deserialize(value, builder.build());
        }

        @Override
        public MiniString with(TagResolver tagResolver) {
            builder.resolver(tagResolver);
            return this;
        }

        @Override
        public MiniString with(String key, Tag tag) {
            builder.tag(key, tag);
            return this;
        }

        @Override
        public MiniString with(Collection<? extends TagResolver> resolvers) {
            builder.resolvers(resolvers);
            return this;
        }
    }
}
