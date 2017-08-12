import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

class JMachine{
	int moves[] = new int[12];
	int id, point=0;
	int color; //black->1, white->2
	public JMachine(int a, int b, int c, int d, int e, int f, int g, int h, int i, int j, int k, int l, int id){
		moves[0] = a;
		moves[1] = b;
		moves[2] = c;
		moves[3] = d;
		moves[4] = e;
		moves[5] = f;
		moves[6] = g;
		moves[7] = h;
		moves[8] = i;
		moves[9] = j;
		moves[10] = k;
		moves[11] = l;
		this.id = id;
	}
	public void setToBlack(){
		color=1;
	}
	public void setToWhite(){
		color=2;
	}
	public int getID(){
		return id;
	}
	public void addPoint(){
		point++;
	}
}

public class JLearn extends JFrame{

	public static JLearn JLearn;
	
    public static int a, i, j, You=2, myTurn=1, end=0, winner, isStarted=0;
    public static int board[][] = new int[21][21];
    public static String[] name = new String[3];
    
    public static JToggleButton clara[][] = new JToggleButton[19][19]; 
    
    public static ImageIcon Background = new ImageIcon(JLearn.class.getResource("Pic.png"));
    public static ImageIcon Jmok0 = new ImageIcon(JLearn.class.getResource("Jmok0.png"));
    public static ImageIcon Jmok1 = new ImageIcon(JLearn.class.getResource("Jmok1.png")); //Black
    public static ImageIcon Jmok2 = new ImageIcon(JLearn.class.getResource("Jmok2.png")); //White
    public static ImageIcon [] Jmoks = {Jmok0, Jmok1, Jmok2};

    public static Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
    
    public JLearn() throws InterruptedException{
    	System.out.println("Starting JLearn...");
    	JennaMok();//1034
    }

    public void JennaMok() throws InterruptedException{
    	Scanner s = new Scanner(System.in);
    	
    	System.out.println("moves[0] = move_4_1 (D: 1000)");
    	System.out.println("moves[1] = move_4_2 (D: 1000)");
        System.out.println("moves[2] = move_4_3 (D: 1000)");
        System.out.println("moves[3] = move_3_1o (D: 350)");
        System.out.println("moves[4] = move_3_1s (D: 50)");
        System.out.println("moves[5] = move_3_2o (D: 350)");
        System.out.println("moves[6] = move_3_2s (D: 50)");
        System.out.println("moves[7] = move_2_1o (D: 10)");
        System.out.println("moves[8] = move_2_1s (D: 5)");
        System.out.println("moves[9] = move_2_2o (D: 10)");
        System.out.println("moves[10] = move_2_2s (D: 5)");
        System.out.println("moves[12] = move_multi3 (D: 150)");
    
    	int range[][] = new int[12][2];
    	int asize[] = new int[12]; 
    	for(int i=0;i<12;i++){
    		range[i][0] = s.nextInt();
    		range[i][1] = s.nextInt();
    		asize[i] = range[i][1]-range[i][0]+1;
    	}
    	
    	System.out.println("Input Complete");
    	JMachine[][][][][][][][][][][][] JMachines= new JMachine[asize[0]][asize[1]][asize[2]][asize[3]][asize[4]][asize[5]][asize[6]][asize[7]][asize[8]][asize[9]][asize[10]][asize[11]];
    	
    	int id=0;
    	for(int a=0;a<=asize[0];a++){
    		for(int b=0;b<=asize[1];b++){
    			for(int c=0;c<=asize[2];c++){
    				for(int d=0;d<=asize[3];d++){
    					for(int e=0;e<=asize[4];e++){
    						for(int f=0;f<=asize[5];f++){
    							for(int g=0;g<=asize[6];g++){
    								for(int h=0;h<=asize[7];h++){
    									for(int i=0;i<=asize[8];i++){
    										for(int j=0;j<=asize[9];j++){
    											for(int k=0;k<=asize[10];k++){
    												for(int l=0;l<=asize[11];l++){
    													JMachines[a-0][b-0][c-0][d-0][e-0][f-0][g-0][h-0][i-0][j-0][k-0][l-0] = new JMachine(a, b, c, d, e, f, g, h, i, j, k, l, id);
    													System.out.println("Creating JMachine("+a+", "+b+", "+c+", "+d+", "+e+", "+f+", "+g+", "+h+", "+i+", "+j+", "+l+", "+id+") (ID:"+id+")");
    													id++;
    												}
    									    	}
    								    	}
    							    	}
    						    	}
    					    	}
    				    	}
    			    	}
    		    	}
    	    	}
        	}
    	}
    	
    	Jenna();
    	
    	System.out.println("Creating JMachine Finished!");
    	for(int a=0;a<=asize[0];a++){
    		for(int b=0;b<=asize[1];b++){
    			for(int c=0;c<=asize[2];c++){
    				for(int d=0;d<=asize[3];d++){
    					for(int e=0;e<=asize[4];e++){
    						for(int f=0;f<=asize[5];f++){
    							for(int g=0;g<=asize[6];g++){
    								for(int h=0;h<=asize[7];h++){
    									for(int i=0;i<=asize[8];i++){
    										for(int j=0;j<=asize[9];j++){
    											for(int k=0;k<=asize[10];k++){
    												for(int l=0;l<=asize[11];l++){
    													//Fighting
    													int currentid = JMachines[a][b][c][d][e][f][g][h][i][j][k][l].getID();
    													System.out.println("Analysis JMachine ID:"+currentid);
    											    	for(int a1=0;a1<=asize[0];a1++){
    											    		for(int b1=0;b1<=asize[1];b1++){
    											    			for(int c1=0;c1<=asize[2];c1++){
    											    				for(int d1=0;d1<=asize[3];d1++){
    											    					for(int e1=0;e1<=asize[4];e1++){
    											    						for(int f1=0;f1<=asize[5];f1++){
    											    							for(int g1=0;g1<=asize[6];g1++){
    											    								for(int h1=0;h1<=asize[7];h1++){
    											    									for(int i1=0;i1<=asize[8];i1++){
    											    										for(int j1=0;j1<=asize[9];j1++){
    											    											for(int k1=0;k1<=asize[10];k1++){
    											    												for(int l1=0;l1<=asize[11];l1++){
    											    													if(JMachines[a1][b1][c1][d1][e1][f1][g1][h1][i1][j1][k1][l1].getID()!=currentid){
    											    														Fight(JMachines[a][b][c][d][e][f][g][h][i][j][k][l], JMachines[a1][b1][c1][d1][e1][f1][g1][h1][i1][j1][k1][l1]);
    											    													}
    											    												}
    											    									    	}
    											    								    	}
    											    							    	}
    											    						    	}
    											    					    	}
    											    				    	}
    											    			    	}
    											    		    	}
    											    	    	}
    											        	}
    											    	}
    												}
    									    	}
    								    	}
    							    	}
    						    	}
    					    	}
    				    	}
    			    	}
    		    	}
    	    	}
        	}
    	}
    	System.out.println("Evaluating Machines Completed!");
    	System.out.println("*Results*");
    	for(int i=0;i<12;i++){
    		System.out.println("move["+i+"]: ");
    	}
    }
    
