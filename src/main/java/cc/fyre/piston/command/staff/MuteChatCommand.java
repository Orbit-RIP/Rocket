package cc.fyre.piston.command.staff;

import cc.fyre.neutron.NeutronConstants;
import cc.fyre.piston.Piston;
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

        Piston.getInstance().getChatHandler().setMuted(!Piston.getInstance().getChatHandler().isMuted());

        final String displayName = sender instanceof Player ? ((Player)sender).getDisplayName(): NeutronConstants.CONSOLE_NAME;

        for (Player loopPlayer : Piston.getInstance().getServer().getOnlinePlayers()) {
            if (!loopPlayer.hasPermission(NeutronConstants.STAFF_PERMISSION)) {
                loopPlayer.sendMessage(ChatColor.LIGHT_PURPLE + "Chat has been " + (Piston.getInstance().getChatHandler().isMuted() ? "muted":"unmuted") + ".");
                continue;
            }

            loopPlayer.sendMessage(ChatColor.LIGHT_PURPLE + "Chat has been " + (Piston.getInstance().getChatHandler().isMuted() ? "muted":"unmuted") + " by " + displayName + ".");
        }
    }
}
