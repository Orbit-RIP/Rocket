package rip.orbit.rocket.command.admin;

import cc.fyre.proton.command.Command;
import cc.fyre.proton.command.flag.Flag;
import cc.fyre.proton.command.param.Parameter;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SudoCommand {

    @Command(
            names = {"sudo"},
            permission = "op"
    )
    public static void sudo(CommandSender sender,@Flag("f")boolean force,@Parameter(name = "player")Player target,@Parameter(name = "command", wildcard = true) String command) {

        boolean oldValue = target.isOp();

        if (force) {
            target.setOp(true);
        }

        target.chat("/" + command);

        if (force) {
            target.setOp(oldValue);
        }

        sender.sendMessage(ChatColor.GREEN + (force ? "Forced":"Made") + " " + ChatColor.WHITE + target.getDisplayName() + ChatColor.GREEN + " to run " + ChatColor.WHITE + "'/" + command + "'" + ChatColor.GREEN + ".");
    }

}
