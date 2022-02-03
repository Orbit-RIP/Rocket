package rip.orbit.rocket.command.admin;

import cc.fyre.proton.command.Command;
import cc.fyre.proton.command.param.Parameter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RenameCommand {

    @Command(
            names = {"rename"},
            permission = "piston.command.rename"
    )
    public static void execute(Player player,@Parameter(name = "name",wildcard = true)String name) {

        final ItemStack itemStack = player.getItemInHand();

        if (player.getItemInHand() == null) {
            player.sendMessage(ChatColor.RED + "You are not holding an item.");
            return;
        }

        if (itemStack.getType() == Material.TRIPWIRE_HOOK) {
            player.sendMessage(ChatColor.RED + "You may not rename this item!");
            return;
        }

        final ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',name));
        itemStack.setItemMeta(itemMeta);

        player.sendMessage(ChatColor.GREEN + "Renamed item to: " + ChatColor.translateAlternateColorCodes('&',name));

    }

}
