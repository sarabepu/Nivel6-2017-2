package uniandes.cupi2.exploradorCuevas.mundo;

import java.io.*;  
import java.util.*;

import uniandes.cupi2.exploradorCuevas.mundo.Casilla;




public class Mundo
{


	private int filas;
	private int columnas;

	private Casilla [][] tablero;

	private int filaJugador;
	private int columnaJugador;
	private int cantidadMaxMovimientos;
	private int cantidadVisitas;
	private Properties datos;


	public Mundo (File arch) throws Exception{
		datos = cargarTablero(arch);
		inicializarTablero(datos);

	}

	public Properties cargarTablero( File arch)throws Exception{

		Properties pDatos = new Properties( );
		FileInputStream in = new FileInputStream( arch );

		try {
			pDatos.load( in );
			in.close( );
		}
		catch( Exception e ) {
			throw new Exception( "Formato inv�lido" );
		}
		return pDatos;
	}

	public  void inicializarTablero(Properties datos){
		
		String numMovimientos = datos.getProperty("explorador.movimientos");
		cantidadMaxMovimientos = Integer.parseInt(numMovimientos);
		
		String numeroFilas = datos.getProperty("explorador.filas");
		filas = Integer.parseInt(numeroFilas);  

		String numeroColumnas= datos.getProperty("explorador.columnas");
		columnas = Integer.parseInt(numeroColumnas);  


		tablero = new Casilla [filas][columnas];

		for (int i = 0; i<filas; i++){
			for(int j = 0; j<columnas; j++){
				char[] filaActual = datos.getProperty("explorador.fila"+i).toCharArray();
				char estado = filaActual[j];
				if (estado == 'J')
				{
					filaJugador = j;
					columnaJugador = i;
				}
						
				tablero[i][j]= new Casilla (estado, i, j );
			}
		}
		tablero[filaJugador][columnaJugador].cambiarEstado(Casilla.JUGADOR);
		System.out.println(filaJugador + "," +  columnaJugador);
	} 



	public int darEstadoCasilla(int i, int j){
		int rpta = 0;
		Casilla actual = tablero[i][j];
		rpta = actual.darEstado();
		return rpta;
	}




	public int darFilas(){
		return filas;
	}

	public int darColumnas(){
		return columnas;
	}
	public Casilla[][] darTablero()
	{
		return tablero;
	}

	public int darFilaJugador()
	{
		return filaJugador;
	}
	public int darColumnaJugador()
	{
		return columnaJugador;
	}
	public int darMaxVisitas()
	{
		return cantidadVisitas;
	}

	public void mover( int i, int j) throws Exception
	{

		if (tablero[i][j].darEstado()==(Casilla.NADA) && cantidadMaxMovimientos > 0)
		{
			tablero[i][j].cambiarEstado(Casilla.JUGADOR);
			filaJugador = i;
			columnaJugador = j;
			cantidadMaxMovimientos--;

		}
		if (tablero[i][j].darEstado()==(Casilla.OBSTACULO))
			throw new Exception("No se puede mover a un obstaculo");
		
		if (tablero[i][j].darEstado()==(Casilla.TESORO) && cantidadMaxMovimientos > 0)
		{
			tablero[i][j].cambiarEstado(Casilla.JUGADOR);
			filaJugador = i;
			columnaJugador = j;
			cantidadMaxMovimientos--;
		}
			

	}


	public String darVecinos()
	{

		String arribaIzquierda= "";
		String arribaDerecha= "";
		String abajoIzquierda= "";
		String abajoDerecha= "";
		String arriba= "";
		String izquierda="";
		String derecha= "";
		String abajo= "";
		
		return null;
		
	}



	public boolean estaEnElTablero(int i, int j)
	{
		if(i<0||j<0||i>(tablero.length-1)||j>(tablero[0].length-1))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	public String Req1( )
	{
		return "Respuesta 1";
	}

	public String Req2( )
	{
		return "Respuesta 2";
	}

}
