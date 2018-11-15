package inf101.games.invaders.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import inf101.games.invaders.IGame;

public class GUI extends JFrame {
	/**
	 * Katalog hvor GUI-en leter etter bilder (relativt til hvor klassefilene
	 * ligger)
	 */
	public static final String bildeSti = "../images/";

	/**
	 * 
	 */
	private static final long serialVersionUID = -8702692606797266435L;

	public GUI(IGame game) {
		GUIPanel guiPanel = new GUIPanel(game);
		add(guiPanel);
		addKeyListener(guiPanel);

		game.setGUIPanel(guiPanel);
		pack();
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}