package rip.orbit.rocket.command.admin;

import rip.orbit.nebula.NebulaConstants;
import rip.orbit.rocket.Rocket;

import cc.fyre.proton.command.Command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * @author xanderume@gmail (JavaProject)
 */
public class FreezeServerCommand {

    @Command(names = { "freezeserver" }, permission = "orbit.headstaff", description = "Freeze the server. Normal players won't be able to move or interact")
    public static void execute(final CommandSender sender) {
        Rocket.getInstance().getServerHandler().setFrozen(!Rocket.getInstance().getServerHandler().isFrozen());

        Rocket.getInstance().getServer().getOnlinePlayers().forEach(loopPlayer -> {

            if (!loopPlayer.hasPermission(NebulaConstants.STAFF_PERMISSION)) {
                loopPlayer.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "The server has been " + (Rocket.getInstance().getServerHandler().isFrozen() ? "" : "un") + "frozen.");
            } else {
                loopPlayer.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "The server has been " + (Rocket.getInstance().getServerHandler().isFrozen() ? "" : "un") + "frozen by " + sender.getName() + ".");
            }

        });

    }

}
