package server.model.players.packets;

import server.Server;
import server.model.players.Client;
import server.model.players.PacketType;

/**
 * Walking packet
 **/
public class Walking implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {		
		if (packetType == 248 || packetType == 164) {
			c.faceUpdate(0);
			c.npcIndex = 0;
			c.playerIndex = 0;
			if (c.followId > 0 || c.followId2 > 0)
				c.getPA().resetFollow();
		}		
		c.getPA().removeAllWindows();
		if(c.duelRule[1] && c.duelStatus == 5) {
			if(Server.playerHandler.players[c.duelingWith] != null) { 
				if(!c.goodDistance(c.getX(), c.getY(), Server.playerHandler.players[c.duelingWith].getX(), Server.playerHandler.players[c.duelingWith].getY(), 1) || c.attackTimer == 0) {
					c.sendMessage("Walking has been disabled in this duel!");
				}
			}
			c.playerIndex = 0;	
			return;		
		}
		
		if(c.freezeTimer > 0) {
			if(Server.playerHandler.players[c.playerIndex] != null) {
				if(c.goodDistance(c.getX(), c.getY(), Server.playerHandler.players[c.playerIndex].getX(), Server.playerHandler.players[c.playerIndex].getY(), 1) && packetType != 98) {
					c.playerIndex = 0;	
					return;
				}
			}
			if (packetType != 98) {
				c.sendMessage("A magical force stops you from moving.");
				c.playerIndex = 0;
			}	
			return;
		}
		
		if (System.currentTimeMillis() - c.lastSpear < 4000) {
			c.sendMessage("You have been stunned.");
			c.playerIndex = 0;
			return;
		}
		
		if (packetType == 98) {
			c.mageAllowed = true;
		}

		if (!c.inEdge()) {
			//c.frame74(7);
		}
		if (!c.inWildsong()) {
			//c.frame74(172);
		}
		if (!c.inDonatorzone() && c.memberStatus == 0) {
			c.getPA().movePlayer(3087, 3500, 0);
			c.sendMessage("@red@None donators can only preview the island, no moving is allowed.");
		} else if (!c.inDonatorzone()) {
			c.frame74(317);
		}

		if (!c.inArena() && c.duelStatus <= 4) {
			c.getPA().movePlayer(3367, 3267, 0);
		}
		if (!c.inStaffzone() && c.playerRights == 0) {
			c.getPA().movePlayer(3087, 3500, 0);
			c.sendMessage("@red@You are not a Staff member!");
		}
		if(c.fishing == true) {
			c.fishing = false;
		}
		if (!c.inGhostspot() && c.messageText == 0) {
			c.forcedChat("I think I should leave through the exit.");
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			c.messageText = 1;
			} else if(c.messageText == 1) {
		}
		if((c.duelStatus >= 1 && c.duelStatus <= 4) || c.duelStatus == 6) {
			if(c.duelStatus == 6) {
				c.getTradeAndDuel().claimStakedItems();		
			}
			return;
		}
		
		
		if(c.respawnTimer > 3) {
			return;
		}
		if(c.inTrade) {
			return;
		}
		if(packetType == 248) {
			packetSize -= 14;
		}
		c.newWalkCmdSteps = (packetSize - 5)/2;
		if(++c.newWalkCmdSteps > c.walkingQueueSize) {
			c.newWalkCmdSteps = 0;
			return;
		}
		
		c.getNewWalkCmdX()[0] = c.getNewWalkCmdY()[0] = 0;
		
		int firstStepX = c.getInStream().readSignedWordBigEndianA()-c.getMapRegionX()*8;
		for(int i = 1; i < c.newWalkCmdSteps; i++) {
			c.getNewWalkCmdX()[i] = c.getInStream().readSignedByte();
			c.getNewWalkCmdY()[i] = c.getInStream().readSignedByte();
		}
		
		int firstStepY = c.getInStream().readSignedWordBigEndian()-c.getMapRegionY()*8;
		c.setNewWalkCmdIsRunning(c.getInStream().readSignedByteC() == 1);
		for(int i1 = 0; i1 < c.newWalkCmdSteps; i1++) {
			c.getNewWalkCmdX()[i1] += firstStepX;
			c.getNewWalkCmdY()[i1] += firstStepY;
		}
	}

}
