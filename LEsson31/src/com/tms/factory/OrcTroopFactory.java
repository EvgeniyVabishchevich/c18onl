package test.factory;

import test.Troop;
import test.raceSoldiers.OrcArcher;
import test.raceSoldiers.OrcWarrior;
import test.raceSoldiers.OrcWizard;

public class OrcTroopFactory implements TroopsFactory {

    @Override
    public Troop createTroop() {
        Troop orcTroop = new Troop();
        orcTroop.setArcher(new OrcArcher());
        orcTroop.setWarrior(new OrcWarrior());
        orcTroop.setWizard(new OrcWizard());
        return orcTroop;
    }
}
