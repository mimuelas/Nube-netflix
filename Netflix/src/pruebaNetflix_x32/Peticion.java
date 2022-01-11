package pruebaNetflix_x32;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Peticion implements Runnable{

	private Socket cliente;
	private List<File> listaVideos;
	private DataInputStream leer;
	private PrintStream salida;
	private JFrame ventanaPrincipal;
	
	//constructor en el que inicalizamos la lista donde se encuentran los vídeos
	public Peticion(Socket cliente) {
		this.cliente=cliente;
		this.listaVideos=new ArrayList<File>();
		File directorio=new File("videos");
		File[] videosDirectorio=directorio.listFiles();
		for(File f:videosDirectorio) {
			this.listaVideos.add(f);
		}
		this.ventanaPrincipal=new JFrame();	
		}
	@Override
	public void run() {
		try{
				this.leer=new DataInputStream(this.cliente.getInputStream());
				this.salida=new PrintStream(this.cliente.getOutputStream());
			
			
			
			boolean bucle=true;
			while(bucle) {
				String primeraLinea=leer.readLine();
				int opcion=Integer.parseInt(primeraLinea);
				switch(opcion) {
				case 0:
					//listar los vídeos disponibles
					this.listarVideos();
					break;
				case 1:
					//reproducir vídeo
					//this.salida.println("¿Qué vídeo quieres que se reproduzca?");
					//this.salida.flush();
					String numeroS=this.leer.readLine();
					int numeroI=Integer.parseInt(numeroS);
					
					if(numeroI>=0 && numeroI<this.listaVideos.size()) {
						File video=this.listaVideos.get(numeroI);
						String ruta=video.getAbsolutePath();
						this.reproducirVideo(ruta);
					}
					else {
						this.salida.println("Número fuera de rango");
					}
					break;
				case 2:
					//this.salida.println("¿Cual es la ruta del video que quieres cargar?");
					String rutaVideo=this.leer.readLine();
					String nombreVideo=this.leer.readLine();
					File video = new File(rutaVideo);
					if(video.isFile()) {
						this.cargarVideo(video,nombreVideo);
					}
					break;
				case 3:
					bucle = false;
					break;
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(this.leer!=null)
					this.leer.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
			try {
				if(this.salida!=null)
					this.salida.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void listarVideos() {
		this.salida.println(this.listaVideos.size());
		for(int i=0;i<this.listaVideos.size();i++) {
			this.salida.println(this.listaVideos.get(i).getName());
		}
		
	}
	
	public void reproducirVideo(String rutaVideo) {
		Ventana v=new Ventana(this.ventanaPrincipal, rutaVideo);
	}
	
	public String cargarVideo(File origen,String nombre) {
		
        File destino = new File("Videos\\" + nombre + ".mpg");
        String dev = nombre + ".mpg";
        try {
                InputStream in = new FileInputStream(origen);
                OutputStream out = new FileOutputStream(destino);

                byte[] buf = new byte[1024];
                int len;

                while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                }
                this.listaVideos.add(destino);
                in.close();
                out.close();
        } catch (IOException ioe){
                ioe.printStackTrace();
        }
        return dev;
	}
	
}
