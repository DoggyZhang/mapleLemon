package handling.channel.handler;

import client.MapleCharacter;
import client.MapleClient;
import handling.MaplePacketHandler;
import handling.vo.recv.ChangeMapSpecialRecvVO;
import server.MaplePortal;
import tools.MaplePacketCreator;
import tools.data.input.SeekableLittleEndianAccessor;

public class ChangeMapSpecialHandler extends MaplePacketHandler<ChangeMapSpecialRecvVO> {


    @Override
    public void handlePacket(ChangeMapSpecialRecvVO recvVO, MapleClient c) {
        MapleCharacter chr = c.getPlayer();
        String portal_name = recvVO.getPortalName();
        if ((chr == null) || (chr.getMap() == null)) {
            return;
        }
        MaplePortal portal = chr.getMap().getPortal(portal_name);
        if ((portal != null) && (!chr.hasBlockedInventory())) {
            portal.enterPortal(c);
        } else {
            c.sendPacket(MaplePacketCreator.enableActions());
        }
    }
}
