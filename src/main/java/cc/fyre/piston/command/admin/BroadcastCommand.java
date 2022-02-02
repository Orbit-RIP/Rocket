package cc.fyre.piston.command.admin;

import cc.fyre.piston.Piston;
import cc.fyre.proton.command.Command;
import cc.fyre.proton.command.param.Parameter;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class BroadcastCommand {

    @Command(
            names = {"broadcast","bc","raw"},
            permission = "piston.command.raw"
    )
    public static void execute(CommandSender sender,@Parameter(name = "message",wildcard = true)String message) {
        Piston.getInstance().getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',message));
    }

}
