package uniandes.cupi2.exploradorCuevas.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;



public class PanelBombas extends JPanel  {

  private InterfazJuego principal;
  private JLabel total;
  private JLabel fila;
  private JLabel columna;
  private JTextField txtTotal;
  private JTextField txtFila;
  private JTextField txtColumna;

  
  public PanelBombas	(InterfazJuego v)
  {
	  principal = v;
	  inicializar();
  }
  
  public void inicializar()
  {
	    total=new JLabel("Total");
	    fila= new JLabel("Fila actual");
	    columna= new JLabel ("Columna actual");
	    txtTotal= new JTextField();
	    txtTotal.setEditable(false);
	    txtColumna= new JTextField();
	    txtColumna.setEditable(false);
	    txtFila= new JTextField();
	    txtFila.setEditable(false);
	    
	    
	    setLayout(new GridLayout(3, 2));
	    TitledBorder border = BorderFactory.createTitledBorder( "Cantidad bombas");
	    border.setTitleColor( Color.BLACK );
	    setBorder( border );
	    
	    add(total);
	    add(txtTotal);
	    add(fila);
	    add(txtFila);
	    add(columna);
	    add(txtColumna);
  }
  
  public void actualizar()
  {
	  txtTotal.setText(""+principal.darTotal());
	  txtFila.setText(""+principal.darBombaFila());
	  txtColumna.setText(""+principal.darBombaColumna());
	  
  }



}
