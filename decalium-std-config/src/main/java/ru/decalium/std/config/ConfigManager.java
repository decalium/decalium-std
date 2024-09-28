package ru.decalium.std.config;

import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationOptions;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.yaml.NodeStyle;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.File;
import java.util.function.UnaryOperator;

public final class ConfigManager {

    private final UnaryOperator<ConfigurationOptions> defaultOptions;
    private final File dataFolder;


    public ConfigManager(UnaryOperator<ConfigurationOptions> defaultOptions, File dataFolder) {
        this.defaultOptions =  defaultOptions;
        this.dataFolder = dataFolder;
    }

    public ConfigManager withOptions(UnaryOperator<ConfigurationOptions> options) {
        return new ConfigManager(opts -> options.apply(this.defaultOptions.apply(opts)), this.dataFolder);
    }

    private YamlConfigurationLoader loader(File file) {
        return YamlConfigurationLoader.builder().defaultOptions(defaultOptions).file(file)
                .nodeStyle(NodeStyle.BLOCK).indent(2).build();
    }

    public <C> C loadConfig(Class<C> clazz, C config, String filename) {
        File configFile = new File(dataFolder, filename);
        YamlConfigurationLoader loader = loader(configFile);
        try {
            if(configFile.exists()) {
                config = loader.load().require(clazz);
            }
            var node = loader.createNode();
            node.set(clazz, config);
            loader.save(node);
        } catch (ConfigurateException e) {
            throw new RuntimeException(e);
        }
        return config;
    }

    public <C> void saveConfig(Class<C> clazz, C config, String filename) {
        YamlConfigurationLoader loader = loader(new File(dataFolder, filename));
        var node = loader.createNode();
        try {
            node.set(clazz, config);
            loader.save(node);
        } catch (ConfigurateException e) {
            throw new RuntimeException(e);
        }
    }
}
