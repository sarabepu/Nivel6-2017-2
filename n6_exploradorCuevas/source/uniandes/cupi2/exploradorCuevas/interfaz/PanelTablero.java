	package uniandes.cupi2.exploradorCuevas.interfaz;


import java.awt.*; 
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.border.*;

import uniandes.cupi2.exploradorCuevas.mundo.Casilla;
public class PanelTablero extends JPanel{

	
	private InterfazJuego principal;
	private JLabel[][] casillas;
	private int filas;
	private int columnas;

	
	public PanelTablero(InterfazJuego v){
		principal = v;
		TitledBorder border = BorderFactory.createTitledBorder("");
		border.setTitleColor( Color.BLACK );
		setBorder( border );
		setLayout ( new GridLayout());
		
	}

	
	public void inicializar(){
		
		//Limpia las imagenes existentes en el panel
		
		removeAll();
		
		//Crea la forma
		filas = principal.darFilas();
		columnas = principal.darColumnas();
		setLayout( new GridLayout(filas, columnas));

		casillas= new JLabel[filas][columnas];
		
		for ( int i = 0; i< casillas.length; i++){
			for( int j =0; j<casillas[0].length; j++){
				
					JLabel actual = new JLabel();
					Casilla c = principal.darCasilla(i, j);
					ImageIcon icono = new ImageIcon( principal.darRutaImagenes(c));
					if(c.esCercana())
					{
						System.out.println("llego aca: "+c.darBombasCerca());
						actual.setForeground(Color.CYAN);
						actual.setHorizontalTextPosition(JLabel.CENTER);
						actual.setText(c.darBombasCerca()+"");
					}
					actual.setIcon(icono);
					casillas[i][j] = actual;
					add(actual);
				
				
			}
		}
		validate();
	} 
	
	public JLabel[][] darTablero(){
		return casillas;
	}
	public void actualizarMapa() 
	{
		for ( int i = 0; i< casillas.length; i++){
			for( int j =0; j<casillas[0].length; j++){

				Casilla c = principal.darCasilla(i, j);
				ImageIcon icono = new ImageIcon( principal.darRutaImagenes(c));
				if(c.esCercana())
				{
					casillas[i][j].setForeground(Color.CYAN);
					casillas[i][j].setHorizontalTextPosition(JLabel.CENTER);
					casillas[i][j].setText(c.darBombasCerca()+"");
				}
				else
					casillas[i][j].setText("");
				casillas[i][j].setIcon(icono);
			}
		}
	}
	public void finJuego(){
		casillas[principal.darFilaJugador()][principal.darColumnaJugador()].setIcon(new ImageIcon("data/imagenes/jugador_quemado.png"));
	}
	





}


