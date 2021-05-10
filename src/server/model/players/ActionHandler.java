package server.model.players;

import java.util.ArrayList;
import server.Config;
import server.model.objects.Object;
import server.util.Misc;
import server.util.ScriptManager;
import server.Server;
import server.event.EventManager;
import server.event.Event;
import server.event.EventContainer;

public class ActionHandler {
	
	private Client c;
		int[] donatorRitem = { 4718, 4720, 4712, 4714, 4724, 4734, 4736, 4738, 4749, 4753, 4757, 4759, 15284, 15285, 14484, 13899, 13902, 13742, 13742, 13740, 13738, 11728, 11724, 11722, 11720, 11720, 11718, 11708, 11706, 11704, 11702, 11700 };
	public int donatorRitem() {
			return donatorRitem[(int) (Math.random() * donatorRitem.length)];
	}
int[] donatorRitem2 = { 1038, 1040, 1042, 1044, 1046, 1046, 1048, 1050, 1055, 1057 };
	public int donatorRitem2() {
			return donatorRitem2[(int) (Math.random() * donatorRitem2.length)];
	}
int[] godwarsChest = { 11690, 11710, 11712, 11714, 11690, 11710, 11712, 11714, 11690, 11710, 11712, 11714, 11690, 11710, 11712, 11714, 11690, 11710, 11712, 11714, 11690, 11710, 11712, 11714, 11690, 11710, 11712, 11714, 11690, 11710, 11712, 11714, 11702, 11704, 11706, 11708 };
	public int godwarsChest() {
			return godwarsChest[(int) (Math.random() * godwarsChest.length)];
	}
	public ActionHandler(Client Client) {
		this.c = Client;
	}
	
	
	public void firstClickObject(int objectType, int obX, int obY) {
		c.clickObjectType = 0;
		//c.sendMessage("Object type: " + objectType);
		switch(objectType) {
		        case 3192:
                		c.highscores();
                	break;
		case 2561:
			c.getThieving().stealFromStall(995, 35000, 10, 1);
		break;
		case 2560:
			c.getThieving().stealFromStall(995, 50000, 40, 40);
		break;
		case 2564:
			c.getThieving().stealFromStall(995, 75000, 60, 60);
		break;
		case 2562:
		if(!c.inDonatorzone() && c.memberStatus == 1) {
			c.getThieving().stealFromStall(995, 150000, 80, 80);
			} else if(!c.inDonatorzone()) {
			c.sendMessage("Donators ONLY! D:");
			} else {
			c.getThieving().stealFromStall(995, 100000, 80, 80);
			}
		break;
		case 104:
			if (c.memberStatus == 1 && c.donatorChest == 2) {
					c.sendMessage("There is appears to be nothing inside.");	

			} else if (c.memberStatus == 1 && c.donatorChest == 0 || c.donatorChest == 1) {
					c.getItems().addItem(donatorRitem(),Misc.random(1));
					c.getItems().addItem(donatorRitem2(),Misc.random(1));
					c.getItems().addItem(995,Misc.random(10000000));
					c.donatorChest = 2;					
					
			} else {
				c.sendMessage("This is a donator only chest.");
			}
		break;
		case 76:
			if (c.killCount < 20) {
					c.sendMessage("You need 20 kills to get the reward.");	

			} else if (c.killCount == 20) {
					c.getItems().addItem(godwarsChest(),Misc.random(1));
					c.getItems().addItem(995,Misc.random(5000000));
					c.killCount = 0;					
					
			} else {
				c.sendMessage("You need 20 kills to get the reward.");
			}
		break;
		case 6512:
			if (c.objectX == 3250 && c.objectY == 9324 && c.getItems().playerHasItem(1,1) && c.getItems().playerHasItem(670,1) && !c.getItems().playerHasItem(4618,1)) {
			c.getItems().addItem(4618,1);
			c.sendMessage("You uncover a mysterious statuette!");
			} else {
			c.sendMessage("There is nothing in this tomb.");
			}
		break;
		case 6501:
			if (c.objectX == 3232 && c.objectY == 9293) {
			c.sendMessage("The heavy trap door wont budge, perhaps i should try opening it from the other side.");
			}
		break;
		case 6514:
			if (c.objectX == 3252 && c.objectY == 9330 && c.getItems().playerHasItem(1,1) && c.getItems().playerHasItem(670,1) && !c.getItems().playerHasItem(4691,1)) {
			c.getItems().addItem(4691,1);
			c.sendMessage("You uncover an ancient Sphinx's Token!");
			} else if (c.objectX == 3246 && c.objectY == 9282 && c.getItems().playerHasItem(1,1) && c.getItems().playerHasItem(670,1) && !c.getItems().playerHasItem(782,1)) {
			c.getItems().addItem(782,1);
			c.sendMessage("You uncover an old Family crest.");
			} else if (c.objectX == 3249 && c.objectY == 9293 && c.getItems().playerHasItem(1,1) && c.getItems().playerHasItem(670,1) && !c.getItems().playerHasItem(708,1)) {
			c.getItems().addItem(708,1);
			c.sendMessage("You uncover a rotting Arcenia root.");
			} else {
			c.sendMessage("There is nothing in this tomb.");
			}
		break;
		case 6513:
			if (c.objectX == 3222 && c.objectY == 9324 && c.getItems().playerHasItem(1,1) && c.getItems().playerHasItem(670,1) && !c.getItems().playerHasItem(4818,1)) {
			c.getItems().addItem(4818,1);
			c.sendMessage("You uncover a mysterious ogre artifact!");
			} else {
			c.sendMessage("There is nothing in this tomb.");
			}
		break;
		case 6516:
			if (c.objectX == 3246 && c.objectY == 9308 && c.getItems().playerHasItem(1,1) && c.getItems().playerHasItem(670,1) && !c.getItems().playerHasItem(694,1)) {
			c.getItems().addItem(694,1);
			c.sendMessage("You uncover some old smashed up ceramic remains.");
			} else {
			c.sendMessage("There is nothing in this tomb.");
			}
		break;
		case 6515:
			if (c.objectX == 3221 && c.objectY == 9320 && c.getItems().playerHasItem(1,1) && c.getItems().playerHasItem(670,1) && !c.getItems().playerHasItem(19,1)) {
			c.getItems().addItem(19,1);
			c.sendMessage("You uncover the famous holy grail!");
			} else if (c.objectX == 3217 && c.objectY == 9295 && c.getItems().playerHasItem(1,1) && c.getItems().playerHasItem(670,1) && !c.getItems().playerHasItem(681,1)) {
			c.getItems().addItem(681,1);
			c.sendMessage("You uncover an old talisman of Zaros.");
			} else if (c.objectX == 3242 && c.objectY == 9302 && c.getItems().playerHasItem(1,1) && c.getItems().playerHasItem(670,1) && !c.getItems().playerHasItem(721,1)) {
			c.getItems().addItem(721,1);
			c.sendMessage("You uncover an expensive looking golden bowl.");
			} else {
			c.sendMessage("There is nothing in this tomb.");
			}
		break;
		case 6517:
			if (c.objectX == 3208 && c.objectY == 9315 && c.getItems().playerHasItem(1,1) && c.getItems().playerHasItem(670,1) && !c.getItems().playerHasItem(739,1)) {
			c.getItems().addItem(739,1);
			c.sendMessage("You uncover the famous holy grail!");
			} else if (c.objectX == 3225 && c.objectY == 9308 && c.getItems().playerHasItem(1,1) && c.getItems().playerHasItem(670,1) && !c.getItems().playerHasItem(6785,1)) {
			c.getItems().addItem(6785,1);
			c.sendMessage("You uncover a large statuette of an ancient goddess.");	
			} else if (c.objectX == 3247 && c.objectY == 9323 && c.getItems().playerHasItem(1,1) && c.getItems().playerHasItem(670,1) && !c.getItems().playerHasItem(697,1)) {
			c.getItems().addItem(697,1);
			c.sendMessage("You uncover some old damaged armour.");	
			} else {
			c.sendMessage("There is nothing in this tomb.");
			}
		break;
		case 4878:
			if(!c.inDonatorzone() && c.memberStatus == 1) {
			c.getThieving().stealFromStall(995, 150000, 80, 80);
			} else {
			c.sendMessage("Donators ONLY! D:");
			}
		break;
		case 9299:
			if (c.absY <= 3190) {
					c.getPA().walkTo(0, 1);
				} else {
					c.getPA().walkTo(0, -1);
				}
			break;
		case 1528:
			if (c.objectX == 3182 && c.objectY == 2984) {
				if (c.absX < 3183) {
					c.getPA().walkTo(1, 0);
				} else {
					c.getPA().walkTo(-1 , 0);
				}
			}
			if (c.objectX == 3172 && c.objectY == 2977) {
				if (c.absY >= 2977) {
					c.getPA().walkTo(0, -1);
				} else if (c.absY < 2977) {
					c.getPA().walkTo(0, 1);
				}
			}
		break;
		case 11844:
			if (c.absX == 2936) {
			c.getPA().walkTo(-1, 0);
			} else if (c.absX == 2935) {
			c.getPA().walkTo(1, 0);
			}
		break;
		case 4383:
			c.getPA().movePlayer(2442, 10147, 0);
		break;
		case 8966:
			c.getPA().movePlayer(2510, 3644, 0);
		break;
		case 4577:
			if (c.absY == 3635) {
				c.getPA().walkTo(0,1);
			} else {
				c.getPA().walkTo(0,-1);
			}
		break;
		case 4558:
		case 4559:
			c.getPA().movePlayer(2522, 3595, 0);
		break;
		case 4551:
			c.getPA().movePlayer(2514, 3619, 0);
		break;
		case 9358:
			c.getPA().movePlayer(2480, 5175, 0);
		break;
		case 9359:
			c.getPA().movePlayer(2862, 9572, 0);
		break;
		case 492:
			c.getPA().movePlayer(2856, 9570, 0);
		break;
		case 2113:
			c.getPA().movePlayer(2474, 3438, 0);
			c.startAnimation(828);
		break;
		case 1764:
			if (c.objectX == 2856 && c.objectY == 9569) {
				c.getPA().movePlayer(2337, 9804, 0);
			}
		break;
		case 4157:
			c.getPA().movePlayer(2855, 9569, 0);
		break;
		case 4031:
			if (c.absY == 3117) {
			if (c.getItems().playerHasItem(1854,1)) {
				c.getItems().deleteItem(1854,c.getItems().getItemSlot(1854), 1);
				c.getPA().walkTo(0, -2);
			} else {
				c.sendMessage("You need a shantay pass to come through here.");
				}
			}
			if (c.absY == 3115) {
				c.getPA().walkTo(0, 2);
			}
		break;
		case 2406:
			if (c.absX > 3201) {
				c.getPA().walkTo(-1,0);
			} else {
				c.getPA().walkTo(1,0);
			}
		break;
		case 3725:
		case 3726:
			if (c.absX > 2824) {
				c.getPA().walkTo(-1,0);
			} else {
				c.getPA().walkTo(1,0);
			}
		break;
		case 3745:
			if (c.absX >= 2823) {
				c.getPA().walkTo(-1,0);
			} else {
				c.getPA().walkTo(1,0);
			}
		break;
			
		case 7257:
			if (c.objectX == 2905 && c.objectY == 3537) {
				c.getPA().movePlayer(3061, 4983, 1);
			}
		break;
		case 11867:
			if (c.objectX == 3019 && c.objectY == 3450) {
				c.getPA().movePlayer(3058, 9776, 0);
			}
		break;
		case 1755:
			if (c.objectX == 3019 && c.objectY == 9850) {
				c.getPA().movePlayer(3018, 3450, 0);
			}
		break;
		case 4615:
			if (c.objectX == 2596 && c.objectY == 3608) {
				c.getPA().movePlayer(2598, 3608, 0);
			}
		break;
		case 2475:
			if (c.objectX == 3233 && c.objectY == 9312) {
				c.getPA().movePlayer(3233, 2887, 0);
			}
		break;
		case 6481:
			if (c.objectX == 3233 && c.objectY == 2888) {
				c.getPA().movePlayer(3234, 9312, 0);
			}
		break;
		/*case 2492:
			if (c.killCount >= 20) {
				c.getDH().sendOption4("Armadyl", "Bandos", "Saradomin", "Zamorak");
				c.dialogueAction = 20;
			} else {
				c.sendMessage("You need 20 kill count before teleporting to a boss chamber.");
			}
		break;*/
		
		case 1765:
			c.getPA().movePlayer(3067, 10256, 0);
		break;
		case 2882:
		case 2883:
			if (c.objectX == 3268) {
				if (c.absX < c.objectX) {
					c.getPA().walkTo(1,0);
				} else {
					c.getPA().walkTo(-1,0);
				}
			}
		break;
		case 272:
			c.getPA().movePlayer(c.absX, c.absY, 1);
		break;
		
		case 273:
			c.getPA().movePlayer(c.absX, c.absY, 0);
		break;
		case 245:
			c.getPA().movePlayer(c.absX, c.absY + 2, 2);
		break;
		case 246:
			c.getPA().movePlayer(c.absX, c.absY - 2, 1);
		break;
		case 1766:
			c.getPA().movePlayer(3016, 3849, 0);
		break;
		case 2469:
			c.getPA().movePlayer(3087, 3500, 0); //back home portal
		break;
		case 12254:
			c.getPA().movePlayer(3505, 9494, 0); //back home portal
		break;
		case 2465: //portal multi
			c.getPA().showInterface(14000);
			//c.sendMessage("You'll need V1.1 Client for this portal to work correctly.");
		break;
		case 7133: //rc portal nat
			c.getPA().movePlayer(2398, 4841, 0);
		break;
		case 7132: //rc portal cosmi
			c.getPA().movePlayer(2162, 4833, 0);
		break;
		case 7141: //rc portal blood
			c.sendMessage("@red@Sorry no blood alter atm.");
		break;
		case 7129: //rc portal fire
			c.getPA().movePlayer(2584, 4836 , 0);
		break;
		case 7130: //rc portal earth
			c.getPA().movePlayer(2660, 4839, 0);
		break;
		case 7131: //rc portal body
			c.getPA().movePlayer(2527, 4833, 0);
		break;
		case 7140: //rc portal mind
			c.getPA().movePlayer(2789, 4834, 0);
		break;
		case 7139: //rc portal air
			c.getPA().movePlayer(2845, 4832, 0);
		break;
		case 7138: //rc portal soul
			c.sendMessage("@red@Sorry no soul alter atm.");
		break;
		case 7137: //rc portal water
			c.getPA().movePlayer(2713, 4836, 0);
		break;
		case 7136: //rc portal death
			c.getPA().movePlayer(2207, 4836, 0);
		break;
		case 7135: //rc portal law
			c.getPA().movePlayer(2464, 4834, 0);
		break;
		case 7134: //rc portal chaos
			c.getPA().movePlayer(2269, 4843, 0);
		break;
		case 7319: //portal to kbd
			c.gfx0(342);
			c.getPA().movePlayer(2275, 4689, 0);
			c.getPA().closeAllWindows();
		break;
		case 2466: //portal multi
			c.getDH().sendOption5("Pest Control", "Troll Stronghold", "Tzhaar Cave", "Duel Arena", "KBD");
			c.portalTwo = 1;
		break;
		case 10251: //portal multi godwars
			c.getPA().showInterface(14040);
			//c.sendMessage("You'll need V1.1 Client for this portal to work correctly.");
		break;
		case 5084: //cave exit
			c.getPA().showInterface(18460);
			c.sendMessage("You see a bright light as you exit the cave.");
			c.timedFade();
			c.getPA().addStarter();
		break;

		case 6552:
			if (c.playerMagicBook == 0) {
				c.playerMagicBook = 1;
				c.setSidebarInterface(6, 12855);
				c.sendMessage("An ancient wisdomin fills your mind.");
				c.getPA().resetAutocast();
			} else {
				c.setSidebarInterface(6, 1151); //modern
				c.playerMagicBook = 0;
				c.sendMessage("You feel a drain on your memory.");
				c.autocastId = -1;
				c.getPA().resetAutocast();
			}	
		break;
		case 4008:
			if (c.playerMagicBook == 0) {
				c.playerMagicBook = 1;
				c.setSidebarInterface(6, 29999);
				c.sendMessage("You suddenly remember lunar magic!");
				c.getPA().resetAutocast();
			} else {
				c.setSidebarInterface(6, 1151); //modern
				c.playerMagicBook = 0;
				c.sendMessage("You feel a drain on your memory.");
				c.autocastId = -1;
				c.getPA().resetAutocast();
			}	
		break;
		case 1816:
			c.getPA().startTeleport2(2271, 4680, 0);			
		break;
		case 1817:
			c.getPA().startTeleport(3067, 10253, 0, "modern");
		break;
		case 1814:
			//ardy lever
			c.getPA().startTeleport(3153, 3923, 0, "modern");
		break;
		
		case 9356:
			//c.getPA().enterCaves();
			c.sendMessage("Temporarily removed due to bugs.");
		break;
		case 1733:
			c.getPA().movePlayer(c.absX, 10322, 0);
		break;
		
		case 1734:
			if (c.absY != 10323) {
			c.getPA().movePlayer(3018, 3450, 0);
			} else {
			c.getPA().movePlayer(3044, 3927, 0);
			}
		break;
		
		case 9357:
			c.getPA().resetTzhaar();
		break;
		
		case 8959:
			if (c.getX() == 2490 && (c.getY() == 10146 || c.getY() == 10148)) {
				if (c.getPA().checkForPlayer(2490, c.getY() == 10146 ? 10148 : 10146)) {
					new Object(6951, c.objectX, c.objectY, c.heightLevel, 1, 10, 8959, 15);	
				}			
			}
		break;
		
		case 2213:
		case 14367:
		case 11758:
		case 3193:
		case 2693:
			c.getPA().openUpBank();
		break;
		
		case 10177:
			c.getPA().movePlayer(1890, 4407, 0);
		break;
		case 10230:
			c.getPA().movePlayer(2900, 4449, 0);
		break;
		case 10229:
			c.getPA().movePlayer(1912, 4367, 0);
		break;
		case 2623:
			if (c.absX >= c.objectX)
				c.getPA().walkTo(-1,0);
			else
				c.getPA().walkTo(1,0);
		break;
		//pc boat
		case 14315:
			c.getPA().movePlayer(2661,2639,0);
		break;
		case 14314:
			c.getPA().movePlayer(2657,2639,0);
		break;
		
		case 1596:
		case 1597:
		case 534:
		case 676:
		case 1918:
		case 12:
		case 19:
		case 21:
		case 941:
		case 52:
		case 1589:
		case 53:
		case 54:
		case 50:
		case 3200:
		case 1265:
		case 1202:
		case 145:
		case 1116:
		case 141:
		case 142:
		case 1601:
		case 1649:
		case 1623:
		case 122:
		case 1620:
		case 374:
		case 1585:
		case 1634:
		case 1617:
		case 72:
		case 1639:
		case 1627:
		case 997:
		case 1624:
		case 1619:
		//gw stuff
		case 2555:
		case 2556:
		case 2557:
		//case 2558:
		case 2559:
		//case 2560:
		//case 2561:
		//case 2562:
		case 2563:
		//case 2564:
		case 2565:
		if (c.absX == 2936) {
			c.getPA().walkTo(-1,0);
		} else {
			c.getPA().walkTo(1,0);
		}
		if (c.absY != 3451 && c.absY != 3450) {
		if (c.getY() >= c.objectY)
			c.getPA().walkTo(0,-1);
		else
			c.getPA().walkTo(0,1);
		}
		break;

		
		case 14235:
		case 14233:
			if (c.objectX == 2670)
				if (c.absX <= 2670)
					c.absX = 2671;
				else
					c.absX = 2670;
			if (c.objectX == 2643)
				if (c.absX >= 2643)
					c.absX = 2642;
				else
					c.absX = 2643;
			if (c.absX <= 2585)
				c.absY += 1;
			else c.absY -= 1;
			c.getPA().movePlayer(c.absX, c.absY, 0);
		break;
		
		case 14829: case 14830: case 14827: case 14828: case 14826: case 14831:
			//Server.objectHandler.startObelisk(objectType);
			Server.objectManager.startObelisk(objectType);
		break;
		case 4387:
			//Server.castleWars.joinWait(c,1);
		break;
		
		case 4388:
			//Server.castleWars.joinWait(c,2);
		break;
		
		case 4408:
			//Server.castleWars.joinWait(c,3);
		break;
		
		case 9369:
			if (c.getY() > 5175)
				c.getPA().movePlayer(2399, 5175, 0);
			else
				c.getPA().movePlayer(2399, 5177, 0);
		break;
		
		case 9368:
			if (c.getY() < 5169) {
				Server.fightPits.removePlayerFromPits(c.playerId);
				c.getPA().movePlayer(2399, 5169, 0);
			}	
		break;
		case 4411:
		case 4415:
		case 4417:
		case 4418:
		case 4419:
		case 4420:
		case 4469:
		case 4470:
		case 4911:
		case 4912:
		case 1747:
		case 1757:
 			//Server.castleWars.handleObjects(c, objectType, obX, obY);
		break;
		
		
		
		case 2286:
		case 154:
		case 4058:
		case 2295:
		case 2285:
		case 2313:
		case 2312:
		case 2314:
			c.getAgility().handleGnomeCourse(objectType,obX,obY);
		break;
		
		//barrows
		//Chest
		case 10284:
			if(c.barrowsKillCount < 5) {
				c.sendMessage("You haven't killed all the brothers");
			}
			if(c.barrowsKillCount == 5 && c.barrowsNpcs[c.randomCoffin][1] == 1) {
				c.sendMessage("I have already summoned this npc.");
			}
			if(c.barrowsNpcs[c.randomCoffin][1] == 0 && c.barrowsKillCount >= 5) {
				Server.npcHandler.spawnNpc(c, c.barrowsNpcs[c.randomCoffin][0], 3551, 9694-1, 0, 0, 120, 30, 200, 200, true, true);
				c.barrowsNpcs[c.randomCoffin][1] = 1;
			}
			if((c.barrowsKillCount > 5 || c.barrowsNpcs[c.randomCoffin][1] == 2) && c.getItems().freeSlots() >= 2) {
				/*c.shakeScreen(3, 2, 3, 2);
				EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer p) {
					c.getPA().createPlayersProjectile(c.absX, c.absY, c.absX, c.absY, 60, 60, 60, 43, 31, - c.playerId - 1, 0);
					c.handleHitMask(5);
					}
				}, 10000);*/
				c.getPA().resetBarrows();
				int random = Misc.random(2);
				if (random == 0 || random == 1) {
				c.getItems().addItem(c.getPA().randomRunes(), Misc.random(150) + 100);
				c.getItems().addItem(c.getPA().randomRunes(), Misc.random(150) + 100);
				c.getItems().addItem(c.getPA().randomBarrows(), 1);
				} else if (random == 2) {
				c.getItems().addItem(c.getPA().randomRunes(), Misc.random(150) + 100);
				c.getItems().addItem(c.getPA().randomBarrows(), 1);
				c.getItems().addItem(c.getPA().randomBarrows(), 1);
				}
				//c.getPA().startTeleport(3564, 3288, 0, "modern");
			} else if(c.barrowsKillCount > 5 && c.getItems().freeSlots() <= 2) {
				c.sendMessage("You need at least 3 inventory slots to open the chest.");
			}
			break;
		//doors
		case 6749:
			if(obX == 3562 && obY == 9678) {
				c.getPA().object(3562, 9678, 6749, -3, 0);
				c.getPA().object(3562, 9677, 6730, -1, 0);
			} else if(obX == 3558 && obY == 9677) {
				c.getPA().object(3558, 9677, 6749, -1, 0);
				c.getPA().object(3558, 9678, 6730, -3, 0);
			}
			break;
		case 6730:
			if(obX == 3558 && obY == 9677) {
				c.getPA().object(3562, 9678, 6749, -3, 0);
				c.getPA().object(3562, 9677, 6730, -1, 0);
			} else if(obX == 3558 && obY == 9678) {
				c.getPA().object(3558, 9677, 6749, -1, 0);
				c.getPA().object(3558, 9678, 6730, -3, 0);
			}
			break;
		case 6727:
			if(obX == 3551 && obY == 9684) {
				c.sendMessage("You cant open this door..");
			}
			break;
		case 6746:
			if(obX == 3552 && obY == 9684) {
				c.sendMessage("You cant open this door..");
			}
			break;
		case 6748:
			if(obX == 3545 && obY == 9678) {
				c.getPA().object(3545, 9678, 6748, -3, 0);
				c.getPA().object(3545, 9677, 6729, -1, 0);
			} else if(obX == 3541 && obY == 9677) {
				c.getPA().object(3541, 9677, 6748, -1, 0);
				c.getPA().object(3541, 9678, 6729, -3, 0);
			}
			break;
		case 6729:
			if(obX == 3545 && obY == 9677){
				c.getPA().object(3545, 9678, 6748, -3, 0);
				c.getPA().object(3545, 9677, 6729, -1, 0);
			} else if(obX == 3541 && obY == 9678) {
				c.getPA().object(3541, 9677, 6748, -1, 0);
				c.getPA().object(3541, 9678, 6729, -3, 0);
			}
			break;
		case 6726:
			if(obX == 3534 && obY == 9684) {
				c.getPA().object(3534, 9684, 6726, -4, 0);
				c.getPA().object(3535, 9684, 6745, -2, 0);
			} else if(obX == 3535 && obY == 9688) {
				c.getPA().object(3535, 9688, 6726, -2, 0);
				c.getPA().object(3534, 9688, 6745, -4, 0);
			}
			break;
		case 6745:
			if(obX == 3535 && obY == 9684) {
				c.getPA().object(3534, 9684, 6726, -4, 0);
				c.getPA().object(3535, 9684, 6745, -2, 0);
			} else if(obX == 3534 && obY == 9688) {
				c.getPA().object(3535, 9688, 6726, -2, 0);
				c.getPA().object(3534, 9688, 6745, -4, 0);
			}
			break;
		case 6743:
			if(obX == 3545 && obY == 9695) {
				c.getPA().object(3545, 9694, 6724, -1, 0);
				c.getPA().object(3545, 9695, 6743, -3, 0);
			} else if(obX == 3541 && obY == 9694) {
				c.getPA().object(3541, 9694, 6724, -1, 0);
				c.getPA().object(3541, 9695, 6743, -3, 0);
			}
			break;
		case 6724:
			if(obX == 3545 && obY == 9694) {
				c.getPA().object(3545, 9694, 6724, -1, 0);
				c.getPA().object(3545, 9695, 6743, -3, 0);
			} else if(obX == 3541 && obY == 9695) {
				c.getPA().object(3541, 9694, 6724, -1, 0);
				c.getPA().object(3541, 9695, 6743, -3, 0);
			}
			break; 
		//end doors
		//coffins
		case 6707: // verac
			c.getPA().movePlayer(3556, 3298, 0);
			break;
			
		case 6823:
			if(server.model.minigames.Barrows.selectCoffin(c, objectType)) {
				return;
			}
			if(c.barrowsNpcs[0][1] == 0) {
				Server.npcHandler.spawnNpc(c, 2030, c.getX(), c.getY()-1, -1, 0, 120, 25, 200, 200, true, true);
				c.barrowsNpcs[0][1] = 1;
			} else {
				c.sendMessage("You have already searched in this sarcophagus.");
			}
			break;

		case 6706: // torag 
			c.getPA().movePlayer(3553, 3283, 0);
			break;
			
		case 6772:
			if(server.model.minigames.Barrows.selectCoffin(c, objectType)) {
				return;
			}
			if(c.barrowsNpcs[1][1] == 0) {
				Server.npcHandler.spawnNpc(c, 2029, c.getX()+1, c.getY(), -1, 0, 120, 20, 200, 200, true, true);
				c.barrowsNpcs[1][1] = 1;
			} else {
				c.sendMessage("You have already searched in this sarcophagus.");
			}
			break;
			
			
		case 6705: // karil stairs
			c.getPA().movePlayer(3565, 3276, 0);
			break;
		case 6822:
			if(server.model.minigames.Barrows.selectCoffin(c, objectType)) {
				return;
			}
			if(c.barrowsNpcs[2][1] == 0) {
				Server.npcHandler.spawnNpc(c, 2028, c.getX(), c.getY()-1, -1, 0, 90, 17, 200, 200, true, true);
				c.barrowsNpcs[2][1] = 1;
			} else {
				c.sendMessage("You have already searched in this sarcophagus.");
			}
			break;
			
		case 6704: // guthan stairs
			c.getPA().movePlayer(3578, 3284, 0);
			break;
		case 6773:
			if(server.model.minigames.Barrows.selectCoffin(c, objectType)) {
				return;
			}
			if(c.barrowsNpcs[3][1] == 0) {
				Server.npcHandler.spawnNpc(c, 2027, c.getX(), c.getY()-1, -1, 0, 120, 23, 200, 200, true, true);
				c.barrowsNpcs[3][1] = 1;
			} else {
				c.sendMessage("You have already searched in this sarcophagus.");
			}
			break;
			
		case 6703: // dharok stairs
			c.getPA().movePlayer(3574, 3298, 0);
			break;
		case 6771:
			if(server.model.minigames.Barrows.selectCoffin(c, objectType)) {
				return;
			}
			if(c.barrowsNpcs[4][1] == 0) {
				Server.npcHandler.spawnNpc(c, 2026, c.getX(), c.getY()-1, -1, 0, 120, 45, 250, 250, true, true);
				c.barrowsNpcs[4][1] = 1;
			} else {
				c.sendMessage("You have already searched in this sarcophagus.");
			}
			break;
			
		case 6702: // ahrim stairs
			c.getPA().movePlayer(3565, 3290, 0);
			break;
		case 6821:
			if(server.model.minigames.Barrows.selectCoffin(c, objectType)) {
				return;
			}
			if(c.barrowsNpcs[5][1] == 0) {
				Server.npcHandler.spawnNpc(c, 2025, c.getX(), c.getY()-1, -1, 0, 90, 19, 200, 200, true, true);
				c.barrowsNpcs[5][1] = 1;
			} else {
				c.sendMessage("You have already searched in this sarcophagus.");
			}
			break;
			
		case 1276:
		case 1278://trees
			if (c.playerEquipment[c.playerWeapon] == 15296) {
			c.woodcut[0] = 1511;
			c.woodcut[1] = 1;
			c.woodcut[2] = 30;
			c.getWoodcutting().startWoodcutting(c.woodcut[0], c.woodcut[1], c.woodcut[2]);
			} else {
			c.woodcut[0] = 1511;
			c.woodcut[1] = 1;
			c.woodcut[2] = 25;
			c.getWoodcutting().startWoodcutting(c.woodcut[0], c.woodcut[1], c.woodcut[2]);
		}
		break;
		
		case 1281: //oak
			if (c.playerEquipment[c.playerWeapon] == 15296) {
			c.woodcut[0] = 1521;
			c.woodcut[1] = 15;
			c.woodcut[2] = 47;
			c.getWoodcutting().startWoodcutting(c.woodcut[0], c.woodcut[1], c.woodcut[2]);
			} else {
			c.woodcut[0] = 1521;
			c.woodcut[1] = 15;
			c.woodcut[2] = 37;
			c.getWoodcutting().startWoodcutting(c.woodcut[0], c.woodcut[1], c.woodcut[2]);
		}
		break;
		
		case 1308: //willow
			if (c.playerEquipment[c.playerWeapon] == 15296) {
			c.woodcut[0] = 1519;
			c.woodcut[1] = 30;
			c.woodcut[2] = 78;
			c.getWoodcutting().startWoodcutting(c.woodcut[0], c.woodcut[1], c.woodcut[2]);
			} else {
			c.woodcut[0] = 1519;
			c.woodcut[1] = 30;
			c.woodcut[2] = 68;
			c.getWoodcutting().startWoodcutting(c.woodcut[0], c.woodcut[1], c.woodcut[2]);
		}
		break;
		
		case 1307: //maple
			if (c.playerEquipment[c.playerWeapon] == 15296) {
			c.woodcut[0] = 1517;
			c.woodcut[1] = 45;
			c.woodcut[2] = 120;
			c.getWoodcutting().startWoodcutting(c.woodcut[0], c.woodcut[1], c.woodcut[2]);
			} else {
			c.woodcut[0] = 1517;
			c.woodcut[1] = 45;
			c.woodcut[2] = 100;
			c.getWoodcutting().startWoodcutting(c.woodcut[0], c.woodcut[1], c.woodcut[2]);
		}
		break;
		
		case 1309: //yew
			if (c.playerEquipment[c.playerWeapon] == 15296) {
			c.woodcut[0] = 1515;
			c.woodcut[1] = 60;
			c.woodcut[2] = 200;
			c.getWoodcutting().startWoodcutting(c.woodcut[0], c.woodcut[1], c.woodcut[2]);
			} else {
			c.woodcut[0] = 1515;
			c.woodcut[1] = 60;
			c.woodcut[2] = 175;
			c.getWoodcutting().startWoodcutting(c.woodcut[0], c.woodcut[1], c.woodcut[2]);
		}
		break;
		
		case 1306: //magic
			if (c.playerEquipment[c.playerWeapon] == 15296) {
			c.woodcut[0] = 1513;
			c.woodcut[1] = 75;
			c.woodcut[2] = 280;
			c.getWoodcutting().startWoodcutting(c.woodcut[0], c.woodcut[1], c.woodcut[2]);
			} else {
			c.woodcut[0] = 1513;
			c.woodcut[1] = 75;
			c.woodcut[2] = 250;
			c.getWoodcutting().startWoodcutting(c.woodcut[0], c.woodcut[1], c.woodcut[2]);
		}
		break;

		
		case 2090://copper
		case 2091:
		if (c.playerEquipment[c.playerWeapon] == 15295) {
			c.mining[0] = 436;
			c.mining[1] = 1;
			c.mining[2] = 25;
			c.getMining().startMining(c.mining[0], c.mining[1], c.mining[2]);
		} else {
			c.mining[0] = 436;
			c.mining[1] = 1;
			c.mining[2] = 18;
			c.getMining().startMining(c.mining[0], c.mining[1], c.mining[2]);
		}
		break;
		case 2491://ess
		if (c.playerEquipment[c.playerWeapon] == 15295) {
			c.mining[0] = 1436;
			c.mining[1] = 1;
			c.mining[2] = 25;
			c.getMining().startMining(c.mining[0], c.mining[1], c.mining[2]);
		} else {
			c.mining[0] = 1436;
			c.mining[1] = 1;
			c.mining[2] = 18;
			c.getMining().startMining(c.mining[0], c.mining[1], c.mining[2]);
		}
		break;
		
		case 2094://tin
		if (c.playerEquipment[c.playerWeapon] == 15295) {
			c.mining[0] = 438;
			c.mining[1] = 1;
			c.mining[2] = 25;
			c.getMining().startMining(c.mining[0], c.mining[1], c.mining[2]);
		} else {
			c.mining[0] = 438;
			c.mining[1] = 1;
			c.mining[2] = 18;
			c.getMining().startMining(c.mining[0], c.mining[1], c.mining[2]);
		}
		break;		
		
		case 145856:
		case 2092:
		case 2093: //iron
		if (c.playerEquipment[c.playerWeapon] == 15295) {
			c.mining[0] = 440;
			c.mining[1] = 15;
			c.mining[2] = 45;
			c.getMining().startMining(c.mining[0], c.mining[1], c.mining[2]);
		} else {
			c.mining[0] = 440;
			c.mining[1] = 15;
			c.mining[2] = 35;
			c.getMining().startMining(c.mining[0], c.mining[1], c.mining[2]);
		}
		break;
		
		case 14850:
		case 14851:
		case 14852:
		case 2096:
		case 2097: //coal
		if (c.playerEquipment[c.playerWeapon] == 15295) {
			c.mining[0] = 453;
			c.mining[1] = 30;
			c.mining[2] = 60;
			c.getMining().startMining(c.mining[0], c.mining[1], c.mining[2]);
		} else {
			c.mining[0] = 453;
			c.mining[1] = 30;
			c.mining[2] = 50;
			c.getMining().startMining(c.mining[0], c.mining[1], c.mining[2]);
		}
		break;		
		
		case 2098:
		case 2099:
		if (c.playerEquipment[c.playerWeapon] == 15295) {
			c.mining[0] = 444;
			c.mining[1] = 40;
			c.mining[2] = 75;
			c.getMining().startMining(c.mining[0], c.mining[1], c.mining[2]);
		} else {
			c.mining[0] = 444;
			c.mining[1] = 40;
			c.mining[2] = 65;
			c.getMining().startMining(c.mining[0], c.mining[1], c.mining[2]);
		}
		break;
		
		case 2102:
		case 2103:
		case 14853:
		case 14854:
		case 14855: //mith ore
		if (c.playerEquipment[c.playerWeapon] == 15295) {
			c.mining[0] = 447;
			c.mining[1] = 55;
			c.mining[2] = 90;
			c.getMining().startMining(c.mining[0], c.mining[1], c.mining[2]);
		} else {
			c.mining[0] = 447;
			c.mining[1] = 55;
			c.mining[2] = 80;
			c.getMining().startMining(c.mining[0], c.mining[1], c.mining[2]);
		}
		break;
		
		case 2105:
		case 14862: //addy ore
		if (c.playerEquipment[c.playerWeapon] == 15295) {
			c.mining[0] = 449;
			c.mining[1] = 70;
			c.mining[2] = 110;
			c.getMining().startMining(c.mining[0], c.mining[1], c.mining[2]);
		} else {
			c.mining[0] = 449;
			c.mining[1] = 70;
			c.mining[2] = 95;
			c.getMining().startMining(c.mining[0], c.mining[1], c.mining[2]);
		}
		break;
		
		case 2106:
		case 14859:
		case 14860: //rune ore
		if (c.playerEquipment[c.playerWeapon] == 15295) {
			c.mining[0] = 451;
			c.mining[1] = 85;
			c.mining[2] = 140;
			c.getMining().startMining(c.mining[0], c.mining[1], c.mining[2]);
		} else {
			c.mining[0] = 451;
			c.mining[1] = 85;
			c.mining[2] = 125;
			c.getMining().startMining(c.mining[0], c.mining[1], c.mining[2]);
		}
		break;
		
		case 8143:
			if (c.farm[0] > 0 && c.farm[1] > 0) {
				c.getFarming().pickHerb();
			}
		break;
	
			// DOORS
		case 1516:
			if (c.absX == 2892) {
				c.getPA().walkTo(-1, 0);
			} else if (c.absX == 2891) {
				c.getPA().walkTo(1, 0);
				
				break;
			}
		case 1519:
			if (c.absX == 2892) {
				c.getPA().walkTo(-1,0);
			} else if (c.absX == 2891) {
					c.getPA().walkTo(1,0);
				break;
			}
		case 1600:
			if (c.objectY == 9487 || c.objectY == 3087) {
				if (c.absY >= c.objectY)
					c.getPA().walkTo(-1,0);
				else
					c.getPA().walkTo(1,0);
				break;
			}
		case 1530:
			if (c.absX == 2922) {
				c.getPA().walkTo(-1, 0);
			} else if (c.absX == 2921) {
				c.getPA().walkTo(1, 0);
			}
			if (c.objectY == 2564 || c.objectY == 3310) {
				if (c.absX >= c.objectX)
					c.getPA().walkTo(-1,0);
				else
					c.getPA().walkTo(1,0);
				break;
			}
		case 1531:
		case 1533:
			if (c.objectY == 4682) {
				if (c.absY >= c.objectY)		
					c.getPA().walkTo(0, -1);
			else 
					c.getPA().walkTo(0, 1);
				
				break;
			}
		case 1534:
		case 11712:
		case 11711:
		case 11707:
		case 11708:
		case 6725:
		case 3198:
		case 3197:
			Server.objectHandler.doorHandling(objectType, c.objectX, c.objectY, 0);	
			break;
		
		case 9319:
			if (c.heightLevel == 0)
				c.getPA().movePlayer(c.absX, c.absY, 1);
			else if (c.heightLevel == 1)
				c.getPA().movePlayer(c.absX, c.absY, 2);
		break;
		
		case 9320:
			if (c.heightLevel == 1)
				c.getPA().movePlayer(c.absX, c.absY, 0);
			else if (c.heightLevel == 2)
				c.getPA().movePlayer(c.absX, c.absY, 1);
		break;
		
		case 4496:
		case 4494:
			if (c.heightLevel == 2) {
				c.getPA().movePlayer(c.absX - 5, c.absY, 1);
			} else if (c.heightLevel == 1) {
				c.getPA().movePlayer(c.absX + 5, c.absY, 0);
			}
		break;
		
		case 4493:
			if (c.heightLevel == 0) {
				c.getPA().movePlayer(c.absX - 5, c.absY, 1);
			} else if (c.heightLevel == 1) {
				c.getPA().movePlayer(c.absX + 5, c.absY, 2);
			}
		break;
		
		case 4495:
			if (c.heightLevel == 1) {
				c.getPA().movePlayer(c.absX + 5, c.absY, 2);
			}
		break;
		
		case 5126:
			if (c.absY == 3554)
				c.getPA().walkTo(0,1);
			else
				c.getPA().walkTo(0,-1);
		break;
		
		case 1759:
			if (c.objectX == 2884 && c.objectY == 3397)
				c.getPA().movePlayer(c.absX, c.absY + 6400, 0);				
		break;
		/*case 3203: //dueling forfeit
			if (c.duelCount > 0) {
				c.sendMessage("You may not forfeit yet.");
				break;
			}
			Client o = (Client) Server.playerHandler.players[c.duelingWith];				
			if(o == null) {
				c.getTradeAndDuel().resetDuel();
				c.getPA().movePlayer(Config.DUELING_RESPAWN_X+(Misc.random(Config.RANDOM_DUELING_RESPAWN)), Config.DUELING_RESPAWN_Y+(Misc.random(Config.RANDOM_DUELING_RESPAWN)), 0);
				break;
			}
			if(c.duelRule[0]) {
				c.sendMessage("Forfeiting the duel has been disabled!");
				break;
			}
			if(o != null) {
				o.getPA().movePlayer(Config.DUELING_RESPAWN_X+(Misc.random(Config.RANDOM_DUELING_RESPAWN)), Config.DUELING_RESPAWN_Y+(Misc.random(Config.RANDOM_DUELING_RESPAWN)), 0);
				c.getPA().movePlayer(Config.DUELING_RESPAWN_X+(Misc.random(Config.RANDOM_DUELING_RESPAWN)), Config.DUELING_RESPAWN_Y+(Misc.random(Config.RANDOM_DUELING_RESPAWN)), 0);
				o.duelStatus = 6;
				o.getTradeAndDuel().duelVictory();
				c.getTradeAndDuel().resetDuel();
				c.getTradeAndDuel().resetDuelItems();
				o.sendMessage("The other player has forfeited the duel!");
				c.sendMessage("You forfeit the duel!");
				break;
			}
			
			break;*/
			
		case 409:
		case 4859:
		//case 2640:
			if(c.playerLevel[5] < c.getPA().getLevelForXP(c.playerXP[5])) {
				c.startAnimation(645);
				c.playerLevel[5] = c.getPA().getLevelForXP(c.playerXP[5]);
				c.sendMessage("You recharge your prayer points.");
				c.getPA().refreshSkill(5);
			} else {
				c.sendMessage("You already have full prayer points.");
			}
			break;
		case 2873:
			if (!c.getItems().ownsCape()) {
				c.startAnimation(645);
				c.sendMessage("Saradomin blesses you with a cape.");
				c.getItems().addItem(2412, 1);
			}	
		break;
		case 2875:
			if (!c.getItems().ownsCape()) {
				c.startAnimation(645);
				c.sendMessage("Guthix blesses you with a cape.");
				c.getItems().addItem(2413, 1);
			}
		break;
		case 2874:
			if (!c.getItems().ownsCape()) {
				c.startAnimation(645);
				c.sendMessage("Zamorak blesses you with a cape.");
				c.getItems().addItem(2414, 1);
			}
		break;
		case 2879:
			c.getPA().movePlayer(2538, 4716, 0);
		break;
		case 2878:
			c.getPA().movePlayer(2509, 4689, 0);
		break;
		case 5960:
			c.getPA().startTeleport2(3090, 3956, 0);
		break;
		
		case 1815:
			c.getPA().startTeleport2(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0);
		break;
		
		case 9706:
			c.getPA().startTeleport2(3105, 3951, 0);
		break;
		case 9707:
			c.getPA().startTeleport2(3105, 3956, 0);
		break;
		
		case 5959:
			c.getPA().startTeleport2(2539, 4712, 0);
		break;
		
		case 2558:
			c.sendMessage("This door is locked.");	
		break;
		
		case 9294:
			if (c.absX < c.objectX) {
				c.getPA().movePlayer(c.objectX + 1, c.absY, 0);
			} else if (c.absX > c.objectX) {
				c.getPA().movePlayer(c.objectX - 1, c.absY, 0);
			}
		break;
		
		case 9293:
			if (c.absX < c.objectX) {
				c.getPA().movePlayer(2892, 9799, 0);
			} else {
				c.getPA().movePlayer(2886, 9799, 0);
			}
		break;
		case 10529:
		case 10527:
			if (c.absY <= c.objectY)
				c.getPA().walkTo(0,1);
			else
				c.getPA().walkTo(0,-1);
		break;
		case 3044:
			c.getSmithing().sendSmelting();
		break;
		case 733:
			c.startAnimation(451);
			/*if (Misc.random(1) == 1) {
				c.getPA().removeObject(c.objectX, c.objectY);
				c.sendMessage("You slash the web.");
			} else {
				c.sendMessage("You fail to slash the webs.");
			}*/
			if (c.objectX == 3158 && c.objectY == 3951) {
				new Object(734, c.objectX, c.objectY, c.heightLevel, 1, 10, 733, 50);
			} else {
				new Object(734, c.objectX, c.objectY, c.heightLevel, 0, 10, 733, 50);
			}
		break;
		
		default:
			ScriptManager.callFunc("objectClick1_"+objectType, c, objectType, obX, obY);
			break;

		}
	}
	
