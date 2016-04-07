package andrey.jollydroid_0704_assetsjson;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrey on 07.04.2016.
 */
public class JsonHelper {
    List<Singer> singers;
    public List<Singer> TakeDataFromJson(Context context){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        singers = new ArrayList<Singer>();
        singers= Arrays.asList(gson.fromJson(loadJSONFromAsset(context), Singer[].class));
        return singers;

    }
    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("artists.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
