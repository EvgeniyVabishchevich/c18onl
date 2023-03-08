package com.tms.factory.raceSoldiers;

import com.tms.factory.soldiers.Soldier;
import com.tms.factory.races.Race;

public interface RaceSoldier extends Race, Soldier {
    default void printInfo() {
        tellRace();
        tellType();
        tellHitType();
    }
}
