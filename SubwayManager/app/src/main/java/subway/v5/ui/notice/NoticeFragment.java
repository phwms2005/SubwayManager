package subway.v5.ui.notice;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import subway.v5.MainActivity;
import subway.v5.R;

public class NoticeFragment extends Fragment {

    private NoticeViewModel noticeViewModel;
    //Linearlayout과 공지 목록을 포함하는 ScrollView
    private ScrollView list_container;
    private LinearLayout notice_list;
    private int month, date;
    private String content;

    public Button makeNoticeText(String content){
        //공지 내용 글씨 크기
        final int NOTICE_CONTENT_SIZE = 20;

        Button temp = new Button(getContext());
        temp.setBackgroundResource(R.drawable.notice_area);
        temp.setText(content);
        //표현할 최대 라인 수
        temp.setMaxLines(2);
        //텍스트가 길면 ... 사용
        temp.setEllipsize(TextUtils.TruncateAt.END);
        temp.setTextSize(NOTICE_CONTENT_SIZE);
        temp.setClickable(false);
        temp.setPadding(15, 0, 0, 0);

        return temp;
    }

    public Button makeNoticeImage(int month, int date){
        final String[] m = {"", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

        Button temp = new Button(getContext());

        temp.setId(R.id.notice_image);
        temp.setTextSize(30);
        temp.setTextColor(Color.WHITE);
        temp.setBackgroundResource(R.drawable.notice_list);
        temp.setText(m[month] + "\n" + date);

        return temp;
    }

    public Button makeNoticeImage2(int month, int date){
        final String[] m = {"", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

        Button temp = new Button(getContext());

        temp.setId(R.id.notice_image);
        temp.setTextSize(30);
        temp.setTextColor(Color.WHITE);
        temp.setBackgroundResource(R.drawable.notice_list2);
        temp.setText(m[month] + "\n" + date);

        return temp;
    }

    public void addNotice(int month, int date, String content){
        RelativeLayout rl = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        Button text = makeNoticeText(content);

        if(MainActivity.theme == 1){
            Button image = makeNoticeImage(month, date);
            rp.topMargin = 40;
            rp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            image.setLayoutParams(rp);
            rl.addView(image);
            rp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            rp.topMargin = 80;
            rp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
            rp.addRule(RelativeLayout.RIGHT_OF, image.getId());
        }
        else {
            Button image = makeNoticeImage2(month, date);
            rp.topMargin = 40;
            rp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            image.setLayoutParams(rp);
            rl.addView(image);
            rp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            rp.topMargin = 80;
            rp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
            rp.addRule(RelativeLayout.RIGHT_OF, image.getId());
        }


        text.setLayoutParams(rp);
        rl.addView(text);


        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lp.gravity = Gravity.CENTER;

        rl.setLayoutParams(lp);

        notice_list.addView(rl);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        String line;
        String notice[];
        InputStream txtResource = getResources().openRawResource(R.raw.notice);
        InputStreamReader inputreader = new InputStreamReader(txtResource);
        BufferedReader buffreader = new BufferedReader(inputreader);
        noticeViewModel =
                ViewModelProviders.of(this).get(NoticeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notice, container, false);

        if(MainActivity.theme == 1){
            root.findViewById(R.id.noticeLayout).setBackgroundResource(R.drawable.notice);
            root.findViewById((R.id.notice_view)).setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else {
            root.findViewById(R.id.noticeLayout).setBackgroundResource(R.drawable.notice2);
            root.findViewById((R.id.notice_view)).setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        //뒤로가기버튼
        ImageButton btnBack = root.findViewById(R.id.notices_back_btn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        //메뉴버튼
        ImageButton menuButton = root.findViewById(R.id.notices_menu_btn);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).openDrawer();
            }
        });

        //멤버 변수 할당
        //멤버 변수 할당
        list_container = (ScrollView) root.findViewById(R.id.notice_view);
        notice_list = (LinearLayout) root.findViewById(R.id.notice_list);

        try {
            while ((line = buffreader.readLine()) != null) {
                notice = line.split("\\t");
                month = Integer.parseInt(notice[0]);
                date = Integer.parseInt(notice[1]);
                content = notice[2];
                addNotice(month, date, content);
            }
        } catch (IOException e) {
            Log.d("log","읽기 실패");
        }
        return root;
    }
}