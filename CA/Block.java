package CA;

import java.util.HashMap;
import java.util.Map;

public class Block {
	int uid;
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
	
	int isChangePerView;
	
	double wightClock;//顺时针偏好收益
	double wightUnClock;//逆时针偏好收益
	
	boolean inWall;//行人是否在墙壁附近
	boolean isChangeWallInCome;//在刚进入墙壁附近时，已经判断过附近行人方向了
	boolean isChangePerForOther;//是否有其他人改变过方向偏好
	
	Map<Integer,Double> cMap=new HashMap<>();
	
	public Block(int logo) {
		
		this.logo=logo;
	}
	
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
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
	
	
	public double getIsChangeClock() {
		return isChangeClock;
	}
	public void setIsChangeClock(double isChangeClock) {
		this.isChangeClock = isChangeClock;
	}

	public double getWightClock() {
		return wightClock;
	}

	public void setWightClock(double wightClock) {
		this.wightClock = wightClock;
	}

	public double getWightUnClock() {
		return wightUnClock;
	}

	public void setWightUnClock(double wightUnClock) {
		this.wightUnClock = wightUnClock;
	}

	public boolean isInWall() {
		return inWall;
	}

	public void setInWall(boolean inWall) {
		this.inWall = inWall;
	}

	public Map<Integer, Double> getcMap() {
		return cMap;
	}

	public void setcMap(Map<Integer, Double> cMap) {
		this.cMap = cMap;
	}

	public boolean isChangeWallInCome() {
		return isChangeWallInCome;
	}

	public void setChangeWallInCome(boolean isChangeWallInCome) {
		this.isChangeWallInCome = isChangeWallInCome;
	}

	public boolean isChangePerForOther() {
		return isChangePerForOther;
	}

	public void setChangePerForOther(boolean isChangePerForOther) {
		this.isChangePerForOther = isChangePerForOther;
	}

	public int getIsChangePerView() {
		return isChangePerView;
	}

	public void setIsChangePerView(int isChangePerView) {
		this.isChangePerView = isChangePerView;
	}

	
}
