package server.model.players.skills;

import server.model.players.Client;
import server.Server;
import server.util.Misc;
/**
 * Slayer.java
 *
 * @author Sanity
 *
 **/
 
public class Slayer {
	
	private Client c;
		
	public Slayer(Client c) {
		this.c = c;
	}
	
	public int[] lowTasks = {1601,1649,1623,122,374,1265,142};
	public int[] lowReqs = {5, 15, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1,1};
	public int[] medTasks = {1585,1634,1617,72,1639,21,941,3376};
	public int[] medReqs = {45,50,1,1,1,1,15,1,1,1,1,1,1};
	public int[] highTasks = {1619,1624,82,53,83,84,1627,997,1615,49,1575,2783};
	public int[] highReqs = {65,75,80,85,1,1,1,50,1,1,90,1,1};
	
	public void giveTask() {
		if (c.playerLevel[18] <= 50)
			giveTask(1);
		else if (c.playerLevel[18] >= 50 && c.playerLevel[18] <= 75)
			giveTask(2);
		else if (c.playerLevel[18] > 75 && c.playerLevel[18] <= 99)
			giveTask(3);
		else
			giveTask(1);
	}
	
	public void giveTask2() {
		for (int j = 0; j < lowTasks.length; j++) {
			if (lowTasks[j] == c.slayerTask) {
				c.sendMessage("You already have an easy task... to kill " + c.taskAmount + " " + Server.npcHandler.getNpcListName(c.slayerTask) + ".");
				return;		
			}	
		}
		giveTask(1);
	}
	
	public void giveTask(int taskLevel) {
		int given = 0;
		int random = 0;
		if (taskLevel == 1) {
			random = (int)(Math.random() * (lowTasks.length - 1));
			given = lowTasks[random];
		} else if (taskLevel == 2) {
			random = (int)(Math.random() * (medTasks.length - 1));
			given = medTasks[random];		
		} else if (taskLevel == 3) {
			random = (int)(Math.random() * (highTasks.length - 1));
			given = highTasks[random];		
		}
		if (!canDoTask(taskLevel,random)) {
			giveTask(taskLevel);
			return;
		}
		c.slayerTask = given;
		c.taskAmount = Misc.random(15) + 15;
		c.sendMessage("You have been assigned to kill " + c.taskAmount + " " + Server.npcHandler.getNpcListName(given) + " as a slayer task.");
	}
	
	public boolean canDoTask(int taskLevel, int random) {
		if (taskLevel == 1) {
			return c.playerLevel[c.playerSlayer] >= lowReqs[random];		
		} else if (taskLevel == 2) {
			return c.playerLevel[c.playerSlayer] >= medReqs[random];		
		} else if (taskLevel == 3) {
			return c.playerLevel[c.playerSlayer] >= highReqs[random];			
		}
		return false;
	}
}