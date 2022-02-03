package cc.fyre.piston.listener;

import cc.fyre.neutron.Neutron;
import cc.fyre.neutron.NeutronConstants;
import cc.fyre.piston.Piston;
import cc.fyre.piston.packet.StaffBroadcastPacket;
import cc.fyre.proton.Proton;
import cc.fyre.proton.util.UUIDUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.UUID;

public class PistonListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        if (!player.hasPermission(NeutronConstants.STAFF_PERMISSION)) {
            return;
        }

        Piston.getInstance().getServer().getScheduler().runTaskLater(Piston.getInstance(), () -> Proton.getInstance().getPidginHandler().sendPacket(new StaffBroadcastPacket(NeutronConstants.STAFF_PERMISSION,
                ChatColor.translateAlternateColorCodes('&', "&6[Staff] " + player.getDisplayName() + " &7has joined the server &e" + Bukkit.getServerName() + "&7."))
        ), 20L);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        if (!player.hasPermission(NeutronConstants.STAFF_PERMISSION)) {
            return;
        }

        Proton.getInstance().getPidginHandler().sendPacket(new StaffBroadcastPacket(NeutronConstants.STAFF_PERMISSION,
                ChatColor.translateAlternateColorCodes('&', "&6[Staff] " + player.getDisplayName() + " &7has left the server &e" + Bukkit.getServerName() + "&7."))
        );
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onSignChange(SignChangeEvent event) {

        if (!event.getPlayer().hasPermission(NeutronConstants.ADMIN_PERMISSION)) {
            return;
        }

        final String[] lines = event.getLines();

        for (int i = 0; i < lines.length; i++) {
            event.setLine(i,ChatColor.translateAlternateColorCodes('&',lines[i]));
        }

    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerInteract(PlayerInteractEvent event) {

        if (event.isCancelled()) {
            return;
        }

        if (!event.hasBlock()) {
            return;
        }

        if (event.getClickedBlock().getType() != Material.SKULL) {
            return;
        }

        final Skull skull = (Skull)event.getClickedBlock().getState();

        try {

            final UUID uuid = UUIDUtils.uuid(skull.getOwner());

            event.getPlayer().sendMessage(ChatColor.GREEN + "This is the head of: " + (uuid == null ? ChatColor.WHITE + skull.getOwner():Neutron.getInstance().getProfileHandler().fromUuid(uuid,true).getFancyName()));
        } catch (NullPointerException ignored) {}

    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerTeleport(PlayerTeleportEvent event) {

        final Player player = event.getPlayer();
        final PlayerTeleportEvent.TeleportCause cause = event.getCause();

        if (cause.name().contains("PEARL") || cause.name().contains("PORTAL")) {
            return;
        }

        if (player.hasPermission(NeutronConstants.STAFF_PERMISSION)) {
            Piston.getInstance().getBackCache().put(player.getUniqueId(), event.getFrom());
        }

    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDeath(PlayerDeathEvent event) {

        final Player player = event.getEntity();

        if (player.hasPermission(NeutronConstants.STAFF_PERMISSION)) {
            Piston.getInstance().getBackCache().put(player.getUniqueId(),player.getLocation());
        }

    }

}
