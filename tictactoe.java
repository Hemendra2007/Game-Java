import java.util.*;
class HelloWorld {
    static int[][] board=new int[3][3];
    static boolean win(int x){
        //for each column
        for(int i=0; i<3; i++){
            boolean valid=true;
            for(int j=0; j<3; j++){
                if(board[i][j]!=x){
                    valid=false;
                    break;
                }
            }
            if(valid){
                return true;
            }
        }
        //for each row
        for(int i=0; i<3; i++){
            boolean valid=true;
            for(int j=0; j<3; j++){
                if(board[j][i]!=x){
                    valid=false;
                    break;
                }
            }
            if(valid){
                return true;
            }
        }
        
        //for diagonal
        if(board[0][0]==board[1][1] && board[1][1]==board[2][2] && board[0][0]==x){
            return true;
        }
        if(board[0][2]==board[1][1] && board[1][1]==board[2][0] && board[0][2]==x){
            return true;
        }
        return false;
    }
    
    static boolean move(int x, int posx, int posy){
        if(board[posx-1][posy-1]!=0){
            return false;
        }
        board[posx-1][posy-1]=x;
        System.out.println();
        print();
        return true;
    }
    static void print(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                switch(board[i][j]){
                    case 0:System.out.print("[ ] ");break;
                    case 2:System.out.print("[O] ");break;
                    case 1:System.out.print("[X] ");break;
                    default:break;
                }
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        int cur=1;
        for(int i=0; i<9; i++){
            System.out.println("Player "+cur+"\'s turn ");
            System.out.print("Enter move: (Row Column)");
            int r,c;
            r=s.nextInt();
            c=s.nextInt();
            boolean x=move(cur,r,c);
            if(!x){
                System.out.println("Block already filled!");
                i--;
                continue;
            }
            if(win(cur)){
                System.out.println("Player "+cur+" wins");
                return;
            }
            cur=(cur==1)?2:1;
        }
        System.out.println("Draw!");
    }
}
