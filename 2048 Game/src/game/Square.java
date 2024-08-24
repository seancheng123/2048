package game;
import java.util.Random;

import javax.swing.ImageIcon;

public class Square {
	private int value;
	private ImageIcon image;
	private static ImageIcon zero = new ImageIcon(new ImageIcon("Images/0.png").getImage());
	private static ImageIcon two = new ImageIcon(new ImageIcon("Images/2.png").getImage());
	private static ImageIcon four = new ImageIcon(new ImageIcon("Images/4.png").getImage());
	private static ImageIcon eight = new ImageIcon(new ImageIcon("Images/8.png").getImage());
	private static ImageIcon oneSix = new ImageIcon(new ImageIcon("Images/16.png").getImage());
	private static ImageIcon threeTwo = new ImageIcon(new ImageIcon("Images/32.png").getImage());
	private static ImageIcon sixFour = new ImageIcon(new ImageIcon("Images/64.png").getImage());
	private static ImageIcon oneTwoEight = new ImageIcon(new ImageIcon("Images/128.png").getImage());
	private static ImageIcon twoFiveSix = new ImageIcon(new ImageIcon("Images/256.png").getImage());
	private static ImageIcon fiveOneTwo = new ImageIcon(new ImageIcon("Images/512.png").getImage());
	private static ImageIcon oneZeroTwoFour = new ImageIcon(new ImageIcon("Images/1024.png").getImage());
	private static ImageIcon twoZeroFourEight = new ImageIcon(new ImageIcon("Images/2048.png").getImage());
	
	public Square() {
		value = 0;
		image = zero;
	}
	
	public boolean isEmpty() {
		return value == 0;
	}
	
	public void startValue() {
		Random r = new Random();
		value = 2 * (r.nextInt(2) + 1);
		updateImage();
	}
	
	public int merge(Square otherSquare) {
		if (value == 0 && otherSquare.value != 0) {
			value = value + otherSquare.value;
			otherSquare.value = 0;
			otherSquare.updateImage();
			updateImage();
			return -1;
		}
		else if (value == otherSquare.value && value != 0) {
			value = value + otherSquare.value;
			otherSquare.value = 0;
			otherSquare.updateImage();
			updateImage();
			return value;
		}
		else {
			return -2;
		}
	}
	
	public boolean equals(Object otherSquare) {
		Square square = (Square)otherSquare;
		return value == square.value;
	}
	
	public ImageIcon getImageIcon() {
		return image;
	}
	
	private void updateImage() {
		switch (value) {
		case 0:
			image = zero;
			break;
		case 2:
			image = two;
			break;
		case 4:
			image = four;
			break;
		case 8:
			image = eight;
			break;
		case 16:
			image = oneSix;
			break;
		case 32:
			image = threeTwo;
			break;
		case 64:
			image = sixFour;
			break;
		case 128:
			image = oneTwoEight;
			break;
		case 256:
			image = twoFiveSix;
			break;
		case 512:
			image = fiveOneTwo;
			break;
		case 1024:
			image = oneZeroTwoFour;
			break;
		case 2048:
			image = twoZeroFourEight;
			break;
		}
	}
}
