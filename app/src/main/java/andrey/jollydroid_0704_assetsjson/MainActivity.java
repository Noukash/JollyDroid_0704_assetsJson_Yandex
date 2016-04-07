package andrey.jollydroid_0704_assetsjson;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView mycardlist;
    DbOpenHelper dbOpenHelper;
    SQLiteDatabase mDb;
    Cursor myCursor;
    MyCursorAdapter myAdapter;
    List<Singer> singers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      mycardlist=(ListView)findViewById(R.id.listview);
        dbOpenHelper=new DbOpenHelper(this);
        mDb=dbOpenHelper.getReadableDatabase();
        myCursor=mDb.query(dbOpenHelper.DB_TABLE, null, null, null, null, null, null);
        myAdapter=new MyCursorAdapter(this,myCursor);
        mycardlist.setAdapter(myAdapter);


    }




}

