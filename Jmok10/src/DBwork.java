import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

public class DBwork implements ActionListener{
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/jmok?useUnicode=yes&amp;characterEncoding=UTF-8&amp;autoReconnect=true";
	private static final String TABLE = "jmok";
	private static final String TABLE2 = "move";
	
	private static final String USER = "root";//"hh";
	private static final String PASS = "1234";//"1111";CL
	private static String sql = null;
	private static String names = null;
	private static String username = null;
	private static String yourname = null;
	
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	
	private static int x, y, count, mcount;
	
	public static void init(){
		x=0;
		y=0;
		count=0;
	}
	
	public static void set19(){
		x=19;
		y=19;
	}

	public static String checkName(){
    	try{
    		while(true){
        		username = JOptionPane.showInputDialog(null, Jmok.Icon, "Login", JOptionPane.PLAIN_MESSAGE);
        		
        		if(username==null||username.length()==0){
        			if(Jmok.isStarted==0)
        				System.exit(0);
        		}
        			
        		else if(username.length()>=20){ //name -> VARCHAR(20)
        			JOptionPane.showMessageDialog(null, "The Username should be less than 15 characters", "Try Again", JOptionPane.ERROR_MESSAGE);
        			continue;
        		}
        		else if(username.equals("jen")){
        			JOptionPane.showMessageDialog(null, "The Username is already Taken...", "Try Again", JOptionPane.ERROR_MESSAGE);
            		continue;		
        		}
        		
    			Class.forName("com.mysql.jdbc.Driver"); //Register JDBC driver

                conn = DriverManager.getConnection(DB_URL,USER,PASS); //Open a connection
    			stmt = conn.createStatement();
    			
    		    sql = "SELECT * FROM "+TABLE+" WHERE name = '"+username+"'"; //Execute a query
    		    rs = stmt.executeQuery(sql);
    			if(rs.next() == false)
    				break;
    			else
    				JOptionPane.showMessageDialog(null, "The Username is already Taken...", "Try Again", JOptionPane.ERROR_MESSAGE);
    		}
    		}catch(SQLException se){
    			JOptionPane.showMessageDialog(null, "Cannot connect to mysql server...", "mySQL Error", JOptionPane.ERROR_MESSAGE);
    			Jmok.isOffline = 1;
    			//Handle errors for JDBC    
    			se.printStackTrace();
	        }catch(Exception ed){
	        	JOptionPane.showMessageDialog(null, "Cannot find mysql driver...", "Driver Error", JOptionPane.ERROR_MESSAGE);
	        	//Handle errors for Class.forName
	        	JOptionPane.showMessageDialog(null, "Download JDBC driver from \nhttps://dev.mysql.com/downloads/connector/j/", "Install Driver", JOptionPane.ERROR_MESSAGE);
	        	ed.printStackTrace();
	        	Jmok.isOffline = 1;
	        }
	 	    finally{
	            //finally block used to close resources
	            try{
	            	if(stmt!=null)
	            		stmt.close();
	            }catch(SQLException se2){}
	            //nothing to do
	            try{
	                if(conn!=null)
	                conn.close();
	            }catch(SQLException se){
	                se.printStackTrace();
	        }
	 	}
    	return username;
    }
	
	public static boolean updateMove(){
    	try{
    		Class.forName("com.mysql.jdbc.Driver"); //Register JDBC driver

            conn = DriverManager.getConnection(DB_URL,USER,PASS); //Open a connection
			stmt = conn.createStatement();
			
			sql = "SELECT move FROM "+TABLE2; //Execute a query
		    rs = stmt.executeQuery(sql);
		    
		    mcount=0;
			while(rs.next()){
				Idea.moves[mcount] = rs.getInt("move");
				System.out.println(Idea.moves[mcount]);
				mcount++;
		    }
			if(mcount!=12){
				return false;
			}
    		}catch(SQLException se){
    			JOptionPane.showMessageDialog(null, "Cannot connect to mysql server...", "mySQL Error", JOptionPane.ERROR_MESSAGE);
    			//Handle errors for JDBC    
    			se.printStackTrace();
    			return false;
	        }catch(Exception ed){
	        	//Handle errors for Class.forName
	        	ed.printStackTrace();
	        	return false;
	        }
	 	    finally{
	            //finally block used to close resources
	            try{
	            	if(stmt!=null)
	            		stmt.close();
	            }catch(SQLException se2){}
	            //nothing to do
	            try{
	                if(conn!=null)
	                conn.close();
	            }catch(SQLException se){
	                se.printStackTrace();
	        }
	 	}
    	return true;
    }
	
