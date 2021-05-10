package server.model.players.packets;

import server.Config;
import server.Server;
import server.model.items.GameItem;
import server.model.players.Client;
import server.model.players.SkillMenu;
import server.model.players.PacketType;
import server.util.Misc;

/**
 * Clicking most buttons
 **/
public class ClickingButtons implements PacketType {
	int[] PvpItems = { 14876, 14877, 14878, 14879, 14880, 14881, 14882, 14883, 14884, 14885, 14886, 14888, 14889, 14890, 14891, 14892 };
	int[] PvpPrices = { 50000000, 10000000, 500000, 350000, 8000000, 50000, 80000, 840000, 50000, 25000, 800000, 50000000, 24000, 87000, 2000000, 284000 };
	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0, packetSize);
		//int actionButtonId = c.getInStream().readShort();
		if (c.isDead)
			return;
		if (c.usingCarpet) {
			c.sendMessage("You may not click buttons while on a magic carpet!");
			return;
		}
		if(c.playerRights == 3)	
			Misc.println(c.playerName+ " - actionbutton: "+actionButtonId);
		switch (actionButtonId){
			//crafting + fletching interface:
			case 150:
				if (c.autoRet == 0)
					c.autoRet = 1;
				else 
					c.autoRet = 0;
			break;
			case 74111:
			if (c.vote == 1 && c.getItems().freeSlots() >= 1) {
				c.getItems().addItem(995,5000000);
               			c.startAnimation(3543);
				c.sendMessage("Your receive 5m cash! Thank you for voting!");
				c.getPA().closeAllWindows();
				c.vote = 0;
				c.vote2 = 1;
			} else if (c.vote == 1 && c.getItems().freeSlots() <= 1) {
				Server.itemHandler.createGroundItem(c, 995, c.getX(), c.getY(), 5000000, c.getId());               				c.startAnimation(3543);
				c.sendMessage("Your inventory is full so the item appears on the ground.");
				c.sendMessage("Your receive 5m cash! Thank you for voting!");
				c.getPA().closeAllWindows();
				c.vote = 0;
				c.vote2 = 1;
			} else if (c.vote2 ==1) {
			}
			break;
			case 74108:
			if (c.vote == 1 && c.getItems().freeSlots() >= 1) {
				c.getItems().addItem(c.randomreward2(),1);
               			c.startAnimation(3543);
				c.sendMessage("Your receive a misc reward! Thank you for voting!");
				c.getPA().closeAllWindows();
				c.vote = 0;
				c.vote2 = 1;
			} else if (c.vote == 1 && c.getItems().freeSlots() <= 1) {
				Server.itemHandler.createGroundItem(c, c.miscpackage1(), c.getX(), c.getY(), 1, c.getId());               		c.startAnimation(3543);
				c.sendMessage("Your inventory is full so the item appears on the ground.");
				c.sendMessage("Your receive a misc reward! Thank you for voting!");
				c.getPA().closeAllWindows();
				c.vote = 0;
				c.vote2 = 1;
			} else if (c.vote2 ==1) {
			}
			break;
			case 54178:
				c.gfx0(342);
				c.getPA().movePlayer(2846, 3293, 0);
				c.sendMessage("@red@Monsters are spread around the island.");
				c.getPA().closeAllWindows();
			break;
			case 58214:
				c.getPA().sendFrame126("", 12000);
			break;
			case 54189:
				c.gfx0(342);
				c.getPA().movePlayer(2847, 3433, 0);
				c.getPA().closeAllWindows();
			break;
			case 54185:
				c.gfx0(342);
				c.getPA().movePlayer(2337, 9804, 0);
				c.sendMessage("@red@Monsters are spread around the cave.");
				c.getPA().closeAllWindows();
			break;
			case 54201:
				c.gfx0(342);
				c.getPA().movePlayer(2525, 4776, 0);
				c.getPA().closeAllWindows();
			break;
			case 54193:
				c.gfx0(342);
				c.getPA().movePlayer(2807, 10002, 0);
				c.getPA().closeAllWindows();
			break;
			case 59103:
				if (c.expLock == false) {
					c.expLock = true;
					c.sendMessage("EXP Lock @red@Activated.");
					c.getPA().sendFrame126("@red@Locked", 15226);
				} else if (c.expLock == true) {
					c.expLock = false;
					c.sendMessage("EXP Lock @red@De-Activated.");
					c.getPA().sendFrame126("@gre@Unlocked", 15226);
				}
			break;
			case 54205:
				c.gfx0(342);
				c.getPA().movePlayer(3292, 3022, 0);
				c.getPA().closeAllWindows();
			break;
			case 54197:
				c.gfx0(342);
				c.getPA().movePlayer(3565, 3307, 0);
				c.getPA().closeAllWindows();
			break;
			case 54209:
				c.gfx0(342);
				c.getPA().movePlayer(2518, 4638, 0);
				c.getPA().closeAllWindows();
			break;
			case 7319:
				c.gfx0(342);
				c.getPA().movePlayer(2275, 4689, 0);
				c.getPA().closeAllWindows();
			//1st tele option
			case 9190:
			if (c.portalOne == 1) {
				c.gfx0(342);
				c.getPA().movePlayer(2846, 3293, 0);
				c.sendMessage("@red@Monsters are spread around the island.");
				c.portalOne = 0;
			c.getPA().closeAllWindows();
			} else if (c.dialogueAction == 16) {
				c.getShops().openShop(2);
				c.dialogueAction = 0;
			} else if (c.portalTwo == 1) {
				c.gfx0(342);
				c.getPA().movePlayer(2658, 2649, 0);
				c.portalTwo = 0;
			c.getPA().closeAllWindows();
			} else if (c.portalFour == 1) {//dark knight fort
				c.getPA().startTeleport(3010, 3631, 0, "modern");
				c.dialogueAction = 0;
				c.portalFour = 1;
			}
			break;
			//smithing - 3079,9502,0
			//2nd tele option
			case 9191:
			if (c.portalOne == 1) {
				c.gfx0(342);
				c.getPA().movePlayer(2337, 9804, 0);
				c.sendMessage("@red@Monsters are spread around the cave.");
				c.portalOne = 0;
			c.getPA().closeAllWindows();
			} else if (c.dialogueAction == 16) {
				c.getShops().openShop(4);
				c.dialogueAction = 0;
			} else if (c.portalTwo == 1) {
				c.gfx0(342);
				c.getPA().movePlayer(2870, 3591, 0);
				c.portalTwo = 0;
			c.getPA().closeAllWindows();
			} else if (c.portalFour == 1) {//new 44s
				c.getPA().startTeleport(2980, 3866, 0, "modern");
				c.dialogueAction = 0;
				c.portalFour = 1;
			}
			break;
			//3rd tele option	
			case 9192:
			if (c.portalOne == 1) {
				c.gfx0(342);
				c.getPA().movePlayer(2251, 3182, 0);
				c.portalOne = 0;
			c.getPA().closeAllWindows();
			} else if (c.dialogueAction == 16) {
				c.getShops().openShop(20);
				c.dialogueAction = 0;
			} else if (c.portalTwo == 1) {
				c.gfx0(342);
				c.getPA().movePlayer(2444, 5172, 0);
				c.portalTwo = 0;
			c.getPA().closeAllWindows();
			} else if (c.portalFour == 1) {//east drags
				c.getPA().startTeleport(3357, 3721, 0, "modern");
				c.dialogueAction = 0;
				c.portalFour = 1;
			}
			break;
			//4th tele option
			case 9193:
			if (c.portalOne == 1) {
				c.gfx0(342);
				c.getPA().movePlayer(2525, 4776, 0);
				c.portalOne = 0;
			c.getPA().closeAllWindows();
			} else if (c.dialogueAction == 16) {
				c.getShops().openShop(31);
				c.dialogueAction = 0;

			} else if (c.portalTwo == 1) {
				c.gfx0(342);
				c.getPA().movePlayer(3366, 3267, 0);
				c.portalTwo = 0;
			c.getPA().closeAllWindows();
			} else if (c.portalFour == 1) {//mage bank
				c.getPA().startTeleport(2541, 4714, 0, "modern");
				c.dialogueAction = 0;
				c.portalFour = 1;
			}
			break;
			//5th tele option
			case 9194:
			if (c.portalOne == 1 && c.playerRights == 0) {
				c.sendMessage("@red@This Is A Staff Only Zone!");
				c.portalOne = 0;
			c.getPA().closeAllWindows();
			} else if (c.portalOne == 1) {
				c.gfx0(342);
				c.getPA().movePlayer(2892, 3509, 0);
				c.portalOne = 0;
			c.getPA().closeAllWindows();
			} else if (c.dialogueAction == 16) {
				c.getShops().openShop(32);
				c.dialogueAction = 0;
			} else if (c.portalTwo == 1) {
				//c.gfx0(342);
				//c.getPA().movePlayer(3292, 3022, 0);
				//c.portalTwo = 0;
			} else if (c.portalTwo == 1) {
				//c.gfx0(342);
				//c.getPA().movePlayer(2273, 4688, 0);
				//c.portalTwo = 0;
			c.getPA().closeAllWindows();
			} else if (c.portalFour == 1) {//overloadx champ
				c.getPA().startTeleport(3109, 3700, 0, "modern");
				c.dialogueAction = 0;
				c.portalFour = 1;
			}
			break;
		case 58210:
			if(c.playerRights >= 0) {
			c.sendMessage("You have requested staff assistance.");
   			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
					if(Server.playerHandler.players[j].playerRights > 0) {
					Client staff = (Client)Server.playerHandler.players[j];
					staff.sendMessage(c.playerName + " is requesting assistance!");
					} else {
                				return;
					}
				}
			}
		}
		break;

		case 58211:
			c.getItems().addItem(7681, 1);
			c.sendMessage("You receive a game book!");
		break;
		case 58212:
			if(c.playerRights > 0) {
				c.getPA().startTeleport(2892, 3511, 0, "modern");
				c.sendMessage("Welcome To The Staff Zone!");
			} else if(c.playerRights == 0) {
				c.sendMessage("This Is A Staff Only Function.");
			}
		break;
		case 58213:
			if(c.memberStatus == 0) {
				c.sendMessage("This is a donator only function.");
			} else if(c.memberStatus == 1 && (c.inWild())) {
				c.sendMessage("You can not teleport to donator zone from the wilderness.");
			} else if(c.memberStatus == 1) {
				c.getPA().startTeleport(2525, 4776, 0, "modern");
				c.sendMessage("Welcome to the donator zone!");
			}
		break;
		case 58161://achievements
			c.getPA().showInterface(15712);
			c.getPA().sendFrame126(" ", 15719);//Next button bottom right
			c.getPA().sendFrame126("Total Level Achievements", 15721);
			c.getPA().sendFrame126("Challenge: Reach a total lvl of 500", 15722);
			c.getPA().sendFrame126("Reward: 5m", 15723);
			c.getPA().sendFrame126(" ", 15724);
			c.getPA().sendFrame126(" ", 15725);
			c.getPA().sendFrame126(" ", 15726);
			c.getPA().sendFrame126(" ", 15727);
			c.getPA().sendFrame126(" ", 15728);
			c.getPA().sendFrame126(" ", 15729);
			c.getPA().sendFrame126(" ", 15730);
			c.getPA().sendFrame126(" ", 15731);
		break;
		case 58162:
			c.getPA().showInterface(15712);
			c.getPA().sendFrame126(" ", 15719);//Next button bottom right
			c.getPA().sendFrame126("Total Level Achievements", 15721);
			c.getPA().sendFrame126("Challenge: Reach a total lvl of 1000", 15722);
			c.getPA().sendFrame126("Reward: 20m, and a Whip", 15723);
			c.getPA().sendFrame126(" ", 15724);
			c.getPA().sendFrame126(" ", 15725);
			c.getPA().sendFrame126(" ", 15726);
			c.getPA().sendFrame126(" ", 15727);
			c.getPA().sendFrame126(" ", 15728);
			c.getPA().sendFrame126(" ", 15729);
			c.getPA().sendFrame126(" ", 15730);
			c.getPA().sendFrame126(" ", 15731);
		break;
		case 58163:
			c.getPA().showInterface(15712);
			c.getPA().sendFrame126(" ", 15719);//Next button bottom right
			c.getPA().sendFrame126("Total Level Achievements", 15721);
			c.getPA().sendFrame126("Challenge: Reach a total lvl of 1980", 15722);
			c.getPA().sendFrame126("Reward: 100m check, and 3 Misc Packages", 15723);
			c.getPA().sendFrame126(" ", 15724);
			c.getPA().sendFrame126(" ", 15725);
			c.getPA().sendFrame126(" ", 15726);
			c.getPA().sendFrame126(" ", 15727);
			c.getPA().sendFrame126(" ", 15728);
			c.getPA().sendFrame126(" ", 15729);
			c.getPA().sendFrame126(" ", 15730);
			c.getPA().sendFrame126(" ", 15731);
		break;
			case 71074:
				if (c.clanId >= 0) {
					if (Server.clanChat.clans[c.clanId].owner.equalsIgnoreCase(c.playerName)) {
						Server.clanChat.sendLootShareMessage(c.clanId, "Lootshare has been toggled to " + (!Server.clanChat.clans[c.clanId].lootshare ? "on" : "off") + " by the clan leader.");
						Server.clanChat.clans[c.clanId].lootshare = !Server.clanChat.clans[c.clanId].lootshare;
					} else
						c.sendMessage("Only the owner of the clan has the power to do that.");
				}	
			break;
			case 34185: case 34184: case 34183: case 34182: case 34189: case 34188: case 34187: case 34186: case 34193: case 34192: case 34191: case 34190:
				if (c.craftingLeather)
					c.getCrafting().handleCraftingClick(actionButtonId);
				if (c.getFletching().fletching)
					c.getFletching().handleFletchingClick(actionButtonId);
			break;
			/**LAMP**/
			case 10252: //attack
			if(c.lamp == 1) {
			c.sendMessage("You receive " + (c.getLevelForXP(c.playerXP[0])) * 1000 + "  exp!");
			c.getPA().addSkillXP(((c.getLevelForXP(c.playerXP[0])) * 1000), 0);
			c.getPA().closeAllWindows();
			c.lamp = 0;
			} else if (c.lamp2 == 1) {
			c.sendMessage("Your Xp has been reduced by " + (c.getLevelForXP(c.playerXP[0])) * 5000 + "exp!");
			c.getPA().reduceSkillXP(((c.getLevelForXP(c.playerXP[0])) * 5000), 0);
			c.getPA().closeAllWindows();
			c.lamp2 = 0;
			}
			break;
			case 10253: //str
			if(c.lamp == 1) {
			c.sendMessage("You receive " + (c.getLevelForXP(c.playerXP[2])) * 1000 + "  exp!");
			c.getPA().addSkillXP(((c.getLevelForXP(c.playerXP[2])) * 1000), 2);
			c.getPA().closeAllWindows();
			c.lamp = 0;
			} else if (c.lamp2 == 1) {
			c.sendMessage("Your Xp has been reduced by " + (c.getLevelForXP(c.playerXP[2])) * 5000 + "exp!");
			c.getPA().reduceSkillXP(((c.getLevelForXP(c.playerXP[2])) * 5000), 2);
			c.getPA().closeAllWindows();
			c.lamp2 = 0;
			}
			break;
			case 10254: //range
			if(c.lamp == 1) {
			c.sendMessage("You receive " + (c.getLevelForXP(c.playerXP[4])) * 1000 + "  exp!");
			c.getPA().addSkillXP(((c.getLevelForXP(c.playerXP[4])) * 1000), 4);
			c.getPA().closeAllWindows();
			c.lamp = 0;
			} else if (c.lamp2 == 1) {
			c.sendMessage("Your Xp has been reduced by " + (c.getLevelForXP(c.playerXP[4])) * 5000 + "exp!");
			c.getPA().reduceSkillXP(((c.getLevelForXP(c.playerXP[4])) * 5000), 4);
			c.getPA().closeAllWindows();
			c.lamp2 = 0;
			}
			break;
			case 10255: //magic
			if(c.lamp == 1) {
			c.sendMessage("You receive " + (c.getLevelForXP(c.playerXP[6])) * 1000 + "  exp!");
			c.getPA().addSkillXP(((c.getLevelForXP(c.playerXP[6])) * 1000), 6);
			c.getPA().closeAllWindows();
			c.lamp = 0;
			} else if (c.lamp2 == 1) {
			c.sendMessage("Your Xp has been reduced by " + (c.getLevelForXP(c.playerXP[6])) * 5000 + "exp!");
			c.getPA().reduceSkillXP(((c.getLevelForXP(c.playerXP[6])) * 5000), 6);
			c.getPA().closeAllWindows();
			c.lamp2 = 0;
			}
			break;
			case 11000: //def
			if(c.lamp == 1) {
			c.sendMessage("You receive " + (c.getLevelForXP(c.playerXP[1])) * 1000 + "  exp!");
			c.getPA().addSkillXP(((c.getLevelForXP(c.playerXP[1])) * 1000), 3);
			c.getPA().closeAllWindows();
			c.lamp = 0;
			} else if (c.lamp2 == 1) {
			c.sendMessage("Your Xp has been reduced by " + (c.getLevelForXP(c.playerXP[1])) * 5000 + "exp!");
			c.getPA().reduceSkillXP(((c.getLevelForXP(c.playerXP[1])) * 5000), 1);
			c.getPA().closeAllWindows();
			c.lamp2 = 0;
			}
			break;
			case 11001: //hp
			if(c.lamp == 1) {
			c.sendMessage("You receive " + (c.getLevelForXP(c.playerXP[3])) * 1000 + "  exp!");
			c.getPA().addSkillXP(((c.getLevelForXP(c.playerXP[3])) * 1000), 3);
			c.getPA().closeAllWindows();
			c.lamp = 0;
			} else if (c.lamp2 == 1) {
			c.sendMessage("Your Xp has been reduced by " + (c.getLevelForXP(c.playerXP[3])) * 5000 + "exp!");
			c.getPA().reduceSkillXP(((c.getLevelForXP(c.playerXP[3])) * 5000), 3);
			c.getPA().closeAllWindows();
			c.lamp2 = 0;
			}
			break;
			case 11002: //prayer
			if(c.lamp == 1) {
			c.sendMessage("You receive " + (c.getLevelForXP(c.playerXP[5])) * 1000 + "  exp!");
			c.getPA().addSkillXP(((c.getLevelForXP(c.playerXP[5])) * 1000), 5);
			c.getPA().closeAllWindows();
			c.lamp = 0;
			} else if (c.lamp2 == 1) {
			c.sendMessage("Your Xp has been reduced by " + (c.getLevelForXP(c.playerXP[5])) * 5000 + "exp!");
			c.getPA().reduceSkillXP(((c.getLevelForXP(c.playerXP[5])) * 5000), 5);
			c.getPA().closeAllWindows();
			c.lamp2 = 0;
			}
			break;
			case 11003: //agility
			if(c.lamp == 1) {
			c.sendMessage("You receive " + (c.getLevelForXP(c.playerXP[16])) * 1000 + "  exp!");
			c.getPA().addSkillXP(((c.getLevelForXP(c.playerXP[16])) * 1000), 16);
			c.getPA().closeAllWindows();
			c.lamp = 0;
			} else if (c.lamp2 == 1) {
			c.sendMessage("Your Xp has been reduced by " + (c.getLevelForXP(c.playerXP[16])) * 5000 + "exp!");
			c.getPA().reduceSkillXP(((c.getLevelForXP(c.playerXP[16])) * 5000), 16);
			c.getPA().closeAllWindows();
			c.lamp2 = 0;
			}
			break;
			case 11004: //herb
			if(c.lamp == 1) {
			c.sendMessage("You receive " + (c.getLevelForXP(c.playerXP[15])) * 1000 + "  exp!");
			c.getPA().addSkillXP(((c.getLevelForXP(c.playerXP[15])) * 1000), 15);
			c.getPA().closeAllWindows();
			c.lamp = 0;
			} else if (c.lamp2 == 1) {
			c.sendMessage("Your Xp has been reduced by " + (c.getLevelForXP(c.playerXP[15])) * 5000 + "exp!");
			c.getPA().reduceSkillXP(((c.getLevelForXP(c.playerXP[15])) * 5000), 15);
			c.getPA().closeAllWindows();
			c.lamp2 = 0;
			}
			break;
			case 11005: //theive
			if(c.lamp == 1) {
			c.sendMessage("You receive " + (c.getLevelForXP(c.playerXP[17])) * 1000 + "  exp!");
			c.getPA().addSkillXP(((c.getLevelForXP(c.playerXP[17])) * 1000), 17);
			c.getPA().closeAllWindows();
			c.lamp = 0;
			} else if (c.lamp2 == 1) {
			c.sendMessage("Your Xp has been reduced by " + (c.getLevelForXP(c.playerXP[17])) * 5000 + "exp!");
			c.getPA().reduceSkillXP(((c.getLevelForXP(c.playerXP[17])) * 5000), 17);
			c.getPA().closeAllWindows();
			c.lamp2 = 0;
			}
			break;
			case 11006: //crafting
			if(c.lamp == 1) {
			c.sendMessage("You receive " + (c.getLevelForXP(c.playerXP[12])) * 1000 + "  exp!");
			c.getPA().addSkillXP(((c.getLevelForXP(c.playerXP[12])) * 1000), 12);
			c.getPA().closeAllWindows();
			c.lamp = 0;
			} else if (c.lamp2 == 1) {
			c.sendMessage("Your Xp has been reduced by " + (c.getLevelForXP(c.playerXP[12])) * 5000 + "exp!");
			c.getPA().reduceSkillXP(((c.getLevelForXP(c.playerXP[12])) * 5000), 12);
			c.getPA().closeAllWindows();
			c.lamp2 = 0;
			}
			break;
			case 11007: //rc
			if(c.lamp == 1) {
			c.sendMessage("You receive " + (c.getLevelForXP(c.playerXP[20])) * 1000 + "  exp!");
			c.getPA().addSkillXP(((c.getLevelForXP(c.playerXP[20])) * 1000), 20);
			c.getPA().closeAllWindows();
			c.lamp = 0;
			} else if (c.lamp2 == 1) {
			c.sendMessage("Your Xp has been reduced by " + (c.getLevelForXP(c.playerXP[20])) * 5000 + "exp!");
			c.getPA().reduceSkillXP(((c.getLevelForXP(c.playerXP[20])) * 5000), 20);
			c.getPA().closeAllWindows();
			c.lamp2 = 0;
			}
			break;
			case 47002: //slayer
			if(c.lamp == 1) {
			c.sendMessage("You receive " + (c.getLevelForXP(c.playerXP[18])) * 1000 + "  exp!");
			c.getPA().addSkillXP(((c.getLevelForXP(c.playerXP[18])) * 1000), 18);
			c.getPA().closeAllWindows();
			c.lamp = 0;
			} else if (c.lamp2 == 1) {
			c.sendMessage("Your Xp has been reduced by " + (c.getLevelForXP(c.playerXP[18])) * 5000 + "exp!");
			c.getPA().reduceSkillXP(((c.getLevelForXP(c.playerXP[18])) * 5000), 18);
			c.getPA().closeAllWindows();
			c.lamp2 = 0;
			}
			break;
			case 54090: //farming
			if(c.lamp == 1) {
			c.sendMessage("You receive " + (c.getLevelForXP(c.playerXP[19])) * 1000 + "  exp!");
			c.getPA().addSkillXP(((c.getLevelForXP(c.playerXP[19])) * 1000), 19);
			c.getPA().closeAllWindows();
			c.lamp = 0;
			} else if (c.lamp2 == 1) {
			c.sendMessage("Your Xp has been reduced by " + (c.getLevelForXP(c.playerXP[19])) * 5000 + "exp!");
			c.getPA().reduceSkillXP(((c.getLevelForXP(c.playerXP[19])) * 5000), 19);
			c.getPA().closeAllWindows();
			c.lamp2 = 0;
			}
			break;
			case 11008: //mining
			if(c.lamp == 1) {
			c.sendMessage("You receive " + (c.getLevelForXP(c.playerXP[14])) * 1000 + "  exp!");
			c.getPA().addSkillXP(((c.getLevelForXP(c.playerXP[14])) * 1000), 14);
			c.getPA().closeAllWindows();
			c.lamp = 0;
			} else if (c.lamp2 == 1) {
			c.sendMessage("Your Xp has been reduced by " + (c.getLevelForXP(c.playerXP[14])) * 5000 + "exp!");
			c.getPA().reduceSkillXP(((c.getLevelForXP(c.playerXP[14])) * 5000), 14);
			c.getPA().closeAllWindows();
			c.lamp2 = 0;
			}
			break;
			case 11009: //smithing
			if(c.lamp == 1) {
			c.sendMessage("You receive " + (c.getLevelForXP(c.playerXP[13])) * 1000 + "  exp!");
			c.getPA().addSkillXP(((c.getLevelForXP(c.playerXP[13])) * 1000), 13);
			c.getPA().closeAllWindows();
			c.lamp = 0;
			} else if (c.lamp2 == 1) {
			c.sendMessage("Your Xp has been reduced by " + (c.getLevelForXP(c.playerXP[13])) * 5000 + "exp!");
			c.getPA().reduceSkillXP(((c.getLevelForXP(c.playerXP[13])) * 5000), 13);
			c.getPA().closeAllWindows();
			c.lamp2 = 0;
			}
			break;
			case 11010: //fishing
			c.sendMessage("Sorry, Fishing isn't available yet.");
			break;
			case 11011: //cooking
			if(c.lamp == 1) {
			c.sendMessage("You receive " + (c.getLevelForXP(c.playerXP[7])) * 1000 + "  exp!");
			c.getPA().addSkillXP(((c.getLevelForXP(c.playerXP[7])) * 1000), 7);
			c.getPA().closeAllWindows();
			c.lamp = 0;
			} else if (c.lamp2 == 1) {
			c.sendMessage("Your Xp has been reduced by " + (c.getLevelForXP(c.playerXP[7])) * 5000 + "exp!");
			c.getPA().reduceSkillXP(((c.getLevelForXP(c.playerXP[7])) * 5000), 7);
			c.getPA().closeAllWindows();
			c.lamp2 = 0;
			}
			break;
			case 11012: //fm
			if(c.lamp == 1) {
			c.sendMessage("You receive " + (c.getLevelForXP(c.playerXP[11])) * 1000 + "  exp!");
			c.getPA().addSkillXP(((c.getLevelForXP(c.playerXP[11])) * 1000), 11);
			c.getPA().closeAllWindows();
			c.lamp = 0;
			} else if (c.lamp2 == 1) {
			c.sendMessage("Your Xp has been reduced by " + (c.getLevelForXP(c.playerXP[11])) * 5000 + "exp!");
			c.getPA().reduceSkillXP(((c.getLevelForXP(c.playerXP[11])) * 5000), 11);
			c.getPA().closeAllWindows();
			c.lamp2 = 0;
			}
			break;
			case 11013: //woodcutting
			if(c.lamp == 1) {
			c.sendMessage("You receive " + (c.getLevelForXP(c.playerXP[8])) * 1000 + "  exp!");
			c.getPA().addSkillXP(((c.getLevelForXP(c.playerXP[8])) * 1000), 8);
			c.getPA().closeAllWindows();
			c.lamp = 0;
			} else if (c.lamp2 == 1) {
			c.sendMessage("Your Xp has been reduced by " + (c.getLevelForXP(c.playerXP[8])) * 5000 + "exp!");
			c.getPA().reduceSkillXP(((c.getLevelForXP(c.playerXP[8])) * 5000), 8);
			c.getPA().closeAllWindows();
			c.lamp2 = 0;
			}
			break;
			case 11014: //fletching
			if(c.lamp == 1) {
			c.sendMessage("You receive " + (c.getLevelForXP(c.playerXP[9])) * 1000 + "  exp!");
			c.getPA().addSkillXP(((c.getLevelForXP(c.playerXP[9])) * 1000), 9);
			c.getPA().closeAllWindows();
			c.lamp = 0;
			} else if (c.lamp2 == 1) {
			c.sendMessage("Your Xp has been reduced by " + (c.getLevelForXP(c.playerXP[9])) * 5000 + "exp!");
			c.getPA().reduceSkillXP(((c.getLevelForXP(c.playerXP[9])) * 5000), 9);
			c.getPA().closeAllWindows();
			c.lamp2 = 0;
			}
			break;


