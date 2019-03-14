package com.example.para14;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    //объект текстового поля
    public TextView tw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //производим поиск и сохранение в памяти текстового поля
        tw = (TextView)findViewById(R.id.idTW);
        //создание класса для работы с HTTP из нового пакета
        String url = "https://regres.in/api/users?page=2";
        //создаем и запускаем процесс запроса к сайту
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        //запускаем обрабаты. результат выполнения запросов
        client.newCall(request).enqueue(new Callback() {
            //выполнение запроса при неудачном запросе
            @Override
            public void onFailure(Call call, IOException e) {
                tw.setText("error connect");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            //проверяем наличие ответа
                if (response.isSuccessful()) {

                    final String myResponse = response.body().string();
                MainActivity.this.runOnUiThread(new Runnable() {
                    //запуск процесса
                    @Override
                    public void run() {
                        //отображение полученных данных
                    tw.setText(myResponse);
                    }
                });
                }
            }
        });

    }
}
