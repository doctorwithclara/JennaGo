import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JTextField;

public class TimerGo implements ActionListener{
    
    public static Timer t;
    public static JTextField field = new JTextField(3);
    public static java.awt.Frame clock = new java.awt.Frame();
    private static Font font = new Font(null , Font.TRUETYPE_FONT, 100);
    private static int count;
    private static ImageIcon Time = new ImageIcon(BetaGo.class.getResource("Time.png"));
    
    TimerGo() {
        t = new Timer(1000,this);
        t.start();
     }
    
    public static void Timer5() {
        count=15;
        TimerGo time = new TimerGo();
        clock.setTitle("Time");
        clock.setSize(300, 210);
        clock.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 22));
        clock.setResizable(false);
        clock.setVisible(true);
        clock.setFocusable(false);
        clock.setLocation(((int)BetaGo.width-1290)/2+30, (int)BetaGo.height/2-450);
        field.setFont(font);
        field.setForeground(Color.RED);
        field.setBackground(Color.BLACK);
        field.setFocusable(false);
        field.setText(" 15:00");
        clock.add(field);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == t) {
            field.setText(null);
            count--;
            field.setText(" "+Integer.toString(count)+":00");
            if(count==0 && BetaGo.end==0){
                clock.dispose();
                JFrame frame= new JFrame();
                JOptionPane.showMessageDialog(frame, null, "GAME OVER", JOptionPane.PLAIN_MESSAGE, Time);
                System.exit(0);
            }
        }
    }
}