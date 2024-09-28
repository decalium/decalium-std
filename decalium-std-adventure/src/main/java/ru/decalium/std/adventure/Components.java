package ru.decalium.std.adventure;

import net.kyori.adventure.text.BuildableComponent;
import net.kyori.adventure.text.Component;

import java.util.function.UnaryOperator;

public final class Components {

    private Components() {}


    public static Component mapChildrenDeep(Component component, UnaryOperator<Component> mapper) {
        BuildableComponent<?, ?> buildable = (BuildableComponent<?, ?>) component;
        return buildable.toBuilder().mapChildrenDeep(c -> (BuildableComponent<?, ?>) mapper.apply(c))
                .build();
    }
}
