package subway.v5.ui.settings;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import subway.v5.MainActivity;
import subway.v5.R;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                ViewModelProviders.of(this).get(SettingsViewModel.class);
        root = inflater.inflate(R.layout.fragment_settings, container, false);

        //뒤로가기버튼
        ImageButton btnBack = root.findViewById(R.id.settings_back_btn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        //메뉴버튼
        ImageButton menuButton = root.findViewById(R.id.settings_menu_btn);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).openDrawer();
            }
        });
        Switch switch_tts = root.findViewById(R.id.switch_tts);
        switch_tts.setChecked(MainActivity.isTts);

        switch_tts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.isTts = isChecked;
            }
        });

        final RadioGroup radioGroup = root.findViewById(R.id.radioGroup);
        if(MainActivity.theme == 1) {
            radioGroup.check(R.id.radio_theme1);
            ((TextView) root.findViewById(R.id.setting_txt_theme)).setText("Theme1");
        } else {
            radioGroup.check(R.id.radio_theme2);
            ((TextView) root.findViewById(R.id.setting_txt_theme)).setText("Theme2");
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_theme1) {
                    MainActivity.theme = 1;
                    ((TextView) root.findViewById(R.id.setting_txt_theme)).setText("Theme1");
                } else {
                    MainActivity.theme = 2;
                    ((TextView) root.findViewById(R.id.setting_txt_theme)).setText("Theme2");
                }
                ((MainActivity) getActivity()).themeChange();
            }
        });

        return root;
    }
}