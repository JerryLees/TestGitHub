package com.example.liruijie.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    private EditText editText;
    private Button btn1;
    private Button btn2;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        listView = (ListView) findViewById(R.id.listView);




        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataFromStrings(false);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataFromStrings(true);
            }
        });


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("http://www.baidu.com").build();

        Call call  = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


            }
        });


        //哈哈 我提交了 看看能不能看到

//        Picasso.with(this).load().into();


    }


    /**
     * 获取大于或者小于0的数字
     *
     * @param isBigThanZero 大于0还是小于0
     * @return
     */
    private void getDataFromStrings(boolean isBigThanZero) {

        String[] arrays = editText.getText().toString().split(",");
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            Integer num = Integer.parseInt(arrays[i]);
            if (num > 0)
                list1.add(num.toString());
            else
                list2.add(num.toString());
        }

        ArrayAdapter<String> adapter;

        if (isBigThanZero)
            adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, (String[]) list1.toArray(new String[list1.size()]));
        else
            adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, (String[]) list2.toArray(new String[list2.size()]));

        listView.setAdapter(adapter);
    }
}
