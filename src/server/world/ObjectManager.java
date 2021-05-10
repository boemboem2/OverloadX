package server.world;

import java.util.ArrayList;

import server.model.objects.Object;
import server.util.Misc;
import server.model.players.Client;
import server.Server;

/**
 * @author Sanity
 */

public class ObjectManager {

	public ArrayList<Object> objects = new ArrayList<Object>();
	private ArrayList<Object> toRemove = new ArrayList<Object>();
	public void process() {
		for (Object o : objects) {
			if (o.tick > 0)
				o.tick--;
			else {
				updateObject(o);
				toRemove.add(o);
			}		
		}
		for (Object o : toRemove) {
			if (isObelisk(o.newId)) {
				int index = getObeliskIndex(o.newId);
				if (activated[index]) {
					activated[index] = false;
					teleportObelisk(index);
				}
			}
			objects.remove(o);	
		}
		toRemove.clear();
	}
	
	public void removeObject(int x, int y) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				c.getPA().object(-1, x, y, 0, 10);			
			}	
		}	
	}
	
	public void updateObject(Object o) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				c.getPA().object(o.newId, o.objectX, o.objectY, o.face, o.type);			
			}	
		}	
	}
	
	public void placeObject(Object o) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				if (c.distanceToPoint(o.objectX, o.objectY) <= 60)
					c.getPA().object(o.objectId, o.objectX, o.objectY, o.face, o.type);
			}	
		}
	}
	
	public Object getObject(int x, int y, int height) {
		for (Object o : objects) {
			if (o.objectX == x && o.objectY == y && o.height == height)
				return o;
		}	
		return null;
	}
	
	public void loadObjects(Client c) {
		if (c == null)
			return;
		for (Object o : objects) {
			if (loadForPlayer(o,c))
				c.getPA().object(o.objectId, o.objectX, o.objectY, o.face, o.type);
		}
		loadCustomSpawns(c);
		if (c.distanceToPoint(2813, 3463) <= 60) {
			c.getFarming().updateHerbPatch();
		}
	}
	
	private int[][] customObjects = {{}};
	public void loadCustomSpawns(Client c) {
		c.getPA().checkObjectSpawn(2561, 3077, 3505, 2, 10);
		c.getPA().checkObjectSpawn(2560, 3082, 3505, 2, 10);
		c.getPA().checkObjectSpawn(2564, 3094, 3505, 2, 10);
		c.getPA().checkObjectSpawn(2562, 3098, 3505, 2, 10);//stalls6163
		c.getPA().checkObjectSpawn(2562, 2530, 4772, 3, 10);
		c.getPA().checkObjectSpawn(6552, 3090, 3510, 3, 10);//anicentalter
		c.getPA().checkObjectSpawn(4008, 3090, 3507, 3, 10);//anicentalter
		c.getPA().checkObjectSpawn(2538, 4719, 3505, 2, 10);//portal backhome
		c.getPA().checkObjectSpawn(409, 3089, 3495, 3, 10);//portal back home
		c.getPA().checkObjectSpawn(2465, 3084, 3496, 3, 10);//multi portal home
		c.getPA().checkObjectSpawn(2466, 3092, 3487, 3, 10);//multi portal home2
		c.getPA().checkObjectSpawn(1755, 3055, 9774, 0, 0);
		c.getPA().checkObjectSpawn(1596, 3008, 3850, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3008, 3849, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3040, 10307, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3040, 10308, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3022, 10311, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3022, 10312, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3044, 10341, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3044, 10342, 1, 0);
		c.getPA().checkObjectSpawn(2213, 3047, 9779, 1, 10);
		c.getPA().checkObjectSpawn(2213, 3080, 9502, 1, 10);
		c.getPA().checkObjectSpawn(2475, 3233, 9312, 1, 10);
		c.getPA().checkObjectSpawn(4551, 2522, 3595, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2855, 3435, 0, 10);//bank skiller
		c.getPA().checkObjectSpawn(114, 2861, 3430, 2, 10);//range skiller
		c.getPA().checkObjectSpawn(3044, 2859, 3433, 2, 10);//furn skiller
		c.getPA().checkObjectSpawn(2783, 2853, 3433, 2, 10);//anvil
		c.getPA().checkObjectSpawn(2097, 2848, 3436, 2, 10);//coal
		c.getPA().checkObjectSpawn(2098, 2849, 3436, 2, 10);//gold
		c.getPA().checkObjectSpawn(2093, 2847, 3436, 2, 10);//iron
		c.getPA().checkObjectSpawn(2094, 2845, 3436, 2, 10);//tin
		c.getPA().checkObjectSpawn(2103, 2850, 3436, 2, 10);//mithril
		c.getPA().checkObjectSpawn(2105, 2851, 3436, 2, 10);//addy
		c.getPA().checkObjectSpawn(2106, 2852, 3436, 2, 10);//rune
		c.getPA().checkObjectSpawn(2090, 2846, 3436, 2, 10);//copper
		c.getPA().checkObjectSpawn(1306, 2849, 3441, 0, 10);//magic
		c.getPA().checkObjectSpawn(1281, 2262, 3195, 2, 10);//oak
		c.getPA().checkObjectSpawn(1308, 2780, 3422, 2, 10);//willow
		c.getPA().checkObjectSpawn(1307, 2770, 3431, 2, 10);//maple
		c.getPA().checkObjectSpawn(1309, 2775, 3430, 2, 10);//yew
		c.getPA().checkObjectSpawn(1309, 2514, 4779, 2, 10);//yew don
		c.getPA().checkObjectSpawn(2106, 2529, 4781, 2, 10);//rune don
		c.getPA().checkObjectSpawn(2106, 2530, 4780, 2, 10);//rune don
		c.getPA().checkObjectSpawn(2106, 2530, 4779, 2, 10);//rune don
		c.getPA().checkObjectSpawn(2213, 2856, 3435, 0, 10);//bank skiller
		c.getPA().checkObjectSpawn(2213, 2862, 3428, 1, 10);//bank skiller
		c.getPA().checkObjectSpawn(10251, 3085, 3484, 0, 10);//portalgodwars
		c.getPA().checkObjectSpawn(104, 2519, 4773, 1, 10);//chest
		c.getPA().checkObjectSpawn(76, 2855, 3760, 1, 10);//chest
		c.getPA().checkObjectSpawn(76, 2865, 9940, 1, 10);//chest
		c.getPA().checkObjectSpawn(76, 3498, 9940, 1, 10);//chest
		c.getPA().checkObjectSpawn(76, 2869, 9786, 1, 10);//chest
		c.getPA().checkObjectSpawn(12254, 2341, 9803, 1, 10);//chest
		c.getPA().checkObjectSpawn(2113, 2864, 3432, 3, 10);//agil ladder
		c.getPA().checkObjectSpawn(2213, 2804, 3463, 1, 10);//bank skiller
		c.getPA().checkObjectSpawn(8151, 2239, 3196, 2, 10);//farmpatch
		c.getPA().checkObjectSpawn(2213, 2918, 4843, 1, 10);//bank es
		c.getPA().checkObjectSpawn(4157, 2334, 9801, 1, 10);//bank es
		c.getPA().checkObjectSpawn(2213, 2787, 3426, 1, 10);//bank skiller
		c.getPA().checkObjectSpawn(2213, 3217, 3219, 1, 10);//bank skiller
		c.getPA().checkObjectSpawn(377, 3097, 3499, 2, 10);//crystal chest @ home
		c.getPA().checkObjectSpawn(1306, 2778, 3432, 0, 10);//Magic 
		c.getPA().checkObjectSpawn(2213, 2531, 4777, 1, 10);//bank donor
		c.getPA().checkObjectSpawn(2213, 2531, 4776, 1, 10);//bank donor
		c.getPA().checkObjectSpawn(2213, 2932, 4679, 1, 10);//bank of Veterans
		c.getPA().checkObjectSpawn(2213, 2892, 3514, 1, 10);//bank of ChillZone
		c.getPA().checkObjectSpawn(377, 2893, 3507, 0, 10);//crystal chest @ Chill Zone
		c.getPA().checkObjectSpawn(7319, 3083, 3486, 0, 10);//Portal for KBD
		c.getPA().checkObjectSpawn(2213, 2208, 4834, 0, 10);//bank at death altar
		if (c.heightLevel == 0)
			c.getPA().checkObjectSpawn(2492, 2911, 3614, 1, 10);
		else
			c.getPA().checkObjectSpawn(-1, 2911, 3614, 1, 10);
			c.getPA().checkObjectSpawn(-1, 3084, 3502, 1, 10);
			c.getPA().checkObjectSpawn(-1, 3088, 3509, 1, 10);
			c.getPA().checkObjectSpawn(-1, 2523, 4777, 1, 10);
	}
	
	public final int IN_USE_ID = 14825;
	public boolean isObelisk(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return true;
		}
		return false;
	}
	public int[] obeliskIds = {14829,14830,14827,14828,14826,14831};
	public int[][] obeliskCoords = {{3154,3618},{3225,3665},{3033,3730},{3104,3792},{2978,3864},{3305,3914}};
	public boolean[] activated = {false,false,false,false,false,false};
	
	public void startObelisk(int obeliskId) {
		int index = getObeliskIndex(obeliskId);
		if (index >= 0) {
			if (!activated[index]) {
				activated[index] = true;
				addObject(new Object(14825, obeliskCoords[index][0], obeliskCoords[index][1], 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4, obeliskCoords[index][1], 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0], obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4, obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId,16));
			}
		}	
	}
	
	public int getObeliskIndex(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return j;
		}
		return -1;
	}
	
	public void teleportObelisk(int port) {
		int random = Misc.random(5);
		while (random == port) {
			random = Misc.random(5);
		}
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				int xOffset = c.absX - obeliskCoords[port][0];
				int yOffset = c.absY - obeliskCoords[port][1];
				if (c.goodDistance(c.getX(), c.getY(), obeliskCoords[port][0] + 2, obeliskCoords[port][1] + 2, 1)) {
					c.getPA().startTeleport2(obeliskCoords[random][0] + xOffset, obeliskCoords[random][1] + yOffset, 0);
				}
			}		
		}
	}
	
	public boolean loadForPlayer(Object o, Client c) {
		if (o == null || c == null)
			return false;
		return c.distanceToPoint(o.objectX, o.objectY) <= 60 && c.heightLevel == o.height;
	}
	
	public void addObject(Object o) {
		if (getObject(o.objectX, o.objectY, o.height) == null) {
			objects.add(o);
			placeObject(o);
		}	
	}




}