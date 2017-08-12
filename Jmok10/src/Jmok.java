import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.Timer;
import javax.swing.ToolTipManager;

public class Jmok extends JFrame{

	public static Jmok JMOK;
	public static JTime clock;
	
    public static int a, i, j, I=1, You=2, myTurn=1, end=0, enable=1, isGo=0, isStarted=0, isOffline=0;
    public static int board[][] = new int[21][21];
    public static String[] name = new String[3];
    public static String user;
    public static ArrayList<String> opponents = new ArrayList<String>();
    public static Font font2 = new Font("Arial", Font.ITALIC, 20);
    public static Font font1 = new Font("Arial", Font.TRUETYPE_FONT, 18);
    
    public static JToggleButton clara[][] = new JToggleButton[19][19]; 
    public static JComboBox combo = null;
    public static JButton refresh = null;
    public static JButton side = null;
    public static JLabel time = null;
    public static JLabel username = null;
    public static JLabel movecount = null;
    public static JFrame ctrlPanel = null;
    
    public static ImageIcon off = new ImageIcon(Jmok.class.getResource("TipOff.png"));
    public static ImageIcon on = new ImageIcon(Jmok.class.getResource("TipOn.png"));
    public static ImageIcon onGo = new ImageIcon(Jmok.class.getResource("onGo.png"));
    public static ImageIcon offGo = new ImageIcon(Jmok.class.getResource("offGo.png"));
    public static ImageIcon Icon = new ImageIcon(Jmok.class.getResource("Jmok.png"));
    public static ImageIcon Background = new ImageIcon(Jmok.class.getResource("Pic.png"));
    public static ImageIcon Go1 = new ImageIcon(Jmok.class.getResource("Go1.png"));
    public static ImageIcon Go2 = new ImageIcon(Jmok.class.getResource("Go2.png"));
    public static ImageIcon Go3 = new ImageIcon(Jmok.class.getResource("Go3.png"));
    public static ImageIcon Win1 = new ImageIcon(Jmok.class.getResource("Win1.png"));
    public static ImageIcon Win2 = new ImageIcon(Jmok.class.getResource("Win2.png"));
    public static ImageIcon Foul1 = new ImageIcon(Jmok.class.getResource("Foul1.png"));
    public static ImageIcon Foul2 = new ImageIcon(Jmok.class.getResource("Foul2.png"));
    public static ImageIcon Time1 = new ImageIcon(Jmok.class.getResource("Time1.png"));
    public static ImageIcon Time2 = new ImageIcon(Jmok.class.getResource("Time2.png"));
    public static ImageIcon Black = new ImageIcon(Jmok.class.getResource("Black.png"));
    public static ImageIcon White = new ImageIcon(Jmok.class.getResource("White.png"));
    public static ImageIcon Ctrl = new ImageIcon(Jmok.class.getResource("Ctrl.png"));
    public static ImageIcon Resign = new ImageIcon(Jmok.class.getResource("Resign.png"));
    public static ImageIcon Jmok0 = new ImageIcon(Jmok.class.getResource("Jmok0.png"));
    public static ImageIcon Jmok1 = new ImageIcon(Jmok.class.getResource("Jmok1.png")); //Black
    public static ImageIcon Jmok2 = new ImageIcon(Jmok.class.getResource("Jmok2.png")); //White
    public static ImageIcon Jmok3 = new ImageIcon(Jmok.class.getResource("Jmok3.png"));
    public static ImageIcon [] Jmoks = {Jmok0, Jmok1, Jmok2, Jmok3};

    public static Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
    
    public Jmok(){
    	System.out.println("Starting Jmok...");
    	System.out.println("Detecting Display... ");
    	System.out.println(screenSize.width+"*"+screenSize.height);
    	JennaMok();//1034
    }

