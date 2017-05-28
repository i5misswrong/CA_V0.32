package CA;

public class Block {
	int x;
	int y;
	int logo;
	int direction;
	boolean clock;//行人方向偏好  TRUE为偏好顺时针  false为偏好逆时针
	boolean isMove;//行人锁定开关 TRUE为可以移动 false为不可移动 在遍历矩阵
					//在遍历矩阵时 ij的行人移动到i+1 j+1 则遍历到i+1j+1时 该点的行人不会继续移动
	double realAngle;//行人与出口的角度
	boolean isSeeView;//行人是否看到过记忆点  false为没见过 TRUE为见过
	boolean isSeeExit;//行人是否看到过出口，false为没见过，TRUE为见过
	double isChangeClock;//行人是否已经改变过方向偏好 false为没改过 TRUE为改过
						//isChangeClock只对行人产生的方向偏好有关 与记忆点收益无关
	boolean isChangeClockView;
	public Block(int logo) {
		
		this.logo=logo;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getLogo() {
		return logo;
	}
	public void setLogo(int logo) {
		this.logo = logo;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public boolean isClock() {
		return clock;
	}
	public void setClock(boolean clock) {
		this.clock = clock;
	}
	public boolean isMove() {
		return isMove;
	}
	public void setMove(boolean isMove) {
		this.isMove = isMove;
	}
	public double getRealAngle() {
		return realAngle;
	}
	public void setRealAngle(double realAngle) {
		this.realAngle = realAngle;
	}
	public boolean isSeeView() {
		return isSeeView;
	}
	public void setSeeView(boolean isSeeView) {
		this.isSeeView = isSeeView;
	}
	public boolean isSeeExit() {
		return isSeeExit;
	}
	public void setSeeExit(boolean isSeeExit) {
		this.isSeeExit = isSeeExit;
	}
	
	public boolean isChangeClockView() {
		return isChangeClockView;
	}
	public void setChangeClockView(boolean isChangeClockView) {
		this.isChangeClockView = isChangeClockView;
	}
	public double getIsChangeClock() {
		return isChangeClock;
	}
	public void setIsChangeClock(double isChangeClock) {
		this.isChangeClock = isChangeClock;
	}
	
}
