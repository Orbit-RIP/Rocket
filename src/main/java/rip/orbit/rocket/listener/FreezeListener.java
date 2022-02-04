package rip.orbit.rocket.listener;

import rip.orbit.nebula.NebulaConstants;
import rip.orbit.rocket.Rocket;
import rip.orbit.rocket.RocketConstants;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;

public class FreezeListener implements Listener {

    private static final String FROZEN_MESSAGE = ChatColor.RED + "You cannot do this while frozen.";

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerKick(PlayerKickEvent event) {

        final Player player = event.getPlayer();

        if (!player.hasMetadata(RocketConstants.FREEZE_METADATA)) {
            return;
        }

        player.removeMetadata(RocketConstants.FREEZE_METADATA, Rocket.getInstance());
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerQuit(PlayerQuitEvent event) {

        final Player player = event.getPlayer();

        if (!player.hasMetadata(RocketConstants.FREEZE_METADATA)) {
            return;
        }

        player.removeMetadata(RocketConstants.FREEZE_METADATA, Rocket.getInstance());

        for (Player loopPlayer : Rocket.getInstance().getServer().getOnlinePlayers()) {

            if (!loopPlayer.hasPermission(NebulaConstants.STAFF_PERMISSION)) {
                continue;
            }

            loopPlayer.sendMessage("");
            loopPlayer.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + player.getName() + " logged out while frozen!");
            loopPlayer.sendMessage("");
        }

    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerMove(PlayerMoveEvent event) {

        final Player player = event.getPlayer();

        if (!player.hasMetadata(RocketConstants.FREEZE_METADATA)) {
            return;
        }

        final Location to = event.getTo();
        final Location from = event.getFrom();

        if (from.getX() != to.getX() || event.getFrom().getZ() != event.getTo().getZ()) {

            final Location newLocation = from.getBlock().getLocation().add(0.5, 0.0, 0.5);

            newLocation.setPitch(to.getPitch());
            newLocation.setYaw(to.getYaw());

            event.setTo(newLocation);
        }

    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityDamage(EntityDamageEvent event) {

        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        final Player player = (Player) event.getEntity();

        if (!player.hasMetadata(RocketConstants.FREEZE_METADATA)) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        if (event.getDamager().hasMetadata(RocketConstants.FREEZE_METADATA)) {
            event.setCancelled(true);
            ((Player) event.getDamager()).sendMessage(FROZEN_MESSAGE);
        }

        if (event.getEntity() instanceof Player && event.getEntity().hasMetadata(RocketConstants.FREEZE_METADATA)) {
            ((Player) event.getDamager()).sendMessage(((Player) event.getEntity()).getDisplayName() + ChatColor.RED + " is currently frozen and cannot be damaged.");
        }

    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryClick(InventoryClickEvent event) {

        final Player player = (Player) event.getWhoClicked();

        if (!player.hasMetadata(RocketConstants.FREEZE_METADATA)) {
            return;
        }

        event.setCancelled(true);
        player.sendMessage(FROZEN_MESSAGE);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerDropItem(PlayerDropItemEvent event) {

        final Player player = event.getPlayer();

        if (!player.hasMetadata(RocketConstants.FREEZE_METADATA)) {
            return;
        }

        event.setCancelled(true);

        player.updateInventory();
        player.sendMessage(FROZEN_MESSAGE);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerInteract(PlayerInteractEvent event) {

        final Player player = event.getPlayer();

        if (!player.hasMetadata(RocketConstants.FREEZE_METADATA)) {
            return;
        }

        event.setCancelled(true);

        player.updateInventory();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockBreak(BlockBreakEvent event) {

        final Player player = event.getPlayer();

        if (!player.hasMetadata(RocketConstants.FREEZE_METADATA)) {
            return;
        }

        event.setCancelled(true);

        player.updateInventory();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockPlace(BlockPlaceEvent event) {

        final Player player = event.getPlayer();

        if (!player.hasMetadata(RocketConstants.FREEZE_METADATA)) {
            return;
        }

        event.setCancelled(true);

        player.updateInventory();
    }

}