    public void JennaMok(){
    	user = DBwork.checkName();
    	name[I]=user;
    	isStarted=1;
    	
    	if(isOffline==1){
    		JFile.load();
    	}
    	else{
        	if(DBwork.updateMove())
        		JFile.backupMove();
        	else
            	JFile.load();
    	}
    	
    	ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setEnabled(false);

        //JMenubar Starts
        JMenuBar mbar = new JMenuBar();
        mbar.setBorder(null);

        JMenu Menu = new JMenu("Menu");
        Menu.setForeground(Color.BLACK);
        Menu.setMnemonic(KeyEvent.VK_M);
        mbar.add(Menu);

        JMenuItem Info = new JMenuItem("Get Info");
        Info.addActionListener((ActionEvent e) -> {
        	JOptionPane.showMessageDialog(null, "Jmok\nVersion 10.17\n By Dr.Shin", "Info", JOptionPane.PLAIN_MESSAGE, Icon);
        });
        Info.setForeground(Color.BLACK);
        Info.setBackground(Color.WHITE);
        Menu.add(Info);
        
        JMenuItem Rename = new JMenuItem("Rename");
        Rename.addActionListener((ActionEvent e) -> {
        	user = DBwork.checkName();
        	if(user!=null&&user.length()!=0){
        		name[I]=user;
        		username.setText(user);
        	}
        });
        Rename.setForeground(Color.BLACK);
        Rename.setBackground(Color.WHITE);
        Menu.add(Rename);
        
        JMenuItem Help = new JMenuItem("Get Help");
        Help.addActionListener((ActionEvent e) -> {
        	JOptionPane.showMessageDialog(null, "In case you choose White:\nPress Button and wait for Challenger\n\n"
        +"In case you choose Black:\nPress 'Refresh' to find your opponent\nand Press Button to Play Game\n\n"+
        "During the Play:\nIf you're not sure whether its your turn,\nCheck if you're remaining time goes\n\n"+
        "Well then, Enjoy the Game!",
        "Help", JOptionPane.INFORMATION_MESSAGE, null);
        });
        Help.setForeground(Color.BLACK);
        Help.setBackground(Color.WHITE);
        Menu.add(Help);
        
        JMenuItem Shortcuts = new JMenuItem("Shortcuts");
        Shortcuts.addActionListener((ActionEvent e) -> {
        	JOptionPane.showMessageDialog(null, "F1 >> Turn On/Off Tooltip\n\n"
        +"F2 >> Enable/Disable Go mode\n\n"+"Esc >> Force Quit",
        "Shortcuts", JOptionPane.INFORMATION_MESSAGE, null);
        });
        Shortcuts.setForeground(Color.BLACK);
        Shortcuts.setBackground(Color.WHITE);
        Menu.add(Shortcuts);
        
        Menu.addSeparator();
        
        JMenuItem Exit = new JMenuItem("Exit ...");
        Exit.addActionListener((ActionEvent e) -> {
        	DBwork.cleanDB(user);
        	System.exit(0);
        });
        Exit.setForeground(Color.RED);
        Exit.setBackground(Color.WHITE);
        Menu.add(Exit);
        //JMenuBar ends
        
        ctrlPanel = new JFrame("Jmok");
        ctrlPanel.setSize(230, 900);
    	ctrlPanel.setLocation((screenSize.width-900)/2-230, (screenSize.height-900)/2-25);
    	ctrlPanel.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we){
            	DBwork.cleanDB(name[I]);
            	System.exit(0);
            }
        });
        ctrlPanel.setResizable(false);
        ctrlPanel.setContentPane(new iPanel(Ctrl));
        ctrlPanel.setLayout(null);
        ctrlPanel.setJMenuBar(mbar);
        
        side = new JButton(Black);
        side.setBounds(61, 63, 100, 100);
        side.setContentAreaFilled(false);
        side.setFocusable(false);
        side.setBorder(null);
        ctrlPanel.add(side);
        
        username = new JLabel(user);
        username.setFont(font2);
        username.setForeground(Color.BLACK);
        username.setBounds(70, 225, 150, 50);
        ctrlPanel.add(username);
        
        opponents.add("(select)");
        opponents.add("AI");
        combo = new JComboBox(opponents.toArray());
        combo.addActionListener((ActionEvent e) -> { 
            JComboBox b = (JComboBox)e.getSource();
            name[You]= (String)b.getSelectedItem();
        });
        combo.setFocusable(false);
        combo.setBounds(67, 300, 90, 30);
        combo.setFont(font1);
        ctrlPanel.add(combo);
        
        refresh = new JButton("Refresh");
        refresh.setMnemonic(KeyEvent.VK_R);
        refresh.addActionListener((ActionEvent e) -> {
        	combo.removeAllItems();
        	DBwork.findWhite();
        	for(a=0;a<opponents.size();a++){
        		combo.addItem(opponents.get(a));
        	}
        });
        refresh.setFocusable(false);
        refresh.setFont(font1);
        refresh.setBounds(61, 393, 102, 40);
        ctrlPanel.add(refresh);
        
        ButtonGroup bg = new ButtonGroup();
        JRadioButton black = new JRadioButton("Black", true);
        black.setForeground(Color.BLACK);
        black.setContentAreaFilled(false);
        black.setMnemonic(KeyEvent.VK_B);
        black.setFocusable(false);
        black.setFont(font1);
        black.addActionListener((ActionEvent e) -> {
        	I=1;//me
        	You=2;//opponent
        	myTurn=1; //1:BLACK 2:WHITE
        	name[You]=null; //name[2]
        	name[I]=user; //name[1]
        	combo.setEnabled(true);
        	refresh.setEnabled(true);
        	side.setIcon(Black);
        });
        black.setBounds(73, 435, 70, 40);
        
        JRadioButton white = new JRadioButton("White", false);
        white.setForeground(Color.BLACK);
        white.setContentAreaFilled(false);
        white.setMnemonic(KeyEvent.VK_W);
        white.setFocusable(false);
        white.setFont(font1);
        white.addActionListener((ActionEvent e) -> {
        	I=2;//me
        	You=1;//opponent
        	myTurn=0; //1:BLACK 2:WHITE
        	name[You]=null; //name[1]
        	name[I]=user; //name[2]
        	combo.setEnabled(false);
        	refresh.setEnabled(false);
        	side.setIcon(White);
        });
        white.setBounds(73, 465, 80, 40);
        bg.add(black);
        bg.add(white);
        ctrlPanel.add(black);
        ctrlPanel.add(white);
        
        time = new JLabel();
        time.setBounds(73, 570, 100, 30);
        time.setFont(font2);
        time.setForeground(Color.BLACK);
        time.setToolTipText("Time Remaining");
        time.setFocusable(false);
        time.setText("30:00:00");
        ctrlPanel.add(time);
        
        movecount = new JLabel();
        movecount.setBounds(73, 637, 100, 30);
        movecount.setFont(font2);
        movecount.setForeground(Color.BLACK);
        movecount.setToolTipText("The Number of Moves You Made");
        movecount.setFocusable(false);
        movecount.setText("0");
        ctrlPanel.add(movecount);
        
        
        JButton go = new JButton();
        go.setMnemonic(KeyEvent.VK_G);
        go.setFont(new Font("Arial", Font.PLAIN, 25));
        go.setIcon(Go1);
        go.setRolloverIcon(Go2);
        go.setPressedIcon(Go3);
        go.setBorder(null);
        go.setFocusable(false);
        go.setContentAreaFilled(false);
        go.addActionListener((ActionEvent e) -> {
        	if(name[You]==null&&I==1) //if you take black not choosing opponent. 
        		JOptionPane.showMessageDialog(null, "Please check your Opponent!", "ERROR", JOptionPane.ERROR_MESSAGE, null);
        	else{
        		Jenna();
        	}
        });
        go.setBounds(12, 630, 200, 200);
        ctrlPanel.add(go);
        
        ctrlPanel.addKeyListener(new Listen(0));
        ctrlPanel.setVisible(true);       
        ctrlPanel.requestFocus();
    }
    
    public void Jenna() {
    	end=0;
    	DBwork.init();
    	
        setTitle("Jmok("+user+")");
        setSize(895, 900); //882
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);       
        setContentPane(new iPanel(Background));
        
        Container c = getContentPane();
        c.setLayout(null);
        c.addKeyListener(new Listen(1));
        
        for(i=0;i<21;i++){
        	board[i][0]=3;
        	board[i][20]=3;
        	board[0][i]=3;
        	board[20][i]=3;
        }//fill bounds with 3
        
        for(i=1;i<=19;i++){
            for(j=1;j<=19;j++){
            	board[i][j]=0;
            }
        }//initialize board[][]
        
        for(i=0;i<19;i++){
            for(j=0;j<19;j++){
            clara[i][j] = new JToggleButton(Jmoks[0]);
            clara[i][j].setRolloverIcon(Jmoks[3]);
            clara[i][j].setSelectedIcon(Jmoks[I]);
            clara[i][j].putClientProperty("column", i);
            clara[i][j].putClientProperty("row", j);
            clara[i][j].setToolTipText("("+Integer.toString(i)+","+Integer.toString(j)+")");
            clara[i][j].setBorder(null);
            clara[i][j].setFocusable(false);
            clara[i][j].setContentAreaFilled(false);
            clara[i][j].setBounds(i*46+7,j*45+6,46,45);
            clara[i][j].addActionListener(new DBwork());
            c.add(clara[i][j]);
            }
        }

        if(I==2){//1:BLACK 2:WHITE
        	name[You]=DBwork.findBlack();
        	DBwork.useDB();
        }
        else{
        	DBwork.showBlack();
        }
        
        clock = new JTime();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we){
            	clock.t.stop();
            	if(name[You].equals("AI")){
            		DBwork.cleanDB(name[I]);
            		movecount.setText("0");
                	time.setText("30:00:00");
            	}
            	else{
            		DBwork.cleanDB(name[You]);
                	DBwork.Resign();
                	System.out.println(user+" : Resigned");	
            	}
            }
        });
        setVisible(true);
        c.requestFocus();
    }
    
    public static void Win(int k){
    	movecount.setText("0");
    	clock.t.stop();
    	time.setText("30:00:00");
    	switch(k){ //1->you win <-> 2->you lost
    	case 7: JOptionPane.showMessageDialog(null, null, "GAME OVER : "+user, JOptionPane.PLAIN_MESSAGE, Resign); break; //win
    	case 6: JOptionPane.showMessageDialog(null, null, "GAME OVER : "+user, JOptionPane.PLAIN_MESSAGE, Time1); break; //win
    	case 5: JOptionPane.showMessageDialog(null, null, "GAME OVER : "+user, JOptionPane.PLAIN_MESSAGE, Time2); 
    	        DBwork.cleanDB(name[I]);
		        DBwork.cleanDB(name[You]);
		        break; //lost
    	case 4: JOptionPane.showMessageDialog(null, null, "GAME OVER : "+user, JOptionPane.PLAIN_MESSAGE, Foul1); break; //win
    	case 3: JOptionPane.showMessageDialog(null, null, "GAME OVER : "+user, JOptionPane.PLAIN_MESSAGE, Foul2);
    	        DBwork.cleanDB(name[I]);
		        DBwork.cleanDB(name[You]);
		        break; //lost
    	default:
    		if(k==I){
    			JOptionPane.showMessageDialog(null, null, "GAME OVER : "+user, JOptionPane.PLAIN_MESSAGE, Win1);
    			end=1;
    		}
    		else{
    			DBwork.cleanDB(name[I]);
    			DBwork.cleanDB(name[You]);
    			JOptionPane.showMessageDialog(null, null, "GAME OVER : "+user, JOptionPane.PLAIN_MESSAGE, Win2);
    			System.exit(0);
    		}
    	}
    	JMOK.setVisible(false);
    }
    
    public static class JFile {
    	private static final String home = System.getProperty("user.home");
    	
    	public static void backupMove(){
    		try {
    			FileOutputStream fout = new FileOutputStream(home+File.separator+"Appdata\\Local\\Jmok.dll");
    			DataOutputStream out = new DataOutputStream(fout);
    			for(int i = 0;i < 12;i++){
    				out.write(Idea.moves[i]);
    			}
    			out.close();
    		}
    		catch(IOException e){}
    	}
    	
    	public static boolean load(){
    		try {
    			FileInputStream fin = new FileInputStream(home+File.separator+"Appdata\\Local\\Jmok.dll");
    			DataInputStream in = new DataInputStream(fin);
    			for(int i=0;i < 12;i++){
    				Idea.moves[i] = in.readInt();
    			}
    			in.close();
    		}
    		catch(IOException e){return false;}
    		return true;
    	}
    }
    
    class JTime implements ActionListener{
        public Timer t;
        public int sec=180000;
        
        JTime() {
            t = new Timer(10,this);
            t.start();
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == t) {
            	time.setText(String.format("%02d",sec/6000)+":"+String.format("%02d",(sec%6000)/100)+":"+String.format("%02d",(sec%6000)%100));
            	if(sec<=0){
            		if(end==0){
            			board[0][0]=5;
                		DBwork.set19();
                		end=1;
                		DBwork.useDB();
            		}
            		t.stop();
            	}
            	else
            		sec--;
            }
        }
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
    
    class Listen extends KeyAdapter{
    	private int c; 
    	public Listen(int c){
    		this.c=c;
    	}
    	@Override
		public void keyPressed (KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_F1){
            	if(enable==0){
            		JOptionPane.showMessageDialog(null, null, "Notice!", JOptionPane.PLAIN_MESSAGE, off);
            	    ToolTipManager.sharedInstance().setEnabled(false);
            		enable=1;
            	}
            	else{
            		JOptionPane.showMessageDialog(null, null, "Notice!", JOptionPane.PLAIN_MESSAGE, on);
            		ToolTipManager.sharedInstance().setEnabled(true);
            		enable=0;
            	}
            }
            else if(e.getKeyCode()==KeyEvent.VK_F2){
            	if(c==0){
            		if(isGo==1){
                        isGo=0;
                        JOptionPane.showMessageDialog(null, null, "Notice!", JOptionPane.PLAIN_MESSAGE, offGo);
                    }
                    else{
                        isGo=1;
                        JOptionPane.showMessageDialog(null, null, "Notice!", JOptionPane.PLAIN_MESSAGE, onGo);
                    }
            	}
        	}
            else if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            	DBwork.cleanDB(user);
            	System.exit(0);
        	}
        }
    }
    
    public static void main(String[] args){
    	EventQueue.invokeLater(() -> {
    		JMOK = new Jmok();
    	});
	}
}