package net.benfro;

import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

public class PluginOne extends Plugin {
    public PluginOne(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        System.out.println("PluginOne.start");
    }

    @Override
    public void stop() {
        System.out.println("PluginOne.stop");
    }

    @Override
    public void delete() {
        System.out.println("PluginOne.delete");
    }
}
