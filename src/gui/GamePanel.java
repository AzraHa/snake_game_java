package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

import javax.swing.JPanel;


import logika.Snake;


/**
 * {@code GamePanel} class je odgovorna za prikazivanje plo�e za igru 
 */
public class GamePanel extends JPanel {
	/**
	 * The Snake instance.
	 */
	Snake snake;
	/**
	 * prikazTabele 
	 */
	JPanel prikazTabele;
	/**
	 * tabelaDugmadi za plocu
	 */
	JButton[][] tabelaDugmadi;
	/**
	 * tabela za plocu
	 */
	int[][] tabela;
	
	
	/**postavljamo hranu,zmiju na plocu
	 * postavljamo tajmer kako bi se zmija kretala na ploci
	 * @param dimenzija sluzi za postavljanje dimenzija table za igru  **/
	public GamePanel(int dimenzija) {
		super();
		
		snake = new Snake();
		tabela = snake.setTable(dimenzija);
		
		//snake.postaviHranu(tabela);
		snake.postaviZmiju(tabela);
		
		setLayout(new BorderLayout());
		prikazTabele = new JPanel();
		
		add(prikazTabele, BorderLayout.CENTER);
		prikazTabele.setLayout(new GridLayout(dimenzija, dimenzija));
		tabelaDugmadi = new JButton[tabela.length][tabela[0].length];
	
		 for(int  i=0; i<tabelaDugmadi.length; i++) {
			  
			  for(int j=0;j<tabelaDugmadi.length;j++) {
				  
				tabelaDugmadi[i][j] = new JButton();
				prikazTabele.add(tabelaDugmadi[i][j]);
				tabelaDugmadi[i][j].setBackground(getBoja(tabela[i][j]));
				tabelaDugmadi[i][j].addKeyListener(new MojKeyListener());
				tabelaDugmadi[i][j].setBorderPainted(false);
			}
		}
		/**Tajmer za kretanje zmije **/
		MojTimerTask mtt = new MojTimerTask();
		Timer timer = new Timer(true);
		timer.scheduleAtFixedRate(mtt, 1000, 1000);
		
	}
	/**
	 * funkcija kojom mijenjamo boje polja u zavisnosti od njihovih vrijednosti **/
	private Color getBoja(int boja) {
		if (boja == 0) {
			return Color.BLACK;
		}
		if (boja == -1) {
			return Color.YELLOW;
		}
		if (boja == 1) {
			return Color.BLUE;
		}
		return new Color(30, 139, 195);
	}
	/**
	 * Osvjezavamo stanje na ploci **/
	public void osvjeziStanjeTabele() {
		for (int i = tabelaDugmadi.length - 1; i >= 0; i--) {
			for (int j = 0; j < tabelaDugmadi[i].length; j++) {
				tabelaDugmadi[i][j].setBackground(getBoja(tabela[i][j]));
			}
		}
	}
	
	/**KeyListener za kontrolu poteza **/
	class MojKeyListener implements KeyListener {

		@SuppressWarnings("static-access")
		@Override
		public void keyPressed(KeyEvent arg0) {
			
			if (arg0.getExtendedKeyCode() == 87) {
				
				snake.pomjeriZmiju(tabela,'w');
				snake.prethodni_potez = 'w';
				
			} else if (arg0.getExtendedKeyCode() == 65) {
				
				snake.pomjeriZmiju(tabela,'a');
				snake.prethodni_potez = 'a';
				
			} else if (arg0.getExtendedKeyCode() == 83) {
				
				snake.pomjeriZmiju(tabela,'s');
				snake.prethodni_potez = 's';

			} else if (arg0.getExtendedKeyCode() == 68) {
				
				snake.pomjeriZmiju(tabela,'d');
				snake.prethodni_potez = 'd';
			}
			osvjeziStanjeTabele();
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	/**Tajmer kojim upravljamo zmijom ukoliko korisnik ne pritisne ni jednu komandu
	 * tako da se zmija krece prema zadnjem unesenom potezu odnosno pravcu**/
	class MojTimerTask extends TimerTask {

		@Override
		public void run() {
			snake.pomjeriZmiju(tabela,Snake.prethodni_potez);
			osvjeziStanjeTabele();
		}
		
	}
}