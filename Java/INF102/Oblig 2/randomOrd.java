/*
 * @author Eirik Ahlsen
 */
import java.util.Random;

public class randomOrd{
	static String[] tilfeldig(int n, int stringLength){
		String[] temp=new String[n];
		Random random = new Random();
		//Fyller tabellen med tilfeldige bokstaver.
		for(int j=0;j<n;j++){
			char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < stringLength; i++) {
				char c = chars[random.nextInt(chars.length)];
				sb.append(c);
			}
			temp[j]=sb.toString();
		}
		return temp;
	}
}