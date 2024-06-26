package rip.orbit.rocket.packet.listener;

import cc.fyre.proton.pidgin.packet.handler.IncomingPacketHandler;
import cc.fyre.proton.pidgin.packet.listener.PacketListener;
import rip.orbit.rocket.packet.StaffBroadcastPacket;

public class PistonPacketListener implements PacketListener {

    @IncomingPacketHandler
    public void onStaffBroadcast(StaffBroadcastPacket packet) {
        packet.broadcast();
    }

}
