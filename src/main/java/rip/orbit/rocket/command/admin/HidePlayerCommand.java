package rip.orbit.rocket.command.admin;

import cc.fyre.proton.command.Command;
import cc.fyre.proton.command.param.Parameter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class HidePlayerCommand {

    @Command(
            names = {"hideplayer"},
            permission = "piston.command.hideplayer"
    )
    public static void execute(Player player,@Parameter(name = "player",defaultValue = "self") Player target) {

        if (player == target) {
            player.sendMessage(ChatColor.RED + "You cannot do this.");
            return;
        }

        player.hidePlayer(target);
        player.sendMessage(ChatColor.GREEN + "Now hiding: " + target.getDisplayName());

    }

}
