import java.util.Random;

import edu.princeton.cs.algs4.Stopwatch;

public class oppg4 {

	static int maxInt(int[] arr){
		int max = arr[0];
		for(int i = 0; i < arr.length; i++){
			if(arr[i] > max){
				max = arr[i];
			}
		}
		return max;
	}
	static int[] Countsort(int[] tab){
		int max = maxInt(tab);
		int[] sorted = new int[tab.length+1];
		int[] count = new int[max+1];
		for(int i = 0; i < tab.length;i++){
			count[tab[i]] = count[tab[i]]+1;
		}
		count[0]--;
		for(int j = 1; j < max + 1; j++){
			count[j] = count[j] + count[j-1];
		}
		for(int k = 0; k < tab.length;k++){
			sorted[count[tab[k]]] = tab[k];
			count[tab[k]] = count[tab[k]]-1;

		}
		return sorted;
	}
	public static void main(String[] args){
		int N = 1000000 ;
		int antall = 1000;
		double temp = 0;
		int[] tab = new int [N];
		for(int i = 0; i < tab.length;i++){
			tab[i] = (int)Math.floor(Math.random()* 100);
		}
		for(int i = 0; i < antall; i++){
			Stopwatch timer = new Stopwatch();
			Countsort(tab);
			double tid  = timer.elapsedTime();
			temp = temp + tid;
		}
		double average = temp/antall;
		System.out.println("N = "+N + " = " + average);
	}
}
