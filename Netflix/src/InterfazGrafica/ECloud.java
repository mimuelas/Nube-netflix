package InterfazGrafica;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class ECloud extends JFrame {

	private JPanel contentPane;
	private Cliente cliente;
	private JTextField textFieldRuta;
	private JTextField textFieldNombre;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente c=new Cliente("localhost",5300);
					ECloud frame = new ECloud(c);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ECloud(Cliente c) {
		cliente = c;
		c.establecerConexion();
		ArrayList<String> listaVideos = cliente.listarVideos();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("Imagenes\\Captura2.PNG"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTituloPrincipal = new JLabel("E-Cloud");
		lblTituloPrincipal.setForeground(Color.WHITE);
		lblTituloPrincipal.setFont(new Font("Times New Roman", Font.PLAIN, 36));
		lblTituloPrincipal.setBounds(96, 11, 150, 65);
		contentPane.add(lblTituloPrincipal);
		
		JLabel lblLogo = new JLabel("New label");
		lblLogo.setIcon(new ImageIcon("Imagenes\\Captura2.PNG"));
		lblLogo.setBounds(10, 11, 129, 72);
		contentPane.add(lblLogo);
		
		JComboBox comboBoxListaPeliculas = new JComboBox();
		comboBoxListaPeliculas.setBounds(10, 94, 275, 23);
		for(int i=0; i<listaVideos.size();i++){
			comboBoxListaPeliculas.addItem(listaVideos.get(i));
		}
		contentPane.add(comboBoxListaPeliculas);
		
		JButton btnReproducirPelicula = new JButton("Reproducir");
		btnReproducirPelicula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.reproducirVideo(comboBoxListaPeliculas.getSelectedIndex());
			}
		});
		btnReproducirPelicula.setBounds(295, 94, 129, 23);
		contentPane.add(btnReproducirPelicula);
		
		textFieldRuta = new JTextField();
		textFieldRuta.setBounds(141, 198, 268, 20);
		contentPane.add(textFieldRuta);
		textFieldRuta.setColumns(10);

		
		JLabel lblRuta = new JLabel("Ruta:");
		lblRuta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRuta.setLabelFor(textFieldRuta);
		lblRuta.setForeground(Color.WHITE);
		lblRuta.setBounds(72, 199, 46, 14);
		contentPane.add(lblRuta);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setBackground(Color.WHITE);
		lblNombre.setBounds(72, 233, 46, 14);
		contentPane.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(141, 229, 86, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		addWindowListener(new WindowAdapter() {
	      	   public void windowClosing(WindowEvent e) {
	      		   c.cerrarCliente();

	      	   }
	      	 });
		
		JButton btnSubirPelicula = new JButton("Subir pelicula");
		btnSubirPelicula.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSubirPelicula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldRuta.getText() != "" && textFieldRuta.getText() != null) {
					cliente.cargarVideo(textFieldRuta.getText(),textFieldNombre.getText());	
					comboBoxListaPeliculas.addItem(textFieldNombre.getText());

					
				}
			}
		});
		btnSubirPelicula.setBounds(314, 229, 95, 23);
		contentPane.add(btnSubirPelicula);
		
		JLabel lblFondo = new JLabel("New label");
		lblFondo.setIcon(new ImageIcon("Imagenes\\Fondo.jpg"));
		lblFondo.setBounds(0, 0, 434, 261);
		contentPane.add(lblFondo);
	}
}