    public void Fight(JMachine A, JMachine B) throws InterruptedException{
    	int aW = 0;
    	int bW = 0;
    	A.setToBlack();
    	B.setToWhite();
    	initBoard();
    	for(int i=0;i<50;i++){
    		while(end==0){
    			Thread.sleep(100);
        		You=1;
        		changeMove(A);
        		Idea.aIntelligence();
        		clara[Idea.getX()-1][Idea.getY()-1].setIcon(Jmok1);

    			Thread.sleep(100);
    			You=2;
        		changeMove(B);
        		Idea.aIntelligence();
        		clara[Idea.getX()-1][Idea.getY()-1].setIcon(Jmok2);
        	}
    		if(winner==1){
    			System.out.println("ID:"+A.getID()+" Win");
    			aW++;
    		}
    		else if(winner==2){
    			System.out.println("ID:"+B.getID()+" Win");
    			bW++;
    		}
    	}
    	initBoard();
    	A.setToWhite();
    	B.setToBlack();
    	for(int i=0;i<50;i++){
    		while(end==0){
        		You=1;
        		changeMove(B);
        		Idea.aIntelligence();
        		clara[Idea.getX()-1][Idea.getY()-1].setIcon(Jmok1);
        		You=2;
        		changeMove(A);
        		Idea.aIntelligence();
        		clara[Idea.getX()-1][Idea.getY()-1].setIcon(Jmok2);
        	}
    		if(winner==2){
    			System.out.println("ID:"+A.getID()+" Win");
    			aW++;
    		}
    		else if(winner==1){
    			System.out.println("ID:"+B.getID()+" Win");
    			bW++;
    		}
    	}
    	System.out.println("Analysis Complete!");
    	System.out.println("ID:"+A.getID()+" Win "+aW+" Times!");
    	System.out.println("ID:"+B.getID()+" Win "+bW+" Times!");
    	if(aW>bW){
    		A.addPoint();
    	}
    	else if(aW<bW){
    		B.addPoint();
    	}
    }
    
    public void changeMove(JMachine A){
    	for(int i=0;i<12;i++){
    		Idea.moves[i] = A.moves[i];
    	}
    }
    
    public void initBoard(){
    	for(i=0;i<21;i++){
        	board[i][0]=3;
        	board[i][20]=3;
        	board[0][i]=3;
        	board[20][i]=3;
        }//fill bounds with 3
        
        for(i=1;i<=19;i++){
            for(j=1;j<=19;j++){
            	board[i][j]=0;
            	clara[i][j].setIcon(Jmok0);
            }
        }//initialize board[][]
    }
    
    public void Jenna() {
    	setTitle("JLearn");
        setSize(895, 900); //882
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);       
        setContentPane(new iPanel(Background));
        
        Container c = getContentPane();
        c.setLayout(null);
        
        for(i=0;i<19;i++){
            for(j=0;j<19;j++){
            clara[i][j] = new JToggleButton(Jmoks[0]);
            clara[i][j].putClientProperty("column", i);
            clara[i][j].putClientProperty("row", j);
            clara[i][j].setToolTipText("("+Integer.toString(i)+","+Integer.toString(j)+")");
            clara[i][j].setBorder(null);
            clara[i][j].setFocusable(false);
            clara[i][j].setContentAreaFilled(false);
            clara[i][j].setBounds(i*46+7,j*45+6,46,45);
            c.add(clara[i][j]);
            }
        }
        
        setVisible(true);
    }
    
    public static void Win(int k){
    	end=1;
    	winner=k;
    }
    
    class iPanel extends JPanel{
    	ImageIcon icon;
    	Image img;
    	public iPanel(ImageIcon icon){
    		this.icon=icon;
    		img=icon.getImage();
    	}
    	public void paintComponent(Graphics g){
    		super.paintComponent(g);
    		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    	}
    }
        
    public static void main(String[] args){
    	EventQueue.invokeLater(() -> {
    		try {
				JLearn = new JLearn();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	});
	}
}