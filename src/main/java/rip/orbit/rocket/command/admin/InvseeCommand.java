package rip.orbit.rocket.command.admin;

import cc.fyre.proton.command.Command;
import cc.fyre.proton.command.param.Parameter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class InvseeCommand {

    @Command(names = { "invsee" }, permission = "orbit.staff", description = "Open a player's inventory")
    public static void invsee(Player sender, @Parameter(name = "player")Player target) {

        if (sender.equals(target)) {
            sender.sendMessage(ChatColor.RED + "You can't use this command on yourself!");
            return;
        }

        sender.openInventory(target.getInventory());
    }

}