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

	public JFrame frame;
	public	JLabel lblNewLabel;
	public SpringLayout springLayout;
	public JButton btnLeft;
	public JButton btnRight;
	public JButton btnUp;
	public JButton btnDown;
	public JButton btnStartGame;
	public JTextField textField;
	public JLabel lblNewLabel_1 ;
	public JComboBox comboBox;
	public JTextPane textPane;
	public JLabel lblTextoVarivel;
	public	GameState game = new GameState();
	public JPanel gp;
	public int contador = 0;
	int numOgres;
	String difficulty;
	
	
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
		char[] l8 = {'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' };
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
		Ogre[] o = new Ogre[numOgres];
		for (int i = 0; i < numOgres; i++) {
			Ogre o1 = new Ogre(3, 1, 'O', '*');// 3 1
			o[i] = o1;
		}
		game.setO(o);
		game.getMapa().mapSetGameMode1(game.getLever(), game.getHero(), game.getGuard());					
	}
	
	boolean MoveGameMode1(char order)
	{
		
		try {
			if(game.getVictory())
			{
				CreateGameMode2(numOgres);
				game.getMapa().mapSetGameMode2(game.getHero(), game.getOgres(), game.getK());
			}
			
			else if (game.moveHero(order)) {
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
				lblTextoVarivel.setText("Ups you lost!");
				return false;
			}
			
			
		} catch (CloneNotSupportedException e1) {
			e1.printStackTrace();
		}
		return true;
	}
	
	boolean MoveGameMode2(char order)
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
				if(game.getDefeat())
					lblTextoVarivel.setText("Ups you lost!");
				
				else
					lblTextoVarivel.setText("Congratulations! You won!");
				disabelButtons();
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
	
	void move(char dir)
	{
		if (game.getGMode() == 1) {
			if (MoveGameMode1(dir) == false) {
				disabelButtons();
			}
		} else if (game.getGMode() == 2) {
			if (MoveGameMode2(dir) == false) {
				disabelButtons();
			}
		}
	}
	
	
	private void initialize(){	
		
		frame = new JFrame();
		frame.setBounds(0, 0, 450, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(450, 350));
		frame.pack();
		frame.setResizable(false);
		
		springLayout = new SpringLayout();
		
		btnLeft = new JButton("Left");
		btnLeft.setFocusable(false);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				move('a');
				frame.repaint();
				//textPane.setText(retMap(game));
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnLeft, -110, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnLeft, -83, SpringLayout.EAST, frame.getContentPane());
		btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDown.setFocusable(false);
				move('s');
				frame.repaint();
				//textPane.setText(retMap(game));
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnDown, 6, SpringLayout.SOUTH, btnLeft);
		btnRight = new JButton("Right");
		btnRight.setFocusable(false);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				move('d');
				frame.repaint();
				//textPane.setText(retMap(game));
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnRight, 6, SpringLayout.EAST, btnLeft);
		springLayout.putConstraint(SpringLayout.SOUTH, btnRight, -6, SpringLayout.NORTH, btnDown);
		springLayout.putConstraint(SpringLayout.EAST, btnRight, -10, SpringLayout.EAST, frame.getContentPane());
		btnUp = new JButton(" Up ");
		btnUp.setFocusable(false);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				move('w');
				frame.repaint();
				//textPane.setText(retMap(game));
			}
		});				
		springLayout.putConstraint(SpringLayout.NORTH, btnRight, 6, SpringLayout.SOUTH, btnUp);
		springLayout.putConstraint(SpringLayout.NORTH, btnLeft, 6, SpringLayout.SOUTH, btnUp);
		springLayout.putConstraint(SpringLayout.WEST, btnLeft, -100, SpringLayout.EAST, btnUp);
		btnStartGame = new JButton("Start Game");
		btnStartGame.setFocusable(false);
		GraphicalInterface i = this;
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contador = 0;
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				btnRight.setEnabled(true);
				btnLeft.setEnabled(true);
				String numberOgres = textField.getText();
				numOgres = parseInt(numberOgres);
				if(numOgres > 5)
				{
					lblTextoVarivel.setText("Number of Ogres has to be and integer less and than 5!");
					actionPerformed(arg0);
				}
				difficulty = (String) comboBox.getSelectedItem();
				CreatGameMode1(difficulty);
				gp = new GraphicsPanel(game, i);
				frame.getContentPane().add(gp);
				//textPane.setText(retMap(game));
				gp.requestFocusInWindow();
				btnStartGame.setEnabled(false);
				lblTextoVarivel.setText("Click on Up/Down/Left/Right to move!");
				frame.repaint();		
			}
		});
		springLayout.putConstraint(SpringLayout.EAST, btnStartGame, -24, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().setLayout(springLayout);
		
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
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 19, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, lblNewLabel);
		frame.getContentPane().add(lblNewLabel_1);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 0, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.SOUTH, comboBox, 0, SpringLayout.SOUTH, lblNewLabel_1);
		frame.getContentPane().add(comboBox);
		
		
		textPane = new JTextPane();
		springLayout.putConstraint(SpringLayout.NORTH, textPane, 23, SpringLayout.SOUTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.WEST, textPane, 0, SpringLayout.WEST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, textPane, 251, SpringLayout.WEST, frame.getContentPane());
		textPane.setFont(new Font("Consolas", Font.PLAIN, 16));
		textPane.setEditable(false);
		//frame.getContentPane().add(textPane);
		
		lblTextoVarivel = new JLabel("Texto Variavel");
		lblTextoVarivel.setFont(new Font("Calibri", Font.PLAIN, 10));
		springLayout.putConstraint(SpringLayout.NORTH, lblTextoVarivel, 6, SpringLayout.SOUTH, textPane);
		springLayout.putConstraint(SpringLayout.WEST, lblTextoVarivel, 0, SpringLayout.WEST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, lblTextoVarivel, -8, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblTextoVarivel, 0, SpringLayout.EAST, textPane);
		frame.getContentPane().add(lblTextoVarivel);
		
		lblTextoVarivel.setText("Select the guard and ogres and press Start!");
		
		btnUp.setEnabled(false);
					
		frame.getContentPane().add(btnUp);
		
		JButton btnExitGame = new JButton("Exit Game");
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
		springLayout.putConstraint(SpringLayout.NORTH, btnUp, -178, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnUp, -115, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnUp, -147, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnUp, -48, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnDown);
		btnLeft.setEnabled(false);
		springLayout.putConstraint(SpringLayout.NORTH, btnExitGame, 12, SpringLayout.SOUTH, btnDown);
        springLayout.putConstraint(SpringLayout.SOUTH, btnExitGame, 45, SpringLayout.SOUTH, btnDown);
        springLayout.putConstraint(SpringLayout.SOUTH, btnDown, -73, SpringLayout.SOUTH, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, btnDown, -48, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnLeft);		
		btnRight.setEnabled(false);		
		frame.getContentPane().add(btnRight);		
		springLayout.putConstraint(SpringLayout.WEST, btnStartGame, 0, SpringLayout.WEST, btnExitGame);
        springLayout.putConstraint(SpringLayout.SOUTH, btnStartGame, 31, SpringLayout.NORTH, lblNewLabel);
		frame.getContentPane().add(btnStartGame);	
		gp = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, gp, 32, SpringLayout.SOUTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.WEST, gp, 0, SpringLayout.WEST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, gp, -49, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, gp, 264, SpringLayout.WEST, frame.getContentPane());
	}
	public JTextField getTextField() {
		return textField;
	}



}
