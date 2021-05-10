package server.model.players.packets;

import server.model.items.GameItem;
import server.model.items.Item;
import server.model.players.Client;
import server.model.players.PacketType;

/**
 * Bank All Items
 **/
public class BankAll implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
	int removeSlot = c.getInStream().readUnsignedWordA();
	int interfaceId = c.getInStream().readUnsignedWord();
	int removeId = c.getInStream().readUnsignedWordA();
	
		switch(interfaceId){			
			case 3900:
			if (c.myShopId == 30 && c.memberStatus < 1) {
				c.sendMessage("You must be a donator to buy from this shop.");
				return;
			}
			c.getShops().buyItem(removeId, removeSlot, 10);
			break;
			
			case 3823:
			if(c.inTrade) {
				c.sendMessage("You can't sell items while trading!");
				return;
			}
			c.getShops().sellItem(removeId, removeSlot, 10);
			break;
			
			case 5064:
			if(c.inTrade) {
				c.sendMessage("You can't bank items while trading!");
				return;
			}
			if (Item.itemStackable[removeId]) {
				c.getItems().bankItem(c.playerItems[removeSlot] , removeSlot, c.playerItemsN[removeSlot]);
			} else {
				c.getItems().bankItem(c.playerItems[removeSlot] , removeSlot, c.getItems().itemAmount(c.playerItems[removeSlot]));
			}
			break;
			
			case 5382:
			c.getItems().fromBank(c.bankItems[removeSlot] , removeSlot, c.bankItemsN[removeSlot]);
			break;	
			
			case 3322:
			if(c.duelStatus <= 0) { 
				if(Item.itemStackable[removeId]){
					c.getTradeAndDuel().tradeItem(removeId, removeSlot, c.playerItemsN[removeSlot]);
		    	} else {
					c.getTradeAndDuel().tradeItem(removeId, removeSlot, 28);  
				}
			} else {
				if(Item.itemStackable[removeId] || Item.itemIsNote[removeId]) {
					c.getTradeAndDuel().stakeItem(removeId, removeSlot, c.playerItemsN[removeSlot]);
				} else {
					c.getTradeAndDuel().stakeItem(removeId, removeSlot, 28);
				}
			}
			break;
			
			case 3415: 
			if(c.duelStatus <= 0) { 
				if(Item.itemStackable[removeId]) {
					for (GameItem item : c.getTradeAndDuel().offeredItems) {
						if(item.id == removeId) {
							c.getTradeAndDuel().fromTrade(removeId, removeSlot, c.getTradeAndDuel().offeredItems.get(removeSlot).amount);
						}
					}
				} else {
					for (GameItem item : c.getTradeAndDuel().offeredItems) {
						if(item.id == removeId) {
							c.getTradeAndDuel().fromTrade(removeId, removeSlot, 28);
						}
					}
				}
            } 
			break;
			
			case 6669:
			if(Item.itemStackable[removeId] || Item.itemIsNote[removeId]) {
				for (GameItem item : c.getTradeAndDuel().stakedItems) {
					if(item.id == removeId) {
						c.getTradeAndDuel().fromDuel(removeId, removeSlot, c.getTradeAndDuel().stakedItems.get(removeSlot).amount);
					}
				}
						
			} else {
				c.getTradeAndDuel().fromDuel(removeId, removeSlot, 28);
			}
			break;

		}
	}

}
