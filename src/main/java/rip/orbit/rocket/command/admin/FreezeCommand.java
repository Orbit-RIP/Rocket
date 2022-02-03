package rip.orbit.rocket.command.admin;

import rip.orbit.rocket.Rocket;

import cc.fyre.proton.command.Command;
import cc.fyre.proton.command.param.Parameter;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FreezeCommand {

    @Command(
            names = {"freeze"},
            permission = "piston.command.freeze"
    )
    public static void execute(CommandSender sender, @Parameter(name = "player") Player player) {
        Rocket.getInstance().getServerHandler().freeze(player);
        sender.sendMessage(player.getDisplayName() + ChatColor.RED + " has been frozen.");
    }

    @Command(
            names = {"unfreeze"},
            permission = "piston.command.freeze")
    public static void unfreeze(CommandSender sender, @Parameter(name = "player") Player player) {
        Rocket.getInstance().getServerHandler().unfreeze(player.getUniqueId());
        sender.sendMessage(player.getDisplayName() + ChatColor.RED + " has been unfrozen.");
    }

}
