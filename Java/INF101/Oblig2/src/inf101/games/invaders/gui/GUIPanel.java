package inf101.games.invaders.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import inf101.games.invaders.IGame;
import inf101.games.invaders.IRectangle;
import inf101.games.invaders.sprites.ISprite;

/**
 * GUI for 101-meterskogen
 * 
 * @author anya
 *
 */
public class GUIPanel extends JPanel implements ActionListener, KeyListener, IGUIPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Her ligger ligger verden
	 */
	private Painter mainPanel;
	/**
	 * Viser tid og poeng nederst
	 */
	private JPanel statusPanel;

	private JLabel status;

	/**
	 * Vekker oss hvert halve sekund
	 */
	private javax.swing.Timer timer;

	private IRectangle screenBox;

	private IGame game;

	/**
	 * Oppretter en ny spill-GUI
	 * 
	 * Anbefalt bredde og høyde er 101.
	 * 
	 * Tingene i muligeTing bør ikke endres -- disse er kun til intern bruk av
	 * GUI-klassen. Alle konkrete implementasjoner av ISprite bør ha en
	 * representant i denne samlingen
	 * 
	 * @param spill
	 *            Spillet som skal kontrolleres
	 * @param muligeTing
	 *            En samling av ting som brukeren kan lage ved å trykke på
	 *            knapper
	 * @param width
	 *            Spillets bredde
	 * @param height
	 *            Spillets høyde
	 */
	public GUIPanel(IGame game) {

		this.game = game;
		this.screenBox = game.getBoundingBox();

		timer = new javax.swing.Timer(10, this); // vekk oss hvert 100
													// millisekund

		mainPanel = new Painter();
		statusPanel = new JPanel();
		setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);
		add(statusPanel, BorderLayout.SOUTH);

		// setIgnoreRepaint(true);

		setDoubleBuffered(true);
		status = new JLabel("Ok");
		statusPanel.add(status);

		timer.start();

	}

	/**
	 * Denne blir kalt av Java hver gang brukeren trykker på en knapp, eller
	 * hver gang timer-signalet avfyres.
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {
			timer.restart();
			game.step();
			status.setText(game.status());
			repaint();
			Toolkit.getDefaultToolkit().sync();
		} else
			System.out.println(e);
	}

	/**
	 * Selve spillområdet
	 * 
	 * @author anya
	 *
	 */
	private class Painter extends JComponent {
		private static final long serialVersionUID = -942947300803441395L;

		public Painter() {
			super();
			this.setMinimumSize(new Dimension(screenBox.getWidth(), screenBox
					.getHeight()));
			this.setPreferredSize(new Dimension(screenBox.getWidth(), screenBox
					.getHeight()));
		}

		/**
		 * Denne kalles hver gang vi skal oppdatere skjermbildet, og er
		 * ansvarlig for å tegne alt på skjermen.
		 * 
		 * @see javax.swing.JComponent#paint(java.awt.Graphics)
		 */
		public void paint(Graphics graphics) {
			super.paint(graphics);
			Graphics2D g = (Graphics2D) graphics;
			// sett skaleringen basert på vinduets nåværende størrelse
			g.setBackground(Color.BLACK);
			g.clearRect(0, 0, screenBox.getWidth(), screenBox.getHeight());
			// bakgrunnsbildet
			if (game.background() != null) {
				Image bg = ImageCache.finnBilde(game.background(), screenBox);

				if (bg != null)
					g.drawImage(bg, 0, 0, null);
			}
			// g.setColor(Color.GRAY);
			// g.drawRect(10, 10, i(bredde), i(høyde));
			// tegn alle tingene
			for (ISprite ting : game.sprites()) {
				IRectangle box = ting.getBoundingBox();

				String bilde = ting.getImage().getFileName();
				IRectangle srcBox = ting.getImage().getSourceBox();
				if (bilde != null) {
					Image img = ImageCache.finnBilde(bilde, null);
					if (img != null)
						g.drawImage(img, box.getX(), box.getY(), box.getX()
								+ box.getWidth(), box.getY() + box.getHeight(), //
								srcBox.getX(), srcBox.getY(), srcBox.getX()
										+ srcBox.getWidth(), srcBox.getY()
										+ srcBox.getHeight(),//
								null);

				}

			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);

		game.keyDown(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		game.keyUp(e.getKeyCode());

	}

	/**
	 * Klasse som ordner med å laste inn og skalere bilder. Ferdige bilder blir
	 * mellomlagret til fremtidig bruk.
	 * 
	 * @author anya
	 *
	 */
	private static class ImageCache {
		private static HashMap<String, Image> images = new HashMap<String, Image>();

		/**
		 * @param lyd
		 *            Navnet på billedfilen (leter i <code>GUI.bildeSti</code>
		 *            underkatalogen)
		 * @param størrelse
		 *            Bredde som bildet skal skaleres til
		 */
		public static Image finnBilde(String bilde, IRectangle size) {
			String key = bilde;
			if (size != null)
				key = key + ":" + size.getWidth() + "x" + size.getHeight();
			if (!images.containsKey(key)) {
				Image img;
				if (!images.containsKey(bilde)) {
					try {
						URL url = ImageCache.class.getResource(GUI.bildeSti
								+ bilde);
						if (url == null)
							throw new IOException("Fant ikke filen: '"
									+ GUI.bildeSti + bilde + "'");
						img = ImageIO.read(url);
						images.put(bilde, img);
					} catch (IOException e) {
						e.printStackTrace();
						return null;
					}
				} else
					img = images.get(bilde);

				if (size != null) {
					img = img.getScaledInstance(size.getWidth(),
							size.getHeight(), Image.SCALE_AREA_AVERAGING);
					/*
					 * int w = img.getWidth(null); int h = img.getHeight(null);
					 * int newW = størrelse; int newH =
					 * (int)Math.round((double)h*(((double)newW)/(double)w));
					 * BufferedImage img2 = new BufferedImage(newW, newH,
					 * BufferedImage.TYPE_INT_ARGB); Graphics2D g2 =
					 * img2.createGraphics();
					 * g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					 * RenderingHints.VALUE_INTERPOLATION_BICUBIC);
					 * g2.drawImage(img, 0, 0, newW, newH, null); g2.dispose();
					 * img = img2;
					 */
					images.put(key, img);
				}
				return img;

			}

			return images.get(key);
		}
	}

	@Override
	public void displayTextOverlay(String text, boolean center) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTextOverlay() {
		// TODO Auto-generated method stub
		
	}
}