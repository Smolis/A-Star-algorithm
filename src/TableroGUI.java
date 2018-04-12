

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class TableroGUI extends javax.swing.JPanel {
	private ImageIcon normal, inicio, fin, obs, marcado, pen1, pen2, pen1m, pen2m;
	private CasillasGUI [][] casillas;
	private int filas;
	private int columnas;
	private int obstaculos;
	private int penalizaciones;
	private boolean ini;
	private boolean fini;
	private double distanciaTotal;
	private int altura;

	private Nodo nodofin;
	Queue<Nodo> abierta;
	



	public TableroGUI() {
		initComponents();
	}
	
	public TableroGUI(int filas, int columnas, int obstaculos, int penalizaciones, int altura) {
		this.filas = filas;
		this.columnas = columnas;
		this.obstaculos = obstaculos;
		this.ini = false;
		this.fini = false;
		this.altura = altura;
		this.distanciaTotal = 0;
		this.penalizaciones = penalizaciones;

		this.abierta = new PriorityQueue<Nodo>();
		

		initComponents();
		int x,y;
		setLayout(new java.awt.GridLayout(filas, columnas));

		cargarImagenes();
		casillas = new CasillasGUI[filas][columnas];
		for (int i = 0; i < filas; i++){
			for (int j = 0; j < columnas; j++){
				casillas[i][j] = new CasillasGUI(this); 
				casillas[i][j].setFondo(normal);
				casillas[i][j].setTipo(0);
				casillas[i][j].setBorder(BorderFactory.createLineBorder(Color.white));
				x = (i * 35)+1;
				y = (j * 35)+1;
				casillas[i][j].setBounds(x, y, 34, 34);
				this.add(casillas[i][j]);
			}
		}
	}


	public Queue<Nodo> getAbierta(){
		return this.abierta;
	}
	public CasillasGUI getCasilla(int x, int y) {
		return casillas[x][y];
	}
	public void pintar(int x, int y){
		if(!ini) {
			this.casillas[x][y].setFondo(inicio);
			casillas[x][y].setTipo(1);
			this.ini = true;
			Nodo aux = new Nodo();
			aux.setX(x);
			aux.setY(y);
			abierta.add(aux);
			this.repaint();
		}
		else if(obstaculos > 0) {
			this.casillas[x][y].setFondo(obs);
			casillas[x][y].setTipo(2);
			this.obstaculos--;
			this.repaint();
		}
		else if(penalizaciones > 0) {
			if(altura == 1) {
				this.casillas[x][y].setFondo(pen1);
				casillas[x][y].setTipo(4);
			}
			else {
				this.casillas[x][y].setFondo(pen2);
				casillas[x][y].setTipo(5);
			}
			
			this.penalizaciones--;
			this.repaint();
		}
		else if(!fini) {
			this.casillas[x][y].setFondo(fin);
			casillas[x][y].setTipo(3);
			this.fini = true;
			nodofin = new Nodo();
			nodofin.setX(x);
			nodofin.setY(y);
			this.repaint();
			AEstrella a = new AEstrella(casillas,nodofin,abierta,filas,columnas);
			if(a.hayFin) {
				pintarCamino(nodofin);
				mostrarMensaje("La distancia recorrida es: "+String.format("%.2f", distanciaTotal));
			}
			else {
				mostrarMensaje("No hay camino posible");
			}

		}


	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}
	public void pintarCamino(Nodo act) {
		if(act.getPadre() != null) {
			if(casillas[act.getX()][act.getY()].getTipo() == 0) {
				this.casillas[act.getX()][act.getY()].setFondo(marcado);
				this.repaint();
			}
			if(casillas[act.getX()][act.getY()].getTipo() == 4) {
				this.casillas[act.getX()][act.getY()].setFondo(pen1m);
				this.repaint();
			}
			if(casillas[act.getX()][act.getY()].getTipo() == 5) {
				this.casillas[act.getX()][act.getY()].setFondo(pen2m);
				this.repaint();
			}
			if(casillas[act.getX()][act.getY()].getTipo() == 3) {
				distanciaTotal = act.getDistanciaRecorrida();
			}
			pintarCamino(act.getPadre());
		}
		
	}
	

	private void cargarImagenes() {
		this.normal = new ImageIcon("imagenes/normal.jpg");
		this.inicio = new ImageIcon("imagenes/inicio.jpg");
		this.fin = new ImageIcon("imagenes/fin.jpg");
		this.obs = new ImageIcon("imagenes/obstaculo.jpg");
		this.marcado = new ImageIcon("imagenes/marcado.jpg");
		this.pen1 = new ImageIcon("imagenes/pen1.jpg");
		this.pen2 = new ImageIcon("imagenes/pen2.jpg");
		this.pen1m = new ImageIcon("imagenes/pen1m.jpg");
		this.pen2m = new ImageIcon("imagenes/pen2m.jpg");
		
	}

	public Nodo getNodoFin() {
		return this.nodofin;
	}

	public int[] getCoordenadas(CasillasGUI casilla) {
		int [] coordenadas = new int[2];
		for (int i=0; i < this.filas; i++) {
			for (int j=0; j < this.columnas; j++) {
				if (this.casillas[i][j] == casilla) {
					coordenadas[0] = i;
					coordenadas[1] = j;
				}
			}
		}
		return coordenadas;
	}

	public CasillasGUI[][] getCasillas() {
		return casillas;
	}

	public void setCasillas(CasillasGUI[][] casillas) {
		this.casillas = casillas;
	}



	private void initComponents() {
		setLayout(null);
		setBackground(Color.white);
		setPreferredSize(new java.awt.Dimension(351, 351));
	}


}