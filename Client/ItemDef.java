import java.io.*;

public final class ItemDef {

	public static void nullLoader() {
		mruNodes2 = null;
		mruNodes1 = null;
		streamIndices = null;
		cache = null;
		stream = null;
	}

	public boolean method192(int j) {
		int k = anInt175;
		int l = anInt166;
		if(j == 1)
		{
			k = anInt197;
			l = anInt173;
		}
		if(k == -1)
			return true;
		boolean flag = true;
		if(!Model.method463(k))
			flag = false;
		if(l != -1 && !Model.method463(l))
			flag = false;
		return flag;
	}
	
	/*public static void dumpStacks() {
		try {
			FileOutputStream out = new FileOutputStream(new File("stackable.dat"));
			for (int j = 0; j < 12000; j++) {
				ItemDef item = ItemDef.forID(j);
				out.write(item.stackable ? 1 : 0);
			}
			out.write(-1);
			out.close();
		} catch (IOException ioe) {ioe.printStackTrace();}	
	}
	
	public static void dumpNotes() {
		try {
			FileOutputStream out = new FileOutputStream(new File("notes.dat"));
			for (int j = 0; j < 12000; j++) {
				ItemDef item = ItemDef.forID(j);
				out.write(item.certTemplateID != -1 ? 0 : 1);
			}
			out.write(-1);
			out.close();
		} catch (IOException ioe) {ioe.printStackTrace();}
	
	
	}*/
	
	public static void dumpPrices() {
		try {
			//FileOutputStream out = new FileOutputStream(new File("prices.dat"));
			FileWriter fw = new FileWriter("prices.txt");
			for (int j = 0; j < 1000; j++) {
				ItemDef item = ItemDef.forID(j);
				fw.write(item.id + " " + item.value + "\r\n");
			}
			fw.close();
		} catch (IOException ioe) {ioe.printStackTrace();}
	
	
	}
	
	public static void unpackConfig(StreamLoader streamLoader)	 {
		stream = new Stream(streamLoader.getDataForName("obj.dat"));
		Stream stream = new Stream(streamLoader.getDataForName("obj.idx"));
		totalItems = stream.readUnsignedWord();
		streamIndices = new int[totalItems + 400000];
		int i = 2;
		for(int j = 0; j < totalItems; j++) {
			streamIndices[j] = i;
			i += stream.readUnsignedWord();
		}
		cache = new ItemDef[10];
		for(int k = 0; k < 10; k++)
			cache[k] = new ItemDef();
		//dumpStacks();
		//dumpNotes();
		dumpPrices();
	}

	public Model method194(int j) {
		int k = anInt175;
		int l = anInt166;
		if(j == 1) {
			k = anInt197;
			l = anInt173;
		}
		if(k == -1)
			return null;
		Model model = Model.method462(k);
		if(l != -1) {
			Model model_1 = Model.method462(l);
			Model aclass30_sub2_sub4_sub6s[] = {
					model, model_1
			};
			model = new Model(2, aclass30_sub2_sub4_sub6s);
		}
	   if (originalModelColors != null) {
			for (int i1 = 0; i1 < originalModelColors.length; i1++)
				model.method476(originalModelColors[i1], modifiedModelColors[i1]);

		}
		return model;
	}

	public boolean method195(int j) {
		int k = maleEquip1;
		int l = maleEquip2;
		int i1 = anInt185;
		if(j == 1) {
			k = femaleEquip1;
			l = femaleEquip2;
			i1 = anInt162;
		}
		if(k == -1)
			return true;
		boolean flag = true;
		if(!Model.method463(k))
			flag = false;
		if(l != -1 && !Model.method463(l))
			flag = false;
		if(i1 != -1 && !Model.method463(i1))
			flag = false;
		return flag;
	}

	public Model method196(int i) {
		int j = maleEquip1;
		int k = maleEquip2;
		int l = anInt185;
		if(i == 1) {
			j = femaleEquip1;
			k = femaleEquip2;
			l = anInt162;
		}
		if(j == -1)
			return null;
		Model model = Model.method462(j);
		if(k != -1)
			if(l != -1) {
				Model model_1 = Model.method462(k);
				Model model_3 = Model.method462(l);
				Model aclass30_sub2_sub4_sub6_1s[] = {
						model, model_1, model_3
				};
				model = new Model(3, aclass30_sub2_sub4_sub6_1s);
			} else {
				Model model_2 = Model.method462(k);
				Model aclass30_sub2_sub4_sub6s[] = {
						model, model_2
				};
				model = new Model(2, aclass30_sub2_sub4_sub6s);
			}
		if(i == 0 && aByte205 != 0)
			model.method475(0, aByte205, 0);
		if(i == 1 && aByte154 != 0)
			model.method475(0, aByte154, 0);
		if (originalModelColors != null) {
			for (int i1 = 0; i1 < originalModelColors.length; i1++)
				model.method476(originalModelColors[i1], modifiedModelColors[i1]);

		}
		return model;
	}

	
	public void setDefaults() {
		modelID = 0;
		name = null;
		description = null;
		originalModelColors = null;
		modifiedModelColors = null;
		modelZoom = 2000;
		modelRotation1 = 0;
		modelRotation2 = 0;
		anInt204 = 0;
		modelOffset1 = 0;
		modelOffset2 = 0;
		stackable = false;
		value = 1;
		membersObject = false;
		groundActions = null;
		actions = null;
		maleEquip1 = -1;
		maleEquip2 = -1;
		aByte205 = 0;
		femaleEquip1 = -1;
		femaleEquip2 = -1;
		aByte154 = 0;
		anInt185 = -1;
		anInt162 = -1;
		anInt175 = -1;
		anInt166 = -1;
		anInt197 = -1;
		anInt173 = -1;
		stackIDs = null;
		stackAmounts = null;
		certID = -1;
		certTemplateID = -1;
		anInt167 = 128;
		anInt192 = 128;
		anInt191 = 128;
		anInt196 = 0;
		anInt184 = 0;
		team = 0;
	}

