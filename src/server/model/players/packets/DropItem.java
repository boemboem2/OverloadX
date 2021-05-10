package server.model.players.packets;

import server.Config;
import server.Server;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.PlayerSave;

/**
 * Drop Item
 **/
public class DropItem implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int itemId = c.getInStream().readUnsignedWordA();
		c.getInStream().readUnsignedByte();
		c.getInStream().readUnsignedByte();
		int slot = c.getInStream().readUnsignedWordA();
		c.alchDelay = System.currentTimeMillis();
		if (Server.playerHandler.players[c.playerId].underAttackBy != 0) {
			if ((c.getShops().getItemShopValue(itemId)*.75) > 1000) {
			c.sendMessage("You can't drop items worth over 1k in combat.");
			return;
			}
		}
		if(c.inTrade) {
			c.sendMessage("You can't drop items while trading!");
			return;
		}
		if(!c.getItems().playerHasItem(itemId,1,slot)) {
			return;
		}
		if(c.tradeTimer > 0) {
			c.sendMessage("You must wait until your starter timer is up to drop items.");
			return;
		}
		if(c.arenas()) {
			c.sendMessage("You can't drop items inside the arena!");
			return;
		}

		boolean droppable = true;
		for (int i : Config.UNDROPPABLE_ITEMS) {
			if (i == itemId) {
				droppable = false;
				break;
			}
		}
		if(c.playerItemsN[slot] != 0 && itemId != -1 && c.playerItems[slot] == itemId + 1) {
			if(droppable) {
				if (c.underAttackBy > 0) {
					if (c.getShops().getItemShopValue(itemId) > 1000) {
						c.sendMessage("You may not drop items worth more than 1000 while in combat.");
						return;
					}
				}
				Server.itemHandler.createGroundItem(c, itemId, c.getX(), c.getY(), c.playerItemsN[slot], c.getId());
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				PlayerSave.saveGame(c);
			} else {
				c.sendMessage("This items cannot be dropped.");
			}
		}

	}
}
