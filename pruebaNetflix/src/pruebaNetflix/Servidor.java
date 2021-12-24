package pruebaNetflix;

import javax.media.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) {
		try(ServerSocket servidor=new ServerSocket(8000)){
			while(true) {
				try(Socket s=servidor.accept();
						DataInputStream data=new DataInputStream(s.getInputStream());
						BufferedReader leer=new BufferedReader(new InputStreamReader(s.getInputStream()));
						//BufferedReader in=new BufferedReader(new InputStreamReader(new FileInputStream("videos")));
						PrintStream escribir=new PrintStream(s.getOutputStream())){
					
					String primeraL=data.readLine();
					System.out.println(primeraL);
					switch(primeraL) {
					case "mostrar":
						File carpeta=new File("videos");
						for(File f: carpeta.listFiles()) {
							escribir.println(f.getName());
						}
						
					}
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
