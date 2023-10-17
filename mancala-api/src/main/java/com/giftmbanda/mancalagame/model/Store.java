package com.giftmbanda.mancalagame.model;

public class Store extends Pit {
    // A store is a type of pit, unlike a regular pit a store when
    // objects begins empty then gathers seeds throughout out the game
    public Store(Integer id) {
        super(id, 0);
    }
}
