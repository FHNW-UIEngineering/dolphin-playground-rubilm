package myapp.presentationmodel;

import org.opendolphin.core.BasePresentationModel;
import org.opendolphin.core.Dolphin;

import myapp.presentationmodel.canton.Energy;
import myapp.presentationmodel.presentationstate.ApplicationState;

public interface BasePmMixin {
    //todo: for all your basePMs (as delivered by your Controllers) specify constants and getter-methods like these
    String ENERGY_PROXY_PM_ID = PMDescription.ENERGY.pmId(-777L);

    default BasePresentationModel getEnergyProxyPM() {
        return (BasePresentationModel) getDolphin().getAt(ENERGY_PROXY_PM_ID);
    }

    default Energy getEnergyProxy() {
        return new Energy(getEnergyProxyPM());
    }

    // always needed
    String APPLICATION_STATE_PM_ID = PMDescription.APPLICATION_STATE.pmId(-888);

    default BasePresentationModel getApplicationStatePM() {
        return (BasePresentationModel) getDolphin().getAt(APPLICATION_STATE_PM_ID);
    }

    default ApplicationState getApplicationState() {
        return new ApplicationState(getApplicationStatePM());
    }

    Dolphin getDolphin();
}
