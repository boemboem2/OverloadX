package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
import server.model.items.ItemAssistant;
import server.util.Misc;


/**
 * Clicking an item, bury bone, eat food etc
 **/
public class ClickItem implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int junk = c.getInStream().readSignedWordBigEndianA();
		int itemSlot = c.getInStream().readUnsignedWordA();
		int itemId = c.getInStream().readUnsignedWordBigEndian();
		if (c.usingCarpet) {
			return;
		}
		if (itemId != c.playerItems[itemSlot] - 1) {
			return;
		}
		if (itemId == 8007 && !c.isInJail()) {
			c.getPA().teleTab("varrock");
		}
		if (itemId == 8008 && !c.isInJail()) {
			c.getPA().teleTab("lumbridge");
		}
		if (itemId == 8009 && !c.isInJail()) {
			c.getPA().teleTab("falador");
		}
/**CLUES**/
		if(itemId == 2677) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("Unscramble this anagram", 6969);
			c.getPA().sendFrame126(" to figure out how to ", 6970);
			c.getPA().sendFrame126("    talk to next:", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("  LXAPGERATEO COEARY", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2678) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2679) {
			c.getPA().showInterface(15712);
			c.getPA().sendFrame126("1", 15712);
			c.getPA().sendFrame126("4", 15713);
			c.getPA().sendFrame126("3", 15714);
			c.getPA().sendFrame126("2", 15715);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2680) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2681) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2682) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2683) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2684) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2685) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2686) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2687) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2688) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2689) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2690) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2691) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2692) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2693) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2694) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2695) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2696) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2697) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2698) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2699) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2700) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2701) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2702) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2703) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2704) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2705) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2706) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2707) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2708) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2709) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2710) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2711) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2712) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}
		if(itemId == 2713) {
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("@yel@Close Window", 6967);
			c.getPA().sendFrame126("", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126("", 6970);
			c.getPA().sendFrame126("", 6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
		}



		if (itemId == 2714 && c.getItems().freeSlots() >= 4) {
			int randomcluereward = Misc.random(100);
				if (randomcluereward == 1) {
				c.clueScroll(c.highcluereward(), 1, c.junkcluereward(), Misc.random(200), c.junkcluereward(), Misc.random(200), c.junkcluereward(), Misc.random(200), 2714);
				} else if (randomcluereward >= 2 && randomcluereward <= 8) {
				c.clueScroll(c.medcluereward(), 1, c.medcluereward(), 1, c.junkcluereward(), Misc.random(200), c.junkcluereward(), Misc.random(200), 2714);
				} else if (randomcluereward >= 9 && randomcluereward <= 27) {
				c.clueScroll(c.medcluereward(), 1, c.junkcluereward(), Misc.random(200), c.junkcluereward(), Misc.random(200), c.junkcluereward(), Misc.random(200), 2714);
				} else if (randomcluereward >= 28 && randomcluereward <= 45) {
				c.clueScroll(c.lowcluereward(), 1, c.lowcluereward(), 1, c.junkcluereward(), Misc.random(200), c.junkcluereward(), Misc.random(200), 2714);
				} else {
				c.clueScroll(c.junkcluereward(), Misc.random(200), c.junkcluereward(), Misc.random(200), c.junkcluereward(), Misc.random(200), c.junkcluereward(), Misc.random(200), 2714);
				}
		 
		} else if (itemId == 4079 &&  c.getItems().freeSlots() <= 4) {
			c.sendMessage("You must have at least 4 open spots to open this casket!");
		}
/**END CLUES**/
		if (itemId == 8010 && !c.isInJail()) {
			c.getPA().teleTab("camelot");
		}
		if (itemId == 8011 && !c.isInJail()) {
			c.getPA().teleTab("ardy");
		}
		if (itemId == 7681) {//overload book
			c.cmdBook1();
		}
		if (itemId == 9050) {
			c.getPA().startTeleport2(3232, 2914, 0);
		}
		if (itemId == 4251) {
			c.getPA().movePlayer(3565, 3316, 0);
			c.sendMessage("You empty the ectophial.");
			c.getItems().deleteItem(4251,c.getItems().getItemSlot(4251), 1);
			c.getItems().addItem(4252, 1);
		}
		if (itemId >= 5509 && itemId <= 5514) {
			int pouch = -1;
			int a = itemId;
			if (a == 5509)
				pouch = 0;
			if (a == 5510)
				pouch = 1;
			if (a == 5512)
				pouch = 2;
			if (a == 5514)
				pouch = 3;
			c.getPA().fillPouch(pouch);
			return;
		}
		if (itemId == 2528) {
			c.getItems().deleteItem(2528,1);
			c.getPA().showInterface(2808);
			c.sendMessage("You rub the lamp.");
			c.lamp = 1;
		}
		if (itemId == 4447) {
			c.getItems().deleteItem(4447,1);
			c.getPA().showInterface(2808);
			c.sendMessage("You rub the lamp.");
			c.lamp2 = 1;
		}
	/**PACKAGES**/
	if (itemId == 15290 && c.getItems().freeSlots() >= 5) {
		c.getItems().deleteItem(15290,1);
		c.getItems().addItem(c.skillerPackage1(),Misc.random(800));
		c.getItems().addItem(c.skillerPackage1(),Misc.random(2000));
		c.getItems().addItem(c.skillerPackage2(),Misc.random(1100));
		c.getItems().addItem(c.skillerPackage2(),Misc.random(1000));
		c.getItems().addItem(c.skillerPackage3(),1);
		c.sendMessage("You open the package and get a reward!");
	} else if (itemId == 15290 && c.getItems().freeSlots() <= 5) {
		c.sendMessage("@red@You must have 5 free inventory slots to open.");
	}
	if (itemId == 15291 && c.getItems().freeSlots() >= 5) {
		c.getItems().deleteItem(15291,1);
		c.getItems().addItem(c.combatpackage1(),1);
		c.getItems().addItem(c.combatpackage2(),1);
		c.getItems().addItem(c.combatpackage3(),1);
		c.sendMessage("You open the package and get a reward!");
	} else if (itemId == 15291 && c.getItems().freeSlots() <= 5) {
		c.sendMessage("@red@You must have 5 free inventory slots to open.");
	}
	if (itemId == 15292 && c.getItems().freeSlots() >= 5) {
		c.getItems().deleteItem(15292,1);
		c.getItems().addItem(c.costumepackage1(),1);
		c.getItems().addItem(c.costumepackage1(),1);
		c.getItems().addItem(c.costumepackage2(),1);
		c.sendMessage("You open the package and get a reward!");
	} else if (itemId == 15292 && c.getItems().freeSlots() <= 5) {
		c.sendMessage("@red@You must have 5 free inventory slots to open.");
	}
	if (itemId == 15293 && c.getItems().freeSlots() >= 5) {
		c.getItems().deleteItem(15293,1);
		c.getItems().addItem(c.pkpackage1(),Misc.random(8000));
		c.getItems().addItem(c.pkpackage1(),Misc.random(8000));
		c.getItems().addItem(c.pkpackage2(),1);
		c.getItems().addItem(c.pkpackage3(),1);
		c.pkPoints += 10;
		c.sendMessage("You open the package and get a reward!");
	} else if (itemId == 15293 && c.getItems().freeSlots() <= 5) {
		c.sendMessage("@red@You must have 5 free inventory slots to open.");
	}
	if (itemId == 15294 && c.getItems().freeSlots() >= 5) {
		c.getItems().deleteItem(15294,1);
		c.getItems().addItem(c.miscpackage3(),1);
		c.getItems().addItem(c.miscpackage2(),Misc.random(500));
		c.getItems().addItem(c.miscpackage2(),Misc.random(500));
		c.getItems().addItem(c.miscpackage1(),1);
		c.getItems().addItem(c.miscpackage1(),1);
		c.getItems().addItem(c.miscpackage1(),1);
		c.sendMessage("You open the package and get a reward!");
	} else if (itemId == 15294 && c.getItems().freeSlots() <= 5) {
		c.sendMessage("@red@You must have 5 free inventory slots to open.");
	}
	if (itemId == 15316 && c.getItems().freeSlots() >= 3) {
		c.packagerandom = Misc.random(1);
		if (c.packagerandom == 0) {
			c.getItems().deleteItem(15316,1);
			c.getItems().addItem(c.randomreward(),1);
			c.getItems().addItem(995,Misc.random(5000000));
			c.sendMessage("You open the package and get a reward!");
		} else if (c.packagerandom == 1) {
			c.getItems().deleteItem(15316,1);
			c.getItems().addItem(c.randomreward2(),1);
			c.getItems().addItem(995,Misc.random(5000000));
			c.sendMessage("You open the package and get a reward!");
		}
	} else if (itemId == 15316 && c.getItems().freeSlots() <= 2) {
		c.sendMessage("@red@You must have 2 free inventory slots to open.");
	}
	/**END OF PACKAGES**/
		if (c.getHerblore().isUnidHerb(itemId))
			c.getHerblore().handleHerbClick(itemId);
		if (c.getFood().isFood(itemId))
			c.getFood().eat(itemId,itemSlot);
		//ScriptManager.callFunc("itemClick_"+itemId, c, itemId, itemSlot);
		if (c.getPotions().isPotion(itemId))
			c.getPotions().handlePotion(itemId,itemSlot);
		if (c.getPrayer().isBone(itemId))
			c.getPrayer().buryBone(itemId, itemSlot);
		if (itemId == 952) {
			if(c.inArea(3553, 3301, 3561, 3294)) {
				c.teleTimer = 3;
				c.newLocation = 1;
			} else if(c.inArea(3550, 3287, 3557, 3278)) {
				c.teleTimer = 3;
				c.newLocation = 2;
			} else if(c.inArea(3561, 3292, 3568, 3285)) {
				c.teleTimer = 3;
				c.newLocation = 3;
			} else if(c.inArea(3570, 3302, 3579, 3293)) {
				c.teleTimer = 3;
				c.newLocation = 4;
			} else if(c.inArea(3571, 3285, 3582, 3278)) {
				c.teleTimer = 3;
				c.newLocation = 5;
			} else if(c.inArea(3562, 3279, 3569, 3273)) {
				c.teleTimer = 3;
				c.newLocation = 6;
			}
		}
	}

}
