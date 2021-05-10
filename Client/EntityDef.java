// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
import java.io.*;
public class EntityDef {
	
	public static void dumpNpcSizes(EntityDef ed) {
		try {
		
		
		
		} catch (Exception e) {}
	
	
	}
	
	public static EntityDef forID(int i) {
		for(int j = 0; j < 20; j++)
			if(cache[j].type == (long)i)
				return cache[j];
		anInt56 = (anInt56 + 1) % 20;
		EntityDef entityDef = cache[anInt56] = new EntityDef();
		stream.currentOffset = streamIndices[i];
		entityDef.type = i;
		entityDef.readValues(stream);
		//entityDef.readValues(true, aClass30_Sub2_Sub2_60); // Collect original NPC data
	if(i == 180) {
	stream.currentOffset = streamIndices[630]; 
	entityDef.aByte68 = 3;
	entityDef.readValues(stream); // Collect original NPC data
	entityDef.actions = new String[5];
	entityDef.actions[1] = "Attack";
    int changedColors = 1; // Number of model colors changed on the NPC
    entityDef.originalModelColors = new int[changedColors];
    entityDef.modifiedModelColors = new int[changedColors];
    entityDef.originalModelColors[0] = 40; // Original model color
    entityDef.modifiedModelColors[0] = 34; // Changed model color
	entityDef.models = new int[9];
	entityDef.models[0] = 9638;//cape
	entityDef.models[1] = 216;//head 235
	entityDef.models[2] = 40207;//plate
	entityDef.models[3] = 5024;//legs
	entityDef.models[4] = 27731;//godsword
	entityDef.models[5] = 13319;//gloves 13307
	entityDef.models[6] = 10683;//boots 10683
	entityDef.models[7] = 17461;//darkness
	entityDef.models[8] = 20147;//arms
	entityDef.standAnim = 4300;
	entityDef.walkAnim = 4306;
	entityDef.anInt58 = 4306;			//anInt58 anim
	entityDef.anInt83 = 4306;			//Walk left anim 
	entityDef.anInt55 = 4306;			//walk right anim
	entityDef.name = "High Voltage Knight";
	entityDef.combatLevel = 176;
	entityDef.description = "A very powerful knight!".getBytes();
	}
	if(i == 184) {
	stream.currentOffset = streamIndices[630]; 
	entityDef.aByte68 = 3;
	entityDef.readValues(stream); // Collect original NPC data
	entityDef.actions = new String[5];
	entityDef.actions[1] = "Attack";
    int changedColors = 1; // Number of model colors changed on the NPC
    entityDef.originalModelColors = new int[changedColors];
    entityDef.modifiedModelColors = new int[changedColors];
    entityDef.originalModelColors[0] = 40; // Original model color
    entityDef.modifiedModelColors[0] = 34; // Changed model color
	entityDef.models = new int[11];
	entityDef.models[0] = 9638;//cape
	entityDef.models[1] = 43658;//sword
	entityDef.models[2] = 56038;//shield
	entityDef.models[3] = 43637;//helm
	entityDef.models[4] = 43642;//legs
	entityDef.models[5] = 43633;//plate
	entityDef.models[6] = 43608;//boots 10683
	entityDef.models[7] = 17461;//darkness
	entityDef.models[8] = 43598;//arms
	entityDef.models[9] = 43648;//gloves
	entityDef.models[10] = 9642;//ammy20174
	entityDef.standAnim = 808;
	entityDef.walkAnim = 819;
	entityDef.anInt58 = 822;			//anInt58 anim
	entityDef.anInt83 = 821;			//Walk left anim 
	entityDef.anInt55 = 820;			//walk right anim
	entityDef.anInt91 = 200; //width?
	entityDef.anInt86 = 200; //height?
	entityDef.name = "High Voltage Champion";
	entityDef.combatLevel = 318;
	entityDef.description = "A title only given to the best of the best!".getBytes();
	}
	if(i == 182) {
	stream.currentOffset = streamIndices[630]; 
	entityDef.aByte68 = 3;
	entityDef.readValues(stream); // Collect original NPC data
	entityDef.actions = new String[5];
	entityDef.actions[1] = "Attack";
    int changedColors = 1; // Number of model colors changed on the NPC
    entityDef.originalModelColors = new int[changedColors];
    entityDef.modifiedModelColors = new int[changedColors];
    entityDef.originalModelColors[0] = 40; // Original model color
    entityDef.modifiedModelColors[0] = 17; // Changed model color
	entityDef.models = new int[10];
	entityDef.models[0] = 9638;//cape
	entityDef.models[1] = 20208;//plate
	entityDef.models[2] = 20192;//legs
	entityDef.models[3] = 20189;//head 235
	entityDef.models[4] = 5197;//bow
	entityDef.models[5] = 20182;//gloves 13307
	entityDef.models[6] = 4930;//boots 10683
	entityDef.models[7] = 17461;//darkness
	entityDef.models[8] = 20204;//ammy20174
	entityDef.models[9] = 20174;//arms
	entityDef.standAnim = 808;
	entityDef.walkAnim = 819;
	entityDef.anInt58 = 822;			//anInt58 anim
	entityDef.anInt83 = 821;			//Walk left anim 
	entityDef.anInt55 = 820;			//walk right anim
	entityDef.name = "High Voltage Archer";
	entityDef.combatLevel = 153;
	entityDef.description = "A very powerful archer!".getBytes();
	}
	if(i == 183) {
	stream.currentOffset = streamIndices[630]; 
	entityDef.aByte68 = 3;
	entityDef.readValues(stream); // Collect original NPC data
	entityDef.actions = new String[5];
	entityDef.actions[1] = "Attack";
    int changedColors = 1; // Number of model colors changed on the NPC
    entityDef.originalModelColors = new int[changedColors];
    entityDef.modifiedModelColors = new int[changedColors];
    entityDef.originalModelColors[0] = 40; // Original model color
    entityDef.modifiedModelColors[0] = 17; // Changed model color
	entityDef.models = new int[11];
	entityDef.models[0] = 9638;//cape
	entityDef.models[1] = 16252;//legs
	entityDef.models[2] = 16256;//body
	entityDef.models[3] = 20137;//head 235
	entityDef.models[4] = 20170;//staff
	entityDef.models[5] = 13319;//gloves 13307
	entityDef.models[6] = 10683;//boots 10683
	entityDef.models[7] = 17461;//darkness
	entityDef.models[8] = 20152;//ammy20174
	entityDef.models[9] = 16253;//arms
	entityDef.models[10] = 20167;//shield
	entityDef.standAnim = 808;
	entityDef.walkAnim = 819;
	entityDef.anInt58 = 822;			//anInt58 anim
	entityDef.anInt83 = 821;			//Walk left anim 
	entityDef.anInt55 = 820;			//walk right anim
	entityDef.name = "High Voltage Saint";
	entityDef.combatLevel = 147;
	entityDef.description = "A very holy and powerful saint!".getBytes();
	}
		/*if(i == 941) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.models = new int[4];
			entityDef.models[0] = 17404;
			entityDef.models[1] = 17405;
			entityDef.models[2] = 17406;
			entityDef.models[3] = 17423;
			entityDef.walkAnim = 79;
			entityDef.standAnim = 90;
			entityDef.name = "Green Dragon";
			entityDef.combatLevel = 79;
			entityDef.description = "Elvarg's Cousin".getBytes();
		}
		if(i == 55) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.models = new int[3];
			entityDef.models[0] = 17408;
			entityDef.models[1] = 17425;
			entityDef.models[2] = 17418;
			entityDef.walkAnim = 79;
			entityDef.standAnim = 90;
			//entityDef.totalColors(6);
			//entityDef.changeColor(0, 910, -22766);
			//entityDef.changeColor(1, 912, -22890);
			//entityDef.changeColor(2, 1938, -23014);
			//entityDef.changeColor(3, 1814, -23138);
			//entityDef.changeColor(4, 2588, -23253);
			//entityDef.changeColor(5, 2469, -22221);
			entityDef.name = "Blue Dragon";
			entityDef.combatLevel = 111;
			entityDef.description = "Choking On...".getBytes();
		}
		if(i == 53) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.models = new int[4];
			entityDef.models[0] = 17409;
			entityDef.models[1] = 17419;
			entityDef.models[2] = 17423;
			entityDef.models[3] = 17426;
			entityDef.walkAnim = 79;
			entityDef.standAnim = 90;
			entityDef.name = "Red Dragon";
			entityDef.combatLevel = 152;
			entityDef.description = "Sunburnt".getBytes();
		}
		if(i == 54) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.models = new int[3];
			entityDef.models[0] = 17404;
			entityDef.models[1] = 17405;
			entityDef.models[2] = 17406;
			entityDef.models[3] = 17423;
			//entityDef.walkAnim = 79;
			//entityDef.standAnim = 90;
			//int changedColors = 6;
			//entityDef.changeColor(0, 2469, 33);
			//entityDef.changeColor(1, 912, 11140);
			//entityDef.changeColor(2, 1938, 10502);
			//entityDef.changeColor(3, 1814, 10378);
			//entityDef.changeColor(4, 2588, 24);
			//entityDef.changeColor(5, 910, 11138);
			entityDef.name = "Black Dragon";
			entityDef.combatLevel = 227;
			entityDef.description = "Big, Black, and you fill in the rest".getBytes();
		}*/
if (i == 1585) {
			stream.currentOffset = streamIndices[630];
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.models = new int[3];
			entityDef.models[0] = 17438;
			entityDef.models[1] = 17451;
			entityDef.models[2] = 17461;
			//entityDef.standAnim = 4662;
			//entityDef.walkAnim = 4660;
			entityDef.name = "Fire Giant";
			entityDef.combatLevel = 86;
			entityDef.description = "Firey".getBytes();
		}
if (i == 111) {
			stream.currentOffset = streamIndices[630];
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.models = new int[3];
			entityDef.models[0] = 17443;
			entityDef.models[1] = 17454;
			entityDef.models[2] = 17461;
			//entityDef.standAnim = 4670;
			//entityDef.walkAnim = 4669;
			entityDef.name = "Ice Giant";
			entityDef.combatLevel = 53;
			entityDef.description = "So Much Ice".getBytes();
		}
if (i == 112) {
			stream.currentOffset = streamIndices[630];
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.models = new int[3];
			entityDef.models[0] = 17439;
			entityDef.models[1] = 17457;
			entityDef.models[2] = 17461;
			//entityDef.standAnim = 4656;
			//entityDef.walkAnim = 4654;
			entityDef.name = "Moss Giant";
			entityDef.combatLevel = 42;
			entityDef.description = "Mossy".getBytes();
		}
if (i == 117) {
			stream.currentOffset = streamIndices[630];
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.models = new int[5];
			entityDef.models[0] = 17462;
			entityDef.models[1] = 17447;
			entityDef.models[2] = 17430;
			entityDef.models[3] = 17467;
			entityDef.models[4] = 17461;
			//entityDef.standAnim = 4650;
			//entityDef.walkAnim = 4649;
			entityDef.name = "Hill Giant";
			entityDef.combatLevel = 28;
			entityDef.description = "Hilly".getBytes();
		}
	if(i == 2558) { // NPC ID
    entityDef.actions = new String[5];
    entityDef.actions[1] = "Attack";
	entityDef.aByte68 = 5;
    entityDef.models = new int[3]; // Number of models the NPC uses
    int changedColors = 1; // Number of model colors changed on the NPC
    entityDef.originalModelColors = new int[changedColors];
    entityDef.modifiedModelColors = new int[changedColors];
    entityDef.originalModelColors[0] = 61; // Original model color
    entityDef.modifiedModelColors[0] = 926; // Changed model color
    entityDef.models[0] = 28002;
    entityDef.models[1] = 28003;
    entityDef.models[2] = 28004;
	entityDef.anInt91 = 128; //width?
	entityDef.anInt86 = 110; //height?
    entityDef.name = "Kree'arra"; // NPC name
    entityDef.combatLevel = 650; //combat level
	entityDef.walkAnim = 6973;
	entityDef.standAnim = 6972;
    entityDef.description = "The mighty lord Armadyls general kree'arra!".getBytes(); // NPC description
	}
	if(i == 2550) { // NPC ID 
    entityDef.actions = new String[5];
    entityDef.actions[1] = "Attack";
    entityDef.models = new int[2]; // Number of models the NPC uses
    int changedColors = 1; // Number of model colors changed on the NPC
    entityDef.originalModelColors = new int[changedColors];
    entityDef.modifiedModelColors = new int[changedColors];
    entityDef.originalModelColors[0] = 61; // Original model color
    entityDef.modifiedModelColors[0] = 926; // Changed model color
    entityDef.models[0] = 27789; // 27789
    entityDef.models[1] = 27785; // 27785
    entityDef.name = "General Graardor"; // NPC name
    entityDef.combatLevel = 624; //combat level
	entityDef.walkAnim = 7058;
	entityDef.standAnim = 7059;
	entityDef.anInt91 = 110; //width?
	entityDef.anInt86 = 110; //height?
	entityDef.aByte68 = 4;
    entityDef.description = "The mighty lord Bandos!".getBytes(); // NPC description
	}
	if(i == 2562) { // NPC ID
    entityDef.actions = new String[5];
    entityDef.actions[1] = "Attack";
	entityDef.aByte68 = 2;
    entityDef.models = new int[3]; // Number of models the NPC uses
    int changedColors = 1; // Number of model colors changed on the NPC
    entityDef.originalModelColors = new int[changedColors];
    entityDef.modifiedModelColors = new int[changedColors];
    entityDef.originalModelColors[0] = 61; // Original model color
    entityDef.modifiedModelColors[0] = 926; // Changed model color
    entityDef.models[0] = 28078;
    entityDef.models[1] = 28071;
    entityDef.models[2] = 28057;
    entityDef.name = "Commander Zilyana"; // NPC name
    entityDef.combatLevel = 648; //combat level
	entityDef.walkAnim = 6962;
	entityDef.standAnim = 6963;
	entityDef.anInt91 = 110;
	entityDef.anInt86 = 110;
    entityDef.description = "The mighty lord Saradomins commander Zilyana !".getBytes(); // NPC description
	}
		if(i == 2554) { 
			stream.currentOffset = streamIndices[0];
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.models = new int[5];
			int changedColors = 1;
			entityDef.originalModelColors = new int[changedColors];
			entityDef.modifiedModelColors = new int[changedColors];
			entityDef.models[0] = 9032; 
			entityDef.models[1] = 9033;
			entityDef.models[2] = 9034;
			entityDef.models[3] = 9035;
			entityDef.models[4] = 9036;
			entityDef.aByte68 = 5;
			entityDef.anInt91 = 100;			//width
			entityDef.anInt86 = 100;			//height
			entityDef.standAnim = 6943;			//Stand anim
			entityDef.walkAnim = 6942;			//Walk anim
			entityDef.anInt58 = 6942;			//anInt58 anim
			entityDef.anInt83 = 6942;			//Walk left anim 
			entityDef.anInt55 = 6942;			//walk right anim
			entityDef.name = "K'ril Tsutsaroth";
			entityDef.combatLevel = 650;
			entityDef.description = "The mighty lord Zamorakian's general K'ril Tsutsaroth!".getBytes(); 
		}
	if(i == 2551) { // NPC ID 
    entityDef.actions = new String[5];
    entityDef.actions[1] = "Attack";
    entityDef.models = new int[2]; // Number of models the NPC uses
    int changedColors = 1; // Number of model colors changed on the NPC
    entityDef.models[0] = 27794; // 27789
    entityDef.models[1] = 24425; // 27785
    entityDef.name = "Sergeant Strongstack"; // NPC name
    entityDef.combatLevel = 141; //combat level
	entityDef.walkAnim = 6152;
	entityDef.standAnim = 6153;
	entityDef.aByte68 = 1;
    entityDef.description = "A follower of Bandos.".getBytes(); // NPC description
	}
	if(i == 2552) { // NPC ID 
    entityDef.actions = new String[5];
    entityDef.actions[1] = "Attack";
    entityDef.models = new int[2]; // Number of models the NPC uses
    int changedColors = 1; // Number of model colors changed on the NPC
    entityDef.models[0] = 27793; // 27789
    entityDef.models[1] = 27792; // 27785
    entityDef.name = "Sergeant Steelwill"; // NPC name
    entityDef.combatLevel = 142; //combat level
	entityDef.walkAnim = 6152;
	entityDef.standAnim = 6153;
	entityDef.aByte68 = 1;
    entityDef.description = "A follower of Bandos.".getBytes(); // NPC description
	}
	if(i == 2553) { // NPC ID 
    entityDef.actions = new String[5];
    entityDef.actions[1] = "Attack";
    entityDef.models = new int[1]; // Number of models the NPC uses
    int changedColors = 1; // Number of model colors changed on the NPC
    entityDef.models[0] = 27795; // 27789
    entityDef.name = "Sergeant Grimspike"; // NPC name
    entityDef.combatLevel = 142; //combat level
	entityDef.walkAnim = 6152;
	entityDef.standAnim = 6153;
	entityDef.aByte68 = 1;
    entityDef.description = "A follower of Bandos.".getBytes(); // NPC description
	}

	if(i == 2555) { // NPC ID 
    entityDef.actions = new String[5];
    entityDef.actions[1] = "Attack";
    entityDef.models = new int[4]; // Number of models the NPC uses
    entityDef.models[0] = 27775; // 27789
	entityDef.models[1] = 27767;
	entityDef.models[2] = 27763;
	entityDef.models[3] = 17390;
    entityDef.name = "Tstanon Karlak"; // NPC name
    entityDef.combatLevel = 145; //combat level
	entityDef.walkAnim = 63;
	entityDef.standAnim = 66;
	entityDef.aByte68 = 3;
    entityDef.description = "A follower of Zamorak.".getBytes(); // NPC description
	}
	if(i == 2556) { // NPC ID 
    entityDef.actions = new String[5];
    entityDef.actions[1] = "Attack";
    entityDef.models = new int[4]; // Number of models the NPC uses
    entityDef.models[0] = 27776; // 27789
	entityDef.models[1] = 27769;
	entityDef.models[2] = 27766;
	entityDef.models[3] = 17390;
    entityDef.name = "Zakl'n Gritch"; // NPC name
    entityDef.combatLevel = 142; //combat level
	entityDef.walkAnim = 63;
	entityDef.standAnim = 66;
	entityDef.anInt91 = 86; //width?
	entityDef.anInt86 = 86; //height?
	entityDef.aByte68 = 2;
    entityDef.description = "A follower of Zamorak.".getBytes(); // NPC description
	}
	if(i == 2557) { // NPC ID 
    entityDef.actions = new String[5];
    entityDef.actions[1] = "Attack";
    entityDef.models = new int[4]; // Number of models the NPC uses
    entityDef.models[0] = 27772; // 27789
	entityDef.models[1] = 27771;
	entityDef.models[2] = 27774;
	entityDef.models[3] = 17390;
    entityDef.name = "Balfrug Kreeyath"; // NPC name
    entityDef.combatLevel = 151; //combat level
	entityDef.walkAnim = 63;
	entityDef.standAnim = 66;
	entityDef.aByte68 = 3;
    entityDef.description = "A follower of Zamorak.".getBytes(); // NPC description
	}
	if (i == 2559) {
	entityDef.name = "Wingman Skree";
	entityDef.aByte68 = 2;
	entityDef.standAnim = 6949;
	entityDef.walkAnim = 6950;
	entityDef.combatLevel = 143;
	entityDef.models = new int[8];
	entityDef.models[0] = 28044;
	entityDef.models[1] = 28054;
	entityDef.models[2] = 28037;
	entityDef.models[3] = 28005;
	entityDef.models[4] = 28069;
	entityDef.models[5] = 28081;
	entityDef.models[6] = 28083;
	entityDef.models[7] = 28079;
	entityDef.actions = new String[5];
	entityDef.actions[0] = null;
	entityDef.actions[1] = "Attack";
	entityDef.actions[2] = null;
	entityDef.actions[3] = null;
	entityDef.actions[4] = null;
	entityDef.description = "A follower of Armadyl.".getBytes();
	}
	if (i == 2560) {
	entityDef.name = "Flockleader Geerin";
	entityDef.aByte68 = 2;
	entityDef.standAnim = 6949;
	entityDef.walkAnim = 6950;
	entityDef.combatLevel = 149;
	entityDef.models = new int[5];
	entityDef.models[0] = 28018;
	entityDef.models[1] = 28028;
	entityDef.models[2] = 28006;
	entityDef.models[3] = 28005;
	entityDef.models[4] = 28063;
	entityDef.actions = new String[5];
	entityDef.actions[0] = null;
	entityDef.actions[1] = "Attack";
	entityDef.actions[2] = null;
	entityDef.actions[3] = null;
	entityDef.actions[4] = null;
	entityDef.description = "A follower of Armadyl.".getBytes();
	}
	if (i == 2561) {
	entityDef.name = "Flight Kilisa";
	entityDef.aByte68 = 2;
	entityDef.standAnim = 6949;
	entityDef.walkAnim = 6950;
	entityDef.combatLevel = 159;
	entityDef.models = new int[6];
	entityDef.models[0] = 28015;
	entityDef.models[1] = 28025;
	entityDef.models[2] = 28012;
	entityDef.models[3] = 28005;
	entityDef.models[4] = 28073;
	entityDef.models[5] = 28072;
	entityDef.actions = new String[5];
	entityDef.actions[0] = null;
	entityDef.actions[1] = "Attack";
	entityDef.actions[2] = null;
	entityDef.actions[3] = null;
	entityDef.actions[4] = null;
	entityDef.description = "A follower of Armadyl.".getBytes();
	}
	
	if (i == 2563) {
	entityDef.name = "Starlight";
	entityDef.aByte68 = 2;
	entityDef.standAnim = 6374;
	entityDef.walkAnim = 6373;
	entityDef.combatLevel = 149;
	entityDef.anInt91 = 110;
	entityDef.anInt86 = 110;
	entityDef.models = new int[2];
	entityDef.models[0] = 27761;
	entityDef.models[1] = 25756;
	entityDef.actions = new String[5];
	entityDef.actions[0] = null;
	entityDef.actions[1] = "Attack";
	entityDef.actions[2] = null;
	entityDef.actions[3] = null;
	entityDef.actions[4] = null;
	entityDef.description = "A follower of Saradomin.".getBytes();
	}
	if (i == 2564) {
	entityDef.name = "Growler";
	entityDef.aByte68 = 3;
	entityDef.standAnim = 7014;
	entityDef.walkAnim = 7015;
	entityDef.combatLevel = 139;
	entityDef.anInt91 = 144;
	entityDef.anInt86 = 144;
	entityDef.models = new int[1];
	entityDef.models[0] = 27718;
	entityDef.actions = new String[5];
	entityDef.actions[0] = null;
	entityDef.actions[1] = "Attack";
	entityDef.actions[2] = null;
	entityDef.actions[3] = null;
	entityDef.actions[4] = null;
	entityDef.description = "A follower of Saradomin.".getBytes();
	}
	if (i == 2565) {
	entityDef.name = "Bree";
	entityDef.aByte68 = 2;
	entityDef.standAnim = 4311;
	entityDef.walkAnim = 4310;
	entityDef.combatLevel = 146;
	entityDef.models = new int[1];
	entityDef.models[0] = 28254;
	entityDef.actions = new String[5];
	entityDef.actions[0] = null;
	entityDef.actions[1] = "Attack";
	entityDef.actions[2] = null;
	entityDef.actions[3] = null;
	entityDef.actions[4] = null;
	entityDef.description = "A follower of Saradomin.".getBytes();
	}
	if(i == 83) {
	stream.currentOffset = streamIndices[630]; 
	entityDef.aByte68 = 3;
	entityDef.readValues(stream); // Collect original NPC data
	entityDef.actions = new String[5];
	entityDef.actions[1] = "Attack";
	entityDef.models = new int[5];
	entityDef.models[0] = 17375;
	entityDef.models[1] = 17394;
	entityDef.models[2] = 17384;
	entityDef.models[3] = 17399;
	entityDef.models[4] = 17390;
	entityDef.standAnim = 66;
	entityDef.walkAnim = 63;
	entityDef.name = "Greater Demon";
	entityDef.combatLevel = 112;
	entityDef.description = "Big, Red & Incredibly Evil".getBytes();
	}
	if(i == 84) {
	stream.currentOffset = streamIndices[630]; 
	entityDef.aByte68 = 3;
	entityDef.readValues(stream); // Collect original NPC data
	entityDef.actions = new String[5];
	entityDef.actions[1] = "Attack";
	entityDef.models = new int[5];
	entityDef.models[0] = 17375;
	entityDef.models[1] = 17391;
	entityDef.models[2] = 17384;
	entityDef.models[3] = 17399;
	entityDef.models[4] = 17390;
	entityDef.originalModelColors = new int[5];
    entityDef.modifiedModelColors = new int[5];
    entityDef.originalModelColors[0] = 910; // Original model color
    entityDef.modifiedModelColors[0] = 898; // Changed model color
	entityDef.originalModelColors[1] = 912; // Original model color
    entityDef.modifiedModelColors[1] = 4; // Changed model color
	entityDef.originalModelColors[2] = 1938; // Original model color
    entityDef.modifiedModelColors[2] = 8; // Changed model color
	entityDef.originalModelColors[3] = 1814; // Original model color
    entityDef.modifiedModelColors[3] = 16; // Changed model color
	entityDef.originalModelColors[4] = 0; // Original model color
    entityDef.modifiedModelColors[4] = 5206; // Changed model color
	entityDef.standAnim = 66;
	entityDef.walkAnim = 63;
	entityDef.name = "Black Demon";
	entityDef.combatLevel = 169;
	entityDef.description = "Big, Black & extremely hard.".getBytes();
	}
	if(i == 82) {
	stream.currentOffset = streamIndices[630]; 
	entityDef.aByte68 = 3;
	entityDef.readValues(stream); // Collect original NPC data
	entityDef.actions = new String[5];
	entityDef.actions[1] = "Attack";
	entityDef.models = new int[5];
	entityDef.models[0] = 17381;
	entityDef.models[1] = 17384;
	entityDef.models[2] = 17392;
	entityDef.models[3] = 17392;
	entityDef.models[4] = 17399;
	entityDef.standAnim = 66;
	entityDef.walkAnim = 63;
	entityDef.anInt91 = 105; //width?
	entityDef.anInt86 = 105; //height?
	entityDef.name = "Lesser Demon";
	entityDef.combatLevel = 92;
	entityDef.description = "Lesser, but still Scary".getBytes();
	}
if(i == 96 || i == 97 || i == 142 || i == 1558 || i == 1559 || i == 1951 || i == 1952 || i== 1953 || i == 1954 || i == 1955 || i == 1956 ) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.description = "A wolf built for snow.".getBytes();
			entityDef.name = "Snow wolf";
			entityDef.combatLevel = 60;
			entityDef.standAnim = 6580;	//Stand anim
			entityDef.walkAnim = 6577;	//Walk anim
			entityDef.anInt58 = 6577;	//anInt58 anim
			entityDef.anInt83 = 6577;	//Walk left anim	
			entityDef.anInt55 = 6577;	//walk right anim
			entityDef.aByte68 = 2;
			entityDef.anInt86 = 135; //90
			entityDef.anInt91 = 135; //90
			entityDef.models = new int[1];
			entityDef.models[0] = 26266;
		}
		
