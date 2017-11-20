package uniandes.cupi2.exploradorCuevas.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class PanelInformacion  extends JPanel implements ActionListener{
	private PanelControles controles;
	private PanelBombas bombas;
	private JPanel movimientos;
	private JLabel mov;
	private InterfazJuego principal;
	
	public PanelInformacion(InterfazJuego v)
	{
		principal=v;
		setLayout(new BorderLayout());

		movimientos= new JPanel();
		mov = new JLabel("--", SwingConstants.CENTER);
		mov.setForeground(Color.BLACK);
		mov.setFont(new Font("--", 50, 50));
		movimientos.add(mov);
		add(movimientos, BorderLayout.NORTH);
		TitledBorder border = BorderFactory.createTitledBorder( "Movimientos restantes");
	    border.setTitleColor( Color.BLACK );
	    movimientos.setBorder( border );
	    
		
		controles=new PanelControles(v);
		add(controles, BorderLayout.CENTER);
		
		bombas=new PanelBombas(v);
		add(bombas, BorderLayout.SOUTH);
		
	}
	public void inicializar()
	{
		mov.setText(""+ principal.darMovs());
		controles.inicializar();
		bombas.actualizar();
	}
	
	public void actualizar()
	{
		bombas.actualizar();
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void finJuego() {
		// TODO Auto-generated method stub
		controles.finJuego();
		
		
	}

}
