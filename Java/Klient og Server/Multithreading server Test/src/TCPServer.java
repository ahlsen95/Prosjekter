/*
 * @author Eirik Ahlsen
 */
import java.net.*; 

public class TCPServer extends Thread { 
	public static void main(String args[]) throws Exception { 
		int id = 0; //Lager en id for en klient.
		ServerSocket welcomeSocket = new ServerSocket(8888);
		try{
			while(true) { 
				Socket connectionSocket = welcomeSocket.accept(); 
				id++;
				System.out.println( "Connection received from: " +
						connectionSocket.getInetAddress().getHostName() + " ID:" + id + "\nUse the words 'FULL','TIME' or 'DATE' to get different formats of todays date."
						+ "\nUse 'CLOSE' to end the connection.");
				Thread thread = new Thread(new Multithread(connectionSocket, id));//Lager en ny thread.
				thread.start(); //Starter runnable.
			}
		}finally{
			welcomeSocket.close();
		}
	} 
} 

