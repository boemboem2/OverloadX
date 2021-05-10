package server.model.players.skills;

import server.Config;
import server.util.Misc;
import server.model.players.Client;
/**
 * Fishing.java
 *
 * @author Ashton
 *
 **/
 
public class Fishing {
	
	private Client c;
	private int fishType;
	private int exp;
	private int req;
	private int equipmentType;
	private final int SALMON_EXP = 250;
	private final int SALMON_ID = 331;
	private final int SHRIMPS_EXP = 175;
	private final int SHRIMPS_ID = 317;
	private final int TROUT_EXP = 250;
	private final int TROUT_ID = 335;
	private final int TUNA_EXP = 325;
	private final int TUNA_ID = 359;
	private final int LOBSTER_EXP = 465;
	private final int LOBSTER_ID = 377;
	private final int MONKFISH_EXP = 525;
	private final int MONKFISH_ID = 7944;
	private final int SHARK_EXP = 600;
	private final int SHARK_ID = 383;
	private final int MANTARAY_EXP = 850;
	private final int MANTARAY_ID = 389;
	private final int ROD_ANIM = 622;
	private final int NET_ANIM = 621;
	private final int HARPOON_ANIM = 618;
	private final int CAGE_ANIM = 619;
	
	public boolean fishing = false;
	
	private final int[] REQS = {1,20,30,40,40,62,76,81};
	private final int[] FISH_TYPES = {317,331,335,359,377,7944,383,389};
	private final int[] EXP = {175,250,450,500,525,600,850,935};
	
	public Fishing(Client c) {
		this.c = c;
	}
	
	public void setupFishing(int fishType) {
		if (c.getItems().playerHasItem(getEquipment(fishType))) {
			if (c.playerLevel[c.playerFishing] >= req) {
				int slot = getSlot(fishType);
					if (slot > -1) {
						this.req = REQS[slot];
						this.fishType = FISH_TYPES[slot];
						this.equipmentType = getEquipment(fishType);
						this.exp = EXP[slot];
						c.fishing = true;
						c.fishTimer = 3 + Misc.random(2);
						if (canFishOther(fishType)) {
						c.startAnimation(FishAnim(fishType));
						}
					}
			} else {
				c.sendMessage("You need a fishing level of " + req + " to fish here.");
				resetFishing();
			}
		} else {
			c.sendMessage("You do not have the correct equipment to use this fishing spot.");
			resetFishing();
		}
	}
	
	public void catchFish() {
		if (c.getItems().playerHasItem(getEquipment(fishType))) {
			if (c.playerLevel[c.playerFishing] >= req) {
				if (c.getItems().freeSlots() > 0) {
					if (canFishOther(fishType)) {
						c.getItems().addItem(otherFishId(fishType),1);
						c.getPA().addSkillXP(otherFishXP(fishType),c.playerFishing);
						c.startAnimation(FishAnim(fishType));
						c.fishTimer = fishTimer();
						} else {
						c.getItems().addItem(fishType,1);
						c.getPA().addSkillXP(otherFishXP(fishType),c.playerFishing);
						c.startAnimation(FishAnim(fishType));
						c.fishTimer = fishTimer();
					}
					c.sendMessage("You catch a fish.");
					c.fishTimer = 2 + Misc.random(2);
				}
			} else {
				c.sendMessage("You need a fishing level of " + req + " to fish here.");
				resetFishing();
			}
		} else {
			c.sendMessage("You do not have the correct equipment to use this fishing spot.");
			resetFishing();
		}
	}
	
	
	private int getSlot(int fishType) {
		for (int j = 0; j < REQS.length; j++)
			if (FISH_TYPES[j] == fishType)
				return j;
		return -1;
	}
	
	private int getEquipment(int fish) {
		switch(fish) {
		case 335: //Trout
		case 331: //Salmon
			return 309; //Fly Fishing Rod
		case 317: //Shrimps
		case 389: //Manta Ray
			return 303; //Small fishing net
		case 377: //Lobster
			return 301; //Lobster pot
		case 383: //Shark
		case 359: //Tuna
		case 7944: //Monkfish
			return 311; //Harpoon
		}
		return -1;
	}
	
	private boolean canFishOther(int fishType) {			
		if (fishType == 335 && c.playerLevel[c.playerFishing] >= 10)
			return true;
		if (fishType == 331 && c.playerLevel[c.playerFishing] >= 20)
			return true;
		if (fishType == 317 && c.playerLevel[c.playerFishing] >= 1)
			return true;
		if (fishType == 389 && c.playerLevel[c.playerFishing] >= 81)
			return true;
		if (fishType == 377 && c.playerLevel[c.playerFishing] >= 40)
			return true;
		if (fishType == 383 && c.playerLevel[c.playerFishing] >= 76)
			return true;
		if (fishType == 359 && c.playerLevel[c.playerFishing] >= 35)
			return true;
		if (fishType == 7944 && c.playerLevel[c.playerFishing] >= 62)
			return true;
		return false;
	}
	
	private int otherFishId(int fishType) {
		if (fishType == 335)
			return TROUT_ID;
		else if (fishType == 331)
			return SALMON_ID;
		else if (fishType == 317)
			return SHRIMPS_ID;
		else if (fishType == 359)
			return TUNA_ID;
		else if (fishType == 377)
			return LOBSTER_ID;
		else if (fishType == 7944)
			return MONKFISH_ID;
		else if (fishType == 383)
			return SHARK_ID;
		else if (fishType == 389)
			return MANTARAY_ID;
			
		return -1;
	}
	
	private int FishAnim(int fishType) {
		if (fishType == 335)
			return ROD_ANIM;
		else if (fishType == 331)
			return ROD_ANIM;
		else if (fishType == 317)
			return NET_ANIM;
		else if (fishType == 359)
			return HARPOON_ANIM;
		else if (fishType == 377)
			return CAGE_ANIM;
		else if (fishType == 7944)
			return HARPOON_ANIM;
		else if (fishType == 383)
			return HARPOON_ANIM;
		else if (fishType == 389)
			return NET_ANIM;
			
		return 0;
	}

	
	private int otherFishXP(int fishType) {
		if (fishType == 335)
			return TROUT_EXP;
		else if (fishType == 331)
			return SALMON_EXP;
		else if (fishType == 317)
			return SHRIMPS_EXP;
		else if (fishType == 359)
			return TUNA_EXP;
		else if (fishType == 377)
			return LOBSTER_EXP;
		else if (fishType == 7944)
			return MONKFISH_EXP;
		else if (fishType == 383)
			return SHARK_EXP;
		else if (fishType == 389)
			return MANTARAY_EXP;	
		return 0;
	}
	
	public void resetFishing() {
		this.exp = 0;
		this.fishType = -1;
		this.equipmentType = -1;
		this.req = 0;
		c.fishTimer = -1;
		c.fishing = false;
	}
	public int fishTimer() {
		int time = Misc.random(5);
		return time;
	}
}