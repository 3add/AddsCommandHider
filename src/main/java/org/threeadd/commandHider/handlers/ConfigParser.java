package org.threeadd.commandHider.handlers;

import org.bukkit.configuration.file.FileConfiguration;
import org.threeadd.commandHider.CommandHider;
import org.threeadd.commandHider.objects.HiddenCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConfigParser {

    public static List<HiddenCommand> getBlockedCommands() {
        FileConfiguration config = CommandHider.getInstance().getConfig();

        List<Map<?,?>> unparsedMapList = config.getMapList("CommandHider.BlockedCommands");
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
        FileConfiguration config = CommandHider.getInstance().getConfig();
        return config.getBoolean("CommandHider.BlockedNamespaces");

    }
}