	public void secondClickObject(int objectType, int obX, int obY) {
		c.clickObjectType = 0;
		//c.sendMessage("Object type: " + objectType);
		switch(objectType) {
			case 2561:
			c.getThieving().stealFromStall(995, 35000, 10, 1);
		break;
		case 2560:
			c.getThieving().stealFromStall(995, 50000, 40, 40);
		break;
		case 2564:
			c.getThieving().stealFromStall(995, 75000, 60, 60);
		break;
		case 2562:
		if(!c.inDonatorzone() && c.memberStatus == 1) {
			c.getThieving().stealFromStall(995, 150000, 80, 80);
			} else if(!c.inDonatorzone()) {
			c.sendMessage("Donators ONLY! D:");
			} else {
			c.getThieving().stealFromStall(995, 100000, 80, 80);
			}
		break;
		case 4878:
			if(!c.inDonatorzone() && c.memberStatus == 1) {
			c.getThieving().stealFromStall(995, 150000, 80, 80);
			} else {
			c.sendMessage("Donators ONLY! D:");
			}
		break;
			case 11666:
			case 3044:
				c.getSmithing().sendSmelting();
			break;
			case 2213:
			case 14367:
			case 11758:
			case 2693:
				c.getPA().openUpBank();
			break;
			
			case 2557:
				if (System.currentTimeMillis() - c.lastLockPick < 3 || c.freezeTimer > 0)
					break;
				if (c.getItems().playerHasItem(1523,1)) {
					c.lastLockPick = System.currentTimeMillis();
						if (Misc.random(10) <= 3){
							c.sendMessage("You fail to pick the lock.");
							break;
						}
					if (c.objectX == 3190 && c.objectY == 3957) {
						if (c.absY == 3958) {
							c.getPA().walkTo2(0,-1);
						} else if (c.absY == 3957) {
							c.getPA().walkTo2(0,1);
						}
					
					} else if (c.objectX == 3191 && c.objectY == 3963) {
						if (c.absY == 3962) {
							c.getPA().walkTo2(0,1);
						} else if (c.absY == 3963) {
							c.getPA().walkTo2(0,-1);
						}				
					}
				} else {
					c.sendMessage("I need a lockpick to pick this lock.");
				}
			break;
			case 104:
				if (c.memberStatus == 1 && c.donatorChest == 0) {
					c.getItems().addItem(donatorRitem(),Misc.random(1));
					c.getItems().addItem(donatorRitem2(),Misc.random(1));
					c.getItems().addItem(995,Misc.random(10000000));
					c.donatorChest = 1;
				} else if (c.memberStatus == 1 && c.donatorChest == 1) {
					c.sendMessage("There is appears to be nothing inside.");						
					
				} else {
					c.sendMessage("This is a donator only chest.");
				}
			break;
		case 76:
			if (c.killCount < 20) {
					c.sendMessage("You need 20 kills to get the reward.");	

			} else if (c.killCount == 20) {
					c.getItems().addItem(godwarsChest(),Misc.random(1));
					c.getItems().addItem(995,Misc.random(5000000));
					c.killCount = 0;					
					
			} else {
				c.sendMessage("You need 20 kills to get the reward.");
			}
		break;
			case 2558:
				if (System.currentTimeMillis() - c.lastLockPick < 3000 || c.freezeTimer > 0)
					break;
				if (c.getItems().playerHasItem(1523,1)) {
					c.lastLockPick = System.currentTimeMillis();
						if (Misc.random(10) <= 3){
							c.sendMessage("You fail to pick the lock.");
							break;
						}
					if (c.objectX == 3044 && c.objectY == 3956) {
						if (c.absX == 3045) {
							c.getPA().walkTo2(-1,0);
						} else if (c.absX == 3044) {
							c.getPA().walkTo2(1,0);
						}
					
					} else if (c.objectX == 3038 && c.objectY == 3956) {
						if (c.absX == 3037) {
							c.getPA().walkTo2(1,0);
						} else if (c.absX == 3038) {
							c.getPA().walkTo2(-1,0);
						}				
					} else if (c.objectX == 3041 && c.objectY == 3959) {
						if (c.absY == 3960) {
							c.getPA().walkTo2(0,-1);
						} else if (c.absY == 3959) {
							c.getPA().walkTo2(0,1);
						}					
					}
				} else {
					c.sendMessage("I need a lockpick to pick this lock.");
				}
			break;
		default:
			ScriptManager.callFunc("objectClick2_"+objectType, c, objectType, obX, obY);
			break;
		}
	}
	
	
	public void thirdClickObject(int objectType, int obX, int obY) {
		c.clickObjectType = 0;
		c.sendMessage("Object type: " + objectType);
		switch(objectType) {
		default:
			ScriptManager.callFunc("objectClick3_"+objectType, c, objectType, obX, obY);
			break;
		}
	}
	
