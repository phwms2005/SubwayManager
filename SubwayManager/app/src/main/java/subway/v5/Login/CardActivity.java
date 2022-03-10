package subway.v5.Login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kakao.auth.ApiResponseCallback;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.network.callback.ResponseCallback;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.UserResponse;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import subway.v5.MainActivity;
import subway.v5.R;

public class CardActivity extends AppCompatActivity {

    //For LoginView Buttons
    private Context mContext;
    private ImageButton btn_custom_login;
    private LoginButton btn_kakao_login;
    private Button btn_custom_logout;
    private SessionCallback callback;

    //For Saving User Data
    private long user_id;
    private String user_nickname;
    private String user_image_url;
    private String user_email;
    private String user_balance;
    private Bitmap bitmap;
    private ImageView user_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Login 로그인정상작동
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        if (MainActivity.theme == 1) {
            findViewById(R.id.layout_card).setBackgroundColor(Color.parseColor("#C84B4B"));
        } else {
            findViewById(R.id.layout_card).setBackgroundColor(Color.parseColor("#033B73"));
        }

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        Session.getCurrentSession().checkAndImplicitOpen();

        mContext = getApplicationContext();

        btn_custom_login = (ImageButton) findViewById(R.id.btn_custom_login);
        btn_kakao_login = (LoginButton) findViewById(R.id.btn_kakao_login);
        btn_custom_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_kakao_login.performClick();
            }
        });

        //Logout 정상작동
        btn_custom_logout = (Button) findViewById(R.id.btn_custom_logout);
        btn_custom_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CardActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                    }
                });
            }
        });
    }


    @Override
    //뒤로가기 눌렀을 때
    public void onBackPressed() {
        super.onBackPressed();

    }

    //Session Callback
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            requestUpdateProfile();
            Toast.makeText(CardActivity.this, "Session Opened", Toast.LENGTH_SHORT).show();
            requestMe();
            //redirectSignupActivity();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Toast.makeText(CardActivity.this, "Session Closed", Toast.LENGTH_SHORT).show();
            if (exception != null) {
                Logger.e(exception);
            }
        }
    }

    protected void redirectSignupActivity() {
        Toast.makeText(CardActivity.this, "RedirectSignupActivity", Toast.LENGTH_SHORT).show();
        /*
        final Intent intent = new Intent(this, LoginViewActivity.class);
        startActivity(intent);
        finish();
        */
    }

    public void requestMe() {
        UserManagement.getInstance().me(new MeV2ResponseCallback() {

            @Override
            //로그인 되어있는 경우
            public void onSuccess(MeV2Response response) {
                user_email = response.getKakaoAccount().getEmail();
                user_id = response.getId();
                user_image_url = response.getKakaoAccount().getProfile().getProfileImageUrl();
                user_nickname = response.getKakaoAccount().getProfile().getNickname();
                user_balance = response.getProperties().get("balance");
                Toast.makeText(CardActivity.this, "Already Logged IN", Toast.LENGTH_SHORT).show();

                //TODO 새로운 레이아웃에 사용자 아이디 표시.
                Intent intent = new Intent(CardActivity.this, UserDataViewActivity.class);
                intent.putExtra("user_id", user_id);
                intent.putExtra("user_email", user_email);
                intent.putExtra("user_image_url", user_image_url);
                intent.putExtra("user_nickname", user_nickname);
                intent.putExtra("user_balance", user_balance);

                try {
                    URL url = new URL(user_image_url);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    //conn.connect();
                    //InputStream is = conn.getInputStream();
                    //bitmap = BitmapFactory.decodeStream(is);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //user_image.setImageBitmap(bitmap);

                startActivity(intent);
            }

            @Override
            //로그인 안되어 있을 경우 -> LoginViewActivity
            public void onFailure(ErrorResult errorResult) {
                Toast.makeText(CardActivity.this, "Failed to get user info.", Toast.LENGTH_SHORT).show();
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);
            }

            @Override
            //세션이 닫혀있는경우 -> LoginViewActivity
            public void onSessionClosed(ErrorResult errorResult) {
                Toast.makeText(CardActivity.this, "Session Closed", Toast.LENGTH_SHORT).show();
            }

            public void redirectMainActivity() {
                Toast.makeText(getApplication(), "Redirect to MainActivity", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void requestUpdateProfile() {
        final Map<String, String> properties = new HashMap<String, String>();
        properties.put("balance", "10000");
        UserManagement.getInstance().requestUpdateProfile(new ApiResponseCallback<Long>() {
            @Override
            public void onSuccess(Long user_id) {

            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
            }
        }, properties);
    }
}


//CLASS TASK
//https://youngest-programming.tistory.com/11
// < >안에 들은 자료형은 순서대로 doInBackground, onProgressUpdate, onPostExecute의 매개변수 자료형을 뜻한다.(내가 사용할 매개변수타입을 설정하면된다)
class BackgroundTask extends AsyncTask<Integer, Integer, Integer> {
    TextView textView;
    ProgressBar progress;
    //백그라운드Task
    BackgroundTask task;
    int value;

    //초기화 단계에서 사용한다. 초기화관련 코드를 작성했다.
    protected void onPreExecute() {
        value = 0;
        progress.setProgress(value);
    }

    //스레드의 백그라운드 작업 구현
    //여기서 매개변수 Intger ... values란 values란 이름의 Integer배열이라 생각하면된다.
    //배열이라 여러개를 받을 수 도 있다. ex) excute(100, 10, 20, 30); 이런식으로 전달 받으면 된다.
    protected Integer doInBackground(Integer... values) {
        //isCancelled()=> Task가 취소되었을때 즉 cancel당할때까지 반복
        while (isCancelled() == false) {
            value++;
            //위에 onCreate()에서 호출한 excute(100)의 100을 사용할려면 이런식으로 해줘도 같은 결과가 나온다.
            //밑 대신 이렇게해도됨 if (value >= values[0].intValue())
            if (value >= 100) {
                break;
            } else {
                //publishProgress()는 onProgressUpdate()를 호출하는 메소드(그래서 onProgressUpdate의 매개변수인 int즉 Integer값을 보냄)
                //즉, 이 메소드를 통해 백그라운드 스레드작업을 실행하면서 중간중간  UI에 업데이트를 할 수 있다.

                //백그라운드에서는 UI작업을 할 수 없기 때문에 사용
                publishProgress(value);
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
        }

        return value;
    }

    //UI작업 관련 작업 (백그라운드 실행중 이 메소드를 통해 UI작업을 할 수 있다)
    //publishProgress(value)의 value를 값으로 받는다.values는 배열이라 여러개 받기가능
    protected void onProgressUpdate(Integer... values) {
        progress.setProgress(values[0].intValue());
        textView.setText("현재 진행 값 : " + values[0].toString());
    }


    //이 Task에서(즉 이 스레드에서) 수행되던 작업이 종료되었을 때 호출됨
    protected void onPostExecute(Integer result) {
        progress.setProgress(0);
        textView.setText("완료되었습니다");
    }

    //Task가 취소되었을때 호출
    protected void onCancelled() {
        progress.setProgress(0);
        textView.setText("취소되었습니다");
    }
}


