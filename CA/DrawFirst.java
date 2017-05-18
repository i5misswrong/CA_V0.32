package CA;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawFirst {
	Data data=new Data();
	int m=data.M;
	JFrame jf;
	JPanel[][] jp;
	
	public DrawFirst(){
		jf=new JFrame("CA");
		jp=new JPanel[m][m];
		jf.setLayout(new GridLayout(m, m,5,5));
		for(int i=0;i<m;i++){
			for(int j=0;j<m;j++){
				jp[i][j]=new JPanel();
				jp[i][j].setBackground(Color.white);
				jf.add(jp[i][j]);
			}
		}
		jf.setTitle("CA_V0.32");
		jf.setVisible(true);
		jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void onColor(Block B[][]){
		for(int i=0;i<m;i++){
			for(int j=0;j<m;j++){
				int logo=B[i][j].logo;
				switch (logo) {
				case 1://people
					jp[i][j].setBackground(Color.blue);
					break;
				case 0:// null
					jp[i][j].setBackground(Color.white);
					break;
				case 10://wall
					jp[i][j].setBackground(Color.PINK);
					break;
				case 100://exit
					jp[i][j].setBackground(Color.DARK_GRAY);
					break;
				case 50://view point
					jp[i][j].setBackground(Color.GREEN);
					break;
				default:
					jp[i][j].setBackground(Color.BLACK);
					break;
				}
			}
		}
	}
}
