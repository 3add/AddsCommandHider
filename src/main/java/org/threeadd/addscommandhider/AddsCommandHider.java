package org.threeadd.addscommandhider;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.threeadd.addscommandhider.commands.MainCommand;
import org.threeadd.addscommandhider.listeners.CommandSendListener;

public final class AddsCommandHider extends JavaPlugin {
    static AddsCommandHider instance;

    public static AddsCommandHider getInstance() {
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
