import java.io.*;
import java.net.*;


public class Server{


	public static void main(String args[]) throws Exception{
		ServerSocket server;
		Socket connection;
		String clientSentence;
		
		server = new ServerSocket(8888);
		
		while(true){
			connection = server.accept();
			System.out.println("Connect from: " + connection.getInetAddress().getHostName());


			BufferedReader inFromClient =
					new BufferedReader(new
							InputStreamReader(connection.getInputStream()));
			DataOutputStream outToClient =
					new DataOutputStream(connection.getOutputStream());

			clientSentence = inFromClient.readLine();
			String newSentence = clientSentence + "55";
			outToClient.writeBytes(newSentence);
				
			server.close();
			
		}
		
	}
}
