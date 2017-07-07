package CA;

public class Data {
	int M=50;
	
	double PEOPLE_DENSITY=0.3;
	int sh=19;
	int PEOPLE_NUMBER=(int) (M*M*PEOPLE_DENSITY);
	
	int EXIT_X=0;
	int EXIT_LONG=3;
	int EXIT_Y_HALF=25;
	int EXIT_Y_LEFT=EXIT_Y_HALF-1;
	int EXIT_Y_RIGHT=EXIT_Y_HALF+1;
	
	int VIEW_NUMBER=5;
	
	int VIEW_ANGLE=45;//记忆角
	int VIEW_RANGE=8;//视野半径
	
	int LOGO_PEOPLE=1;
	int LOGO_WALL=10;
	int LOGO_EXIT=100;
	int LOGO_VIEW=50;
	int LOGO_NULL=0;
}
