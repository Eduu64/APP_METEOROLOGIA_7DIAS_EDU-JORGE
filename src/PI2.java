import java.awt.Color;
import java.awt.Font;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.time.LocalDateTime;

public class PI2 {


	// Función que realiza la llamada a la API y devuelve la respuesta
	public static String llamada() {
		String respuesta="";
		Date dia = new Date();
		Date dia_final = new Date();

		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

		//fecha+7
		Calendar c = Calendar.getInstance();
		c.setTime(dia_final);
		c.add(Calendar.DATE, 7);
		dia_final = c.getTime();


		String fecha_actual = ft.format(dia);
		String fecha_fin = ft.format(dia_final);


		//System.out.println(fecha_actual);
		//System.out.println(fecha_fin);

		double lat=40.4205122;
		double lon=-3.6641961;
		try {
			URL url = new URL("https://api.open-meteo.com/v1/gfs?latitude="+lat+"&longitude="+lon+"&hourly=temperature_2m&daily=weathercode,temperature_2m_max,temperature_2m_min,precipitation_sum,snowfall_sum,precipitation_hours,windspeed_10m_max&current_weather=true&start_date="+fecha_actual+"&end_date="+fecha_fin+"&timezone=auto");
			HttpURLConnection conexion = (HttpURLConnection) url.openConnection(); //crea objeto de conexion
			conexion.setRequestMethod("GET"); //Metodo de conexion
			conexion.connect(); //conexion

			int respuestaAPI = conexion.getResponseCode();


			if(respuestaAPI != 200) {
				System.out.println("Error");
			}else {
				Scanner p = new Scanner(url.openStream()); //Escanea el output de la API
				respuesta = p.nextLine(); //Guarda output en una variable
				p.close();
			}

		}catch(Exception e) {
			System.out.println(e);
		}

		return respuesta;
	}

