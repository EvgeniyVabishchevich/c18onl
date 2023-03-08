package com.tms.factory.raceSoldiers;

import com.tms.factory.soldiers.Archer;

public class OrcArcher extends Archer implements RaceSoldier {
    @Override
    public void tellRace() {
        System.out.println("I'm orc archer");
    }
}
