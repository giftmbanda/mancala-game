package com.giftmbanda.mancalagame.repository;

import com.giftmbanda.mancalagame.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game, String> {
}