	public static ItemDef forID(int i) {
		for(int j = 0; j < 10; j++)
			if(cache[j].id == i)
				return cache[j];
		cacheIndex = (cacheIndex + 1) % 10;
		ItemDef itemDef = cache[cacheIndex];
		stream.currentOffset = streamIndices[i];
		itemDef.id = i;
		itemDef.setDefaults();
		itemDef.readValues(stream);
		if(itemDef.certTemplateID != -1)
			itemDef.toNote();
		if(!isMembers && itemDef.membersObject) {
			itemDef.name = "Members Object";
			itemDef.description = "Login to a members' server to use this object.".getBytes();
			itemDef.groundActions = null;
			itemDef.actions = null;
			itemDef.team = 0;
		}
			switch (itemDef.id) {
		case 11722:
			itemDef.femaleEquip1 = itemDef.maleEquip1;
			itemDef.femaleEquip2 = itemDef.maleEquip2;
		break;
		case 1513: // Magic logs
			itemDef.models(26902, -1, -1, -1, -1);
		break;
		case 2661:
			itemDef.name = "Saradomin plate";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.originalModelColors = new int[4];
			itemDef.modifiedModelColors = new int[4];
			itemDef.originalModelColors[0] = 61;
			itemDef.originalModelColors[1] = 41;
			itemDef.originalModelColors[2] = 24;
			itemDef.originalModelColors[3] = 11187;
			itemDef.modifiedModelColors[0] = 36133;
			itemDef.modifiedModelColors[1] = 37270;
			itemDef.modifiedModelColors[2] = 10467;
			itemDef.modifiedModelColors[3] = 10467;
			itemDef.modelID = 3020;
			itemDef.modelZoom = 1180;
			itemDef.modelRotation1 = 452;
			itemDef.modelRotation2 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = 0;
			itemDef.anInt204 = 0;
			itemDef.maleEquip1 = 3379;
			itemDef.femaleEquip1 = 3383;
			itemDef.description = "It's a Saradomin plate.".getBytes();
			break;
		case 15295:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.name = "@gre@OverloadX Pickaxe"; //Name
			itemDef.description = "A OverloadX Pickaxe!".getBytes(); //Description
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors[0] = 0;
			itemDef.modifiedModelColors[0] = 22464;
			itemDef.modelID = 11401;//2529
			itemDef.modelZoom = 1382;
			itemDef.modelRotation1 = 660;
			itemDef.modelRotation2 = 997;
			itemDef.modelOffset2 = -6;
			itemDef.modelOffset1 = 1;
			itemDef.maleEquip1 = 11399;//509
			itemDef.femaleEquip1 = 11400;//509
			break;
		case 1265:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wield";
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors[0] = 0;
			itemDef.modifiedModelColors[0] = 5652;
			itemDef.modelID = 11401;
			itemDef.modelZoom = 1382;
			itemDef.modelRotation1 = 660;
			itemDef.modelRotation2 = 997;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffset2 = 1;
			itemDef.maleEquip1 = 11399;
			itemDef.femaleEquip1 = 11400;
			itemDef.name = "Bronze pickaxe";
			itemDef.description = "It's a Bronze pickaxe.".getBytes();
			break;
		case 1267:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wield";
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors = new int[1];
			itemDef.originalModelColors[0] = 0;
			itemDef.modifiedModelColors[0] = 33;
			itemDef.modelID = 11401;
			itemDef.modelZoom = 1382;
			itemDef.modelRotation1 = 660;
			itemDef.modelRotation2 = 997;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffset2 = 1;
			itemDef.maleEquip1 = 11399;
			itemDef.femaleEquip1 = 11400;
			itemDef.name = "Iron pickaxe";
			itemDef.description = "It's an Iron pickaxe.".getBytes();
			break;
		case 1269:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wield";
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors = new int[1];
			itemDef.originalModelColors[0] = 0;
			itemDef.modifiedModelColors[0] = 2130;
			itemDef.modelID = 11401;
			itemDef.modelZoom = 1382;
			itemDef.modelRotation1 = 660;
			itemDef.modelRotation2 = 997;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffset2 = 1;
			itemDef.maleEquip1 = 11399;
			itemDef.femaleEquip1 = 11400;
			itemDef.name = "Steel pickaxe";
			itemDef.description = "It's a Steel pickaxe.".getBytes();
			break;
		case 1271:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wield";
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors = new int[1];
			itemDef.originalModelColors[0] = 0;
			itemDef.modifiedModelColors[0] = 21662;
			itemDef.modelID = 11401;
			itemDef.modelZoom = 1382;
			itemDef.modelRotation1 = 660;
			itemDef.modelRotation2 = 997;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffset2 = 1;
			itemDef.maleEquip1 = 11399;
			itemDef.femaleEquip1 = 11400;
			itemDef.name = "Adamant pickaxe";
			itemDef.description = "It's an Adamant pickaxe.".getBytes();
			break;
		case 1273:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wield";
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors = new int[1];
			itemDef.originalModelColors[0] = 0;
			itemDef.modifiedModelColors[0] = 43279;
			itemDef.modelID = 11401;
			itemDef.modelZoom = 1382;
			itemDef.modelRotation1 = 660;
			itemDef.modelRotation2 = 997;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffset2 = 1;
			itemDef.maleEquip1 = 11399;
			itemDef.femaleEquip1 = 11400;
			itemDef.name = "Mithril pickaxe";
			itemDef.description = "It's a Mithril pickaxe.".getBytes();
			break;
		case 1275:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wield";
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors = new int[1];
			itemDef.originalModelColors[0] = 0;
			itemDef.modifiedModelColors[0] = 36133;
			itemDef.modelID = 11401;
			itemDef.modelZoom = 1382;
			itemDef.modelRotation1 = 660;
			itemDef.modelRotation2 = 997;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffset2 = 1;
			itemDef.maleEquip1 = 11399;
			itemDef.femaleEquip1 = 11400;
			itemDef.name = "Rune pickaxe";
			itemDef.description = "It's a Rune pickaxe.".getBytes();
			break;
		case 15296:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.name = "@gre@OverloadX Axe"; //Name
			itemDef.description = "A OverloadX Woodcutting Axe!".getBytes(); //Description
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors[0] = 61;
			itemDef.modifiedModelColors[0] = 22464;
			itemDef.modelID = 2544;
			itemDef.modelZoom = 1060;
			itemDef.modelRotation1 = 520;
			itemDef.modelRotation2 = 1196;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = -15;
			itemDef.maleEquip1 = 510;
			itemDef.femaleEquip1 = 510;
			break;
		case 15297:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.name = "Ring of Flying"; //Name
			itemDef.description = "A ring that makes you fly!".getBytes(); 
			itemDef.modelID = 2784;
			itemDef.modelZoom = 830;
			itemDef.modelRotation1 = 268;
			itemDef.modelRotation2 = 180;
			itemDef.anInt204 = 2024;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -35;
			break;
	case 15298:
		itemDef.name = "Abyssal whip";
		itemDef.actions = new String[5];
		itemDef.actions[1] = "Wield";
		itemDef.originalModelColors = new int[2];
		itemDef.modifiedModelColors = new int[2];
		itemDef.originalModelColors[0] = 944;
		itemDef.modifiedModelColors[0] = 18215;
		itemDef.originalModelColors[1] = 0;
		itemDef.modifiedModelColors[1] = 0;
		itemDef.modelID = 48001;
            itemDef.modelZoom = 840;
            itemDef.modelRotation1 = 280;//rotation 1
            itemDef.modelRotation2 = 0;//rotation 2
            itemDef.modelOffset1 = 0;//model offset 1
            itemDef.modelOffset2 = 0;//model offset 2
		itemDef.maleEquip1 = 48000;
		itemDef.femaleEquip1 = 48000;
		itemDef.description = "It's a Abyssal whip.".getBytes();
	break;
	case 15299:
		itemDef.name = "Abyssal whip";
		itemDef.actions = new String[5];
		itemDef.actions[1] = "Wield";
		itemDef.originalModelColors = new int[2];
		itemDef.modifiedModelColors = new int[2];
		itemDef.originalModelColors[0] = 944;
		itemDef.modifiedModelColors[0] = 127;
		itemDef.originalModelColors[1] = 0;
		itemDef.modifiedModelColors[1] = 0;
		itemDef.modelID = 48001;
            itemDef.modelZoom = 840;
            itemDef.modelRotation1 = 280;//rotation 1
            itemDef.modelRotation2 = 0;//rotation 2
            itemDef.modelOffset1 = 0;//model offset 1
            itemDef.modelOffset2 = 0;//model offset 2
		itemDef.maleEquip1 = 48000;
		itemDef.femaleEquip1 = 48000;
		itemDef.description = "It's a Abyssal whip.".getBytes();
	break;

	case 15300:
		itemDef.name = "Abyssal whip";
		itemDef.actions = new String[5];
		itemDef.actions[1] = "Wield";
		itemDef.originalModelColors = new int[2];
		itemDef.modifiedModelColors = new int[2];
		itemDef.originalModelColors[0] = 944;
		itemDef.modifiedModelColors[0] = -28884;
		itemDef.originalModelColors[1] = 0;
		itemDef.modifiedModelColors[1] = 0;
		itemDef.modelID = 48001;
            itemDef.modelZoom = 840;
            itemDef.modelRotation1 = 280;//rotation 1
            itemDef.modelRotation2 = 0;//rotation 2
            itemDef.modelOffset1 = 0;//model offset 1
            itemDef.modelOffset2 = 0;//model offset 2
		itemDef.maleEquip1 = 48000;
		itemDef.femaleEquip1 = 48000;
		itemDef.description = "It's a Abyssal whip.".getBytes();
	break;
	case 15301:
		itemDef.name = "Abyssal whip";
		itemDef.actions = new String[5];
		itemDef.actions[1] = "Wield";
		itemDef.originalModelColors = new int[2];
		itemDef.modifiedModelColors = new int[2];
		itemDef.originalModelColors[0] = 944;
		itemDef.modifiedModelColors[0] = 8128;
		itemDef.originalModelColors[1] = 0;
		itemDef.modifiedModelColors[1] = 0;
	 	itemDef.modelID = 48001;
            itemDef.modelZoom = 840;
            itemDef.modelRotation1 = 280;//rotation 1
            itemDef.modelRotation2 = 0;//rotation 2
            itemDef.modelOffset1 = 0;//model offset 1
            itemDef.modelOffset2 = 0;//model offset 2
		itemDef.maleEquip1 = 48000;
		itemDef.femaleEquip1 = 48000;
		itemDef.description = "It's a Abyssal whip.".getBytes();
	break;
case 15311:
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wear";
            itemDef.modelID = 45000;
            itemDef.modelZoom = 2000;
            itemDef.modelRotation1 = 500;
            itemDef.modelRotation2 = 0;
            itemDef.modelOffset1 = -6;
            itemDef.modelOffset2 = 1;
            itemDef.anInt204 = 14;
            itemDef.maleEquip1 = 45001;
            itemDef.femaleEquip1 = 45001;
            itemDef.name = "Wings Of Saradomin";
            itemDef.description = "Rosejuice's wings of saradomin".getBytes();
break;
case 15307:
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wear";
            itemDef.modelID = 45002;
            itemDef.modelZoom = 2000;
            itemDef.modelRotation1 = 500;
            itemDef.modelRotation2 = 0;
            itemDef.modelOffset1 = -6;
            itemDef.modelOffset2 = 1;
            itemDef.anInt204 = 14;
            itemDef.maleEquip1 = 45003;
            itemDef.femaleEquip1 = 45003;
            itemDef.name = "Death Wings Of Anarchy";
            itemDef.description = "Rosejuice's wings of anarchy".getBytes();
break;
case 15308:
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wear";
            itemDef.modelID = 45008;
            itemDef.modelZoom = 2000;
            itemDef.modelRotation1 = 500;
            itemDef.modelRotation2 = 0;
            itemDef.modelOffset1 = -6;
            itemDef.modelOffset2 = 1;
            itemDef.anInt204 = 14;
            itemDef.maleEquip1 = 45009;
            itemDef.femaleEquip1 = 45009;
            itemDef.name = "Wings Of Life";
            itemDef.description = "Rosejuice's wings of life.".getBytes();
break;
case 15309:
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wear";
            itemDef.modelID = 45004;
            itemDef.modelZoom = 2000;
            itemDef.modelRotation1 = 500;
            itemDef.modelRotation2 = 0;
            itemDef.modelOffset1 = -6;
            itemDef.modelOffset2 = 1;
            itemDef.anInt204 = 14;
            itemDef.maleEquip1 = 45006;
            itemDef.femaleEquip1 = 45006;
            itemDef.name = "Deadly Slayer Cape";
            itemDef.description = "Large winged deadly slayer cape".getBytes();
break;
case 15310:
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wear";
            itemDef.modelID = 45005;
            itemDef.modelZoom = 2000;
            itemDef.modelRotation1 = 500;
            itemDef.modelRotation2 = 0;
            itemDef.modelOffset1 = -6;
            itemDef.modelOffset2 = 1;
            itemDef.anInt204 = 14;
            itemDef.maleEquip1 = 45007;
            itemDef.femaleEquip1 = 45007;
            itemDef.name = "Angel Cape";
            itemDef.description = "Large winged angel cape".getBytes();
break;
case 15312:
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wear";
            itemDef.modelID = 40560;
            itemDef.modelZoom = 1382;
            itemDef.modelRotation1 = 364;
            itemDef.modelRotation2 = 1145;
            itemDef.modelOffset1 = 7;
            itemDef.modelOffset2 = -12;
            itemDef.maleEquip1 = 48470;
            itemDef.femaleEquip1 = 48470;
            itemDef.name = "Dragon pickaxe";
            itemDef.description = "A pickaxe made from dragon!".getBytes();
break;
case 15313:
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wear";
            itemDef.modelID = 54494;
            itemDef.modelZoom = 1360;
            itemDef.modelRotation1 = 498;//rotation 1
            itemDef.modelRotation2 = 354;//rotation 2
            itemDef.modelOffset1 = 4;//model offset 1
            itemDef.modelOffset2 = 2;//model offset 2
            itemDef.maleEquip1 = 56294;//male wield ModelId
            itemDef.femaleEquip1 = 56294;//female wield ModelId
            itemDef.name = "Chaotic maul";
            itemDef.description = "A maul used to claim life from those who deserve it.".getBytes();
break;
case 15315:
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wear";
            itemDef.modelID = 48001;
            itemDef.modelZoom = 840;
            itemDef.modelRotation1 = 280;//rotation 1
            itemDef.modelRotation2 = 0;//rotation 2
            itemDef.modelOffset1 = 0;//model offset 1
            itemDef.modelOffset2 = 0;//model offset 2
            itemDef.maleEquip1 = 48000;//male wield ModelId
            itemDef.femaleEquip1 = 48000;//female wield ModelId
            itemDef.name = "Abyssal Whip@red@(NEW)";
            itemDef.description = "A weapon from the abyss.".getBytes();
break;
            /*case 15315:
                itemDef.actionData(1, "Wield");
                itemDef.models(5412, 48000, 48000, -1, -1);//48001
                itemDef.modelData(840, 280, -1, -1, -1);
                itemDef.itemData("Abyssal Whip@red@(NEW)", "A weapon from the abyss.");
                break;*/
		case 15302:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Equip"; 
			itemDef.modelID = 24253;
			itemDef.maleEquip1 = 24254;
			itemDef.femaleEquip1 = 24254;
			itemDef.modelZoom = 800;
			itemDef.modelRotation1 = 498;
			itemDef.modelOffset2 = 1300;
			itemDef.name = "Santa Teresa";
			itemDef.description = "Noitora's Zampaketo.".getBytes();
			break;
		case 15316:
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Claim Prize";
			itemDef.name = "Monster Package"; //Name
			itemDef.description = "A rare monster package.".getBytes(); //Description
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors[0] = 22410;
			itemDef.modifiedModelColors[0] = 3990;
			itemDef.modelID = 2426;
			itemDef.modelZoom = 1180;
			itemDef.modelRotation1 = 160;
			itemDef.modelRotation2 = 172;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = 0;
			break;

		case 15317: //item ID
			itemDef.actions = new String[5]; 
			itemDef.actions[1] = "Wear"; 
			itemDef.modelID = 147; //drop and inventory model id 
			itemDef.femaleEquip1 = 146; //female model id 
			itemDef.maleEquip1 = 146; //male model id
			itemDef.modelZoom = 800;
			itemDef.modelRotation1 = 160; 
			itemDef.modelRotation2 = 152;
			itemDef.modelOffset1 = -1; 
			itemDef.modelOffset2 = 6;
			itemDef.anInt175 = 56; 
			itemDef.anInt197 = 116; 
			itemDef.name = "Black dragon mask"; //name of the item 
			itemDef.description = "RAWR Im a dragon!".getBytes(); //examine info }
		break; 

		case 15318: //item Id
            			itemDef.actions = new String[5];
            			itemDef.actions[1] = "Wear";
            			itemDef.originalModelColors = new int[4];
            			itemDef.modifiedModelColors = new int[4];
            			itemDef.originalModelColors[0] = 57280;
            			itemDef.originalModelColors[1] = 54503;
            			itemDef.originalModelColors[2] = 54183;
            			itemDef.originalModelColors[3] = 11200;
            			itemDef.modifiedModelColors[0] = 9152;
            			itemDef.modifiedModelColors[1] = 7104;
            			itemDef.modifiedModelColors[2] = 7104;
            			itemDef.modifiedModelColors[3] = 796;
            			itemDef.modelID = 42222;
            			itemDef.modelZoom = 2000;
            			itemDef.modelRotation1 = 500;
            			itemDef.modelRotation2 = 0;
            			itemDef.modelOffset1 = 14;
            			itemDef.modelOffset2 = -6;
            			itemDef.maleEquip1 = 42223;
            			itemDef.femaleEquip1 = 42223;
            			itemDef.anInt175 = -1;
            			itemDef.anInt197 = -1;
            			itemDef.anInt199 = -1;
           			itemDef.name = "OverloadX Donator Cape"; // made by nathanr87
            			itemDef.description = "The Cape Worn By Donators.".getBytes();
		break;
		case 15319: //item Id
            			itemDef.actions = new String[5];
            			itemDef.actions[1] = "Wear";
            			itemDef.modelID = 53007;
            			itemDef.modelZoom = 2000;
            			itemDef.modelRotation1 = 500;
            			itemDef.modelRotation2 = 0;
            			itemDef.modelOffset1 = 14;
            			itemDef.modelOffset2 = -6;
            			itemDef.maleEquip1 = 53006;
            			itemDef.femaleEquip1 = 53006;
            			itemDef.anInt175 = -1;
            			itemDef.anInt197 = -1;
            			itemDef.anInt199 = -1;
           			itemDef.name = "PKers cape"; // made by nathanr87
            			itemDef.description = "A cape only achieved through the slaughting of many people.".getBytes();
		break;
		case 15320: //item Id
            			itemDef.actions = new String[5];
            			itemDef.actions[1] = "Wear";
            			itemDef.modelID = 53014;
            			itemDef.modelZoom = 2000;
            			itemDef.modelRotation1 = 500;
            			itemDef.modelRotation2 = 0;
            			itemDef.modelOffset1 = 14;
            			itemDef.modelOffset2 = -6;
            			itemDef.maleEquip1 = 53013;
            			itemDef.femaleEquip1 = 53013;
            			itemDef.anInt175 = -1;
            			itemDef.anInt197 = -1;
            			itemDef.anInt199 = -1;
           			itemDef.name = "Mod cape"; // made by nathanr87
            			itemDef.description = "A special cape just for moderators!".getBytes();
		break;
		case 15321: //item Id
            			itemDef.actions = new String[5];
            			itemDef.actions[1] = "Wear";
            			itemDef.modelID = 53016;
            			itemDef.modelZoom = 2000;
            			itemDef.modelRotation1 = 500;
            			itemDef.modelRotation2 = 0;
            			itemDef.modelOffset1 = 14;
            			itemDef.modelOffset2 = -6;
            			itemDef.maleEquip1 = 53015;
            			itemDef.femaleEquip1 = 53015;
            			itemDef.anInt175 = -1;
            			itemDef.anInt197 = -1;
            			itemDef.anInt199 = -1;
           			itemDef.name = "Admin cape"; // made by nathanr87
            			itemDef.description = "A special cape just for administrators!".getBytes();
		break;
		case 15322: //item Id
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Summon: ";
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors[0] = 7446;//color change
			itemDef.modifiedModelColors[0] = 926;//color change 1
			itemDef.modelID = 21946;//itemdrop look
			itemDef.modelZoom = 1595;//zoom
			itemDef.modelRotation1 = 196; //rotation rotation up/down
			itemDef.modelRotation2 = 2031;//rotation left/right
			itemDef.anInt204 = 2047;//i think more zoom stuff 
			itemDef.maleEquip1 = 14935;//male weild
			itemDef.femaleEquip1 = 14935;//female weild
			itemDef.modelOffset1 = 2;  //offset
			itemDef.modelOffset2 = -1;  //offset
			itemDef.name = "@gre@Pet Monkey";
			itemDef.description = "A cute little monkey.".getBytes();
			break;
		case 15323: //item Id
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wield";
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors[0] = 0;
			itemDef.modifiedModelColors[0] = 0;
			itemDef.modelID = 14053;//item look
			itemDef.modelZoom = 1957;
			itemDef.modelRotation1 = 498;
			itemDef.modelRotation2 = 484;
			itemDef.femaleEquip1 = 14052;
			itemDef.maleEquip1 = 14052;
			itemDef.name = "Dildo";
			itemDef.description = "Be sure to clean after use.".getBytes();
			break;
		case 15324:
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Open";
			itemDef.name = "Runes Box"; //Name
			itemDef.description = "A box containing runes.".getBytes(); //Description
			itemDef.modelID = 3683;
			itemDef.modelZoom = 3840;
			itemDef.modelRotation1 = 108;
			itemDef.modelRotation2 = 1684;
			itemDef.modelOffset1 = 15;
			itemDef.modelOffset2 = -126;
			break;
		case 15325:
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Open";
			itemDef.name = "Pure Box"; //Name
			itemDef.description = "A box containing a set of pure armor.".getBytes(); //Description
			itemDef.modelID = 3683;
			itemDef.modelZoom = 3840;
			itemDef.modelRotation1 = 108;
			itemDef.modelRotation2 = 1684;
			itemDef.modelOffset1 = 15;
			itemDef.modelOffset2 = -126;
			break;
		case 15326:
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Open";
			itemDef.name = "Zerker Box"; //Name
			itemDef.description = "A box containing a set of zerker armor.".getBytes(); //Description
			itemDef.modelID = 3683;
			itemDef.modelZoom = 3840;
			itemDef.modelRotation1 = 108;
			itemDef.modelRotation2 = 1684;
			itemDef.modelOffset1 = 15;
			itemDef.modelOffset2 = -126;
			break;
		case 15327:
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Open";
			itemDef.name = "Dharoks Box"; //Name
			itemDef.description = "A box containing a set of dharoks armor.".getBytes(); //Description
			itemDef.modelID = 3683;
			itemDef.modelZoom = 3840;
			itemDef.modelRotation1 = 108;
			itemDef.modelRotation2 = 1684;
			itemDef.modelOffset1 = 15;
			itemDef.modelOffset2 = -126;
			break;
		case 15328:
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Open";
			itemDef.name = "Food Box"; //Name
			itemDef.description = "A box containing a large amount of food.".getBytes(); //Description
			itemDef.modelID = 3683;
			itemDef.modelZoom = 3840;
			itemDef.modelRotation1 = 108;
			itemDef.modelRotation2 = 1684;
			itemDef.modelOffset1 = 15;
			itemDef.modelOffset2 = -126;
			break;
		case 15329:
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Open";
			itemDef.name = "Arrows Box"; //Name
			itemDef.description = "A box containing a lot of arrows.".getBytes(); //Description
			itemDef.modelID = 3683;
			itemDef.modelZoom = 3840;
			itemDef.modelRotation1 = 108;
			itemDef.modelRotation2 = 1684;
			itemDef.modelOffset1 = 15;
			itemDef.modelOffset2 = -126;
			break;
		case 15330:
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Open";
			itemDef.name = "Skilling Box"; //Name
			itemDef.description = "A box containing materials used for skilling.".getBytes(); //Description
			itemDef.modelID = 3683;
			itemDef.modelZoom = 3840;
			itemDef.modelRotation1 = 108;
			itemDef.modelRotation2 = 1684;
			itemDef.modelOffset1 = 15;
			itemDef.modelOffset2 = -126;
			break;
		case 15331:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear"; // String name
			itemDef.actions[2] = "Spin"; // String name
			itemDef.modelID = 12071; // Drop/Inv Model
			itemDef.maleEquip1 = 12072; // Male Wield Model
			itemDef.femaleEquip1 = 12072; // Female Wield
			itemDef.modelZoom = 900; // Zoom - Increase to make inv model smaller
			itemDef.modelRotation1 = 498; // Rotate up/down -  Increase to rotate upwards
			itemDef.modelRotation2 = 1300; // Rotate right/left - Increase to rotate right
			itemDef.modelOffset1 = -1; // Position in inv, increase to move right
			itemDef.modelOffset2 = -1; // Position in inv, increase to move up
			itemDef.name = "Candy Cane"; // Item Name
			itemDef.description = "A must-have Christmas accessory.".getBytes(); // Item Examine
			break;
		case 15332:
			itemDef.name = "Reindeer hat";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Destroy";
			itemDef.modelID = 20469;
			itemDef.maleEquip1 = 20503;
			itemDef.modelZoom = 210;
			itemDef.modelRotation1 = 1488;
			itemDef.modelRotation2 = 1;
			itemDef.femaleEquip1 = 20505;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = -8;
			itemDef.description = "It's a Reindeer hat.".getBytes();
			break;
	case 15333:
		itemDef.name = "Abyssal whip";
		itemDef.actions = new String[5];
		itemDef.actions[1] = "Wield";
		itemDef.originalModelColors = new int[2];
		itemDef.modifiedModelColors = new int[2];
		itemDef.originalModelColors[0] = 944;
		itemDef.modifiedModelColors[0] = 0;
		itemDef.originalModelColors[1] = 0;
		itemDef.modifiedModelColors[1] = 0;
	 	itemDef.modelID = 48001;
            itemDef.modelZoom = 840;
            itemDef.modelRotation1 = 280;//rotation 1
            itemDef.modelRotation2 = 0;//rotation 2
            itemDef.modelOffset1 = 0;//model offset 1
            itemDef.modelOffset2 = 0;//model offset 2
		itemDef.maleEquip1 = 48000;
		itemDef.femaleEquip1 = 48000;
		itemDef.description = "It's a Abyssal whip.".getBytes();
	break;
	case 15334:
		itemDef.name = "Abyssal whip";
		itemDef.actions = new String[5];
		itemDef.actions[1] = "Wield";
		itemDef.originalModelColors = new int[2];
		itemDef.modifiedModelColors = new int[2];
		itemDef.originalModelColors[0] = 944;
		itemDef.modifiedModelColors[0] = 51136;
		itemDef.originalModelColors[1] = 0;
		itemDef.modifiedModelColors[1] = 0;
	 	itemDef.modelID = 48001;
            itemDef.modelZoom = 840;
            itemDef.modelRotation1 = 280;//rotation 1
            itemDef.modelRotation2 = 0;//rotation 2
            itemDef.modelOffset1 = 0;//model offset 1
            itemDef.modelOffset2 = 0;//model offset 2
		itemDef.maleEquip1 = 48000;
		itemDef.femaleEquip1 = 48000;
		itemDef.description = "It's a Abyssal whip.".getBytes();
	break;
		case 15335:
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Drink";
			itemDef.name = "Overload(4)"; //Name
			itemDef.description = "An overload potion!".getBytes();
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors[0] = 61;
			itemDef.modifiedModelColors[0] = 0;
			itemDef.modelID = 2789;
			itemDef.modelZoom = 550;
			itemDef.modelRotation1 = 84;
			itemDef.modelRotation2 = 1996;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
		break;
		case 15336:
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Drink";
			itemDef.name = "Overload(3)"; //Name
			itemDef.description = "An overload potion!".getBytes();
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors[0] = 61;
			itemDef.modifiedModelColors[0] = 0;
			itemDef.modelID = 2697;
			itemDef.modelZoom = 550;
			itemDef.modelRotation1 = 84;
			itemDef.modelRotation2 = 1996;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
		break;
		case 15337:
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Drink";
			itemDef.name = "Overload(2)"; //Name
			itemDef.description = "An overload potion!".getBytes();
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors[0] = 61;
			itemDef.modifiedModelColors[0] = 0;
			itemDef.modelID = 2384;
			itemDef.modelZoom = 550;
			itemDef.modelRotation1 = 84;
			itemDef.modelRotation2 = 1996;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
		break;
		case 15338:
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Drink";
			itemDef.name = "Overload(1)"; //Name
			itemDef.description = "An overload potion!".getBytes();
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors[0] = 61;
			itemDef.modifiedModelColors[0] = 0;
			itemDef.modelID = 2621;
			itemDef.modelZoom = 550;
			itemDef.modelRotation1 = 84;
			itemDef.modelRotation2 = 1996;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
		break;
		case 15290:
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Claim Prize";
			itemDef.name = "Skiller Package"; //Name
			itemDef.description = "A skiller package obtained through donating".getBytes(); //Description
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors[0] = 22410;
			itemDef.modifiedModelColors[0] = 22464;
			itemDef.modelID = 2426;
			itemDef.modelZoom = 1180;
			itemDef.modelRotation1 = 160;
			itemDef.modelRotation2 = 172;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = 0;
			break;
		case 15291:
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Claim Prize";
			itemDef.name = "Combat Package"; //Name
			itemDef.description = "A Combat package obtained through donating".getBytes(); //Description
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors[0] = 22410;
			itemDef.modifiedModelColors[0] = 1010;
			itemDef.modelID = 2426;
			itemDef.modelZoom = 1180;
			itemDef.modelRotation1 = 160;
			itemDef.modelRotation2 = 172;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = 0;
			break;
		case 15292:
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Claim Prize";
			itemDef.name = "Costume Package"; //Name
			itemDef.description = "A Costume package obtained through donating".getBytes(); //Description
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors[0] = 22410;
			itemDef.modifiedModelColors[0] = 23009;
			itemDef.modelID = 2426;
			itemDef.modelZoom = 1180;
			itemDef.modelRotation1 = 160;
			itemDef.modelRotation2 = 172;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = 0;
			break;
		case 15293:
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Claim Prize";
			itemDef.name = "PKer Package"; //Name
			itemDef.description = "A PKer package obtained through donating".getBytes(); //Description
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors[0] = 22410;
			itemDef.modifiedModelColors[0] = 8464;
			itemDef.modelID = 2426;
			itemDef.modelZoom = 1180;
			itemDef.modelRotation1 = 160;
			itemDef.modelRotation2 = 172;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = 0;
			break;
		case 15294:
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Claim Prize";
			itemDef.name = "Misc Package"; //Name
			itemDef.description = "A Combat package obtained through donating".getBytes(); //Description
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors[0] = 22410;
			itemDef.modifiedModelColors[0] = 0;
			itemDef.modelID = 2426;
			itemDef.modelZoom = 1180;
			itemDef.modelRotation1 = 160;
			itemDef.modelRotation2 = 172;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = 0;
			break;



		case 15283:
			itemDef.name = "@bla@Black PartyHat";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors[0] = 926;
			itemDef.modifiedModelColors[0] = 0; 
			itemDef.modelID = 2635;//Item Look
			itemDef.modelZoom = 440;
			itemDef.modelRotation1 = 76;
			itemDef.modelRotation2 = 1852;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = 1;
			itemDef.maleEquip1 = 187;
			itemDef.femaleEquip1 = 363;
			itemDef.description = "A Black Partyhat, the most expensive item in the game".getBytes();
			break;
		case 15282:
			itemDef.name = "@bla@Black h'ween Mask";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors[0] = 926;  //original color
			itemDef.modifiedModelColors[0] = 0; //changed color
			itemDef.modelID = 2438;
			itemDef.modelZoom = 730;
			itemDef.modelRotation1 = 516;
			itemDef.modelRotation2 = 0;
			itemDef.maleEquip1 = 3188;
			itemDef.femaleEquip1 = 3192;
			itemDef.description = "Aaaarrrghhh... I'm a monster.".getBytes();
			break;
		case 15287:
			itemDef.name = "Check @gre@(1B)";
			itemDef.modelID = 2430;
			itemDef.modelZoom = 830;
			itemDef.modelRotation1 = 404;
			itemDef.modelRotation2 = 1644;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = 5;
			itemDef.anInt204 = 0;
			itemDef.description = "A check worth 1B coins.".getBytes();
			break;
		case 15314:
			itemDef.name = "@red@Donator ticket!";
			itemDef.modelID = 2430;
			itemDef.modelZoom = 830;
			itemDef.modelRotation1 = 404;
			itemDef.modelRotation2 = 1644;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = 5;
			itemDef.anInt204 = 0;
			itemDef.description = "Turn this in to a staff member to get donator!!".getBytes();
			break;
		case 15288:
			itemDef.name = "Check @gre@(100M)";
			itemDef.modelID = 2430;
			itemDef.modelZoom = 830;
			itemDef.modelRotation1 = 404;
			itemDef.modelRotation2 = 1644;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = 5;
			itemDef.anInt204 = 0;
			itemDef.description = "A check worth 100M coins.".getBytes();
			break;
		case 15289:
			itemDef.name = "Check @gre@(10M)";
			itemDef.modelID = 2430;
			itemDef.modelZoom = 830;
			itemDef.modelRotation1 = 404;
			itemDef.modelRotation2 = 1644;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = 5;
			itemDef.anInt204 = 0;
			itemDef.description = "A check worth 10M coins.".getBytes();
			break;

	case 542:
		itemDef.actions = new String[5];
		itemDef.actions[1] = "Wear";
		itemDef.modelID = 33952;
		itemDef.modelZoom = 2100;
		itemDef.modelRotation1 = 636;
		itemDef.modelRotation2 = 0;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = 7;
		itemDef.maleEquip1 = 16252;
		itemDef.femaleEquip1 = 33958;
		itemDef.name = "Monks robe";
		itemDef.description = "Its a monks robe".getBytes();
	break;

	case 544:
		itemDef.actions = new String[5];
		itemDef.actions[1] = "Wear";
		itemDef.modelID = 33948;
		itemDef.modelZoom = 1370;
		itemDef.modelRotation1 = 536;
		itemDef.modelRotation2 = 0;
		itemDef.modelOffset2 = 7;
		itemDef.modelOffset1 = 0;
		itemDef.maleEquip1 = 16256;
		itemDef.femaleEquip1 = 33957;
		itemDef.maleEquip2 = 16253;
		itemDef.femaleEquip2 = 33959;
		itemDef.name = "Monks robe";
		itemDef.description = "Its a Monks robe".getBytes();
	break;
		case 15284:
			itemDef.name = "@red@Dragon Cape";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.originalModelColors= new int[1];
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors[0] = 40;
			itemDef.modifiedModelColors[0] = 34;
			itemDef.modelID = 9631;
			itemDef.modelZoom = 2040;
			itemDef.modelRotation1 = 545;
			itemDef.modelRotation2 = 2;
			itemDef.modelOffset2 = 5;
			itemDef.modelOffset1 = 4;
			itemDef.anInt204 = 0;
			itemDef.maleEquip1 = 9638;
			itemDef.femaleEquip1 = 9640;
			itemDef.description = "A rare cape with liquid dragon flowing down it.".getBytes();
			break;

		case 15286:
			itemDef.name = "@red@Santa's Staff";
			itemDef.actions = new String[6];
			itemDef.actions[1] = "Equip";
			itemDef.actions[2] = "";
			itemDef.modelID = 5060;
			itemDef.modelZoom = 1040;
			itemDef.modelRotation1 = 1545;
			itemDef.modelRotation2 = 2;
			itemDef.modelOffset2 = 5;
			itemDef.modelOffset1 = 4;
			itemDef.anInt204 = 0;
			itemDef.maleEquip1 = 5036;
			itemDef.femaleEquip1 = 5036;
			itemDef.description = "This Staff Belongs To I Love Santa...".getBytes();
			break;

		case 15285:
			itemDef.name = "@blu@Water cape";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.originalModelColors= new int[1];
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors[0] = 40;
			itemDef.modifiedModelColors[0] = 17;
			itemDef.modelID = 9631;
			itemDef.modelZoom = 2040;
			itemDef.modelRotation1 = 545;
			itemDef.modelRotation2 = 2;
			itemDef.modelOffset2 = 5;
			itemDef.modelOffset1 = 4;
			itemDef.anInt204 = 0;
			itemDef.maleEquip1 = 9638;
			itemDef.femaleEquip1 = 9640;
			itemDef.description = "A rare cape of water.".getBytes();
			break;

		case 13887:
			itemDef.name = "Vesta's chainbody";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.modelID = 42593;
			itemDef.modelZoom = 1440;
			itemDef.modelRotation1 = 545;
			itemDef.modelRotation2 = 2;
			itemDef.modelOffset2 = 5;
			itemDef.modelOffset1 = 4;
			itemDef.anInt204 = 0;
			itemDef.maleEquip1 = 42624;
			itemDef.femaleEquip1 = 42644;
			itemDef.description = "Vesta's chainbody, a reward from PVP.".getBytes();
			break;
case 3686://pestle & mortor
itemDef.actions = new String[5];
itemDef.actions[1] = "Wield";
itemDef.modelID = 18964;
itemDef.modelZoom = 2000;
itemDef.maleEquip1 = 18964;
itemDef.femaleEquip1 = 18964;
itemDef.name = "null";
itemDef.description = "It's a null.".getBytes();
break;

case 3685: //crafting right
itemDef.actions = new String[5];
itemDef.actions[1] = "Wield";
itemDef.modelID = 18966;
itemDef.modelZoom = 2000;
itemDef.maleEquip1 = 18966;
itemDef.femaleEquip1 = 18966;
itemDef.name = "null";
itemDef.description = "It's a null.".getBytes();
break;

case 3684://crafting left
itemDef.actions = new String[5];
itemDef.actions[1] = "Wield";
itemDef.modelID = 18962;
itemDef.modelZoom = 2000;
itemDef.maleEquip1 = 18962;
itemDef.femaleEquip1 = 18962;
itemDef.name = "null";
itemDef.description = "It's a null.".getBytes();
break;

case 3683: //cooking right
itemDef.actions = new String[5];
itemDef.actions[1] = "Wield";
itemDef.modelID = 18963;
itemDef.modelZoom = 2000;
itemDef.maleEquip1 = 18963;
itemDef.femaleEquip1 = 18963;
itemDef.name = "null";
itemDef.description = "It's a null.".getBytes();
break;

case 3682: //wc axe
itemDef.actions = new String[5];
itemDef.actions[1] = "Wield";
itemDef.modelID = 18965;
itemDef.modelZoom = 2000;
itemDef.maleEquip1 = 18965;
itemDef.femaleEquip1 = 18965;
itemDef.name = "null";
itemDef.description = "It's a null.".getBytes();
break;

case 3680: // defense shield right
itemDef.actions = new String[5];
itemDef.actions[1] = "Wield";
itemDef.modelID = 18959;
itemDef.modelZoom = 2000;
itemDef.maleEquip1 = 18959;
itemDef.femaleEquip1 = 18959;
itemDef.name = "null";
itemDef.description = "It's a null.".getBytes();
break;

case 3681: // defense shield left
itemDef.actions = new String[5];
itemDef.actions[1] = "Wield";
itemDef.modelID = 18960;
itemDef.modelZoom = 2000;
itemDef.maleEquip1 = 18960;
itemDef.femaleEquip1 = 18960;
itemDef.name = "null";
itemDef.description = "It's a null.".getBytes();
break;

		case 13893:
			itemDef.name = "Vesta's plateskirt";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.modelID = 42581;
			itemDef.modelZoom = 1753;
			itemDef.modelRotation1 = 562;
			itemDef.modelRotation2 = 1;
			itemDef.modelOffset2 = 11;
			itemDef.modelOffset1 = -3;
			itemDef.anInt204 = 0;
			itemDef.maleEquip1 = 42633;
			itemDef.femaleEquip1 = 42647;
			itemDef.description = "Vesta's plateskirt, a reward from PVP.".getBytes();
			break;
		case 13899:
			itemDef.name = "Vesta's longsword";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Equip";
			itemDef.modelID = 42597;
			itemDef.modelZoom = 1744;
			itemDef.modelRotation1 = 738;
			itemDef.modelRotation2 = 1985;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = 0;
			itemDef.anInt204 = 0;
			itemDef.maleEquip1 = 9519;
			itemDef.femaleEquip1 = 9519;
			itemDef.description = "Vesta's longsword, a reward from PVP.".getBytes();
			break;

		case 13905:
			itemDef.name = "Vesta's spear";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Equip";
			itemDef.modelID = 42599;
			itemDef.modelZoom = 2022;
			itemDef.modelRotation1 = 480;
			itemDef.modelRotation2 = 15;
			itemDef.modelOffset2 = 5;
			itemDef.modelOffset1 = 0;
			itemDef.anInt204 = 0;
			itemDef.maleEquip1 = 9518;
			itemDef.femaleEquip1 = 9518;
			itemDef.description = "Vesta's spear, a reward from PVP.".getBytes();
			break;

		case 13858:
			itemDef.name = "Zuriel's robe top";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.modelID = 42591;
			itemDef.modelZoom = 1373;
			itemDef.modelRotation1 = 373;
			itemDef.modelRotation2 = 0;
			itemDef.modelOffset2 = -7;
			itemDef.modelOffset1 = 0;
			itemDef.anInt204 = 0;
			itemDef.maleEquip1 = 42627;
			itemDef.femaleEquip1 = 42642;
			itemDef.description = "Zuriel's robe top, a reward from PVP.".getBytes();
			break;
		case 13861:
			itemDef.name = "Zuriel's robe bottom";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.modelID = 42588;
			itemDef.modelZoom = 1697;
			itemDef.modelRotation1 = 512;
			itemDef.modelRotation2 = 0;
			itemDef.modelOffset2 = -9;
			itemDef.modelOffset1 = 2;
			itemDef.anInt204 = 0;
			itemDef.maleEquip1 = 42634;
			itemDef.femaleEquip1 = 42645;
			itemDef.description = "Zuriel's robe bottom, a reward from PVP.".getBytes();
			break;
		case 13864:
			itemDef.name = "Zuriel's hood";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.modelID = 42604;
			itemDef.modelZoom = 720;
			itemDef.modelRotation1 = 28;
			itemDef.modelRotation2 = 0;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = 1;
			itemDef.anInt204 = 0;
			itemDef.maleEquip1 = 42638;
			itemDef.femaleEquip1 = 42653;
			itemDef.description = "Zuriel's hood, a reward from PVP.".getBytes();
			break;
		case 13867:
			itemDef.name = "Zuriel's staff";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Equip";
			itemDef.modelID = 42595;
			itemDef.modelZoom = 2000;
			itemDef.modelRotation1 = 366;
			itemDef.modelRotation2 = 3;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = 0;
			itemDef.anInt204 = 0;
			itemDef.maleEquip1 = 9520;
			itemDef.femaleEquip1 = 9520;
			itemDef.description = "Zuriel's staff, a reward from PVP.".getBytes();
			break;

		case 14479:
			itemDef.name = "Dragon platebody";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Equip";
			itemDef.modelID = 44576;
			itemDef.modelZoom = 1360;
			itemDef.modelRotation1 = 443;
			itemDef.modelRotation2 = 2047;
			itemDef.modelOffset2 = -1;
			itemDef.modelOffset1 = 0;
			itemDef.maleEquip1 = 40207;
			itemDef.femaleEquip1 = 40427;
			itemDef.description = "Provides excellent protection.".getBytes();
			break;

		case 13884:
			itemDef.name = "Statius's platebody";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.modelID = 42602;
			itemDef.modelZoom = 1312;
			itemDef.modelRotation1 = 272;
			itemDef.modelRotation2 = 2047;
			itemDef.modelOffset2 = 39;
			itemDef.modelOffset1 = -2;
			itemDef.anInt204 = 0;
			itemDef.maleEquip1 = 42625;
			itemDef.femaleEquip1 = 42641;
			itemDef.description = "Statius's platebody, a reward from PVP.".getBytes();
			break;
		case 13890:
			itemDef.name = "Statius's platelegs";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.modelID = 42590;
			itemDef.modelZoom = 1625;
			itemDef.modelRotation1 = 355;
			itemDef.modelRotation2 = 2046;
			itemDef.modelOffset2 = -11;
			itemDef.modelOffset1 = 0;
			itemDef.anInt204 = 0;
			itemDef.maleEquip1 = 42632;
			itemDef.femaleEquip1 = 42649;
			itemDef.description = "Statius's platelegs, a reward from PVP.".getBytes();
			break;

		case 13896:
			itemDef.name = "Statius's full helm";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.modelID = 42596;
			itemDef.modelZoom = 789;
			itemDef.modelRotation1 = 96;
			itemDef.modelRotation2 = 2039;
			itemDef.modelOffset2 = -7;
			itemDef.modelOffset1 = 2;
			itemDef.anInt204 = 0;
			itemDef.maleEquip1 = 42639;
			itemDef.femaleEquip1 = 42655;
			itemDef.description = "Statius's full helm, a reward from PVP.".getBytes();
			break;
		case 13902:
			itemDef.name = "Statius's warhammer";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wield";
			itemDef.modelID = 42577;
			itemDef.modelZoom = 1360;
			itemDef.modelRotation1 = 507;
			itemDef.modelRotation2 = 27;
			itemDef.modelOffset2 = 6;
			itemDef.modelOffset1 = 7;
			itemDef.anInt204 = 0;
			itemDef.maleEquip1 = 9521;
			itemDef.femaleEquip1 = 9521;
			itemDef.description = "Statius's warhammer, a reward from PVP.".getBytes();
			break;
		case 13870:
			itemDef.name = "Morrigan's leather body";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.modelID = 42578;
			itemDef.modelZoom = 1184;
			itemDef.modelRotation1 = 545;
			itemDef.modelRotation2 = 2;
			itemDef.modelOffset2 = 5;
			itemDef.modelOffset1 = 4;
			itemDef.anInt204 = 0;
			itemDef.maleEquip1 = 42626;
			itemDef.femaleEquip1 = 42643;
			itemDef.description = "Morrigan's leather body, a reward from PVP.".getBytes();
			break;

		case 13873:
			itemDef.name = "Morrigan's leather chaps";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.modelID = 42603;
			itemDef.modelZoom = 1753;
			itemDef.modelRotation1 = 482;
			itemDef.modelRotation2 = 1;
			itemDef.modelOffset2 = 11;
			itemDef.modelOffset1 = -3;
			itemDef.anInt204 = 0;
			itemDef.maleEquip1 = 42631;
			itemDef.femaleEquip1 = 42646;
			itemDef.description = "Morrigan's leather chaps, a reward from PVP.".getBytes();
			break;

		case 13876:
			itemDef.name = "Morrigan's coif";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.modelID = 42583;
			itemDef.modelZoom = 592;
			itemDef.modelRotation1 = 537;
			itemDef.modelRotation2 = 5;
			itemDef.modelOffset2 = 6;
			itemDef.modelOffset1 = -3;
			itemDef.anInt204 = 0;
			itemDef.maleEquip1 = 42636;
			itemDef.femaleEquip1 = 42652;
			itemDef.description = "Morrigan's coif, a reward from PVP.".getBytes();
			break;
		case 13879:
			itemDef.name = "Morrigan's javelin";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wield";
			itemDef.modelID = 42592;
			itemDef.modelZoom = 1872;
			itemDef.modelRotation1 = 282;
			itemDef.modelRotation2 = 2009;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = 0;
			itemDef.anInt204 = 0;
			itemDef.maleEquip1 = 42613;
			itemDef.femaleEquip1 = 42613;
			itemDef.description = "Morrigan's javelin, a reward from PVP.".getBytes();
			break;
		case 13883:
			itemDef.name = "Morrigan's throwing axe";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wield";
			itemDef.modelID = 42582;
			itemDef.modelZoom = 976;
			itemDef.modelRotation1 = 672;
			itemDef.modelRotation2 = 2024;
			itemDef.modelOffset2 = 4;
			itemDef.modelOffset1 = -5;
			itemDef.anInt204 = 0;
			itemDef.maleEquip1 = 42611;
			itemDef.femaleEquip1 = 42611;
			itemDef.description = "Morrigan's throwing axe, a reward from PVP.".getBytes();
			break;
		case 13738:
			itemDef.actions = new String[5];//menu
			itemDef.actions[1] = "Wield";
			itemDef.modelID = 40922;//inventory/drop model
			itemDef.modelZoom = 1616;//Model Zoom
			itemDef.modelRotation1 = 396;//rotation 1
			itemDef.modelRotation2 = 1050;//rotation 2
			itemDef.modelOffset1 = -3;//model offset 1
			itemDef.modelOffset2 = 4;//model offset 2
			itemDef.maleEquip1 = 40944;//male wield ModelId
			itemDef.femaleEquip1 = 40944;//female wield ModelId
			itemDef.anInt188 = -1;//female sleeve
			itemDef.anInt164 = -1;//male sleeve
			itemDef.aByte154 = -10;
			itemDef.aByte205 = -10;
			itemDef.name = "Arcane spirit shield";//name
			itemDef.description = "It's a Arcane spirit shield.".getBytes();//name
		break;
		case 13744:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wield";
			itemDef.modelID = 40920;
			itemDef.modelZoom = 1616;
			itemDef.modelRotation1 = 396;
			itemDef.modelRotation2 = 1050;
			itemDef.modelOffset1 = -3;
			itemDef.modelOffset2 = 4;
			itemDef.maleEquip1 = 40940;
			itemDef.femaleEquip1 = 40940;
			itemDef.anInt188 = -1;
			itemDef.anInt164 = -1;
			itemDef.aByte154 = -10;
			itemDef.aByte205 = -10;
			itemDef.name = "Spectral spirit shield";
			itemDef.description = "It's a Spectral spirit shield.".getBytes();
		break;
		case 13740:
			itemDef.actions = new String[5];//menu
			itemDef.actions[1] = "Wield";
			itemDef.modelID = 40921;//inventory/drop model
			itemDef.modelZoom = 1616;//Model Zoom
			itemDef.modelRotation1 = 396;//rotation 1
			itemDef.modelRotation2 = 1050;//rotation 2
			itemDef.modelOffset1 = -3;//model offset 1
			itemDef.modelOffset2 = 4;//model offset 2
			itemDef.maleEquip1 = 40939;//male wield ModelId
			itemDef.femaleEquip1 = 40939;//female wield ModelId
			itemDef.anInt188 = -1;//female sleeve
			itemDef.anInt164 = -1;//male sleeve
			itemDef.aByte154 = -10;
			itemDef.aByte205 = -10;
			itemDef.name = "Divine spirit shield";//name
			itemDef.description = "It's a Divine spirit shield.".getBytes();//name
		break;
		case 13734:
			itemDef.actions = new String[5];//menu
			itemDef.actions[1] = "Wield";
			itemDef.modelID = 40919;//inventory/drop model
			itemDef.modelZoom = 1616;//Model Zoom
			itemDef.modelRotation1 = 396;//rotation 1
			itemDef.modelRotation2 = 1050;//rotation 2
			itemDef.modelOffset1 = -3;//model offset 1
			itemDef.modelOffset2 = 4;//model offset 2
			itemDef.maleEquip1 = 40943;//male wield ModelId
			itemDef.femaleEquip1 = 40943;//female wield ModelId
			itemDef.anInt188 = -1;//female sleeve
			itemDef.anInt164 = -1;//male sleeve
			itemDef.aByte154 = -10;
			itemDef.aByte205 = -10;
			itemDef.name = "Spirit shield";//name
			itemDef.description = "It's a Spirit shield.".getBytes();//name
		break;
		case 13736:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wield";
			itemDef.modelID = 40913;
			itemDef.modelZoom = 1616;
			itemDef.modelRotation1 = 396;
			itemDef.modelRotation2 = 1050;
			itemDef.modelOffset1 = -3;
			itemDef.modelOffset2 = 4;
			itemDef.maleEquip1 = 40941;
			itemDef.femaleEquip1 = 40941;
			itemDef.anInt188 = -1;
			itemDef.anInt164 = -1;
			itemDef.aByte154 = -10;
			itemDef.aByte205 = -10;
			itemDef.name = "Blessed spirit shield";
			itemDef.description = "It's a Blessed spirit shield.".getBytes();
		break;
		case 13742:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wield";
			itemDef.modelID = 40915;
			itemDef.modelZoom = 1616;
			itemDef.modelRotation1 = 396;
			itemDef.modelRotation2 = 1050;
			itemDef.modelOffset1 = -3;
			itemDef.modelOffset2 = 4;
			itemDef.maleEquip1 = 40942;
			itemDef.femaleEquip1 = 40942;
			itemDef.anInt188 = -1;
			itemDef.anInt164 = -1;
			itemDef.aByte154 = -10;
			itemDef.aByte205 = -10;
			itemDef.name = "Elysian spirit shield";
			itemDef.description = "It's an Elysian spirit shield.".getBytes();
		break;
		case 15272:
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Eat";
			itemDef.modelID = 48728;
			itemDef.modelZoom = 1460;
			itemDef.modelRotation1 = 499;
			itemDef.modelRotation2 = 1926;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = 0;
			itemDef.name = "Rocktail";
			itemDef.description = "Some nicely cooked rocktail.".getBytes();
		break;
		case 15273:
			itemDef.modelID = 48728;
			itemDef.modelZoom = 1460;
			itemDef.modelRotation1 = 499;
			itemDef.modelRotation2 = 1926;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = 0;
			itemDef.name = "Rocktail";
			itemDef.stackable = true;
			itemDef.description = "Exchange this at any bank for Rocktail.".getBytes();
		break;
		case 15271:
			itemDef.actions = new String[5];
			itemDef.modelID = 48722;
			itemDef.modelZoom = 1460;
			itemDef.modelRotation1 = 499;
			itemDef.modelRotation2 = 1926;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = 0;
			itemDef.name = "Raw rocktail";
			itemDef.description = "I should try cooking this.".getBytes();
		break;
		case 15274:
			itemDef.actions = new String[5];
			itemDef.modelID = 48725;
			itemDef.modelZoom = 1460;
			itemDef.modelRotation1 = 499;
			itemDef.modelRotation2 = 1926;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = 0;
			itemDef.name = "Burnt rocktail";
			itemDef.description = "Oops! Maybe a little less heat next time.".getBytes();
		break;
		case 14876:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Exchange";
			itemDef.modelID = 47258;
			itemDef.modelZoom = 1513;
			itemDef.modelRotation1 = 0;
			itemDef.modelRotation2 = 202;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = 43;
			itemDef.stackable = false;
			itemDef.name = "Ancient statuette";
			itemDef.description = "A mysterious statuette of ancient times.".getBytes();
		break;
		case 14877:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Exchange";
			itemDef.modelID = 47257;
			itemDef.modelZoom = 1360;
			itemDef.modelRotation1 = 81;
			itemDef.modelRotation2 = 337;
			itemDef.modelOffset2 = -27;
			itemDef.modelOffset1 = 0;
			itemDef.stackable = false;
			itemDef.name = "Seren statuette";
			itemDef.description = "A small statuette that appears to be entirely made of crystal.".getBytes();
		break;
		case 14878:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Exchange";
			itemDef.modelID = 47256;
			itemDef.modelZoom = 1360;
			itemDef.modelRotation1 = 0;
			itemDef.modelRotation2 = 148;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = -30;
			itemDef.stackable = false;
			itemDef.name = "Armadyl statuette";
			itemDef.description = "A dedication to Armadyl, carved from the wing bones of his fallen warriors.".getBytes();
		break;
		case 14879:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Exchange";
			itemDef.modelID = 47250;
			itemDef.modelZoom = 976;
			itemDef.modelRotation1 = 0;
			itemDef.modelRotation2 = 75;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = 33;
			itemDef.stackable = false;
			itemDef.name = "Zamorak statuette";
			itemDef.description = "A small obsidian statuette in the shape of a black demon. ".getBytes();
		break;
		case 14880:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Exchange";
			itemDef.modelID = 47248;
			itemDef.modelZoom = 1488;
			itemDef.modelRotation1 = 75;
			itemDef.modelRotation2 = 94;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = 43;
			itemDef.stackable = false;
			itemDef.name = "Saradomin statuette";
			itemDef.description = "An angel statuette dedicated to Saradomin.".getBytes();
		break;
		case 14881:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Exchange";
			itemDef.modelID = 47244;
			itemDef.modelZoom = 1360;
			itemDef.modelRotation1 = 153;
			itemDef.modelRotation2 = 1841;
			itemDef.modelOffset2 = -32;
			itemDef.modelOffset1 = 0;
			itemDef.stackable = false;
			itemDef.name = "Bandos statuette";
			itemDef.description = "A statuette resembling an ork-like creature.".getBytes();
		break;
		case 14882:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Exchange";
			itemDef.modelID = 47247;
			itemDef.modelZoom = 720;
			itemDef.modelRotation1 = 105;
			itemDef.modelRotation2 = 1653;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = 57;
			itemDef.stackable = false;
			itemDef.name = "Ruby chalice";
			itemDef.description = "A small obsidian challice with a finely cut ruby in it.".getBytes();
		break;
		case 14883:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Exchange";
			itemDef.modelID = 47252;
			itemDef.modelZoom = 1300;
			itemDef.modelRotation1 = 141;
			itemDef.modelRotation2 = 1949;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = 0;
			itemDef.stackable = false;
			itemDef.name = "Guthixian brazier";
			itemDef.description = "A bronze ritual brazier, trimmed with jade and emeralds.".getBytes();
		break;
		case 14884:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Exchange";
			itemDef.modelID = 47251;
			itemDef.modelZoom = 1032;
			itemDef.modelRotation1 = 364;
			itemDef.modelRotation2 = 1872;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = 5;
			itemDef.stackable = false;
			itemDef.name = "Armadyl totem";
			itemDef.description = "A painted, wooden eagle in fine condition.".getBytes();
		break;
		case 14885:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Exchange";
			itemDef.modelID = 47259;
			itemDef.modelZoom = 724;
			itemDef.modelRotation1 = 377;
			itemDef.modelRotation2 = 916;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = -1;
			itemDef.stackable = false;
			itemDef.name = "Zamorak medallion";
			itemDef.description = "A black, metal symbol decorated with three blood red rubies.".getBytes();
		break;
		case 14886:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Exchange";
			itemDef.modelID = 47246;
			itemDef.modelZoom = 1744;
			itemDef.modelRotation1 = 0;
			itemDef.modelRotation2 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = -32;
			itemDef.stackable = false;
			itemDef.name = "Saradomin carving";
			itemDef.description = "A wooden angel icon dedicated to Saradomin. ".getBytes();
		break;
		case 14887:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Exchange";
			itemDef.modelID = 47245;
			itemDef.modelZoom = 921;
			itemDef.modelRotation1 = 552;
			itemDef.modelRotation2 = 94;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = 1;
			itemDef.stackable = false;
			itemDef.name = "Bandos scrimshaw";
			itemDef.description = "A stylised ogre face, crafted out of bone.".getBytes();
		break;
		case 14888:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Exchange";
			itemDef.modelID = 47254;
			itemDef.modelZoom = 1347;
			itemDef.modelRotation1 = 81;
			itemDef.modelRotation2 = 1670;
			itemDef.modelOffset2 = 15;
			itemDef.modelOffset1 = 3;
			itemDef.stackable = false;
			itemDef.name = "Saradomin amphora";
			itemDef.description = "A ceramic vase with a Saradomin symbol painted on it.".getBytes();
		break;
		case 14889:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Exchange";
			itemDef.modelID = 47249;
			itemDef.modelZoom = 848;
			itemDef.modelRotation1 = 111;
			itemDef.modelRotation2 = 1347;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = -5;
			itemDef.stackable = false;
			itemDef.name = "Ancient psaltery bridge";
			itemDef.description = "A part of an old instrument.".getBytes();
		break;
		case 14890:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Exchange";
			itemDef.modelID = 47255;
			itemDef.modelZoom = 835;
			itemDef.modelRotation1 = 512;
			itemDef.modelRotation2 = 13;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = -1;
			itemDef.stackable = false;
			itemDef.name = "Bronzed dragon claw";
			itemDef.description = "This big claw was once part of a ceremonial necklace.".getBytes();
		break;
		case 14891:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Exchange";
			itemDef.modelID = 47243;
			itemDef.modelZoom = 1104;
			itemDef.modelRotation1 = 130;
			itemDef.modelRotation2 = 1820;
			itemDef.modelOffset2 = 20;
			itemDef.modelOffset1 = 0;
			itemDef.stackable = false;
			itemDef.name = "Third age carafe";
			itemDef.description = "A very old clay carafe.".getBytes();
		break;
		case 14892:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Exchange";
			itemDef.modelID = 47253;
			itemDef.modelZoom = 1360;
			itemDef.modelRotation1 = 512;
			itemDef.modelRotation2 = 417;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = -9;
			itemDef.stackable = false;
			itemDef.name = "Broken statue headdress";
			itemDef.description = "This was once part of a big statue.".getBytes();
		break;
		case 15000:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.femaleEquip1 = 56505;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = -12;
			itemDef.modelZoom = 976;
			itemDef.modelRotation2 = 51;
			itemDef.modelRotation1 = 510;
			itemDef.maleEquip1 = 55825;
			itemDef.modelID = 56779;
			itemDef.name = "Arcane stream necklace";
			itemDef.description = "The energy from this necklace is unlike anything you have ever felt.".getBytes();
		break;
		case 15001:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wield";
			itemDef.femaleEquip1 = 51802;//female
			itemDef.modelOffset1 = -5;
			itemDef.modelOffset2 = 2;
			itemDef.modelZoom = 1490;
			itemDef.modelRotation2 = 1400;
			itemDef.modelRotation1 = 148;
			itemDef.maleEquip1 = 51800;
			itemDef.modelID = 51799;
			itemDef.name = "@yel@Staff of light";
			itemDef.description = "Humming with power.".getBytes();
		break;
		case 15018:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -15;
			itemDef.modelZoom = 620;
			itemDef.modelRotation2 = 1940;
			itemDef.modelRotation1 = 340;
			itemDef.modelID = 9932;
			itemDef.name = "Seers' ring (i)";
			itemDef.description = "A mysterious ring that has been imbued.".getBytes();
		break;
		case 15019:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.modelOffset1 = 4;
			itemDef.modelOffset2 = -15;
			itemDef.modelZoom = 630;
			itemDef.modelRotation2 = 1904;
			itemDef.modelRotation1 = 332;
			itemDef.modelID = 9930;
			itemDef.name = "Archers' ring (i)";
			itemDef.description = "Improves the wearer's skill with a bow. This has been imbued.".getBytes();
		break;
		case 15020:
			itemDef.actions = new String[5];

			itemDef.actions[1] = "Wear";
			itemDef.modelOffset1 = 2;
			itemDef.modelOffset2 = -7;
			itemDef.modelZoom = 570;
			itemDef.modelRotation2 = 1776;
			itemDef.modelRotation1 = 348;
			itemDef.modelID = 9933;
			itemDef.name = "Warrior ring (i)";
			itemDef.description = "A legendary ring once worn by Fremennik warriors. This has been imbued.".getBytes();
		break;
		case 15220:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -15;
			itemDef.modelZoom = 600;
			itemDef.modelRotation2 = 1916;
			itemDef.modelRotation1 = 324;
			itemDef.modelID = 9931;
			itemDef.name = "Berserker ring (i)";
			itemDef.description = "A ring reputed to bring out a berserk fury in its wearer. This has been imbued".getBytes();
		break;
		case 14484:
                	itemDef.actions = new String[5];
                	itemDef.actions[1] = "Wield";
                	itemDef.modelID = 44590;
                	itemDef.maleEquip1 = 43660;//maleEquip1
                	itemDef.femaleEquip1 = 43660;//femaleEquip1
                	itemDef.modelZoom = 789;
                	itemDef.modelRotation1 = 240;
                	itemDef.modelRotation2 = 60;
                	itemDef.modelOffset1 = -1;
                	itemDef.modelOffset2 = -23;
                	itemDef.name = "Dragon claws";
                	itemDef.description = "A set of fighting claws.".getBytes();
                break;
		case 15037:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wield";
			itemDef.femaleEquip1 = 35085;//female
			itemDef.modelOffset1 = 9;
			itemDef.modelOffset2 = 13;
			itemDef.modelZoom = 1425;
			itemDef.modelRotation2 = 1300;
			itemDef.modelRotation1 = 700;
			itemDef.maleEquip1 = 35085;
			itemDef.modelID = 35084;
			itemDef.name = "Chaotic rapier";
			itemDef.aByte205 = -12;
			itemDef.aByte154 = -12;
			itemDef.anInt188 = -1;//female sleeve
			itemDef.anInt164 = -1;//male sleeve
			itemDef.description = "A razor-sharp rapier.".getBytes();
		break;
		case 15038:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wield";
			itemDef.femaleEquip1 = 35087;//female
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = 0;
			itemDef.modelZoom = 1650;
			itemDef.modelRotation2 = 1300;
			itemDef.modelRotation1 = 498;
			itemDef.maleEquip1 = 35087;
			itemDef.modelID = 35086;
			itemDef.name = "Chaotic longsword";
			itemDef.anInt188 = -1;//female sleeve
			itemDef.anInt164 = -1;//male sleeve
			itemDef.description = "A dangerously-sharp longsword.".getBytes();
		break;
		case 15040:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wield";
			itemDef.femaleEquip1 = 35091;//female
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = 0;
			itemDef.modelZoom = 1711;
			itemDef.modelRotation2 = 365;
			itemDef.modelRotation1 = 350;
			itemDef.maleEquip1 = 35091;
			itemDef.modelID = 35090;
			itemDef.name = "Chaotic staff";
			itemDef.aByte205 = -10;
			itemDef.aByte154 = -10;
			itemDef.anInt188 = -1;//female sleeve
			itemDef.anInt164 = -1;//male sleeve
			itemDef.description = "A staff used by the greatest of wizards.".getBytes();
		break;	
		}
		/* Hardcoded items start here */
		/**/
		return itemDef;
	}

