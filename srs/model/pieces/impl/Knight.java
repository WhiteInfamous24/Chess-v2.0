package model.pieces.impl;

import java.util.ArrayList;

import model.pieces.Piece;
import utils.Position;
import utils.enums.ColorEnum;
import utils.enums.PieceEnum;

public class Knight extends Piece {
    public Knight(ColorEnum c) {
        super(PieceEnum.KNIGHT, c, false);
    }

    public ArrayList<Position> possibleMovements(Position pos) {
        ArrayList<Position> output = new ArrayList<Position>();
        int x = pos.getX();
        int y = pos.getY();
        if (y+1 <= 7) {
            if (x+2 <= 7)
                output.add(new Position(x+2, y+1));
            if (x-2 >= 0)
                output.add(new Position(x-2, y+1));
        }
        if (y-1 >= 0) {
            if (x+2 <= 7)
                output.add(new Position(x+2, y-1));
            if (x-2 >= 0)
                output.add(new Position(x-2, y-1));
        }
        if (y+2 <= 7) {
            if (x+1 <= 7)
                output.add(new Position(x+1, y+2));
            if (x-1 >= 0)
                output.add(new Position(x-1, y+2));
        }
        if (y-2 >= 0) {
            if (x+1 <= 7)
                output.add(new Position(x+1, y-2));
            if (x-1 >= 0)
                output.add(new Position(x-1, y-2));
        }
        return output;
    }

    public ArrayList<Position> possibleTakes(Position pos) {
        ArrayList<Position> output = new ArrayList<Position>();
        int x = pos.getX();
        int y = pos.getY();
        if (y+1 <= 7) {
            if (x+2 <= 7)
                output.add(new Position(x+2, y+1));
            if (x-2 >= 0)
                output.add(new Position(x-2, y+1));
        }
        if (y-1 >= 0) {
            if (x+2 <= 7)
                output.add(new Position(x+2, y-1));
            if (x-2 >= 0)
                output.add(new Position(x-2, y-1));
        }
        if (y+2 <= 7) {
            if (x+1 <= 7)
                output.add(new Position(x+1, y+2));
            if (x-1 >= 0)
                output.add(new Position(x-1, y+2));
        }
        if (y-2 >= 0) {
            if (x+1 <= 7)
                output.add(new Position(x+1, y-2));
            if (x-1 >= 0)
                output.add(new Position(x-1, y-2));
        }
        return output;
    }
}
