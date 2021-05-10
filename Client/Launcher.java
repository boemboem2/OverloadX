import java.awt.*;
import javax.swing.*;
import sign.signlink;

/**
 * Launcher - Shows splash screen and starts client.
 * @author Klepto
 */
public class Launcher extends JWindow {

	/**
	 * Splash screen duration (milliseconds).
	 */
	private int splashDuration = 3000;

	/**
	 * Splash screen image.
	 */
	public String fileName = signlink.findcachedir()+ "Data/Sprites/Jframe/splash.png";

	/**
	 * Shows splash screen in the center of desktop.
	 */
	public void showSplash() {
		JPanel content = (JPanel)getContentPane();
		Image img = new ImageIcon(fileName).getImage();
		int width = img.getWidth(null);
		int height = img.getHeight(null);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width-width)/2;
		int y = (screen.height-height)/2;
		setBounds(x,y,width,height);
		JLabel label = new JLabel(new ImageIcon(fileName));
		content.setOpaque(false);
		label.setOpaque(false);
		content.add(label, BorderLayout.CENTER);
		setVisible(true);
		/**
		 * Sleep can be replaced with various loadings.
		 */
		try { Thread.sleep(splashDuration); } catch (Exception e) {}
		setVisible(false);
	}

	/**
	 * Starts splash screen.
	 */
	public static void main(String[] args) {
		Launcher splash = new Launcher();
		splash.showSplash();
		client.main(new String[] {"10", "0", "highmem", "members", "32"});
	}

}