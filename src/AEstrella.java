

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.Queue;


public class AEstrella {
	private int filas;
	private int columnas;
	private double distanciaTotal;
	private CasillasGUI [][] casillas;

	boolean hayFin = false;

	private Nodo nodofin;
	Queue<Nodo> abierta;
	List<Nodo> cerrada ;

	public AEstrella(CasillasGUI [][] casillas , Nodo nodofin, Queue<Nodo> abierta, int filas, int columnas) {
		this.nodofin = nodofin;
		this.abierta = abierta;
		this.cerrada = new ArrayList<Nodo>();
		this.casillas = casillas;
		this.filas = filas;
		this.columnas = columnas;
		astar();
		
	}
	public void astar() {
		abierta.peek().setDistancia(calcularDist(abierta.peek(),nodofin));
		abierta.peek().setDistanciaRecorrida(0);
		while(abierta.size() != 0) {
			Nodo top = abierta.peek();
			cerrada.add(top);
			abierta.remove(abierta.peek());
			if(top.getDistancia() != 0 && estaEnCerrada(cerrada,top)) {
				expandir(top);

			}
			else {
				abierta.removeAll(abierta);
			}
		}
		
		if(estaEnCerrada(cerrada,nodofin)) {
			this.hayFin = true;
		}
		
	}
	
	
	public boolean hayFin() {
		return this.hayFin;
	}
	public boolean estaEnCerrada(List<Nodo> cerrada, Nodo n) {
		boolean esta = false;
		Iterator<Nodo> i = cerrada.iterator();
		while(i.hasNext()) {
			Nodo act = i.next();
			if(n.getX() == act.getX() && n.getY() == act.getY()) {
				esta = true;
			}
		}
		return esta;
	}
	public void expandir(Nodo exp) {
		boolean expandido = false;
		int[] x = {-1,0,1,-1,1,-1,1,0};
		int[] y = {-1,-1,-1,1,1,0,0,1};
		for(int i = 0; i < x.length; i++) {
			int nx= exp.getX()+x[i];
			int ny = exp.getY()+y[i];
			if(esValidoX(nx) && esValidoY(ny)) {
				Nodo nuevo = new Nodo();
				nuevo.setPadre(exp);
				nuevo.setX(nx);
				nuevo.setY(ny);
				nuevo.setDistancia(calcularDist(nuevo, nodofin));
				if(nuevo.getDistancia() == 0) {
					nodofin.setPadre(exp);
					calcularDistRecorrida(nodofin);
				}
				if(!estaEnCerrada(cerrada,nuevo)) {
					
					if(casillas[nx][ny].getTipo() == 2) {
						cerrada.add(nuevo);
					}
					else if(casillas[nx][ny].getTipo() == 4) {
						abierta.add(nuevo);
						double nuevaDist = nuevo.getDistancia()+1;
						nuevo.setDistancia(nuevaDist);
						expandido = true;
					}
					else if(casillas[nx][ny].getTipo() == 5) {
						abierta.add(nuevo);
						calcularDistRecorrida(nuevo);
						double nuevaDist = nuevo.getDistancia()+2;
						nuevo.setDistancia(nuevaDist);
						expandido = true;
					}
					else if(!abierta.contains(nuevo)) {
						abierta.add(nuevo);
						calcularDistRecorrida(nuevo);
						expandido = true;
					}	

				}

			}
		}
		if(!expandido) {
			cerrada.remove(exp);
		}

	}

	private void calcularDistRecorrida(Nodo nuevo) {
		double recorrida = nuevo.getPadre().getDistanciaRecorrida();
		double padre = calcularDist(nuevo, nuevo.getPadre());
		nuevo.setDistanciaRecorrida(padre+recorrida);
		
	}
	public boolean esValidoX(int n) {
		return n >= 0 && n < filas;
	}

	public boolean esValidoY(int n) {
		return n >= 0 && n < columnas;
	}

	public double calcularDist(Nodo act, Nodo fin) {
		double elevadoX = (double) Math.pow(act.getX()-fin.getX(), 2);
		double elevadoY = (double) Math.pow(act.getY()-fin.getY(), 2);
		double dist = (double)Math.sqrt(elevadoX+elevadoY);
		return dist;

	}
}
