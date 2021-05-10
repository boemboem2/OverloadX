package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
import server.Connection;
import server.util.Misc;

/**
 * Chat : Modified by Alex. 
 **/
public class Chat implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		c.setChatTextEffects(c.getInStream().readUnsignedByteS());
		c.setChatTextColor(c.getInStream().readUnsignedByteS());
        c.setChatTextSize((byte)(c.packetSize - 2));
        c.inStream.readBytes_reverseA(c.getChatText(), c.getChatTextSize(), 0);
			String[] stuffz = {"full -1 "}; //add more!
					String term = Misc.textUnpack(c.getChatText(), c.packetSize - 2).toLowerCase();
			if(c.said >= 1){
			c.sendMessage("Don't even try :)");
			c.logout();
			}
			for(int i = 0; i < stuffz.length; i++) {
					if(term.contains(stuffz[i])) {
					c.said++;
					c.sendMessage("Please, do not use that term!");
						return;
					}
					}
		if (!Connection.isMuted(c))
			c.setChatTextUpdateRequired(true);
	}	
}