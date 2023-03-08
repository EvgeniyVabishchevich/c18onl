package com.tms.factory.raceSoldiers;

import com.tms.factory.soldiers.Warrior;

public class HumanWarrior extends Warrior implements RaceSoldier {
    @Override
    public void tellRace() {
        System.out.println("I'm human warrior");
    }
}
