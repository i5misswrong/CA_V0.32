package CA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PeopleInCome {
	//计算行人之间的收益的方法
	Data data=new Data();
	int m=data.M;
	//-----------------计算行人与行人的收益-----------------------
	public ArrayList<Direction> countPeoInCome(Block B[][],int dx,int dy){
		ArrayList<Direction> peoInCome=new ArrayList<Direction>();
		Map<Integer,Double> pni=new HashMap<Integer,Double>();
		Map<Integer,Double> pdi=new HashMap<Integer,Double>();
		for(int i=0;i<10;i++){
			pni.put(i,0.0);
			pdi.put(i,0.0);
		}
		int peopleNumber=0;
		for(int i=0;i<m;i++){
			for(int j=0;j<m;j++){
				if(B[i][j].logo==data.LOGO_PEOPLE){
					peopleNumber++;
				}
			}
		}
		for(int i=0;i<m;i++){
			for(int j=0;j<m;j++){
				if(B[i][j].logo==data.LOGO_PEOPLE){
					double parallel=j-dy;
					double vertical=i-dx;
					if(Math.sqrt(parallel*parallel+vertical*vertical)<data.VIEW_RANGE){//如果在视野范围内
						int quadrat=judeQuadrat(parallel, vertical);
						double theta=countAngelPeopleAndExit(quadrat, parallel, vertical);
						int othersA=judgeAngleAndTheta(theta);//行人数量收益
						switch (othersA) {
						case 1:
							pni.put(1, pni.get(1)+1);
							break;
						case 2:
							pni.put(2, pni.get(2)+1);
							break;
						case 3:
							pni.put(3, pni.get(3)+1);
							break;
						case 4:
							pni.put(4, pni.get(4)+1);
							break;
						case 5:
							pni.put(5, pni.get(5)+1);
							break;
						case 6:
							pni.put(6, pni.get(6)+1);
							break;
						case 7:
							pni.put(7, pni.get(7)+1);
							break;
						case 8:
							pni.put(8, pni.get(8)+1);
							break;
						case 9:
							pni.put(9, pni.get(9)+1);
							break;
						default:
							break;
						}
						int othersD=B[i][j].getDirection();//行人方向收益
						switch (othersD) {
						case 1:
							pdi.put(1, pdi.get(1)+1);
							break;
						case 2:
							pdi.put(2, pdi.get(2)+1);
							break;
						case 3:
							pdi.put(3, pdi.get(3)+1);
							break;
						case 4:
							pdi.put(4, pdi.get(4)+1);
							break;
						case 5:
							pdi.put(5, pdi.get(5)+1);
							break;
						case 6:
							pdi.put(6, pdi.get(6)+1);
							break;
						case 7:
							pdi.put(7, pdi.get(7)+1);
							break;
						case 8:
							pdi.put(8, pdi.get(8)+1);
							break;
						case 9:
							pdi.put(9, pdi.get(9)+1);
							break;
						default:
							break;
						}
					}
					
				}
			}
			
		}
		for(int j=0;j<pdi.size();j++){
			Direction dir=new Direction();//相加，存入list
			dir.setDirection(j);
			dir.setInCome((pni.get(j)+pdi.get(j))/peopleNumber/5+(Math.random())*0.0001);
			peoInCome.add(dir);
		}
		return peoInCome;
		
		
	}
	public int judgeAngleAndTheta(double theta){
		int d=0;
		if(theta<157.5 & theta >=112.5){
			d=1;
		}
		if(theta<112.5 & theta >=67.5){
			d=2;
		}
		if(theta<67.5 & theta >=22.5){
			d=3;
		}
		if(theta<202.5 & theta >=157.5){
			d=4;
		}
		if(theta<22.5 & theta >=0){
			d=6;
		}
		if(theta<360 & theta >=337.5){
			d=6;
		}
		if(theta<247.5 & theta >=202.5){
			d=7;
		}
		if(theta<292.5 & theta >=247.5){
			d=8;
		}
		if(theta<337.5 & theta >=292.5){
			d=9;
		}
		return d;
	}
	public int  judeQuadrat(double parallel,double vertical){
		int quadrat=0;
		if(vertical<0 & parallel>0){
			quadrat=1;
		}
		else if(vertical<0 & parallel<0){
			quadrat=2;
		}
		else if(vertical>0 & parallel<0){
			quadrat=3;
		}
		else if(vertical>0 & parallel>0){
			quadrat=4;
		}
		else if(vertical==0 & parallel>0){
			quadrat=5;//0
		}
		else if(vertical==0 & parallel<0){
			quadrat=6;//180;
		}
		else if(parallel==0 & vertical<0){
			quadrat=7;//90
		}
		else if(parallel==0 & vertical>0){
			quadrat=8;//270
		}
		else if(parallel==0 & vertical==0){
			quadrat=9;
		}
		else{
			System.out.println("the people on the axis");
		}
		return quadrat;
	}
	public double countAngelPeopleAndExit(int quadrat,double parallel,double vertical){
		double parallelAbs=Math.abs(parallel);
		double verticalAbs=Math.abs(vertical);
		double k=verticalAbs/parallelAbs;
		double theta=0;
		switch (quadrat) {
		case 1:
			theta=Math.atan(k);
			break;
		case 2:
			theta=Math.PI-Math.atan(k);
			break;
		case 3:
			theta=Math.PI+Math.atan(k);
			break;
		case 4:
			theta=Math.PI*2-Math.atan(k);
			break;
		case 5:
			theta=0.001*(Math.random()-0.5);
			break;
		case 6:
			theta=180+0.001*(Math.random()-0.5);
			break;
		case 7:
			theta=90+0.001*(Math.random()-0.5);
			break;
		case 8:
			theta=270+0.001*(Math.random()-0.5);
			break;
		case 9:
			theta=360*(Math.random());
			break;
		default:
			System.out.println("countAnglePeoAndExit err");
			break;
		}
		return Math.toDegrees(theta);
	}
}
