import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Window extends JFrame{

    public static JTextArea area = new JTextArea(16,36);
    public static JTextField field = new JTextField(25);
    public static JLabel lab = new JLabel(">> ");
    public static JFrame f = new JFrame();
   
    public static void MyJenna(){
        f.setTitle("Terminal(Developer)");
        f.setSize(500, 430);
        f.setDefaultCloseOperation(HIDE_ON_CLOSE);
        f.setLayout(new FlowLayout(FlowLayout.CENTER,2,13));
        f.setLocationRelativeTo(area);
        f.setResizable(false);
        f.setVisible(false);
        
        Font font1 = new Font(null , Font.TRUETYPE_FONT, 15);
        Font font2 = new Font(null , Font.BOLD, 20);
        
        area.setBackground(Color.BLACK);
        area.setForeground(Color.GREEN);
        area.setFont(font1);
        area.setFocusable(false);
        field.setBackground(Color.BLACK);
        field.setForeground(Color.GREEN);
        field.setFont(font2);
        lab.setFont(font2);
        
        f.add(new JScrollPane(area));
        f.add(lab);
        f.add(field);
        
        field.addActionListener((ActionEvent e) -> {
            JTextField t = (JTextField)e.getSource();
            switch(t.getText()){
                case "count": moveCount(DoctorGo.count); break;
                case "help": area.append(">>For more information on a specific command, ask the Doctor!\n");
                area.append("count\tShow the current move count\n");
                area.append("exit\tClose the Terminal\n");
                area.append("kill\tTerminate the Process\n"); break;
                case "exit": f.setVisible(false); break;
                case "kill": System.exit(0);
                default: area.append(">>'"+t.getText()+"' is not recognized as an internal command\n");
            }
            t.setText("");
        });
    }
    
    public static void moveCount(int count){
        area.append(">> MoveCount: "+count+"\n");
    }
}