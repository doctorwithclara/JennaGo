import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ToolTipManager;

public class DoctorGo extends JFrame{    
    public static int i, j, x, y, count, check=0, port;
    public static int board[][] = new int[19][19];
    public static ServerSocket sSocket;
    public static String serverName;
    public static Socket client;
    public static DataInputStream in;
    public static DataOutputStream out;
    public static JTextArea area = new JTextArea(7,26);
    public static JTextField field = new JTextField(13);
    JToggleButton clara[][] = new JToggleButton[19][19]; 
    ImageIcon JennaGo = new ImageIcon(getClass().getResource("JennaGo0.png"));
    ImageIcon JennaGo1 = new ImageIcon(getClass().getResource("JennaGo1.png")); //Black->Client
    ImageIcon JennaGo2 = new ImageIcon(getClass().getResource("JennaGo2.png")); //White->Server
    ImageIcon Foul = new ImageIcon(getClass().getResource("Foul.png"));
    
    public DoctorGo() {
        Jenna();
    }
    
    public void Jenna(){
    	JFrame f = new JFrame("Client Console");
    	f.setSize(450, 400);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));
        Font font = new Font(null, Font.TYPE1_FONT, 17);
        Font font1 = new Font(null, Font.BOLD, 21);
        JLabel label1 = new JLabel("IP address for Server :");
        label1.setFont(font);
        f.add(label1);
        
        JTextField ip = new JTextField(9);
        ip.setForeground(Color.BLUE);
        ip.setFont(font);
        ip.addActionListener((ActionEvent e) -> {
            JTextField t = (JTextField)e.getSource();
			serverName=t.getText();
			area.append(" Set ip for Server to : "+serverName+"\n");
        });
        f.add(ip);
        
        JLabel label2 = new JLabel("Port for Connection :");
        label2.setFont(font);
        f.add(label2);
        
        JButton connect = new JButton("Connect");
        
        JTextField pt = new JTextField(3);
        pt.setForeground(Color.RED);
        pt.setFont(font);
        pt.addActionListener((ActionEvent e) -> {
            JTextField t = (JTextField)e.getSource();
			port=Integer.parseInt(t.getText());
			area.append(" Set port to : "+port+"\n");
			connect.setToolTipText(" Connect for server on port "+port);
        });
        f.add(pt);
        
        ToolTipManager.sharedInstance().setInitialDelay(0);
        
		area.setBackground(Color.BLACK);
        area.setForeground(Color.WHITE);
        area.setFocusable(false);
        area.setFont(font);
        f.add(new JScrollPane(area));
        
        connect.setFont(font);
        connect.addActionListener((ActionEvent e) -> {
        	if(serverName==null||serverName.length()==0){
        		JFrame frameS= new JFrame("Error");
                JOptionPane.showMessageDialog(frameS,"Invalid ip address for Server!","ERROR!",JOptionPane.ERROR_MESSAGE);
        	}
        	else if(port<2000||port>=10000){
        		JFrame frameS= new JFrame("Error");
                JOptionPane.showMessageDialog(frameS,"Invalid port number for Connection!"
                +"\n(valid port: 2000~9999)","ERROR!",JOptionPane.ERROR_MESSAGE);
        	}
        	else{
        	    try {
        		    area.append(" Connecting to "+serverName+" on port "+port+"\n");
        		    client = new Socket(serverName, port);
        		    out = new DataOutputStream(client.getOutputStream());
        		    out.writeUTF(" Just connected to "+client.getRemoteSocketAddress()+"\n");
                    //MyJenna();
                    in = new DataInputStream(client.getInputStream());
                    area.append(in.readUTF());
                }
        	    catch(IOException i){
        	        area.append(" IOException : Check Connection!\n");
                }
        	}
        });
        f.add(connect);
        
        field.setFont(font1);
        field.setBackground(Color.BLACK);
        field.setForeground(Color.WHITE);
        field.setText(" ");
        field.addActionListener((ActionEvent e) -> {
            JTextField t = (JTextField)e.getSource();
			try {
				//out = new DataOutputStream(client.getOutputStream());
				out.writeUTF(" Client :"+ t.getText()+"\n");
				area.append(" Client :"+ t.getText()+"\n");
				area.append(in.readUTF());
				//in = new DataInputStream(client.getInputStream());
			} catch (Exception e1) {
				area.append(" OutputStream Error : Check Connection!\n");
			}
            t.setText(" ");
        });
        f.add(field);
        f.setVisible(true);
    }

    public void MyJenna() {
        setTitle("DOCTOR GO!");
        setSize(1000, 1025);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        
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
        
        for(i=0;i<19;i++){
            for(j=0;j<19;j++){
            clara[i][j] = new JToggleButton(JennaGo);
            clara[i][j].setSelectedIcon(JennaGo1);
            clara[i][j].putClientProperty("column", i);
            clara[i][j].putClientProperty("row", j);
            clara[i][j].setToolTipText("("+Integer.toString(i-1)+","+Integer.toString(j-1)+")");
            clara[i][j].setBorder(null);
            clara[i][j].addKeyListener(new Listen());
            clara[i][j].setBounds(i*50+20,j*50+20,50,50);
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
                board[x][y]=1;//Client->1
                
				try {
					out = new DataOutputStream(client.getOutputStream());
					out.writeUTF(Integer.toString(x)+"&"+Integer.toString(y));
					in = new DataInputStream(client.getInputStream());
	                area.append("Server: "+in.readUTF()+"\n");
	                String [] str= in.readUTF().split("&");
	                if(str.length==2){
	                	x = Integer.parseInt(str[0]);
	                	y = Integer.parseInt(str[1]);
	                }
				} catch (IOException j) {
					area.append(" IOException : Check Connection!\n");
				}
				
				
				
            });
            }
        }
    }
   
    public void Win(int k) throws IOException{
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
        client.close();
        System.exit(0);
    }
    
    public class Listen extends KeyAdapter{
    	int enable=0;
    	JFrame frame = new JFrame("Message");
    	ImageIcon off = new ImageIcon(getClass().getResource("TipOff.png"));
    	ImageIcon on = new ImageIcon(getClass().getResource("TipOn.png"));
    	
    	public void keyPressed (KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_F1);
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
            new DoctorGo();
        });
    }
}