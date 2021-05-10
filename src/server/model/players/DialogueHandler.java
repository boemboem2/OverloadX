package server.model.players;

public class DialogueHandler {

	public Client c;
	
	public DialogueHandler(Client client) {
		this.c = client;
	}
	
	/**
	 * Handles all talking
	 * @param dialogue The dialogue you want to use
	 * @param npcId The npc id that the chat will focus on during the chat
	 */
	public void sendDialogues(int dialogue, int npcId) {
		c.talkingNpc = npcId;
		switch(dialogue) {
		case 0:
			c.talkingNpc = -1;
			c.getPA().removeAllWindows();
			c.nextChat = 0;
			break;
		case 1:
			sendStatement("You found a hidden tunnel! Do you want to enter it?");
			c.dialogueAction = 1;
			c.nextChat = 2;
			break;
		case 2:
			sendOption2("Yea! I'm fearless!",  "No way! That looks scary!");
			c.dialogueAction = 1;
			c.nextChat = 0;
			break;
		case 3:
			sendNpcChat4("Hello!", "My name is Duradel and I am a master of the slayer skill.", "I can assign you a slayer task suitable to your combat level.", 
			"Would you like a slayer task?", c.talkingNpc, "Duradel");
			c.nextChat = 4;
		break;
		case 5:
			sendNpcChat4("Hello adventurer...", "My name is Kolodion, the master of this mage bank.", "Would you like to play a minigame in order ", 
						"to earn points towards recieving magic related prizes?", c.talkingNpc, "Kolodion");
			c.nextChat = 6;
		break;
		case 6:
			sendNpcChat4("The way the game works is as follows...", "You will be teleported to the wilderness,", 
			"You must kill mages to recieve points,","redeem points with the chamber guardian.", c.talkingNpc, "Kolodion");
			c.nextChat = 15;
		break;
		case 11:
			sendNpcChat4("Hello!", "My name is Duradel and I am a master of the slayer skill.", "I can assign you a slayer task suitable to your combat level.", 
			"Would you like a slayer task?", c.talkingNpc, "Duradel");
			c.nextChat = 12;
		break;
		case 12:
			sendOption2("Yes I would like a slayer task.", "No I would not like a slayer task.");
			c.dialogueAction = 5;
		break;
		case 13:
			sendNpcChat4("Hello!", "My name is Duradel and I am a master of the slayer skill.", "I see I have already assigned you a task to complete.", 
			"Would you like me to give you an easier task?", c.talkingNpc, "Duradel");
			c.nextChat = 14;
		break;
		case 14:
			sendOption2("Yes I would like an easier task.", "No I would like to keep my task.");
			c.dialogueAction = 6;
		break;
		case 15:
			sendOption2("Yes I would like to play", "No, sounds too dangerous for me.");
			c.dialogueAction = 7;
		break;
		case 16:
			sendOption2("I would like to fix all my barrows.", "Cancel");
			c.dialogueAction = 8;
		break;
		case 17:
			sendOption5("Air", "Mind", "Water", "Earth", "Runecrafting Shop");
			c.dialogueAction = 10;
			c.nextChat =18;
		break;
		case 18:
			sendOption5("Fire", "Body", "Cosmic", "Astral", "Runecrafting Shop");
			c.dialogueAction = 11;
			c.nextChat =19;
		break;
		case 19:
			sendOption5("Nature", "Law", "Death", "Blood", "Runecrafting Shop");
			c.dialogueAction = 12;
			c.nextChat =17;
		break;
		case 20:
			sendNpcChat1("Would you like to ride the flying carpet for 200 gold?", c.talkingNpc, "Rug Merchant");
			c.nextChat = 21;
		break;
		case 21:
			sendOption2("Yes",  "No");
			c.dialogueAction = 13;
		break;
		case 22:
			sendNpcChat1("You currently have "+c.pkPoints+" PK points.", c.talkingNpc, "Mazchna");
			c.dialogueAction = 39;
		break;
		case 23:
			sendOption5("Dark Knight Fortress", "44 Portals", "East Dragons", "Mage Bank", "High Voltage Champ");
			c.portalFour = 1;
		break;
		case 24:
			sendOption4("Weapons and accessories", "Low Level Shop", "Food & Potions", "Archer Store");
			c.dialogueAction = 15;
		break;
		case 25:
			sendOption5("Magic Armour Shop", "Armour Shop", "Costume Shop", "Skillcape Shop", "Skillcape Shop two");
			c.dialogueAction = 16;
		break;
		case 26:
			sendNpcChat2("Welcome to OverloadX!", "To begin your journey go up the stairs and use the exit.",  c.talkingNpc, "Archaeologist");
			c.dialogueAction = 18;
		break;
		case 27:
			sendNpcChat2("Which elemental altar would you like to visit?", "I also have a small runecrafting shop if your interested.",  c.talkingNpc, "Mage of Zamorak");
			c.nextChat =17;
		break;
		case 28:
			sendNpcChat2("Welcome to the skiller area.", "Which shop would you like to use?",  c.talkingNpc, "Arianwyn");
			c.nextChat =29;
			//c.dialogueAction = 20;
			c.shopThree = 1;
		break;
		case 29:
			sendOption4("General Store", "Tools", "Raw materials", "Raw materials two");
			//c.dialogueAction = 20;
			c.shopThree = 1;
		break;
		case 30:
			sendNpcChat4("Hello adventurer...", "My name is Sir Tristram,", "I am here looking for artifacts lost from my ancestors ", 
							"do you have any that I can buy off of you for gold?", c.talkingNpc, "Sir Tristram");
			c.nextChat = 31;
		break;
		case 31:
			sendOption2("Sure, I wont be needing them.", "No thanks.");
			c.dialogueAction = 31;
		break;
		case 32:
			sendPlayerChat2("Hmm..", "maybe I should leave through the exit.");
			c.dialogueAction = 33;
		break;
		case 33:
			sendNpcChat1("Hello there adventurer", c.talkingNpc, "Archaeological expert");
			c.nextChat = 34;
		break;
		case 34:
			sendPlayerChat1("Hello there, Im in search of a quest!");
			c.nextChat = 35;
		break;
		case 35:
			sendNpcChat3("Hmmm...", "A quest you say?", "Let me think",  c.talkingNpc, "Archaeological expert");
			c.nextChat = 36;
		break;	
		case 36:
			sendNpcChat2("Well unfortunately, I dont need any quests being done", "but I am on my way to excavate an ancient egyptian tomb.",  c.talkingNpc, "Archaeological expert");
			c.nextChat = 37;
		break;
		case 37:
			sendNpcChat2("Im traveling to the center of Al-Kahird desert in search", "of ancient artefacts and I could use an extra hand.",  c.talkingNpc, "Archaeological expert");
			c.nextChat = 38;
		break;
		case 38:
			sendNpcChat1("If your interested meet me near Al-Kahird.",  c.talkingNpc, "Archaeological expert");
			c.nextChat = 39;
		break;
		case 39:
			sendNpcChat3("But you will have to excuse me for now,", "I have been traveling for many months and", "must take some time to relax.",  c.talkingNpc, "Archaeological expert");
			c.nextChat = 40;
		break;
		case 40:
			sendNpcChat3("In a few days when I am fully rested,", "Then I will resume my travels and you can", "meet me in Al-Kahird",  c.talkingNpc, "Archaeological expert");
			c.nextChat = 41;
		break;
		case 41:
			sendNpcChat2("But untill then, I will remain here", "resting untill the time comes",  c.talkingNpc, "Archaeological expert");
			c.nextChat = 42;
		break;
		case 42:
			sendPlayerChat1("Ok, Thanks");
			c.getPA().closeAllWindows();
		break;
		case 200:
			sendNpcChat4("Hello there "+c.playerName+"!"," I have the ability to reset your combat stats for free!","But remember, this is irreversable!","What would you like me to do?", c.talkingNpc, "Town Crier");
			c.nextChat = 21;
		break;
		case 210:
			sendOption4("Reset Defence", "Reset Prayer", "Reset Attack", "Reset All Combat Stats");
			c.dialogueAction = 42;
		break;
		case 230:
			sendNpcChat2("Congratulations!", "Your defence has been completely reset!",c.talkingNpc, "Town Crier");
			c.nextChat = 0;
		break;
		case 240:
			sendNpcChat2("Congratulations!", "Your attack has been completely reset!",c.talkingNpc, "Town Crier");
			c.nextChat = 0;
		break;
		case 250:
			sendNpcChat2("Congratulations!", "Your combat stats have been completely reset!",c.talkingNpc, "Town Crier");
			c.nextChat = 0;
		break;
		case 260:
			sendNpcChat2("Congratulations!","Your prayer have been completely reset!",c.talkingNpc, "Town Crier");
			c.nextChat = 0;
		break;
		}
	}
	
