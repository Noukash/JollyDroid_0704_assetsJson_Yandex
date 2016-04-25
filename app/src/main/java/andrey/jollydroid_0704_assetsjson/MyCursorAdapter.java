package andrey.jollydroid_0704_assetsjson;


import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.koushikdutta.ion.Ion;

/**
 * Created by Andrey on 07.04.2016.
 */
public class MyCursorAdapter extends CursorAdapter {
    TextView textview_singername,textView_singerGenres,textView_singerAlbumsTracks;
    ImageView imageview_singerphoto;
    @SuppressWarnings("deprecation")
    public MyCursorAdapter(Context context,Cursor c){
        super(context,c);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View newView = inflater.inflate(R.layout.card_items, parent, false);

        return newView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        textview_singername=(TextView)view.findViewById(R.id.SingerName);
        imageview_singerphoto=(ImageView)view.findViewById(R.id.SingerPhoto);
        textView_singerGenres=(TextView)view.findViewById(R.id.SingerGenres);
        textView_singerAlbumsTracks=(TextView)view.findViewById(R.id.SingerAlbumsTracks);
        String small_image_url=cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_SMALL_IMAGE));
        int loader = R.drawable.yandex_small_icon;
        Glide.with(context).load(small_image_url).placeholder(loader).into(imageview_singerphoto);
        textview_singername.setText(cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_NAME)));
        textView_singerGenres.setText(cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_GENRES)));
        textView_singerAlbumsTracks
                .setText(cursor.getInt(cursor.getColumnIndex(DbOpenHelper.COLUMN_ALBUMS)) + " альбомов, " + cursor.getInt(cursor.getColumnIndex(DbOpenHelper.COLUMN_TRACKS)) + " песен");

    }
}



