package com.example.TicTacToeSpring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    TicTacToeRepository repository;

    public GameController(TicTacToeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/startGame")
    public TicTacToeGame getGames() {
        return new TicTacToeGame(repository.save(new TicTacToeMoveEntity()));
    }

    @PostMapping("/move/{i}/{j}")
    public TicTacToeGame move(@PathVariable Integer i, @PathVariable Integer j) {
        var previous = repository.findTopByOrderByIdDesc();

        if (previous.isEmpty()) throw new IllegalArgumentException("Game not found");

        var newGame = new TicTacToeGame(previous.get());

        if (!newGame.isMoveValid(i, j)) throw new IllegalArgumentException("Move not valid");
        if (newGame.isGameOver()) throw new IllegalArgumentException("Game is over");

        newGame.makeMove(i, j);
        repository.save(new TicTacToeMoveEntity(newGame));
        return newGame;
    }
}
