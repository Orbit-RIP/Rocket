package cc.fyre.piston.command.staff;

import cc.fyre.neutron.NeutronConstants;
import cc.fyre.piston.Piston;
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

        final String displayName = sender instanceof Player ? ((Player)sender).getDisplayName(): NeutronConstants.CONSOLE_NAME;

        for (Player loopPlayer : Piston.getInstance().getServer().getOnlinePlayers()) {

            if (loopPlayer.hasPermission(NeutronConstants.STAFF_PERMISSION)) {
                loopPlayer.sendMessage(ChatColor.GREEN + "The chat has been cleared by a staff member");
                continue;
            }

            for (int i = 0; i < 75; i++) {
                loopPlayer.sendMessage("");
            }

        }
    }
}
