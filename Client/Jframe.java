import sign.signlink;
import java.net.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon; 
public class Jframe extends client implements ActionListener {

    private static JMenuItem menuItem;
	private static JFrame frame;

	public Jframe(String args[]) {
		super();
		try {
			sign.signlink.startpriv(InetAddress.getByName(server));
			initUI();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void setCursor(int id) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Cursor cursor = toolkit.createCustomCursor(toolkit.getImage(signlink.findcachedir()+ "Data/Sprites/CustomCursors/Cursor "+id+".PNG"), new Point(0,0), signlink.findcachedir()+ "Data/Sprites/Cursor "+id+".PNG");
		frame.setCursor(cursor);
	}
	public void initUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JPopupMenu.setDefaultLightWeightPopupEnabled(false);
			frame = new JFrame("OverloadX 317 Client");
			frame.setIconImage(Toolkit.getDefaultToolkit().getImage(signlink.findcachedir()+ "Data/Sprites/Jframe/icon.png"));
			frame.setLayout(new BorderLayout());
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JPanel gamePanel = new JPanel();
			gamePanel.setLayout(new BorderLayout());
			gamePanel.add(this);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			int w = 765;
			int h = 503;
			int x = (dim.width-w)/2;
			int y = (dim.height-h)/2;
			frame.setLocation(x, y);
			gamePanel.setPreferredSize(new Dimension(765, 503));
			JMenu fileMenu = new JMenu("  File  ");
			JMenu toolMenu = new JMenu("  Tools  ");
			JMenu infoMenu = new JMenu("  Info  ");
			JMenu toggleMenu = new JMenu("  Toggles  ");
			JMenu profileMenu = new JMenu("  Profile  ");
			String[] mainButtons = new String[] { "Save", "-", "Exit", "-", "Website", "Forums" };
			String[] toolButtons = new String[] { "Item Search" };
			String[] infoButtons = new String[] { "Client Information" };
			String[] toggleButtons = new String[] { "New HP bar", "Old HP bar", "-", "Toggle 10x Damage", "Untoggle 10x Damage", "-", "Toggle Client Data", "Untoggle Client Data" };
			String[] profileButtons = new String[] { "Donate", "Vote" };
				

			for (String name : mainButtons) {
				JMenuItem menuItem = new JMenuItem(name);
				if (name.equalsIgnoreCase("-")) {
					fileMenu.addSeparator();
				} else {
					menuItem.addActionListener(this);
					fileMenu.add(menuItem);
				}
			}
			for (String name : toolButtons) 
			{
				JMenuItem menuItem = new JMenuItem(name);
				if (name.equalsIgnoreCase("-"))
					toolMenu.addSeparator();
				else 
				{
					menuItem.addActionListener(this);
					toolMenu.add(menuItem);
				}
			}
			
			for (String name : infoButtons) 
			{
				JMenuItem menuItem = new JMenuItem(name);
				if (name.equalsIgnoreCase("-"))
					infoMenu.addSeparator();
				else 
				{
					menuItem.addActionListener(this);
					infoMenu.add(menuItem);
				}
			}
			for (String name : toggleButtons)
			{
				JMenuItem menuItem = new JMenuItem(name);
				if (name.equalsIgnoreCase("-"))
				toggleMenu.addSeparator();
				else
				{
					menuItem.addActionListener(this);
					toggleMenu.add(menuItem);
				}
			}
			for (String name : profileButtons)
			{
				JMenuItem menuItem = new JMenuItem(name);
				if (name.equalsIgnoreCase("-"))
				toggleMenu.addSeparator();
				else
				{
					menuItem.addActionListener(this);
					profileMenu.add(menuItem);
				}
			}
			JMenuBar menuBar = new JMenuBar();
			JMenuBar jmenubar = new JMenuBar();

			frame.add(jmenubar); 
			menuBar.add(fileMenu);
			menuBar.add(toolMenu);
			menuBar.add(infoMenu);
			menuBar.add(toggleMenu);
			menuBar.add(profileMenu);
			//menuBar.add(barMenu);
			frame.getContentPane().add(menuBar, BorderLayout.NORTH);
			frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
			frame.pack();

			frame.setVisible(true); // can see the client
			frame.setResizable(false); // resizeable frame
			init();
		} catch (Exception e) {
				e.printStackTrace();
		}
	}

	public URL getCodeBase() {
		try {
			return new URL("http://" + server + "/cache");
		} catch (Exception e) {
			return super.getCodeBase();
		}
	}

	public URL getDocumentBase() {
		return getCodeBase();
	}

	public void loadError(String s) {
		System.out.println("loadError: " + s);
	}

	public String getParameter(String key) {
			return "";
	}

	private static void openUpWebSite(String url) {
		Desktop d = Desktop.getDesktop();
		try {
			d.browse(new URI(url)); 	
		} catch (Exception e) {
		}
	}
	public void actionPerformed(ActionEvent evt) {
		String cmd = evt.getActionCommand();
		try {
			if (cmd != null) {
				if (cmd.equalsIgnoreCase("Save"))
				{
        					UserLoader.saveuser();
        					System.out.println("Saving file");
					JOptionPane.showMessageDialog(null, "Your login details and toggles have been saved!", "OverloadX 317", 1);
				}
				if (cmd.equalsIgnoreCase("Exit")) {
					System.exit(0);
				}
				if (cmd.equalsIgnoreCase("New HP bar"))
				{
					HPBarToggle = true;
					HpBar = 1;
				}
				if (cmd.equalsIgnoreCase("Old HP bar"))
				{
					HPBarToggle = false;
					HpBar = 0;
				}
				if (cmd.equalsIgnoreCase("Toggle 10x Damage"))
				{
					newDamage = true;
					HitDamage = 1;
				}
				if (cmd.equalsIgnoreCase("Untoggle 10x Damage"))
				{
					newDamage = false;
					HitDamage = 0;
				}
				if (cmd.equalsIgnoreCase("Toggle Client Data"))
				{
					clientData = true;
				}
				if (cmd.equalsIgnoreCase("Untoggle Client Data"))
				{
					clientData = false;
				}
       				 if (cmd.equalsIgnoreCase("Website"))
				{
					openUpWebSite("");	
				}
       				 if (cmd.equalsIgnoreCase("Forums"))
				{
					openUpWebSite("");	
				}
				if (cmd.equalsIgnoreCase("Vote")) {
					openUpWebSite("");
				}
				if (cmd.equalsIgnoreCase("Guides")) {
					openUpWebSite("");
				}
				if (cmd.equalsIgnoreCase("Support")) {
					openUpWebSite("");
				}
				if (cmd.equalsIgnoreCase("Twitter")) {
					openUpWebSite("");
				}
				if (cmd.equalsIgnoreCase("Update Client")) {
					openUpWebSite("");
				}
				if (cmd.equalsIgnoreCase("Donate")) {
					openUpWebSite("");
				}
				if (cmd.equalsIgnoreCase("Highscores")) {
					openUpWebSite("");
				}
			if (cmd.equalsIgnoreCase("Client Information")){
				JOptionPane.showMessageDialog(this, "OverloadX 317 Official Client!\nHead Coder: I Love Santa\nCredits To: Galkon\nHead of Community: Moparscape\nThank you for playing, OverloadX 317 Team!", "Client Updates", JOptionPane.INFORMATION_MESSAGE); 
			}
			if (cmd.equalsIgnoreCase("World Map")){
				launchURL("2. WorldMap.bat");
			}
			if (cmd.equalsIgnoreCase("Item Search")){
				launchURL("1. ItemList.bat");
			}	
			}
		} catch (Exception e) {
		}
	}
}