package myapp.controller;

import myapp.presentationmodel.canton.EnergyCommands;
import org.opendolphin.core.Dolphin;
import org.opendolphin.core.server.DTO;
import org.opendolphin.core.server.ServerPresentationModel;
import org.opendolphin.core.server.comm.ActionRegistry;

import myapp.presentationmodel.BasePmMixin;
import myapp.presentationmodel.PMDescription;
import myapp.presentationmodel.canton.Energy;
import myapp.service.SomeService;
import myapp.util.Controller;

class EnergyController extends Controller implements BasePmMixin {

    private final SomeService service;

    private Energy energyProxy;

    EnergyController(SomeService service) {
        this.service = service;
    }

    @Override
    public void registerCommands(ActionRegistry registry) {
        registry.register(EnergyCommands.LOAD_ENERGY, ($, $$) -> loadEnergy());
    }

    public ServerPresentationModel loadEnergy() {
        DTO dto = service.loadSomeEntity();
        ServerPresentationModel pm = createPM(PMDescription.ENERGY, dto);

        energyProxy.getPresentationModel().syncWith(pm);

        return pm;
    }

    @Override
    protected void initializeBasePMs() {
        ServerPresentationModel pm = createProxyPM(PMDescription.ENERGY, ENERGY_PROXY_PM_ID);

        energyProxy = new Energy(pm);
    }

    @Override
    protected void setDefaultValues() {
    }

    @Override
    protected void setupValueChangedListener() {
    }

    @Override
    public Dolphin getDolphin() {
        return getServerDolphin();
    }
}
