import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.ToolTipManager;
import java.util.Random;

public class BetaGo extends JFrame{    
    public static int i, j, x, y, count, check=0, end=0;
    public static int board[][] = new int[21][21];
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();
    JToggleButton clara[][] = new JToggleButton[20][20]; 
    ImageIcon BetaGo = new ImageIcon(getClass().getResource("BetaGo.png"));
    ImageIcon JennaGo = new ImageIcon(getClass().getResource("JennaGo0.png"));
    ImageIcon JennaGo1 = new ImageIcon(getClass().getResource("JennaGo1.png")); //Black
    ImageIcon JennaGo2 = new ImageIcon(getClass().getResource("JennaGo2.png")); //White
    ImageIcon Foul = new ImageIcon(getClass().getResource("Foul.png"));
    
    public BetaGo() {
        Jenna();
    }

    public void Jenna() {
        String [] colors = {"WHITE", "BLACK"};
    	JFrame choice = new JFrame("Opening");
        String color = (String) JOptionPane.showInputDialog(choice, 
        "Which side do you want?", "BetaGo 5.5", JOptionPane.QUESTION_MESSAGE, BetaGo, colors, colors[1]);
        
        if(color==null)
        	System.exit(0);
        else if(color.equals(colors[0])){
        	ImageIcon temp = JennaGo1;
            JennaGo1 = JennaGo2;
            JennaGo2 = temp;
            check=1;
        }//1:Human(WHITE) 2:Computer(BLACK)

        setTitle("BETA GO!");
        setSize(595, 625);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);       
        
        Container c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(187,137,33));
        
        for(i=0;i<21;i++){
        	board[i][0]=3;
        	board[i][20]=3;
        	board[0][i]=3;
        	board[20][i]=3;
        }//set bounds to 3
        
        ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setEnabled(false);
        
        for(i=1;i<=19;i++){
            for(j=1;j<=19;j++){
            clara[i][j] = new JToggleButton(JennaGo);
            clara[i][j].setSelectedIcon(JennaGo1);
            clara[i][j].putClientProperty("column", i);
            clara[i][j].putClientProperty("row", j);
            clara[i][j].setToolTipText("("+Integer.toString(i)+","+Integer.toString(j)+")");
            clara[i][j].setBorder(null);
            clara[i][j].addKeyListener(new Listen());
            clara[i][j].setBounds(i*30-20,j*30-20,30,30);
            c.add(clara[i][j]);
            clara[i][j].addActionListener((ActionEvent e) -> { 
            	JToggleButton b = (JToggleButton) e.getSource();
                x = (int)b.getClientProperty("column");
                y = (int)b.getClientProperty("row");
                if(board[x][y]!=0){
                    JFrame frame= new JFrame();
                    JOptionPane.showMessageDialog(frame, null, "GAME OVER", JOptionPane.PLAIN_MESSAGE, Foul);
                    System.exit(0);
                }
                try {
            	    Thread.sleep(100);
            	} catch(InterruptedException ex) {
            	    Thread.currentThread().interrupt();
            	}
                board[x][y]=1;
                Idea.iLoveJenna();
                board[Idea.x][Idea.y]=2;
                clara[Idea.x][Idea.y].setIcon(JennaGo2);
                count++;
                if(end==1){	
                	Window.Win(2);
                }
            });
            }
        }
        if(check==1){
    		int x_r = 10;
	        int y_r = 10;
        	Random r = new Random();
        	if(r.nextInt(100)%3==0){
        	    x_r = r.nextInt(3)+9;
        	    y_r = r.nextInt(3)+9;
        	}
        	board[x_r][y_r]=2;
        	clara[x_r][y_r].setIcon(JennaGo2);
        }
        Window.MyJenna();
    }
    
    public class Listen extends KeyAdapter{
    	int enable=1;
    	JFrame frame = new JFrame("Message");
    	ImageIcon off = new ImageIcon(getClass().getResource("TipOff.png"));
    	ImageIcon on = new ImageIcon(getClass().getResource("TipOn.png"));
    	
    	public void keyPressed (KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_F1)
                Window.f.setVisible(true);
            else if(e.getKeyCode()==KeyEvent.VK_F2){
            	if(enable==0){
            		JOptionPane.showMessageDialog(frame, null, "Notice!", JOptionPane.PLAIN_MESSAGE, off);
            	    ToolTipManager.sharedInstance().setEnabled(false);
            		enable=1;
            	}
            	else{
            		JOptionPane.showMessageDialog(frame, null, "Notice!", JOptionPane.PLAIN_MESSAGE, on);
            		ToolTipManager.sharedInstance().setEnabled(true);
            		enable=0;
            	}
            }	
        }
    }
       
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new BetaGo().setVisible(true);
        });
    }
}