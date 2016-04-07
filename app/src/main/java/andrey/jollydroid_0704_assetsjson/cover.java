package andrey.jollydroid_0704_assetsjson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrey on 07.04.2016.
 */
public class cover {
    @SerializedName("small")
    private String small;
    @SerializedName("big")
    private String big;

    public String getBig() {
        return big;
    }

    public String getSmall() {
        return small;
    }
}
