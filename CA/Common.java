package CA;

import java.security.AllPermission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Common {
	//公共方法类
	ViewInCome VIC=new ViewInCome();
	PeopleInCome PIC=new PeopleInCome();
	ExitInCome EIC=new ExitInCome();
	WallInCome WIC=new WallInCome();
	OldDirectionInCome ODIC=new OldDirectionInCome();
	Data data=new Data();
	int m=data.M;
	//-------------计算所有收益之和-------------------
	public ArrayList<Direction> addInCome(Block B[][],int dx,int dy){
		ArrayList<Direction> allInCome=new ArrayList<>();//总收益ArrayList
		Map<Integer,Double> allMap=new HashMap<>();//总收益map
		for(int i=0;i<10;i++){
			allMap.put(i,0.0);//初始化
		}
		Map<Integer,Double> viewMap=VIC.outPutDirection(B,dx, dy);//记忆点收益
		Map<Integer,Double> peopleMap=PIC.countPeoInCome(B, dx, dy);//行人收益
		Map<Integer,Double> wallMap=WIC.countWallInCome02(B, dx, dy);//墙壁收益
		Map<Integer,Double> oldDMap=ODIC.getOldDirection(B, dx, dy);//旧方向收益
		ArrayList<Direction> exitInCome=EIC.countExitInCome(dx, dy);//出口收益
		isSeeExit(B, dx, dy);
		if(B[dx][dy].isSeeExit | isInExit(B, dx, dy)){//如果在出口范围内 或者 看到过出口
			for(int i=0;i<exitInCome.size();i++){
				Direction d=new Direction();
				d.setDirection(exitInCome.get(i).getDirection());
				d.setInCome(exitInCome.get(i).getInCome());
				allInCome.add(d);//将出口收益存到总收益中
			}
		}
		else{
			if(isInWall(B, dx, dy)){//如果看到墙了
				for(int i=0;i<10;i++){
					allMap.put(i,wallMap.get(i));
				}
				for(int i=0;i<allMap.size();i++){
					Direction d=new Direction();
					d.setDirection(i);
					d.setInCome(allMap.get(i)+Math.random()*0.01);
					allInCome.add(d);//将墙壁收益放到总收益中
				}
			}
			else{//没有看到出口 也没有看到墙壁
				for(int i=0;i<10;i++){
					allMap.put(i,viewMap.get(i)+peopleMap.get(i)+oldDMap.get(i));//包含记忆收益 行人收益和旧方向收益
				}
				for(int i=0;i<allMap.size();i++){
					Direction d=new Direction();
					d.setDirection(i);
					d.setInCome(allMap.get(i)+Math.random()*0.01);
					allInCome.add(d);//放入总收益中
				}
			}
		}
			
		for(int i=0;i<allInCome.size();i++){//去除总收益中的null和方向=0的情况
			if(allInCome.get(i)==null | allInCome.get(i).getDirection()==0){
				allInCome.remove(i);
				--i;
			}
		}
		for(int i=0;i<allInCome.size()-1;i++){//排序
			for(int j=0;j<allInCome.size()-1;j++){
				if(allInCome.get(j).getInCome()<allInCome.get(j+1).getInCome()){
					Direction d1=allInCome.get(j);
					Direction d2=allInCome.get(j+1);
					allInCome.set(j, d2);
					allInCome.set(j+1,d1);
				}
			}
		}
		return allInCome;//返回总收益ArrayList
	}
//	public ArrayList<Direction> addInCome(Block B[][],int dx,int dy){
//		ArrayList<Direction> arrDir=VIC.outPutDirection(dx, dy);
//		ArrayList<Direction> arrPeo=PIC.countPeoInCome(B, dx, dy);
//		ArrayList<Direction> arrExit=EIC.countExitInCome(dx, dy);
//		ArrayList<Direction> arrWall=WIC.countWallInCome(B, dx, dy);
//		ArrayList<Direction> arrAll=new ArrayList<>();
//		if(isInExit(B, dx, dy)){
//			for(int i=0;i<arrExit.size();i++){
//				Direction d=new Direction();
//				d.setDirection(arrExit.get(i).getDirection());
//				d.setInCome(arrExit.get(i).getInCome());
//				arrAll.add(d);
//			}
//		}
//		else{
//			if(isInRange(B,dx, dy)){//如果行人在视野点附近
//				for(int i=0;i<arrDir.size();i++){
//					for(int j=0;j<arrPeo.size();j++){
//						for(int k=0;k<arrWall.size();k++){
//							if(arrDir.get(i).getDirection()==arrPeo.get(j).getDirection()){
//								if(arrDir.get(i).getDirection()==arrWall.get(k).getDirection()){
//									Direction d=new Direction();
//									d.setDirection(arrDir.get(i).getDirection());
//									d.setInCome(arrDir.get(i).getInCome()+arrPeo.get(j).getInCome()+arrWall.get(k).getInCome()+Math.random()*0.001);
//									arrAll.add(d);
//								}
//								
//							}
//						}
//						
//					}
//				}
//			}
//			else{//行人在盲区
//				for(int i=0;i<arrWall.size();i++){
//					for(int j=0;j<arrPeo.size();j++){
//						if(arrWall.get(i).getDirection()==arrPeo.get(j).getDirection()){
//							Direction d=new Direction();
//							d.setDirection(arrWall.get(i).getDirection());
//							d.setInCome(arrPeo.get(j).getInCome()+arrWall.get(i).getInCome()+Math.random()*0.001);
//							arrAll.add(d);
//						}
//					}
//				}
//			}
//		}
//		
//		//去除null和方向为0的数据
//		for(int i=0;i<arrAll.size();i++){
//			if(arrAll.get(i)==null | arrAll.get(i).getDirection()==0){
//				arrAll.remove(i);
//				--i;
//			}
//		}
//		//排序
//		for(int i=0;i<arrAll.size()-1;i++){
//			for(int j=0;j<arrAll.size()-1;j++){
//				if(arrAll.get(j).getInCome()<arrAll.get(j+1).getInCome()){
//					Direction d1=arrAll.get(j);
//					Direction d2=arrAll.get(j+1);
//					arrAll.set(j, d2);
//					arrAll.set(j+1,d1);
//				}
//			}
//		}
//		return arrAll;
//	}
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
	//-----------------设置isseeexit-------------------------
	public void isSeeExit(Block B[][],int dx,int dy){
		boolean flag=false;
		for(int i=0;i<data.M;i++){
			for(int j=0;j<data.M;j++){
				if(B[i][j].logo==data.LOGO_EXIT){
					double ex=data.EXIT_X-dx;
					double ey=data.EXIT_Y_HALF-dy;
					if(Math.sqrt(ex*ex+ey*ey)<data.VIEW_RANGE){
						B[dx][dy].setSeeExit(true);
					}
				}
			}
		}
	}
	//----------------检查是否在请附近-------------------
	public boolean isInWall(Block B[][],int dx,int dy){
		boolean flag=false;
		if(Math.abs(dx-m)<data.VIEW_RANGE | Math.abs(dy-m)<data.VIEW_RANGE | Math.abs(dx-0)<data.VIEW_RANGE | Math.abs(dy-0)<data.VIEW_RANGE){
			flag=true;
		}
		return flag;
	}
}
