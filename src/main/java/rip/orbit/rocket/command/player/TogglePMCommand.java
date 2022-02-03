package rip.orbit.rocket.command.player;

import rip.orbit.rocket.Rocket;
import cc.fyre.proton.command.Command;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TogglePMCommand {

    @Command(
            names = {"togglepm","tpm"},
            permission = ""
    )
    public static void execute(Player player) {

        if (!Rocket.getInstance().getToggleMessagesCache().containsKey(player.getUniqueId())) {
            Rocket.getInstance().getToggleMessagesCache().put(player.getUniqueId(),false);
        } else {
            Rocket.getInstance().getToggleMessagesCache().put(player.getUniqueId(),!Rocket.getInstance().getToggleMessagesCache().get(player.getUniqueId()));
        }

        player.sendMessage(ChatColor.GOLD + "Messages: " + (Rocket.getInstance().getToggleMessagesCache().get(player.getUniqueId()) ? ChatColor.GREEN + "Enabled":ChatColor.RED + "Disabled"));
    }

}