	public void firstClickNpc(int npcType) {
		c.clickNpcType = 0;
		c.npcClickIndex = 0;
		switch(npcType) {
			case 2262:
				c.getDH().sendDialogues(23, npcType);
			break;
			case 2244:
				c.getShops().openShop(30);
			break;		
			case 619:
				if (c.getItems().playerHasItem(2677,1)) {
					if (c.cluescroll == 5) {
					c.getItems().deleteItem(2677, 1);
					c.getItems().addItem(2714, 1);
					c.startAnimation(862);
					c.cluescroll = 0;
					c.sendMessage("Congratulations! Treasure Trail completed!");
					} else if (c.cluescroll < 5) {
					c.getItems().deleteItem(2677, 1);
					c.getItems().addItem(c.clueScroll(), 1);
					c.cluescroll += 1;
					c.sendMessage("You recieve another clue scroll.");
					}			

			} else {
				c.getDH().sendDialogues(33, npcType);
			}
			break;
			case 243:
				c.getDH().sendDialogues(30,npcType);
			break;
			case 314: //salmon
				c.getFishing().setupFishing(335);
			break;
			case 315: //trout
				c.getFishing().setupFishing(331);
			break;
			case 316: //shrimps
				c.getFishing().setupFishing(317);
			break;
			case 321: //Lobsters
				c.getFishing().setupFishing(377);
			break;
			case 323: //Manta Ray
				if(c.memberStatus == 1) {
				c.getFishing().setupFishing(389);
				}
			break;
			case 651:
				c.getShops().openShop(29);
			break;
			case 553:
				c.getPA().startTeleport(2922, 4846, 0, "modern");
			break;
       			case 1449: // buff monkey
			if (c.buff == 0) {
				c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]);
				c.playerLevel[2] += 8;
				c.getPA().refreshSkill(2);
				c.playerLevel[0] = c.getLevelForXP(c.playerXP[0]);
				c.playerLevel[0] += 8;
				c.getPA().refreshSkill(0);
				c.playerLevel[1] = c.getLevelForXP(c.playerXP[1]);
				c.playerLevel[1] += 8;
				c.getPA().refreshSkill(1);
				c.playerLevel[4] = c.getLevelForXP(c.playerXP[4]);
				c.playerLevel[4] += 8;
				c.getPA().refreshSkill(4);
				c.startAnimation(1914);
				c.gfx100(466);
				c.gfx100(191);
				c.gfx100(78);
				c.gfx100(287);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.sendMessage("You feel great strength flow into you!");
				c.buff= 1;
			} else if (c.buff== 1) {
				c.sendMessage("You no longer receive any strength.");
			}
            			break;
			case 457:
			//c.forcedChat("Hmm.. maybe I should leave through the exit.");
			//c.forcedChatUpdateRequired = true;
			//c.updateRequired = true;
				c.getDH().sendDialogues(32, npcType);
			break;
			case 409:
				c.getShops().openShop(31);
			break;
			case 676:
				//c.getShops().openShop(32);
				c.getDH().sendDialogues(25, npcType);
			break;
			case 1294:
				c.getShops().openShop(23);
			break;
			case 1334:
				if (!c.getItems().ownsBook()) {
					c.getItems().addItem(3842, 1);
					c.sendMessage("You recieve a Unholy book from Jossik.");
				} else {
					c.sendMessage("You already have a book.");
				}
			break;
			case 587:
				c.getShops().openShop(22);
			break;
			case 3792:
				c.getPA().movePlayer(2659, 2676, 0);
			break;
			case 1596:
				c.getDH().sendDialogues(22, npcType);
			break;
			case 3020:
			c.getDH().sendDialogues(20, npcType);
			/*if (c.getItems().playerHasItem(995,200)) {
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
			break;*/
			break;
			/*case 1918:
			if (c.getItems().playerHasItem(995,200)) {
				c.getItems().deleteItem(995,c.getItems().getItemSlot(995), 200);
				c.getPA().movePlayer(3308, 3108, 0);
				c.sendMessage("You pay 200 coins and are flown to the Shanty pass.");
			} else {
				c.sendMessage("You need 200 coins to ride the carpet.");
				}
			break;*/
			case 3791:
				c.getPA().movePlayer(3049, 3235, 0);
			break;
			case 2825:
				c.getPA().movePlayer(2710, 9466, 0);
				c.sendMessage("The ship boards and the pirate escorts you to the dungeon.");
			break;
			case 534:
				//c.getShops().openShop(21);
				c.getDH().sendDialogues(24, npcType);
			c.dialogueAction = 9;
			break;
			case 1071:
				c.getShops().openShop(19);
			break;
			case 706:
				c.getDH().sendDialogues(9, npcType);
			break;
			case 2258:
				//c.getDH().sendDialogues(27, npcType);
				c.getPA().startTeleport(3039, 4834, 0, "modern");
			break;
			case 1918:
				c.getDH().sendDialogues(26, npcType);
			break;
			case 1202:
				c.getDH().sendDialogues(28, npcType);
			break;
			case 1599:
				if (c.slayerTask <= 0) {
					c.getDH().sendDialogues(11,npcType);
				} else {
					c.getDH().sendDialogues(13,npcType);
				}
			break;
			case 461:
				c.getShops().openShop(2);
			break;
			