	public void actionData(int a, String b) {
		actions = new String[5];
		actions[a] = b;
	}
	
	public void totalColors(int total) {
	   originalModelColors = new int[total];	   
	   modifiedModelColors = new int[total];
	}
	
	public void colors(int id, int original, int modified) {
		originalModelColors[id] = original;
		modifiedModelColors[id] = modified;
	}
	public void itemData(String n, String d) {
		name = n;
		description = d.getBytes();
	}
	public void models(int mID, int mE, int fE, int mE2, int fE2) {
		modelID = mID;
		maleEquip1 = mE;
		femaleEquip1 = fE;
		maleEquip2 = mE2;
		femaleEquip2 = fE2;
	}
	public void modelData(int mZ, int mR1, int mR2, int mO1, int mO2) {
		modelZoom = mZ;
		modelRotation1 = mR1;
		modelRotation2 = mR2;
		modelOffset1 = mO1;
		modelOffset2 = mO2;
	}

	public void toNote() {
		ItemDef itemDef = forID(certTemplateID);
		modelID = itemDef.modelID;
		modelZoom = itemDef.modelZoom;
		modelRotation1 = itemDef.modelRotation1;
		modelRotation2 = itemDef.modelRotation2;
		anInt204 = itemDef.anInt204;
		modelOffset1 = itemDef.modelOffset1;
		modelOffset2 = itemDef.modelOffset2;
		originalModelColors = itemDef.originalModelColors;
		modifiedModelColors = itemDef.modifiedModelColors;
		ItemDef itemDef_1 = forID(certID);
		name = itemDef_1.name;
		membersObject = itemDef_1.membersObject;
		value = itemDef_1.value;
		String s = "a";
		char c = itemDef_1.name.charAt(0);
		if(c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')
			s = "an";
		description = ("Swap this note at any bank for " + s + " " + itemDef_1.name + ".").getBytes();
		stackable = true;
	}

	public static Sprite getSprite(int i, int j, int k) {
		if(k == 0) {
			Sprite sprite = (Sprite) mruNodes1.insertFromCache(i);
			if(sprite != null && sprite.anInt1445 != j && sprite.anInt1445 != -1) {
				sprite.unlink();
				sprite = null;
			}
			if(sprite != null)
				return sprite;
		}
		ItemDef itemDef = forID(i);
		if(itemDef.stackIDs == null)
			j = -1;
		if(j > 1) {
			int i1 = -1;
			for(int j1 = 0; j1 < 10; j1++)
				if(j >= itemDef.stackAmounts[j1] && itemDef.stackAmounts[j1] != 0)
					i1 = itemDef.stackIDs[j1];
			if(i1 != -1)
				itemDef = forID(i1);
		}
		Model model = itemDef.method201(1);
		if(model == null)
			return null;
		Sprite sprite = null;
		if(itemDef.certTemplateID != -1) {
			sprite = getSprite(itemDef.certID, 10, -1);
			if(sprite == null)
				return null;
		}
		Sprite sprite2 = new Sprite(32, 32);
		int k1 = Texture.textureInt1;
		int l1 = Texture.textureInt2;
		int ai[] = Texture.anIntArray1472;
		int ai1[] = DrawingArea.pixels;
		int i2 = DrawingArea.width;
		int j2 = DrawingArea.height;
		int k2 = DrawingArea.topX;
		int l2 = DrawingArea.bottomX;
		int i3 = DrawingArea.topY;
		int j3 = DrawingArea.bottomY;
		Texture.aBoolean1464 = false;
		DrawingArea.initDrawingArea(32, 32, sprite2.myPixels);
		DrawingArea.drawPixels(32, 0, 0, 0, 32);
		Texture.method364();
		int k3 = itemDef.modelZoom;
		if(k == -1)
			k3 = (int)((double)k3 * 1.5D);
		if(k > 0)
			k3 = (int)((double)k3 * 1.04D);
		int l3 = Texture.anIntArray1470[itemDef.modelRotation1] * k3 >> 16;
		int i4 = Texture.anIntArray1471[itemDef.modelRotation1] * k3 >> 16;
		model.method482(itemDef.modelRotation2, itemDef.anInt204, itemDef.modelRotation1, itemDef.modelOffset1, l3 + model.modelHeight / 2 + itemDef.modelOffset2, i4 + itemDef.modelOffset2);
		for(int i5 = 31; i5 >= 0; i5--) {
			for(int j4 = 31; j4 >= 0; j4--)
				if(sprite2.myPixels[i5 + j4 * 32] == 0)
					if(i5 > 0 && sprite2.myPixels[(i5 - 1) + j4 * 32] > 1)
						sprite2.myPixels[i5 + j4 * 32] = 1;
					else if(j4 > 0 && sprite2.myPixels[i5 + (j4 - 1) * 32] > 1)
						sprite2.myPixels[i5 + j4 * 32] = 1;
					else if(i5 < 31 && sprite2.myPixels[i5 + 1 + j4 * 32] > 1)
						sprite2.myPixels[i5 + j4 * 32] = 1;
					else if(j4 < 31 && sprite2.myPixels[i5 + (j4 + 1) * 32] > 1)
						sprite2.myPixels[i5 + j4 * 32] = 1;
		}
		if(k > 0) {
			for(int j5 = 31; j5 >= 0; j5--) {
				for(int k4 = 31; k4 >= 0; k4--)
					if(sprite2.myPixels[j5 + k4 * 32] == 0)
						if(j5 > 0 && sprite2.myPixels[(j5 - 1) + k4 * 32] == 1)
							sprite2.myPixels[j5 + k4 * 32] = k;
						else if(k4 > 0 && sprite2.myPixels[j5 + (k4 - 1) * 32] == 1)
							sprite2.myPixels[j5 + k4 * 32] = k;
						else if(j5 < 31 && sprite2.myPixels[j5 + 1 + k4 * 32] == 1)
							sprite2.myPixels[j5 + k4 * 32] = k;
						else if(k4 < 31 && sprite2.myPixels[j5 + (k4 + 1) * 32] == 1)
							sprite2.myPixels[j5 + k4 * 32] = k;
			}
		} else if(k == 0) {
			for(int k5 = 31; k5 >= 0; k5--) {
				for(int l4 = 31; l4 >= 0; l4--)
					if(sprite2.myPixels[k5 + l4 * 32] == 0 && k5 > 0 && l4 > 0 && sprite2.myPixels[(k5 - 1) + (l4 - 1) * 32] > 0)
						sprite2.myPixels[k5 + l4 * 32] = 0x302020;
			}
		}
		if(itemDef.certTemplateID != -1) {
			int l5 = sprite.anInt1444;
			int j6 = sprite.anInt1445;
			sprite.anInt1444 = 32;
			sprite.anInt1445 = 32;
			sprite.drawSprite(0, 0);
			sprite.anInt1444 = l5;
			sprite.anInt1445 = j6;
		}
		if(k == 0)
			mruNodes1.removeFromCache(sprite2, i);
		DrawingArea.initDrawingArea(j2, i2, ai1);
		DrawingArea.setDrawingArea(j3, k2, l2, i3);
		Texture.textureInt1 = k1;
		Texture.textureInt2 = l1;
		Texture.anIntArray1472 = ai;
		Texture.aBoolean1464 = true;
		if(itemDef.stackable)
			sprite2.anInt1444 = 33;
		else
			sprite2.anInt1444 = 32;
		sprite2.anInt1445 = j;
		return sprite2;
	}

	public Model method201(int i) {
		if(stackIDs != null && i > 1) {
			int j = -1;
			for(int k = 0; k < 10; k++)
				if(i >= stackAmounts[k] && stackAmounts[k] != 0)
					j = stackIDs[k];
			if(j != -1)
				return forID(j).method201(1);
		}
		Model model = (Model) mruNodes2.insertFromCache(id);
		if(model != null)
			return model;
		model = Model.method462(modelID);
		if(model == null)
			return null;
		if(anInt167 != 128 || anInt192 != 128 || anInt191 != 128)
			model.method478(anInt167, anInt191, anInt192);
		if (originalModelColors != null) {
			for (int l = 0; l < originalModelColors.length; l++)
				model.method476(originalModelColors[l], modifiedModelColors[l]);

		}
		model.method479(64 + anInt196, 768 + anInt184, -500, -500, -150, true);
		model.aBoolean1659 = true;
		mruNodes2.removeFromCache(model, id);
		return model;
	}

	public Model method202(int i) {
		if(stackIDs != null && i > 1) {
			int j = -1;
			for(int k = 0; k < 10; k++)
				if(i >= stackAmounts[k] && stackAmounts[k] != 0)
					j = stackIDs[k];
			if(j != -1)
				return forID(j).method202(1);
		}
		Model model = Model.method462(modelID);
		if(model == null)
			return null;
		if (originalModelColors != null) {
			for (int l = 0; l < originalModelColors.length; l++)
				model.method476(originalModelColors[l], modifiedModelColors[l]);

		}
		return model;
	}

	public void readValues(Stream stream) {
		do {
			int i = stream.readUnsignedByte();
			if(i == 0)
				return;
			if(i == 1)
				modelID = stream.readUnsignedWord();
			else if(i == 2)
				name = stream.readString();
			else if(i == 3)
				description = stream.readBytes();
			else if(i == 4)
				modelZoom = stream.readUnsignedWord();
			else if(i == 5)
				modelRotation1 = stream.readUnsignedWord();
			else if(i == 6)
				modelRotation2 = stream.readUnsignedWord();
			else if(i == 7) {
				modelOffset1 = stream.readUnsignedWord();
				if(modelOffset1 > 32767)
					modelOffset1 -= 0x10000;
			} else if(i == 8) {
				modelOffset2 = stream.readUnsignedWord();
				if(modelOffset2 > 32767)
					modelOffset2 -= 0x10000;
			} else if(i == 10)
				stream.readUnsignedWord();
			else if(i == 11)
				stackable = true;
			else if(i == 12)
				value = stream.readDWord();
			else if(i == 16)
				membersObject = true;
			else if(i == 23) {
				maleEquip1 = stream.readUnsignedWord();
				aByte205 = stream.readSignedByte();
			} else if(i == 24)
				maleEquip2 = stream.readUnsignedWord();
			else if(i == 25) {
				femaleEquip1 = stream.readUnsignedWord();
				aByte154 = stream.readSignedByte();
			} else if(i == 26)
				femaleEquip2 = stream.readUnsignedWord();
			else if(i >= 30 && i < 35) {
				if(groundActions == null)
					groundActions = new String[5];
				groundActions[i - 30] = stream.readString();
				if(groundActions[i - 30].equalsIgnoreCase("hidden"))
					groundActions[i - 30] = null;
			} else if(i >= 35 && i < 40) {
				if(actions == null)
					actions = new String[5];
				actions[i - 35] = stream.readString();
			} else if(i == 40) {
				int j = stream.readUnsignedByte();
				originalModelColors = new int[j];
				modifiedModelColors = new int[j];
				for(int k = 0; k < j; k++) {
					originalModelColors[k] = stream.readUnsignedWord();
					modifiedModelColors[k] = stream.readUnsignedWord();
				}
			} else if(i == 78)
				anInt185 = stream.readUnsignedWord();
			else if(i == 79)
				anInt162 = stream.readUnsignedWord();
			else if(i == 90)
				anInt175 = stream.readUnsignedWord();
			else if(i == 91)
				anInt197 = stream.readUnsignedWord();
			else if(i == 92)
				anInt166 = stream.readUnsignedWord();
			else if(i == 93)
				anInt173 = stream.readUnsignedWord();
			else if(i == 95)
				anInt204 = stream.readUnsignedWord();
			else if(i == 97)
				certID = stream.readUnsignedWord();
			else if(i == 98)
				certTemplateID = stream.readUnsignedWord();
			else if(i >= 100 && i < 110) {
				if(stackIDs == null) {
					stackIDs = new int[10];
					stackAmounts = new int[10];
				}
				stackIDs[i - 100] = stream.readUnsignedWord();
				stackAmounts[i - 100] = stream.readUnsignedWord();
			} else if(i == 110)
				anInt167 = stream.readUnsignedWord();
			else if(i == 111)
				anInt192 = stream.readUnsignedWord();
			else if(i == 112)
				anInt191 = stream.readUnsignedWord();
			else if(i == 113)
				anInt196 = stream.readSignedByte();
			else if(i == 114)
				anInt184 = stream.readSignedByte() * 5;
			else if(i == 115)
				team = stream.readUnsignedByte();
		} while(true);
	}

	public ItemDef() {
		id = -1;
	}

	public byte aByte154;
	public int value;
	public int[] modifiedModelColors;
	public int id;
	static MRUNodes mruNodes1 = new MRUNodes(100);
	public static MRUNodes mruNodes2 = new MRUNodes(50);
	public int[] originalModelColors;
	public boolean membersObject;
	public int anInt162;
	public int certTemplateID;
	public int femaleEquip2;
	public int maleEquip1;
	public int anInt166;
	public int anInt167;
	public String groundActions[];
	public int modelOffset1;
	public String name;
	public static ItemDef[] cache;
	public int anInt173;
	public int modelID;
	public int anInt175;
	public boolean stackable;
	public byte description[];
	public int certID;
	public static int cacheIndex;
	public int modelZoom;
	public static boolean isMembers = true;
	public static Stream stream;
	public int anInt184;
	public int anInt185;
	public int maleEquip2;
	public String actions[];
	public int modelRotation1;
	public int anInt191;
	public int anInt192;
	public int[] stackIDs;
	public int modelOffset2;
	public static int[] streamIndices;
	public int anInt196;
	public int anInt197;
	public int modelRotation2;
	public int femaleEquip1;
	public int[] stackAmounts;
	public int team;
	public static int totalItems;
	public int anInt204;
	public byte aByte205;
	public int anInt164;
	public int anInt199;
	public int anInt188;
}