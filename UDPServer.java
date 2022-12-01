import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPServer{
public static void main(String args[]){
DatagramSocket aSocket = null;
	try{
		
		//Create Sockets
		aSocket = new DatagramSocket(9900);
		
		//Key word to end the program
		String fin= "end";
		
		while(true){
			
			//Receive info
				//Create a buffer to save the string from the console
			byte[] buffer = new byte[1000];
			DatagramPacket request = new DatagramPacket(buffer, buffer.length);					//DatagramPacket to received the info from the client
			aSocket.receive(request);															//Received the info into the program
			
			String info = new String (request.getData(),0,request.getLength());					//Convert the buffer to string
			System.out.println("Client: "+ info);												//Output the message from the client
			buffer = info.toUpperCase().getBytes();												//Convert the string back to buffer and make it uppercase
				
			
			DatagramPacket rBack = new DatagramPacket(buffer, buffer.length, request.getAddress(), request.getPort());	//Prepare the packet to reply back
			aSocket.send(rBack);																						//Send the message back to client
			
			
			if("end".equals(new String (request.getData(),0,request.getLength())))					//If to end the program if client or user hits end
				break;
			
		}
	}
	catch (SocketException e){
		System.out.println("Socket: " + e.getMessage());
	}
	catch (IOException e) {
		System.out.println("IO: " + e.getMessage());
	}
	finally {
		//Close Socket
		if(aSocket != null) aSocket.close();
		}
	}
	
	
}