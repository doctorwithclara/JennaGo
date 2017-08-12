import java.util.Random;
import java.util.Vector;

public class Idea {
    private static int x, y, i, j, k;
    private static int length, i1, j1;
    private static int m1, m2;
    private static int max = 0;
    
    public static int[] moves = {1000, 1000, 1000, 350, 50, 350, 50, 10, 5, 10, 5, 150};
    private static int[][][] move = new int[21][21][9];
    private static int[][][] colors = new int[21][21][9];
    private static int[][][] multi3 = new int[21][21][9];
    private static Vector<Integer> iV = new Vector<Integer>();
    private static Vector<Integer> jV = new Vector<Integer>();
    
    /*
    moves[0] = move_4_1
    moves[1] = move_4_2
    moves[2] = move_4_3
        
    moves[3] = move_3_1o
    moves[4] = move_3_1s
    moves[5] = move_3_2o
    moves[6] = move_3_2s
        
    moves[7] = move_2_1o
    moves[8] = move_2_1s
    moves[9] = move_2_2o
    moves[10] = move_2_2s
        
    moves[12] = move_multi3	
    */

    public static int getX(){
    	return x;
    }
    
    public static int getY(){
    	return y;
    }
    
    private static int max(int a, int b) {
        return (a>b) ? a:b;
    }
    
    private static void initialize3(){
    	length = 0;
        i1 = i;
        j1 = j;
    }
    
    private static void fillMove(int i, int j, int c) {
    	//c==1 -> Black, c==2 -> White
    	initialize3();
    	
        while (Jmok.board[i][j1] == c) {
            if (++length > 4) {
                Jmok.Win(c);
                return;
            }
            ++j1;
        }
        
        move[i][j - 1][7] = max(move[i][j - 1][7], length);
        colors[i][j - 1][7] = c;
        move[i][j1][2] = max(move[i][j1][2], length);
        colors[i][j1][2] = c;
        
        initialize3();
    	
        while (Jmok.board[i1][j] == c) {
            if (++length > 4) {
            	Jmok.Win(c);
                return;
            }
            ++i1;
        }
        
        move[i - 1][j][5] = max(move[i - 1][j][5], length);
        colors[i - 1][j][5] = c;
        move[i1][j][4] = max(move[i1][j][4], length);
        colors[i1][j][4] = c;
        
        initialize3();
    	
        while (Jmok.board[i1][j1] == c) {
            if (++length > 4) {
            	Jmok.Win(c);
                return;
            }
            ++i1;
            ++j1;
        }
        
        move[i - 1][j - 1][8] = max(move[i - 1][j - 1][8], length);
        colors[i - 1][j - 1][8] = c;
        move[i1][j1][1] = max(move[i1][j1][1], length);
        colors[i1][j1][1] = c;
        
        initialize3();
    	
        while (Jmok.board[i1][j1] == c) {
            if (++length > 4) {
            	Jmok.Win(c);
                return;
            }
            ++i1;
            --j1;
        }
        
        move[i - 1][j + 1][3] = max(move[i - 1][j + 1][3], length);
        colors[i - 1][j - 1][3] = c;
        move[i1][j1][6] = max(move[i1][j1][6], length);
        colors[i1][j1][6] = c;
    }

    public static void scanBoard() {
        if (Jmok.board[0][0] == 4) {
            Jmok.Win(3);
            System.exit(0);
        }
        else if (Jmok.board[0][0] == 5) {
            Jmok.Win(5);
            System.exit(0);
        }
        
        for(i = 1;i <= 19;i++){
        	for(j = 1;j <= 19;j++){
        		if (Jmok.board[i][j] == 1||Jmok.board[i][j] == 2) {
                    fillMove(i, j, Jmok.board[i][j]);
                }
        	}
        }
    }

