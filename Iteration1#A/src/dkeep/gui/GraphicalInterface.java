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
import dkeep.logic.Rookie;
import dkeep.logic.Suspicious;

import javax.swing.JTextPane;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class GraphicalInterface {

	private JFrame frame;
	private JTextField textField;
	private final JLabel lblNewLabel_2 = new JLabel("CENAS");

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
	
	public void CreateGame1(String difficulty, GameState game)
	{
		int diff = 0;
		if (difficulty == "Drunken") {
			diff = 1;
		} else if (difficulty == "Rookie") {
			diff = 2;
		}
		else if (difficulty == "Suspicious") {
			diff = 3;
        }
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

		game = new GameState(1,1,1,8,1, diff, map, 7,8);
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
		GameState game = null;
		frame = new JFrame();
		frame.setBounds(0, 0, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, -10, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblNewLabel = new JLabel("Number of Ogres");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2, 0, SpringLayout.WEST, lblNewLabel);
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
		springLayout.putConstraint(SpringLayout.SOUTH, textPane, -6, SpringLayout.NORTH, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.EAST, textPane, 251, SpringLayout.WEST, frame.getContentPane());
		textPane.setEditable(false);
		frame.getContentPane().add(textPane);
		
		StyledDocument doc = textPane.getStyledDocument();
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String numberOgres = textField.getText();
				int numOgres = Integer.parseInt(numberOgres);
				String difficulty = (String) comboBox.getSelectedItem();
				int diff = 0;
				if (difficulty == "Drunken") {
					diff = 1;
				} else if (difficulty == "Rookie") {
					diff = 2;
				}
				else if (difficulty == "Suspicious") {
					diff = 3;
		        }
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

				GameState game2 = new GameState(1,1,1,8,1, diff, map, 7,8);
				try {
					doc.insertString(0, retMap(game2), null);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		springLayout.putConstraint(SpringLayout.NORTH, btnStartGame, 0, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, btnStartGame, -24, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnStartGame);
		
		JButton btnExitGame = new JButton("Exit Game");
		btnExitGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				System.exit(0);
			}
		});
	
		springLayout.putConstraint(SpringLayout.SOUTH, btnExitGame, -22, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnExitGame, 0, SpringLayout.EAST, btnStartGame);
		frame.getContentPane().add(btnExitGame);
		
		
		JButton btnUp = new JButton("Up");
		btnUp.setEnabled(false);
		frame.getContentPane().add(btnUp);
		
		JButton btnDown = new JButton("Down");
		btnDown.setEnabled(false);
		springLayout.putConstraint(SpringLayout.EAST, btnUp, 0, SpringLayout.EAST, btnDown);
		springLayout.putConstraint(SpringLayout.WEST, btnDown, 0, SpringLayout.WEST, btnExitGame);
		frame.getContentPane().add(btnDown);
		
		JButton btnLeft = new JButton("Left");
		springLayout.putConstraint(SpringLayout.WEST, btnLeft, 57, SpringLayout.EAST, textPane);
		btnLeft.setEnabled(false);
		springLayout.putConstraint(SpringLayout.NORTH, btnLeft, 112, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnUp, -6, SpringLayout.NORTH, btnLeft);
		springLayout.putConstraint(SpringLayout.NORTH, btnDown, 6, SpringLayout.SOUTH, btnLeft);
		frame.getContentPane().add(btnLeft);
		
		JButton btnRight = new JButton("Right");
		btnRight.setEnabled(false);
		springLayout.putConstraint(SpringLayout.NORTH, btnRight, 0, SpringLayout.NORTH, btnLeft);
		springLayout.putConstraint(SpringLayout.WEST, btnRight, 8, SpringLayout.EAST, btnLeft);
		frame.getContentPane().add(btnRight);
		frame.getContentPane().add(lblNewLabel_2);
	}
	public JTextField getTextField() {
		return textField;
	}
}
