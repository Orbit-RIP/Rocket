package rip.orbit.rocket.command.admin;

import rip.orbit.rocket.event.PlayerClearInventoryEvent;
import cc.fyre.proton.command.Command;
import cc.fyre.proton.command.param.Parameter;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.stream.Stream;

public class ClearCommand {

    @Command(
            names = {"clear", "ci"},
            permission = "orbit.seniormod"
    )
    public static void execute(CommandSender sender,@Parameter(name = "player",defaultValue = "self") Player target) {

        if (!(sender instanceof Player) && target == sender) {
            sender.sendMessage(Color.RED + "/clear " + "<player>");
            return;
        }

        //https://stackoverflow.com/questions/80476/how-can-i-concatenate-two-arrays-in-java

        final ItemStack[] contents = Stream.concat(
                Arrays.stream(target.getInventory().getContents()),
                Arrays.stream(target.getInventory().getArmorContents()))
                .toArray(ItemStack[]::new);

        final PlayerClearInventoryEvent event = new PlayerClearInventoryEvent(sender,target,contents);

        event.call();

        if (event.isCancelled()) {
            sender.sendMessage(ChatColor.RED + "Failed to clear " + target.getDisplayName() + ChatColor.RED + "'s inventory.");
            return;
        }

        target.getInventory().clear();
        target.getInventory().setArmorContents(null);

        sender.sendMessage(ChatColor.GOLD + "You have cleared " + (target == sender ? "your": target.getDisplayName() + ChatColor.GOLD + "'s") + ChatColor.GOLD + " inventory.");
    }

}
