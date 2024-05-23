package poker.servlet;

import java.io.InputStream;
import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.spi.JsonProvider;

@Component("Json")
public final class Json {
    //@Autowired
    private static final JsonProvider provider = JsonProvider.provider();

    public Json() {
        //provider = JsonProvider.provider();
    }

    public JsonObject newJsonObject(String json) {
        JsonObject jsonObject = null;
        try {
            jsonObject = provider.createReader(new StringReader(json.toString())).readObject();
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        return jsonObject;
    }

    public JsonObject newJsonObject(InputStream in) {
        JsonObject jsonObject = null;
        try {
            jsonObject = provider.createReader(in).readObject();
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        return jsonObject;
    }

    public JsonArray newJsonArray(InputStream in) {
        JsonArray jsonArray = null;
        try {
            jsonArray = provider.createReader(in).readArray();
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        return jsonArray;
    }

    public JsonArray newJsonArray(String line) {
        JsonArray jsonArray = null;
        try {
            jsonArray = provider.createReader(new StringReader(line.toString())).readArray();
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        return jsonArray;
    }

    public JsonObjectBuilder newJsonObjectBuilder() {
        return provider.createObjectBuilder();
    }

    public JsonArrayBuilder newJsonArrayBuilder() {
        return provider.createArrayBuilder();
    }

}