package org.threeadd.addscommandhider.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.threeadd.addscommandhider.AddsCommandHider;
import org.threeadd.addscommandhider.handlers.ConfigParser;
import org.threeadd.addscommandhider.objects.HiddenCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandSendListener implements Listener {

    @EventHandler
    public void onEvent(PlayerCommandSendEvent event) {
        Player player = event.getPlayer();
        int removedCommands = 0;

        // Manual Blocked Commands
        for (HiddenCommand blockedCommand : ConfigParser.getBlockedCommands()) {
            String permission = blockedCommand.getBypassPermission();
            String label = blockedCommand.getCommandLabel();

            if (!player.hasPermission(permission)) {
                event.getCommands().remove(label);
                removedCommands++;
            }
        }

        // Namespaces
        List<String> availableCommands = new ArrayList<>(event.getCommands());
        for (String command : availableCommands) {
            if (!player.hasPermission("CommandHider.bypass.namespacing")) {
                if (command.contains(":")) {
                    if (ConfigParser.getBlockNamespaceStatus()) {
                        event.getCommands().remove(command);
                        removedCommands++;
                    }
                }
            }
        }

        AddsCommandHider.getInstance().getLogger().info("Hid " + removedCommands + " commands from " + player.getName());
    }
}
