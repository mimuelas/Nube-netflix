package InterfazGrafica;

import InterfazGrafica.ECloud;

public class Principal {
	public static void main(String[] args) {

		//Ventana v=new Ventana("C:\\Users\\Juan\\eclipse-workspace\\pruebaNetflix_x32\\videos\\2.MPG");
		Cliente c=new Cliente("localhost",5300);
		ECloud frame = new ECloud(c);
		
	}
}
