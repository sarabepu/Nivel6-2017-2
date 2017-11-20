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
    setSize(new Dimension(790, 694));
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


  public String darRutaImagenes(int estado){
    String rpta = "";
    if (estado==Casilla.OBSTACULO)
      rpta= "data/imagenes/obstaculo.png";
    else if (estado==Casilla.NADA)
      rpta= "data/imagenes/casilla_vacia.png";
    else if (estado==Casilla.BOMBA)
        rpta= "data/imagenes/casilla_vacia.png";
    else if (estado==Casilla.TESORO)
      rpta = "data/imagenes/tesoro.gif";
    else if (estado==Casilla.JUGADOR)
      rpta = "data/imagenes/jugador.gif";
    else if(estado== Casilla.CERCANA)
    rpta = "data/imagenes/casilla_iluminada.png";
    return rpta;
  }

  public void mover(int i, int j) {

try
{
    mundo.mover(i,j);
    panelTablero.actualizarMapa();
    panelInformacion.actualizar();
    //Actualizar numeros de visitas
}
catch(Exception e)
{
	if(e.getMessage().equals("No tiene la llave para abrir la puerta"))
	{
		JOptionPane.showMessageDialog( this, e.getMessage(), "Mover", JOptionPane.ERROR_MESSAGE);
	}
	else if(e.getMessage().equals("No ha recogido todos los chips"))
	{
		JOptionPane.showMessageDialog( this, e.getMessage(), "Mover", JOptionPane.ERROR_MESSAGE);
	}
	else if(e.getMessage().equals("¡Felicitaciones! Gano el juego"))
	{
		finJuego(e.getMessage());
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
	  return (Integer) null;
  }
  public String darTotal()
  {
	  return null;
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
  public void darVecinos()
  {
	  String mensaje= mundo.darVecinos();
		JOptionPane.showMessageDialog( this, mensaje, "Cantidad visitas", JOptionPane.INFORMATION_MESSAGE);
	
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


  public void finJuego(String mensaje) {
		JOptionPane.showMessageDialog( this, mensaje, "Juego Terminado", JOptionPane.INFORMATION_MESSAGE);
		panelInformacion.finJuego();
		panelOpciones.finJuego();
		panelTablero.finJuego();

	}


public String darBombaFila() {
	// TODO Auto-generated method stub
	return null;
}


public String darBombaColumna() {
	// TODO Auto-generated method stub
	return null;
}
  

 

}
