package subway.v5;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.speech.tts.TextToSpeech;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;

import subway.v5.paths.StationInfo;
import subway.v5.paths.SubPath;
import subway.v5.ui.home.HomeFragment;

import static android.speech.tts.TextToSpeech.ERROR;

public class MainActivity extends AppCompatActivity {
    private static SubPath pathData;
    public static boolean isTts = true;
    public static int theme = 1;

    public static int start = 0;
    public static int end = 0;


    private AppBarConfiguration mAppBarConfiguration;
    private boolean isResult = false;

    //========FOR TTS==========//
    TextToSpeech tts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_schedules, R.id.nav_result, R.id.nav_laf,
                R.id.nav_complaints, R.id.nav_notices, R.id.nav_credits, R.id.nav_settings)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navigationView, navController);

        pathData = new SubPath(getResources().openRawResource(R.raw.input));

        //============FOR TTS Initialize============//
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != ERROR) {
                    // 언어를 선택한다.
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });
    }

    public static StationInfo getPath(int start, int end, int type) {
        return pathData.paths[start][end][type];
    }

    public void openDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.openDrawer(GravityCompat.END);
    }

    public void themeChange() {
        if (theme == 1)
            findViewById(R.id.nav_view).setBackgroundColor(Color.parseColor("#97354C"));
        else
            findViewById(R.id.nav_view).setBackgroundColor(Color.parseColor("#033B73"));

    }

    public void setIsResult(boolean isResult) {
        this.isResult = isResult;
    }

    public boolean getIsResult() {
        return this.isResult;
    }

    //========================FOR TTS===============================//
    public void Speak(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // TTS 객체가 남아있다면 실행을 중지하고 메모리에서 제거한다.
        if (tts != null) {
            tts.stop();
            tts.shutdown();
            tts = null;
        }
    }

    @SuppressWarnings("deprecation")
    private void ttsUnder20(String text) {
        HashMap<String, String> map = new HashMap<>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MessageId");
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, map);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void ttsGreater21(String text) {
        String utteranceId = this.hashCode() + "";
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
    }

}
