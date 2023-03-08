package com.tms.factory.race_soldiers;

import com.tms.factory.soldiers.Soldier;
import com.tms.factory.races.Race;

public interface RaceSoldier extends Race, Soldier {
    default void printInfo() {
        tellRace();
        tellType();
        tellHitType();
    }
}
