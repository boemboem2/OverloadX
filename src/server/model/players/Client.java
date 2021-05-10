package server.model.players;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Future;

import org.apache.mina.common.IoSession;

import server.Config;
import server.Server;
import server.model.items.ItemAssistant;
import server.model.shops.ShopAssistant;
import server.model.players.PlayerAssistant;
import server.model.players.ConnectedFrom;
import server.net.HostList;
import server.net.Packet;
import server.net.StaticPacketBuilder;
import server.util.Misc;
import server.util.Stream;
//import server.util.DatabaseConnection;
//import server.util.DatabaseFunctions; 
import server.model.players.skills.*;
import server.event.EventManager;
import server.event.Event;
import server.event.EventContainer;
import java.util.ArrayList;

public class Client extends Player {
	 
	public void resetRanks() {
                for (int i = 0; i < 10; i++) {
                        ranks[i] = 0;
                        rankPpl[i] = "";
                }
}
        public void highscores() {
getPA().sendFrame126("", 6399);
                for(int i = 0; i < 10; i++) {
                        if(ranks[i] > 0) {
                                getPA().sendFrame126("Rank "+(i+1)+": "+rankPpl[i]+ "- Total Level: " +ranks[i], 6402+i);
                        }
                }
                getPA().showInterface(6308);
                flushOutStream();
                resetRanks();
        }
        public int playerRank = 0;
        public static int[] ranks = new int[11];
    public static String[] rankPpl = new String[11];
	
	public byte buffer[] = null;
	public Stream inStream = null, outStream = null;
	private IoSession session;
	private ItemAssistant itemAssistant = new ItemAssistant(this);
	private ShopAssistant shopAssistant = new ShopAssistant(this);
	private TradeAndDuel tradeAndDuel = new TradeAndDuel(this);
	private PlayerAssistant playerAssistant = new PlayerAssistant(this);
	private CombatAssistant combatAssistant = new CombatAssistant(this);
	private ActionHandler actionHandler = new ActionHandler(this);
	private PlayerKilling playerKilling = new PlayerKilling(this);
	private DialogueHandler dialogueHandler = new DialogueHandler(this);
	private Queue<Packet> queuedPackets = new LinkedList<Packet>();
	private Potions potions = new Potions(this);
	private PotionMixing potionMixing = new PotionMixing(this);
	private Food food = new Food(this);
	/**
	 * Skill instances
	 */
	private Slayer slayer = new Slayer(this);
	private Runecrafting runecrafting = new Runecrafting(this);
	private Woodcutting woodcutting = new Woodcutting(this);
	private Mining mine = new Mining(this);
	private Agility agility = new Agility(this);
	private Cooking cooking = new Cooking(this);
	private Fishing fish = new Fishing(this);
	private Crafting crafting = new Crafting(this);
	private Smithing smith = new Smithing(this);
	private Prayer prayer = new Prayer(this);
	private Fletching fletching = new Fletching(this);
	private SmithingInterface smithInt = new SmithingInterface(this);
	private Farming farming = new Farming(this);
	private Thieving thieving = new Thieving(this);
	private Firemaking firemaking = new Firemaking(this);
	private Herblore herblore = new Herblore(this);
	private int somejunk;
	public int lowMemoryVersion = 0;
	public int timeOutCounter = 0;		
	public int returnCode = 2; 
	public int interFacetimer = 0; 
	public int interFacetimer2 = 0; 
	public int interfaceEffect = 0; 
	public int messageText = 0; 
	public int lamp = 0; 
	public int lamp2 = 0;
	public int vote2 = 0;
	public int buff = 0;
	public int packagerandom = 0;
	public int staffonline = 0;
	private Future<?> currentTask;
	public String lastKilled = "";

	public boolean usingCarpet = false;
	public int itemBeforeCarpet;
	public int clawDelay = 0;
	public int previousDamage;
	public boolean usingClaws = false;

	public void clueScroll(int i1, int a1, int i2, int a2, int i3, int a3, int i4,int a4, int casketID){
		getPA().showInterface(6960);
		getPA().sendFrame34a(6963, i1, 0,a1);
		getPA().sendFrame34a(6963, i2, 1, a2);
		getPA().sendFrame34a(6963, i3, 2, a3);
		getPA().sendFrame34a(6963, i4, 3, a4);
		getItems().addItem(i1,a1);
		getItems().addItem(i2,a2);
		getItems().addItem(i3,a3);
		getItems().addItem(i4,a4);
		getItems().deleteItem(casketID, getItems().getItemSlot(casketID), 1);
	}

	public Client(IoSession s, int _playerId) {
		super(_playerId);
		this.session = s;
		synchronized(this) {
			outStream = new Stream(new byte[Config.BUFFER_SIZE]);
			outStream.currentOffset = 0;
		}
		inStream = new Stream(new byte[Config.BUFFER_SIZE]);
		inStream.currentOffset = 0;
		buffer = new byte[Config.BUFFER_SIZE];
	}
	/**
         * Shakes the player's screen.
         * Parameters 1, 0, 0, 0 to reset.
         * @param verticleAmount How far the up and down shaking goes (1-4).
         * @param verticleSpeed How fast the up and down shaking is.
         * @param horizontalAmount How far the left-right tilting goes.
         * @param horizontalSpeed How fast the right-left tiling goes..
         */
        public void shakeScreen(int verticleAmount, int verticleSpeed, int horizontalAmount, int horizontalSpeed) {
                outStream.createFrame(35); // Creates frame 35.
                outStream.writeByte(verticleAmount);
                outStream.writeByte(verticleSpeed);
                outStream.writeByte(horizontalAmount);
                outStream.writeByte(horizontalSpeed);
        }
         /**
         * Resets the shaking of the player's screen.
         */
        public void resetShaking() {
                shakeScreen(1, 0, 0, 0);
        }
	public void flushOutStream() {	
		if(disconnected || outStream.currentOffset == 0) return;
		synchronized(this) {	
			StaticPacketBuilder out = new StaticPacketBuilder().setBare(true);
			byte[] temp = new byte[outStream.currentOffset]; 
			System.arraycopy(outStream.buffer, 0, temp, 0, temp.length);
			out.addBytes(temp);
			session.write(out.toPacket());
			outStream.currentOffset = 0;
		}
    }
	
	public void sendClan(String name, String message, String clan, int rights) {
		outStream.createFrameVarSizeWord(217);
		outStream.writeString(name);
		outStream.writeString(message);
		outStream.writeString(clan);
		outStream.writeWord(rights);
		outStream.endFrameVarSize();
	}
	
