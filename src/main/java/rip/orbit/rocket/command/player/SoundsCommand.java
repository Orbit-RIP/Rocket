package rip.orbit.rocket.command.player;

import rip.orbit.rocket.Rocket;
import cc.fyre.proton.command.Command;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SoundsCommand {

    @Command(
            names = {"sounds"},
            permission = ""
    )
    public static void execute(Player player) {

        if (!Rocket.getInstance().getMessageSoundsCache().containsKey(player.getUniqueId())) {
            Rocket.getInstance().getMessageSoundsCache().put(player.getUniqueId(),false);
        } else {
            Rocket.getInstance().getMessageSoundsCache().put(player.getUniqueId(),!Rocket.getInstance().getMessageSoundsCache().get(player.getUniqueId()));
        }

        player.sendMessage(ChatColor.GOLD + "Sounds: " + (Rocket.getInstance().getMessageSoundsCache().get(player.getUniqueId()) ? ChatColor.GREEN + "Enabled":ChatColor.RED + "Disabled"));
    }

}
