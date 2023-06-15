import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.FileWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import javax.swing.ImageIcon;
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

//Eduardo Morales Calvo y Jorge Pérez de Dios
//https://github.com/Eduu64/APP_METEOROLOGIA_7DIAS_EDU-JORGE.git
public class PI2 {


	// Función que realiza la llamada a la API y devuelve la respuesta
	public static String llamada() {
		String respuesta="";
		Date dia = new Date(); //Objeto date
		Date dia_final = new Date();//Objeto date

		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");//Formato para objeto date

		//fecha+7 dias para establecer el rango de busqueda de dias (quiero que me diga la meteorología de 7 dias)
		Calendar c = Calendar.getInstance();
		c.setTime(dia_final);
		c.add(Calendar.DATE, 7);
		dia_final = c.getTime();


		String fecha_actual = ft.format(dia); //se establece en la string el dia con el formato especificado
		String fecha_fin = ft.format(dia_final); //se establece en la string el dia con el formato especificado
		//fechas para establecer el rango de fechas en la busqueda en la API

		//System.out.println(fecha_actual);
		//System.out.println(fecha_fin);

		//coordenadas
		double lat=40.4205122;
		double lon=-3.6641961;
		try {

			URL url = new URL("https://api.open-meteo.com/v1/gfs?latitude="+lat+"&longitude="+lon+"&hourly=temperature_2m&daily=weathercode,temperature_2m_max,temperature_2m_min,precipitation_sum,snowfall_sum,precipitation_hours,windspeed_10m_max&current_weather=true&start_date="+fecha_actual+"&end_date="+fecha_fin+"&timezone=auto");
			HttpURLConnection conexion = (HttpURLConnection) url.openConnection(); //crea objeto de conexion
			conexion.setRequestMethod("GET"); //Metodo de conexion
			conexion.connect(); //conexion

			int respuestaAPI = conexion.getResponseCode(); //respuesta de la API


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
	public static void desfragmentacion(String info, JSONObject ob, JPanel panel_hoy, JPanel panel_horas, JPanel panel_semanal){
		//Se establece los componentes de la GUI
		JLabel Imagen_Tiempo = new JLabel();
		Imagen_Tiempo.setBounds(297, 20, 90, 90);
		panel_hoy.add(Imagen_Tiempo);

		JLabel Temperatura_hor = new JLabel();
		Temperatura_hor.setFont(new Font("Dubai", Font.BOLD, 28));
		Temperatura_hor.setBounds(20, 28, 85, 80);
		panel_hoy.add(Temperatura_hor);


		JLabel Recomendaciones = new JLabel("New label");
		Recomendaciones.setFont(new Font("Dubai", Font.PLAIN, 10));
		Recomendaciones.setVerticalAlignment(SwingConstants.TOP);
		Recomendaciones.setBounds(18, 245, 409, 250);
		panel_hoy.add(Recomendaciones);


		JLabel Info_viento = new JLabel();
		Info_viento.setFont(new Font("Dubai", Font.BOLD, 13));
		Info_viento.setBounds(25, 185, 60, 30);
		panel_hoy.add(Info_viento);


		JLabel Info_prob_lluvia = new JLabel();
		Info_prob_lluvia.setFont(new Font("Dubai", Font.BOLD, 13));
		Info_prob_lluvia.setBounds(133, 185, 50, 30);
		panel_hoy.add(Info_prob_lluvia);


		JLabel Info_nieve = new JLabel();
		Info_nieve.setText("0.0");
		Info_nieve.setFont(new Font("Dubai", Font.BOLD, 13));
		Info_nieve.setBounds(237, 185, 50, 30);
		panel_hoy.add(Info_nieve);

		JLabel Info_hora_lluvia = new JLabel();
		Info_hora_lluvia.setFont(new Font("Dubai", Font.BOLD, 13));
		Info_hora_lluvia.setBounds(340, 185, 45, 30);
		panel_hoy.add(Info_hora_lluvia);


		JLabel max_temperatura = new JLabel();
		max_temperatura.setFont(new Font("Dubai", Font.BOLD, 14));
		max_temperatura.setBounds(183, 45, 46, 14);
		panel_hoy.add(max_temperatura);

		JLabel min_temperatura = new JLabel();
		min_temperatura.setFont(new Font("Dubai", Font.BOLD, 14));
		min_temperatura.setBounds(183, 80, 46, 14);
		panel_hoy.add(min_temperatura);

		JLabel Descripcion_tiempo = new JLabel();
		Descripcion_tiempo.setHorizontalAlignment(SwingConstants.CENTER);
		Descripcion_tiempo.setFont(new Font("Dubai", Font.BOLD, 16));
		Descripcion_tiempo.setBounds(270, 105, 145, 14);
		panel_hoy.add(Descripcion_tiempo);

		JLabel temp_hor_0 = new JLabel("New label");
		temp_hor_0.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_0.setBounds(119, 11, 79, 14);
		panel_horas.add(temp_hor_0);

		JLabel temp_hor_1 = new JLabel("New label");
		temp_hor_1.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_1.setBounds(119, 36, 79, 14);
		panel_horas.add(temp_hor_1);

		JLabel temp_hor_2 = new JLabel("New label");
		temp_hor_2.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_2.setBounds(119, 61, 79, 14);
		panel_horas.add(temp_hor_2);

		JLabel temp_hor_3 = new JLabel("New label");
		temp_hor_3.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_3.setBounds(119, 86, 79, 14);
		panel_horas.add(temp_hor_3);

		JLabel temp_hor_4 = new JLabel("New label");
		temp_hor_4.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_4.setBounds(119, 111, 79, 14);
		panel_horas.add(temp_hor_4);

		JLabel temp_hor_5 = new JLabel("New label");
		temp_hor_5.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_5.setBounds(119, 136, 79, 14);
		panel_horas.add(temp_hor_5);

		JLabel temp_hor_6 = new JLabel("New label");
		temp_hor_6.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_6.setBounds(119, 161, 79, 14);
		panel_horas.add(temp_hor_6);

		JLabel temp_hor_7 = new JLabel("New label");
		temp_hor_7.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_7.setBounds(119, 186, 79, 14);
		panel_horas.add(temp_hor_7);

		JLabel temp_hor_8 = new JLabel("New label");
		temp_hor_8.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_8.setBounds(119, 211, 79, 14);
		panel_horas.add(temp_hor_8);

		JLabel temp_hor_9 = new JLabel("New label");
		temp_hor_9.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_9.setBounds(119, 236, 79, 14);
		panel_horas.add(temp_hor_9);

		JLabel temp_hor_10 = new JLabel("New label");
		temp_hor_10.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_10.setBounds(119, 261, 79, 14);
		panel_horas.add(temp_hor_10);

		JLabel temp_hor_11 = new JLabel("New label");
		temp_hor_11.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_11.setBounds(119, 286, 79, 14);
		panel_horas.add(temp_hor_11);

		JLabel temp_hor_12 = new JLabel("New label");
		temp_hor_12.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_12.setBounds(119, 311, 79, 14);
		panel_horas.add(temp_hor_12);

		JLabel temp_hor_13 = new JLabel("New label");
		temp_hor_13.setFont(new Font("Dubai", Font.BOLD, 12));
		temp_hor_13.setBounds(119, 336, 79, 14);
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
		fecha1.setBounds(15, 15, 130, 14);
		panel_semanal.add(fecha1);

		JLabel fecha2 = new JLabel("New label");
		fecha2.setFont(new Font("Dubai", Font.BOLD, 12));
		fecha2.setBounds(15, 76, 130, 14);
		panel_semanal.add(fecha2);

		JLabel fecha3 = new JLabel("New label");
		fecha3.setFont(new Font("Dubai", Font.BOLD, 12));
		fecha3.setBounds(15, 138, 130, 14);
		panel_semanal.add(fecha3);

		JLabel fecha4 = new JLabel("New label");
		fecha4.setFont(new Font("Dubai", Font.BOLD, 12));
		fecha4.setBounds(15, 200, 130, 14);
		panel_semanal.add(fecha4);

		JLabel fecha5 = new JLabel("New label");
		fecha5.setFont(new Font("Dubai", Font.BOLD, 12));
		fecha5.setBounds(15, 262, 130, 14);
		panel_semanal.add(fecha5);

		JLabel fecha6 = new JLabel("New label");
		fecha6.setFont(new Font("Dubai", Font.BOLD, 12));
		fecha6.setBounds(15, 324, 130, 14);
		panel_semanal.add(fecha6);

		JLabel max_temperatura_semanal1 = new JLabel();
		max_temperatura_semanal1.setFont(new Font("Dubai", Font.BOLD, 14));
		max_temperatura_semanal1.setBounds(335, 16, 46, 14);
		panel_semanal.add(max_temperatura_semanal1);

		JLabel max_temperatura_semanal2 = new JLabel();
		max_temperatura_semanal2.setFont(new Font("Dubai", Font.BOLD, 14));
		max_temperatura_semanal2.setBounds(335, 78, 46, 14);
		panel_semanal.add(max_temperatura_semanal2);

		JLabel max_temperatura_semanal3 = new JLabel();
		max_temperatura_semanal3.setFont(new Font("Dubai", Font.BOLD, 14));
		max_temperatura_semanal3.setBounds(335, 140, 46, 14);
		panel_semanal.add(max_temperatura_semanal3);

		JLabel max_temperatura_semanal4 = new JLabel();
		max_temperatura_semanal4.setFont(new Font("Dubai", Font.BOLD, 14));
		max_temperatura_semanal4.setBounds(335, 202, 46, 14);
		panel_semanal.add(max_temperatura_semanal4);

		JLabel max_temperatura_semanal5 = new JLabel();
		max_temperatura_semanal5.setFont(new Font("Dubai", Font.BOLD, 14));
		max_temperatura_semanal5.setBounds(335, 264, 46, 14);
		panel_semanal.add(max_temperatura_semanal5);

		JLabel max_temperatura_semanal6 = new JLabel();
		max_temperatura_semanal6.setFont(new Font("Dubai", Font.BOLD, 14));
		max_temperatura_semanal6.setBounds(335, 326, 46, 14);
		panel_semanal.add(max_temperatura_semanal6);

		JLabel min_temperatura_semanal1 = new JLabel();
		min_temperatura_semanal1.setFont(new Font("Dubai", Font.BOLD, 14));
		min_temperatura_semanal1.setBounds(335, 36, 46, 14);
		panel_semanal.add(min_temperatura_semanal1);

		JLabel min_temperatura_semanal2 = new JLabel();
		min_temperatura_semanal2.setFont(new Font("Dubai", Font.BOLD, 14));
		min_temperatura_semanal2.setBounds(335, 98, 46, 14);
		panel_semanal.add(min_temperatura_semanal2);

		JLabel min_temperatura_semanal3 = new JLabel();
		min_temperatura_semanal3.setFont(new Font("Dubai", Font.BOLD, 14));
		min_temperatura_semanal3.setBounds(335, 160, 46, 14);
		panel_semanal.add(min_temperatura_semanal3);

		JLabel min_temperatura_semanal4 = new JLabel();
		min_temperatura_semanal4.setFont(new Font("Dubai", Font.BOLD, 14));
		min_temperatura_semanal4.setBounds(335, 222, 46, 14);
		panel_semanal.add(min_temperatura_semanal4);

		JLabel min_temperatura_semanal5 = new JLabel();
		min_temperatura_semanal5.setFont(new Font("Dubai", Font.BOLD, 14));
		min_temperatura_semanal5.setBounds(335, 284, 46, 14);
		panel_semanal.add(min_temperatura_semanal5);

		JLabel min_temperatura_semanal6 = new JLabel();
		min_temperatura_semanal6.setFont(new Font("Dubai", Font.BOLD, 14));
		min_temperatura_semanal6.setBounds(335, 346, 46, 14);
		panel_semanal.add(min_temperatura_semanal6);

		JLabel viento_semanal = new JLabel("24 km/h");
		viento_semanal.setFont(new Font("Dubai", Font.BOLD, 10));
		viento_semanal.setBounds(175, 44, 46, 14);
		panel_semanal.add(viento_semanal);

		JLabel prob_semanal = new JLabel("24 mm");
		prob_semanal.setFont(new Font("Dubai", Font.BOLD, 10));
		prob_semanal.setBounds(222, 44, 46, 14);
		panel_semanal.add(prob_semanal);

		JLabel nieve_semanal = new JLabel("24 cm");
		nieve_semanal.setFont(new Font("Dubai", Font.BOLD, 10));
		nieve_semanal.setBounds(261, 44, 46, 14);
		panel_semanal.add(nieve_semanal);

		JLabel hor_semanal = new JLabel("0.0 h");
		hor_semanal.setFont(new Font("Dubai", Font.BOLD, 10));
		hor_semanal.setBounds(300, 44, 46, 14);
		panel_semanal.add(hor_semanal);

		JLabel viento_semanal2 = new JLabel("22 km/h");
		viento_semanal2.setFont(new Font("Dubai", Font.BOLD, 10));
		viento_semanal2.setBounds(175, 106, 46, 14);
		panel_semanal.add(viento_semanal2);

		JLabel prob_semanal2 = new JLabel("26 mm");
		prob_semanal2.setFont(new Font("Dubai", Font.BOLD, 10));
		prob_semanal2.setBounds(222, 106, 46, 14);
		panel_semanal.add(prob_semanal2);

		JLabel nieve_semanal2 = new JLabel("23 cm");
		nieve_semanal2.setFont(new Font("Dubai", Font.BOLD, 10));
		nieve_semanal2.setBounds(261, 106, 46, 14);
		panel_semanal.add(nieve_semanal2);

		JLabel hor_semanal2 = new JLabel("0.5 h");
		hor_semanal2.setFont(new Font("Dubai", Font.BOLD, 10));
		hor_semanal2.setBounds(300, 106, 46, 14);
		panel_semanal.add(hor_semanal2);

		JLabel viento_semanal3 = new JLabel("24 km/h");
		viento_semanal3.setFont(new Font("Dubai", Font.BOLD, 10));
		viento_semanal3.setBounds(175, 168, 46, 14);
		panel_semanal.add(viento_semanal3);

		JLabel prob_semanal3 = new JLabel("24 mm");
		prob_semanal3.setFont(new Font("Dubai", Font.BOLD, 10));
		prob_semanal3.setBounds(222, 168, 46, 14);
		panel_semanal.add(prob_semanal3);

		JLabel nieve_semanal3 = new JLabel("24 cm");
		nieve_semanal3.setFont(new Font("Dubai", Font.BOLD, 10));
		nieve_semanal3.setBounds(261, 168, 46, 14);
		panel_semanal.add(nieve_semanal3);

		JLabel hor_semanal3 = new JLabel("0.0 h");
		hor_semanal3.setFont(new Font("Dubai", Font.BOLD, 10));
		hor_semanal3.setBounds(300, 168, 46, 14);
		panel_semanal.add(hor_semanal3);

		JLabel viento_semanal4 = new JLabel("24 km/h");
		viento_semanal4.setFont(new Font("Dubai", Font.BOLD, 10));
		viento_semanal4.setBounds(175, 230, 46, 14);
		panel_semanal.add(viento_semanal4);

		JLabel prob_semanal4 = new JLabel("24 mm");
		prob_semanal4.setFont(new Font("Dubai", Font.BOLD, 10));
		prob_semanal4.setBounds(222, 230, 46, 14);
		panel_semanal.add(prob_semanal4);

		JLabel nieve_semanal4 = new JLabel("24 cm");
		nieve_semanal4.setFont(new Font("Dubai", Font.BOLD, 10));
		nieve_semanal4.setBounds(261, 230, 46, 14);
		panel_semanal.add(nieve_semanal4);

		JLabel hor_semanal4 = new JLabel("0.0 h");
		hor_semanal4.setFont(new Font("Dubai", Font.BOLD, 10));
		hor_semanal4.setBounds(300, 230, 46, 14);
		panel_semanal.add(hor_semanal4);

		JLabel viento_semanal5 = new JLabel("24 km/h");
		viento_semanal5.setFont(new Font("Dubai", Font.BOLD, 10));
		viento_semanal5.setBounds(175, 292, 46, 14);
		panel_semanal.add(viento_semanal5);

		JLabel prob_semanal5 = new JLabel("24 mm");
		prob_semanal5.setFont(new Font("Dubai", Font.BOLD, 10));
		prob_semanal5.setBounds(222, 292, 46, 14);
		panel_semanal.add(prob_semanal5);

		JLabel nieve_semanal5 = new JLabel("24 cm");
		nieve_semanal5.setFont(new Font("Dubai", Font.BOLD, 10));
		nieve_semanal5.setBounds(261, 292, 46, 14);
		panel_semanal.add(nieve_semanal5);

		JLabel hor_semanal5 = new JLabel("0.0 h");
		hor_semanal5.setFont(new Font("Dubai", Font.BOLD, 10));
		hor_semanal5.setBounds(300, 292, 46, 14);
		panel_semanal.add(hor_semanal5);

		JLabel viento_semanal6 = new JLabel("24 km/h");
		viento_semanal6.setFont(new Font("Dubai", Font.BOLD, 10));
		viento_semanal6.setBounds(175, 354, 46, 14);
		panel_semanal.add(viento_semanal6);

		JLabel prob_semanal6 = new JLabel("24 mm");
		prob_semanal6.setFont(new Font("Dubai", Font.BOLD, 10));
		prob_semanal6.setBounds(222, 354, 46, 14);
		panel_semanal.add(prob_semanal6);

		JLabel nieve_semanal6 = new JLabel("24 cm");
		nieve_semanal6.setFont(new Font("Dubai", Font.BOLD, 10));
		nieve_semanal6.setBounds(261, 354, 46, 14);
		panel_semanal.add(nieve_semanal6);

		JLabel hor_semanal6 = new JLabel("0.0 h");
		hor_semanal6.setFont(new Font("Dubai", Font.BOLD, 10));
		hor_semanal6.setBounds(300, 354, 46, 14);
		panel_semanal.add(hor_semanal6);

		JLabel Imagen_Tiempo1 = new JLabel("Imagen");
		Imagen_Tiempo1.setBounds(80, 8, 85, 55);
		panel_semanal.add(Imagen_Tiempo1);

		JLabel Imagen_Tiempo2 = new JLabel("Imagen");
		Imagen_Tiempo2.setBounds(80, 70, 85, 55);
		panel_semanal.add(Imagen_Tiempo2);

		JLabel Imagen_Tiempo3 = new JLabel("Imagen");
		Imagen_Tiempo3.setBounds(80, 132, 85, 55);
		panel_semanal.add(Imagen_Tiempo3);

		JLabel Imagen_Tiempo4 = new JLabel("Imagen");
		Imagen_Tiempo4.setBounds(80, 193, 85, 55);
		panel_semanal.add(Imagen_Tiempo4);

		JLabel Imagen_Tiempo5 = new JLabel("Imagen");
		Imagen_Tiempo5.setBounds(80, 255, 85, 55);
		panel_semanal.add(Imagen_Tiempo5);

		JLabel Imagen_Tiempo6 = new JLabel("Imagen");
		Imagen_Tiempo6.setBounds(80, 317, 85, 55);
		panel_semanal.add(Imagen_Tiempo6);

		//Se establecen formatos dias
		SimpleDateFormat hr = new SimpleDateFormat("yyyy-MM-dd HH:00");
		SimpleDateFormat di = new SimpleDateFormat("E yyyy-MM-dd");

		//variables para guardar los datos
		double [] temperatura = new double[192];
		int cod[] = new int[7];
		double temperatura_max[]=new double[7];	
		double temperatura_min[]=new double[7];	
		double lluv[]=new double[7];
		double hor_lluv[]=new double[7];
		double niev[]=new double[7];	
		double vient[]=new double[7];


		String [] fecha = new String[192]; //String de los dias y horas

		//Del objeto JSON desfragmenta en diferentes datos
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

			temperatura[i]= temp.getDouble(i); //guarda la temperatura por horas en un array
			fecha[i]=dias.getString(i).replace("T"," "); //guarda las fechas de las temperaturas

		}
		//Se crea un array con los 7 dias de la semana
		Calendar c = Calendar.getInstance();
		String []dias_semana = new String[7];
		String []dias_semana_sin_horas = new String[7];

		for(int z=0;z<=6;z++) {
			Date dia = new Date();
			c.setTime(dia);
			c.add(Calendar.DATE, z);
			dia = c.getTime();
			dias_semana[z]=hr.format(dia); //Se guarda en el array los 7 dias de la semana en la misma hora que nos encontramos
			dias_semana_sin_horas[z]=di.format(dia); //Se guarda en el array de los 7 dias de la semana sin hora
		}

		for(int i = 0;i<=6;i++) {
			//bucle para obtener datos que no dependen de la hora sino que son diarios
			cod[i]=codigo.getInt(i); //weather code
			temperatura_max[i]=temp_max.getDouble(i);
			temperatura_min[i]=temp_min.getDouble(i);
			lluv[i]=lluvia.getDouble(i);//luvia
			hor_lluv[i]=hora_lluvia.getDouble(i);//hora que va a llover
			niev[i]=nieve.getDouble(i);//nieve
			vient[i]=viento.getDouble(i);//viento
		}

		//De aqui al final de la función se establecen los datos en la interfaz grafica
		for (int i = 0; i < dias.length(); i++) {

			if(fecha[i].equals(dias_semana[0])) { 
				//Si la fecha (las fechas y horas  de las temperaturas por horas) == al PRIMER dia y hora (por lo tanto hoy a esta misma hora)  que nos encontramos
				Temperatura_hor.setText(String.valueOf(temperatura[i])+ " ºC");
				max_temperatura.setText(String.valueOf(temperatura_max[0])+ " ºC");
				min_temperatura.setText(String.valueOf(temperatura_min[0])+ " ºC");
				Info_viento.setText(String.valueOf(vient[0])+ " km/h");
				Info_prob_lluvia.setText(String.valueOf(lluv[0])+ " mm");
				Info_hora_lluvia.setText(String.valueOf(hor_lluv[0])+ " h");
				Info_nieve.setText(String.valueOf(niev[0]) + " cm");

			}
		}//fin del bucle
		//Establece las imagenes del tiempo, el switch recorre los codigos los código WMO los if son para establecer la foto en cada dia que tenga ese código
		try {
			ImageIcon icono = new ImageIcon(); 
			for(int i=0;i<=6;i++) {
				switch(cod[i]) {
				case 0:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/01d@2x.png")); 
					if(i==0) {
						Descripcion_tiempo.setText("Despejado");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}
					break;
				case 1:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/01d@2x.png")); 
					if(i==0) {
						Descripcion_tiempo.setText("Semidespejado");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}
					break;
				case 2:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/02d@2x.png")); 
					if(i==0) {
						Descripcion_tiempo.setText("Seminublado");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}					
					break;
				case 3:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/03d@2x.png")); 
					if(i==0) {
						Descripcion_tiempo.setText("Nublado");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}				
					break;
				case 45:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/50d@2x.png")); 
					if(i==0) {
						Descripcion_tiempo.setText("Nebuloso");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}	
					break;
				case 48:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/50d@2x.png")); 
					if(i==0) {
						Descripcion_tiempo.setText("Nebuloso");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}						
					break;
				case 51:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/09d@2x.png")); 
					if(i==0) {
						Descripcion_tiempo.setText("Llovizna leve");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}						
					break;
				case 53:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/09d@2x.png")); 
					if(i==0) {
						Descripcion_tiempo.setText("LLovizna");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}					
					break;
				case 55:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/09d@2x.png")); 
					if(i==0) {
						Descripcion_tiempo.setText("Gran LLovizna");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}						
					break;
				case 56:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/09d@2x.png")); 
					if(i==0) {
						Descripcion_tiempo.setText("Llovizna helada");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}						
					break;
				case 57:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/09d@2x.png")); 
					if(i==0) {
						Descripcion_tiempo.setText("Llovizna helada");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}							
					break;
				case 61:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/10d@2x.png")); 
					if(i==0) {
						Descripcion_tiempo.setText("Lluvia leve");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}							
					break;
				case 63:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/10d@2x.png")); 
					if(i==0) {
						Descripcion_tiempo.setText("Lluvia");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}								
					break;
				case 65:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/10d@2x.png")); 
					if(i==0) {
						Descripcion_tiempo.setText("Lluvia fuerte");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}						
					break;
				case 66:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/10d@2x.png")); 
					if(i==0) {
						Descripcion_tiempo.setText("LLuvia helada");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}					
					break;
				case 67:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/10d@2x.png"));
					if(i==0) {
						Descripcion_tiempo.setText("LLuvia helada");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}							
					break;
				case 71:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/13d@2x.png"));
					if(i==0) {
						Descripcion_tiempo.setText("Nieve leve");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}					
					break;
				case 73:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/13d@2x.png"));
					if(i==0) {
						Descripcion_tiempo.setText("Nieve");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}					
					break;
				case 75:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/13d@2x.png"));
					if(i==0) {
						Descripcion_tiempo.setText("Nieve fuerte");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}				
					break;
				case 77:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/13d@2x.png"));
					if(i==0) {
						Descripcion_tiempo.setText("Copos de nieve");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}					
					break;
				case 80:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/09d@2x.png"));
					if(i==0) {
						Descripcion_tiempo.setText("Chispea leve");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}				
					break;
				case 81:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/09d@2x.png"));
					if(i==0) {
						Descripcion_tiempo.setText("Chispea");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}				
					break;
				case 82:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/09d@2x.png"));
					if(i==0) {
						Descripcion_tiempo.setText("Chispea fuerte");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}	
					break;
				case 85:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/13d@2x.png"));
					if(i==0) {
						Descripcion_tiempo.setText("Chispea nieve");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}				
					break;
				case 86:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/13d@2x.png"));
					if(i==0) {
						Descripcion_tiempo.setText("Chispea nieve");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}				
					break;
				case 95:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/11d@2x.png"));
					if(i==0) {
						Descripcion_tiempo.setText("Tormenta electrica");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}					
					break;
				case 96:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/11d@2x.png"));
					if(i==0) {
						Descripcion_tiempo.setText("Tormenta granizada");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}					
					break;
				case 99:
					icono = new ImageIcon(new URL("http://openweathermap.org/img/wn/11d@2x.png"));
					if(i==0) {
						Descripcion_tiempo.setText("Tormenta granizada");
						Imagen_Tiempo.setIcon(icono);
					}
					if(i==1) {
						Imagen_Tiempo1.setIcon(icono);
					}
					if(i==2) {
						Imagen_Tiempo2.setIcon(icono);
					}
					if(i==3) {
						Imagen_Tiempo3.setIcon(icono);
					}
					if(i==4) {
						Imagen_Tiempo4.setIcon(icono);
					}
					if(i==5) {
						Imagen_Tiempo5.setIcon(icono);
					}
					if(i==6) {
						Imagen_Tiempo6.setIcon(icono);
					}						
					break;
				default:
					// Código por defecto si el código no está en la lista
					break;
				}
			}
		}catch(Exception e) {
			System.out.println(e);
		}

		//temperaturas por horas pero solo se ponen las 24 primeras (24horas)
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

		//fecha proximas (6 dias siguientes)
		fecha1.setText(dias_semana_sin_horas[1]);
		fecha2.setText(dias_semana_sin_horas[2]);
		fecha3.setText(dias_semana_sin_horas[3]);
		fecha4.setText(dias_semana_sin_horas[4]);
		fecha5.setText(dias_semana_sin_horas[5]);
		fecha6.setText(dias_semana_sin_horas[6]);

		//Temperatura max y min de las fechas proximas
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

		viento_semanal.setText(String.valueOf(vient[1])+ " km/h");
		prob_semanal.setText(String.valueOf(lluv[1])+ " mm");
		hor_semanal.setText(String.valueOf(hor_lluv[1])+ " h");
		nieve_semanal.setText(String.valueOf(niev[1]) + " cm");
		viento_semanal2.setText(String.valueOf(vient[2])+ " km/h");
		prob_semanal2.setText(String.valueOf(lluv[2])+ " mm");
		hor_semanal2.setText(String.valueOf(hor_lluv[2])+ " h");
		nieve_semanal2.setText(String.valueOf(niev[2]) + " cm");
		viento_semanal3.setText(String.valueOf(vient[3])+ " km/h");
		prob_semanal3.setText(String.valueOf(lluv[3])+ " mm");
		hor_semanal3.setText(String.valueOf(hor_lluv[3])+ " h");
		nieve_semanal3.setText(String.valueOf(niev[3]) + " cm");
		viento_semanal4.setText(String.valueOf(vient[4])+ " km/h");
		prob_semanal4.setText(String.valueOf(lluv[4])+ " mm");
		hor_semanal4.setText(String.valueOf(hor_lluv[4])+ " h");
		nieve_semanal4.setText(String.valueOf(niev[4]) + " cm");
		viento_semanal5.setText(String.valueOf(vient[5])+ " km/h");
		prob_semanal5.setText(String.valueOf(lluv[5])+ " mm");
		hor_semanal5.setText(String.valueOf(hor_lluv[5])+ " h");
		nieve_semanal5.setText(String.valueOf(niev[5]) + " cm");
		viento_semanal6.setText(String.valueOf(vient[6])+ " km/h");
		prob_semanal6.setText(String.valueOf(lluv[6])+ " mm");
		hor_semanal6.setText(String.valueOf(hor_lluv[6])+ " h");
		nieve_semanal6.setText(String.valueOf(niev[6]) + " cm");

		//recomendaciones, haciendo un filtro con los if
		String alerta = "¡Alerta! No es recomendable hacer mantenimiento debido a:";
		String alertas = "";
		boolean hayAlertas = false;

		if (temperatura_max[0] > 38) {
			alertas += "Temperatura máxima demasiada alta.<br>";
			hayAlertas = true;
		}

		if (temperatura_min[0] < 0) {
			alertas += "Temperatura mínima demasiado baja<br>";
			hayAlertas = true;
		}

		if (lluv[0] > 15) {
			alertas += "Alta probabilidad de lluvia.<br>";
			hayAlertas = true;
		}

		if (niev[0] > 3) {
			alertas += "Alta probabilidad de nieve.<br>";
			hayAlertas = true;
		}

		if (vient[0] > 25) {
			alertas += "Alta velocidad del viento.<br>";
			hayAlertas = true;
		}

		if (hayAlertas) {
			Recomendaciones.setText("<html>" + alerta + "<br>" + alertas + "</html>");
		} else {
			Recomendaciones.setText("Se puede realizar el mantenimiento sin ningún riesgo.");
		}

		//crea y guarda en el archivo csv las variables para crear el historico
		try (FileWriter fw = new FileWriter("BaseDeDatos.csv",true)) {
			for(int i=0;i<=6;i++) {
				fw.write(dias_semana_sin_horas[i]+";");
				fw.write(cod[i]+";");
				fw.write(temperatura_max[i]+";");
				fw.write(temperatura_min[i]+";");
				fw.write(lluv[i]+";");
				fw.write(hor_lluv[i]+";");
				fw.write(niev[i]+";");
				fw.write(vient[i]+"\n");
				if(i==6) {
					fw.write("---------------------------------------------------------------");
					fw.write("\n");
				}
			}
		}catch(Exception e){
			System.out.print(e.getMessage());//obtiene el error
		}
	}



	public static void main(String[] args) {
		System.setProperty("sun.java2d.uiScale", "100%"); // establece la escala de texto,imagenes y APP independientemente de la escala del OS 

		JFrame interfaz = new JFrame();//frame

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//forma en la que se ve el programa dependiendo del OS
		}catch(Exception e) {
			e.printStackTrace();
		}

		//parametros de interfaz asi como labels,etc.
		interfaz.setAlwaysOnTop(true);
		interfaz.setResizable(false);
		interfaz.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagenes/Logo.png"));
		interfaz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		interfaz.setBounds(100, 100, 450, 507);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		interfaz.setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 51);
		contentPane.add(panel);
		panel.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 48, 434, 420);
		tabbedPane.setFont(new Font("Dubai", Font.BOLD, 12));
		tabbedPane.setBorder(null); // quita bordes
		tabbedPane.setFocusable(false); // quita bordes
		contentPane.add(tabbedPane);

		JPanel panel_hoy = new JPanel();
		panel_hoy.setBackground(Color.WHITE);
		tabbedPane.addTab("HOY", null, panel_hoy, null);
		panel_hoy.setLayout(null);

		JPanel panel_horas = new JPanel();
		panel_horas.setBackground(Color.WHITE);
		tabbedPane.addTab("TEMPERATURAS POR HORAS", null, panel_horas, null);
		panel_horas.setLayout(null);

		JPanel panel_semanal = new JPanel();
		panel_semanal.setBackground(Color.WHITE);
		tabbedPane.addTab("TEMPERATURAS PRÓXIMAS", null, panel_semanal, null);
		panel_semanal.setLayout(null);

		//panel 

		JLabel Titulo = new JLabel("ClimaTowers");
		Titulo.setFont(new Font("Dubai", Font.BOLD, 28));
		Titulo.setBounds(10, 11, 154, 26);
		panel.add(Titulo);

		JLabel Imagen_pos = new JLabel(new ImageIcon("Imagenes/posicion1.png"));
		Imagen_pos.setBounds(301, 19, 20, 20);
		panel.add(Imagen_pos);

		JLabel Titulo1 = new JLabel("TorreEspaña");
		Titulo1.setFont(new Font("Dubai", Font.BOLD, 17));
		Titulo1.setBounds(323, 18, 154, 26);
		panel.add(Titulo1);

		//panel_hoy

		JLabel Imagen_viento = new JLabel(new ImageIcon("Imagenes/icono_viento.png"));
		Imagen_viento.setBounds(35, 155, 30, 30);
		panel_hoy.add(Imagen_viento);

		JLabel Imagen_prob_lluvia = new JLabel(new ImageIcon("Imagenes/lluvia_icono.png"));
		Imagen_prob_lluvia.setBounds(139, 155, 30, 30);
		panel_hoy.add(Imagen_prob_lluvia);


		JLabel Imagen_nieve = new JLabel(new ImageIcon("Imagenes/copo_nieve_icono.png"));
		Imagen_nieve.setBounds(241, 155, 30, 30);
		panel_hoy.add(Imagen_nieve);

		JLabel Imagen_hor_lluvia = new JLabel(new ImageIcon("Imagenes/hora_lluvia_icono.png"));
		Imagen_hor_lluvia.setBounds(340, 155, 30, 30);
		panel_hoy.add(Imagen_hor_lluvia);

		JLabel Imagen_maxmin = new JLabel(new ImageIcon("Imagenes/maxmin_icono.png"));
		Imagen_maxmin.setBounds(123, 45, 50, 50);
		panel_hoy.add(Imagen_maxmin);


		//panel horas

		JLabel hor0 = new JLabel("00:00");
		hor0.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0.setBounds(30, 11, 79, 14);
		panel_horas.add(hor0);

		JLabel hor0_1 = new JLabel("01:00");
		hor0_1.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_1.setBounds(30, 36, 79, 14);
		panel_horas.add(hor0_1);

		JLabel hor0_2 = new JLabel("02:00");
		hor0_2.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_2.setBounds(30, 61, 79, 14);
		panel_horas.add(hor0_2);

		JLabel hor0_3 = new JLabel("03:00");
		hor0_3.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_3.setBounds(30, 86, 79, 14);
		panel_horas.add(hor0_3);

		JLabel hor0_4 = new JLabel("04:00");
		hor0_4.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_4.setBounds(30, 111, 79, 14);
		panel_horas.add(hor0_4);

		JLabel hor0_5 = new JLabel("05:00");
		hor0_5.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_5.setBounds(30, 136, 79, 14);
		panel_horas.add(hor0_5);

		JLabel hor0_6 = new JLabel("06:00");
		hor0_6.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_6.setBounds(30, 161, 79, 14);
		panel_horas.add(hor0_6);

		JLabel hor0_7 = new JLabel("07:00");
		hor0_7.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_7.setBounds(30, 186, 79, 14);
		panel_horas.add(hor0_7);

		JLabel hor0_8 = new JLabel("08:00");
		hor0_8.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_8.setBounds(30, 211, 79, 14);
		panel_horas.add(hor0_8);

		JLabel hor0_9 = new JLabel("09:00");
		hor0_9.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_9.setBounds(30, 236, 79, 14);
		panel_horas.add(hor0_9);

		JLabel hor0_10 = new JLabel("10:00");
		hor0_10.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_10.setBounds(30, 261, 79, 14);
		panel_horas.add(hor0_10);

		JLabel hor0_11 = new JLabel("11:00");
		hor0_11.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_11.setBounds(30, 286, 79, 14);
		panel_horas.add(hor0_11);

		JLabel hor0_12 = new JLabel("12:00");
		hor0_12.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_12.setBounds(30, 311, 79, 14);
		panel_horas.add(hor0_12);

		JLabel hor0_13 = new JLabel("13:00");
		hor0_13.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_13.setBounds(30, 336, 79, 14);
		panel_horas.add(hor0_13);

		JLabel hor0_14 = new JLabel("14:00");
		hor0_14.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_14.setBounds(243, 11, 79, 14);
		panel_horas.add(hor0_14);

		JLabel hor0_15 = new JLabel("15:00");
		hor0_15.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_15.setBounds(243, 36, 79, 14);
		panel_horas.add(hor0_15);

		JLabel hor0_16 = new JLabel("16:00");
		hor0_16.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_16.setBounds(243, 61, 79, 14);
		panel_horas.add(hor0_16);

		JLabel hor0_17 = new JLabel("17:00");
		hor0_17.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_17.setBounds(243, 86, 79, 14);
		panel_horas.add(hor0_17);

		JLabel hor0_18 = new JLabel("18:00");
		hor0_18.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_18.setBounds(243, 111, 79, 14);
		panel_horas.add(hor0_18);

		JLabel hor0_19 = new JLabel("19:00");
		hor0_19.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_19.setBounds(243, 136, 79, 14);
		panel_horas.add(hor0_19);

		JLabel hor0_20 = new JLabel("20:00");
		hor0_20.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_20.setBounds(243, 161, 79, 14);
		panel_horas.add(hor0_20);

		JLabel hor0_21 = new JLabel("21:00");
		hor0_21.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_21.setBounds(243, 186, 79, 14);
		panel_horas.add(hor0_21);

		JLabel hor0_22 = new JLabel("22:00");
		hor0_22.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_22.setBounds(243, 211, 79, 14);
		panel_horas.add(hor0_22);

		JLabel hor0_23 = new JLabel("23:00");
		hor0_23.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_23.setBounds(243, 236, 79, 14);
		panel_horas.add(hor0_23);

		JLabel hor0_24 = new JLabel("00:00");
		hor0_24.setFont(new Font("Dubai", Font.BOLD, 12));
		hor0_24.setBounds(243, 261, 79, 14);
		panel_horas.add(hor0_24);

		//panel semanal


		JLabel semanal_max1 = new JLabel("MAX");
		semanal_max1.setFont(new Font("Dubai", Font.BOLD, 14));
		semanal_max1.setBounds(373, 15, 46, 14);
		panel_semanal.add(semanal_max1);

		JLabel semanal_max2 = new JLabel("MAX");
		semanal_max2.setFont(new Font("Dubai", Font.BOLD, 14));
		semanal_max2.setBounds(373, 77, 46, 14);
		panel_semanal.add(semanal_max2);

		JLabel semanal_max3 = new JLabel("MAX");
		semanal_max3.setFont(new Font("Dubai", Font.BOLD, 14));
		semanal_max3.setBounds(373, 139, 46, 14);
		panel_semanal.add(semanal_max3);

		JLabel semanal_max4 = new JLabel("MAX");
		semanal_max4.setFont(new Font("Dubai", Font.BOLD, 14));
		semanal_max4.setBounds(373, 201, 46, 14);
		panel_semanal.add(semanal_max4);

		JLabel semanal_max5 = new JLabel("MAX");
		semanal_max5.setFont(new Font("Dubai", Font.BOLD, 14));
		semanal_max5.setBounds(373, 263, 46, 14);
		panel_semanal.add(semanal_max5);

		JLabel semanal_max6 = new JLabel("MAX");
		semanal_max6.setFont(new Font("Dubai", Font.BOLD, 14));
		semanal_max6.setBounds(373, 325, 46, 14);
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


		JLabel Imagen_viento1 = new JLabel(new ImageIcon("Imagenes/icono_viento.png"));
		Imagen_viento1.setBounds(180, 14, 30, 30);
		panel_semanal.add(Imagen_viento1);

		JLabel Imagen_prob_lluvia1 = new JLabel(new ImageIcon("Imagenes/lluvia_icono.png"));
		Imagen_prob_lluvia1.setBounds(222, 14, 30, 30);
		panel_semanal.add(Imagen_prob_lluvia1);


		JLabel Imagen_nieve1 = new JLabel(new ImageIcon("Imagenes/copo_nieve_icono.png"));
		Imagen_nieve1.setBounds(258, 14, 30, 30);
		panel_semanal.add(Imagen_nieve1);

		JLabel Imagen_hor_lluvia1 = new JLabel(new ImageIcon("Imagenes/hora_lluvia_icono.png"));
		Imagen_hor_lluvia1.setBounds(295, 14, 30, 30);
		panel_semanal.add(Imagen_hor_lluvia1);

		JLabel Imagen_viento2 = new JLabel(new ImageIcon("Imagenes/icono_viento.png"));
		Imagen_viento2.setBounds(180, 75, 30, 30);
		panel_semanal.add(Imagen_viento2);

		JLabel Imagen_prob_lluvia2 = new JLabel(new ImageIcon("Imagenes/lluvia_icono.png"));
		Imagen_prob_lluvia2.setBounds(222, 75, 30, 30);
		panel_semanal.add(Imagen_prob_lluvia2);

		JLabel Imagen_nieve2 = new JLabel(new ImageIcon("Imagenes/copo_nieve_icono.png"));
		Imagen_nieve2.setBounds(258, 75, 30, 30);
		panel_semanal.add(Imagen_nieve2);

		JLabel Imagen_hor_lluvia2 = new JLabel(new ImageIcon("Imagenes/hora_lluvia_icono.png"));
		Imagen_hor_lluvia2.setBounds(295, 75, 30, 30);
		panel_semanal.add(Imagen_hor_lluvia2);

		JLabel Imagen_viento3 = new JLabel(new ImageIcon("Imagenes/icono_viento.png"));
		Imagen_viento3.setBounds(180, 139, 30, 30);
		panel_semanal.add(Imagen_viento3);

		JLabel Imagen_prob_lluvia3 = new JLabel(new ImageIcon("Imagenes/lluvia_icono.png"));
		Imagen_prob_lluvia3.setBounds(222, 139, 30, 30);
		panel_semanal.add(Imagen_prob_lluvia3);


		JLabel Imagen_nieve3 = new JLabel(new ImageIcon("Imagenes/copo_nieve_icono.png"));
		Imagen_nieve3.setBounds(258, 139, 30, 30);
		panel_semanal.add(Imagen_nieve3);

		JLabel Imagen_hor_lluvia3 = new JLabel(new ImageIcon("Imagenes/hora_lluvia_icono.png"));
		Imagen_hor_lluvia3.setBounds(295, 139, 30, 30);
		panel_semanal.add(Imagen_hor_lluvia3);

		JLabel Imagen_viento4 = new JLabel(new ImageIcon("Imagenes/icono_viento.png"));
		Imagen_viento4.setBounds(180, 200, 30, 30);
		panel_semanal.add(Imagen_viento4);

		JLabel Imagen_prob_lluvia4 = new JLabel(new ImageIcon("Imagenes/lluvia_icono.png"));
		Imagen_prob_lluvia4.setBounds(222, 200, 30, 30);
		panel_semanal.add(Imagen_prob_lluvia4);

		JLabel Imagen_nieve4 = new JLabel(new ImageIcon("Imagenes/copo_nieve_icono.png"));
		Imagen_nieve4.setBounds(258, 200, 30, 30);
		panel_semanal.add(Imagen_nieve4);

		JLabel Imagen_hor_lluvia4 = new JLabel(new ImageIcon("Imagenes/hora_lluvia_icono.png"));
		Imagen_hor_lluvia4.setBounds(295, 200, 30, 30);
		panel_semanal.add(Imagen_hor_lluvia4);

		JLabel Imagen_viento5 = new JLabel(new ImageIcon("Imagenes/icono_viento.png"));
		Imagen_viento5.setBounds(180, 263, 30, 30);
		panel_semanal.add(Imagen_viento5);

		JLabel Imagen_prob_lluvia5 = new JLabel(new ImageIcon("Imagenes/lluvia_icono.png"));
		Imagen_prob_lluvia5.setBounds(222, 263, 30, 30);
		panel_semanal.add(Imagen_prob_lluvia5);

		JLabel Imagen_nieve5 = new JLabel(new ImageIcon("Imagenes/copo_nieve_icono.png"));
		Imagen_nieve5.setBounds(258, 263, 30, 30);
		panel_semanal.add(Imagen_nieve5);

		JLabel Imagen_hor_lluvia5 = new JLabel(new ImageIcon("Imagenes/hora_lluvia_icono.png"));
		Imagen_hor_lluvia5.setBounds(295, 263, 30, 30);
		panel_semanal.add(Imagen_hor_lluvia5);

		JLabel Imagen_viento6 = new JLabel(new ImageIcon("Imagenes/icono_viento.png"));
		Imagen_viento6.setBounds(180, 323, 30, 30);
		panel_semanal.add(Imagen_viento6);

		JLabel Imagen_prob_lluvia6 = new JLabel(new ImageIcon("Imagenes/lluvia_icono.png"));
		Imagen_prob_lluvia6.setBounds(222, 323, 30, 30);
		panel_semanal.add(Imagen_prob_lluvia6);

		JLabel Imagen_nieve6 = new JLabel(new ImageIcon("Imagenes/copo_nieve_icono.png"));
		Imagen_nieve6.setBounds(258, 323, 30, 30);
		panel_semanal.add(Imagen_nieve6);

		JLabel Imagen_hor_lluvia6 = new JLabel(new ImageIcon("Imagenes/hora_lluvia_icono.png"));
		Imagen_hor_lluvia6.setBounds(295, 323, 30, 30);
		panel_semanal.add(Imagen_hor_lluvia6);



		//se activan funciones

		String informacionAPI = llamada(); //llama a la API
		JSONObject ob = new JSONObject(informacionAPI); //se convierte en objeto la respuesta de la API
		desfragmentacion(informacionAPI,ob, panel_hoy, panel_horas, panel_semanal);//se desfragmenta y se representa en la interfaz



		//fondos panel_hoy

		JLabel Imagen_fondo = new JLabel(new ImageIcon("Imagenes/cuadrado.png"));
		Imagen_fondo.setBackground(Color.WHITE);
		Imagen_fondo.setForeground(Color.WHITE);
		Imagen_fondo.setBounds(10, 11, 409, 117);
		panel_hoy.add(Imagen_fondo);

		JLabel Imagen_fondo_1 = new JLabel(new ImageIcon("Imagenes/cuadrado1.png"));
		Imagen_fondo_1.setForeground(Color.WHITE);
		Imagen_fondo_1.setBackground(Color.WHITE);
		Imagen_fondo_1.setBounds(10, 138, 409, 86);
		panel_hoy.add(Imagen_fondo_1);

		JLabel Recomendaciones_fondo = new JLabel(new ImageIcon("Imagenes/cuadrado.png"));
		Recomendaciones_fondo.setForeground(Color.WHITE);
		Recomendaciones_fondo.setBackground(Color.WHITE);
		Recomendaciones_fondo.setBounds(9, 235, 409, 127);
		panel_hoy.add(Recomendaciones_fondo);

		//panel_horas
		JLabel Imagen_fondo_horas = new JLabel(new ImageIcon("Imagenes/cuadrado5.png"));
		Imagen_fondo_horas.setBackground(Color.WHITE);
		Imagen_fondo_horas.setForeground(Color.WHITE);
		Imagen_fondo_horas.setBounds(17, 3, 152, 353);
		panel_horas.add(Imagen_fondo_horas);

		JLabel Imagen_fondo_horas1= new JLabel(new ImageIcon("Imagenes/cuadrado6.png"));
		Imagen_fondo_horas1.setBackground(Color.WHITE);
		Imagen_fondo_horas1.setForeground(Color.WHITE);
		Imagen_fondo_horas1.setBounds(230, 6, 152, 277);
		panel_horas.add(Imagen_fondo_horas1);
		//fondos panel_semanal

		JLabel fondo_tiempo0 = new JLabel(new ImageIcon("Imagenes/cuadrado2.png"));
		fondo_tiempo0.setBounds(10, 11, 409, 51);
		panel_semanal.add(fondo_tiempo0);

		JLabel fondo_tiempo1 = new JLabel(new ImageIcon("Imagenes/cuadrado2.png"));
		fondo_tiempo1.setBounds(10, 73, 409, 51);
		panel_semanal.add(fondo_tiempo1);

		JLabel fondo_tiempo2 = new JLabel(new ImageIcon("Imagenes/cuadrado2.png"));
		fondo_tiempo2.setBounds(10, 135, 409, 51);
		panel_semanal.add(fondo_tiempo2);

		JLabel fondo_tiempo3 = new JLabel(new ImageIcon("Imagenes/cuadrado2.png"));
		fondo_tiempo3.setBounds(10, 197, 409, 51);
		panel_semanal.add(fondo_tiempo3);

		JLabel fondo_tiempo4 = new JLabel(new ImageIcon("Imagenes/cuadrado2.png"));
		fondo_tiempo4.setBounds(10, 259, 409, 51);
		panel_semanal.add(fondo_tiempo4);

		JLabel fondo_tiempo5 = new JLabel(new ImageIcon("Imagenes/cuadrado2.png"));
		fondo_tiempo5.setBounds(10, 321, 409, 51);
		panel_semanal.add(fondo_tiempo5);

		//interfaz visible
		interfaz.setVisible(true);






	}

}
