package subway.v5.ui.result;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.LinkedList;

import subway.v5.MainActivity;
import subway.v5.R;
import subway.v5.ui.home.HomeViewModel;

public class ResultFragment extends Fragment {
    public View root;
    public ViewPager viewPager;
    private FragmentAdapter fragmentAdapter;

    private long btnNextPressTime = 0;
    private long btnPrePressTime = 0;

    private ResultViewModel resultViewModel;

    public static ResultFragment newInstance() {
        return new ResultFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        // TODO: Use the ViewModel

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        resultViewModel =
                ViewModelProviders.of(this).get(ResultViewModel.class);
        root = inflater.inflate(R.layout.fragment_result, container, false);



        if(MainActivity.theme == 1){
            AppCompatImageButton ms = root.findViewById(R.id.mini_start);
            ms.setImageResource(R.drawable.station_start);
            AppCompatImageButton md = root.findViewById(R.id.mini_dest);
            md.setImageResource(R.drawable.station_dest);
            AppCompatImageButton m1 = root.findViewById(R.id.mini_trans1);
            m1.setImageResource(R.drawable.station_trans);
            AppCompatImageButton m2 = root.findViewById(R.id.mini_trans2);
            m2.setImageResource(R.drawable.station_trans);
            AppCompatImageButton m3 = root.findViewById(R.id.mini_trans3);
            m3.setImageResource(R.drawable.station_trans);
            AppCompatImageButton m4 = root.findViewById(R.id.mini_trans4);
            m4.setImageResource(R.drawable.station_trans);
            AppCompatImageButton m5 = root.findViewById(R.id.mini_trans5);
            m5.setImageResource(R.drawable.station_trans);
            AppCompatImageButton m6 = root.findViewById(R.id.mini_trans6);
            m6.setImageResource(R.drawable.station_trans);
            AppCompatImageView rb = root.findViewById(R.id.result_back);
            rb.setImageResource(R.drawable.result_background);
            AppCompatImageView rcb = root.findViewById(R.id.result_clock_back);
            rcb.setImageResource(R.drawable.time);
        }
        else {
            AppCompatImageButton ms = root.findViewById(R.id.mini_start);
            ms.setImageResource(R.drawable.station_start2);
            AppCompatImageButton md = root.findViewById(R.id.mini_dest);
            md.setImageResource(R.drawable.station_dest2);
            AppCompatImageButton m1 = root.findViewById(R.id.mini_trans1);
            m1.setImageResource(R.drawable.station_trans2);
            AppCompatImageButton m2 = root.findViewById(R.id.mini_trans2);
            m2.setImageResource(R.drawable.station_trans2);
            AppCompatImageButton m3 = root.findViewById(R.id.mini_trans3);
            m3.setImageResource(R.drawable.station_trans2);
            AppCompatImageButton m4 = root.findViewById(R.id.mini_trans4);
            m4.setImageResource(R.drawable.station_trans2);
            AppCompatImageButton m5 = root.findViewById(R.id.mini_trans5);
            m5.setImageResource(R.drawable.station_trans2);
            AppCompatImageButton m6 = root.findViewById(R.id.mini_trans6);
            m6.setImageResource(R.drawable.station_trans2);
            AppCompatImageView rb = root.findViewById(R.id.result_back);
            rb.setImageResource(R.drawable.result_background2);
            AppCompatImageView rcb = root.findViewById(R.id.result_clock_back);
            rcb.setImageResource(R.drawable.time2);
        }


        //뒤로가기버튼
        ImageButton btnBack = root.findViewById(R.id.result_back_btn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        //메뉴버튼
        ImageButton menuButton = root.findViewById(R.id.result_menu_btn);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).openDrawer();
            }
        });

        //결과값 선택
        viewPager = root.findViewById(R.id.path_container);
        fragmentAdapter = new FragmentAdapter(getChildFragmentManager());
        // ViewPager와  FragmentAdapter 연결
        viewPager.setAdapter(fragmentAdapter);

        // ViewPager Item Margin 조정
        viewPager.setClipToPadding(false);
        int dpValue = 50;
        float d = getResources().getDisplayMetrics().density;
        int margin = (int) (dpValue * d);
        viewPager.setPadding(margin, 0, margin, 0);
        viewPager.setPageMargin(margin / 2);

        //페이지 시작 시 default result setting(시계)
        Button btnNext = root.findViewById(R.id.result_next_btn);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (System.currentTimeMillis() > btnNextPressTime + 1000) {
                    btnNextPressTime = System.currentTimeMillis();
                    return;
                }
                if (System.currentTimeMillis() <= btnNextPressTime + 1000) {
                    btnNextPressTime = 0;
                    if (viewPager.getCurrentItem() < 3)
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                }
            }
        });

        Button btnPre = root.findViewById(R.id.result_pre_btn);
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (System.currentTimeMillis() > btnPrePressTime + 1000) {
                    btnPrePressTime = System.currentTimeMillis();
                    return;
                }
                if (System.currentTimeMillis() <= btnPrePressTime + 1000) {
                    btnPrePressTime = 0;
                    if (viewPager.getCurrentItem() > 0)
                        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
                }
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        // FragmentAdapter에 Fragment 추가, Image 개수만큼 추가
        for (int i = 0; i < 4; i++) {
            PathFragment pathFragment = new PathFragment();
            int start = MainActivity.start;
            int end = MainActivity.end;
            //내용물 수정
            Bundle bundle = new Bundle();
            bundle.putInt("type", i);

            pathFragment.setArguments(bundle);
            fragmentAdapter.addItem(i, pathFragment);
        }
        fragmentAdapter.notifyDataSetChanged();

        //ViewPager 2번째 컨텐츠로 설정
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
        viewPager.setCurrentItem(1);
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        View station_dest = root.findViewById(R.id.mini_dest);
        private LinkedList<View> station_trans = new LinkedList<View>();

        TextView txt_current = root.findViewById(R.id.txt_current);
        TextView txt_line = root.findViewById(R.id.txt_line);
        TextView txt_pre = root.findViewById(R.id.txt_pre);
        TextView txt_next = root.findViewById(R.id.txt_next);

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mAnimation(position);
            setStationBtnListener(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        private void mAnimation(int type) {
            station_trans.add(root.findViewById(R.id.mini_trans1));
            station_trans.add(root.findViewById(R.id.mini_trans2));
            station_trans.add(root.findViewById(R.id.mini_trans3));
            station_trans.add(root.findViewById(R.id.mini_trans4));
            station_trans.add(root.findViewById(R.id.mini_trans5));
            station_trans.add(root.findViewById(R.id.mini_trans6));

            LinkedList<ValueAnimator> anims = new LinkedList<ValueAnimator>();
            anims.add(animateStation(station_dest, 200, MainActivity.getPath(MainActivity.start, MainActivity.end, type).getSize()
                    + 1 - MainActivity.getPath(MainActivity.start, MainActivity.end, type).getTransNum()));
            for (int i = 1; i <= MainActivity.getPath(MainActivity.start, MainActivity.end, type).getTransNum(); i++) {
                anims.add(animateStation(station_trans.get(i), 200, MainActivity.getPath(MainActivity.start, MainActivity.end, type).getTransSize(i)));
            }

            for (int i = 1; i < anims.size(); i++) {
                anims.get(i).start();
            }
            anims.get(0).start();
        }

        private void setStationBtnListener(final int position) {

            txt_current.setText("");
            txt_line.setText("Line");
            txt_pre.setText("");
            txt_next.setText("");

            LinkedList<View> btn_image = new LinkedList<View>();
            btn_image.add(root.findViewById(R.id.btn_station1));
            btn_image.add(root.findViewById(R.id.btn_station2));
            btn_image.add(root.findViewById(R.id.btn_station3));
            btn_image.add(root.findViewById(R.id.btn_station4));
            btn_image.add(root.findViewById(R.id.btn_station5));
            btn_image.add(root.findViewById(R.id.btn_station6));
            btn_image.add(root.findViewById(R.id.btn_station7));
            btn_image.add(root.findViewById(R.id.btn_station8));
            btn_image.add(root.findViewById(R.id.btn_station9));
            btn_image.add(root.findViewById(R.id.btn_station10));
            btn_image.add(root.findViewById(R.id.btn_station11));
            btn_image.add(root.findViewById(R.id.btn_station12));
            btn_image.add(root.findViewById(R.id.btn_station13));
            btn_image.add(root.findViewById(R.id.btn_station14));
            btn_image.add(root.findViewById(R.id.btn_station15));
            btn_image.add(root.findViewById(R.id.btn_station16));
            btn_image.add(root.findViewById(R.id.btn_station17));
            btn_image.add(root.findViewById(R.id.btn_station18));
            btn_image.add(root.findViewById(R.id.btn_station19));

            for (int i = 0; i < 19; i++) {
                btn_image.get(i).setOnClickListener(new StationBtnListener(position, i + 1));
            }

            for (int i = 0; i <= MainActivity.getPath(MainActivity.start, MainActivity.end, position).getTransNum(); i++) {
                station_trans.get(i).setOnClickListener(new StationBtnListener(position, MainActivity.getPath(MainActivity.start, MainActivity.end, position).getTransSize(i)));
            }

            root.findViewById(R.id.mini_start).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txt_current.setText(convertStation(MainActivity.start));
                    txt_line.setText("Line " + convertLine(MainActivity.start));
                    txt_pre.setText("");
                    txt_next.setText(convertStation(MainActivity.getPath(MainActivity.start, MainActivity.end, position).getPath().get(0)));
                }
            });

            root.findViewById(R.id.mini_dest).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txt_current.setText(convertStation(MainActivity.end));
                    txt_line.setText("도착");
                    txt_pre.setText(convertStation(MainActivity.getPath(MainActivity.start, MainActivity.end, position).getPath().get(MainActivity.getPath(MainActivity.start, MainActivity.end, position).getSize() - 1)));
                    txt_next.setText("X");
                }
            });
        }
    }

    public class StationBtnListener implements View.OnClickListener {
        int type;
        int num;

        StationBtnListener(int type, int num) {
            this.type = type;
            this.num = num;
        }

        @Override
        public void onClick(View v) {
            TextView txt_current = root.findViewById(R.id.txt_current);
            TextView txt_line = root.findViewById(R.id.txt_line);
            TextView txt_pre = root.findViewById(R.id.txt_pre);
            TextView txt_next = root.findViewById(R.id.txt_next);

            int station_seq = MainActivity.getPath(MainActivity.start, MainActivity.end, type).getStation(num);

            String a = Integer.toString(station_seq);
            String b = Integer.toString(MainActivity.getPath(MainActivity.start, MainActivity.end, type).getSize());

            txt_line.setText(b);
            txt_current.setText(a);
            if (station_seq == -1) {
                txt_current.setText("");
                txt_line.setText("Line");
                txt_pre.setText("");
                txt_next.setText("");
            } else {
                txt_current.setText(convertStation(MainActivity.getPath(MainActivity.start, MainActivity.end, type).getPath().get(station_seq)));
                if (station_seq + 1 == MainActivity.getPath(MainActivity.start, MainActivity.end, type).getPath().size())
                    txt_line.setText("Line " + convertLine(MainActivity.getPath(MainActivity.start, MainActivity.end, type).getPath().get(station_seq)));
                else if (MainActivity.getPath(MainActivity.start, MainActivity.end, type).isTrans(MainActivity.getPath(MainActivity.start, MainActivity.end, type)
                        .getPath().get(station_seq), MainActivity.getPath(MainActivity.start, MainActivity.end, type).getPath().get(station_seq + 1)))
                    txt_line.setText("  환승\n" + convertLine(MainActivity.getPath(MainActivity.start, MainActivity.end, type).getPath().get(station_seq))
                            + " to " + convertLine(MainActivity.getPath(MainActivity.start, MainActivity.end, type).getPath().get(station_seq + 1)));
                else
                    txt_line.setText("Line " + convertLine(MainActivity.getPath(MainActivity.start, MainActivity.end, type).getPath().get(station_seq)));
                if (num - 1 == 0) txt_pre.setText(convertStation(MainActivity.start));
                else
                    txt_pre.setText(convertStation(MainActivity.getPath(MainActivity.start, MainActivity.end, type).getPath()
                            .get(MainActivity.getPath(MainActivity.start, MainActivity.end, type).getStation(num - 1))));
                if (station_seq + 1 == MainActivity.getPath(MainActivity.start, MainActivity.end, type).getPath().size())
                    txt_next.setText(convertStation(MainActivity.end));
                else
                    txt_next.setText(convertStation(MainActivity.getPath(MainActivity.start, MainActivity.end, type).getPath()
                            .get(MainActivity.getPath(MainActivity.start, MainActivity.end, type).getStation(num + 1))));
            }

            /*if (station_seq != -1) {
                txt_current.setText(convertStation(MainActivity.getPath(MainActivity.start, MainActivity.end, type).getPath()
                        .get(station_seq)));
                if(num - 1 < 0) txt_pre.setText("");
                else txt_pre.setText(convertStation(MainActivity.getPath(MainActivity.start, MainActivity.end, type).getPath()
                        .get(MainActivity.getPath(MainActivity.start, MainActivity.end, type).getStation(num - 1))));
                if(num + 1 >= MainActivity.getPath(MainActivity.start, MainActivity.end, type).getPath().size()) txt_next.setText("");
                else txt_next.setText(convertStation(MainActivity.getPath(MainActivity.start, MainActivity.end, type).getPath()
                        .get(MainActivity.getPath(MainActivity.start, MainActivity.end, type).getStation(num + 1))));
                if(station_seq > 30) txt_line.setText("");
                else txt_line.setText("Line " + convertLine(MainActivity.getPath(MainActivity.start, MainActivity.end, type).getPath()
                        .get(MainActivity.getPath(MainActivity.start, MainActivity.end, type).getStation(num + 1))));
            } else {
                txt_current.setText("");
                txt_line.setText("");
                txt_pre.setText("");
                txt_next.setText("");
            }*/
        }
    }

    private String convertStation(int num) {
        switch (num) {
            case 1:
                return "A1";
            case 2:
                return "A2";
            case 3:
                return "A3";
            case 4:
                return "A4";
            case 5:
                return "B1";
            case 6:
                return "B2";
            case 7:
                return "B3";
            case 8:
                return "B4";
            case 9:
                return "B5";
            case 10:
                return "B6";
            case 11:
                return "B7";
            case 12:
                return "B8";
            case 13:
                return "C1";
            case 14:
                return "C2";
            case 15:
                return "D1";
            case 16:
                return "D2";
            case 17:
                return "D3";
            case 18:
                return "E1";
            case 19:
                return "E2";
            case 20:
                return "E3";
            case 21:
                return "E4";
            case 22:
                return "F1";
            case 23:
                return "F2";
            case 24:
                return "F3";
            case 25:
                return "F4";
            case 26:
                return "F5";
            case 27:
                return "G1";
            case 28:
                return "G2";
            case 29:
                return "G3";
            case 30:
                return "G4";
            case 31:
                return "T1";
            case 32:
                return "T1";
            case 33:
                return "T2";
            case 34:
                return "T2";
            case 35:
                return "T2";
            case 36:
                return "T3";
            case 37:
                return "T3";
            case 38:
                return "T4";
            case 39:
                return "T4";
            case 40:
                return "T5";
            case 41:
                return "T5";
            case 42:
                return "T6";
            case 43:
                return "T6";
            case 44:
                return "T7";
            case 45:
                return "T7";
            case 46:
                return "T8";
            case 47:
                return "T8";
            case 48:
                return "T8";
            case 49:
                return "T9";
            case 50:
                return "T9";
            case 51:
                return "T10";
            case 52:
                return "T10";
            default:
                return "";
        }
    }

    private String convertLine(int num) {
        switch (num) {
            case 1:
                return "A";
            case 2:
                return "A";
            case 3:
                return "A";
            case 4:
                return "A";
            case 5:
                return "B";
            case 6:
                return "B";
            case 7:
                return "B";
            case 8:
                return "B";
            case 9:
                return "B";
            case 10:
                return "B";
            case 11:
                return "B";
            case 12:
                return "B";
            case 13:
                return "C";
            case 14:
                return "C";
            case 15:
                return "D";
            case 16:
                return "D";
            case 17:
                return "D";
            case 18:
                return "E";
            case 19:
                return "E";
            case 20:
                return "E";
            case 21:
                return "E";
            case 22:
                return "F";
            case 23:
                return "F";
            case 24:
                return "F";
            case 25:
                return "F";
            case 26:
                return "F";
            case 27:
                return "G";
            case 28:
                return "G";
            case 29:
                return "G";
            case 30:
                return "G";
            case 31:
                return "A";
            case 32:
                return "F";
            case 33:
                return "A";
            case 34:
                return "B";
            case 35:
                return "C";
            case 36:
                return "A";
            case 37:
                return "B";
            case 38:
                return "B";
            case 39:
                return "C";
            case 40:
                return "C";
            case 41:
                return "D";
            case 42:
                return "D";
            case 43:
                return "E";
            case 44:
                return "A";
            case 45:
                return "D";
            case 46:
                return "B";
            case 47:
                return "F";
            case 48:
                return "G";
            case 49:
                return "A";
            case 50:
                return "G";
            case 51:
                return "E";
            case 52:
                return "G";
            default:
                return "";
        }
    }

    private ValueAnimator animateStation(final View station, long duration, int repeat) {
        ValueAnimator anim = ValueAnimator.ofInt(90, 90 - repeat * 18);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int val = (Integer) animation.getAnimatedValue();
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) station.getLayoutParams();
                layoutParams.circleAngle = val;
                station.setLayoutParams(layoutParams);
            }
        });
        anim.setDuration(duration * repeat);
        anim.setInterpolator(new LinearInterpolator());


        return anim;
    }
}
