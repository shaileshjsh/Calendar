package org.example;

import java.util.Arrays;

public class Piece {
    private int [][] shape;
    private final int pieceNum;

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
            System.arraycopy(shape[p], 0, temp[p], 0, 4);
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
                System.arraycopy(rotated[p], 0, temp[p], 0, 4);
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