	// Función que procesa la respuesta de la API y obtiene la información de temperatura para los días requeridos
	public static void desfragmentacion(String info, JSONObject ob, JPanel panel_hoy, JPanel panel_horas, JPanel panel_semanal) {

		JLabel Imagen_Tiempo = new JLabel();
		Imagen_Tiempo.setBounds(313, 28, 80, 80);
		panel_hoy.add(Imagen_Tiempo);

		JLabel Temperatura_hor = new JLabel();
		Temperatura_hor.setFont(new Font("Dubai", Font.BOLD, 28));
		Temperatura_hor.setBounds(20, 28, 85, 80);
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


		JLabel Info_viento = new JLabel();
		Info_viento.setFont(new Font("Dubai", Font.BOLD, 13));
		Info_viento.setBounds(25, 190, 54, 30);
		panel_hoy.add(Info_viento);


		JLabel Info_prob_lluvia = new JLabel();
		Info_prob_lluvia.setFont(new Font("Dubai", Font.BOLD, 13));
		Info_prob_lluvia.setBounds(133, 190, 50, 30);
		panel_hoy.add(Info_prob_lluvia);
	
		
		JLabel Info_nieve = new JLabel();
		Info_nieve.setText("0.0");
		Info_nieve.setFont(new Font("Dubai", Font.BOLD, 13));
		Info_nieve.setBounds(237, 190, 50, 30);
		panel_hoy.add(Info_nieve);

		JLabel Info_hora_lluvia = new JLabel();
		Info_hora_lluvia.setFont(new Font("Dubai", Font.BOLD, 13));
		Info_hora_lluvia.setBounds(340, 190, 45, 30);
		panel_hoy.add(Info_hora_lluvia);


		JLabel max_temperatura = new JLabel();
		max_temperatura.setFont(new Font("Dubai", Font.BOLD, 14));
		max_temperatura.setBounds(183, 45, 46, 14);
		panel_hoy.add(max_temperatura);

		JLabel min_temperatura = new JLabel();
		min_temperatura.setFont(new Font("Dubai", Font.BOLD, 14));
		min_temperatura.setBounds(183, 80, 46, 14);
		panel_hoy.add(min_temperatura);

		JLabel Descripcion_tiempo = new JLabel("Despejado");
		Descripcion_tiempo.setHorizontalAlignment(SwingConstants.CENTER);
		Descripcion_tiempo.setFont(new Font("Dubai", Font.BOLD, 16));
		Descripcion_tiempo.setBounds(274, 105, 145, 14);
		panel_hoy.add(Descripcion_tiempo);
		
		JLabel temp_hor_0 = new JLabel("New label");
		temp_hor_0.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0.setBounds(99, 11, 79, 14);
		panel_horas.add(temp_hor_0);

		JLabel temp_hor_1 = new JLabel("New label");
		temp_hor_1.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_1.setBounds(99, 36, 79, 14);
		panel_horas.add(temp_hor_1);

		JLabel temp_hor_2 = new JLabel("New label");
		temp_hor_2.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_2.setBounds(99, 61, 79, 14);
		panel_horas.add(temp_hor_2);

		JLabel temp_hor_3 = new JLabel("New label");
		temp_hor_3.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_3.setBounds(99, 86, 79, 14);
		panel_horas.add(temp_hor_3);

		JLabel temp_hor_4 = new JLabel("New label");
		temp_hor_4.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_4.setBounds(99, 111, 79, 14);
		panel_horas.add(temp_hor_4);

		JLabel temp_hor_5 = new JLabel("New label");
		temp_hor_5.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_5.setBounds(99, 136, 79, 14);
		panel_horas.add(temp_hor_5);

		JLabel temp_hor_6 = new JLabel("New label");
		temp_hor_6.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_6.setBounds(99, 161, 79, 14);
		panel_horas.add(temp_hor_6);

		JLabel temp_hor_7 = new JLabel("New label");
		temp_hor_7.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_7.setBounds(99, 186, 79, 14);
		panel_horas.add(temp_hor_7);

		JLabel temp_hor_8 = new JLabel("New label");
		temp_hor_8.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_8.setBounds(99, 211, 79, 14);
		panel_horas.add(temp_hor_8);

		JLabel temp_hor_9 = new JLabel("New label");
		temp_hor_9.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_9.setBounds(99, 236, 79, 14);
		panel_horas.add(temp_hor_9);

		JLabel temp_hor_10 = new JLabel("New label");
		temp_hor_10.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_10.setBounds(99, 261, 79, 14);
		panel_horas.add(temp_hor_10);

		JLabel temp_hor_11 = new JLabel("New label");
		temp_hor_11.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_11.setBounds(99, 286, 79, 14);
		panel_horas.add(temp_hor_11);

		JLabel temp_hor_12 = new JLabel("New label");
		temp_hor_12.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_12.setBounds(99, 311, 79, 14);
		panel_horas.add(temp_hor_12);

		JLabel temp_hor_13 = new JLabel("New label");
		temp_hor_13.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_13.setBounds(99, 336, 79, 14);
		panel_horas.add(temp_hor_13);

		JLabel temp_hor_14 = new JLabel("New label");
		temp_hor_14.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_14.setBounds(332, 11, 79, 14);
		panel_horas.add(temp_hor_14);

		JLabel temp_hor_15 = new JLabel("New label");
		temp_hor_15.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_15.setBounds(332, 36, 79, 14);
		panel_horas.add(temp_hor_15);

		JLabel temp_hor_16 = new JLabel("New label");
		temp_hor_16.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_16.setBounds(332, 61, 79, 14);
		panel_horas.add(temp_hor_16);

		JLabel temp_hor_17 = new JLabel("New label");
		temp_hor_17.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_17.setBounds(332, 86, 79, 14);
		panel_horas.add(temp_hor_17);

		JLabel temp_hor_18 = new JLabel("New label");
		temp_hor_18.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_18.setBounds(332, 111, 79, 14);
		panel_horas.add(temp_hor_18);

		JLabel temp_hor_19 = new JLabel("New label");
		temp_hor_19.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_19.setBounds(332, 136, 79, 14);
		panel_horas.add(temp_hor_19);

		JLabel temp_hor_20 = new JLabel("New label");
		temp_hor_20.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_20.setBounds(332, 161, 79, 14);
		panel_horas.add(temp_hor_20);

		JLabel temp_hor_21 = new JLabel("New label");
		temp_hor_21.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_21.setBounds(332, 186, 79, 14);
		panel_horas.add(temp_hor_21);

		JLabel temp_hor_22 = new JLabel("New label");
		temp_hor_22.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_22.setBounds(332, 211, 79, 14);
		panel_horas.add(temp_hor_22);

		JLabel temp_hor_23 = new JLabel("New label");
		temp_hor_23.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_23.setBounds(332, 236, 79, 14);
		panel_horas.add(temp_hor_23);

		JLabel temp_hor_24 = new JLabel("New label");
		temp_hor_24.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_24.setBounds(332, 261, 79, 14);
		panel_horas.add(temp_hor_24);
		
		JLabel fecha1 = new JLabel("New label");
		fecha1.setFont(new Font("Dubai", Font.BOLD, 12));
		fecha1.setBounds(10, 11, 130, 14);
		panel_semanal.add(fecha1);

		JLabel fecha2 = new JLabel("New label");
		fecha2.setFont(new Font("Dubai", Font.BOLD, 12));
		fecha2.setBounds(10, 73, 130, 14);
		panel_semanal.add(fecha2);

		JLabel fecha3 = new JLabel("New label");
		fecha3.setFont(new Font("Dubai", Font.BOLD, 12));
		fecha3.setBounds(10, 135, 130, 14);
		panel_semanal.add(fecha3);

		JLabel fecha4 = new JLabel("New label");
		fecha4.setFont(new Font("Dubai", Font.BOLD, 12));
		fecha4.setBounds(10, 197, 130, 14);
		panel_semanal.add(fecha4);

		JLabel fecha5 = new JLabel("New label");
		fecha5.setFont(new Font("Dubai", Font.BOLD, 12));
		fecha5.setBounds(10, 259, 130, 14);
		panel_semanal.add(fecha5);

		JLabel fecha6 = new JLabel("New label");
		fecha6.setFont(new Font("Dubai", Font.BOLD, 12));
		fecha6.setBounds(10, 321, 130, 14);
		panel_semanal.add(fecha6);

		JLabel max_temperatura_semanal1 = new JLabel();
		max_temperatura_semanal1.setFont(new Font("Dubai", Font.BOLD, 14));
		max_temperatura_semanal1.setBounds(317, 11, 46, 14);
		panel_semanal.add(max_temperatura_semanal1);

		JLabel max_temperatura_semanal2 = new JLabel();
		max_temperatura_semanal2.setFont(new Font("Dubai", Font.BOLD, 14));
		max_temperatura_semanal2.setBounds(317, 73, 46, 14);
		panel_semanal.add(max_temperatura_semanal2);

		JLabel max_temperatura_semanal3 = new JLabel();
		max_temperatura_semanal3.setFont(new Font("Dubai", Font.BOLD, 14));
		max_temperatura_semanal3.setBounds(317, 135, 46, 14);
		panel_semanal.add(max_temperatura_semanal3);

		JLabel max_temperatura_semanal4 = new JLabel();
		max_temperatura_semanal4.setFont(new Font("Dubai", Font.BOLD, 14));
		max_temperatura_semanal4.setBounds(317, 197, 46, 14);
		panel_semanal.add(max_temperatura_semanal4);

		JLabel max_temperatura_semanal5 = new JLabel();
		max_temperatura_semanal5.setFont(new Font("Dubai", Font.BOLD, 14));
		max_temperatura_semanal5.setBounds(317, 259, 46, 14);
		panel_semanal.add(max_temperatura_semanal5);

		JLabel max_temperatura_semanal6 = new JLabel();
		max_temperatura_semanal6.setFont(new Font("Dubai", Font.BOLD, 14));
		max_temperatura_semanal6.setBounds(317, 321, 46, 14);
		panel_semanal.add(max_temperatura_semanal6);

		JLabel min_temperatura_semanal1 = new JLabel();
		min_temperatura_semanal1.setFont(new Font("Dubai", Font.BOLD, 14));
		min_temperatura_semanal1.setBounds(317, 36, 46, 14);
		panel_semanal.add(min_temperatura_semanal1);

		JLabel min_temperatura_semanal2 = new JLabel();
		min_temperatura_semanal2.setFont(new Font("Dubai", Font.BOLD, 14));
		min_temperatura_semanal2.setBounds(317, 98, 46, 14);
		panel_semanal.add(min_temperatura_semanal2);

		JLabel min_temperatura_semanal3 = new JLabel();
		min_temperatura_semanal3.setFont(new Font("Dubai", Font.BOLD, 14));
		min_temperatura_semanal3.setBounds(317, 160, 46, 14);
		panel_semanal.add(min_temperatura_semanal3);

		JLabel min_temperatura_semanal4 = new JLabel();
		min_temperatura_semanal4.setFont(new Font("Dubai", Font.BOLD, 14));
		min_temperatura_semanal4.setBounds(317, 222, 46, 14);
		panel_semanal.add(min_temperatura_semanal4);

		JLabel min_temperatura_semanal5 = new JLabel();
		min_temperatura_semanal5.setFont(new Font("Dubai", Font.BOLD, 14));
		min_temperatura_semanal5.setBounds(317, 284, 46, 14);
		panel_semanal.add(min_temperatura_semanal5);

		JLabel min_temperatura_semanal6 = new JLabel();
		min_temperatura_semanal6.setFont(new Font("Dubai", Font.BOLD, 14));
		min_temperatura_semanal6.setBounds(317, 346, 46, 14);
		panel_semanal.add(min_temperatura_semanal6);


		
		
		SimpleDateFormat hr = new SimpleDateFormat("yyyy-MM-dd HH:00");
		SimpleDateFormat di = new SimpleDateFormat("EEEE yyyy-MM-dd");

		double [] temperatura = new double[192];
		int cod[] = new int[7];
		double temperatura_max[]=new double[7];	
		double temperatura_min[]=new double[7];	
		double lluv[]=new double[7];
		double hor_lluv[]=new double[7];
		double niev[]=new double[7];	
		double vient[]=new double[7];


		String [] fecha = new String[192];

		JSONArray dias = ob.getJSONObject("hourly").getJSONArray("time");
		JSONArray temp = ob.getJSONObject("hourly").getJSONArray("temperature_2m");
		JSONArray codigo = ob.getJSONObject("daily").getJSONArray("weathercode");
		JSONArray temp_max = ob.getJSONObject("daily").getJSONArray("temperature_2m_max");
		JSONArray temp_min = ob.getJSONObject("daily").getJSONArray("temperature_2m_min");
		JSONArray lluvia = ob.getJSONObject("daily").getJSONArray("precipitation_sum");
		JSONArray hora_lluvia = ob.getJSONObject("daily").getJSONArray("precipitation_hours");
		JSONArray nieve = ob.getJSONObject("daily").getJSONArray("snowfall_sum");
		JSONArray viento = ob.getJSONObject("daily").getJSONArray("windspeed_10m_max");

		for (int i = 0; i < dias.length(); i++) {

			temperatura[i]= temp.getDouble(i);
			fecha[i]=dias.getString(i).replace("T"," ");

		}

		Calendar c = Calendar.getInstance();
		String []dias_semana = new String[7];
		String []dias_semana_sin_horas = new String[7];

		for(int z=0;z<=6;z++) {
			Date dia = new Date();
			c.setTime(dia);
			c.add(Calendar.DATE, z);
			dia = c.getTime();
			dias_semana[z]=hr.format(dia);
			dias_semana_sin_horas[z]=di.format(dia);
		}

		for(int i = 0;i<=6;i++) {
			cod[i]=codigo.getInt(i);
			temperatura_max[i]=temp_max.getDouble(i);
			temperatura_min[i]=temp_min.getDouble(i);
			lluv[i]=lluvia.getDouble(i);
			hor_lluv[i]=hora_lluvia.getDouble(i);
			niev[i]=nieve.getDouble(i);
			vient[i]=viento.getDouble(i);
		}


		for (int i = 0; i < dias.length(); i++) {

			if(fecha[i].equals(dias_semana[0])) {
				Temperatura_hor.setText(String.valueOf(temperatura[i])+ " ºC");
				max_temperatura.setText(String.valueOf(temperatura_max[0])+ " ºC");
				min_temperatura.setText(String.valueOf(temperatura_min[0])+ " ºC");
				Info_viento.setText(String.valueOf(vient[0])+ " km/h");
				Info_prob_lluvia.setText(String.valueOf(lluv[0])+ " mm");
				Info_hora_lluvia.setText(String.valueOf(hor_lluv[0])+ " h");
				Info_nieve.setText(String.valueOf(niev[0]) + " cm");
			}
		}//fin del bucle
		
		temp_hor_0.setText(String.valueOf(temperatura[0])+ " ºC");
		temp_hor_1.setText(String.valueOf(temperatura[1])+ " ºC");
		temp_hor_2.setText(String.valueOf(temperatura[2])+ " ºC");
		temp_hor_3.setText(String.valueOf(temperatura[3])+ " ºC");
		temp_hor_4.setText(String.valueOf(temperatura[4])+ " ºC");
		temp_hor_5.setText(String.valueOf(temperatura[5])+ " ºC");
		temp_hor_6.setText(String.valueOf(temperatura[6])+ " ºC");
		temp_hor_7.setText(String.valueOf(temperatura[7])+ " ºC");
		temp_hor_8.setText(String.valueOf(temperatura[8])+ " ºC");
		temp_hor_9.setText(String.valueOf(temperatura[9])+ " ºC");
		temp_hor_10.setText(String.valueOf(temperatura[10])+ " ºC");
		temp_hor_11.setText(String.valueOf(temperatura[11])+ " ºC");
		temp_hor_12.setText(String.valueOf(temperatura[12])+ " ºC");
		temp_hor_13.setText(String.valueOf(temperatura[13])+ " ºC");
		temp_hor_14.setText(String.valueOf(temperatura[14])+ " ºC");
		temp_hor_15.setText(String.valueOf(temperatura[15])+ " ºC");
		temp_hor_16.setText(String.valueOf(temperatura[16])+ " ºC");
		temp_hor_17.setText(String.valueOf(temperatura[17])+ " ºC");
		temp_hor_18.setText(String.valueOf(temperatura[18])+ " ºC");
		temp_hor_19.setText(String.valueOf(temperatura[19])+ " ºC");
		temp_hor_20.setText(String.valueOf(temperatura[20])+ " ºC");
		temp_hor_21.setText(String.valueOf(temperatura[21])+ " ºC");
		temp_hor_22.setText(String.valueOf(temperatura[22])+ " ºC");
		temp_hor_23.setText(String.valueOf(temperatura[23])+ " ºC");
		temp_hor_24.setText(String.valueOf(temperatura[24])+ " ºC");
		
		fecha1.setText(dias_semana_sin_horas[1]);
		fecha2.setText(dias_semana_sin_horas[2]);
		fecha3.setText(dias_semana_sin_horas[3]);
		fecha4.setText(dias_semana_sin_horas[4]);
		fecha5.setText(dias_semana_sin_horas[5]);
		fecha6.setText(dias_semana_sin_horas[6]);

		max_temperatura_semanal1.setText(String.valueOf(temperatura_max[1]));
		max_temperatura_semanal2.setText(String.valueOf(temperatura_max[2]));
		max_temperatura_semanal3.setText(String.valueOf(temperatura_max[3]));
		max_temperatura_semanal4.setText(String.valueOf(temperatura_max[4]));
		max_temperatura_semanal5.setText(String.valueOf(temperatura_max[5]));
		max_temperatura_semanal6.setText(String.valueOf(temperatura_max[6]));
		
		min_temperatura_semanal1.setText(String.valueOf(temperatura_min[1]));
		min_temperatura_semanal2.setText(String.valueOf(temperatura_min[2]));
		min_temperatura_semanal3.setText(String.valueOf(temperatura_min[3]));
		min_temperatura_semanal4.setText(String.valueOf(temperatura_min[4]));
		min_temperatura_semanal5.setText(String.valueOf(temperatura_min[5]));
		min_temperatura_semanal6.setText(String.valueOf(temperatura_min[6]));
















	}



