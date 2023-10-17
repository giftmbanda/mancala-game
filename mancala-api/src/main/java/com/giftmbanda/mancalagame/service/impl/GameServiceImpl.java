package com.giftmbanda.mancalagame.service.impl;

import com.giftmbanda.mancalagame.exception.GameNotFoundException;
import com.giftmbanda.mancalagame.model.Game;
import com.giftmbanda.mancalagame.repository.GameRepository;
import com.giftmbanda.mancalagame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game createGame(int seeds) {
        Game game = new Game(seeds);
        gameRepository.save(game);
        return game;
    }

    @Override
    @Cacheable(value = "games", key = "'game-' + #gameId")
    public Game getGameById(String id) throws GameNotFoundException {
        Optional<Game> gameOptional = gameRepository.findById(id);

        if (gameOptional.isEmpty()) {
            throw new GameNotFoundException(id);
        }
        return gameOptional.get();
    }

    @CacheEvict(value = "games", key = "'game-' + #gameId") // Cache individual pit sowing results
    public Game updateGame(Game game) {
        return gameRepository.save(game);
    }
}
