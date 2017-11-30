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
	private int numeroBombas;
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
			throw new Exception( "Formato invalido" );
		}
		return pDatos;
	}

	public  void inicializarTablero(Properties datos){

		numeroBombas = 0;
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
					filaJugador = i;
					columnaJugador = j;
				}
				else if (estado == 'B')
					numeroBombas++;

				tablero[i][j]= new Casilla (estado, i, j );
			}
		}
		volverCercanas(filaJugador, columnaJugador, true);
		bombasCasillas();
	} 



	public int darEstadoCasilla(int i, int j){
		int rpta = 0;
		Casilla actual = tablero[i][j];
		rpta = actual.darEstado();
		return rpta;
	}

	public Casilla darCasillas(int i, int j)
	{
		return tablero[i][j];
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
	public int darMaxMovs()
	{
		return cantidadMaxMovimientos;

	}
	public int darNumeroBombas()
	{
		return numeroBombas;
	}

	public void mover( int i, int j) throws Exception
	{

		if (cantidadMaxMovimientos == 1)
		{
			cantidadMaxMovimientos--;
			throw new Exception("Se terminaron los moviemtos");
		}
		
		if (tablero[i][j].darEstado()==(Casilla.OBSTACULO))
		{
			throw new Exception("No se puede mover en esa dirección");
		}
		if (tablero[i][j].darEstado()==(Casilla.BOMBA))
		{
			volverCercanas(filaJugador, columnaJugador, false);
			tablero[filaJugador][columnaJugador].cambiarEstado(Casilla.NADA);
			tablero[i][j].cambiarEstado(Casilla.JUGADOR);
			filaJugador = i;
			columnaJugador = j;
			cantidadMaxMovimientos--;
			volverCercanas(filaJugador, columnaJugador, true);
			throw new Exception("Haz pisado una bomba");
		}

		if (tablero[i][j].darEstado()==(Casilla.NADA))
		{
			volverCercanas(filaJugador, columnaJugador, false);
			tablero[filaJugador][columnaJugador].cambiarEstado(Casilla.NADA);
			tablero[i][j].cambiarEstado(Casilla.JUGADOR);
			filaJugador = i;
			columnaJugador = j;
			volverCercanas(filaJugador, columnaJugador, true);
			cantidadMaxMovimientos--;

		}

		if (tablero[i][j].darEstado()==(Casilla.TESORO) )
		{
			volverCercanas(filaJugador, columnaJugador, false);
			tablero[i][j].cambiarEstado(Casilla.JUGADOR);
			tablero[filaJugador][columnaJugador].cambiarEstado(Casilla.NADA);
			filaJugador = i;
			columnaJugador = j;
			volverCercanas(filaJugador, columnaJugador, true);
			cantidadMaxMovimientos--;
			throw new Exception("Haz ganado");
		}



	}


	public int numeroBombasCercanas(int i, int j)
	{
		int respuesta = 0;
		if (estaEnElTablero(i-1, j-1) && tablero[i-1][j-1].darEstado() == Casilla.BOMBA)
			respuesta++;
		if (estaEnElTablero(i-1, j) && tablero[i-1][j].darEstado() == Casilla.BOMBA)
			respuesta++;
		if (estaEnElTablero(i-1, j+1) && tablero[i-1][j+1].darEstado() == Casilla.BOMBA)
			respuesta++;
		if (estaEnElTablero(i, j-1) && tablero[i][j-1].darEstado() == Casilla.BOMBA)
			respuesta++;
		if (estaEnElTablero(i, j) && tablero[i][j].darEstado() == Casilla.BOMBA)
			respuesta++;
		if (estaEnElTablero(i, j+1) && tablero[i][j+1].darEstado() == Casilla.BOMBA)
			respuesta++;
		if (estaEnElTablero(i+1, j-1) && tablero[i+1][j-1].darEstado() == Casilla.BOMBA)
			respuesta++;
		if (estaEnElTablero(i+1, j) && tablero[i+1][j].darEstado() == Casilla.BOMBA)
			respuesta++;
		if (estaEnElTablero(i+1, j+1) && tablero[i+1][j+1].darEstado() == Casilla.BOMBA)
			respuesta++;
		return respuesta;
	}

	public int numeroBombasFila()
	{ 
		int respuesta=0;
		for(int i=0; i<columnas; i++)
		{
			if(tablero[filaJugador][i].darEstado()==Casilla.BOMBA)
			{
				respuesta++;
			}
		}
		return respuesta;
	}
	public int numeroBombasColumna()
	{
		int respuesta=0;
		for(int i=0; i<filas; i++)
		{
			if(tablero[i][columnaJugador].darEstado()==Casilla.BOMBA)
			{
				respuesta++;
			}
		}
		return respuesta;
	}
	public void bombasCasillas()
	{
		int numero = 0;
		for (int i = 0; i < filas; i++)
		{
			for (int j = 0; j < columnas; j++) 
			{
				numero = numeroBombasCercanas(i, j);
				darCasillas(i, j).cambiarBombasCerca(numero);
			}
		}
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

	public boolean esNadaOBomba(int i, int j)
	{
		return (darCasillas(i, j).darEstado() == Casilla.BOMBA || darCasillas(i, j).darEstado() == Casilla.NADA);
	}



	public void volverCercanas(int i, int j, boolean aCercana)
	{
		if (estaEnElTablero(i-1, j-1) && esNadaOBomba(i-1, j-1) )
			darCasillas(i-1, j-1).cambiarEsCercana(aCercana);

		if (estaEnElTablero(i-1, j) && esNadaOBomba(i-1, j))
			darCasillas(i-1, j).cambiarEsCercana(aCercana);

		if (estaEnElTablero(i-1, j+1) && esNadaOBomba(i-1, j+1))
			darCasillas(i-1, j+1).cambiarEsCercana(aCercana);

		if (estaEnElTablero(i, j-1) && esNadaOBomba(i, j-1))
			darCasillas(i, j-1).cambiarEsCercana(aCercana);

		if (estaEnElTablero(i, j+1) && esNadaOBomba(i, j+1))
			darCasillas(i, j+1).cambiarEsCercana(aCercana);

		if (estaEnElTablero(i+1, j-1) && esNadaOBomba(i+1, j-1))
			darCasillas(i+1, j-1).cambiarEsCercana(aCercana);

		if (estaEnElTablero(i+1, j) && esNadaOBomba(i+1, j))
			darCasillas(i+1, j).cambiarEsCercana(aCercana);

		if (estaEnElTablero(i+1, j+1) && esNadaOBomba(i+1, j+1))
			darCasillas(i+1, j+1).cambiarEsCercana(aCercana);
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
