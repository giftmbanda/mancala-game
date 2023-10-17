package com.giftmbanda.mancalagame.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pit implements Serializable {
    // Pit is a container of seeds, objects begins with a set of number of seeds.
    // seeds increment by 1 at a time, or decrement to 0, Pit store can increment by more than 1
    private int id; // represent the index of the pit 1 to 14, 7 and 14 being the pit stores
    private int seeds;

    public void clear() {
        this.seeds = 0;
    }

    public void sow() {
        this.seeds++;
    }

    public void addSeeds(int seeds) {
        this.seeds += seeds;
    }

    @JsonIgnore
    public Boolean isEmpty() {
        return this.seeds == 0;
    }
}
