package ru.decalium.std.adventure.message;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

import java.util.Collection;
import java.util.List;

record MessageImpl(List<Statement> statements) implements Message {
    @Override
    public Message with(TagResolver tagResolver) {
        return new Container(statements, TagResolver.builder().resolver(tagResolver));
    }

    @Override
    public Message with(String key, Tag tag) {
        return new Container(statements, TagResolver.builder().tag(key, tag));
    }

    @Override
    public Message with(Collection<? extends TagResolver> resolvers) {
        return new Container(statements, TagResolver.builder().resolvers(resolvers));
    }

    @Override
    public void send(Audience audience, TagResolver resolver) {
        for(Statement statement : this.statements) statement.send(audience, resolver);
    }

    record Container(List<Statement> statements, TagResolver.Builder builder) implements Message {

        @Override
        public Message with(TagResolver tagResolver) {
            builder.resolver(tagResolver);
            return this;
        }

        @Override
        public Message with(String key, Tag tag) {
            builder.tag(key, tag);
            return this;
        }

        @Override
        public Message with(Collection<? extends TagResolver> resolvers) {
            builder.resolvers(resolvers);
            return this;
        }

        @Override
        public void send(Audience audience, TagResolver resolver) {
            TagResolver r = TagResolver.resolver(resolver, this.builder.build());
            for(Statement statement : this.statements) statement.send(audience, r);
        }
    }
}
