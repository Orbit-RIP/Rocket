package rip.orbit.rocket.event;

import rip.orbit.rocket.Rocket;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * @author xanderume@gmail (JavaProject)
 */
public class PlayerClearInventoryEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    @Getter private CommandSender sender;
    @Getter private Player target;
    @Getter private ItemStack[] contents;

    public PlayerClearInventoryEvent(CommandSender sender,Player target,ItemStack[] contents) {
        this.sender = sender;
        this.target = target;
        this.contents = contents;
    }

    @Getter @Setter private boolean cancelled = false;

    @Override public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public void call() {
        Rocket.getInstance().getServer().getPluginManager().callEvent(this);
    }

}
