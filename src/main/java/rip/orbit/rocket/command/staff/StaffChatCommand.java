package rip.orbit.rocket.command.staff;

import rip.orbit.nebula.NebulaConstants;
import rip.orbit.rocket.packet.StaffBroadcastPacket;
import cc.fyre.proton.Proton;
import cc.fyre.proton.command.Command;
import cc.fyre.proton.command.param.Parameter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChatCommand {

    @Command(
            names = {"staffchat","sc"},
            permission = "piston.command.staffchat"
    )
    public static void execute(CommandSender sender,@Parameter(name = "message",wildcard = true)String message) {

        final String displayName = sender instanceof Player ? ((Player)sender).getDisplayName(): NebulaConstants.CONSOLE_NAME;

        Proton.getInstance().getPidginHandler().sendPacket(new StaffBroadcastPacket(NebulaConstants.STAFF_PERMISSION,ChatColor.GOLD + "[Staff]" +
                ChatColor.GRAY + " (" + ChatColor.YELLOW + Bukkit.getServerName() + ChatColor.GRAY + ") " + displayName + ChatColor.GRAY + ": " + ChatColor.YELLOW + message)
        );

    }

}