		if(i == 141) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.description = "A wolf".getBytes();
			entityDef.name = "@bla@Black Wolf";
			entityDef.combatLevel = 83;
			entityDef.standAnim = 6580;	//Stand anim
			entityDef.walkAnim = 6577;	//Walk anim
			entityDef.anInt58 = 6577;	//anInt58 anim
			entityDef.anInt83 = 6577;	//Walk left anim	
			entityDef.anInt55 = 6577;	//walk right anim
			entityDef.aByte68 = 2;
			entityDef.anInt86 = 135; //90
			entityDef.anInt91 = 135; //90
			entityDef.models = new int[1];
			entityDef.models[0] = 26264;
		}
		
		if(i == 95 || i == 1330) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.description = "A wolf".getBytes();
			entityDef.name = "Wolf";
			entityDef.combatLevel = 60;
			entityDef.standAnim = 6580;	//Stand anim
			entityDef.walkAnim = 6577;	//Walk anim
			entityDef.anInt58 = 6577;	//anInt58 anim
			entityDef.anInt83 = 6577;	//Walk left anim	
			entityDef.anInt55 = 6577;	//walk right anim
			entityDef.aByte68 = 2;
			entityDef.anInt86 = 135; //90
			entityDef.anInt91 = 135; //90
			entityDef.models = new int[1];
			entityDef.models[0] = 26265;
		}
		if (i == 1160) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.name = "Kalphite Queen";
			entityDef.combatLevel = 333;
			entityDef.standAnim = 6236;	//Stand anim
			entityDef.walkAnim = 6236;	//Walk anim
			entityDef.anInt58 = 6236;	//anInt58 anim
			entityDef.anInt83 = 6236;	//Walk left anim	
			entityDef.anInt55 = 6236;	//walk right anim
			entityDef.aByte68 = 4;
			entityDef.anInt86 = 135; //90
			entityDef.anInt91 = 135; //90
			entityDef.models = new int[3];
			entityDef.models[0] = 24602;
			entityDef.models[1] = 24605;
			entityDef.models[2] = 24606;
		}
		/*if (i == 90) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.name = "Skeleton";
			entityDef.combatLevel = 22;
			entityDef.standAnim = 5483;	//Stand anim
			entityDef.walkAnim = 5481;	//Walk anim
			entityDef.anInt58 = 5481;	//anInt58 anim
			entityDef.anInt83 = 5481;	//Walk left anim	
			entityDef.anInt55 = 5481;	//walk right anim
			entityDef.anInt86 = 135; //90
			entityDef.anInt91 = 135; //90
			entityDef.models = new int[6];
			entityDef.models[0] = 21157;
			entityDef.models[1] = 21160;
			entityDef.models[2] = 21175;
			entityDef.models[3] = 21182;
			entityDef.models[4] = 21187;
			entityDef.models[5] = 21201;
		}*/
		if(i == 1575) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.description = "A hound of the hellish skeltal variety.".getBytes();
			entityDef.name = "Skeletal hellhound";
			entityDef.combatLevel = 97;
			entityDef.standAnim = 6580;	//Stand anim
			entityDef.walkAnim = 6577;	//Walk anim
			entityDef.anInt58 = 6577;	//anInt58 anim
			entityDef.anInt83 = 6577;	//Walk left anim	
			entityDef.anInt55 = 6577;	//walk right anim
			entityDef.aByte68 = 2;
			entityDef.anInt86 = 135; //90
			entityDef.anInt91 = 135; //90
			entityDef.models = new int[1];
			entityDef.models[0] = 26262;
		}
		
		if(i == 49) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.description = "A hound straight from hell.".getBytes();
			entityDef.name = "Hellhound";
			entityDef.combatLevel = 122;
			entityDef.standAnim = 6580;	//Stand anim
			entityDef.walkAnim = 6577;	//Walk anim
			entityDef.anInt58 = 6577;	//anInt58 anim
			entityDef.anInt83 = 6577;	//Walk left anim	
			entityDef.anInt55 = 6577;	//walk right anim
			entityDef.aByte68 = 2;
			entityDef.anInt86 = 135; //90
			entityDef.anInt91 = 135; //90
			entityDef.models = new int[1];
			entityDef.models[0] = 26258;
		}
