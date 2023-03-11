package com.tms.factory.race_soldiers;

import com.tms.factory.soldiers.Warrior;

public class OrcWarrior extends Warrior implements RaceSoldier {
    @Override
    public void tellRace() {
        System.out.println("I'm orc warrior");
    }
}
