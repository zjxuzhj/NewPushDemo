package com.zhj.newpushdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Log.i("a",  FirebaseInstanceId.getInstance().getToken());

    }

    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("application/json;charset=utf-8");
    private final OkHttpClient client = new OkHttpClient();

    public void run() throws Exception {
        String postBody = " {\n" +
                "    \"to\" : \"eNh8RzL09LY:APA91bEKwVeY-FGl_h-9oTZ7BZQJ79xR_EtPBpnoq3ecuPwTpbLWgrVaTuqjoakDZCuf0SVVsc5QbnOOAWYpHuLH7_QYiwT7LE2XMSA_rokM6NB0HlwfcuY-oYNnZsqxveumhg7tR0G2\",\n" +
                "    \"notification\" : {\n" +
                "      \"body\" : \"great match!\",\n" +
                "      \"title\" : \"Portugal vs. Denmark\",\n" +
                "      \"icon\" : \"myicon\"\n" +
                "    }\n" +
                "  }";
        Request request = new Request.Builder()
                .url("https://fcm.googleapis.com/fcm/send")
                .header("Content-Type", "application/json;charset=utf-8")
                .header("Authorization", "key:AAAAvt8PsTc:APA91bFjsbsccwMDjxr7m04Fm9qEKVesfpm_3Gdy-9Wv_TC33nmi-9o6ksbUK1eK-TIyn9q6khLF7MHRSqj0DbxPyN4SVPZED0cEFE5E9ysz5VIZFZkOUHjuws7cKKfhSNhlQ9cYgL7kcdcjIOi7xYVYyyWSOjCaew")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String a = response.body().string();
                Log.i("response", "run: " + a);
            }
        });

    }
}
