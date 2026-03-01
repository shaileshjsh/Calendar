package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        //System.out.println("Hello world!");
        Stack<Piece> st = new Stack<>();
        Deque<Piece> pieceBank = new ArrayDeque<>();
        List<Stack<Piece>> allAnswers = new ArrayList<>();

        for (int i = 1; i <= 8; i++) {
            pieceBank.add(CreatePieces.createPiece(i));
        }
        Board board = new Board();
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter date and month DD MM (Ex. 5 12)");
        int date = sc.nextInt();
        int month = sc.nextInt();
//        System.out.println(date + " " + month);
        board.setDate((month < 7) ? 0 : 1, (month < 7) ? month - 1 : month - 7);
        board.setDate(((date % 7) == 0) ? (date / 7) + 1 : (date / 7) + 2, ((date % 7) == 0) ? 6 : (date % 7) - 1);
        System.out.println("============================");
        System.out.println("Board with expected date");
        System.out.println("============================");
        printArray(board.getBoardShape());
        System.out.println();
        System.out.println();

        boolean boardSolved = false;

        boolean finished = false;
        int n1 = pieceBank.size();
        while ((!finished) && (n1 > 0)) {

            boardSolved = solveBoard(board, pieceBank, 0, st, allAnswers) ||
                    solveBoard(board, pieceBank, 1, st, allAnswers) ||
                    solveBoard(board, pieceBank, 2, st, allAnswers) ||
                    solveBoard(board, pieceBank, 3, st, allAnswers) ||
                    solveBoard(board, pieceBank, 4, st, allAnswers) ||
                    solveBoard(board, pieceBank, 5, st, allAnswers) ||
                    solveBoard(board, pieceBank, 6, st, allAnswers) ||
                    solveBoard(board, pieceBank, 7, st, allAnswers);
            if (boardSolved) finished = true;
            else {
                n1--;
                pieceBank.addLast(pieceBank.removeFirst());
            }
        }

        int solutionNumber = 0;
        for (Stack<Piece> s : allAnswers) {
            solutionNumber++;
            if (s.size() == 8) {
                System.out.println("============================");
                System.out.println("Solution: "+ solutionNumber);
                System.out.println("============================");
                //int pieceNumber = 0;
                for(int i =0; i<s.size();i++){
                //while (!s.isEmpty()) {
                    //for (Piece b : a.toArray()) {
                    //pieceNumber++;
                    System.out.println("============================");
                    System.out.println("Piece Number: "+ i);
                    System.out.println("============================");
                    printArrayOnes(s.get(i).getShape());
                    System.out.println();
                }
            }
        }
    }

        static boolean solveBoard (Board board, Deque < Piece > pieceBank,int rotation, Stack<Piece > st, List <Stack<Piece>> allAnswers){

            boolean success = false;
            Board board1 = new Board();
            board1.setBoardShape(board.getBoardShape());

            Deque<Piece> pieceBank1 = new ArrayDeque<>(pieceBank);
            Piece piece1 = new Piece(pieceBank1.getFirst().getShape(), pieceBank1.getFirst().getPieceNum());

            /////Debug block


            if((piece1.getPieceNum()==1) && rotation > 3) return false;
            if((piece1.getPieceNum()==4) && rotation > 3) return  false;
            if((piece1.getPieceNum()==6) && rotation > 1) return  false;
            if((piece1.getPieceNum()==7) && ((rotation == 2)||(rotation == 3)||(rotation == 6)||(rotation == 7))) return false;

            if ((rotation > 0) && (rotation < 4)) {
                piece1.setShape(pieceBank1.getFirst().rotate(rotation));
            } else if (rotation == 4) {
                //Piece piece1 = new Piece(pieceBank1.peek().getShape());
                piece1.setShape(pieceBank1.getFirst().flip());

            } else if (rotation > 4) {
                //Piece piece1 = new Piece(pieceBank1.peek().getShape());
                Piece piece2 = new Piece(pieceBank1.getFirst().getShape(),pieceBank1.getFirst().getPieceNum());
                piece2.setShape(pieceBank1.peek().flip());
                piece1.setShape(piece2.rotate(rotation - 4));

            }
//            printArray(board1.getBoardShape());
//            printArray(piece1.getShape());
//            System.out.println();

            if (board1.fit(piece1.getShape())) {
                st.push(new Piece(piece1.getShape(), piece1.getPieceNum()));

//                System.out.println("==========================");
//                System.out.println("Fitted at rotation "+ rotation);
//                System.out.println("==========================");
                Piece piece3 = new Piece(pieceBank1.getFirst().getShape(),pieceBank1.getFirst().getPieceNum());
                pieceBank1.remove();
                if (pieceBank1.isEmpty()) {
                    //System.out.println("Solved");
                    if (st.size() == 8) {
                        Stack<Piece> st1 = new Stack<>();
                        st1 = (Stack<Piece>) st.clone();
                        allAnswers.add(st1);
                    }
                    pieceBank1.addFirst(piece3);
                    st.pop();
                    return false;
                }
                int n = pieceBank1.size();
//                System.out.println("=========PieceBank Size: " + n + " ============");
                while ((!success) && (n > 0)) {

                        success = solveBoard(board1, pieceBank1, 0, st, allAnswers) ||
                                solveBoard(board1, pieceBank1, 1, st, allAnswers) ||
                                solveBoard(board1, pieceBank1, 2, st, allAnswers) ||
                                solveBoard(board1, pieceBank1, 3, st, allAnswers) ||
                                solveBoard(board1, pieceBank1, 4, st, allAnswers) ||
                                solveBoard(board1, pieceBank1, 5, st, allAnswers) ||
                                solveBoard(board1, pieceBank1, 6, st, allAnswers) ||
                                solveBoard(board1, pieceBank1, 7, st, allAnswers);

                    if ((!success)) {
                        n--;
                        pieceBank1.addLast(pieceBank1.removeFirst());
//                        System.out.println("=========ROTATED : N = "+n+" ============");
                    }
                }
                if (success) return true;
            } else {
                return false;
                //pieceBank1.addLast(pieceBank1.removeFirst());
            }
//            System.out.println("========= end of heirarchy =========");
            if (!st.empty()) st.pop();
            return false;
        }


        public static void printArray ( int[][] a){
            for (int i = 0; i < a[0].length; i++) {
                for (int j = 0; j < a.length; j++) {
                    System.out.print(a[i][j] + " ");
                }
                System.out.println();
            }

        }

        public static void printArrayOnes ( int[][] a){
            for (int i = 0; i < a[0].length; i++) {
                for (int j = 0; j < a.length; j++) {
                    if (a[i][j] == 1)
                        System.out.print(a[i][j] + " ");
                    else
                        System.out.print(". ");
                }
                System.out.println();
            }

        }

}
