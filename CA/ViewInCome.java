package CA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewInCome {
	//计算视野收益的方法
	Data data=new Data();
	int exitX=data.EXIT_X;
	int exitY=data.EXIT_Y;
	int m=data.M;
	//------------判断出口在第几象限-------------------------
	public int judeQuadrat(double parallel,double vertical){
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
	//------------------计算出口与行人的角度---------------------------
	public double countAnglePeopleAndExit(int quadrat,double parallel,double vertical){
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
		default:
			System.out.println("countAnglePeoAndExit err");
			break;
		}
		return Math.toDegrees(theta);
	}
	//------------逆时针后的视野角----------------
	public double anticlock(double theta){
		double thetaMid=theta+data.VIEW_ANGLE;
		double theta1;
		if(thetaMid>=360){
			theta1=thetaMid-360;
		}
		else{
			theta1=thetaMid;
		}
		return theta1;
	}
	//----------------顺时针后的视野角---------------------
	public double clock(double theta){
		double thetaMid=theta-data.VIEW_ANGLE;
		double theta2;
		if(thetaMid<0){
			theta2=360+thetaMid;
		}
		else{
			theta2=thetaMid;
		}
		return theta2;
	}
	//---------------计算逆时针视野角k1所产生的收益------------------------
	public Map<Integer,Double> countTheta1(double theta1,Map<Integer,Double>viewInCome1){
		if(theta1<157.5 & theta1 >=112.5){
			viewInCome1.put(1, (theta1-112.5)/45);
		}
		if(theta1<112.5 & theta1 >=67.5){
			viewInCome1.put(2, (theta1-67.5)/45);
		}
		if(theta1<67.5 & theta1 >=22.5){
			viewInCome1.put(3, (theta1-22.5)/45);
		}
		if(theta1<202.5 & theta1 >=157.5){
			viewInCome1.put(4, (theta1-157.5)/45);
		}
		if(theta1<22.5 & theta1 >=0){
			viewInCome1.put(6, (theta1+22.5)/45);
		}
		if(theta1<360 & theta1 >=337.5){
			viewInCome1.put(6, (theta1-337.5)/45);
		}
		if(theta1<247.5 & theta1 >=202.5){
			viewInCome1.put(7, (theta1-202.5)/45);
		}
		if(theta1<292.5 & theta1 >=247.5){
			viewInCome1.put(8, (theta1-247.5)/45);
		}
		if(theta1<337.5 & theta1 >=292.5){
			viewInCome1.put(9, (theta1-292.5)/45);
		}
		return viewInCome1;
	}
	//---------------------计算顺时针视野角k2的收益----------------------------
	public Map<Integer,Double> countTheta2(double theta2,Map<Integer,Double>viewInCome2){
		if(theta2<157.5 & theta2 >=112.5){
			viewInCome2.put(1, (157.5-theta2)/45);
		}
		if(theta2<112.5 & theta2 >=67.5){
			viewInCome2.put(2, (112.5-theta2)/45);
		}
		if(theta2<67.5 & theta2 >=22.5){
			viewInCome2.put(3, (67.5-theta2)/45);
		}
		if(theta2<202.5 & theta2 >=157.5){
			viewInCome2.put(4, (202.5-theta2)/45);
		}
		if(theta2<22.5 & theta2 >=0){
			viewInCome2.put(6, (22.5-theta2)/45);
		}
		if(theta2<360 & theta2 >=337.5){
			viewInCome2.put(6, (382.5-theta2)/45);
		}
		if(theta2<247.5 & theta2 >=202.5){
			viewInCome2.put(7, (247.5-theta2)/45);
		}
		if(theta2<292.5 & theta2 >=247.5){
			viewInCome2.put(8, (292.5-theta2)/45);
		}
		if(theta2<337.5 & theta2 >=292.5){
			viewInCome2.put(9, (337.5-theta2)/45);
		}
		return viewInCome2;
	}
	//-----------------------计算某个格子完全位于视野角中--------------------
	public Map<Integer,Double> isCompleteInTheta(double theta1,double theta2,Map<Integer,Double>viewInComeCom){
		if(theta1>=157.5 & theta2<=112.5){
			viewInComeCom.put(1,1.01);
		}
		if(theta1>=112.5 & theta2<=67.5){
			viewInComeCom.put(2,1.01);
		}
		if(theta1>=67.5 & (theta2<=22.5 | theta2>theta1)){
			viewInComeCom.put(3,1.01);
		}
		if(theta1>=202.5 & theta2<=157.5){
			viewInComeCom.put(4,1.01);
		}
		if(theta1>=22.5 & theta2>=337.5){
			viewInComeCom.put(6,1.01);
		}
		if(theta1>=247.5 & theta2<=202.5){
			viewInComeCom.put(7,1.01);
		}
		if(theta1>=292.5 & theta2<=247.5){
			viewInComeCom.put(8,1.01);
		}
		if(theta1>=337.5 & theta2<=292.5){
			viewInComeCom.put(9,1.01);
		}
		return viewInComeCom;
	}
	//------------------将视野角加起来---------------------------
	public Map addViemInCome(Map<Integer,Double> viewInCome1,Map<Integer,Double> viewInCome2,Map<Integer,Double> viewInComeCom,Map<Integer,Double> seeExitInCome){
		Map<Integer,Double> viewInComeAll=new HashMap<>();
		for(int i=0;i<10;i++){
			viewInComeAll.put(i,viewInCome1.get(i)+viewInCome2.get(i)+viewInComeCom.get(i)+(Math.random())*0.0001);
		}
		return viewInComeAll;
	}
	//---------------------将map转化为list 向外输出----------------------
	public Map outPutDirection(Block B[][],int px,int py){
		Map<Integer,Double> viewInCome1=new HashMap<Integer,Double>();
		Map<Integer,Double> viewInCome2=new HashMap<Integer,Double>();
		Map<Integer,Double> viewInComeCom=new HashMap<Integer,Double>();
		Map<Integer,Double> seeExitInCome=new HashMap<Integer,Double>();
		
		Map<Integer,Double> viewInComeAll=new HashMap<Integer,Double>();
		boolean flag=false;//判断是否遇到记忆点
		for(int i=0;i<10;i++){//收益map的初始化
			viewInCome1.put(i, 0.0);
			viewInCome2.put(i, 0.0);
			viewInComeCom.put(i, 0.0);
			seeExitInCome.put(i, 0.0);
			viewInComeAll.put(i, 0.0);
		}
		for(int i=0;i<m;i++){
			for(int j=0;j<m;j++){
				if(B[i][j].logo==data.LOGO_VIEW){
					int vx=Math.abs(i-px);
					int vy=Math.abs(j-py);
					if(Math.sqrt(vx*vx+vy*vy)<data.VIEW_RANGE){//如果行人与记忆点的距离小于视野半径
						flag=true;//改为TRUE
						B[px][py].setSeeView(true);//改为TRUE
					}
				}
			}
		}
		if(flag){//如果见过记忆点了
			double parallel=exitY-py;//与出口的水平距离
			double vertical=exitX-px;//与出口的垂直距离
			int quadrat=judeQuadrat(parallel, vertical);//判断出口位于行人的第几象限
			double theta=countAnglePeopleAndExit(quadrat, parallel, vertical);//计算与出口的角度
			B[px][py].setRealAngle(theta);//将角度存入realAngle
			double theta1=anticlock(theta);//得到逆时针后的角度
			double theta2=clock(theta);//得到顺时针后的角度
			viewInCome1=countTheta1(theta1, viewInCome1);//得到逆时针后的收益
			viewInCome2=countTheta2(theta2, viewInCome2);//得到顺时针后的收益
			viewInComeCom=isCompleteInTheta(theta1, theta2, viewInComeCom);//得到完全位于k1k2中间的收益
			viewInComeAll=addViemInCome(viewInCome1, viewInCome2, viewInComeCom, seeExitInCome);//将所有收益加起来
		}
		return viewInComeAll;//返回收益map
	}
}
