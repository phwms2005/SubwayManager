package subway.v5.paths;

public class WeightGroup {
	public int time;
	public int distance;
	public int fare;
	public int transport;
	
	public WeightGroup(int t, int d, int f, int p) {
		time = t;
		distance = d;
		fare = f;
		transport = p;
	}
	
	public void addWeight(int t, int d, int f, int p) {
		time += t;
		distance += d;
		fare += f;
		transport += p;
	}
}
