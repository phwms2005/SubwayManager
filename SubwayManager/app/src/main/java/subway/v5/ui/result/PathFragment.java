package subway.v5.ui.result;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import subway.v5.MainActivity;
import subway.v5.R;
import subway.v5.paths.StationInfo;

public class PathFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_path, container, false);

        TextView text_type = view.findViewById(R.id.path_type);
        TextView text_start = view.findViewById(R.id.start_station);
        TextView text_dest = view.findViewById(R.id.dest_station);
        TextView text_time = view.findViewById(R.id.path_time);
        TextView text_dis = view.findViewById(R.id.path_distance);
        TextView text_fee = view.findViewById(R.id.path_fee);
        TextView text_trans = view.findViewById(R.id.path_trans);

        if (getArguments() != null) {
            //Bundle을 이용하여 Path 편집
            Bundle args = getArguments();
            int type = args.getInt("type");

            StationInfo data = MainActivity.getPath(MainActivity.start, MainActivity.end, type);

            switch (type){
                case 0:
                    text_type.setText("최소시간");
                    break;
                case 1:
                    text_type.setText("최단거리");
                    break;
                case 2:
                    text_type.setText("최소요금");
                    break;
                case 3:
                    text_type.setText("환승횟수");
                    break;
            }

            text_start.setText(findStation(MainActivity.start));
            text_dest.setText(findStation(MainActivity.end));
            text_time.setText(data.getTime()/10 + "분");
            text_dis.setText("거리 " + data.getDistance()/10 + "km");
            text_fee.setText("요금" + data.getFee()/10 + "원");
            text_trans.setText("환승 " + data.getTransNum() + "회");
        }

        return view;
    }

    private String findStation(int station){
        switch (station){
            case 0:
                return "Error";
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
                return "T3";
            case 35:
                return "T3";
            case 36:
                return "T3";
            case 37:
                return "T4";
            case 38:
                return "T4";
            case 39:
                return "T5";
            case 40:
                return "T5";
            case 41:
                return "T6";
            case 42:
                return "T6";
            case 43:
                return "T7";
            case 44:
                return "T7";
            case 45:
                return "T8";
            case 46:
                return "T8";
            case 47:
                return "T9";
            case 48:
                return "T9";
            case 49:
                return "T9";
            case 51:
                return "T10";
            case 52:
                return "T10";
            default:
                return "Error";
        }
    }
}
