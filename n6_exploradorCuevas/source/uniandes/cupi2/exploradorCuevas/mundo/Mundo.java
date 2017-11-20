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
		tablero[filaJugador][columnaJugador].cambiarEstado(Casilla.JUGADOR);
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
	public int darMaxMovs()
	{
		System.out.println(cantidadMaxMovimientos	);
		return cantidadMaxMovimientos;

	}
	public int darNumeroBombas()
	{
		return numeroBombas;
	}

	public void mover( int i, int j) throws Exception
	{
		if (cantidadMaxMovimientos == 0)
			throw new Exception("Se terminaron los moviemtos");
		
		if (tablero[i][j].darEstado()==(Casilla.BOMBA))
		{
			tablero[filaJugador][columnaJugador].cambiarEstado(Casilla.NADA);
			tablero[i][j].cambiarEstado(Casilla.JUGADOR);
			filaJugador = i;
			columnaJugador = j;
			cantidadMaxMovimientos--;
			throw new Exception("Haz pisado una bomba");
		}
		

		if (tablero[i][j].darEstado()==(Casilla.NADA))
		{
			tablero[filaJugador][columnaJugador].cambiarEstado(Casilla.NADA);
			tablero[i][j].cambiarEstado(Casilla.JUGADOR);
			filaJugador = i;
			columnaJugador = j;
			cantidadMaxMovimientos--;

		}
		
		if (tablero[i][j].darEstado()==(Casilla.TESORO) )
		{
			tablero[i][j].cambiarEstado(Casilla.JUGADOR);
			filaJugador = i;
			columnaJugador = j;
			cantidadMaxMovimientos--;
			throw new Exception("Haz ganado");
		}
		
		

	}
	
	
	
	public ArrayList<Casilla> obtenerVecinos(int i, int j)
	{
		ArrayList<Casilla> respuesta = new ArrayList<Casilla>();
		if (estaEnElTablero(i-1, j-1))
			respuesta.add(tablero[i-1][j-1]);
		if (estaEnElTablero(i-1, j))
			respuesta.add(tablero[i-1][j]);
		if (estaEnElTablero(i-1, j-1))
			respuesta.add(tablero[i-1][j-1]);
		if (estaEnElTablero(i-1, j+1))
			respuesta.add(tablero[i-1][j+1]);
		if (estaEnElTablero(i, j-1))
			respuesta.add(tablero[i][j-1]);
		if (estaEnElTablero(i, j+1))
			respuesta.add(tablero[i][j+1]);
		if (estaEnElTablero(i+1, j-1))
			respuesta.add(tablero[i+1][j-1]);
		if (estaEnElTablero(i+1, j))
			respuesta.add(tablero[i+1][j]);
		if (estaEnElTablero(i+1, j+1))
			respuesta.add(tablero[i+1][j+1]);
		
		return respuesta;
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
		if (estaEnElTablero(i, j+1) && tablero[i][j+11].darEstado() == Casilla.BOMBA)
			respuesta++;
		if (estaEnElTablero(i+1, j-1) && tablero[i+1][j-1].darEstado() == Casilla.BOMBA)
			respuesta++;
		if (estaEnElTablero(i+1, j) && tablero[i+1][j].darEstado() == Casilla.BOMBA)
			respuesta++;
		if (estaEnElTablero(i+1, j+1) && tablero[i+1][j+1].darEstado() == Casilla.BOMBA)
			respuesta++;
		return respuesta;
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
