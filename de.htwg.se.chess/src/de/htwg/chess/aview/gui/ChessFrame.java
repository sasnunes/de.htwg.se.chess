package de.htwg.chess.aview.gui;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.SpringLayout;

import de.htwg.chess.controller.IChessController;
import de.htwg.util.observer.Event;
import de.htwg.util.observer.IObserver;

public class ChessFrame extends JFrame implements IObserver {
	private static final long serialVersionUID = -5716512372424855918L;
	private IChessController controller;
	private Container pane;
	private static final int DEFAULT_X = 800;
	private static final int DEFAULT_Y = 1000;
	private BoardPanel boardPanel;
	private InfoPanel infoPanel;

	public ChessFrame(final IChessController controller) {
		this.controller = controller;
		controller.addObserver(this);
		setTitle("Chess");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(DEFAULT_X + 5, DEFAULT_Y);
		setJMenuBar(new ChessMenuBar(controller, this));
		pane = getContentPane();
		pane.setLayout(null);
		constructChessPane();
		setResizable(false);
		setVisible(true);
	}

	public final void constructChessPane() {
		boardPanel = new BoardPanel(controller);
		boardPanel.setSize(DEFAULT_X, DEFAULT_X);
		pane.add(boardPanel);

		infoPanel = new InfoPanel(controller);
		infoPanel.setSize(DEFAULT_X, DEFAULT_Y - DEFAULT_X);
		infoPanel.setLocation(0,DEFAULT_X);
		pane.add(infoPanel);

		
	}

	@Override
	public void update(Event e) {
		infoPanel.setTurn(controller.whoIsOnTurn().toString());
		for(ChessButton cb : boardPanel.getButtonList()) {
			cb.setText(controller.getSymboleByField(cb.getXCoor(), cb.getYCoor()));
			
		}
		infoPanel.setStatus(controller.getStatusMessage());
	}

}
