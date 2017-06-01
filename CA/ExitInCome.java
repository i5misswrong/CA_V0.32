package CA;

import java.util.ArrayList;

public class ExitInCome {
	Data data=new Data();
	
	public ArrayList<Direction> countExitInCome(int dx,int dy){
		ArrayList<Direction> exitInCome=new ArrayList<>();
		Direction D1=new Direction();
		D1.setDirection(1);
		D1.setInCome(1/Math.sqrt((data.EXIT_X-(dx-1))*(data.EXIT_X-(dx-1))+(data.EXIT_Y_HALF-(dy-1))*(data.EXIT_Y_HALF-(dy-1)))+0.0001*Math.random());
		exitInCome.add(D1);
		
		Direction D2=new Direction();
		D2.setDirection(2);
		D2.setInCome(1/Math.sqrt((data.EXIT_X-(dx-1))*(data.EXIT_X-(dx-1))+(data.EXIT_Y_HALF-(dy))*(data.EXIT_Y_HALF-(dy)))+0.0001*Math.random());
		exitInCome.add(D2);
		
		Direction D3=new Direction();
		D3.setDirection(3);
		D3.setInCome(1/Math.sqrt((data.EXIT_X-(dx-1))*(data.EXIT_X-(dx-1))+(data.EXIT_Y_HALF-(dy+1))*(data.EXIT_Y_HALF-(dy+1)))+0.0001*Math.random());
		exitInCome.add(D3);
		
		Direction D4=new Direction();
		D4.setDirection(4);
		D4.setInCome(1/Math.sqrt((data.EXIT_X-(dx))*(data.EXIT_X-(dx))+(data.EXIT_Y_HALF-(dy-1))*(data.EXIT_Y_HALF-(dy-1)))+0.0001*Math.random());
		exitInCome.add(D4);
		
		Direction D5=new Direction();
		D5.setDirection(5);
		D5.setInCome(1/Math.sqrt((data.EXIT_X-(dx))*(data.EXIT_X-(dx))+(data.EXIT_Y_HALF-(dy))*(data.EXIT_Y_HALF-(dy)))+0.0001*Math.random());
		exitInCome.add(D5);
		
		Direction D6=new Direction();
		D6.setDirection(6);
		D6.setInCome(1/Math.sqrt((data.EXIT_X-(dx))*(data.EXIT_X-(dx))+(data.EXIT_Y_HALF-(dy+1))*(data.EXIT_Y_HALF-(dy+1)))+0.0001*Math.random());
		exitInCome.add(D6);
		
		Direction D7=new Direction();
		D7.setDirection(7);
		D7.setInCome(1/Math.sqrt((data.EXIT_X-(dx+1))*(data.EXIT_X-(dx+1))+(data.EXIT_Y_HALF-(dy-1))*(data.EXIT_Y_HALF-(dy-1)))+0.0001*Math.random());
		exitInCome.add(D7);
		
		Direction D8=new Direction();
		D8.setDirection(8);
		D8.setInCome(1/Math.sqrt((data.EXIT_X-(dx+1))*(data.EXIT_X-(dx+1))+(data.EXIT_Y_HALF-(dy))*(data.EXIT_Y_HALF-(dy)))+0.0001*Math.random());
		exitInCome.add(D8);
		
		Direction D9=new Direction();
		D9.setDirection(9);
		D9.setInCome(1/Math.sqrt((data.EXIT_X-(dx+1))*(data.EXIT_X-(dx+1))+(data.EXIT_Y_HALF-(dy+1))*(data.EXIT_Y_HALF-(dy+1)))+0.0001*Math.random());
		exitInCome.add(D9);
		
		return exitInCome;
	}
}
