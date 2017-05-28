package CA;

import java.util.HashMap;
import java.util.Map;

public class OldDirectionInCome {
	Data data=new Data();
	public Map getOldDirection(Block B[][],int dx,int dy){
		Map<Integer,Double> oldDirectionInCome=new HashMap<Integer, Double>();
		int oldDirection=B[dx][dy].getDirection();
		for(int i=0;i<10;i++){
			oldDirectionInCome.put(i,0.0);
		}
		if(oldDirection==1){
			oldDirectionInCome.put(1,0.5+(Math.random())*0.001);
		}
		else if(oldDirection==2){
			oldDirectionInCome.put(2,0.5+(Math.random())*0.001);
		}
		else if(oldDirection==3){
			oldDirectionInCome.put(3,0.5+(Math.random())*0.001);
		}
		else if(oldDirection==4){
			oldDirectionInCome.put(4,0.5+(Math.random())*0.001);
		}
		else if(oldDirection==5){
			oldDirectionInCome.put(1,0.1+(Math.random())*0.001);
			oldDirectionInCome.put(2,0.1+(Math.random())*0.001);
			oldDirectionInCome.put(3,0.1+(Math.random())*0.001);
			oldDirectionInCome.put(4,0.1+(Math.random())*0.001);
			oldDirectionInCome.put(5,0.1+(Math.random())*0.001);
			oldDirectionInCome.put(6,0.1+(Math.random())*0.001);
			oldDirectionInCome.put(7,0.1+(Math.random())*0.001);
			oldDirectionInCome.put(8,0.1+(Math.random())*0.001);
		}
		else if(oldDirection==6){
			oldDirectionInCome.put(6,0.5+(Math.random())*0.001);
		}
		else if(oldDirection==7){
			oldDirectionInCome.put(7,0.5+(Math.random())*0.001);
		}
		else if(oldDirection==8){
			oldDirectionInCome.put(8,0.5+(Math.random())*0.001);
		}
		else if(oldDirection==9){
			oldDirectionInCome.put(9,0.5+(Math.random())*0.001);
		}
		return oldDirectionInCome;
	}
}
