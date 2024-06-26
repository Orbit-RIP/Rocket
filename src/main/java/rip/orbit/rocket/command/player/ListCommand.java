package rip.orbit.rocket.command.player;

import rip.orbit.nebula.Nebula;
import rip.orbit.nebula.rank.Rank;
import rip.orbit.rocket.Rocket;
import cc.fyre.proton.Proton;
import cc.fyre.proton.command.Command;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListCommand {

    @Command(
            names = {"list","online","players"},
            permission = ""
    )
    public static void execute(CommandSender sender) {

        sender.sendMessage(StringUtils.join(Nebula.getInstance().getRankHandler().getSortedValueCache()
                .stream()
                .map(Rank::getFancyName)
                .collect(Collectors.toList()), new StringBuilder().append(ChatColor.WHITE).append(", ").toString())
        );

        sender.sendMessage(ChatColor.WHITE + "(" + getSortedPlayers(sender,true).size() + "/" + Rocket.getInstance().getServer().getMaxPlayers() + ")" + " " + "[" + StringUtils.join(getSortedPlayers(sender,true).stream()
                        .map(Player::getDisplayName)
                        .collect(Collectors.toList()),
                new StringBuilder().append(ChatColor.WHITE).append(", ").toString()) + ChatColor.WHITE + "]"
        );

    }

    public static List<Player> getSortedPlayers(CommandSender sender,boolean canSeeOnly) {

        final List<Player> toReturn = new ArrayList<>();

        for (Player loopPlayer : Rocket.getInstance().getServer().getOnlinePlayers()) {

            if ((sender instanceof Player) && canSeeOnly && !Proton.getInstance().getVisibilityHandler().treatAsOnline(loopPlayer,(Player)sender)) {
                continue;
            }

            toReturn.add(loopPlayer);
        }


        return toReturn.stream().sorted(new PlayerComparator().reversed()).collect(Collectors.toList());
    }

    static class PlayerComparator implements Comparator<Player> {

        @Override
        public int compare(Player player,Player otherPlayer) {
            return Integer.compare(Nebula.getInstance().getProfileHandler().fromUuid(player.getUniqueId()).getActiveGrant().getRank().getWeight().get(),Nebula.getInstance().getProfileHandler().fromUuid(otherPlayer.getUniqueId()).getActiveGrant().getRank().getWeight().get());
        }
    }

}
