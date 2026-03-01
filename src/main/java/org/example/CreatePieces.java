package org.example;

public class CreatePieces {
    public static Piece createPiece(int i){
        switch (i){
            case 1:
                int[][] a1 = {{1,0,0,0},
                        {1,0,0,0},
                        {1,1,1,0},
                        {0,0,0,0}};
                return new Piece(a1, 1);

            case 2:
                int[][] a2 = {{1,0,0,0},
                        {1,0,0,0},
                        {1,0,0,0},
                        {1,1,0,0}};
                return new Piece(a2,2);

            case 3:
                int[][] a3 = {{1,1,1,0},
                        {1,1,0,0},
                        {0,0,0,0},
                        {0,0,0,0}};
                return new Piece(a3,3);

            case 4:
                int[][] a4 = {{1,1,1,0},
                        {1,0,1,0},
                        {0,0,0,0},
                        {0,0,0,0}};
                return new Piece(a4,4);

            case 5:
                int[][] a5 = {{1,1,1,0},
                        {0,0,1,1},
                        {0,0,0,0},
                        {0,0,0,0}};
                return new Piece(a5,5);

            case 6:
                int[][] a6 = {{1,1,1,0},
                        {1,1,1,0},
                        {0,0,0,0},
                        {0,0,0,0}};
                return new Piece(a6,6);

            case 7:
                int[][] a7 = {{0,1,1,0},
                        {0,1,0,0},
                        {1,1,0,0},
                        {0,0,0,0}};
                return new Piece(a7,7);

            case 8:
                int[][] a8 = {{1,0,0,0},
                        {1,0,0,0},
                        {1,1,0,0},
                        {1,0,0,0}};
                return new Piece(a8,8);

            default:
                int[][] a9 = {{1,1,1,1},
                        {1,1,1,1},
                        {1,1,1,1},
                        {1,1,1,1}};
                return new Piece(a9,0);
        }
    }
}
