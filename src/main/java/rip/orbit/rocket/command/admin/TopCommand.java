package rip.orbit.rocket.command.admin;

import cc.fyre.proton.command.Command;
import org.bukkit.entity.Player;

public class TopCommand {

    @Command(names = {"top"}, permission = "piston.command.top")
    public static void execute(Player player) {
        player.teleport(player.getWorld().getHighestBlockAt(player.getLocation()).getLocation());
    }

}
