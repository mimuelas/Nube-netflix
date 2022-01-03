package pruebaNetflix_x32;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor {
	public static void main(String[] args) {
		ExecutorService pool=Executors.newCachedThreadPool();
		try(ServerSocket servidor=new ServerSocket(5300)){
			while(true) {
				Socket conexion=servidor.accept();
				//se atenderán peticiones del cliente
				Peticion peticion=new Peticion(conexion);
				pool.execute(peticion);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pool.shutdown();
	}
}
