package mastermind;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;

public class GameBoardComponent extends JPanel {

	public Color iconColor;  				//currently selected color
	int x;									//x position of game board
	int y;									//y position of game board
	CircleIcon[] c = new CircleIcon[49];	//game board circle icons
	CircleIcon[] p = new CircleIcon[49];	//game board peg icons
	int row;								//current row of guess [1-12]
	int col;								//current column of guess [1-4]
	boolean initialize;						//triggers game board initialization
	String guess;							//guess
	String code;							//secret code
	Random r;								//used for code generation
	String result;							//represents correctness of guess
	int round;								//current round															
	int streak;								//current streak
	boolean reset;							//used to reset game board
	
	/**
	 * Initializes the game board
	 */
	public GameBoardComponent(int x, int y) {
		iconColor = Color.GRAY;
		this.x = x;
		this.y = y;
		initialize = true;
		code = "";
		result = "";
		reset = false;
		r = new Random();
		round = 1;
		streak = 0;
		generateCode();
		gameBoard();
	}
	
	/**
	 * Re-initializes the game board to start a new game
	 */
	public void reset(int x, int y) {
		reset = true;
		this.iconColor = Color.GRAY;
		this.x = x;
		this.y = y;
		this.initialize = true;
		code = "";
		result = "";
		row = 1;
		r = new Random();
		round = 0;
		generateCode();
		gameBoard();
	}
	
	/**
	 * Fill game board with icons for circles and pegs
	 */
	public void gameBoard() {
		for (int i = 1; i <= 48; i = i + 4) {
			CircleIcon circle1 = new CircleIcon(35);
			CircleIcon circle2 = new CircleIcon(35);
			CircleIcon circle3 = new CircleIcon(35);
			CircleIcon circle4 = new CircleIcon(35);
			c[i] = circle1;
			c[i+1] = circle2;
			c[i+2] = circle3;
			c[i+3] = circle4;
		}
		
		for (int i = 1; i <= 48; i = i + 4) {
			CircleIcon peg1 = new CircleIcon(15);
			CircleIcon peg2 = new CircleIcon(15);
			CircleIcon peg3 = new CircleIcon(15);
			CircleIcon peg4 = new CircleIcon(15);
			p[i] = peg1;
			p[i+1] = peg2;
			p[i+2] = peg3;
			p[i+3] = peg4;
		}
	}
	
	/**
	 * Sets the component's icon with a given color
	 * @param color - the color of the icon
	 */
	public void setColor(Color color) {
		this.iconColor = color;
	}
	
	/**
	 * Selects a specific component
	 * @param col - the column of of the component
	 * @param row - the row of the component
	 */
	public void select(int col, int row) {
		this.col = col;
		this.row = row;
	}
	
	/**
	 * Generates the 4-color secret passcode
	 * Possible colors: Red, Yellow, Green, Purple, Black, White
	 */
	public void generateCode() {
		int result = -1;
		int upper = 5;
		for (int i = 0; i < 4; i++) {
			result = r.nextInt(upper);
			if (result == 0) {
				code = code + "R";
			} else if (result == 1) {
				code = code + "Y";
			} else if (result == 2) {
				code = code + "G";
			} else if (result == 3) {
				code = code + "P";
			} else if (result == 4) {
				code = code + "B";
			} else if (result == 5) {
				code = code + "W";
			}
		}
	}
	
	/**
	 * Submits a guess for evaluation
	 * Guess is converted from 4 Color objects to a string of length 4
	 */
	public void submit() {
		Color one = c[((4 * row) - 4) + 1].color;
		Color two = c[((4 * row) - 4) + 2].color;
		Color three = c[((4 * row) - 4) + 3].color;
		Color four = c[((4 * row) - 4) + 4].color;
		
		guess = "";
		
		if (one == Color.RED) {
			this.guess = guess + "R";
		} else if (one == Color.YELLOW) {
			this.guess = guess + "Y";
		} else if (one == Color.GREEN) {
			this.guess = guess + "G";
		} else if (one == Color.MAGENTA) {
			this.guess = guess + "P";
		} else if (one == Color.BLACK) {
			this.guess = guess + "B";
		} else if (one == Color.WHITE) {
			this.guess = guess + "W";
		}
		
		if (two == Color.RED) {
			this.guess = guess + "R";
		} else if (two == Color.YELLOW) {
			this.guess = guess + "Y";
		} else if (two == Color.GREEN) {
			this.guess = guess + "G";
		} else if (two == Color.MAGENTA) {
			this.guess = guess + "P";
		} else if (two == Color.BLACK) {
			this.guess = guess + "B";
		} else if (two == Color.WHITE) {
			this.guess = guess + "W";
		}
		
		if (three == Color.RED) {
			this.guess = guess + "R";
		} else if (three == Color.YELLOW) {
			this.guess = guess + "Y";
		} else if (three == Color.GREEN) {
			this.guess = guess + "G";
		} else if (three == Color.MAGENTA) {
			this.guess = guess + "P";
		} else if (three == Color.BLACK) {
			this.guess = guess + "B";
		} else if (three == Color.WHITE) {
			this.guess = guess + "W";
		}
		
		if (four == Color.RED) {
			this.guess = guess + "R";
		} else if (four == Color.YELLOW) {
			this.guess = guess + "Y";
		} else if (four == Color.GREEN) {
			this.guess = guess + "G";
		} else if (four == Color.MAGENTA) {
			this.guess = guess + "P";
		} else if (four == Color.BLACK) {
			this.guess = guess + "B";
		} else if (four == Color.WHITE) {
			this.guess = guess + "W";
		}

		results();
	}
	
