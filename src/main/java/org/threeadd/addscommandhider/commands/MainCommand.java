package org.threeadd.addscommandhider.commands;

import dev.jorel.commandapi.CommandTree;
import dev.jorel.commandapi.arguments.LiteralArgument;
import dev.jorel.commandapi.executors.CommandArguments;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.threeadd.addscommandhider.AddsCommandHider;

public class MainCommand {

    public MainCommand() {
        new CommandTree("addscommandhider")
                .withPermission("addscommandhider.command")
                .executes(this::helpMessage)
                .then(new LiteralArgument("reload").executes(this::reloadConfig))
                .then(new LiteralArgument("help").executes(this::helpMessage))
                .then(new LiteralArgument("version").executes(this::infoMessage))
                .register("addscommandhider");
    }

    private void helpMessage(CommandSender sender, CommandArguments args) {

        String version = AddsCommandHider.getInstance().getPluginMeta().getVersion();
        sender.sendMessage(textParse("\n<#AE78FF><b>Adds Command Hider </b><#CFAFFF>v" + version + "\n\n" +
                " <dark_gray>- <white>/addscommandhider reload <gray>- <#CFAFFF>Reload config and player commands\n" +
                " <dark_gray>- <white>/addscommandhider help <gray>- <#CFAFFF>Receive the help message\n" +
                " <dark_gray>- <white>/addscommandhider version <gray>- <#CFAFFF>Receive the version\n" +
                "\nBrought to you by 3add\n"));
    }

    private void infoMessage(CommandSender sender, CommandArguments args) {
        String version = AddsCommandHider.getInstance().getPluginMeta().getVersion();
        sender.sendMessage(textParse("Running <#AE78FF><b>Adds Command Hider </b><#CFAFFF>v" + version));
    }

    private void reloadConfig(CommandSender sender, CommandArguments args) {
        AddsCommandHider.getInstance().reloadConfig();
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.updateCommands();
        }
        sender.sendMessage(textParse("<green>Reloading config and player commands..."));
    }

    private Component textParse(String unParsedText) {
        return MiniMessage.miniMessage().deserialize(unParsedText);
    }
}