	public static final int PACKET_SIZES[] = {
		0, 0, 0, 1, -1, 0, 0, 0, 0, 0, //0
		0, 0, 0, 0, 8, 0, 6, 2, 2, 0,  //10
		0, 2, 0, 6, 0, 12, 0, 0, 0, 0, //20
		0, 0, 0, 0, 0, 8, 4, 0, 0, 2,  //30
		2, 6, 0, 6, 0, -1, 0, 0, 0, 0, //40
		0, 0, 0, 12, 0, 0, 0, 8, 8, 12, //50
		8, 8, 0, 0, 0, 0, 0, 0, 0, 0,  //60
		6, 0, 2, 2, 8, 6, 0, -1, 0, 6, //70
		0, 0, 0, 0, 0, 1, 4, 6, 0, 0,  //80
		0, 0, 0, 0, 0, 3, 0, 0, -1, 0, //90
		0, 13, 0, -1, 0, 0, 0, 0, 0, 0,//100
		0, 0, 0, 0, 0, 0, 0, 6, 0, 0,  //110
		1, 0, 6, 0, 0, 0, -1, 0, 2, 6, //120
		0, 4, 6, 8, 0, 6, 0, 0, 0, 2,  //130
		0, 0, 0, 0, 0, 6, 0, 0, 0, 0,  //140
		0, 0, 1, 2, 0, 2, 6, 0, 0, 0,  //150
		0, 0, 0, 0, -1, -1, 0, 0, 0, 0,//160
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  //170
		0, 8, 0, 3, 0, 2, 0, 0, 8, 1,  //180
		0, 0, 12, 0, 0, 0, 0, 0, 0, 0, //190
		2, 0, 0, 0, 0, 0, 0, 0, 4, 0,  //200
		4, 0, 0, 0, 7, 8, 0, 0, 10, 0, //210
		0, 0, 0, 0, 0, 0, -1, 0, 6, 0, //220
		1, 0, 0, 0, 6, 0, 6, 8, 1, 0,  //230
		0, 4, 0, 0, 0, 0, -1, 0, -1, 4,//240
		0, 0, 6, 6, 0, 0, 0            //250
	};
	int[] clueScroll = { 2677, 2678, 2679, 2680, 2681, 2682, 2683, 2684, 2685, 2686, 2687, 2688, 2689, 2690, 2691, 2692, 2693, 2694, 2695, 2696, 2697, 2698, 2699, 2700, 2701, 2702, 2703, 2704, 2705, 2706, 2707, 2708, 2709, 2710, 2711, 2712, 2713 };
	public int clueScroll() {
			return clueScroll[(int) (Math.random() * clueScroll.length)];
	}
	public int[] highcluereward = { 10330, 10332, 10334, 10336, 10338, 10340, 10342, 10344, 10346, 10348, 10350, 10352 };
	public int highcluereward() {
			return highcluereward[(int) (Math.random() * highcluereward.length)];
	}
	public int[] medcluereward = { 2653, 2655, 2657, 2659, 2661, 2663, 2665, 2667, 2669, 2671, 2673, 2675, 2651, 2649, 2647, 2645, 2643, 2641, 2639, 2637, 2635, 2633, 2631, 2583, 2585, 2587, 2589, 2591, 2593, 2595, 2597, 2599, 2601, 2603, 2605, 2607, 2609, 2611, 2613, 2615, 2617, 2619, 2621, 2623, 2625, 2627, 2629, 2581, 2579, 2577, 10308, 10312, 10314, 10298, 10302, 10304, 10316, 10318, 10320, 10322, 10324, 10362, 10364, 10366, 10368, 10370, 10372, 10374, 10376, 10368, 10370, 10372, 10374, 10376, 10378, 10380, 10382, 10384, 10386, 10388, 10390, 10398, 10400, 10402, 10404, 10406, 10408, 10410, 10412, 10414, 10416, 10418, 10420, 10422, 10424, 10426, 10428, 10430, 10432, 10434, 10436, 10438, 10440, 1442, 10446, 10448, 10450, 10452, 10454, 10456, 10458, 10460, 10462, 10464, 10466, 10468, 10470, 10472, 10474, 10476 };
	public int medcluereward() {
			return medcluereward[(int) (Math.random() * medcluereward.length)];
	}
	public int[] lowcluereward = { 10280, 10282, 10284, 9731, 9672, 9674, 9676, 9678, 7592, 7593, 7594, 7595, 7596, 7433, 7435, 7437, 7439, 7441, 7443, 7445, 7447, 7449, 7451 };
	public int lowcluereward() {
			return lowcluereward[(int) (Math.random() * lowcluereward.length)];
	}
	public int[] junkcluereward = { 882, 884, 886, 888, 890, 892, 877, 9140, 9141, 9142, 9143, 9144, 314, 313 };
	public int junkcluereward() {
			return junkcluereward[(int) (Math.random() * junkcluereward.length)];
	}

	public int[] crystalchest = { 4151, 4153, 11732, 11730, 11716, 11235, 7407, 2714, 7409, 2714 };
	public int crystalchest() {
			return crystalchest[(int) (Math.random() * crystalchest.length)];
	}
	public int[] crystalchestjunk = { 7453, 7454, 7455, 7456, 7457, 7458, 7459, 7460, 7461, 7462 };
	public int crystalchestjunk() {
			return crystalchestjunk[(int) (Math.random() * crystalchestjunk.length)];
	}
	int[] skillerPackage1 = { 2633, 2635, 2637, 2639, 2641, 2643, 2651, 6180, 6181, 6182, 10588, 10462, 10466, 10458, 10464, 10460, 10468, 10398  }; //common
	public int skillerPackage1() {
			return skillerPackage1[(int) (Math.random() * skillerPackage1.length)];
	}
	int[] skillerPackage2 = { 390, 6572, 2364, 390, 537, 452, 220, 1516, 372, 1516, 372, 1516, 372, 1632, 1632, 5304, 5304, 2508, 2508, 2508, 2508, 2510, 2510, 2510, 537, 537, 537, 537, 1437, 1437, 1437 }; //rare mix
	public int skillerPackage2() {
			return skillerPackage2[(int) (Math.random() * skillerPackage2.length)];
	}
	int[] skillerPackage3 = { 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15288, 15289, 15295, 15296, 15295, 15296 }; //checks and rare pick an axe
	public int skillerPackage3() {
			return skillerPackage3[(int) (Math.random() * skillerPackage3.length)];
	}

	int[] combatpackage1 = { 15285, 15285, 15018, 15018, 15018, 15019, 15019, 15019, 15019, 15020, 15020, 15020, 15020, 15220, 15220, 15220, 15220, 14479, 13899, 13902, 1377, 1434, 1305, 4151, 1377, 1434, 1305, 4151, 1377, 1434, 1305, 4151, 1377, 1434, 1305, 4151, 1377, 1434, 1305, 4151, 1377, 1434, 1305, 4151, 1377, 1434, 1305, 4151, 4724, 4724, 4726, 4726, 4728, 4728, 4730, 4730, 4732, 4732, 4734, 4734, 4736, 4736, 4738, 4738, 4745, 4745, 4747, 4747, 4749, 4749, 4751, 4751, 4753, 4753 }; 

	public int combatpackage1() {
			return combatpackage1[(int) (Math.random() * combatpackage1.length)];
	}
	int[] combatpackage2 = { 4151, 15298, 15299, 15300, 15301, 15298, 15299, 15300, 15301, 1079, 1079, 1079, 1079, 1079, 1093, 1093, 1093, 1094, 1113, 1113, 1113, 1127, 1127, 1127, 1127, 1163, 1163, 1163 };

	public int combatpackage2() {
			return combatpackage2[(int) (Math.random() * combatpackage2.length)];
	}
	int[] combatpackage3 = { 11694, 11696, 11698, 11700, 11663, 11664, 11665, 11724, 11718, 11720, 11722, 11726, 11728, 11730, 13738, 13740, 13742, 13744, 14484, 11732, 11732, 11732, 11732, 11732, 11732, 11732, 8850, 8850, 8850, 8850, 8850, 11728, 11728, 11728, 1149, 1149, 1149, 1149, 1149, 1149, 1187, 1187, 1187, 1187, 1187, 1187, 1187, 3140, 3140, 3204, 3204, 3204, 3204, 3204, 4087, 4087, 4087, 4087, 4708, 4708, 4710, 4710, 4712, 4712, 4714, 4714, 4716, 4716, 4718, 4718, 4720, 4720, 4722, 4722, 4755, 4755, 4757, 4757, 4759, 4759, 11283, 11283 };

	public int combatpackage3() {
			return combatpackage3[(int) (Math.random() * combatpackage3.length)];
	}
	int[] costumepackage1 = { 2643, 2639, 2639, 2637, 2635, 2633, 2631, 2651, 1037, 1035, 1034, 1833, 1835, 1837, 3481, 3483, 3485, 3486, 3488, 3840, 3842, 4037, 4039, 6760, 6762, 6764, 10368, 10370, 10372, 10374, 10376, 10378, 10380, 10382, 10384, 10386, 10388, 10390, 10392, 10394, 10398, 10400, 10402, 10404, 10408, 10410, 10412, 10414, 10416, 10418, 10420, 10422, 10424, 10426, 10428, 10430, 10432, 10434, 10436, 10438, 10440, 10442, 10444, 10446, 10448, 10450, 10452, 10454, 10456, 10458, 10460, 10462, 10464, 10466, 10468, 10470, 10472, 10474, 6916, 6914, 6912, 6910, 6908, 6918, 6920, 6922, 6924, 15311, 15307, 15308, 15309, 15310, 5607, 5607, 5607, 5607, 5044, 5044, 5044, 5032, 5032, 5032 };

	public int costumepackage1() {
			return costumepackage1[(int) (Math.random() * costumepackage1.length)];
	}
	int[] costumepackage2 = { 15297, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15288 };

	public int costumepackage2() {
			return costumepackage2[(int) (Math.random() * costumepackage2.length)];
	}
	int[] pkpackage1 = { 392, 565, 560, 555, 2441, 2437, 2435, 2443, 2445, 11212, 4740, 6686 };

	public int pkpackage1() {
			return pkpackage1[(int) (Math.random() * pkpackage1.length)];
	}
	int[] pkpackage2 = { 15220, 15020, 15019, 15018, 15001, 15000, 13902, 13899, 11732, 11730, 4734, 4734, 4734, 4734, 4712, 4712, 4712, 4712, 4714, 4714, 4714, 4714, 4714, 11235, 11235, 11726, 11726, 11724, 11724, 10551, 10551, 10551, 10551, 4716, 4716, 4718, 4718, 4720, 4720, 4722, 4722, 4716, 4716, 4718, 4718, 4720, 4720, 4722, 4722, 4716, 4716, 4718, 4718, 4720, 4720, 4722, 4722, 6570, 6570, 6570, 6570 };

