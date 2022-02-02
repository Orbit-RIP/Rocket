package cc.fyre.piston.command.player;

import cc.fyre.piston.Piston;
import cc.fyre.proton.command.Command;
import cc.fyre.proton.command.param.Parameter;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class MessageCommand {

    @Command(
            names = {"message","msg","tell","whisper","t","w"},
            permission = ""
    )
    public static void execute(Player player,@Parameter(name = "player")Player target,@Parameter(name = "message",wildcard = true)String message) {

        if (!Piston.getInstance().canMessage(player,target)) {
            return;
        }

        if (!Piston.getInstance().getMessageSoundsCache().containsKey(player.getUniqueId()) || Piston.getInstance().getMessageSoundsCache().get(player.getUniqueId())) {
            target.playSound(target.getLocation(),Sound.SUCCESSFUL_HIT,1.0F,1.0F);
        }

        Piston.getInstance().getConversationCache().put(player.getUniqueId(),target.getUniqueId());
        Piston.getInstance().getConversationCache().put(target.getUniqueId(),player.getUniqueId());

        player.sendMessage(ChatColor.GRAY + "(To " + target.getDisplayName() + ChatColor.GRAY + "): " + message);
        target.sendMessage(ChatColor.GRAY + "(From " + player.getDisplayName() + ChatColor.GRAY + "): " + message);

    }
}
