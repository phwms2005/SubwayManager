package subway.v5.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

import subway.v5.MainActivity;
import subway.v5.R;
import subway.v5.Login.CardActivity;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private View root;

    private int startStation = 0;
    private int destStation = 0;

    private ImageButton menuButton;
    private FloatingActionButton gpsButton;
    private FloatingActionButton cardButton;

    private static ArrayList<View> sel_array = new ArrayList<View>();

    private SearchView searchView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);

        if(MainActivity.theme == 1){
            AppCompatImageView v = root.findViewById(R.id.search_back);
            v.setImageResource(R.drawable.shape_search);
        }
        else {
            AppCompatImageView v = root.findViewById(R.id.search_back);
            v.setImageResource(R.drawable.shape_search2);
        }

        menuButton = (ImageButton) root.findViewById(R.id.main_menu_btn);
        gpsButton = (FloatingActionButton) root.findViewById(R.id.main_gps_FAB);
        cardButton = (FloatingActionButton) root.findViewById(R.id.main_card_FAB);

        View v = ((LayoutInflater) root.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.map_scroll_layout, null, false);
        v.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ZoomView zoomView = new ZoomView(root.getContext());
        zoomView.addView(v);
        RelativeLayout main_container = (RelativeLayout) root.findViewById(R.id.map_container);
        main_container.addView(zoomView);

        sel_array = new ArrayList<View>();
        setSelectView();

        searchView = root.findViewById(R.id.main_search);
        searchView.setIconifiedByDefault(false);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                for(int i = 0; i < sel_array.size(); i++){
                    sel_array.get(i).setVisibility(View.INVISIBLE);
                }
                switch(newText){
                    case "a":
                    case "A":
                        sel_array.get(0).setVisibility(View.VISIBLE);
                        sel_array.get(1).setVisibility(View.VISIBLE);
                        sel_array.get(2).setVisibility(View.VISIBLE);
                        sel_array.get(3).setVisibility(View.VISIBLE);
                        sel_array.get(30).setVisibility(View.VISIBLE);
                        sel_array.get(31).setVisibility(View.VISIBLE);
                        sel_array.get(32).setVisibility(View.VISIBLE);
                        sel_array.get(36).setVisibility(View.VISIBLE);
                        sel_array.get(38).setVisibility(View.VISIBLE);

                        break;
                    case "b":
                    case "B":
                        sel_array.get(4).setVisibility(View.VISIBLE);
                        sel_array.get(5).setVisibility(View.VISIBLE);
                        sel_array.get(6).setVisibility(View.VISIBLE);
                        sel_array.get(7).setVisibility(View.VISIBLE);
                        sel_array.get(8).setVisibility(View.VISIBLE);
                        sel_array.get(9).setVisibility(View.VISIBLE);
                        sel_array.get(10).setVisibility(View.VISIBLE);
                        sel_array.get(11).setVisibility(View.VISIBLE);
                        sel_array.get(31).setVisibility(View.VISIBLE);
                        sel_array.get(32).setVisibility(View.VISIBLE);
                        sel_array.get(33).setVisibility(View.VISIBLE);
                        sel_array.get(37).setVisibility(View.VISIBLE);

                        break;
                    case "c":
                    case "C":
                        sel_array.get(12).setVisibility(View.VISIBLE);
                        sel_array.get(13).setVisibility(View.VISIBLE);
                        sel_array.get(31).setVisibility(View.VISIBLE);
                        sel_array.get(33).setVisibility(View.VISIBLE);
                        sel_array.get(34).setVisibility(View.VISIBLE);

                        break;
                    case "d":
                    case "D":
                        sel_array.get(14).setVisibility(View.VISIBLE);
                        sel_array.get(15).setVisibility(View.VISIBLE);
                        sel_array.get(16).setVisibility(View.VISIBLE);
                        sel_array.get(34).setVisibility(View.VISIBLE);
                        sel_array.get(35).setVisibility(View.VISIBLE);
                        sel_array.get(36).setVisibility(View.VISIBLE);

                        break;
                    case "e":
                    case "E":
                        sel_array.get(17).setVisibility(View.VISIBLE);
                        sel_array.get(18).setVisibility(View.VISIBLE);
                        sel_array.get(19).setVisibility(View.VISIBLE);
                        sel_array.get(20).setVisibility(View.VISIBLE);
                        sel_array.get(35).setVisibility(View.VISIBLE);
                        sel_array.get(39).setVisibility(View.VISIBLE);

                        break;
                    case "f":
                    case "F":
                        sel_array.get(21).setVisibility(View.VISIBLE);
                        sel_array.get(22).setVisibility(View.VISIBLE);
                        sel_array.get(23).setVisibility(View.VISIBLE);
                        sel_array.get(24).setVisibility(View.VISIBLE);
                        sel_array.get(25).setVisibility(View.VISIBLE);
                        sel_array.get(30).setVisibility(View.VISIBLE);
                        sel_array.get(37).setVisibility(View.VISIBLE);

                        break;
                    case "g":
                    case "G":
                        sel_array.get(26).setVisibility(View.VISIBLE);
                        sel_array.get(27).setVisibility(View.VISIBLE);
                        sel_array.get(28).setVisibility(View.VISIBLE);
                        sel_array.get(29).setVisibility(View.VISIBLE);
                        sel_array.get(37).setVisibility(View.VISIBLE);
                        sel_array.get(38).setVisibility(View.VISIBLE);
                        sel_array.get(39).setVisibility(View.VISIBLE);

                        break;
                    case "t":
                    case "T":
                        sel_array.get(30).setVisibility(View.VISIBLE);
                        sel_array.get(31).setVisibility(View.VISIBLE);
                        sel_array.get(32).setVisibility(View.VISIBLE);
                        sel_array.get(33).setVisibility(View.VISIBLE);
                        sel_array.get(34).setVisibility(View.VISIBLE);
                        sel_array.get(35).setVisibility(View.VISIBLE);
                        sel_array.get(36).setVisibility(View.VISIBLE);
                        sel_array.get(37).setVisibility(View.VISIBLE);
                        sel_array.get(38).setVisibility(View.VISIBLE);
                        sel_array.get(39).setVisibility(View.VISIBLE);
                        break;
                    case "a1":
                    case "A1":
                        sel_array.get(0).setVisibility(View.VISIBLE);
                        break;
                    case "a2":
                    case "A2":
                        sel_array.get(1).setVisibility(View.VISIBLE);
                        break;
                    case "a3":
                    case "A3":
                        sel_array.get(2).setVisibility(View.VISIBLE);
                        break;
                    case "a4":
                    case "A4":
                        sel_array.get(3).setVisibility(View.VISIBLE);
                        break;
                    case "b1":
                    case "B1":
                        sel_array.get(4).setVisibility(View.VISIBLE);
                        break;
                    case "b2":
                    case "B2":
                        sel_array.get(5).setVisibility(View.VISIBLE);
                        break;
                    case "b3":
                    case "B3":
                        sel_array.get(6).setVisibility(View.VISIBLE);
                        break;
                    case "b4":
                    case "B4":
                        sel_array.get(7).setVisibility(View.VISIBLE);
                        break;
                    case "b5":
                    case "B5":
                        sel_array.get(8).setVisibility(View.VISIBLE);
                        break;
                    case "b6":
                    case "B6":
                        sel_array.get(9).setVisibility(View.VISIBLE);
                        break;
                    case "b7":
                    case "B7":
                        sel_array.get(10).setVisibility(View.VISIBLE);
                        break;
                    case "b8":
                    case "B8":
                        sel_array.get(11).setVisibility(View.VISIBLE);
                        break;
                    case "c1":
                    case "C1":
                        sel_array.get(12).setVisibility(View.VISIBLE);
                        break;
                    case "c2":
                    case "C2":
                        sel_array.get(13).setVisibility(View.VISIBLE);
                        break;
                    case "d1":
                    case "D1":
                        sel_array.get(14).setVisibility(View.VISIBLE);
                        break;
                    case "d2":
                    case "D2":
                        sel_array.get(15).setVisibility(View.VISIBLE);
                        break;
                    case "d3":
                    case "D3":
                        sel_array.get(16).setVisibility(View.VISIBLE);
                        break;
                    case "e1":
                    case "E1":
                        sel_array.get(17).setVisibility(View.VISIBLE);
                        break;
                    case "e2":
                    case "E2":
                        sel_array.get(18).setVisibility(View.VISIBLE);
                        break;
                    case "e3":
                    case "E3":
                        sel_array.get(19).setVisibility(View.VISIBLE);
                        break;
                    case "e4":
                    case "E4":
                        sel_array.get(20).setVisibility(View.VISIBLE);
                        break;
                    case "f1":
                    case "F1":
                        sel_array.get(21).setVisibility(View.VISIBLE);
                        break;
                    case "f2":
                    case "F2":
                        sel_array.get(22).setVisibility(View.VISIBLE);
                        break;
                    case "f3":
                    case "F3":
                        sel_array.get(23).setVisibility(View.VISIBLE);
                        break;
                    case "f4":
                    case "F4":
                        sel_array.get(24).setVisibility(View.VISIBLE);
                        break;
                    case "f5":
                    case "F5":
                        sel_array.get(25).setVisibility(View.VISIBLE);
                        break;
                    case "g1":
                    case "G1":
                        sel_array.get(26).setVisibility(View.VISIBLE);
                        break;
                    case "g2":
                    case "G2":
                        sel_array.get(27).setVisibility(View.VISIBLE);
                        break;
                    case "g3":
                    case "G3":
                        sel_array.get(28).setVisibility(View.VISIBLE);
                        break;
                    case "g4":
                    case "G4":
                        sel_array.get(29).setVisibility(View.VISIBLE);
                        break;
                    case "t1":
                    case "T1":
                        sel_array.get(30).setVisibility(View.VISIBLE);
                        break;
                    case "t2":
                    case "T2":
                        sel_array.get(31).setVisibility(View.VISIBLE);
                        break;
                    case "t3":
                    case "T3":
                        sel_array.get(32).setVisibility(View.VISIBLE);
                        break;
                    case "t4":
                    case "T4":
                        sel_array.get(33).setVisibility(View.VISIBLE);
                        break;
                    case "t5":
                    case "T5":
                        sel_array.get(34).setVisibility(View.VISIBLE);
                        break;
                    case "t6":
                    case "T6":
                        sel_array.get(35).setVisibility(View.VISIBLE);
                        break;
                    case "t7":
                    case "T7":
                        sel_array.get(36).setVisibility(View.VISIBLE);
                        break;
                    case "t8":
                    case "T8":
                        sel_array.get(37).setVisibility(View.VISIBLE);
                        break;
                    case "t9":
                    case "T9":
                        sel_array.get(38).setVisibility(View.VISIBLE);
                        break;
                    case "t10":
                    case "T10":
                        sel_array.get(39).setVisibility(View.VISIBLE);
                        break;
                }
                return false;
            }
        });


        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                ((MainActivity) getActivity()).openDrawer();
                //((MainActivity) getActivity()).Speak("음성");
            }
        });

        gpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                Random  r = new Random();
                int i = r.nextInt(40);

                sel_array.get(i).setVisibility(View.VISIBLE);
            }
        });

        cardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                Intent intent = new Intent(getActivity(), CardActivity.class);
                startActivity(intent);
            }
        });

        addStationListener();
        return root;
    }

    private void closeSearch(){
        searchView.clearFocus();
        for(int i = 0; i < sel_array.size(); i++){
            sel_array.get(i).setVisibility(View.INVISIBLE);
        }
    }

    private void addStationListener(){
        (root.findViewById(R.id.imageButton_a1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("a1");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_a2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("a2");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_a3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("a3");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_a4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("a4");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_b1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("b1");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_b2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("b2");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_b3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("b3");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_b4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("b4");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_b5)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("b5");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_b6)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("b6");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_b7)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("b7");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_b8)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("b8");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_c1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("c1");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_c2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("c2");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_d1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("d1");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_d2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("d2");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_d3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("d3");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_e1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("e1");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_e2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("e2");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_e3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("e3");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_e4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("e4");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_f1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("f1");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_f2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("f2");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_f3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("f3");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_f4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("f4");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_f5)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("f5");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_g1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("g1");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_g2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("g2");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_g3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("g3");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_g4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("g4");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_t1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("t1");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_t2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("t2");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_t3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("t3");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_t4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("t4");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_t5)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("t5");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_t6)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("t6");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_t7)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("t7");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_t8)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("t8");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_t9)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("t9");
                stationBtnAction(v);
            }
        });
        (root.findViewById(R.id.imageButton_t10)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSearch();
                if(MainActivity.isTts) ((MainActivity) getActivity()).Speak("t10");
                stationBtnAction(v);
            }
        });
    }

    private void stationBtnAction(View view){
        if(startStation == 0){
            switch (view.getId()){
                case R.id.imageButton_a1: startStation = 1; break;
                case R.id.imageButton_a2: startStation = 2; break;
                case R.id.imageButton_a3: startStation = 3; break;
                case R.id.imageButton_a4: startStation = 4; break;
                case R.id.imageButton_b1: startStation = 5; break;
                case R.id.imageButton_b2: startStation = 6; break;
                case R.id.imageButton_b3: startStation = 7; break;
                case R.id.imageButton_b4: startStation = 8; break;
                case R.id.imageButton_b5: startStation = 9; break;
                case R.id.imageButton_b6: startStation = 10; break;
                case R.id.imageButton_b7: startStation = 11; break;
                case R.id.imageButton_b8: startStation = 12; break;
                case R.id.imageButton_c1: startStation = 13; break;
                case R.id.imageButton_c2: startStation = 14; break;
                case R.id.imageButton_d1: startStation = 15; break;
                case R.id.imageButton_d2: startStation = 16; break;
                case R.id.imageButton_d3: startStation = 17; break;
                case R.id.imageButton_e1: startStation = 18; break;
                case R.id.imageButton_e2: startStation = 19; break;
                case R.id.imageButton_e3: startStation = 20; break;
                case R.id.imageButton_e4: startStation = 21; break;
                case R.id.imageButton_f1: startStation = 22; break;
                case R.id.imageButton_f2: startStation = 23; break;
                case R.id.imageButton_f3: startStation = 24; break;
                case R.id.imageButton_f4: startStation = 25; break;
                case R.id.imageButton_f5: startStation = 26; break;
                case R.id.imageButton_g1: startStation = 27; break;
                case R.id.imageButton_g2: startStation = 28; break;
                case R.id.imageButton_g3: startStation = 29; break;
                case R.id.imageButton_g4: startStation = 30; break;
                case R.id.imageButton_t1: startStation = 31; break;
                case R.id.imageButton_t2: startStation = 33; break;
                case R.id.imageButton_t3: startStation = 36; break;
                case R.id.imageButton_t4: startStation = 38; break;
                case R.id.imageButton_t5: startStation = 40; break;
                case R.id.imageButton_t6: startStation = 42; break;
                case R.id.imageButton_t7: startStation = 44; break;
                case R.id.imageButton_t8: startStation = 46; break;
                case R.id.imageButton_t9: startStation = 49; break;
                case R.id.imageButton_t10: startStation = 51; break;
            }
        }
        else if(startStation != 0){
            switch (view.getId()){
                case R.id.imageButton_a1: destStation = 1; break;
                case R.id.imageButton_a2: destStation = 2; break;
                case R.id.imageButton_a3: destStation = 3; break;
                case R.id.imageButton_a4: destStation = 4; break;
                case R.id.imageButton_b1: destStation = 5; break;
                case R.id.imageButton_b2: destStation = 6; break;
                case R.id.imageButton_b3: destStation = 7; break;
                case R.id.imageButton_b4: destStation = 8; break;
                case R.id.imageButton_b5: destStation = 9; break;
                case R.id.imageButton_b6: destStation = 10; break;
                case R.id.imageButton_b7: destStation = 11; break;
                case R.id.imageButton_b8: destStation = 12; break;
                case R.id.imageButton_c1: destStation = 13; break;
                case R.id.imageButton_c2: destStation = 14; break;
                case R.id.imageButton_d1: destStation = 15; break;
                case R.id.imageButton_d2: destStation = 16; break;
                case R.id.imageButton_d3: destStation = 17; break;
                case R.id.imageButton_e1: destStation = 18; break;
                case R.id.imageButton_e2: destStation = 19; break;
                case R.id.imageButton_e3: destStation = 20; break;
                case R.id.imageButton_e4: destStation = 21; break;
                case R.id.imageButton_f1: destStation = 22; break;
                case R.id.imageButton_f2: destStation = 23; break;
                case R.id.imageButton_f3: destStation = 24; break;
                case R.id.imageButton_f4: destStation = 25; break;
                case R.id.imageButton_f5: destStation = 26; break;
                case R.id.imageButton_g1: destStation = 27; break;
                case R.id.imageButton_g2: destStation = 28; break;
                case R.id.imageButton_g3: destStation = 29; break;
                case R.id.imageButton_g4: destStation = 30; break;
                case R.id.imageButton_t1: destStation = 31; break;
                case R.id.imageButton_t2: destStation = 33; break;
                case R.id.imageButton_t3: destStation = 36; break;
                case R.id.imageButton_t4: destStation = 38; break;
                case R.id.imageButton_t5: destStation = 40; break;
                case R.id.imageButton_t6: destStation = 42; break;
                case R.id.imageButton_t7: destStation = 44; break;
                case R.id.imageButton_t8: destStation = 46; break;
                case R.id.imageButton_t9: destStation = 49; break;
                case R.id.imageButton_t10: destStation = 51; break;
            }
            if(startStation != destStation){
                MainActivity.start = startStation;
                MainActivity.end = destStation;
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_nav_home_to_nav_result);
            }
            startStation = 0;
            destStation = 0;
        }
    }

    private void setSelectView(){
        this.sel_array.add(root.findViewById(R.id.sel_a1));
        this.sel_array.add(root.findViewById(R.id.sel_a2));
        this.sel_array.add(root.findViewById(R.id.sel_a3));
        this.sel_array.add(root.findViewById(R.id.sel_a4));
        this.sel_array.add(root.findViewById(R.id.sel_b1));
        this.sel_array.add(root.findViewById(R.id.sel_b2));
        this.sel_array.add(root.findViewById(R.id.sel_b3));
        this.sel_array.add(root.findViewById(R.id.sel_b4));
        this.sel_array.add(root.findViewById(R.id.sel_b5));
        this.sel_array.add(root.findViewById(R.id.sel_b6));
        this.sel_array.add(root.findViewById(R.id.sel_b7));
        this.sel_array.add(root.findViewById(R.id.sel_b8));
        this.sel_array.add(root.findViewById(R.id.sel_c1));
        this.sel_array.add(root.findViewById(R.id.sel_c2));
        this.sel_array.add(root.findViewById(R.id.sel_d1));
        this.sel_array.add(root.findViewById(R.id.sel_d2));
        this.sel_array.add(root.findViewById(R.id.sel_d3));
        this.sel_array.add(root.findViewById(R.id.sel_e1));
        this.sel_array.add(root.findViewById(R.id.sel_e2));
        this.sel_array.add(root.findViewById(R.id.sel_e3));
        this.sel_array.add(root.findViewById(R.id.sel_e4));
        this.sel_array.add(root.findViewById(R.id.sel_f1));
        this.sel_array.add(root.findViewById(R.id.sel_f2));
        this.sel_array.add(root.findViewById(R.id.sel_f3));
        this.sel_array.add(root.findViewById(R.id.sel_f4));
        this.sel_array.add(root.findViewById(R.id.sel_f5));
        this.sel_array.add(root.findViewById(R.id.sel_g1));
        this.sel_array.add(root.findViewById(R.id.sel_g2));
        this.sel_array.add(root.findViewById(R.id.sel_g3));
        this.sel_array.add(root.findViewById(R.id.sel_g4));
        this.sel_array.add(root.findViewById(R.id.sel_t1));
        this.sel_array.add(root.findViewById(R.id.sel_t2));
        this.sel_array.add(root.findViewById(R.id.sel_t3));
        this.sel_array.add(root.findViewById(R.id.sel_t4));
        this.sel_array.add(root.findViewById(R.id.sel_t5));
        this.sel_array.add(root.findViewById(R.id.sel_t6));
        this.sel_array.add(root.findViewById(R.id.sel_t7));
        this.sel_array.add(root.findViewById(R.id.sel_t8));
        this.sel_array.add(root.findViewById(R.id.sel_t9));
        this.sel_array.add(root.findViewById(R.id.sel_t10));
    }
}