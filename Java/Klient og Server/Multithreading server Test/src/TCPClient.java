/*
 * @author Eirik Ahlsen
 */
import java.io.*; 
import java.net.*; 
class TCPClient { 

	public static void main(String args[]) throws Exception { 
		String word;
		String wordFromServer;
		Socket clientSocket = new Socket("localhost", 8888);

		while(true){
			try{
				//Setter opp stream reader.
				BufferedReader inFromUser = 
						new BufferedReader(new InputStreamReader(System.in)); 
				DataOutputStream outToServer = 
						new DataOutputStream(clientSocket.getOutputStream()); 
				BufferedReader inFromServer = 
						new BufferedReader(new
								InputStreamReader(clientSocket.getInputStream())); 
			
				//Tar inn ord fra bruker, sender til server, får tilbake fra server og skriver ut hva bruker og server har sendt.
				word = inFromUser.readLine().toUpperCase(); 
				outToServer.writeBytes(word + '\n');
				outToServer.flush();
				System.out.println("TO SERVER: " + word);
				wordFromServer = inFromServer.readLine(); 
				System.out.println("FROM SERVER: " + wordFromServer);

				if(word.equals("CLOSE")){
					clientSocket.close();
					System.exit(1); /*Var usikker på om hele programmet skulle lukkes eller om bare forbinnelsen
					med serveren skulle avsluttes.*/
				}
			}catch(IOException e){
				System.out.println(e);	
			}
		}
	}
} 


