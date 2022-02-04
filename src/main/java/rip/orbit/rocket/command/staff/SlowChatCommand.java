package rip.orbit.rocket.command.staff;

import rip.orbit.nebula.NebulaConstants;
import rip.orbit.rocket.Rocket;
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

        final String displayName = sender instanceof Player ? ((Player)sender).getName(): NebulaConstants.CONSOLE_NAME;

        if (Rocket.getInstance().getChatHandler().getSlowTime() > 0) {
            Rocket.getInstance().getChatHandler().setSlowTime(0);
            Rocket.getInstance().getServer().broadcastMessage(ChatColor.GREEN + "Public chat has been unslowed" + ChatColor.GREEN + ".");
            return;
        }

        Rocket.getInstance().getChatHandler().setSlowTime(seconds);
        Rocket.getInstance().getServer().broadcastMessage(ChatColor.GREEN + "Public chat has been slowed by " + displayName + ".");
    }

}