	/*
	 * Information Box
	 */
	
	public void sendStartInfo(String text, String text1, String text2, String text3, String title) {
		c.getPA().sendFrame126(title, 6180);
		c.getPA().sendFrame126(text, 6181);
		c.getPA().sendFrame126(text1, 6182);
		c.getPA().sendFrame126(text2, 6183);
		c.getPA().sendFrame126(text3, 6184);
		c.getPA().sendFrame164(6179);
	}
	
	/*
	 * Options
	 */
	
	public void sendOption(String s, String s1) {
		c.getPA().sendFrame126("Select an Option", 2470);
	 	c.getPA().sendFrame126(s, 2471);
		c.getPA().sendFrame126(s1, 2472);
		c.getPA().sendFrame126("Click here to continue", 2473);
		c.getPA().sendFrame164(13758);
	}	
	
	public void sendOption2(String s, String s1) {
		c.getPA().sendFrame126("Select an Option", 2460);
		c.getPA().sendFrame126(s, 2461);
		c.getPA().sendFrame126(s1, 2462);
		c.getPA().sendFrame164(2459);
	}
	
	public void sendOption3(String s, String s1, String s2) {
		c.getPA().sendFrame126("Select an Option", 2470);
		c.getPA().sendFrame126(s, 2471);
		c.getPA().sendFrame126(s1, 2472);
		c.getPA().sendFrame126(s2, 2473);
		c.getPA().sendFrame164(2469);
	}
	
