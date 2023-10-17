package com.giftmbanda.mancalagame.service;

import com.giftmbanda.mancalagame.model.Game;

public interface GameService {
    Game createGame(int stones);

    Game getGameById(String id);

    Game updateGame(Game game);
}
