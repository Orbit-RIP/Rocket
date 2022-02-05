package rip.orbit.rocket.command.admin;

import cc.fyre.proton.command.Command;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class FlyCommand {

    @Command(
            names = {"fly"},
            permission = "orbit.staff"
    )
    public static void execute(Player player) {

         player.setAllowFlight(!player.getAllowFlight());
         player.sendMessage(ChatColor.GOLD + "Fly: " + (player.getAllowFlight() ? ChatColor.GREEN + "Enabled":ChatColor.RED + "Disabled"));

    }

}
