package beans.classes;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

public class YandexTranslateAPI {
    
    private final String urlKey;
    private HttpsURLConnection connection;
    private DataOutputStream dataOutputStream;
    private InputStream response ;

    public YandexTranslateAPI() {
        urlKey="trnsl.1.1.20161122T102623Z.948e96bf721b9b02.ad49e87b97665e999fea5d216047cf5ad00c0544";
    }
      
    public String translate(String word) throws IOException { 
        URL urlObj = new URL("https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + urlKey);
        connection = (HttpsURLConnection)urlObj.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeBytes("text=" + URLEncoder.encode(word, "UTF-8") + "&lang=" + "ru");
        response = connection.getInputStream();

        String json = new java.util.Scanner(response).nextLine();
        String result = new String(json.substring(json.indexOf("[") + 2, json.indexOf("]") - 1).getBytes(),"UTF-8" );
        return result;
    }
}
