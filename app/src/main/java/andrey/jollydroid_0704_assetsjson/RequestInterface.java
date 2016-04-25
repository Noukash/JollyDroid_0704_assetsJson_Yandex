package andrey.jollydroid_0704_assetsjson;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.Call;

/**
 * Created by Andrey on 24.04.2016.
 */
public interface RequestInterface {

    @GET("mobilization-2016/artists.json")
    Call<List<Singer>> getJSON();
}