if (i == 88) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.name = "Stag";
			entityDef.combatLevel = 14;
			entityDef.standAnim = 6374;	//Stand anim
			entityDef.walkAnim = 6373;	//Walk anim
			entityDef.anInt58 = 6373;	//anInt58 anim
			entityDef.anInt83 = 6373;	//Walk left anim	
			entityDef.anInt55 = 6373;	//walk right anim
			entityDef.aByte68 = 2;
			entityDef.anInt86 = 135; //90
			entityDef.anInt91 = 135; //90
			entityDef.models = new int[1];
			entityDef.models[0] = 25753;
		}
		
		if (i == 89) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.name = "Unicorn";
			entityDef.combatLevel = 14;
			entityDef.standAnim = 6374;	//Stand anim
			entityDef.walkAnim = 6373;	//Walk anim
			entityDef.anInt58 = 6373;	//anInt58 anim
			entityDef.anInt83 = 6373;	//Walk left anim	
			entityDef.anInt55 = 6373;	//walk right anim
			entityDef.aByte68 = 2;
			entityDef.anInt86 = 135; //90
			entityDef.anInt91 = 135; //90
			entityDef.models = new int[1];
			entityDef.models[0] = 25755;
		}
		
		if (i == 133) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.name = "Unicorn";
			entityDef.combatLevel = 14;
			entityDef.standAnim = 6374;	//Stand anim
			entityDef.walkAnim = 6373;	//Walk anim
			entityDef.anInt58 = 6373;	//anInt58 anim
			entityDef.anInt83 = 6373;	//Walk left anim	
			entityDef.anInt55 = 6373;	//walk right anim
			entityDef.aByte68 = 2;
			entityDef.anInt86 = 135; //90
			entityDef.anInt91 = 135; //90
			entityDef.models = new int[1];
			entityDef.models[0] = 25754;
		}
