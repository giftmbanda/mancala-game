package com.giftmbanda.mancalagame.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.giftmbanda.mancalagame.constants.Constants;
import com.giftmbanda.mancalagame.exception.PitNotFoundException;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.giftmbanda.mancalagame.constants.Constants.*;

@Setter
@Getter
@Document(collection = "games")
public class Game implements Serializable {

    @Id
    private String id; // MongoDB stores object id's as strings
    private List<Pit> pits;
    private Player player;
    @JsonIgnore
    private int currentPitIndex;

    public Game() {
        this(defaultPitSeeds);
    }

    public Game(int seeds) {
        this.pits = new ArrayList<>();

        for (int pitId = 1; pitId <= Constants.totalPits; pitId++) {
            if (pitId == Constants.rightPitStoreId || pitId == Constants.leftPitStoreId) {
                pits.add(new Store(pitId));
            } else {
                pits.add(new Pit(pitId, seeds));
            }
        }
    }

    public Game(String id, int seeds) {
        this(seeds);
        this.id = id;
    }

    public Pit getPit(int pitIndex) {
        if (pitIndex < 1 || pitIndex > pits.size()) {
            throw new PitNotFoundException("Pit index (" + pitIndex + ") not found");
        }
        return pits.get(pitIndex - 1);
    }

}