	public static void findWhite(){
		Jmok.opponents.clear(); //remove all
		Jmok.opponents.add("AI");//add AI by default
        
    	try{
       			Class.forName("com.mysql.jdbc.Driver"); //Register JDBC driver

                conn = DriverManager.getConnection(DB_URL,USER,PASS); //Open a connection
    			stmt = conn.createStatement();
    			
    		    sql = "SELECT name FROM "+TABLE+" WHERE count = -1 AND remark = 'jen'"; //Execute a query
    		    rs = stmt.executeQuery(sql);
    			while(rs.next()){
    				names = rs.getString("name");
    				Jmok.opponents.add(names);
    		    }
    		}catch(SQLException se){
    			JOptionPane.showMessageDialog(null, "Cannot connect to mysql server...", "mySQL Error", JOptionPane.ERROR_MESSAGE);
    			//Handle errors for JDBC    
    			se.printStackTrace();
	        }catch(Exception ed){
	        	//Handle errors for Class.forName
	        	ed.printStackTrace();
	        }
	 	    finally{
	            //finally block used to close resources
	            try{
	            	if(stmt!=null)
	            		stmt.close();
	            }catch(SQLException se2){}
	            //nothing to do
	            try{
	                if(conn!=null)
	                conn.close();
	            }catch(SQLException se){
	                se.printStackTrace();
	        }
	 	}
    }
	
	public static String showBlack(){
    	try{
    		Class.forName("com.mysql.jdbc.Driver"); //Register JDBC driver
    		conn = DriverManager.getConnection(DB_URL,USER,PASS); //Open a connection
    		stmt = conn.createStatement();
    		
    		sql = "INSERT INTO "+TABLE+" VALUES('"+Jmok.name[Jmok.I]+"', "+(-1)+", "+x+", "+y+", '"+Jmok.name[Jmok.You]+"')";
    	
        	stmt.executeUpdate(sql);
        
    		}catch(SQLException se){
    			JOptionPane.showMessageDialog(null, "Cannot connect to mysql server...", "mySQL Error", JOptionPane.ERROR_MESSAGE);
    			//Handle errors for JDBC    
    			se.printStackTrace();
	        }catch(Exception ed){
	        	//Handle errors for Class.forName
	        	ed.printStackTrace();
	        }
	 	    finally{
	            //finally block used to close resources
	            try{
	            	if(stmt!=null)
	            		stmt.close();
	            }catch(SQLException se2){}
	            //nothing to do
	            try{
	                if(conn!=null)
	                conn.close();
	            }catch(SQLException se){
	                se.printStackTrace();
	        }
	 	}
    	return username;
    }

	
	public static String findBlack(){
    	try{
    		Class.forName("com.mysql.jdbc.Driver"); //Register JDBC driver
    		conn = DriverManager.getConnection(DB_URL,USER,PASS); //Open a connection
			stmt = conn.createStatement();
			
			sql = "INSERT INTO "+TABLE+" VALUES('"+Jmok.name[Jmok.I]+"', "+(-1)+", "+x+", "+y+", 'jen')";
 	    	stmt.executeUpdate(sql);
 	    	
    		while(true){
    			sql = "SELECT name FROM " + TABLE + " WHERE count = -1 AND remark = '"+Jmok.name[Jmok.I]+"'";
				rs = stmt.executeQuery(sql);
						
				if(rs.next() == true) {
					yourname=rs.getString("name");
					break;
				}
				Thread.sleep(500); //polling
		    }
    		
    		}catch(SQLException se){
    			JOptionPane.showMessageDialog(null, "Cannot connect to mysql server...", "mySQL Error", JOptionPane.ERROR_MESSAGE);
    			//Handle errors for JDBC    
    			se.printStackTrace();
	        }catch(Exception ed){
	        	//Handle errors for Class.forName
	        	ed.printStackTrace();
	        }
	 	    finally{
	            //finally block used to close resources
	            try{
	            	if(stmt!=null)
	            		stmt.close();
	            }catch(SQLException se2){}
	            //nothing to do
	            try{
	                if(conn!=null)
	                conn.close();
	            }catch(SQLException se){
	                se.printStackTrace();
	        }
	    }
    	cleanDB(Jmok.name[Jmok.I], -1);
    	cleanDB(yourname, -1);
    	return yourname;
    }

