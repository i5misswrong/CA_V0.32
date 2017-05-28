package CA;

import java.util.ArrayList;

public class Rule {
	//规则类，负责检查碰撞，行人最终方向的获取
	Data data=new Data();
	ViewInCome VIC=new ViewInCome();
	Common common=new Common();
	//---------------检查碰撞方法----------------------
	public boolean isCrashOthers(Block B[][],int i,int j,int d){
		boolean flag=false;//如果下一点不能行动，返回TRUE
		int x=0;
		int y=0;
		switch (d) {
		case 1:
			x=i-1;
			y=j-1;
			break;
		case 2:
			x=i-1;
			y=j;
			break;
		case 3:
			x=i-1;
			y=j+1;
			break;
		case 4:
			x=i;
			y=j-1;
			break;
		case 5:
			x=i;
			y=j;
			break;
		case 6:
			x=i;
			y=j+1;
			break;
		case 7:
			x=i+1;
			y=j-1;
			break;
		case 8:
			x=i+1;
			y=j;
			break;
		case 9:
			x=i+1;
			y=j+1;
			break;
		default:
			break;
		}
		if(B[x][y].logo==data.LOGO_PEOPLE){
			flag=true;
		}
		if(B[x][y].logo==data.LOGO_WALL){
			flag=true;
		}
		if(B[x][y].logo==data.LOGO_VIEW){
			flag=true;
		}
		return flag;
	}
	//--------------获取最终方向的方法----------------------------
	public int getCorrectDirection(Block B[][],int dx,int dy){
		ArrayList<Direction> arrAll=common.addInCome(B, dx, dy);//获取方向集合
		int d=1;
		int h=0;
		boolean flag=false;
		while(isCrashOthers(B, dx,dy,arrAll.get(h).getDirection())){//遍历集合，直到找出一个可以移动的方向
			h++;
			if(h>2){
				flag=true;//如果8次之后还没找到，结束循环
				break;
			}
		}
		if(flag){
			d=5;//没找到下一步的移动方向的，原地等待
		}
		else{
			d=arrAll.get(h).getDirection();
		}
		return d;//返回int
	}
	//------------------判断到达出口-------------------------
	public boolean isGoExit(Block B[][],int dx,int dy){
		boolean flag=false;
		if(B[dx][dy].logo==data.LOGO_EXIT){
			flag=true;
		}
		return flag;
	}
}
