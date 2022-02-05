package rip.orbit.rocket.command.admin;

import rip.orbit.rocket.Rocket;
import cc.fyre.proton.command.Command;
import cc.fyre.proton.command.param.Parameter;
import cc.fyre.proton.util.ReflectionUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class SetSlotsCommand {

    @Command(
            names = {"setslots", "setmaxslots", "setservercap", "ssc"},
            permission = "op"
    )
    public static void execute(CommandSender sender,@Parameter(name = "slots") int slots) {

        if (slots < 0) {
            sender.sendMessage(ChatColor.RED + "The number of slots must be greater or equal to zero.");
            return;
        }

        ReflectionUtil.setMaxPlayers(Rocket.getInstance().getServer(),slots);

        Rocket.getInstance().getConfig().set("server.slots",slots);
        Rocket.getInstance().saveConfig();

        sender.sendMessage(ChatColor.GREEN + "Slots set to " + ChatColor.WHITE + slots + ChatColor.GREEN + ".");
    }

}
