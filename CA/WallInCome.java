package CA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WallInCome {
	Data data =new Data();
	int m=data.M;
	int range=data.VIEW_RANGE;
	
	
	public Map countWallInCome02(Block B[][],int dx,int dy){
		Map<Integer,Double> clockInCome=new HashMap<Integer, Double>();//方向偏好转化为方向收益
		for(int i=0;i<10;i++){
			clockInCome.put(i,0.0);
		}
		int howWall=judgeWallArea(dx, dy);//行人位于那个墙附近
		isInWall(B, dx, dy);//行人是否在墙壁附近
		
		if(B[dx][dy].isInWall()){//如果行人在墙壁附近
			if(B[dx][dy].getRealAngle()==999){//如果行人咩有见过记忆点
				//使用  那边人多往哪里走
				Map<Integer, Double> aMap=countClockInComeWithPeople(B, dx, dy);
				if(aMap.get(0).doubleValue() - aMap.get(1).doubleValue() >0){
					B[dx][dy].setClock(true);
				}
				else{
					B[dx][dy].setClock(false);
				}
			}
			else if(B[dx][dy].getRealAngle()==666){//如果该行人 是被其他行人改变方向的
				
			}
			else{//如果改行人见过记忆点
				countClockInComeWithView(B, dx, dy);//使用记忆点收益
				changeOthers(B, dx, dy);//改变旁边的人
			}
		}
		
		
		
		
		
		
		//------------------------行人顺逆时针转化为方向收益----------------------------
		if(B[dx][dy].clock){//如果是顺时针
			if((Math.abs(dx-m)<data.VIEW_RANGE)){//1
				if((Math.abs(dx-m)<data.VIEW_RANGE  &  Math.abs(dy-m)<data.VIEW_RANGE )){//12
					clockInCome.put(4,0.5);
					clockInCome.put(7,0.5);
					clockInCome.put(8,0.5);
				}
				else if((Math.abs(dx-m)<data.VIEW_RANGE  &  Math.abs(dy-0)<data.VIEW_RANGE )){//14
					clockInCome.put(2,0.5);
					clockInCome.put(4,0.5);
					clockInCome.put(1,0.5);
				}
				else{
					clockInCome.put(4,0.5);
					clockInCome.put(7,0.5);
				}
			}
			else if((Math.abs(dx-0)<data.VIEW_RANGE)){//3
				if((Math.abs(dx-0)<data.VIEW_RANGE  &  Math.abs(dy-m)<data.VIEW_RANGE )){//23
					clockInCome.put(6,0.5);
					clockInCome.put(9,0.5);
					clockInCome.put(8,0.5);
				}
				else if((Math.abs(dx-0)<data.VIEW_RANGE  &  Math.abs(dy-0)<data.VIEW_RANGE )){//34
					clockInCome.put(6,0.5);
					clockInCome.put(3,0.5);
					clockInCome.put(2,0.5);
				}
				else{
					clockInCome.put(3,0.5);
					clockInCome.put(6,0.5);
				}
			}
			else if((Math.abs(dy-m)<data.VIEW_RANGE)){//2
				clockInCome.put(8,0.5);
				clockInCome.put(9,0.5);
			}
			else if((Math.abs(dy-0)<data.VIEW_RANGE) ){//4
				clockInCome.put(1,0.5);
				clockInCome.put(2,0.5);
			}
			else{
				
			}
		}
		else{//如果是逆时针
			if((Math.abs(dx-m)<data.VIEW_RANGE)){//1
				if((Math.abs(dx-m)<data.VIEW_RANGE  &  Math.abs(dy-0)<data.VIEW_RANGE )){//14
					clockInCome.put(6,0.5);
					clockInCome.put(9,0.5);
					clockInCome.put(8,0.5);
				}
				else if((Math.abs(dx-m)<data.VIEW_RANGE  &  Math.abs(dy-m)<data.VIEW_RANGE )){//12
					clockInCome.put(2,0.5);
					clockInCome.put(3,0.5);
					clockInCome.put(6,0.5);
				}
				else{
					clockInCome.put(6,0.5);
					clockInCome.put(9,0.5);
				}
			}
			else if((Math.abs(dx-0)<data.VIEW_RANGE)){//3
				if((Math.abs(dx-0)<data.VIEW_RANGE  &  Math.abs(dy-m)<data.VIEW_RANGE )){//23
					clockInCome.put(4,0.5);
					clockInCome.put(1,0.5);
					clockInCome.put(2,0.5);
				}
				else if((Math.abs(dx-0)<data.VIEW_RANGE  &  Math.abs(dy-0)<data.VIEW_RANGE )){//34
					clockInCome.put(8,0.5);
					clockInCome.put(7,0.5);
					clockInCome.put(4,0.5);
				}
				else{
					clockInCome.put(1,0.5);
					clockInCome.put(4,0.5);
				}
			}
			else if((Math.abs(dy-0)<data.VIEW_RANGE) ){//4
				clockInCome.put(7,0.5);
				clockInCome.put(8,0.5);
			}
			else if((Math.abs(dy-m)<data.VIEW_RANGE)){//2
				clockInCome.put(2,0.5);
				clockInCome.put(3,0.5);
			}
			else{
				
			}
		}
		return clockInCome;//返回方向收益map
		
	}
	//-------------------计算记忆点--方向偏好--------------------
	public void countClockInComeWithView(Block B[][],int dx,int dy){
		double theta=B[dx][dy].getRealAngle();//获取与出口的角度
			if(theta!=999 & theta!=666){//999是初始值  =999表示没见过记忆点   666表示被见过记忆点的人指点了
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
					if(B[dx][dy].getRealAngle()>=270 & B[dx][dy].getRealAngle()<360){
						B[dx][dy].setRealAngle(999);
					}
					else{
						if(theta<325 | theta>135){
							B[dx][dy].setClock(false);
						}
						else{
							B[dx][dy].setClock(true);
						}
					}
					
					break;
				case 23://如果行人位于右上角
					if(B[dx][dy].getRealAngle()>0 & B[dx][dy].getRealAngle()<90){//如果行人记忆角大于0小于90
						B[dx][dy].setRealAngle(999);//将记忆角消除
						//会出现 行人在左边见过记忆点  但是突然跑到右边墙壁 记忆角完全错误 如果在0-90内，会一直徘徊
					}
					else{
						if(theta>90 & theta<270){
							B[dx][dy].setClock(false);
						}
						else{
							B[dx][dy].setClock(true);
						}
						int ac=B[dx][dy].getIsChangePerView();//这里忘了、、
						B[dx][dy].setIsChangePerView(ac+1);
					}
					break;
				case 34:
						if(B[dx][dy].getRealAngle()>90 & B[dx][dy].getRealAngle()<180){
							B[dx][dy].setRealAngle(999);
						}
						else{
							if(theta>180){
								B[dx][dy].setClock(false);
							}
							else{
								B[dx][dy].setClock(true);
							}
							int ac=B[dx][dy].getIsChangePerView();
							B[dx][dy].setIsChangePerView(ac+1);
						}
						
					
					break;
				case 41:
					if(B[dx][dy].getRealAngle()>180 & B[dx][dy].getRealAngle()<270){
						B[dx][dy].setRealAngle(999);
					}
					else{
						if(theta<45 | theta>325){
							B[dx][dy].setClock(false);
						}
						else{
							B[dx][dy].setClock(true);
						}
					}
					
					break;
				}
			}
	}
	//----------------------计算行人--方向偏好收益--------------------
	public Map countClockInComeWithPeople(Block B[][],int dx,int dy){
		//该方法是 当行人进入墙壁范围时，将行人视野范围内顺时针和逆时针的人数统计出来 放入map 最后返回
		double otherM=0.0;//视野范围里总人数
		int howWall=judgeWallArea(dx, dy);//判断行人位于那个墙附近
		int clockPeo=0;//顺时针行人数量
		int unClockPeo=0;//逆时针行人数量
		if(B[dx][dy].inWall /*& B[dx][dy].isChangeWallInCome()*/){//如果行人位于墙壁附近
			switch (howWall) {
			case 1:
				for(int i=dx;i<dx+data.VIEW_RANGE;i++){//遍历行人附近的格子
					for(int j=dy-data.VIEW_RANGE;j<dy+data.VIEW_RANGE;j++){
						if(i<1 | i>data.M-1 | j<1 | j>data.M-1){//越界处理
							
						}
						else{
							if(B[i][j].getLogo()==data.LOGO_PEOPLE){//如果该格子是行人
								otherM++;//行人总数+1
								if(B[i][j].clock){//如果该行人是顺时针方向
									clockPeo++;//顺时针行人数量+1
								}
								else{
									unClockPeo++;//反之 逆时针行人数量+1
								}
							}
						}
						
					}
				}
				break;
			case 2:
				for(int i=dx-data.VIEW_RANGE;i<dx+data.VIEW_RANGE;i++){
					for(int j=dy;j<dy+data.VIEW_RANGE;j++){
						if(i<1 | i>data.M-1 | j<1 | j>data.M-1){
							
						}
						else{
							if(B[i][j].getLogo()==data.LOGO_PEOPLE){
								otherM++;
								if(B[i][j].clock){
									clockPeo++;
								}
								else{
									unClockPeo++;
								}
							}
						}
						
					}
				}
				break;
			case 3:
				for(int i=dx-data.VIEW_RANGE;i<dx;i++){
					for(int j=dy-data.VIEW_RANGE;j<dy+data.VIEW_RANGE;j++){
						if(i<1 | i>data.M-1 | j<1 | j>data.M-1){
							
						}
						else{
							if(B[i][j].getLogo()==data.LOGO_PEOPLE){
								otherM++;
								if(B[i][j].clock){
									clockPeo++;
								}
								else{
									unClockPeo++;
								}
							}
						}
						
					}
				}
				break;
			case 4:
				for(int i=dx-data.VIEW_RANGE;i<dx+data.VIEW_RANGE;i++){
					for(int j=dy-data.VIEW_RANGE;j<dy;j++){
						if(i<1 | i>data.M-1 | j<1 | j>data.M-1){
							
						}
						else{
							if(B[i][j].getLogo()==data.LOGO_PEOPLE){
								otherM++;
								if(B[i][j].clock){
									clockPeo++;
								}
								else{
									unClockPeo++;
								}
							}
						}
						
					}
				}
				break;
			case 12:
				for(int i=dx;i<dx+data.VIEW_RANGE;i++){
					for(int j=dy;j<dy+data.VIEW_RANGE;j++){
						if(i<1 | i>data.M-1 | j<1 | j>data.M-1){
							
						}
						else{
							if(B[i][j].getLogo()==data.LOGO_PEOPLE){
								otherM++;
								if(B[i][j].clock){
									clockPeo++;
								}
								else{
									unClockPeo++;
								}
							}
						}
						
					}
				}
				break;
			case 23:
				for(int i=dx-data.VIEW_RANGE;i<dx;i++){
					for(int j=dy;j<dy+data.VIEW_RANGE;j++){
						if(i<1 | i>data.M-1 | j<1 | j>data.M-1){
							
						}
						else{
							if(B[i][j].getLogo()==data.LOGO_PEOPLE){
								otherM++;
								if(B[i][j].clock){
									clockPeo++;
								}
								else{
									unClockPeo++;
								}
							}
						}
						
					}
				}
				break;
			case 34:
				for(int i=dx-data.VIEW_RANGE;i<dx;i++){
					for(int j=dy-data.VIEW_RANGE;j<dy;j++){
						if(i<1 | i>data.M-1 | j<1 | j>data.M-1){
							
						}
						else{
							if(B[i][j].getLogo()==data.LOGO_PEOPLE){
								otherM++;
								if(B[i][j].clock){
									clockPeo++;
								}
								else{
									unClockPeo++;
								}
							}
						}
						
					}
				}
				break;
			case 41:
				for(int i=dx;i<dx+data.VIEW_RANGE;i++){
					for(int j=dy-data.VIEW_RANGE;j<dy;j++){
						if(i<1 | i>data.M-1 | j<1 | j>data.M-1){
							
						}
						else{
							if(B[i][j].getLogo()==data.LOGO_PEOPLE){
								otherM++;
								if(B[i][j].clock){
									clockPeo++;
								}
								else{
									unClockPeo++;
								}
							}
						}
						
					}
				}
				break;
			default:
				break;
				
			}
			B[dx][dy].setChangeWallInCome(false);//???
		}
		Map<Integer,Double> aMap=new HashMap<>();//hashmap
		aMap.put(0, (double) clockPeo);//分别存入顺时针和逆时针行人
		aMap.put(1, (double) unClockPeo);
		
		return aMap;//防护cmap
	}
	//--------------------看见过记忆点的行人改变其他人的方向偏好------------
	public void changeOthers(Block B[][],int dx,int dy){
		double rom=Math.random()-0.5;//产生一个正负0.5的随机数
			boolean preference=B[dx][dy].isClock();//行人方向偏好
			for(int i=dx-1;i<dx+1;i++){//遍历行人周围的8个格子
				for(int j=dy-1;j<dy+1;j++){
					if((i<1 | i>data.M-1 | j<1 | j>data.M-1)){//越界处理
						
					}
					else{
						if(B[i][j].getLogo()==data.LOGO_PEOPLE){//如果是行人
							//如果该行人见过记忆点了   此处可能有问题
							if(B[dx][dy].getRealAngle()!=999 & B[dx][dy].getRealAngle()!=666){
								if(preference){//如果他是顺时针移动
									if(rom>0){//有50%的概率
										B[i][j].setClock(true);//将周围行人设置为TRUE
										B[i][j].setChangePerForOther(true);//设置 已经由其他行人改变过偏好了
									}
									if(B[i][j].getRealAngle()==999){//如果该行人没有见过记忆点
										B[i][j].setRealAngle(666);//666表示没见过记忆点  但是经过 看到过记忆点的人 的提醒了
									}
								}
								else{
									if(rom>0){//有50%的概率
										B[i][j].setClock(false);//设置为逆时针
										B[i][j].setChangePerForOther(true);
									}
									if(B[i][j].getRealAngle()==999){
										B[i][j].setRealAngle(666);
									}
								}
							}
						}
					}
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
	//解决死锁问题 
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
		//---------------检查是否在墙壁附近------------------
//		public void isInWall(Block B[][],int dx,int dy){
//			for(int i=dx-data.VIEW_RANGE;i<dx+data.VIEW_RANGE;i++){
//				for(int j=dy-data.VIEW_RANGE;j<dy+data.VIEW_RANGE;j++){
//					if(i<1 | i>data.M-1 | j<1 | j>data.M-1){
//						
//					}
//					else{
//						if(B[i][j].getLogo()==data.LOGO_WALL){
//							B[dx][dy].setInWall(true);
//						}
//						else{
//							B[dx][dy].setInWall(false);
//							B[dx][dy].setChangeWallInCome(false);
//						}
//					}
//				}
//			}
//		}
		public void isInWall(Block B[][],int dx,int dy){
			if(Math.abs(dx-m)<data.VIEW_RANGE | Math.abs(dy-m)<data.VIEW_RANGE | Math.abs(dx-0)<data.VIEW_RANGE | Math.abs(dy-0)<data.VIEW_RANGE){
				B[dx][dy].setInWall(true);;
			}
			else{
				B[dx][dy].setInWall(false);
			}
		}
		
}
