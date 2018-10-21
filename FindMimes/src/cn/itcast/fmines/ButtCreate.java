package cn.itcast.fmines;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.midi.ShortMessage;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.RepaintManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.SliderUI;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

public class ButtCreate extends JFrame implements ActionListener,Runnable,MouseListener{
	
	private final int loEMPTY         = 0;    
    private final int loMINE          = 1;    
    private final int loCHECKED       = 2;
    
	private static int JBSIZE=20;
	private static int FMSIZE=30;
	private int lastMines=30;
	
	private JLabel showTime=new JLabel();
	private int LOMINE_SIZE=10;
	
	static Thread td=new Thread();
	public static int t;
	public static final int LOBUTTON_BOEDER=30;
	private final static int loSTART_X       =10;     
    private final static int loSTART_Y       = 40; 
    private boolean flag;    
	private int checkFlag=0;
    
	
	private int[][] background=new int[JBSIZE][JBSIZE];
	private JButton[][] jb;
	private JPanel panel;
	private JMenuBar meaBar;
	private JLabel jl;
	
	public static int Continue=0;
	
	public static int res;
	ArrayList<Point> minesP=new ArrayList<Point>();
	public ButtCreate() {
		
		
		
	}
	public void Inbackground(){
		for(int rows=0;rows<background.length;rows++){
			for(int cols=0;cols<background[rows].length;cols++){
				background[rows][cols]=loEMPTY;
			}
		}
	}
	public void InMines(){
		int f=FMSIZE;
		int g=0;
		Random random=new Random();	
			for(int i=f;i>0;){
				int x=random.nextInt(JBSIZE);
				int y=random.nextInt(JBSIZE);
				//System.out.println(x+","+y);
				if(background[x][y]==loEMPTY){
					//minesP.add(new Point(x,y));
					background[x][y]=loMINE;
					i--;
					g++;
				}
			}
			System.out.println(g);
	}
	//初始化地图
	public void inMP(){
		Continue=0;
		flag = false;  
		jb=new JButton[JBSIZE][JBSIZE];
		panel=new JPanel();
		
		jl=new JLabel();
		this.setResizable(false);
		setLayout(new BorderLayout());		
		JMenu fileJMenu=new JMenu("游戏开始");
		fileJMenu.add(new AbstractAction("开始") {
			
			public void actionPerformed(ActionEvent e) {
				
				panel.repaint();
				//JOptionPane.showConfirmDialog(null,"是否执行");
				t=0;
				
				//Continue=2;
			}
		});
		
		//beItem.setAccelerator(KeyStroke.getKeyStroke("ctrl 1"));	
		fileJMenu.add(new AbstractAction("exit") {		
			public void actionPerformed(ActionEvent e) {
				System.exit(0);			
			}
		});
		JMenu levelJMenu = JMeunLevel();
		
		JMenu login=new JMenu("登陆注册");
		login.add(new AbstractAction("登陆") {
			
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		login.add(new AbstractAction("注册") {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		login.add(new AbstractAction("排名") {
			
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		meaBar=new JMenuBar();
		meaBar.add(fileJMenu);
		meaBar.add(levelJMenu);
		meaBar.add(login);
		meaBar.setBounds(0, 0,LOBUTTON_BOEDER*JBSIZE+2*loSTART_X, 20);
		this.add(meaBar);
		
		jl.setBounds(0,22,100,18);
		jl.setText("雷数："+FMSIZE);
		this.add(jl);
		
		showTime.setText("已用时间：0秒");
		showTime.setBounds(LOBUTTON_BOEDER*JBSIZE+loSTART_X-100,22,100,18);
		this.add(showTime);	
		InButton();
		//this.setResizable(false);
		add(panel, BorderLayout.CENTER);
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public JMenu JMeunLevel() {
		JMenu levelJMenu=new JMenu("困难等级");
		levelJMenu.add(new AbstractAction("初级") {			
			public void actionPerformed(ActionEvent e) {
				
				FMSIZE=30;
				jl.setBounds(0,22,100,18);
				jl.setText("雷数："+FMSIZE);
				removeButton();
				Inbackground();
				InMines();
				InButton();
				add(panel, BorderLayout.CENTER);
				panel.repaint();
				//td.start();
				t=0;
				//Continue=true;
			}
		});
		levelJMenu.add(new AbstractAction("中级") {			
			public void actionPerformed(ActionEvent e) {
				//JBSIZE=30;
				FMSIZE=40;
				//panel.setSize(LOBUTTON_BOEDER*JBSIZE+2*loSTART_X,LOBUTTON_BOEDER*JBSIZE+2*loSTART_Y);
				jl.setBounds(0,22,100,18);
				jl.setText("雷数："+FMSIZE);
				removeButton();
				Inbackground();
				InMines();
				InButton();
				add(panel, BorderLayout.CENTER);
				panel.repaint();
				t=0;
				//System.out.println(FMSIZE);
				//panel.repaint();
				//Continue=true;
			}
		});
		levelJMenu.add(new AbstractAction("高级") {		
			public void actionPerformed(ActionEvent e) {
				
				FMSIZE=50;
				jl.setBounds(0,22,100,18);
				jl.setText("雷数："+FMSIZE);
				removeButton();
				Inbackground();
				InMines();
				InButton();
				add(panel, BorderLayout.CENTER);
				panel.repaint();
				t=0;
				
				//Continue=true;
			}
		});
		return levelJMenu;
	}
	public void intBound(JMenu fileJMenu, JMenu levelJMenu) {
	}
	public void InButton() {
		for(int rows=0;rows<JBSIZE;rows++){
			for(int cols=0;cols<JBSIZE;cols++){
				jb[rows][cols]=new JButton();			
				jb[rows][cols].addActionListener(this);
				jb[rows][cols].addMouseListener(this);
				
				jb[rows][cols].setName(rows+"_"+cols);
				
				jb[rows][cols].setBounds(rows*LOBUTTON_BOEDER+loSTART_X,cols*LOBUTTON_BOEDER+loSTART_Y,LOBUTTON_BOEDER,LOBUTTON_BOEDER);
				
				this.add(jb[rows][cols]);
				
			}
		}
	}	
	public void removeButton() {
		for(int rows=0;rows<JBSIZE;rows++){
			for(int cols=0;cols<JBSIZE;cols++){
				this.remove(jb[rows][cols]);
			}
		}
	}	
public void flash(){
		ButtCreate buttCreate=new ButtCreate();
		buttCreate.setSize(LOBUTTON_BOEDER*JBSIZE+2*loSTART_X,LOBUTTON_BOEDER*JBSIZE+2*loSTART_Y);
		buttCreate.Inbackground();
		buttCreate.InMines();
		buttCreate.inMP();
		buttCreate.setVisible(Boolean.TRUE);
		buttCreate.run();
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(Continue==1||Continue==2){
				//System.out.println("2");
				break;
			}
		}
		if(res==JOptionPane.YES_OPTION){
			buttCreate.dispose();
			//buttCreate.setVisible(Boolean.FALSE);
			
		}else if(res==JOptionPane.NO_OPTION || res==JOptionPane.CANCEL_OPTION){
			System.exit(0);
			Continue=1;
		}
}

//主函数
public static void main(String[] args) {
	ButtCreate buttCreate=new ButtCreate();
	do{
		Continue=0;
		buttCreate.flash();
		System.out.println(JBSIZE+","+FMSIZE);
		/*try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//buttCreate.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}while(Continue==2);
}


public void showMines(){
	for(int rows=0;rows<background.length;rows++){
		for(int cols=0;cols<background[rows].length;cols++){
			if(background[rows][cols]==loMINE){
					jb[rows][cols].setIcon(new ImageIcon("image/Mime.png"));	
				}
			}
		}
	}
public void Choose(){
		res=JOptionPane.showConfirmDialog(null, "你踩到雷了", "是否进行新游戏",JOptionPane.YES_NO_CANCEL_OPTION );
		if(res==JOptionPane.YES_OPTION){
			Continue=2;
			
		}else{
			
			Continue=1;
		}
}
public void mouseClicked(MouseEvent e) {
		
		Object obj=e.getSource();
		String[] temp_str=((JButton)obj).getName().split("_");
		
		int x=Integer.parseInt(temp_str[0]);
		int y=Integer.parseInt(temp_str[1]);
		if(e.getButton()==3){
			if((obj instanceof JButton)==false){
				System.out.println("内部错误");
				return;
			}
			if("{1}quot".equals(jb[x][y].getIcon())){
				
				jb[x][y].setText("");
			}else{
				jb[x][y].setText("{1}quot");
			}
			
		}else if(e.getButton()==MouseEvent.BUTTON3){
			int temp=1;
			if(temp%2==1){
				temp++;
				if(background[x][y]==loMINE){}
					jb[x][y].setIcon(new ImageIcon("image/flag.png"));
			}else{
				
			}
		}
	}
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void run() {	
		t=0;
		while(true){
			if(flag){
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			t++;
			showTime.setText("已用时："+t+"秒");
		}
			
	}
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();    
		
        int x, y;    
        if ((obj instanceof JButton) == false) {    
        	showMessage("错误","内部错误");
            return;    
        }    
        String[] tmp_str = ((JButton) obj).getName().split("_");    
        x = Integer.parseInt(tmp_str[0]);    
        y = Integer.parseInt(tmp_str[1]);
        
        if (background[x][y] == loMINE) {    
            System.out.println("死亡");    
            flag = true;    
            showMines(); 
            Choose();
            return;    
        }   
      showEandNumber(x, y,0); 
      //jb[x][y].setEnabled(false);
      checkSuccess();
	}
	private void showMessage(String title, String info) {    
        jl.setText(info);    
        System.out.println("in functino showMessage()  :  " + info);    
    }   
	public void checkSuccess(){
		int cs=0;
		for(int i=0;i<JBSIZE;i++){
			for(int j=0;j<JBSIZE;j++){
				if(jb[i][j].isEnabled()){
					cs++;
				}
			}
		}	
		if(cs==FMSIZE){
			String temp=showTime.getText();
			temp=temp.replace("[^0-9]", "");
			showMessage("chengg"+temp+"秒","成功");
			flag=true;
			
			showMines();
			res=JOptionPane.showConfirmDialog(null, "恭喜找到所有的雷", "是否进行新游戏",JOptionPane.YES_NO_CANCEL_OPTION );
			if(res==JOptionPane.YES_OPTION){
				Continue=2;
			}else{
				Continue=1;
			}
		}
	}
	public int getNumber(Point hM){
		
		int flag=0;
		Point point1=new Point(hM.x-1,hM.y-1);
		Point point2=new Point(hM.x-1,hM.y);
		Point point3=new Point(hM.x-1,hM.y+1);
		Point point4=new Point(hM.x,hM.y-1);
		Point point5=new Point(hM.x,hM.y+1);
		Point point6=new Point(hM.x+1,hM.y-1);
		Point point7=new Point(hM.x+1,hM.y);
		Point point8=new Point(hM.x+1,hM.y+1);
		ArrayList<Point> aroundList=new ArrayList<Point>();
		aroundList.add(point1);
		aroundList.add(point2);
		aroundList.add(point3);
		aroundList.add(point4);
		aroundList.add(point5);
		aroundList.add(point6);
		aroundList.add(point7);
		aroundList.add(point8);
		for(int i=0;i<aroundList.size();i++){
			Point pointtemp=aroundList.get(i);
			if(pointtemp.x>=0&&pointtemp.y>=0&& pointtemp.x<background.length&&pointtemp.y<background[0].length){
				if(background[pointtemp.x][pointtemp.y]==loMINE){
					++flag;
				}else{
					;
				}
				//aroundList.remove(i);
			}
		}
		return flag;
	}
	public void showEandNumber(int x,int y,int d){
		background[x][y]=loCHECKED;
		Point point=new Point(x,y);
		int number=getNumber(point);
		switch (number) {
				case 0://
					jb[x][y].setEnabled(false);
					minesP.add(new Point(x,y));
					Point point1=new Point(x-1,y-1);
					
					Point point2=new Point(x-1,y);
					
					Point point3=new Point(x-1,y+1);
					
					Point point4=new Point(x,y-1);
					
					Point point5=new Point(x,y+1);
					
					Point point6=new Point(x+1,y-1);
					
					Point point7=new Point(x+1,y);
					
					Point point8=new Point(x+1,y+1);
					
					ArrayList<Point> aroundList=new ArrayList<Point>();
					aroundList.add(point1);
					aroundList.add(point2);
					aroundList.add(point3);
					aroundList.add(point4);
					aroundList.add(point5);
					aroundList.add(point6);
					aroundList.add(point7);
					aroundList.add(point8);
					for(int i=0;i<aroundList.size();i++){
							Point pointtemp=aroundList.get(i);
							if(pointtemp.x>=0&&pointtemp.y>=0&& pointtemp.x<background.length&&pointtemp.y<background[0].length
									&&background[pointtemp.x][pointtemp.y]!=loCHECKED){
								
								if (getNumber(pointtemp)==0) {
									jb[pointtemp.x][pointtemp.y].setEnabled(false);
									minesP.add(new Point(pointtemp.x,pointtemp.y));
									if(d<=2){	
										showEandNumber(pointtemp.x, pointtemp.y, d + 1);
									}
								}
								//aroundList.remove(i);
							}
					}
				break;
				case 1:
					jb[x][y].setIcon(new ImageIcon("image/1.png"));
					//jb[x][y].setDisabledIcon(new ImageIcon("image/1.png"));
					//jb[x][y].setEnabled(false);
					break;
				case 2:
					jb[x][y].setIcon(new ImageIcon("image/2.png"));
					break;
				case 3:
					jb[x][y].setIcon(new ImageIcon("image/3.png"));
					break;
				case 4:
					jb[x][y].setIcon(new ImageIcon("image/4.png"));
					break;
				case 5:
					jb[x][y].setIcon(new ImageIcon("image/5.png"));
					break;
				case 6:
					jb[x][y].setIcon(new ImageIcon("image/6.png"));
					break;
				case 7:
					jb[x][y].setIcon(new ImageIcon("image/7.png"));
					break;
				case 8:
					jb[x][y].setIcon(new ImageIcon("image/8.png"));
					break;
					
				default:
					break;
		}
		jb[x][y].setEnabled(false);
		
		minesP.add(new Point(x,y));
		//return number;
	}
	
}
