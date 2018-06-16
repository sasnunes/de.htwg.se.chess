package de.htwg.chess.aview.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.chess.controller.IChessController;

public class ChessMenuBar extends JMenuBar {

	private static final long serialVersionUID = -644317550911475244L;
	private static final Logger LOGGER = LogManager.getLogger(ChessMenuBar.class.getName());
	IChessController controller;
	JMenu gameMenu;
	JMenuItem restartMenuItem, quitMenuItem;

	JMenu persistenceMenu;
	JMenuItem saveMenuItem, loadMenuItem;

	JMenu helpMenu;
	JMenuItem helpMenuItem, aboutMenuItem;

	public ChessMenuBar(IChessController controller, JFrame frame) {
		this.controller = controller;
		createGameMenu(controller);
		createPersistenceMenu(controller);
		createHelpMenu(frame);

	}

	private void createGameMenu(final IChessController controller) {
		gameMenu = new JMenu("Game");
		restartMenuItem = new JMenuItem("Restart");
		restartMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.restart();
			}
		});
		restartMenuItem.setMnemonic(KeyEvent.VK_R);
		gameMenu.add(restartMenuItem);
		gameMenu.addSeparator();
		quitMenuItem = new JMenuItem("Quit");
		quitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.quit();
			}
		});
		quitMenuItem.setMnemonic(KeyEvent.VK_Q);
		gameMenu.add(quitMenuItem);
		this.add(gameMenu);
	}

	private void createPersistenceMenu(final IChessController controller) {
		persistenceMenu = new JMenu("Persistence");
		saveMenuItem = new JMenuItem("Save");
		saveMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.saveAllFields();
			}
		});
		saveMenuItem.setMnemonic(KeyEvent.VK_S);
		persistenceMenu.add(saveMenuItem);
		persistenceMenu.addSeparator();
		loadMenuItem = new JMenuItem("Load");
		loadMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.loadAllFields();
			}
		});
		loadMenuItem.setMnemonic(KeyEvent.VK_L);
		persistenceMenu.add(loadMenuItem);
		this.add(persistenceMenu);
	}

	private void createHelpMenu(final JFrame frame) {
		helpMenu = new JMenu("?");
		helpMenuItem = new JMenuItem("Chess rules");
		helpMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec(
							"rundll32 url.dll,FileProtocolHandler " + "https://www.chess.com/learn-how-to-play-chess");
				} catch (IOException ioe) {
					LOGGER.info(ioe);
				}
			}
		});
		helpMenu.add(helpMenuItem);
		aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Made by Sascha Nunes and Kevin Deggelmann\n" + "v1.0");
			}
		});
		helpMenu.addSeparator();
		helpMenu.add(aboutMenuItem);
		this.add(helpMenu);
	}
}
