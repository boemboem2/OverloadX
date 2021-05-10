package server.model.players.packets;

import server.Config;
import server.model.players.Client;
import server.model.players.PacketType;

/**
 * Trading
 */
public class Trade implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int tradeId = c.getInStream().readSignedWordBigEndian();
		c.getPA().resetFollow();
		
		if(c.arenas()) {
			c.sendMessage("You can't trade inside the arena!");
			return;
		}
		if (c.tradeTimer > 0) {
			c.sendMessage("You must wait 30 minutes to trade after creating an account.");
			return;
		}
		if(c.playerRights == 4 && !Config.ADMIN_CAN_TRADE) {
			c.sendMessage("Trading as an admin has been disabled.");
			return;
		}
		if(c.inWild()) {  
			c.sendMessage("You can't trade in the Wilderness!");
			return;
		}
		if (tradeId != c.playerId)
			c.getTradeAndDuel().requestTrade(tradeId);
	}
		
}
