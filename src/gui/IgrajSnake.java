package gui;

import javax.swing.JFrame;


/**
 *{@code IgrajSnake} classa je odgovorna za pokretanje ploèe za igru i postavljanje odgovarajucih parametara**/
public class IgrajSnake {
	/**
	 *{@code main} metoda je odgovorna za pokretanje ploèe za igru i postavljanje odgovarajucih parametara**/
	public static void main(String[] args) {
		
		JFrame jf = new JFrame("Snake igrica");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GamePanel mojPanel = new GamePanel(12);
		jf.setSize(500,500);
		jf.setContentPane(mojPanel);
		jf.setVisible(true);
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
	}
	
}