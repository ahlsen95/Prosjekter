import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.DepthFirstSearch;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;

public class dfs {
	
	public static void main(String[] args){
		
		Digraph g = new Digraph(new In("C:/Users/Ole/Desktop/tinyDAG.txt"));
		DepthFirstOrder p = new DepthFirstOrder(g);
		System.out.println("Pre: " + p.pre());	
		System.out.println("Post: " +p.post());
		System.out.println("ReversrePost: " + p.reversePost());
	}
}
