package server.model.items;

import server.Config;
import server.Server;
import server.model.players.Client;
import server.util.Misc;

public class ItemAssistant {

	private Client c;
	
	public ItemAssistant(Client client) {
		this.c = client;
	}
		
	/**
	Items
	**/
	
	public int[][] brokenBarrows = {{4708,4860},{4710,4866},{4712,4872},{4714,4878},{4716,4884},
	{4720,4896},{4718,4890},{4720,4896},{4722,4902},{4732,4932},{4734,4938},{4736,4944},{4738,4950},
	{4724,4908},{4726,4914},{4728,4920},{4730,4926},{4745,4956},{4747,4926},{4749,4968},{4751,4794},
	{4753,4980},{4755,4986},{4757,4992},{4759,4998}};

	public void resetItems(int WriteFrame) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrameVarSizeWord(53);
				c.getOutStream().writeWord(WriteFrame);
				c.getOutStream().writeWord(c.playerItems.length);
				for (int i = 0; i < c.playerItems.length; i++) {
					if(c.playerItemsN[i] > 254) {
						c.getOutStream().writeByte(255); 		
						c.getOutStream().writeDWord_v2(c.playerItemsN[i]);
					} else {
						c.getOutStream().writeByte(c.playerItemsN[i]);
					}
					c.getOutStream().writeWordBigEndianA(c.playerItems[i]); 
				}
				c.getOutStream().endFrameVarSizeWord();
				c.flushOutStream();
			}
		}
	}

	public int getBankAmount(int item) {
		int count = 0;	
		for (int j = 0; j < c.bankItems.length; j++) {
			if (c.bankItems[j] == item + 1) {
				count += c.bankItemsN[j];
			}		
		}
		return count;
	}
	public int getItemCount(int itemID) {
		int count = 0;	
		for (int j = 0; j < c.playerItems.length; j++) {
			if (c.playerItems[j] == itemID + 1) {
				count += c.playerItemsN[j];
			}		
		}
		return count;
	}
	
	public void writeBonus() {
		int offset = 0;
		String send = "";
		for (int i = 0; i < c.playerBonus.length; i++) {
			if (c.playerBonus[i] >= 0) {
				send = BONUS_NAMES[i]+": +"+c.playerBonus[i];
			} else {
				send = BONUS_NAMES[i]+": -"+java.lang.Math.abs(c.playerBonus[i]);
			}

			if (i == 10) {
				offset = 1;
			}
			c.getPA().sendFrame126(send, (1675+i+offset));
		}

	}
	
	public int getTotalCount(int itemID) {
		int count = 0;	
		for (int j = 0; j < c.playerItems.length; j++) {
			if (Item.itemIsNote[itemID+1]) {
				if (itemID+2 == c.playerItems[j])
					count += c.playerItemsN[j];
			} 
			if (!Item.itemIsNote[itemID+1]) {
				if (itemID+1 == c.playerItems[j]) {
					count += c.playerItemsN[j];
				}
			}
		}
		for (int j = 0; j < c.bankItems.length; j++) {
			if (c.bankItems[j] == itemID + 1) {
				count += c.bankItemsN[j];
			}		
		}
		return count;
	}
	
	public void sendItemsKept() {
		synchronized(c) {
			if(c.getOutStream() != null && c != null ) {
				c.getOutStream().createFrameVarSizeWord(53);
				c.getOutStream().writeWord(6963);
				c.getOutStream().writeWord(c.itemKeptId.length);
				for (int i = 0; i < c.itemKeptId.length; i++) {
					if(c.playerItemsN[i] > 254) {
						c.getOutStream().writeByte(255); 
						c.getOutStream().writeDWord_v2(1);
					} else {
						c.getOutStream().writeByte(1);
					}
					if(c.itemKeptId[i] > 0) {
					   c.getOutStream().writeWordBigEndianA(c.itemKeptId[i]+1);
					} else {
						c.getOutStream().writeWordBigEndianA(0);
					}
				}
				c.getOutStream().endFrameVarSizeWord();   
				c.flushOutStream();
			}
		}
    }
	
	
	/**
	* Item kept on death
	**/
	
	public void keepItem(int keepItem, boolean deleteItem) { 	
		int value = 0;
		int item = 0;
		int slotId = 0;
		boolean itemInInventory = false;
		for(int i = 0; i < c.playerItems.length; i++) {
			if(c.playerItems[i]-1 > 0) {
				int inventoryItemValue = c.getShops().getItemShopValue(c.playerItems[i] - 1);
				if(inventoryItemValue > value && (!c.invSlot[i])) {
					value = inventoryItemValue;
					item = c.playerItems[i] - 1;
					slotId = i;
					itemInInventory = true;			
				}
			}
		}
		for(int i1 = 0; i1 < c.playerEquipment.length; i1++) {
			if(c.playerEquipment[i1] > 0) {
				int equipmentItemValue = c.getShops().getItemShopValue(c.playerEquipment[i1]);
				if(equipmentItemValue > value && (!c.equipSlot[i1])) {
					value = equipmentItemValue;
					item = c.playerEquipment[i1];
					slotId = i1;
					itemInInventory = false;			
				}
			}
		}	
		if(itemInInventory) {
			c.invSlot[slotId] = true;
			if(deleteItem) {					
				deleteItem(c.playerItems[slotId]-1, getItemSlot(c.playerItems[slotId]-1), 1);
			}
		} else {
			c.equipSlot[slotId] = true;
			if(deleteItem) {
				deleteEquipment(item, slotId);
			}		
		}
		c.itemKeptId[keepItem] = item;	
	}
		
	/**
	* Reset items kept on death
	**/
	
	public void resetKeepItems() {
		for(int i = 0; i < c.itemKeptId.length; i++) {
			c.itemKeptId[i] = -1;
		}
		for(int i1 = 0; i1 < c.invSlot.length; i1++) {
			c.invSlot[i1] = false;
		}
		for(int i2 = 0; i2 < c.equipSlot.length; i2++) {
			c.equipSlot[i2] = false;
		}		
	}
	
	/**
	* delete all items
	**/
	
	public void deleteAllItems() {	
		for(int i1 = 0; i1 < c.playerEquipment.length; i1++) {
			deleteEquipment(c.playerEquipment[i1], i1);
		}
		for(int i = 0; i < c.playerItems.length; i++) {
			deleteItem(c.playerItems[i]-1, getItemSlot(c.playerItems[i]-1), c.playerItemsN[i]);
		}
	}
	
	
	/**
	* Drop all items for your killer
	**/
	public void dropAllItems() {
		Client o = (Client) Server.playerHandler.players[c.killerId];
		
		for(int i = 0; i < c.playerItems.length; i++) {
			if(o != null) {
				if (tradeable(c.playerItems[i] - 1)) {
					Server.itemHandler.createGroundItem(o, c.playerItems[i] -1, c.getX(), c.getY(), c.playerItemsN[i], c.killerId);
				} else {
					if (specialCase(c.playerItems[i] - 1))
						Server.itemHandler.createGroundItem(o, 995, c.getX(), c.getY(), getUntradePrice(c.playerItems[i]-1), c.killerId);
						Server.itemHandler.createGroundItem(c, c.playerItems[i] -1, c.getX(), c.getY(), c.playerItemsN[i], c.playerId);
				}
			} else {
				Server.itemHandler.createGroundItem(c, c.playerItems[i] -1, c.getX(), c.getY(), c.playerItemsN[i], c.playerId);
			}
		} 
		for(int e = 0; e < c.playerEquipment.length; e++) {
			if(o != null) {
				if (tradeable(c.playerEquipment[e])) {
					Server.itemHandler.createGroundItem(o, c.playerEquipment[e], c.getX(), c.getY(), c.playerEquipmentN[e], c.killerId);
				} else {
					if (specialCase(c.playerEquipment[e]))
						Server.itemHandler.createGroundItem(o, 995, c.getX(), c.getY(), getUntradePrice(c.playerEquipment[e]), c.killerId);
					Server.itemHandler.createGroundItem(c, c.playerEquipment[e], c.getX(), c.getY(), c.playerEquipmentN[e], c.playerId);
				}
			} else {
				Server.itemHandler.createGroundItem(c, c.playerEquipment[e], c.getX(), c.getY(), c.playerEquipmentN[e], c.playerId);
			}
		}
		if(o != null) {	
			Server.itemHandler.createGroundItem(o, 526, c.getX(), c.getY(), 1, c.killerId);
		}	
	}
	public int getUntradePrice(int item) {
		switch (item) {
			case 2518:
			case 2524:
			case 2526:
			return 100000;
			case 2520:
			case 2522:
			return 150000;
		}
		return 0;
	}
		public void dropAllItemsPVP() {
		Client pl = (Client) Server.playerHandler.players[c.killerId];
		int random = Misc.random(100);
		
		if (Server.playerHandler.players[c.killerId].connectedFrom.equals(Server.playerHandler.players[c.playerKilled].connectedFrom)) {
			c.sendMessage("You do not get loot from killing yourself.");
			} else if (c.isInPVP() && random == 2) {
				Server.itemHandler.createGroundItem(pl, PVPItems(), c.getX(), c.getY(), Misc.random(2), c.killerId);
				Server.itemHandler.createGroundItem(pl, GoodDrop(), c.getX(), c.getY(), 1, c.killerId);
				Server.itemHandler.createGroundItem(pl, GoodDrop(), c.getX(), c.getY(), 1, c.killerId);
				Server.itemHandler.createGroundItem(pl, FoodDrop(), c.getX(), c.getY(), 1, c.killerId);
				Server.itemHandler.createGroundItem(pl, FoodDrop(), c.getX(), c.getY(), 1, c.killerId);
				Server.itemHandler.createGroundItem(pl, FoodDrop(), c.getX(), c.getY(), 1, c.killerId);
				Server.itemHandler.createGroundItem(pl, FoodDrop(), c.getX(), c.getY(), 1, c.killerId);
			} else if (c.isInPVP() && random > 2 && random < 5) {
				Server.itemHandler.createGroundItem(pl, GoodDrop(), c.getX(), c.getY(), 1, c.killerId);
				Server.itemHandler.createGroundItem(pl, FoodDrop(), c.getX(), c.getY(), 1, c.killerId);
				Server.itemHandler.createGroundItem(pl, FoodDrop(), c.getX(), c.getY(), 1, c.killerId);
				Server.itemHandler.createGroundItem(pl, FoodDrop(), c.getX(), c.getY(), 1, c.killerId);
				Server.itemHandler.createGroundItem(pl, FoodDrop(), c.getX(), c.getY(), 1, c.killerId);
			} else if (c.isInPVP() && random > 5 && random < 30) {
				Server.itemHandler.createGroundItem(pl, MedDrop(), c.getX(), c.getY(), 1, c.killerId);
				Server.itemHandler.createGroundItem(pl, FoodDrop(), c.getX(), c.getY(), 1, c.killerId);
				Server.itemHandler.createGroundItem(pl, FoodDrop(), c.getX(), c.getY(), 1, c.killerId);
				Server.itemHandler.createGroundItem(pl, FoodDrop(), c.getX(), c.getY(), 1, c.killerId);
				Server.itemHandler.createGroundItem(pl, FoodDrop(), c.getX(), c.getY(), Misc.random(2), c.killerId);
				Server.itemHandler.createGroundItem(pl, FoodDrop(), c.getX(), c.getY(), Misc.random(2), c.killerId);
			} else if (c.isInPVP() && random > 30 && random < 60) {
				Server.itemHandler.createGroundItem(pl, LowDrop(), c.getX(), c.getY(), 1, c.killerId);
				Server.itemHandler.createGroundItem(pl, LowDrop(), c.getX(), c.getY(), 1, c.killerId);
				Server.itemHandler.createGroundItem(pl, FoodDrop(), c.getX(), c.getY(), 1, c.killerId);
				Server.itemHandler.createGroundItem(pl, FoodDrop(), c.getX(), c.getY(), Misc.random(2), c.killerId);
				Server.itemHandler.createGroundItem(pl, FoodDrop(), c.getX(), c.getY(), Misc.random(2), c.killerId);
				Server.itemHandler.createGroundItem(pl, FoodDrop(), c.getX(), c.getY(), Misc.random(2), c.killerId);
			} else if (c.isInPVP() && random > 60 && random < 100) {
				Server.itemHandler.createGroundItem(pl, LowDrop(), c.getX(), c.getY(), 1, c.killerId);
				Server.itemHandler.createGroundItem(pl, FoodDrop(), c.getX(), c.getY(), Misc.random(2), c.killerId);
				Server.itemHandler.createGroundItem(pl, FoodDrop(), c.getX(), c.getY(), Misc.random(2), c.killerId);
				Server.itemHandler.createGroundItem(pl, FoodDrop(), c.getX(), c.getY(), Misc.random(2), c.killerId);
			}

	}

		int[] PvpItems = { 14876, 14877, 14878, 14879, 14880, 14881, 14882, 14883, 14884, 14885, 14886, 14888, 14889, 14890, 14891, 14892, 11146, 11147, 11148 };
		int[] PvPDrops = {11694, 11696, 11700, 11698, 11716, 11718, 11720, 11722, 11724, 11726, 11728, 13736, 13738, 13740, 13742, 13744, 14484, 15000, 15284, 13899, 13902, 13899, 13902};
		int[] GoodDrops = {11732, 15018, 15019, 15020, 15220,15001, 11730, 4878, 4873};
		int[] FoodDrops = {2550,391,385,379,361,139, 15272,7060};
		int[] MedDrops = {15335,6737,6735,6733,6731,11128,6585,11732,4151,4151,4151,4716,4718,4720,4722,4724,4726,4728,4730,4732,4734,4736,4708,4710,4712,11732,4714,4740,4741,4743,4745,4747,4749,4751,5753,4755,4757,4759};
		int[] LowDrops = {3751,10828,4091,4093,4101,4103,4111,4113,4131,1079,1127,1713,385,391,361,139,15335,15335};

		public int randomDrop() {
			return PvPDrops[(int) (Math.random() * PvPDrops.length)];
		}
		public int PVPItems() {
			return PvpItems[(int) (Math.random() * PvpItems.length)];
		}
		public int FoodDrop() {
			return FoodDrops[(int) (Math.random() * FoodDrops.length)];
		}
		public int LowDrop() {
			return LowDrops[(int) (Math.random() * LowDrops.length)];
		}
		public int MedDrop() {
			return MedDrops[(int) (Math.random() * MedDrops.length)];
		}
		public int GoodDrop() {
			return GoodDrops[(int) (Math.random() * GoodDrops.length)];
		}
	public boolean specialCase(int itemId) {
		switch (itemId) {
			case 2518:
			case 2520:
			case 2522:
			case 2524:
			case 2526:
			return true;		
		}
		return false;
	}
	
	public void handleSpecialPickup(int itemId) {
		//c.sendMessage("My " + getItemName(itemId) + " has been recovered. I should talk to the void knights to get it back.");
		//c.getItems().addToVoidList(itemId);
	}
	
	public void addToVoidList(int itemId) {
		switch (itemId) {
			case 2518:
			c.voidStatus[0]++;
			break;
			case 2520:
			c.voidStatus[1]++;
			break;
			case 2522:
			c.voidStatus[2]++;
			break;
			case 2524:
			c.voidStatus[3]++;
			break;
			case 2526:
			c.voidStatus[4]++;
			break;	
		}
	}
	
	public boolean tradeable(int itemId) {
		for (int j = 0; j < Config.ITEM_TRADEABLE.length; j++) {
			if (itemId == Config.ITEM_TRADEABLE[j])
				return false;
		}	
		return true;
	}
	
	/**
	*Add Item
	**/
	public boolean addItem(int item, int amount) {
		synchronized(c) {
			if (amount < 1) {
				amount = 1;
			}
			if(item <= 0) {
				return false;
			}
			if ((((freeSlots() >= 1) || playerHasItem(item, 1)) && Item.itemStackable[item]) || ((freeSlots() > 0) && !Item.itemStackable[item])) {
				for (int i = 0; i < c.playerItems.length; i++) {
					if ((c.playerItems[i] == (item + 1)) && Item.itemStackable[item]
							&& (c.playerItems[i] > 0)) {
						c.playerItems[i] = (item + 1);
						if (((c.playerItemsN[i] + amount) < Config.MAXITEM_AMOUNT)
								&& ((c.playerItemsN[i] + amount) > -1)) {
							c.playerItemsN[i] += amount;
						} else {
							c.playerItemsN[i] = Config.MAXITEM_AMOUNT;
						}
						if(c.getOutStream() != null && c != null ) {
							c.getOutStream().createFrameVarSizeWord(34);
							c.getOutStream().writeWord(3214);
							c.getOutStream().writeByte(i);
							c.getOutStream().writeWord(c.playerItems[i]);
							if (c.playerItemsN[i] > 254) {
								c.getOutStream().writeByte(255);
								c.getOutStream().writeDWord(c.playerItemsN[i]);
							} else {
								c.getOutStream().writeByte(c.playerItemsN[i]);
							}
							c.getOutStream().endFrameVarSizeWord();
							c.flushOutStream();
						}
						i = 30;
						return true;
					}
				}
				for (int i = 0; i < c.playerItems.length; i++) {
					if (c.playerItems[i] <= 0) {
						c.playerItems[i] = item + 1;
						if ((amount < Config.MAXITEM_AMOUNT) && (amount > -1)) {
							c.playerItemsN[i] = 1;
							if (amount > 1) {
								c.getItems().addItem(item, amount - 1);
								return true;
							}
						} else {
							c.playerItemsN[i] = Config.MAXITEM_AMOUNT;
						}
						/*if(c.getOutStream() != null && c != null ) {
							c.getOutStream().createFrameVarSizeWord(34);
							c.getOutStream().writeWord(3214);
							c.getOutStream().writeByte(i);
							c.getOutStream().writeWord(c.playerItems[i]);
							if (c.playerItemsN[i] > 254) {
								c.getOutStream().writeByte(255);
								c.getOutStream().writeDWord(c.playerItemsN[i]);
							} else {
								c.getOutStream().writeByte(c.playerItemsN[i]);
							}
							c.getOutStream().endFrameVarSizeWord();
							c.flushOutStream();
						}*/
						resetItems(3214);
						i = 30;
						return true;
					}
				}
				return false;
			} else {
				resetItems(3214);
				c.sendMessage("Not enough space in your inventory.");
				return false;
			}
		}
	}
	
	public String itemType(int item) {
		for (int i=0; i < Item.capes.length;i++) {
			if(item == Item.capes[i])
			  return "cape";
		}
		for (int i=0; i < Item.hats.length;i++) {
			if(item == Item.hats[i])
			  return "hat";
		}
		for (int i=0; i< Item.boots.length;i++) {
			if(item == Item.boots[i])
			  return "boots";
		}
		for (int i=0; i< Item.gloves.length;i++) {
			if(item == Item.gloves[i])
			  return "gloves";
		}
		for (int i=0; i< Item.shields.length;i++) {
			if(item == Item.shields[i])
			  return "shield";
		}
		for (int i=0; i< Item.amulets.length;i++) {
			if(item == Item.amulets[i])
			  return "amulet";
		}
		for (int i=0; i< Item.arrows.length;i++) {
			if(item == Item.arrows[i])
			  return "arrows";
		}
		for (int i=0; i< Item.rings.length;i++) {
			if(item == Item.rings[i])
			  return "ring";
		}
		for (int i=0; i< Item.body.length;i++) {
			if(item == Item.body[i])
			  return "body";
		}
		for (int i=0; i< Item.legs.length;i++) {
			if(item == Item.legs[i])
			  return "legs";
		}
		return "weapon";
	}
	
	/**
	*Bonuses
	**/

	public final String[] BONUS_NAMES = {
		"Stab", "Slash", "Crush", "Magic", "Range", "Stab", "Slash",
		"Crush", "Magic", "Range", "Strength", "Prayer"
	};
	public void resetBonus() {
		for (int i = 0; i < c.playerBonus.length; i++) {
			c.playerBonus[i] = 0;
		}
	}
	public void getBonus() {
		for (int i = 0; i < c.playerEquipment.length; i++) {
			if (c.playerEquipment[i] > -1) {
				for (int j = 0; j < Config.ITEM_LIMIT; j++) {
					if (Server.itemHandler.ItemList[j] != null){
							if (Server.itemHandler.ItemList[j].itemId == c.playerEquipment[i]) {
							for (int k = 0; k < c.playerBonus.length; k++) {
								c.playerBonus[k] += Server.itemHandler.ItemList[j].Bonuses[k];
							}
							break;
						}
					}
				}
			}
		}
	}
	
	
	/**
	*Wear Item
	**/

	public void sendWeapon(int Weapon, String WeaponName) {
		String WeaponName2 = WeaponName.replaceAll("Bronze", "");
		WeaponName2 = WeaponName2.replaceAll("Iron", "");
		WeaponName2 = WeaponName2.replaceAll("Steel", "");
		WeaponName2 = WeaponName2.replaceAll("Black", "");
		WeaponName2 = WeaponName2.replaceAll("Mithril", "");
		WeaponName2 = WeaponName2.replaceAll("Adamant", "");
		WeaponName2 = WeaponName2.replaceAll("Rune", "");
		WeaponName2 = WeaponName2.replaceAll("Granite", "");
		WeaponName2 = WeaponName2.replaceAll("Dragon", "");
		WeaponName2 = WeaponName2.replaceAll("Drag", "");
		WeaponName2 = WeaponName2.replaceAll("Crystal", "");
		WeaponName2 = WeaponName2.trim();
		if (WeaponName.equals("Unarmed")) {
			c.setSidebarInterface(0, 5855); //punch, kick, block
			c.getPA().sendFrame126(WeaponName, 5857);
		} else if (WeaponName.startsWith("Abyssal")) {
			c.setSidebarInterface(0, 12290); //flick, lash, deflect
			c.getPA().sendFrame246(12291, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 12293);
		} else if (WeaponName.endsWith("bow") || WeaponName.endsWith("10")|| WeaponName.endsWith("full") || WeaponName.startsWith("seercull")) {
			c.setSidebarInterface(0, 1764); //accurate, rapid, longrange
			c.getPA().sendFrame246(1765, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 1767);
		} else if (WeaponName.startsWith("Staff") || WeaponName.endsWith("staff") || WeaponName.endsWith("wand")) {
			c.setSidebarInterface(0, 328); //spike, impale, smash, block
			c.getPA().sendFrame246(329, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 331);
		} else if (WeaponName2.startsWith("dart") || WeaponName2.startsWith("knife") || WeaponName2.startsWith("javelin") || WeaponName.equalsIgnoreCase("toktz-xil-ul")) {
			c.setSidebarInterface(0, 4446); //accurate, rapid, longrange
			c.getPA().sendFrame246(4447, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 4449);
        		} else if (WeaponName2.endsWith("claws")) {
			c.setSidebarInterface(0, 2276); //stab, lunge, slash, block
			c.getPA().sendFrame246(2277, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 2279);
		} else if (WeaponName2.startsWith("dagger") || WeaponName2.contains("anchor") || WeaponName2.contains("sword")) {
			c.setSidebarInterface(0, 2276); //stab, lunge, slash, block
			c.getPA().sendFrame246(2277, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 2279);
		} else if (WeaponName2.startsWith("pickaxe")) {
			c.setSidebarInterface(0, 5570); //spike, impale, smash, block
			c.getPA().sendFrame246(5571, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 5573);
		} else if (WeaponName2.startsWith("axe") || WeaponName2.startsWith("battleaxe")) {
			c.setSidebarInterface(0, 1698); //chop, hack, smash, block
			c.getPA().sendFrame246(1699, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 1701);
		} else if (WeaponName2.startsWith("halberd")) {
			c.setSidebarInterface(0, 8460); //jab, swipe, fend
			c.getPA().sendFrame246(8461, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 8463);
		} else if (WeaponName2.startsWith("Scythe")) {
			c.setSidebarInterface(0, 8460); //jab, swipe, fend
			c.getPA().sendFrame246(8461, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 8463);
		} else if (WeaponName2.startsWith("spear")) {
			c.setSidebarInterface(0, 4679); //lunge, swipe, pound, block
			c.getPA().sendFrame246(4680, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 4682);
		} else if (WeaponName2.toLowerCase().contains("mace")){
			c.setSidebarInterface(0, 3796);
			c.getPA().sendFrame246(3797, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 3799);
		} else if (c.playerEquipment[c.playerWeapon] == 4153 || c.playerEquipment[c.playerWeapon] == 15313 || c.playerEquipment[c.playerWeapon] == 13902)  {
			c.setSidebarInterface(0, 425); //war hamer equip.
			c.getPA().sendFrame246(426, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 428);		
		} else {
			c.setSidebarInterface(0, 2423); //chop, slash, lunge, block
			c.getPA().sendFrame246(2424, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 2426);
		}
		
	}
	

	/**
	*Item Requirements
	**/
	
		public void getRequirements(String itemName, int itemId) {
		c.attackLevelReq = c.defenceLevelReq = c.strengthLevelReq = c.hitpointsLevelReq = c.rangeLevelReq = c.prayerLevelReq = c.magicLevelReq = c.cookingLevelReq = c.wcLevelReq = c.fletchingLevelReq = c.fishingLevelReq = c.fmLevelReq = c.craftingLevelReq = c.smithingLevelReq = c.miningLevelReq = c.herbloreLevelReq = c.agilityLevelReq = c.thievingLevelReq = c.slayerLevelReq = c.farmingLevelReq = c.rcLevelReq = 0;
			switch(itemId) {
		
	// ---------------- Basic --------------- //	
		
			/**
			*Donator stuff
			**/
			case 13738: // spirit sheild
			case 13740: // spirit sheild
			case 13742: // spirit sheild
			case 13744: // spirit sheild
			c.defenceLevelReq = 65;
			break;
			case 13899: // statius hammer
			case 13902: // vesta long
			c.attackLevelReq = 70;
			break;
			case 15038: //chaotic longsword
			case 15037: //chaotic rapier
			c.attackLevelReq = 80;
			break;
			case 15040: //chaotic staff
			c.magicLevelReq = 80;
			break;
			case 15285: // water cape
			case 15284: // dragon cape
			break;
//rc pouchs
			case 4213: //crystal bow new
			c.rangeLevelReq = 70;
			c.agilityLevelReq = 50;
			break;
			case 5510://med
			c.rcLevelReq = 25;
			break;
			case 5512://large
			c.rcLevelReq = 50;
			break;
			case 5514://med
			c.rcLevelReq = 75;
			break;
			/**
			*Steel armor
			**/
			
			// Steel armor
			case 1069: // Steel platelegs
			case 1083: // Steel plateskirt
			case 1105: // Steel chainbody
			case 1119: // Steel pladebody
			case 1141: // Steel med helm
			case 1157: // Steel full helm
			case 1177: // Steel sq shield
			case 1193: // Steel kiteshield
			case 4123: // Steel boots
			case 8846: // Steel defender
			c.defenceLevelReq = 5;
			break;
			// Steel weapons
			case 1207: // Steel dagger
			case 1241: // Steel spear
			case 1269: // Steel pickaxe
			case 1281: // Steel sword
			case 1295: // Steel longsword
			case 1311: // Steel 2h sword
			case 1325: // Steel scimitar
			case 1339: // Steel warhammer
			case 1353: // Steel axe
			case 1365: // Steel battleaxe
			case 1424: // Steel mace
			case 3097: // Steel claws
			case 3194: // Steel halberd
			c.attackLevelReq = 5;
			break;
			
			/**
			*Black / White armor
			**/
			
			// Black armor
			case 1077: // Black platelegs
			case 2585: // Black platelegs (t)
			case 2593: // Black platelegs (g)
			case 1089: // Black plateskirt
			case 3472: // Black plateskirt (t)
			case 3473: // Black plateskirt (g)
			case 1107: // Black chainbody
			case 1125: // Black pladebody
			case 2583: // Black pladebody (t)
			case 2591: // Black pladebody (g)
			case 1151: // Black med helm
			case 1165: // Black full helm
			case 2587: // Black full helm (t)
			case 2595: // Black full helm (g)
			case 1179: // Black sq shield
			case 1195: // Black kiteshield
			case 2589: // Black kiteshield (t)
			case 2597: // Black kiteshield (g)
			case 4125: // Black boots
			case 8847: // Black defender
			c.defenceLevelReq = 10;
			break;
			// Black weapons
			case 1217: // Black dagger
			case 4580: // Black spear
			//case : // Black pickaxe
			case 1283: // Black sword
			case 1297: // Black longsword
			case 1313: // Black 2h sword
			case 1327: // Black scimitar
			case 1341: // Black warhammer
			case 1361: // Black axe
			case 1367: // Black battleaxe
			case 1426: // Black mace
			case 3098: // Black claws
			case 3196: // Black halberd
			c.attackLevelReq = 10;
			break;			

			// White armor
			case 6625: // White platelegs
			case 6627: // White plateskirt
			case 6615: // White chainbody
			case 6617: // White pladebody
			case 6621: // White med helm
			case 6623: // White full helm
			case 6631: // White sq shield
			case 6633: // White kiteshield
			case 6619: // White boots
			case 6629: // White gloves
			c.defenceLevelReq = 10;
			break;
			// White weapons
			case 6591: // White dagger
			case 6605: // White sword
			case 6607: // White longsword
			case 6609: // White 2h sword
			case 6611: // White scimitar
			case 6613: // White warhammer
			case 6589: // White battleaxe
			case 6601: // White mace
			case 6587: // White claws
			case 6599: // White halberd
			c.attackLevelReq = 10;
			break;
			case 6603: // White magic staff
			c.attackLevelReq = 10;
			c.magicLevelReq = 10;
			break;	
			
			/**
			*Mithril armor
			**/	
		
			// Mithril armor
			case 1071: // Mithril platelegs
			case 1085: // Mithril plateskirt
			case 1109: // Mithril chainbody
			case 1121: // Mithril pladebody
			case 1143: // Mithril med helm
			case 1159: // Mithril full helm
			case 1181: // Mithril sq shield
			case 1197: // Mithril kiteshield
			case 4127: // Mithril boots
			case 8848: // Mithril defender
			c.defenceLevelReq = 20;
			break;
			// Mithril weapons
			case 1209: // Mithril dagger
			case 1243: // Mithril spear
			case 1273: // Mithril pickaxe
			case 1285: // Mithril sword
			case 1299: // Mithril longsword
			case 1315: // Mithril 2h sword
			case 1329: // Mithril scimitar
			case 1343: // Mithril warhammer
			case 1355: // Mithril axe
			case 1369: // Mithril battleaxe
			case 1428: // Mithril mace
			case 3099: // Mithril claws
			case 3198: // Mithril halberd
			c.attackLevelReq = 20;
			break;		
			
			/**
			*Adamant armor
			**/				
			
			// Adamant armor
			case 1073: // Adamant platelegs
			case 2601: // Adamant platelegs (t)
			case 2609: // Adamant platelegs (g)
			case 1091: // Adamant plateskirt
			case 3474: // Adamant plateskirt (t)
			case 3475: // Adamant plateskirt (g)
			case 1111: // Adamant chainbody
			case 1123: // Adamant pladebody
			case 2599: // Adamant pladebody (t)
			case 2607: // Adamant pladebody (g)
			case 1145: // Adamant med helm
			case 1161: // Adamant full helm
			case 2605: // Adamant full helm (t)
			case 2613: // Adamant full helm (g)
			case 1183: // Adamant sq shield
			case 1199: // Adamant kiteshield
			case 2603: // Adamant kiteshield (t)
			case 2611: // Adamant kiteshield (g)
			case 4129: // Adamant boots
			case 8849: // Adamant defender
			c.defenceLevelReq = 30;
			break;
			// Adamant weapons
			case 1211: // Adamant dagger
			case 1245: // Adamant spear
			case 1271: // Adamant pickaxe
			case 1287: // Adamant sword
			case 1301: // Adamant longsword
			case 1317: // Adamant 2h sword
			case 1331: // Adamant scimitar
			case 1345: // Adamant warhammer
			case 1357: // Adamant axe
			case 1371: // Adamant battleaxe
			case 1430: // Adamant mace
			case 3100: // Adamant claws
			case 3200: // Adamant halberd
			c.attackLevelReq = 30;
			break;			
			
			/**
			*Rune armor
			**/			
			
			// Rune armor
			case 1079: // Rune platelegs
			case 2625: // Rune platelegs (t)
			case 2617: // Rune platelegs (g)		
			case 3483: // Gilded platelegs
			case 2655: // Zamorak platelegs
			case 2671: // Guthix platelegs
			case 2663: // Saradomin platelegs			
			case 1093: // Rune plateskirt
			case 3477: // Rune plateskirt (t)
			case 3476: // Rune plateskirt (g)
			case 3485: // Gilded plateskirt
			case 3478: // Zamorak plateskirt
			case 3480: // Guthix plateskirt
			case 3479: // Saradomin plateskirt	
			case 1113: // Rune chainbody
			case 1127: // Rune pladebody
			case 2623: // Rune pladebody (t)
			case 2615: // Rune pladebody (g)
			case 3481: // Gilded platebody
			case 2653: // Zamorak platebody
			case 2669: // Guthix platebody
			case 2661: // Saradomin platebody
			case 1147: // Rune med helm
			case 1163: // Rune full helm
			case 2627: // Rune full helm (t)
			case 2619: // Rune full helm (g)
			case 3486: // Gilded full helm
			case 2657: // Zamorak full helm
			case 2673: // Guthix full helm
			case 2665: // Saradomin full helm
			case 1185: // Rune sq shield
			case 1201: // Rune kiteshield
			case 2629: // Rune kiteshield (t)
			case 2621: // Rune kiteshield (g)
			case 3488: // Gilded kiteshield
			case 2659: // Zamorak kiteshield
			case 2675: // Guthix kiteshield
			case 2667: // Saradomin kiteshield
			case 4131: // Rune boots
			case 8850: // Rune defender
			c.defenceLevelReq = 40;
			break;
			// Rune weapons
			case 1213: // Rune dagger
			case 1247: // Rune spear
			case 1275: // Rune pickaxe
			case 1289: // Rune sword
			case 1303: // Rune longsword
			case 1319: // Rune 2h sword
			case 1333: // Rune scimitar
			case 1347: // Rune warhammer
			case 1359: // Rune axe
			case 1373: // Rune battleaxe
			case 1432: // Rune mace
			case 3101: // Rune claws
			case 3202: // Rune halberd
			c.attackLevelReq = 40;
			break;	
			
			/**
			*Dragon armor
			**/	

			// Dragon armor
			case 4087: // Dragon platelegs
			case 4585: // Dragon plateskirt
			case 3140: // Dragon chainbody
			case 1149: // Dragon med helm
			case 11335: // Dragon full helm
			case 1187: // Dragon sq shield
			case 11732: // Dragon boots
			case 14479: //dragon plate
			c.defenceLevelReq = 60;
			break;
			// Dragon weapons
			case 15312: //dragon pick
			case 1215: // Dragon dagger
			case 5698: // Dragon dagger (p++)
			case 1249: // Dragon spear
			case 5730: // Dragon spear (p++)
			case 1305: // Dragon longsword
			case 7158: // Dragon 2h sword
			case 4587: // Dragon scimitar
			case 6739: // Dragon axe
			case 1377: // Dragon battleaxe
			case 1434: // Dragon mace
			case 13664: // Dragon claws
			case 3204: // Dragon halberd'
			case 14484: //dragon claw
			c.attackLevelReq = 60;
			break;	
			
	//------------ Ranging ----------//
	
			/**
			*Bows / crossbows
			**/		
			case 853: // Maple shortbow
			break;
			case 857: // Yew shortbow
				c.rangeLevelReq = 40;
			break;
			case 861: // Magic shortbow
				c.rangeLevelReq = 50;
			break;
			case 11235: // Dark bow
				c.rangeLevelReq = 60;
			break;
			case 4734: // Karil's crossbow
				c.rangeLevelReq = 70;
			break;
			
			case 9179: // Steel c'bow
				c.rangeLevelReq = 26;
			break;	
			case 9181: // Mithril c'bow
				c.rangeLevelReq = 36;
			break;	
			case 9183: // Adamant c'bow
				c.rangeLevelReq = 46;
			break;	
			case 9185: // Rune c'bow
				c.rangeLevelReq = 61;
			break;		
			
			/**
			*Arrows / bolts
			**/		

			case 886: // Steel arrows
				c.rangeLevelReq = 10;
			break;
			case 888: // Mithril arrows
				c.rangeLevelReq = 20;
			break;
			case 890: // Adamant arrows
				c.rangeLevelReq = 30;
			break;
			case 892: // Rune arrows
				c.rangeLevelReq = 40;
			break;			
			case 11212: // Dragon arrows
				c.rangeLevelReq = 60;
			break;			
	
			case 9141: // Steel bolts
				c.rangeLevelReq = 26;
			break;	
			case 9142: // Mithril bolts
			case 9338: // Emerald bolts
			case 9241: // Emerald bolts (e)
				c.rangeLevelReq = 36;
			break;	
			case 9143: // Adamant bolts
			case 9339: // Ruby bolts
			case 9242: // Ruby bolts (e)
			case 9340: // Diamond bolts
			case 9243: // Diamond bolts (e)
				c.rangeLevelReq = 46;
			break;	
			case 9144: // Rune bolts
			case 9341: // Dragon bolts
			case 9244: // Dragon bolts (e)
			case 9342: // Onyx bolts
			case 9245: // Onyx bolts (e)
				c.rangeLevelReq = 61;
			break;				

			/**
			*Ranging equipment / armor
			**/				
			
			case 2577: // Ranger boots
			case 2581: // Robin hood hat
				c.rangeLevelReq = 40;
			break;
			
			/**
			*Dragonhide armor
			**/				
			
			// Green d'hide //
			
			case 1099: // Green d'hide chaps
			case 7378: // Green d'hide chaps (g)
			case 7380: // Green d'hide chaps (t)
			case 1065: // Green d'hide vambs			
			case 10079: // Green spiky vambs	
				c.rangeLevelReq = 40;
			break;
			case 1135: // Green d'Hide body
			case 7370: // Green d'Hide body (g)
			case 7372: // Green d'Hide body (t)
				c.rangeLevelReq = 40;
				c.defenceLevelReq = 40;
			break;
			
			// Blue d'hide //
			
			case 2493: // Blue d'hide chaps
			case 7382: // Blue d'hide chaps (g)
			case 7384: // Blue d'hide chaps (t)
			case 2487: // Blue d'hide vambs			
			case 10081: // Blue spiky vambs	
				c.rangeLevelReq = 50;
			break;
			case 2499: // Blue d'Hide body
			case 7374: // Blue d'Hide body (g)
			case 7376: // Blue d'Hide body (t)
				c.rangeLevelReq = 50;
				c.defenceLevelReq = 40;
			break;			
			
			// Red d'hide //
			
			case 2495: // Red d'hide chaps
			case 2489: // Red d'hide vambs			
			case 10083: // Red spiky vambs	
				c.rangeLevelReq = 60;
			break;
			case 2501: // Red d'Hide body
				c.rangeLevelReq = 60;
				c.defenceLevelReq = 40;
			break;	

			// Black d'hide //
			
			case 2491: // Black d'hide vambs			
			case 2497: // Black d'hide chaps
			case 10368: // Zamorak vambs				
			case 10372: // Zamorak chaps
			case 10376: // Guthix vambs	
			case 10380: // Guthix chaps
			case 10384: // Saradomin vambs	
			case 10388: // Saradomin chaps		
			case 10085: // Black spiky vambs
				c.rangeLevelReq = 70;
			break;
			
			case 2503: // Black d'hide body
			case 10370: // Zamorak d'hide body				
			case 10374: // Zamorak coif
			case 10378: // Guthix d'hide body	
			case 10382: // Guthix coif
			case 10386: // Saradomin d'hide body	
			case 10390: // Saradomin coif
				c.rangeLevelReq = 70;
				c.defenceLevelReq = 40;
			break;	
			
			/**
			*Knives / javelins / darts
			**/			
			
			case 865: // Steel throwing knives
			case 827: // Steel javelins
			case 808: // Steel darts
			case 802: // Steel thrownaxe
				c.rangeLevelReq = 5;
			break;

			case 869: // Black throwing knives
				c.rangeLevelReq = 10;
			break;
			
			case 866: // Mithril throwing knives
			case 828: // Mithril javelins
			case 809: // Mithril darts
			case 803: // Mithril thrownaxe
				c.rangeLevelReq = 20;
			break;			
			
			case 867: // Adamant throwing knives
			case 829: // Adamant javelins
			case 810: // Adamant darts
			case 804: // Adamant thrownaxe
				c.rangeLevelReq = 30;
			break;			
								
			case 868: // Rune throwing knives
			case 830: // Rune javelins
			case 811: // Rune darts
			case 805: // Rune thrownaxe
				c.rangeLevelReq = 40;
			break;			
					
			case 11230: // Dragon darts
				c.rangeLevelReq = 60;
			break;	
			
			case 13879: //Morrigan's Javelin
			case 13883: //Morrigan's Throwing Axe
				c.rangeLevelReq = 78;
			break;		
			
			/**
			*Miscellanious // Armor
			**/		
	
			case 6522: // Obby rings
				c.rangeLevelReq = 60;
			break;
	
			case 6149: // Spined gloves
			case 6143: // Spined boots
			case 6135: // Spined chaps
			case 6133: // Spined body
			case 6131: // Spined helm
				c.rangeLevelReq = 40;
				c.defenceLevelReq = 40;
			break;					
			
			case 1097: // Studded chaps
			case 7366: // Studded chaps (g)
			case 7368: // Studded chaps (t)
				c.rangeLevelReq = 20;
			break;
			
			case 1133: // Studded body
			case 7362: // Studded body (g)
			case 7364: // Studded body (t)
				c.rangeLevelReq = 20;
				c.defenceLevelReq = 20;
			break;			
				
			case 6326: // Snakeskin bandana
			case 6322: // Snakeskin body
			case 6324: // Snakeskin chaps
			case 6328: // Snakeskin boots
			case 6330: // Snakeskin vambraces
				c.rangeLevelReq = 30;
				c.defenceLevelReq = 30;
			break;		
				
			case 11718: // Armadyl helm
			case 11720: // Armadyl chestplate
			case 11722: // Armadyl plateskirt
			case 4732: // Karil's coif
			case 4736: // Karil's leathertop
			case 4738: // Karil's leatherskirt
				c.rangeLevelReq = 70;
				c.defenceLevelReq = 70;
			break;
			
	//------------ Magic ----------//	

			/**
			*Magic equipment
			**/	
			case 6889: // Mages' book
				c.magicLevelReq = 60;
			break;
			case 2579: // Wizard boots
				c.magicLevelReq = 20;
			break;
			case 2412: // Saradomin cape
			case 2413: // Guthix cape
			case 2414: // Zamorak cape
			case 2415: // Saradomin staff
			case 2416: // Guthix staff
			case 2417: // Zamorak staff
				c.magicLevelReq = 60;
			break;			
			/**
			*Staves
			**/	
	
			case 6526: // Obby staff
				c.attackLevelReq = 60;
				c.magicLevelReq = 60;			
			break;
	
			case 1391: // Battlestaff
				c.attackLevelReq = 20;
				c.magicLevelReq = 20;			
			break;
			
			case 1393: // Fire battlestaff
			case 1395: // Water battlestaff
			case 1397: // Air battlestaff
			case 1399: // Earth battlestaff
			case 6562: // Mud battlestaff
			case 3053: // Lava battlestaff
				c.attackLevelReq = 30;
				c.magicLevelReq = 30;
			break;

			case 1401: // Mystic fire staff
			case 1403: // Mystic water staff
			case 1405: // Mystic air staff
			case 1407: // Mystic earth staff
			case 6563: // Mystic mud staff
			case 3054: // Mystic lava staff
				c.attackLevelReq = 40;
				c.magicLevelReq = 40;
			break;
			
			case 6908: // Beginner wand
				c.magicLevelReq = 45;
			break;
			case 6910: // Apprentice wand
				c.magicLevelReq = 50;
			break;
			case 4675: // Ancient staff
				c.attackLevelReq = 50;
				c.magicLevelReq = 50;
			break;
			case 6912: // Teacher wand
				c.magicLevelReq = 55;
			break;
			case 6914: // Master wand
				c.magicLevelReq = 60;
			break;			
			case 4710: // Ahrim's staff
				c.attackLevelReq = 70;
				c.magicLevelReq = 70;
			break;				
			
			/**
			*Mage armor
			**/	
			
			case 6153: // skeletal gloves
			case 6147: // skeletal boots
			case 6141: // skeletal bottom
			case 6139: // skeletal top
			case 6137: // skeletal helm
				c.magicLevelReq = 40;
				c.defenceLevelReq = 40;
			break;		

			case 6916: // Infinity top
			case 6918: // Infinity hat
			case 6920: // Infinity boots
			case 6922: // Infinity gloves
			case 6924: // Infinity bottom
				c.magicLevelReq = 50;
				c.defenceLevelReq = 25;
			break;			
			
			case 3385: // Splitbark helm
			case 3387: // Splitbark body
			case 3389: // Splitbark legs
			case 3391: // Splitbark gauntlets
			case 3393: // Splitbark boots
				c.magicLevelReq = 40;
				c.defenceLevelReq = 40;				
			break;

			case 7399: // Enchanted top
			case 7398: // Enchanted bottom
			case 7400: // Enchanted hat
			case 4089: // Mystic hat (blue)
			case 4091: // Mystic top (blue)
			case 4093: // Mystic bottom (blue)
			case 4095: // Mystic gloves (blue)
			case 4097: // Mystic boots (blue)		
			case 4099: // Mystic hat (gold)
			case 4101: // Mystic top (gold)
			case 4103: // Mystic bottom (gold)
			case 4105: // Mystic gloves (gold)
			case 4107: // Mystic boots (gold)
			case 4109: // Mystic hat (black)
			case 4111: // Mystic top (black)
			case 4113: // Mystic bottom (black)
			case 4115: // Mystic gloves (black)
			case 4117: // Mystic boots (black)
				c.magicLevelReq = 40;
				c.defenceLevelReq = 20;				
			break;
			
			case 4708: // Ahrim's hood
			case 4712: // Ahrim's robetop
			case 4714: // Ahrim's robeskirt
				c.magicLevelReq = 70;
				c.defenceLevelReq = 70;				
			break;				
					
			/**
			*Others
			**/	

	//------------ Melee ----------//	

			/**
			*Weapons
			**/	

			case 6527: // Obby mace
			case 6523: // Obby sword
			case 6525: // Obby knife
				c.attackLevelReq = 60;
			break;	

			case 15313: // chaotic maul
				c.attackLevelReq = 80;
			break;

			case 4153: // Granite maul
				c.attackLevelReq = 50;
				c.strengthLevelReq = 50;
			break;		
			case 10887: // Barrelchest anchor
				c.attackLevelReq = 60;
				c.strengthLevelReq = 40;
			break;				
			case 11694: // Armadyl godsword
				c.attackLevelReq = 75;
			break;
			case 11696: // Bandos godsword
				c.attackLevelReq = 75;
			break;
			case 11698: // Saradomin godsword
				c.attackLevelReq = 75;
			break;
			case 11700: // Zamorak godsword
				c.attackLevelReq = 75;
			break;
			case 11730: // Saradomin sword
				c.attackLevelReq = 70;
			break;
			case 15298:
			case 15299:
			case 15300:
			case 15301:
			case 15315:
			case 4151: // Abyssal whip
				c.attackLevelReq = 70;
			break;
			case 4718: // Dharok's greataxe
			case 4747: // Torag's hammers
				c.attackLevelReq = 70;
				c.strengthLevelReq = 70;
			break;
			case 4726: // Guthan's warspear
			case 4755: // Verac's flail
				c.attackLevelReq = 70;
			break;
			
			case 6528: // Tzhaar-ket-om (obsidian maul / obby maul)
				c.strengthLevelReq = 60;
			break;	
			
			/**
			*Neitiznot // Fremmenik
			**/		
			
			case 10828: // Helm of neitiznot
				c.defenceLevelReq = 55;
			break;
			case 3749: // Archer helm
			case 3755: // Farseer helm
			case 3753: // Warrior helm
			case 3751: // Berserker helm
				c.defenceLevelReq = 45;
			break;

			/**
			*Armor
			**/
			
			case 5574: // Initiate sallet
			case 5575: // Initiate hauberk
			case 5576: // Initiate cuisse
				c.defenceLevelReq = 20;
			break;
		
			case 9672: // Proselyte sallet
			case 9674: // Proselyte hauberk
			case 9676: // Proselyte cuisse
			case 9678: // Proselyte tasset
				c.defenceLevelReq = 30;
			break;
			
			case 11724: // Bandos chestplate
			case 11726: // Bandos tassets
			case 11728: // Bandos boots
				c.defenceLevelReq = 65;
			break;
			
			case 13893: // Vesta's Plateskirt
			case 13887: // Vesta's Chainbody
			case 13884:// Statius's Platebody
			case 13890:// Statius's Platelegs
			case 13896://Statius's Full Helm
				c.defenceLevelReq = 78; 
			break;
			
			case 6151: // Rock shell gloves
			case 6145: // Rock shell boots
			case 6130: // Rock shell legs
			case 6129: // Rock shell body
			case 6128: // Rock shell helm
				c.defenceLevelReq = 40;
			break;				
			
			case 10589: // Granite helm
			case 10564: // Granite body
			case 6809: // Granite legs
			case 3122: // Granite shield
				c.defenceLevelReq = 50;
			break;			

			case 4716: // Dharok's helm
			case 4720: // Dharok's platebody
			case 4722: // Dharok's platelegs
			case 4724: // Guthan's helm
			case 4728: // Guthan's platebody
			case 4730: // Guthan's chainskirt
			case 4745: // Torag's helm
			case 4749: // Torag's platebody
			case 4751: // Torag's platelegs
			case 4753: // Verac's helm
			case 4757: // Verac's brassard
			case 4759: // Verac's plateskirt
				c.defenceLevelReq = 70;
			break;				
			
			/**
			*Shields
			**/

			case 13000: // Arcane spirit shield
			case 13001: // Spectral spirit shield
				c.defenceLevelReq = 75;
				c.magicLevelReq = 65;
				c.prayerLevelReq = 70;
			break;
			case 13003: // Divine spirit shield
			case 13004: // Elysian spirit shield
				c.defenceLevelReq = 75;
				c.prayerLevelReq = 75;
			break;
			case 13005: // Normal spirit shield
				c.defenceLevelReq = 40;
				c.prayerLevelReq = 55;
			break;
			case 13006: // Blessed spirit shield
				c.defenceLevelReq = 70;
				c.prayerLevelReq = 60;
			break;	
			
			case 11283: // Dragonfire shield
			case 11284: // Dragonfire shield
				c.defenceLevelReq = 75;
			break;				

			case 6524: // Obby shield / Toktz ket xil
				c.defenceLevelReq = 60;
			break;	

			
			/**
			*Others
			**/
			case 8901: // Black mask (10)
			case 8903: // Black mask (9)
			case 8905: // Black mask (8)
			case 8907: // Black mask (7)
			case 8909: // Black mask (6)
			case 8911: // Black mask (5)
			case 8913: // Black mask (4)
			case 8915: // Black mask (3)
			case 8917: // Black mask (2)
			case 8919: // Black mask (1)
			case 8921: // Black mask
				c.defenceLevelReq = 10;
				c.strengthLevelReq = 20;
			break;
			case 1131: // Hardleather body
				c.defenceLevelReq = 10;
			break;
			case 10551: // Fighter torso
				c.defenceLevelReq = 40;
			return;
			case 10547: // Healer hat
			case 10548: // Fighter hat
			case 10549: // Runner hat
			case 10550: // Ranger hat
				c.defenceLevelReq = 45;
			return;
			
	//------------ Others ----------//	

			/**
			*Void / Misc
			**/
			case 11663:
			case 11664:
			case 8840:
			case 8839:
			case 8841: //Third age range // 3rd age
				c.rangeLevelReq = 42;
				c.defenceLevelReq = 42;
				c.magicLevelReq = 42;
				c.attackLevelReq = 42;
			break;

			/**
			*Third age / 3rd age
			**/
			
			case 10330:
			case 10332:
			case 10334:
			case 10336: //Third age range // 3rd age
				c.rangeLevelReq = 65;
				c.defenceLevelReq = 45;
			break;
			case 10338:
			case 10340:
			case 10342:
			case 10344:	//Third age mage // 3rd age
				c.magicLevelReq = 65;
				c.defenceLevelReq = 30;
			break;
			case 10346:
			case 10348:
			case 10350:
			case 10352: //Third age melee // 3rd age
				c.defenceLevelReq = 65;
			break;	

			
			
			/**
			*Skillcapes
			**/
			case 9747: // Attack cape
			case 9748: // Attack cape (t)
			case 9749: // Attack hood
				c.attackLevelReq = 99;
			break;
			case 9750: // Strength cape
			case 9751: // Strength cape (t)
			case 9752: // Strength hood
				c.strengthLevelReq = 99;
			break;
			case 9753: // Defence cape
			case 9754: // Defence cape (t)
			case 9755: // Defence hood
				c.defenceLevelReq = 99;
			break;
			case 9756: // Range cape
			case 9757: // Range cape (t)
			case 9758: // Range hood
				c.rangeLevelReq = 99;
			break;
			case 9759: // Prayer cape
			case 9760: // Prayer cape (t)
			case 9761: // Prayer hood
				c.prayerLevelReq = 99;
			break;
			case 9762: // Magic cape
			case 9763: // Magic cape (t)
			case 9764: // Magic hood
				c.magicLevelReq = 99;
			break;
			case 9765: // Runecrafting cape
			case 9766: // Runecrafting cape (t)
			case 9767: // Runecrafting hood
				c.rcLevelReq = 99;
			break;
			case 9768: // Hitpoints cape
			case 9769: // Hitpoints cape (t)
			case 9770: // Hitpoints hood
				c.hitpointsLevelReq = 99;
			break;
			case 9771: // Agility cape
			case 9772: // Agility cape (t)
			case 9773: // Agility hood
				c.agilityLevelReq = 99;
			break;
			case 9774: // Herblore cape
			case 9775: // Herblore cape (t)
			case 9776: // Herblore hood
				c.herbloreLevelReq = 99;
			break;
			case 9777: // Thieving cape
			case 9778: // Thieving cape (t)
			case 9779: // Thieving hood
				c.thievingLevelReq = 99;
			break;
			case 9780: // Crafting cape
			case 9781: // Crafting cape (t)
			case 9782: // Crafting hood
				c.craftingLevelReq = 99;
			break;
			case 9783: // Fletching cape
			case 9784: // Fletching cape (t)
			case 9785: // Fletching hood
				c.fletchingLevelReq = 99;
			break;
			case 9786: // Slayer cape
			case 9787: // Slayer cape (t)
			case 9788: // Slayer hood
				c.slayerLevelReq = 99;
			break;
			case 9792: // Mining cape
			case 9793: // Mining cape (t)
			case 9794: // Mining hood
				c.miningLevelReq = 99;
			break;
			case 9795: // Smithing cape
			case 9796: // Smithing cape (t)
			case 9797: // Smithing hood
				c.smithingLevelReq = 99;
			break;
			case 9798: // Fishing cape
			case 9799: // Fishing cape (t)
			case 9800: // Fishing hood
				c.fishingLevelReq = 99;
			break;
			case 9801: // Cooking cape
			case 9802: // Cooking cape (t)
			case 9803: // Cooking hood
				c.cookingLevelReq = 99;
			break;
			case 9804: // Firemaking cape
			case 9805: // Firemaking cape (t)
			case 9806: // Firemaking hood
				c.fmLevelReq = 99;
			break;
			case 9807: // Woodcutting cape
			case 9808: // Woodcutting cape (t)
			case 9809: // Woodcutting hood
				c.wcLevelReq = 99;
			break;
			case 9810: // Farming cape
			case 9811: // Farming cape (t)
			case 9812: // Farming hood
				c.farmingLevelReq = 99;
			break;
			case 9813: // quest
			case 9814: // quest
				c.miningLevelReq = 99;
				c.smithingLevelReq = 99;
				c.wcLevelReq = 99;
				c.fmLevelReq = 99;
				c.cookingLevelReq = 99;
				c.fletchingLevelReq = 99;
				c.craftingLevelReq = 99;
				c.thievingLevelReq = 99;
				c.herbloreLevelReq = 99;
				c.rcLevelReq = 99;
				c.fishingLevelReq = 99;
			break;
			
			/**
			*RFD gloves / recipe for disaster
			**/
			
			case 7460: // Rune gloves
				c.defenceLevelReq = 20;
			break;
			case 7461: // Dragon gloves
				c.defenceLevelReq = 45;
			break;
			case 7462: // Barrow gloves
				c.defenceLevelReq = 45;
			break;			
			
			/**
			*Void
			case 8839: // Void knight top
			case 8840: // Void knight bottom
			case 8842: // Void knight gloves
			case 11663: // Void mage helm
			case 11664: // Void range helm
			case 11665: // Void melee helm
				c.attackLevelReq = 42;
				c.strengthLevelReq = 42;
				c.rangeLevelReq = 42;
				c.defenceLevelReq = 42;
				c.magicLevelReq = 42;
			break;
			**/			

					
			/**
			*Others
			**/
			// God robes
			case 10458: // Saradomin robe top
			case 10460: // Zamorak robe top
			case 10462: // Guthix robe top			
			case 10464: // Saradomin robe bottom
			case 10466: // Zamorak robe bottom
			case 10468: // Guthix robe bottom			
				c.prayerLevelReq = 20;
			break;			
			case 10446: // Saradomin cloak
			case 10448: // Guthix cloak
			case 10450: // Zamorak cloak			
				c.prayerLevelReq = 40;
			break;					
			case 10452: // Saradomin mitre
			case 10454: // Guthix mitre
			case 10456: // Zamorak mitre			
				c.prayerLevelReq = 40;
				c.magicLevelReq = 40;
			break;	
			case 10470: // Saradomin stole
			case 10472: // Guthix stole
			case 10474: // Zamorak stole			
				c.prayerLevelReq = 60;
			break;			

			// Crystal
			case 4214: // Crystal bow full
			case 4215: // Crystal bow 9/10
			case 4216: // Crystal bow 8/10
			case 4217: // Crystal bow 7/10
			case 4218: // Crystal bow 6/10
			case 4219: // Crystal bow 5/10
			case 4220: // Crystal bow 4/10
			case 4221: // Crystal bow 3/10
			case 4222: // Crystal bow 2/10
			case 4223: // Crystal bow 1/10
				c.rangeLevelReq = 70;
				c.agilityLevelReq = 50;			
			break;
			case 4225: // Crystal shield full
			case 4226: // Crystal shield 9/10
			case 4227: // Crystal shield 8/10
			case 4228: // Crystal shield 7/10
			case 4229: // Crystal shield 6/10
			case 4230: // Crystal shield 5/10
			case 4231: // Crystal shield 4/10
			case 4232: // Crystal shield 3/10
			case 4233: // Crystal shield 2/10
			case 4234: // Crystal shield 1/10
				c.defenceLevelReq = 70;
				c.agilityLevelReq = 50;			
			break;			
			
		}
		if(itemName.contains("spear") || itemName.contains("dagger")) {
			if(itemName.contains("Steel")) {
				c.attackLevelReq = 5;
			}
			if(itemName.contains("Black")) {
				c.attackLevelReq = 10;
			}
			if(itemName.contains("Mithril")) {
				c.attackLevelReq = 20;
			}
			if(itemName.contains("Adam")) {
				c.attackLevelReq = 30;
			}
			if(itemName.contains("Rune")) {
				c.attackLevelReq = 40;
			}
			if(itemName.contains("Dragon")) {
				c.attackLevelReq = 60;
			}
		}
	}
	/**
	*two handed weapon check
	**/
	public boolean is2handed(String itemName, int itemId) {
		if(itemName.contains("ahrim") || itemName.contains("karil") || itemName.contains("verac") || itemName.contains("guthan") || itemName.contains("dharok") || itemName.contains("torag")) {
			return true;
		}
		if(itemName.contains("longbow") || itemName.contains("shortbow") || itemName.contains("ark bow")) {
			return true;
		}
		if(itemName.contains("crystal")) {
			return true;
		}
		if (itemName.contains("godsword") || itemName.contains("claws") || itemName.contains("aradomin sword") || itemName.contains("2h") || itemName.contains("spear")){ 
			return true;
		}
		switch(itemId) {
			case 6724: // seercull
			case 11730:
			case 4153:
			case 6528:
			case 14484:
			case 15313:
			case 10887:
			return true;
		}
		return false;
	}
	
	/**
	* Weapons special bar, adds the spec bars to weapons that require them
	* and removes the spec bars from weapons which don't require them
	**/
	
	public void addSpecialBar(int weapon) {
		switch(weapon) {
			
			case 15299:
			case 15300:
			case 15315:
			case 15298:
			case 4151: // whip
			c.getPA().sendFrame171(0, 12323);
			specialAmount(weapon, c.specAmount, 12335);
			break;
			
			case 859: // magic bows
			case 861:
			case 11235:
			c.getPA().sendFrame171(0, 7549);
			specialAmount(weapon, c.specAmount, 7561);
			break;
			
			case 4587: // dscimmy
			c.getPA().sendFrame171(0, 7599);
			specialAmount(weapon, c.specAmount, 7611);
			break;
			
			case 3204: // d hally
			c.getPA().sendFrame171(0, 8493);
			specialAmount(weapon, c.specAmount, 8505);
			break;
			
			case 1377: // d battleaxe
			c.getPA().sendFrame171(0, 7499);
			specialAmount(weapon, c.specAmount, 7511);
			break;
			
			case 4153: // gmaul
			case 13902:
			c.getPA().sendFrame171(0, 7474);
			specialAmount(weapon, c.specAmount, 7486);
			break;
			
			case 1249: //dspear
			c.getPA().sendFrame171(0, 7674);
			specialAmount(weapon, c.specAmount, 7686);
			break;
		
			
			case 1215:// dragon dagger
			case 1231:
			case 5680:
			case 5698:
			case 1305: // dragon long
			case 10887:
			case 11694:
			case 11698:
			case 11700:
			case 11730:
			case 11696:
			case 13899:
			case 14484:
			c.getPA().sendFrame171(0, 7574); 
			specialAmount(weapon, c.specAmount, 7586);
			break;

			case 1434: // dragon mace
			c.getPA().sendFrame171(0, 7624);
			specialAmount(weapon, c.specAmount, 7636);
			break;
			
			default:
			c.getPA().sendFrame171(1, 7624); // mace interface
			c.getPA().sendFrame171(1, 7474); // hammer, gmaul
			c.getPA().sendFrame171(1, 7499); // axe
			c.getPA().sendFrame171(1, 7549);  // bow interface
			c.getPA().sendFrame171(1, 7574); // sword interface
			c.getPA().sendFrame171(1, 7599); // scimmy sword interface, for most swords
			c.getPA().sendFrame171(1, 8493);
			c.getPA().sendFrame171(1, 12323); // whip interface
			break;		
		}
	}
	
	/**
	* Specials bar filling amount
	**/
	
	public void specialAmount(int weapon, double specAmount, int barId) {
		c.specBarId = barId;
		c.getPA().sendFrame70(specAmount >= 10 ? 500 : 0, 0, (--barId));
        c.getPA().sendFrame70(specAmount >= 9 ? 500 : 0, 0, (--barId));
        c.getPA().sendFrame70(specAmount >= 8 ? 500 : 0, 0, (--barId));
		c.getPA().sendFrame70(specAmount >= 7 ? 500 : 0, 0, (--barId));
		c.getPA().sendFrame70(specAmount >= 6 ? 500 : 0, 0, (--barId));
		c.getPA().sendFrame70(specAmount >= 5 ? 500 : 0, 0, (--barId));
		c.getPA().sendFrame70(specAmount >= 4 ? 500 : 0, 0, (--barId));
		c.getPA().sendFrame70(specAmount >= 3 ? 500 : 0, 0, (--barId));
		c.getPA().sendFrame70(specAmount >= 2 ? 500 : 0, 0, (--barId));
		c.getPA().sendFrame70(specAmount >= 1 ? 500 : 0, 0, (--barId));	
		updateSpecialBar();
		sendWeapon(weapon, getItemName(weapon));
	}
	
	/**
	* Special attack text and what to highlight or blackout
	**/
	
	public void updateSpecialBar() {
		if(c.usingSpecial) {
			c.getPA().sendFrame126(
			""+(c.specAmount >= 2 ?  "@yel@S P" : "@bla@S P")
			+""+(c.specAmount >= 3 ?  "@yel@ E" : "@bla@ E") 
			+""+(c.specAmount >= 4 ?  "@yel@ C I" : "@bla@ C I")
			+""+(c.specAmount >= 5 ?  "@yel@ A L" : "@bla@ A L") 
			+""+(c.specAmount >= 6 ?  "@yel@  A" : "@bla@  A") 
			+""+(c.specAmount >= 7 ?  "@yel@ T T" : "@bla@ T T") 
			+""+(c.specAmount >= 8 ?  "@yel@ A" : "@bla@ A") 
			+""+(c.specAmount >= 9 ?  "@yel@ C" : "@bla@ C") 
			+""+(c.specAmount >= 10 ?  "@yel@ K" : "@bla@ K") , c.specBarId);
		} else {
			c.getPA().sendFrame126("@bla@S P E C I A L  A T T A C K", c.specBarId);
		}
	}
	
	
	/**
	*Wear Item
	**/

	public boolean wearItem(int wearID, int slot) {
		synchronized(c) {
			int targetSlot=0;
			boolean canWearItem = true;
			if(c.playerItems[slot] == (wearID+1)) {				
				getRequirements(getItemName(wearID).toLowerCase(), wearID);	
				targetSlot = Item.targetSlots[wearID];

if(wearID == 14879) {
	if(c.playerEquipment[c.playerRing] == -1) {
	wearID = -1;
	deleteItem(14879,1);
	addItem(995,750000);
	c.sendMessage("You have gotten 750k");
	} else {
	c.sendMessage("You must remove your ring to do that!");
	return false;
	}
	}
	if(wearID == 14878) {
	if(c.playerEquipment[c.playerRing] == -1) {
	wearID = -1;
	deleteItem(14878,1);
	addItem(995,550000);
	c.sendMessage("You have gotten 550k");
	} else {
	c.sendMessage("You must remove your ring to do that!");
	return false;
	}
	}
	if(wearID == 14877) {
	if(c.playerEquipment[c.playerRing] == -1) {
	wearID = -1;
	deleteItem(14877,1);
	addItem(995,550000);
	c.sendMessage("You have gotten 550k");
	} else {
	c.sendMessage("You must remove your ring to do that!");
	return false;
	}
	}
	if(wearID == 14876) {
	if(c.playerEquipment[c.playerRing] == -1) {
	wearID = -1;
	deleteItem(14876,1);
	addItem(995,450000);
	c.sendMessage("You have gotten 450k");
	} else {
	c.sendMessage("You must remove your ring to do that!");
	return false;
	}
	}
	if(wearID == 14880) {
	if(c.playerEquipment[c.playerRing] == -1) {
	wearID = -1;
	deleteItem(14880, 1);
	addItem(995,750000);
	c.sendMessage("You have gotten 750k");
	} else {
	c.sendMessage("You must remove your ring to do that!");
	return false;
	}
	}
	if(wearID == 14881) {
	if(c.playerEquipment[c.playerRing] == -1) {
	wearID = -1;
	deleteItem(14881, 1);
	addItem(995,750000);
	c.sendMessage("You have gotten 750k");
	} else {
	c.sendMessage("You must remove your ring to do that!");
	return false;
	}
	}
	if(wearID == 14882) {
	if(c.playerEquipment[c.playerRing] == -1) {
	wearID = -1;
	deleteItem(14882, 1);
	addItem(995,250000);
	c.sendMessage("You have gotten 250k");
	} else {
	c.sendMessage("You must remove your ring to do that!");
	return false;
	}
	}
	if(wearID == 14883) {
	if(c.playerEquipment[c.playerRing] == -1) {
	wearID = -1;
	deleteItem(14883, 1);
	addItem(995,500000);
	c.sendMessage("You have gotten 500k");
	} else {
	c.sendMessage("You must remove your ring to do that!");
	return false;
	}
	}
	if(wearID == 14884) {
	if(c.playerEquipment[c.playerRing] == -1) {
	wearID = -1;
	deleteItem(14884, 1);
	addItem(995,1000000);
	c.sendMessage("You have gotten 1m");
	} else {
	c.sendMessage("You must remove your ring to do that!");
	return false;
	}
	}
	if(wearID == 14885) {
	if(c.playerEquipment[c.playerRing] == -1) {
	wearID = -1;
	deleteItem(14885, 1);
	addItem(995,750000);
	c.sendMessage("You have gotten 750k");
	} else {
	c.sendMessage("You must remove your ring to do that!");
	return false;
	}
	}
	if(wearID == 14886) {
	if(c.playerEquipment[c.playerRing] == -1) {
	wearID = -1;
	deleteItem(14886, 1);
	addItem(995,550000);
	c.sendMessage("You have gotten 550k");
	} else {
	c.sendMessage("You must remove your ring to do that!");
	return false;
	}
	}
	if(wearID == 14887) {
	if(c.playerEquipment[c.playerRing] == -1) {
	wearID = -1;
	deleteItem(14887, 1);
	addItem(995,650000);
	c.sendMessage("You have gotten 650k");
	} else {
	c.sendMessage("You must remove your ring to do that!");
	return false;
	}
	}
	if(wearID == 14888) {
	if(c.playerEquipment[c.playerRing] == -1) {
	wearID = -1;
	deleteItem(14888, 1);
	addItem(995,150000);
	c.sendMessage("You have gotten 150k");
	} else {
	c.sendMessage("You must remove your ring to do that!");
	return false;
	}
	}
	if(wearID == 14889) {
	if(c.playerEquipment[c.playerRing] == -1) {
	wearID = -1;
	deleteItem(14889, 1);
	addItem(995,150000);
	c.sendMessage("You have gotten 150k");
	} else {
	c.sendMessage("You must remove your ring to do that!");
	return false;
	}
	}
	if(wearID == 14890) {
	if(c.playerEquipment[c.playerRing] == -1) {
	wearID = -1;
	deleteItem(14890, 1);
	addItem(995,350000);
	c.sendMessage("You have gotten 350k");
	} else {
	c.sendMessage("You must remove your ring to do that!");
	return false;
	}
	}
	if(wearID == 14891) {
	if(c.playerEquipment[c.playerRing] == -1) {
	wearID = -1;
	deleteItem(14891,1);
	addItem(995,200000);
	c.sendMessage("You have gotten 200k");
	} else {
	c.sendMessage("You must remove your ring to do that!");
	return false;
	}
	}
	if(wearID == 14892) {
	if(c.playerEquipment[c.playerRing] == -1) {
	wearID = -1;
	deleteItem(14892,1);
	addItem(995,150000);
	c.sendMessage("You have gotten 150k");
	} else {
	c.sendMessage("You must remove your ring to do that!");
	return false;
	}
	}
	
				/*if(itemType(wearID).equalsIgnoreCase("cape")) {
					targetSlot=1;
				} else if(itemType(wearID).equalsIgnoreCase("hat")) {
					targetSlot=0;
				} else if(itemType(wearID).equalsIgnoreCase("amulet")) {
					targetSlot=2;
				} else if(itemType(wearID).equalsIgnoreCase("arrows")) {
					targetSlot=13;
				} else if(itemType(wearID).equalsIgnoreCase("body")) {
					targetSlot=4;
				} else if(itemType(wearID).equalsIgnoreCase("shield")) {
					targetSlot=5;
				} else if(itemType(wearID).equalsIgnoreCase("legs")) {
					targetSlot=7;
				} else if(itemType(wearID).equalsIgnoreCase("gloves")) {
					targetSlot=9;
				} else if(itemType(wearID).equalsIgnoreCase("boots")) {
					targetSlot=10;	
				} else if(itemType(wearID).equalsIgnoreCase("ring")) {
					targetSlot=12;
				} else {
					targetSlot = 3;
				}*/
				switch (wearID) {
					case 13858:
						targetSlot = 4;
					break;
					case 13861:
						targetSlot = 7;
					break;
					case 13893:
						targetSlot = 7;
					break;	
					case 13870:
						targetSlot = 4;
					break;
					case 13873:
						targetSlot = 7;
					break;
					case 13876:
						targetSlot = 0;
					break;
					case 13879:
						targetSlot = 3;
					break;
					case 13883:
						targetSlot = 3;
					break;
					case 15286:
						targetSlot = 3;
					break;
					case 10778:
						targetSlot = 4;
					break;
					case 10780:
						targetSlot = 4;
					break;
					case 10776:
						targetSlot = 4;
					break;
					case 6181:
						targetSlot = 7;
					break;
					case 13884:
						targetSlot = 4;
					break;
					case 13890: 
						targetSlot = 7;
					break;
					case 13896: 
						targetSlot = 0;
					break;
					case 1005:
						targetSlot = 4;
					break;
				}
				if (wearID == 15220 || wearID == 15020 || wearID == 15019 || wearID == 15018) {
					targetSlot = 12;
				}
				if (wearID == 15220 || wearID == 15020 || wearID == 15019 || wearID == 15018) {
					targetSlot = 12;
				}
				if ((wearID == 15000 || wearID == 13899 || wearID == 14479 || wearID == 15284 || wearID == 15285 || wearID == 13902 || wearID == 13738 || wearID == 13740 || wearID == 13742 || wearID == 13744 || wearID == 15318) && (c.memberStatus == 0)){
					c.sendMessage("This is a donator only item.");
					return false;
				}/*else if ((wearID == 15000 || wearID == 13899 || wearID == 14479 || wearID == 15284 || wearID == 15285 || wearID == 13902) && (c.memberStatus == 1)){
					c.memberStatus = 1;
					return false;
				}*/
				if ((wearID == 15319) && (c.KC <= 1000)){
					c.sendMessage("You need a KC of 1000 to use this item.");
					return false;
				}
				if ((wearID == 15320) && (c.playerRights <= 0)){
					c.sendMessage("This is a mod only cape.");
					return false;
				}
				if ((wearID == 15321) && (c.playerRights <= 1)){
					c.sendMessage("This is a admin only cape.");
					return false;
				}
				if(c.duelRule[11] && targetSlot == 0) {
					c.sendMessage("Wearing hats has been disabled in this duel!");
					return false;
				}
				if(c.duelRule[12] && targetSlot == 1) {
					c.sendMessage("Wearing capes has been disabled in this duel!");
					return false;
				}
				if(c.duelRule[13]  && targetSlot == 2) {
					c.sendMessage("Wearing amulets has been disabled in this duel!");
					return false;
				}
				if(c.duelRule[14]  && targetSlot == 3) {
					c.sendMessage("Wielding weapons has been disabled in this duel!");
					return false;
				}
				if(c.duelRule[15]  && targetSlot == 4) {
					c.sendMessage("Wearing bodies has been disabled in this duel!");
					return false;
				}
				if((c.duelRule[16] && targetSlot == 5) || (c.duelRule[16] && is2handed(getItemName(wearID).toLowerCase(), wearID))) {
					c.sendMessage("Wearing shield has been disabled in this duel!");
					return false;
				}
				if(c.duelRule[17]  && targetSlot == 7) {
					c.sendMessage("Wearing legs has been disabled in this duel!");
					return false;
				}
				if(c.duelRule[18]  && targetSlot == 9) {
					c.sendMessage("Wearing gloves has been disabled in this duel!");
					return false;
				}
				if(c.duelRule[19]  && targetSlot == 10) {
					c.sendMessage("Wearing boots has been disabled in this duel!");
					return false;
				}
				if(c.duelRule[20]  && targetSlot == 12) {
					c.sendMessage("Wearing rings has been disabled in this duel!");
					return false;
				}
				if(c.duelRule[21]  && targetSlot == 13) {
					c.sendMessage("Wearing arrows has been disabled in this duel!");
					return false;
				}

				if(Config.itemRequirements) {
					if(targetSlot == 1 || targetSlot == 0) {
						if(c.attackLevelReq > 0) { // Attack
							if(c.getPA().getLevelForXP(c.playerXP[0]) < c.attackLevelReq) {
								c.sendMessage("You need an attack level of "+c.attackLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}	
						if(c.defenceLevelReq > 0) { // Defence
							if(c.getPA().getLevelForXP(c.playerXP[1]) < c.defenceLevelReq) {
								c.sendMessage("You need a defence level of "+c.defenceLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
						if(c.strengthLevelReq > 0) { // Strength
							if(c.getPA().getLevelForXP(c.playerXP[2]) < c.strengthLevelReq) {
								c.sendMessage("You need a strength level of "+c.strengthLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
						if(c.hitpointsLevelReq > 0) { // Hitpoints
							if(c.getPA().getLevelForXP(c.playerXP[3]) < c.hitpointsLevelReq) {
								c.sendMessage("You need a hitpoints level of "+c.hitpointsLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
						if(c.rangeLevelReq > 0) { // ranged
							if(c.getPA().getLevelForXP(c.playerXP[4]) < c.rangeLevelReq) {
								c.sendMessage("You need a ranged level of "+c.rangeLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
						if(c.prayerLevelReq > 0) { // prayer
							if(c.getPA().getLevelForXP(c.playerXP[5]) < c.prayerLevelReq) {
								c.sendMessage("You need a prayer level of "+c.prayerLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
						if(c.magicLevelReq > 0) { // magic
							if(c.getPA().getLevelForXP(c.playerXP[6]) < c.magicLevelReq) {
								c.sendMessage("You need a magic level of "+c.magicLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
						if(c.cookingLevelReq > 0) { // cooking
							if(c.getPA().getLevelForXP(c.playerXP[7]) < c.cookingLevelReq) {
								c.sendMessage("You need a cooking level of "+c.cookingLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
						if(c.wcLevelReq > 0) { // wc
							if(c.getPA().getLevelForXP(c.playerXP[8]) < c.wcLevelReq) {
								c.sendMessage("You need a woodcutting level of "+c.wcLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
						if(c.fletchingLevelReq > 0) { // fletching
							if(c.getPA().getLevelForXP(c.playerXP[9]) < c.fletchingLevelReq) {
								c.sendMessage("You need a fletching level of "+c.fletchingLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
						if(c.fishingLevelReq > 0) { // fishing
							if(c.getPA().getLevelForXP(c.playerXP[10]) < c.fishingLevelReq) {
								c.sendMessage("You need a fishing level of "+c.fishingLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
						if(c.fmLevelReq > 0) { // fm
							if(c.getPA().getLevelForXP(c.playerXP[11]) < c.fmLevelReq) {
								c.sendMessage("You need a firemaking level of "+c.fmLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
						if(c.craftingLevelReq > 0) { // crafting
							if(c.getPA().getLevelForXP(c.playerXP[12]) < c.craftingLevelReq) {
								c.sendMessage("You need a crafting level of "+c.craftingLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
						if(c.smithingLevelReq > 0) { // smithing
							if(c.getPA().getLevelForXP(c.playerXP[13]) < c.smithingLevelReq) {
								c.sendMessage("You need a smithing level of "+c.smithingLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
						if(c.miningLevelReq > 0) { // mining
							if(c.getPA().getLevelForXP(c.playerXP[14]) < c.miningLevelReq) {
								c.sendMessage("You need a mining level of "+c.miningLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
						if(c.herbloreLevelReq > 0) { // herblore
							if(c.getPA().getLevelForXP(c.playerXP[15]) < c.herbloreLevelReq) {
								c.sendMessage("You need a herblore level of "+c.herbloreLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
						if(c.agilityLevelReq > 0) { // agility
							if(c.getPA().getLevelForXP(c.playerXP[16]) < c.agilityLevelReq) {
								c.sendMessage("You need an agility level of "+c.agilityLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
						if(c.thievingLevelReq > 0) { // thieving
							if(c.getPA().getLevelForXP(c.playerXP[17]) < c.thievingLevelReq) {
								c.sendMessage("You need a thieving level of "+c.thievingLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
						if(c.slayerLevelReq > 0) { // slayer
							if(c.getPA().getLevelForXP(c.playerXP[18]) < c.slayerLevelReq) {
								c.sendMessage("You need a slayer level of "+c.slayerLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
						if(c.farmingLevelReq > 0) { // farming
							if(c.getPA().getLevelForXP(c.playerXP[19]) < c.farmingLevelReq) {
								c.sendMessage("You need a farming level of "+c.farmingLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
						if(c.rcLevelReq > 0) { // rc
							if(c.getPA().getLevelForXP(c.playerXP[20]) < c.rcLevelReq) {
								c.sendMessage("You need a runecrafting level of "+c.rcLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
					}
					if(targetSlot == 10 || targetSlot == 7 || targetSlot == 2 || targetSlot == 5 || targetSlot == 4 || targetSlot == 9 || targetSlot == 10) {
						if(c.defenceLevelReq > 0) { // Defensive
							if(c.getPA().getLevelForXP(c.playerXP[1]) < c.defenceLevelReq) {
								c.sendMessage("You need a defence level of "+c.defenceLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
						if(c.rangeLevelReq > 0) {
							if(c.getPA().getLevelForXP(c.playerXP[4]) < c.rangeLevelReq) {
								c.sendMessage("You need a range level of "+c.rangeLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
						if(c.magicLevelReq > 0) {
							if(c.getPA().getLevelForXP(c.playerXP[6]) < c.magicLevelReq) {
								c.sendMessage("You need a magic level of "+c.magicLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
						if(c.prayerLevelReq > 0) {
							if(c.getPA().getLevelForXP(c.playerXP[5]) < c.prayerLevelReq) {
								c.sendMessage("You need a prayer level of "+c.prayerLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
						if(c.agilityLevelReq > 0) { // agility
							if(c.getPA().getLevelForXP(c.playerXP[16]) < c.agilityLevelReq) {
								c.sendMessage("You need an agility level of "+c.agilityLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
					}
					if(wearID == 8839 || wearID == 8840 || wearID == 8842 || wearID == 11663 || wearID == 11664 || wearID == 11665) { // Void
						if(c.attackLevelReq > 0) {
							if(c.getPA().getLevelForXP(c.playerXP[0]) < c.attackLevelReq) {
								c.sendMessage("You need an attack level of "+c.attackLevelReq+" to wield this weapon.");
								canWearItem = false;
							}
						}
						if(c.strengthLevelReq > 0) {
							if(c.getPA().getLevelForXP(c.playerXP[2]) < c.strengthLevelReq) {
								c.sendMessage("You need an strength level of "+c.strengthLevelReq+" to wield this weapon.");
								canWearItem = false;
							}
						}
						if(c.rangeLevelReq > 0) {
							if(c.getPA().getLevelForXP(c.playerXP[4]) < c.rangeLevelReq) {
								c.sendMessage("You need a range level of "+c.rangeLevelReq+" to wield this weapon.");
								canWearItem = false;
							}
						}
						if(c.magicLevelReq > 0) {
							if(c.getPA().getLevelForXP(c.playerXP[6]) < c.magicLevelReq) {
								c.sendMessage("You need a magic level of "+c.magicLevelReq+" to wield this weapon.");
								canWearItem = false;
							}
						}
						if(c.defenceLevelReq > 0) {
							if(c.getPA().getLevelForXP(c.playerXP[1]) < c.defenceLevelReq) {
								c.sendMessage("You need a defence level of "+c.defenceLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
					}						
					if(targetSlot == 3 || targetSlot == 13) { // Attack
						if(c.attackLevelReq > 0) {
							if(c.getPA().getLevelForXP(c.playerXP[0]) < c.attackLevelReq) {
								c.sendMessage("You need an attack level of "+c.attackLevelReq+" to wield this weapon.");
								canWearItem = false;
							}
						}
						if(c.agilityLevelReq > 0) { // agility
							if(c.getPA().getLevelForXP(c.playerXP[16]) < c.agilityLevelReq) {
								c.sendMessage("You need an agility level of "+c.agilityLevelReq+" to wear this item.");
								canWearItem = false;
							}
						}
						if(c.strengthLevelReq > 0) {
							if(c.getPA().getLevelForXP(c.playerXP[2]) < c.strengthLevelReq) {
								c.sendMessage("You need an strength level of "+c.strengthLevelReq+" to wield this weapon.");
								canWearItem = false;
							}
						}
						if(c.rangeLevelReq > 0) {
							if(c.getPA().getLevelForXP(c.playerXP[4]) < c.rangeLevelReq) {
								c.sendMessage("You need a range level of "+c.rangeLevelReq+" to wield this weapon.");
								canWearItem = false;
							}
						}
						if(c.magicLevelReq > 0) {
							if(c.getPA().getLevelForXP(c.playerXP[6]) < c.magicLevelReq) {
								c.sendMessage("You need a magic level of "+c.magicLevelReq+" to wield this weapon.");
								canWearItem = false;
							}
						}
					}
}

				if(!canWearItem) {
					return false;
				}
				
				int wearAmount = c.playerItemsN[slot];
				if (wearAmount < 1) {
					return false;
				}
				if (targetSlot == c.playerWeapon) {
					c.autocasting = false;
					c.autocastId = 0;
					c.getPA().sendFrame36(108, 0);
				}

				if(slot >= 0 && wearID >= 0) {
					int toEquip = c.playerItems[slot];
					int toEquipN = c.playerItemsN[slot];
					int toRemove = c.playerEquipment[targetSlot];
					int toRemoveN = c.playerEquipmentN[targetSlot];
					if (toEquip == toRemove + 1 && Item.itemStackable[toRemove]) {
						deleteItem(toRemove, getItemSlot(toRemove), toEquipN);
						c.playerEquipmentN[targetSlot] += toEquipN;
					} else if (targetSlot != 5 && targetSlot != 3) {
						c.playerItems[slot] = toRemove + 1;
						c.playerItemsN[slot] = toRemoveN;
						c.playerEquipment[targetSlot] = toEquip - 1;
						c.playerEquipmentN[targetSlot] = toEquipN;
					} else if (targetSlot == 5) {
						boolean wearing2h = is2handed(getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase(), c.playerEquipment[c.playerWeapon]);
						boolean wearingShield = c.playerEquipment[c.playerShield] > 0;
						if (wearing2h) {
							toRemove = c.playerEquipment[c.playerWeapon];
							toRemoveN = c.playerEquipmentN[c.playerWeapon];
							c.playerEquipment[c.playerWeapon] = -1;
							c.playerEquipmentN[c.playerWeapon] = 0;
							updateSlot(c.playerWeapon);
						}
						c.playerItems[slot] = toRemove + 1;
						c.playerItemsN[slot] = toRemoveN;
						c.playerEquipment[targetSlot] = toEquip - 1;
						c.playerEquipmentN[targetSlot] = toEquipN;			
					} else if (targetSlot == 3) {
						boolean is2h = is2handed(getItemName(wearID).toLowerCase(), wearID);
						boolean wearingShield = c.playerEquipment[c.playerShield] > 0;
						boolean wearingWeapon = c.playerEquipment[c.playerWeapon] > 0;
						if (is2h) {
							if (wearingShield && wearingWeapon) {
								if (freeSlots() > 0) {
									c.playerItems[slot] = toRemove + 1;
									c.playerItemsN[slot] = toRemoveN;
									c.playerEquipment[targetSlot] = toEquip - 1;
									c.playerEquipmentN[targetSlot] = toEquipN;
									removeItem(c.playerEquipment[c.playerShield], c.playerShield);
								} else {
									c.sendMessage("You do not have enough inventory space to do this.");
									return false;
								}						
							} else if (wearingShield && !wearingWeapon) {
								c.playerItems[slot] = c.playerEquipment[c.playerShield] + 1;
								c.playerItemsN[slot] = c.playerEquipmentN[c.playerShield];
								c.playerEquipment[targetSlot] = toEquip - 1;
								c.playerEquipmentN[targetSlot] = toEquipN;
								c.playerEquipment[c.playerShield] = -1;
								c.playerEquipmentN[c.playerShield] = 0;
								updateSlot(c.playerShield);
							} else {
								c.playerItems[slot] = toRemove + 1;
								c.playerItemsN[slot] = toRemoveN;
								c.playerEquipment[targetSlot] = toEquip - 1;
								c.playerEquipmentN[targetSlot] = toEquipN;	
							}
						} else {
							c.playerItems[slot] = toRemove + 1;
							c.playerItemsN[slot] = toRemoveN;
							c.playerEquipment[targetSlot] = toEquip - 1;
							c.playerEquipmentN[targetSlot] = toEquipN;	
						}
					}
					resetItems(3214);
				}
				if(targetSlot == 3) {
					c.usingSpecial = false;
					addSpecialBar(wearID);
				}
				if(c.getOutStream() != null && c != null ) {
					c.getOutStream().createFrameVarSizeWord(34);
					c.getOutStream().writeWord(1688);
					c.getOutStream().writeByte(targetSlot);
					c.getOutStream().writeWord(wearID+1);

					if (c.playerEquipmentN[targetSlot] > 254) {
						c.getOutStream().writeByte(255);
						c.getOutStream().writeDWord(c.playerEquipmentN[targetSlot]);
					} else {
						c.getOutStream().writeByte(c.playerEquipmentN[targetSlot]);
					}
					
					c.getOutStream().endFrameVarSizeWord();
					c.flushOutStream();
				}
				sendWeapon(c.playerEquipment[c.playerWeapon], getItemName(c.playerEquipment[c.playerWeapon]));
				resetBonus();
				getBonus();
				writeBonus();
				c.getCombat().getPlayerAnimIndex(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
				c.getPA().requestUpdates();
				return true;
			} else {
				return false;
			}
		}
	}
	
	
	public void wearItem(int wearID, int wearAmount, int targetSlot) {	
		synchronized(c) {
			if(c.getOutStream() != null && c != null ) {
				c.getOutStream().createFrameVarSizeWord(34);
				c.getOutStream().writeWord(1688);
				c.getOutStream().writeByte(targetSlot);
				c.getOutStream().writeWord(wearID+1);

				if (wearAmount > 254) {
					c.getOutStream().writeByte(255);
					c.getOutStream().writeDWord(wearAmount);
				} else {
					c.getOutStream().writeByte(wearAmount);
				}		
				c.getOutStream().endFrameVarSizeWord();
				c.flushOutStream();
				c.playerEquipment[targetSlot]=wearID;
				c.playerEquipmentN[targetSlot]=wearAmount;
				c.getItems().sendWeapon(c.playerEquipment[c.playerWeapon], c.getItems().getItemName(c.playerEquipment[c.playerWeapon]));
				c.getItems().resetBonus();
				c.getItems().getBonus();
				c.getItems().writeBonus();
				c.getCombat().getPlayerAnimIndex(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
				c.updateRequired = true; 
				c.setAppearanceUpdateRequired(true);
			}
		}
	}
	
	public void updateSlot(int slot) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null ) {
				c.getOutStream().createFrameVarSizeWord(34);
				c.getOutStream().writeWord(1688);
				c.getOutStream().writeByte(slot);
				c.getOutStream().writeWord(c.playerEquipment[slot] + 1);
				if (c.playerEquipmentN[slot] > 254) {
					c.getOutStream().writeByte(255);
					c.getOutStream().writeDWord(c.playerEquipmentN[slot]);
				} else {
					c.getOutStream().writeByte(c.playerEquipmentN[slot]);
				}
				c.getOutStream().endFrameVarSizeWord();
				c.flushOutStream();
			}			
		}
	
	}

	/**
	*Remove Item
	**/
	public void removeItem(int wearID, int slot) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				if(c.playerEquipment[slot] > -1){
					if(addItem(c.playerEquipment[slot], c.playerEquipmentN[slot])) {
						c.playerEquipment[slot]=-1;
						c.playerEquipmentN[slot]=0;
						sendWeapon(c.playerEquipment[c.playerWeapon], getItemName(c.playerEquipment[c.playerWeapon]));
						resetBonus();
						getBonus();
						writeBonus();
						c.getCombat().getPlayerAnimIndex(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
						c.getOutStream().createFrame(34);
						c.getOutStream().writeWord(6);
						c.getOutStream().writeWord(1688);
						c.getOutStream().writeByte(slot);
						c.getOutStream().writeWord(0);
						c.getOutStream().writeByte(0);
						c.flushOutStream();
						c.updateRequired = true; 
						c.setAppearanceUpdateRequired(true);
					}
				}
			}
		}
	}
		
	/**
	*BANK
	*/
	
	public void rearrangeBank() {
		int totalItems = 0;
		int highestSlot = 0;
		for (int i = 0; i < Config.BANK_SIZE; i++) {
			if (c.bankItems[i] != 0) { 
				totalItems ++;
				if (highestSlot <= i) {	
					highestSlot = i;
				}
			}  
		}
		
		for (int i = 0; i <= highestSlot; i++) {
			if (c.bankItems[i] == 0) {
				boolean stop = false;
			
			for (int k = i; k <= highestSlot; k++) {
				if (c.bankItems[k] != 0 && !stop) {
					int spots = k - i;
						for (int j = k; j <= highestSlot; j++) {
							c.bankItems[j-spots] = c.bankItems[j];
							c.bankItemsN[j-spots] = c.bankItemsN[j];
							stop = true;
							c.bankItems[j] = 0; c.bankItemsN[j] = 0; 
						}
					}
				}					
			}
		}
		
	int totalItemsAfter = 0;
	for (int i = 0; i < Config.BANK_SIZE; i++) {
		if (c.bankItems[i] != 0) { 
		totalItemsAfter ++; 
		} 
	}
		
	if (totalItems != totalItemsAfter) 
		c.disconnected = true;
	}
	
	
	public void itemOnInterface(int id, int amount) {
		synchronized(c) {
			c.getOutStream().createFrameVarSizeWord(53);
			c.getOutStream().writeWord(2274);
			c.getOutStream().writeWord(1);
			if (amount > 254){
				c.getOutStream().writeByte(255);
				c.getOutStream().writeDWord_v2(amount);
			} else {
				c.getOutStream().writeByte(amount);
			}
			c.getOutStream().writeWordBigEndianA(id); 
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();
		}
	}
	
	public void resetBank(){
		synchronized(c) {
			c.getOutStream().createFrameVarSizeWord(53);
			c.getOutStream().writeWord(5382); // bank
			c.getOutStream().writeWord(Config.BANK_SIZE);
			for (int i=0; i<Config.BANK_SIZE; i++){
				if (c.bankItemsN[i] > 254){
					c.getOutStream().writeByte(255);
					c.getOutStream().writeDWord_v2(c.bankItemsN[i]);
				} else {
					c.getOutStream().writeByte(c.bankItemsN[i]); 	
				}
				if (c.bankItemsN[i] < 1) {
					c.bankItems[i] = 0;
				}
				if (c.bankItems[i] > Config.ITEM_LIMIT || c.bankItems[i] < 0) {
					c.bankItems[i] = Config.ITEM_LIMIT;
				}
				c.getOutStream().writeWordBigEndianA(c.bankItems[i]); 
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();
		}
	}
	
	
	public void resetTempItems(){
		synchronized(c) {
			int itemCount = 0;
			for (int i = 0; i < c.playerItems.length; i++) {
				if (c.playerItems[i] > -1) {
					itemCount=i;
				}
			}
			c.getOutStream().createFrameVarSizeWord(53);
			c.getOutStream().writeWord(5064);
			c.getOutStream().writeWord(itemCount+1); 
			for (int i = 0; i < itemCount+1; i++) {
				if (c.playerItemsN[i] > 254) {
					c.getOutStream().writeByte(255); 						
					c.getOutStream().writeDWord_v2(c.playerItemsN[i]);
				} else {
					c.getOutStream().writeByte(c.playerItemsN[i]);
				}
				if (c.playerItems[i] > Config.ITEM_LIMIT || c.playerItems[i] < 0) {
					c.playerItems[i] = Config.ITEM_LIMIT;
				}
				c.getOutStream().writeWordBigEndianA(c.playerItems[i]); 
			}
			c.getOutStream().endFrameVarSizeWord();	
			c.flushOutStream();
		}
	}
	
	
	public boolean bankItem(int itemID, int fromSlot, int amount){
		if(c.inTrade) {
			c.sendMessage("You can't store items while trading!");
			return false;
		}
		if (c.playerItemsN[fromSlot] <= 0){
			return false;
		}
		if (!Item.itemIsNote[c.playerItems[fromSlot]-1]) {
			if (c.playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Item.itemStackable[c.playerItems[fromSlot]-1] || c.playerItemsN[fromSlot] > 1) {
				int toBankSlot = 0;
				boolean alreadyInBank=false;
			    for (int i=0; i< Config.BANK_SIZE; i++) {
						if (c.bankItems[i] == c.playerItems[fromSlot]) {
							if (c.playerItemsN[fromSlot]<amount)
									amount = c.playerItemsN[fromSlot];
							alreadyInBank = true;
							toBankSlot = i;
							i=Config.BANK_SIZE+1;
						}
				}

				if (!alreadyInBank && freeBankSlots() > 0) {
						for (int i=0; i<Config.BANK_SIZE; i++) {
							if (c.bankItems[i] <= 0) {
									toBankSlot = i;
									i=Config.BANK_SIZE+1;
							}
						}
						c.bankItems[toBankSlot] = c.playerItems[fromSlot];
						if (c.playerItemsN[fromSlot]<amount){
							amount = c.playerItemsN[fromSlot];
						}
						if ((c.bankItemsN[toBankSlot] + amount) <= Config.MAXITEM_AMOUNT && (c.bankItemsN[toBankSlot] + amount) > -1) {
							c.bankItemsN[toBankSlot] += amount;
						} else {
							c.sendMessage("Bank full!");
							return false;
						}
						deleteItem((c.playerItems[fromSlot]-1), fromSlot, amount);
						resetTempItems();
						resetBank();
						return true;
				}
				else if (alreadyInBank) {
						if ((c.bankItemsN[toBankSlot] + amount) <= Config.MAXITEM_AMOUNT && (c.bankItemsN[toBankSlot] + amount) > -1) {
							c.bankItemsN[toBankSlot] += amount;
						} else {
							c.sendMessage("Bank full!");
							return false;
						}
						deleteItem((c.playerItems[fromSlot]-1), fromSlot, amount);
						resetTempItems();
						resetBank();
						return true;
				} else {
						c.sendMessage("Bank full!");
						return false;
				}
			} else {
				itemID = c.playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank=false;
			    for (int i=0; i<Config.BANK_SIZE; i++) {
						if (c.bankItems[i] == c.playerItems[fromSlot]) {
							alreadyInBank = true;
							toBankSlot = i;
							i=Config.BANK_SIZE+1;
						}
				}
				if (!alreadyInBank && freeBankSlots() > 0) {
			       	for (int i=0; i<Config.BANK_SIZE; i++) {
						if (c.bankItems[i] <= 0) {
								toBankSlot = i;
								i=Config.BANK_SIZE+1;
						}
					}
						int firstPossibleSlot=0;
						boolean itemExists = false;
						while (amount > 0) {
							itemExists = false;
							for (int i=firstPossibleSlot; i<c.playerItems.length; i++) {
									if ((c.playerItems[i]) == itemID) {
										firstPossibleSlot = i;
										itemExists = true;
										i=30;
									}
							}
							if (itemExists) {
									c.bankItems[toBankSlot] = c.playerItems[firstPossibleSlot];
									c.bankItemsN[toBankSlot] += 1;
									deleteItem((c.playerItems[firstPossibleSlot]-1), firstPossibleSlot, 1);
									amount--;
							} else {
									amount=0;
							}
						}
						resetTempItems();
						resetBank();
						return true;
				} else if (alreadyInBank) {
						int firstPossibleSlot=0;
						boolean itemExists = false;
						while (amount > 0) {
							itemExists = false;
							for (int i=firstPossibleSlot; i<c.playerItems.length; i++) {
								if ((c.playerItems[i]) == itemID) {
									firstPossibleSlot = i;
									itemExists = true;
									i=30;
								}
							}
							if (itemExists) {
									c.bankItemsN[toBankSlot] += 1;
									deleteItem((c.playerItems[firstPossibleSlot]-1), firstPossibleSlot, 1);
									amount--;
							} else {
									amount=0;
							}
						}
						resetTempItems();
						resetBank();
						return true;
				} else {
						c.sendMessage("Bank full!");
						return false;
				}
			}
		}
		else if (Item.itemIsNote[c.playerItems[fromSlot]-1] && !Item.itemIsNote[c.playerItems[fromSlot]-2]) {
			if (c.playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Item.itemStackable[c.playerItems[fromSlot]-1] || c.playerItemsN[fromSlot] > 1) {
				int toBankSlot = 0;
				boolean alreadyInBank=false;
			    for (int i=0; i<Config.BANK_SIZE; i++) {
						if (c.bankItems[i] == (c.playerItems[fromSlot]-1)) {
							if (c.playerItemsN[fromSlot]<amount)
									amount = c.playerItemsN[fromSlot];
						alreadyInBank = true;
						toBankSlot = i;
						i=Config.BANK_SIZE+1;
						}
				}

				if (!alreadyInBank && freeBankSlots() > 0) {
			       	for (int i=0; i<Config.BANK_SIZE; i++) {
						if (c.bankItems[i] <= 0) {
								toBankSlot = i;
								i=Config.BANK_SIZE+1;
						}
					}
					c.bankItems[toBankSlot] = (c.playerItems[fromSlot]-1);
					if (c.playerItemsN[fromSlot]<amount){
						amount = c.playerItemsN[fromSlot];
					}
					if ((c.bankItemsN[toBankSlot] + amount) <= Config.MAXITEM_AMOUNT && (c.bankItemsN[toBankSlot] + amount) > -1) {
						c.bankItemsN[toBankSlot] += amount;
					} else {
						return false;
					}
					deleteItem((c.playerItems[fromSlot]-1), fromSlot, amount);
					resetTempItems();
					resetBank();
					return true;
				}
				else if (alreadyInBank) {
					if ((c.bankItemsN[toBankSlot] + amount) <= Config.MAXITEM_AMOUNT && (c.bankItemsN[toBankSlot] + amount) > -1) {
						c.bankItemsN[toBankSlot] += amount;
					} else {
						return false;
					}
					deleteItem((c.playerItems[fromSlot]-1), fromSlot, amount);
					resetTempItems();
					resetBank();
					return true;
				} else {
						c.sendMessage("Bank full!");
						return false;
				}
			} else {
				itemID = c.playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank=false;
			    for (int i=0; i<Config.BANK_SIZE; i++) {
					if (c.bankItems[i] == (c.playerItems[fromSlot]-1)) {
						alreadyInBank = true;
						toBankSlot = i;
						i=Config.BANK_SIZE+1;
					}
				}
				if (!alreadyInBank && freeBankSlots() > 0) {
			       	for (int i=0; i<Config.BANK_SIZE; i++) {
						if (c.bankItems[i] <= 0){
								toBankSlot = i;
								i=Config.BANK_SIZE+1;
						}
					}
						int firstPossibleSlot=0;
						boolean itemExists = false;
						while (amount > 0) {
							itemExists = false;
							for (int i=firstPossibleSlot; i<c.playerItems.length; i++) {
								if ((c.playerItems[i]) == itemID) {
									firstPossibleSlot = i;
									itemExists = true;
									i=30;
								}
							}
							if (itemExists) {
									c.bankItems[toBankSlot] = (c.playerItems[firstPossibleSlot]-1);
									c.bankItemsN[toBankSlot] += 1;
									deleteItem((c.playerItems[firstPossibleSlot]-1), firstPossibleSlot, 1);
									amount--;
							} else {
									amount=0;
							}
						}
						resetTempItems();
						resetBank();
						return true;
				}
				else if (alreadyInBank) {
						int firstPossibleSlot=0;
						boolean itemExists = false;
						while (amount > 0) {
							itemExists = false;
							for (int i=firstPossibleSlot; i<c.playerItems.length; i++) {
								if ((c.playerItems[i]) == itemID) {
									firstPossibleSlot = i;
									itemExists = true;
									i=30;
								}
							}
							if (itemExists) {
									c.bankItemsN[toBankSlot] += 1;
									deleteItem((c.playerItems[firstPossibleSlot]-1), firstPossibleSlot, 1);
									amount--;
							} else {
									amount=0;
							}
						}
						resetTempItems();
						resetBank();
						return true;
				} else {
						c.sendMessage("Bank full!");
						return false;
				}
			}
		} else {
			c.sendMessage("Item not supported "+(c.playerItems[fromSlot]-1));
			return false;
		}
	}
	
	
	public int freeBankSlots(){
		int freeS=0;
        for (int i=0; i < Config.BANK_SIZE; i++) {
			if (c.bankItems[i] <= 0) {
				freeS++;
			}
		}
		return freeS;
	}
	
	
	public void fromBank(int itemID, int fromSlot, int amount) {
		if (amount > 0) {
		  if (c.bankItems[fromSlot] > 0) {
			if (!c.takeAsNote) {
			  if (Item.itemStackable[c.bankItems[fromSlot]-1]) {
				if (c.bankItemsN[fromSlot] > amount) {
				  if (addItem((c.bankItems[fromSlot]-1), amount)) {
					c.bankItemsN[fromSlot] -= amount;
					resetBank();
					c.getItems().resetItems(5064);
				  }
				} else {
				  if (addItem((c.bankItems[fromSlot]-1), c.bankItemsN[fromSlot])) {
					c.bankItems[fromSlot] = 0;
					c.bankItemsN[fromSlot] = 0;
					resetBank();
					c.getItems().resetItems(5064);
				  }
				}
			  } else {
				while (amount > 0) {
				  if (c.bankItemsN[fromSlot] > 0) {
					if (addItem((c.bankItems[fromSlot]-1), 1)) {
					  c.bankItemsN[fromSlot] += -1;
					  amount--;
					} else {
					  amount = 0;
					}
				  } else {
					amount = 0;
				  }
				}
				resetBank();
				c.getItems().resetItems(5064);
			  }
			} else if (c.takeAsNote && Item.itemIsNote[c.bankItems[fromSlot]]) {
				if (c.bankItemsN[fromSlot] > amount) {
					if (addItem(c.bankItems[fromSlot], amount)) {
						c.bankItemsN[fromSlot] -= amount;
						resetBank();
						c.getItems().resetItems(5064);
					}
				} else {
					if (addItem(c.bankItems[fromSlot], c.bankItemsN[fromSlot])) {
						c.bankItems[fromSlot] = 0;
						c.bankItemsN[fromSlot] = 0;
						resetBank();
						c.getItems().resetItems(5064);
					}
				}
			} else {
			  c.sendMessage("This item can't be withdrawn as a note.");
			  if (Item.itemStackable[c.bankItems[fromSlot]-1]) {
				if (c.bankItemsN[fromSlot] > amount) {
				  if (addItem((c.bankItems[fromSlot]-1), amount)) {
					c.bankItemsN[fromSlot] -= amount;
					resetBank();
					c.getItems().resetItems(5064);
				  }
				} else {
				  if (addItem((c.bankItems[fromSlot]-1), c.bankItemsN[fromSlot])) {
					c.bankItems[fromSlot] = 0;
					c.bankItemsN[fromSlot] = 0;
					resetBank();
					c.getItems().resetItems(5064);
				  }
				}
			  } else {
				while (amount > 0) {
				  if (c.bankItemsN[fromSlot] > 0) {
					if (addItem((c.bankItems[fromSlot]-1), 1)) {
					  c.bankItemsN[fromSlot] += -1;
					  amount--;
					} else {
					  amount = 0;
					}
				  } else {
					amount = 0;
				  }
				}
				resetBank();
				c.getItems().resetItems(5064);
			  }
			}
		  }
		}
	}

  	public int itemAmount(int itemID){
		int tempAmount=0;
        for (int i=0; i < c.playerItems.length; i++) {
			if (c.playerItems[i] == itemID) {
				tempAmount+=c.playerItemsN[i];
			}
		}
		return tempAmount;
	}
	
	public boolean isStackable(int itemID) {	
		return Item.itemStackable[itemID];
	}
	
	
	/**
	*Update Equip tab
	**/

	
	public void setEquipment(int wearID, int amount, int targetSlot) {
		synchronized(c) {
			c.getOutStream().createFrameVarSizeWord(34);
			c.getOutStream().writeWord(1688);
			c.getOutStream().writeByte(targetSlot);
			c.getOutStream().writeWord(wearID+1);
			if (amount > 254) {
				c.getOutStream().writeByte(255);
				c.getOutStream().writeDWord(amount);
			} else {
				c.getOutStream().writeByte(amount);	
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();
			c.playerEquipment[targetSlot]=wearID;
			c.playerEquipmentN[targetSlot]=amount;
			c.updateRequired = true; 
			c.setAppearanceUpdateRequired(true);
		}
	}
	
	
	/**
	*Move Items
	**/
	
	public void moveItems(int from, int to, int moveWindow) {
		if (moveWindow == 3724) {
			int tempI;
			int tempN;
			tempI = c.playerItems[from];
			tempN = c.playerItemsN[from];

			c.playerItems[from] = c.playerItems[to];
			c.playerItemsN[from] = c.playerItemsN[to];
			c.playerItems[to] = tempI;
			c.playerItemsN[to] = tempN;
		}

		if (moveWindow == 34453 && from >= 0 && to >= 0 && from < Config.BANK_SIZE && to < Config.BANK_SIZE && to < Config.BANK_SIZE) {
			int tempI;
			int tempN;
			tempI = c.bankItems[from];
			tempN = c.bankItemsN[from];

			c.bankItems[from] = c.bankItems[to];
			c.bankItemsN[from] = c.bankItemsN[to];
			c.bankItems[to] = tempI;
			c.bankItemsN[to] = tempN;
		}

		if (moveWindow == 34453) {
			resetBank();
		}
		if (moveWindow == 18579) {
			int tempI;
			int tempN;
			tempI = c.playerItems[from];
			tempN = c.playerItemsN[from];

			c.playerItems[from] = c.playerItems[to];
			c.playerItemsN[from] = c.playerItemsN[to];
			c.playerItems[to] = tempI;
			c.playerItemsN[to] = tempN;
			resetItems(3214);
		}
			resetTempItems();
		if (moveWindow == 3724) {
			resetItems(3214);
		}

	}
	
	/**
	*delete Item
	**/
	
	public void deleteEquipment(int i, int j) {
		synchronized(c) {
			if(Server.playerHandler.players[c.playerId] == null) {
				return;
			}
			if(i < 0) {
				return;
			}
			
			c.playerEquipment[j] = -1;
			c.playerEquipmentN[j] = c.playerEquipmentN[j] - 1;
			c.getOutStream().createFrame(34);
			c.getOutStream().writeWord(6);
			c.getOutStream().writeWord(1688);
			c.getOutStream().writeByte(j);
			c.getOutStream().writeWord(0);
			c.getOutStream().writeByte(0);
			getBonus();
			if(j == c.playerWeapon) {
			 sendWeapon(-1, "Unarmed");
			}
			resetBonus();
			getBonus();
			writeBonus();
			c.updateRequired = true; 
			c.setAppearanceUpdateRequired(true);		
		}			
   	}
	
	public void deleteItem(int id, int amount) {
		if(id <= 0)
			return;
		for (int j = 0; j < c.playerItems.length; j++) {
			if (amount <= 0)
				break;
			if (c.playerItems[j] == id+1) {
				c.playerItems[j] = 0;
				c.playerItemsN[j] = 0;
				amount--;			
			}	
		}
		resetItems(3214);
	}
	
	public void deleteItem(int id, int slot, int amount) {
		if(id <= 0 || slot < 0) {
			return;
		}
		if (c.playerItems[slot] == (id+1)) {
			if (c.playerItemsN[slot] > amount) {
				c.playerItemsN[slot] -= amount;
			} else {
				c.playerItemsN[slot] = 0;
				c.playerItems[slot] = 0;
			}
			resetItems(3214);
		}
	}
	public void deleteItem2(int id, int amount)	{
		int am = amount;
		for (int i = 0; i < c.playerItems.length; i++) {
			if (am == 0) {
				break;
			}
			if (c.playerItems[i] == (id+1))	{
				if (c.playerItemsN[i] > amount)	{
					c.playerItemsN[i] -= amount;
					break;
				}
				else {
					c.playerItems[i] = 0;
					c.playerItemsN[i] = 0;
					am--;
				}
			}
		}
		resetItems(3214);
	}
	
	/**
	* Delete Arrows
	**/
	public void deleteArrow() {
		synchronized(c) {
			if (c.playerEquipment[c.playerCape] == 10499 && Misc.random(5) != 1 && c.playerEquipment[c.playerArrows] != 4740)
				return;
			if(c.playerEquipmentN[c.playerArrows] == 1) {
				c.getItems().deleteEquipment(c.playerEquipment[c.playerArrows], c.playerArrows);
			}
			if(c.playerEquipmentN[c.playerArrows] != 0) {
				c.getOutStream().createFrameVarSizeWord(34);
				c.getOutStream().writeWord(1688);
				c.getOutStream().writeByte(c.playerArrows);
				c.getOutStream().writeWord(c.playerEquipment[c.playerArrows]+1);
				if (c.playerEquipmentN[c.playerArrows] -1 > 254) {
					c.getOutStream().writeByte(255);
					c.getOutStream().writeDWord(c.playerEquipmentN[c.playerArrows] -1);
				} else {
					c.getOutStream().writeByte(c.playerEquipmentN[c.playerArrows] -1); 
				}
				c.getOutStream().endFrameVarSizeWord();
				c.flushOutStream();
				c.playerEquipmentN[c.playerArrows] -= 1;
			}  
			c.updateRequired = true; 
			c.setAppearanceUpdateRequired(true);
		}
	}
	
	public void deleteEquipment() {
		synchronized(c) {
			if(c.playerEquipmentN[c.playerWeapon] == 1) {
				c.getItems().deleteEquipment(c.playerEquipment[c.playerWeapon], c.playerWeapon);
			}
			if(c.playerEquipmentN[c.playerWeapon] != 0) {
				c.getOutStream().createFrameVarSizeWord(34);
				c.getOutStream().writeWord(1688);
				c.getOutStream().writeByte(c.playerWeapon);
				c.getOutStream().writeWord(c.playerEquipment[c.playerWeapon]+1);
				if (c.playerEquipmentN[c.playerWeapon] -1 > 254) {
					c.getOutStream().writeByte(255);
					c.getOutStream().writeDWord(c.playerEquipmentN[c.playerWeapon] -1);
				} else {
					c.getOutStream().writeByte(c.playerEquipmentN[c.playerWeapon] -1); 
				}
				c.getOutStream().endFrameVarSizeWord();
				c.flushOutStream();
				c.playerEquipmentN[c.playerWeapon] -= 1;
			}  
			c.updateRequired = true; 
			c.setAppearanceUpdateRequired(true);
		}
	}
	
	/**
	* Dropping Arrows
	**/
	
	
	public void dropArrowNpc() {
		if (c.playerEquipment[c.playerCape] == 10499)
			return;
		int enemyX = Server.npcHandler.npcs[c.oldNpcIndex].getX();
		int enemyY = Server.npcHandler.npcs[c.oldNpcIndex].getY();
		if(Misc.random(10) >= 4) {
			if (Server.itemHandler.itemAmount(c.rangeItemUsed, enemyX, enemyY) == 0) {
				Server.itemHandler.createGroundItem(c, c.rangeItemUsed, enemyX, enemyY, 1, c.getId());
			} else if (Server.itemHandler.itemAmount(c.rangeItemUsed, enemyX, enemyY) != 0) {
				int amount = Server.itemHandler.itemAmount(c.rangeItemUsed, enemyX, enemyY);
				Server.itemHandler.removeGroundItem(c, c.rangeItemUsed, enemyX, enemyY, false);
				Server.itemHandler.createGroundItem(c, c.rangeItemUsed, enemyX, enemyY, amount+1, c.getId());
			}		
		}
	}	
	
	public void dropArrowPlayer() {
		int enemyX = Server.playerHandler.players[c.oldPlayerIndex].getX();
		int enemyY = Server.playerHandler.players[c.oldPlayerIndex].getY();
		if (c.playerEquipment[c.playerCape] == 10499)
			return;
		if(Misc.random(10) >= 4) {
			if (Server.itemHandler.itemAmount(c.rangeItemUsed, enemyX, enemyY) == 0) {
				Server.itemHandler.createGroundItem(c, c.rangeItemUsed, enemyX, enemyY, 1, c.getId());
			} else if (Server.itemHandler.itemAmount(c.rangeItemUsed, enemyX, enemyY) != 0) {
				int amount = Server.itemHandler.itemAmount(c.rangeItemUsed, enemyX, enemyY);
				Server.itemHandler.removeGroundItem(c, c.rangeItemUsed, enemyX, enemyY, false);
				Server.itemHandler.createGroundItem(c, c.rangeItemUsed, enemyX, enemyY, amount+1, c.getId());
			}		
		}
	}
	
	
	public void removeAllItems() {
		for (int i = 0; i < c.playerItems.length; i++) {
			c.playerItems[i] = 0;
		}
		for (int i = 0; i < c.playerItemsN.length; i++) {
			c.playerItemsN[i] = 0;
		}
		resetItems(3214);
	}
	
	public int freeSlots(){
		int freeS = 0;
        for (int i=0; i < c.playerItems.length; i++){
			if (c.playerItems[i] <= 0){
				freeS++;
			}
		}
		return freeS;
	}
	
	public int findItem(int id, int[] items, int[] amounts) {
		for (int i = 0; i < c.playerItems.length; i++) {
			if (((items[i] - 1) == id) && (amounts[i] > 0)) {
				return i;
			}
		}
		return -1;
	}
	
	public String getItemName(int ItemID) {
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (Server.itemHandler.ItemList[i] != null) {
				if (Server.itemHandler.ItemList[i].itemId == ItemID) {
					return Server.itemHandler.ItemList[i].itemName;
				}
			}
		}
		return "Unarmed";
	}
	
	public int getItemId(String itemName) {
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (Server.itemHandler.ItemList[i] != null) {
				if (Server.itemHandler.ItemList[i].itemName.equalsIgnoreCase(itemName)) {
					return Server.itemHandler.ItemList[i].itemId;
				}
			}
		}
		return -1;
	}
	
	public int getItemSlot(int ItemID) {
		for (int i = 0; i < c.playerItems.length; i++) {
			if((c.playerItems[i] - 1) == ItemID){
				return i;
			}
		}	
		return -1;
	}
	
	public int getItemAmount(int ItemID) {
		int itemCount = 0;
		for (int i = 0; i < c.playerItems.length; i++) {
			if((c.playerItems[i] - 1) == ItemID) {
				itemCount += c.playerItemsN[i];
			}
		}
		return itemCount;
	}
	
	
	public boolean playerHasItem(int itemID, int amt, int slot) {
	    itemID++;
	    int found = 0;
		if (c.playerItems[slot] == (itemID)) {
			for (int i = 0; i < c.playerItems.length; i++)  {
				if (c.playerItems[i] == itemID)  {
					if(c.playerItemsN[i] >= amt) {
						return true;
					} else {
						found++;
					}
            	}
        	}
			if(found >= amt) {
				return true;
			}
        	return false;
		}
		return false;
	}
	
	public boolean playerHasItem(int itemID) {
	    itemID++;
			for (int i = 0; i < c.playerItems.length; i++)  {
				if (c.playerItems[i] == itemID)
					return true;
        	}
		return false;
	}	
	
	
	public boolean playerHasItem(int itemID, int amt) {
	    itemID++;
	    int found = 0;
		for (int i = 0; i < c.playerItems.length; i++) {
            if (c.playerItems[i] == itemID) {
		    	if(c.playerItemsN[i] >= amt){
					return true;
				} else{
			    	found++;
				}
            }
        }
			if(found >= amt) {
				return true;
			}
        	return false;
	}
	
	public int getUnnotedItem(int ItemID) {
		int NewID = ItemID - 1;
		String NotedName = "";
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (Server.itemHandler.ItemList[i] != null) {
				if (Server.itemHandler.ItemList[i].itemId == ItemID) {
					NotedName = Server.itemHandler.ItemList[i].itemName;
				}
			}
		}
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (Server.itemHandler.ItemList[i] != null) {
				if (Server.itemHandler.ItemList[i].itemName == NotedName) {
					if (Server.itemHandler.ItemList[i].itemDescription.startsWith("Swap this note at any bank for a") == false) {
						NewID = Server.itemHandler.ItemList[i].itemId;
						break;
					}
				}
			}
		}
		return NewID;
	}
	
	
	/**
	*Drop Item
	**/
	
	public void createGroundItem(int itemID, int itemX, int itemY, int itemAmount) {
		synchronized(c) {
			c.getOutStream().createFrame(85);
			c.getOutStream().writeByteC((itemY - 8 * c.mapRegionY));
			c.getOutStream().writeByteC((itemX - 8 * c.mapRegionX));
			c.getOutStream().createFrame(44);
			c.getOutStream().writeWordBigEndianA(itemID);
			c.getOutStream().writeWord(itemAmount);
			c.getOutStream().writeByte(0);	
			c.flushOutStream();
		}
	}
	
	/**
	*Pickup Item
	**/
	
	public void removeGroundItem(int itemID, int itemX, int itemY, int Amount) {
		synchronized(c) {
			c.getOutStream().createFrame(85);
			c.getOutStream().writeByteC((itemY - 8 * c.mapRegionY));
			c.getOutStream().writeByteC((itemX - 8 * c.mapRegionX));
			c.getOutStream().createFrame(156);
			c.getOutStream().writeByteS(0);
			c.getOutStream().writeWord(itemID);
			c.flushOutStream();
		}
	}
	
	public boolean ownsCape() {
		if (c.getItems().playerHasItem(2412,1) || c.getItems().playerHasItem(2413,1) || c.getItems().playerHasItem(2414,1))
			return true;
		for (int j = 0; j < Config.BANK_SIZE; j++) {
			if (c.bankItems[j] == 2412 || c.bankItems[j] == 2413 || c.bankItems[j] == 2414)
				return true;		
		}
		if (c.playerEquipment[c.playerCape] == 2413 || c.playerEquipment[c.playerCape] == 2414 || c.playerEquipment[c.playerCape] == 2415)
			return true;
		return false;	
	}
	public boolean ownsBook() {
		if (c.getItems().playerHasItem(3842,1))
			return true;
		for (int j = 0; j < Config.BANK_SIZE; j++) {
			if (c.bankItems[j] == 3842)
				return true;		
		}
		if (c.playerEquipment[c.playerCape] == 3842)
			return true;
		return false;	
	}
	
	public boolean hasAllShards() { 
		return playerHasItem(11712,1) && playerHasItem(11712,1) && playerHasItem(11714,1);
	}
	
	public void makeBlade() {
		deleteItem(11710,1);
		deleteItem(11712,1);
		deleteItem(11714,1);
		addItem(11690,1);
		c.sendMessage("You combine the shards to make a blade.");
	}
	
	public void makeGodsword(int i) {
		int godsword = i - 8;
		if (playerHasItem(11690) && playerHasItem(i)) {
			deleteItem(11690,1);
			deleteItem(i,1);
			addItem(i - 8, 1);
			c.sendMessage("You combine the hilt and the blade to make a godsword.");
		}	
	}
	
	public boolean isHilt(int i) {
		return i >= 11702 && i <= 11708 && i%2 == 0;
	}
	

}