	public static void main(String[] args) {
		System.setProperty("sun.java2d.uiScale", "100%"); // establece la escala de texto,imagenes y APP independientemente de la escala del OS 
		
		JFrame interfaz = new JFrame();

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}catch(Exception e) {
			e.printStackTrace();
		}


		interfaz.setAlwaysOnTop(true);
		interfaz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		interfaz.setBounds(100, 100, 450, 500);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		interfaz.setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 51);
		contentPane.add(panel);
		panel.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 48, 434, 413);
		tabbedPane.setFont(new Font("Dubai", Font.BOLD, 12));
		tabbedPane.setBorder(null); // quita bordes
		tabbedPane.setFocusable(false); // quita bordes
		contentPane.add(tabbedPane);

		JPanel panel_hoy = new JPanel();
		tabbedPane.addTab("HOY", null, panel_hoy, null);
		panel_hoy.setLayout(null);
		
		JPanel panel_horas = new JPanel();
		tabbedPane.addTab("TEMPERATURAS POR HORAS", null, panel_horas, null);
		panel_horas.setLayout(null);
		
		JPanel panel_semanal = new JPanel();
		tabbedPane.addTab("TEMPERATURAS PRÓXIMAS", null, panel_semanal, null);
		panel_semanal.setLayout(null);
		
		//panel 
		
		JLabel Titulo = new JLabel("ClimaTowers");
		Titulo.setFont(new Font("Dubai", Font.BOLD, 28));
		Titulo.setBounds(10, 11, 154, 26);
		panel.add(Titulo);

		//panel_hoy
		
		JLabel Imagen_viento = new JLabel(new ImageIcon("Imagenes/icono_viento.png"));
		Imagen_viento.setBounds(35, 160, 30, 30);
		panel_hoy.add(Imagen_viento);

		JLabel Imagen_prob_lluvia = new JLabel(new ImageIcon("Imagenes/lluvia_icono.png"));
		Imagen_prob_lluvia.setBounds(133, 160, 30, 30);
		panel_hoy.add(Imagen_prob_lluvia);
		
		
		JLabel Imagen_nieve = new JLabel(new ImageIcon("Imagenes/nieve_icono.png"));
		Imagen_nieve.setBounds(233, 160, 30, 30);
		panel_hoy.add(Imagen_nieve);

		JLabel Imagen_hor_lluvia = new JLabel(new ImageIcon("Imagenes/hora_lluvia_icono.png"));
		Imagen_hor_lluvia.setBounds(340, 160, 30, 30);
		panel_hoy.add(Imagen_hor_lluvia);

		JLabel Imagen_maxmin = new JLabel(new ImageIcon("Imagenes/maxmin_icono.png"));
		Imagen_maxmin.setBounds(123, 45, 50, 50);
		panel_hoy.add(Imagen_maxmin);
		
		
		//panel horas

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

		//panel semanal
	

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
		
		//se activan funciones
		
		String informacionAPI = llamada();
		JSONObject ob = new JSONObject(informacionAPI);
		desfragmentacion(informacionAPI,ob, panel_hoy, panel_horas, panel_semanal);
		
		//fondos panel_hoy
		
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
		
		//fondos panel_semanal
		
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
		
		//interfaz visible
		
		interfaz.setVisible(true);
		
		



	}

}
