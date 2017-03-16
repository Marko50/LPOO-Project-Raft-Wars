package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.SpringLayout;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

import dkeep.cli.*;
import dkeep.logic.Drunken;
import dkeep.logic.GameMap;
import dkeep.logic.GameState;
import dkeep.logic.Hero;
import dkeep.logic.Key;
import dkeep.logic.Lever;
import dkeep.logic.Ogre;
import dkeep.logic.Rookie;
import dkeep.logic.Suspicious;

import javax.swing.JTextPane;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.Font;

public class GraphicalInterface {

	private JFrame frame;
	private JTextField textField;
	private	GameState game = new GameState();
	public int contador = 0;
	
	
	int numOgres;
	String difficulty;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicalInterface window = new GraphicalInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GraphicalInterface() {
		initialize();
	}
	
	public void CreateGameMode2(int n)
	{
		game.setGMode(2);
		char[] l0a = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' ,'X', 'X' };
		char[] l1a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
		char[] l2a = {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
		char[] l3a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
		char[] l4a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
		char[] l5a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
		char[] l6a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
		char[] l7a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
		char[] l8a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' };
		char[] l9a = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' ,'X', 'X' };
		char[][] map2 = {l0a,l1a,l2a,l3a,l4a,l5a,l6a,l7a,l8a,l9a};
		
		Lever l = new Lever(0, 0, 'k');
		game.setLever(l);
		GameMap map = new GameMap(map2);
		game.setMapa(map);
		Key k = new Key(2,1,'k');
		game.setK(k);
		game.setDefeat(false);
		game.setVictory(false);
        Hero h1 = new Hero(1, 8, 'H');
        game.setHero(h1);
        Ogre[] o = new Ogre[n];
        for(int i = 0; i < n; i++)
        {
        	Ogre o1 = new Ogre(3, 1, 'O', '*');//3 1
        	o[i] = o1;
        }
        game.setO(o);
        game.getMapa().mapSetGameMode2(game.getHero(), game.getOgres(), game.getK());
	}
	
	public void CreatGameMode1(String difficulty)
	{
		game.setGMode(1);
		int[][] movs = { { 8, 1 }, { 7, 1 }, { 7, 2 }, { 7, 3 }, { 7, 4 }, { 7, 5 }, { 6, 5 }, { 5, 5 }, { 4, 5 },
				{ 3, 5 }, { 2, 5 }, { 1, 5 }, { 1, 6 }, { 2, 6 }, { 3, 6 }, { 4, 6 }, { 5, 6 }, { 6, 6 }, { 7, 6 },
				{ 8, 6 }, { 8, 5 }, { 8, 4 }, { 8, 3 }, { 8, 2 } };
 
		char[] l0 = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' ,'X', 'X' };
		char[] l1 = {'X', ' ', ' ', ' ', 'I', ' ', 'X', ' ' ,' ', 'X' };
		char[] l2 = {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ' ,' ', 'X' };
		char[] l3 = {'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ' ,' ', 'X' };
		char[] l4 = {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ' ,' ', 'X' };
		char[] l5 = {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
		char[] l6 = {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
		char[] l7 = {'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X' ,' ', 'X' };
		char[] l8 = {'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'};
		char[] l9 = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' ,'X', 'X' };
		char[][] map = {l0,l1,l2,l3,l4,l5,l6,l7,l8,l9};	
		Hero h = new Hero(1,1, 'H');
		game.setHero(h);
		Key k = new Key(0,0,'k');
		game.setK(k);
		Lever l = new Lever(7,8,'k');
		game.setLever(l);
		GameMap m = new GameMap(map);
		game.setMapa(m);
		game.setDefeat(false);
		if (difficulty == "Drunken") {
			Drunken d = new Drunken(8, 1, 'D', movs);
			game.setGuard(d);
		} else if (difficulty == "Rookie") {
			Rookie r = new Rookie(8, 1, 'R', movs);
			game.setGuard(r);
		}
		else if (difficulty == "Suspicious") {
			Suspicious s = new Suspicious(8, 1, 'S', movs);
			game.setGuard(s);
        }		
		game.getMapa().mapSetGameMode1(game.getLever(), game.getHero(), game.getGuard());					
	}
	
	public boolean MoveGameMode1(char order)
	{
		
		try {
			if(game.getVictory())
				CreateGameMode2(numOgres);
			if (game.moveHero(order)) {
				game.getMapa().setPos(game.getGuard().getPos()[0], game.getGuard().getPos()[1], ' ');
				if (contador == 23 && game.getGuard().getDirection() == 1)
					contador = 0;
				else if (contador == 0 && game.getGuard().getDirection() == -1) {
					contador = 23;
				} else if (game.getGuard().getSleep() == 0)
					contador = contador + game.getGuard().getDirection();

				game.getGuard().gMove(contador);
				game.getMapa().mapSetGameMode1(game.getLever(), game.getHero(), game.getGuard());
				game.heroNearGuard();
			}
			if (game.getDefeat()) {
				game.getMapa().mapSetGameMode1(game.getLever(), game.getHero(), game.getGuard());
				System.exit(1);
				return false;
			}
			
			
		} catch (CloneNotSupportedException e1) {
			e1.printStackTrace();
		}
		return true;
	}
	
	public boolean MoveGameMode2(char order)
	{
		try {
			if(game.moveHero(order))
			{
				game.moveOgres();
				game.ogreOnKey();
				game.getMapa().mapSetGameMode2(game.getHero(), game.getOgres(), game.getK());
				game.heroNearOgre();
			}
			if (game.getDefeat() || game.getVictory()) {
				game.getMapa().mapSetGameMode2(game.getHero(), game.getOgres(), game.getK());
				System.exit(1);
				return false;
			}
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}	
		return true;
	}
	
	String retMap(GameState game)
	{
		String res = "   ";
		char[][] map = game.getMapa().getMap();
		for (int i = 0; i < map.length; i++) {
			for (int c = 0; c < map[i].length; c++) {
				res += map[i][c];
				res += " ";
			}
			res += "\n   ";
		}
		return res;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(){							
		frame = new JFrame();
		frame.setBounds(0, 0, 450, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		SpringLayout springLayout = new SpringLayout();
		JButton btnLeft = new JButton("Left");
		springLayout.putConstraint(SpringLayout.SOUTH, btnLeft, -110, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnLeft, -83, SpringLayout.EAST, frame.getContentPane());
		JButton btnDown = new JButton("Down");
		springLayout.putConstraint(SpringLayout.NORTH, btnDown, 6, SpringLayout.SOUTH, btnLeft);
		JButton btnRight = new JButton("Right");
		springLayout.putConstraint(SpringLayout.WEST, btnRight, 6, SpringLayout.EAST, btnLeft);
		springLayout.putConstraint(SpringLayout.SOUTH, btnRight, -6, SpringLayout.NORTH, btnDown);
		springLayout.putConstraint(SpringLayout.EAST, btnRight, -10, SpringLayout.EAST, frame.getContentPane());
		JButton btnUp = new JButton(" Up ");
		springLayout.putConstraint(SpringLayout.NORTH, btnRight, 6, SpringLayout.SOUTH, btnUp);
		springLayout.putConstraint(SpringLayout.NORTH, btnLeft, 6, SpringLayout.SOUTH, btnUp);
		springLayout.putConstraint(SpringLayout.WEST, btnLeft, -100, SpringLayout.EAST, btnUp);
		JButton btnStartGame = new JButton("Start Game");
		springLayout.putConstraint(SpringLayout.EAST, btnStartGame, -24, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblNewLabel = new JLabel("Number of Ogres");
		springLayout.putConstraint(SpringLayout.NORTH, btnStartGame, 0, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, -3, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, lblNewLabel);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		JLabel lblNewLabel_1 = new JLabel("Guard Level");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 19, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, lblNewLabel);
		frame.getContentPane().add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 0, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.SOUTH, comboBox, 0, SpringLayout.SOUTH, lblNewLabel_1);
		frame.getContentPane().add(comboBox);
				
		JTextPane textPane = new JTextPane();
		springLayout.putConstraint(SpringLayout.SOUTH, textPane, 240, SpringLayout.SOUTH, lblNewLabel_1);
		textPane.setFont(new Font("Consolas", Font.PLAIN, 16));
		springLayout.putConstraint(SpringLayout.NORTH, textPane, 27, SpringLayout.SOUTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.WEST, textPane, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textPane, 251, SpringLayout.WEST, frame.getContentPane());
		textPane.setEditable(false);
		frame.getContentPane().add(textPane);
		
		JLabel lblTextoVarivel = new JLabel("Texto Variavel");
		springLayout.putConstraint(SpringLayout.NORTH, lblTextoVarivel, 6, SpringLayout.SOUTH, textPane);
		springLayout.putConstraint(SpringLayout.WEST, lblTextoVarivel, 22, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblTextoVarivel, 20, SpringLayout.SOUTH, textPane);
		springLayout.putConstraint(SpringLayout.EAST, lblTextoVarivel, 0, SpringLayout.EAST, lblNewLabel_1);
		frame.getContentPane().add(lblTextoVarivel);
				
		StyledDocument doc = textPane.getStyledDocument();
				
		btnUp.setEnabled(false);
			
		MouseAdapter btnUpMouseListener = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				if (game.getGMode() == 1) {
					if (MoveGameMode1('w') == false) {
						btnRight.setEnabled(false);
						btnLeft.setEnabled(false);
						btnUp.setEnabled(false);
						btnDown.setEnabled(false);
						btnStartGame.setEnabled(false);
					}
				} else if (game.getGMode() == 2) {
					if (MoveGameMode2('w') == false) {
						btnRight.setEnabled(false);
						btnLeft.setEnabled(false);
						btnUp.setEnabled(false);
						btnDown.setEnabled(false);
						btnStartGame.setEnabled(false);
					}
				}
				textPane.setText(retMap(game));
			}
		};
		
		
		frame.getContentPane().add(btnUp);
		
		JButton btnExitGame = new JButton("Exit Game");
		springLayout.putConstraint(SpringLayout.WEST, btnExitGame, -50, SpringLayout.EAST, btnLeft);
		
		
		springLayout.putConstraint(SpringLayout.SOUTH, btnExitGame, 45, SpringLayout.SOUTH, btnDown);
		springLayout.putConstraint(SpringLayout.EAST, btnExitGame, 60, SpringLayout.EAST, btnLeft);
		btnExitGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {		
				System.exit(0);
			}
		});
		frame.getContentPane().add(btnExitGame);
		
		
		btnDown.setEnabled(false);
		btnDown.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("null")
			@Override
			public void mouseReleased(MouseEvent e) {
				if (game.getGMode() == 1) {
					if (MoveGameMode1('s') == false) {
						btnRight.setEnabled(false);
						btnLeft.setEnabled(false);
						btnUp.setEnabled(false);
						btnDown.setEnabled(false);
						btnStartGame.setEnabled(false);
					}
				} else if (game.getGMode() == 2) {
					if (MoveGameMode2('s') == false) {
						btnRight.setEnabled(false);
						btnLeft.setEnabled(false);
						btnUp.setEnabled(false);
						btnDown.setEnabled(false);
						btnStartGame.setEnabled(false);
					}
				}
				textPane.setText(retMap(game));
			}
		});
		
		springLayout.putConstraint(SpringLayout.NORTH, btnUp, -178, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnUp, -115, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnUp, -147, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnUp, -48, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnDown);

		btnLeft.setEnabled(false);
		btnLeft.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("null")
			@Override
			public void mouseReleased(MouseEvent e) {
				if (game.getGMode() == 1) {
					if (MoveGameMode1('a') == false) {
						btnRight.setEnabled(false);
						btnLeft.setEnabled(false);
						btnUp.setEnabled(false);
						btnDown.setEnabled(false);
						btnStartGame.setEnabled(false);
					}
				} else if (game.getGMode() == 2) {
					if (MoveGameMode2('a') == false) {
						btnRight.setEnabled(false);
						btnLeft.setEnabled(false);
						btnUp.setEnabled(false);
						btnDown.setEnabled(false);
						btnStartGame.setEnabled(false);
					}
				}
				textPane.setText(retMap(game));
				
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnExitGame, 12, SpringLayout.SOUTH, btnDown);
        springLayout.putConstraint(SpringLayout.SOUTH, btnExitGame, 45, SpringLayout.SOUTH, btnDown);
        springLayout.putConstraint(SpringLayout.SOUTH, btnDown, -73, SpringLayout.SOUTH, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, btnDown, -48, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnLeft);
		
		btnRight.setEnabled(false);
		btnRight.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("null")
			@Override
			public void mouseReleased(MouseEvent e) {
				if (game.getGMode() == 1) {
					if (MoveGameMode1('d') == false) {
						btnRight.setEnabled(false);
						btnLeft.setEnabled(false);
						btnUp.setEnabled(false);
						btnDown.setEnabled(false);
						btnStartGame.setEnabled(false);
					}
				} else if (game.getGMode() == 2) {
					if (MoveGameMode2('d') == false) {
						btnRight.setEnabled(false);
						btnLeft.setEnabled(false);
						btnUp.setEnabled(false);
						btnDown.setEnabled(false);
						btnStartGame.setEnabled(false);
					}
				}
				textPane.setText(retMap(game));
			}
		});
		frame.getContentPane().add(btnRight);
		
				
		btnStartGame.addMouseListener(new MouseAdapter() {
			@SuppressWarnings({ "deprecation", "null" })
			@Override
			public void mouseReleased(MouseEvent e) {
				btnUp.setEnabled(true);
				btnUp.addMouseListener(btnUpMouseListener);
				btnDown.setEnabled(true);
				btnRight.setEnabled(true);
				btnLeft.setEnabled(true);
				String numberOgres = textField.getText();
				numOgres = Integer.parseInt(numberOgres);
				difficulty = (String) comboBox.getSelectedItem();
				CreatGameMode1(difficulty);
				try {
					
					doc.insertString(0, retMap(game), null);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}

			}			
		});
		
		springLayout.putConstraint(SpringLayout.WEST, btnStartGame, 0, SpringLayout.WEST, btnExitGame);
        springLayout.putConstraint(SpringLayout.SOUTH, btnStartGame, 31, SpringLayout.NORTH, lblNewLabel);
		frame.getContentPane().add(btnStartGame);
	}
	public JTextField getTextField() {
		return textField;
	}
}
