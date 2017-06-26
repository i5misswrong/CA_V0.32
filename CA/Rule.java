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
			if(h>3){
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
	public void peoMove(Block B[][],int dx,int dy,int d){
		switch (d) {//进行移动
		case 1:
			B[dx-1][dy-1]=B[dx][dy];//移动点
			B[dx-1][dy-1].setMove(false);//移动过该点了  本次循环忽视该点
			B[dx][dy]=new Block(data.LOGO_NULL);//将原先点设为null
			break;
		case 2:
			B[dx-1][dy]=B[dx][dy];
			B[dx-1][dy].setMove(false);
			B[dx][dy]=new Block(data.LOGO_NULL);
			break;
		case 3:
			B[dx-1][dy+1]=B[dx][dy];
			B[dx-1][dy+1].setMove(false);
			B[dx][dy]=new Block(data.LOGO_NULL);
			break;
		case 4:
			B[dx][dy-1]=B[dx][dy];
			B[dx][dy-1].setMove(false);
			B[dx][dy]=new Block(data.LOGO_NULL);
			break;
		case 5:
			break;
		case 6:
			B[dx][dy+1]=B[dx][dy];
			B[dx][dy+1].setMove(false);
			B[dx][dy]=new Block(data.LOGO_NULL);
			break;
		case 7:
			B[dx+1][dy-1]=B[dx][dy];
			B[dx+1][dy-1].setMove(false);
			B[dx][dy]=new Block(data.LOGO_NULL);
			break;
		case 8:
			B[dx+1][dy]=B[dx][dy];
			B[dx+1][dy].setMove(false);
			B[dx][dy]=new Block(data.LOGO_NULL);
			break;
		case 9:
			B[dx+1][dy+1]=B[dx][dy];
			B[dx+1][dy+1].setMove(false);
			B[dx][dy]=new Block(data.LOGO_NULL);
			break;
		default:
			break;
		}
	}
	public void peoMoveArray(ArrayList<Block> BA,int z,int dx,int dy,int d){
		switch (d) {
		case 1:
			BA.get(z).setX(dx-1);
			BA.get(z).setY(dy-1);
			break;
		case 2:
			BA.get(z).setX(dx-1);
			BA.get(z).setY(dy);
			break;
		case 3:
			BA.get(z).setX(dx-1);
			BA.get(z).setY(dy+1);
			break;
		case 4:
			BA.get(z).setX(dx);
			BA.get(z).setY(dy-1);
			break;
		case 5:
			BA.get(z).setX(dx);
			BA.get(z).setY(dy);
			break;
		case 6:
			BA.get(z).setX(dx);
			BA.get(z).setY(dy+1);
			break;
		case 7:
			BA.get(z).setX(dx+1);
			BA.get(z).setY(dy-1);
			break;
		case 8:
			BA.get(z).setX(dx+1);
			BA.get(z).setY(dy);
			break;
		case 9:
			BA.get(z).setX(dx+1);
			BA.get(z).setY(dy+1);
			break;
			
		default:
			break;
		}
	}
	public void deadLock(Block B[][]){
		int lockRange=15; //假定的死锁范围
		int t=0;//死锁区域内行人计数器
		for(int i=1;i<lockRange;i++){
			for(int j=1;j<lockRange;j++){
				if(B[i][j].getDirection()==5){
					t++;
				}
			}
		}
		double p=(double)t/(lockRange*lockRange);//原地不动的人占总人数的比例
		boolean flag=false;
		if((Math.random()-0.5)>0){
			flag=true;
		}
		if(p>0.6){
			for(int i=1;i<lockRange;i++){
				for(int j=1;j<lockRange;j++){
					B[i][j].setClock(flag);
				}
			}
		}
	}
}
