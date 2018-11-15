import java.io.*;
import java.net.*;

public class Client {
	public static void main(String args[]) throws Exception{
		String sentence;
		String asd = "asd";
		
		
		
		Socket connection = new Socket("127.0.0.1",8888);
		BufferedReader inFromServer= new BufferedReader(new InputStreamReader(connection.getInputStream()));
		DataOutputStream outToServer = new DataOutputStream(connection.getOutputStream());
		
		outToServer.writeBytes(asd);
		sentence = inFromServer.readLine();
		System.out.println(sentence);
		connection.close();
		
	}
}
