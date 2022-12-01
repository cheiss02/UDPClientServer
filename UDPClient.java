import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPClient{
public static void main(String args[]){
	//Create the socket
	DatagramSocket aSocket = null;
	//Create a scanner to save the input from the console
	Scanner sc = new Scanner(System.in);
	
	//Ask the client for the server IP address
	System.out.println("Please add the Ip address of the server");
	String ipAddress = sc.nextLine();
	//ASk the client for the server port number
	System.out.println("Please add the Port Number of the server");
	int portNumber = sc.nextInt();
	
	try {
		
	//Create sockets
	
	
	aSocket = new DatagramSocket(8800);

	//Infinite loop to be able to send multiple messages
	while(true){
		
		//send info
		
		
		
		
		InetAddress aHost = InetAddress.getByName(ipAddress);		//Get the ip address 
		int serverPort = 9900;
		String info = sc.nextLine();		//ASk for a message
		byte [] m = info.getBytes();		//Save the info from the console in a buffer
		if(serverPort != portNumber){
			//Port incorrect
			break;
		}
		
		//Create a datagram packet to send the information to the client
		DatagramPacket request = new DatagramPacket(m, m.length, aHost, portNumber);
		aSocket.send(request);		//Send the message to the client

		//End if the client hits the key work "end"
		if("end".equals(info))
			break;
		
		//Received the info back from the server in upper case
		byte[] buffer = new byte[1000];			//Save the information in a buffer to display it as a string
		DatagramPacket reply = new DatagramPacket(buffer, buffer.length);		//Create a datagram to receive the reply
		aSocket.receive(reply);													//Get the reply
		System.out.println("Reply: " + new String(reply.getData()));			//Display the reply in a console
		
		
	
	}
	
	}
	catch (SocketException e){
		System.out.println("Socket: " + e.getMessage());
		}
	catch (IOException e){
		System.out.println("IO: " + e.getMessage());
		}
	
	finally {
	//close sockets
	if(aSocket != null) aSocket.close();
	}
	}

}