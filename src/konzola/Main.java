package konzola;

import logika.Snake;


/**
 * {@code Main} class je odgovorna za pokretanje ploèe za igru 
 * odnosno igre u konzoli
 */

public class Main {
	/**
	 * {@code main} metoda je odgovorna za pokretanje igre u konzoli
	 */
	public static void main(String args[]) {
		    
			Snake s = new Snake();
			
			int[][] tabla = s.setTable(12);
	
		    s.postaviZmiju(tabla); 
	
		    s.ispisiPravila();
		   
		    s.ispisiTablu(tabla); 
			 
		    /**unos poteza za kretanje zmije sve dok se ne unese q za kraj **/
		    do {
		    	
		    	s.unosPoteza();
			    
		    	/**sve dok unesene komande nisu ok , trazi od korisnika unos**/
			    
		    	while(s.potez!='w' & s.potez!='a' & s.potez!='s' & s.potez!='d' & s.potez!='q' & s.potez!='\n') {
			    	 
		    		System.out.println("Pogresan unos. Unesite neki od sljedecih znakova \n w = GORE, a = LIJEVO, s = DOLE, d = DESNO, q = KRAJ");
			    	 
		    		System.out.println('\n');
			    	 
		    		s.unosPoteza();
		      
		    	}
	
		    	s.promijeniTablu(tabla, s.prethodni_potez);
		      		      
		    	System.out.println('\n');
		      
		    	for(int i=0;i<tabla[0].length;i++) System.out.print("#  ");
		      
		    	System.out.println('\n');
	
		    } while (s.potez != 'q');
	
		    System.out.println("Kraj igre.");

	  }
}
