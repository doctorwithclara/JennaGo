public class Idea extends Window{
    
    public static int x, y, k, i, j, k0, k2, i0, j0, i1, j1, i2, j2, d, multi3_1=0, multi3_2=0, count=0, max=0, future=0;
    public static int move[][][] = new int [19][19][9];
    //1 2 3
    //4   5
    //6 7 8  0-> vote
    
    public static int max(int a, int b){
        return (a>b) ? a:b;
    }
    
    public static void mesure(int i, int j, int k, int c, int move[][][]){
        int length=0;
        i1=i;
        j1=j;
        switch(k){
            case 0: while(true){  //horizontal right
                if(BetaGo.board[i][j1]==c){
                    length++;
                    if(length>4){
                        Win(c);
                        break;
                    }
                    j1++;
                }
                else{
                    try{
                    move[i][j-1][5] = max(move[i][j-1][5],length);
                    move[i][j1][4] = max(move[i][j1][4],length);}
                    catch(ArrayIndexOutOfBoundsException e){
                    } break;
                }
            } break;
            
            case 1: while(true){  //vertical down
                if(BetaGo.board[i1][j]==c){
                    length++;
                    if(length>4){
                        Win(c);
                        break;
                    }
                    i1++;
                }
                else{
                    try{
                    move[i-1][j][7] = max(move[i-1][j][7], length);
                    move[i1][j][2] = max(move[i1][j][2], length);}
                    catch(ArrayIndexOutOfBoundsException e){
                    } break;
                }
            } break;
            
            case 2: while(true){ //right-down
                if(BetaGo.board[i1][j1]==c){
                    length++;
                    if(length>4){
                        Win(c);
                        break;
                    }
                    i1++;
                    j1++;
                }
                else{
                    try{
                    move[i-1][j-1][8] = max(move[i-1][j-1][8], length);
                    move[i1][j1][1] = max(move[i1][j1][1], length);}
                    catch(ArrayIndexOutOfBoundsException e){
                    } break;
                }
            } break;
            
            case 3: while(true){ //left down
                if(BetaGo.board[i1][j1]==c){
                    length++;
                    if(length>4){
                        Win(c);
                        break;
                    }
                    i1++;
                    j1--;
                }
                else{
                    try{
                    move[i-1][j+1][6] = max(move[i-1][j+1][6], length);
                    move[i1][j1][3] = max(move[i1][j1][3], length);}
                    catch(ArrayIndexOutOfBoundsException e){
                    } break;
                }
            } break;
        }
        length=0;
    }
    
    public static void scan(int move[][][]){
        for(i=0;i<19;i++){
            for(j=0;j<19;j++){
                if(BetaGo.board[i][j]==2)
                    mesure(i, j, 0, 2, move);
                    mesure(i, j, 1, 2, move);
                    mesure(i, j, 2, 2, move);
                    mesure(i, j, 3, 2, move);
            }
        }
        
        for(i=0;i<19;i++){
            for(j=0;j<19;j++){
                if(BetaGo.board[i][j]==1)
                    mesure(i, j, 0, 1, move);
                    mesure(i, j, 1, 1, move);
                    mesure(i, j, 2, 1, move);
                    mesure(i, j, 3, 1, move);
            }
        }
    }
    
    public static void AI(){
        
        scan(move);
      
        for(i=0;i<19;i++){
            for(j=0;j<19;j++){
                if(BetaGo.board[i][j]!=0){
                    move[i][j][0]=0;
                    MoveCheck();
                }
                else{
                    for(k=1;k<9;k++){
                        switch (move[i][j][k]) {
                            case 4:
                                switch(k){
                                    case 1:
                                        if(BetaGo.board[i-1][j-1]==2)
                                            move[i][j][0]+=100;
                                        break;
                                    case 2:
                                        if(BetaGo.board[i-1][j]==2)
                                            move[i][j][0]+=100;
                                        break;
                                    case 3:
                                        if(BetaGo.board[i-1][j+1]==2)
                                            move[i][j][0]+=100;
                                        break;
                                    case 4:
                                        if(BetaGo.board[i][j-1]==2)
                                            move[i][j][0]+=100;
                                        break;
                                    case 5:
                                        if(BetaGo.board[i][j+1]==2)
                                            move[i][j][0]+=100;
                                        break;
                                    case 6:
                                        if(BetaGo.board[i+1][j-1]==2)
                                            move[i][j][0]+=100;
                                        break;
                                    case 7:
                                        if(BetaGo.board[i+1][j]==2)
                                            move[i][j][0]+=100;
                                        break;
                                    case 8:
                                        if(BetaGo.board[i+1][j+1]==2)
                                            move[i][j][0]+=100;
                                        break;
                                }
                                move[i][j][0]+=300;
                                break;
                            case 3:
                                try{
                                	d=BetaGo.board[i][j];
                                    switch(k){
                                        case 1:
                                            if(BetaGo.board[i-4][j-4]==0){
                                                move[i][j][0]+=150;
                                                if(BetaGo.board[i-5][j-5]==d||BetaGo.board[i+1][j+1]==d)
                                                    move[i][j][0]+=300;
                                            }
                                            break;
                                        case 2:
                                            if(BetaGo.board[i-4][j]==0){
                                                move[i][j][0]+=150;
                                                if(BetaGo.board[i-5][j]==d||BetaGo.board[i+1][j]==d)
                                                    move[i][j][0]+=300;
                                            }
                                            break;
                                        case 3:
                                            if(BetaGo.board[i-4][j+4]==0){
                                                move[i][j][0]+=150;
                                                if(BetaGo.board[i-5][j+5]==d||BetaGo.board[i+1][j-1]==d)
                                                    move[i][j][0]+=300;
                                            }
                                            break;
                                        case 4:
                                            if(BetaGo.board[i][j-4]==0){
                                                move[i][j][0]+=150;
                                                if(BetaGo.board[i][j-5]==d||BetaGo.board[i][j+1]==d)
                                                    move[i][j][0]+=300;
                                            }
                                            break;
                                        case 5:
                                            if(BetaGo.board[i][j+4]==0){
                                                move[i][j][0]+=150;
                                                if(BetaGo.board[i][j+5]==d||BetaGo.board[i][j-1]==d)
                                                    move[i][j][0]+=300;
                                            }
                                            break;
                                        case 6:
                                            if(BetaGo.board[i+4][j-4]==0){
                                                move[i][j][0]+=150;
                                                if(BetaGo.board[i+5][j-5]==d||BetaGo.board[i-1][j+1]==d)
                                                    move[i][j][0]+=300;
                                            }
                                            break;
                                        case 7:
                                            if(BetaGo.board[i+4][j]==0){
                                                move[i][j][0]+=150;
                                                if(BetaGo.board[i+5][j]==d||BetaGo.board[i-1][j]==d)
                                                    move[i][j][0]+=300;        
                                            }
                                            break;
                                        case 8:
                                            if(BetaGo.board[i+4][j+4]==0){
                                                move[i][j][0]+=150;
                                                if(BetaGo.board[i+5][j+5]==d&&BetaGo.board[i-1][j-1]==d)
                                                    move[i][j][0]+=300;
                                            }
                                            break; 
                                    }
                                }
                                catch(ArrayIndexOutOfBoundsException e){}
                                finally{move[i][j][0]+=move[i][j][k];}
                                break;
                            case 2:
                                d=BetaGo.board[i][j];
                                try{
                                    switch(k){
                                        case 1:
                                        	if(BetaGo.board[i-2][j-2]==0&&BetaGo.board[i+1][j+1]==0){
                                                if(d==1)
                                        		    multi3_1++;
                                                else
                                                	multi3_2++;
                                        	    if(BetaGo.board[i-3][j-3]==d||BetaGo.board[i+2][j+2]==d)
                                        	    	move[i][j][0]+=400;
                                        	}
                                            break;
                                        case 2:
                                            if(BetaGo.board[i-2][j]==0&&BetaGo.board[i+1][j]==0){
                                            	if(d==1)
                                        		    multi3_1++;
                                                else
                                                	multi3_2++;
                                            	if(BetaGo.board[i-3][j]==d||BetaGo.board[i+2][j]==d)
                                                    move[i][j][0]+=400;
                                            }
                                            break;
                                        case 3:
                                            if(BetaGo.board[i-2][j+2]==0&&BetaGo.board[i+1][j-1]==0){
                                            	if(d==1)
                                        		    multi3_1++;
                                                else
                                                	multi3_2++;
                                            	if(BetaGo.board[i-3][j+3]==d||BetaGo.board[i+2][j-2]==d)
                                            	    move[i][j][0]+=400;
                                            }
                                            break;
                                        case 4:
                                            if(BetaGo.board[i][j-2]==0&&BetaGo.board[i][j+1]==0){
                                            	if(d==1)
                                        		    multi3_1++;
                                                else
                                                	multi3_2++;
                                            	if(BetaGo.board[i][j-3]==d||BetaGo.board[i][j+2]==d)
                                            	    move[i][j][0]+=400;
                                            }
                                            break;
                                        case 5:
                                            if(BetaGo.board[i][j+2]==0&&BetaGo.board[i][j-1]==0){
                                            	if(d==1)
                                        		    multi3_1++;
                                                else
                                                	multi3_2++;
                                            	if(BetaGo.board[i][j+3]==d||BetaGo.board[i][j-2]==d)
                                            	    move[i][j][0]+=400;
                                            }
                                            break;
                                        case 6:
                                            if(BetaGo.board[i+2][j-2]==0&&BetaGo.board[i-1][j+1]==0){
                                            	if(d==1)
                                        		    multi3_1++;
                                                else
                                                	multi3_2++;
                                            	if(BetaGo.board[i][j+3]==d||BetaGo.board[i][j-2]==d)
                                        	        move[i][j][0]+=400;
                                            }
                                            break;
                                        case 7:
                                            if(BetaGo.board[i+2][j]==0&&BetaGo.board[i-1][j]==0){
                                            	if(d==1)
                                        		    multi3_1++;
                                                else
                                                	multi3_2++;
                                            	if(BetaGo.board[i+3][j]==d||BetaGo.board[i-2][j]==d)
                                            	    move[i][j][0]+=400;
                                            }
                                            break;
                                        case 8:
                                            if(BetaGo.board[i+2][j+2]==0&&BetaGo.board[i-1][j-1]==0){
                                            	if(d==1)
                                        		    multi3_1++;
                                                else
                                                	multi3_2++;
                                            	if(BetaGo.board[i+3][j+3]==d||BetaGo.board[i-2][j-2]==d)
                                            	    move[i][j][0]+=400;
                                            }
                                            break;
                                    }
                                    if((multi3_1>=2)||(multi3_2>=2))
                                    	move[i][j][0]+=300;
                                    
                                    multi3_1=0;
                                    multi3_2=0;
                                }
                                catch(ArrayIndexOutOfBoundsException e){}
                                finally{move[i][j][0]+=move[i][j][k];}
                                break;
                            default:
                                move[i][j][0]+=move[i][j][k];
                                break;
                        }
                    }
                }   
            }
        }
        
        showMove(move);
        
        for(i=0;i<19;i++){
            for(j=0;j<19;j++){
                if(move[i][j][0]>max){
                x=i;
                y=j;
                max=move[i][j][0];
                MaxDet(i, j);
                }
            }
        }
        
        showMax(move, x, y);
        if(max>=400)
            End();
        max=0;
        MoveConfirm();
        
        for(i=0;i<19;i++){
            for(j=0;j<19;j++){
                for(k=0;k<9;k++)
                    move[i][j][k]=0;
            }
        }
        MoveClean();
        count++;
        MoveCount(count);
    }
}