package andrey.jollydroid_0704_assetsjson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 07.04.2016.
 */
public class Singer {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String singer_name;
    @SerializedName("genres")
    private List<String> genre;
    @SerializedName("tracks")
    private int tracks;
    @SerializedName("albums")
    private int album_count;
    @SerializedName("link")
    private String link;
    @SerializedName("description")
    private String description;
    @SerializedName("cover")
    private cover cover_type;

    public String getSinger_name() {
        return singer_name;
    }

    public List<String> getGenre() {
        return genre;
    }

    public int getTracks() {
        return tracks;
    }

    public int getAlbum_count() {
        return album_count;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public cover getCoverType() {
        return cover_type;
    }

    public int getId() {
        return id;
    }
}
