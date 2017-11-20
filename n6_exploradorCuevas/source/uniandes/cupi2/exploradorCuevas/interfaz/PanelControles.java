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
		arribaD.addActionListener(this);
		arribaD.setActionCommand(ARRIBAIZ);



		arriba = new JButton ();
		arriba.setIcon(new ImageIcon("data/imagenes/direccion-1.png"));
		arriba.setEnabled(false);
		arriba.addActionListener(this);
		arriba.setActionCommand(ARRIBA);

		arribaI = new JButton ();
		arribaI.setIcon(new ImageIcon("data/imagenes/direccion-2.png"));
		arribaI.setEnabled(false);	
		arribaI.addActionListener(this);
		arribaI.setActionCommand(ARRIBADER);

		

		izquierda = new JButton ();
		izquierda.setIcon(new ImageIcon("data/imagenes/direccion-3.png"));
		izquierda.setEnabled(false);
		izquierda.addActionListener(this);
		izquierda.setActionCommand(IZQUIERDA);



		derecha = new JButton ();
		derecha.setIcon(new ImageIcon("data/imagenes/direccion-5.png"));
		derecha.setEnabled(false);
		derecha.addActionListener(this);
		derecha.setActionCommand(DERECHA);


		abajoD = new JButton ();
		abajoD.setIcon(new ImageIcon("data/imagenes/direccion-6.png"));
		abajoD.setEnabled(false);
		abajoD.addActionListener(this);
		abajoD.setActionCommand(ABAJOIZ);
		
		abajo = new JButton ();
		abajo.setIcon(new ImageIcon("data/imagenes/direccion-7.png"));
		abajo.setEnabled(false);
		abajo.addActionListener(this);
		abajo.setActionCommand(ABAJO);


		abajoI = new JButton ();
		abajoI.setIcon(new ImageIcon("data/imagenes/direccion-8.png"));
		abajoI.setEnabled(false);
		abajoI.addActionListener(this);
		abajoI.setActionCommand(ABAJODER);
		
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
		abajoD.setEnabled(true);
		abajoI.setEnabled(true);
		arriba.setEnabled(true);
		derecha.setEnabled(true);
		izquierda.setEnabled(true);	
		arribaD.setEnabled(true);
		arribaI.setEnabled(true);
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
		else if (comando.equals(ARRIBADER)){
			try {
				principal.mover(principal.darFilaJugador()-1, principal.darColumnaJugador()+1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
	}
		else if (comando.equals(ARRIBAIZ)){
			try {
				principal.mover(principal.darFilaJugador()-1, principal.darColumnaJugador()-1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
	}
		else if (comando.equals(ABAJOIZ)){
			try {
				principal.mover(principal.darFilaJugador()+1, principal.darColumnaJugador()-1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
	}
		else if (comando.equals(ABAJODER)){
			try {
				principal.mover(principal.darFilaJugador()+1, principal.darColumnaJugador()+1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
	}
		

	}
	public void finJuego() {
		// TODO Auto-generated method stub
		abajo.setEnabled(false);
		abajoI.setEnabled(false);
		abajoD.setEnabled(false);
		arriba.setEnabled(false);
		arribaD.setEnabled(false);
		arribaI.setEnabled(false);
		derecha.setEnabled(false);
		izquierda.setEnabled(false);	
		
	}
	}
