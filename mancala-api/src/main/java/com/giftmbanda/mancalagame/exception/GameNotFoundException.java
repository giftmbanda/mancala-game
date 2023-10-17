package com.giftmbanda.mancalagame.exception;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(String gameId) {
        super("Game not found with ID: " + gameId);
    }
}