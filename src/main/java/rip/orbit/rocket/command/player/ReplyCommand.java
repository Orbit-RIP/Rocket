package rip.orbit.rocket.command.player;

import rip.orbit.rocket.Rocket;
import cc.fyre.proton.command.Command;
import cc.fyre.proton.command.param.Parameter;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ReplyCommand {

    @Command(
            names = {"reply","r"},
            permission = ""
    )
    public static void execute(Player player,@Parameter(name = "message",wildcard = true)String message) {

        if (!Rocket.getInstance().getConversationCache().containsKey(player.getUniqueId()) || Rocket.getInstance().getConversationCache().get(player.getUniqueId()) == null) {
            player.sendMessage(ChatColor.RED + "You have no one to reply to.");
            return;
        }

        final Player target = Rocket.getInstance().getServer().getPlayer(Rocket.getInstance().getConversationCache().get(player.getUniqueId()));

        if (target == null) {
            player.sendMessage(ChatColor.RED + "That player is no longer online.");
            return;
        }

        if (!Rocket.getInstance().canMessage(player,target)) {
            return;
        }

        if (!Rocket.getInstance().getMessageSoundsCache().containsKey(target.getUniqueId()) || Rocket.getInstance().getMessageSoundsCache().get(target.getUniqueId())) {
            target.playSound(player.getLocation(),Sound.SUCCESSFUL_HIT,1.0F,1.0F);
        }

        Rocket.getInstance().getConversationCache().put(player.getUniqueId(),target.getUniqueId());
        Rocket.getInstance().getConversationCache().put(target.getUniqueId(),player.getUniqueId());

        player.sendMessage(ChatColor.GRAY + "(To " + target.getDisplayName() + ChatColor.GRAY + "): " + message);
        target.sendMessage(ChatColor.GRAY + "(From " + player.getDisplayName() + ChatColor.GRAY + "): " + message);

    }

}
