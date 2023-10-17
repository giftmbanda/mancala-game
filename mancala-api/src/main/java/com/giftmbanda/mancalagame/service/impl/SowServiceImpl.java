package com.giftmbanda.mancalagame.service.impl;

import com.giftmbanda.mancalagame.constants.Constants;
import com.giftmbanda.mancalagame.model.Game;
import com.giftmbanda.mancalagame.model.Pit;
import com.giftmbanda.mancalagame.model.Player;
import com.giftmbanda.mancalagame.service.SowService;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service

public class SowServiceImpl implements SowService {
    public Game sow(Game game, int pitId) {

        if (pitId == Constants.rightPitStoreId || pitId == Constants.leftPitStoreId) {
            return game;
        }

        if (game.getPlayer() == null) {
            if (pitId < Constants.rightPitStoreId) {
                game.setPlayer(Player.PLAYER_A);
            } else {
                game.setPlayer(Player.PLAYER_B);
            }
        }

        if (game.getPlayer() == Player.PLAYER_A && pitId > Constants.rightPitStoreId
                || game.getPlayer() == Player.PLAYER_B && pitId < Constants.rightPitStoreId) {
            return game;
        }

        Pit pit = game.getPit(pitId);
        int seeds = pit.getSeeds();

        if (seeds == Constants.emptySeed) {
            return game;
        }

        pit.setSeeds(Constants.emptySeed);
        game.setCurrentPitIndex(pitId);

        IntStream.range(0, seeds -1).forEach(index-> sowRight(game, false));

        sowRight(game, true);
        int currentPitIndex = game.getCurrentPitIndex();

        if (currentPitIndex != Constants.rightPitStoreId && currentPitIndex != Constants.leftPitStoreId) {
            game.setPlayer(nextPlayer(game.getPlayer()));
        }
        return game;
    }

    private void sowRight(Game game, Boolean lastSeed) {

        int currentPitIndex = game.getCurrentPitIndex() % Constants.totalPits + 1;
        Player player = game.getPlayer();

        if ((currentPitIndex == Constants.rightPitStoreId && player == Player.PLAYER_B)
                || (currentPitIndex == Constants.leftPitStoreId && player == Player.PLAYER_A)) {
            currentPitIndex = currentPitIndex % Constants.totalPits + 1;
        }

        game.setCurrentPitIndex(currentPitIndex);
        Pit pit = game.getPit(currentPitIndex);

        if (!lastSeed || currentPitIndex == Constants.rightPitStoreId || currentPitIndex == Constants.leftPitStoreId) {
            pit.sow();
            return;
        }

        Pit oppositePit = game.getPit(Constants.totalPits - currentPitIndex);

        if (pit.isEmpty() && !oppositePit.isEmpty()) {

            int oppositeStones = oppositePit.getSeeds();
            oppositePit.clear();

            int pitStoreIndex = (currentPitIndex < Constants.rightPitStoreId) ? Constants.rightPitStoreId : Constants.leftPitStoreId;

            Pit pitStore = game.getPit(pitStoreIndex);
            pitStore.addSeeds(oppositeStones + 1);
            return;
        }

        pit.sow();
    }

    public Player nextPlayer(Player currentTurn) {
        return (currentTurn == Player.PLAYER_A) ? Player.PLAYER_B : Player.PLAYER_A;
    }
}
