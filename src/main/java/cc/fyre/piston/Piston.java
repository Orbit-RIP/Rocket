package cc.fyre.piston;

import cc.fyre.piston.listener.FreezeListener;
import cc.fyre.piston.server.ServerHandler;

import cc.fyre.proton.Proton;
import cc.fyre.proton.util.ReflectionUtil;
import lombok.Getter;
import cc.fyre.piston.chat.ChatHandler;

import cc.fyre.piston.listener.PistonListener;

import cc.fyre.piston.packet.StaffBroadcastPacket;
import cc.fyre.piston.packet.listener.PistonPacketListener;
import cc.fyre.piston.util.Cooldown;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Piston extends JavaPlugin {

    @Getter private static Piston instance;

    @Getter private long startupTime;

    @Getter private ChatHandler chatHandler;
    @Getter private ServerHandler serverHandler;

    @Getter private Map<UUID,UUID> conversationCache = new HashMap<>();
    @Getter private Map<UUID,Boolean> messageSoundsCache = new HashMap<>();
    @Getter private Map<UUID,Boolean> toggleMessagesCache = new HashMap<>();
    @Getter private Map<UUID,Cooldown> reportCooldownCache = new HashMap<>();
    @Getter private Map<UUID,Cooldown> requestCooldownCache = new HashMap<>();

    @Getter private Map<UUID,Location> backCache = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;

        this.startupTime = System.currentTimeMillis();

        this.getServer().getPluginManager().registerEvents(new FreezeListener(),this);
        this.getServer().getPluginManager().registerEvents(new PistonListener(),this);

        Proton.getInstance().getCommandHandler().registerAll(this);

        ReflectionUtil.setMaxPlayers(this.getServer(),this.getConfig().getInt("server.slots",100));

        this.chatHandler = new ChatHandler(this);
        this.serverHandler = new ServerHandler(this);

        Proton.getInstance().getPidginHandler().registerPacket(StaffBroadcastPacket.class);
        Proton.getInstance().getPidginHandler().registerListener(new PistonPacketListener());

    }

    @Override
    public void onDisable() {
        this.conversationCache.clear();
        this.messageSoundsCache.clear();
        this.toggleMessagesCache.clear();
    }

    public boolean canMessage(Player player,Player target) {

        if (this.toggleMessagesCache.containsKey(player.getUniqueId()) && !this.toggleMessagesCache.get(player.getUniqueId())) {
            player.sendMessage(ChatColor.RED + "You have private messages toggled.");
            return false;
        }

        if (this.toggleMessagesCache.containsKey(target.getUniqueId()) && !this.toggleMessagesCache.get(target.getUniqueId())) {
            player.sendMessage(target.getDisplayName() + ChatColor.RED + " has private messages toggled.");
            return false;
        }

        return true;
    }
}