			case 683:
				c.getShops().openShop(3);
			break;
			case 648:
				c.getShops().openShop(5);
			break;
			case 549:
				c.getShops().openShop(28);
			break;
			
			case 2538:
				c.getShops().openShop(6);
			break;
			
			case 519:
				c.getShops().openShop(8);
			break;
			case 1282:
				c.getShops().openShop(7);
			break;
			case 1152:
				c.getDH().sendDialogues(16,npcType);
			break;
			case 494:
				c.getPA().openUpBank();
			break;
			case 2566:
				//c.getShops().openSkillCape();
			break;
			case 3789:
				c.sendMessage("You currently have " + c.pcPoints + " pest control points.");
			break;
			case 3788:
				c.getShops().openVoid();
			break;
			case 905:
				c.getDH().sendDialogues(5, npcType);
			break;
			case 460:
				c.getDH().sendDialogues(3, npcType);
			break;
			case 462:
				c.getDH().sendDialogues(7, npcType);
			break;
			case 522:
			case 523:
				c.getShops().openShop(1);
			break;
			case 599:
				c.getPA().showInterface(3559);
				c.canChangeAppearance = true;
			break;
			case 904:
				c.sendMessage("You have " + c.magePoints + " points.");
			break;
		default:
			ScriptManager.callFunc("npcClick1_"+npcType, c, npcType);
			if(c.playerRights == 3) 
				Misc.println("First Click Npc : "+npcType);
			break;
		}
	}

	public void secondClickNpc(int npcType) {
		c.clickNpcType = 0;
		c.npcClickIndex = 0;
		switch(npcType) {
			case 1596:
				c.getShops().openShop(29);
			break;
			case 553:
				c.getPA().startTeleport(2922, 4846, 0, "modern");
			break;
			case 1039:
				c.getShops().openShop(30);
			break;
			case 546:
				c.getShops().openShop(27);
			break;
			case 541:
				c.getShops().openShop(26);
			break;
			case 2824:
				c.getShops().openShop(25);
			break;
			case 570:
				c.getShops().openShop(24);
			break;
			case 587:
				c.getShops().openShop(22);
			break;
			case 321: //Shark
				c.getFishing().setupFishing(383);
			break;
			case 324: //Tuna
				c.getFishing().setupFishing(359);
			break;
			case 333: //Monkfish
				c.getFishing().setupFishing(7944);
			break;
			case 534:
				c.getDH().sendDialogues(24, npcType);
			break;
			case 2258:
				c.getShops().openShop(33);
			break;
			case 2270:
				c.getShops().openShop(20);
			break;
			case 1282:
				c.getShops().openShop(7);
			break;
			case 3788:
				c.getShops().openVoid();
			break;
			case 494:
				c.getPA().openUpBank();
			break;
			case 904:
				c.getShops().openShop(17);
			break;
			case 522:
			case 523:
				c.getShops().openShop(1);
			break;
			case 461:
				c.getShops().openShop(2);
			break;
			
			case 683:
				c.getShops().openShop(3);
			break;
			
			case 549:
				c.getShops().openShop(28);
			break;
			
			case 2538:
				c.getShops().openShop(6);
			break;
			
			case 519:
				c.getShops().openShop(8);
			break;
			case 3789:
				c.getShops().openShop(18);
			break;
			case 1:
			case 9:
			case 18:
			case 20:
			case 26:
			case 21:
				c.getThieving().stealFromNPC(npcType);
			break;
			default:
				ScriptManager.callFunc("npcClick2_"+npcType, c, npcType);
				if(c.playerRights == 3) 
					Misc.println("Second Click Npc : "+npcType);
				break;
			
		}
	}
	
	public void thirdClickNpc(int npcType) {
		c.clickNpcType = 0;
		c.npcClickIndex = 0;
		switch(npcType) {
			case 1596:
				c.getShops().openShop(29);
			break;
			default:
				ScriptManager.callFunc("npcClick3_"+npcType, c, npcType);
				if(c.playerRights == 3) 
					Misc.println("Third Click NPC : "+npcType);
				break;

		}
	}
	

}