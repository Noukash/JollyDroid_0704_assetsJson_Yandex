package andrey.jollydroid_0704_assetsjson;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ListView mycardlist;

    SQLiteDatabase mDb;
    Cursor myCursor;
    MyCursorAdapter myAdapter;
    List<Singer> data;
    private DbOpenHelper dbOpenHelper;
    SwipeRefreshLayout swipeRefreshLayout;
    public static final String DB_ID="DB_ID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mycardlist = (ListView) findViewById(R.id.listview);
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipeRefresherLayout);
        //Берем данные из базы данных при открытии приложения
        dbOpenHelper = new DbOpenHelper(this);
        mDb = dbOpenHelper.getReadableDatabase();
        myCursor = mDb.query(dbOpenHelper.DB_TABLE, null, null, null, null, null, null);
        myAdapter = new MyCursorAdapter(this, myCursor);
        mycardlist.setAdapter(myAdapter);
        mycardlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),SingerDetailsActivity.class);
                intent.putExtra(DB_ID,id);
                startActivity(intent);
            }
        });
        //при сдвиге экрана вниз(Swipe) обновляем данные из интернета
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                    //проверяем есть ли интернет
                if (haveNetworkConnection()) {
                    TakeDataFromJson(dbOpenHelper);
                }
                else{
                    Toast.makeText(getApplicationContext(),"No internet connection detected",Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }

            }
        });
        //если есть интернет обновляем информацию
        if(haveNetworkConnection()) {
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(true);
                    TakeDataFromJson(dbOpenHelper);

                }
            });
        }
        else{
            Toast.makeText(getApplicationContext(),"No internet connection detected",Toast.LENGTH_SHORT).show();

        }
    }


    public void TakeDataFromJson(final DbOpenHelper dbOpenHelper) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://download.cdn.yandex.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<List<Singer>> call = request.getJSON();
        call.enqueue(new Callback<List<Singer>>(){

            @Override
            public void onResponse(Call<List<Singer>> call, Response<List<Singer>> response) {
                data=response.body();
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            //добавляем данные с сервера в дб и обновляем адаптер
                            dbOpenHelper.InsertDataInDB(data);
                            myCursor=mDb.query(dbOpenHelper.DB_TABLE,null,null,null,null,null,null);
                            myAdapter=new MyCursorAdapter(getApplicationContext(),myCursor);
                            mycardlist.setAdapter(myAdapter);
                            swipeRefreshLayout.setRefreshing(false);

                        }
                    });



            }

            @Override
            public void onFailure(Call<List<Singer>> call, Throwable t) {
                Log.d("Error",t.getMessage());
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }
    //check if there is internet
    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }





}

