/**
 * @author Jade Webb
 * @version 1.0
 * @date 17 December 2021
 * @email jade.webb@sjsu.edu
 */

package mastermind;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame extends JFrame{
	public final int FRAME_WIDTH = 500;
	public final int FRAME_HEIGHT = 975;
	public GameBoardComponent gameBoard = new GameBoardComponent(50, 35); // game board of circles and pegs
	int row; //tracks row of current guess
	
	/**
	 * Initializes the frame with the following components:
	 	* Row of color buttons
	 	* Row of number buttons
	 	* Game board of circles and pegs
	 */
	public Frame() {
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		row = 1;
		
		//Row of color buttons
		JButton redButton = new JButton("Red");
		redButton.addActionListener(createColorButtonListener(Color.RED));
		JButton yellowButton = new JButton("Yellow");
		yellowButton.addActionListener(createColorButtonListener(Color.YELLOW));
		JButton greenButton = new JButton("Green");
		greenButton.addActionListener(createColorButtonListener(Color.GREEN));
		JButton purpleButton = new JButton("Purple");
		purpleButton.addActionListener(createColorButtonListener(Color.MAGENTA));
		JButton blackButton = new JButton("Black");
		blackButton.addActionListener(createColorButtonListener(Color.BLACK));
		JButton whiteButton = new JButton("White");
		whiteButton.addActionListener(createColorButtonListener(Color.WHITE));
		
		//Row of number buttons
		JButton oneButton = new JButton("1");
		oneButton.addActionListener(createNumberButtonListener(1));
		JButton twoButton = new JButton("2");
		twoButton.addActionListener(createNumberButtonListener(2));
		JButton threeButton = new JButton("3");
		threeButton.addActionListener(createNumberButtonListener(3));
		JButton fourButton = new JButton("4");
		fourButton.addActionListener(createNumberButtonListener(4));
		
		//Submit button
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(createSubmitButtonListener());
		
		//Panel of color buttons
		JPanel colorPanel = new JPanel();
		colorPanel.add(redButton);
		colorPanel.add(yellowButton);
		colorPanel.add(greenButton);
		colorPanel.add(purpleButton);
		colorPanel.add(blackButton);
		colorPanel.add(whiteButton);
		
		//Panel of number buttons arranged to align with game board
		JPanel numberPanel = new JPanel();
		numberPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0,0,0,20);
		numberPanel.add(oneButton, c);
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0,0,0,20);
		numberPanel.add(twoButton, c);
		c.gridx = 2;
		c.gridy = 0;
		c.insets = new Insets(0,0,0,20);
		numberPanel.add(threeButton, c);
		c.gridx = 3;
		c.gridy = 0;
		c.insets = new Insets(0,0,0,20);
		numberPanel.add(fourButton, c);
		
		//Panel with row of color buttons and row of number buttons
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		panel.add(colorPanel, c);
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0,0,0,148);
		panel.add(numberPanel, c);
		
		//Arrange components within frame
		this.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.NORTH);
		this.add(submitButton, BorderLayout.PAGE_END);
		this.add(gameBoard);
	}
	
	/**
	 * Creates an ActionListener that changes the color of the circle icon depending on which color button is clicked
	 * @param color - the color to change the circle icon to
	 * @return an ActionListener
	 */
	public ActionListener createColorButtonListener (final Color color) {
		return event -> {
				gameBoard.setColor(color);
				gameBoard.repaint();
				};
	}
	
	/**
	 * Creates an ActionListener that selects a certain circle icon depending on which number button is clicked
	 * @param col - the column of the circle
	 * @return an ActionListener
	 */
	public ActionListener createNumberButtonListener (final int col) {
		return event -> {
				gameBoard.select(col, row);
				};
	}
	
	/**
	 * Creates an ActionListener that submits the current guess for evaluation
	 * @param color - the color to change the circle icon to
	 * @return an ActionListener
	 */
	public ActionListener createSubmitButtonListener () {
		return event -> {
				gameBoard.submit();
				gameBoard.repaint();
				row++;
				
				//reset row if game board is reset 
				if (gameBoard.reset) {
					row = 1;
					gameBoard.reset = false;
				}
				};
	}
}
