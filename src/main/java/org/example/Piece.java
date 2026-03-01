package org.example;

import java.util.Arrays;

public class Piece {
    private int [][] shape;
    private int pieceNum;

    public Piece(int[][] shape, int pieceNum) {
        this.shape = shape;
        this.pieceNum = pieceNum;
    }

    public int[][] getShape() {
        return shape;
    }
    public int getPieceNum() {
        return pieceNum;
    }

    public void setShape(int[][] shape) {
        this.shape = shape;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "shape=" + Arrays.deepToString(shape) +
                '}';
    }

    public int[][] rotate(int numOfRotations){
        int [][] rotated = new int[4][4];
        int [][] temp = new int[4][4];
        int n;
        for(int p=0; p<4; p++){
            for(int q=0; q<4; q++){
                temp[p][q] = shape[p][q];
            }
        }
        for(int t=0; t<numOfRotations; t++) {
            for (int i = 0; i < 4; i++) {
                n = 0;
                for (int j = 3; j >= 0; j--) {
                    rotated[n][i] = temp[i][j];
                    n++;
                }
            }
            for(int p=0; p<4; p++){
                for(int q=0; q<4; q++){
                    temp[p][q] = rotated[p][q];
                }
            }
        }
        //shape = rotated;
        return rotated;
    }

    public int[][] flip(){
        int [][] flipped = new int[4][4];
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                flipped[i][3-j]=shape[i][j];
            }
        }
        //shape = flipped;
        return  flipped;
    }


}
