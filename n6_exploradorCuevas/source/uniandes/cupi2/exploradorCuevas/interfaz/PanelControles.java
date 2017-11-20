package uniandes.cupi2.exploradorCuevas.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.exploradorCuevas.interfaz.InterfazJuego;

public class PanelControles extends JPanel implements ActionListener {
	public final static String ARRIBA ="arriba";
	public final static String ABAJO ="abajo";
	public final static String DERECHA ="derecha";
	public final static String IZQUIERDA ="izquierda";
	public final static String ARRIBADER ="arribader";
	public final static String ABAJODER ="abajoder";
	public final static String ARRIBAIZ ="arribaiz";
	public final static String ABAJOIZ ="abajoiz";

	
	private InterfazJuego principal;

	
	private JButton arriba;
	private JButton abajo;
	private JButton derecha;
	private JButton izquierda;
	private JButton arribaD;
	private JButton abajoD;
	private JButton arribaI;
	private JButton abajoI;
	
	public PanelControles(InterfazJuego v){
		principal = v;
		
		
		TitledBorder border = BorderFactory.createTitledBorder( "Controles");
		border.setTitleColor( Color.BLACK );
		setBorder( border );
		setLayout(new GridLayout(7,3));
		arribaD = new JButton ();
		arribaD.setIcon(new ImageIcon("data/imagenes/direccion-0.png"));
		arribaD.setEnabled(false);
		arribaD.setPreferredSize(new Dimension(51, 51));
		arribaD.addActionListener(this);
		arribaD.setActionCommand(ARRIBADER);



		arriba = new JButton ();
		arriba.setIcon(new ImageIcon("data/imagenes/direccion-1.png"));
		arriba.setEnabled(false);
		arriba.setPreferredSize(new Dimension(51, 51));
		arriba.addActionListener(this);
		arriba.setActionCommand(ARRIBA);

		arribaI = new JButton ();
		arribaI.setIcon(new ImageIcon("data/imagenes/direccion-2.png"));
		arribaI.setEnabled(false);
		arribaI.setPreferredSize(new Dimension(51, 51));
		arribaI.addActionListener(this);
		arribaI.setActionCommand(ARRIBAIZ);

		

		izquierda = new JButton ();
		izquierda.setIcon(new ImageIcon("data/imagenes/direccion-3.png"));
		izquierda.setEnabled(false);
		izquierda.setPreferredSize(new Dimension(51, 51));
		izquierda.addActionListener(this);
		izquierda.setActionCommand(IZQUIERDA);



		derecha = new JButton ();
		derecha.setIcon(new ImageIcon("data/imagenes/direccion-5.png"));
		derecha.setEnabled(false);
		derecha.setPreferredSize(new Dimension(51, 51));
		derecha.addActionListener(this);
		derecha.setActionCommand(DERECHA);


		abajoD = new JButton ();
		abajoD.setIcon(new ImageIcon("data/imagenes/direccion-6.png"));
		abajoD.setEnabled(false);
		abajoD.setPreferredSize(new Dimension(51, 51));
		abajoD.addActionListener(this);
		abajoD.setActionCommand(ABAJODER);
		
		abajo = new JButton ();
		abajo.setIcon(new ImageIcon("data/imagenes/direccion-7.png"));
		abajo.setEnabled(false);
		abajo.setPreferredSize(new Dimension(51, 51));
		abajo.addActionListener(this);
		abajo.setActionCommand(ABAJO);


		abajoI = new JButton ();
		abajoI.setIcon(new ImageIcon("data/imagenes/direccion-8.png"));
		abajoI.setEnabled(false);
		abajoI.setPreferredSize(new Dimension(51, 51));
		abajoI.addActionListener(this);
		abajoI.setActionCommand(ABAJOIZ);
		
		 add(new JLabel(""));
		 add(new JLabel(""));
		 add(new JLabel(""));
		 add(arribaD);
		 add(arriba);
		 add(arribaI);
		 add(izquierda);
		 add(new JLabel(""));
		 add(derecha);
		 add(abajoD);
		 add(abajo);
		 add(abajoI);
		 add(new JLabel(""));
		 add(new JLabel(""));
		 add(new JLabel(""));
		




	}
	public void inicializar (){
		abajo.setEnabled(true);
		arriba.setEnabled(true);
		derecha.setEnabled(true);
		izquierda.setEnabled(true);		
	}


	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		if (comando.equals(ARRIBA)){
			
				try {
					principal.mover(principal.darFilaJugador()-1, principal.darColumnaJugador());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			

		}
		else if ( comando.equals(ABAJO)){
			
				try {
					principal.mover(principal.darFilaJugador()+1, principal.darColumnaJugador());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		}
		else if (comando.equals(DERECHA)){
			
				try {
					principal.mover(principal.darFilaJugador(), principal.darColumnaJugador()+1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
		}
		else if (comando.equals(IZQUIERDA)){
			
				try {
					principal.mover(principal.darFilaJugador(), principal.darColumnaJugador()-1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		}
		

	}
	public void finJuego() {
		// TODO Auto-generated method stub
		abajo.setEnabled(false);
		arriba.setEnabled(false);
		derecha.setEnabled(false);
		izquierda.setEnabled(false);	
		
	}
	}
