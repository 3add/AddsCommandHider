package org.threeadd.commandHider.objects;

public class HiddenCommand {

    String commandLabel;
    String bypassPermission;

    public HiddenCommand(String commandLabel, String bypassPermission) {
        this.commandLabel = commandLabel;
        this.bypassPermission = bypassPermission;
    }

    public String getCommandLabel() {
        return commandLabel;
    }

    public String getBypassPermission() {
        return bypassPermission;
    }

}