if(i == 105 || i == 106 || i == 1195) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.name = "Bear";
			entityDef.standAnim = 4919;
			entityDef.walkAnim = 4923;
			entityDef.anInt58 = 4923;
			entityDef.anInt83 = 4923;	
			entityDef.anInt55 = 4923;
			entityDef.models = new int[1];
			if (i == 106) {
				entityDef.models[0] = 18882;
				entityDef.combatLevel = 19;
			} else {
				entityDef.combatLevel = 21;
				entityDef.models[0] = 18884;
			}
		}
		
		if(i == 1196 || i == 1197) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.name = "Bear Cub";
			entityDef.standAnim = 4919;
			entityDef.walkAnim = 4924;
			entityDef.anInt58 = 4924;
			entityDef.anInt83 = 4924;	
			entityDef.anInt55 = 4924;
			entityDef.models = new int[1];
			entityDef.models[0] = 18883;
			if (i == 1196) {
				entityDef.combatLevel = 17;
				entityDef.anInt86 = 175; //90
				entityDef.anInt91 = 175; //90
			} else {
				entityDef.combatLevel = 15;
				entityDef.anInt86 = 155; //90
				entityDef.anInt91 = 155; //90
			}
		}
/*if(i == 74) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.name = "Zombie";
			entityDef.combatLevel = 18;
			entityDef.standAnim = 5565;	//Stand anim
			entityDef.walkAnim = 5566;	//Walk anim
			entityDef.anInt58 = 5566;	//anInt58 anim
			entityDef.anInt83 = 5566;	//Walk left anim	
			entityDef.anInt55 = 5566;	//walk right anim
			entityDef.aByte68 = 1;
			entityDef.anInt86 = 135; //90
			entityDef.anInt91 = 135; //90
			entityDef.models = new int[8];
			entityDef.models[0] = 21102;
			entityDef.models[1] = 21071;
			entityDef.models[2] = 21064;
			entityDef.models[3] = 21000;
			entityDef.models[4] = 21028;
			entityDef.models[5] = 21082;
			entityDef.models[6] = 21029;
			entityDef.models[7] = 21037;
		}
		if(i == 75) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.name = "Zombie";
			entityDef.combatLevel = 24;
			entityDef.standAnim = 5565;	//Stand anim
			entityDef.walkAnim = 5566;	//Walk anim
			entityDef.anInt58 = 5566;	//anInt58 anim
			entityDef.anInt83 = 5566;	//Walk left anim	
			entityDef.anInt55 = 5566;	//walk right anim
			entityDef.aByte68 = 1;
			entityDef.anInt86 = 135; //90
			entityDef.anInt91 = 135; //90
			entityDef.models = new int[7];
			entityDef.models[0] = 21102;
			entityDef.models[1] = 21058;
			entityDef.models[2] = 21037;
			entityDef.models[3] = 21087;
			entityDef.models[4] = 21116;
			entityDef.models[5] = 21073;
			entityDef.models[6] = 21136;
		}
		if(i == 76) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.name = "Zombie";
			entityDef.combatLevel = 25;
			entityDef.standAnim = 5565;	//Stand anim
			entityDef.walkAnim = 5566;	//Walk anim
			entityDef.anInt58 = 5566;	//anInt58 anim
			entityDef.anInt83 = 5566;	//Walk left anim	
			entityDef.anInt55 = 5566;	//walk right anim
			entityDef.aByte68 = 1;
			entityDef.anInt86 = 135; //90
			entityDef.anInt91 = 135; //90
			entityDef.models = new int[7];
			entityDef.models[0] = 21104;
			entityDef.models[1] = 21057;
			entityDef.models[2] = 21037;
			entityDef.models[3] = 21086;
			entityDef.models[4] = 21113;
			entityDef.models[5] = 21073;
			entityDef.models[6] = 21133;
		}*/
