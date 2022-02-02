package cc.fyre.piston.command.staff;

import cc.fyre.neutron.NeutronConstants;
import cc.fyre.piston.Piston;
import cc.fyre.proton.command.Command;
import cc.fyre.proton.command.param.Parameter;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author xanderume@gmail (JavaProject)
 */
public class SlowChatCommand {

    @Command(
            names = {"slowchat"},
            permission = "piston.command.slowchat"
    )
    public static void execute(CommandSender sender,@Parameter(name = "seconds",defaultValue = "5")int seconds) {

        final String displayName = sender instanceof Player ? ((Player)sender).getDisplayName(): NeutronConstants.CONSOLE_NAME;

        if (Piston.getInstance().getChatHandler().getSlowTime() > 0) {
            Piston.getInstance().getChatHandler().setSlowTime(0);
            Piston.getInstance().getServer().broadcastMessage(ChatColor.LIGHT_PURPLE + "Public chat has been unslowed by " + displayName + ChatColor.LIGHT_PURPLE + ".");
            return;
        }

        Piston.getInstance().getChatHandler().setSlowTime(seconds);
        Piston.getInstance().getServer().broadcastMessage(ChatColor.LIGHT_PURPLE + "Public chat has been slowed by " + displayName + ChatColor.LIGHT_PURPLE + ".");
    }

}
