package pruebaNetflix;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
	public static void main(String[] args) {
		try(Socket cliente=new Socket("localhost",8000);
				DataInputStream leerTexto=new DataInputStream(cliente.getInputStream());
				BufferedReader teclado=new BufferedReader(new InputStreamReader(System.in));
				PrintStream mandarOpciones=new PrintStream(cliente.getOutputStream())){
			
			System.out.println("Elige una opcion");
			System.out.println("1. Mostrar videos disponibles");
			int opcion=Integer.parseInt(teclado.readLine());
			System.out.println(opcion);
			switch(opcion) 
			{
			case 1:
				//le mandamos al servidor que queremos que nos muestre
				mandarOpciones.println("mostrar");
				List<String> listaVideos=new ArrayList<String>();
				String linea=leerTexto.readLine();
				while(linea!=null) {
					listaVideos.add(linea);
;					System.out.println(linea);
					linea=leerTexto.readLine();
				}
				
				
				
			}
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
