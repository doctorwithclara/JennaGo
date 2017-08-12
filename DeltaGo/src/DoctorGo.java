import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.ToolTipManager;

public class DoctorGo extends JFrame{    
    public static int i, j, x, y, count, check=0;
    public static int board[][] = new int[21][21];
    JToggleButton clara[][] = new JToggleButton[20][20]; 
    ImageIcon DoctorGo = new ImageIcon(getClass().getResource("DoctorGo.png"));
    ImageIcon JennaGo = new ImageIcon(getClass().getResource("JennaGo0.png"));
    ImageIcon JennaGo1 = new ImageIcon(getClass().getResource("JennaGo1.png")); //Black
    ImageIcon JennaGo2 = new ImageIcon(getClass().getResource("JennaGo2.png")); //White
    ImageIcon Foul = new ImageIcon(getClass().getResource("Foul.png"));
    
    public DoctorGo() {
        Jenna();
    }

    public void Jenna() {
        String [] colors = {"WHITE", "BLACK"};
    	JFrame choice = new JFrame("Opening");
        String color = (String) JOptionPane.showInputDialog(choice, 
        "Which side do you want?", "DoctorGo 1.0", JOptionPane.QUESTION_MESSAGE, DoctorGo, colors, colors[0]);
        
        if(color==null)
        	System.exit(0);
        else if(color.equals(colors[0])){
        	ImageIcon temp = JennaGo1;
            JennaGo1 = JennaGo2;
            JennaGo2 = temp;
            check=1;
        }//1:Human(WHITE) 2:Computer(BLACK)

        setTitle("DOCTOR GO!");
        setSize(1000, 1025);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);       
        
        Container c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(187,137,33));
        
        JLabel [][] mark = new JLabel[19][2];
        for(i=0;i<19;i++){
        	mark[i][0]=new JLabel(String.format("%02d", i));
        	mark[i][1]=new JLabel(String.format("%02d", i));
        	mark[i][0].setBounds(i*50+38,3,15,15);
        	mark[i][1].setBounds(3,i*50+38,15,15);
        	mark[i][0].setForeground(Color.white);
        	mark[i][1].setForeground(Color.white);
        	c.add(mark[i][0]);
        	c.add(mark[i][1]);
        }
        
        ToolTipManager.sharedInstance().setInitialDelay(0);
        
        for(i=1;i<=19;i++){
            for(j=1;j<=19;j++){
            clara[i][j] = new JToggleButton(JennaGo);
            clara[i][j].setSelectedIcon(JennaGo1);
            clara[i][j].putClientProperty("column", i);
            clara[i][j].putClientProperty("row", j);
            clara[i][j].setToolTipText("("+Integer.toString(i-1)+","+Integer.toString(j-1)+")");
            clara[i][j].setBorder(null);
            clara[i][j].addKeyListener(new Listen());
            clara[i][j].setBounds(i*50-30,j*50-30,50,50);
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
                board[x][y]=1;
                Idea.AI();
                board[Idea.x][Idea.y]=2;
                clara[Idea.x][Idea.y].setIcon(JennaGo2);
                count++;
            });
            }
        }
        if(check==1){
        	board[10][10]=2;
        	clara[10][10].setIcon(JennaGo2);
        }
        Window.MyJenna();
    }
   
    public static void Win(int k){
        if(k==1){
            JFrame frame= new JFrame();
            ImageIcon Win1 = new ImageIcon(DoctorGo.class.getResource("Win1.png"));
            JOptionPane.showMessageDialog(frame, null, "GAME OVER", JOptionPane.PLAIN_MESSAGE, Win1);
        }
        else{
            JFrame frame= new JFrame();
            ImageIcon Win2 = new ImageIcon(DoctorGo.class.getResource("Win2.png"));
            JOptionPane.showMessageDialog(frame, null, "GAME OVER", JOptionPane.PLAIN_MESSAGE, Win2);
        }
        System.exit(0);
    }
    
    public class Listen extends KeyAdapter{
    	int enable=0;
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
            new DoctorGo().setVisible(true);
        });
    }
}