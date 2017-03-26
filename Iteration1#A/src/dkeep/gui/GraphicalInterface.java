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
import java.awt.Dimension;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; 

public class GraphicalInterface{
	public MapEditor me;
	public JFrame frame;
	public	JLabel lblNewLabel;
	public SpringLayout springLayout;
	public JButton btnLeft;
	public JButton btnRight;
	public JButton btnUp;
	public JButton btnDown;
	public JButton btnStartGame;
	public JButton btnExitGame;
	public JButton btnCreateAMap;
	public GraphicalInterface i;
	public JTextField textField;
	public JLabel lblNewLabel_1 ;
	public JComboBox comboBox;
	public JTextPane textPane;
	public JLabel lblTextoVarivel;
	public boolean selfMap = false;
	private	GameState game = new GameState();
	public GraphicsPanel gp;
	public int contador = 0;
	int numOgres;
	int difficulty;
	

	public int parseInt(String text)
	{
		int num;

		try
		{
			num = Integer.parseInt(text);
			return num;
		}

		catch(NumberFormatException e){
			return 6;
		}
	}


	public void disabelButtons()
	{
		btnRight.setEnabled(false);
		btnLeft.setEnabled(false);
		btnUp.setEnabled(false);
		btnDown.setEnabled(false);
		btnStartGame.setEnabled(true);
		btnCreateAMap.setEnabled(true);
	}

	public void enableButtons()
	{
		btnRight.setEnabled(true);
		btnLeft.setEnabled(true);
		btnUp.setEnabled(true);
		btnDown.setEnabled(true);
		btnStartGame.setEnabled(false);
		btnCreateAMap.setEnabled(false);
	}

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

	void endGame(String text) {
		lblTextoVarivel.setText(text);
		disabelButtons();
		gp.removeListener();
	}

	void move(char dir) {
		if (getGame().getGMode() == 1 ) {
			if (getGame().updateGameMode1(dir) == false)
				endGame("Ups you lost!");
		} else if (getGame().getGMode() == 2) {
			if (getGame().updateGameMode2(dir) == false)
				endGame("Ups you lost!");
			if(getGame().getVictory())
				endGame("Congratulations! You won!");
		}
		else if (selfMap) {
			if (getGame().updateGameMode(dir) == false) {
				endGame("Ups you lost!");
				selfMap = false;	
			}
			if(getGame().getVictory()){
				endGame("Congratulations! You won!");
				selfMap = false;
			}
		}
	}

	private void initialize(){	
		frame = new JFrame();
		frame.setBounds(0, 0, 450, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(450, 450));
		frame.pack();
		frame.setResizable(false);
		springLayout = new SpringLayout();
		btnLeft = new JButton("Left");
		springLayout.putConstraint(SpringLayout.SOUTH, btnLeft, -210, SpringLayout.SOUTH, frame.getContentPane());
		btnLeft.setFocusable(false);
		initialize1dot2();
	}
	
