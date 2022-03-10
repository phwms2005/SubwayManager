package subway.v5.paths;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Vector;

public class Graph {
	private final int V = 53;
	private final int INT_LIMIT = 99999999;
	private LinkedList<Pair<Integer, WeightGroup>>[] adj;
	
	public final String[] name = { "", "A1", "A2", "A3", "A4", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "C1",
			"C2", "D1", "D2", "D3", "E1", "E2", "E3", "E4", "F1", "F2", "F3", "F4", "F5", "G1", "G2", "G3", "G4",
			"T1(A)", "T1(F)", "T2(A)", "T2(B)", "T2(C)", "T3(A)", "T3(B)", "T4(B)", "T4(C)", "T5(C)", "T5(D)", "T6(D)",
			"T6(E)", "T7(A)", "T7(D)", "T8(B)", "T8(F)", "T8(G)", "T9(A)", "T9(G)", "T10(E)", "T10(G)" };
	public final int[] except = { 32, 34, 35, 37, 39, 41, 43, 45, 47, 48, 50, 52 };

	
	public Graph() {
		adj = new LinkedList[V];
	    for (int i = 0; i < V; i++)
	    {
	    	adj[i] = new LinkedList<Pair<Integer, WeightGroup>>();
	    }
	}
	
	public void addEdge(int u, int v, WeightGroup w) {
		adj[u].add(new Pair(v, w));
		adj[v].add(new Pair(u, w));
	}
	
	public void printPath(int parent[], int j, ArrayList p) {
		if (parent[j] == -1) {
			return;
		}
		printPath(parent, parent[j], p);
		p.add(j);
	}
	
	public StationInfo shortestPath(int src, int dest, int mode) {
		PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>();
		
		ArrayList<WeightGroup> dist = new ArrayList<WeightGroup>(V);
		for(int i = 0 ; i < V ; i++) {
			dist.add(i, new WeightGroup(INT_LIMIT, INT_LIMIT, INT_LIMIT, INT_LIMIT));
		}
		
		int[] parent = new int[V];
		for(int i = 0 ; i < V ; i++) {
			parent[i] = -1;
		}
		
		pq.offer(new Pair<Integer, Integer>(0, src));
		dist.set(src, new WeightGroup(0, 0, 0, 0));
		WeightGroup result = new WeightGroup(0, 0, 0, 0);
		ArrayList<Integer> path;
		int r_t = 0, r_f = 0, r_d = 0, r_p = 0;
		while(!pq.isEmpty()) {
			int u = pq.poll().second;
			Pair<Integer, WeightGroup> j;
			
			for(Iterator<Pair<Integer, WeightGroup>> i = adj[u].iterator() ; i.hasNext();) {
				j = i.next();
				int v = j.first;
				int t = j.second.time;
				int f = j.second.fare;
				int d = j.second.distance;
				int p = j.second.transport;
				
				switch(mode) {
				case 0:
					if(dist.get(v).time > dist.get(u).time + t) {
						parent[v] = u;
						dist.set(v, new WeightGroup(dist.get(u).time + t, dist.get(u).distance + d, dist.get(u).fare + f, dist.get(u).transport + p));
						pq.offer(new Pair<Integer, Integer>(dist.get(v).time, v));
					}
					break;
				case 1:
					if(dist.get(v).distance > dist.get(u).distance + d) {
						parent[v] = u;
						dist.set(v, new WeightGroup(dist.get(u).time + t, dist.get(u).distance + d, dist.get(u).fare + f, dist.get(u).transport + p));
						pq.offer(new Pair<Integer, Integer>(dist.get(v).distance, v));
					}
					break;
				case 2:
					if(dist.get(v).fare > dist.get(u).fare + f) {
						parent[v] = u;
						dist.set(v, new WeightGroup(dist.get(u).time + t, dist.get(u).distance + d, dist.get(u).fare + f, dist.get(u).transport + p));
						pq.offer(new Pair<Integer, Integer>(dist.get(v).fare, v));
					}
					break;
				
				case 3:
					if(dist.get(v).transport > dist.get(u).transport + p) {
						parent[v] = u;
						dist.set(v, new WeightGroup(dist.get(u).time + t, dist.get(u).distance + d, dist.get(u).fare + f, dist.get(u).transport + p));
						pq.offer(new Pair<Integer, Integer>(dist.get(v).transport, v));
					}
					break;
				}
			}
		}
		
		path = new ArrayList<Integer>();
		printPath(parent, dest, path);
		if(path.size() != 0){
			path.remove(path.size() - 1);
		}
		//int Mode, int TransNum, double Time, int Fee, double Distance,
        //ArrayList<Integer> Path
        return new StationInfo(mode, dist.get(dest).transport, dist.get(dest).time, dist.get(dest).fare, dist.get(dest).distance, path);
	}
}
