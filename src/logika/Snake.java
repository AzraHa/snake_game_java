package logika;

import java.util.Scanner;


/**
 *
 * @author Azra
 *
 */
public class Snake {

	/**
	 * @param REZULTAT varijabla za rezultat
	 */
	public static int REZULTAT; 
	 
	/**
	 * varijabla za provjeru poteza
	 */
	public static int PROMIJENJEN;
	
	/**
	 *@param potez varijabla za spremanje poteza
	 */
	public static char potez = 'w'; 

	/**
	 * @param prethodni_potez varijabla za spremanje prethodnog poteza
	 */
	public static char prethodni_potez = 'd';
	
	/**
	 * @param tabla - tabla za igru
	 */
	public int [][]tabla;
	
	/** 
	 *@param dimenzija  
	 *funkcija vraca tablu dimenzija*dimenzija
	**/
	public int[][] setTable(int dimenzija) {
		
	     return this.tabla = new int[dimenzija][dimenzija];
	 }
	/** @param tabla postavljamo zmiju u tabelu na poziciju [0][0] te hranu na random poziciju **/
	public void postaviZmiju(int[][] tabla) {
		  
		  tabla[0][0] = 1;

		  postaviHranu(tabla);

	  }

	  /** Cuva prethodni potez tako da korisnik unosi samo enter za kretanje **/
	  public void unosPoteza() {

		  Scanner unos = new Scanner(System.in);

	      try {
	    	  
	    	  potez =  unos.nextLine().charAt(0);
	    	  
	    	  if(potez=='\n') potez = prethodni_potez;
	    	  
	    	  else prethodni_potez = potez;
	      
	      } catch (StringIndexOutOfBoundsException e) {
	    	  
	    	  potez = prethodni_potez;
	      }
	  }
	  /**Ispis pravila igre za korisnika**/

	  public void ispisiPravila() {
		  
		  System.out.println("Unesite neki od sljedecih znakova \n w=GORE, a=LIJEVO, s=DOLE, d=DESNO, q=KRAJ \n");

	  }

	  /**
	   * @param tabla 
	   * Postavljamo hranu na random poziciju na tabli
	   * **/
	  public void postaviHranu(int[][] tabla) {
		  
		  boolean postavljena = false; 

		  int red, kolona;

		  while(postavljena == false) {
			  
			  red = (int)(Math.random() * tabla.length);
			  
			  kolona = (int)(Math.random() * tabla[0].length);
	
		      if(tabla[red][kolona] == 0) {
		    	  
		    	  tabla[red][kolona] = -1;
		    	  
		    	  postavljena = true;
		      }
	      }
	  }
	  
	  /**Ispis table u konzoli**/
	  public void ispisiTablu(int[][] tabla) {
		  
		  boolean nema_hrane = true;

		  for(int red=0;red<tabla.length;red++) {
			  
			  for(int kolona=0;kolona<tabla[0].length;kolona++) {

		        /**X je galava ,- su polja table, @ je hrana , O je tijelo**/
				  
				  if(tabla[red][kolona] == 1) System.out.print("X  ");
				  
				  else if(tabla[red][kolona] == 0) System.out.print("-  ");
				  
				  else if(tabla[red][kolona] == -1) System.out.print("@  ");
				  
				  else System.out.print("O  ");

				  if(tabla[red][kolona] == -1) nema_hrane = false;
			  }
			  System.out.println();
		  }
		  if(nema_hrane == true) postaviHranu(tabla);
	  }

	  /**
	   *@param tabla, @param potez
	   *Sve dok korisnik ne zeli napustiti igru pomjeramo zmiju 
	   **/
	  public void promijeniTablu(int[][] tabla, char potez) {
		  
		  if(potez!='q') { 
			  
			  pomjeriZmiju(tabla,potez);
			  
			  ispisiTablu(tabla);
		  }
		  
	  }

	  
	  /**
	   * *@param tabla, @param potez
	   * Pomjeramo zmiju na osnovu slova tj.pravila**/
	  public void pomjeriZmiju(int[][] tabla, char potez) {
		  
		  int swapA = 0;
	    
		  int swapB = 0;

	  	
		  outerloop:
	  		
			  for(int red=0;red<tabla.length;red++) {
	  			
				  for(int kolona=0;kolona<tabla[0].length;kolona++) {
					  
					  if(tabla[red][kolona] == 1) {
	  					
						  swapA = red;
	  					
						  swapB = kolona;
						  
						  if(potez=='w') potezGore(tabla, swapA, swapB);
						  
						  if(potez=='s') potezDole(tabla,swapA, swapB);
						  
						  if(potez=='a') potezLijevo(tabla, swapA, swapB);
						  
						  if(potez=='d') potezDesno(tabla, swapA, swapB);
						  
						  tabla[swapA][swapB] = 0;
						  
						  break outerloop;
						  
					  }
					  
				  }
				  
			  }
		  
		  int len = duzinaZmije(tabla);
		  
		  pomjeriTijeloZmije(tabla,len,swapA,swapB);

	  }

