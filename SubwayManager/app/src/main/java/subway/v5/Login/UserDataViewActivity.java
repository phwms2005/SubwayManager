package subway.v5.Login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import subway.v5.MainActivity;
import subway.v5.R;

public class UserDataViewActivity extends AppCompatActivity {

    long user_id;
    String user_profile_image_url;
    String user_nickname;
    String user_balance;
    ImageView user_profile_image;
    Bitmap bitmap;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdataview);

        if(MainActivity.theme == 1){
            findViewById(R.id.user_id).setBackgroundColor(Color.parseColor("#C84B4B"));
        }
        else {
            findViewById(R.id.user_id).setBackgroundColor(Color.parseColor("#033B73"));
        }
        //LoginViewActivity  (intent)-> UserDataViewActivity
        Intent intent = getIntent();

        user_id = intent.getExtras().getLong("user_id");
        user_profile_image_url = intent.getExtras().getString("user_image_url");
        user_nickname = intent.getExtras().getString("user_nickname");
        user_balance = intent.getExtras().getString("user_balance");
        TextView textView_user_nickname = (TextView)findViewById(R.id.user_nickname);
        textView_user_nickname.setText("NickName : "+ user_nickname);
        TextView textView_user_balance = (TextView)findViewById(R.id.user_balance);
        textView_user_balance.setText("Your Balance : "+ user_balance);
    }

    @Override
    //뒤로가기 눌렀을 때
    public void onBackPressed() {
        super.onBackPressed();
    }
}
