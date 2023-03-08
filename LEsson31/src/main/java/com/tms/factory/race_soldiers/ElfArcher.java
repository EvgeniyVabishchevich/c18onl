package com.tms.factory.race_soldiers;

import com.tms.factory.soldiers.Archer;

public class ElfArcher extends Archer implements RaceSoldier {
    @Override
    public void tellRace() {
        System.out.println("I'm elf archer");
    }
}
