package userInterface.impl;

import java.util.ArrayList;
import java.util.Scanner;

import model.Board;
import model.pieces.Piece;
import model.pieces.impl.Bishop;
import model.pieces.impl.Knight;
import model.pieces.impl.Queen;
import model.pieces.impl.Rook;
import service.impl.GameService;
import userInterface.UserInterface;
import utils.Position;
import utils.enums.ColorEnum;

public class ConsoleUI extends UserInterface {
    private GameService gameService = GameService.getInstance();
    Scanner scanner = new Scanner(System.in);

    public ConsoleUI() {
        update();
    }

    //I feel you shouldnt be able to make a piece from the view, fix it later
    public Piece requestToChoosePiece(ColorEnum c) {
        Piece piece;
        System.out.println("'1' ----> Bishop");
        System.out.println("'2' ----> Knight");
        System.out.println("'3' ----> Rook");
        System.out.println("'4' ----> Queen");
        System.out.println("(default) Queen\n");
        requestUserInput(CHOOSE_PIECE_MESSAGE);
        String input = getInput();
        piece = switch (input) {
            case "1" -> new Bishop(c);
            case "2" -> new Knight(c);
            case "3" -> new Rook(c);
            default -> new Queen(c);
        };
        return piece;
    }

    @Override
    public void requestUserInput(String message) {
        System.out.println(message);
    }

    @Override
    public String getInput() {
        return scanner.nextLine();
    }

    @Override
    public void showBoard() {
        Board b = gameService.getBoard();
        int row = 8;
        for (int i = 0; i < 8; i++) {
            if (i == 0) {
                System.out.println("||===||===============================================================================================||===||");
                System.out.println("||   ||     A     |     B     |     C     |     D     |     E     |     F     |     G     |     H     ||   ||");
                System.out.println("||===||===============================================================================================||===||");
            }
            else
                System.out.println("||---||-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------||---||");
            System.out.print("||   |");
            for (int j = 0; j < 8; j++) {
                System.out.print("|");
                if (b.getPiece(new Position(j, i)) != null)
                    System.out.printf(" %-10s", b.getPiece(new Position(j, i)).getName());
                else
                    System.out.printf("%11s", " ");
            }
            System.out.println("||   ||");
            System.out.print("|| " + row +" |");
            for (int j = 0; j < 8; j++) {
                System.out.print("|");
                if (b.getPiece(new Position(j, i)) != null)
                    System.out.printf(" %-10s", b.getPiece(new Position(j, i)).getColor());
                else
                    System.out.printf("%11s", " ");
            }
            System.out.println("|| " + row + " ||");
            System.out.println("||   ||           |           |           |           |           |           |           |           ||   ||");
            row--;
        }
        System.out.println("||===||===============================================================================================||===||");
        System.out.println("||   ||     A     |     B     |     C     |     D     |     E     |     F     |     G     |     H     ||   ||");
        System.out.println("||===||===============================================================================================||===||");
        System.out.println(" ");
    }

    @Override
    public void showTakenPieces() {
        ArrayList<Piece> b_p_t = gameService.getTakenPieces(ColorEnum.BLACK);
        ArrayList<Piece> w_p_t = gameService.getTakenPieces(ColorEnum.WHITE);;
        System.out.print("||====================|");
        for (Piece piece : b_p_t)
            System.out.print("|===========");
        if (b_p_t.size() == 0)
            System.out.print("|");
        else
            System.out.print("||");
        System.out.print("\n|| BLACK PIECES TAKEN |");
        for (Piece piece : b_p_t)
            System.out.printf("| %-10s", piece.getName());
        if (b_p_t.size() == 0)
            System.out.print("|");
        else
            System.out.print("||");
        System.out.print("\n||--------------------|");
        for (Piece piece : b_p_t)
            System.out.print("|-----------");
        if (b_p_t.size() == 0)
            System.out.print("|");
        else
            System.out.print("||");
        System.out.print("\n||--------------------|");
        for (Piece piece : w_p_t)
            System.out.print("|-----------");
        if (w_p_t.size() == 0)
            System.out.print("|");
        else
            System.out.print("||");
        System.out.print("\n|| WHITE PIECES TAKEN |");
        for (Piece piece : w_p_t)
            System.out.printf("| %-10s", piece.getName());
        if (w_p_t.size() == 0)
            System.out.print("|");
        else
            System.out.print("||");
        System.out.print("\n||====================|");
        for (Piece piece : w_p_t)
            System.out.print("|===========");
        if (w_p_t.size() == 0)
            System.out.print("|");
        else
            System.out.print("||");
        System.out.println(" ");
    }

    @Override
    public void movePiece() {
        String posOne = validatePosition(PIECE_TO_MOVE_MESSAGE);
        String posTwo = validatePosition(WHERE_TO_MOVE_MESSAGE);
        try {
            gameService.movePiece(posOne,posTwo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void showTurn() {
        System.out.println(TURN_MESSAGE + gameService.getTurn());
        System.out.println(" ");
    }

    @Override
    public void update() {
        clearConsole();
        showBoard();
        showTakenPieces();
    }

    private String validatePosition(String message) {
        String pos;
        boolean valid;
        do {
            valid = true;
            requestUserInput(message);
            pos = getInput().toLowerCase().trim();
            if (pos.length() != 2) {
                valid = false;
                System.out.println(INVALID_POSITION_MESSAGE);
            }
//            char letter = pos.charAt(0);
//            int number = pos.charAt(1);
//            if (letter < 'a' || letter > 'h' || number > 8) {
//                valid = false;
//                System.out.println(INVALID_POSITION_MESSAGE);
//            }
        } while (!valid);
        return pos;
    }
    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
