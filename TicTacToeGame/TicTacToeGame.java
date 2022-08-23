import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

class TicTacToeGame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int x, y;
	int count = 0;
	boolean yourOrPlayer1_turn;
	Icon player_1_icon, player_2_icon, icon; 
	JLabel l1,l2;
	JButton button[] = new JButton[9];
	JButton reset;
	Font font = new Font("Comic Sans MS", Font.BOLD, 15);
	static final int WON = 1;
	static final int DRAW = 2;
	static final int GAME_INCOMPLETE = 0;
	int[][] ttt = new int[3][3];
	String player_1_name;
	String player_2_name;
	

	/*********************************************************/

	//Constructor
	//gets call from main()
	TicTacToeGame(){

		super("Tic-Tac-Toe");
	
		x = 10; //x-coordinate of button
		y = 10; //y-coordinate of button
		int j = 0;
		// Creating 9 buttons in game window
		for(int i=0; i<9; i++, x+=100, j++){
			button[i] = new JButton();
			if(j == 3){
				j=0;
				y+=100;
				x=10;
			}
			button[i].setBounds(x, y, 100, 100); //takes parameter x-Coordinate, y-coordinate, width, height
			button[i].setBackground(Color.white);
			add(button[i]);
			button[i].addActionListener(this); //When clicked button --> method actionPerformed is called
		}

		//creating "Reset" button
		reset=new JButton("RESET");
		reset.setBounds(112, 335, 95, 50); //takes parameter x-Coordinate, y-coordinate, width, height
		reset.setBackground(Color.white);
		reset.setFont(font);
		add(reset);
		reset.addActionListener(this); //When clicked button --> method actionPerformed is called
		
		yourOrPlayer1_turn = true;

		player_1_icon = new ImageIcon("smallCircle.png");
		player_2_icon = new ImageIcon("smallStar.png");
		
		setDesign();
		setLayout(null);
		setSize(335,450);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		player_1_name = JOptionPane.showInputDialog(TicTacToeGame.this, "Player 1's Name: ");
		player_2_name = JOptionPane.showInputDialog(TicTacToeGame.this, "Player 2's Name: ");
	} 

	private void setDesign() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/************************************************************/

	//gets call from showButton --> actionListner()
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == reset){
			resetCall();
		}
		else{ 
			for(int i=0; i<9; i++){
				if(e.getSource() == button[i]){
					if(button[i].getIcon() == null){
						if(yourOrPlayer1_turn == true){ 
							icon = player_1_icon;  
						} 
						else{ 
							icon = player_2_icon;         
						}
						button[i].setIcon(icon);
						int xi = i/3;
						int yj = i%3;
						if(yourOrPlayer1_turn) {
							ttt[xi][yj] = 1;
						}
						else {
							ttt[xi][yj] = 2;
						}
						count++;
						int compleated = GAME_INCOMPLETE;
						compleated = check(xi, yj, count);
						if(compleated == WON) {
							reset(ttt);
							if(yourOrPlayer1_turn) {
								JOptionPane.showMessageDialog(TicTacToeGame.this, "!!! Congratulation "+ player_1_name +" !!! --- YOU WON ---");
								resetCall();
								break;
							}
							else {
								JOptionPane.showMessageDialog(TicTacToeGame.this, "!!! Congratulation "+ player_2_name +" !!! --- YOU WON ---");	
								resetCall();
								break;
							}
						}
						else if(compleated == DRAW) {
							reset(ttt);
							JOptionPane.showMessageDialog(TicTacToeGame.this, "!!! It's a DRAW !!!");			 
							break;
						}
						yourOrPlayer1_turn = ! yourOrPlayer1_turn;
					}
				} 
			}
		}
	}

	private void resetCall() {
		count = 0;
		yourOrPlayer1_turn = true;
		for(int i=0; i<9; i++){
			button[i].setIcon(null);
		}
		int nameChange = JOptionPane.showConfirmDialog(TicTacToeGame.this, "Want to change Player's name?");
		if(nameChange == 0) {
			player_1_name = JOptionPane.showInputDialog(TicTacToeGame.this, "Player 1's Name: ");
			player_2_name = JOptionPane.showInputDialog(TicTacToeGame.this, "Player 's Name: ");
		}
	}

	private void reset(int[][] ttt2) {
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				ttt[i][j] = 0;
			}
		}
		count = 0;
	}

	private int check(int xi, int yj, int count) {
		// TODO Auto-generated method stub
		if(ttt[xi][0] != 0 && ttt[xi][0] == ttt[xi][1] && ttt[xi][1] ==ttt[xi][2]) {
			return WON;
		}
		if(ttt[0][yj] != 0 && ttt[0][yj] == ttt[1][yj] && ttt[1][yj] ==ttt[2][yj]) {
			return WON;
		}
		if(ttt[0][0] != 0 && ttt[0][0] == ttt[1][1] && ttt[1][1] ==ttt[2][2]) {
			return WON;
		}
		if(ttt[0][2] != 0 && ttt[0][2] == ttt[1][1] && ttt[1][1] ==ttt[2][0]) {
			return WON;
		}
		if(count==9) {
			return DRAW;
		}
		return GAME_INCOMPLETE;
	}

	/************************************************************/

	public static void main(String []args){
		new TicTacToeGame();
	}

	/************************************************************/

}