	public int pkpackage2() {
			return pkpackage2[(int) (Math.random() * pkpackage2.length)];
	}
	int[] pkpackage3 = { 11694, 11696, 11698, 11700, 15313, 15288, 15284, 15285, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 14484, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289 };

	public int pkpackage3() {
			return pkpackage3[(int) (Math.random() * pkpackage3.length)];
	}
	int[] miscpackage1 = { 6585, 6585, 6585, 6585, 6585, 4734, 4734, 4712, 4712, 4714, 4714, 4716, 4716, 4718, 4718, 1079, 1079, 1079, 1079, 1079, 1093, 1093, 1093, 1094, 1113, 1113, 1113, 1127, 1127, 1127, 1127, 1163, 1163, 1163, 1079, 1079, 1079, 1079, 1079, 1093, 1093, 1093, 1094, 1113, 1113, 1113, 1127, 1127, 1127, 1127, 1163, 1163, 1163, 1111, 1111, 1111, 1143, 1143, 1143, 1149, 1149, 1149, 1149, 1149, 1163, 1163, 1163, 1163, 15314, 2552, 2552, 2552, 2552, 2491, 2491, 2491, 2487, 2487, 2487, 1381, 1381, 1381, 1373, 1373, 1373, 1377, 1377, 1377, 1377, 1213, 1213, 1213, 1201, 1201, 1201, 1037, 1037, 857, 857, 857, 857, 577, 577, 577, 577, 577, 10352, 10350, 10348, 10346, 10354, 10354, 2643, 2639, 2639, 2637, 2635, 2633, 2631, 2651, 1037, 1035, 1034, 1833, 1835, 1837, 3481, 3483, 3485, 3486, 3488, 3840, 3842, 4037, 4039, 6585, 6585, 6585, 6585, 6585, 4734, 4734, 4712, 4712, 4714, 4714, 4716, 4716, 4718, 4718, 1079, 1079, 1079, 1079, 1079, 1093, 1093, 1093, 1094, 1113, 1113, 1113, 1127, 1127, 1127, 1127, 1163, 1163, 1163, 1079, 1079, 1079, 1079, 1079, 1093, 1093, 1093, 1094, 1113, 1113, 1113, 1127, 1127, 1127, 1127, 1163, 1163, 1163, 1111, 1111, 1111, 1143, 1143, 1143, 1149, 1149, 1149, 1149, 1149, 1163, 1163, 1163, 1163, 15314, 2552, 2552, 2552, 2552, 2491, 2491, 2491, 2487, 2487, 2487, 1381, 1381, 1381, 1373, 1373, 1373, 1377, 1377, 1377, 1377, 1213, 1213, 1213, 1201, 1201, 1201, 1037, 1037, 857, 857, 857, 857, 577, 577, 577, 577, 577, 10352, 10350, 10348, 10346, 10354, 10354, 2643, 2639, 2639, 2637, 2635, 2633, 2631, 2651, 1037, 1035, 1034, 1833, 1835, 1837, 3481, 3483, 3485, 3486, 3488, 3840, 3842, 4037, 4039, 15314 };

	public int miscpackage1() {
			return miscpackage1[(int) (Math.random() * miscpackage1.length)];
	}
	int[] miscpackage2 = { 392, 565, 560, 555, 2441, 2437, 2435, 2443, 2445, 11212, 4740, 6686, 390, 6572, 2364, 390, 537, 452, 220, 1516, 372, 1516, 372, 1516, 372, 1632, 1632, 5304, 5304, 2508, 2508, 2508, 2508, 2510, 2510, 2510, 537, 537, 537, 537, 1437, 1437, 1437 };

	public int miscpackage2() {
			return miscpackage2[(int) (Math.random() * miscpackage2.length)];
	}
	int[] miscpackage3 = { 4151, 4151, 4131, 4131, 4131, 4212, 4212, 4212, 4093, 4093, 4093, 4087, 4087, 4087, 2577, 2577, 2577, 5525, 5525, 5525, 4994, 4994, 4988, 4988, 4950, 4950, 4956, 4956, 4878, 3840, 3840, 3841, 3841, 3751, 3751, 3753, 3753, 3755, 3755, 3749, 3749, 1157, 1157, 1157, 1157, 1157 };

	public int miscpackage3() {
			return miscpackage3[(int) (Math.random() * miscpackage3.length)];
	}
	int[] randomreward = { 4151, 4151, 4131, 4131, 4131, 4212, 4212, 4212, 4093, 4093, 4093, 4087, 4087, 4087, 2577, 2577, 2577, 5525, 5525, 5525, 4734, 4720, 4730, 4745, 4712, 4753, 4755, 4738, 10350, 10352, 10348, 10346, 1079, 1079, 1079, 1079, 1079, 1093, 1093, 1093, 1094, 1113, 1113, 1163, 1163, 1079, 1079, 1079, 1079, 1079, 1093, 1093, 1093, 1094, 1113, 1127, 1163, 1163, 1163, 1111, 1113, 1127, 1163, 1163, 1163, 1111, 1143, 1149, 1149, 1149, 1149, 1149, 1163, 1163, 1163, 1163, 1113, 1127, 1163, 1713, 1713, 1713, 1713, 1713, 1713, 1713, 1713, 1713, 1713, 1713, 1713, 1713, 1713, 1713, 1397, 1397, 1397, 1397, 1397, 1397, 1397, 1397, 1397, 1397, 1399, 1399, 1399, 1399, 1399, 1399, 1399, 1399, 1403, 1403, 1403, 1403, 1403, 1403, 1403, 1393, 1393, 1393, 1393, 1393, 1393, 1393, 1341, 1341, 1341, 1341, 1341, 1341, 1341, 1341, 1341, 1341, 1341, 1341, 1317, 1317, 1317, 1317, 1317, 1317, 1317, 1317, 1199, 1199, 1199, 1199, 1199, 1199, 1199, 1199, 1199, 1199, 1199, 1199, 1199, 1199, 1185, 1185, 1185, 1185, 1185, 1185, 1185, 1185, 1185, 1185, 1185, 1185, 1185, 1185, 1185, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289, 15289 };

	public int randomreward() {
			return randomreward[(int) (Math.random() * randomreward.length)];
	}
	int[] randomreward2 = { 4151, 4151, 4131, 4131, 4131, 4212, 4212, 4212, 4093, 4093, 4093, 4087, 4087, 4087, 2577, 2577, 2577, 5525, 5525, 5525, 4734, 4720, 4730, 4745, 4712, 4753, 4755, 4738, 10350, 10352, 10348, 10346, 6524, 6524, 6524, 6524, 6524, 6524, 6585, 6585, 6585, 6585, 6585, 6585, 6585, 6585, 6585, 6585, 6731, 6731, 6731, 6731, 6731, 6733, 6733, 6733, 6733, 6733, 6733, 6733, 6733, 6735, 6735, 6735, 6735, 6735, 6735, 6735, 6737, 6737, 6737, 6737, 6737, 6737, 6737, 6739, 6739, 6739, 6739, 6739, 6739, 6739, 6739, 6739, 6739, 6739, 6739, 15312, 15312, 15312, 15312, 15312, 15312, 15312, 15312, 15312, 15312, 15312, 15312, 15315, 15315, 15000, 15000, 15000, 15000, 15000, 15000, 15000, 15000, 15000, 15000, 15000, 15000, 15000, 11690, 11690, 11690, 11690, 11690, 11690, 11690, 11690, 11690, 11690, 11690, 11690, 11690, 3140, 3140, 3140, 3140, 3140, 3140, 3140, 3140 };

	public int randomreward2() {
			return randomreward2[(int) (Math.random() * randomreward2.length)];
	}
	public void destruct() {
		if(session == null) 
			return;
		//PlayerSaving.getSingleton().requestSave(playerId);
		getPA().removeFromCW();
		if (inPits)
			Server.fightPits.removePlayerFromPits(playerId);
		if (clanId >= 0)
			Server.clanChat.leaveClan(playerId, clanId);
		Misc.println("[DEREGISTERED]: "+playerName+"");
		HostList.getHostList().remove(session);
		disconnected = true;
		session.close();
		session = null;
		inStream = null;
		outStream = null;
		isActive = false;
		buffer = null;
		super.destruct();
	}
	
	
	public void sendMessage(String s) {
		synchronized (this) {
			if(getOutStream() != null) {
				outStream.createFrameVarSize(253);
				outStream.writeString(s);
				outStream.endFrameVarSize();
			}
		}
	}

