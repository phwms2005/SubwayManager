package subway.v5.ui.credit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import subway.v5.MainActivity;
import subway.v5.R;

public class CreditsFragment extends Fragment {

    private CreditsViewModel creditsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        creditsViewModel =
                ViewModelProviders.of(this).get(CreditsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_credits, container, false);

        if(MainActivity.theme == 1){
            root.findViewById(R.id.fragment_layout).setBackgroundResource(R.drawable.gradation_background);
        }
        else {
            root.findViewById(R.id.fragment_layout).setBackgroundResource(R.drawable.gradation_background2);
        }

        //뒤로가기버튼
        ImageButton btnBack = root.findViewById(R.id.card_back_btn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        //메뉴버튼
        ImageButton menuButton = root.findViewById(R.id.credits_menu_btn);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).openDrawer();
            }
        });

        ImageView rabbit = (ImageView) root.findViewById(R.id.credit);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(rabbit);
        Glide.with(this.getContext()).load(R.drawable.credit_gif).into(gifImage);

        return root;
    }
}