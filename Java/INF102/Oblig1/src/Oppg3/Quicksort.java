package Oppg3;
import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.Stopwatch;

public class Quicksort {
	public static void main(String[] args){
		double temp = 0;
		int antall = 10000;
		int N = 5000;
		Comparable a[] = new Comparable[N];
		for(int i = 0; i < N; i++){
			a[i] = N-i;
		}

		for(int i = 0; i < antall; i++){
			Stopwatch timer = new Stopwatch();
			Quick.sort(a);
			double tid = timer.elapsedTime();
			temp = temp + tid;

		}
		double average = temp/antall;
		System.out.println("N = "+N + " Gjennomsnitt = " + average +" Tid = " +temp);

	}
}
