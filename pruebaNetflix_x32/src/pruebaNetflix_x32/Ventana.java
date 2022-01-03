package pruebaNetflix_x32;


import java.awt.BorderLayout; 
import java.awt.Component;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Jonathan
 */
public class Ventana extends JFrame{
    Player player;
    Component video;
    Component controles;
    String rutaVideo;
    
    public Ventana(String rutaVideo){
        setTitle("Reproductor v�deo Miguel y Juan");
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        this.rutaVideo=rutaVideo;
        init(this.rutaVideo);
    }

    public void init(String rutaVideo) {
            //panel principal
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            
            URL url=null;
            try {
                url = new URL("file:\\"+rutaVideo);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                player = Manager.createRealizedPlayer(new MediaLocator(url));
                video = player.getVisualComponent();
                video.setSize(800,500);
                video.setVisible(true);
                if(video != null)
                    panel.add("Center",video);
                
                controles = player.getControlPanelComponent();
                controles.setSize(800,100);
                controles.setVisible(true);
                if(controles != null)
                    panel.add("South",controles);
                
                add(panel);
                player.start();
                panel.updateUI();
            } catch (Exception ex) {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
