package subway.v5.Login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.helper.log.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import subway.v5.MainActivity;
import subway.v5.R;

public class CheckLoginActivity extends Activity {
    //Profile profile = new Profile();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklogin);
        //requestMe();
    }

    public void requestMe(){
        List<String> keys = new ArrayList<>();
        keys.add("properties.nickname");
        keys.add("properties.profile_image");
        keys.add("kakao_account.email");

        UserManagement.getInstance().me(keys, new MeV2ResponseCallback() {
            @Override
            //로그인 되어있는 경우
            public void onSuccess(MeV2Response response) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Logger.d("user_id : " + response.getId());
                Logger.d("email: " + response.getKakaoAccount().getEmail());



                //TODO 새로운 레이아웃에 사용자 아이디 표시.
                redirectMainActivity();
            }

            @Override
            //로그인 안되어 있을 경우 -> CardActivity
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);
            }

            @Override
            //세션이 닫혀있는경우 -> CardActivity
            public void onSessionClosed(ErrorResult errorResult) {

            }

            public void redirectLoginActivity() {

            }

            public void redirectMainActivity() {

            }
        });
    }

}
