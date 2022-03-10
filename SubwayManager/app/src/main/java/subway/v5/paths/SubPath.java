package subway.v5.paths;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import subway.v5.MainActivity;
import subway.v5.R;
import subway.v5.paths.Graph;
import subway.v5.paths.StationInfo;

public class SubPath {
    public StationInfo[][][] paths = new StationInfo[53][53][4];

    public SubPath(InputStream input) {
        BufferedReader br;
        Graph g = new Graph();

        try {
            InputStreamReader stream = new InputStreamReader(input, "utf-8");
            br = new BufferedReader(stream);
            String line;
            String str[];
            int start, end, transport, time, fare, distance;

            while ((line = br.readLine()) != null) {
                str = line.split(" ");

                start = Integer.parseInt(str[0]);
                end = Integer.parseInt(str[1]);
                time = (int) (10 * Double.parseDouble(str[2]));
                fare = (int) (10 * Double.parseDouble(str[3]));
                distance = (int) (10 * Double.parseDouble(str[4]));
                transport = Integer.parseInt(str[5]);

                WeightGroup wg = new WeightGroup(time, fare, distance, transport);
                g.addEdge(start, end, wg);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        final int MODE_LENGTH = 4;
        final int STATION_LENGTH = 53;

        for (int mode = 0; mode < MODE_LENGTH; mode++) {
            for (int station_src = 1; station_src < STATION_LENGTH; station_src++) {
                for (int station_dest = 1; station_dest < STATION_LENGTH; station_dest++) {
                    paths[station_src][station_dest][mode] = g.shortestPath(station_src, station_dest, mode);
                }
            }
        }
    }
}
