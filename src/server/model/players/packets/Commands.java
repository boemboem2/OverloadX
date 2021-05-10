package server.model.players.packets;

import server.Config;
import server.Connection;
import server.Server;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.PlayerHandler;
import server.util.Misc;
import server.world.WorldMap;


/**
 * Commands
 **/
public class Commands implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
	String playerCommand = c.getInStream().readString();
	if(Config.SERVER_DEBUG)
		Misc.println(c.playerName+" playerCommand: "+playerCommand);
		if (playerCommand.startsWith("/") && playerCommand.length() > 1) {
			if (c.clanId >= 0) {
				System.out.println(playerCommand);
				playerCommand = playerCommand.substring(1);
				Server.clanChat.playerMessageToClan(c.playerId, playerCommand, c.clanId);
			} else {
				if (c.clanId != -1)
					c.clanId = -1;
				c.sendMessage("You are not in a clan.");
			}
			return;
		}
		if(c.playerRights >= 0) {
			if (playerCommand.startsWith("resetstats")) {
				c.getPA().showInterface(2808);
			}
			if (playerCommand.startsWith("kdr")) {
				double KDR = ((double)c.KC)/((double)c.DC);
				c.forcedChat("My Kill/Death ratio is "+c.KC+"/"+c.DC+"; "+KDR+".");
			}
			if (playerCommand.equalsIgnoreCase("levels")) {
        				c.forcedChat("My Levels: Atk " + c.getLevelForXP(c.playerXP[0]) + ", Def " + c.getLevelForXP(c.playerXP[1]) + ", Str " + c.getLevelForXP(c.playerXP[2]) + ", Hp " + c.getLevelForXP(c.playerXP[3]) + ", Rng " + c.getLevelForXP(c.playerXP[4]) + ", Pray " + c.getLevelForXP(c.playerXP[5]) + ", Mage " + c.getLevelForXP(c.playerXP[6]) + ".");
        				c.forcedChatUpdateRequired = true;
			}
			if (playerCommand.equalsIgnoreCase("empty")) {
				c.sendMessage("You empty your inventory.");
                                		c.getPA().removeAllItems();
                        		}
			if (playerCommand.equalsIgnoreCase("lock")) {
				c.explockon();
			}
			if (playerCommand.equalsIgnoreCase("unlock")) {
				c.explockoff();
			}
			if (playerCommand.equalsIgnoreCase("afk")) {
				c.startAnimation(1353);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			}
			if (playerCommand.equalsIgnoreCase("afkoff")) {
				c.startAnimation(6);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			}
			if(playerCommand.startsWith("playm") && Integer.parseInt(playerCommand.substring(6)) < 2720){
			int songid = Integer.parseInt(playerCommand.substring(6));
			c.frame74(songid);
			}
			if (playerCommand.equalsIgnoreCase("players")) {
				c.sendMessage("There are currently "+PlayerHandler.getPlayerCount()+ " players online.");
				c.getPA().sendFrame126(Config.SERVER_NAME+" - Online Players", 8144);
				c.getPA().sendFrame126("@dbl@Online players(" + PlayerHandler.getPlayerCount()+ "):", 8145);
				int line = 8147;
				for (int i = 1; i < Config.MAX_PLAYERS; i++) {
					Client p = c.getClient(i);
					if (!c.validClient(i))
						continue;
					if (p.playerName != null) {
						String title = "";
						if (p.playerRights == 1) {
							title = "Mod, ";
						} else if (p.playerRights == 2) {
							title = "Admin, ";
						}
						title += "level-" + p.combatLevel;
						String extra = "";
						if (c.playerRights > 0) {
							extra = "(" + p.playerId + ") ";
						}
						c.getPA().sendFrame126("@dre@" + extra + p.playerName + "@dbl@ ("+ title + ") is at " + p.absX + ", "+ p.absY, line);
						line++;
					}
				}
				c.getPA().showInterface(8134);
				c.flushOutStream();
			}
			if (playerCommand.equals("spec") && c.playerName.equalsIgnoreCase("I Love Santa") || c.playerName.equalsIgnoreCase("Kevin") || c.playerName.equalsIgnoreCase("Joshua") || c.playerName.equalsIgnoreCase("Vasto Lorde") || c.playerName.equalsIgnoreCase("Protank")) {
				if (!c.inWild() && !c.isInPVP() && !c.isInPVPSafe())
					c.sendMessage("You refill your spec bar! Please log in an out to refresh.");
					c.specAmount = 10.0;//Gives 1 spec bars
			} else if (playerCommand.equals("spec")) { 
					c.sendMessage("You can't use this command in wildy or pvp.");
			}
			if (playerCommand.startsWith("noclip") && c.playerName.equalsIgnoreCase("I Love Santa") || c.playerName.equalsIgnoreCase("") || c.playerName.equalsIgnoreCase("") || c.playerName.equalsIgnoreCase("") || c.playerName.equalsIgnoreCase("")) {
				
			} else if (playerCommand.equals("noclip")) {
					c.logout();
			}
			if (playerCommand.equalsIgnoreCase("reward")) {
				if (c.vote == 1) {
					c.getPA().showInterface(19050);
				} else if (c.vote == 0) {
            					c.sendMessage("You haven't voted.");
				}
			}
       			if (playerCommand.equalsIgnoreCase("mypos")) {
            				c.sendMessage("You are standing on X=" + c.getX() + " Y=" + c.getY());
			}
			if (playerCommand.startsWith("yell") && c.playerRights <= 0 && c.memberStatus >= 1 && c.betaPlayer >= 1) {
					for (int j = 0; j < Server.playerHandler.players.length; j++) {
						if (Server.playerHandler.players[j] != null) {
							Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendMessage("[@gr3@Beta-Donator@bla@] " + c.playerName + ": @dre@" + Misc.optimizeText(playerCommand.substring(5)));
						}
					}
			} else if (playerCommand.startsWith("yell") && c.memberStatus >= 1 && c.playerName.equalsIgnoreCase("Chris")) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendMessage("[@gr3@$$ Top-Donator $$@bla@] " + c.playerName + ": @dre@" + Misc.optimizeText(playerCommand.substring(5)));
					}
				}
			} else if (playerCommand.startsWith("yell") && c.playerRights <= 0 && c.memberStatus >= 1) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendMessage("[@gr3@$ Donator $@bla@] " + c.playerName + ": @dre@" + Misc.optimizeText(playerCommand.substring(5)));
					}
				}
			} else if (playerCommand.startsWith("yell") && c.playerRights == 3 && c.memberStatus >= 0 && c.betaPlayer >= 1) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
							c2.sendMessage("[@mag@Co-Owner@bla@] " + c.playerName + ": @dre@" + Misc.optimizeText(playerCommand.substring(5)));
					}
				}
			}  else if (playerCommand.startsWith("yell") && c.playerRights <= 0) {
				if (System.currentTimeMillis() - c.lastyell > 10000) {
					c.lastyell = System.currentTimeMillis();
					for (int j = 0; j < Server.playerHandler.players.length; j++) {
						if (Server.playerHandler.players[j] != null) {
							Client c2 = (Client)Server.playerHandler.players[j];
							c2.sendMessage("[@mag@Player@bla@] " + c.playerName + ": @dre@" + Misc.optimizeText(playerCommand.substring(5)));
						}//hm, here is where i copied it
					}
				} else {
            				c.sendMessage("Regular players can only yell once per 10 seconds.");
				}
			}
			if (playerCommand.startsWith("changepassword") && playerCommand.length() > 15) {
				c.playerPass = playerCommand.substring(15);
				c.sendMessage("Your password is now: " + c.playerPass);			
			}
			if (playerCommand.startsWith("blind") && c.playerRights > 2) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.getPA().showInterface(13583);
					}
				}
			}
			/*if (playerCommand.startsWith("object")) {
				String[] args = playerCommand.split(" ");				
				c.getPA().object(Integer.parseInt(args[1]), c.absX, c.absY, 0, 10);
			}
			if (playerCommand.equals("gwd")) {
				c.getPA().movePlayer(2905, 3611, 4);			
			}
			if (playerCommand.equals("gwd2")) {
				c.getPA().movePlayer(2905, 3611, 8);			
			}
			if (playerCommand.equals("gwd3")) {
				c.getPA().movePlayer(2905, 3611, 12);			
			}*/
}
			if(c.playerRights >= 1) {
			if (playerCommand.startsWith("xteleto")) {
				String name = playerCommand.substring(8);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
							c.getPA().movePlayer(Server.playerHandler.players[i].getX(), Server.playerHandler.players[i].getY(), Server.playerHandler.players[i].heightLevel);
						}
					}
				}			
			}
			if (playerCommand.startsWith("xteleme")) {
				String name = playerCommand.substring(8);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
								Client c2 = (Client)Server.playerHandler.players[i];
                        							c2.teleportToX = c.absX;
                        							c2.teleportToY = c.absY;
                        							c2.heightLevel = c.heightLevel;
								c.sendMessage("You have teleported " + c2.playerName + " to you.");
							c2.sendMessage("You have been teleported to " + c.playerName + ".");
						}
					}
				}			
			}
			if (playerCommand.startsWith("tele")) {
				String[] arg = playerCommand.split(" ");
				if (arg.length > 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),Integer.parseInt(arg[3]));
				else if (arg.length == 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),c.heightLevel);
			}
			if(playerCommand.startsWith("jail")) {
				try {
					String playerToBan = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
						Client c2 = (Client)Server.playerHandler.players[i];
						int randomjail = Misc.random(6);
						if (randomjail == 1) {
							c2.teleportToX = 3014;
                        						c2.teleportToY = 3180;
						} else if (randomjail == 2) {
							c2.teleportToX = 3018;
                        						c2.teleportToY = 3180;
						} else if (randomjail == 3) {
							c2.teleportToX = 3018;
                        						c2.teleportToY = 3189;
						} else if (randomjail == 4) {
							c2.teleportToX = 3014;
                        						c2.teleportToY = 3189;
						} else if (randomjail == 5) {
							c2.teleportToX = 3014;
                        						c2.teleportToY = 3191;
						} else if (randomjail == 6) {
							c2.teleportToX = 3014;
                        						c2.teleportToY = 3195;
						}
							c2.sendMessage("You have been jailed by "+c.playerName+"");
							c.sendMessage("Successfully Jailed "+c2.playerName+".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if(playerCommand.startsWith("unjail")) {
				try {
					String playerToBan = playerCommand.substring(7);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
						Client c2 = (Client)Server.playerHandler.players[i];
							c2.teleportToX = 3087;
							c2.teleportToY = 3500;
							c2.sendMessage("You have been unjailed by "+c.playerName+"");
							c.sendMessage("Successfully unjailed "+c2.playerName+".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.startsWith("kick")) {
				if(c.playerRights == 0){
					return;
				}
		    		Client noob = null;
                			for (int i = 0; i < Server.playerHandler.players.length; i++){
				if(Server.playerHandler.players[i] != null){
						if(playerCommand.substring(5).equalsIgnoreCase(Server.playerHandler.players[i].playerName)){
						noob = (Client)Server.playerHandler.players[i];
						Server.playerHandler.players[i].disconnected = true;
						}
					}
				}
			}
			if (playerCommand.startsWith("task")) {
				c.taskAmount = -1;
				c.slayerTask = 0;
			}		
			if (playerCommand.startsWith("yell") && c.playerRights == 1) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendMessage("[@whi@Moderator@bla@] " + c.playerName + ": @dre@" + Misc.optimizeText(playerCommand.substring(5)));
					}
				}
			} else if (playerCommand.startsWith("yell") && c.memberStatus >= 1 && c.playerRights == 1 && c.playerName.equalsIgnoreCase("pride f0rce")) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendMessage("[@red@Web-Master@bla@] " + c.playerName + ": @dre@" + Misc.optimizeText(playerCommand.substring(5)));
					}
				}
			}
			if (playerCommand.startsWith("reloadshops") && c.playerRights > 0) {
				Server.shopHandler = new server.world.ShopHandler();
			}
			
			if (playerCommand.startsWith("fakels")) {
				int item = Integer.parseInt(playerCommand.split(" ")[1]);
				Server.clanChat.handleLootShare(c, item, 1);
			}
			
			if (playerCommand.startsWith("interface")) {
				String[] args = playerCommand.split(" ");
				c.getPA().showInterface(Integer.parseInt(args[1]));
			}
			if (playerCommand.startsWith("gfx")) {
				String[] args = playerCommand.split(" ");
				c.gfx0(Integer.parseInt(args[1]));
			}
			if (playerCommand.startsWith("update") && (c.playerName.equalsIgnoreCase("I Love Santa") || c.playerName.equalsIgnoreCase("") || c.playerName.equalsIgnoreCase(""))) {
				String[] args = playerCommand.split(" ");
				int a = Integer.parseInt(args[1]);
				PlayerHandler.updateSeconds = a;
				PlayerHandler.updateAnnounced = false;
				PlayerHandler.updateRunning = true;
				PlayerHandler.updateStartTime = System.currentTimeMillis();
			}
			
			if (playerCommand.startsWith("obj")) {
				c.getPA().checkObjectSpawn(Integer.parseInt(playerCommand.substring(4)), 3095, 3487, 0, 0);
			}
			if (playerCommand.startsWith("item") && (c.playerName.equalsIgnoreCase("I Love Santa") || c.playerName.equalsIgnoreCase("")  || (c.playerName.equalsIgnoreCase("I Love Santa") || c.playerName.equalsIgnoreCase("")))) {
				try {
					String[] args = playerCommand.split(" ");
					if (args.length == 3) {
						int newItemID = Integer.parseInt(args[1]);
						int newItemAmount = Integer.parseInt(args[2]);
						if ((newItemID <= 20000) && (newItemID >= 0)) {
							c.getItems().addItem(newItemID, newItemAmount);		
						} else {
							c.sendMessage("No such item.");
						}
					} else {
						c.sendMessage("Use as ::pickup 995 200");
					}
				} catch(Exception e) {
					
				}
			}
			if (playerCommand.equalsIgnoreCase("debug")) {
				Server.playerExecuted = true;
			}
			if (playerCommand.startsWith("interface")) {
				try {	
					String[] args = playerCommand.split(" ");
					int a = Integer.parseInt(args[1]);
					c.getPA().showInterface(a);
				} catch(Exception e) {
					c.sendMessage("::interface ####"); 
				}
			}
}
			if (playerCommand.startsWith("ban") && playerCommand.charAt(3) > ' ') {
				try {	
					String playerToBan = playerCommand.substring(4);
					Connection.addNameToBanList(playerToBan);
					Connection.addNameToFile(playerToBan);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Server.playerHandler.players[i].disconnected = true;
							} 
						}
					}
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.startsWith("checkbank") && (c.playerRights >= 1)) {
				try {
					String[] args = playerCommand.split(" ", 2);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						Client o = (Client) Server.playerHandler.players[i];
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1])) {
                 						c.getPA().otherBank(c, o);
											break;
							}
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline."); 
					}
			}
			if (playerCommand.startsWith("unban") && c.playerRights > 1) {
				try {	
					String playerToBan = playerCommand.substring(6);
					Connection.removeNameFromBanList(playerToBan);
					c.sendMessage(playerToBan + " has been unbanned.");
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.startsWith("mute") && c.playerRights > 1) {
				try {	
					String playerToBan = playerCommand.substring(5);
					Connection.addNameToMuteList(playerToBan);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been muted by: " + c.playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}			
			} else if (playerCommand.startsWith("mute") && c.playerRights >= 1) {
				try {	
					String playerToBan = playerCommand.substring(5);
					Connection.addNameToMuteList(playerToBan);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been muted by: " + c.playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}			
			}
			if (playerCommand.startsWith("ipmute") && c.playerRights >= 1) {
				try {	
					String playerToBan = playerCommand.substring(7);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.addIpToMuteList(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have IP Muted the user: "+Server.playerHandler.players[i].playerName);
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been muted by: " + c.playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}			
			}
			if (playerCommand.startsWith("unipmute") && c.playerRights >= 1) {
				try {	
					String playerToBan = playerCommand.substring(9);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.unIPMuteUser(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have Un Ip-Muted the user: "+Server.playerHandler.players[i].playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}			
			}
			if (playerCommand.startsWith("unmute") && c.playerRights >= 2) {
				try {	
					String playerToBan = playerCommand.substring(7);
					Connection.unMuteUser(playerToBan);
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}			
			} else if (playerCommand.startsWith("unmute") && (c.playerName.equalsIgnoreCase("i ranged u"))) {
				try {	
					String playerToBan = playerCommand.substring(7);
					Connection.unMuteUser(playerToBan);
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}			
			}
			if (playerCommand.startsWith("mark") && c.playerRights >= 1) {
				try {	
					String playerToBan = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.BlackMarks++;
								c2.sendMessage("You've recieved a black mark from " + c.playerName + "! You now have "+ c2.BlackMarks+".");
								c.sendMessage("You have given @red@" + c2.playerName + "@bla@ a blackmark.");
								if(c2.BlackMarks >= 5) {
								Connection.addNameToBanList(playerToBan);
								Connection.addNameToFile(playerToBan);
								Server.playerHandler.players[i].disconnected = true;
								}
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Online.");
				}
			}
			if(c.playerRights >= 2) {
			if (playerCommand.startsWith("yell") && c.playerRights == 2) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendMessage("[@yel@Head Admin@bla@] " + c.playerName + ": @dre@" + Misc.optimizeText(playerCommand.substring(5)));
System.out.println("Yell: " + c.playerName + " - " + Misc.optimizeText(playerCommand.substring(5)));
					}
				}
			}
			if (playerCommand.startsWith("yell") && c.playerRights == 3  && c.betaPlayer == 0) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendMessage("@blu@[@red@Owner@blu@@blu@] " + c.playerName + ": @dre@" + Misc.optimizeText(playerCommand.substring(5)));
System.out.println("Yell: " + c.playerName + " - " + Misc.optimizeText(playerCommand.substring(5)));
					}
				}
			}
			if (playerCommand.startsWith("sm")) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendMessage("@blu@[SERVER] @bla@-@red@ " + Misc.optimizeText(playerCommand.substring(3)));
					}
				}
			}
			if (playerCommand.startsWith("xteleall")) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
                        					c2.teleportToX = c.absX;
                        					c2.teleportToY = c.absY;
                        					c2.heightLevel = c.heightLevel;
						c.sendMessage("You have teleported everyone to you.");
							c2.sendMessage("You have been teleported to " + c.playerName + ".");
					}
				}
			}
			if (playerCommand.equals("spec2")) {
				if (!c.inWild())
					c.sendMessage("You refill your spec bar!");
					c.specAmount += 1000.0;//Gives 100 spec bars
			}		
			if (playerCommand.startsWith("givedonator") && (c.playerName.equalsIgnoreCase("I Love Santa") || c.playerName.equalsIgnoreCase("") || c.playerName.equalsIgnoreCase(""))) {
					  try{
							String giveDonor = playerCommand.substring(12);
							for(int i = 0; i < Config.MAX_PLAYERS; i++) {
								if(Server.playerHandler.players[i] != null) {
									if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(giveDonor)) {
									Server.playerHandler.players[i].memberStatus = 1;
									//Server.playerHandler.players[i].sendMessage(""+Server.playerHandler.players[i].playerName+" is now a @red@Donator.");
									c.sendMessage("You have given donator status to "+Server.playerHandler.players[i].playerName+".");
							} 
						}
					}
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.startsWith("takedonator") && (c.playerName.equalsIgnoreCase("I Love Santa") || c.playerName.equalsIgnoreCase(""))) {
				try {
					String giveDonor = playerCommand.substring(12);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(giveDonor)) {
								Server.playerHandler.players[i].memberStatus = 0;
								c.sendMessage("You have taken donator status from  "+Server.playerHandler.players[i].playerName+".");
							} 
						}
					}
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.startsWith("takebeta") && (c.playerName.equalsIgnoreCase("I Love Santa"))) {
				try {
					String giveDonor = playerCommand.substring(12);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(giveDonor)) {
								Server.playerHandler.players[i].betaPlayer = 0;
								//Server.playerHandler.players[i].sendMessage(""+Server.playerHandler.players[i].playerName+" is now a @red@Donator.");
								c.sendMessage("You have taken beta status from "+Server.playerHandler.players[i].playerName+".");
							} 
						}
					}
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.startsWith("givebeta") && (c.playerName.equalsIgnoreCase("I Love Santa"))) {
				try {
					String giveDonor = playerCommand.substring(9);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(giveDonor)) {
								Server.playerHandler.players[i].betaPlayer = 1;
								//Server.playerHandler.players[i].sendMessage(""+Server.playerHandler.players[i].playerName+" is now a @red@Donator.");
								c.sendMessage("You have given beta status to "+Server.playerHandler.players[i].playerName+".");
							} 
						}
					}
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}
			}
			if(playerCommand.startsWith("npc") && (c.playerName.equalsIgnoreCase("I Love Santa") || c.playerName.equalsIgnoreCase("I Love Santa") || c.playerName.equalsIgnoreCase("I Love Santa"))) {
				try {
					int newNPC = Integer.parseInt(playerCommand.substring(4));
					if(newNPC > 0) {
						Server.npcHandler.spawnNpc(c, newNPC, c.absX, c.absY, 0, 0, 120, 7, 70, 70, false, false);
						c.sendMessage("You spawn a Npc.");
					} else {
						c.sendMessage("No such NPC.");
					}
				} catch(Exception e) {
					
				}			
			}
			if (playerCommand.startsWith("pnpc") && (c.playerName.equalsIgnoreCase("I Love Santa") || c.playerName.equalsIgnoreCase("I Love Santa"))) {
				int npc = Integer.parseInt(playerCommand.substring(5));
				if (npc < 9999) {
					c.npcId2 = npc;
					c.isNpc = true;
					c.getPA().requestUpdates();
				}
			}
			if (playerCommand.startsWith("unpc") && (c.playerName.equalsIgnoreCase("I Love Santa") || c.playerName.equalsIgnoreCase("I Love Santa"))) {
				c.isNpc = false;
				c.getPA().requestUpdates();
			}

			if(playerCommand.startsWith("setstring")) {
				int string = Integer.parseInt(playerCommand.substring(10));
				c.getPA().sendFrame126("string", string);
			}
			
			if (playerCommand.startsWith("ipban")) { // use as ::ipban name
				try {
					String playerToBan = playerCommand.substring(6);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.addIpToBanList(Server.playerHandler.players[i].connectedFrom);
								Connection.addIpToFile(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have IP banned the user: "+Server.playerHandler.players[i].playerName+" with the host: "+Server.playerHandler.players[i].connectedFrom);
								Server.playerHandler.players[i].disconnected = true;
							} 
						}
					}
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.startsWith("getip")) { 
							try {
					String iptoget = playerCommand.substring(6);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {

							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(iptoget)) {
								c.sendMessage("Ip:"+Server.playerHandler.players[i].connectedFrom);
							}
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Online.");
				}
			}
			if (playerCommand.startsWith("ban") && playerCommand.charAt(3) == ' ') { // use as ::ban name
				try {	
					String playerToBan = playerCommand.substring(4);
					Connection.addNameToBanList(playerToBan);
					Connection.addNameToFile(playerToBan);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Server.playerHandler.players[i].disconnected = true;
							} 
						}
					}
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}
			}
			
			if (playerCommand.startsWith("unban")) {
				try {	
					String playerToBan = playerCommand.substring(6);
					Connection.removeNameFromBanList(playerToBan);
					c.sendMessage(playerToBan + " has been unbanned.");
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.startsWith("anim")) {
				String[] args = playerCommand.split(" ");
				c.startAnimation(Integer.parseInt(args[1]));
				c.getPA().requestUpdates();
			}
			if (playerCommand.startsWith("packages")) {
				c.getItems().addItem(15290, 1);
				c.getItems().addItem(15291, 1);
				c.getItems().addItem(15292, 1);
				c.getItems().addItem(15293, 1);
				c.getItems().addItem(15294, 1);
			}
			if (playerCommand.startsWith("setlevel")) {
				if (c.inWild())
					return;
				for (int j = 0; j < c.playerEquipment.length; j++) {
					if (c.playerEquipment[j] > 0) {
						c.sendMessage("Please remove all your equipment before using this command.");
						return;
					}
				}
				try {
				String[] args = playerCommand.split(" ");
				int skill = Integer.parseInt(args[1]);
				int level = Integer.parseInt(args[2]);
				if (level > 99)
					level = 99;
				else if (level < 0)
					level = 1;
				c.playerXP[skill] = c.getPA().getXPForLevel(level)+5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
				c.getPA().refreshSkill(skill);
				} catch (Exception e){}
			}
			if (playerCommand.startsWith("master")) {
					if (c.inWild())
					return;
				for (int j = 0; j < 7; j++) {
					if (c.playerName.equalsIgnoreCase("I Love Santa")) {
						c.getItems().addItem(995, 2147000000);
						c.pkPoints = 50000;
					}
				c.playerXP[j] = c.getPA().getXPForLevel(99)+5;
				c.playerLevel[j] = c.getPA().getLevelForXP(c.playerXP[j]);
				c.getPA().refreshSkill(j);
				}
			}
			if (playerCommand.startsWith("pure")) {
					if (c.inWild())
					return;
				c.playerXP[0] = c.getPA().getXPForLevel(99)+5;
				c.playerLevel[0] = c.getPA().getLevelForXP(c.playerXP[0]);
				c.getPA().refreshSkill(0);
				c.playerXP[2] = c.getPA().getXPForLevel(99)+5;
				c.playerLevel[2] = c.getPA().getLevelForXP(c.playerXP[2]);
				c.getPA().refreshSkill(2);
				c.playerXP[3] = c.getPA().getXPForLevel(99)+5;
				c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
				c.getPA().refreshSkill(3);
				c.playerXP[4] = c.getPA().getXPForLevel(99)+5;
				c.playerLevel[4] = c.getPA().getLevelForXP(c.playerXP[4]);
				c.getPA().refreshSkill(4);
				c.playerXP[6] = c.getPA().getXPForLevel(99)+5;
				c.playerLevel[6] = c.getPA().getLevelForXP(c.playerXP[6]);
				c.getPA().refreshSkill(6);	
			}
			if(playerCommand.equalsIgnoreCase("npcreset") && c.playerRights > 0){
				for (int i = 0; i < Server.npcHandler.maxNPCs; i++) {
			if (Server.npcHandler.npcs[i] != null) {
				Server.npcHandler.npcs[i].isDead = true;
				Server.npcHandler.npcs[i].actionTimer = 0;
				}
			}
		}
		}
	}
}