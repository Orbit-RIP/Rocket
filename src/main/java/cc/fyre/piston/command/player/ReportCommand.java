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

public class ReportCommand {

    @Command(
            names = {"report"},
            permission = ""
    )
    public static void execute(Player player,@Parameter(name = "player")Player target,@Parameter(name = "reason",wildcard = true)String reason) {

        if (Piston.getInstance().getReportCooldownCache().containsKey(player.getUniqueId()) && Piston.getInstance().getReportCooldownCache().get(player.getUniqueId()).getRemaining() > 0) {
            player.sendMessage(ChatColor.RED + "Report Cooldown: " + ChatColor.YELLOW + FormatUtil.millisToTimer(Piston.getInstance().getReportCooldownCache().get(player.getUniqueId()).getRemaining()));
            return;
        }

        Piston.getInstance().getReportCooldownCache().put(player.getUniqueId(),new Cooldown(60_000L));

        Proton.getInstance().getPidginHandler().sendPacket(new StaffBroadcastPacket(NeutronConstants.STAFF_PERMISSION,ChatColor.GRAY + "[" + ChatColor.BLUE + "Report" + ChatColor.GRAY + "]" +
                "[" + ChatColor.BLUE + Bukkit.getServerName() + ChatColor.GRAY + "] " + player.getDisplayName() + ChatColor.GRAY + " has reported " + target.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.GRAY + reason)
        );
    }

}