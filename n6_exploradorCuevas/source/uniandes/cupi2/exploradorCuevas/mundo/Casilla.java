package uniandes.cupi2.exploradorCuevas.mundo;

public class Casilla {
	public final static char NADA = 'N';
	public final static char JUGADOR = 'J';
	public final static char CERCANA = 'C';
	public final static char OBSTACULO = 'O';
	public final static char TESORO = 'T';
	public final static char BOMBA = 'B';
	
	private int estado;
	private int columna;
	private int fila;

	public Casilla (int pEstado, int pFila, int pColumna){
		estado = pEstado;
		columna = pColumna;
		fila = pFila;
	}
	public int darEstado()
	{
		return estado; 
	}
	public int darFila() {
		return fila;
	}
	public int darColumna(){
		return columna;
	}
	public void cambiarEstado(int pEstado){
		estado=pEstado;
	}
	
}
