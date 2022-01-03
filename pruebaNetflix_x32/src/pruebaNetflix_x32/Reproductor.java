package pruebaNetflix_x32;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.Time;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Reproductor extends JFrame implements KeyListener{
	//PANEL
	JPanel panel;
	//REPRODUCTOR
	Player mediaPlayer;
	//COMPONENTE DE VIDEO
	Component video;
	//COMPONENTE DE CONTROLES
	Component controles;

	// INICIALIZAMOS VARIABLES NECESARIAS PARA CONSTRUIR EL MENU
	JMenuBar jmb_barra;
	JMenu jm_menu;
	JMenuItem jmi_abrir;
	JMenuItem jmi_salir;

	//INICIALIZAMOS VARIABLES DE FILECHOOSER
	JFileChooser fileChooser = new JFileChooser();
	ArrayList<String> lista = new ArrayList();
	int index = 0;

	//INICIALIAMOS VARIABLES DE CONTROL
	char anterior = 'a';
	char siguiente = 'd';
	char showMenu = 'w';
	
	public Reproductor(){
        initMenu();
        init();
        //ESTABLECEMOS RUTA POR DEFECTO FILECHOOSER
        fileChooser.setCurrentDirectory(new File("file:\\C:\\Users\\Juan\\eclipse-workspace\\pruebaNetflix_x32\\videos\\1.MPG"));
    }
	
	public final void initMenu(){
        // INICIALIZAMOS VARIABLES DE MENU
        jmi_abrir = new JMenuItem("Agregar video");
       // jmi_abrir.setIcon(new ImageIcon(getClass().getResource("/resources/abrir.png")));
        jmi_salir = new JMenuItem("Salir");
        //jmi_salir.setIcon(new ImageIcon(getClass().getResource("/resources/cerrar.png")));
        // INICIALIZAMOS NUESTRO MENU
        jm_menu = new JMenu("Menu");
       // jm_menu.setIcon(new ImageIcon(getClass().getResource("/resources/menu.png")));
        // INICIALIZAMOS NUESTRA BARRA DE MENU
        jmb_barra = new JMenuBar();
        // AGREGAMOS NUESTROS ITEMS DE MENU A NUESTRO MENU
        jm_menu.add(jmi_abrir);
        jm_menu.addSeparator();
        jm_menu.add(jmi_salir);
        // AGREGAMOS NUESTRO MENU A NUESTRA BARRA DE MENU
        jmb_barra.add(jm_menu);
        // AGREGAMOS NUESTRA BARRA DE MENU A NUESTRO PROYECTO
        setJMenuBar(jmb_barra);
        jmb_barra.setVisible(false);
        
        // EVENTOS DE MENU
        jmi_salir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                //TERMINAMOS EL SISTEMA
                System.exit(0);
            }
        });
        jmi_abrir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                int resultado = fileChooser.showOpenDialog(Reproductor.this);
                //SI EL USUARIO SELECCIONO UN ARCHIVO
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    File archivoSeleccionado = fileChooser.getSelectedFile();
                    lista.add(archivoSeleccionado.getAbsolutePath());
                    //SI ES EL PRIMER ARCHIVO SE COMIENZA A REPRODUCIR
                    if(lista.size() == 1){
                        reproducir();
                    }
                }
            }
        });
    }
	public final void init(){
	       //CREAMOS UN CONTENEDOR
	       panel = new JPanel();
	       //DEFINIMOS LA FORMA EN QUE VA A CONTENER ELEMENTOS EL CONTENEDOR
	       panel.setLayout(new BorderLayout());
	       //AGREGAMOS EL CONTENEDOR A NUESTRA VENTANA
	       add(panel); 
	       //EVENTOS
	       //AGREGAMOS EL EVENTO QUE DETECTA CUANDO PRESIONAMOS UNA TECLA - MAS ADELANTE LO IMPLEMENTAMOS CON KEYTYPED
	       addKeyListener(Reproductor.this);
	   }
	public void reproducir(){
        try {
            //COMPROBAMOS SI NO HABIA INSTANACIAS DE PLAYER REPRODUCIENDO
            if(mediaPlayer!=null){
                mediaPlayer.stop();
            }
            // OBTENEMOS LA RUTA DE LA LISTA DE ARCHIVOS EN BASE A LA POSICION ACTUAL
            URL url = new URL("file:\\"+lista.get(index));
            // CONVERTIMOS LA RUTA A RECURSO MEDIALOCATOR
            MediaLocator ml = new MediaLocator(url);
            //CREAMOS EL PLAYER Y LE ASIGNAMOS EL MEDIA LOCATOR
            mediaPlayer = Manager.createRealizedPlayer( ml );
            // OBTENEMOS LOS COMPONENTES DE VIDEO Y CONTROLES DEL REPRODUCTOR
            video = mediaPlayer.getVisualComponent();
            controles = mediaPlayer.getControlPanelComponent();
            //ELIMINAMOS CUALQUIER COMPONENTE QUE EXISTA EN EL CONTENDOR
            panel.removeAll();
            //AGREGAMOS LOS COMPONENTES DE VIDEO Y CONTROLES AL CONTENEDOR
            if ( video != null )
                panel.add("Center", video ); 
            if ( controles != null )
                panel.add( "South", controles ); 
            // DAMOS PLAY AL REPRODUCTOR
            mediaPlayer.start();
            // ACTUALIZAMOS LA INTERFAZ DEL CONTENEDOR PARA ACTUALIZAR CAMBIOS
            panel.updateUI();
            
            // EVENTOS DEL REPRODUCTOR
            mediaPlayer.addControllerListener(new ControllerListener(){
                @Override
                public void controllerUpdate(ControllerEvent ce) {
                    // SI EL EVENTO ES UNA INSTANCIA DE ENDOFMEDIA ES QUE TERMINO DE REPRODUCIR
                    if(ce instanceof EndOfMediaEvent) {
                        // REINICIAMOS EL VIDEO QUE SE REPRODUCIA
                        mediaPlayer.setMediaTime(new Time(0));
                        mediaPlayer.start();
                    }
                }                
            });
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            JOptionPane.showMessageDialog(Reproductor.this, "Ocurrio un error al reproducir el archivo "+new File(lista.get(index)).getName(), "Atención", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	public void keyTyped(KeyEvent e) {
        // SOLO HACEMOS ALGO SI LA LISTA DE REPRODUCCION TIENE ELEMNTOS
        System.out.println(e.getKeyChar());
        //SI SE PRESIONA LA TECLA ALT MUESTRA MENU O LO OCULTA
        if(e.getKeyChar() == showMenu){
            if(jmb_barra.isVisible()){
                jmb_barra.setVisible(false);
            }else{
                jmb_barra.setVisible(true);
            }                
        }else if(lista.size() > 0){
            //SI SE PRESIONA LA TECLA ANTERIOR SI MODIFICA EL INDEX Y SE MANDA A REPRODUCIR DE NUEVO
            if(e.getKeyChar() == anterior){
                if(index==0)
                    index = 0;
                else
                    index--;
                reproducir();
            }
            //SI LA SE PRESIONA LA TECLA SIGUIENTE SI MODIFICA EL INDEX Y SE MANDA A REPRODUCIR DE NUEVO
            if(e.getKeyChar() == siguiente){
                if((index+1) < lista.size())
                    index++;
                reproducir();
            }
        }else{
            //SINO HAY ELEMENTOS EN LA LISTA NOTIFICA AL USUARIO
            JOptionPane.showMessageDialog(Reproductor.this,"No hay elementos en la lista de reproducción.","Atención",JOptionPane.INFORMATION_MESSAGE);
        }
    }

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}