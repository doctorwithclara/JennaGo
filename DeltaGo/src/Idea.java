public class Idea extends Window{
    
    public static int x, y, z, i, j, i1, j1, i2, j2, i3, j3, h, max=0, length;
    public static int move[][][][] = new int [21][21][9][3];
    //1 2 3
    //4   5
    //6 7 8  0-> vote
    public static void set(int i$, int j$){
    	i1=i$;
        j1=j$;
        length=0;
    }
    
    public static void mesure(int i$, int j$, int k){
    	set(i$, j$+1);
        while(true){
        	if(DoctorGo.board[i1][j1]==k){
                length++;
                if(length>4)
            		DoctorGo.Win(k);
                j1++;   
                }
            else{
            	move[i1][j1][5][k] = length;
            	break;
            }
        }
        set(i$, j$-1);
        while(true){
        	if(DoctorGo.board[i1][j1]==k){
                length++;
                if(length>4)
            		DoctorGo.Win(k);
                j1--;   
                }
            else{
            	move[i1][j1][4][k] = length;
            	break;
            }
        }
        set(i$-1, j$);
        while(true){
        	if(DoctorGo.board[i1][j1]==k){
                length++;
                if(length>4)
            		DoctorGo.Win(k);
                i1--;   
                }
            else{
            	move[i1][j1][2][k] = length;
            	break;
            }
        }
        set(i$+1, j$);
        while(true){
        	if(DoctorGo.board[i1][j1]==k){
                length++;
                if(length>4)
            		DoctorGo.Win(k);
                i1++;   
                }
            else{
            	move[i1][j1][7][k] = length;
            	break;
            }
        }
        set(i$-1, j$-1);
        while(true){
        	if(DoctorGo.board[i1][j1]==k){
                length++;
                if(length>4)
            		DoctorGo.Win(k);
                i1--;
                j1--;
                }
            else{
            	move[i1][j1][1][k] = length;
            	break;
            }
        }
        set(i$+1, j$+1);
        while(true){
        	if(DoctorGo.board[i1][j1]==k){
                length++;
                if(length>4)
            		DoctorGo.Win(k);
               	i1++;
                j1++;
                }
            else{
            	move[i1][j1][8][k] = length;
            	break;
            }
        }
        set(i$+1, j$-1);
        while(true){
        	if(DoctorGo.board[i1][j1]==k){
                length++;
                if(length>4)
            		DoctorGo.Win(k);
            	i1++;
                j1--;
                }
            else{
            	move[i1][j1][6][k] = length;
            	break;
            }
        }
        set(i$, j$);
        while(true){
        	if(DoctorGo.board[i1][j1]==k){
                length++;
                if(length>4)
            		DoctorGo.Win(k);
            	i1--;
                j1++;
                }
            else{
            	move[i1][j1][3][k] = length;
            	break;
            }
        }
    }
    
    public static void scan(int x2, int y2){
    	DoctorGo.board[x2][y2]=1;
        for(i2=1;i2<=19;i2++){
            for(j2=1;j2<=19;j2++){
                if(DoctorGo.board[i2][j2]==1)
                    mesure(i2, j2, 1);
            }
        }
        DoctorGo.board[x2][y2]=2;
        for(i2=1;i2<=19;i2++){
            for(j2=1;j2<=19;j2++){
                if(DoctorGo.board[i2][j2]==2)
                    mesure(i2, j2, 2);
            }
        }
        DoctorGo.board[x2][y2]=0;
    }
    
    public static void intelligence(int i$, int j$, int k){
    	switch(move[i$][j$][1][k]){
    	case 5: move[i$][j$][0][k]+=150; break;     
    	case 4: move[i$][j$][0][k]+=100; break;
    	case 3: move[i$][j$][0][k]+=50; break;
    	case 2: move[i$][j$][0][k]+=10; break;
    	case 1: move[i$][j$][0][k]+=1; break;
    	}
    	switch(move[i$][j$][2][k]){
	    case 5: move[i$][j$][0][k]+=150; break;
	    case 4: move[i$][j$][0][k]+=100; break;
	    case 3: move[i$][j$][0][k]+=50; break;
	    case 2: move[i$][j$][0][k]+=10; break;
	    case 1: move[i$][j$][0][k]+=1; break;
	    }
    	switch(move[i$][j$][3][k]){
	    case 5: move[i$][j$][0][k]+=150; break;    
	    case 4: move[i$][j$][0][k]+=100; break;
	    case 3: move[i$][j$][0][k]+=50; break;
	    case 2: move[i$][j$][0][k]+=10; break;
	    case 1: move[i$][j$][0][k]+=1; break;
	    }
    	switch(move[i$][j$][4][k]){
	    case 5: move[i$][j$][0][k]+=150; break;
	    case 4: move[i$][j$][0][k]+=100; break;
	    case 3: move[i$][j$][0][k]+=50; break;
	    case 2: move[i$][j$][0][k]+=10; break;
	    case 1: move[i$][j$][0][k]+=1; break;
	    }
    	switch(move[i$][j$][5][k]){
    	case 5: move[i$][j$][0][k]+=150; break;
    	case 4: move[i$][j$][0][k]+=100; break;
    	case 3: move[i$][j$][0][k]+=50; break;
    	case 2: move[i$][j$][0][k]+=10; break;
    	case 1: move[i$][j$][0][k]+=1; break;
    	}
    	switch(move[i$][j$][6][k]){
	    case 5: move[i$][j$][0][k]+=150; break;
	    case 4: move[i$][j$][0][k]+=100; break;
	    case 3: move[i$][j$][0][k]+=50; break;
	    case 2: move[i$][j$][0][k]+=10; break;
	    case 1: move[i$][j$][0][k]+=1; break;
	    }
    	switch(move[i$][j$][7][k]){
	    case 5: move[i$][j$][0][k]+=150; break;     
	    case 4: move[i$][j$][0][k]+=100; break;
	    case 3: move[i$][j$][0][k]+=50; break;
	    case 2: move[i$][j$][0][k]+=10; break;
	    case 1: move[i$][j$][0][k]+=1; break;
	    }
    	switch(move[i$][j$][8][k]){
	    case 5: move[i$][j$][0][k]+=150; break;
	    case 4: move[i$][j$][0][k]+=100; break;
	    case 3: move[i$][j$][0][k]+=50; break;
	    case 2: move[i$][j$][0][k]+=10; break;
	    case 1: move[i$][j$][0][k]+=1; break;
	    }
    }
    
    public static void AI(){
        for(i=1;i<=19;i++){
            for(j=1;j<=19;j++){
                if(DoctorGo.board[i][j]!=0){
                    move[i][j][0][0]=0;
                }
                else{
                	scan(i,j);
                    intelligence(i,j,1);
                    intelligence(i,j,2);
                    move[i][j][0][0] = move[i][j][0][1]+move[i][j][0][2];
                }
            }
        }
        
        for(i=1;i<=19;i++){
            for(j=1;j<=19;j++){
            	if(move[i][j][0][0]>max){
            		max=move[i][j][0][0];
            		x=i;
            		y=j;
            	}
            }
        }
        
        max=0;
        show();
        
        for(i=1;i<=19;i++){
            for(j=1;j<=19;j++){
            	for(h=0;h<9;h++){
            		move[i][j][h][0]=0;
            		move[i][j][h][1]=0;
            		move[i][j][h][2]=0;
            	}
            }
        }
        
    }
    public static void show(){
    	for(i3=1;i3<=19;i3++){
    		for(j3=1;j3<=19;j3++){
    			Window.area.append(Integer.toString(move[i3][j3][0][0]));
    		}
    		Window.area.append("\n");
    	}
    }
}