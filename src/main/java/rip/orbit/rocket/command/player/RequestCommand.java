package rip.orbit.rocket.command.player;

import rip.orbit.nebula.NebulaConstants;
import rip.orbit.nebula.util.FormatUtil;
import rip.orbit.rocket.Rocket;
import rip.orbit.rocket.packet.StaffBroadcastPacket;
import rip.orbit.rocket.util.Cooldown;
import cc.fyre.proton.Proton;
import cc.fyre.proton.command.Command;
import cc.fyre.proton.command.param.Parameter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class  RequestCommand {

    @Command(
            names = {"request","helpop"},
            permission = ""
    )
    public static void execute(Player player,@Parameter(name = "reason",wildcard = true)String reason) {

        if (Rocket.getInstance().getRequestCooldownCache().containsKey(player.getUniqueId()) && Rocket.getInstance().getRequestCooldownCache().get(player.getUniqueId()).getRemaining() > 0) {
            player.sendMessage(ChatColor.RED + "Request Cooldown: " + ChatColor.YELLOW + FormatUtil.millisToTimer(Rocket.getInstance().getRequestCooldownCache().get(player.getUniqueId()).getRemaining()));
            return;
        }

        Rocket.getInstance().getRequestCooldownCache().put(player.getUniqueId(),new Cooldown(60_000L));

        Proton.getInstance().getPidginHandler().sendPacket(new StaffBroadcastPacket(NebulaConstants.STAFF_PERMISSION,ChatColor.GOLD + "[" + ChatColor.GOLD + "Request" + ChatColor.GOLD + "]" +
               ChatColor.GRAY + " (" + ChatColor.YELLOW + Bukkit.getServerName() + ChatColor.GRAY + ") " + ChatColor.GOLD + player.getDisplayName() + ChatColor.GRAY + " needs assistance: " + ChatColor.GOLD + reason)
        );
    }

}
