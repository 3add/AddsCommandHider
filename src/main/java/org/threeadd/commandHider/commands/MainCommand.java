package org.threeadd.commandHider.commands;


import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandTree;
import dev.jorel.commandapi.RegisteredCommand;
import dev.jorel.commandapi.arguments.AbstractArgument;
import dev.jorel.commandapi.arguments.LiteralArgument;
import dev.jorel.commandapi.executors.CommandArguments;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.threeadd.commandHider.CommandHider;

import java.util.List;

public class MainCommand {

    public MainCommand() {
        new CommandTree("commandhider")
                .withPermission("commandhider.command")
                .executes(this::helpMessage)
                .then(
                        new LiteralArgument("reload")
                                .executes(this::reloadConfig)
                )
                .then(
                        new LiteralArgument("help")
                                .executes(this::helpMessage)
                )
                .register("commandhider");
    }

    private void helpMessage(CommandSender sender, CommandArguments args) {

        String version = CommandHider.getInstance().getPluginMeta().getVersion();
        sender.sendMessage(textParse("\n<#AE78FF><b>COMMAND HIDER </b><#CFAFFF>v" + version + "\n" +
                " <dark_gray>- <white>/commandhider reload <gray>- <#CFAFFF>Reload config and player commands\n" +
                " <dark_gray>- <white>/commandhider help <gray>- <#CFAFFF>Receive the help message" +
                "\n\nBrought to you by 3add\n"));
    }

    private void reloadConfig(CommandSender sender, CommandArguments args) {
        CommandHider.getInstance().reloadConfig();
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.updateCommands();
        }
        sender.sendMessage(textParse("<green>Reloading config and player commands..."));
    }

    private Component textParse(String unParsedText) {
        return MiniMessage.miniMessage().deserialize(unParsedText);
    }
}
