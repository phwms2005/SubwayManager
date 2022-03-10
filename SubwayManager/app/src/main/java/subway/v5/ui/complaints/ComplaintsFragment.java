package subway.v5.ui.complaints;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import subway.v5.MainActivity;
import subway.v5.R;

public class ComplaintsFragment extends Fragment implements View.OnClickListener{

    private ComplaintsViewModel complaintsViewModel;

    //전송 이벤트
    @Override
    public void onClick(View v) {
        Intent email = new Intent(Intent.ACTION_SEND);
        String[] to = {"seventhseven@naver.com"};
        EditText complanContent = (EditText)(getView().findViewById(R.id.complainContent));
        //입력된 내용이 없을 시 반응 없음
        if (complanContent.getText().toString().length() == 0) {
            Toast.makeText(getActivity(), "민원 내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            email.setType("plain/Text");
            email.putExtra(Intent.EXTRA_EMAIL, to);
            email.putExtra(Intent.EXTRA_SUBJECT, "민원 제기");
            email.putExtra(Intent.EXTRA_TEXT, complanContent.getText().toString());
            email.setType("message/rfc822");
            startActivity(email);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        complaintsViewModel =
                ViewModelProviders.of(this).get(ComplaintsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_complaints, container, false);

        if(MainActivity.theme == 1){
            root.findViewById(R.id.compl_id).setBackgroundColor(Color.parseColor("#C84B4B"));
            AppCompatImageView iv = root.findViewById(R.id.imageView3);
            iv.setImageResource(R.drawable.e_mail_background);
            root.findViewById(R.id.frameLayout).setBackgroundColor(Color.parseColor("#E6A5A5"));
            AppCompatImageButton ib = root.findViewById(R.id.sendButton);
            ib.setImageResource(R.drawable.send_button);
        }
        else {
            root.findViewById(R.id.compl_id).setBackgroundColor(Color.parseColor("#033B73"));
            AppCompatImageView iv = root.findViewById(R.id.imageView3);
            iv.setImageResource(R.drawable.e_mail_background2);
            root.findViewById(R.id.frameLayout).setBackgroundColor(Color.parseColor("#198AFB"));
            AppCompatImageButton ib = root.findViewById(R.id.sendButton);
            ib.setImageResource(R.drawable.send_button2);
        }

        //뒤로가기버튼
        ImageButton btnBack = root.findViewById(R.id.complaints_back_btn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        //메뉴버튼
        ImageButton menuButton = root.findViewById(R.id.complaints_menu_btn);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).openDrawer();
            }
        });

        //전송버튼
        ImageButton sendButton = root.findViewById(R.id.sendButton);
        sendButton.setOnClickListener(this);
        return root;
    }
}