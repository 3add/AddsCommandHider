package org.threeadd.commandHider.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.threeadd.commandHider.CommandHider;
import org.threeadd.commandHider.handlers.ConfigParser;
import org.threeadd.commandHider.objects.HiddenCommand;

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

        CommandHider.getInstance().getLogger().info("Hid " + removedCommands + " commands from " + player.getName());
    }
}
