package CA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WallInCome {
	Data data =new Data();
	int m=data.M;
	int range=data.VIEW_RANGE;
	
	
	public Map countWallInCome02(Block B[][],int dx,int dy){
		Map<Integer,Double> clockInCome=new HashMap<Integer, Double>();
		for(int i=0;i<10;i++){
			clockInCome.put(i,0.0);
		}
		int howWall=judgeWallArea(dx, dy);//行人位于那个墙附近
		if(howWall!=1){//如果不是最下面的墙
			if(isInRange(B, dx, dy) ){//如果看见过记忆点
				//if(B[dx][dy].isChangeClockView<=3){
					countClockInComeWithView(B, dx, dy);//使用记忆点--方向偏好收益
				//}
				
			}
			else{
				countClockInComeWithPeople(B, dx, dy);//使用行人--方向偏好收益
			}
		}
		//------------------------行人顺逆时针转化为方向收益----------------------------
		if(B[dx][dy].clock){//如果是顺时针
			if((Math.abs(dx-m)<data.VIEW_RANGE)){//1
				if((Math.abs(dx-m)<data.VIEW_RANGE  &  Math.abs(dy-m)<data.VIEW_RANGE )){//12
					clockInCome.put(4,0.5+(Math.random()*0.01));
					clockInCome.put(7,0.5+(Math.random()*0.01));
					clockInCome.put(8,0.5+(Math.random()*0.01));
				}
				else if((Math.abs(dx-m)<data.VIEW_RANGE  &  Math.abs(dy-0)<data.VIEW_RANGE )){//14
					clockInCome.put(2,0.5+(Math.random()*0.01));
					clockInCome.put(4,0.5+(Math.random()*0.01));
					clockInCome.put(1,0.5+(Math.random()*0.01));
				}
				else{
					clockInCome.put(4,0.5+(Math.random()*0.01));
					clockInCome.put(7,0.5+(Math.random()*0.01));
				}
			}
			else if((Math.abs(dx-0)<data.VIEW_RANGE)){//3
				if((Math.abs(dx-0)<data.VIEW_RANGE  &  Math.abs(dy-m)<data.VIEW_RANGE )){//23
					clockInCome.put(6,0.5+(Math.random()*0.01));
					clockInCome.put(9,0.5+(Math.random()*0.01));
					clockInCome.put(8,0.5+(Math.random()*0.01));
				}
				else if((Math.abs(dx-0)<data.VIEW_RANGE  &  Math.abs(dy-0)<data.VIEW_RANGE )){//34
					clockInCome.put(6,0.5+(Math.random()*0.01));
					clockInCome.put(3,0.5+(Math.random()*0.01));
					clockInCome.put(2,0.5+(Math.random()*0.01));
				}
				else{
					clockInCome.put(3,0.5+(Math.random()*0.01));
					clockInCome.put(6,0.5+(Math.random()*0.01));
				}
			}
			else if((Math.abs(dy-m)<data.VIEW_RANGE)){//2
				clockInCome.put(8,0.5+(Math.random()*0.01));
				clockInCome.put(9,0.5+(Math.random()*0.01));
			}
			else if((Math.abs(dy-0)<data.VIEW_RANGE) ){//4
				clockInCome.put(1,0.5+(Math.random()*0.01));
				clockInCome.put(2,0.5+(Math.random()*0.01));
			}
			else{
				
			}
		}
		else{//如果是逆时针
			if((Math.abs(dx-m)<data.VIEW_RANGE)){//1
				if((Math.abs(dx-m)<data.VIEW_RANGE  &  Math.abs(dy-0)<data.VIEW_RANGE )){//14
					clockInCome.put(6,0.5+(Math.random()*0.01));
					clockInCome.put(9,0.5+(Math.random()*0.01));
					clockInCome.put(8,0.5+(Math.random()*0.01));
				}
				else if((Math.abs(dx-m)<data.VIEW_RANGE  &  Math.abs(dy-m)<data.VIEW_RANGE )){//12
					clockInCome.put(2,0.5+(Math.random()*0.01));
					clockInCome.put(3,0.5+(Math.random()*0.01));
					clockInCome.put(6,0.5+(Math.random()*0.01));
				}
				else{
					clockInCome.put(6,0.5+(Math.random()*0.01));
					clockInCome.put(9,0.5+(Math.random()*0.01));
				}
			}
			else if((Math.abs(dx-0)<data.VIEW_RANGE)){//3
				if((Math.abs(dx-0)<data.VIEW_RANGE  &  Math.abs(dy-m)<data.VIEW_RANGE )){//23
					clockInCome.put(4,0.5+(Math.random()*0.01));
					clockInCome.put(1,0.5+(Math.random()*0.01));
					clockInCome.put(2,0.5+(Math.random()*0.01));
				}
				else if((Math.abs(dx-0)<data.VIEW_RANGE  &  Math.abs(dy-0)<data.VIEW_RANGE )){//34
					clockInCome.put(8,0.5+(Math.random()*0.01));
					clockInCome.put(7,0.5+(Math.random()*0.01));
					clockInCome.put(4,0.5+(Math.random()*0.01));
				}
				else{
					clockInCome.put(1,0.5+(Math.random()*0.01));
					clockInCome.put(4,0.5+(Math.random()*0.01));
				}
			}
			else if((Math.abs(dy-0)<data.VIEW_RANGE) ){//4
				clockInCome.put(7,0.5+(Math.random()*0.01));
				clockInCome.put(8,0.5+(Math.random()*0.01));
			}
			else if((Math.abs(dy-m)<data.VIEW_RANGE)){//2
				clockInCome.put(2,0.5+(Math.random()*0.01));
				clockInCome.put(3,0.5+(Math.random()*0.01));
			}
			else{
				
			}
		}
		return clockInCome;//返回方向收益map
		
	}
	//-------------------计算记忆点--方向偏好--------------------
	public void countClockInComeWithView(Block B[][],int dx,int dy){
		double theta=B[dx][dy].getRealAngle();//获取与出口的角度
			if(theta!=999){//999是初始值  =999表示没见过记忆点
				int wallArea=judgeWallArea(dx, dy);//在那个墙附近
				switch (wallArea) {
				case 1:
					if(theta<90 | theta>270){
						B[dx][dy].setClock(false);
					}
					else{
						B[dx][dy].setClock(true);
					}
					break;
				case 2:
					if(theta<180){
						B[dx][dy].setClock(false);
					}
					else{
						B[dx][dy].setClock(true);
					}
					break;
				case 3:
					if(theta>90 & theta<270){
						B[dx][dy].setClock(false);
					}
					else{
						B[dx][dy].setClock(true);
					}
					break;
				case 4:
					if(theta>180){
						B[dx][dy].setClock(false);
					}
					else{
						B[dx][dy].setClock(true);
					}
					break;
				case 12:
					if(theta<325 | theta>135){
						B[dx][dy].setClock(false);
					}
					else{
						B[dx][dy].setClock(true);
					}
					break;
				case 23:
					if(theta>45 & theta<225){
						B[dx][dy].setClock(false);
					}
					else{
						B[dx][dy].setClock(true);
					}
					break;
				case 34:
					if(theta>135 & theta<325){
						B[dx][dy].setClock(false);
					}
					else{
						B[dx][dy].setClock(true);
					}
					break;
				case 41:
					if(theta<45 | theta>325){
						B[dx][dy].setClock(false);
					}
					else{
						B[dx][dy].setClock(true);
					}
					break;
				}
				B[dx][dy].setIsChangeClockView(B[dx][dy].getIsChangeClockView()+1);// 表示已经见过记忆点 并且设置了方向偏好
			}
	}
	//----------------------计算行人--方向偏好收益--------------------
	public void countClockInComeWithPeople(Block B[][],int dx,int dy){
		double otherM=0.0;//视野范围里总人数
		double otherN=0.0;//与自己方向偏好相反的人数
		double otherP=0.0;//位于行人逆时针方向的人数
		int howWall=judgeWallArea(dx, dy);
		for(int i=0;i<m;i++){
			for(int j=0;j<m;j++){
				if(B[i][j].logo==data.LOGO_PEOPLE){
					if(Math.sqrt( (dx-i)*(dx-i)+(dy-j)*(dy-j) )<data.VIEW_RANGE){
						otherM++;
						if(B[dx][dy].clock!=B[i][j].clock){
							otherN++;
						}
						switch (howWall) {
						case 1:
							if(j>dy){
								otherP++;
							}
							break;
						case 2:
							if(i<dx){
								otherP++;
							}
							break;
						case 3:
							if(j<dy){
								otherP++;
							}
							break;
						case 4:
							if(i>dx){
								otherP++;
							}
							break;
						case 12:
							if(i<dx & j>dy){
								otherP++;
							}
							break;
						case 23:
							if(i<dx & j<dy){
								otherP++;
							}
							break;
						case 34:
							if(i>dx & j<dy){
								otherP++;
							}
							break;
						case 41:
							if(i>dx & j>dy){
								otherP++;
							}
							break;
						default:
							break;
						}
					}
				}
			}
		}
		
		double romTen=(Math.random()-0.5)/5;//正负0.01-0.1的随机数
		double probably=0.0;//行人改变偏好的概率
		if(B[dx][dy].isChangeClock>=3){//行人只能改变3次偏好 由行人产生
			probably=0.1;
		}
		else{
			probably=otherN/(otherM) ;//+romTen;
			if(probably>0.8){//如果大于0.5
				B[dx][dy].setIsChangeClock(B[dx][dy].getIsChangeClock()+1);//次数+1
				B[dx][dy].setClock(!B[dx][dy].isClock());//改变偏好
			}
		}
		
	}
	//--------------判断行人在哪个墙附近---------------------
	public int judgeWallArea(int dx,int dy){
		int wallArea=0;
		if(Math.abs(dx-m)<range & Math.abs(dy-m)<range){
			wallArea=12;
		}
		else if(Math.abs(dx-0)<range & Math.abs(dy-m)<range){
			wallArea=23;
		}
		else if(Math.abs(dx-0)<range & Math.abs(dy-0)<range){
			wallArea=34;
		}
		else if(Math.abs(dx-m)<range & Math.abs(dy-0)<range){
			wallArea=41;
		}
		else if(Math.abs(dx-m)<range){
			wallArea=1;
		}
		else if(Math.abs(dy-m)<range){
			wallArea=2;
		}
		else if(Math.abs(dx-0)<range){
			wallArea=3;
		}
		else if(Math.abs(dy-0)<range){
			wallArea=4;
		}
		return wallArea;
		
	}
	
	//----------------检查是否在出口附近-----------------
		public boolean isInExit(Block B[][],int dx,int dy){
			boolean flag=false;
			for(int i=0;i<data.M;i++){
				for(int j=0;j<data.M;j++){
					if(B[i][j].logo==data.LOGO_EXIT){
						double ex=data.EXIT_X-dx;
						double ey=data.EXIT_Y_HALF-dy;
						if(Math.sqrt(ex*ex+ey*ey)<data.VIEW_RANGE){
							flag=true;
						}
					}
				}
			}
			return flag;
		}
		//-----------------检查是否在记忆点附近-----------------
		public boolean isInRange(Block B[][],int dx,int dy){
			boolean flag=false;
			for(int i=0;i<data.M;i++){
				for(int j=0;j<data.M;j++){
					if(B[i][j].logo==data.LOGO_VIEW){
						double parallel=j-dy;
						double vertical=i-dx;
						if((Math.sqrt(parallel*parallel+vertical*vertical)<data.VIEW_RANGE)){
							flag=true;
						}
					}
				}
			}
			
			return flag;
		}
}
