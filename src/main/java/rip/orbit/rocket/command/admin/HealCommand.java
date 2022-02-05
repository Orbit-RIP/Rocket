package rip.orbit.rocket.command.admin;


import cc.fyre.proton.command.Command;
import cc.fyre.proton.command.param.Parameter;
import org.bukkit.entity.Player;

public class HealCommand {

    @Command(
            names = {"heal"},
            permission = "orbit.admin"
    )
    public static void execute(Player player,@Parameter(name = "player",defaultValue = "self") Player target) {
        target.setHealth(20);
    }

}
