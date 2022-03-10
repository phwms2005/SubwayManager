package subway.v5.ui.schedule;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import subway.v5.MainActivity;
import subway.v5.R;
import subway.v5.ui.laf.LafViewModel;

public class SchedulesFragment extends Fragment {

    private LafViewModel lafViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_laf, container, false);
        WebView webView = (WebView)v.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadUrl("https://www.osan.go.kr/m/contents/contents.do?conts=10/10_03.jsp&mId=10030000");
        return v;
        /*
        lafViewModel =
                ViewModelProviders.of(this).get(LafViewModel.class);
        View root = inflater.inflate(R.layout.fragment_laf, container, false);
        */
        //뒤로가기버튼
        /*
        ImageButton btnBack = root.findViewById(R.id.laf_back_btn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        //메뉴버튼
        ImageButton menuButton = root.findViewById(R.id.laf_menu_btn);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).openDrawer();
            }
        });
         */
        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.naver.com"));
        //startActivity(intent);

        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.osan.go.kr/m/contents/contents.do?conts=10/10_03.jsp&mId=10030000")));

        //getActivity().onBackPressed();
        //getActivity();


        //return root;
    }
}