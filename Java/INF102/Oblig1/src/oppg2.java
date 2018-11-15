import edu.princeton.cs.algs4.Quick3string;

public class oppg2 {
	static String[][] liste = {
			{"Eirik","Mikal","Anders","Kent"},
			{"Kent","Eirik","Jens","Gunnar"},
			{"Gunnar","Johannes","Eirik","Lise"},
			{"Kim","Andreas","Kent","Maren"}
	};
	static String[] nyListe = new String[liste[0].length*4];
	
	public static void fyllListe(){
		System.out.print("Usortert liste: ");
		for(int i = 0; i < liste.length;i++){
			for(int j = 0; j < liste.length;j++){
				nyListe[4*(i)+(j)] = liste[i][j];
				System.out.print(liste[i][j]+" ");
			}
		}
	}
	public static void main(String[] args){
		fyllListe();
		System.out.println("");
		Quick3string.sort(nyListe);
		System.out.print("Sortert: ");
		for(int i = 0; i < nyListe.length;i++){
			System.out.print(nyListe[i]+" ");
		}
		System.out.println("");
		int teller = 1;
		for(int i = 1; i < nyListe.length;i++){
			if(nyListe[i] == nyListe[i-1]){
				teller = teller + 1;
			}
			else{
				teller = 1;
			}
			if (teller == 3){
				System.out.println("Triplikat: " + nyListe[i]);
			}
		}
	}
}
