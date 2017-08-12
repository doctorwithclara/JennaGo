import java.awt.*;
import javax.swing.*;

public class Window extends JFrame{

    public static JTextArea area = new JTextArea(30,24);
    public static JFrame f = new JFrame();
    public static ImageIcon Win1 = new ImageIcon(BetaGo.class.getResource("Win1.png"));
    public static ImageIcon Win2 = new ImageIcon(BetaGo.class.getResource("Win2.png"));
   
    public static void MyJenna(){
        f.setTitle("Developer Console");
        f.setSize(355, 680);
        f.setLocation(((int)BetaGo.width-1290)/2, (int)BetaGo.height/2-208);
        f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        f.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 22));
        f.setResizable(false);
        f.setVisible(true);
        
        Font font = new Font(null , Font.TRUETYPE_FONT, 15);
        Font font2 = new Font(null , Font.BOLD, 15);
        
        f.add(new JScrollPane(area));
        area.setBackground(Color.BLACK);
        area.setForeground(Color.GREEN);
        area.setFocusable(false);
        area.setFont(font);
    }
    
    public static void showMove(int move[][][]){
        for(int i=0;i<19;i++){
            area.append(">>");
            for(int j=0;j<19;j++){
                area.append(move[i][j][0]+" ");
            }
            area.append("\n");
        }
    }
    
    public static void showMax(int move[][][], int x, int y){
        area.append(">>Max_final: ("+x+", "+y+")\n>>");
        for(int i=1;i<9;i++)
            area.append(move[x][y][i]+" ");
        area.append("\n");
    }
    
    public static void Win(int c){
        if(c==1){
            BetaGo.end=1;
            TimerGo.clock.dispose();
            JFrame frame= new JFrame();
            JOptionPane.showMessageDialog(frame, null, "GAME OVER", JOptionPane.PLAIN_MESSAGE, Win2);
            System.exit(0);
        }
        else{
            BetaGo.end=1;
            TimerGo.clock.dispose();
            JFrame frame= new JFrame();
            JOptionPane.showMessageDialog(frame, null, "GAME OVER", JOptionPane.PLAIN_MESSAGE, Win1);
            System.exit(0);
        }
    }
    
    public static void MoveClean(){
        area.append(">>Move Clean : Successful\n");
    }
    
    public static void MoveCheck(){
        area.append(">>Move Check : Successful\n");
    }
    
    public static void MoveConfirm(){
        area.append(">>Move Confirm : Successful\n");
    }
    
    public static void End(){
        area.append(">>The Game is About to END!\n");
    }
    
    public static void MoveCount(int count){
        area.append(">>--------------------------------- MoveCount: "+count+"\n");
    }
    
    public static void MaxDet(int x, int y){
        area.append(">>Candidate = X:"+x+" Y:"+y+"\n");
    }
}