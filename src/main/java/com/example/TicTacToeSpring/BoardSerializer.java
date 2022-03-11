package com.example.TicTacToeSpring;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BoardSerializer {

    public static Cell[][] deserialize(String serializedBoard) {
        return Arrays.stream(serializedBoard.split(";"))
                .map(r -> Arrays.stream(r.split(",")).map(Cell::valueOf).toArray(Cell[]::new))
                .toArray(Cell[][]::new);
    }

    public static String serialize(Cell[][] board) {
        return Arrays.stream(board)
                .map(r -> Arrays.stream(r).map(Cell::toString).collect(Collectors.joining(",")))
                .collect(Collectors.joining(";"));
    }
}
