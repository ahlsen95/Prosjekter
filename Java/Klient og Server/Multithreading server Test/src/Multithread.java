/*
 * @author Eirik Ahlsen
 */
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Multithread implements Runnable{
	Socket socket;
	int id;
	
	public Multithread(Socket socket,int id){ //konstruktør.
		this.socket = socket;
		this.id = id;
	}
	public void run(){
		String clientWord;
		try 
		{	
			while(true){
				//Setter opp stream reader.
				BufferedReader inFromClient = 
						new BufferedReader(new
								InputStreamReader(socket.getInputStream())); 
				DataOutputStream  outToClient = 
						new DataOutputStream(socket.getOutputStream());
				
				clientWord = inFromClient.readLine(); 
				
				//Responser etter hva klient har sendt inn. For å få med dagens dato og ikke bare måneden kan "d" legges inn før "MMMM" i SimpleDateFormat. 
				//Usikker på om det skulle være med eller ikke.
				if(clientWord.equals("FULL")){
					System.out.println("From Client("+ id + "): " + clientWord);
					String date = new SimpleDateFormat("MMMM yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
					System.out.println("To Client("+ id + "): " + date);
					outToClient.writeBytes(date + '\n');
				}
				else if(clientWord.equals("DATE")){
					System.out.println("From Client("+ id + "): " + clientWord);
					String date = new SimpleDateFormat("MMMM yyyy").format(Calendar.getInstance().getTime());
					System.out.println("To Client("+ id + "): " + date);
					outToClient.writeBytes(date + '\n');
				}
				else if(clientWord.equals("TIME")){
					System.out.println("From Client("+ id + "): " + clientWord);
					String date = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
					System.out.println("To Client("+ id + "): " + date);
					outToClient.writeBytes(date + '\n');
				}
				else if(clientWord.equals("CLOSE")){
					String close = "Shutting down...";
					System.out.println("From Client("+ id + "): " + clientWord);
					System.out.println("To Client("+ id + "): " + close);
					outToClient.writeBytes(close + '\n');
					socket.close();//lukker socket.
					break;//avslutter while.
				}
				else{
					System.out.println("From Client("+ id + "):"+ clientWord);
					System.out.println("To Client(" + id + "): Invalid word. Use 'FULL,'TIME','DATE' or 'CLOSE'.");
					outToClient.writeBytes("Invalid word. Use 'FULL,'TIME','DATE' or 'CLOSE'." + '\n');
				}

			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();

		}
	}
}

