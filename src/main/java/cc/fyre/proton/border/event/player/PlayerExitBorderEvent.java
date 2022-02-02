package cc.fyre.proton.border.event.player;

import lombok.Getter;
import cc.fyre.proton.border.Border;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerExitBorderEvent extends PlayerBorderEvent {

    @Getter private final Location from;
    @Getter private final Location to;

    public PlayerExitBorderEvent(Border border,Player player,Location from,Location to) {
        super(border, player);
        this.from = from;
        this.to = to;
    }

}
