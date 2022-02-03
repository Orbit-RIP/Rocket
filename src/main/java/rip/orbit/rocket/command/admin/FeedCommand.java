package rip.orbit.rocket.command.admin;

import cc.fyre.proton.command.Command;
import cc.fyre.proton.command.param.Parameter;
import org.bukkit.entity.Player;

public class FeedCommand {

    @Command(
            names = {"feed"},
            permission = "piston.command.feed"
    )
    public static void execute(Player player,@Parameter(name = "player",defaultValue = "self") Player target) {
        target.setFoodLevel(20);
    }

}