	/**
	 * Evaluates guess compared to passcode
	 * Resulting peg colors stored in string of length 4
	 */
	public void results() {
		String copy = code;
		copy = code;
		result = "";
		if (guess.length() != 4) {
			System.out.println("Error: please guess 4 colors");
			//break;
		}
		for (int j = 0; j < 4; j++) {
			if (copy.substring(j, j+1).equals(guess.substring(j, j+1)) && !(copy.substring(j, j+1).equals("x"))) {
				result = result + "b";
				copy = copy.substring(0, j) + "x" + copy.substring(j+1);
				guess = guess.substring(0, j) + "x" + guess.substring(j+1);
			}
		}
		for (int j = 0; j < 4; j++) {
			for (int k = 0; k < 4; k++) {
				if (copy.substring(j, j+1).equals(guess.substring(k, k+1)) && !(copy.substring(j, j+1).equals("x"))) {
					result = result + "w";
					copy = copy.substring(0, j) + "x" + copy.substring(j+1);  
					guess = guess.substring(0, k) + "x" + guess.substring(k+1);
				}
			}
		}
		round++;
		if (result.equals("bbbb")) {
			streak++;
			JOptionPane.showMessageDialog(null,  "You win!\nStreak: " + streak, "Game Over", JOptionPane.INFORMATION_MESSAGE);
			reset(x, y);
		}
		if (!result.equals("bbbb") && round == 13) {
			streak = 0;
			JOptionPane.showMessageDialog(null,  "You lose.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
			reset(x, y);
		}
	}
	
	/**
	 * Paints the component with 48 colored circle icons and 48 colored peg icons
	 * @param g - graphics used to paint the component
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int i = 1; i <= 48; i = i + 4) {
			//First circle in the row
			CircleIcon circle1 = c[i];
			if (col == 1 && row == ((i - 1) / 4) + 1) {
				circle1.setIconColor(iconColor);
			} else if (circle1.color == Color.GRAY){
				circle1.setIconColor(Color.GRAY);
			} else if (initialize) {
				circle1.setIconColor(Color.GRAY);
			}
			circle1.paintIcon(this, g, x, y * i/2);
			
			//Second circle in the row
			CircleIcon circle2 = c[i+1];
			if (col == 2 && row == ((i - 1) / 4) + 1) {
				circle2.setIconColor(iconColor);
			} else if (circle2.color == Color.GRAY){
				circle2.setIconColor(Color.GRAY);
			} else if (initialize) {
				circle2.setIconColor(Color.GRAY);
			}
			circle2.paintIcon(this, g, x + 60, y * i/2);
			
			//Third circle in the row
			CircleIcon circle3 = c[i+2];
			if (col == 3 && row == ((i - 1) / 4) + 1) {
				circle3.setIconColor(iconColor);
			} else if (circle3.color == Color.GRAY){
				circle3.setIconColor(Color.GRAY);
			} else if (initialize) {
				circle3.setIconColor(Color.GRAY);
			}
			circle3.paintIcon(this, g, x + 120, y * i/2);
			
			//Fourth circle in the row
			CircleIcon circle4 = c[i+3];
			if (col == 4 && row == ((i - 1) / 4) + 1) {
				circle4.setIconColor(iconColor);
			} else if (circle4.color == Color.GRAY){
				circle4.setIconColor(Color.GRAY);
			} else if (initialize) {
				circle4.setIconColor(Color.GRAY);
			}
			circle4.paintIcon(this, g, x + 180, y * i/2);
			
			//First peg in the row
			CircleIcon peg1 = p[i];
			if (result.length() >= 1) {
				if (result.contains("b") && row == ((i - 1) / 4) + 1) {
					peg1.setIconColor(Color.BLACK);
					int index = result.indexOf("b");
					result = result.substring(0, index) + "x" + result.substring(index+1);  
				} else if (result.contains("w") && row == ((i - 1) / 4) + 1){
					peg1.setIconColor(Color.WHITE);
					int index = result.indexOf("w");
					result = result.substring(0, index) + "x" + result.substring(index+1);  
				} 
				peg1.paintIcon(this, g, x + 270, y * i/2);
			}
			if (peg1.color == Color.BLACK){
				peg1.setIconColor(Color.BLACK);
				peg1.paintIcon(this, g, x + 270, y * i/2);
			} else if (peg1.color == Color.WHITE){
				peg1.setIconColor(Color.WHITE);
				peg1.paintIcon(this, g, x + 270, y * i/2);
			} else {
				peg1.setIconColor(Color.GRAY);
				peg1.paintIcon(this, g, x + 270, y * i/2);
			}
			
			//Second peg in the row
			CircleIcon peg2 = p[i+1];
			if (result.length() >= 2) {
				if (result.contains("b") && row == ((i - 1) / 4) + 1) {
					peg2.setIconColor(Color.BLACK);
					int index = result.indexOf("b");
					result = result.substring(0, index) + "x" + result.substring(index+1);  
				} else if (result.contains("w") && row == ((i - 1) / 4) + 1){
					peg2.setIconColor(Color.WHITE);
					int index = result.indexOf("w");
					result = result.substring(0, index) + "x" + result.substring(index+1);  
				}
				peg2.paintIcon(this, g, x + 300, y * i/2);
			}
			if (peg2.color == Color.BLACK){
				peg2.setIconColor(Color.BLACK);
				peg2.paintIcon(this, g, x + 300, y * i/2);
			} else if (peg2.color == Color.WHITE){
				peg2.setIconColor(Color.WHITE);
				peg2.paintIcon(this, g, x + 300, y * i/2);
			} else {
				peg2.setIconColor(Color.GRAY);
				peg2.paintIcon(this, g, x + 300, y * i/2);
			}
			
			//Third peg in the row
			CircleIcon peg3 = p[i+2];
			if (result.length() >= 3) {
				if (result.contains("b") && row == ((i - 1) / 4) + 1) {
					peg3.setIconColor(Color.BLACK);
					int index = result.indexOf("b");
					result = result.substring(0, index) + "x" + result.substring(index+1);  
				} else if (result.contains("w") && row == ((i - 1) / 4) + 1){
					peg3.setIconColor(Color.WHITE);
					int index = result.indexOf("w");
					result = result.substring(0, index) + "x" + result.substring(index+1);  
				} 
				peg3.paintIcon(this, g, x + 330, y * i/2);
			}
			if (peg3.color == Color.BLACK){
				peg3.setIconColor(Color.BLACK);
				peg3.paintIcon(this, g, x + 330, y * i/2);
			} else if (peg3.color == Color.WHITE){
				peg3.setIconColor(Color.WHITE);
				peg3.paintIcon(this, g, x + 330, y * i/2);
			} else {
				peg3.setIconColor(Color.GRAY);
				peg3.paintIcon(this, g, x + 330, y * i/2);
			}
			
			//Fourth peg in the row
			CircleIcon peg4 = p[i+3];
			if (result.length() == 4) {
				if (result.contains("b") && row == ((i - 1) / 4) + 1) {
					peg4.setIconColor(Color.BLACK);
					int index = result.indexOf("b");
					result = result.substring(0, index) + "x" + result.substring(index+1);  
				} else if (result.contains("w") && row == ((i - 1) / 4) + 1){
					peg4.setIconColor(Color.WHITE);
					int index = result.indexOf("w");
					result = result.substring(0, index) + "x" + result.substring(index+1);  
				}
				peg4.paintIcon(this, g, x + 360, y * i/2);
			}
			if (peg4.color == Color.BLACK){
				peg4.setIconColor(Color.BLACK);
				peg4.paintIcon(this, g, x + 360, y * i/2);
			} else if (peg4.color == Color.WHITE){
				peg4.setIconColor(Color.WHITE);
				peg4.paintIcon(this, g, x + 360, y * i/2);
			} else {
				peg4.setIconColor(Color.GRAY);
				peg4.paintIcon(this, g, x + 360, y * i/2);
			}
		}
		initialize = false;
	}
}
