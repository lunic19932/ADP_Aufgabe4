package pascalscheDreieck;

public class PascalscheDreieck {

	private  int zeile;
	private int arrayGroesse;
	
	
	public PascalscheDreieck(int zeile){
		this.zeile=zeile;
		this.arrayGroesse=zeile+1;
		
	}
	/**
	 * Rekursive Pascalsche-Dreiecks-Zeilen-Berechnung für n-Zeile=0(Initialisierung)
	 * @return
	 */
	public long[] rekursiv(){
		long[] zeileRekursiv=new long[1];
		zeileRekursiv[0]=1;
		if(zeile<=0){
			return zeileRekursiv;
		}
		
		return rekursiv(zeileRekursiv);
	}
	/**
	 * Rekursive Pascalsche-Dreiecks-Zeilen-Berechnung für n-Zeile>0
	 * @param vorherigeZeile
	 * @return
	 */
	public long[] rekursiv(long[] vorherigeZeile){
		long[] temp=new long[vorherigeZeile.length+1];
		temp[0]=1;
		temp[temp.length-1]=1;
		for(int i=1;i<temp.length-1;i++){
			temp[i]=vorherigeZeile[i-1]+vorherigeZeile[i];
		}
		if(temp.length!=zeile+1){
		return rekursiv(temp);
		}
		return temp;
	}
	/**
	 * Iterative Pascalsche-Dreiecks-Zeilen-Berechnung
	 * @return n-Zeile des Pascalschen Dreiecks
	 */
	public long[] iterativ(){
		long[]zeileIterativ=new long[arrayGroesse];
		zeileIterativ[0]=1;
		long[] temp=new long[arrayGroesse];
		for(int i=0;i<arrayGroesse;i++){
			for(int k=1;k<i;k++){
				zeileIterativ[k]=temp[k-1]+temp[k];
			}
			zeileIterativ[i]=1;
			System.arraycopy(zeileIterativ, 0, temp, 0, zeile);
		}	
		return zeileIterativ;
	}
	/**
	 * Pascalsche-Dreiecks-Zeilen-Berechnung mit Binomialkoeffizient
	 * @return n-Zeile des Pascalschen Dreiecks
	 */
	public long[] binominal(){
		long[]zeileBinominal=new long[zeile+1];
		zeileBinominal[0]=1;
		zeileBinominal[zeile]=1;
		double fakN=fakultaet(zeile);
		for(int i=1;i<zeile;i++){
			zeileBinominal[i]=(long)(fakN/(fakultaet(i)*fakultaet(zeile-i)));			
		}
		return zeileBinominal;
	}
	/**
	 * Bestimmt die Fakultaet von n.
	 * @param n 
	 * @return n!
	 */
	public double fakultaet(int n){
		double fak=1;
		while(n>0){
			fak=fak*n;
			n--;
		}
		return fak;
	}
	/**
	 * Konsolenausgabe für Arrays
	 * @param array
	 */
	public void printArray(long[] array){
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		PascalscheDreieck pd=new PascalscheDreieck(49);
		System.out.print("Rekrusiv	: ");
		pd.printArray(pd.rekursiv());
		System.out.print("Iterativ	: ");
		pd.printArray(pd.iterativ());
		System.out.print("Binominal	: ");
		pd.printArray(pd.binominal());
	}
}