if (i == 107) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.description = "Get the scorpion repellent!".getBytes();
			entityDef.name = "Scorpion";
			entityDef.combatLevel = 14;
			entityDef.standAnim = 6252;	//Stand anim
			entityDef.walkAnim = 6253;	//Walk anim
			entityDef.anInt58 = 6253;	//anInt58 anim
			entityDef.anInt83 = 6253;	//Walk left anim	
			entityDef.anInt55 = 6253;	//walk right anim
			entityDef.aByte68 = 3;
			entityDef.anInt86 = 135; //90
			entityDef.anInt91 = 135; //90
			entityDef.models = new int[1];
			entityDef.models[0] = 24609;
		}
		if (i == 108) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.description = "Poison!".getBytes();
			entityDef.name = "Poison scorpion";
			entityDef.combatLevel = 14;
			entityDef.standAnim = 6252;	//Stand anim
			entityDef.walkAnim = 6253;	//Walk anim
			entityDef.anInt58 = 6253;	//anInt58 anim
			entityDef.anInt83 = 6253;	//Walk left anim	
			entityDef.anInt55 = 6253;	//walk right anim
			entityDef.aByte68 = 3;
			entityDef.anInt86 = 135; //90
			entityDef.anInt91 = 135; //90
			entityDef.models = new int[1];
			entityDef.models[0] = 24611;
		}
		if (i == 144 || i == 1523) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.description = "King of the scorpions.".getBytes();
			entityDef.name = "King scorpion";
			entityDef.combatLevel = 32;
			entityDef.standAnim = 6252;	//Stand anim
			entityDef.walkAnim = 6253;	//Walk anim
			entityDef.anInt58 = 6253;	//anInt58 anim
			entityDef.anInt83 = 6253;	//Walk left anim	
			entityDef.anInt55 = 6253;	//walk right anim
			entityDef.aByte68 = 4;
			entityDef.anInt86 = 135; //90
			entityDef.anInt91 = 135; //90
			entityDef.models = new int[1];
			entityDef.models[0] = 24610;
		}
