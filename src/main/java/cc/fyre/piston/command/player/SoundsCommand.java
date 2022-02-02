package cc.fyre.piston.command.player;

import cc.fyre.piston.Piston;
import cc.fyre.proton.command.Command;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SoundsCommand {

    @Command(
            names = {"sounds"},
            permission = ""
    )
    public static void execute(Player player) {

        if (!Piston.getInstance().getMessageSoundsCache().containsKey(player.getUniqueId())) {
            Piston.getInstance().getMessageSoundsCache().put(player.getUniqueId(),false);
        } else {
            Piston.getInstance().getMessageSoundsCache().put(player.getUniqueId(),!Piston.getInstance().getMessageSoundsCache().get(player.getUniqueId()));
        }

        player.sendMessage(ChatColor.GOLD + "Sounds: " + (Piston.getInstance().getMessageSoundsCache().get(player.getUniqueId()) ? ChatColor.GREEN + "Enabled":ChatColor.RED + "Disabled"));
    }

}
