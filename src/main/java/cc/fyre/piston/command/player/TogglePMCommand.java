package cc.fyre.piston.command.player;

import cc.fyre.piston.Piston;
import cc.fyre.proton.command.Command;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TogglePMCommand {

    @Command(
            names = {"togglepm","tpm"},
            permission = ""
    )
    public static void execute(Player player) {

        if (!Piston.getInstance().getToggleMessagesCache().containsKey(player.getUniqueId())) {
            Piston.getInstance().getToggleMessagesCache().put(player.getUniqueId(),false);
        } else {
            Piston.getInstance().getToggleMessagesCache().put(player.getUniqueId(),!Piston.getInstance().getToggleMessagesCache().get(player.getUniqueId()));
        }

        player.sendMessage(ChatColor.GOLD + "Messages: " + (Piston.getInstance().getToggleMessagesCache().get(player.getUniqueId()) ? ChatColor.GREEN + "Enabled":ChatColor.RED + "Disabled"));
    }

}