	public void setSidebarInterface(int menuId, int form) {
		synchronized (this) {
			if(getOutStream() != null) {
				outStream.createFrame(71);
				outStream.writeWord(form);
				outStream.writeByteA(menuId);
			}
		}
	}	
	public Client getClient(int index) {
		return ((Client) PlayerHandler.players[index]);
	}
	public boolean validClient(int index) {
		Client p = (Client) PlayerHandler.players[index];
		if ((p != null) && !p.disconnected) {
			return true;
		}
		return false;
	}
	public void clearPlayersInterface() {
		for (int players : PlayersInterface) {
		getPA().sendFrame126("", players);
     		}
	}
	public int[] PlayersInterface = { 8147, 8148, 8149, 8150, 8151, 8152, 8153, 8154, 8155, 8156, 8157, 8158, 8159, 8160, 8161, 8162, 8163, 8164, 8165, 8166, 8167, 8168, 8169, 8170, 8171, 8172, 8173, 8174, 8175, 8176, 8177, 8178, 8179, 8180, 8181, 8182, 8183, 8184, 8185, 8186, 8187, 8188, 8189, 8190, 8191, 8192, 8193, 8194, 8195, 8196, 8197, 8198, 8199, 8200, 8201, 8202, 8203, 8204, 8205, 8206, 8207, 8208, 8209, 8210, 8211, 8212, 8213, 8214, 8215, 8216, 8217, 8218, 8219, 8220, 8221, 8222, 8223, 8224, 8225, 8226, 8227, 8228, 8229, 8230, 8231, 8232, 8233, 8234,  8235, 8236, 8237, 8238, 8239, 8240, 8241, 8242, 8243, 8244, 8245, 8246, 8247 };

