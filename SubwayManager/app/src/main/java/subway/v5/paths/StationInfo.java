package subway.v5.paths;
import java.util.ArrayList;

public class StationInfo {
   private int Mode; // �����
   private int TransNum; // ȯ��Ƚ��
   private ArrayList<Integer> TransStation; // ȯ�¿�
   private double Time; // �ҿ�ð�
   private int Fee; // �ҿ� �ݾ�
   private double Distance; // �ѰŸ�
   private ArrayList<Integer> Path; // �������

   public StationInfo(int Mode, int TransNum, double Time, int Fee, double Distance,
         ArrayList<Integer> Path) {
      this.Mode = Mode;
      this.TransNum = TransNum;
      this.Path = Path;
      this.Time = Time;
      this.Fee = Fee;
      this.Distance = Distance;
   }

   public int getTransNum() {
      return TransNum;
   }

   public ArrayList<Integer> getTransStation() {
      return TransStation;
   }

   public double getTime() {
      return Time;
   }

   public int getFee() {
      return Fee;
   }

   public double getDistance() {
      return Distance;
   }

   public ArrayList<Integer> getPath() {
      return Path;
   }

   public int getSize(){ return this.Path.size(); }

   public int getTransSize(int transNum){
      if(transNum == 0) return 0;
      int temp = 0;
      for(int i = 1; i < this.Path.size(); i++){
         if(isTrans(this.Path.get(i),this.Path.get(i-1))){
            transNum--;
            temp++;
         }
         if(transNum == 0){
            return i - temp + 1;
         }
      }
      return 0;
   }

   public int getStation(int num){
       int temp = 0;
       if(num == 1) return 0;
       for(int i = 1; i < this.Path.size(); i++){
           if(isTrans(this.Path.get(i),this.Path.get(i-1))) temp++;
           if(i == num + temp - 1) return i;
       }
       return -1;
   }

   public boolean isTrans(int a, int b){
      if(a==31&&b==32) return true;      if(a==32&&b==31) return true;
      if(a==33&&b==34) return true;      if(a==33&&b==35) return true;
      if(a==34&&b==33) return true;      if(a==34&&b==35) return true;
      if(a==35&&b==33) return true;      if(a==35&&b==34) return true;
      if(a==36&&b==37) return true;      if(a==37&&b==36) return true;
      if(a==38&&b==39) return true;      if(a==39&&b==38) return true;
      if(a==40&&b==41) return true;      if(a==41&&b==40) return true;
      if(a==42&&b==43) return true;      if(a==43&&b==42) return true;
      if(a==44&&b==45) return true;      if(a==45&&b==44) return true;
      if(a==46&&b==47) return true;      if(a==46&&b==48) return true;
      if(a==47&&b==46) return true;      if(a==47&&b==48) return true;
      if(a==48&&b==46) return true;      if(a==48&&b==47) return true;
      if(a==49&&b==50) return true;      if(a==50&&b==49) return true;
      if(a==51&&b==52) return true;      if(a==52&&b==51) return true;
      return false;
   }
}