    public static void Resign(){
    	try{
    		Class.forName("com.mysql.jdbc.Driver"); //Register JDBC driver
    		conn = DriverManager.getConnection(DB_URL,USER,PASS); //Open a connection
    		stmt = conn.createStatement();
    		
    		sql = "INSERT INTO "+TABLE+" VALUES('"+Jmok.name[Jmok.I]+"', "+count+", 19, 0, 'resign')";
    	
        	stmt.executeUpdate(sql);
        
    		}catch(SQLException se){
    			JOptionPane.showMessageDialog(null, "Cannot connect to mysql server...", "mySQL Error", JOptionPane.ERROR_MESSAGE);
    			//Handle errors for JDBC    
    			se.printStackTrace();
	        }catch(Exception ed){
	        	//Handle errors for Class.forName
	        	ed.printStackTrace();
	        }
	 	    finally{
	            //finally block used to close resources
	            try{
	            	if(stmt!=null)
	            		stmt.close();
	            }catch(SQLException se2){}
	            //nothing to do
	            try{
	                if(conn!=null)
	                conn.close();
	            }catch(SQLException se){
	                se.printStackTrace();
	        }
	 	}
    }
    
    public static void cleanDB(String erase){
    	try{
			Class.forName("com.mysql.jdbc.Driver"); //Register JDBC driver

            conn = DriverManager.getConnection(DB_URL,USER,PASS); //Open a connection
			stmt = conn.createStatement();
			
		    sql = "DELETE FROM "+TABLE+" WHERE name = '"+erase+"'";
	 	    stmt.executeUpdate(sql); //Execute a query
	 	    
	 	    System.out.println(erase+" -> Clean DB : Successful!");
	 	    }catch(SQLException se){
	 	    	JOptionPane.showMessageDialog(null, "Cannot connect to mysql server...", "mySQL Error", JOptionPane.ERROR_MESSAGE);
	 	    	//Handle errors for JDBC    
	 	    	se.printStackTrace();
	        }catch(Exception ed){
	        	//Handle errors for Class.forName
	        	ed.printStackTrace();
	        }
	 	    finally{
	            //finally block used to close resources
	            try{
	            	if(stmt!=null)
	            		stmt.close();
	            }catch(SQLException se2){}
	            //nothing to do
	            try{
	                if(conn!=null)
	                conn.close();
	            }catch(SQLException se){
	                se.printStackTrace();
	        }
	 	}
    }
    
    public static void cleanDB(String erase, int c){
    	try{
			Class.forName("com.mysql.jdbc.Driver"); //Register JDBC driver

            conn = DriverManager.getConnection(DB_URL,USER,PASS); //Open a connection
			stmt = conn.createStatement();
			
		    sql = "DELETE FROM "+TABLE+" WHERE name = '"+erase+"' AND count = "+c;
	 	    stmt.executeUpdate(sql); //Execute a query
	 	    
	 	    System.out.println(erase+" -> Clean DB("+c+") : Successful!");
	 	    }catch(SQLException se){
	 	    	JOptionPane.showMessageDialog(null, "Cannot connect to mysql server...", "mySQL Error", JOptionPane.ERROR_MESSAGE);
    		//Handle errors for JDBC    
	 	    se.printStackTrace();
	        }catch(Exception ed){
	        //Handle errors for Class.forName
	        ed.printStackTrace();
	        }
	 	    finally{
	            //finally block used to close resources
	            try{
	            	if(stmt!=null)
	            		stmt.close();
	            }catch(SQLException se2){}
	            //nothing to do
	            try{
	                if(conn!=null)
	                conn.close();
	            }catch(SQLException se){
	                se.printStackTrace();
	        }
	 	}
    }
    
