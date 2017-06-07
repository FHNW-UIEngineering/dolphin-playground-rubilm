package myapp.controller;

import java.util.List;

import myapp.presentationmodel.canton.CantonCommands;
import org.opendolphin.core.Dolphin;
import org.opendolphin.core.server.DTO;
import org.opendolphin.core.server.ServerPresentationModel;
import org.opendolphin.core.server.comm.ActionRegistry;

import myapp.presentationmodel.BasePmMixin;
import myapp.presentationmodel.PMDescription;
import myapp.presentationmodel.canton.Canton;
import myapp.service.SomeService;
import myapp.util.Controller;

class CantonController extends Controller implements BasePmMixin {

    private final SomeService service;

    private Canton cantonProxy;

    CantonController(SomeService service) {
        this.service = service;
    }

    @Override
    public void registerCommands(ActionRegistry registry) {
        registry.register(CantonCommands.LOAD_CANTON, ($, $$) -> loadCanton());
    }

    public ServerPresentationModel loadCanton() {
        DTO dto = service.loadSomeEntity();
        ServerPresentationModel pm = createPM(PMDescription.CANTON, dto);

        cantonProxy.getPresentationModel().syncWith(pm);

        return pm;
    }

    @Override
    protected void initializeBasePMs() {
        ServerPresentationModel pm = createProxyPM(PMDescription.CANTON, CANTON_PROXY_PM_ID);

        cantonProxy = new Canton(pm);
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
