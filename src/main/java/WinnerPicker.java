import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WinnerPicker {

    private char winner = ' ';

    public char getWinner() {
        return winner;
    }

    public void pickWinner(HashMap<Integer,Box> fullBoxes) {

        //give values for column and row
        for (Map.Entry<Integer, Box> entry : fullBoxes.entrySet()) {
            Integer boxIndex = entry.getKey();

            int column = 0;
            int row = 0;

            switch (boxIndex) {
                case 0:
                    column = 1;
                    row = 1;
                    break;
                case 1:
                    column = 2;
                    row = 1;
                    break;
                case 2:
                    column = 3;
                    row = 1;
                    break;
                case 3:
                    column = 1;
                    row = 2;
                    break;
                case 4:
                    column = 2;
                    row = 2;
                    break;
                case 5:
                    column = 3;
                    row = 2;
                    break;
                case 6:
                    column = 1;
                    row = 3;
                    break;
                case 7:
                    column = 2;
                    row = 3;
                    break;
                case 8:
                    column = 3;
                    row = 3;
                    break;
            }

            entry.getValue().setColumn(column);
            entry.getValue().setRow(row);
        }

        //check lines for column
        long columnsX = fullBoxes.entrySet().stream()
                .map(entry -> entry.getValue())
                .filter(box -> box.getToken() == 'X')
                .map(box -> box.getColumn())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 2)
                .map(e -> e.getKey())
                .count();

        long columnsO = fullBoxes.entrySet().stream()
                .map(entry -> entry.getValue())
                .filter(box -> box.getToken() == 'O')
                .map(box -> box.getColumn())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 2)
                .map(e -> e.getKey())
                .count();

        //check lines for rows
        long rowsX = fullBoxes.entrySet().stream()
                .map(entry -> entry.getValue())
                .filter(box -> box.getToken() == 'X')
                .map(box -> box.getRow())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 2)
                .map(e -> e.getKey())
                .count();

        long rowsO = fullBoxes.entrySet().stream()
                .map(entry -> entry.getValue())
                .filter(box -> box.getToken() == 'O')
                .map(box -> box.getRow())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 2)
                .map(e -> e.getKey())
                .count();

        if (columnsX > 0|| rowsX > 0) {
            winner = 'X';
            announceWinner(winner);
        } else if (columnsO > 0 || rowsO > 0) {
            winner = 'O';
            announceWinner(winner);
        }

        //check lines for diagonals
        Set<Integer> diagonalX = fullBoxes.entrySet().stream()
                .map(entry -> entry.getValue())
                .filter(box ->box.getToken() == 'X')
                .map(box -> box.getPosition())
                .collect(Collectors.toSet());

        if(diagonalX.contains(4)) {
            if(diagonalX.contains(0)) {
                if(diagonalX.contains(8)) {
                    winner = 'X';
                    announceWinner(winner);
                }
            } else if(diagonalX.contains(2)) {
                if(diagonalX.contains(6)) {
                    winner = 'X';
                    announceWinner(winner);
                }
            }
        }

        Set<Integer> diagonalO = fullBoxes.entrySet().stream()
                .map(entry -> entry.getValue())
                .filter(box ->box.getToken() == 'O')
                .map(box -> box.getPosition())
                .collect(Collectors.toSet());

        if(diagonalO.contains(4)) {
            if(diagonalO.contains(0)) {
                if(diagonalO.contains(8)) {
                    winner = 'O';
                    announceWinner(winner);
                }
            } else if(diagonalO.contains(2)) {
                if(diagonalO.contains(6)) {
                    winner = 'O';
                    announceWinner(winner);
                }
            }
        }
    }

    public void announceWinner(char winner) {
        Alert gameOver = new Alert(Alert.AlertType.CONFIRMATION);
        gameOver.setTitle("WINNER!!!");
        gameOver.setHeaderText(winner + " wins. Do you want to play again?");
        Optional<ButtonType> result1 = gameOver.showAndWait();
        if(result1.get() == ButtonType.OK) {
            Game newGame = new Game();
            newGame.restart();
        } else {
            Platform.exit();
        }
    }
}
