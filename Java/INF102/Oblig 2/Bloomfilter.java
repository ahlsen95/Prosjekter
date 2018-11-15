/*
 * @author Eirik Ahlsen
 */

import java.util.BitSet;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;


public class Bloomfilter {
	static final int size = 10000019;
	static final BitSet bits = new BitSet(size);
	static final BitSet bits2 = new BitSet(size);

	public static void lesFil(){
		String s ="";
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream("Skriv inn plassering til Ecoli.1M.36mer.txt"));    
			BufferedReader br = new BufferedReader(new InputStreamReader(dis)); 
			for(int i = 0; i < 1000000;i++){
				s = s + br.readLine();
				Bloomfilter.add(s);
				s = "";
			}
			br.close();
		}catch(Exception e){System.out.println(e);}

		try{
			DataOutputStream os = new DataOutputStream(new FileOutputStream("Skriv inn plassering til dokumentet programmet skal skrive til."));
			for (int i=0; i<bits.size(); i++) {
				os.writeBoolean(bits.get(i));
			}
			os.close();
		} catch (Exception e) {System.out.println(e);}

	}

	public static void FinnPos(){
		String s = "";
		int positiv = 0;
		try {
			FileReader k = new FileReader("Skriv inn plasseringen til tekstdokumentet i andre try-kolonne i lesFil()");
			DataInputStream dis = new DataInputStream(new FileInputStream("Skriv inn plassering til Ecoli.2M.36mer.txt"));    
			BufferedReader br = new BufferedReader(new InputStreamReader(dis)); 
			for(int i = 0; i < bits.size();i++){
				s = s + k.read();
				if(s.equals("1"))
					bits2.set(i, true);
				else
					bits2.set(i, false);
				//System.out.println(Bloomfilter.contains(s));
				s = "";
			}
			for(int i = 0; i < 2000000; i++){
				s = s + br.readLine();
				if(Bloomfilter.contains(s))
					positiv = positiv + 1;
				s = "";
			}
			System.out.println("Antall positive = "+ positiv);
			br.close();
			k.close();
		}catch(Exception e){System.out.println(e);}

	}

	public static void main(String[] args){

		lesFil();
		FinnPos();
	}

	public static void add(String word){

		bits.set(hash(word), true);
		bits.set(hash2(word), true);
		bits.set(hash3(word), true);

	}

	public static boolean contains(String word){

		if(bits2.get(hash(word)) == false) return false;
		if(bits2.get(hash2(word)) == false) return false;
		if(bits2.get(hash3(word)) == false) return false;
		return true;
	}

	public static int hash(String word){

		int resultat = 0;
		int l = word.length();
		for (int i = 0; i < l; i++){
			resultat = (5 * resultat + word.charAt(i)) % 10000019;
		}
		return resultat;
	}
	public static int hash2(String word){

		int resultat = 0;
		int l = word.length();
		for (int i = 0; i < l; i++){
			resultat = (7*resultat + word.charAt(i)) % 10000019;
		}
		return resultat;
	}
	public static int hash3(String word){

		int resultat = 0;
		int l = word.length();
		for (int i = 0; i < l; i++){
			resultat = (11*resultat + word.charAt(i)) % 10000019;
		}
		return resultat;
	}
}
