package helperclass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonReaderURL {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }


    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private static JSONObject readJsonFromGet(String link){
        String body = null;
        try {
            body = GetURL.main(link);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonbody = new JSONObject(body);
        return jsonbody;
    }

    public static String JSONstringer(String link, String parameter) throws IOException, JSONException{
        JSONObject json = readJsonFromUrl(link);
        return json.get(parameter).toString();
    }

    public static String JSONstrigerGet(String link, String parameter, String key, String ifcondition, String dovalue){

        JSONObject json = readJsonFromGet(link);
        JSONArray jsona = json.getJSONObject("current").getJSONArray(key);
        for (Object obj: jsona){
            JSONObject param = (JSONObject) obj;
            if(param.get(ifcondition).toString().equalsIgnoreCase(parameter)){
                return  param.get(dovalue).toString();
            }

        }
        return json.get("current").toString();

    }

}