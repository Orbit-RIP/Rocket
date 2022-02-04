package rip.orbit.rocket.command.staff;

import rip.orbit.nebula.NebulaConstants;
import rip.orbit.rocket.Rocket;
import cc.fyre.proton.command.Command;
import cc.fyre.proton.command.flag.Flag;
import cc.fyre.proton.command.param.Parameter;
import cc.fyre.proton.command.param.defaults.offlineplayer.OfflinePlayerWrapper;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TeleportCommand {

    @Command(names = { "teleport", "tp", "tpto", "goto" }, permission = "piston.command.teleport", description = "Teleport yourself to a player")
    public static void execute(Player player,@Parameter(name = "player")OfflinePlayerWrapper offlinePlayerWrapper) {

        final Player target = offlinePlayerWrapper.loadSync().getPlayer();

        if (target == null) {
            player.sendMessage(ChatColor.RED + "No online or offline player with the name " + offlinePlayerWrapper.getName() + " found.");
            return;
        }

        player.teleport(target);
        player.sendMessage(ChatColor.GREEN + "Teleporting you to " + (player.isOnline() ? "" : "offline player ") + ChatColor.WHITE + target.getDisplayName() + ChatColor.GREEN + ".");
    }

    @Command(names = { "tphere", "bring", "s" }, permission = "piston.command.teleport.other", description = "Teleport a player to you")
    public static void execute(Player player, @Parameter(name = "player") Player target, @Flag(value = { "s", "silent" }, description = "Silently teleport the player (staff members always get messaged)")boolean silent) {
        target.teleport(player);

        player.sendMessage(ChatColor.GREEN + "Teleporting " + ChatColor.WHITE + target.getDisplayName() + ChatColor.GREEN + " to you.");

        if (!silent || target.hasPermission(NebulaConstants.STAFF_PERMISSION)) {
            target.sendMessage(ChatColor.GREEN + "Teleporting you to " + ChatColor.WHITE + player.getDisplayName() + ChatColor.GREEN + ".");
        }

    }

    @Command(names = { "back" }, permission = "piston.command.teleport", description = "Teleport to your last location")
    public static void execute(Player player,@Parameter(name = "player",defaultValue = "self")Player target) {

        if (!Rocket.getInstance().getBackCache().containsKey(target.getUniqueId())) {
            player.sendMessage(ChatColor.RED + "No previous location recorded.");
            return;
        }

        target.teleport(Rocket.getInstance().getBackCache().get(target.getUniqueId()));
        target.sendMessage(ChatColor.GREEN + "Teleporting " + (target.getUniqueId().equals(player.getUniqueId()) ? "":target.getDisplayName()) + "to " + (target.getUniqueId().equals(player.getUniqueId()) ? "your":"their") + " last recorded location.");
    }

    @Command(names = { "tppos" }, permission = "piston.command.teleport", description = "Teleport to coordinates")
    public static void execute(Player player, @Parameter(name = "x") double x, @Parameter(name = "y") double y, @Parameter(name = "z") double z, @Parameter(name = "player", defaultValue = "self") Player target) {

        if (!player.equals(target) && !player.hasPermission("piston.command.teleport.other")) {
            player.sendMessage(ChatColor.RED + "No permission to teleport other players.");
            return;
        }

        if (isBlock(x)) {
            x += ((z >= 0.0) ? 0.5 : -0.5);
        }

        if (isBlock(z)) {
            z += ((x >= 0.0) ? 0.5 : -0.5);
        }

        target.teleport(new Location(target.getWorld(), x, y, z));

        final String location = ChatColor.translateAlternateColorCodes('&',String.format("&e[&f%s&e, &f%s&e, &f%s&e]&6", x, y, z));

        if (!player.equals(target)) {
            player.sendMessage(ChatColor.GREEN + "Teleporting " + ChatColor.WHITE + target.getDisplayName() + ChatColor.GREEN + " to " + location + ".");
        }

        target.sendMessage(ChatColor.GREEN + "Teleporting you to " + location + ".");
    }

    private static boolean isBlock(double value) {
        return value % 1.0 == 0.0;
    }

}
