package org.threeadd.commandHider;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.threeadd.commandHider.commands.MainCommand;
import org.threeadd.commandHider.listeners.CommandSendListener;

public final class CommandHider extends JavaPlugin {
    static CommandHider instance;

    public static CommandHider getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this)
                .verboseOutput(false)
        );
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        this.getLogger().info("Started CommandHider v" + this.getPluginMeta().getVersion() + "by 3add");
        Bukkit.getPluginManager().registerEvents(new CommandSendListener(), this);

        saveResource("config.yml", false);
        saveDefaultConfig();

        CommandAPI.onEnable();

        new MainCommand();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        CommandAPI.onDisable();
    }
}
