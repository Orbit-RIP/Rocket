package rip.orbit.rocket.command.admin;

import cc.fyre.proton.command.Command;
import org.bukkit.entity.Player;

public class CraftCommand {

    @Command(
            names = {"craft","workbench"},
            permission = "piston.command.craft"
    )
    public static void execute(Player player) {
        player.openWorkbench(player.getLocation(),true);
    }

}