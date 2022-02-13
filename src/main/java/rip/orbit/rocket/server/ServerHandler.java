package rip.orbit.rocket.server;

import rip.orbit.rocket.Rocket;
import rip.orbit.rocket.RocketConstants;
import cc.fyre.proton.Proton;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ServerHandler {

    @Getter @Setter private Rocket instance;

    @Getter @Setter private boolean frozen;

    public ServerHandler(Rocket instance) {
        this.instance = instance;

        this.frozen = false;
    }

    public void freeze(Player player) {

        player.setMetadata(RocketConstants.FREEZE_METADATA,new FixedMetadataValue(Proton.getInstance(),true));

        this.instance.getServer().getScheduler().runTaskLater(Proton.getInstance(),() -> this.unfreeze(player.getUniqueId()), 20L * TimeUnit.HOURS.toSeconds(2L));

        final Location location = player.getLocation();

        int tries = 0;

        while (1.0 <= location.getY() && !location.getBlock().getType().isSolid() && tries++ < 100) {

            location.subtract(0.0, 1.0, 0.0);

            if (!(location.getY() <= 0.0)) {
                continue;
            }

        }

        if (100 <= tries) {
            this.instance.getLogger().info("Hit the 100 try limit on the freeze command.");
        }

        location.setY(location.getBlockY());

        player.teleport(location.add(0.0,1.0,0.0));
    }

    private void sendDangerSign(Player player, String ... args) {
        String[] lines = new String[]{"", "", "", "", "", "", ""};
        System.arraycopy(args, 0, lines, 0, args.length);
        player.sendMessage(ChatColor.WHITE + "\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588" + ChatColor.RESET);
        player.sendMessage(ChatColor.WHITE + "\u2588\u2588\u2588\u2588" + ChatColor.RED + "\u2588" + ChatColor.WHITE + "\u2588\u2588\u2588\u2588" + ChatColor.RESET + (lines[0].isEmpty() ? "" : new StringBuilder().append(" ").append(lines[0]).toString()));
        player.sendMessage(ChatColor.WHITE + "\u2588\u2588\u2588" + ChatColor.RED + "\u2588" + ChatColor.GOLD + "\u2588" + ChatColor.RED + "\u2588" + ChatColor.WHITE + "\u2588\u2588\u2588" + ChatColor.RESET + (lines[1].isEmpty() ? "" : new StringBuilder().append(" ").append(lines[1]).toString()));
        player.sendMessage(ChatColor.WHITE + "\u2588\u2588" + ChatColor.RED + "\u2588" + ChatColor.GOLD + "\u2588" + ChatColor.BLACK + "\u2588" + ChatColor.GOLD + "\u2588" + ChatColor.RED + "\u2588" + ChatColor.WHITE + "\u2588\u2588" + ChatColor.RESET + (lines[2].isEmpty() ? "" : new StringBuilder().append(" ").append(lines[2]).toString()));
        player.sendMessage(ChatColor.WHITE + "\u2588\u2588" + ChatColor.RED + "\u2588" + ChatColor.GOLD + "\u2588" + ChatColor.BLACK + "\u2588" + ChatColor.GOLD + "\u2588" + ChatColor.RED + "\u2588" + ChatColor.WHITE + "\u2588\u2588" + ChatColor.RESET + (lines[3].isEmpty() ? "" : new StringBuilder().append(" ").append(lines[3]).toString()));
        player.sendMessage(ChatColor.WHITE + "\u2588" + ChatColor.RED + "\u2588" + ChatColor.GOLD + "\u2588\u2588" + ChatColor.BLACK + "\u2588" + ChatColor.GOLD + "\u2588\u2588" + ChatColor.RED + "\u2588" + ChatColor.WHITE + "\u2588" + ChatColor.RESET + (lines[4].isEmpty() ? "" : new StringBuilder().append(" ").append(lines[4]).toString()));
        player.sendMessage(ChatColor.WHITE + "\u2588" + ChatColor.RED + "\u2588" + ChatColor.GOLD + "\u2588\u2588\u2588\u2588\u2588" + ChatColor.RED + "\u2588" + ChatColor.WHITE + "\u2588" + ChatColor.RESET + (lines[5].isEmpty() ? "" : new StringBuilder().append(" ").append(lines[5]).toString()));
        player.sendMessage(ChatColor.RED + "\u2588" + ChatColor.GOLD + "\u2588\u2588\u2588" + ChatColor.BLACK + "\u2588" + ChatColor.GOLD + "\u2588\u2588\u2588" + ChatColor.RED + "\u2588" + ChatColor.RESET + (lines[6].isEmpty() ? "" : new StringBuilder().append(" ").append(lines[6]).toString()));
        player.sendMessage(ChatColor.WHITE + "\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588" + ChatColor.RESET);
    }

    public void unfreeze(UUID uuid) {

        final Player player = this.instance.getServer().getPlayer(uuid);

        if (player == null) {
            return;
        }

        player.removeMetadata(RocketConstants.FREEZE_METADATA,Proton.getInstance());
        player.sendMessage(ChatColor.GREEN + "You have been unfrozen by a staff member.");
    }

}