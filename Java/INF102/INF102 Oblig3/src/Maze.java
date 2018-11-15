import java.io.FileReader;
import java.util.Scanner;
import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;

public class Maze {
	public static void connect(Tile tile, Graph Graph){
		if(tile.getValue() == 1){
			tile.setVisited(true);
			if((tile.getNorth()!= null)&&(tile.getNorth().getValue() == 1) && (tile.getNorth().getVisited() == false)){
				Graph.addEdge(tile.getGraphVertex(),tile.getNorth().getGraphVertex());
				connect(tile.getNorth(),Graph);
			}
			if((tile.getWest()!= null)&&(tile.getWest().getValue() == 1) && (tile.getWest().getVisited() == false)){
				Graph.addEdge(tile.getGraphVertex(),tile.getWest().getGraphVertex());
				connect(tile.getWest(),Graph);
			}
			if((tile.getSouth()!= null)&&(tile.getSouth().getValue() == 1) && (tile.getSouth().getVisited() == false)){
				Graph.addEdge(tile.getGraphVertex(),tile.getSouth().getGraphVertex());
				connect(tile.getSouth(),Graph);
			}
			if((tile.getEast()!= null)&&(tile.getEast().getValue() == 1) && (tile.getEast().getVisited() == false)){
				Graph.addEdge(tile.getGraphVertex(),tile.getEast().getGraphVertex());
				connect(tile.getEast(),Graph);
			}
		}
	}
	public static void solve(Tile[][] tile, Graph g){
		int s = tile[0][1].getGraphVertex();
		int d = tile[19][28].getGraphVertex();
		int teller = 0;
		
		BreadthFirstPaths bfs = new BreadthFirstPaths(g,s);
		
		for (int v = tile[9][0].getGraphVertex(); v < tile[11][29].getGraphVertex(); v++) {
			teller = -2;
			if (bfs.hasPathTo(v)) {
				BreadthFirstPaths bfs2 = new BreadthFirstPaths(g,v);
				for(int x : bfs2.pathTo(d))
					teller++;
				
				for (int x : bfs.pathTo(v)) {
					teller++;
				}
				//Fikk ikke helt til å finne midten av pathen for så å dele denne opp så gjorde det litt tungvint her.
				if(teller == 48){
					System.out.printf("%d to %d (%d) = ", s, v, bfs.distTo(v));
					for(int x : bfs.pathTo(v)){
	                    if (x == s) System.out.print(x);
	                    else        System.out.print("-" + x);
	                 
					}
					System.out.println();
					System.out.printf("%d to %d (%d) = ", d,v,bfs2.distTo(d));
					for (int x : bfs2.pathTo(d)) {
	                    if (x == v) System.out.print(x);
	                    else        System.out.print("-" + x);
	                   
					}
					   System.out.println();
					   System.out.println("total skritteller: " + teller);
					   System.out.println();
				}
			}
		}
		
	}

	public static void main(String[] args){
		int[][] maze = new int[20][30];
		Tile[][] tile = new Tile[20][30];
		Graph g = new Graph(600);
		int l = 0;
		int teller = 0;
		try{
			Scanner in = new Scanner(new FileReader("C:/Users/Ole/Desktop/simple_maze_20x30.txt"));
			String str = in.nextLine();
			for(int i = 0; i < 20; i++){	
				for(int j = 0; j < 30;j++){
					if(l == 30){
						str = in.nextLine();
						l = 0;
					}
					maze[i][j] = Integer.parseInt(str.substring(l, l+1));					
					l++;
				}
			}
			in.close();
		}catch(Exception e){System.out.println(e);}
		
		for(int x = 0; x < 20; x++){
			for(int y = 0; y < 30;y++){
				tile[x][y] = new Tile(maze[x][y]);
				tile[x][y].setGraphVertex(teller);
				if(x >0)
					tile[x][y].connectNorth(tile[x-1][y]);
				if(y > 0)
					tile[x][y].connectWest(tile[x][y-1]);

				teller++;
			}
		}	
		for(int x = 0; x < 20; x++){
			for(int y = 0; y < 30;y++){
				connect(tile[x][y],g);
			}
		}
		//Fikk et problem hvor tile 62 og 63 ikke ville lage en edge mellom hverandre så måtte tvinge programmet å gjøre dette for å fikse problemet.
		g.addEdge(tile[2][2].getGraphVertex(),tile[2][2].getEast().getGraphVertex());
		
		solve(tile,g);
	
	}
}