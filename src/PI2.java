import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Scanner;
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

		double lat=40.41;
		double lon=-3.7;
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
				System.out.println(respuesta);
				p.close();
			}

		}catch(Exception e) {
			System.out.println(e);
		}

		return respuesta;
	}

	// Función que procesa la respuesta de la API y obtiene la información de temperatura para los días requeridos
	public static void desfragmentacion(String info, JSONObject ob) {
		Date dia = new Date();
		Date dia_7 = new Date();

		SimpleDateFormat hr = new SimpleDateFormat("yyyy-MM-dd HH:00");
		String hora_actual = hr.format(dia);

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
		String []dias_semana = new String[6];

		for(int z=0;z<6;z++) {
			int x=1;
			c.setTime(dia_7);
			c.add(Calendar.DATE, x);
			dia_7 = c.getTime();
			dias_semana[z]=hr.format(dia_7);
			x++;
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

			if(fecha[i].equals(hora_actual)) {
				System.out.println(fecha[i]+": "+temperatura[i]);
				System.out.println(cod[0]+" "+temperatura_max[0]+" "+temperatura_min[0]+" "+lluv[0]+" "+hor_lluv[0]+" "+niev[0]+" "+vient[0]);
				
			}

			for(int y=0;y<6;y++) {
				if(fecha[i].equals(dias_semana[y])) {
					System.out.println(dias_semana[y]+": "+temperatura[i]);
					if(y>0) {
						System.out.println(cod[y]+" "+temperatura_max[y]+" "+temperatura_min[y]+" "+lluv[y]+" "+hor_lluv[y]+" "+niev[y]+" "+vient[y]);
					}
				}

			}

		}

	}



	public static void main(String[] args) {

		String informacionAPI = llamada();
		JSONObject ob = new JSONObject(informacionAPI);
		desfragmentacion(informacionAPI,ob);


	}

}
