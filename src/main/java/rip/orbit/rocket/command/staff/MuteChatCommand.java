package rip.orbit.rocket.command.staff;

import rip.orbit.nebula.NebulaConstants;
import rip.orbit.rocket.Rocket;
import cc.fyre.proton.command.Command;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteChatCommand {

    @Command(
            names = {"mutechat","mc"},
            permission = "piston.command.mutechat"
    )
    public static void execute(CommandSender sender) {

        Rocket.getInstance().getChatHandler().setMuted(!Rocket.getInstance().getChatHandler().isMuted());

        final String displayName = sender instanceof Player ? ((Player)sender).getDisplayName(): NebulaConstants.CONSOLE_NAME;

        for (Player loopPlayer : Rocket.getInstance().getServer().getOnlinePlayers()) {
            if (!loopPlayer.hasPermission(NebulaConstants.STAFF_PERMISSION)) {
                loopPlayer.sendMessage(ChatColor.LIGHT_PURPLE + "Chat has been " + (Rocket.getInstance().getChatHandler().isMuted() ? "muted":"unmuted") + ".");
                continue;
            }

            loopPlayer.sendMessage(ChatColor.GREEN + "Public chat has been " + (Rocket.getInstance().getChatHandler().isMuted() ? "muted":"unmuted") + ".");
        }
    }
}
