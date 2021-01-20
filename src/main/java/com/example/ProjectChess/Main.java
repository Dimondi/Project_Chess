package com.example.ProjectChess;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveList;

import java.util.List;

public class Main {
    public static void main(String args[]){
        // Creates a new chessboard in the standard initial position
        Board board = new Board();

        //Make a move from E2 to E4 squares
        board.doMove(new Move(Square.E2, Square.E4));
        board.doMove(new Move(Square.E1, Square.E5));

        //print the chessboard in a human-readable form
        System.out.println(board.getPieceLocation(Piece.WHITE_KING));
        System.out.println(board.isMoveLegal(new Move(Square.E1, Square.E4),true));
    }
}
