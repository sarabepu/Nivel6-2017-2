package uniandes.cupi2.exploradorCuevas.interfaz;

import java.awt.*; 
import java.io.File;

import javax.swing.*;

import uniandes.cupi2.exploradorCuevas.mundo.*;


public class InterfazJuego extends JFrame {
	private PanelOpciones panelOpciones;
	private PanelTablero panelTablero;
	private PanelImagen panelImagen;
	private PanelInformacion panelInformacion;
	private Mundo mundo;
	private File tablero;



	public InterfazJuego(){
		setSize(new Dimension(720, 694));
		setLayout (new BorderLayout());
		setResizable(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		panelImagen =new PanelImagen();
		add(panelImagen, BorderLayout.NORTH);

		panelOpciones =new PanelOpciones(this);
		add(panelOpciones, BorderLayout.SOUTH);

		panelTablero = new PanelTablero(this);
		add(panelTablero, BorderLayout.CENTER);

		panelInformacion = new PanelInformacion(this);
		add(panelInformacion, BorderLayout.EAST);


	}


	public void pedirTablero(){  

		JFileChooser fc = new JFileChooser( "./data" );
		fc.setDialogTitle( "Seleccionar mapa" );
		int resultado = fc.showOpenDialog( this );
		if( resultado == JFileChooser.APPROVE_OPTION )
		{
			tablero = fc.getSelectedFile( );
			try{
				mundo=new Mundo(tablero);

				panelTablero.inicializar();
				panelOpciones.inicializar();
				panelInformacion.inicializar();

			}
			catch (Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Formato invalido", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public Casilla darCasilla(int i, int j)
	{
		return mundo.darCasillas(i, j);
	}

	public String darRutaImagenes(Casilla pCasilla){
		String rpta = "";

		if (pCasilla.darEstado() == Casilla.OBSTACULO)
			rpta= "data/imagenes/obstaculo.png";
		else if(pCasilla.esCercana())
			rpta = "data/imagenes/casilla_iluminada.png";
		else if (pCasilla.darEstado() == Casilla.NADA)
			rpta= "data/imagenes/casilla_vacia.png";
		else if (pCasilla.darEstado() == Casilla.BOMBA)
			rpta= "data/imagenes/casilla_vacia.png";
		else if (pCasilla.darEstado() == Casilla.TESORO)
			rpta = "data/imagenes/tesoro.gif";
		else if (pCasilla.darEstado() == Casilla.JUGADOR)
			rpta = "data/imagenes/jugador.gif";
		return rpta;
	}

	public void mover(int i, int j) {

		try
		{
			mundo.mover(i,j);
			panelTablero.inicializar();;
			panelInformacion.actualizar();
			//Actualizar numeros de visitas
		}
		catch(Exception e)
		{
			panelTablero.inicializar();
			panelInformacion.actualizar();
			if(e.getMessage().equals("Haz pisado una bomba"))
			{
				panelTablero.darTablero()[mundo.darFilaJugador()][mundo.darColumnaJugador()].setIcon(new ImageIcon("data/imagenes/jugador_quemado.png"));
				finJuego("¡Pisaste una boma! Tu juego ha terminado");	
			}
			else if(e.getMessage().equals("Se terminaron los moviemtos"))
			{
				finJuego("¡Te haz quedado sin movimientos! Tu juego ha terminado");
			}

			else if(e.getMessage().equals("Haz ganado"))
			{
				finJuego("¡Felicidades! Ganaste el juego");
			}
			else if(e.getMessage().equals("No se puede mover en esa dirección"))
			{
				JOptionPane.showMessageDialog( this, e.getMessage(), "Cuidado", JOptionPane.INFORMATION_MESSAGE);

			}
			


		}



	}


	public int darFilas(){

		return mundo.darFilas();
	}
	public int darColumnas(){

		return mundo.darColumnas();
	}

	public int darMovs()
	{
		return mundo.darMaxMovs();
	}
	public String darTotal()
	{
		return String.valueOf(mundo.darNumeroBombas());
	}

	public int darEstado(int i, int j)
	{
		return mundo.darEstadoCasilla(i,j);
	}
	public int darFilaJugador()
	{
		return mundo.darFilaJugador();
	}
	public int darColumnaJugador()
	{
		return mundo.darColumnaJugador();
	}

	public void reiniciar() {
		try{

			mundo=new Mundo(tablero);
			panelTablero.inicializar();
			panelOpciones.inicializar();
			panelInformacion.inicializar();

		}
		catch (Exception e){
			e.printStackTrace();
		}
	}



	public void finJuego(String mensaje) {
		panelInformacion.finJuego();
		panelOpciones.finJuego();
		JOptionPane.showMessageDialog( this, mensaje, "Juego Terminado", JOptionPane.INFORMATION_MESSAGE);

	}


	public String darBombaFila() {
		// TODO Auto-generated method stub
		return mundo.numeroBombasFila()+"";
	}


	public String darBombaColumna() {
		// TODO Auto-generated method stub
		return mundo.numeroBombasColumna()+"";
	}
	public void Req1( )
	{
		String resultado = mundo.Req1( );

		JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
	}


	public void Req2( )
	{
		String resultado = mundo.Req2( );

		JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
	}
	public static void main(String[] args) 
	{
		InterfazJuego vent = new InterfazJuego( );
		vent.setVisible( true );
	}






}
