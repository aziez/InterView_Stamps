import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    private static final String url = "https://api.openweathermap.org/data/2.5/onecall?lat=-6.121435&lon=106.774124&exclude=current,minutely,hourly&appid=4e6941a16e604e8e0e05d7cc05f8cd57&lang=id&units=metric";


    public static void main(String[] args){
        System.out.println("Weather Forecast : ");
        HttpHandler sh = new HttpHandler();
        String jsonStr = sh.makeServiceCall(url);

        if (jsonStr != null){
            try {
                JSONObject jsonObject = new JSONObject(jsonStr);
                String daerah = jsonObject.getString("timezone");
                System.out.println(daerah);

                JSONArray harian = jsonObject.getJSONArray("daily");

                for (int i = 0; i < harian.length(); i++){
                    JSONObject c = harian.getJSONObject(i);
                    long timeStamp = c.getLong("dt");
                    Date timeD = new Date(timeStamp * 1000);
                    SimpleDateFormat sdf = new SimpleDateFormat("EE, dd, MMM, yyyy : ");
                    String time = sdf.format(timeD);

                    JSONObject suhu = c.getJSONObject("temp");
                    double day = suhu.getDouble("day");

                    System.out.println(time+day+"°C");

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}

class HttpHandler{
    public HttpHandler() {
    }

    public String makeServiceCall(String reqUrl){
        String res = null;

        try {
            URL url = new URL(reqUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            InputStream in = new BufferedInputStream(conn.getInputStream());
            res = convertStreamToString(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    private String convertStreamToString(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        String line;

        try {
            while ((line = reader.readLine()) != null){
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}
