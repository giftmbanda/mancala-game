package com.giftmbanda.mancalagame.service;

import com.giftmbanda.mancalagame.model.Game;

public interface SowService {
    Game sow(Game game, int pitIndex) throws Exception;
}