    public static void pRecognition() {
    	/*
    	1 2 3
    	4   5
    	6 7 8
    	*/
        for(i = 1;i <= 19;i++){
        	for(j = 1;j <= 19;j++){
        		if (Jmok.board[i][j] != 0) {
                    move[i][j][0] = 0;
                } 
        		else{
                	for(k = 1;k < 9;k++){
                		switch (move[i][j][k]) {
               			case 4: {
               				move[i][j][0] += moves[0];//moves[0]
                            if(colors[i][j][k] == Jmok.You){
                            	move[i][j][0] += moves[0];//I-priority
                            }
                            break;
                        }
               			//for moves[1], closed or semi-closed doesn't matter. 
               			case 3: {
                        	switch (k) {
                            case 1: {
                                if (Jmok.board[i + 1][j + 1] == colors[i][j][k]) {
                                    move[i][j][0] += moves[1];//moves[1]_2
                                    if (colors[i][j][k] == Jmok.You) {
                                    	move[i][j][0] += moves[1];//I-priority
                                    }
                                }
                        
                                else if (Jmok.board[i - 4][j - 4] == 0 || Jmok.board[i + 1][j + 1] == 0) { 
                                	if (Jmok.board[i - 4][j - 4] == 0 && Jmok.board[i + 1][j + 1] == 0) {
                                		move[i][j][0] += moves[3];//move_3_1_open
                                		if (colors[i][j][k] == Jmok.You) {
                                        	move[i][j][0] += moves[3];//I-priority
                                        }
                                    }
                                	else{
                                		move[i][j][0] += moves[4];//move_3_1_semi-closed
                                		multi3[i][j][k]=1;
                                		if (colors[i][j][k] == Jmok.You) {
                                        	move[i][j][0] += moves[4];//I-priority
                                        }
                                	}
                                }
                                break;
                            }
                            case 2: {
                                if (Jmok.board[i][j + 1] == colors[i][j][k]) {
                                	move[i][j][0] += moves[1];//moves[1]_2
                                    if (colors[i][j][k] == Jmok.You) {
                                    	move[i][j][0] += moves[1];//I-priority
                                    }
                                }
                                
                                else if (Jmok.board[i][j - 4] == 0 || Jmok.board[i][j + 1] == 0) {
                                    if (Jmok.board[i][j - 4] == 0 && Jmok.board[i][j + 1] == 0) {
                                    	move[i][j][0] += moves[3];//move_3_1_open
                                		if (colors[i][j][k] == Jmok.You) {
                                        	move[i][j][0] += moves[3];//I-priority
                                        }
                                    }
                                    else{
                                		move[i][j][0] += moves[4];//move_3_1_semi-closed
                                		multi3[i][j][k]=1;
                                		if (colors[i][j][k] == Jmok.You) {
                                        	move[i][j][0] += moves[4];//I-priority
                                        }
                                	}
                                }
                                break;
                            }
                            case 3: {
                                if (Jmok.board[i - 1][j + 1] == colors[i][j][k]) {
                                	move[i][j][0] += moves[1];//moves[1]_2
                                    if (colors[i][j][k] == Jmok.You) {
                                    	move[i][j][0] += moves[1];//I-priority
                                    }
                                }
                                
                                else if (Jmok.board[i + 4][j - 4] == 0 || Jmok.board[i - 1][j + 1] == 0) {
                                    if (Jmok.board[i + 4][j - 4] == 0 && Jmok.board[i - 1][j + 1] == 0) {
                                    	move[i][j][0] += moves[3];//move_3_1_open
                                		if (colors[i][j][k] == Jmok.You) {
                                        	move[i][j][0] += moves[3];//I-priority
                                        }
                                    }
                                    else{
                                		move[i][j][0] += moves[4];//move_3_1_semi-closed
                                		multi3[i][j][k]=1;
                                		if (colors[i][j][k] == Jmok.You) {
                                        	move[i][j][0] += moves[4];//I-priority
                                        }
                                	}
                                }
                                break;
                            }
                            case 4: {
                                if (Jmok.board[i + 1][j] == colors[i][j][k]) {
                                	move[i][j][0] += moves[1];//moves[1]_2
                                    if (colors[i][j][k] == Jmok.You) {
                                    	move[i][j][0] += moves[1];//I-priority
                                    }
                                }
                                else if (Jmok.board[i - 4][j] == 0 || Jmok.board[i + 1][j] == 0) {
                                    if (Jmok.board[i - 4][j] == 0 && Jmok.board[i + 1][j] == 0) {
                                    	move[i][j][0] += moves[3];//move_3_1_open
                                		if (colors[i][j][k] == Jmok.You) {
                                        	move[i][j][0] += moves[3];//I-priority
                                        }
                                    }
                                    else{
                                		move[i][j][0] += moves[4];//move_3_1_semi-closed
                                		multi3[i][j][k]=1;
                                		if (colors[i][j][k] == Jmok.You) {
                                        	move[i][j][0] += moves[4];//I-priority
                                        }
                                	}
                                }
                                break;
                            }
                            case 5: {
                                if (Jmok.board[i - 1][j] == colors[i][j][k]) {
                                	move[i][j][0] += moves[1];//moves[1]_2
                                    if (colors[i][j][k] == Jmok.You) {
                                    	move[i][j][0] += moves[1];//I-priority
                                    }
                                }
                                else if (Jmok.board[i + 4][j] == 0 || Jmok.board[i - 1][j] == 0) {
                                    if (Jmok.board[i + 4][j] == 0 && Jmok.board[i - 1][j] == 0) {
                                    	move[i][j][0] += moves[3];//move_3_1_open
                                		if (colors[i][j][k] == Jmok.You) {
                                        	move[i][j][0] += moves[3];//I-priority
                                        }
                                    }
                                    else{
                                		move[i][j][0] += moves[4];//move_3_1_semi-closed
                                		multi3[i][j][k]=1;
                                		if (colors[i][j][k] == Jmok.You) {
                                        	move[i][j][0] += moves[4];//I-priority
                                        }
                                	}
                                }
                                break;
                            }
                            case 6: {
                                if (Jmok.board[i + 1][j - 1] == colors[i][j][k]) {
                                	move[i][j][0] += moves[1];//moves[1]_2
                                    if (colors[i][j][k] == Jmok.You) {
                                    	move[i][j][0] += moves[1];//I-priority
                                    }
                                }
                                else if (Jmok.board[i - 4][j + 4] == 0 || Jmok.board[i + 1][j - 1] == 0) {
                                    if (Jmok.board[i - 4][j + 4] == 0 && Jmok.board[i + 1][j - 1] == 0) {
                                    	move[i][j][0] += moves[3];//move_3_1_open
                                		if (colors[i][j][k] == Jmok.You) {
                                        	move[i][j][0] += moves[3];//I-priority
                                        }
                                    }
                                    else{
                                		move[i][j][0] += moves[4];//move_3_1_semi-closed
                                		multi3[i][j][k]=1;
                                		if (colors[i][j][k] == Jmok.You) {
                                        	move[i][j][0] += moves[4];//I-priority
                                        }
                                	}
                                }
                                break;
                            }
                            case 7: {
                                if (Jmok.board[i][j - 1] == colors[i][j][k]) {
                                	move[i][j][0] += moves[1];//moves[1]_2
                                    if (colors[i][j][k] == Jmok.You) {
                                    	move[i][j][0] += moves[1];//I-priority
                                    }
                                }
                                else if (Jmok.board[i][j + 4] == 0 || Jmok.board[i][j - 1] == 0) {
                                    if (Jmok.board[i][j + 4] == 0 && Jmok.board[i][j - 1] == 0) {
                                    	move[i][j][0] += moves[3];//move_3_1_open
                                		if (colors[i][j][k] == Jmok.You) {
                                        	move[i][j][0] += moves[3];//I-priority
                                        }
                                    }
                                    else{
                                		move[i][j][0] += moves[4];//move_3_1_semi-closed
                                		multi3[i][j][k]=1;
                                		if (colors[i][j][k] == Jmok.You) {
                                        	move[i][j][0] += moves[4];//I-priority
                                        }
                                	}
                                }
                                break;
                            }
                            case 8: {
                                if (Jmok.board[i - 1][j - 1] == colors[i][j][k]) {
                                	move[i][j][0] += moves[1];//moves[1]_2
                                    if (colors[i][j][k] == Jmok.You) {
                                    	move[i][j][0] += moves[1];//I-priority
                                    }
                                }
                                else if (Jmok.board[i + 4][j + 4] == 0 || Jmok.board[i - 1][j - 1] == 0) {
                                    if (Jmok.board[i + 4][j + 4] == 0 && Jmok.board[i - 1][j - 1] == 0) {
                                    	move[i][j][0] += moves[3];//move_3_1_open
                                		if (colors[i][j][k] == Jmok.You) {
                                        	move[i][j][0] += moves[3];//I-priority
                                        }
                                    }
                                    else{
                                		move[i][j][0] += moves[4];//move_3_1_semi-closed
                                		multi3[i][j][k]=1;
                                		if (colors[i][j][k] == Jmok.You) {
                                        	move[i][j][0] += moves[4];//I-priority
                                        }
                                	}
                                }
                                break;
                            }
                        	}
                        	break;
                        }
                        case 2: {
                        	switch (k) {
                            case 1: {
                            	if (Jmok.board[i + 1][j + 1] == colors[i][j][k]) {
                            		if(move[i][j][8] == 2){
                            			if(colors[i][j][k] == Jmok.You){
                                        	move[i][j][0] += moves[2]/2;//I-priority
                                        }
                            			move[i][j][0] += moves[2]/2;//moves[2]_3
                            		}
                            		else if(move[i][j][8] == 1){
                            			if (Jmok.board[i + 2][j + 2] == 0 && Jmok.board[i - 3][j - 3] == 0){
                            				move[i][j][0] += moves[5];//move_3_2_open
                                    		if (colors[i][j][k] == Jmok.You) {
                                            	move[i][j][0] += moves[5];//I-priority
                                            }
                            			}
                            			else if(Jmok.board[i + 2][j + 2] == 0 || Jmok.board[i - 3][j - 3] == 0){
                            				move[i][j][0] += moves[6];
                            				multi3[i][j][k]=1;
                                    	}
                            		}
                            	}
                            	else if(Jmok.board[i + 1][j + 1] == 0 || Jmok.board[i - 3][j - 3] == 0){
                            		if(Jmok.board[i + 1][j + 1] == 0 && Jmok.board[i - 3][j - 3] == 0){
                            			move[i][j][0] += moves[7];
                            			multi3[i][j][k]=1;
                            		}
                            		else{
                            			move[i][j][0] += moves[8];
                            		}
                            	}
                            	break;
                            }
                            case 2: {
                            	if (Jmok.board[i][j + 1] == colors[i][j][k]) {
                            		if(move[i][j][7] == 2){
                            			if(colors[i][j][k] == Jmok.You){
                                        	move[i][j][0] += moves[2]/2;//I-priority
                                        }
                            			move[i][j][0] += moves[2]/2;//moves[2]_3
                            		}
                            		else if(move[i][j][7] == 1){
                            			if (Jmok.board[i][j + 2] == 0 && Jmok.board[i][j - 3] == 0){
                            				move[i][j][0] += moves[5];//move_3_2_open
                                    		if (colors[i][j][k] == Jmok.You) {
                                            	move[i][j][0] += moves[5];//I-priority
                                            }
                            			}
                            			else if(Jmok.board[i][j + 2] == 0 || Jmok.board[i][j - 3] == 0){
                            				move[i][j][0] += moves[6];
                            				multi3[i][j][k]=1;
                            			}
                            		}
                            	}
                            	else if(Jmok.board[i][j + 1] == 0 || Jmok.board[i][j - 3] == 0){
                            		if(Jmok.board[i][j + 1] == 0 && Jmok.board[i][j - 3] == 0){
                            			move[i][j][0] += moves[7];
                            			multi3[i][j][k]=1;
                            		}
                            		else{
                            			move[i][j][0] += moves[8];
                            		}
                            	}
                                break;
                            }
                            case 3: {
                            	if (Jmok.board[i - 1][j + 1] == colors[i][j][k]) {
                            		if(move[i][j][6] == 2){
                            			if(colors[i][j][k] == Jmok.You){
                                        	move[i][j][0] += moves[2]/2;//I-priority
                                        }
                            			move[i][j][0] += moves[2]/2;//moves[2]_3
                            		}
                            		else if(move[i][j][6] == 1){
                            			if (Jmok.board[i - 2][j + 2] == 0 && Jmok.board[i + 3][j - 3] == 0){
                            				move[i][j][0] += moves[5];//move_3_2_open
                                    		if (colors[i][j][k] == Jmok.You) {
                                            	move[i][j][0] += moves[5];//I-priority
                                            }
                            			}
                            			else if(Jmok.board[i - 2][j + 2] == 0 || Jmok.board[i + 3][j - 3] == 0){
                            				move[i][j][0] += moves[6];
                            				multi3[i][j][k]=1;
                            			}
                            		}
                            	}
                            	else if(Jmok.board[i - 1][j + 1] == 0 || Jmok.board[i + 3][j - 3] == 0){
                            		if(Jmok.board[i - 1][j + 1] == 0 && Jmok.board[i + 3][j - 3] == 0){
                            			move[i][j][0] += moves[7];
                            			multi3[i][j][k]=1;
                            		}
                            		else{
                            			move[i][j][0] += moves[8];
                            		}
                            	}
                                break;
                            }
                            case 4: {
                            	if (Jmok.board[i + 1][j] == colors[i][j][k]) {
                            		if(move[i][j][5] == 2){
                            			if(colors[i][j][k] == Jmok.You){
                                        	move[i][j][0] += moves[2]/2;//I-priority
                                        }
                            			move[i][j][0] += moves[2]/2;//moves[2]_3
                            		}
                            		else if(move[i][j][5] == 1){
                            			if (Jmok.board[i + 2][j] == 0 && Jmok.board[i - 3][j] == 0){
                            				move[i][j][0] += moves[5];//move_3_2_open
                                    		if (colors[i][j][k] == Jmok.You) {
                                            	move[i][j][0] += moves[5];//I-priority
                                            }
                            			}
                            			else if(Jmok.board[i + 2][j] == 0 || Jmok.board[i - 3][j] == 0){
                            				move[i][j][0] += moves[6];
                            				multi3[i][j][k]=1;
                            			}
                            		}
                            	}
                            	else if(Jmok.board[i + 1][j] == 0 || Jmok.board[i - 3][j] == 0){
                            		if(Jmok.board[i + 1][j] == 0 && Jmok.board[i - 3][j] == 0){
                            			move[i][j][0] += moves[7];
                            			multi3[i][j][k]=1;
                            		}
                            		else{
                            			move[i][j][0] += moves[8];
                            		}
                            	}
                                break;
                            }
                            case 5: {
                            	if (Jmok.board[i - 1][j] == colors[i][j][k]) {
                            		if(move[i][j][4] == 2){
                            			if(colors[i][j][k] == Jmok.You){
                                        	move[i][j][0] += moves[2]/2;//I-priority
                                        }
                            			move[i][j][0] += moves[2]/2;//moves[2]_3
                            		}
                            		else if(move[i][j][4] == 1){
                            			if (Jmok.board[i - 2][j] == 0 && Jmok.board[i + 3][j] == 0){
                            				move[i][j][0] += moves[5];//move_3_2_open
                                    		if (colors[i][j][k] == Jmok.You) {
                                            	move[i][j][0] += moves[5];//I-priority
                                            }
                            			}
                            			else if(Jmok.board[i - 2][j] == 0 || Jmok.board[i + 3][j] == 0){
                            				move[i][j][0] += moves[6];
                            				multi3[i][j][k]=1;
                            			}
                            		}
                            	}
                            	else if(Jmok.board[i - 1][j] == 0 || Jmok.board[i + 3][j] == 0){
                            		if(Jmok.board[i - 1][j] == 0 && Jmok.board[i + 3][j] == 0){
                            			move[i][j][0] += moves[7];
                            			multi3[i][j][k]=1;
                            		}
                            		else{
                            			move[i][j][0] += moves[8];
                            		}
                            	}
                                break;
                            }
                            case 6: {
                            	if (Jmok.board[i + 1][j - 1] == colors[i][j][k]) {
                            		if(move[i][j][3] == 2){
                            			if(colors[i][j][k] == Jmok.You){
                                        	move[i][j][0] += moves[2]/2;//I-priority
                                        }
                            			move[i][j][0] += moves[2]/2;//moves[2]_3
                            		}
                            		else if(move[i][j][3] == 1){
                            			if (Jmok.board[i + 2][j - 2] == 0 && Jmok.board[i - 3][j + 3] == 0){
                            				move[i][j][0] += moves[5];//move_3_2_open
                                    		if (colors[i][j][k] == Jmok.You) {
                                            	move[i][j][0] += moves[5];//I-priority
                                            }
                            			}
                            			else if(Jmok.board[i + 2][j - 2] == 0 || Jmok.board[i - 3][j + 3] == 0){
                            				move[i][j][0] += moves[6];
                            				multi3[i][j][k]=1;
                            			}
                            		}
                            	}
                            	else if(Jmok.board[i + 1][j - 1] == 0 || Jmok.board[i - 3][j + 3] == 0){
                            		if(Jmok.board[i + 1][j - 1] == 0 && Jmok.board[i - 3][j + 3] == 0){
                            			move[i][j][0] += moves[7];
                            			multi3[i][j][k]=1;
                            		}
                            		else{
                            			move[i][j][0] += moves[8];
                            		}
                            	}
                                break;
                            }
                            case 7: {
                            	if (Jmok.board[i - 1][j] == colors[i][j][k]) {
                            		if(move[i][j][2] == 2){
                            			if(colors[i][j][k] == Jmok.You){
                                        	move[i][j][0] += moves[2]/2;//I-priority
                                        }
                            			move[i][j][0] += moves[2]/2;//moves[2]_3
                            		}
                            		else if(move[i][j][2] == 1){
                            			if (Jmok.board[i - 2][j] == 0 && Jmok.board[i + 3][j] == 0){
                            				move[i][j][0] += moves[5];//move_3_2_open
                                    		if (colors[i][j][k] == Jmok.You) {
                                            	move[i][j][0] += moves[5];//I-priority
                                            }
                            			}
                            			else if(Jmok.board[i - 2][j] == 0 || Jmok.board[i + 3][j] == 0){
                            				move[i][j][0] += moves[6];
                            				multi3[i][j][k]=1;
                            			}
                            		}
                            	}
                            	else if(Jmok.board[i - 1][j] == 0 || Jmok.board[i + 3][j] == 0){
                            		if(Jmok.board[i - 1][j] == 0 && Jmok.board[i + 3][j] == 0){
                            			move[i][j][0] += moves[7];
                            			multi3[i][j][k]=1;
                            		}
                            		else{
                            			move[i][j][0] += moves[8];
                            		}
                            	}
                                break;
                            }
                            case 8: {
                            	if (Jmok.board[i - 1][j - 1] == colors[i][j][k]) {
                            		if(move[i][j][1] == 2){
                            			if(colors[i][j][k] == Jmok.You){
                                        	move[i][j][0] += moves[2]/2;//I-priority
                                        }
                            			move[i][j][0] += moves[2]/2;//moves[2]_3
                            		}
                            		else if(move[i][j][1] == 1){
                            			if (Jmok.board[i - 2][j - 2] == 0 && Jmok.board[i + 3][j + 3] == 0){
                            				move[i][j][0] += moves[5];//move_3_2_open
                                    		if (colors[i][j][k] == Jmok.You) {
                                            	move[i][j][0] += moves[5];//I-priority
                                            }
                            			}
                            			else if(Jmok.board[i - 2][j - 2] == 0 || Jmok.board[i + 3][j + 3] == 0){
                            				move[i][j][0] += moves[6];
                            				multi3[i][j][k]=1;
                            			}
                            		}
                            	}
                            	else if(Jmok.board[i - 1][j - 1] == 0 || Jmok.board[i + 3][j + 3] == 0){
                            		if(Jmok.board[i - 1][j - 1] == 0 && Jmok.board[i + 3][j + 3] == 0){
                            			move[i][j][0] += moves[7];
                            			multi3[i][j][k]=1;
                            		}
                            		else{
                            			move[i][j][0] += moves[8];
                            		}
                            	}
                                break;
                            }
                        	}
                        	break;
                        }
                        default: {
                            move[i][j][0] += move[i][j][k];
                        	}
                		}
                	}
                	m1=0;
            		m2=0;
            		
            		for(k = 1;k < 9;k++){
            			if(multi3[i][j][k]==1){
            				m1++;
            			}
            			else if(multi3[i][j][k]==2){
            				m2++;
            			}
            		}
            		
            		if(m1>=2){
            			move[i][j][0]+=moves[11];
            		}
            		if(m2>=2){
            			move[i][j][0]+=moves[11];
            		}
        		}
        	}
        }
    }

