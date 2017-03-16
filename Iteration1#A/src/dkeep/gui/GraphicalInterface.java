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
import dkeep.logic.Rookie;
import dkeep.logic.Suspicious;

import javax.swing.JTextPane;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class GraphicalInterface {

	private JFrame frame;
	private JTextField textField;
	
	public int contador = 0;

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
	

	
	String retMap(GameState game)
	{
		String res = "";
		char[][] map = game.getMapa().getMap();
		for (int i = 0; i < map.length; i++) {
			for (int c = 0; c < map[i].length; c++) {
				//System.out.print(map[i][c]);
				res += map[i][c];
				//System.out.print(" ");
				res += " ";
			}
			//System.out.print("\n");
			res += "\n";
		}
		return res;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(){
		GameState game = new GameState();
		GameState game2 = null;
		
		
		frame = new JFrame();
		frame.setBounds(0, 0, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblNewLabel = new JLabel("Number of Ogres");
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
		springLayout.putConstraint(SpringLayout.NORTH, textPane, 27, SpringLayout.SOUTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.WEST, textPane, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, textPane, -30, SpringLayout.SOUTH, frame.getContentPane());
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
		
		JButton btnUp = new JButton("Up");
		btnUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {

					if (game.moveHero('w')) {
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
					textPane.setText(retMap(game));
					if (game.getDefeat()) {
						game.getMapa().mapSetGameMode1(game.getLever(), game.getHero(), game.getGuard());
						textPane.setText(retMap(game));
						System.exit(1);
					}
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnUp);
		
		JButton btnExitGame = new JButton("Exit Game");
		btnExitGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {		
				System.exit(0);
			}
		});
		JButton btnStartGame = new JButton("Start Game");
		
		springLayout.putConstraint(SpringLayout.SOUTH, btnExitGame, -22, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnExitGame, 0, SpringLayout.EAST, btnStartGame);
		frame.getContentPane().add(btnExitGame);
		
		JButton btnDown = new JButton("Down");
		btnDown.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("null")
			@Override
			public void mouseReleased(MouseEvent e) {
				try {

					if (game.moveHero('s')) {
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
					textPane.setText(retMap(game));
					if (game.getDefeat()) {
						game.getMapa().mapSetGameMode1(game.getLever(), game.getHero(), game.getGuard());
						textPane.setText(retMap(game));
						System.exit(1);
					}
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		springLayout.putConstraint(SpringLayout.EAST, btnUp, 0, SpringLayout.EAST, btnDown);
		springLayout.putConstraint(SpringLayout.WEST, btnDown, 0, SpringLayout.WEST, btnExitGame);
		frame.getContentPane().add(btnDown);
		
		JButton btnLeft = new JButton("Left");
		btnLeft.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("null")
			@Override
			public void mouseReleased(MouseEvent e) {
				try {

					if (game.moveHero('a')) {
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
					textPane.setText(retMap(game));
					if (game.getDefeat()) {
						game.getMapa().mapSetGameMode1(game.getLever(), game.getHero(), game.getGuard());
						textPane.setText(retMap(game));
						System.exit(1);
					}
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnLeft, 57, SpringLayout.EAST, textPane);
		springLayout.putConstraint(SpringLayout.NORTH, btnLeft, 112, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnUp, -6, SpringLayout.NORTH, btnLeft);
		springLayout.putConstraint(SpringLayout.NORTH, btnDown, 6, SpringLayout.SOUTH, btnLeft);
		frame.getContentPane().add(btnLeft);
		
		JButton btnRight = new JButton("Right");
		btnRight.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("null")
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					if (game.moveHero('d')) {
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
					textPane.setText(retMap(game));
					if (game.getDefeat()) {
						game.getMapa().mapSetGameMode1(game.getLever(), game.getHero(), game.getGuard());
						textPane.setText(retMap(game));
						System.exit(1);
					}
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnRight, 0, SpringLayout.NORTH, btnLeft);
		springLayout.putConstraint(SpringLayout.WEST, btnRight, 8, SpringLayout.EAST, btnLeft);
		frame.getContentPane().add(btnRight);
		
		lblTextoVarivel.setText("Select the number of Ogees and the type of Guard and hit Start Game\n");
				
		btnStartGame.addMouseListener(new MouseAdapter() {
			@SuppressWarnings({ "deprecation", "null" })
			@Override
			public void mouseReleased(MouseEvent e) {
				
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
				String numberOgres = textField.getText();
				int numOgres = Integer.parseInt(numberOgres);
				String difficulty = (String) comboBox.getSelectedItem();
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
				try {
					
					doc.insertString(0, retMap(game), null);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	
			}
			
			
		});
		
		springLayout.putConstraint(SpringLayout.NORTH, btnStartGame, 0, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, btnStartGame, -24, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnStartGame);
		
		
		
		
		
		

	}
	public JTextField getTextField() {
		return textField;
	}
}
