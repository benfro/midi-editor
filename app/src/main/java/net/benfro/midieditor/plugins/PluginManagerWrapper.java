package net.benfro.midieditor.plugins;

import lombok.Getter;
import org.pf4j.DefaultPluginManager;
import org.pf4j.PluginManager;
import org.pf4j.PluginState;
import org.pf4j.PluginWrapper;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public enum PluginManagerWrapper {
    
    INSTANCE;

    @Getter
    private final PluginManager pluginManager = new DefaultPluginManager();

    public List<PluginWrapper> getLoadedPlugins() {
        pluginManager.loadPlugins();
        return pluginManager.getPlugins();
    }

    public PluginState loadAndStartPlugin(Path path) {
        final String pluginId = pluginManager.loadPlugin(path);
        if (Objects.nonNull(pluginId)) {
            return pluginManager.startPlugin(pluginId);
        } else {
            throw new IllegalStateException("Very bad things happened");
        }
    }
}