/*if (i == 81) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.name = "Cow";
			entityDef.combatLevel = 2;
			entityDef.standAnim = 5852;	//Stand anim
			entityDef.walkAnim = 5848;	//Walk anim
			entityDef.anInt58 = 5848;	//anInt58 anim
			entityDef.anInt83 = 5848;	//Walk left anim	
			entityDef.anInt55 = 5848;	//walk right anim
			entityDef.aByte68 = 3;
			entityDef.anInt86 = 135; //90
			entityDef.anInt91 = 135; //90
			entityDef.models = new int[1];
			entityDef.models[0] = 23893;
		}
		
			if (i == 80) { //hope nobody liked camels
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.name = "Yak";
			entityDef.combatLevel = 23;
			entityDef.standAnim = 5852;	//Stand anim
			entityDef.walkAnim = 5848;	//Walk anim
			entityDef.anInt58 = 5848;	//anInt58 anim
			entityDef.anInt83 = 5848;	//Walk left anim	
			entityDef.anInt55 = 5848;	//walk right anim
			entityDef.aByte68 = 3;
			entityDef.anInt86 = 135; //90
			entityDef.anInt91 = 135; //90
			entityDef.models = new int[1];
			entityDef.models[0] = 23892;
		}
		if (i == 397) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.name = "Cow";
			entityDef.combatLevel = 2;
			entityDef.standAnim = 5852;	//Stand anim
			entityDef.walkAnim = 5848;	//Walk anim
			entityDef.anInt58 = 5848;	//anInt58 anim
			entityDef.anInt83 = 5848;	//Walk left anim	
			entityDef.anInt55 = 5848;	//walk right anim
			entityDef.aByte68 = 3;
			entityDef.anInt86 = 135; //90
			entityDef.anInt91 = 135; //90
			entityDef.models = new int[1];
			entityDef.models[0] = 23895;
		}
			if (i == 1691) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.name = "Undead cow";
			entityDef.combatLevel = 2;
			entityDef.standAnim = 5852;	//Stand anim
			entityDef.walkAnim = 5848;	//Walk anim
			entityDef.anInt58 = 5848;	//anInt58 anim
			entityDef.anInt83 = 5848;	//Walk left anim	
			entityDef.anInt55 = 5848;	//walk right anim
			entityDef.aByte68 = 3;
			entityDef.anInt86 = 135; //90
			entityDef.anInt91 = 135; //90
			entityDef.models = new int[1];
			entityDef.models[0] = 23894;
		}
if (i == 100) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.name = "Goblin";
			entityDef.combatLevel = 5;
			entityDef.standAnim = 6181;	//Stand anim
			entityDef.walkAnim = 6180;	//Walk anim
			entityDef.anInt58 = 6180;	//anInt58 anim
			entityDef.anInt83 = 6180;	//Walk left anim	
			entityDef.anInt55 = 6180;	//walk right anim
			entityDef.aByte68 = 2;
			entityDef.anInt86 = 135; //90
			entityDef.anInt91 = 135; //90
			entityDef.models = new int[7];
			entityDef.models[0] = 24467;
			entityDef.models[1] = 24450;
			entityDef.models[2] = 24446;
			entityDef.models[3] = 24435;
			entityDef.models[4] = 24475;
			entityDef.models[5] = 24487;
			entityDef.models[6] = 24494;
		}
		if (i == 103) {
			stream.currentOffset = streamIndices[630]; 
			entityDef.readValues(stream);
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.name = "Goblin";
			entityDef.combatLevel = 13;
			entityDef.standAnim = 6181;	//Stand anim
			entityDef.walkAnim = 6180;	//Walk anim
			entityDef.anInt58 = 6180;	//anInt58 anim
			entityDef.anInt83 = 6180;	//Walk left anim	
			entityDef.anInt55 = 6180;	//walk right anim
			entityDef.aByte68 = 2;
			entityDef.anInt86 = 135; //90
			entityDef.anInt91 = 135; //90
			entityDef.models = new int[8];
			entityDef.models[0] = 24495;
			entityDef.models[1] = 24496;
			entityDef.models[2] = 24484;
			entityDef.models[3] = 24474;
			entityDef.models[4] = 24466;
			entityDef.models[5] = 24434;
			entityDef.models[6] = 24450;
			entityDef.models[7] = 24446;
		}*/
		return entityDef;
	}
	public static int totalNPCs;
	public static void rewriteNpcs() {
		try {
			DataOutputStream dat = new DataOutputStream(new FileOutputStream("npc.dat"));
			DataOutputStream idx = new DataOutputStream(new FileOutputStream("npc.idx"));
			idx.writeShort(totalNPCs);
			dat.writeShort(totalNPCs);
			for (int j = 0; j < 6386; j++) {
				int offset1 = dat.size();
				EntityDef npc = forID(j);
				if (npc.anIntArray73 == null) {
					dat.writeByte(0);
					idx.writeShort(1);
					continue;
				}
				if (npc.models != null) {
					dat.writeByte(1);
					dat.writeByte(npc.models.length);
					for (int j2 : npc.models)
						dat.writeShort(j2);				
				}
				if (npc.name != null) {
					dat.writeByte(2);
					writeString(dat, npc.name);	
				}
				if (npc.description != null) {
					dat.writeByte(3);
					writeString(dat, npc.description.toString());					
				}
				if (npc.aByte68 != 1) {
					dat.writeByte(12);
					dat.writeByte(npc.aByte68);
				}
				if (npc.standAnim != -1) {
					dat.writeByte(13);
					dat.writeShort(npc.standAnim);				
				}
				if (npc.walkAnim != -1 || npc.anInt58 != -1 || npc.anInt55 != -1 || npc.anInt83 != -1) {
					dat.writeByte(17);
					dat.writeShort(npc.walkAnim);
					dat.writeShort(npc.anInt58);
					dat.writeShort(npc.anInt83);
					dat.writeShort(npc.anInt55);				
				}
				if (npc.actions != null) {
					for (int ii = 0; ii < 5; ii++) {
						dat.writeByte(30 + ii);
						if (npc.actions[ii] == null)
							writeString(dat, "hidden");
						else
							writeString(dat, npc.actions[ii]);
					}				
				}
				if (npc.originalModelColors != null) {
					dat.writeByte(40);
					dat.writeByte(npc.originalModelColors.length);
					for (int ii = 0; ii < npc.originalModelColors.length; ii++) {
						dat.writeShort(npc.originalModelColors[ii]);
						dat.writeShort(npc.modifiedModelColors[ii]);
					}
				
				}
				if (npc.anIntArray73 != null) {
					dat.writeByte(60);
					dat.write(npc.anIntArray73.length);
					for (int j1 : npc.anIntArray73) {
						dat.writeShort(j1);					
					}				
				}
				if (!npc.aBoolean87) {
					dat.writeByte(93);
				}
				if (npc.combatLevel != -1) {
					dat.writeByte(95);
					dat.writeShort(npc.combatLevel);				
				}
				if(npc.anInt91 != 128) {
					dat.writeByte(97);
					dat.writeShort(npc.anInt91);
				}
				if(npc.anInt86 != 128) {
					dat.writeByte(98);
					dat.writeShort(npc.anInt86);
				}
				if(npc.aBoolean93) {
					dat.writeByte(99);
				}
				if(npc.anInt85 != -1) {
					dat.writeByte(100);
					dat.writeByte(npc.anInt85);
				}
				if(npc.anInt92 != -1) {
					dat.writeByte(101);
					dat.writeByte((npc.anInt92 / 5));
				}
				if(npc.anInt75 != -1) {
					dat.writeByte(102);
					dat.writeShort(npc.anInt75);
				}
				if(npc.anInt79 != 32) {
					dat.writeByte(103);
					dat.writeShort(npc.anInt79);
				}
				if(npc.childrenIDs != null) {
					dat.writeByte(106);
					dat.writeShort(npc.anInt57);
					dat.writeShort(npc.anInt59);
					dat.writeByte(npc.childrenIDs.length - 1);
					for(int ii = 0; ii < npc.childrenIDs.length; ii++) {
						dat.writeShort(npc.childrenIDs[ii]);
					}
				}
				if(!npc.aBoolean84) {
					dat.writeByte(107);
				}
				dat.writeByte(0);
				int offset2 = dat.size();
				int writeOffset = offset2 - offset1;
				idx.writeShort(writeOffset);				
			}
			dat.close();
			idx.close();
		} catch(IOException ioe){}
	}
	
	public static void writeString(DataOutputStream dos, String toWrite) {
		try {
			dos.write(toWrite.getBytes());
			dos.writeByte(10);		
		} catch(IOException ioe){}
	
	}

	public Model method160()
	{
		if(childrenIDs != null)
		{
			EntityDef entityDef = method161();
			if(entityDef == null)
				return null;
			else
				return entityDef.method160();
		}
		if(anIntArray73 == null)
			return null;
		boolean flag1 = false;
		for(int i = 0; i < anIntArray73.length; i++)
			if(!Model.method463(anIntArray73[i]))
				flag1 = true;

		if(flag1)
			return null;
		Model aclass30_sub2_sub4_sub6s[] = new Model[anIntArray73.length];
		for(int j = 0; j < anIntArray73.length; j++)
			aclass30_sub2_sub4_sub6s[j] = Model.method462(anIntArray73[j]);

		Model model;
		if(aclass30_sub2_sub4_sub6s.length == 1)
			model = aclass30_sub2_sub4_sub6s[0];
		else
			model = new Model(aclass30_sub2_sub4_sub6s.length, aclass30_sub2_sub4_sub6s);
		if(originalModelColors != null)
		{
			for(int k = 0; k < originalModelColors.length; k++)
				model.method476(originalModelColors[k], modifiedModelColors[k]);

		}
		return model;
	}

	public EntityDef method161()
	{
		int j = -1;
		if(anInt57 != -1)
		{
			VarBit varBit = VarBit.cache[anInt57];
			int k = varBit.anInt648;
			int l = varBit.anInt649;
			int i1 = varBit.anInt650;
			int j1 = client.anIntArray1232[i1 - l];
			j = clientInstance.variousSettings[k] >> l & j1;
		} else
		if(anInt59 != -1)
			j = clientInstance.variousSettings[anInt59];
		if(j < 0 || j >= childrenIDs.length || childrenIDs[j] == -1)
			return null;
		else
			return forID(childrenIDs[j]);
	}

	public static void unpackConfig(StreamLoader streamLoader) {
		stream = new Stream(streamLoader.getDataForName("npc.dat"));
		Stream stream2 = new Stream(streamLoader.getDataForName("npc.idx"));
		totalNPCs = stream2.readUnsignedWord();
		streamIndices = new int[totalNPCs + 5000];
		int i = 2;
		for(int j = 0; j < totalNPCs; j++) {
			streamIndices[j] = i;
			i += stream2.readUnsignedWord();
		}
		cache = new EntityDef[20];
		for(int k = 0; k < 20; k++)
			cache[k] = new EntityDef();

	}

	public static void nullLoader()
	{
		mruNodes = null;
		streamIndices = null;
		cache = null;
		stream = null;
	}

	public Model method164(int j, int k, int ai[])
	{
		if(childrenIDs != null)
		{
			EntityDef entityDef = method161();
			if(entityDef == null)
				return null;
			else
				return entityDef.method164(j, k, ai);
		}
		Model model = (Model) mruNodes.insertFromCache(type);
		if(model == null)
		{
			boolean flag = false;
			for(int i1 = 0; i1 < models.length; i1++)
				if(!Model.method463(models[i1]))
					flag = true;

			if(flag)
				return null;
			Model aclass30_sub2_sub4_sub6s[] = new Model[models.length];
			for(int j1 = 0; j1 < models.length; j1++)
				aclass30_sub2_sub4_sub6s[j1] = Model.method462(models[j1]);

			if(aclass30_sub2_sub4_sub6s.length == 1)
				model = aclass30_sub2_sub4_sub6s[0];
			else
				model = new Model(aclass30_sub2_sub4_sub6s.length, aclass30_sub2_sub4_sub6s);
			if(originalModelColors != null)
			{
				for(int k1 = 0; k1 < originalModelColors.length; k1++)
					model.method476(originalModelColors[k1], modifiedModelColors[k1]);

			}
			model.method469();
			model.method479(64 + anInt85, 850 + anInt92, -30, -50, -30, true);
			mruNodes.removeFromCache(model, type);
		}
		Model model_1 = Model.aModel_1621;
		model_1.method464(model, Class36.method532(k) & Class36.method532(j));
		if(k != -1 && j != -1)
			model_1.method471(ai, j, k);
		else
		if(k != -1)
			model_1.method470(k);
		if(anInt91 != 128 || anInt86 != 128)
			model_1.method478(anInt91, anInt91, anInt86);
		model_1.method466();
		model_1.anIntArrayArray1658 = null;
		model_1.anIntArrayArray1657 = null;
		if(aByte68 == 1)
			model_1.aBoolean1659 = true;
		return model_1;
	}

	public void readValues(Stream stream)
	{
		do
		{
			int i = stream.readUnsignedByte();
			if(i == 0)
				return;
			if(i == 1)
			{
				int j = stream.readUnsignedByte();
				models = new int[j];
				for(int j1 = 0; j1 < j; j1++)
					models[j1] = stream.readUnsignedWord();

			} else
			if(i == 2)
				name = stream.readString();
			else
			if(i == 3)
				description = stream.readBytes();
			else
			if(i == 12)
				aByte68 = stream.readSignedByte();
			else
			if(i == 13)
				standAnim = stream.readUnsignedWord();
			else
			if(i == 14)
				walkAnim = stream.readUnsignedWord();
			else
			if(i == 17)
			{
				walkAnim = stream.readUnsignedWord();
				anInt58 = stream.readUnsignedWord();
				anInt83 = stream.readUnsignedWord();
				anInt55 = stream.readUnsignedWord();
			} else
			if(i >= 30 && i < 40)
			{
				if(actions == null)
					actions = new String[5];
				actions[i - 30] = stream.readString();
				if(actions[i - 30].equalsIgnoreCase("hidden"))
					actions[i - 30] = null;
			} else
			if(i == 40)
			{
				int k = stream.readUnsignedByte();
				originalModelColors = new int[k];
				modifiedModelColors = new int[k];
				for(int k1 = 0; k1 < k; k1++)
				{
					originalModelColors[k1] = stream.readUnsignedWord();
					modifiedModelColors[k1] = stream.readUnsignedWord();
				}

			} else
			if(i == 60)
			{
				int l = stream.readUnsignedByte();
				anIntArray73 = new int[l];
				for(int l1 = 0; l1 < l; l1++)
					anIntArray73[l1] = stream.readUnsignedWord();

			} else
			if(i == 90)
				stream.readUnsignedWord();
			else
			if(i == 91)
				stream.readUnsignedWord();
			else
			if(i == 92)
				stream.readUnsignedWord();
			else
			if(i == 93)
				aBoolean87 = false;
			else
			if(i == 95)
				combatLevel = stream.readUnsignedWord();
			else
			if(i == 97)
				anInt91 = stream.readUnsignedWord();
			else
			if(i == 98)
				anInt86 = stream.readUnsignedWord();
			else
			if(i == 99)
				aBoolean93 = true;
			else
			if(i == 100)
				anInt85 = stream.readSignedByte();
			else
			if(i == 101)
				anInt92 = stream.readSignedByte() * 5;
			else
			if(i == 102)
				anInt75 = stream.readUnsignedWord();
			else
			if(i == 103)
				anInt79 = stream.readUnsignedWord();
			else
			if(i == 106)
			{
				anInt57 = stream.readUnsignedWord();
				if(anInt57 == 65535)
					anInt57 = -1;
				anInt59 = stream.readUnsignedWord();
				if(anInt59 == 65535)
					anInt59 = -1;
				int i1 = stream.readUnsignedByte();
				childrenIDs = new int[i1 + 1];
				for(int i2 = 0; i2 <= i1; i2++)
				{
					childrenIDs[i2] = stream.readUnsignedWord();
					if(childrenIDs[i2] == 65535)
						childrenIDs[i2] = -1;
				}

			} else
			if(i == 107)
				aBoolean84 = false;
		} while(true);
	}

	public EntityDef()
	{
		anInt55 = -1;
		anInt57 = -1;
		anInt58 = -1;
		anInt59 = -1;
		combatLevel = -1;
		anInt64 = 1834;
		walkAnim = -1;
		aByte68 = 1;
		anInt75 = -1;
		standAnim = -1;
		type = -1L;
		anInt79 = 32;
		anInt83 = -1;
		aBoolean84 = true;
		anInt86 = 128;
		aBoolean87 = true;
		anInt91 = 128;
		aBoolean93 = false;
	}

	public int anInt55;
	public static int anInt56;
	public int anInt57;
	public int anInt58;
	public int anInt59;
	public static Stream stream;
	public int combatLevel;
	public final int anInt64;
	public String name;
	public String actions[];
	public int walkAnim;//walkAnim
	public byte aByte68;
	public int[] modifiedModelColors;//modifiedModelColors
	public static int[] streamIndices;
	public int[] anIntArray73;
	public int anInt75;
	public int[] originalModelColors;//originalModelColors
	public int standAnim;//anInt177
	public long type;
	public int anInt79;
	public static EntityDef[] cache;
	public static client clientInstance;
	public int anInt83;
	public boolean aBoolean84;
	public int anInt85;
	public int anInt86;
	public boolean aBoolean87;
	public int childrenIDs[];
	public byte description[];
	public int anInt91;
	public int anInt92;
	public boolean aBoolean93;
	public int[] models;//models
	public static MRUNodes mruNodes = new MRUNodes(30);

}