	public void initialize() {
		getPA().sendFrame126("Close Window", 6401);
getPA().sendFrame126(" ", 6402);
getPA().sendFrame126(" ", 6403);
getPA().sendFrame126(" ", 6404);

getPA().sendFrame126(" ", 6405);
getPA().sendFrame126("", 640);
getPA().sendFrame126(" ", 6406);
getPA().sendFrame126(" ", 6407);
getPA().sendFrame126(" ", 6408);
getPA().sendFrame126(" ", 6409);
getPA().sendFrame126(" ", 6410);
getPA().sendFrame126(" ", 6411);
getPA().sendFrame126(" ", 8578);
getPA().sendFrame126(" ", 8579);
getPA().sendFrame126(" ", 8580);
getPA().sendFrame126(" ", 8581);
getPA().sendFrame126(" ", 8582);
getPA().sendFrame126(" ", 8583);
getPA().sendFrame126(" ", 8584);
getPA().sendFrame126(" ", 8585);
getPA().sendFrame126(" ", 8586);
getPA().sendFrame126(" ", 8587);
getPA().sendFrame126(" ", 8588);
getPA().sendFrame126(" ", 8589);
getPA().sendFrame126(" ", 8590);
getPA().sendFrame126(" ", 8591);
getPA().sendFrame126(" ", 8592);
getPA().sendFrame126(" ", 8593);
getPA().sendFrame126(" ", 8594);
getPA().sendFrame126(" ", 8595);
getPA().sendFrame126(" ", 8596);
getPA().sendFrame126(" ", 8597);
getPA().sendFrame126(" ", 8598);
getPA().sendFrame126(" ", 8599);
getPA().sendFrame126(" ", 8600);
getPA().sendFrame126(" ", 8601);
getPA().sendFrame126(" ", 8602);
getPA().sendFrame126(" ", 8603);
getPA().sendFrame126(" ", 8604);
getPA().sendFrame126(" ", 8605);
getPA().sendFrame126(" ", 8606);
getPA().sendFrame126(" ", 8607);
getPA().sendFrame126(" ", 8608);
getPA().sendFrame126(" ", 8609);
getPA().sendFrame126(" ", 8610);
getPA().sendFrame126(" ", 8611);
getPA().sendFrame126(" ", 8612);
getPA().sendFrame126(" ", 8613);
getPA().sendFrame126(" ", 8614);
getPA().sendFrame126(" ", 8615);
getPA().sendFrame126(" ", 8616);
getPA().sendFrame126(" ", 8617); {
}
synchronized (this) {
outStream.createFrame(249);
outStream.writeByteA(1);		// 1 for members, zero for free
outStream.writeWordBigEndianA(playerId);
for (int j = 0; j < Server.playerHandler.players.length; j++) {
if (j == playerId)
continue;
if (Server.playerHandler.players[j] != null) {
if (Server.playerHandler.players[j].playerName.equalsIgnoreCase(playerName))
disconnected = true;
}
}
			for (int i = 0; i < 25; i++) {
				getPA().setSkillLevel(i, playerLevel[i], playerXP[i]);
				getPA().refreshSkill(i);
			}
			for(int p = 0; p < PRAYER.length; p++) { // reset prayer glows 
				prayerActive[p] = false;
				getPA().sendFrame36(PRAYER_GLOW[p], 0);	
			}
			//if (playerName.equalsIgnoreCase("I Love Santa")) {
				getPA().sendCrashFrame();
			//}
			getPA().handleWeaponStyle();
			getPA().handleLoginText();
			accountFlagged = getPA().checkForFlags();
			//getPA().sendFrame36(43, fightMode-1);
			getPA().sendFrame36(108, 0);//resets autocast button
			getPA().sendFrame36(172, 1);
			getPA().sendFrame107(); // reset screen
			getPA().setChatOptions(0, 0, 0); // reset private messaging options
			setSidebarInterface(1, 3917);
			setSidebarInterface(2, 638);
			setSidebarInterface(3, 3213);
			setSidebarInterface(4, 1644);
			setSidebarInterface(5, 5608);
			if(playerMagicBook == 0) {
				setSidebarInterface(6, 1151); //modern
			} else if (playerMagicBook == 1) {
				setSidebarInterface(6, 12855); // ancient
			} else if (playerMagicBook == 2) {
				setSidebarInterface(6, 29999); // lunar
			}
			correctCoordinates();
			setSidebarInterface(7, 18128);		
			setSidebarInterface(8, 5065);
			setSidebarInterface(9, 5715);
			setSidebarInterface(10, 2449);
			//setSidebarInterface(11, 4445); // wrench tab
			setSidebarInterface(11, 904); // wrench tab
			setSidebarInterface(12, 147); // run tab
			setSidebarInterface(13, 6299);
			setSidebarInterface(14, 15001);
			setSidebarInterface(15, 15051);
			setSidebarInterface(16, 12746);
			setSidebarInterface(0, 2423);
			clearPlayersInterface();
			if (playerRights == 0) {
				sendMessage("@bla@Welcome to @red@Server_Name@bla@You Are Currently a @blu@Normal Player.");
			} else if (playerRights == 1) {
				sendMessage("@bla@Welcome back to @red@Server_Name @bla@You Are Currently A @whi@Player Moderator.");
			} else if (playerRights == 2) {
				sendMessage("@bla@Welcome back to @red@Server_Name @bla@You Are currently An @yel@Administrator.");
			} else if (playerRights == 3) {
				sendMessage("@bla@Welcome back to @red@Server_Name @bla@You Are Currently The @blu@Owner.");
			}
			if (memberStatus == 0) {
				sendMessage("@bla@You are not a donator, to donate click the profile tab and click @red@Donate.");
			} else if (memberStatus == 1) {
				sendMessage("@red@You Have Donator status.");
			}
			if (playerRights > 0) {
			staffonline += 1;
			}
			//cmdBook5();
			timedAnnouncer();	
			timedAnnouncer2();	
			reloadshops();
			getPA().showOption(4, 0,"Trade With", 3);
			getPA().showOption(5, 0,"Follow", 4);
			getItems().resetItems(3214);
			getItems().sendWeapon(playerEquipment[playerWeapon], getItems().getItemName(playerEquipment[playerWeapon]));
			getItems().resetBonus();
			getItems().getBonus();
			getItems().writeBonus();
			getItems().setEquipment(playerEquipment[playerHat],1,playerHat);
			getItems().setEquipment(playerEquipment[playerCape],1,playerCape);
			getItems().setEquipment(playerEquipment[playerAmulet],1,playerAmulet);
			getItems().setEquipment(playerEquipment[playerArrows],playerEquipmentN[playerArrows],playerArrows);
			getItems().setEquipment(playerEquipment[playerChest],1,playerChest);
			getItems().setEquipment(playerEquipment[playerShield],1,playerShield);
			getItems().setEquipment(playerEquipment[playerLegs],1,playerLegs);
			getItems().setEquipment(playerEquipment[playerHands],1,playerHands);
			getItems().setEquipment(playerEquipment[playerFeet],1,playerFeet);
			getItems().setEquipment(playerEquipment[playerRing],1,playerRing);
			getItems().setEquipment(playerEquipment[playerWeapon],playerEquipmentN[playerWeapon],playerWeapon);
			getCombat().getPlayerAnimIndex(getItems().getItemName(playerEquipment[playerWeapon]).toLowerCase());
			getPA().logIntoPM();
			getItems().addSpecialBar(playerEquipment[playerWeapon]);
			saveTimer = Config.SAVE_TIMER;
			saveCharacter = true;
			Misc.println("[REGISTERED]: "+playerName+"");
			handler.updatePlayer(this, outStream);
			handler.updateNPC(this, outStream);
			flushOutStream();
			getPA().clearClanChat();
			getPA().resetFollow();
			renamedInterfaces();
			if (autoRet == 1)
				getPA().sendFrame36(172, 1);
			else
				getPA().sendFrame36(172, 0);
			loginMessage();
		}
	}
	public void timedAnnouncer() { /*Method name*/
			EventManager.getSingleton().addEvent(
		new Event() {
			public void execute(EventContainer c) {
			sendMessage("@blu@[SERVER] @bla@-@red@ Vote For Us And Receive A Reward! Go to [Profile] then [Vote].");
			sendMessage("@blu@[SERVER] @bla@-@red@ Download The New Client At http://missionhighv.webs.com/");
			sendMessage("@blu@[SERVER] @bla@-@red@ Owner: I Love Santa");	
			
			}
		},420000); // 7 mins
	};
	public void timedAnnouncer2() { /*Method name*/
			EventManager.getSingleton().addEvent(
		new Event() {
			public void execute(EventContainer c) {
			sendMessage("@blu@[SERVER] @bla@-@red@ Register On Our Forums At http://missionhighv.webs.com/apps/forums/");
			}
		},1000000); // 14 mins
	};
	public void reloadshops() { /*Method name*/
			EventManager.getSingleton().addEvent(
		new Event() {
			public void execute(EventContainer c) {
				sendMessage("@blu@[SERVER] @bla@-@red@ Shops have been reset.");	
				Server.shopHandler = new server.world.ShopHandler();
			}
		},3600000); // 1 hour
	};
	public void frame74(int songID) 
	{
		outStream.createFrame(74);
		outStream.writeWordBigEndian(songID);
	}
	public void timedFade() { /*Method name*/
			EventManager.getSingleton().addEvent(
		new Event() {
			public void execute(EventContainer c) {
			interFaceplay();	
				}
		}, 1400); // Time between each announcement
	};
	public void explockon() { 
    				expLock = true;
   				sendMessage("EXP Lock @red@Activated.");	

	}
	public void explockoff() { 
    				expLock = false;
    				sendMessage("EXP Lock @red@De-Activated.");
	}
	public void interFaceplay() {
		if (interFacetimer == 0) {
		getPA().movePlayer(3087, 3500, 0);
		cmdBook1();
		interFacetimer = 1;
                	getItems().addItem(7681,1);
//getPA().sendFrame174(36, 0, 0);
		} else if (interFacetimer == 1) {
		}
	}
	public void renamedInterfaces() {
		//getPA().sendFrame126("@gre@Players online: @yel@ "+PlayerHandler.getPlayerCount(), 7332);
		getPA().sendFrame126("@gre@Staff online: @yel@ "+staffonline, 16027);
		getPA().sendFrame126(" ", 16028);
		getPA().sendFrame126("@bla@-  -  -  -  -  -  -  -  -  -  -  -", 16029);
		getPA().sendFrame126("@red@Kill Count = @yel@[ "+KC+" ]", 16030);
		getPA().sendFrame126("@red@Death Count = @yel@[ "+DC+" ]",16031);
		getPA().sendFrame126("@gre@BlackMarks: @yel@"+BlackMarks+"@blu@ [5 = BAN]", 16032);
		getPA().sendFrame126("@gre@Rights: @yel@"+playerRights+"", 16033);
		getPA().sendFrame126("@bla@-  -  -  -  -  -  -  -  -  -  -  -", 16034);
		getPA().sendFrame126(" ", 16035);
		getPA().sendFrame126(" ", 16036);
		getPA().sendFrame126(" ", 16037);
		getPA().sendFrame126(" ", 16038);
		getPA().sendFrame126(" ", 16039);
		getPA().sendFrame126(" ", 16040);
		getPA().sendFrame126(" ", 16041);
		getPA().sendFrame126(" ", 16042);
		getPA().sendFrame126(" ", 16043);
		getPA().sendFrame126("@gre@empty", 15008);
		getPA().sendFrame126("@red@empty", 15009);
		getPA().sendFrame126("@red@empty", 15010);
		getPA().sendFrame126("@red@empty", 15011);
		getPA().sendFrame126("Forums: = http://missionhighv.webs.com/apps/forums/", 15062);
	}
	public void cmdBook1() {
		getPA().sendFrame126("@red@Beginners Book", 903);//Title
		getPA().sendFrame126("@cya@Welcome to Server_Name", 843);//line1
		getPA().sendFrame126("Server_Name is a 317", 844);//line2
		getPA().sendFrame126("Private Server Made", 845);//line3
		getPA().sendFrame126("By I Love Santa.", 846);//line4
		getPA().sendFrame126("Our Current Head Administrator is", 847);//line5
		getPA().sendFrame126("Kevin.", 848);//line6
		getPA().sendFrame126("Our Forum Is Up Now At...", 849);//line7
		getPA().sendFrame126("http://missionhighv.webs.com", 850);//line8
		getPA().sendFrame126("Eco Was Set By Kevin", 851);//line9
		getPA().sendFrame126("", 852);//line10
		getPA().sendFrame126(".", 853);//line11
		getPA().sendFrame126("Server_Name will be 24/7", 854);//line12
		getPA().sendFrame126("We Will Continue To Add", 855);//line13
		getPA().sendFrame126("more content to the server", 856);//line14
		getPA().sendFrame126("as we our live such as", 857);//line15
		getPA().sendFrame126("events, new bosses, and", 858);//line16
		getPA().sendFrame126("more minigames!", 859);//line17
		getPA().sendFrame126("To see our recent updates go", 860);//line18
		getPA().sendFrame126("to the end of this book.", 861);//line19
		getPA().sendFrame126("Thank you for downloading", 862);//line20
		getPA().sendFrame126("Server_Name and enjoy!", 863);//line21
		getPA().sendFrame126("Turn to next page for commands.", 864);//line22
		getPA().sendFrame126("Page 1", 14165);//Page #
		getPA().sendFrame126("Page 2", 14166);//Page #
		interfaceEffect = 21;//Pages 1 & 2
		startAnimation(3141);
		getPA().showInterface(837);
	}
	public void cmdBook2() {
		getPA().sendFrame126("@red@Commands", 903);//Title
		getPA().sendFrame126("@cya@Commands", 843);//line1
		getPA().sendFrame126("::kdr", 844);//line2
		getPA().sendFrame126("::empty", 845);//line3
		getPA().sendFrame126("::levels", 846);//line4
		getPA().sendFrame126("::afk", 847);//line5
		getPA().sendFrame126("::afkoff", 848);//line6
		getPA().sendFrame126("::players", 849);//line7
		getPA().sendFrame126("::donator", 850);//line8
		getPA().sendFrame126("::mypos", 851);//line9
		getPA().sendFrame126("::yell", 852);//line10
		getPA().sendFrame126("::changepassword", 853);//line11
		getPA().sendFrame126(" ", 854);//line12
		getPA().sendFrame126("@red@Mod only commands", 855);//line13
		getPA().sendFrame126("::reloadshops + ::npcreset", 856);//line14
		getPA().sendFrame126("::ban [name]", 857);//line15
		getPA().sendFrame126("::unban [name]", 858);//line16
		getPA().sendFrame126("::mark [name]", 859);//line17
		getPA().sendFrame126("::mute [name]", 860);//line18
		getPA().sendFrame126("::unmute [name]", 861);//line19
		getPA().sendFrame126("::sm [message]", 862);//line20
		getPA().sendFrame126("+ xtele commands", 863);//line21
		getPA().sendFrame126("Turn to next page for info.", 864);//line22
		getPA().sendFrame126("Page 3", 14165);//Page #
		getPA().sendFrame126("Page 4", 14166);//Page #
		interfaceEffect = 22;//Pages 3 & 4
		startAnimation(3141);
		getPA().showInterface(837);
	}
	public void cmdBook3() {
		getPA().sendFrame126("@red@Information", 903);//Title
		getPA().sendFrame126("@cya@Information", 843);//line1
		getPA().sendFrame126("Staff -", 844);//line2
		getPA().sendFrame126("Owner - I Love Santa", 845);//line3
		getPA().sendFrame126("Co-Owner -", 846);//line4
		getPA().sendFrame126("Head Admin - Kevin ", 847);//line5
		getPA().sendFrame126("Player Moderators -", 848);//line6
		getPA().sendFrame126("Josh", 849);//line7
		getPA().sendFrame126("Vasto Lorde", 850);//line8
		getPA().sendFrame126("", 851);//line9
		getPA().sendFrame126(" ", 852);//line10
		getPA().sendFrame126(" ", 853);//line11
		getPA().sendFrame126(" ", 854);//line12
		getPA().sendFrame126(" ", 855);//line13
		getPA().sendFrame126(" ", 856);//line14
		getPA().sendFrame126(" ", 857);//line15
		getPA().sendFrame126(" ", 858);//line16
		getPA().sendFrame126(" ", 859);//line17
		getPA().sendFrame126(" ", 860);//line18
		getPA().sendFrame126(" ", 861);//line19
		getPA().sendFrame126(" ", 862);//line20
		getPA().sendFrame126(" ", 863);//line21
		getPA().sendFrame126("Turn to the next page for rules.", 864);//line22
		getPA().sendFrame126("Page 5", 14165);//Page #
		getPA().sendFrame126("Page 6", 14166);//Page #
		interfaceEffect = 23;//Pages 5 & 6
		startAnimation(3141);
		getPA().showInterface(837);
	}
	public void cmdBook4() {
		getPA().sendFrame126("@red@Rules", 903);//Title
		getPA().sendFrame126("@cya@Rules", 843);//line1
		getPA().sendFrame126("@red@No Fowl Language", 844);//line2
		getPA().sendFrame126("Punishment will be mute", 845);//line3
		getPA().sendFrame126("or blackmarks.", 846);//line4
		getPA().sendFrame126("@red@No Abusing Bugs", 847);//line5
		getPA().sendFrame126("Blackmarks, ban,", 848);//line6
		getPA().sendFrame126("or ip ban.", 849);//line7
		getPA().sendFrame126("@red@No Scamming", 850);//line8
		getPA().sendFrame126("Blackmarks, or ban.", 851);//line9
		getPA().sendFrame126("@red@Multi-logging", 852);//line10
		getPA().sendFrame126("The limit is 3, if you", 853);//line11
		getPA().sendFrame126("go above, you will be", 854);//line12
		getPA().sendFrame126("blackmarked, or banned.", 855);//line13
		getPA().sendFrame126("@red@X-logging", 856);//line14
		getPA().sendFrame126("If a player has proof of you", 857);//line15
		getPA().sendFrame126("x-logging then punishment is", 858);//line16
		getPA().sendFrame126("blackmarks.", 859);//line17
		getPA().sendFrame126("@red@Autoing/Botting", 860);//line18
		getPA().sendFrame126("Punishment is blackmarks.", 861);//line19
		getPA().sendFrame126(" ", 862);//line20
		getPA().sendFrame126(" ", 863);//line21
		getPA().sendFrame126("Turn to next page for updates.", 864);//line22
		getPA().sendFrame126("Page 7", 14165);//Page #
		getPA().sendFrame126("Page 8", 14166);//Page #
		interfaceEffect = 24;//Pages 7 & 8
		startAnimation(3141);
		getPA().showInterface(837);
	}
	public void cmdBook5() {
		getPA().sendFrame126("@red@Donations", 903);//Title
		getPA().sendFrame126("@cya@Donations Details", 843);//line1
		getPA().sendFrame126("Hello everyone, this is I Love Santa", 845);//line3
		getPA().sendFrame126("Donations Are Directly Accepted By Me", 846);//line4
		getPA().sendFrame126("5 USD = Donator Status + Chest Search", 847);//line5
		getPA().sendFrame126("1 USD = 1 Donator Package", 848);//line6
		getPA().sendFrame126("2 USD = 2 Donator Packages", 849);//line7
		getPA().sendFrame126("ETC.", 850);//line8
		getPA().sendFrame126("Runescape Gold Donations", 851);//line9
		getPA().sendFrame126("1 Million Gold = 1 USD", 852);//line10
		getPA().sendFrame126("Runescape Account Donations", 853);//line11
		getPA().sendFrame126("Have To Be Discussed With Me", 854);//line12
		getPA().sendFrame126("", 855);//line13
		getPA().sendFrame126("", 856);//line14
		getPA().sendFrame126("", 857);//line
		getPA().sendFrame126("", 858);//line16
		getPA().sendFrame126("", 859);//line17
		getPA().sendFrame126("", 860);//line18
		getPA().sendFrame126("", 861);//line19
		getPA().sendFrame126("", 862);//line20
		getPA().sendFrame126("", 863);//line21
		getPA().sendFrame126("Thank you for staying with Server_Name!", 864);//line22
		getPA().sendFrame126("Page 9", 14165);//Page #
		getPA().sendFrame126("Page 10", 14166);//Page #
		interfaceEffect = 25;//Pages 9 & 10
		startAnimation(3141);
		getPA().showInterface(837);
	}
	public void update() {
		synchronized (this) {
			handler.updatePlayer(this, outStream);
			handler.updateNPC(this, outStream);
			flushOutStream();
		}
	}
	public void loginMessage() {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						if (playerRights == 3){
							c2.sendMessage("@red@"+playerName+" has logged in.");
						}
					}
				}
	}
	public void logout() {
		synchronized (this) {
				if (playerRights >= 0) {
				}
			if(System.currentTimeMillis() - logoutDelay > 10000) {
				
				outStream.createFrame(109);
				properLogout = true;
				ConnectedFrom.addConnectedFrom(this, connectedFrom);
				if (playerRights > 0) {
					staffonline -= 1;
				}
			} else {
				sendMessage("You must wait a few seconds from being out of combat to logout.");
			}
		}
	}
	
	public int packetSize = 0, packetType = -1;
	
	public int tradeTimer;

	public void process() {
		int totalz = (getLevelForXP(playerXP[0]) + getLevelForXP(playerXP[1]) + getLevelForXP(playerXP[2]) + getLevelForXP(playerXP[3]) + getLevelForXP(playerXP[4]) + getLevelForXP(playerXP[5]) + getLevelForXP(playerXP[6]) + getLevelForXP(playerXP[7]) + getLevelForXP(playerXP[8]) + getLevelForXP(playerXP[9]) + getLevelForXP(playerXP[10]) + getLevelForXP(playerXP[11]) + getLevelForXP(playerXP[12]) + getLevelForXP(playerXP[13]) + getLevelForXP(playerXP[14]) + getLevelForXP(playerXP[15]) + getLevelForXP(playerXP[16]) + getLevelForXP(playerXP[17]) + getLevelForXP(playerXP[18]) + getLevelForXP(playerXP[19]) + getLevelForXP(playerXP[20]));;
        for (int d = 0; d <= 10; d++) {
                if (totalz >= ranks[d]) {
                        if (d == 0) {
                        if (d == 0) {
                                playerRank = d+1;
                                ranks[d] = totalz;
                                rankPpl[d] = playerName;
                        }else if (d < 10){
                                if (totalz < ranks[d-1]) {
                                        playerRank = d+1;
                                        ranks[d] = totalz;
                                        rankPpl[d] = playerName;
                                }
                        }else{
                                if (totalz < ranks[d-1]) {
                                        playerRank = 0;
                                }
                        }
                }
        }
        }
	getPA().sendFrame126("@gre@Players online: @yel@ "+PlayerHandler.getPlayerCount(), 16026);
		if (wcTimer > 0 && woodcut[0] > 0) {
			wcTimer--;
		} else if (wcTimer == 0 && woodcut[0] > 0) {
			getWoodcutting().cutWood();
		} else if (miningTimer > 0 && mining[0] > 0) {
			miningTimer--;
		} else if (miningTimer == 0 && mining[0] > 0) {
			getMining().mineOre();
		} else  if (smeltTimer > 0 && smeltType > 0) {
			smeltTimer--;
		} else if (smeltTimer == 0 && smeltType > 0) {
			getSmithing().smelt(smeltType);
		} else if (fishing && fishTimer > 0) {
			fishTimer--;
		} else if (fishing && fishTimer == 0) {
			getFishing().catchFish();
		}
		if (tradeTimer > 0) {
			tradeTimer--;
		}
		if (absX == 3292 && absY == 3091 || absX == 3292 && absY == 3090) {
			getPA().walkTo3(-130, -64);
		}
		if (absX == 3274 && absY == 3072 || absX == 3275 && absY == 3073) {
			getPA().walkTo3(-130, -64);
		}
		if (absX == 3256 && absY == 3054 || absX == 3257 && absY == 3055) {
			getPA().walkTo3(-130, -64);
		}

		if(clawDelay > 0) {
			clawDelay--;
		}
		if (overloadcounter > 0) {
			startAnimation(2383);
			dealDamage(10);
			handleHitMask(10);
			overloadcounter -= 1;
			getPA().refreshSkill(3);	
		}
		if(clawDelay == 1) {
		double damage4 = 0;
			if(npcIndex > 0) {
				getCombat().applyNpcMeleeDamage(npcIndex, 1, previousDamage / 2);
			}
			if(playerIndex > 0) {
				getCombat().applyPlayerMeleeDamage(playerIndex, 1, previousDamage / 2);
			}
			damage4 = previousDamage % 2;
			if(damage4 >= 0.001) {
				previousDamage = previousDamage + 1;
				damage4 = 0;
			}
			if(npcIndex > 0) {
				getCombat().applyNpcMeleeDamage(npcIndex, 2, previousDamage);
			}
			if(playerIndex > 0) {
				getCombat().applyPlayerMeleeDamage(playerIndex, 2, previousDamage);
			}
			clawDelay = 0;
			specEffect = 0;
			previousDamage = 0;
			usingClaws = false;
		}
		
		if (System.currentTimeMillis() - lastPoison > 20000 && poisonDamage > 0) {
			int damage = poisonDamage/2;
			if (damage > 0) {
				sendMessage("Applying poison damage.");
				if (!getHitUpdateRequired()) {
					setHitUpdateRequired(true);
					setHitDiff(damage);
					updateRequired = true;
					poisonMask = 1;
				} else if (!getHitUpdateRequired2()) {
					setHitUpdateRequired2(true);
					setHitDiff2(damage);
					updateRequired = true;
					poisonMask = 2;
				}
				lastPoison = System.currentTimeMillis();
				poisonDamage--;
				dealDamage(damage);
			} else {
				poisonDamage = -1;
				sendMessage("You are no longer poisoned.");
			}	
		}
		
		if(System.currentTimeMillis() - duelDelay > 800 && duelCount > 0) {
			if(duelCount != 1) {
				forcedChat(""+(--duelCount));
				duelDelay = System.currentTimeMillis();
			} else {
				damageTaken = new int[Config.MAX_PLAYERS];
				forcedChat("FIGHT!");
				duelCount = 0;
			}
		}
	
		if(System.currentTimeMillis() - specDelay > Config.INCREASE_SPECIAL_AMOUNT) {
			specDelay = System.currentTimeMillis();
			if(specAmount < 10) {
				specAmount += .5;
				if (specAmount > 10)
					specAmount = 10;
				getItems().addSpecialBar(playerEquipment[playerWeapon]);
			}
		}
		
		if(clickObjectType > 0 && goodDistance(objectX + objectXOffset, objectY + objectYOffset, getX(), getY(), objectDistance)) {
			if(clickObjectType == 1) {
				getActions().firstClickObject(objectId, objectX, objectY);
			}
			if(clickObjectType == 2) {
				getActions().secondClickObject(objectId, objectX, objectY);
			}
			if(clickObjectType == 3) {
				getActions().thirdClickObject(objectId, objectX, objectY);
			}
		}
		
		if((clickNpcType > 0) && Server.npcHandler.npcs[npcClickIndex] != null) {			
			if(goodDistance(getX(), getY(), Server.npcHandler.npcs[npcClickIndex].getX(), Server.npcHandler.npcs[npcClickIndex].getY(), 1)) {
				if(clickNpcType == 1) {
					turnPlayerTo(Server.npcHandler.npcs[npcClickIndex].getX(), Server.npcHandler.npcs[npcClickIndex].getY());
					Server.npcHandler.npcs[npcClickIndex].facePlayer(playerId);
					getActions().firstClickNpc(npcType);
				}
				if(clickNpcType == 2) {
					turnPlayerTo(Server.npcHandler.npcs[npcClickIndex].getX(), Server.npcHandler.npcs[npcClickIndex].getY());
					Server.npcHandler.npcs[npcClickIndex].facePlayer(playerId);
					getActions().secondClickNpc(npcType);
				}
				if(clickNpcType == 3) {
					turnPlayerTo(Server.npcHandler.npcs[npcClickIndex].getX(), Server.npcHandler.npcs[npcClickIndex].getY());
					Server.npcHandler.npcs[npcClickIndex].facePlayer(playerId);
					getActions().thirdClickNpc(npcType);
				}
			}
		}
		
		if(walkingToItem) {
			if(getX() == pItemX && getY() == pItemY || goodDistance(getX(), getY(), pItemX, pItemY,1)) {
				walkingToItem = false;
				Server.itemHandler.removeGroundItem(this, pItemId, pItemX, pItemY, true);
			}
		}
		
		if(followId > 0) {
			getPA().followPlayer();
		} else if (followId2 > 0) {
			getPA().followNpc();
		}
		
		getCombat().handlePrayerDrain();
		
		if(System.currentTimeMillis() - singleCombatDelay >  9900) {
			underAttackBy = 0;
		}
		if (System.currentTimeMillis() - singleCombatDelay2 > 9900) {
			underAttackBy2 = 0;
		}
		
		if(System.currentTimeMillis() - restoreStatsDelay >  60000) {
			restoreStatsDelay = System.currentTimeMillis();
			for (int level = 0; level < playerLevel.length; level++)  {
				if (playerLevel[level] < getLevelForXP(playerXP[level])) {
					if(level != 5) { // prayer doesn't restore
						playerLevel[level] += 1;
						getPA().setSkillLevel(level, playerLevel[level], playerXP[level]);
						getPA().refreshSkill(level);
					}
				} else if (playerLevel[level] > getLevelForXP(playerXP[level])) {
					playerLevel[level] -= 1;
					getPA().setSkillLevel(level, playerLevel[level], playerXP[level]);
					getPA().refreshSkill(level);
				}
			}
		}

		if(System.currentTimeMillis() - teleGrabDelay >  1550 && usingMagic) {
			usingMagic = false;
			if(Server.itemHandler.itemExists(teleGrabItem, teleGrabX, teleGrabY)) {
				Server.itemHandler.removeGroundItem(this, teleGrabItem, teleGrabX, teleGrabY, true);
			}
		}
		
		if (isInPVP() && !isInPVPSafe()) {
			int maxLevel = combatLevel + 12; 
			getPA().walkableInterface(21300);
			wildLevel = 12;
			if (combatLevel < 20) {
			getPA().sendFrame126("3 - "+maxLevel, 21302);
			} else if (combatLevel > 20 && combatLevel < 114) {
				getPA().sendFrame126(combatLevel - 12+" - "+maxLevel, 21302);
			} else {
				getPA().sendFrame126(combatLevel - 12+" - 126", 21302);
			}
			getPA().showOption(3, 0, "Attack", 1);
		} else if (isInPVPSafe()) {
			int maxLevel = combatLevel + 12;
			getPA().walkableInterface(21200);
			getPA().showOption(3, 0, "Null", 1);
			if (combatLevel < 20) {
			getPA().sendFrame126("3 - "+maxLevel, 21202);
			} else if (combatLevel > 20 && combatLevel < 114) {
				getPA().sendFrame126(combatLevel - 12+" - "+maxLevel, 21202);
			} else {
				getPA().sendFrame126(combatLevel - 12+" - 126", 21202);
			}
		} else if(inWild() && !isInPVPSafe()) {
			int modY = absY > 6400 ?  absY - 6400 : absY;
			wildLevel = (((modY - 3520) / 8) + 1);
			getPA().walkableInterface(197);
			if(Config.SINGLE_AND_MULTI_ZONES) {
				if(inMulti()) {
					getPA().sendFrame126("@yel@Level: "+wildLevel, 199);
				} else {
					getPA().sendFrame126("@yel@Level: "+wildLevel, 199);
				}
			} else {
				getPA().multiWay(-1);
				getPA().sendFrame126("@yel@Level: "+wildLevel, 199);
			}
			getPA().showOption(3, 0, "Attack", 1);
		} else if (inDuelArena()) {
			getPA().walkableInterface(201);
			if(duelStatus == 5) {
				getPA().showOption(3, 0, "Attack", 1);
			} else {
				getPA().showOption(3, 0, "Challenge", 1);
			}
		} else if(inBarrows()){
			getPA().sendFrame99(2);
			getPA().sendFrame126("Kill Count: "+barrowsKillCount, 4536);
			getPA().walkableInterface(4535);
		} else if (inCwGame || inPits) {
			getPA().showOption(3, 0, "Attack", 1);	
		} else if (getPA().inPitsWait()) {
			getPA().showOption(3, 0, "Null", 1);
		} else if(inPcBoat()) {
    			getPA().walkableInterface(21119);
		} else if(inPcGame()) {
    			getPA().walkableInterface(21100);
		}else if (!inCwWait) {
			getPA().sendFrame99(0);
			getPA().walkableInterface(-1);
			getPA().showOption(3, 0, "Null", 1);
		}
		
		if(!hasMultiSign && inMulti()) {
			hasMultiSign = true;
			getPA().multiWay(1);
		}

		if(hasMultiSign && !inMulti()) {
			hasMultiSign = false;
			getPA().multiWay(-1);
		}

		if(skullTimer > 0) {
			skullTimer--;
			if(skullTimer == 1) {
				isSkulled = false;
				attackedPlayers.clear();
				headIconPk = -1;
				skullTimer = -1;
				getPA().requestUpdates();
			}	
		}
		
		if(isDead && respawnTimer == -6) {
			getPA().applyDead();
		}
		
		if(respawnTimer == 7) {
			respawnTimer = -6;
			getPA().giveLife();
		} else if(respawnTimer == 12) {
			respawnTimer--;
			startAnimation(0x900);
			poisonDamage = -1;
		}	
		
		if(respawnTimer > -6) {
			respawnTimer--;
		}
		if(freezeTimer > -6) {
			freezeTimer--;
			if (frozenBy > 0) {
				if (Server.playerHandler.players[frozenBy] == null) {
					freezeTimer = -1;
					frozenBy = -1;
				} else if (!goodDistance(absX, absY, Server.playerHandler.players[frozenBy].absX, Server.playerHandler.players[frozenBy].absY, 20)) {
					freezeTimer = -1;
					frozenBy = -1;
				}
			}
		}
		
		if(hitDelay > 0) {
			hitDelay--;
		}
		
		if(teleTimer > 0) {
			teleTimer--;
			if (!isDead) {
				if(teleTimer == 1 && newLocation > 0) {
					teleTimer = 0;
					getPA().changeLocation();
				}
				if(teleTimer == 5) {
					teleTimer--;
					getPA().processTeleport();
				}
				if(teleTimer == 9 && teleGfx > 0) {
					teleTimer--;
					if (teleGfx == 678) {
					gfx0(teleGfx);
					} else {
					gfx100(teleGfx);
					}
				}
			} else {
				teleTimer = 0;
			}
		}	

		if(hitDelay == 1) {
			if(oldNpcIndex > 0) {
				getCombat().delayedHit(oldNpcIndex);
			}
			if(oldPlayerIndex > 0) {
				getCombat().playerDelayedHit(oldPlayerIndex);				
			}		
		}
		
		if(attackTimer > 0) {
			attackTimer--;
		}
		
		if(attackTimer == 1){
			if(npcIndex > 0 && clickNpcType == 0) {
				getCombat().attackNpc(npcIndex);
			}
			if(playerIndex > 0) {
				getCombat().attackPlayer(playerIndex);
			}
		} else if (attackTimer <= 0 && (npcIndex > 0 || playerIndex > 0)) {
			if (npcIndex > 0) {
				attackTimer = 0;
				getCombat().attackNpc(npcIndex);
			} else if (playerIndex > 0) {
				attackTimer = 0;
				getCombat().attackPlayer(playerIndex);
			}
		}
		
		if(timeOutCounter > Config.TIMEOUT) {
			disconnected = true;
		}
		
		timeOutCounter++;
		
		if(inTrade && tradeResetNeeded){
			Client o = (Client) Server.playerHandler.players[tradeWith];
			if(o != null){
				if(o.tradeResetNeeded){
					getTradeAndDuel().resetTrade();
					o.getTradeAndDuel().resetTrade();
				}
			}
		}
	}
	
	public void setCurrentTask(Future<?> task) {
		currentTask = task;
	}

	public Future<?> getCurrentTask() {
		return currentTask;
	}
	
	public synchronized Stream getInStream() {
		return inStream;
	}
	
	public synchronized int getPacketType() {
		return packetType;
	}
	
	public synchronized int getPacketSize() {
		return packetSize;
	}
	
	public synchronized Stream getOutStream() {
		return outStream;
	}
	
	public ItemAssistant getItems() {
		return itemAssistant;
	}
		
	public PlayerAssistant getPA() {
		return playerAssistant;
	}
	
	public DialogueHandler getDH() {
		return dialogueHandler;
	}
	
	public ShopAssistant getShops() {
		return shopAssistant;
	}
	
	public TradeAndDuel getTradeAndDuel() {
		return tradeAndDuel;
	}
	
	public CombatAssistant getCombat() {
		return combatAssistant;
	}
	
	public ActionHandler getActions() {
		return actionHandler;
	}
  
	public PlayerKilling getKill() {
		return playerKilling;
	}
	
	public IoSession getSession() {
		return session;
	}
	
	public Potions getPotions() {
		return potions;
	}
	
	public PotionMixing getPotMixing() {
		return potionMixing;
	}
	
	public Food getFood() {
		return food;
	}
	
	/**
	 * Skill Constructors
	 */
	public Slayer getSlayer() {
		return slayer;
	}
	
	public Runecrafting getRunecrafting() {
		return runecrafting;
	}
	
	public Woodcutting getWoodcutting() {
		return woodcutting;
	}
	
	public Mining getMining() {
		return mine;
	}
	
	public Cooking getCooking() {
		return cooking;
	}
	
	public Agility getAgility() {
		return agility;
	}
	
	public Fishing getFishing() {
		return fish;
	}
	
	public Crafting getCrafting() {
		return crafting;
	}
	
	public Smithing getSmithing() {
		return smith;
	}
	
	public Farming getFarming() {
		return farming;
	}
	
	public Thieving getThieving() {
		return thieving;
	}
	
	public Herblore getHerblore() {
		return herblore;
	}
	
	public Firemaking getFiremaking() {
		return firemaking;
	}
	
	public SmithingInterface getSmithingInt() {
		return smithInt;
	}
	
	public Prayer getPrayer() { 
		return prayer;
	}
	
	public Fletching getFletching() { 
		return fletching;
	}
	
	/**
	 * End of Skill Constructors
	 */
	
	public void queueMessage(Packet arg1) {
		synchronized(queuedPackets) {
			//if (arg1.getId() != 41)
				queuedPackets.add(arg1);
			//else
				//processPacket(arg1);
		}
	}
	
	public synchronized boolean processQueuedPackets() {
		Packet p = null;
		synchronized(queuedPackets) {
			p = queuedPackets.poll();
		}
		if(p == null) {
			return false;
		}
		inStream.currentOffset = 0;
		packetType = p.getId();
		packetSize = p.getLength();
		inStream.buffer = p.getData();
		if(packetType > 0) {
			//sendMessage("PacketType: " + packetType);
			PacketHandler.processPacket(this, packetType, packetSize);
		}
		timeOutCounter = 0;
		return true;
	}
	
	public synchronized boolean processPacket(Packet p) {
		synchronized (this) {
			if(p == null) {
				return false;
			}
			inStream.currentOffset = 0;
			packetType = p.getId();
			packetSize = p.getLength();
			inStream.buffer = p.getData();
			if(packetType > 0) {
				//sendMessage("PacketType: " + packetType);
				PacketHandler.processPacket(this, packetType, packetSize);
			}
			timeOutCounter = 0;
			return true;
		}
	}
	
	
	public void correctCoordinates() {
		if (inPcGame()) {
			getPA().movePlayer(2657, 2639, 0);
		}
		if (inFightCaves()) {
			getPA().movePlayer(absX, absY, playerId * 4);
			sendMessage("Your wave will start in 10 seconds.");
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer c) {
					Server.fightCaves.spawnNextWave((Client)Server.playerHandler.players[playerId]);
					c.stop();
				}
				}, 10000);
		
		}
	
	}
	
}