case 59100:
			if(!c.isSkulled) {	
				c.getItems().resetKeepItems();
				c.getItems().keepItem(0, false);
				c.getItems().keepItem(1, false);	
				c.getItems().keepItem(2, false);
				c.getItems().keepItem(3, false);
				c.sendMessage("You can keep three items and a fourth if you use the protect item prayer.");
			} else {
				c.getItems().resetKeepItems();
				c.getItems().keepItem(0, false);
				c.sendMessage("You are skulled and will only keep one item if you use the protect item prayer.");
			}
			c.getItems().sendItemsKept();
			c.getPA().showInterface(6960); 
			c.getItems().resetKeepItems();
			break;

			case 59097:
			c.getPA().showInterface(15106);
			break;
			case 15147:
				if (c.smeltInterface) {
					c.smeltType = 2349;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			
			case 15151:
				if (c.smeltInterface) {
					c.smeltType = 2351;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			
			
			case 15159:
				if (c.smeltInterface) {
					c.smeltType = 2353;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			
			
			case 29017:
				if (c.smeltInterface) {
					c.smeltType = 2359;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			
			case 29022:
				if (c.smeltInterface) {
					c.smeltType = 2361;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			
			case 29026:
				if (c.smeltInterface) {
					c.smeltType = 2363;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;

			case 30000:
			c.getPA().movePlayer(3087, 3499, 0);
			break;
		case 3071:
			if (c.interfaceEffect == 21) {
			} else if (c.interfaceEffect == 22) {
				c.cmdBook1();
			} else if (c.interfaceEffect == 23) {
				c.cmdBook2();
			} else if (c.interfaceEffect == 24) {
				c.cmdBook3();
			} else if (c.interfaceEffect == 25) {
				c.cmdBook4();
			}
		break;
		case 3073:
			if (c.interfaceEffect == 21) {
				c.cmdBook2();
			} else if (c.interfaceEffect == 22) {
				c.cmdBook3();
			} else if (c.interfaceEffect == 23) {
				c.cmdBook4();
			} else if (c.interfaceEffect == 24) {
				c.cmdBook5();
			}
		break;
		case 39178:
			c.getPA().closeAllWindows();
		break;
			case 58253:
			//c.getPA().showInterface(15106);
			c.getItems().writeBonus();
			break;
			
			/** Skillcapes **/
			case 154:
			if(c.getPA().wearingCape(c.playerEquipment[c.playerCape])) {
				c.stopMovement();
				c.gfx0(c.getPA().skillcapeGfx(c.playerEquipment[c.playerCape]));
				c.startAnimation(c.getPA().skillcapeEmote(c.playerEquipment[c.playerCape]));
			} else {
				c.sendMessage("You must be wearing a Skillcape to do this emote.");
			}
			break;

			case 59004:
			c.getPA().removeAllWindows();
			break;
			
			case 70212:
				if (c.clanId > -1)
					Server.clanChat.leaveClan(c.playerId, c.clanId);
				else
					c.sendMessage("You are not in a clan.");
			break;
			case 62137:
				if (c.clanId >= 0) {
					c.sendMessage("You are already in a clan.");
					break;
				}
				if (c.getOutStream() != null) {
					c.getOutStream().createFrame(187);
					c.flushOutStream();
				}	
			break;
			
			case 9167:
				if (c.dialogueAction == 14) { //44s
				c.getPA().startTeleport(2980, 3866, 0, "modern");
				c.dialogueAction = 0;
				}
			break;

			case 9168:
				if (c.dialogueAction == 14) { //EASTS
				c.getPA().startTeleport(3357, 3721, 0, "modern");
				c.dialogueAction = 0;
				}
			break;
		
			case 9169:
				if (c.dialogueAction == 14) { //MB
				c.getPA().startTeleport(2541, 4714, 0, "modern");
				c.dialogueAction = 0;
				}
			break;

			case 54224://arma
				c.gfx0(342);
				c.getPA().movePlayer(2852, 3755, 0);
				c.getPA().closeAllWindows();
			break;

			case 54228://zammy
				c.gfx0(342);
				c.getPA().movePlayer(2872, 9783, 0);
				c.getPA().closeAllWindows();
			break;

			case 54231://sara
				c.gfx0(342);
				c.getPA().movePlayer(2868, 9937, 0);
				c.getPA().closeAllWindows();
			break;

			case 54221://bandos
				c.gfx0(342);
				c.getPA().movePlayer(3502, 9937, 0);
				c.getPA().closeAllWindows();
			break;

			case 9178:// skiller shop, godwars portal, glory
				if (c.portalThree == 1) {
					c.gfx0(342);
					c.getPA().movePlayer(2852, 3755, 0);
					c.portalThree = 0;
			c.getPA().closeAllWindows();
				} else if (c.shopThree == 1) {
					c.getShops().openShop(1);
					c.shopThree = 0;
				} else if (c.usingGlory) {
					c.getPA().startTeleport(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(3428, 3538, 0, "modern");
				c.dialogueAction = 0;
				if (c.dialogueAction == 3)		
					c.getPA().startTeleport(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0, "modern");
				c.dialogueAction = 0;
				if (c.dialogueAction == 4)
					c.getPA().startTeleport(3565, 3314, 0, "modern");
				c.dialogueAction = 0;
				if (c.dialogueAction == 20) {
					c.getPA().startTeleport(2897, 3618, 4, "modern");
				c.dialogueAction = 0;
					c.killCount = 0;
}
				} else {
				c.getShops().openShop(21);
}
					
			break;
			
			case 9179://multi shop
				if (c.portalThree == 1) {
					c.gfx0(342);
					c.getPA().movePlayer(2868, 9937, 0);
					c.portalThree = 0;
			c.getPA().closeAllWindows();
				} else if (c.shopThree == 1) {
					c.getShops().openShop(34);
					c.shopThree = 0;
				} else if (c.usingGlory) {
					c.getPA().startTeleport(Config.AL_KHARID_X, Config.AL_KHARID_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(2884, 3395, 0, "modern");
				c.dialogueAction = 0;
				if (c.dialogueAction == 3)
					c.getPA().startTeleport(3243, 3513, 0, "modern");
				c.dialogueAction = 0;
				if (c.dialogueAction == 4)
					c.getPA().startTeleport(2444, 5170, 0, "modern");
				c.dialogueAction = 0;
				if (c.dialogueAction == 20) {
					c.getPA().startTeleport(2897, 3618, 12, "modern");
				c.dialogueAction = 0;
					c.killCount = 0;
}
				} else {
				c.getShops().openShop(23);
	}
			break;
			
			case 9180:	
				if (c.portalThree == 1) {
					c.gfx0(342);
					c.getPA().movePlayer(3502, 9937, 0);
					c.portalThree = 0;
			c.getPA().closeAllWindows();
				} else if (c.shopThree == 1) {
					c.getShops().openShop(35);
					c.shopThree = 0;	
				} else if (c.usingGlory) {
					c.getPA().startTeleport(2916, 3168, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(2471,10137, 0, "modern");
				c.dialogueAction = 0;	
				if (c.dialogueAction == 3)
					c.getPA().startTeleport(3363, 3676, 0, "modern");
				c.dialogueAction = 0;
				if (c.dialogueAction == 4)
					c.getPA().startTeleport(2659, 2676, 0, "modern");
				c.dialogueAction = 0;
				if (c.dialogueAction == 20) {
					c.getPA().startTeleport(2897, 3618, 8, "modern");
				c.dialogueAction = 0;
					c.killCount = 0;
}
				} else {
				c.getShops().openShop(26);	
}
			break;
			
			case 9181:
				if (c.portalThree == 1) {
					c.gfx0(342);
					c.getPA().movePlayer(2872, 9783, 0);
					c.portalThree = 0;
			c.getPA().closeAllWindows();
				} else if (c.shopThree == 1) {
					c.getShops().openShop(36);
					c.shopThree = 0;
				} else if (c.usingGlory) {
					c.getPA().startTeleport(3103, 3249, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(2669,3714, 0, "modern");
				c.dialogueAction = 0;
				if (c.dialogueAction == 3)	
					c.getPA().startTeleport(2540, 4716, 0, "modern");
				c.dialogueAction = 0;
				if (c.dialogueAction == 4) {
					c.getPA().startTeleport(3366, 3266, 0, "modern");
				c.dialogueAction = 0;
					c.sendMessage("Dueling is at your own risk. Refunds will not be given for items lost due to glitches.");
				}
				if (c.dialogueAction == 20) {
					//c.getPA().startTeleport(3366, 3266, 0, "modern");
				c.dialogueAction = 0;
					//c.killCount = 0;
					c.sendMessage("This will be added shortly");
}
				} else {
				c.getShops().openShop(3);
}
			break;
			
			case 1093:
			case 1094:
			case 1097:
				if (c.autocastId > 0) {
					c.getPA().resetAutocast();
				} else {
					if (c.playerMagicBook == 1) {
						if (c.playerEquipment[c.playerWeapon] == 4675)
							c.setSidebarInterface(0, 1689);
						else
							c.sendMessage("You can't autocast ancients without an ancient staff.");
					} else if (c.playerMagicBook == 0) {
						if (c.playerEquipment[c.playerWeapon] == 4170) {
							c.setSidebarInterface(0, 12050);
						} else {
							c.setSidebarInterface(0, 1829);
						}	
					}
						
				}		
			break;
			
			case 9157://barrows tele to tunnels
				if(c.dialogueAction == 1) {
					int r = 4;
					//int r = Misc.random(3);
					switch(r) {
						case 0:
							c.getPA().movePlayer(3534, 9677, 0);
							break;
						
						case 1:
							c.getPA().movePlayer(3534, 9712, 0);
							break;
						
						case 2:
							c.getPA().movePlayer(3568, 9712, 0);
							break;
						
						case 3:
							c.getPA().movePlayer(3568, 9677, 0);
							break;
						case 4:
							c.getPA().movePlayer(3551, 9694, 0);
							break;
					}
				} else if (c.dialogueAction == 2) {
					c.getPA().movePlayer(2507, 4717, 0);		
				} else if (c.dialogueAction == 5) {
					c.getSlayer().giveTask();
				} else if (c.dialogueAction == 6) {
					c.getSlayer().giveTask2();
				} else if (c.dialogueAction == 7) {
					c.getPA().startTeleport(3088,3933,0,"modern");
					c.sendMessage("NOTE: You are now in the wilderness...");
				} else if (c.dialogueAction == 8) {
					c.getPA().fixAllBarrows();
								} else if (c.dialogueAction == 31) {
					for (int j = 0; j < PvpItems.length; j++) {
						if (c.getItems().playerHasItem(PvpItems[j])) {
							int amount = c.getItems().getItemAmount(PvpItems[j]);
							c.getItems().deleteItem(PvpItems[j], amount);
							c.getItems().addItem(995, PvpPrices[j]);
					}
				}
			} else if (c.dialogueAction == 13) {
					if (c.getItems().playerHasItem(995,200)) {
						c.getItems().deleteItem(995,c.getItems().getItemSlot(995), 200);
						c.itemBeforeCarpet = c.playerEquipment[c.playerWeapon];
						c.playerEquipment[c.playerWeapon] = 5614;
						c.getPA().movePlayer(3308, 3109, 0);
						c.startAnimation(2262);
						c.isRunning2 = true;
						c.usingCarpet = true;
						c.getPA().walkTo3(-130, -64);
						c.getCombat().getPlayerAnimIndex(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
						c.updateRequired = true; 
						c.setAppearanceUpdateRequired(true);
					} else {
					c.sendMessage("You need 200 coins to ride the carpet.");
					}	
				} else if (c.usingGamesNeck) {
					c.getPA().startTeleport2(2899, 3565, 0);
				} else if (c.usingROD) {
					c.getPA().startTeleport(3314, 3234, 0, "modern");
				}
				c.dialogueAction = 0;
				c.getPA().removeAllWindows();
				break;
			case 9158:
				if (c.dialogueAction == 8) {
				} else if (c.usingGamesNeck) {
					c.getPA().startTeleport2(2524, 3587, 0);
				} else if (c.usingROD) {
					c.getPA().startTeleport(2441, 3089, 0, "modern");
				}
				c.dialogueAction = 0;
				c.getPA().removeAllWindows();
				break;
			
			/**Specials**/
			case 29188:
			c.specBarId = 7636; // the special attack text - sendframe126(S P E C I A L  A T T A C K, c.specBarId);
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29163:
			c.specBarId = 7611;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 33033:
			c.specBarId = 8505;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29038:
			c.specBarId = 7486;
			if (c.playerEquipment[c.playerWeapon] == 4153) {
			c.getCombat().handleGmaulPlayer();
			} else {
			c.usingSpecial = !c.usingSpecial;
			}
			c.getItems().updateSpecialBar();
			break;
			
			case 29063:
			if(c.getCombat().checkSpecAmount(c.playerEquipment[c.playerWeapon])) {
				c.gfx0(246);
				c.forcedChat("Raarrrrrgggggghhhhhhh!");
				c.startAnimation(1056);
				c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]) + (c.getLevelForXP(c.playerXP[2]) * 15 / 100);
				c.getPA().refreshSkill(2);
				c.getItems().updateSpecialBar();
			} else {
				c.sendMessage("You don't have the required special energy to use this attack.");
			}
			break;
			
			case 48023:
			c.specBarId = 12335;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29138:
			c.specBarId = 7586;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29113:
			c.specBarId = 7561;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29238:
			c.specBarId = 7686;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			/**Dueling**/			
			case 26065: // no forfeit
			case 26040:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(0);
			break;
			
			case 26066: // no movement
			case 26048:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(1);
			break;
			
			case 26069: // no range
			case 26042:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(2);
			break;
			
			case 26070: // no melee
			case 26043:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(3);
			break;				
			
			case 26071: // no mage
			case 26041:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(4);
			break;
				
			case 26072: // no drinks
			case 26045:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(5);
			break;
			
			case 26073: // no food
			case 26046:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(6);
			break;
			
			case 26074: // no prayer
			case 26047:	
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(7);
			break;
			
			case 26076: // obsticals
			case 26075:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(8);
			break;
			
			case 2158: // fun weapons
			case 2157:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(9);
			break;
			
			case 30136: // sp attack
			case 30137:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(10);
			break;	

			case 53245: //no helm
			c.duelSlot = 0;
			c.getTradeAndDuel().selectRule(11);
			break;
			
			case 53246: // no cape
			c.duelSlot = 1;
			c.getTradeAndDuel().selectRule(12);
			break;
			
			case 53247: // no ammy
			c.duelSlot = 2;
			c.getTradeAndDuel().selectRule(13);
			break;
			
			case 53249: // no weapon.
			c.duelSlot = 3;
			c.getTradeAndDuel().selectRule(14);
			break;
			
			case 53250: // no body
			c.duelSlot = 4;
			c.getTradeAndDuel().selectRule(15);
			break;
			
			case 53251: // no shield
			c.duelSlot = 5;
			c.getTradeAndDuel().selectRule(16);
			break;
			
			case 53252: // no legs
			c.duelSlot = 7;
			c.getTradeAndDuel().selectRule(17);
			break;
			
			case 53255: // no gloves
			c.duelSlot = 9;
			c.getTradeAndDuel().selectRule(18);
			break;
			
			case 53254: // no boots
			c.duelSlot = 10;
			c.getTradeAndDuel().selectRule(19);
			break;
			
			case 53253: // no rings
			c.duelSlot = 12;
			c.getTradeAndDuel().selectRule(20);
			break;
			
			case 53248: // no arrows
			c.duelSlot = 13;
			c.getTradeAndDuel().selectRule(21);
			break;
			
			
			case 26018:	
			Client o = (Client) Server.playerHandler.players[c.duelingWith];
			if(o == null) {
				c.getTradeAndDuel().declineDuel();
				return;
			}
			
			if(c.duelRule[2] && c.duelRule[3] && c.duelRule[4]) {
				c.sendMessage("You won't be able to attack the player with the rules you have set.");
				break;
			}
			c.duelStatus = 2;
			if(c.duelStatus == 2) {
				c.getPA().sendFrame126("Waiting for other player...", 6684);
				o.getPA().sendFrame126("Other player has accepted.", 6684);
			}
			if(o.duelStatus == 2) {
				o.getPA().sendFrame126("Waiting for other player...", 6684);
				c.getPA().sendFrame126("Other player has accepted.", 6684);
			}
			
			if(c.duelStatus == 2 && o.duelStatus == 2) {
				c.canOffer = false;
				o.canOffer = false;
				c.duelStatus = 3;
				o.duelStatus = 3;
				c.getTradeAndDuel().confirmDuel();
				o.getTradeAndDuel().confirmDuel();
			}
			break;
			
			case 25120:
			if(c.duelStatus == 5) {
				break;
			}
			Client o1 = (Client) Server.playerHandler.players[c.duelingWith];
			if(o1 == null) {
				c.getTradeAndDuel().declineDuel();
				return;
			}

			c.duelStatus = 4;
			if(o1.duelStatus == 4 && c.duelStatus == 4) {				
				c.getTradeAndDuel().startDuel();
				o1.getTradeAndDuel().startDuel();
				o1.duelCount = 4;
				c.duelCount = 4;
				c.duelDelay = System.currentTimeMillis();
				o1.duelDelay = System.currentTimeMillis();
			} else {
				c.getPA().sendFrame126("Waiting for other player...", 6571);
				o1.getPA().sendFrame126("Other player has accepted", 6571);
			}
			break;
	
			
			case 4169: // god spell charge
			c.usingMagic = true;
			if(!c.getCombat().checkMagicReqs(48)) {
				break;
			}
				
			if(System.currentTimeMillis() - c.godSpellDelay < Config.GOD_SPELL_CHARGE) {
				c.sendMessage("You still feel the charge in your body!");
				break;
			}
			c.godSpellDelay	= System.currentTimeMillis();
			c.sendMessage("You feel charged with a magical power!");
			c.gfx100(c.MAGIC_SPELLS[48][3]);
			c.startAnimation(c.MAGIC_SPELLS[48][2]);
			c.usingMagic = false;
	        break;
			
			
			case 28164: // item kept on death 
			break;
			
			case 152:
			c.isRunning2 = !c.isRunning2;
			int frame = c.isRunning2 == true ? 1 : 0;
			c.getPA().sendFrame36(173,frame);
			break;
			
			case 9154:
			c.logout();
			break;
			
			case 21010:
			c.takeAsNote = true;
			break;

			case 21011:
			c.takeAsNote = false;
			break;
			
			
			//home teleports
			case 4171:
			case 50056:
			case 117048:
			String type = c.playerMagicBook == 0 ? "modern" : "ancient";
			c.getPA().startTeleport(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0, type);	
			c.shakeScreen(1, 0, 0, 0);
			break;
			
			case 50235: //paddewa
			break;

			case 4140: //varrock
			/*if (c.getItems().playerHasItem(563,1) && c.getItems().playerHasItem(554,1) && c.getItems().playerHasItem(556,3)) {*/
				c.getPA().startTeleport(3214, 3424, 0, "modern");
				/*c.getItems().deleteItem(563,c.getItems().getItemSlot(563),1);
				c.getItems().deleteItem(554,c.getItems().getItemSlot(554),1);
				c.getItems().deleteItem(556,c.getItems().getItemSlot(556),3);
			} else {
				c.sendMessage("You do not have the required runes for this spell.");	
			}*/
			break;

			case 50245: //senntisen 
			break;

			case 4143: //lumb
			/*if (c.getItems().playerHasItem(563,1) && c.getItems().playerHasItem(557,1) && c.getItems().playerHasItem(556,3)) {*/
				c.getPA().startTeleport(3223, 3218, 0, "modern");
				/*c.getItems().deleteItem(563,c.getItems().getItemSlot(563),1);
				c.getItems().deleteItem(557,c.getItems().getItemSlot(557),1);
				c.getItems().deleteItem(556,c.getItems().getItemSlot(556),3);
			} else {
				c.sendMessage("You do not have the required runes for this spell.");	
			}*/
			break;
			
			case 50253://kharyrll
			if (c.getItems().playerHasItem(563,2) && c.getItems().playerHasItem(565,1)) {
				c.getPA().startTeleport(3494, 3472, 0, "ancient");
				c.getItems().deleteItem(563,c.getItems().getItemSlot(563),2);
				c.getItems().deleteItem(565,c.getItems().getItemSlot(565),1);
			} else {
				c.sendMessage("You do not have the required runes for this spell.");	
			}
			break;

			case 4146://fally
			/*if (c.getItems().playerHasItem(563,1) && c.getItems().playerHasItem(555,1) && c.getItems().playerHasItem(556,3)) {*/
				c.getPA().startTeleport(2965, 3380, 0, "modern");
				/*c.getItems().deleteItem(563,c.getItems().getItemSlot(563),1);
				c.getItems().deleteItem(555,c.getItems().getItemSlot(555),1);
				c.getItems().deleteItem(556,c.getItems().getItemSlot(556),3);
			} else {
				c.sendMessage("You do not have the required runes for this spell.");	
			}*/
			break;
			

			case 51005:
			break;

			case 4150: //cammy
			/*if (c.getItems().playerHasItem(563,1) && c.getItems().playerHasItem(556,5)) {*/
				c.getPA().startTeleport(2757, 3477, 0, "modern");
				/*c.getItems().deleteItem(563,c.getItems().getItemSlot(563),1);
				c.getItems().deleteItem(556,c.getItems().getItemSlot(556),5);
			} else {
				c.sendMessage("You do not have the required runes for this spell.");	
			}*/
			break;			
			
			case 51013: //dareyak tele
			break;

			case 6004: //ardy
			/*if (c.getItems().playerHasItem(563,2) && c.getItems().playerHasItem(555,2)) {*/
				c.getPA().startTeleport(2662, 3307, 0, "modern");
				/*c.getItems().deleteItem(563,c.getItems().getItemSlot(563),2);
				c.getItems().deleteItem(555,c.getItems().getItemSlot(555),2);
			} else {
				c.sendMessage("You do not have the required runes for this spell.");	
			}*/
			break; 
			
			
			case 51023: //carralangar
			if (c.getItems().playerHasItem(563,2) && c.getItems().playerHasItem(566,2)) {
				c.getPA().startTeleport(3140, 3676, 0, "ancient");
				c.getItems().deleteItem(563,c.getItems().getItemSlot(563),2);
				c.getItems().deleteItem(566,c.getItems().getItemSlot(566),2);
			} else {
				c.sendMessage("You do not have the required runes for this spell.");	
			}
			break;

			case 6005:
			c.getDH().sendOption5("Option 16", "Option 2", "Option 3", "Option 4", "Option 5");
			c.teleAction = 6;
			break; 
			
			
			case 51031://annakarl
			if (c.getItems().playerHasItem(563,2) && c.getItems().playerHasItem(565,2)) {
				c.getPA().startTeleport(3289, 3887, 0, "ancient");
				c.getItems().deleteItem(563,c.getItems().getItemSlot(563),2);
				c.getItems().deleteItem(565,c.getItems().getItemSlot(565),2);
			} else {
				c.sendMessage("You do not have the required runes for this spell.");	
			}
			break;
			
			case 29031:
			break; 		
			
			
			case 51039: //ghorrock tele
			if (c.getItems().playerHasItem(563,2) && c.getItems().playerHasItem(555,8)) {
				c.getPA().startTeleport(2977, 3925, 0, "ancient");
				c.getItems().deleteItem(563,c.getItems().getItemSlot(563),2);
				c.getItems().deleteItem(555,c.getItems().getItemSlot(555),8);
			} else {
				c.sendMessage("You do not have the required runes for this spell.");	
			}
			break;

			case 72038:
			if (c.getItems().playerHasItem(563,2) && c.getItems().playerHasItem(554,2)  && c.getItems().playerHasItem(555,2) && c.getItems().playerHasItem(1963,1)) {
				c.getPA().startTeleport(2787, 2786, 0, "modern");
				c.getItems().deleteItem(563,c.getItems().getItemSlot(563),2);
				c.getItems().deleteItem(557,c.getItems().getItemSlot(555),2);
				c.getItems().deleteItem(556,c.getItems().getItemSlot(554),2);
				c.getItems().deleteItem(1963,c.getItems().getItemSlot(1963),1);
			} else {
				c.sendMessage("You do not have the required runes for this spell (You may need a banana).");	
			}
			break;
			
	                 
			case 9125: //Accurate
			case 6221: // range accurate
			case 22228: //punch (unarmed)
			case 48010: //flick (whip)
			case 21200: //spike (pickaxe)
			case 1080: //bash (staff)
			case 6168: //chop (axe)
			case 6236: //accurate (long bow)
			case 17102: //accurate (darts)
			case 8234: //stab (dagger)
			c.fightMode = 0;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;
			
			case 9126: //Defensive
			case 48008: //deflect (whip)
			case 22229: //block (unarmed)
			case 21201: //block (pickaxe)
			case 1078: //focus - block (staff)
			case 6169: //block (axe)
			case 33019: //fend (hally)
			case 18078: //block (spear)
			case 8235: //block (dagger)
			c.fightMode = 1;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;
			
			case 9127: // Controlled
			case 48009: //lash (whip)
			case 33018: //jab (hally)
			case 6234: //longrange (long bow)
			case 6219: //longrange
			case 18077: //lunge (spear)
			case 18080: //swipe (spear)
			case 18079: //pound (spear)
			case 17100: //longrange (darts)
			c.fightMode = 3;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;
			
			case 9128: //Aggressive
			case 6220: // range rapid
			case 22230: //kick (unarmed)
			case 21203: //impale (pickaxe)
			case 21202: //smash (pickaxe)
			case 1079: //pound (staff)
			case 6171: //hack (axe)
			case 6170: //smash (axe)
			case 33020: //swipe (hally)
			case 6235: //rapid (long bow)
			case 17101: //repid (darts)
			case 8237: //lunge (dagger)
			case 8236: //slash (dagger)
			c.fightMode = 2;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;	
			
			
			/**Prayers**/
			case 21233: // thick skin
			c.getCombat().activatePrayer(0);
			break;	
			case 21234: // burst of str
			c.getCombat().activatePrayer(1);
			break;	
			case 21235: // charity of thought
			c.getCombat().activatePrayer(2);
			break;	
			case 70080: // range
			c.getCombat().activatePrayer(3);
			break;
			case 70082: // mage
			c.getCombat().activatePrayer(4);
			break;
			case 21236: // rockskin
			c.getCombat().activatePrayer(5);
			break;
			case 21237: // super human
			c.getCombat().activatePrayer(6);
			break;
			case 21238:	// improved reflexes
			c.getCombat().activatePrayer(7);
			break;
			case 21239: //hawk eye
			c.getCombat().activatePrayer(8);
			break;
			case 21240:
			c.getCombat().activatePrayer(9);
			break;
			case 21241: // protect Item
			c.getCombat().activatePrayer(10);
			break;			
			case 70084: // 26 range
			c.getCombat().activatePrayer(11);
			break;
			case 70086: // 27 mage
			c.getCombat().activatePrayer(12);
			break;	
			case 21242: // steel skin
			c.getCombat().activatePrayer(13);
			break;
			case 21243: // ultimate str
			c.getCombat().activatePrayer(14);
			break;
			case 21244: // incredible reflex
			c.getCombat().activatePrayer(15);
			break;	
			case 21245: // protect from magic
			c.getCombat().activatePrayer(16);
			break;					
			case 21246: // protect from range
			c.getCombat().activatePrayer(17);
			break;
			case 21247: // protect from melee
			c.getCombat().activatePrayer(18);
			break;
			case 70088: // 44 range
			c.getCombat().activatePrayer(19);
			break;	
			case 70090: // 45 mystic
			c.getCombat().activatePrayer(20);
			break;				
			case 2171: // retrui
			c.getCombat().activatePrayer(21);
			break;					
			case 2172: // redem
			c.getCombat().activatePrayer(22);
			break;					
			case 2173: // smite
			c.getCombat().activatePrayer(23);
			break;
			case 70092: // chiv
			c.getCombat().activatePrayer(24);
			break;
			case 70094: // piety
			c.getCombat().activatePrayer(25);
			break;
			case 70100: // Turmoil
			c.getCombat().activatePrayer(26);
			break;

			case 13092:
			Client ot = (Client) Server.playerHandler.players[c.tradeWith];
			if(ot == null) {
				c.getTradeAndDuel().declineTrade();
				c.sendMessage("Trade declined as the other player has disconnected.");
				break;
			}
			c.getPA().sendFrame126("Waiting for other player...", 3431);
			ot.getPA().sendFrame126("Other player has accepted", 3431);	
			c.goodTrade= true;
			ot.goodTrade= true;
			
			for (GameItem item : c.getTradeAndDuel().offeredItems) {
				if (item.id > 0) {
					if(ot.getItems().freeSlots() < c.getTradeAndDuel().offeredItems.size()) {					
						c.sendMessage(ot.playerName +" only has "+ot.getItems().freeSlots()+" free slots, please remove "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items.");
						ot.sendMessage(c.playerName +" has to remove "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items or you could offer them "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items.");
						c.goodTrade= false;
						ot.goodTrade= false;
						c.getPA().sendFrame126("Not enough inventory space...", 3431);
						ot.getPA().sendFrame126("Not enough inventory space...", 3431);
							break;
					} else {
						c.getPA().sendFrame126("Waiting for other player...", 3431);				
						ot.getPA().sendFrame126("Other player has accepted", 3431);
						c.goodTrade= true;
						ot.goodTrade= true;
						}
					}	
				}	
				if (c.inTrade && !c.tradeConfirmed && ot.goodTrade && c.goodTrade) {
					c.tradeConfirmed = true;
					if(ot.tradeConfirmed) {
						c.getTradeAndDuel().confirmScreen();
						ot.getTradeAndDuel().confirmScreen();
						break;
					}
							  
				}

		
			break;
					
			case 13218:
			c.tradeAccepted = true;
			Client ot1 = (Client) Server.playerHandler.players[c.tradeWith];
				if (ot1 == null) {
					c.getTradeAndDuel().declineTrade();
					c.sendMessage("Trade declined as the other player has disconnected.");
					break;
				}
				
				if (c.inTrade && c.tradeConfirmed && ot1.tradeConfirmed && !c.tradeConfirmed2) {
					c.tradeConfirmed2 = true;
					if(ot1.tradeConfirmed2) {	
						c.acceptedTrade = true;
						ot1.acceptedTrade = true;
						c.getTradeAndDuel().giveItems();
						ot1.getTradeAndDuel().giveItems();
						break;
					}
				ot1.getPA().sendFrame126("Other player has accepted.", 3535);
				c.getPA().sendFrame126("Waiting for other player...", 3535);
				}
				
			break;		
			/* Rules Interface Buttons */
			case 125011: //Click agree
				if(!c.ruleAgreeButton) {
					c.ruleAgreeButton = true;
					c.getPA().sendFrame36(701, 1);
				} else {
					c.ruleAgreeButton = false;
					c.getPA().sendFrame36(701, 0);
				}
				break;
			case 125003://Accept
				if(c.ruleAgreeButton) {
					c.getPA().showInterface(3559);
					c.newPlayer = false;
				} else if(!c.ruleAgreeButton) {
					c.sendMessage("You need to click on you agree before you can continue on.");
				}
				break;
			case 125006://Decline
				c.sendMessage("You have chosen to decline, Client will be disconnected from the server.");
				break;
			/* End Rules Interface Buttons */
			/* Player Options */
			case 74176:
				if(!c.mouseButton) {
					c.mouseButton = true;
					c.getPA().sendFrame36(500, 1);
					c.getPA().sendFrame36(170,1);
				} else if(c.mouseButton) {
					c.mouseButton = false;
					c.getPA().sendFrame36(500, 0);
					c.getPA().sendFrame36(170,0);					
				}
				break;
			case 74184:
				if(!c.splitChat) {
					c.splitChat = true;
					c.getPA().sendFrame36(502, 1);
					c.getPA().sendFrame36(287, 1);
				} else {
					c.splitChat = false;
					c.getPA().sendFrame36(502, 0);
					c.getPA().sendFrame36(287, 0);
				}
				break;
			case 74180:
				if(!c.chatEffects) {
					c.chatEffects = true;
					c.getPA().sendFrame36(501, 1);
					c.getPA().sendFrame36(171, 0);
				} else {
					c.chatEffects = false;
					c.getPA().sendFrame36(501, 0);
					c.getPA().sendFrame36(171, 1);
				}
				break;
			case 74188:
				if(!c.acceptAid) {
					c.acceptAid = true;
					c.getPA().sendFrame36(503, 1);
					c.getPA().sendFrame36(427, 1);
				} else {
					c.acceptAid = false;
					c.getPA().sendFrame36(503, 0);
					c.getPA().sendFrame36(427, 0);
				}
				break;
			case 74192:
				if(!c.isRunning2) {
					c.isRunning2 = true;
					c.getPA().sendFrame36(504, 1);
					c.getPA().sendFrame36(173, 1);
				} else {
					c.isRunning2 = false;
					c.getPA().sendFrame36(504, 0);
					c.getPA().sendFrame36(173, 0);
				}
				break;
			case 74201://brightness1
				c.getPA().sendFrame36(505, 1);
				c.getPA().sendFrame36(506, 0);
				c.getPA().sendFrame36(507, 0);
				c.getPA().sendFrame36(508, 0);
				c.getPA().sendFrame36(166, 1);
				break;
			case 74203://brightness2
				c.getPA().sendFrame36(505, 0);
				c.getPA().sendFrame36(506, 1);
				c.getPA().sendFrame36(507, 0);
				c.getPA().sendFrame36(508, 0);
				c.getPA().sendFrame36(166,2);
				break;

			case 74204://brightness3
				c.getPA().sendFrame36(505, 0);
				c.getPA().sendFrame36(506, 0);
				c.getPA().sendFrame36(507, 1);
				c.getPA().sendFrame36(508, 0);
				c.getPA().sendFrame36(166,3);
				break;

			case 74205://brightness4
				c.getPA().sendFrame36(505, 0);
				c.getPA().sendFrame36(506, 0);
				c.getPA().sendFrame36(507, 0);
				c.getPA().sendFrame36(508, 1);
				c.getPA().sendFrame36(166,4);
				break;
			case 74206://area1
				c.getPA().sendFrame36(509, 1);
				c.getPA().sendFrame36(510, 0);
				c.getPA().sendFrame36(511, 0);
				c.getPA().sendFrame36(512, 0);
				break;
			case 74207://area2
				c.getPA().sendFrame36(509, 0);
				c.getPA().sendFrame36(510, 1);
				c.getPA().sendFrame36(511, 0);
				c.getPA().sendFrame36(512, 0);
				break;
			case 74208://area3
				c.getPA().sendFrame36(509, 0);
				c.getPA().sendFrame36(510, 0);
				c.getPA().sendFrame36(511, 1);
				c.getPA().sendFrame36(512, 0);
				break;
			case 74209://area4
				c.getPA().sendFrame36(509, 0);
				c.getPA().sendFrame36(510, 0);
				c.getPA().sendFrame36(511, 0);
				c.getPA().sendFrame36(512, 1);
				break;
			case 168:
                c.startAnimation(855);
            break;
            case 169:
                c.startAnimation(856);
            break;
            case 162:
                c.startAnimation(857);
            break;
            case 164:
                c.startAnimation(858);
            break;
            case 165:
                c.startAnimation(859);
            break;
            case 161:
                c.startAnimation(860);
            break;
            case 170:
                c.startAnimation(861);
            break;
            case 171:
                c.startAnimation(862);
            break;
            case 163:
                c.startAnimation(863);
            break;
            case 167:
                c.startAnimation(864);
            break;
            case 172:
                c.startAnimation(865);
            break;
            case 166:
                c.startAnimation(866);
            break;
            case 52050:
                c.startAnimation(2105);
            break;
            case 52051:
                c.startAnimation(2106);
            break;
            case 52052:
                c.startAnimation(2107);
            break;
            case 52053:
                c.startAnimation(2108);
            break;
            case 52054:
                c.startAnimation(2109);
            break;
            case 52055:
                c.startAnimation(2110);
            break;
            case 52056:
                c.startAnimation(2111);
            break;
            case 52057:
                c.startAnimation(2112);
            break;
            case 52058:
                c.startAnimation(2113);
            break;
            case 43092:
                c.startAnimation(0x558);
            break;
            case 2155:
                c.startAnimation(0x46B);
            break;
            case 25103:
                c.startAnimation(0x46A);
            break;
            case 25106:
                c.startAnimation(0x469);
            break;
            case 2154:
                c.startAnimation(0x468);
            break;
            case 52071:
                c.startAnimation(0x84F);
            break;
            case 52072:
                c.startAnimation(0x850);
            break;
            case 59062:
                c.startAnimation(2836);
            break;
            case 72032:
                c.startAnimation(3544);
            break;
            case 72033:
                c.startAnimation(3543);
            break;
            case 72254:
                c.startAnimation(3866);
            break;
			/* END OF EMOTES */
			case 28166:
				
				break;
			case 118098:
				if (c.playerLevel[1] < 40) {
					c.sendMessage("You need a defence level of 40 or more to cast this.");
				} else {
					c.getPA().vengMe();
				}
				//SkillMenu.openInterface(c, -1)
				//SkillMenu.openInterface(c,0);
			break;			
			
			case 47130:					
				c.forcedText = "I must slay another " + c.taskAmount + " " + Server.npcHandler.getNpcListName(c.slayerTask) + ".";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;
			
			case 24017:
				c.getPA().resetAutocast();
				//c.sendFrame246(329, 200, c.playerEquipment[c.playerWeapon]);
				c.getItems().sendWeapon(c.playerEquipment[c.playerWeapon], c.getItems().getItemName(c.playerEquipment[c.playerWeapon]));
				//c.setSidebarInterface(0, 328);
				//c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151 : c.playerMagicBook == 1 ? 12855 : 1151);
			break;
			
			case 33206:
				c.forcedText = "[Quick Chat] My Attack Level Is " + c.playerLevel[0] + "";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;
			
			case 33209:
				c.forcedText = "[Quick Chat] My Strength Level Is " + c.playerLevel[2] + "";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;
			
			case 33212:
				c.forcedText = "[Quick Chat] My Defence Level Is " + c.playerLevel[1] + "";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;

			case 33215:
				c.forcedText = "[Quick Chat] My Range Level Is " + c.playerLevel[4] + "";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;

			case 33218:
				c.forcedText = "[Quick Chat] My Prayer Level Is " + c.playerLevel[5] + "";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;

			case 33221:
				c.forcedText = "[Quick Chat] My Magic Level Is " + c.playerLevel[6] + "";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;
		
			case 33224:
				c.forcedText = "[Quick Chat] My Runecrafting Level Is " + c.playerLevel[20] + "";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;

			case 33207:
				c.forcedText = "[Quick Chat] My Hitpoints Level Is " + c.playerLevel[3] + "";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;

			case 33210:
				c.forcedText = "[Quick Chat] My Agility Level Is " + c.playerLevel[16] + "";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;

			case 33213:
				c.forcedText = "[Quick Chat] My Herblore Level Is " + c.playerLevel[15] + "";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;

			case 33216:
				c.forcedText = "[Quick Chat] My Thieving Level Is " + c.playerLevel[17] + "";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;

			case 33219:
				c.forcedText = "[Quick Chat] My Crafting Level Is " + c.playerLevel[12] + "";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;

			case 33222:
				c.forcedText = "[Quick Chat] My Fletching Level Is " + c.playerLevel[9] + "";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;

			case 33208:
				c.forcedText = "[Quick Chat] My Mining Level Is " + c.playerLevel[14] + "";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;

			case 33211:
				c.forcedText = "[Quick Chat] My Smithing Level Is " + c.playerLevel[13] + "";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;

			case 33214:
				c.forcedText = "[Quick Chat] My Fishing Level Is " + c.playerLevel[10] + "";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;

			case 33217:
				c.forcedText = "[Quick Chat] My Cooking Level Is " + c.playerLevel[7] + "";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;

			case 33220:
				c.forcedText = "[Quick Chat] My Firemaking Level Is " + c.playerLevel[11] + "";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;

			case 33223:
				c.forcedText = "[Quick Chat] My Woodcutting Level Is " + c.playerLevel[8] + "";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;

			case 33226:
				c.forcedText = "[Quick Chat] My Farming Level Is " + c.playerLevel[19] + "";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;
		}
		if (c.isAutoButton(actionButtonId))
			c.assignAutocast(actionButtonId);
	}

}
