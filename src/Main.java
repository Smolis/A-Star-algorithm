

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



public class Main{

	private static final long serialVersionUID = 1L;

	private JFrame ventana;
	private JPanel panelLateral;
	private TableroGUI tablero;
	private JTextField fil;
	private JTextField col;
	private JTextField obs;
	private int filas;
	private int columnas;
	private int obstaculos;
	private int penalizaciones;
	private int altura;
	private JTextField pen;
    private JComboBox jComboBox1;
	private CasillasGUI[][] casillas;

	public Main(){
		ventana = new JFrame("A*");
		this.filas = 10;
		this.columnas = 10;
		this.obstaculos = 9;
		this.penalizaciones = 3;
		this.altura = 2;
		initGUI();



	}
	private void initGUI() {
		ventana.setSize(800,500);
		ventana.setLocation(300, 150);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setVisible(true);

		casillas = new CasillasGUI[filas][columnas];
		tablero = new TableroGUI(filas, columnas, obstaculos, penalizaciones,2);

		casillas = tablero.getCasillas();
		panelLateral = new JPanel();
		tablero.setBackground(Color.white);
		panelLateral.setBackground(Color.white);
		ventana.add(tablero);
		panelLateral();


	}

	private void actualizarValores(int filas, int columnas, int obstaculos, int penalizaciones, int altura) {
		this.filas = filas;
		this.columnas = columnas;
		this.obstaculos = obstaculos;
		this.penalizaciones = penalizaciones;
		this.altura = altura;
		casillas = new CasillasGUI[filas][columnas];

	}


	private void panelLateral() {
		JLabel labFil = new JLabel();
		JLabel labCol = new JLabel ();
		JLabel labObs = new JLabel ();
		JLabel labPen = new javax.swing.JLabel();
		fil = new JTextField();
		obs = new JTextField();
		col = new JTextField();
		JButton empezar = new JButton();
		
		fil = new javax.swing.JTextField();
        labFil = new javax.swing.JLabel();
        labCol = new javax.swing.JLabel();
        col = new javax.swing.JTextField();
        empezar = new javax.swing.JButton();
        labObs = new javax.swing.JLabel();
        obs = new javax.swing.JTextField();
        
        pen = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();

        labFil.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        labFil.setText("Filas");

        labCol.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        labCol.setText("Columnas");

        empezar.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        empezar.setText("Empezar");
        empezar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(!fil.getText().isEmpty() && !col.getText().isEmpty() && !obs.getText().isEmpty() && !pen.getText().isEmpty()){
					int altura = jComboBox1.getSelectedIndex()+1;
					actualizarValores(Integer.parseInt(fil.getText()),Integer.parseInt(col.getText()), Integer.parseInt(obs.getText()), Integer.parseInt(pen.getText()),altura);
					ventana.remove(tablero);
					tablero = new TableroGUI(filas, columnas, obstaculos, penalizaciones, altura);
					ventana.add(tablero);
					ventana.revalidate();
					
				}
				else {
					ventana.remove(tablero);
					tablero = new TableroGUI(filas, columnas, obstaculos, penalizaciones, altura);
					ventana.add(tablero);
					ventana.revalidate();
				}
				
			}
		});
		

        labObs.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        labObs.setText("Obstáculos");

        labPen.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        labPen.setText("Penalizaciones");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Altura 1", "Altura 2"}));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panelLateral);
        panelLateral.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(empezar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labObs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fil)
                    .addComponent(col, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(obs)
                    .addComponent(labPen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labFil, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labCol, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pen, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 5, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(labFil, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labCol, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(col, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labObs, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(obs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labPen, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(empezar)
                .addContainerGap())
        );

		ventana.add(panelLateral, BorderLayout.EAST);

	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main();

			}
		});


	}



}             

