package rip.orbit.rocket.command.staff;

import cc.fyre.neutron.NeutronConstants;
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

        final String displayName = sender instanceof Player ? ((Player)sender).getDisplayName(): NeutronConstants.CONSOLE_NAME;

        Proton.getInstance().getPidginHandler().sendPacket(new StaffBroadcastPacket(NeutronConstants.STAFF_PERMISSION,ChatColor.GOLD + "[Staff]" +
                ChatColor.GRAY + " (" + ChatColor.YELLOW + Bukkit.getServerName() + ChatColor.GRAY + ") " + displayName + ChatColor.GRAY + ": " + ChatColor.YELLOW + message)
        );

    }

}
