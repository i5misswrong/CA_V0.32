package CA;

public class RunFiction {
	public static void main(String [] args){
		Data data=new Data();
		Rule rule=new Rule();
		DrawFirst DF=new DrawFirst();
		int m=data.M;
		int peopleNumber=data.PEOPLE_NUMBER;//行人总数
		int viewNumber=data.VIEW_NUMBER;//记忆点总数
		int h=0;//疏散步长计数器
		Block B[][]=new Block[m][m];//所有方块矩阵
		for(int i=0;i<m;i++){
			for(int j=0;j<m;j++){
				B[i][j]=new Block(data.LOGO_NULL);//初始化矩阵
			}
		}
		for(int i=0;i<m;i++){
			for(int j=0;j<m;j++){
				B[0][j]=new Block(data.LOGO_WALL);//设置墙壁
				B[m-1][j]=new Block(data.LOGO_WALL);
				B[i][0]=new Block(data.LOGO_WALL);
				B[i][m-1]=new Block(data.LOGO_WALL);
			}
		}
		B[data.EXIT_X][data.EXIT_Y]=new Block(data.LOGO_EXIT);//设置出口
		
		//----------随机产生行人--------------------------
		int t=0;//计数器
		while(t<peopleNumber){//如果计数器小于行人总数，则一直循环
			int rx=(int)(Math.random()*(data.M-2))+2;//随机x y坐标
			int ry=(int)(Math.random()*(data.M-2))+2;
			if(B[rx][ry].logo==0){//如果这一点为空
				B[rx][ry]=new Block(data.LOGO_PEOPLE);//添加行人
				double c=Math.random()-0.5;
				if(c>0){//行人遇到墙时 优先逆时针还时顺时针
					B[rx][ry].setClock(false);// false 是逆时针
				}
				else{
					B[rx][ry].setClock(true);//TRUE 是顺时针
				}
				B[rx][ry].setRealAngle(999);
				t++;//计数器+1
			}
		}
		
		//---------产生指定行人-----------------------------------
//		B[10][40]=new Block(data.LOGO_PEOPLE);
//		B[10][40].setClock(true);
//		B[6][18]=new Block(data.LOGO_PEOPLE);
		//------------------------------------------------------
		
		//----------产生指定记忆点-------------------------------------
//		B[10][15]=new Block(data.LOGO_VIEW);
//		B[6][18]=new Block(data.LOGO_VIEW);
		B[10][10]=new Block(data.LOGO_VIEW);
		B[40][10]=new Block(data.LOGO_VIEW);
		B[10][40]=new Block(data.LOGO_VIEW);
		B[40][40]=new Block(data.LOGO_VIEW);
		
		//----------产生随机记忆点-----------------------------------
//		int k=0;//同上
//		while(k<viewNumber){
//			boolean flag=true;
//			int rx=(int)(Math.random()*(data.M-2))+2;//随机x y坐标
//			int ry=(int)(Math.random()*(data.M-2))+2;
//			if(B[rx][ry].logo==0 ){
//				B[rx][ry]=new Block(data.LOGO_VIEW);
//				k++;
//			}
//		}
		//------------------------------------------------------
		
		DF.onColor(B);//第一次绘图
		 try {
             Thread.sleep(5000);//休眠5s 便于观察
         } catch (Exception e) {
             e.printStackTrace();
         }
		while(true){//主程序循环体
			for(int i=1;i<m-1;i++){
				for(int j=1;j<m-1;j++){
					B[i][j].setMove(true);//将所有行人的可移动状态设为TRUE 可以移动
				}
			}
			for(int i=1;i<m-1;i++){//跳过边界遍历
				for(int j=1;j<m-1;j++){
					if(B[i][j].logo==1 & B[i][j].isMove){//如果该点是行人
						int d=rule.getCorrectDirection(B, i, j);//获取方向
						B[i][j].setDirection(d);
						switch (d) {//进行移动
						case 1:
							B[i-1][j-1]=B[i][j];//移动点
							B[i-1][j-1].setMove(false);//移动过该点了  本次循环忽视该点
							B[i][j]=new Block(data.LOGO_NULL);//将原先点设为null
							break;
						case 2:
							B[i-1][j]=B[i][j];
							B[i-1][j].setMove(false);
							B[i][j]=new Block(data.LOGO_NULL);
							break;
						case 3:
							B[i-1][j+1]=B[i][j];
							B[i-1][j+1].setMove(false);
							B[i][j]=new Block(data.LOGO_NULL);
							break;
						case 4:
							B[i][j-1]=B[i][j];
							B[i][j-1].setMove(false);
							B[i][j]=new Block(data.LOGO_NULL);
							break;
						case 5:
							break;
						case 6:
							B[i][j+1]=B[i][j];
							B[i][j+1].setMove(false);
							B[i][j]=new Block(data.LOGO_NULL);
							break;
						case 7:
							B[i+1][j-1]=B[i][j];
							B[i+1][j-1].setMove(false);
							B[i][j]=new Block(data.LOGO_NULL);
							break;
						case 8:
							B[i+1][j]=B[i][j];
							B[i+1][j].setMove(false);
							B[i][j]=new Block(data.LOGO_NULL);
							break;
						case 9:
							B[i+1][j+1]=B[i][j];
							B[i+1][j+1].setMove(false);
							B[i][j]=new Block(data.LOGO_NULL);
							break;
						default:
							break;
						}
					}
				}
			}
			for(int i=0;i<m;i++){//到达出口
				for(int j=0;j<m;j++){
					if(i==data.EXIT_X & j==data.EXIT_Y){
						if(B[i][j].logo==data.LOGO_PEOPLE){//如果行人到达出口
							B[i][j]=new Block(data.LOGO_EXIT);//设置一个新的出口
							peopleNumber--;//行人数-1
						}
					}
				}
			}
			 try {
	             Thread.sleep(100);
	         } catch (Exception e) {
	             e.printStackTrace();
	         }
			 DF.onColor(B);//重绘
			 h++;
			 System.out.println("After---"+h+"---times,surplus---"+peopleNumber+"---people");
		}
	}
}