	public void sendOption4(String s, String s1, String s2, String s3) {
		c.getPA().sendFrame126("Select an Option", 2481);
		c.getPA().sendFrame126(s, 2482);
		c.getPA().sendFrame126(s1, 2483);
		c.getPA().sendFrame126(s2, 2484);
		c.getPA().sendFrame126(s3, 2485);
		c.getPA().sendFrame164(2480);
	}
	
	public void sendOption5(String s, String s1, String s2, String s3, String s4) {
		c.getPA().sendFrame126("Select an Option", 2493);
		c.getPA().sendFrame126(s, 2494);
		c.getPA().sendFrame126(s1, 2495);
		c.getPA().sendFrame126(s2, 2496);
		c.getPA().sendFrame126(s3, 2497);
		c.getPA().sendFrame126(s4, 2498);
		c.getPA().sendFrame164(2492);
	}

	/*
	 * Statements
	 */
	
	public void sendStatement(String s) { // 1 line click here to continue chat box interface
		c.getPA().sendFrame126(s, 357);
		c.getPA().sendFrame126("Click here to continue", 358);
		c.getPA().sendFrame164(356);
	}
	
	/*
	 * Npc Chatting
	 */
	
	public void sendNpcChat1(String s, int ChatNpc, String name) {
		c.getPA().sendFrame200(4883, 591);
		c.getPA().sendFrame126(name, 4884);
		c.getPA().sendFrame126(s, 4885);
		c.getPA().sendFrame75(ChatNpc, 4883);
		c.getPA().sendFrame164(4882);
	}
	
	public void sendNpcChat2(String s, String s1, int ChatNpc, String name) {
		c.getPA().sendFrame200(4888, 591);
		c.getPA().sendFrame126(name, 4889);
		c.getPA().sendFrame126(s, 4890);
		c.getPA().sendFrame126(s1, 4891);
		c.getPA().sendFrame75(ChatNpc, 4888);
		c.getPA().sendFrame164(4887);
	}
	
	public void sendNpcChat3(String s, String s1, String s2, int ChatNpc, String name) {
		c.getPA().sendFrame200(4894, 591);
		c.getPA().sendFrame126(name, 4895);
		c.getPA().sendFrame126(s, 4896);
		c.getPA().sendFrame126(s1, 4897);
		c.getPA().sendFrame126(s2, 4898);
		c.getPA().sendFrame75(ChatNpc, 4894);
		c.getPA().sendFrame164(4893);
	}
	
	public void sendNpcChat4(String s, String s1, String s2, String s3, int ChatNpc, String name) {
		c.getPA().sendFrame200(4901, 591);
		c.getPA().sendFrame126(name, 4902);
		c.getPA().sendFrame126(s, 4903);
		c.getPA().sendFrame126(s1, 4904);
		c.getPA().sendFrame126(s2, 4905);
		c.getPA().sendFrame126(s3, 4906);
		c.getPA().sendFrame75(ChatNpc, 4901);
		c.getPA().sendFrame164(4900);
	}
	
	/*
	 * Player Chating Back
	 */
	
	public void sendPlayerChat1(String s) {
		c.getPA().sendFrame200(969, 591);
		c.getPA().sendFrame126(c.playerName, 970);
		c.getPA().sendFrame126(s, 971);
		c.getPA().sendFrame185(969);
		c.getPA().sendFrame164(968);
	}
	
	public void sendPlayerChat2(String s, String s1) {
		c.getPA().sendFrame200(974, 591);
		c.getPA().sendFrame126(c.playerName, 975);
		c.getPA().sendFrame126(s, 976);
		c.getPA().sendFrame126(s1, 977);
		c.getPA().sendFrame185(974);
		c.getPA().sendFrame164(973);
	}
	
	public void sendPlayerChat3(String s, String s1, String s2) {
		c.getPA().sendFrame200(980, 591);
		c.getPA().sendFrame126(c.playerName, 981);
		c.getPA().sendFrame126(s, 982);
		c.getPA().sendFrame126(s1, 983);
		c.getPA().sendFrame126(s2, 984);
		c.getPA().sendFrame185(980);
		c.getPA().sendFrame164(979);
	}
	
	public void sendPlayerChat4(String s, String s1, String s2, String s3) {
		c.getPA().sendFrame200(987, 591);
		c.getPA().sendFrame126(c.playerName, 988);
		c.getPA().sendFrame126(s, 989);
		c.getPA().sendFrame126(s1, 990);
		c.getPA().sendFrame126(s2, 991);
		c.getPA().sendFrame126(s3, 992);
		c.getPA().sendFrame185(987);
		c.getPA().sendFrame164(986);
	}
}
