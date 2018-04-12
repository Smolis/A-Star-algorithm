
public class Nodo implements Comparable<Nodo>{
	private double distancia;
	private int x;
	private int y;
	private Nodo padre;
	private double distanciaRecorrida;
	
	public double getDistancia() {
		return distancia;
	}
	
	public void setDistancia(double dist) {
		this.distancia = dist;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int compareTo(Nodo o) {
		if (distancia > o.getDistancia()) {
            return 1;
        } 
		else if(distancia < o.getDistancia()) {
			return -1;
		}
		else {
            return 0;
        }
	}

	public Nodo getPadre() {
		return padre;
	}

	public void setPadre(Nodo padre) {
		this.padre = padre;
	}

	public double getDistanciaRecorrida() {
		return distanciaRecorrida;
	}

	public void setDistanciaRecorrida(double distanciaRecorrida) {
		this.distanciaRecorrida = distanciaRecorrida;
	}
	
	
}
