package andrey.jollydroid_0704_assetsjson;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.koushikdutta.ion.Ion;

import org.w3c.dom.Text;

public class SingerDetailsActivity extends AppCompatActivity {
    long db_id;
    String selection,big_image_url;
    DbOpenHelper dbOpenHelper;
    SQLiteDatabase mDB;
    Cursor myPersonalCursor;
    TextView Genres,AlbumsAndTracks,Biography;
    ImageView BigPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singer_details);
        Genres=(TextView)findViewById(R.id.genres);
        AlbumsAndTracks=(TextView)findViewById(R.id.AlbumsAndTracks);
        Biography=(TextView)findViewById(R.id.SingerBiography);
        BigPhoto=(ImageView)findViewById(R.id.BigSingerPhoto);
        Intent intent=getIntent();
        db_id=intent.getLongExtra(MainActivity.DB_ID,0);
        selection="_id=?";
        String[] selectionargs={db_id+""};
        dbOpenHelper=new DbOpenHelper(this);
        mDB=dbOpenHelper.getReadableDatabase();
        myPersonalCursor=mDB.query(dbOpenHelper.DB_TABLE,null,selection,selectionargs,null,null,null);
       if(myPersonalCursor!=null&& myPersonalCursor.moveToFirst()){
           String GenresText,AlbumsAndTracksText,BiographyText,Title;
           big_image_url=myPersonalCursor.getString(myPersonalCursor.getColumnIndex(dbOpenHelper.COLUMN_BIG_IMAGE));
           int loader = R.drawable.yandex_small_icon;

           Glide.with(getApplicationContext()).load(big_image_url).placeholder(loader).into(BigPhoto);
           Title=myPersonalCursor.getString(myPersonalCursor.getColumnIndex(dbOpenHelper.COLUMN_NAME));
           setTitle(Title);
           GenresText=myPersonalCursor.getString(myPersonalCursor.getColumnIndex(dbOpenHelper.COLUMN_GENRES));
           AlbumsAndTracksText=myPersonalCursor.getInt(myPersonalCursor.getColumnIndex(DbOpenHelper.COLUMN_ALBUMS))
                   + " альбомов " + "   "+"\u2022"+"   "+myPersonalCursor.getInt(myPersonalCursor.getColumnIndex(DbOpenHelper.COLUMN_TRACKS)) + " песен";
           BiographyText=myPersonalCursor.getString(myPersonalCursor.getColumnIndex(dbOpenHelper.COLUMN_DESCRIPTION));

           Genres.setText(GenresText);
           AlbumsAndTracks.setText(AlbumsAndTracksText);
           Biography.setText(BiographyText);

       }
        mDB.close();
        myPersonalCursor.close();


    }
}