    public static void useDB(){
    	try{
    		Class.forName("com.mysql.jdbc.Driver"); //Register JDBC driver

            conn = DriverManager.getConnection(DB_URL,USER,PASS); //Open a connection
			stmt = conn.createStatement();
			
			sql = "INSERT INTO "+TABLE+" VALUES('"+Jmok.name[Jmok.I]+"', "+count+", "+x+", "+y+", null)";
 	    	stmt.executeUpdate(sql);

			if(Jmok.isGo==0)
                Idea.scanBoard();
 	    	
 	    	if(Jmok.end==0){
 	    		
 	   		    while(true){
 		            sql = "SELECT x, y FROM " + TABLE + " WHERE count >= " + count + " AND name = '"+Jmok.name[Jmok.You]+"'";
 					rs = stmt.executeQuery(sql);
 							
 					if(rs.next() == true) {
 						x = rs.getInt("x");
 						y = rs.getInt("y");
 						if(x==19){
 							if(y==19){
 	 							Jmok.JMOK.setVisible(false);
 	 							Jmok.clock.t.stop();
 	 							Jmok.Win(6);//OK
 	 						}
 	 						else if(y==0){
 	 							Jmok.JMOK.setVisible(false);
 	 							Jmok.clock.t.stop();
 	 							DBwork.cleanDB(Jmok.name[Jmok.You]);
 	 							Jmok.Win(7);//OK
 							}
 						}
 						break;
 					}
 					Thread.sleep(500); //polling
 		        }
 		        
 	   		    Jmok.clara[x][y].setIcon(Jmok.Jmoks[Jmok.You]);
 		        Jmok.clara[x][y].setRolloverIcon(null);
 		        
 		        if(Jmok.board[x+1][y+1]!=0){
 		        	Jmok.JMOK.setVisible(false);
 		        	Jmok.clock.t.stop();
 		        	Jmok.Win(4);
 		        }
 		        else
 		        	Jmok.board[x+1][y+1]=Jmok.You;
 	    	}
	        rs.close();
            }catch(ArrayIndexOutOfBoundsException ae){
    		//Nothing to do
    		}catch(SQLException se){
    			JOptionPane.showMessageDialog(null, "Cannot connect to mysql server...", "mySQL Error", JOptionPane.ERROR_MESSAGE);
    		//Handle errors for JDBC    
	 	    se.printStackTrace();
	        }catch(Exception ed){
	        //Handle errors for Class.forName and ArrayOutOfBoundsException
	        ed.printStackTrace();
	        }
	 	    finally{
	            //finally block used to close resources
	            try{
	            	if(stmt!=null)
	            		stmt.close();
	            }catch(SQLException se2){}
	            //nothing to do
	            try{
	                if(conn!=null)
	                conn.close();
	            }catch(SQLException se){
	                se.printStackTrace();
	                //Jmok.myTurn=1;
	        }
	        count++;
	 	}
    }
    
	public void actionPerformed(ActionEvent e){
		JToggleButton b = (JToggleButton) e.getSource();
        x = (int)b.getClientProperty("column");
        y = (int)b.getClientProperty("row");
        count++; //before the query
        Jmok.movecount.setText(Integer.toString(count));
        
        if(Jmok.board[x+1][y+1]!=0){
        	Jmok.board[0][0]=4;
        	Jmok.end=1;
        }
        else{
        	Jmok.board[x+1][y+1]=Jmok.I;
        	Jmok.clara[x][y].setIcon(Jmok.Jmoks[Jmok.I]);
        }
        
        if(Jmok.name[Jmok.You].equals("AI")){
        	Idea.aIntelligence();
        	Jmok.clara[Idea.getX()-1][Idea.getY()-1].setIcon(Jmok.Jmoks[Jmok.You]);
        	Jmok.clara[Idea.getX()-1][Idea.getY()-1].setRolloverIcon(null);
        }
        else{
        	useDB();
        }
        
        if(Jmok.end==0&&Jmok.isGo==0)
        	Idea.scanBoard();
	}
}