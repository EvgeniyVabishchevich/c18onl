package com.tms.factory.race_soldiers;

import com.tms.factory.soldiers.Warrior;

public class HumanWarrior extends Warrior implements RaceSoldier {
    @Override
    public void tellRace() {
        System.out.println("I'm human warrior");
    }
}