	  /**
	   * funkcija pomjera tijelo zmije**/
	  public void pomjeriTijeloZmije(int[][] tabla, int len, int swapA, int swapB) {
		  
		  int index = 2;
		  
		  while(index<=len) {
			  
			  outerloop:
				  
				  for(int red=0;red<tabla.length;red++) {

					  for(int kolona=0;kolona<tabla[0].length;kolona++) {

						  if(tabla[red][kolona] == index) {

							  tabla[swapA][swapB] = index;

							  swapA = red;

							  swapB = kolona;

							  tabla[swapA][swapB] = 0;

							  break outerloop;

						  }

					  }

				  }

			  index += 1;

		  }

		  if(PROMIJENJEN != REZULTAT) {
			  
			  tabla[swapA][swapB] = len+1;

			  PROMIJENJEN += 1;

		  }

	  }

	  /**
	   * duzina zmije **/
	  public int duzinaZmije(int[][] tabla) {

		  int max = 1;
		  
		  for(int red=0;red<tabla.length;red++) {
			  
			  for(int kolona=0;kolona<tabla[0].length;kolona++) {
				  
				  if(tabla[red][kolona] > max) max = tabla[red][kolona];
				  
			  }
			  
		  }
		  
		  return max;

	  }

	/**
	 * funkcija kojom zmiju pomjeramo prema gore 
	 * ukoliko naiðemo na hranu mijenjamo polja i povecavamo zmiju 
	 * takoðer ukoliko doðe do preklapanja zmije sa samom sobom tada je kraj igre **/
	  public void potezGore(int[][] tabla, int swapA, int swapB) {

		  try {

			  if(tabla[swapA-1][swapB]==-1) {

				  REZULTAT += 1;
				  
				  System.out.println("Rezultat: " + REZULTAT);
				  
				  postaviHranu(tabla);
			  }
			  if(tabla[swapA-1][swapB]>1) gameOver();
			  
			  else tabla[swapA-1][swapB] = tabla[swapA][swapB];
			  
		  } catch(ArrayIndexOutOfBoundsException exception) {
			  
			  if(tabla[swapA+tabla.length-1][swapB]>1) gameOver();
			  
			  else tabla[swapA+tabla.length-1][swapB] = tabla[swapA][swapB];
			  
		  }
		  
	  }

	  /**
		 * funkcija kojom zmiju pomjeramo prema dole
		 * ukoliko naiðemo na hranu mijenjamo polja i povecavamo zmiju 
		 * takoðer ukoliko doðe do preklapanja zmije sa samom sobom tada je kraj igre  **/
	  public void potezDole(int[][] tabla, int swapA, int swapB) {

		  try {
			  
			  if(tabla[swapA+1][swapB]==-1) {
				  
				  REZULTAT += 1;
				  
				  System.out.println("Rezultat: " + REZULTAT);
				  
				  postaviHranu(tabla);
				  
			  }
			  
			  if(tabla[swapA+1][swapB]>1) gameOver();
			  
			  else tabla[swapA+1][swapB] = tabla[swapA][swapB];
			  
		  } catch(ArrayIndexOutOfBoundsException exception) {
			  
			  if(tabla[tabla.length-swapA-1][swapB]>1) gameOver();
			  
			  else tabla[tabla.length-swapA-1][swapB] = tabla[swapA][swapB];
			  
		  }
		  
	  }

	  /**
		 * funkcija kojom zmiju pomjeramo prema lijevo
		 * ukoliko naiðemo na hranu mijenjamo polja i povecavamo zmiju 
		 * takoðer ukoliko doðe do preklapanja zmije sa samom sobom tada je kraj igre  **/
	  public void potezLijevo(int[][] tabla,  int swapA, int swapB) {
		  
		  try {
			  
			  if(tabla[swapA][swapB-1]==-1) {
				  
				  REZULTAT += 1;
				  
				  System.out.println("Rezultat: " + REZULTAT);
				  
				  postaviHranu(tabla);
				  
			  }
			  
			  if(tabla[swapA][swapB-1]>1) gameOver();
			  
			  else tabla[swapA][swapB-1] = tabla[swapA][swapB];
			  
		  } catch(ArrayIndexOutOfBoundsException exception) {
			  
			  if(tabla[swapA][swapB+tabla[0].length-1]>1) gameOver();
			  
			  else tabla[swapA][swapB+tabla[0].length-1] = tabla[swapA][swapB];
			  
		  }

	  }

	  /**
	   * @param swapA , @param swapB
		 * funkcija kojom zmiju pomjeramo prema desno
		 * ukoliko naiðemo na hranu mijenjamo polja i povecavamo zmiju 
		 * takoðer ukoliko doðe do preklapanja zmije sa samom sobom tada je kraj igre  **/
	  public void potezDesno(int[][] tabla, int swapA, int swapB) {
		  
		  try {
			  
			  if(tabla[swapA][swapB+1]==-1) {
				  
				  REZULTAT += 1;
				  
				  System.out.println("Rezultat: " + REZULTAT);
				  
				  postaviHranu(tabla);
				  
			  }
			  
			  if(tabla[swapA][swapB+1] > 1) gameOver();
			  
			  else tabla[swapA][swapB+1] = tabla[swapA][swapB];
			  
		  } catch(ArrayIndexOutOfBoundsException exception) {
			  
			  if(tabla[swapA][tabla[0].length-swapB-1] > 1) gameOver();
			  
			  else tabla[swapA][tabla[0].length-swapB-1] = tabla[swapA][swapB];
			  
		  }

	  }

	  /**GameOver funkcija 
	   * ispisujemo rezultat i napustamo program**/
	  public void gameOver() {
		  
		  System.out.println("Game over. Rezultat: " + REZULTAT);
		 		  
		   System.exit(0);
		  
	  }
}
