package kr.rvs.kkutu.network.packet;

/**
 * Created by Junhyeong Lim on 2017-10-07.
 */
public interface PacketHandler {
    void handle(PacketHandlers handlers, Packet packet);
}
