package rip.orbit.rocket.command.admin;

import cc.fyre.proton.command.Command;
import org.bukkit.entity.Player;

public class CraftCommand {

    @Command(
            names = {"craft","workbench"},
            permission = "orbit.donator"
    )
    public static void execute(Player player) {
        player.openWorkbench(player.getLocation(),true);
    }

}
