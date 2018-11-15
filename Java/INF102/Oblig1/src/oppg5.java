import edu.princeton.cs.algs4.Stack;

public class oppg5 {
	public static void main(String[] args){
		Stack<Integer> q = new Stack<Integer>();
		int N = 4;
		int sum = 1;
		for(int i = 0; i < N;i++){
			for(int j = 0; j <i;j++){
				sum = sum*2;
			}
			q.push(sum);
			System.out.println(q.pop());
		}
	}
}
