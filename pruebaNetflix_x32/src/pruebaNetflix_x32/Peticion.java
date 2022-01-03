package pruebaNetflix_x32;

import java.net.Socket;

public class Peticion implements Runnable{

	private Socket cliente;
	public Peticion(Socket cliente) {
		this.cliente=cliente;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
