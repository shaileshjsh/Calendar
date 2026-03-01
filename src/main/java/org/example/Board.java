package org.example;

public class Board {
    private final int [][] boardShape = {{0,0,0,0,0,0,1},
            {0,0,0,0,0,0,1},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,1,1,1,1}};

    public int[][] getBoardShape() {
        return boardShape;
    }

    public void setDate(int i, int j){
        boardShape[i][j]=1;
    }

    public void setBoardShape(int[][] boardShape) {
        for(int i=0;i<7;i++){
            System.arraycopy(boardShape[i], 0, this.boardShape[i], 0, 7);
        }
    }

    public boolean fit(int[][] shape){
        int firstEmptyRow=0;
        int firstEmptyCol=0;
        int shapeOccupiedRow=0;
        int shapeOccupiedCol=0;
        outerloop:
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                if (boardShape[i][j] == 0) {
                    firstEmptyRow = i;
                    firstEmptyCol = j;
                    break outerloop;
                }
            }
        }

        outerloop1:
        for(int i=0;i<4;i++) {
            for (int j = 0; j < 4; j++) {
                if (shape[i][j] == 1) {
                    shapeOccupiedRow = i;
                    shapeOccupiedCol = j;
                    break outerloop1;
                }
            }
        }

        int n = firstEmptyRow;

        for(int i=shapeOccupiedRow;i<4;i++) {
            int m=firstEmptyCol-shapeOccupiedCol;
            //if (m<0) return false; //Removed newly
            for (int j = 0; j < 4; j++) {
                if ((m<0) && shape[i][j] == 0) { //added newly
                    m++;
                    continue;
                }
                if ((m<0) && shape[i][j] == 1) return false; //added newly
                if ((m>=7) && (shape[i][j]==1)) {
                    return false;}
                else if ((m>=7)) {
                    m++; //added newly
                    continue;
                }
                else if ((n>=7) && (shape[i][j]==1)) {
                    return false;
                }
                else if ((n>=7)) {
                    continue;
                }
                else {
                    if ((shape[i][j] == 1) && (boardShape[n][m] == 1)) return false;
                    m++;
                }
            }
            n++;
        }

        n = firstEmptyRow;
        for(int i=shapeOccupiedRow;i<4;i++) {
            if (n==7) continue;
            int m=firstEmptyCol-shapeOccupiedCol;
            //if (m<0) return false;
            for (int j = 0; j < 4; j++) {
                if ((m<0) && shape[i][j] == 0) { //added newly
                    m++;
                    continue;
                }
                if ((m<0) && shape[i][j] == 1) return false; //added newly
                if (m==7) continue;
                if(shape[i][j]==1) boardShape[n][m]=1;
                m++;
            }
            n++;
        }
        return true;

    }

}
