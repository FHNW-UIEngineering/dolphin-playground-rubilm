package myapp.controller;

import org.opendolphin.core.server.action.DolphinServerAction;
import org.opendolphin.core.server.comm.ActionRegistry;

import myapp.service.SomeService;

/**
 * At the reception all controllers check in.
 *
 */

public class Reception extends DolphinServerAction {
    private SomeService service;

    public Reception(SomeService myService) {
        this.service = myService;
    }

    public void registerIn(ActionRegistry registry) {
        // todo register all your controllers here.
        getServerDolphin().register(new EnergyController(service));

        //always needed
        getServerDolphin().register(new ApplicationStateController());
    }
}
