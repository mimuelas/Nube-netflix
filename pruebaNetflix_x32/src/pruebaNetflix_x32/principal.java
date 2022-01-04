package pruebaNetflix_x32;

public class principal {
	public static void main(String[] args) {
		//Ventana v=new Ventana("C:\\Users\\Juan\\eclipse-workspace\\pruebaNetflix_x32\\videos\\2.MPG");
		Cliente c=new Cliente("localhost",5300);
		c.jugar();
		
	}
}
