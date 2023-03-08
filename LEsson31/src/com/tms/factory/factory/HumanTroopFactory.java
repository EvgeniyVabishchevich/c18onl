package test.factory;

import test.Troop;
import test.raceSoldiers.*;

public class HumanTroopFactory implements TroopsFactory {
    @Override
    public Troop createTroop() {
        Troop humanTroop = new Troop();
        humanTroop.setArcher(new HumanArcher());
        humanTroop.setWarrior(new HumanWarrior());
        humanTroop.setWizard(new HumanWizard());
        return humanTroop;
    }
}
