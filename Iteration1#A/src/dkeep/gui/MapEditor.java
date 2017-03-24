package dkeep.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.SpringLayout;

import dkeep.logic.GameMap;
import dkeep.logic.GameState;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MapEditor {
	private GameState game;
	public GraphicsPanelMapEditor gpme;
	public JFrame frame;
	public JButton btnStartGame;
	public JButton btnExitGame;
	public JButton btnDoorClosed;
	public JButton btnWall;
	public JButton btnFloor;
	public JButton btnHero;
	public JButton btnOgre;
	public JButton btnKey;
	public JButton btnLever;
	private JTextField textField;
	private JTextField textField_1;
	public JButton btnDoorOpened;
	public JButton btnGenerate;
	public boolean HeroSelected;
	public boolean OgreSelected;
	public boolean GuardSelected;
	public boolean DoorOpenedSelected;
	public boolean DoorClosedSelected;
	public boolean KeySelected;
	public boolean LeverSelected;
	public boolean WallSelected;
	public boolean FloorSelected;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapEditor window = new MapEditor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public int parseInt(String text)
	{
		int num;
		
		try
		{
			num = Integer.parseInt(text);
			return num;
		}
		
		catch(NumberFormatException e){
			return 11;
		}
	}
	
	
	public void generateEmptyMap(int y, int x)
	{
		char[][] aux = new char[y][x];
		for(int i = 0; i < y; i++)
		{
			for(int j = 0; j < x; j++)
			{
				aux[i][j] = ' ';
			}
		}
		
		game.setMapa(new GameMap(aux));
	}

	
	/**
	 * Create the application.
	 */
	
	public MapEditor(GameState g)
	{
		this.game = g;
	}
	
	public MapEditor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.pack();
		frame.setBounds(100, 100, 450, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		frame.setPreferredSize(new Dimension(450, 450));
		btnDoorClosed = new JButton("Door Closed");
		springLayout.putConstraint(SpringLayout.EAST, btnDoorClosed, 10, SpringLayout.EAST, frame.getContentPane());
		btnDoorClosed.setEnabled(false);
		btnDoorClosed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HeroSelected = false;
				OgreSelected = false;
				GuardSelected = false;
				DoorOpenedSelected = false;
				DoorClosedSelected = true;
				KeySelected = false;
				LeverSelected = false;
				WallSelected = false;
				FloorSelected = false;
			}
		});
		frame.getContentPane().add(btnDoorClosed);
		
		btnWall = new JButton("Wall");
		springLayout.putConstraint(SpringLayout.WEST, btnWall, 446, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnWall, -10, SpringLayout.EAST, frame.getContentPane());
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HeroSelected = false;
				OgreSelected = false;
				GuardSelected = false;
				DoorOpenedSelected = false;
				DoorClosedSelected = false;
				KeySelected = false;
				LeverSelected = false;
				WallSelected = true;
				FloorSelected = false;
			}
		});
		btnWall.setEnabled(false);
		frame.getContentPane().add(btnWall);
		
		btnFloor = new JButton("Floor");
		springLayout.putConstraint(SpringLayout.NORTH, btnFloor, 68, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnFloor, -10, SpringLayout.EAST, frame.getContentPane());
		btnFloor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HeroSelected = false;
				OgreSelected = false;
				GuardSelected = false;
				DoorOpenedSelected = false;
				DoorClosedSelected = false;
				KeySelected = false;
				LeverSelected = false;
				WallSelected = false;
				FloorSelected = true;
			}
		});
		btnFloor.setEnabled(false);
		springLayout.putConstraint(SpringLayout.SOUTH, btnFloor, -320, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(btnFloor);
		
		btnLever = new JButton("Lever");
		springLayout.putConstraint(SpringLayout.EAST, btnLever, -10, SpringLayout.EAST, frame.getContentPane());
		btnLever.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HeroSelected = false;
				OgreSelected = false;
				GuardSelected = false;
				DoorOpenedSelected = false;
				DoorClosedSelected = false;
				KeySelected = true;
				LeverSelected = false;
				WallSelected = false;
				FloorSelected = false;
			}
		});
		btnLever.setEnabled(false);
		springLayout.putConstraint(SpringLayout.NORTH, btnLever, 6, SpringLayout.SOUTH, btnFloor);
		springLayout.putConstraint(SpringLayout.SOUTH, btnLever, -291, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(btnLever);
		
		btnKey = new JButton("Key");
		springLayout.putConstraint(SpringLayout.WEST, btnKey, 333, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnKey, -10, SpringLayout.EAST, frame.getContentPane());
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HeroSelected = false;
				OgreSelected = false;
				GuardSelected = false;
				DoorOpenedSelected = false;
				DoorClosedSelected = false;
				KeySelected = true;
				LeverSelected = false;
				WallSelected = false;
				FloorSelected = false;
			}
		});
		btnKey.setEnabled(false);
		springLayout.putConstraint(SpringLayout.NORTH, btnKey, 6, SpringLayout.SOUTH, btnLever);
		springLayout.putConstraint(SpringLayout.SOUTH, btnKey, -262, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(btnKey);
		
		btnOgre = new JButton("Ogre");
		springLayout.putConstraint(SpringLayout.WEST, btnOgre, 333, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnOgre, -10, SpringLayout.EAST, frame.getContentPane());
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HeroSelected = false;
				OgreSelected = true;
				GuardSelected = false;
				DoorOpenedSelected = false;
				DoorClosedSelected = false;
				KeySelected = false;
				LeverSelected = false;
				WallSelected = false;
				FloorSelected = false;
			}
		});
		btnOgre.setEnabled(false);
		springLayout.putConstraint(SpringLayout.NORTH, btnOgre, 6, SpringLayout.SOUTH, btnKey);
		frame.getContentPane().add(btnOgre);
		
		btnHero = new JButton("Hero");
		springLayout.putConstraint(SpringLayout.NORTH, btnHero, 18, SpringLayout.SOUTH, btnDoorClosed);
		springLayout.putConstraint(SpringLayout.WEST, btnHero, 363, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnHero, -90, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnHero, 0, SpringLayout.EAST, btnWall);
		btnHero.setEnabled(false);
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HeroSelected = true;
				OgreSelected = false;
				GuardSelected = false;
				DoorOpenedSelected = false;
				DoorClosedSelected = false;
				KeySelected = false;
				LeverSelected = false;
				WallSelected = false;
				FloorSelected = false;
			}
		});
		frame.getContentPane().add(btnHero);
		
		btnStartGame = new JButton("Start Game");
		springLayout.putConstraint(SpringLayout.NORTH, btnStartGame, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnStartGame, -107, SpringLayout.EAST, frame.getContentPane());
		btnStartGame.setEnabled(false);
		frame.getContentPane().add(btnStartGame);
		
		btnExitGame = new JButton("Exit Game");
		springLayout.putConstraint(SpringLayout.NORTH, btnWall, 64, SpringLayout.SOUTH, btnExitGame);
		springLayout.putConstraint(SpringLayout.SOUTH, btnWall, -87, SpringLayout.NORTH, btnExitGame);
		springLayout.putConstraint(SpringLayout.EAST, btnExitGame, -10, SpringLayout.EAST, frame.getContentPane());
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnExitGame, -10, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(btnExitGame);	
		
		JLabel lblHeight = new JLabel("Height");
		springLayout.putConstraint(SpringLayout.NORTH, lblHeight, 4, SpringLayout.NORTH, btnWall);
		frame.getContentPane().add(lblHeight);
		
		JLabel lblWidth = new JLabel("Width");
		springLayout.putConstraint(SpringLayout.NORTH, lblWidth, 15, SpringLayout.SOUTH, lblHeight);
		springLayout.putConstraint(SpringLayout.EAST, lblWidth, -199, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblHeight, 0, SpringLayout.EAST, lblWidth);
		frame.getContentPane().add(lblWidth);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 7, SpringLayout.SOUTH, btnStartGame);
		springLayout.putConstraint(SpringLayout.WEST, textField, 234, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField, 0, SpringLayout.EAST, btnStartGame);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -6, SpringLayout.NORTH, textField_1);
		springLayout.putConstraint(SpringLayout.WEST, btnFloor, 7, SpringLayout.EAST, textField_1);
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 0, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.EAST, textField_1, 86, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, 1, SpringLayout.NORTH, btnFloor);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		btnDoorOpened = new JButton("Door Opened");
		springLayout.putConstraint(SpringLayout.WEST, btnDoorClosed, 10, SpringLayout.WEST, btnDoorOpened);
		springLayout.putConstraint(SpringLayout.EAST, btnDoorOpened, -4, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnDoorClosed, 10, SpringLayout.SOUTH, btnDoorOpened);
		btnDoorOpened.setEnabled(false);
		springLayout.putConstraint(SpringLayout.NORTH, btnDoorOpened, 6, SpringLayout.SOUTH, btnOgre);
		frame.getContentPane().add(btnDoorOpened);
		MapEditor i = this;
		btnGenerate = new JButton("Generate");
		springLayout.putConstraint(SpringLayout.NORTH, btnGenerate, 8, SpringLayout.SOUTH, textField_1);
		springLayout.putConstraint(SpringLayout.SOUTH, btnGenerate, -291, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnGenerate, -107, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnLever, 6, SpringLayout.EAST, btnGenerate);
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int height = parseInt(textField.getText());
				int width = parseInt(textField_1.getText());
				if(width > 10 || height > 10 )
				{
					actionPerformed(e);
				}
				btnHero.setEnabled(true);
				game = new GameState();
				generateEmptyMap(height ,width);
				gpme = new GraphicsPanelMapEditor(i);
				frame.getContentPane().add(gpme);
				gpme.requestFocusInWindow();
				frame.pack();
				frame.repaint();
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnGenerate, 0, SpringLayout.WEST, btnStartGame);
		frame.getContentPane().add(btnGenerate);
	}

	public GameState getGame() {
		return game;
	}

	public void setGame(GameState gi) {
		this.game = gi;
	}
}
