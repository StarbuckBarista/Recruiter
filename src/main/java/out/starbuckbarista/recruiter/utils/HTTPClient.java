package out.starbuckbarista.recruiter.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPClient {

    String url;

    public HTTPClient(String url) {
        this.url = url;
    }

    public JsonObject getRawResponse() throws IOException {

        HttpURLConnection httpClient = (HttpURLConnection) new URL(this.url).openConnection();
        httpClient.connect();

        JsonParser jsonParser = new JsonParser();
        JsonElement jsonRoot = jsonParser.parse(new InputStreamReader((InputStream) httpClient.getContent()));

        return jsonRoot.getAsJsonObject();
    }
}
