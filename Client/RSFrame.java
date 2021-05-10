import java.awt.*;

final class RSFrame extends Frame {

	public RSFrame(RSApplet RSApplet_, int i, int j) {
		rsApplet = RSApplet_;
		setTitle("Trace's Client by Galkon");
		setResizable(false);
		setVisible(true);
		toFront();
		setSize(i + 8, j + 28);
		setResizable(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = 765;
		int h = 503;
		int x = (dim.width-w)/2;
		int y = (dim.height-h)/2;
		setLocation(x, y);
		setLocationRelativeTo(null);
	}

	public Graphics getGraphics() {
		Graphics g = super.getGraphics();
		g.translate(4, 24);
		return g;
	}

	public void update(Graphics g) {
		rsApplet.update(g);
	}

	public void paint(Graphics g) {
		rsApplet.paint(g);
	}

	private final RSApplet rsApplet;
}
