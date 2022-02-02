package cc.fyre.piston.command.admin;

import cc.fyre.piston.Piston;
import cc.fyre.piston.PistonConstants;
import cc.fyre.proton.Proton;
import cc.fyre.proton.command.Command;
import cc.fyre.proton.command.param.Parameter;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ScreenShareCommand {

    @Command(
            names={"screenshare", "ss"},
            permission="piston.command.freeze"
    )
    public static void screenshare(CommandSender sender, @Parameter(name="player") Player player) {
        Piston.getInstance().getServerHandler().freeze(player);
        sender.sendMessage(ChatColor.YELLOW + "You froze " + player.getDisplayName() + "!");
        new BukkitRunnable(){

            @Override
            public void run() {
                if (!player.hasMetadata(PistonConstants.FREEZE_METADATA)) {
                    this.cancel();
                    return;
                }
                for (int i = 0; i < 3; ++i) {
                    player.sendMessage("");
                }
                ScreenShareCommand.sendDangerSign(player, "", ChatColor.DARK_RED.toString() + "Do " + ChatColor.BOLD + "NOT" + ChatColor.DARK_RED + " log out!", ChatColor.RED + "If you do, you will be banned!", ChatColor.YELLOW + "Please download " + ChatColor.BOLD + "TeamSpeak 3" + ChatColor.YELLOW + "", ChatColor.YELLOW + "and join ts.orbit.rip", "", "");
                player.sendMessage("");
            }
        }.runTaskTimer(Proton.getInstance(), 0L, 100L);
    }

    private static void sendDangerSign(Player player, String ... args) {
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

}