	public void initialize1dot2() {
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				move('a');
				System.out.println("Hx: "+game.getHero().getPos()[0]+"   Hy: " + game.getHero().getPos()[1]);
				frame.repaint();
			}
		});
		springLayout.putConstraint(SpringLayout.EAST, btnLeft, -83, SpringLayout.EAST, frame.getContentPane());
		btnDown = new JButton("Down");
		springLayout.putConstraint(SpringLayout.SOUTH, btnDown, -173, SpringLayout.SOUTH, frame.getContentPane());
		initialize1dot3();
	}

	public void initialize1dot3() {
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move('s');
				System.out.println("Hx: "+game.getHero().getPos()[0]+"   Hy: " + game.getHero().getPos()[1]);
				frame.repaint();
			}
		});
		initialize2();	
	}
	
	public void initialize2() {
		springLayout.putConstraint(SpringLayout.NORTH, btnDown, 6, SpringLayout.SOUTH, btnLeft);
		btnRight = new JButton("Right");
		btnRight.setFocusable(false);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				move('d');
				System.out.println("Hx: "+game.getHero().getPos()[0]+"   Hy: " + game.getHero().getPos()[1]);
				frame.repaint();
			}
		});
		initialize2dot2();
	}
	
	public void initialize2dot2() {
		springLayout.putConstraint(SpringLayout.WEST, btnRight, 6, SpringLayout.EAST, btnLeft);
		springLayout.putConstraint(SpringLayout.SOUTH, btnRight, -6, SpringLayout.NORTH, btnDown);
		springLayout.putConstraint(SpringLayout.EAST, btnRight, -10, SpringLayout.EAST, frame.getContentPane());
		btnUp = new JButton(" Up ");
		springLayout.putConstraint(SpringLayout.NORTH, btnUp, -278, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnUp, -247, SpringLayout.SOUTH, frame.getContentPane());
		btnUp.setFocusable(false);
		initialize2dot3();
	}

	public void initialize2dot3() {
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				move('w');
				System.out.println("Hx: "+game.getHero().getPos()[0]+"   Hy: " + game.getHero().getPos()[1]);
				frame.repaint();
			}
		});				
		springLayout.putConstraint(SpringLayout.NORTH, btnRight, 6, SpringLayout.SOUTH, btnUp);
		springLayout.putConstraint(SpringLayout.NORTH, btnLeft, 6, SpringLayout.SOUTH, btnUp);
		springLayout.putConstraint(SpringLayout.WEST, btnLeft, -100, SpringLayout.EAST, btnUp);
		initialize3();
	}
	
	public void initialize3() {
		btnStartGame = new JButton("Start Game");
		btnStartGame.setFocusable(false);
		i = this;
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contador = 0;
				if(!selfMap) {
					String numberOgres = textField.getText();
					numOgres = parseInt(numberOgres);
					if(numOgres > 5) {
						lblTextoVarivel.setText("Number of Ogres has to be and integer less and than 5!");
						actionPerformed(arg0);
					}
					difficulty = comboBox.getSelectedIndex() + 1;
					int[] hx = {1,1}, gx = {8,1}, lx = {7,8}, kx = {2,1}, og = {5,5, numOgres};;
					setGame(new GameState(hx, gx, difficulty, lx, kx, og));
				}
				enableButtons();
				gp = new GraphicsPanel(i);
				frame.getContentPane().add(gp);
				gp.requestFocusInWindow();
				lblTextoVarivel.setText("Click on Up/Down/Left/Right to move!");
				frame.repaint();		
			}
		});
		springLayout.putConstraint(SpringLayout.EAST, btnStartGame, -24, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().setLayout(springLayout);
		initialize4();
	}

	public void initialize4() {
		lblNewLabel = new JLabel("Number of Ogres");
		springLayout.putConstraint(SpringLayout.NORTH, btnStartGame, 0, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblNewLabel);
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, -3, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, lblNewLabel);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		lblNewLabel_1 = new JLabel("Guard Level");
		initialize4dot2();
	}

	public void initialize4dot2() {
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 19, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, lblNewLabel);
		frame.getContentPane().add(lblNewLabel_1);
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Drunken", "Rookie", "Suspicious"}));
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 0, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.SOUTH, comboBox, 0, SpringLayout.SOUTH, lblNewLabel_1);
		frame.getContentPane().add(comboBox);
		textPane = new JTextPane();
		springLayout.putConstraint(SpringLayout.NORTH, textPane, 23, SpringLayout.SOUTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.WEST, textPane, 0, SpringLayout.WEST, lblNewLabel);
		initialize4dot3();
	}
	
	public void initialize4dot3() {
		springLayout.putConstraint(SpringLayout.EAST, textPane, 251, SpringLayout.WEST, frame.getContentPane());
		textPane.setFont(new Font("Consolas", Font.PLAIN, 16));
		textPane.setEditable(false);
		lblTextoVarivel = new JLabel("Texto Variavel");
		springLayout.putConstraint(SpringLayout.NORTH, lblTextoVarivel, -22, SpringLayout.SOUTH, frame.getContentPane());
		initialize5();
	}
	
	public void initialize5() {
		springLayout.putConstraint(SpringLayout.EAST, lblTextoVarivel, 0, SpringLayout.EAST, frame.getContentPane());
		lblTextoVarivel.setFont(new Font("Calibri", Font.PLAIN, 10));
		springLayout.putConstraint(SpringLayout.WEST, lblTextoVarivel, 0, SpringLayout.WEST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, lblTextoVarivel, -8, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(lblTextoVarivel);
		lblTextoVarivel.setText("Select the guard and ogres and press Start!");
		btnUp.setEnabled(false);			
		frame.getContentPane().add(btnUp);
		btnExitGame = new JButton("Exit Game");
		springLayout.putConstraint(SpringLayout.SOUTH, textPane, 0, SpringLayout.SOUTH, btnExitGame);
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
		springLayout.putConstraint(SpringLayout.WEST, btnUp, -115, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnUp, -48, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnDown);
		btnLeft.setEnabled(false);
		springLayout.putConstraint(SpringLayout.NORTH, btnExitGame, 12, SpringLayout.SOUTH, btnDown);
		springLayout.putConstraint(SpringLayout.SOUTH, btnExitGame, 45, SpringLayout.SOUTH, btnDown);
		initialize6();
	}
	
	public void initialize6() {
		 springLayout.putConstraint(SpringLayout.EAST, btnDown, -48, SpringLayout.EAST, frame.getContentPane());
			frame.getContentPane().add(btnLeft);		
			btnRight.setEnabled(false);		
			frame.getContentPane().add(btnRight);		
			springLayout.putConstraint(SpringLayout.WEST, btnStartGame, 0, SpringLayout.WEST, btnExitGame);
	        springLayout.putConstraint(SpringLayout.SOUTH, btnStartGame, 31, SpringLayout.NORTH, lblNewLabel);
			frame.getContentPane().add(btnStartGame);	
			gp = new GraphicsPanel();
			springLayout.putConstraint(SpringLayout.NORTH, gp, 32, SpringLayout.SOUTH, lblNewLabel_1);
			springLayout.putConstraint(SpringLayout.WEST, gp, 0, SpringLayout.WEST, lblNewLabel);
			springLayout.putConstraint(SpringLayout.SOUTH, gp, -49, SpringLayout.SOUTH, frame.getContentPane());
			springLayout.putConstraint(SpringLayout.EAST, gp, 264, SpringLayout.WEST, frame.getContentPane());
			
			btnCreateAMap = new JButton("Create a Map");
			btnCreateAMap.setEnabled(true);
			btnCreateAMap.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setGame(new GameState());
					selfMap = true;
					MapEditor m = new MapEditor(i);
					m.frame.setVisible(true);
					frame.setVisible(false);
				}
			});
			springLayout.putConstraint(SpringLayout.NORTH, btnCreateAMap, -70, SpringLayout.NORTH, btnUp);
			springLayout.putConstraint(SpringLayout.WEST, btnCreateAMap, 0, SpringLayout.WEST, btnExitGame);
			springLayout.putConstraint(SpringLayout.SOUTH, btnCreateAMap, -31, SpringLayout.NORTH, btnUp);
			springLayout.putConstraint(SpringLayout.EAST, btnCreateAMap, 0, SpringLayout.EAST, btnExitGame);
			frame.getContentPane().add(btnCreateAMap);
	}

	public JTextField getTextField() {
		return textField;
	}


	public GameState getGame() {
		return game;
	}


	public void setGame(GameState game) {
		this.game = game;
	}
}
