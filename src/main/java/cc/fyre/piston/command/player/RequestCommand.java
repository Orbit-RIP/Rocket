package cc.fyre.piston.command.player;

import cc.fyre.neutron.NeutronConstants;
import cc.fyre.neutron.util.FormatUtil;
import cc.fyre.piston.Piston;
import cc.fyre.piston.packet.StaffBroadcastPacket;
import cc.fyre.piston.util.Cooldown;
import cc.fyre.proton.Proton;
import cc.fyre.proton.command.Command;
import cc.fyre.proton.command.param.Parameter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class RequestCommand {

    @Command(
            names = {"request","helpop"},
            permission = ""
    )
    public static void execute(Player player,@Parameter(name = "reason",wildcard = true)String reason) {

        if (Piston.getInstance().getRequestCooldownCache().containsKey(player.getUniqueId()) && Piston.getInstance().getRequestCooldownCache().get(player.getUniqueId()).getRemaining() > 0) {
            player.sendMessage(ChatColor.RED + "Request Cooldown: " + ChatColor.YELLOW + FormatUtil.millisToTimer(Piston.getInstance().getRequestCooldownCache().get(player.getUniqueId()).getRemaining()));
            return;
        }

        Piston.getInstance().getRequestCooldownCache().put(player.getUniqueId(),new Cooldown(60_000L));

        Proton.getInstance().getPidginHandler().sendPacket(new StaffBroadcastPacket(NeutronConstants.STAFF_PERMISSION,ChatColor.GRAY + "[" + ChatColor.GOLD + "Request" + ChatColor.GRAY + "]" +
                "[" + ChatColor.GOLD + Bukkit.getServerName() + ChatColor.GRAY + "] " + player.getDisplayName() + ChatColor.GRAY + " has requested assistance: " + ChatColor.WHITE + reason)
        );
    }

}