    public static void aIntelligence() {
        scanBoard();
        pRecognition(); 
        
        System.out.println(move[10][10][1]+" "+move[10][10][2]+" "+move[10][10][3]);
        System.out.println(move[10][10][4]+" "+move[10][10][0]+" "+move[10][10][5]);
        System.out.println(move[10][10][6]+" "+move[10][10][7]+" "+move[10][10][8]);
        
        for(i = 1;i <= 19;i++){
        	for(j = 1;j <= 19;j++){
        		if (move[i][j][0] > max) {
        			x = i;
        			y = j;
        			
        			iV.removeAllElements();
        			jV.removeAllElements();
        			iV.add(i);
                    jV.add(j);
                    
                    max = move[i][j][0];
                }
        		else if(move[i][j][0] == max){
        			iV.add(i);
                    jV.add(j);
        		}
        	}
        }
        
        if(iV.size()>1){
        	Random r = new Random();
        	int rand = r.nextInt(iV.size());
        	x = iV.get(rand);
            y = jV.get(rand);
        }
        
        Jmok.board[x][y] = Jmok.You;
        
        for(i = 1;i <= 19;i++){
        	for(j = 1;j <= 19;j++){
        		System.out.print(Jmok.board[j][i]+" ");
        	}
        	System.out.print("| ");
        	for(j = 1;j <= 19;j++){
        		System.out.print(move[j][i][0]+" ");
        	}
        	System.out.println();
        }
        System.out.println("-----------------------------------------------------------------------------");
        
        for(i = 0;i < 21;i++){//initialization begins
            for(j = 0;j < 21;j++){
        		for(k = 0;k < 9;k++){
        			move[i][j][k] = 0;
        			colors[i][j][k] = 0;
                    multi3[i][j][k] = 0;
        		}
        	}
        }
        
        max = 0;//initialization ends
    }
}