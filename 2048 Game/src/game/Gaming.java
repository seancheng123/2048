package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gaming {
	private static Square[][] grid = new Square[4][4];
	private static int score;
	
	public static void main(String[] args) {
		new GamingGUI();
	}
	
	private static boolean gameOver() {
		boolean over = true;
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j].isEmpty()) {
					over = false;
				}
				if ((i - 1 >= 0 && grid[i - 1][j].equals(grid[i][j])) ||
					(j + 1 < grid[0].length && grid[i][j + 1].equals(grid[i][j])) ||
					(i + 1 < grid.length && grid[i + 1][j].equals(grid[i][j])) ||
					(j - 1 >= 0 && grid[i][j - 1].equals(grid[i][j]))) {
					over = false;
				}
			}
		}
		return over;
	}
	
	private static boolean addSquare() {
		int position = 0;
		Square[] emptySquares = new Square[16];
		Random r = new Random();
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j].isEmpty()) {
					emptySquares[position++] = grid[i][j];
				}
			}
		}
		
		if (position > 0) {
			emptySquares[r.nextInt(position)].startValue();
			return true;
		}
		return false;
	}
	
	private static void reset() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = new Square();
			}
		}
		score = 0;
		addSquare();
		addSquare();
	}
	
	private static class GamingGUI implements KeyListener, ActionListener {
		private JFrame frame;
		private JPanel layout;
		private JPanel scoreBackground;
		private JLabel scoreText;
		private JPanel textBackground;
		private JTextField text;
		private JPanel endBackground;
		private JLabel endText;
		private JPanel restartBackground;
		private JButton button;
		
		public GamingGUI() {
			reset();
			scoreBackground = new JPanel();
			scoreBackground.setLayout(new GridBagLayout());
			
			textBackground = new JPanel();
			textBackground.setLayout(new GridBagLayout());
			
			endBackground = new JPanel();
			endBackground.setLayout(new GridBagLayout());
			
			restartBackground = new JPanel();
			restartBackground.setLayout(new GridBagLayout());
			
			text = new JTextField();
			text.addKeyListener(this);
			
	        layout= new JPanel();
	        layout.setLayout(new GridLayout(5, 4));

			frame = new JFrame("2048");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[0].length; j++) {
					layout.add(new JLabel(grid[i][j].getImageIcon()));
				}
			}
			
			scoreText = new JLabel("Score: " + score);
			scoreText.setFont(new Font(null, Font.BOLD, 20));
			scoreBackground.setBackground(Color.lightGray);
			scoreBackground.add(scoreText);
			layout.add(scoreBackground);
			
			textBackground.setBackground(Color.lightGray);
			text.setPreferredSize(new Dimension(50, 50));
			text.setHorizontalAlignment(JTextField.CENTER);
			JLabel directions = new JLabel("Enter w, a, s, or d: ");
			directions.setFont(new Font(null, Font.PLAIN, 20));
			textBackground.add(directions);
			textBackground.add(text);
			layout.add(textBackground);
			
			restartBackground.setBackground(Color.lightGray);
			button = new JButton("RESTART");
			button.addActionListener(this);
			button.setPreferredSize(new Dimension(100, 50));
			restartBackground.add(button);
			layout.add(restartBackground);
			
			endBackground.setBackground(Color.lightGray);
			endText = new JLabel("");
			endText.setFont(new Font(null, Font.BOLD, 20));
			endBackground.add(endText);
			layout.add(endBackground);
			
			frame.getContentPane().add(layout);
			text.requestFocusInWindow();

			frame.pack();
	        frame.setVisible(true);
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			boolean validKey = true;
			int k;
			int returned;
			boolean moved = false;
			boolean end = false;
			switch (e.getKeyChar()) {
			case 119: 
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						k = 1;
						while (i - k >= 0) {
							returned = grid[i - k][j].merge(grid[i - k + 1][j]);
							if (returned == -1) {
								moved = true;
							}
							else if (returned == -2) {
								break;
							}
							else {
								moved = true;
								score += returned;
								if (returned == 2048) {
									end = true;
								}
								break;
							}
							k++;
						}
					}
				}
				break;
			case 97:
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						k = 1;
						while (j - k >= 0) {
							returned = grid[i][j - k].merge(grid[i][j - k + 1]);
							if (returned == -1) {
								moved = true;
							}
							else if (returned == -2) {
								break;
							}
							else {
								moved = true;
								score += returned;
								if (returned == 2048) {
									end = true;
								}
								break;
							}
							k++;
						}
					}
				}
				break;
			case 115:
				for (int i = grid.length - 1; i >= 0; i--) {
					for (int j = grid[0].length - 1; j >= 0; j--) {
						k = 1;
						while (i + k < grid.length) {
							returned = grid[i + k][j].merge(grid[i + k - 1][j]);
							if (returned == -1) {
								moved = true;
							}
							else if (returned == -2) {
								break;
							}
							else {
								moved = true;
								score += returned;
								if (returned == 2048) {
									end = true;
								}
								break;
							}
							k++;
						}
					}
				}
				break;
			case 100:
				for (int i = grid.length - 1; i >= 0; i--) {
					for (int j = grid[0].length - 1; j >= 0; j--) {
						k = 1;
						while (j + k < grid[0].length) {
							returned = grid[i][j + k].merge(grid[i][j + k - 1]);
							if (returned == -1) {
								moved = true;
							}
							else if (returned == -2) {
								break;
							}
							else {
								moved = true;
								score += returned;
								if (returned == 2048) {
									end = true;
								}
								break;
							}
							k++;
						}
					}
				}
				break;
			default:
				validKey = false;
			}
			if (end) {
				stop(true);
				update();
			}
			else if (validKey && gameOver()) {
				stop(false);
				update();
			}
			else if(validKey && moved) {
				addSquare();
				update();
			}
			text.setText("");
		}
		
		
		private void update() {
			int k = 0;
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[0].length; j++) {
					((JLabel)layout.getComponent(k++)).setIcon(grid[i][j].getImageIcon());
				}
			}
			
			scoreText.setText("Score: " + score);
			
			text.setText("");
			
			frame.pack();
		}
		
		private void stop(boolean win) {
			if (win) {
				endText.setText("YOU WIN!");
			}
			else {
				endText.setText("GAME OVER!");
			}
			text.removeKeyListener(this);
			frame.pack();
		}

		@Override
		public void keyPressed(KeyEvent e) {
			;
		}

		@Override
		public void keyReleased(KeyEvent e) {
			;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			endText.setText("");
			text.requestFocusInWindow();
			if (text.getKeyListeners().length == 0) {
				text.addKeyListener(this);
			}
			reset();
			update();
		}
		
	}
	
}
