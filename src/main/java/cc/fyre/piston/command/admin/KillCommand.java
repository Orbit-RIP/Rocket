package cc.fyre.piston.command.admin;

import cc.fyre.proton.command.Command;
import cc.fyre.proton.command.param.Parameter;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KillCommand {

    @Command(
            names = {"kill"},
            permission = "piston.command.kill"
    )
    public static void execute(Player sender, @Parameter(name = "player", defaultValue = "self") Player player) {

        player.setHealth(0.0);

        if (player.equals(sender)) {
            sender.sendMessage(ChatColor.GREEN + "You have been killed.");
            return;
        }

        sender.sendMessage(player.getDisplayName() + ChatColor.GREEN + " has been killed.");
    }
}
