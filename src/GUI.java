import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame{
	private JPanel contentPane;
	
	public GUI(){
		
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 51);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel Titulo = new JLabel("ClimaTowers");
		Titulo.setFont(new Font("Dubai", Font.BOLD, 28));
		Titulo.setBounds(10, 11, 154, 26);
		panel.add(Titulo);
		
		JButton Boton_Buscar = new JButton("");
		Boton_Buscar.setBounds(394, 11, 30, 30);
		panel.add(Boton_Buscar);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 48, 434, 413);
		contentPane.add(tabbedPane);
		
		JPanel panel_hoy = new JPanel();
		tabbedPane.addTab("New tab", null, panel_hoy, null);
		panel_hoy.setLayout(null);
		
		
		
		JLabel Imagen_Tiempo = new JLabel("");
		Imagen_Tiempo.setBounds(313, 28, 80, 80);
		panel_hoy.add(Imagen_Tiempo);
		
		JLabel Temperatura_hor = new JLabel("26 ºC");
		Temperatura_hor.setFont(new Font("Dubai", Font.BOLD, 28));
		Temperatura_hor.setBounds(20, 28, 80, 80);
		panel_hoy.add(Temperatura_hor);
		
		
		JLabel Recomendaciones = new JLabel("New label");
		Recomendaciones.setFont(new Font("Dubai", Font.BOLD, 12));
		Recomendaciones.setVerticalAlignment(SwingConstants.TOP);
		Recomendaciones.setBounds(10, 245, 409, 36);
		panel_hoy.add(Recomendaciones);
		
		JLabel Recomendaciones1 = new JLabel("New label");
		Recomendaciones1.setFont(new Font("Dubai", Font.BOLD, 12));
		Recomendaciones1.setVerticalAlignment(SwingConstants.TOP);
		Recomendaciones1.setBounds(10, 291, 409, 36);
		panel_hoy.add(Recomendaciones1);
		
		JLabel Recomendaciones2 = new JLabel("New label");
		Recomendaciones2.setFont(new Font("Dubai", Font.BOLD, 12));
		Recomendaciones2.setVerticalAlignment(SwingConstants.TOP);
		Recomendaciones2.setBounds(10, 338, 409, 36);
		panel_hoy.add(Recomendaciones2);
		
		JLabel Imagen_viento = new JLabel(new ImageIcon("Imagenes/icono_viento.png"));
		Imagen_viento.setBounds(35, 160, 30, 30);
		panel_hoy.add(Imagen_viento);
		
		
		JLabel Info_viento = new JLabel();
		Info_viento.setText("15 km/h");
		Info_viento.setFont(new Font("Dubai", Font.BOLD, 13));
		Info_viento.setBounds(25, 190, 54, 30);
		panel_hoy.add(Info_viento);
		
		
		JLabel Imagen_prob_lluvia = new JLabel(new ImageIcon("Imagenes/lluvia_icono.png"));
		Imagen_prob_lluvia.setBounds(190, 160, 30, 30);
		panel_hoy.add(Imagen_prob_lluvia);
		
		JLabel Info_prob_lluvia = new JLabel();
		Info_prob_lluvia.setText("15 %");
		Info_prob_lluvia.setFont(new Font("Dubai", Font.BOLD, 13));
		Info_prob_lluvia.setBounds(190, 190, 50, 30);
		panel_hoy.add(Info_prob_lluvia);
		
		JLabel Info_hora_lluvia = new JLabel();
		Info_hora_lluvia.setText("0.0 h");
		Info_hora_lluvia.setFont(new Font("Dubai", Font.BOLD, 13));
		Info_hora_lluvia.setBounds(340, 190, 45, 30);
		panel_hoy.add(Info_hora_lluvia);
		
		JLabel Imagen_hor_lluvia = new JLabel(new ImageIcon("Imagenes/hora_lluvia_icono.png"));
		Imagen_hor_lluvia.setBounds(340, 160, 30, 30);
		panel_hoy.add(Imagen_hor_lluvia);
		
		JLabel Imagen_maxmin = new JLabel(new ImageIcon("Imagenes/maxmin_icono.png"));
		Imagen_maxmin.setBounds(123, 45, 50, 50);
		panel_hoy.add(Imagen_maxmin);
		
		JLabel max_temperatura = new JLabel("40 ºC");
		max_temperatura.setFont(new Font("Dubai", Font.BOLD, 14));
		max_temperatura.setBounds(183, 45, 46, 14);
		panel_hoy.add(max_temperatura);
		
		JLabel min_temperatura = new JLabel("10 ºC");
		min_temperatura.setFont(new Font("Dubai", Font.BOLD, 14));
		min_temperatura.setBounds(183, 80, 46, 14);
		panel_hoy.add(min_temperatura);
		
		JLabel Descripcion_tiempo = new JLabel("Despejado");
		Descripcion_tiempo.setHorizontalAlignment(SwingConstants.CENTER);
		Descripcion_tiempo.setFont(new Font("Dubai", Font.BOLD, 16));
		Descripcion_tiempo.setBounds(274, 105, 145, 14);
		panel_hoy.add(Descripcion_tiempo);
		
		JLabel Imagen_fondo = new JLabel(new ImageIcon("Imagenes/cuadrado.png"));
		Imagen_fondo.setBackground(Color.WHITE);
		Imagen_fondo.setForeground(Color.WHITE);
		Imagen_fondo.setBounds(10, 11, 409, 117);
		panel_hoy.add(Imagen_fondo);
		
		JLabel Imagen_fondo_1 = new JLabel(new ImageIcon("Imagenes/cuadrado.png"));
		Imagen_fondo_1.setForeground(Color.WHITE);
		Imagen_fondo_1.setBackground(Color.WHITE);
		Imagen_fondo_1.setBounds(10, 147, 409, 86);
		panel_hoy.add(Imagen_fondo_1);
		
		JLabel Recomendaciones_fondo = new JLabel(new ImageIcon("Imagenes/cuadrado.png"));
		Recomendaciones_fondo.setForeground(Color.WHITE);
		Recomendaciones_fondo.setBackground(Color.WHITE);
		Recomendaciones_fondo.setBounds(10, 244, 409, 130);
		panel_hoy.add(Recomendaciones_fondo);
		
		
		JPanel panel_horas = new JPanel();
		tabbedPane.addTab("New tab", null, panel_horas, null);
		panel_horas.setLayout(null);
		
		JLabel hor0 = new JLabel("00:00");
		hor0.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0.setBounds(10, 11, 79, 14);
		panel_horas.add(hor0);
		
		JLabel hor0_1 = new JLabel("01:00");
		hor0_1.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_1.setBounds(10, 36, 79, 14);
		panel_horas.add(hor0_1);
		
		JLabel hor0_2 = new JLabel("02:00");
		hor0_2.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_2.setBounds(10, 61, 79, 14);
		panel_horas.add(hor0_2);
		
		JLabel hor0_3 = new JLabel("03:00");
		hor0_3.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_3.setBounds(10, 86, 79, 14);
		panel_horas.add(hor0_3);
		
		JLabel hor0_4 = new JLabel("04:00");
		hor0_4.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_4.setBounds(10, 111, 79, 14);
		panel_horas.add(hor0_4);
		
		JLabel hor0_5 = new JLabel("05:00");
		hor0_5.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_5.setBounds(10, 136, 79, 14);
		panel_horas.add(hor0_5);
		
		JLabel hor0_6 = new JLabel("06:00");
		hor0_6.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_6.setBounds(10, 161, 79, 14);
		panel_horas.add(hor0_6);
		
		JLabel hor0_7 = new JLabel("07:00");
		hor0_7.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_7.setBounds(10, 186, 79, 14);
		panel_horas.add(hor0_7);
		
		JLabel hor0_8 = new JLabel("08:00");
		hor0_8.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_8.setBounds(10, 211, 79, 14);
		panel_horas.add(hor0_8);
		
		JLabel hor0_9 = new JLabel("09:00");
		hor0_9.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_9.setBounds(10, 236, 79, 14);
		panel_horas.add(hor0_9);
		
		JLabel hor0_10 = new JLabel("09:00");
		hor0_10.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_10.setBounds(10, 261, 79, 14);
		panel_horas.add(hor0_10);
		
		JLabel hor0_11 = new JLabel("10:00");
		hor0_11.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_11.setBounds(10, 286, 79, 14);
		panel_horas.add(hor0_11);
		
		JLabel hor0_12 = new JLabel("11:00");
		hor0_12.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_12.setBounds(10, 311, 79, 14);
		panel_horas.add(hor0_12);
		
		JLabel hor0_13 = new JLabel("12:00");
		hor0_13.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_13.setBounds(10, 336, 79, 14);
		panel_horas.add(hor0_13);
		
		JLabel hor0_14 = new JLabel("13:00");
		hor0_14.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_14.setBounds(243, 11, 79, 14);
		panel_horas.add(hor0_14);
		
		JLabel hor0_15 = new JLabel("14:00");
		hor0_15.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_15.setBounds(243, 36, 79, 14);
		panel_horas.add(hor0_15);
		
		JLabel hor0_16 = new JLabel("15:00");
		hor0_16.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_16.setBounds(243, 61, 79, 14);
		panel_horas.add(hor0_16);
		
		JLabel hor0_17 = new JLabel("16:00");
		hor0_17.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_17.setBounds(243, 86, 79, 14);
		panel_horas.add(hor0_17);
		
		JLabel hor0_18 = new JLabel("17:00");
		hor0_18.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_18.setBounds(243, 111, 79, 14);
		panel_horas.add(hor0_18);
		
		JLabel hor0_19 = new JLabel("18:00");
		hor0_19.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_19.setBounds(243, 136, 79, 14);
		panel_horas.add(hor0_19);
		
		JLabel hor0_20 = new JLabel("19:00");
		hor0_20.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_20.setBounds(243, 161, 79, 14);
		panel_horas.add(hor0_20);
		
		JLabel hor0_21 = new JLabel("20:00");
		hor0_21.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_21.setBounds(243, 186, 79, 14);
		panel_horas.add(hor0_21);
		
		JLabel hor0_22 = new JLabel("21:00");
		hor0_22.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_22.setBounds(243, 211, 79, 14);
		panel_horas.add(hor0_22);
		
		JLabel hor0_23 = new JLabel("22:00");
		hor0_23.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_23.setBounds(243, 236, 79, 14);
		panel_horas.add(hor0_23);
		
		JLabel hor0_24 = new JLabel("23:00");
		hor0_24.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_24.setBounds(243, 261, 79, 14);
		panel_horas.add(hor0_24);
		
		JLabel temp_hor_0 = new JLabel("New label");
		temp_hor_0.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0.setBounds(99, 11, 79, 14);
		panel_horas.add(temp_hor_0);
		
		JLabel temp_hor_0_1 = new JLabel("New label");
		temp_hor_0_1.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0_1.setBounds(99, 36, 79, 14);
		panel_horas.add(temp_hor_0_1);
		
		JLabel temp_hor_0_2 = new JLabel("New label");
		temp_hor_0_2.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0_2.setBounds(99, 61, 79, 14);
		panel_horas.add(temp_hor_0_2);
		
		JLabel temp_hor_0_3 = new JLabel("New label");
		temp_hor_0_3.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0_3.setBounds(99, 86, 79, 14);
		panel_horas.add(temp_hor_0_3);
		
		JLabel temp_hor_0_4 = new JLabel("New label");
		temp_hor_0_4.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0_4.setBounds(99, 111, 79, 14);
		panel_horas.add(temp_hor_0_4);
		
		JLabel temp_hor_0_5 = new JLabel("New label");
		temp_hor_0_5.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0_5.setBounds(99, 136, 79, 14);
		panel_horas.add(temp_hor_0_5);
		
		JLabel temp_hor_0_6 = new JLabel("New label");
		temp_hor_0_6.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0_6.setBounds(99, 161, 79, 14);
		panel_horas.add(temp_hor_0_6);
		
		JLabel temp_hor_0_7 = new JLabel("New label");
		temp_hor_0_7.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0_7.setBounds(99, 186, 79, 14);
		panel_horas.add(temp_hor_0_7);
		
		JLabel temp_hor_0_8 = new JLabel("New label");
		temp_hor_0_8.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0_8.setBounds(99, 211, 79, 14);
		panel_horas.add(temp_hor_0_8);
		
		JLabel temp_hor_0_9 = new JLabel("New label");
		temp_hor_0_9.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0_9.setBounds(99, 236, 79, 14);
		panel_horas.add(temp_hor_0_9);
		
		JLabel temp_hor_0_10 = new JLabel("New label");
		temp_hor_0_10.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0_10.setBounds(99, 261, 79, 14);
		panel_horas.add(temp_hor_0_10);
		
		JLabel temp_hor_0_11 = new JLabel("New label");
		temp_hor_0_11.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0_11.setBounds(99, 286, 79, 14);
		panel_horas.add(temp_hor_0_11);
		
		JLabel temp_hor_0_12 = new JLabel("New label");
		temp_hor_0_12.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0_12.setBounds(99, 311, 79, 14);
		panel_horas.add(temp_hor_0_12);
		
		JLabel temp_hor_0_13 = new JLabel("New label");
		temp_hor_0_13.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0_13.setBounds(99, 336, 79, 14);
		panel_horas.add(temp_hor_0_13);
		
		JLabel temp_hor_0_13_1 = new JLabel("New label");
		temp_hor_0_13_1.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0_13_1.setBounds(332, 11, 79, 14);
		panel_horas.add(temp_hor_0_13_1);
		
		JLabel temp_hor_0_13_1_1 = new JLabel("New label");
		temp_hor_0_13_1_1.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0_13_1_1.setBounds(332, 36, 79, 14);
		panel_horas.add(temp_hor_0_13_1_1);
		
		JLabel temp_hor_0_13_1_2 = new JLabel("New label");
		temp_hor_0_13_1_2.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0_13_1_2.setBounds(332, 61, 79, 14);
		panel_horas.add(temp_hor_0_13_1_2);
		
		JLabel temp_hor_0_13_1_3 = new JLabel("New label");
		temp_hor_0_13_1_3.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0_13_1_3.setBounds(332, 86, 79, 14);
		panel_horas.add(temp_hor_0_13_1_3);
		
		JLabel temp_hor_0_13_1_4 = new JLabel("New label");
		temp_hor_0_13_1_4.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0_13_1_4.setBounds(332, 111, 79, 14);
		panel_horas.add(temp_hor_0_13_1_4);
		
		JLabel temp_hor_0_13_1_5 = new JLabel("New label");
		temp_hor_0_13_1_5.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0_13_1_5.setBounds(332, 136, 79, 14);
		panel_horas.add(temp_hor_0_13_1_5);
		
		JLabel temp_hor_0_14 = new JLabel("New label");
		temp_hor_0_14.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0_14.setBounds(332, 161, 79, 14);
		panel_horas.add(temp_hor_0_14);
		
		JLabel temp_hor_0_15 = new JLabel("New label");
		temp_hor_0_15.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0_15.setBounds(332, 186, 79, 14);
		panel_horas.add(temp_hor_0_15);
		
		JLabel temp_hor_0_16 = new JLabel("New label");
		temp_hor_0_16.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0_16.setBounds(332, 211, 79, 14);
		panel_horas.add(temp_hor_0_16);
		
		JLabel temp_hor_0_17 = new JLabel("New label");
		temp_hor_0_17.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0_17.setBounds(332, 236, 79, 14);
		panel_horas.add(temp_hor_0_17);
		
		JLabel temp_hor_0_18 = new JLabel("New label");
		temp_hor_0_18.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0_18.setBounds(332, 261, 79, 14);
		panel_horas.add(temp_hor_0_18);
		
		JPanel panel_semanal = new JPanel();
		tabbedPane.addTab("New tab", null, panel_semanal, null);
		panel_semanal.setLayout(null);
		
		JLabel fecha1 = new JLabel("New label");
		fecha1.setFont(new Font("Dubai", Font.BOLD, 12));
		fecha1.setBounds(10, 11, 70, 14);
		panel_semanal.add(fecha1);
		
		JLabel fecha2 = new JLabel("New label");
		fecha2.setFont(new Font("Dubai", Font.BOLD, 12));
		fecha2.setBounds(10, 73, 70, 14);
		panel_semanal.add(fecha2);
		
		JLabel fecha3 = new JLabel("New label");
		fecha3.setFont(new Font("Dubai", Font.BOLD, 12));
		fecha3.setBounds(10, 135, 70, 14);
		panel_semanal.add(fecha3);
		
		JLabel fecha4 = new JLabel("New label");
		fecha4.setFont(new Font("Dubai", Font.BOLD, 12));
		fecha4.setBounds(10, 197, 70, 14);
		panel_semanal.add(fecha4);
		
		JLabel fecha5 = new JLabel("New label");
		fecha5.setFont(new Font("Dubai", Font.BOLD, 12));
		fecha5.setBounds(10, 259, 70, 14);
		panel_semanal.add(fecha5);
		
		JLabel fecha6 = new JLabel("New label");
		fecha6.setFont(new Font("Dubai", Font.BOLD, 12));
		fecha6.setBounds(10, 321, 70, 14);
		panel_semanal.add(fecha6);
		
		JLabel max_temperatura_semanal1 = new JLabel("40 ºC");
		max_temperatura_semanal1.setFont(new Font("Dubai", Font.BOLD, 14));
		max_temperatura_semanal1.setBounds(317, 11, 46, 14);
		panel_semanal.add(max_temperatura_semanal1);
		
		JLabel max_temperatura_semanal2 = new JLabel("40 ºC");
		max_temperatura_semanal2.setFont(new Font("Dubai", Font.BOLD, 14));
		max_temperatura_semanal2.setBounds(317, 73, 46, 14);
		panel_semanal.add(max_temperatura_semanal2);
		
		JLabel max_temperatura_semanal3 = new JLabel("40 ºC");
		max_temperatura_semanal3.setFont(new Font("Dubai", Font.BOLD, 14));
		max_temperatura_semanal3.setBounds(317, 135, 46, 14);
		panel_semanal.add(max_temperatura_semanal3);
		
		JLabel max_temperatura_semanal4 = new JLabel("40 ºC");
		max_temperatura_semanal4.setFont(new Font("Dubai", Font.BOLD, 14));
		max_temperatura_semanal4.setBounds(317, 197, 46, 14);
		panel_semanal.add(max_temperatura_semanal4);
		
		JLabel max_temperatura_semanal5 = new JLabel("40 ºC");
		max_temperatura_semanal5.setFont(new Font("Dubai", Font.BOLD, 14));
		max_temperatura_semanal5.setBounds(317, 259, 46, 14);
		panel_semanal.add(max_temperatura_semanal5);
		
		JLabel max_temperatura_semanal6 = new JLabel("40 ºC");
		max_temperatura_semanal6.setFont(new Font("Dubai", Font.BOLD, 14));
		max_temperatura_semanal6.setBounds(317, 321, 46, 14);
		panel_semanal.add(max_temperatura_semanal6);
		
		JLabel min_temperatura_1 = new JLabel("10 ºC");
		min_temperatura_1.setFont(new Font("Dubai", Font.BOLD, 14));
		min_temperatura_1.setBounds(317, 36, 46, 14);
		panel_semanal.add(min_temperatura_1);
		
		JLabel min_temperatura_1_1 = new JLabel("10 ºC");
		min_temperatura_1_1.setFont(new Font("Dubai", Font.BOLD, 14));
		min_temperatura_1_1.setBounds(317, 98, 46, 14);
		panel_semanal.add(min_temperatura_1_1);
		
		JLabel min_temperatura_1_2 = new JLabel("10 ºC");
		min_temperatura_1_2.setFont(new Font("Dubai", Font.BOLD, 14));
		min_temperatura_1_2.setBounds(317, 160, 46, 14);
		panel_semanal.add(min_temperatura_1_2);
		
		JLabel min_temperatura_1_3 = new JLabel("10 ºC");
		min_temperatura_1_3.setFont(new Font("Dubai", Font.BOLD, 14));
		min_temperatura_1_3.setBounds(317, 222, 46, 14);
		panel_semanal.add(min_temperatura_1_3);
		
		JLabel min_temperatura_1_4 = new JLabel("10 ºC");
		min_temperatura_1_4.setFont(new Font("Dubai", Font.BOLD, 14));
		min_temperatura_1_4.setBounds(317, 284, 46, 14);
		panel_semanal.add(min_temperatura_1_4);
		
		JLabel min_temperatura_1_5 = new JLabel("10 ºC");
		min_temperatura_1_5.setFont(new Font("Dubai", Font.BOLD, 14));
		min_temperatura_1_5.setBounds(317, 346, 46, 14);
		panel_semanal.add(min_temperatura_1_5);
		
		JLabel semanal_max1 = new JLabel("MAX");
		semanal_max1.setFont(new Font("Dubai", Font.BOLD, 14));
		semanal_max1.setBounds(373, 11, 46, 14);
		panel_semanal.add(semanal_max1);
		
		JLabel semanal_max2 = new JLabel("MAX");
		semanal_max2.setFont(new Font("Dubai", Font.BOLD, 14));
		semanal_max2.setBounds(373, 73, 46, 14);
		panel_semanal.add(semanal_max2);
		
		JLabel semanal_max3 = new JLabel("MAX");
		semanal_max3.setFont(new Font("Dubai", Font.BOLD, 14));
		semanal_max3.setBounds(373, 135, 46, 14);
		panel_semanal.add(semanal_max3);
		
		JLabel semanal_max4 = new JLabel("MAX");
		semanal_max4.setFont(new Font("Dubai", Font.BOLD, 14));
		semanal_max4.setBounds(373, 197, 46, 14);
		panel_semanal.add(semanal_max4);
		
		JLabel semanal_max5 = new JLabel("MAX");
		semanal_max5.setFont(new Font("Dubai", Font.BOLD, 14));
		semanal_max5.setBounds(373, 259, 46, 14);
		panel_semanal.add(semanal_max5);
		
		JLabel semanal_max6 = new JLabel("MAX");
		semanal_max6.setFont(new Font("Dubai", Font.BOLD, 14));
		semanal_max6.setBounds(373, 321, 46, 14);
		panel_semanal.add(semanal_max6);
		
		JLabel semanal_min1 = new JLabel("MIN");
		semanal_min1.setFont(new Font("Dubai", Font.BOLD, 14));
		semanal_min1.setBounds(373, 36, 46, 14);
		panel_semanal.add(semanal_min1);
		
		JLabel semanal_min2 = new JLabel("MIN");
		semanal_min2.setFont(new Font("Dubai", Font.BOLD, 14));
		semanal_min2.setBounds(373, 98, 46, 14);
		panel_semanal.add(semanal_min2);
		
		JLabel semanal_min3 = new JLabel("MIN");
		semanal_min3.setFont(new Font("Dubai", Font.BOLD, 14));
		semanal_min3.setBounds(373, 160, 46, 14);
		panel_semanal.add(semanal_min3);
		
		JLabel semanal_min4 = new JLabel("MIN");
		semanal_min4.setFont(new Font("Dubai", Font.BOLD, 14));
		semanal_min4.setBounds(373, 222, 46, 14);
		panel_semanal.add(semanal_min4);
		
		JLabel semanal_min5 = new JLabel("MIN");
		semanal_min5.setFont(new Font("Dubai", Font.BOLD, 14));
		semanal_min5.setBounds(373, 284, 46, 14);
		panel_semanal.add(semanal_min5);
		
		JLabel semanal_min6 = new JLabel("MIN");
		semanal_min6.setFont(new Font("Dubai", Font.BOLD, 14));
		semanal_min6.setBounds(373, 346, 46, 14);
		panel_semanal.add(semanal_min6);
		
		
		JLabel fondo_tiempo0 = new JLabel(new ImageIcon("Imagenes/cuadrado.png"));
		fondo_tiempo0.setBounds(10, 11, 409, 51);
		panel_semanal.add(fondo_tiempo0);
		
		JLabel fondo_tiempo1 = new JLabel(new ImageIcon("Imagenes/cuadrado.png"));
		fondo_tiempo1.setBounds(10, 73, 409, 51);
		panel_semanal.add(fondo_tiempo1);
		
		JLabel fondo_tiempo2 = new JLabel(new ImageIcon("Imagenes/cuadrado.png"));
		fondo_tiempo2.setBounds(10, 135, 409, 51);
		panel_semanal.add(fondo_tiempo2);
		
		JLabel fondo_tiempo3 = new JLabel(new ImageIcon("Imagenes/cuadrado.png"));
		fondo_tiempo3.setBounds(10, 197, 409, 51);
		panel_semanal.add(fondo_tiempo3);
		
		JLabel fondo_tiempo4 = new JLabel(new ImageIcon("Imagenes/cuadrado.png"));
		fondo_tiempo4.setBounds(10, 259, 409, 51);
		panel_semanal.add(fondo_tiempo4);
		
		JLabel fondo_tiempo5 = new JLabel(new ImageIcon("Imagenes/cuadrado.png"));
		fondo_tiempo5.setBounds(10, 321, 409, 51);
		panel_semanal.add(fondo_tiempo5);
	}
}