// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public final class RSInterface {

	public void swapInventoryItems(int i, int j) {
		int k = inv[i];
		inv[i] = inv[j];
		inv[j] = k;
		k = invStackSizes[i];
		invStackSizes[i] = invStackSizes[j];
		invStackSizes[j] = k;
	}

	public static void unpack(StreamLoader streamLoader, TextDrawingArea textDrawingAreas[], StreamLoader streamLoader_1) {
		aMRUNodes_238 = new MRUNodes(50000);
		Stream stream = new Stream(streamLoader.getDataForName("data"));
		int i = -1;
		int j = stream.readUnsignedWord();
		interfaceCache = new RSInterface[31000];
		while(stream.currentOffset < stream.buffer.length) {
			int k = stream.readUnsignedWord();
			if(k == 65535) {
				i = stream.readUnsignedWord();
				k = stream.readUnsignedWord();
			}
			RSInterface rsInterface = interfaceCache[k] = new RSInterface();
			rsInterface.id = k;
			rsInterface.parentID = i;
			rsInterface.type = stream.readUnsignedByte();
			rsInterface.atActionType = stream.readUnsignedByte();
			rsInterface.contentType = stream.readUnsignedWord();
			rsInterface.width = stream.readUnsignedWord();
			rsInterface.height = stream.readUnsignedWord();
			rsInterface.aByte254 = (byte) stream.readUnsignedByte();
			rsInterface.mOverInterToTrigger = stream.readUnsignedByte();
			if(rsInterface.mOverInterToTrigger != 0)
				rsInterface.mOverInterToTrigger = (rsInterface.mOverInterToTrigger - 1 << 8) + stream.readUnsignedByte();
			else
				rsInterface.mOverInterToTrigger = -1;
			int i1 = stream.readUnsignedByte();
			if(i1 > 0) {
				rsInterface.anIntArray245 = new int[i1];
				rsInterface.anIntArray212 = new int[i1];
				for(int j1 = 0; j1 < i1; j1++) {
					rsInterface.anIntArray245[j1] = stream.readUnsignedByte();
					rsInterface.anIntArray212[j1] = stream.readUnsignedWord();
				}

			}
			int k1 = stream.readUnsignedByte();
			if(k1 > 0) {
				rsInterface.valueIndexArray = new int[k1][];
				for(int l1 = 0; l1 < k1; l1++) {
					int i3 = stream.readUnsignedWord();
					rsInterface.valueIndexArray[l1] = new int[i3];
					for(int l4 = 0; l4 < i3; l4++)
						rsInterface.valueIndexArray[l1][l4] = stream.readUnsignedWord();

				}

			}
			if(rsInterface.type == 0) {
				rsInterface.drawsTransparent = false;
				rsInterface.scrollMax = stream.readUnsignedWord();
				rsInterface.isMouseoverTriggered = stream.readUnsignedByte() == 1;
				int i2 = stream.readUnsignedWord();
				rsInterface.children = new int[i2];
				rsInterface.childX = new int[i2];
				rsInterface.childY = new int[i2];
				for(int j3 = 0; j3 < i2; j3++) {
					rsInterface.children[j3] = stream.readUnsignedWord();
					rsInterface.childX[j3] = stream.readSignedWord();
					rsInterface.childY[j3] = stream.readSignedWord();
				}
			}
			if(rsInterface.type == 1) {
				stream.readUnsignedWord();
				stream.readUnsignedByte();
			}
			if(rsInterface.type == 2) {
				rsInterface.inv = new int[rsInterface.width * rsInterface.height];
				rsInterface.invStackSizes = new int[rsInterface.width * rsInterface.height];
				rsInterface.aBoolean259 = stream.readUnsignedByte() == 1;
				rsInterface.isInventoryInterface = stream.readUnsignedByte() == 1;
				rsInterface.usableItemInterface = stream.readUnsignedByte() == 1;
				rsInterface.aBoolean235 = stream.readUnsignedByte() == 1;
				rsInterface.invSpritePadX = stream.readUnsignedByte();
				rsInterface.invSpritePadY = stream.readUnsignedByte();
				rsInterface.spritesX = new int[20];
				rsInterface.spritesY = new int[20];
				rsInterface.sprites = new Sprite[20];
				for(int j2 = 0; j2 < 20; j2++) {
					int k3 = stream.readUnsignedByte();
					if(k3 == 1) {
						rsInterface.spritesX[j2] = stream.readSignedWord();
						rsInterface.spritesY[j2] = stream.readSignedWord();
						String s1 = stream.readString();
						if(streamLoader_1 != null && s1.length() > 0) {
							int i5 = s1.lastIndexOf(",");
							rsInterface.sprites[j2] = method207(Integer.parseInt(s1.substring(i5 + 1)), streamLoader_1, s1.substring(0, i5));
						}
					}
				}
				rsInterface.actions = new String[5];
				for(int l3 = 0; l3 < 5; l3++) {
					rsInterface.actions[l3] = stream.readString();
					if(rsInterface.actions[l3].length() == 0)
						rsInterface.actions[l3] = null;
					if(rsInterface.parentID == 1644)
						rsInterface.actions[2] = "Operate";
					if(rsInterface.parentID == 3824) 
                                        	rsInterface.actions[4] = "Buy 100";
				}
			}
			if(rsInterface.type == 3)
				rsInterface.aBoolean227 = stream.readUnsignedByte() == 1;
			if(rsInterface.type == 4 || rsInterface.type == 1) {
				rsInterface.centerText = stream.readUnsignedByte() == 1;
				int k2 = stream.readUnsignedByte();
				if(textDrawingAreas != null)
					rsInterface.textDrawingAreas = textDrawingAreas[k2];
				rsInterface.textShadow = stream.readUnsignedByte() == 1;
			}
			if(rsInterface.type == 4) {
				rsInterface.message = stream.readString();
				rsInterface.aString228 = stream.readString();
			}
			if(rsInterface.type == 1 || rsInterface.type == 3 || rsInterface.type == 4)
				rsInterface.textColor = stream.readDWord();
			if(rsInterface.type == 3 || rsInterface.type == 4) {
				rsInterface.anInt219 = stream.readDWord();
				rsInterface.anInt216 = stream.readDWord();
				rsInterface.anInt239 = stream.readDWord();
			}
			if(rsInterface.type == 5) {
				rsInterface.drawsTransparent = false;
				String s = stream.readString();
				if(streamLoader_1 != null && s.length() > 0) {
					int i4 = s.lastIndexOf(",");
					rsInterface.sprite1 = method207(Integer.parseInt(s.substring(i4 + 1)), streamLoader_1, s.substring(0, i4));
				}
				s = stream.readString();
				if(streamLoader_1 != null && s.length() > 0) {
					int j4 = s.lastIndexOf(",");
					rsInterface.sprite2 = method207(Integer.parseInt(s.substring(j4 + 1)), streamLoader_1, s.substring(0, j4));
				}
			}
			if(rsInterface.type == 6) {
				int l = stream.readUnsignedByte();
				if(l != 0) {
					rsInterface.anInt233 = 1;
					rsInterface.mediaID = (l - 1 << 8) + stream.readUnsignedByte();
				}
				l = stream.readUnsignedByte();
				if(l != 0) {
					rsInterface.anInt255 = 1;
					rsInterface.anInt256 = (l - 1 << 8) + stream.readUnsignedByte();
				}
				l = stream.readUnsignedByte();
				if(l != 0)
					rsInterface.anInt257 = (l - 1 << 8) + stream.readUnsignedByte();
				else
					rsInterface.anInt257 = -1;
				l = stream.readUnsignedByte();
				if(l != 0)
					rsInterface.anInt258 = (l - 1 << 8) + stream.readUnsignedByte();
				else
					rsInterface.anInt258 = -1;
				rsInterface.modelZoom = stream.readUnsignedWord();
				rsInterface.modelRotation1 = stream.readUnsignedWord();
				rsInterface.modelRotation2 = stream.readUnsignedWord();
			}
			if(rsInterface.type == 7) {
				rsInterface.inv = new int[rsInterface.width * rsInterface.height];
				rsInterface.invStackSizes = new int[rsInterface.width * rsInterface.height];
				rsInterface.centerText = stream.readUnsignedByte() == 1;
				int l2 = stream.readUnsignedByte();
				if(textDrawingAreas != null)
					rsInterface.textDrawingAreas = textDrawingAreas[l2];
				rsInterface.textShadow = stream.readUnsignedByte() == 1;
				rsInterface.textColor = stream.readDWord();
				rsInterface.invSpritePadX = stream.readSignedWord();
				rsInterface.invSpritePadY = stream.readSignedWord();
				rsInterface.isInventoryInterface = stream.readUnsignedByte() == 1;
				rsInterface.actions = new String[5];
				for(int k4 = 0; k4 < 5; k4++) {
					rsInterface.actions[k4] = stream.readString();
					if(rsInterface.actions[k4].length() == 0)
						rsInterface.actions[k4] = null;
				}

			}
			if(rsInterface.atActionType == 2 || rsInterface.type == 2) {
				rsInterface.selectedActionName = stream.readString();
				rsInterface.spellName = stream.readString();
				rsInterface.spellUsableOn = stream.readUnsignedWord();
			}

			if(rsInterface.type == 8)
				rsInterface.message = stream.readString();

			if(rsInterface.atActionType == 1 || rsInterface.atActionType == 4 || rsInterface.atActionType == 5 || rsInterface.atActionType == 6) {
				rsInterface.tooltip = stream.readString();
				if(rsInterface.tooltip.length() == 0) {
					if(rsInterface.atActionType == 1)
						rsInterface.tooltip = "Ok";
					if(rsInterface.atActionType == 4)
						rsInterface.tooltip = "Select";
					if(rsInterface.atActionType == 5)
						rsInterface.tooltip = "Select";
					if(rsInterface.atActionType == 6)
						rsInterface.tooltip = "Continue";
				}
			}
		}
		aClass44 = streamLoader;
		EquipmentTab(textDrawingAreas);
		prayerTab(textDrawingAreas);
		emoteTab();
		optionTab(textDrawingAreas);
		clanChatTab(textDrawingAreas);
		Sidebar0(textDrawingAreas);
		friendsTab(textDrawingAreas);
        		ignoreTab(textDrawingAreas);
		Pestpanel(textDrawingAreas);
		Pestpanel2(textDrawingAreas);
		EquipmentScreen(textDrawingAreas);
		customPortal(textDrawingAreas);
		customPortal2(textDrawingAreas);
		magicTab(textDrawingAreas);
		ancientMagicTab(textDrawingAreas);
		configureLunar(textDrawingAreas);
		Curses(textDrawingAreas);
		achievementTab(textDrawingAreas);
		constructLunar();
		questTab(textDrawingAreas);
		PVPInterface2(textDrawingAreas);
		PVPInterface(textDrawingAreas);
		overloadxTab(textDrawingAreas);
		ColorChanger(textDrawingAreas);
		vote(textDrawingAreas);
		aMRUNodes_238 = null;
	}
	public static void addPrayer(int i, int configId, int configFrame, int requiredValues, int prayerSpriteID, String PrayerName, int Hover) {
		RSInterface Interface = addTabInterface(i);
		Interface.id = i;
		Interface.parentID = 22500;
		Interface.type = 5;
		Interface.atActionType = 4;
		Interface.contentType = 0;
		Interface.aByte254 = 0;
		Interface.mOverInterToTrigger = Hover;
		Interface.sprite1 = imageLoader(0, "Curses/GLOW");
		Interface.sprite2 = imageLoader(1, "Curses/GLOW");
		Interface.width = 34;
		Interface.height = 34;
		Interface.anIntArray245 = new int[1];
		Interface.anIntArray212 = new int[1];
		Interface.anIntArray245[0] = 1;
		Interface.anIntArray212[0] = configId;
		Interface.valueIndexArray = new int[1][3];
		Interface.valueIndexArray[0][0] = 5;
		Interface.valueIndexArray[0][1] = configFrame;
		Interface.valueIndexArray[0][2] = 0;
		Interface.tooltip = "Activate@or1@ " + PrayerName;
		Interface = addTabInterface(i + 1);
		Interface.id = i + 1;
		Interface.parentID = 22500;
		Interface.type = 5;
		Interface.atActionType = 0;
		Interface.contentType  = 0;
		Interface.aByte254 = 0;
		Interface.sprite1 = imageLoader(prayerSpriteID, "Curses/PRAYON");
		Interface.sprite2 = imageLoader(prayerSpriteID, "Curses/PRAYOFF");
		Interface.width = 34;
		Interface.height = 34;
		Interface.anIntArray245 = new int[1];
		Interface.anIntArray212 = new int[1];
		Interface.anIntArray245[0] = 2;
		Interface.anIntArray212[0] = requiredValues + 1;
		Interface.valueIndexArray = new int[1][3];
		Interface.valueIndexArray[0][0] = 2;
		Interface.valueIndexArray[0][1] = 5;
		Interface.valueIndexArray[0][2] = 0;
	}
	public static void addTooltip(int id, String text, int H, int W) {
		RSInterface rsi = addTabInterface(id);
		rsi.id = id;
		rsi.type = 0;
		rsi.isMouseoverTriggered = true;
		rsi.mOverInterToTrigger = -1;
		addTooltipBox(id + 1, text);
		rsi.totalChildren(1);
		rsi.child(0, id + 1, 0, 0);
		rsi.height = H;
		rsi.width = W;
	}
       public static void addClickableText(int id, String text, String tooltip, TextDrawingArea tda[], int idx, int color, int width, int height)
    {
        RSInterface Tab = addTabInterface(id);
        Tab.parentID = id;
        Tab.id = id;
        Tab.type = 4;
        Tab.atActionType = 1;
        Tab.width = width;
        Tab.height = height;
        Tab.contentType = 0;
        Tab.aByte254 = 0;
        Tab.mOverInterToTrigger = -1;
        Tab.centerText = false;
        Tab.textShadow = true;
        Tab.textDrawingAreas = tda[idx];
        Tab.message = text;
        Tab.tooltip = tooltip;
        Tab.aString228 = "";
        Tab.textColor = color;
        Tab.anInt219 = 0;
        Tab.anInt216 = 0xFFFFFF;
        Tab.anInt239 = 0;
    }
	public static void magicTab(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(1151);
		RSInterface homeHover = addTabInterface(1196);
		RSInterface spellButtons = interfaceCache[12424];
		spellButtons.scrollMax = 0; spellButtons.height = 260; spellButtons.width = 190;
		int[] spellButton = {
			1196, 1199, 1206, 1215, 1224, 1231, 1240, 1249, 1258, 1267, 1274, 1283, 1573,
			1290, 1299, 1308, 1315, 1324, 1333, 1340, 1349, 1358, 1367, 1374, 1381, 1388,
			1397, 1404, 1583, 12038, 1414, 1421, 1430, 1437, 1446, 1453, 1460, 1469, 15878,
			1602, 1613, 1624, 7456, 1478, 1485, 1494, 1503, 1512, 1521, 1530, 1544, 1553,
			1563, 1593, 1635, 12426, 12436, 12446, 12456, 6004, 18471
		};
		tab.totalChildren(63);
		tab.child(0, 12424, 13, 24);
		for(int i1 = 0; i1 < spellButton.length; i1++) {
			int yPos = i1 > 34 ? 8 : 183;
			tab.child(1, 1195, 13, 24);
			tab.child(i1 + 2, spellButton[i1], 5, yPos);
			addButton(1195, 1, "Magic/Home", "Cast @gre@Home Teleport");
			RSInterface homeButton = interfaceCache[1195];
			homeButton.mOverInterToTrigger = 1196;
		}
		for(int i2 = 0; i2 < spellButton.length; i2++) {
			if(i2 < 60)
				spellButtons.childX[i2] = spellButtons.childX[i2] + 24;
			if(i2 == 6 || i2 == 12 || i2 == 19 || i2 == 35 || i2 == 41 || i2 == 44 || i2 == 49 || i2 == 51)
				spellButtons.childX[i2] = 0;
			spellButtons.childY[6] = 24; spellButtons.childY[12] = 48;
			spellButtons.childY[19] = 72; spellButtons.childY[49] = 96;
			spellButtons.childY[44] = 120; spellButtons.childY[51] = 144;
			spellButtons.childY[35] = 170; spellButtons.childY[41] = 192;
		}
		homeHover.isMouseoverTriggered = true;
		addText(1197, "Level 0: Home Teleport", tda, 1, 0xFE981F, true, true);
		RSInterface homeLevel = interfaceCache[1197]; homeLevel.width = 174; homeLevel.height = 68;
		addText(1198, "A teleport which requires no", tda, 0, 0xAF6A1A, true, true);
		addText(18998, "runes and no required level that", tda, 0, 0xAF6A1A, true, true);
		addText(18999, "teleports you to the main land.", tda, 0, 0xAF6A1A, true, true);
		homeHover.totalChildren(4);
		homeHover.child(0, 1197, 3, 4);
		homeHover.child(1, 1198, 91, 23);
		homeHover.child(2, 18998, 91, 34);
		homeHover.child(3, 18999, 91, 45);
	}
	
	public static void ancientMagicTab(TextDrawingArea[] tda) {
		RSInterface tab = addInterface(12855);
		addButton(12856, 1, "Magic/Home", "Cast @gre@Home Teleport");
		RSInterface homeButton = interfaceCache[12856]; 
		homeButton.mOverInterToTrigger = 1196;
		int[] itfChildren = {
			12856, 12939, 12987, 13035, 12901, 12861, 13045, 12963, 13011, 13053, 12919, 12881, 13061,
			12951, 12999, 13069, 12911, 12871, 13079, 12975, 13023, 13087, 12929, 12891, 13095, 1196,
			12940, 12988, 13036, 12902, 12862, 13046, 12964, 13012, 13054, 12920, 12882, 13062, 12952,
			13000, 13070, 12912, 12872, 13080, 12976, 13024, 13088, 12930, 12892, 13096
		};
		tab.totalChildren(itfChildren.length);
		for(int i1 = 0, xPos = 18, yPos = 8; i1 < itfChildren.length; i1++, xPos += 45) {
			if(xPos > 175) {
				xPos = 18; yPos += 28;
			}
			if(i1 < 25)
				tab.child(i1, itfChildren[i1], xPos, yPos);
			if(i1 > 24) {
				yPos = i1 < 41 ? 181 : 1;
				tab.child(i1, itfChildren[i1], 4, yPos);
			}
		}
	}
	
	public static void drawBlackBox(int xPos, int yPos) {
        ///Light Coloured Borders\\\
		DrawingArea.drawPixels(71, yPos - 1, xPos - 2, 0x726451, 1); // Left line
		DrawingArea.drawPixels(69, yPos, xPos + 174, 0x726451, 1); // Right line
		DrawingArea.drawPixels(1, yPos - 2, xPos - 2, 0x726451, 178); // Top Line
		DrawingArea.drawPixels(1, yPos + 68, xPos, 0x726451, 174); // Bottom Line

        ///Dark Coloured Borders\\\
		DrawingArea.drawPixels(71, yPos - 1, xPos - 1, 0x2E2B23, 1); // Left line
		DrawingArea.drawPixels(71, yPos - 1, xPos + 175, 0x2E2B23, 1); // Right line
		DrawingArea.drawPixels(1, yPos - 1, xPos, 0x2E2B23, 175); // Top line
		DrawingArea.drawPixels(1, yPos + 69, xPos, 0x2E2B23, 175); // Top line

        ///Black Box\\\
        DrawingArea.method335(0x000000, yPos, 174, 68, 220, xPos); //Yes method335 is galkons opacity method
    }
	public static void questTab(TextDrawingArea[] tda) {
        RSInterface tab = addTabInterface(638);
        RSInterface list = addTabInterface(16025);
        addText(640, "Quests", tda, 2, 0xeb981f, false, true);
        addText(663, "Free", tda, 2, 0xff9900, false, true);
        addText(682, "Members", tda, 2, 0xff9900, false, true);
        addSprite(16022, 4, "SPRITE");
        addText(16023, "Quest Points: ---", tda, 0, 0xeb981f, false, true);
        addSprite(16024, 5, "SPRITE");
        tab.totalChildren(6);
        tab.child(0, 640, 5, 5);
        tab.child(1, 16024, 0, 25);
        tab.child(2, 16025, 6, 24);
        tab.child(3, 16022, 0, 22);
        tab.child(4, 16022, 0, 249);
        tab.child(5, 16023, 4, 251);
        /* List/scrollbar */
        for(int i = 16026; i <= 16125; i++) {
            addClickableText(i, ""+i, "Show", tda, 0, 0xff0000, 90, 150);
        }
        	/*addClickableText(15036, "Hard", "Read Journal", tda, 0, 0xff0000, 90, 11);
	addClickableText(16026, "Hard", "Show", tda, 2, 0xeb981f, false, true, 150);	//LINE 1
		addClickableText(16027, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 2
		addClickableText(16028, "", "Show", tda, 2, 0xeb981f, false, true, 150);	//LINE 3
		addClickableText(16029, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 4
		addClickableText(16030, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 5
		addClickableText(16031, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 6
		addClickableText(16032, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 7
		addClickableText(16033, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 8
		addClickableText(16034, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 9
		addClickableText(16035, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 10
		addClickableText(16036, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 11
		addClickableText(16037, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 12
		addClickableText(16038, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 13
		addClickableText(16039, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 14
		addClickableText(16040, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 15
		addClickableText(16041, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 16
		addClickableText(16042, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 17
		addClickableText(16043, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 18
		addClickableText(16044, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 19
		addClickableText(16045, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 20
		addClickableText(16046, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 21
		addClickableText(16047, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 22
		addClickableText(16048, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 23
		addClickableText(16049, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 24
		addClickableText(16050, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 25
		addClickableText(16051, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 26
		addClickableText(16052, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 27
		addClickableText(16053, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 28
		addClickableText(16054, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 29
		addClickableText(16055, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 30
		addClickableText(16056, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 31
		addClickableText(16057, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 32
		addClickableText(16058, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 33
		addClickableText(16059, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 34
		addClickableText(16060, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 35
		addClickableText(16061, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 36
		addClickableText(16062, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 37
		addClickableText(16063, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 38
		addClickableText(16064, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 39
		addClickableText(16065, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 40
		addClickableText(16066, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 41
		addClickableText(16067, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 42
		addClickableText(16068, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 43
		addClickableText(16069, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 44
		addClickableText(16070, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 45
		addClickableText(16071, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 46
		addClickableText(16072, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 47
		addClickableText(16073, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 48
		addClickableText(16074, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 49
		addClickableText(16075, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 50
		addClickableText(16076, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 51
		addClickableText(16077, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 52
		addClickableText(16078, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 53
		addClickableText(16079, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 54
		addClickableText(16080, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 55
		addClickableText(16081, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 56
		addClickableText(16082, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 57
		addClickableText(16083, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 58
		addClickableText(16084, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 59
		addClickableText(16085, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 60
		addClickableText(16086, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 61
		addClickableText(16087, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 62
		addClickableText(16088, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 63
		addClickableText(16089, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 64
		addClickableText(16090, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 65
		addClickableText(16091, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 66
		addClickableText(16092, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 67
		addClickableText(16093, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 68
		addClickableText(16094, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 69
		addClickableText(16095, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 70
		addClickableText(16096, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 71
		addClickableText(16097, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 72
		addClickableText(16098, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 73
		addClickableText(16099, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 74
		addClickableText(16100, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 75
		addClickableText(16101, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 76
		addClickableText(16102, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 77
		addClickableText(16103, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 78
		addClickableText(16104, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 79
		addClickableText(16105, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 80
		addClickableText(16106, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 81
		addClickableText(16107, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 82
		addClickableText(16108, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 83
		addClickableText(16109, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 84
		addClickableText(16110, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 85
		addClickableText(16111, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 86
		addClickableText(16112, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 87
		addClickableText(16113, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 88
		addClickableText(16114, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 89
		addClickableText(16115, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 90
		addClickableText(16116, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 91
		addClickableText(16117, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 92
		addClickableText(16118, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 93
		addClickableText(16119, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 94
		addClickableText(16120, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 95
		addClickableText(16121, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 96
		addClickableText(16122, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 97
		addClickableText(16123, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 98
		addClickableText(16124, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 99
		addClickableText(16125, "", "Show", tda, 0, 0xff0000, false, true, 150);	//LINE 100*/
        list.totalChildren(101);
        list.child(0, 663, 4, 2);
        for(int id = 1, cid = 16026; id <= 100 && cid <= 16125; id++, cid++) {
            list.childY[1] = 18;
            list.child(id, cid, 9, list.childY[id - 1] + 13);
        }
        list.width = 168;
        list.height = 225;
        list.scrollMax = 1320;
    }
	public static void addButton(int id, int sid, String spriteName, String tooltip) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 1;
		tab.contentType = 0;
		tab.aByte254 = (byte)0;
		tab.mOverInterToTrigger = 52;
		tab.sprite1 = imageLoader(sid, spriteName);
		tab.sprite2 = imageLoader(sid, spriteName);
		tab.width = tab.sprite1.myWidth;
		tab.height = tab.sprite2.myHeight;
		tab.tooltip = tooltip;
	}
	public static void addToggleButton(int id, int bID, int bID2, String bName, String tT, int configID, int aT, int configFrame) {
		RSInterface tab = addTabInterface(id);
		tab.parentID = id;
		tab.id = id;
		tab.type = 5;
		tab.atActionType = aT;
		tab.contentType = 0;// anInt214
		tab.aByte254 = 0;
		tab.mOverInterToTrigger = -1;// anInt230
		tab.anIntArray212 = new int[1];
		tab.anIntArray245 = new int[1];
		tab.anIntArray212[0] = 1;
		tab.anIntArray245[0] = configID;
		tab.valueIndexArray = new int[1][3];
		tab.valueIndexArray[0][0] = 5;
		tab.valueIndexArray[0][1] = configFrame;
		tab.valueIndexArray[0][2] = 0;
		tab.sprite1 = imageLoader(bID, bName);
		tab.sprite2 = imageLoader(bID2, bName);
		tab.width = tab.sprite1.myWidth;
		tab.height = tab.sprite1.myHeight;
		tab.tooltip = tT;
	}
	public static void ColorChanger(TextDrawingArea[] TDA) {
		RSInterface tab = addTabInterface(17000);
		addSprite(17001, 18, "PrivateChat/CUSTOM");
		addHoverButton(17002, "PrivateChat/CUSTOM", 1, 16, 16, "1", 0, 17003, 1);
		addHoveredButton(17003, "PrivateChat/CUSTOM", 0, 19, 20, 17004);
		addHoverButton(17005, "/PrivateChat/CUSTOM", 2, 16, 16, "1", 0, 17006, 1);
		addHoveredButton(17006, "/PrivateChat/CUSTOM", 0, 19, 20, 17007);
		addHoverButton(17008, "/PrivateChat/CUSTOM", 3, 16, 16, "1", 0, 17009, 1);
		addHoveredButton(17009, "/PrivateChat/CUSTOM", 0, 19, 20, 17010);
		addHoverButton(17011, "/PrivateChat/CUSTOM", 4, 16, 16, "1", 0, 17012, 1);
		addHoveredButton(17012, "/PrivateChat/CUSTOM", 0, 19, 20, 17013);
		addHoverButton(17014, "/PrivateChat/CUSTOM", 5, 16, 16, "1", 0, 17015, 1);
		addHoveredButton(17015, "/PrivateChat/CUSTOM", 0, 19, 20, 17016);
		addHoverButton(17017, "/PrivateChat/CUSTOM", 6, 16, 16, "1", 0, 17018, 1);
		addHoveredButton(17018, "/PrivateChat/CUSTOM", 0, 19, 20, 17019);
		addHoverButton(17020, "/PrivateChat/CUSTOM", 7, 16, 16, "1", 0, 17021, 1);
		addHoveredButton(17021, "/PrivateChat/CUSTOM", 0, 19, 20, 17022);
		addHoverButton(17023, "/PrivateChat/CUSTOM", 8, 16, 16, "1", 0, 17024, 1);
		addHoveredButton(17024, "/PrivateChat/CUSTOM", 0, 19, 20, 17025);
		addHoverButton(17026, "/PrivateChat/CUSTOM", 9, 16, 16, "1", 0, 17027, 1);
		addHoveredButton(17027, "/PrivateChat/CUSTOM", 0, 19, 20, 17028);
		addHoverButton(17029, "/PrivateChat/CUSTOM", 10, 16, 16, "1", 0, 17030, 1);
		addHoveredButton(17030, "/PrivateChat/CUSTOM", 0, 19, 20, 17031);
		addHoverButton(17032, "/PrivateChat/CUSTOM", 11, 16, 16, "1", 0, 17033, 1);
		addHoveredButton(17033, "/PrivateChat/CUSTOM", 0, 19, 20, 17034);
		addHoverButton(17035, "/PrivateChat/CUSTOM", 12, 16, 16, "1", 0, 17036, 1);
		addHoveredButton(17036, "/PrivateChat/CUSTOM", 0, 19, 20, 17037);
		addHoverButton(17038, "/PrivateChat/CUSTOM", 13, 16, 16, "1", 0, 17039, 1);
		addHoveredButton(17039, "/PrivateChat/CUSTOM", 0, 19, 20, 17040);
		addHoverButton(17041, "/PrivateChat/CUSTOM", 14, 16, 16, "1", 0, 17042, 1);
		addHoveredButton(17042, "/PrivateChat/CUSTOM", 0, 19, 20, 17043);
		addHoverButton(17044, "/PrivateChat/CUSTOM", 15, 16, 16, "1", 0, 17045, 1);
		addHoveredButton(17045, "/PrivateChat/CUSTOM", 0, 19, 20, 17046);
		addHoverButton(17047, "/PrivateChat/CUSTOM", 16, 16, 15, "Close", -1, 17048, 1);
		addHoveredButton(17048, "/PrivateChat/CUSTOM", 17, 19, 20, 17049);
		addToggleButton(17050, 0, 1, "/PrivateChat/CHECK", "Toggle", 1, 4, 500);
		tab.totalChildren(34);
		tab.child(0, 17001, 0, 2);
		tab.child(1, 17002, 26, 119);
		tab.child(2, 17003, 25, 117);
		tab.child(3, 17005, 56, 119);
		tab.child(4, 17006, 55, 117);
		tab.child(5, 17008, 86, 119);
		tab.child(6, 17009, 85, 117);
		tab.child(7, 17011, 116, 119);
		tab.child(8, 17012, 115, 117);
		tab.child(9, 17014, 146, 119);
		tab.child(10, 17015, 145, 117);
		tab.child(11, 17017, 26, 149);
		tab.child(12, 17018, 25, 147);
		tab.child(13, 17020, 56, 149);
		tab.child(14, 17021, 55, 147);
		tab.child(15, 17023, 86, 149);
		tab.child(16, 17024, 85, 147);
		tab.child(17, 17026, 116, 149);
		tab.child(18, 17027, 115, 147);
		tab.child(19, 17029, 146, 149);
		tab.child(20, 17030, 145, 147);
		tab.child(21, 17032, 26, 179);
		tab.child(22, 17033, 25, 177);
		tab.child(23, 17035, 56, 179);
		tab.child(24, 17036, 55, 177);
		tab.child(25, 17038, 86, 179);
		tab.child(26, 17039, 85, 177);
		tab.child(27, 17041, 116, 179);
		tab.child(28, 17042, 115, 177);
		tab.child(29, 17044, 146, 179);
		tab.child(30, 17045, 145, 177);
		tab.child(31, 17047, 167, 10);
		tab.child(32, 17048, 167, 10);
		tab.child(33, 17050, 167, 50);
	}
	public static void Curses(TextDrawingArea[] TDA) {
		RSInterface Interface = addTabInterface(22500);
		int index = 0;
		setChildren(62, Interface);
		addText(22501, "99/99", 0xFF981F, false, false, -1, TDA, 1);
		/*Top Row*/
		addPrayer(22503, 0, 83, 49, 7, "Protect Item", 22542);
		setBounds(22503, 2, 5, index, Interface);index++;//Glow
		setBounds(22504, 8, 8, index, Interface);index++;//Icon
		addPrayer(22505, 0, 84, 49, 4, "Sap Warrior", 22544);
		setBounds(22505, 40, 5, index, Interface);index++;//Glow
		setBounds(22506, 47, 12, index, Interface);index++;//Icon
		addPrayer(22507, 0, 85, 51, 5, "Sap Ranger", 22546);
		setBounds(22507, 76, 5, index, Interface);index++;//Glow
		setBounds(22508, 82, 11, index, Interface);index++;//Icon
		addPrayer(22509, 0, 101, 53, 3, "Sap Mage", 22548);
		setBounds(22509, 113, 5, index, Interface);index++;//Glow
		setBounds(22510, 116, 8, index, Interface);index++;//Icon
		addPrayer(22511, 0, 102, 55, 2, "Sap Spirit", 22550);
		setBounds(22511, 150, 5, index, Interface);index++;//Glow
		setBounds(22512, 155, 10, index, Interface);index++;//Icon
		/*2nd Row*/
		addPrayer(22513, 0, 86, 58, 18, "Berserker", 22552);
		setBounds(22513, 2, 45, index, Interface);index++;//Glow
		setBounds(22514, 9, 48, index, Interface);index++;//Icon
		addPrayer(22515, 0, 87, 61, 15, "Deflect Summoning", 22554);
		setBounds(22515, 39, 45, index, Interface);index++;//Glow
		setBounds(22516, 42, 47, index, Interface);index++;//Icon
		addPrayer(22517, 0, 88, 64, 17, "Deflect Magic", 22556);
		setBounds(22517, 76, 45, index, Interface);index++;//Glow
		setBounds(22518, 79, 48, index, Interface);index++;//Icon
		addPrayer(22519, 0, 89, 67, 16, "Deflect Missiles", 22558);
		setBounds(22519, 113, 45, index, Interface);index++;//Glow
		setBounds(22520, 116, 48, index, Interface);index++;//Icon
		addPrayer(22521, 0, 90, 70, 6, "Deflect Melee", 22560);
		setBounds(22521, 151, 45, index, Interface);index++;//Glow
		setBounds(22522, 154, 48, index, Interface);index++;//Icon
		/*3rd Row*/
		addPrayer(22523, 0, 91, 73, 9, "Leech Attack", 22562);
		setBounds(22523, 2, 82, index, Interface);index++;//Glow
		setBounds(22524, 6, 86, index, Interface);index++;//Icon
		addPrayer(22525, 0, 103, 75, 10, "Leech Ranged", 22564);
		setBounds(22525, 40, 82, index, Interface);index++;//Glow
		setBounds(22526, 42, 86, index, Interface);index++;//Icon
		addPrayer(22527, 0, 104, 77, 11, "Leech Magic", 22566);
		setBounds(22527, 77, 82, index, Interface);index++;//Glow
		setBounds(22528, 79, 86, index, Interface);index++;//Icon
		addPrayer(22529, 0, 92, 79, 12, "Leech Defence", 22568);
		setBounds(22529, 114, 83, index, Interface);index++;//Glow
		setBounds(22530, 119, 87, index, Interface);index++;//Icon
		addPrayer(22531, 0, 93, 81, 13, "Leech Strength", 22570);
		setBounds(22531, 153, 83, index, Interface);index++;//Glow
		setBounds(22532, 156, 86, index, Interface);index++;//Icon
		/*Bottom Row*/
		addPrayer(22533, 0, 94, 83, 14, "Leech Energy", 22572);
		setBounds(22533, 2, 120, index, Interface);index++;//Glow
		setBounds(22534, 7, 125, index, Interface);index++;//Icon
		addPrayer(22535, 0, 95, 85, 19, "Leech Special Attack", 22574);
		setBounds(22535, 40, 120, index, Interface);index++;//Glow
		setBounds(22536, 45, 124, index, Interface);index++;//Icon
		addPrayer(22537, 0, 96, 88, 1, "Wrath", 22576);
		setBounds(22537, 78, 120, index, Interface);index++;//Glow
		setBounds(22538, 86, 124, index, Interface);index++;//Icon
		addPrayer(22539, 0, 97, 91, 8, "Soul Split", 22578);
		setBounds(22539, 114, 120, index, Interface);index++;//Glow
		setBounds(22540, 120, 125, index, Interface);index++;//Icon
		addPrayer(23000, 0, 105, 94, 0, "Turmoil", 22580);
		setBounds(23000, 151, 120, index, Interface);index++;//Glow
		setBounds(23001, 153, 127, index, Interface);index++;//Icon
		/*Prayer Icon/Text*/
		addSprite(22502, 0, "Curses/ICON");
		setBounds(22501, 85, 241, index, Interface);index++;//Text
		setBounds(22502, 65, 241, index, Interface);index++;//Icon
		/*Tooltips/Hover Boxes*/
		addTooltip(22542, "Level 50\nProtect Item\nKeep 1 extra item if you die", 100, 150);
		addTooltip(22544, "Level 50\nSap Warrior\nDrains 10% of enemy Attack,\nStrength and Defence,\nincreasing to 20% over time", 100, 150);
		addTooltip(22546, "Level 52\nSap Ranger\nDrains 10% of enemy Ranged\nand Defence, increasing to 20%\nover time", 100, 175);
		addTooltip(22548, "Level 54\nSap Mage\nDrains 10% of enemy Magic\nand Defence, increasing to 20%\nover time", 100, 175);
		addTooltip(22550, "Level 56\nSap Spirit\nDrains enenmy special attack\nenergy", 100, 175);
		addTooltip(22552, "Level 59\nBerserker\nBoosted stats last 15% longer", 100, 175);
		addTooltip(22554, "Level 62\nDeflect Summoning\nReduces damage dealt from\nSummoning scrolls, prevents the\nuse of a familiar's special\nattack, and can deflect some of\ndamage back to the attacker", 125, 175);
		addTooltip(22556, "Level 65\nDeflect Magic\nProtects against magical attacks\nand can deflect some of the\ndamage back to the attacker", 100, 175);
		addTooltip(22558, "Level 68\nDeflect Missiles\nProtects against ranged attacks\nand can deflect some of the\ndamage back to the attacker", 100, 175);
		addTooltip(22560, "Level 71\nDeflect Melee\nProtects against melee attacks\nand can deflect some of the\ndamage back to the attacker", 100, 175);
		addTooltip(22562, "Level 74\nLeech Attack\nBoosts Attack by 5%, increasing\nto 10% over time, while draining\nenemy Attack by 10%,\nincreasing to 25% over time", 100, 175);
		addTooltip(22564, "Level 76\nLeech Ranged\nBoosts Ranged by 5%, increasing\nto 10% over time,\nwhile draining enemy Ranged by\n10%, increasing to 25% over\ntime", 113, 175);
		addTooltip(22566, "Level 78\nLeech Magic\nBoosts Magic by 5%, increasing\nto 10% over time, while draining\nenemy Magic by 10%, increasing\nto 25% over time", 100, 175);
		addTooltip(22568, "Level 80\nLeech Defence\nBoosts Defence by 5%, increasing\nto 10% over time,\nwhile draining enemy Defence by\n10%, increasing to 25% over\ntime", 113, 180);
		addTooltip(22570, "Level 82\nLeech Strength\nBoosts Strength by 5%, increasing\nto 10% over time,\nwhile draining enemy Strength by\n10%, increasing to 25% over\ntime", 113, 180);
		addTooltip(22572, "Level 84\nLeech Energy\nDrains enemy run energy, while\nincreasing your own", 113, 180);
		addTooltip(22574, "Level 86\nLeech Special Attack\nDrains enemy special attack\nenergy, while increasing your\nown", 113, 180);
		addTooltip(22576, "Level 89\nWrath\nInflicts damage to nearby\ntargets if you die", 113, 180);
		addTooltip(22578, "Level 92\nSoul Split\n1/4 of damage dealt is\nalso removed from\nopponent's Prayer and added to\nyour Hitpoints", 113, 180);
		addTooltip(22580, "Level 95\nTurmoil\nIncreases Attack and Defence\nby 15%, plus 15% of enemy's\nlevel, and Strength by 23% plus\n10% of enemy's level", 113, 180);
		setBounds(22542, 10, 40, index, Interface);index++;
		setBounds(22544, 20, 40, index, Interface);index++;
		setBounds(22546, 20, 40, index, Interface);index++;
		setBounds(22548, 20, 40, index, Interface);index++;
		setBounds(22550, 20, 40, index, Interface);index++;
		setBounds(22552, 10, 80, index, Interface);index++;
		setBounds(22554, 10, 80, index, Interface);index++;
		setBounds(22556, 10, 80, index, Interface);index++;
		setBounds(22558, 10, 80, index, Interface);index++;
		setBounds(22560, 10, 80, index, Interface);index++;
		setBounds(22562, 10, 120, index, Interface);index++;
		setBounds(22564, 10, 120, index, Interface);index++;
		setBounds(22566, 10, 120, index, Interface);index++;
		setBounds(22568, 5, 120, index, Interface);index++;
		setBounds(22570, 5, 120, index, Interface);index++;
		setBounds(22572, 10, 160, index, Interface);index++;
		setBounds(22574, 10, 160, index, Interface);index++;
		setBounds(22576, 10, 160, index, Interface);index++;
		setBounds(22578, 10, 160, index, Interface);index++;
		setBounds(22580, 10, 160, index, Interface);index++;
	}
	
	public static void prayerTab(TextDrawingArea[] tda) {
        RSInterface tab = addTabInterface(5608);
        RSInterface currentPray = interfaceCache[687];
        currentPray.textColor = 0xFF981F;
        currentPray.textShadow = true;
        currentPray.message = "%1/%2";
        addSprite(5651, 0, "PRAYER");
        addPrayer(18000, 0, 601, 7, 0, "Sharp Eye");
        addPrayer(18002, 0, 602, 8, 1, "Mystic Will");
        addPrayer(18004, 0, 603, 25, 2, "Hawk Eye");
        addPrayer(18006, 0, 604, 26, 3, "Mystic Lore");
        addPrayer(18008, 0, 605, 43, 4, "Eagle Eye");
        addPrayer(18010, 0, 606, 44, 5, "Mystic Might");
        addPrayer(18012, 0, 607, 60, 6, "Chivalry");
        addPrayer(18014, 0, 608, 70, 7, "Piety");
        addPrayer(18020, 0, 609, 95, 8, "Turmoil");
        tab.totalChildren(56);
        /* Buttons/glows */
        tab.child(0, 5609, 6, 4);
        tab.child(1, 5610, 42, 4);
        tab.child(2, 5611, 78, 4);
        tab.child(3, 5612, 6, 40);
        tab.child(4, 5613, 42, 40);
        tab.child(5, 5614, 78, 40);
        tab.child(6, 5615, 114, 40);
        tab.child(7, 5616, 150, 40);
        tab.child(8, 5617, 6, 76);
        tab.child(9, 5618, 114, 76);
        tab.child(10, 5619, 150, 76);
        tab.child(11, 5620, 6, 112);
        tab.child(12, 5621, 42, 112);
        tab.child(13, 5622, 78, 112);
        tab.child(14, 5623, 114, 112);
        tab.child(15, 683, 42, 148);
        tab.child(16, 684, 78, 148);
        tab.child(17, 685, 114, 148);
        /* Sprites */
        tab.child(18, 5632, 8, 6);
        tab.child(19, 5633, 44, 6);
        tab.child(20, 5634, 80, 6);
        tab.child(21, 5635, 8, 42);
        tab.child(22, 5636, 44, 42);
        tab.child(23, 5637, 80, 42);
        tab.child(24, 5638, 116, 42);
        tab.child(25, 5639, 152, 42);
        tab.child(26, 5640, 8, 79);
        tab.child(27, 5641, 116, 78);
        tab.child(28, 5642, 152, 78);
        tab.child(29, 5643, 8, 114);
        tab.child(30, 5644, 44, 114);
        tab.child(31, 686, 80, 114);
        tab.child(32, 5645, 116, 114);
        tab.child(33, 5649, 44, 150);
        tab.child(34, 5647, 80, 150);
        tab.child(35, 5648, 116, 150);
        /* New prayers */
        tab.child(36, 18000, 114, 4);
        tab.child(37, 18001, 117, 8);
        tab.child(38, 18002, 150, 4);
        tab.child(39, 18003, 153, 7);
        tab.child(40, 18004, 42, 76);
        tab.child(41, 18005, 45, 80);
        tab.child(42, 18006, 78, 76);
        tab.child(43, 18007, 81, 79);
        tab.child(44, 18008, 150, 112);
        tab.child(45, 18009, 153, 116);
        tab.child(46, 18010, 6, 148);
        tab.child(47, 18011, 9, 151);
        tab.child(48, 18012, 150, 148);
        tab.child(49, 18013, 157, 151);
        tab.child(50, 18014, 6, 184);
        tab.child(51, 18015, 8, 194);
        /* Prayer icons & text */
        tab.child(52, 5651, 65, 242);
        tab.child(53, 687, 14, 244);
        tab.child(54, 18020, 46, 184);
        tab.child(55, 18021, 48, 192);
		String[] tools = {"Thick Skin", "Burst of Strength", "Clarity of Thought", "Rock Skin", "Superhuman Strength", "Improved Reflexes", "Rapid Restore", "Rapid Heal", "Protect Item", "Steel Skin", "Ultimate Strength", "Incredible Reflexes", 
						  "Protect from Magic", "Protect from Range", "Protect from Melee", "Retribution", "Redemption", "Smite"};
		int count = 0;
		for (int j = 5609; j <= 5623; j++) {
			RSInterface tab2 = interfaceCache[j];
			tab2.tooltip = "Activate @or2@" + tools[count++];
		}
		for (int j = 683; j <= 685; j++) {
			RSInterface tab2 = interfaceCache[j];
			tab2.tooltip = "Activate @or2@" + tools[count++];
		}
    }

	public String popupString;
	public static void addTooltipBox(int id, String text) {
		RSInterface rsi = addInterface(id);
		rsi.id = id;
		rsi.parentID = id;
		rsi.type = 8;
		rsi.popupString = text;
	}
	public static void addTooltip(int id, String text) {
		RSInterface rsi = addInterface(id);
		rsi.id = id;
		rsi.type = 0;
		rsi.isMouseoverTriggered = true;
		rsi.mOverInterToTrigger = -1;
		addTooltipBox(id + 1, text);
		rsi.totalChildren(1);
		rsi.child(0, id + 1, 0, 0);
	}

	public static void addPrayer(int i, int configId, int configFrame, int anIntArray212, int spriteID, String prayerName) {
        RSInterface tab = addTabInterface(i);
        tab.id = i;
        tab.parentID = 5608;
        tab.type = 5;
        tab.atActionType = 4;
        tab.contentType = 0;
        tab.aByte254 = 0;
        tab.mOverInterToTrigger = -1;
        tab.sprite1 = imageLoader(0, "PRAYERGLOW");
        tab.sprite2 = imageLoader(1, "PRAYERGLOW");
        tab.width = 34;
        tab.height = 34;
        tab.anIntArray245 = new int[1];
        tab.anIntArray212 = new int[1];
        tab.anIntArray245[0] = 1;
        tab.anIntArray212[0] = configId;
        tab.valueIndexArray = new int[1][3];
        tab.valueIndexArray[0][0] = 5;
        tab.valueIndexArray[0][1] = configFrame;
        tab.valueIndexArray[0][2] = 0;
        tab.tooltip = "Activate@or2@ " + prayerName;
        //tab.tooltip = "Select";
        RSInterface tab2 = addTabInterface(i + 1);
        tab2.id = i + 1;
        tab2.parentID = 5608;
        tab2.type = 5;
        tab2.atActionType = 0;
        tab2.contentType  = 0;
        tab2.aByte254 = 0;
        tab2.mOverInterToTrigger = -1;
        tab2.sprite1 = imageLoader(spriteID, "/PRAYER/PRAYON");
        tab2.sprite2 = imageLoader(spriteID, "/PRAYER/PRAYOFF");
        tab2.width = 34;
        tab2.height = 34;
        tab2.anIntArray245 = new int[1];
        tab2.anIntArray212 = new int[1];
        tab2.anIntArray245[0] = 2;
        tab2.anIntArray212[0] = anIntArray212 + 1;
        tab2.valueIndexArray = new int[1][3];
        tab2.valueIndexArray[0][0] = 2;
        tab2.valueIndexArray[0][1] = 5;
        tab2.valueIndexArray[0][2] = 0;
		//RSInterface tab3 = addTabInterface(i + 50);
    }
	public static void friendsTab(TextDrawingArea[] tda) {
        RSInterface tab = addTabInterface(5065);
        RSInterface list = interfaceCache[5066];
        addText(5067, "Friends List", tda, 1, 0xff9933, true, true);
        addText(5070, "Add Friend", tda, 0, 0xff9933, false, true);
        addText(5071, "Delete Friend", tda, 0, 0xff9933, false, true);
        addSprite(16126, 4, "/Friends/SPRITE");
        addSprite(16127, 8, "/Friends/SPRITE");
        addHoverButton(5068, "/Friends/SPRITE", 6, 72, 32, "Add Friend", 201, 5072, 1);
        addHoveredButton(5072, "/Friends/SPRITE", 7, 72, 32, 5073);
        addHoverButton(5069, "/Friends/SPRITE", 6, 72, 32, "Delete Friend", 202, 5074, 1);
        addHoveredButton(5074, "/Friends/SPRITE", 7, 72, 32, 5075);
        tab.totalChildren(11);
        tab.child(0, 5067, 95, 4);
        tab.child(1, 16127, 0, 25);
        tab.child(2, 16126, 0, 221);
        tab.child(3, 5066, 0, 24);
        tab.child(4, 16126, 0, 22);
        tab.child(5, 5068, 15, 226);
        tab.child(6, 5072, 15, 226);
        tab.child(7, 5069, 103, 226);
        tab.child(8, 5074, 103, 226);
        tab.child(9, 5070, 25, 237);
        tab.child(10, 5071, 106, 237);
        list.height = 196; list.width = 174;
        for(int id = 5092, i = 0; id <= 5191 && i <= 99; id++, i++) {
            list.children[i] = id; list.childX[i] = 3; list.childY[i] = list.childY[i] - 7;
        } for(int id = 5192, i = 100; id <= 5291 && i <= 199; id++, i++) {
            list.children[i] = id; list.childX[i] = 131; list.childY[i] = list.childY[i] - 7;
        }
    }

    public static void ignoreTab(TextDrawingArea[] tda) {
        RSInterface tab = addTabInterface(5715);
        RSInterface list = interfaceCache[5716];
        addText(5717, "Ignore List", tda, 1, 0xff9933, true, true);
        addText(5720, "Add Name", tda, 0, 0xff9933, false, true);
        addText(5721, "Delete Name", tda, 0, 0xff9933, false, true);
        addHoverButton(5718, "/Friends/SPRITE", 6, 72, 32, "Add Name", 501, 5722, 1);
        addHoveredButton(5722, "/Friends/SPRITE", 7, 72, 32, 5723);
        addHoverButton(5719, "/Friends/SPRITE", 6, 72, 32, "Delete Name", 502, 5724, 1);
        addHoveredButton(5724, "/Friends/SPRITE", 7, 72, 32, 5725);
        tab.totalChildren(11);
        tab.child(0, 5717, 95, 4);
        tab.child(1, 16127, 0, 25);
        tab.child(2, 16126, 0, 221);
        tab.child(3, 5716, 0, 24);
        tab.child(4, 16126, 0, 22);
        tab.child(5, 5718, 15, 226);
        tab.child(6, 5722, 15, 226);
        tab.child(7, 5719, 103, 226);
        tab.child(8, 5724, 103, 226);
        tab.child(9, 5720, 27, 237);
        tab.child(10, 5721, 108, 237);
        list.height = 196;
        list.width = 174;
        for(int id = 5742, i = 0; id <= 5841 && i <= 99; id++, i++) {
            list.children[i] = id; list.childX[i] = 3; list.childY[i] = list.childY[i] - 7;
        }
    }

		private static Sprite CustomSpriteLoader(int id, String s)
    {
        long l = (TextClass.method585(s) << 8) + (long)id;
        Sprite sprite = (Sprite)aMRUNodes_238.insertFromCache(l);
        if(sprite != null) { return sprite; }
        try {
            sprite = new Sprite("/Attack/"+id + s);
            aMRUNodes_238.removeFromCache(sprite, l);
        } catch(Exception exception) { 
			return null; }
        return sprite;
    }

    public static RSInterface addInterface(int id)
    {
        RSInterface rsi = interfaceCache[id] = new RSInterface();
        rsi.id = id;
        rsi.parentID = id;
        rsi.width = 512;
        rsi.height = 334;
        return rsi;
    }

    public static void addText(int id, String text, TextDrawingArea tda[], int idx, int color, boolean centered) {
        RSInterface rsi = interfaceCache[id] = new RSInterface();
        if(centered)
          rsi.centerText = true;
        rsi.textShadow = true;
        rsi.textDrawingAreas = tda[idx];
        rsi.message = text;
        rsi.textColor = color;
        rsi.id = id;
        rsi.type = 4;
    }

    public static void textColor(int id, int color)
    { RSInterface rsi = interfaceCache[id]; rsi.textColor = color; }

    public static void textSize(int id, TextDrawingArea tda[], int idx)
    { RSInterface rsi = interfaceCache[id]; rsi.textDrawingAreas = tda[idx]; }

    public static void addCacheSprite(int id, int sprite1, int sprite2, String sprites)
    {
        RSInterface rsi = interfaceCache[id] = new RSInterface();
        rsi.sprite1 = method207(sprite1, aClass44, sprites);
        rsi.sprite2 = method207(sprite2, aClass44, sprites);
        rsi.parentID = id;
        rsi.id = id;
        rsi.type = 5;
    }
    
    public static void sprite1(int id, int sprite)
    { RSInterface class9 = interfaceCache[id];
        class9.sprite1 = CustomSpriteLoader(sprite, "");
    }

    public static void addActionButton(int id, int sprite, int sprite2, int width, int height, String s)
    {
        RSInterface rsi = interfaceCache[id] = new RSInterface();
        rsi.sprite1 = CustomSpriteLoader(sprite, "");
        if (sprite2 == sprite)
          rsi.sprite2 = CustomSpriteLoader(sprite, "a");
        else
          rsi.sprite2 = CustomSpriteLoader(sprite2, "");
        rsi.tooltip = s;
        rsi.contentType = 0;
        rsi.atActionType = 1;
        rsi.width = width;
        rsi.mOverInterToTrigger = 52;
        rsi.parentID = id;
        rsi.id = id;
        rsi.type = 5;
        rsi.height = height;
    }

    public static void addToggleButton(int id, int sprite, int setconfig, int width, int height, String s)
    {
        RSInterface rsi = addInterface(id);
        rsi.sprite1 = CustomSpriteLoader(sprite, "");
        rsi.sprite2 = CustomSpriteLoader(sprite, "a");
        rsi.anIntArray212 = new int[1];
        rsi.anIntArray212[0] = 1;
        rsi.anIntArray245 = new int[1];
        rsi.anIntArray245[0] = 1;
        rsi.valueIndexArray = new int[1][3];
        rsi.valueIndexArray[0][0] = 5;
        rsi.valueIndexArray[0][1] = setconfig;
        rsi.valueIndexArray[0][2] = 0;
        rsi.atActionType = 4;
        rsi.width = width;
        rsi.mOverInterToTrigger = -1;
        rsi.parentID = id;
        rsi.id = id;
        rsi.type = 5;
        rsi.height = height;
        rsi.tooltip = s;
    }

    public void totalChildren(int id, int x, int y)
    { children = new int[id]; childX = new int[x]; childY = new int[y]; }

    public static void removeSomething(int id)
    { RSInterface rsi = interfaceCache[id] = new RSInterface(); }
	
	public void specialBar(int id) //7599
    {
        /*addActionButton(ID, SpriteOFF, SpriteON, Width, Height, "SpriteText");*/
            addActionButton(id-12, 7587, -1, 150, 26, "Use @gre@Special Attack");
        /*removeSomething(ID);*/
        for (int i = id-11; i < id; i++)
            removeSomething(i);

        RSInterface rsi = interfaceCache[id-12];
            rsi.width = 150;
            rsi.height = 26;

        rsi = interfaceCache[id];
            rsi.width = 150;
            rsi.height = 26;

            rsi.child(0, id-12, 0, 0);

            rsi.child(12, id+1, 3, 7);

            rsi.child(23, id+12, 16, 8);

        for (int i = 13; i < 23; i++) {
            rsi.childY[i] -= 1;
        }

        rsi = interfaceCache[id+1];
            rsi.type = 5;
            rsi.sprite1 = CustomSpriteLoader(7600, "");

        for (int i = id+2; i < id+12; i++) {
        rsi = interfaceCache[i];
            rsi.type = 5;
        }

        sprite1(id+2, 7601);sprite1(id+3, 7602);
        sprite1(id+4, 7603);sprite1(id+5, 7604);
        sprite1(id+6, 7605);sprite1(id+7, 7606);
        sprite1(id+8, 7607);sprite1(id+9, 7608);
        sprite1(id+10, 7609);sprite1(id+11, 7610);
    }

    public static void Sidebar0(TextDrawingArea[] tda)
    {
        /*Sidebar0a(id, id2, id3, "text1", "text2", "text3", "text4", str1x, str1y, str2x, str2y, str3x, str3y, str4x, str4y, img1x, img1y, img2x, img2y, img3x, img3y, img4x, img4y, tda);*/
            Sidebar0a(1698, 1701, 7499, "Chop", "Hack", "Smash", "Block", 42, 75, 127, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
            Sidebar0a(2276, 2279, 7574, "Stab", "Lunge", "Slash", "Block", 43, 75, 124, 75, 41, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
            Sidebar0a(2423, 2426, 7599, "Chop", "Slash", "Lunge", "Block", 42, 75, 125, 75, 40, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
            Sidebar0a(3796, 3799, 7624, "Pound", "Pummel", "Spike", "Block", 39, 75, 121, 75, 41, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
            Sidebar0a(4679, 4682, 7674, "Lunge", "Swipe", "Pound", "Block", 40, 75, 124, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
            Sidebar0a(4705, 4708, 7699, "Chop", "Slash", "Smash", "Block", 42, 75, 125, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
            Sidebar0a(5570, 5573, 7724, "Spike", "Impale", "Smash", "Block", 41, 75, 123, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
            Sidebar0a(7762, 7765, 7800, "Chop", "Slash", "Lunge", "Block", 42, 75, 125, 75, 40, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
        /*Sidebar0b(id, id2, "text1", "text2", "text3", "text4", str1x, str1y, str2x, str2y, str3x, str3y, str4x, str4y, img1x, img1y, img2x, img2y, img3x, img3y, img4x, img4y, tda);*/
            Sidebar0b(776, 779, "Reap", "Chop", "Jab", "Block", 42, 75, 126, 75, 46, 128, 125, 128, 122, 103, 122, 50, 40, 103, 40, 50, tda);
        /*Sidebar0c(id, id2, id3, "text1", "text2", "text3", str1x, str1y, str2x, str2y, str3x, str3y, img1x, img1y, img2x, img2y, img3x, img3y, tda);*/
            Sidebar0c(425, 428, 7474, "Pound", "Pummel", "Block", 39, 75, 121, 75, 42, 128, 40, 103, 40, 50, 122, 50, tda);
            Sidebar0c(1749, 1752, 7524, "Accurate", "Rapid", "Longrange", 33, 75, 125, 75, 29, 128, 40, 103, 40, 50, 122, 50, tda);
            Sidebar0c(1764, 1767, 7549, "Accurate", "Rapid", "Longrange", 33, 75, 125, 75, 29, 128, 40, 103, 40, 50, 122, 50, tda);
            Sidebar0c(4446, 4449, 7649, "Accurate", "Rapid", "Longrange", 33, 75, 125, 75, 29, 128, 40, 103, 40, 50, 122, 50, tda);
            Sidebar0c(5855, 5857, 7749, "Punch", "Kick", "Block", 40, 75, 129, 75, 42, 128, 40, 50, 122, 50, 40, 103, tda);
            Sidebar0c(6103, 6132, 6117, "Bash", "Pound", "Block", 43, 75, 124, 75, 42, 128, 40, 103, 40, 50, 122, 50, tda);
            Sidebar0c(8460, 8463, 8493, "Jab", "Swipe", "Fend", 46, 75, 124, 75, 43, 128, 40, 103, 40, 50, 122, 50, tda);
            Sidebar0c(12290, 12293, 12323, "Flick", "Lash", "Deflect", 44, 75, 127, 75, 36, 128, 40, 50, 40, 103, 122, 50, tda);
        /*Sidebar0d(id, id2, "text1", "text2", "text3", str1x, str1y, str2x, str2y, str3x, str3y, img1x, img1y, img2x, img2y, img3x, img3y, tda);*/
            Sidebar0d(328, 331, "Bash", "Pound", "Focus", 42, 66, 39, 101, 41, 136, 40, 120, 40, 50, 40, 85, tda);

        RSInterface rsi = addInterface(19300);
        /*textSize(ID, wid, Size);*/
            textSize(3983, tda, 0);
        /*addToggleButton(id, sprite, config, width, height, wid);*/
            addToggleButton(150, 150, 172, 150, 44, "Auto Retaliate");

        rsi.totalChildren(2, 2, 2);
            rsi.child(0, 3983, 52, 25); //combat level
            rsi.child(1, 150, 21, 153); //auto retaliate

        rsi = interfaceCache[3983];
            rsi.centerText = true;
            rsi.textColor = 0xff981f;
    }

    public static void Sidebar0a(int id, int id2, int id3, String text1, String text2, String text3, String text4,
                                               int str1x, int str1y, int str2x, int str2y, int str3x, int str3y, int str4x, int str4y,
                                               int img1x, int img1y, int img2x, int img2y, int img3x, int img3y, int img4x, int img4y, TextDrawingArea[] tda) //4button spec
    {
        RSInterface rsi = addInterface(id); //2423
        /*addText(ID, "Text", tda, Size, Colour, Centered);*/
            addText(id2, "-2", tda, 3, 0xff981f, true); //2426
            addText(id2+11, text1, tda, 0, 0xff981f, false);
            addText(id2+12, text2, tda, 0, 0xff981f, false);
            addText(id2+13, text3, tda, 0, 0xff981f, false);
            addText(id2+14, text4, tda, 0, 0xff981f, false);
        /*specialBar(ID);*/
            rsi.specialBar(id3); //7599

            rsi.width = 190;
            rsi.height = 261;

        int last = 15; int frame = 0;
        rsi.totalChildren(last, last, last);

            rsi.child(frame, id2+3, 21, 46); frame++; //2429
            rsi.child(frame, id2+4, 104, 99); frame++; //2430
            rsi.child(frame, id2+5, 21, 99); frame++; //2431
            rsi.child(frame, id2+6, 105, 46); frame++; //2432

            rsi.child(frame, id2+7, img1x, img1y); frame++; //bottomright 2433
            rsi.child(frame, id2+8, img2x, img2y); frame++; //topleft 2434
            rsi.child(frame, id2+9, img3x, img3y); frame++; //bottomleft 2435
            rsi.child(frame, id2+10, img4x, img4y); frame++; //topright 2436

            rsi.child(frame, id2+11, str1x, str1y); frame++; //chop 2437
            rsi.child(frame, id2+12, str2x, str2y); frame++; //slash 2438
            rsi.child(frame, id2+13, str3x, str3y); frame++; //lunge 2439
            rsi.child(frame, id2+14, str4x, str4y); frame++; //block 2440

            rsi.child(frame, 19300, 0, 0); frame++; //stuffs
            rsi.child(frame, id2, 94, 4); frame++; //weapon 2426
            rsi.child(frame, id3, 21, 205); frame++; //special attack 7599

        for (int i = id2+3; i < id2+7; i++) { //2429 - 2433
        rsi = interfaceCache[i];
            rsi.sprite1 = CustomSpriteLoader(19301, ""); rsi.sprite2 = CustomSpriteLoader(19301, "a");
            rsi.width = 68; rsi.height = 44;
        }
    }

    public static void Sidebar0b(int id, int id2, String text1, String text2, String text3, String text4,
                                               int str1x, int str1y, int str2x, int str2y, int str3x, int str3y, int str4x, int str4y,
                                               int img1x, int img1y, int img2x, int img2y, int img3x, int img3y, int img4x, int img4y, TextDrawingArea[] tda) //4button nospec
    {
        RSInterface rsi = addInterface(id); //2423
        /*addText(ID, "Text", tda, Size, Colour, Centered);*/
            addText(id2, "-2", tda, 3, 0xff981f, true); //2426
            addText(id2+11, text1, tda, 0, 0xff981f, false);
            addText(id2+12, text2, tda, 0, 0xff981f, false);
            addText(id2+13, text3, tda, 0, 0xff981f, false);
            addText(id2+14, text4, tda, 0, 0xff981f, false);

            rsi.width = 190;
            rsi.height = 261;

        int last = 14; int frame = 0;
        rsi.totalChildren(last, last, last);

            rsi.child(frame, id2+3, 21, 46); frame++; //2429
            rsi.child(frame, id2+4, 104, 99); frame++; //2430
            rsi.child(frame, id2+5, 21, 99); frame++; //2431
            rsi.child(frame, id2+6, 105, 46); frame++; //2432

            rsi.child(frame, id2+7, img1x, img1y); frame++; //bottomright 2433
            rsi.child(frame, id2+8, img2x, img2y); frame++; //topleft 2434
            rsi.child(frame, id2+9, img3x, img3y); frame++; //bottomleft 2435
            rsi.child(frame, id2+10, img4x, img4y); frame++; //topright 2436

            rsi.child(frame, id2+11, str1x, str1y); frame++; //chop 2437
            rsi.child(frame, id2+12, str2x, str2y); frame++; //slash 2438
            rsi.child(frame, id2+13, str3x, str3y); frame++; //lunge 2439
            rsi.child(frame, id2+14, str4x, str4y); frame++; //block 2440

            rsi.child(frame, 19300, 0, 0); frame++; //stuffs
            rsi.child(frame, id2, 94, 4); frame++; //weapon 2426

        for (int i = id2+3; i < id2+7; i++) { //2429 - 2433
        rsi = interfaceCache[i];
            rsi.sprite1 = CustomSpriteLoader(19301, ""); rsi.sprite2 = CustomSpriteLoader(19301, "a");
            rsi.width = 68; rsi.height = 44;
        }
    }

    public static void Sidebar0c(int id, int id2, int id3, String text1, String text2, String text3,
                                               int str1x, int str1y, int str2x, int str2y, int str3x, int str3y,
                                               int img1x, int img1y, int img2x, int img2y, int img3x, int img3y, TextDrawingArea[] tda) //3button spec
    {
        RSInterface rsi = addInterface(id); //2423
        /*addText(ID, "Text", tda, Size, Colour, Centered);*/
            addText(id2, "-2", tda, 3, 0xff981f, true); //2426
            addText(id2+9, text1, tda, 0, 0xff981f, false);
            addText(id2+10, text2, tda, 0, 0xff981f, false);
            addText(id2+11, text3, tda, 0, 0xff981f, false);
        /*specialBar(ID);*/
            rsi.specialBar(id3); //7599

            rsi.width = 190;
            rsi.height = 261;

        int last = 12; int frame = 0;
        rsi.totalChildren(last, last, last);

            rsi.child(frame, id2+3, 21, 99); frame++;
            rsi.child(frame, id2+4, 105, 46); frame++;
            rsi.child(frame, id2+5, 21, 46); frame++;

            rsi.child(frame, id2+6, img1x, img1y); frame++; //topleft
            rsi.child(frame, id2+7, img2x, img2y); frame++; //bottomleft
            rsi.child(frame, id2+8, img3x, img3y); frame++; //topright

            rsi.child(frame, id2+9, str1x, str1y); frame++; //chop
            rsi.child(frame, id2+10, str2x, str2y); frame++; //slash
            rsi.child(frame, id2+11, str3x, str3y); frame++; //lunge

            rsi.child(frame, 19300, 0, 0); frame++; //stuffs
            rsi.child(frame, id2, 94, 4); frame++; //weapon
            rsi.child(frame, id3, 21, 205); frame++; //special attack 7599

        for (int i = id2+3; i < id2+6; i++) {
        rsi = interfaceCache[i];
            rsi.sprite1 = CustomSpriteLoader(19301, ""); rsi.sprite2 = CustomSpriteLoader(19301, "a");
            rsi.width = 68; rsi.height = 44;
        }
    }

    public static void Sidebar0d(int id, int id2, String text1, String text2, String text3,
                                               int str1x, int str1y, int str2x, int str2y, int str3x, int str3y,
                                               int img1x, int img1y, int img2x, int img2y, int img3x, int img3y, TextDrawingArea[] tda) //3button nospec (magic intf)
    {
        RSInterface rsi = addInterface(id); //2423
        /*addText(ID, "Text", tda, Size, Colour, Centered);*/
            addText(id2, "-2", tda, 3, 0xff981f, true); //2426
            addText(id2+9, text1, tda, 0, 0xff981f, false);
            addText(id2+10, text2, tda, 0, 0xff981f, false);
            addText(id2+11, text3, tda, 0, 0xff981f, false);

            //addText(353, "Spell", tda, 0, 0xff981f, false);
			removeSomething(353);
            addText(354, "Spell", tda, 0, 0xff981f, false);

            addCacheSprite(337, 19, 0, "combaticons");
            addCacheSprite(338, 13, 0, "combaticons2");
            addCacheSprite(339, 14, 0, "combaticons2");

        /*addToggleButton(id, sprite, config, width, height, tooltip);*/
            //addToggleButton(349, 349, 108, 68, 44, "Select");
			removeSomething(349);
            addToggleButton(350, 350, 108, 68, 44, "Select");

            rsi.width = 190;
            rsi.height = 261;

			int last = 15; int frame = 0;
			rsi.totalChildren(last, last, last);

            rsi.child(frame, id2+3, 20, 115); frame++;
            rsi.child(frame, id2+4, 20, 80); frame++;
            rsi.child(frame, id2+5, 20, 45); frame++;

            rsi.child(frame, id2+6, img1x, img1y); frame++; //topleft
            rsi.child(frame, id2+7, img2x, img2y); frame++; //bottomleft
            rsi.child(frame, id2+8, img3x, img3y); frame++; //topright

            rsi.child(frame, id2+9, str1x, str1y); frame++; //bash
            rsi.child(frame, id2+10, str2x, str2y); frame++; //pound
            rsi.child(frame, id2+11, str3x, str3y); frame++; //focus

            rsi.child(frame, 349, 105, 46); frame++; //spell1
            rsi.child(frame, 350, 104, 106); frame++; //spell2

            rsi.child(frame, 353, 125, 74); frame++; //spell
            rsi.child(frame, 354, 125, 134); frame++; //spell

            rsi.child(frame, 19300, 0, 0); frame++; //stuffs
            rsi.child(frame, id2, 94, 4); frame++; //weapon
    }
	

	public static void emoteTab() {

        RSInterface tab = addTabInterface(147);
        RSInterface scroll = addTabInterface(148);
        tab.totalChildren(1);
        tab.child(0, 148, 0, 1);
        addButton(168, 0, "/Emotes/EMOTE", "Yes",41,47);
        addButton(169, 1, "/Emotes/EMOTE", "No",41,47);
        addButton(164, 2, "/Emotes/EMOTE", "Bow",41,47);
        addButton(165, 3, "/Emotes/EMOTE", "Angry",41,47);
        addButton(162, 4, "/Emotes/EMOTE", "Think",41,47);
        addButton(163, 5, "/Emotes/EMOTE", "Wave",41,47);
        addButton(13370, 6, "/Emotes/EMOTE", "Shrug",41,47);
        addButton(171, 7, "/Emotes/EMOTE", "Cheer",41,47);
        addButton(167, 8, "/Emotes/EMOTE", "Beckon",41,47);
        addButton(170, 9, "/Emotes/EMOTE", "Laugh",41,47);
        addButton(13366, 10, "/Emotes/EMOTE", "Jump for Joy",41,47);
        addButton(13368, 11, "/Emotes/EMOTE", "Yawn",41,47);
        addButton(166, 12, "/Emotes/EMOTE", "Dance",41,47);
        addButton(13363, 13, "/Emotes/EMOTE", "Jig",41,47);
        addButton(13364, 14, "/Emotes/EMOTE", "Spin",41,47);
        addButton(13365, 15, "/Emotes/EMOTE", "Headbang",41,47);
        addButton(161, 16, "/Emotes/EMOTE", "Cry",41,47);
        addButton(11100, 17, "/Emotes/EMOTE", "Blow kiss",41,47);
        addButton(13362, 18, "/Emotes/EMOTE", "Panic",41,47);
        addButton(13367, 19, "/Emotes/EMOTE", "Raspberry",41,47);
        addButton(172, 20, "/Emotes/EMOTE", "Clap",41,47);
        addButton(13369, 21, "/Emotes/EMOTE", "Salute",41,47);
        addButton(13383, 22, "/Emotes/EMOTE", "Goblin Bow",41,47);
        addButton(13384, 23, "/Emotes/EMOTE", "Goblin Salute",41,47);
        addButton(667, 24, "/Emotes/EMOTE", "Glass Box",41,47);
        addButton(6503, 25, "/Emotes/EMOTE", "Climb Rope",41,47);
        addButton(6506, 26, "/Emotes/EMOTE", "Lean On Air",41,47);
        addButton(666, 27, "/Emotes/EMOTE", "Glass Wall",41,47);
        addButton(18464, 28, "/Emotes/EMOTE", "Zombie Walk",41,47);
        addButton(18465, 29, "/Emotes/EMOTE", "Zombie Dance",41,47);
        addButton(15166, 30, "/Emotes/EMOTE", "Scared",41,47);
        addButton(18686, 31, "/Emotes/EMOTE", "Rabbit Hop",41,47);
        addConfigButton(154, 147, 32, 33,"/Emotes/EMOTE", 41, 47, "Skillcape Emote", 0, 1, 700);
        scroll.totalChildren(33);
        scroll.child(0, 168, 10, 7);
        scroll.child(1, 169, 54, 7);
        scroll.child(2, 164, 98, 14);
        scroll.child(3, 165, 137, 7);
        scroll.child(4, 162, 9, 56);
        scroll.child(5, 163, 48, 56);
        scroll.child(6, 13370, 95, 56);
        scroll.child(7, 171, 137, 56);
        scroll.child(8, 167, 7, 105);
        scroll.child(9, 170, 51, 105);
        scroll.child(10, 13366, 95, 104);
        scroll.child(11, 13368, 139, 105);
        scroll.child(12, 166, 6, 154);
        scroll.child(13, 13363, 50, 154);
        scroll.child(14, 13364, 90, 154);
        scroll.child(15, 13365, 135, 154);
        scroll.child(16, 161, 8, 204);
        scroll.child(17, 11100, 51, 203);
        scroll.child(18, 13362, 99, 204);
        scroll.child(19, 13367, 137, 203);
        scroll.child(20, 172, 10, 253);
        scroll.child(21, 13369, 53, 253);
        scroll.child(22, 13383, 88, 258);
        scroll.child(23, 13384, 138, 252);
        scroll.child(24, 667, 2, 303);
        scroll.child(25, 6503, 49, 302);
        scroll.child(26, 6506, 93, 302);
        scroll.child(27, 666, 137, 302);
        scroll.child(28, 18464, 9, 352);
        scroll.child(29, 18465, 50, 352);
        scroll.child(30, 15166, 94, 356);
        scroll.child(31, 18686, 141, 353);
        scroll.child(32, 154, 5, 401);
        scroll.width = 173; scroll.height = 258; scroll.scrollMax = 453;
    }
	
	public static void optionTab(TextDrawingArea[] tda) {
        RSInterface tab = addTabInterface(904);
        RSInterface energy = interfaceCache[149];
        energy.textColor = 0xff9933;
        addSprite(905, 9, "/Options/SPRITE");
        addSprite(907, 18, "/Options/SPRITE");
        addSprite(909, 29, "/Options/SPRITE");
        addSprite(951, 32, "/Options/SPRITE");
        addSprite(953, 33, "/Options/SPRITE");
        addSprite(955, 34, "/Options/SPRITE");
        addSprite(947, 36, "/Options/SPRITE");
        addSprite(949, 35, "/Options/SPRITE");
		//run button here
        addConfigButton(152, 904, 30, 31, "/Options/SPRITE", 40, 40, "Toggle-run", 1, 5, 173);
        addConfigButton(906, 904, 10, 14, "/Options/SPRITE", 32, 16, "Dark", 1, 5, 166);
        addConfigButton(908, 904, 11, 15, "/Options/SPRITE", 32, 16, "Normal", 2, 5, 166);
        addConfigButton(910, 904, 12, 16, "/Options/SPRITE", 32, 16, "Bright", 3, 5, 166);
        addConfigButton(912, 904, 13, 17, "/Options/SPRITE", 32, 16, "Very Bright", 4, 5, 166);
        addConfigButton(930, 904, 19, 24, "/Options/SPRITE", 26, 16, "Music Off", 4, 5, 168);
        addConfigButton(931, 904, 20, 25, "/Options/SPRITE", 26, 16, "Music Level-1", 3, 5, 168);
        addConfigButton(932, 904, 21, 26, "/Options/SPRITE", 26, 16, "Music Level-2", 2, 5, 168);
        addConfigButton(933, 904, 22, 27, "/Options/SPRITE", 26, 16, "Music Level-3", 1, 5, 168);
        addConfigButton(934, 904, 23, 28, "/Options/SPRITE", 24, 16, "Music Level-4", 0, 5, 168);
        addConfigButton(941, 904, 19, 24, "/Options/SPRITE", 26, 16, "Sound Effects Off", 4, 5, 169);
        addConfigButton(942, 904, 20, 25, "/Options/SPRITE", 26, 16, "Sound Effects Level-1", 3, 5, 169);
        addConfigButton(943, 904, 21, 26, "/Options/SPRITE", 26, 16, "Sound Effects Level-2", 2, 5, 169);
        addConfigButton(944, 904, 22, 27, "/Options/SPRITE", 26, 16, "Sound Effects Level-3", 1, 5, 169);
        addConfigButton(945, 904, 23, 28, "/Options/SPRITE", 24, 16, "Sound Effects Level-4", 0, 5, 169);
        addConfigButton(913, 904, 30, 31, "/Options/SPRITE", 40, 40, "Toggle-Mouse Buttons", 0, 5, 170);
        addConfigButton(915, 904, 30, 31, "/Options/SPRITE", 40, 40, "Toggle-Chat Effects", 0, 5, 171);
        addConfigButton(957, 904, 30, 31, "/Options/SPRITE", 40, 40, "Toggle-Split Private Chat", 1, 5, 287);
        addConfigButton(12464, 904, 30, 31, "/Options/SPRITE", 40, 40, "Toggle-Accept Aid", 0, 5, 427);
        tab.totalChildren(28);
        int x = 0;
        int y = 2;
        tab.child(0, 905, 13 + x, 10 + y);
        tab.child(1, 906, 48 + x, 18 + y);
        tab.child(2, 908, 80 + x, 18 + y);
        tab.child(3, 910, 112 + x, 18 + y);
        tab.child(4, 912, 144 + x, 18 + y);
        tab.child(5, 907, 14 + x, 55 + y);
        tab.child(6, 930, 49 + x, 61 + y);
        tab.child(7, 931, 75 + x, 61 + y);
        tab.child(8, 932, 101 + x, 61 + y);
        tab.child(9, 933, 127 + x, 61 + y);
        tab.child(10, 934, 151 + x, 61 + y);
        tab.child(11, 909, 13 + x, 99 + y);
        tab.child(12, 941, 49 + x, 104 + y);
        tab.child(13, 942, 75 + x, 104 + y);
        tab.child(14, 943, 101 + x, 104 + y);
        tab.child(15, 944, 127 + x, 104 + y);
        tab.child(16, 945, 151 + x, 104 + y);
        tab.child(17, 913, 15, 153);
        tab.child(18, 955, 19, 159);
        tab.child(19, 915, 75, 153);
        tab.child(20, 953, 79, 160);
        tab.child(21, 957, 135, 153);
        tab.child(22, 951, 139, 159);
        tab.child(23, 12464, 15, 208);
        tab.child(24, 949, 20, 213);
        tab.child(25, 152, 75, 208);
        tab.child(26, 947, 87, 212);
        tab.child(27, 149, 80, 231);
    }
	
	public static void clanChatTab(TextDrawingArea[] tda) {
        RSInterface tab = addTabInterface(18128);
        addHoverButton(18129, "/Clan Chat/SPRITE", 6, 72, 32, "Join Chat", 550, 18130, 1);
        addHoveredButton(18130, "/Clan Chat/SPRITE", 7, 72, 32, 18131);
        addHoverButton(18132, "/Clan Chat/SPRITE", 6, 72, 32, "Leave Chat", -1, 18133, 5);
        addHoveredButton(18133, "/Clan Chat/SPRITE", 7, 72, 32, 18134);
		addButton(18250, 0, "/Clan Chat/Lootshare", "Toggle lootshare");
        addText(18135, "Join Chat", tda, 0, 0xff9b00, true, true);
        addText(18136, "Leave Chat", tda, 0, 0xff9b00, true, true);
        addSprite(18137, 37, "/Clan Chat/SPRITE");
        addText(18138, "Clan Chat", tda, 1, 0xff9b00, true, true);
        addText(18139, "Talking in: Not in chat", tda, 0, 0xff9b00, false, true);
        addText(18140, "Owner: None", tda, 0, 0xff9b00, false, true);
        tab.totalChildren(14);
        tab.child(0, 16126, 0, 221);
        tab.child(1, 16126, 0, 59);
        tab.child(2, 18137, 0, 62);
        tab.child(3, 18143, 0, 62);
        tab.child(4, 18129, 15, 226);
        tab.child(5, 18130, 15, 226);
        tab.child(6, 18132, 103, 226);
        tab.child(7, 18133, 103, 226);
        tab.child(8, 18135, 51, 237);
        tab.child(9, 18136, 139, 237);
        tab.child(10, 18138, 95, 1);
        tab.child(11, 18139, 10, 23);
        tab.child(12, 18140, 25, 38);
		tab.child(13, 18250, 145,15);
        /* Text area */
        RSInterface list = addTabInterface(18143);
        list.totalChildren(100);
        for(int i = 18144; i <= 18244; i++) {
            addText(i, "", tda, 0, 0xffffff, false, true);
        }
        for(int id = 18144, i = 0; id <= 18243 && i <= 99; id++, i++) {
            list.children[i] = id; list.childX[i] = 5;
            for(int id2 = 18144, i2 = 1; id2 <= 18243 && i2<= 99; id2++, i2++) {
                list.childY[0] = 2;
                list.childY[i2] = list.childY[i2 - 1] + 14;
            }
        }
        list.height = 158; list.width = 174;
        list.scrollMax = 1405;
    }
	
	public static void addText(int i, String s,int k, boolean l, boolean m, int a,TextDrawingArea[] TDA, int j) {
		RSInterface RSInterface = addInterface(i);
		RSInterface.parentID = i;
		RSInterface.id = i;
		RSInterface.type = 4;
		RSInterface.atActionType = 0;
		RSInterface.width = 0;
		RSInterface.height = 0;
		RSInterface.contentType = 0;
		RSInterface.aByte254 = 0;
		RSInterface.mOverInterToTrigger = a;
		RSInterface.centerText = l;
		RSInterface.textShadow = m;
		RSInterface.textDrawingAreas = TDA[j];
		RSInterface.message = s;
		RSInterface.aString228 = "";
		RSInterface.textColor = k;
	}
	
	public static void Pestpanel(TextDrawingArea[] tda) {
		RSInterface RSinterface = addInterface(21119);
		addText(21120, "What", 0x999999, false, true, 52, tda, 1);
		addText(21121, "What", 0x33cc00, false, true, 52, tda, 1);
		addText(21122, "(Need 5 to 25 players)", 0xFFcc33, false, true, 52, tda, 1);
		addText(21123, "Points", 0x33ccff, false, true, 52, tda, 1);
		int last = 4;
		RSinterface.children = new int[last];
		RSinterface.childX = new int[last];
		RSinterface.childY = new int[last];
		setBounds(21120, 15, 12, 0,RSinterface);
		setBounds(21121, 15, 30, 1,RSinterface);
		setBounds(21122, 15, 48, 2,RSinterface);
		setBounds(21123, 15, 66, 3,RSinterface);
	}
		
	public static void Pestpanel2(TextDrawingArea[] tda) {
		RSInterface RSinterface = addInterface(21100);
		addSprite(21101, 0, "Pest Control/PEST1");
		addSprite(21102, 1, "Pest Control/PEST1");
		addSprite(21103, 2, "Pest Control/PEST1");
		addSprite(21104, 3, "Pest Control/PEST1");
		addSprite(21105, 4, "Pest Control/PEST1");
		addSprite(21106, 5, "Pest Control/PEST1");
		addText(21107, "", 0xCC00CC, false, true, 52, tda, 1);
		addText(21108, "", 0x0000FF, false, true, 52, tda, 1);
		addText(21109, "", 0xFFFF44, false, true, 52, tda, 1);
		addText(21110, "", 0xCC0000, false, true, 52, tda, 1);
		addText(21111, "250", 0x99FF33, false, true, 52, tda, 1);//w purp
		addText(21112, "250", 0x99FF33, false, true, 52, tda, 1);//e blue
		addText(21113, "250", 0x99FF33, false, true, 52, tda, 1);//se yel
		addText(21114, "250", 0x99FF33, false, true, 52, tda, 1);//sw red
		addText(21115, "200", 0x99FF33, false, true, 52, tda, 1);//attacks
		addText(21116, "0", 0x99FF33, false, true, 52, tda, 1);//knights hp
		addText(21117, "Time Remaining:", 0xFFFFFF, false, true, 52, tda, 0);
		addText(21118, "", 0xFFFFFF, false, true, 52, tda, 0);
		int last = 18;
		RSinterface.children = new int[last];
		RSinterface.childX = new int[last];
		RSinterface.childY = new int[last];
		setBounds(21101, 361, 26, 0,RSinterface);
		setBounds(21102, 396, 26, 1,RSinterface);
		setBounds(21103, 436, 26, 2,RSinterface);
		setBounds(21104, 474, 26, 3,RSinterface);
		setBounds(21105, 3, 21, 4,RSinterface);
		setBounds(21106, 3, 50, 5,RSinterface);
		setBounds(21107, 371, 60, 6,RSinterface);
		setBounds(21108, 409, 60, 7,RSinterface);
		setBounds(21109, 443, 60, 8,RSinterface);
		setBounds(21110, 479, 60, 9,RSinterface);
		setBounds(21111, 362, 10, 10,RSinterface);
		setBounds(21112, 398, 10, 11,RSinterface);
		setBounds(21113, 436, 10, 12,RSinterface);
		setBounds(21114, 475, 10, 13,RSinterface);
		setBounds(21115, 32, 32, 14,RSinterface);
		setBounds(21116, 32, 62, 15,RSinterface);
		setBounds(21117, 8, 88, 16,RSinterface);
		setBounds(21118, 87, 88, 17,RSinterface);
	}
	
	public String hoverText;
	public static void addHoverBox(int id, String text) {
        RSInterface rsi = interfaceCache[id];//
        rsi.id = id;
        rsi.parentID = id;
		rsi.isMouseoverTriggered = true;
        rsi.type = 8;
        rsi.hoverText = text;
    }
	
	public static void addText(int id, String text, TextDrawingArea tda[], int idx, int color, boolean center, boolean shadow) {
		RSInterface tab = addTabInterface(id);
		tab.parentID = id;
		tab.id = id;
		tab.type = 4;
		tab.atActionType = 0;
		tab.width = 0;
		tab.height = 11;
		tab.contentType = 0;
		tab.aByte254 = 0;
		tab.mOverInterToTrigger = -1;
		tab.centerText = center;
		tab.textShadow = shadow;
		tab.textDrawingAreas = tda[idx];
		tab.message = text;
		tab.aString228 = "";
		tab.textColor = color;
		tab.anInt219 = 0;
		tab.anInt216 = 0;
		tab.anInt239 = 0;	
	}

	public static void addButton(int id, int sid, String spriteName, String tooltip, int w, int h) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 1;
		tab.contentType = 0;
		tab.aByte254 = (byte)0;
		tab.mOverInterToTrigger = 52;
		tab.sprite1 = imageLoader(sid, spriteName);
		tab.sprite2 = imageLoader(sid, spriteName);
		tab.width = w;
		tab.height = h;
		tab.tooltip = tooltip;
	}
	
	public static void addConfigButton(int ID, int pID, int bID, int bID2, String bName, int width, int height, String tT, int configID, int aT, int configFrame) {
        RSInterface Tab = addTabInterface(ID);
        Tab.parentID = pID;
        Tab.id = ID;
        Tab.type = 5;
        Tab.atActionType = aT;
        Tab.contentType = 0;
        Tab.width = width;
        Tab.height = height;
        Tab.aByte254 = 0;
        Tab.mOverInterToTrigger = -1;
        Tab.anIntArray245 = new int[1];
        Tab.anIntArray212 = new int[1];
        Tab.anIntArray245[0] = 1;
        Tab.anIntArray212[0] = configID;
        Tab.valueIndexArray = new int[1][3];
        Tab.valueIndexArray[0][0] = 5;
        Tab.valueIndexArray[0][1] = configFrame;
        Tab.valueIndexArray[0][2] = 0;
        Tab.sprite1 = imageLoader(bID, bName);
        Tab.sprite2 = imageLoader(bID2, bName);
        Tab.tooltip = tT;
    }

	public static void addSprite(int id, int spriteId, String spriteName) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.aByte254 = (byte)0;
		tab.mOverInterToTrigger = 52;
		tab.sprite1 = imageLoader(spriteId, spriteName);
		tab.sprite2 = imageLoader(spriteId, spriteName); 
		tab.width = 512;
		tab.height = 334;
	}

	public static void addHoverButton(int i, String imageName, int j, int width, int height, String text, int contentType, int hoverOver, int aT) {//hoverable button
		RSInterface tab = addTabInterface(i);
		tab.id = i;
		tab.parentID = i;
		tab.type = 5;
		tab.atActionType = aT;
		tab.contentType = contentType;
		tab.aByte254 = 0;
		tab.mOverInterToTrigger = hoverOver;
		tab.sprite1 = imageLoader(j, imageName);
		tab.sprite2 = imageLoader(j, imageName);
		tab.width = width;
		tab.height = height;
		tab.tooltip = text;
	}

	public static void addHoveredButton(int i, String imageName, int j, int w, int h, int IMAGEID) {//hoverable button
		RSInterface tab = addTabInterface(i);
		tab.parentID = i;
		tab.id = i;
		tab.type = 0;
		tab.atActionType = 0;
		tab.width = w;
		tab.height = h;
		tab.isMouseoverTriggered = true;
		tab.aByte254 = 0;
		tab.mOverInterToTrigger = -1;
		tab.scrollMax = 0;
		addHoverImage(IMAGEID, j, j, imageName);
		tab.totalChildren(1);
		tab.child(0, IMAGEID, 0, 0);
	}

	public static void addHoverImage(int i, int j, int k, String name) {
		RSInterface tab = addTabInterface(i);
		tab.id = i;
		tab.parentID = i;
		tab.type = 5;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.width = 512;
		tab.height = 334;
		tab.aByte254 = 0;
		tab.mOverInterToTrigger = 52;
		tab.sprite1 = imageLoader(j, name);
		tab.sprite2 = imageLoader(k, name);
	}

	public static void addTransparentSprite(int id, int spriteId, String spriteName) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.aByte254 = (byte)0;
		tab.mOverInterToTrigger = 52;
		tab.sprite1 = imageLoader(spriteId, spriteName);
		tab.sprite2 = imageLoader(spriteId, spriteName); 
		tab.width = 512;
		tab.height = 334;
		tab.drawsTransparent = true;
	}

	public static RSInterface addScreenInterface(int id) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 0;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.width = 512;
		tab.height = 334;
		tab.aByte254 = (byte)0;
		tab.mOverInterToTrigger = 0;
		return tab;
	}

	public static RSInterface addTabInterface(int id) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;//250
		tab.parentID = id;//236
		tab.type = 0;//262
		tab.atActionType = 0;//217
		tab.contentType = 0;
		tab.width = 512;//220
		tab.height = 700;//267
		tab.aByte254 = (byte)0;
		tab.mOverInterToTrigger = -1;//Int 230
		return tab;
	}

	private static Sprite imageLoader(int i, String s) {
		long l = (TextClass.method585(s) << 8) + (long)i;
		Sprite sprite = (Sprite) aMRUNodes_238.insertFromCache(l);
		if(sprite != null)
			return sprite;
		try {
			sprite = new Sprite(s+" "+i);
			aMRUNodes_238.removeFromCache(sprite, l);
		} catch(Exception exception) {
			return null;
		}
		return sprite;
	}

	public void child(int id, int interID, int x, int y) {
		children[id] = interID;
		childX[id] = x;
		childY[id] = y;
	}

	public void totalChildren(int t) {
		children = new int[t];
		childX = new int[t];
		childY = new int[t];
	}

	private Model method206(int i, int j)
	{
		Model model = (Model) aMRUNodes_264.insertFromCache((i << 16) + j);
		if(model != null)
			return model;
		if(i == 1)
			model = Model.method462(j);
		if(i == 2)
			model = EntityDef.forID(j).method160();
		if(i == 3)
			model = client.myPlayer.method453();
		if(i == 4)
			model = ItemDef.forID(j).method202(50);
		if(i == 5)
			model = null;
		if(model != null)
			aMRUNodes_264.removeFromCache(model, (i << 16) + j);
		return model;
	}

	private static Sprite method207(int i, StreamLoader streamLoader, String s) {
		long l = (TextClass.method585(s) << 8) + (long)i;
		Sprite sprite = (Sprite) aMRUNodes_238.insertFromCache(l);
		if(sprite != null)
			return sprite;
		try {
			sprite = new Sprite(streamLoader, s, i);
			aMRUNodes_238.removeFromCache(sprite, l);
		} catch(Exception _ex) {
			return null;
		}
		return sprite;
	}

	public static void method208(boolean flag, Model model) {
		int i = 0;//was parameter
		int j = 5;//was parameter
		if(flag)
			return;
		aMRUNodes_264.unlinkAll();
		if(model != null && j != 4)
			aMRUNodes_264.removeFromCache(model, (j << 16) + i);
	}

	public Model method209(int j, int k, boolean flag) {
		Model model;
		if(flag)
			model = method206(anInt255, anInt256);
		else
			model = method206(anInt233, mediaID);
		if(model == null)
			return null;
		if(k == -1 && j == -1 && model.anIntArray1640 == null)
			return model;
		Model model_1 = new Model(true, Class36.method532(k) & Class36.method532(j), false, model);
		if(k != -1 || j != -1)
			model_1.method469();
		if(k != -1)
			model_1.method470(k);
		if(j != -1)
			model_1.method470(j);
		model_1.method479(64, 768, -50, -10, -50, true);
			return model_1;
	}

	public RSInterface() {}

	public static StreamLoader aClass44;
	public boolean drawsTransparent;
	public Sprite sprite1;
	public int anInt208;
	public Sprite sprites[];
	public static RSInterface interfaceCache[];
	public int anIntArray212[];
	public int contentType;//anInt214
	public int spritesX[];
	public int anInt216;
	public int atActionType;
	public String spellName;
	public int anInt219;
	public int width;
	public String tooltip;
	public String selectedActionName;
	public boolean centerText;
	public int scrollPosition;
	public String actions[];
	public int valueIndexArray[][];
	public boolean aBoolean227;
	public String aString228;
	public int mOverInterToTrigger;
	public int invSpritePadX;
	public int textColor;
	public int anInt233;
	public int mediaID;
	public boolean aBoolean235;
	public int parentID;
	public int spellUsableOn;
	private static MRUNodes aMRUNodes_238;
	public int anInt239;
	public int children[];
	public int childX[];
	public boolean usableItemInterface;
	public TextDrawingArea textDrawingAreas;
	public int invSpritePadY;
	public int anIntArray245[];
	public int anInt246;
	public int spritesY[];
	public String message;
	public boolean isInventoryInterface;
	public int id;
	public int invStackSizes[];
	public int inv[];
	public byte aByte254;
	private int anInt255;
	private int anInt256;
	public int anInt257;
	public int anInt258;
	public boolean aBoolean259;
	public Sprite sprite2;
	public int scrollMax;
	public int type;
	public int anInt263;
	private static final MRUNodes aMRUNodes_264 = new MRUNodes(30);
	public int anInt265;
	public boolean isMouseoverTriggered;
	public int height;
	public boolean textShadow;
	public int modelZoom;
	public int modelRotation1;
	public int modelRotation2;
	public int childY[];

	public static void EquipmentTab(TextDrawingArea[] tda)
	{
		RSInterface tab = interfaceCache[1644];
		addHoverButton(15201, "Equipment/CUSTOM", 1, 40, 40, "Show Equipment Screen", 0, 15202, 1);
		addHoveredButton(15202, "Equipment/CUSTOM", 5, 40, 40, 15203);
		addHoverButton(15204, "Equipment/CUSTOM", 2, 40, 40, "Items Kept on Death", 0, 15205, 1);
		addHoveredButton(15205, "Equipment/CUSTOM", 4, 40, 40, 15206);
		addHoverButton(15207, "Equipment/CUSTOM", 3, 40, 40, "Experience Lock", 0, 15208, 1);
		addHoveredButton(15208, "Equipment/CUSTOM", 6, 40, 40, 15209);
		addText(15226, "Unlocked", tda, 0, 0x00FF00, true, true);
		
		//removeSomething(15109);
		
		tab.child(23, 15201, 21, 210);
		tab.child(1, 15226, 95, 250);
		tab.child(24, 15202, 21, 210);
		tab.child(25, 15204, 129, 210);
		tab.child(26, 15205, 129, 210);
		tab.child(0, 15207, 75, 210);
		tab.child(2, 15208, 75, 210);
		/*tab.child(3, 15109, 41+39+30, 212);*/
	}
	public static void customPortal2(TextDrawingArea[] wid) {
		RSInterface tab = addTabInterface(14040);
		addSprite(14041, 7, "Equipment/CUSTOM");
		addHoverButton(14042, "Equipment/CUSTOM", 8, 21, 21, "Close", 250, 14043, 3);
		addHoveredButton(14043, "Equipment/CUSTOM", 9, 21, 21, 14044);
		addHoverButton(14045, "Other/CUSTOM", 18, 119, 171, "@red@Bandos", 0, 14046, 1);
		addHoveredButton(14046, "Other/CUSTOM", 17, 119, 171, 14047);
		addHoverButton(14048, "Other/CUSTOM", 20, 238, 159, "@red@Armadyll", 0, 14049, 1);
		addHoveredButton(14049, "Other/CUSTOM", 19, 238, 159, 14050);
        		addText(14051, "Choose which god...", wid, 2, 0xFF9900, false, true);
		addHoverButton(14052, "Other/CUSTOM", 22, 170, 205, "@red@Zamorak", 0, 14053, 1);
		addHoveredButton(14053, "Other/CUSTOM", 21, 170, 205, 14054);
		addHoverButton(14055, "Other/CUSTOM", 24, 83, 97, "@red@Saradomin", 0, 14056, 1);
		addHoveredButton(14056, "Other/CUSTOM", 23, 83, 97, 14057);
		tab.totalChildren(12);
		tab.child(0, 14041, 4, 20);//bg
		tab.child(1, 14045, 365, 150);//bandos
		tab.child(2, 14046, 365, 150);//bandos hover
		tab.child(3, 14048, 257, 21);//arma
		tab.child(4, 14049, 257, 21);//arma hover
		tab.child(5, 14051, 200, 162);//text
		tab.child(6, 14052, 5, 21);//zam
		tab.child(7, 14053, 5, 21);//zam hover
		tab.child(8, 14055, 71, 219);//sara
		tab.child(9, 14056, 71, 219);//sara hover
		tab.child(10, 14042, 476, 29);//exit
		tab.child(11, 14043, 476, 29);//exit hover
	}
	public static void vote(TextDrawingArea[] TDA) {
		RSInterface tab = addTabInterface(19050);
		addSprite(19051, 4, "Vote/CUSTOM");
		addHoverButton(19052, "Vote/CUSTOM", 0, 63, 44, "@red@Misc reward", 0, 19053, 1);
		addHoveredButton(19053, "Vote/CUSTOM", 1, 63, 44, 19054);
		addHoverButton(19055, "Vote/CUSTOM", 2, 39, 45, "@red@Cash reward", 0, 19056, 1);
		addHoveredButton(19056, "Vote/CUSTOM", 3, 39, 45, 19057);
		tab.totalChildren(5);
		tab.child(0, 19051, 80, 95);
		tab.child(1, 19052, 300, 155);
		tab.child(2, 19053, 300, 155);
		tab.child(3, 19055, 150, 155);
		tab.child(4, 19056, 150, 155);
	}
	public static void customPortal(TextDrawingArea[] wid) {
		RSInterface tab = addTabInterface(14000);
		addSprite(14001, 7, "Equipment/CUSTOM");
		addHoverButton(14002, "Other/CUSTOM", 1, 215, 32, "@gre@Low level training area", 0, 14003, 1);
		addHoveredButton(14003, "Other/CUSTOM", 2, 215, 32, 14004);
		addHoverButton(14005, "Equipment/CUSTOM", 8, 21, 21, "Close", 250, 14006, 3);
		addHoveredButton(14006, "Equipment/CUSTOM", 9, 21, 21, 14007);
        		addText(14008, "Low level training Area", wid, 2, 0xFF9900, false, true);
		addHoverButton(14009, "Other/CUSTOM", 3, 215, 32, "@gre@High level training area", 0, 14010, 1);
		addHoveredButton(14010, "Other/CUSTOM", 4, 215, 32, 14011);
        		addText(14012, "High level training Area", wid, 2, 0xFF9900, false, true);
		addHoverButton(14013, "Other/CUSTOM", 5, 215, 32, "@gre@Skilling area", 0, 14014, 1);
		addHoveredButton(14014, "Other/CUSTOM", 6, 215, 32, 14015);
        		addText(14016, "Skilling area", wid, 2, 0xFF9900, false, true);
		addHoverButton(14017, "Other/CUSTOM", 9, 215, 32, "@gre@Low level slayer area", 0, 14018, 1);
		addHoveredButton(14018, "Other/CUSTOM", 10, 215, 32, 14019);
        		addText(14020, "Low level slayer area", wid, 2, 0xFF9900, false, true);
		addHoverButton(14021, "Other/CUSTOM", 13, 215, 32, "@gre@Barrows", 0, 14022, 1);
		addHoveredButton(14022, "Other/CUSTOM", 14, 215, 32, 14023);
        		addText(14024, "Barrows", wid, 2, 0xFF9900, false, true);
		addHoverButton(14025, "Other/CUSTOM", 7, 215, 32, "@gre@Donator area", 0, 14026, 1);
		addHoveredButton(14026, "Other/CUSTOM", 8, 215, 32, 14027);
        		addText(14028, "@red@Donator area", wid, 2, 0xFF9900, false, true);
		addHoverButton(14029, "Other/CUSTOM", 11, 215, 32, "@gre@High level slayer area", 0, 14030, 1);
		addHoveredButton(14030, "Other/CUSTOM", 12, 215, 32, 14031);
        		addText(14032, "High level slayer area", wid, 2, 0xFF9900, false, true);
		addHoverButton(14033, "Other/CUSTOM", 15, 215, 32, "@gre@Dagannoths", 0, 14034, 1);
		addHoveredButton(14034, "Other/CUSTOM", 16, 215, 32, 14035);
        		addText(14036, "Dagannoths", wid, 2, 0xFF9900, false, true);
		tab.totalChildren(27);
		tab.child(0, 14001, 4, 20);//bg
		tab.child(1, 14002, 32, 55);//low lvl
		tab.child(2, 14003, 32, 55);//low lvl hover
		tab.child(3, 14005, 476, 29);//exit
		tab.child(4, 14006, 476, 29);//exit hover
		tab.child(5, 14008, 63, 35);//text low lvl
		tab.child(6, 14009, 262, 55);//high lvl
		tab.child(7, 14010, 262, 55);//highlvl hover
		tab.child(8, 14012, 293, 35);//text high lvl
		tab.child(9, 14013, 32, 123);//skiller
		tab.child(10, 14014, 32, 123);//skiller hover
		tab.child(11, 14016, 95, 103);//skiller text
		tab.child(12, 14017, 32, 191);//low lvl slayer
		tab.child(13, 14018, 32, 191);//low lvl slayer hover
		tab.child(14, 14020, 72, 171);//low lvl slayer text
		tab.child(15, 14021, 32, 259);//barrows
		tab.child(16, 14022, 32, 259);//barrows hover
		tab.child(17, 14024, 112, 239);//barrows text
		tab.child(18, 14025, 262, 123);//donator
		tab.child(19, 14026, 262, 123);//donator hover
		tab.child(20, 14028, 325, 103);//donator text
		tab.child(21, 14029, 262, 191);//High level slayer area
		tab.child(22, 14030, 262, 191);//High level slayer area hover
		tab.child(23, 14032, 302, 171);//High level slayer area text
		tab.child(24, 14033, 262, 259);//Dagannoths
		tab.child(25, 14034, 262, 259);//Dagannoths hover
		tab.child(26, 14036, 333, 239);//Dagannoths text

	}

	public static void EquipmentScreen(TextDrawingArea[] wid)
	{
		RSInterface tab = addTabInterface(15106);
			addSprite(15107, 1, "Equipment/bg");
			addHoverButton(15210, "Equipment/SPRITE", 1, 21, 21, "Close", 250, 15211, 3);
			addHoveredButton(15211, "Equipment/SPRITE", 3, 21, 21, 15212);
			addText(15111, "", wid, 2, 0xe4a146, false, true);
			addText(15112, "Attack bonuses", wid, 2, 0xFF8900, false, true);
			addText(15113, "Defence bonuses", wid, 2, 0xFF8900, false, true);
			addText(15114, "Other bonuses", wid, 2, 0xFF8900, false, true);
			for(int i = 1675; i <= 1684; i++) { textSize(i, wid, 1); }
			textSize(1686, wid, 1); textSize(1687, wid, 1);
			addChar(15125);
			tab.totalChildren(44);
			tab.child(0, 15107, 15, 5);
			tab.child(1, 15210, 476, 8);
			tab.child(2, 15211, 476, 8);
			tab.child(3, 15111, 14, 30);
			int Child = 4; int Y = 45;
			for(int i = 1675; i <= 1679; i++){
			tab.child(Child, i, 29, Y);
			Child++; Y += 14; }
			tab.child(9, 1680, 29, 137); // 161
			tab.child(10, 1681, 29, 153);
			tab.child(11, 1682, 29, 168);
			tab.child(12, 1683, 29, 183);
			tab.child(13, 1684, 29, 197);
			tab.child(14, 1686, 29, 262-24);
			tab.child(17, 1687, 29, 276-24);
			tab.child(15, 15125, 170, 200);
			tab.child(16, 15112, 24, 30);
			tab.child(18, 15113, 24, 122); // 147
			tab.child(19, 15114, 24, 223);
			tab.child(20, 1645, 104+295, 149-52);
			tab.child(21, 1646, 399, 163);
			tab.child(22, 1647, 399, 163);
			tab.child(23, 1648, 399, 58+146);
			tab.child(24, 1649, 26+22+297-2, 110-44+118-13+5);
			tab.child(25, 1650, 321+22, 58+154);
			tab.child(26, 1651, 321+134, 58+118);
			tab.child(27, 1652, 321+134, 58+154);
			tab.child(28, 1653, 321+48, 58+81);
			tab.child(29, 1654, 321+107, 58+81);
			tab.child(30, 1655, 321+58, 58+42);
			tab.child(31, 1656, 321+112, 58+41);
			tab.child(32, 1657, 321+78, 58+4);
			tab.child(33, 1658, 321+37, 58+43);
			tab.child(34, 1659, 321+78, 58+43);
			tab.child(35, 1660, 321+119, 58+43);
			tab.child(36, 1661, 321+22, 58+82);
			tab.child(37, 1662, 321+78, 58+82);
			tab.child(38, 1663, 321+134, 58+82);
			tab.child(39, 1664, 321+78, 58+122);
			tab.child(40, 1665, 321+78, 58+162);
			tab.child(41, 1666, 321+22, 58+162);
			tab.child(42, 1667, 321+134, 58+162);
			tab.child(43, 1688, 50+297-2, 110-13+5);
			for(int i = 1675; i <= 1684; i++){
			RSInterface rsi = interfaceCache[i];
            rsi.textColor = 0xFF9200;
			rsi.centerText = false; }
			for(int i = 1686; i <= 1687; i++) {
			RSInterface rsi = interfaceCache[i];
			rsi.textColor = 0xFF9200;
			rsi.centerText = false; }
	}
		
		public static void addChar(int ID) { 
			RSInterface t = interfaceCache[ID] = new RSInterface(); 
			t.id = ID; 
			t.parentID = ID; 
			t.type = 6;
			t.atActionType = 0; 
			t.contentType = 328; 
			t.width = 180; 
			t.height = 190; 
			t.aByte254 = 0;
			t.mOverInterToTrigger = 0;
			t.modelZoom = 560;
			t.modelRotation1 = 30;
			t.modelRotation2 = 0; 
			t.anInt257 = -1; 
			t.anInt258 = -1; 
		}
		
		private static Sprite LoadLunarSprite(int i, String s) {
			Sprite sprite = imageLoader(i,"/Lunar/" + s);
			return sprite;
		}
private static Sprite LoadSprite(int i, String s) {
        long l = (TextClass.method585(s) << 8) + (long) i;
        Sprite sprite = (Sprite) aMRUNodes_238.insertFromCache(l);
        if (sprite != null) {
            return sprite;
        }
        try {
            sprite = new Sprite("./Sprites/"+s+" "+i+".PNG");
            aMRUNodes_238.removeFromCache(sprite, l);
        } catch (Exception exception) {
            System.out.println(exception);
            return null;
        }
        return sprite;
    }
	
public static void addLunarSprite(int i, int j, String name) {
        RSInterface RSInterface = addInterface(i);
        RSInterface.id = i;
        RSInterface.parentID = i;
        RSInterface.type = 5;
        RSInterface.atActionType = 0;
        RSInterface.drawsTransparent = true;
        //RSInterface.transAmount = 230;
        RSInterface.contentType = 0;
        RSInterface.aByte254 = 0;
        RSInterface.mOverInterToTrigger = 52;
        RSInterface.sprite1 =  imageLoader(j, name);
        RSInterface.width = 500;
        RSInterface.height = 500;
        RSInterface.tooltip = "";
    }
	
public static void drawRune(int i,int id, String runeName) {
        RSInterface RSInterface = addInterface(i);
        RSInterface.type = 5;
        RSInterface.atActionType = 0;
        RSInterface.contentType = 0;
        RSInterface.aByte254 = 0;
        RSInterface.mOverInterToTrigger = 52;
        RSInterface.sprite1 =  imageLoader(id, "Lunar/RUNE");
        RSInterface.width = 500;
        RSInterface.height = 500;
    }
	
	public static void addRuneText(int ID, int runeAmount, int RuneID, TextDrawingArea[] font){
		RSInterface rsInterface = addInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.type = 4;
		rsInterface.atActionType = 0;
		rsInterface.contentType = 0;
		rsInterface.width = 0;
		rsInterface.height = 14;
		rsInterface.aByte254 = 0;
		rsInterface.mOverInterToTrigger= -1;
		rsInterface.anIntArray245 = new int[1];
		rsInterface.anIntArray212 = new int[1];
		rsInterface.anIntArray245[0] = 3;
		rsInterface.anIntArray212[0] = runeAmount;
		rsInterface.valueIndexArray = new int[1][4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = RuneID;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.centerText = true;
		rsInterface.textDrawingAreas = font[0];
		rsInterface.textShadow = true;
		rsInterface.message = "%1/"+runeAmount+"";
		rsInterface.aString228 = "";
		rsInterface.textColor = 12582912;
		rsInterface.anInt219 = 49152;	
	}
	
public static void homeTeleport(){
        RSInterface RSInterface = addInterface(30000);
        RSInterface.tooltip = "Cast @gre@Lunar Home Teleport";
        RSInterface.id = 30000;
        RSInterface.parentID = 30000;
        RSInterface.type = 5;
        RSInterface.atActionType = 5;
        RSInterface.contentType = 0;
        RSInterface.aByte254 = 0;
        RSInterface.mOverInterToTrigger = 30001;
        RSInterface.sprite1 =  imageLoader(1, "Lunar/SPRITE");
        RSInterface.width = 20;
        RSInterface.height = 20;
        RSInterface Int = addInterface(30001);
        Int.isMouseoverTriggered = true;
    	Int.mOverInterToTrigger = -1;
        setChildren(1, Int);
        addLunarSprite(30002, 0, "SPRITE");
        setBounds(30002, 0, 0,0, Int);
	}
public static void addLunar2RunesSmallBox(int ID, int r1, int r2, int ra1, int ra2,int rune1, int lvl,String name, String descr,TextDrawingArea[] TDA,int sid,int suo,int type){
	RSInterface rsInterface = addInterface(ID);
	rsInterface.id = ID;
	rsInterface.parentID = 1151;
	rsInterface.type = 5;
	rsInterface.atActionType = type;
	rsInterface.contentType = 0;
	rsInterface.mOverInterToTrigger = ID+1;
	rsInterface.spellUsableOn = suo;
	rsInterface.selectedActionName = "Cast On";
	rsInterface.width = 20;
	rsInterface.height = 20;
	rsInterface.tooltip = "Cast @gre@"+name;
	rsInterface.spellName = name;
	rsInterface.anIntArray245 = new int[3];
	rsInterface.anIntArray212 = new int[3];
	rsInterface.anIntArray245[0] = 3;
	rsInterface.anIntArray212[0] = ra1;
	rsInterface.anIntArray245[1] = 3;
	rsInterface.anIntArray212[1] = ra2;
	rsInterface.anIntArray245[2] = 3;
	rsInterface.anIntArray212[2] = lvl;
	rsInterface.valueIndexArray = new int[3][];
	rsInterface.valueIndexArray[0] = new int[4];
	rsInterface.valueIndexArray[0][0] = 4;
	rsInterface.valueIndexArray[0][1] = 3214;
	rsInterface.valueIndexArray[0][2] = r1;
	rsInterface.valueIndexArray[0][3] = 0;
	rsInterface.valueIndexArray[1] = new int[4];
	rsInterface.valueIndexArray[1][0] = 4;
	rsInterface.valueIndexArray[1][1] = 3214;
	rsInterface.valueIndexArray[1][2] = r2;
	rsInterface.valueIndexArray[1][3] = 0;
	rsInterface.valueIndexArray[2] = new int[3];
	rsInterface.valueIndexArray[2][0] = 1;
	rsInterface.valueIndexArray[2][1] = 6;
	rsInterface.valueIndexArray[2][2] = 0;
	rsInterface.sprite2 =  imageLoader(sid, "Lunar/LUNARON");
	rsInterface.sprite1 =  imageLoader(sid, "Lunar/LUNAROFF");
	RSInterface INT = addInterface(ID+1);
	INT.isMouseoverTriggered = true;
	INT.mOverInterToTrigger = -1;
	setChildren(7, INT);
	addLunarSprite(ID+2, 0, "Lunar/BOX");
	setBounds(ID+2, 0, 0, 0, INT);
	addText(ID+3, "Level "+(lvl+1)+": "+name, 0xFF981F, true, true, 52, TDA, 1);
	setBounds(ID+3, 90, 4, 1, INT);
	addText(ID+4, descr, 0xAF6A1A, true, true, 52, TDA, 0);	
	setBounds(ID+4, 90, 19, 2, INT);
	setBounds(30016, 37, 35, 3, INT);//Rune
	setBounds(rune1, 112, 35, 4, INT);//Rune
	addRuneText(ID+5, ra1+1, r1, TDA);
	setBounds(ID+5, 50, 66, 5, INT);
	addRuneText(ID+6, ra2+1, r2, TDA);
	setBounds(ID+6, 123, 66, 6, INT);

}

public static void addLunar3RunesSmallBox(int ID, int r1, int r2, int r3, int ra1, int ra2, int ra3,int rune1, int rune2, int lvl,String name, String descr,TextDrawingArea[] TDA, int sid,int suo,int type){
	RSInterface rsInterface = addInterface(ID);
	rsInterface.id = ID;
	rsInterface.parentID = 1151;
	rsInterface.type = 5;
	rsInterface.atActionType = type;
	rsInterface.contentType = 0;
	rsInterface.mOverInterToTrigger = ID+1;
	rsInterface.spellUsableOn = suo;
	rsInterface.selectedActionName = "Cast on";
	rsInterface.width = 20;
	rsInterface.height = 20;
	rsInterface.tooltip = "Cast @gre@"+name;
	rsInterface.spellName = name;
	rsInterface.anIntArray245 = new int[4];
	rsInterface.anIntArray212 = new int[4];
	rsInterface.anIntArray245[0] = 3;
	rsInterface.anIntArray212[0] = ra1;
	rsInterface.anIntArray245[1] = 3;
	rsInterface.anIntArray212[1] = ra2;
	rsInterface.anIntArray245[2] = 3;
	rsInterface.anIntArray212[2] = ra3;
	rsInterface.anIntArray245[3] = 3;
	rsInterface.anIntArray212[3] = lvl;
	rsInterface.valueIndexArray = new int[4][];
	rsInterface.valueIndexArray[0] = new int[4];
	rsInterface.valueIndexArray[0][0] = 4;
	rsInterface.valueIndexArray[0][1] = 3214;
	rsInterface.valueIndexArray[0][2] = r1;
	rsInterface.valueIndexArray[0][3] = 0;
	rsInterface.valueIndexArray[1] = new int[4];
	rsInterface.valueIndexArray[1][0] = 4;
	rsInterface.valueIndexArray[1][1] = 3214;
	rsInterface.valueIndexArray[1][2] = r2;
	rsInterface.valueIndexArray[1][3] = 0;
	rsInterface.valueIndexArray[2] = new int[4];
	rsInterface.valueIndexArray[2][0] = 4;
	rsInterface.valueIndexArray[2][1] = 3214;
	rsInterface.valueIndexArray[2][2] = r3;
	rsInterface.valueIndexArray[2][3] = 0;
	rsInterface.valueIndexArray[3] = new int[3];
	rsInterface.valueIndexArray[3][0] = 1;
	rsInterface.valueIndexArray[3][1] = 6;
	rsInterface.valueIndexArray[3][2] = 0;
	rsInterface.sprite2 =  imageLoader(sid, "Lunar/LUNARON");
	rsInterface.sprite1 =  imageLoader(sid, "Lunar/LUNAROFF");
	RSInterface INT = addInterface(ID+1);
	INT.isMouseoverTriggered = true;
	INT.mOverInterToTrigger = -1;
	setChildren(9, INT);
	addLunarSprite(ID+2, 0, "Lunar/BOX");
	setBounds(ID+2, 0, 0, 0, INT);
	addText(ID+3, "Level "+(lvl+1)+": "+name, 0xFF981F, true, true, 52, TDA, 1);setBounds(ID+3, 90, 4, 1, INT);
	addText(ID+4, descr, 0xAF6A1A, true, true, 52, TDA, 0);	setBounds(ID+4, 90, 19, 2, INT);
	setBounds(30016, 14, 35, 3, INT);
	setBounds(rune1, 74, 35, 4, INT);
	setBounds(rune2, 130, 35, 5, INT);
	addRuneText(ID+5, ra1+1, r1, TDA);
	setBounds(ID+5, 26, 66, 6, INT);
	addRuneText(ID+6, ra2+1, r2, TDA);
	setBounds(ID+6, 87, 66, 7, INT);
	addRuneText(ID+7, ra3+1, r3, TDA);
	setBounds(ID+7, 142, 66, 8, INT);
}

public static void addLunar3RunesBigBox(int ID, int r1, int r2, int r3, int ra1, int ra2, int ra3,int rune1, int rune2, int lvl,String name, String descr,TextDrawingArea[] TDA, int sid,int suo,int type){
	RSInterface rsInterface = addInterface(ID);
	rsInterface.id = ID;
	rsInterface.parentID = 1151;
	rsInterface.type = 5;
	rsInterface.atActionType = type;
	rsInterface.contentType = 0;
	rsInterface.mOverInterToTrigger = ID+1;
	rsInterface.spellUsableOn = suo;
	rsInterface.selectedActionName = "Cast on";
	rsInterface.width = 20;
	rsInterface.height = 20;
	rsInterface.tooltip = "Cast @gre@"+name;
	rsInterface.spellName = name;
	rsInterface.anIntArray245 = new int[4];
	rsInterface.anIntArray212 = new int[4];
	rsInterface.anIntArray245[0] = 3;
	rsInterface.anIntArray212[0] = ra1;
	rsInterface.anIntArray245[1] = 3;
	rsInterface.anIntArray212[1] = ra2;
	rsInterface.anIntArray245[2] = 3;
	rsInterface.anIntArray212[2] = ra3;
	rsInterface.anIntArray245[3] = 3;
	rsInterface.anIntArray212[3] = lvl;
	rsInterface.valueIndexArray = new int[4][];
	rsInterface.valueIndexArray[0] = new int[4];
	rsInterface.valueIndexArray[0][0] = 4;
	rsInterface.valueIndexArray[0][1] = 3214;
	rsInterface.valueIndexArray[0][2] = r1;
	rsInterface.valueIndexArray[0][3] = 0;
	rsInterface.valueIndexArray[1] = new int[4];
	rsInterface.valueIndexArray[1][0] = 4;
	rsInterface.valueIndexArray[1][1] = 3214;
	rsInterface.valueIndexArray[1][2] = r2;
	rsInterface.valueIndexArray[1][3] = 0;
	rsInterface.valueIndexArray[2] = new int[4];
	rsInterface.valueIndexArray[2][0] = 4;
	rsInterface.valueIndexArray[2][1] = 3214;
	rsInterface.valueIndexArray[2][2] = r3;
	rsInterface.valueIndexArray[2][3] = 0;
	rsInterface.valueIndexArray[3] = new int[3];
	rsInterface.valueIndexArray[3][0] = 1;
	rsInterface.valueIndexArray[3][1] = 6;
	rsInterface.valueIndexArray[3][2] = 0;
	rsInterface.sprite2 =  imageLoader(sid, "Lunar/LUNARON");
	rsInterface.sprite1 =  imageLoader(sid, "Lunar/LUNAROFF");
	RSInterface INT = addInterface(ID+1);
	INT.isMouseoverTriggered = true;
	INT.mOverInterToTrigger = -1;
	setChildren(9, INT);
	addLunarSprite(ID+2, 1, "Lunar/BOX");
	setBounds(ID+2, 0, 0, 0, INT);
	addText(ID+3, "Level "+(lvl+1)+": "+name, 0xFF981F, true, true, 52, TDA, 1);setBounds(ID+3, 90, 4, 1, INT);
	addText(ID+4, descr, 0xAF6A1A, true, true, 52, TDA, 0);	setBounds(ID+4, 90, 21, 2, INT);
	setBounds(30016, 14, 48, 3, INT);
	setBounds(rune1, 74, 48, 4, INT);
	setBounds(rune2, 130, 48, 5, INT);
	addRuneText(ID+5, ra1+1, r1, TDA);
	setBounds(ID+5, 26, 79, 6, INT);
	addRuneText(ID+6, ra2+1, r2, TDA);
	setBounds(ID+6, 87, 79, 7, INT);
	addRuneText(ID+7, ra3+1, r3, TDA);
	setBounds(ID+7, 142, 79, 8, INT);
}

public static void addLunar3RunesLargeBox(int ID, int r1, int r2, int r3, int ra1, int ra2, int ra3,int rune1, int rune2, int lvl,String name, String descr,TextDrawingArea[] TDA, int sid,int suo,int type){
	RSInterface rsInterface = addInterface(ID);
	rsInterface.id = ID;
	rsInterface.parentID = 1151;
	rsInterface.type = 5;
	rsInterface.atActionType = type;
	rsInterface.contentType = 0;
	rsInterface.mOverInterToTrigger = ID+1;
	rsInterface.spellUsableOn = suo;
	rsInterface.selectedActionName = "Cast on";
	rsInterface.width = 20;
	rsInterface.height = 20;
	rsInterface.tooltip = "Cast @gre@"+name;
	rsInterface.spellName = name;
	rsInterface.anIntArray245 = new int[4];
	rsInterface.anIntArray212 = new int[4];
	rsInterface.anIntArray245[0] = 3;
	rsInterface.anIntArray212[0] = ra1;
	rsInterface.anIntArray245[1] = 3;
	rsInterface.anIntArray212[1] = ra2;
	rsInterface.anIntArray245[2] = 3;
	rsInterface.anIntArray212[2] = ra3;
	rsInterface.anIntArray245[3] = 3;
	rsInterface.anIntArray212[3] = lvl;
	rsInterface.valueIndexArray = new int[4][];
	rsInterface.valueIndexArray[0] = new int[4];
	rsInterface.valueIndexArray[0][0] = 4;
	rsInterface.valueIndexArray[0][1] = 3214;
	rsInterface.valueIndexArray[0][2] = r1;
	rsInterface.valueIndexArray[0][3] = 0;
	rsInterface.valueIndexArray[1] = new int[4];
	rsInterface.valueIndexArray[1][0] = 4;
	rsInterface.valueIndexArray[1][1] = 3214;
	rsInterface.valueIndexArray[1][2] = r2;
	rsInterface.valueIndexArray[1][3] = 0;
	rsInterface.valueIndexArray[2] = new int[4];
	rsInterface.valueIndexArray[2][0] = 4;
	rsInterface.valueIndexArray[2][1] = 3214;
	rsInterface.valueIndexArray[2][2] = r3;
	rsInterface.valueIndexArray[2][3] = 0;
	rsInterface.valueIndexArray[3] = new int[3];
	rsInterface.valueIndexArray[3][0] = 1;
	rsInterface.valueIndexArray[3][1] = 6;
	rsInterface.valueIndexArray[3][2] = 0;
	rsInterface.sprite2 =  imageLoader(sid, "Lunar/LUNARON");
	rsInterface.sprite1 =  imageLoader(sid, "Lunar/LUNAROFF");
	RSInterface INT = addInterface(ID+1);
	INT.isMouseoverTriggered = true;
	INT.mOverInterToTrigger = -1;
	setChildren(9, INT);
	addLunarSprite(ID+2, 2, "Lunar/BOX");
	setBounds(ID+2, 0, 0, 0, INT);
	addText(ID+3, "Level "+(lvl+1)+": "+name, 0xFF981F, true, true, 52, TDA, 1);
	setBounds(ID+3, 90, 4, 1, INT);
	addText(ID+4, descr, 0xAF6A1A, true, true, 52, TDA, 0);	
	setBounds(ID+4, 90, 34, 2, INT);
	setBounds(30016, 14, 61, 3, INT);
	setBounds(rune1, 74, 61, 4, INT);
	setBounds(rune2, 130, 61, 5, INT);
	addRuneText(ID+5, ra1+1, r1, TDA);
	setBounds(ID+5, 26, 92, 6, INT);
	addRuneText(ID+6, ra2+1, r2, TDA);
	setBounds(ID+6, 87, 92, 7, INT);
	addRuneText(ID+7, ra3+1, r3, TDA);
	setBounds(ID+7, 142, 92, 8, INT);
}
	public static void setChildren(int total,RSInterface i){
        i.children = new int[total];
        i.childX = new int[total];
        i.childY = new int[total];
	}
public static void configureLunar(TextDrawingArea[] TDA){
		homeTeleport();
		drawRune(30003,1, "Fire");
		drawRune(30004,2, "Water");
		drawRune(30005,3, "Air");
		drawRune(30006,4, "Earth");
		drawRune(30007,5, "Mind");
		drawRune(30008,6, "Body");
		drawRune(30009,7, "Death");
		drawRune(30010,8, "Nature");
		drawRune(30011,9, "Chaos");
		drawRune(30012,10, "Law");
		drawRune(30013,11, "Cosmic");
		drawRune(30014,12, "Blood");
		drawRune(30015,13, "Soul");
		drawRune(30016,14, "Astral");
		addLunar3RunesSmallBox(30017, 553, 554, 555, 0, 4, 3, 30003, 30004, 64, "Bake Pie","Bake pies without a stove",TDA,0, 16,2);
		addLunar2RunesSmallBox(30025, 553, 557, 0, 7, 30006, 65,"Cure Plant", "Cure disease on farming patch",TDA,1, 4,2);
		addLunar3RunesBigBox(30032, 553, 564, 558, 0,0, 0, 30013, 30007, 65,"Monster Examine", "Detect the combat statistics of a\\nmonster",TDA, 2,2,2);
		addLunar3RunesSmallBox(30040, 553, 564, 556, 0, 0, 1, 30013, 30005, 66, "NPC Contact","Speak with varied NPCs",TDA,3,0,2);
		addLunar3RunesSmallBox(30048, 553, 563, 557, 0, 0, 9, 30012, 30006, 67, "Cure Other","Cure poisoned players",TDA,4,8,2);
		addLunar3RunesSmallBox(30056, 553, 555, 554, 0, 2, 0, 30004, 30003, 67, "Humidify","fills certain vessels with water",TDA,5,0,5);
		addLunar3RunesSmallBox(30064, 0, 0, 0, 0, 0, 0, 30012, 30006, 68, "null","null",TDA,6,0,5);
		addLunar3RunesBigBox(30075, 0, 0, 0, 0, 0, 0, 30012,  30006, 69,"null", "null",TDA, 7,0,5);
		addLunar3RunesSmallBox(30083, 553, 563, 557, 0, 0, 0, 30012, 30006, 70, "null","null",TDA,8,0,5);
		addLunar3RunesSmallBox(30091, 553, 564, 563, 1, 0, 0, 30013, 30012, 70, "Cure Me","Cures Poison",TDA,9,0,5);
		addLunar2RunesSmallBox(30099, 553, 557, 1, 1, 30006, 70,"Hunter Kit", "Get a kit of hunting gear",TDA,10,0,5);
		addLunar3RunesSmallBox(30106, 553, 563, 555,  0, 0,0, 30012, 30004, 71,"null", "null",TDA,11,0,5);
		addLunar3RunesBigBox(30114, 553, 563, 555, 0, 0, 0, 30012, 30004, 72,"null", "null",TDA, 12,0,5);
		addLunar3RunesSmallBox(30122, 553, 564, 563, 1, 1, 1, 30013, 30012, 73, "Cure Group","Cures Poison on players",TDA,13,0,5);
		addLunar3RunesBigBox(30130, 553, 564, 559, 1, 1, 4, 30013, 30008, 74,"Stat Spy", "Cast on another player to see their\\nskill levels",TDA, 14,8,2);
		addLunar3RunesBigBox(30138, 553, 563, 554, 1, 1, 2, 30012, 30003, 74,"blank", "Nothing currently.",TDA, 15,0,5);
		addLunar3RunesBigBox(30146, 553, 563, 554, 1, 1, 5, 30012, 30003, 75,"blank", "nothing currently",TDA, 16,0,5);
		addLunar3RunesSmallBox(30154, 553, 554, 556, 1, 5, 9, 30003, 30005, 76, "Superglass Make","Make glass without a furnace",TDA,17, 16,2);
		addLunar3RunesSmallBox(30162, 553, 563, 555, 1, 1, 3, 30012, 30004, 77, "Blank","nothing currently",TDA,18,0,5);
		addLunar3RunesSmallBox(30170, 553, 563, 555, 1, 1, 7, 30012, 30004, 78, "Blank","nothing currently",TDA,19,0,5);
		addLunar3RunesBigBox(30178, 553, 564, 559, 1, 0, 4, 30013, 30008, 78,"Dream", "Take a rest and restore hitpoints 3\\n times faster",TDA, 20,0,5);
		addLunar3RunesSmallBox(30186, 553, 557, 555, 1, 9, 4, 30006, 30004, 79, "String Jewellery","String amulets without wool",TDA,21,0,5);
		addLunar3RunesLargeBox(30194, 553, 557, 555, 1, 9, 9, 30006, 30004, 80,"Stat Restore Pot\\nShare", "Share a potion with up to 4 nearby\\nplayers",TDA, 22,0,5);
		addLunar3RunesSmallBox(30202, 553, 554, 555, 1, 6, 6, 30003, 30004, 81, "Magic Imbue","Combine runes without a talisman",TDA,23,0,5);
		addLunar3RunesBigBox(30210, 553, 561, 557, 2, 1, 14, 30010, 30006, 82,"Fertile Soil", "Fertilise a farming patch with super\\ncompost",TDA, 24, 4,2);
		addLunar3RunesBigBox(30218, 553, 557, 555, 2, 11, 9, 30006, 30004, 83,"Boost Potion Share", "Shares a potion with up to 4 nearby\\nplayers",TDA, 25, 0,5);
		addLunar3RunesSmallBox(30226, 553, 563, 555, 2, 2, 9, 30012, 30004, 84, "blank","nothing currently",TDA,26,0,5);
		addLunar3RunesLargeBox(30234, 553, 563, 555, 1, 2, 13, 30012, 30004, 85, "blank", "nothing currently",TDA, 27,0,5);
		addLunar3RunesSmallBox(30242, 553, 557, 561, 2, 14, 0, 30006, 30010, 85, "Plank Make","Turn Logs into planks",TDA,28,16,5);
		addLunar3RunesSmallBox(30250, 553, 563, 555, 2, 2, 9, 30012, 30004, 86, "blank","nothing currently",TDA,29,0,5);//END
		addLunar3RunesSmallBox(30258, 553, 563, 555, 2, 2, 14, 30012, 30004, 87, "blank","nothing currently",TDA,30,0,5);
		addLunar3RunesSmallBox(30266, 553, 563, 555, 2, 2, 7, 30012, 30004, 88, "blank","nothing currently",TDA,31,0,5);
		addLunar3RunesBigBox(30274, 553, 563, 555, 2, 2, 15, 30012, 30004, 89, "blank","nothing currently",TDA,32,0,5);
		addLunar3RunesBigBox(30282, 553, 563, 561, 2, 1, 0, 30012, 30010, 90, "Energy Transfer","Spend hitpoints and SA Energy to\\n give another player hitpoints and run energy",TDA,33,8,2);
		addLunar3RunesBigBox(30290, 553, 563, 565, 2, 2, 0, 30012, 30014, 91, "Heal Other","Transfer up to 75% of hitpoints\\n to another player",TDA,34,8,2);
		addLunar3RunesBigBox(30298, 553, 560, 557, 2, 1, 9, 30009, 30006, 92, "Vengeance Other","Allows another player to rebound\\ndamage to an opponent",TDA,35,8,2);
		addLunar3RunesSmallBox(30306, 9075, 560, 557, 3, 1, 9,30009, 30006, 93, "Vengeance","Rebound damage to an opponent",TDA,36,0,5);
		addLunar3RunesBigBox(30314, 553, 565, 563, 3, 2, 5, 30014, 30012, 94, "Heal Group","Transfer up to 75% of hitpoints to a group",TDA,37,0,5);
		addLunar3RunesBigBox(30322, 553, 564, 563, 2, 1, 0, 30013, 30012, 95, "Spellbook Swap","Change to another spellbook for 1\\nspell cast",TDA,38,0,5);
	}
	
	public static void constructLunar(){
		RSInterface Interface = addInterface(29999);
		setChildren(71, Interface);
		setBounds(30000, 11, 10, 0, Interface);
		setBounds(30017, 40, 9, 1, Interface);
		setBounds(30025, 71, 12, 2, Interface);
		setBounds(30032, 103, 10, 3, Interface);
		setBounds(30040, 135, 12, 4, Interface);
		setBounds(30048, 165, 10, 5, Interface);
		setBounds(30056, 8, 38, 6, Interface);
		setBounds(30064, 39, 39, 7, Interface);
		setBounds(30075, 71, 39, 8, Interface);
		setBounds(30083, 103, 39, 9, Interface);
		setBounds(30091, 135, 39, 10, Interface);
		setBounds(30099, 165, 37, 11, Interface);
		setBounds(30106, 12, 68, 12, Interface);
		setBounds(30114, 42, 68, 13, Interface);
		setBounds(30122, 71, 68, 14, Interface);
		setBounds(30130, 103, 68, 15, Interface);
		setBounds(30138, 135, 68, 16, Interface);
		setBounds(30146, 165, 68, 17, Interface);
		setBounds(30154, 14, 97, 18, Interface);
		setBounds(30162, 42, 97, 19, Interface);
		setBounds(30170, 71, 97, 20, Interface);
		setBounds(30178, 101, 97, 21, Interface);
		setBounds(30186, 135, 98, 22, Interface);
		setBounds(30194, 168, 98, 23, Interface);
		setBounds(30202, 11, 125, 24, Interface);
		setBounds(30210, 42, 124, 25, Interface);
		setBounds(30218, 74, 125, 26, Interface);
		setBounds(30226, 103, 125, 27, Interface);
		setBounds(30234, 135, 125, 28, Interface);
		setBounds(30242, 164, 126, 29, Interface);
		setBounds(30250, 10, 155, 30, Interface);	
		setBounds(30258, 42, 155, 31, Interface);	
		setBounds(30266, 71, 155, 32, Interface);	
		setBounds(30274, 103, 155, 33, Interface);
		setBounds(30282, 136, 155, 34, Interface);	
		setBounds(30290, 165, 155, 35, Interface);	
		setBounds(30298, 13, 185, 36, Interface);	
		setBounds(30306, 42, 185, 37, Interface);	
		setBounds(30314, 71, 184, 38, Interface);
		setBounds(30322, 104, 184, 39, Interface);	
		setBounds(30001, 6, 184, 40, Interface);//hover
		setBounds(30018, 5, 176, 41, Interface);//hover
		setBounds(30026, 5, 176, 42, Interface);//hover
		setBounds(30033, 5, 163, 43, Interface);//hover
		setBounds(30041, 5, 176, 44, Interface);//hover
		setBounds(30049, 5, 176, 45, Interface);//hover
		setBounds(30057, 5, 176, 46, Interface);//hover
		setBounds(30065, 5, 176, 47, Interface);//hover
		setBounds(30076, 5, 163, 48, Interface);//hover
		setBounds(30084, 5, 176, 49, Interface);//hover
		setBounds(30092, 5, 176, 50, Interface);//hover
		setBounds(30100, 5, 176, 51, Interface);//hover
		setBounds(30107, 5, 176, 52, Interface);//hover
		setBounds(30115, 5, 163, 53, Interface);//hover
		setBounds(30123, 5, 176, 54, Interface);//hover
		setBounds(30131, 5, 163, 55, Interface);//hover
		setBounds(30139, 5, 163, 56, Interface);//hover
		setBounds(30147, 5, 163, 57, Interface);//hover
		setBounds(30155, 5, 176, 58, Interface);//hover
		setBounds(30163, 5, 176, 59, Interface);//hover
		setBounds(30171, 5, 176, 60, Interface);//hover
		setBounds(30179, 5, 163, 61, Interface);//hover
		setBounds(30187, 5, 176, 62, Interface);//hover
		setBounds(30195, 5, 149, 63, Interface);//hover
		setBounds(30203, 5, 176, 64, Interface);//hover
		setBounds(30211, 5, 163, 65, Interface);//hover
		setBounds(30219, 5, 163, 66, Interface);//hover
		setBounds(30227, 5, 176, 67, Interface);//hover
		setBounds(30235, 5, 149, 68, Interface);//hover
		setBounds(30243, 5, 176, 69, Interface);//hover
		setBounds(30251, 5, 176, 70, Interface);//hover
	}
	
	
	
		
		public static void setBounds(int ID, int X, int Y, int frame, RSInterface RSinterface){
			RSinterface.children[frame] = ID;
			RSinterface.childX[frame] = X;
			RSinterface.childY[frame] = Y;
		}
		
		public static void addButton(int i, int j, String name, int W, int H, String S, int AT) {
			RSInterface RSInterface = addInterface(i);
			RSInterface.id = i;
			RSInterface.parentID = i;
			RSInterface.type = 5;
			RSInterface.atActionType = AT;
			RSInterface.contentType = 0;
			RSInterface.aByte254 = 0;
			RSInterface.mOverInterToTrigger = 52;
			RSInterface.sprite1 = imageLoader(j,name);
			RSInterface.sprite2 = imageLoader(j,name);
			RSInterface.width = W;
			RSInterface.height = H;
			RSInterface.tooltip = S;
		}
	public static void PVPInterface(TextDrawingArea[] tda) {
		RSInterface RSinterface = addInterface(21200);
		addSprite(21201, 0, "Other/NOTINWILD1");
		addText(21202, "Safe Zone", tda, 1, 0xff9040, true, true);
		int last = 2;
		RSinterface.children = new int[last];
		RSinterface.childX = new int[last];
		RSinterface.childY = new int[last];
		setBounds(21201, 427, 285, 0,RSinterface);
		setBounds(21202, 444, 318, 1,RSinterface);
	}
	
	public static void PVPInterface2(TextDrawingArea[] tda) {
		RSInterface RSinterface = addInterface(21300);
		addSprite(21301, 0, "Other/INWILD1");
		addText(21302, "PvP Hotspot", tda, 1, 0xff9040, true, true);
		int last = 2;
		RSinterface.children = new int[last];
		RSinterface.childX = new int[last];
		RSinterface.childY = new int[last];
		setBounds(21301, 427, 285, 0,RSinterface);
		setBounds(21302, 444, 318, 1,RSinterface);
	}
    public static void achievementTab(TextDrawingArea[] tda) {
        RSInterface tab = addTabInterface(15001);
        RSInterface scroll = addTabInterface(15006);
        addText(15002, "Achievements", tda, 2, 0xEB981F, false, true);
        addSprite(15003, 0, "Achieve/ACH");
        addSprite(15004, 1, "Achieve/ACH");
        addSprite(15005, 0, "Achieve/ACH");
        tab.totalChildren(5);
        tab.child(0, 15002, 5, 5);
        tab.child(1, 15003, 0, 22);
        tab.child(2, 15004, 0, 25);
        tab.child(3, 15005, 0, 249);
        tab.child(4, 15006, 0, 25);
        scroll.width = 174; scroll.height = 224; scroll.scrollMax = 496;
        
        addText(15007, "OverloadX 317 Achievements", tda, 2, 0xFF9900, false, true);
        /*Lumby/Draynor*/
        addClickableText(15008, "Lumbridge/Draynor", "Read Journal", tda, 0, 0xff0000, 90, 11);
        addClickableText(15009, "Beginner", "Read Journal", tda, 0, 0xff0000, 90, 11);
        addClickableText(15010, "Easy", "Read Journal", tda, 0, 0xff0000, 90, 11);
        addClickableText(15011, "Medium", "Read Journal", tda, 0, 0xff0000, 90, 11);
        
        addClickableText(15012, "Ardougne", "Read Journal", tda, 0, 0xff0000, 90, 11);
        /*Ardougne*/
        addClickableText(15013, "Ardougne", "Read Journal", tda, 0, 0xff0000, 90, 11);
        addClickableText(15014, "Easy", "Read Journal", tda, 0, 0xff0000, 90, 11);
        addClickableText(15015, "Medium", "Read Journal", tda, 0, 0xff0000, 90, 11);
        addClickableText(15016, "Hard", "Read Journal", tda, 0, 0xff0000, 90, 11);
        /*Falador*/
        addClickableText(15017, "Falador", "Read Journal", tda, 0, 0xff0000, 90, 11);
        addClickableText(15018, "Easy", "Read Journal", tda, 0, 0xff0000, 90, 11);
        addClickableText(15019, "Medium", "Read Journal", tda, 0, 0xff0000, 90, 11);
        addClickableText(15020, "Hard", "Read Journal", tda, 0, 0xff0000, 90, 11);
        /*Fremennik*/
        addClickableText(15021, "Fremennik", "Read Journal", tda, 0, 0xff0000, 90, 11);
        addClickableText(15022, "Easy", "Read Journal", tda, 0, 0xff0000, 90, 11);
        addClickableText(15023, "Medium", "Read Journal", tda, 0, 0xff0000, 90, 11);
        addClickableText(15024, "Hard", "Read Journal", tda, 0, 0xff0000, 90, 11);
        /*Karamja*/
        addClickableText(15025, "Karamja", "Read Journal", tda, 0, 0xff0000, 90, 11);
        addClickableText(15026, "Easy", "Read Journal", tda, 0, 0xff0000, 90, 11);
        addClickableText(15027, "Medium", "Read Journal", tda, 0, 0xff0000, 90, 11);
        addClickableText(15028, "Hard", "Read Journal", tda, 0, 0xff0000, 90, 11);
        /*Seers*/
        addClickableText(15029, "Seers' Village", "Read Journal", tda, 0, 0xff0000, 90, 11);
        addClickableText(15030, "Easy", "Read Journal", tda, 0, 0xff0000, 90, 11);
        addClickableText(15031, "Medium", "Read Journal", tda, 0, 0xff0000, 90, 11);
        addClickableText(15032, "Hard", "Read Journal", tda, 0, 0xff0000, 90, 11);
        /*Varrock*/
        addClickableText(15033, "Varrock", "Read Journal", tda, 0, 0xff0000, 90, 11);
        addClickableText(15034, "Easy", "Read Journal", tda, 0, 0xff0000, 90, 11);
        addClickableText(15035, "Medium", "Read Journal", tda, 0, 0xff0000, 90, 11);
        addClickableText(15036, "Hard", "Read Journal", tda, 0, 0xff0000, 90, 11);
        scroll.totalChildren(30);
        scroll.child(0, 15007, 14, 11);
        scroll.child(1, 15008, 14, 33);
        scroll.child(2, 15009, 14, 47);
        scroll.child(3, 15010, 14, 61);
        scroll.child(4, 15011, 14, 75);
        scroll.child(5, 15012, 14, 99);
        scroll.child(6, 15013, 14, 121);
        scroll.child(7, 15014, 14, 135);
        scroll.child(8, 15015, 14, 149);
        scroll.child(9, 15016, 14, 163);
        scroll.child(10, 15017, 14, 183);
        scroll.child(11, 15018, 14, 198);
        scroll.child(12, 15019, 14, 211);
        scroll.child(13, 15020, 14, 225);
        scroll.child(14, 15021, 14, 248);
        scroll.child(15, 15022, 14, 262);
        scroll.child(16, 15023, 14, 275);
        scroll.child(17, 15024, 14, 289);
        scroll.child(18, 15025, 14, 312);
        scroll.child(19, 15026, 14, 326);
        scroll.child(20, 15027, 14, 339);
        scroll.child(21, 15028, 14, 353);
        scroll.child(22, 15029, 14, 376);
        scroll.child(23, 15030, 14, 390);
        scroll.child(24, 15031, 14, 403);
        scroll.child(25, 15032, 14, 417);
        scroll.child(26, 15033, 14, 440);
        scroll.child(27, 15034, 14, 454);
        scroll.child(28, 15035, 14, 467);
        scroll.child(29, 15036, 14, 481);
	}

    public static void overloadxTab(TextDrawingArea[] tda) {
        RSInterface tab = addTabInterface(15051);
        RSInterface scroll = addTabInterface(15052);
        addText(15053, "OverloadX", tda, 2, 0xFF9900, false, true);
        addSprite(15054, 0, "Achieve/ACH");
        addSprite(15055, 1, "Achieve/ACH");
        addSprite(15056, 0, "Achieve/ACH");
        tab.totalChildren(5);
        tab.child(0, 15053, 5, 5);
        tab.child(1, 15055, 0, 25);
        tab.child(2, 15056, 0, 249);
        tab.child(3, 15054, 0, 25);
        tab.child(4, 15052, 0, 25);
        scroll.width = 174; scroll.height = 224; scroll.scrollMax = 225;

        addText(15057, "Actions", tda, 2, 0xFF9900, false, true);
        addClickableText(15058, "Request staff assistance", "Use", tda, 0, 0xfa69ff, 90, 11);
        addClickableText(15059, "Request a gamebook", "Use", tda, 0, 0x06ff00, 90, 11);
        addClickableText(15060, "Staff teleport", "Use", tda, 0, 0x0c9709, 90, 11);
        addClickableText(15061, "Donator teleport", "Use", tda, 0, 0x06ff00, 90, 11);
        addClickableText(15062, "Empty", "Use", tda, 0, 0x06ff00, 90, 11);   
	addSprite(15063, 2, "Achieve/ACH");   
        addText(15064, "Thank you for playing!", tda, 2, 0xFF9900, false, true);
        scroll.totalChildren(8);
        scroll.child(0, 15057, 14, 11);
        scroll.child(1, 15058, 28, 33);
        scroll.child(2, 15059, 28, 47);
        scroll.child(3, 15060, 28, 61);
        scroll.child(4, 15061, 28, 75);
        scroll.child(5, 15062, 28, 89);
        scroll.child(6, 15063, 3, 110);
        scroll.child(7, 15064, 20, 209);
       }	
}
