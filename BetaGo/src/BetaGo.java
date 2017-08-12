import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class BetaGo extends JFrame{    
    public static int i, j, x, y, end=0, start=0;
    public static int board[][] = new int[19][19];    
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static double width = screenSize.getWidth();
    public static double height = screenSize.getHeight();
    
    public BetaGo() {
        Jenna();
    }

    public void Jenna() {
        
        ImageIcon Rule = new ImageIcon(getClass().getResource("Rule.png"));
        ImageIcon JennaGo = new ImageIcon(getClass().getResource("JennaGo0.png"));
        ImageIcon JennaGo1 = new ImageIcon(getClass().getResource("JennaGo2.png"));
        ImageIcon JennaGo2 = new ImageIcon(getClass().getResource("JennaGo1.png"));
        ImageIcon Foul = new ImageIcon(getClass().getResource("Foul.png"));
        
        JFrame opening= new JFrame();
        JOptionPane.showMessageDialog(opening, null, "BetaGo 3.0 (unstable)", JOptionPane.PLAIN_MESSAGE, Rule);
        
        setTitle("BetaGo 3.0");
        setSize(920, 945); //f.setSize(355, 680);clock.setSize(300, 210);
        setLocation(((int)width-1290)/2+370, ((int)height-945)/2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);       
        
        Container c = getContentPane();
        c.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        c.setBackground(new Color(239,228,176));
        
        Font font1 = new Font(null , Font.BOLD, 20);
        Font font2 = new Font(null , Font.BOLD, 25);
        Font font3 = new Font(null , Font.BOLD, 15);
        
        Window.MyJenna();
        
        JToggleButton clara[][] = new JToggleButton[19][19]; 
        
        board[9][9]=2;
        
        for(i=0;i<19;i++){
            for(j=0;j<19;j++){
            clara[i][j] = new JToggleButton(JennaGo);
            clara[i][j].setSelectedIcon(JennaGo1);
            clara[i][j].setPreferredSize(new Dimension(48,48));
            clara[i][j].putClientProperty("column", i);
            clara[i][j].putClientProperty("row", j);
            clara[i][j].setBorder(null);
            clara[i][j].addKeyListener(new Listen());
            c.add(clara[i][j]);
            clara[i][j].addActionListener((ActionEvent e) -> { 
                JToggleButton b = (JToggleButton) e.getSource();
                x = (int)b.getClientProperty("column");
                y = (int)b.getClientProperty("row");
                start=1;
                if(board[x][y]!=0){
                    end=1;
                    TimerGo.clock.dispose();
                    JFrame frame= new JFrame();
                    JOptionPane.showMessageDialog(frame, null, "GAME OVER", JOptionPane.PLAIN_MESSAGE, Foul);
                    System.exit(0);
                }    
                board[x][y]=1;
                Idea.AI();
                board[Idea.x][Idea.y]=2;
                clara[Idea.x][Idea.y].setIcon(JennaGo2);
                TimerGo.Timer5();
            });
            }
        }
        clara[9][9].setIcon(JennaGo2);
    }
    
    public class Listen extends KeyAdapter{
        @Override
    	public void keyPressed (KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_F1);
                Window.MyJenna();
    }}
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new BetaGo().setVisible(true);
        });
    }
}