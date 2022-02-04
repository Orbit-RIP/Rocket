package rip.orbit.rocket.command.staff;

import rip.orbit.nebula.NebulaConstants;
import rip.orbit.rocket.Rocket;
import cc.fyre.proton.command.Command;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand {

    @Command(
            names = {"clearchat","CC"},
            permission = "piston.command.clearchat"
    )
    public static void execute(CommandSender sender) {

        final String displayName = sender instanceof Player ? ((Player)sender).getDisplayName(): NebulaConstants.CONSOLE_NAME;

        for (Player loopPlayer : Rocket.getInstance().getServer().getOnlinePlayers()) {

            if (loopPlayer.hasPermission(NebulaConstants.STAFF_PERMISSION)) {
                loopPlayer.sendMessage(ChatColor.GREEN + "The chat has been cleared by a staff member");
                continue;
            }

            for (int i = 0; i < 75; i++) {
                loopPlayer.sendMessage("");
            }

        }
    }
}
