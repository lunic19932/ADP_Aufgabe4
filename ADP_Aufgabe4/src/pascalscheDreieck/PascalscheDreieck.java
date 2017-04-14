package pascalscheDreieck;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
public class PascalscheDreieck {

	private  int zeile;
	private int arrayGroesse;
	private int rekursivZaehler;
	private int iterativZaehler;
	private int binominalZaehler;
	
	
	public PascalscheDreieck(int zeile){
		this.zeile=zeile;
		this.arrayGroesse=zeile+1;
		
	}
	/**
	 * Rekursive Pascalsche-Dreiecks-Zeilen-Berechnung für n-Zeile=0(Initialisierung)
	 * @return
	 */
	public long[] rekursiv(){
		rekursivZaehler=0;
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
			rekursivZaehler++;
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
		iterativZaehler=0;
		long[]zeileIterativ=new long[arrayGroesse];
		zeileIterativ[0]=1;
		long[] temp=new long[arrayGroesse];
		for(int i=0;i<arrayGroesse;i++){
			for(int k=1;k<i;k++){
				iterativZaehler++;
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
		binominalZaehler=0;
		long[]zeileBinominal=new long[zeile+1];
		zeileBinominal[0]=1;
		zeileBinominal[zeile]=1;
		double fakN=fakultaet(zeile);
		for(int i=1;i<zeile;i++){
			zeileBinominal[i]=(long)(fakN/(fakultaet(i)*fakultaet(zeile-i)));	
			binominalZaehler++;

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
	
	public int getAufwandRekursiv(){
		rekursiv();
		return rekursivZaehler;
	}
	public int getAufwandIterativ(){
		iterativ();
		return iterativZaehler;
	}
	public int getAufwandBinominal(){
		binominal();
		return binominalZaehler;
	}
	public static void main(String[] args) throws IOException {
		//Erstellen Exceldatei Dateiblatt + Ueberschriften
		HSSFWorkbook datei=new HSSFWorkbook();
		Sheet blatt=datei.createSheet("Aufwand");
		Row titelReihe =blatt.createRow(0);
		Cell zeilenTitel=titelReihe.createCell(0);
		zeilenTitel.setCellValue("Zeile");
		Cell aufwandRekursivTitel=titelReihe.createCell(1);
		aufwandRekursivTitel.setCellValue("Aufwand Rekursiv");
		Cell aufwandIterativTitel=titelReihe.createCell(2);
		aufwandIterativTitel.setCellValue("Aufwand Iterativ");
		Cell aufwandBinominalTitel=titelReihe.createCell(3);
		aufwandBinominalTitel.setCellValue("Aufwand Binominal");
		//Aufwandsanalyse in Excel
		int n=49;
		for(int i=1;i<n+1;i++){
			PascalscheDreieck pd=new PascalscheDreieck(i);
			Row reihe =blatt.createRow(i);
			Cell zeile=reihe.createCell(0);
			zeile.setCellValue(i);
			Cell aufwandRekursiv=reihe.createCell(1);
			aufwandRekursiv.setCellValue(pd.getAufwandRekursiv());
			Cell aufwandIterativ=reihe.createCell(2);
			aufwandIterativ.setCellValue(pd.getAufwandIterativ());
			Cell aufwandBinominal=reihe.createCell(3);
			aufwandBinominal.setCellValue(pd.getAufwandBinominal());
		}
		
		
		try {
			FileOutputStream output = new FileOutputStream("ADP4_Aufwand.xls");
			datei.write(output);
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
