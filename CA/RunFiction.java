package CA;
import jxl.*;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javazoom.jl.player.Player;

public class RunFiction {
	// 此范围测试方法
//	public static void main(String [] args){
//			Data data=new Data();
//			Rule rule=new Rule();
//			DrawFirst DF=new DrawFirst();
//			int m=data.M;
//			
//			double PEOPLE_DENSITY=data.PEOPLE_DENSITY;
//			
//			int peopleNumber=(int) (m*m*PEOPLE_DENSITY);
////			int peopleNumber=5;
//			int counutPeopleNumber=peopleNumber;
//			int viewNumber=data.VIEW_NUMBER;//记忆点总数
//			int h=0;//疏散步长计数器
//			ArrayList<Integer> count=new ArrayList<>();
//			ArrayList<Integer> times=new ArrayList<>();
//			Block B[][]=new Block[m][m];//所有方块矩阵
//			for(int i=0;i<m;i++){
//				for(int j=0;j<m;j++){
//					B[i][j]=new Block(data.LOGO_NULL);//初始化矩阵
//				}
//			}
//			for(int i=0;i<m;i++){
//				for(int j=0;j<m;j++){
//					B[0][j]=new Block(data.LOGO_WALL);//设置墙壁
//					B[m-1][j]=new Block(data.LOGO_WALL);
//					B[i][0]=new Block(data.LOGO_WALL);
//					B[i][m-1]=new Block(data.LOGO_WALL);
//				}
//			}
//			B[data.EXIT_X][data.EXIT_Y_LEFT]=new Block(data.LOGO_EXIT);//设置出口
//			B[data.EXIT_X][data.EXIT_Y_HALF]=new Block(data.LOGO_EXIT);//设置出口
//			B[data.EXIT_X][data.EXIT_Y_RIGHT]=new Block(data.LOGO_EXIT);//设置出口
//			//----------随机产生行人--------------------------
//			int t=0;//计数器
//			ArrayList<Block> BA=new ArrayList<Block>();
////			
//			while(t<peopleNumber){//如果计数器小于行人总数，则一直循环
//				int rx=(int)(Math.random()*(data.M-2))+2;//随机x y坐标
//				int ry=(int)(Math.random()*(data.M-2))+2;
//				Map<Integer,Double> cMap=new HashMap<>();
//				cMap.put(0, 0.0);
//				cMap.put(1, 0.0);
//				if(B[rx][ry].logo==0){//如果这一点为空
//					B[rx][ry]=new Block(data.LOGO_PEOPLE);//添加行人
//					double c=Math.random()-0.5;
//					if(c>0){//行人遇到墙时 优先逆时针还时顺时针
//						B[rx][ry].setClock(false);// false 是逆时针
//					}
//					else{
//						B[rx][ry].setClock(true);//TRUE 是顺时针
//					}
//					B[rx][ry].setX(rx);
//					B[rx][ry].setY(ry);
//					B[rx][ry].setRealAngle(999);// define angle=999 use type-token
//					B[rx][ry].setUid(t);//set userId
//					BA.add(B[rx][ry]);
//					B[rx][ry].setcMap(cMap);
//					t++;//计数器+1
//				}
//			}
//			
//			//---------产生指定行人-----------------------------------
////			B[10][40]=new Block(data.LOGO_PEOPLE);
////			B[10][40].setClock(true);
////			B[6][18]=new Block(data.LOGO_PEOPLE);
//			//------------------------------------------------------
//			
//			//----------产生指定记忆点-------------------------------------
//////			case 1
////			B[25][25]=new Block(data.LOGO_VIEW);
////			B[12][12]=new Block(data.LOGO_VIEW);
////			B[12][38]=new Block(data.LOGO_VIEW);
////			B[38][12]=new Block(data.LOGO_VIEW);
////			B[38][38]=new Block(data.LOGO_VIEW);
////			case 2
////			B[10][25]=new Block(data.LOGO_VIEW);
////			B[25][10]=new Block(data.LOGO_VIEW);
////			B[25][25]=new Block(data.LOGO_VIEW);
////			B[25][40]=new Block(data.LOGO_VIEW);
////			B[40][25]=new Block(data.LOGO_VIEW);
//////			case 3
////			B[0][0]=new Block(data.LOGO_VIEW);
////			B[0][49]=new Block(data.LOGO_VIEW);
////			B[25][25]=new Block(data.LOGO_VIEW);
////			B[49][0]=new Block(data.LOGO_VIEW);
////			B[49][49]=new Block(data.LOGO_VIEW);
//////			case 4
////			B[25][0]=new Block(data.LOGO_VIEW);
////			B[25][49]=new Block(data.LOGO_VIEW);
////			B[30][15]=new Block(data.LOGO_VIEW);
////			B[30][35]=new Block(data.LOGO_VIEW);
////			B[49][25]=new Block(data.LOGO_VIEW);
//////			
////			//case 5
////			B[12][2]=new Block(data.LOGO_VIEW);
////			B[12][48]=new Block(data.LOGO_VIEW);
////			B[25][25]=new Block(data.LOGO_VIEW);
////			B[38][2]=new Block(data.LOGO_VIEW);
////			B[38][48]=new Block(data.LOGO_VIEW);
//			
////			case 6
////			B[10][10]=new Block(data.LOGO_VIEW);
////			B[10][25]=new Block(data.LOGO_VIEW);
////			B[10][40]=new Block(data.LOGO_VIEW);
////			B[25][10]=new Block(data.LOGO_VIEW);
////			B[25][40]=new Block(data.LOGO_VIEW);
////			case 7
////			B[25][5]=new Block(data.LOGO_VIEW);
////			B[25][45]=new Block(data.LOGO_VIEW);
////			B[45][5]=new Block(data.LOGO_VIEW);
////			B[45][25]=new Block(data.LOGO_VIEW);
////			B[45][45]=new Block(data.LOGO_VIEW);
////			case 8
////			B[0][0]=new Block(data.LOGO_VIEW);
////			B[0][49]=new Block(data.LOGO_VIEW);
////			B[10][15]=new Block(data.LOGO_VIEW);
////			B[10][35]=new Block(data.LOGO_VIEW);
////			B[20][25]=new Block(data.LOGO_VIEW);
////			case 9
////			B[15][0]=new Block(data.LOGO_VIEW);
////			B[15][49]=new Block(data.LOGO_VIEW);
////			B[35][0]=new Block(data.LOGO_VIEW);
////			B[35][49]=new Block(data.LOGO_VIEW);
////			B[15][25]=new Block(data.LOGO_VIEW);
//			//----------产生随机记忆点-----------------------------------
////			int k=0;//同上
////			while(k<viewNumber){
////				boolean flag=true;
////				int rx=(int)(Math.random()*(data.M-2))+2;//随机x y坐标
////				int ry=(int)(Math.random()*(data.M-2))+2;
////				if(B[rx][ry].logo==0 ){
////					B[rx][ry]=new Block(data.LOGO_VIEW);
////					k++;
////				}
////			}
//			//------------------------------------------------------
//			
//			 DF.onColor(B);//第一次绘图
//			 try {
//	             Thread.sleep(1000);//休眠5s 便于观察
//	         } catch (Exception e) {
//	             e.printStackTrace();
//	         }
//			 boolean finialFlag=false;
//			 while(!finialFlag){
//				 for(int i=0;i<BA.size();i++){
//					 BA.get(i).setMove(true);
//				 }
//				 for(int z=0;z<BA.size();z++){
//					 
//					 int i=BA.get(z).getX();
//					 int j=BA.get(z).getY();
//					 if(B[i][j].getLogo()==data.LOGO_PEOPLE){
//						 int d=rule.getCorrectDirection(B, i, j);//获取方向
//						 B[i][j].setDirection(d);//设置方向
//						 rule.peoMove(B, i, j, d);//移动
//						 rule.peoMoveArray(BA, z, i, j, d);
//					 }
//					 
//				 }
//				 for(int i=0;i<m;i++){//到达出口
//						for(int j=0;j<m;j++){
//							if(i==data.EXIT_X & (j==data.EXIT_Y_HALF | j==data.EXIT_Y_LEFT | j==data.EXIT_Y_RIGHT)){
//								if(B[i][j].logo==data.LOGO_PEOPLE){//如果行人到达出口
//									B[i][j]=new Block(data.LOGO_EXIT);//设置一个新的出口
//									counutPeopleNumber--;//行人数-1
//								}
//							}
//							
//						}
//				 }
//				 for(int i=0;i<BA.size();i++){
//					 if(BA.get(i).getX()==data.EXIT_X & (BA.get(i).getY()==data.EXIT_Y_HALF | BA.get(i).getY()==data.EXIT_Y_LEFT | BA.get(i).getY()==data.EXIT_Y_RIGHT)){
//						 BA.remove(i);
//						 --i;
//					 }
//				 }
//				 try {
//		             Thread.sleep(100);
//		         } catch (Exception e) {
//		             e.printStackTrace();
//		         }
//				 DF.onColor(B);//重绘
//				 h++;
//				 if( h>2000 | counutPeopleNumber==0){
//					 finialFlag=true;
//				 }
////				 System.out.println("After---"+h+"---times,surplus---"+counutPeopleNumber+"---people");
//			 }
//	}
	
//-------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------
	public static void main(String [] args){
		
		ArrayList<ExcleSheet> excleSheetList=new ArrayList<>();
//		ExcleSheet e1=new ExcleSheet();
//		e1.setSHEET(1);
//		e1.setDENSITY(0.01);
//		excleSheetList.add(e1);
		for(int i=0;i<16;i++){
			ExcleSheet excleSheet=new ExcleSheet();
			excleSheet.setSHEET(i);
			excleSheet.setDENSITY(0.05*(i+1));
			excleSheetList.add(excleSheet);
		}
		for(int excleS=0;excleS<excleSheetList.size();excleS++){
			for(int excleJ=0;excleJ<5;excleJ++){
				double PEOPLE_DENSITY=excleSheetList.get(excleS).getDENSITY();
				int sh=excleSheetList.get(excleS).getSHEET();
				
				
				Data data=new Data();
				Rule rule=new Rule();
//				DrawFirst DF=new DrawFirst();
				int m=data.M;
				
				int peopleNumber=(int) (m*m*PEOPLE_DENSITY);
			//		int peopleNumber=5;
				int counutPeopleNumber=peopleNumber;
				int viewNumber=data.VIEW_NUMBER;//记忆点总数
				int h=0;//疏散步长计数器
				ArrayList<Integer> count=new ArrayList<>();
				ArrayList<Integer> times=new ArrayList<>();
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
				B[data.EXIT_X][data.EXIT_Y_LEFT]=new Block(data.LOGO_EXIT);//设置出口
				B[data.EXIT_X][data.EXIT_Y_HALF]=new Block(data.LOGO_EXIT);//设置出口
				B[data.EXIT_X][data.EXIT_Y_RIGHT]=new Block(data.LOGO_EXIT);//设置出口
				//----------随机产生行人--------------------------
				int t=0;//计数器
				ArrayList<Block> BA=new ArrayList<Block>();
			//		
				while(t<peopleNumber){//如果计数器小于行人总数，则一直循环
					int rx=(int)(Math.random()*(data.M-2))+2;//随机x y坐标
					int ry=(int)(Math.random()*(data.M-2))+2;
					Map<Integer,Double> cMap=new HashMap<>();
					cMap.put(0, 0.0);
					cMap.put(1, 0.0);
					if(B[rx][ry].logo==0){//如果这一点为空
						B[rx][ry]=new Block(data.LOGO_PEOPLE);//添加行人
						double c=Math.random()-0.5;
						if(c>0){//行人遇到墙时 优先逆时针还时顺时针
							B[rx][ry].setClock(false);// false 是逆时针
						}
						else{
							B[rx][ry].setClock(true);//TRUE 是顺时针
						}
						B[rx][ry].setX(rx);
						B[rx][ry].setY(ry);
						B[rx][ry].setRealAngle(999);// define angle=999 use type-token
						B[rx][ry].setUid(t);//set userId
						BA.add(B[rx][ry]);
						B[rx][ry].setcMap(cMap);
						t++;//计数器+1
					}
				}
				
				//---------产生指定行人-----------------------------------
			//		B[10][40]=new Block(data.LOGO_PEOPLE);
			//		B[10][40].setClock(true);
			//		B[6][18]=new Block(data.LOGO_PEOPLE);
				//------------------------------------------------------
				
				//----------产生指定记忆点-------------------------------------
//				case 1  NEW 
//				B[25][25]=new Block(data.LOGO_VIEW);
//				B[12][12]=new Block(data.LOGO_VIEW);
//				B[12][38]=new Block(data.LOGO_VIEW);
//				B[38][12]=new Block(data.LOGO_VIEW);
//				B[38][38]=new Block(data.LOGO_VIEW);
////			case 2   NEW
//				B[0][0]=new Block(data.LOGO_VIEW);
//				B[0][49]=new Block(data.LOGO_VIEW);
//				B[25][25]=new Block(data.LOGO_VIEW);
//				B[49][0]=new Block(data.LOGO_VIEW);
//				B[49][49]=new Block(data.LOGO_VIEW);
//		////	case 3  NEW
//				B[10][10]=new Block(data.LOGO_VIEW);
//				B[10][25]=new Block(data.LOGO_VIEW);
//				B[10][40]=new Block(data.LOGO_VIEW);
//				B[25][10]=new Block(data.LOGO_VIEW);
//				B[25][40]=new Block(data.LOGO_VIEW);
	//			case 4  NEW
//				B[25][5]=new Block(data.LOGO_VIEW);
//				B[25][45]=new Block(data.LOGO_VIEW);
//				B[45][5]=new Block(data.LOGO_VIEW);
//				B[45][25]=new Block(data.LOGO_VIEW);
//				B[45][45]=new Block(data.LOGO_VIEW);
				
											////		case 1
								//					B[25][25]=new Block(data.LOGO_VIEW);
								//					B[12][12]=new Block(data.LOGO_VIEW);
								//					B[12][38]=new Block(data.LOGO_VIEW);
								//					B[38][12]=new Block(data.LOGO_VIEW);
								//					B[38][38]=new Block(data.LOGO_VIEW);
											//		case 2
								//					B[10][25]=new Block(data.LOGO_VIEW);
								//					B[25][10]=new Block(data.LOGO_VIEW);
								//					B[25][25]=new Block(data.LOGO_VIEW);
								//					B[25][40]=new Block(data.LOGO_VIEW);
								//					B[40][25]=new Block(data.LOGO_VIEW);
											////		case 3
								//					B[0][0]=new Block(data.LOGO_VIEW);
								//					B[0][49]=new Block(data.LOGO_VIEW);
								//					B[25][25]=new Block(data.LOGO_VIEW);
								//					B[49][0]=new Block(data.LOGO_VIEW);
								//					B[49][49]=new Block(data.LOGO_VIEW);
											////		case 4
								//					B[25][0]=new Block(data.LOGO_VIEW);
								//					B[25][49]=new Block(data.LOGO_VIEW);
								//					B[30][15]=new Block(data.LOGO_VIEW);
								//					B[30][35]=new Block(data.LOGO_VIEW);
								//					B[49][25]=new Block(data.LOGO_VIEW);
											////		
											//		//case 5
									//				B[12][2]=new Block(data.LOGO_VIEW);
									//				B[12][48]=new Block(data.LOGO_VIEW);
									//				B[25][25]=new Block(data.LOGO_VIEW);
									//				B[38][2]=new Block(data.LOGO_VIEW);
									//				B[38][48]=new Block(data.LOGO_VIEW);
								//					case 6
								//					B[10][10]=new Block(data.LOGO_VIEW);
								//					B[10][25]=new Block(data.LOGO_VIEW);
								//					B[10][40]=new Block(data.LOGO_VIEW);
								//					B[25][10]=new Block(data.LOGO_VIEW);
								//					B[25][40]=new Block(data.LOGO_VIEW);
								//					case 7
								//					B[25][5]=new Block(data.LOGO_VIEW);
								//					B[25][45]=new Block(data.LOGO_VIEW);
								//					B[45][5]=new Block(data.LOGO_VIEW);
								//					B[45][25]=new Block(data.LOGO_VIEW);
								//					B[45][45]=new Block(data.LOGO_VIEW);
								////					case 8
								//					B[0][0]=new Block(data.LOGO_VIEW);
								//					B[0][49]=new Block(data.LOGO_VIEW);
								//					B[10][15]=new Block(data.LOGO_VIEW);
								//					B[10][35]=new Block(data.LOGO_VIEW);
								//					B[20][25]=new Block(data.LOGO_VIEW);
								////					case 9
								//					B[15][0]=new Block(data.LOGO_VIEW);
								//					B[15][49]=new Block(data.LOGO_VIEW);
								//					B[35][0]=new Block(data.LOGO_VIEW);
								//					B[35][49]=new Block(data.LOGO_VIEW);
								//					B[15][25]=new Block(data.LOGO_VIEW);
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
				
	//			 DF.onColor(B);//第一次绘图
	//			 try {
	//		         Thread.sleep(1000);//休眠5s 便于观察
	//		     } catch (Exception e) {
	//		         e.printStackTrace();
	//		     }
				 boolean finialFlag=false;
				 while(!finialFlag){
					 for(int i=0;i<BA.size();i++){
						 BA.get(i).setMove(true);
					 }
					 for(int z=0;z<BA.size();z++){
						 
						 int i=BA.get(z).getX();
						 int j=BA.get(z).getY();
						 if(B[i][j].getLogo()==data.LOGO_PEOPLE){
							 int d=rule.getCorrectDirection(B, i, j);//获取方向
							 B[i][j].setDirection(d);//设置方向
							 rule.peoMove(B, i, j, d);//移动
							 rule.peoMoveArray(BA, z, i, j, d);
						 }
						 
					 }
					 for(int i=0;i<m;i++){//到达出口
							for(int j=0;j<m;j++){
								if(i==data.EXIT_X & (j==data.EXIT_Y_HALF | j==data.EXIT_Y_LEFT | j==data.EXIT_Y_RIGHT)){
									if(B[i][j].logo==data.LOGO_PEOPLE){//如果行人到达出口
										B[i][j]=new Block(data.LOGO_EXIT);//设置一个新的出口
										counutPeopleNumber--;//行人数-1
									}
								}
								
							}
					 }
					 for(int i=0;i<BA.size();i++){
						 if(BA.get(i).getX()==data.EXIT_X & (BA.get(i).getY()==data.EXIT_Y_HALF | BA.get(i).getY()==data.EXIT_Y_LEFT | BA.get(i).getY()==data.EXIT_Y_RIGHT)){
							 BA.remove(i);
							 --i;
						 }
					 }
	//				 try {
	//		             Thread.sleep(100);
	//		         } catch (Exception e) {
	//		             e.printStackTrace();
	//		         }
	//				 DF.onColor(B);//重绘
					 h++;
					 if( h>2000 ){
						 finialFlag=true;
					 }
					 count.add(counutPeopleNumber);
					 times.add(h);
			//			 System.out.println("After---"+h+"---times,surplus---"+counutPeopleNumber+"---people");
				 }
				 int u=1;
				int s=sh;//修改工作簿
				try { 
					boolean flag=true;
					Workbook book=Workbook.getWorkbook(new File("modle.xls"));
					Sheet sheet1=book.getSheet(s);
					
					WritableWorkbook wbook=Workbook.createWorkbook(new File("modle.xls"),book);
					WritableSheet sh1=wbook.getSheet(s);
					for(int i=0;i<times.size();i++){
						while(flag){
							Cell c=sh1.getCell(u,1);
							if(c.getContents().equals("")){
								
								flag=false;
							}
							else{
								u++;
							}
						}
						if(!flag){
							sh1.addCell(new Label(u,i+1,Integer.toString(count.get(i))));
							sh1.addCell(new Label(0,i+1,Integer.toString(times.get(i))));
						}
					}
					wbook.write();
					wbook.close();
				 }
				 catch(Exception e) {
					 System.out.println("has error");
				 } 
				System.out.println("have success");
				System.out.println("第"+u+"次记录数据,当前是"+PEOPLE_DENSITY+",在第"+s+"张工作簿上");
			}
	}
		
		
		
		Player player;
	     File music=new File("a.mp3");
	     try {
	    	  
	          for(int i=0;i<3;i++){
	        	  BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
		          player = new Player(buffer);
	        	  player.play();
	          }
	          
	     } catch (Exception e) {
			// TODO: handle exception
	     }
}
//-------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------
//	此方法为录入数据方法
//	public static void main(String [] args){
//		
//		ArrayList<ExcleSheet> excleSheetList=new ArrayList<>();
//		ExcleSheet e1=new ExcleSheet();
//		e1.setSHEET(1);
//		e1.setDENSITY(0.01);
//		excleSheetList.add(e1);
//		for(int i=2;i<19;i++){
//			ExcleSheet excleSheet=new ExcleSheet();
//			excleSheet.setSHEET(i);
//			excleSheet.setDENSITY(0.05*(i-1));
//			excleSheetList.add(excleSheet);
//		}
//		for(int excleS=0;excleS<excleSheetList.size();excleS++){
//			for(int excleJ=0;excleJ<5;excleJ++){
//			Data data=new Data();
//			Rule rule=new Rule();
//			int m=data.M;
//			
//			double PEOPLE_DENSITY=excleSheetList.get(excleS).getDENSITY();
//			int sh=excleSheetList.get(excleS).getSHEET();
//			int peopleNumber=(int) (m*m*PEOPLE_DENSITY);
//			int counutPeopleNumber=peopleNumber;
//			
//			int viewNumber=data.VIEW_NUMBER;//记忆点总数
//			int h=0;//疏散步长计数器
//			ArrayList<Integer> count=new ArrayList<>();
//			ArrayList<Integer> times=new ArrayList<>();
//			Block B[][]=new Block[m][m];//所有方块矩阵
//			for(int i=0;i<m;i++){
//				for(int j=0;j<m;j++){
//					B[i][j]=new Block(data.LOGO_NULL);//初始化矩阵
//				}
//			}
//			for(int i=0;i<m;i++){
//				for(int j=0;j<m;j++){
//					B[0][j]=new Block(data.LOGO_WALL);//设置墙壁
//					B[m-1][j]=new Block(data.LOGO_WALL);
//					B[i][0]=new Block(data.LOGO_WALL);
//					B[i][m-1]=new Block(data.LOGO_WALL);
//				}
//			}
//			B[data.EXIT_X][data.EXIT_Y_LEFT]=new Block(data.LOGO_EXIT);//设置出口
//			B[data.EXIT_X][data.EXIT_Y_HALF]=new Block(data.LOGO_EXIT);//设置出口
//			B[data.EXIT_X][data.EXIT_Y_RIGHT]=new Block(data.LOGO_EXIT);//设置出口
//			//----------随机产生行人--------------------------
//			int t=0;//计数器
//			while(t<peopleNumber){//如果计数器小于行人总数，则一直循环
//				int rx=(int)(Math.random()*(data.M-2))+2;//随机x y坐标
//				int ry=(int)(Math.random()*(data.M-2))+2;
//				if(B[rx][ry].logo==0){//如果这一点为空
//					B[rx][ry]=new Block(data.LOGO_PEOPLE);//添加行人
//					double c=Math.random()-0.5;
//					if(c>0){//行人遇到墙时 优先逆时针还时顺时针
//						B[rx][ry].setClock(false);// false 是逆时针
//					}
//					else{
//						B[rx][ry].setClock(true);//TRUE 是顺时针
//					}
//					B[rx][ry].setRealAngle(999);
//					t++;//计数器+1
//				}
//			}
//			
//			//---------产生指定行人-----------------------------------
////			B[10][40]=new Block(data.LOGO_PEOPLE);
////			B[10][40].setClock(true);
////			B[6][18]=new Block(data.LOGO_PEOPLE);
//			//------------------------------------------------------
//			
//			//----------产生指定记忆点-------------------------------------
//////			case 1
////			B[25][25]=new Block(data.LOGO_VIEW);
////			B[12][12]=new Block(data.LOGO_VIEW);
////			B[12][38]=new Block(data.LOGO_VIEW);
////			B[38][12]=new Block(data.LOGO_VIEW);
////			B[38][38]=new Block(data.LOGO_VIEW);
////			case 2
////			B[10][25]=new Block(data.LOGO_VIEW);
////			B[25][10]=new Block(data.LOGO_VIEW);
////			B[25][25]=new Block(data.LOGO_VIEW);
////			B[25][40]=new Block(data.LOGO_VIEW);
////			B[40][25]=new Block(data.LOGO_VIEW);
//////			case 3
////			B[0][0]=new Block(data.LOGO_VIEW);
////			B[0][49]=new Block(data.LOGO_VIEW);
////			B[25][25]=new Block(data.LOGO_VIEW);
////			B[49][0]=new Block(data.LOGO_VIEW);
////			B[49][49]=new Block(data.LOGO_VIEW);
//////			case 4
////			B[25][0]=new Block(data.LOGO_VIEW);
////			B[25][49]=new Block(data.LOGO_VIEW);
////			B[30][15]=new Block(data.LOGO_VIEW);
////			B[30][35]=new Block(data.LOGO_VIEW);
////			B[49][25]=new Block(data.LOGO_VIEW);
//////			
////			//case 5
//			B[12][2]=new Block(data.LOGO_VIEW);
//			B[12][48]=new Block(data.LOGO_VIEW);
//			B[25][25]=new Block(data.LOGO_VIEW);
//			B[38][2]=new Block(data.LOGO_VIEW);
//			B[28][38]=new Block(data.LOGO_VIEW);
//			//----------产生随机记忆点-----------------------------------
////			int k=0;//同上
////			while(k<viewNumber){
////				boolean flag=true;
////				int rx=(int)(Math.random()*(data.M-2))+2;//随机x y坐标
////				int ry=(int)(Math.random()*(data.M-2))+2;
////				if(B[rx][ry].logo==0 ){
////					B[rx][ry]=new Block(data.LOGO_VIEW);
////					k++;
////				}
////			}
//			//------------------------------------------------------
//			
//			 boolean finialFlag=false;
//			while(!finialFlag){//主程序循环体
//				
//				for(int i=1;i<m-1;i++){
//					for(int j=1;j<m-1;j++){
//						B[i][j].setMove(true);//将所有行人的可移动状态设为TRUE 可以移动
//					}
//				}
//				for(int i=1;i<m-1;i++){//跳过边界遍历
//					for(int j=1;j<m-1;j++){
//						if(B[i][j].logo==1 & B[i][j].isMove){//如果该点是行人
//							int d=rule.getCorrectDirection(B, i, j);//获取方向
//							B[i][j].setDirection(d);
//							switch (d) {//进行移动
//							case 1:
//								B[i-1][j-1]=B[i][j];//移动点
//								B[i-1][j-1].setMove(false);//移动过该点了  本次循环忽视该点
//								B[i][j]=new Block(data.LOGO_NULL);//将原先点设为null
//								break;
//							case 2:
//								B[i-1][j]=B[i][j];
//								B[i-1][j].setMove(false);
//								B[i][j]=new Block(data.LOGO_NULL);
//								break;
//							case 3:
//								B[i-1][j+1]=B[i][j];
//								B[i-1][j+1].setMove(false);
//								B[i][j]=new Block(data.LOGO_NULL);
//								break;
//							case 4:
//								B[i][j-1]=B[i][j];
//								B[i][j-1].setMove(false);
//								B[i][j]=new Block(data.LOGO_NULL);
//								break;
//							case 5:
//								break;
//							case 6:
//								B[i][j+1]=B[i][j];
//								B[i][j+1].setMove(false);
//								B[i][j]=new Block(data.LOGO_NULL);
//								break;
//							case 7:
//								B[i+1][j-1]=B[i][j];
//								B[i+1][j-1].setMove(false);
//								B[i][j]=new Block(data.LOGO_NULL);
//								break;
//							case 8:
//								B[i+1][j]=B[i][j];
//								B[i+1][j].setMove(false);
//								B[i][j]=new Block(data.LOGO_NULL);
//								break;
//							case 9:
//								B[i+1][j+1]=B[i][j];
//								B[i+1][j+1].setMove(false);
//								B[i][j]=new Block(data.LOGO_NULL);
//								break;
//							default:
//								break;
//							}
//						}
//					}
//				}
//				for(int i=0;i<m;i++){//到达出口
//					for(int j=0;j<m;j++){
//						if(i==data.EXIT_X & (j==data.EXIT_Y_HALF | j==data.EXIT_Y_LEFT | j==data.EXIT_Y_RIGHT)){
//							if(B[i][j].logo==data.LOGO_PEOPLE){//如果行人到达出口
//								B[i][j]=new Block(data.LOGO_EXIT);//设置一个新的出口
//								counutPeopleNumber--;//行人数-1
//							}
//						}
//						
//					}
//				}
//				 h++;
//				 if( h>2000){
//					 finialFlag=true;
//				 }
//				 count.add(counutPeopleNumber);
//				 times.add(h);
//			}
//			int u=1;
//			int s=sh;//修改工作簿
//			try { 
//				boolean flag=true;
//				Workbook book=Workbook.getWorkbook(new File("modle.xls"));
//				Sheet sheet1=book.getSheet(s);
//				
//				WritableWorkbook wbook=Workbook.createWorkbook(new File("modle.xls"),book);
//				WritableSheet sh1=wbook.getSheet(s);
//				for(int i=0;i<times.size();i++){
//					while(flag){
//						Cell c=sh1.getCell(u,1);
//						if(c.getContents().equals("")){
//							
//							flag=false;
//						}
//						else{
//							u++;
//						}
//					}
//					if(!flag){
//						sh1.addCell(new Label(u,i+1,Integer.toString(count.get(i))));
//						sh1.addCell(new Label(0,i+1,Integer.toString(times.get(i))));
//					}
//				}
//				wbook.write();
//				wbook.close();
//			 }
//			 catch(Exception e) {
//				 System.out.println("has error");
//			 } 
//			System.out.println("have success");
//			System.out.println("第"+u+"次记录数据,当前是"+PEOPLE_DENSITY+",在第"+s+"张工作簿上");
//		}
//		}
//	}
//-------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------
	
	
}
