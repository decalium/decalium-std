package ru.decalium.std.commands.configurate;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.incendo.cloud.minecraft.extras.MinecraftHelp;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.Map;

@ConfigSerializable
public final class HelpMessageProvider implements MinecraftHelp.MessageProvider<Audience> {

    private Component title = Component.text("Help");
    private Component command = Component.text("Command");
    private Component description = Component.text("Description");
    private Component noDescription = Component.text("No description");
    private Component arguments = Component.text("Arguments");
    private Component optional = Component.text("Optional");
    private Component searchResults = Component.text("Showing search results for query");
    private Component noResults = Component.text("No results for query");
    private Component availableCommands = Component.text("Available commands");
    private Component clickToShowHelp = Component.text("Click to show help for this command");
    private Component pageOutOfRange = Component.text("Error: Page <page> is not in range. Must be in range [1, <max_pages>]");
    private Component clickForNextPage = Component.text("Click for next page");
    private Component clickForPreviousPage = Component.text("Click for previous page");
    @Override
    public @NonNull Component provide(@NonNull Audience sender, @NonNull String key, @NonNull Map<String, String> args) {
        return switch (key) {
            case MinecraftHelp.MESSAGE_HELP_TITLE -> this.title;
            case MinecraftHelp.MESSAGE_COMMAND -> this.command;
            case MinecraftHelp.MESSAGE_DESCRIPTION -> this.description;
            case MinecraftHelp.MESSAGE_NO_DESCRIPTION -> this.noDescription;
            case MinecraftHelp.MESSAGE_ARGUMENTS -> this.arguments;
            case MinecraftHelp.MESSAGE_OPTIONAL -> this.optional;
            case MinecraftHelp.MESSAGE_SHOWING_RESULTS_FOR_QUERY -> this.searchResults;
            case MinecraftHelp.MESSAGE_NO_RESULTS_FOR_QUERY -> this.noResults;
            case MinecraftHelp.MESSAGE_AVAILABLE_COMMANDS -> this.availableCommands;
            case MinecraftHelp.MESSAGE_PAGE_OUT_OF_RANGE -> this.pageOutOfRange;
            case MinecraftHelp.MESSAGE_CLICK_TO_SHOW_HELP -> this.clickToShowHelp;
            case MinecraftHelp.MESSAGE_CLICK_FOR_NEXT_PAGE -> this.clickForNextPage;
            case MinecraftHelp.MESSAGE_CLICK_FOR_PREVIOUS_PAGE -> this.clickForPreviousPage;
            default -> throw new IllegalArgumentException("Unknown message");
        };
    }
}
