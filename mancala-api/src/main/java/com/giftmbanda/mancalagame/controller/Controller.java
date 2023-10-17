package com.giftmbanda.mancalagame.controller;

import com.giftmbanda.mancalagame.constants.Constants;
import com.giftmbanda.mancalagame.exception.ErrorResponse;
import com.giftmbanda.mancalagame.exception.GameNotFoundException;
import com.giftmbanda.mancalagame.model.Game;
import com.giftmbanda.mancalagame.service.GameService;
import com.giftmbanda.mancalagame.service.SowService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.giftmbanda.mancalagame.constants.Constants.defaultPitSeeds;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/game/")
public class Controller {

    @Autowired
    private GameService gameService;

    @Autowired
    private SowService sowService;

    @Operation(summary = "Create a game", description = "Creates a new Mancala game and returns a Game Schema as a response.")
    @PostMapping("create")
    public ResponseEntity<Game> createGame() throws Exception {
        Game game = gameService.createGame(defaultPitSeeds);
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @Operation(summary = "Sow seeds in a pit", description = "Sow seeds in a specific pit of a Mancala game by providing the game ID and pit ID.")
    @PutMapping("{gameId}/pit/{pitId}")
    public ResponseEntity<?> updateGame(@PathVariable("gameId") String gameId, @PathVariable("pitId") int pitId) throws Exception {
        log.info("Sowing seeds in pit. Game ID: " + gameId + ", Pit Index: " + pitId);

        if (pitId < 1 || pitId >= Constants.leftPitStoreId || pitId == Constants.rightPitStoreId) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Invalid pit index (" + pitId + "). The pit index should be between 1 and 6 or 8 and 13. Please provide a valid pit index.");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        Game game = gameService.getGameById(gameId); // retrieve a game by passed game ID
        Game sowedGame = sowService.sow(game, pitId); // Sow game
        Game updatedGame = gameService.updateGame(sowedGame); // updated the sowed game
        return new ResponseEntity<>(updatedGame, HttpStatus.OK);
    }

    @Operation(summary = "Retrieve game information", description = "Retrieve information about a specific Mancala game by providing the game ID.")
    @GetMapping("{gameId}")
    public ResponseEntity<?> getGameById(@PathVariable("gameId") String gameId) {
        log.info("Retrieving game information. Game ID: " + gameId);
        Game game = gameService.getGameById(gameId);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleGameNotFound(GameNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
