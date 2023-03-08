package test.raceSoldiers;

import test.soldiers.Soldier;
import test.races.Race;

public interface RaceSoldier extends Race, Soldier {
    default void printInfo() {
        tellRace();
        tellType();
        tellHitType();
    }
}
