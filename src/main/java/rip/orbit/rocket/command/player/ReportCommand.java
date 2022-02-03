package rip.orbit.rocket.command.player;

import cc.fyre.neutron.NeutronConstants;
import cc.fyre.neutron.util.FormatUtil;
import rip.orbit.rocket.Rocket;
import rip.orbit.rocket.packet.StaffBroadcastPacket;
import rip.orbit.rocket.util.Cooldown;
import cc.fyre.proton.Proton;
import cc.fyre.proton.command.Command;
import cc.fyre.proton.command.param.Parameter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ReportCommand {

    @Command(
            names = {"report"},
            permission = ""
    )
    public static void execute(Player player,@Parameter(name = "player")Player target,@Parameter(name = "reason",wildcard = true)String reason) {

        if (Rocket.getInstance().getReportCooldownCache().containsKey(player.getUniqueId()) && Rocket.getInstance().getReportCooldownCache().get(player.getUniqueId()).getRemaining() > 0) {
            player.sendMessage(ChatColor.RED + "Report Cooldown: " + ChatColor.YELLOW + FormatUtil.millisToTimer(Rocket.getInstance().getReportCooldownCache().get(player.getUniqueId()).getRemaining()));
            return;
        }

        Rocket.getInstance().getReportCooldownCache().put(player.getUniqueId(),new Cooldown(60_000L));

        Proton.getInstance().getPidginHandler().sendPacket(new StaffBroadcastPacket(NeutronConstants.STAFF_PERMISSION,ChatColor.GOLD + "[" + ChatColor.GOLD + "Report" + ChatColor.GOLD + "]" +
               ChatColor.GRAY + " (" + ChatColor.YELLOW + Bukkit.getServerName() + ChatColor.GRAY + ") " + player.getDisplayName() + ChatColor.GRAY + " has reported " + target.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.GOLD + reason)
        );
    }

}
