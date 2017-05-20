package CA;

import java.util.ArrayList;

public class Common {
	//公共方法类
	ViewInCome VIC=new ViewInCome();
	PeopleInCome PIC=new PeopleInCome();
	ExitInCome EIC=new ExitInCome();
	Data data=new Data();
	//-------------计算所有收益之和-------------------
	public ArrayList<Direction> addInCome(Block B[][],int dx,int dy){
		ArrayList<Direction> arrDir=VIC.outPutDirection(dx, dy);
		ArrayList<Direction> arrPeo=PIC.countPeoInCome(B, dx, dy);
		ArrayList<Direction> arrExit=EIC.countExitInCome(dx, dy);
		ArrayList<Direction> arrAll=new ArrayList<>();
		if(isInExit(B, dx, dy)){
			for(int i=0;i<arrExit.size();i++){
				Direction d=new Direction();
				d.setDirection(arrExit.get(i).getDirection());
				d.setInCome(arrExit.get(i).getInCome());
				arrAll.add(d);
			}
		}
		else{
			if(isInRange(B,dx, dy)){//如果行人在视野点附近
				for(int i=0;i<arrDir.size();i++){
					for(int j=0;j<arrPeo.size();j++){
						if(arrDir.get(i).getDirection()==arrPeo.get(j).getDirection()){
							Direction d=new Direction();
							d.setDirection(arrDir.get(i).getDirection());
							d.setInCome(arrDir.get(i).getInCome()+arrPeo.get(j).getInCome());
							arrAll.add(d);
						}
					}
				}
			}
			else{//行人在盲区
				for(int i=0;i<arrDir.size();i++){
					for(int j=0;j<arrPeo.size();j++){
						if(arrDir.get(i).getDirection()==arrPeo.get(j).getDirection()){
							Direction d=new Direction();
							d.setDirection(arrDir.get(i).getDirection());
							d.setInCome(arrPeo.get(j).getInCome());
							arrAll.add(d);
						}
					}
				}
			}
		}
		
		//去除null和方向为0的数据
		for(int i=0;i<arrAll.size();i++){
			if(arrAll.get(i)==null | arrAll.get(i).getDirection()==0){
				arrAll.remove(i);
				--i;
			}
		}
		//排序
		for(int i=0;i<arrAll.size()-1;i++){
			for(int j=0;j<arrAll.size()-1;j++){
				if(arrAll.get(j).getInCome()<arrAll.get(j+1).getInCome()){
					Direction d1=arrAll.get(j);
					Direction d2=arrAll.get(j+1);
					arrAll.set(j, d2);
					arrAll.set(j+1,d1);
				}
			}
		}
		return arrAll;
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
	//----------------检查是否在出口附近-----------------
	public boolean isInExit(Block B[][],int dx,int dy){
		boolean flag=false;
		for(int i=0;i<data.M;i++){
			for(int j=0;j<data.M;j++){
				if(B[i][j].logo==data.LOGO_EXIT){
					double ex=data.EXIT_X-dx;
					double ey=data.EXIT_Y-dy;
					if(Math.sqrt(ex*ex+ey*ey)<data.VIEW_RANGE){
						flag=true;
					}
				}
			}
		}
		return flag;
	}
}
