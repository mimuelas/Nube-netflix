package InterfazGrafica;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {
	
	private String host;
	private int puerto;
	private PrintStream salida;
	private DataInputStream leer;
	
	
	public Cliente(String host,int puerto) {
		this.host=host;
		this.puerto=puerto;
	}
	
//	public void jugar() {
//		if(this.establecerConexion()) {
//			
//			boolean bucle=true;
//			while(bucle) {
//				System.out.println("¿Qué quieres hacer?");
//				System.out.println("1. Listar los vídeos disponibles");
//				System.out.println("2. Reproducir un vídeo");
//				System.out.println("3. Cargar un video");
//				System.out.println("4. Salir");
//				
//				Scanner teclado=new Scanner(System.in);
//				int opcion=teclado.nextInt();
//				switch(opcion) {
//				case 1:
//					this.listarVideos();
//					break;
//				case 2:
//					this.reproducirVideo();
//					break;
//				case 3:
//					this.cargarVideo();
//					break;
//				case 4:
//					bucle=false;
//				}
//			}
//			
//		}
//		
//	}
	
	public ArrayList<String> listarVideos(){
		ArrayList<String> lista = new ArrayList<String>();
		this.salida.println(0);
		try {
			String linea=this.leer.readLine();
			int size=Integer.parseInt(linea);
			for(int i=0;i<size;i++) {				
				linea=this.leer.readLine();
				lista.add(linea);
//				System.out.println(linea);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public void reproducirVideo(int Nvideo) {
		this.salida.println(1);
		
//			String lectura = this.leer.readLine();
//			System.out.println(lectura);
//			Scanner teclado=new Scanner(System.in);
//			int numero=teclado.nextInt();
			this.salida.println(Nvideo);
		
	}
	public void cargarVideo(String rutaVideo,String nombre) {
		
			this.salida.println(2);

//			String lectura = this.leer.readLine();
//			System.out.println(lectura);
//			Scanner teclado=new Scanner(System.in);
//			String rutaVideo=teclado.nextLine();
			this.salida.println(rutaVideo);
			this.salida.println(nombre);
	}
	public boolean establecerConexion() {
		boolean posible=false;
		try {
			Socket cliente=new Socket(this.host,this.puerto);
			this.leer=new DataInputStream(cliente.getInputStream());
			this.salida=new PrintStream(cliente.getOutputStream());
			posible=true;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return posible;
	}
	//hacer cerrar conexion
	public void cerrarConexion() {
		
		if(this.leer!=null) {
			try {
				this.leer.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
		if(this.salida!=null) {
			try {
				this.salida.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public void cerrarCliente() {
		this.salida.println(3);
		this.salida.flush();
		this.cerrarConexion();
	}
	
}
