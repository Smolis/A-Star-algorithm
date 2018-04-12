
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
public class CasillasGUI extends javax.swing.JPanel implements MouseListener {
    
    private TableroGUI tablero;
    private ImageIcon fondo;
    private static int [] casillaMarcada = new int[2];
    private int tipo; //0 - normal, 1 - inicio, 2 - obstaculo, 3 - fin, 4 - penzalizacion1, 5-penalizacion2

    public CasillasGUI() {        
        // este constructor no se usará, se deja para poder crear el bean.        
    }
    
    public CasillasGUI(TableroGUI t) {
        initComponents();        
        this.tablero = t;
        this.addMouseListener(this);
        
    }
    
    public void setFondo(ImageIcon fondo){
        this.fondo = fondo;
    }
    
    public ImageIcon getFondo(){        
        return this.fondo;
    }
    
    public void setTipo(int tipo) {
    	this.tipo = tipo;
    }
    public int getTipo() {
    	return this.tipo;
    }
    
                          
    private void initComponents() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 161, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 193, Short.MAX_VALUE)
        );
    }                       
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(fondo.getImage(), 0,0,this.getWidth(),this.getHeight(),this);
    }
    
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){
            this.setCasillaMarcada(tablero.getCoordenadas((CasillasGUI)e.getComponent())); 
            this.tablero.pintar(this.getCasillaMarcada()[0],this.getCasillaMarcada()[1]);
    }
    public void mouseReleased(MouseEvent e){}
    
    public static int[] getCasillaMarcada() {
        return casillaMarcada;
    }
    public static void setCasillaMarcada(int[] aCasillaMarcada) {
        casillaMarcada = aCasillaMarcada;
    }                  
    
}