package org.threeadd.addscommandhider.handlers;

import org.bukkit.configuration.file.FileConfiguration;
import org.threeadd.addscommandhider.AddsCommandHider;
import org.threeadd.addscommandhider.objects.HiddenCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConfigParser {

    public static List<HiddenCommand> getBlockedCommands() {
        FileConfiguration config = AddsCommandHider.getInstance().getConfig();

        List<Map<?,?>> unparsedMapList = config.getMapList("AddsCommandHider.BlockedCommands");
        List<HiddenCommand> commandList = new ArrayList<>();
        for (Map<?, ?> unknownMap : unparsedMapList) {

            unknownMap.forEach((command, permission) -> {
                HiddenCommand hiddenCommand = new HiddenCommand(command.toString(), permission.toString());
                commandList.add(hiddenCommand);
            });

        }
        return commandList;
    }

    public static boolean getBlockNamespaceStatus() {
        FileConfiguration config = AddsCommandHider.getInstance().getConfig();
        return config.getBoolean("AddsCommandHider.BlockedNamespaces");

    }
}
