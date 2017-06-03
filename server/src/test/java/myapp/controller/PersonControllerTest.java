package myapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.opendolphin.core.server.DTO;
import org.opendolphin.core.server.DefaultServerDolphin;
import org.opendolphin.core.server.ServerConnector;
import org.opendolphin.core.server.ServerModelStore;
import org.opendolphin.core.server.ServerPresentationModel;

import myapp.presentationmodel.PMDescription;
import myapp.presentationmodel.canton.Canton;
import myapp.presentationmodel.canton.CantonAtt;
import myapp.service.SomeService;
import myapp.util.DTOMixin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Dieter Holz
 */
public class PersonControllerTest {
    CantonController controller;
    ServerModelStore serverModelStore;
    ServiceStub service;

    @Before
    public void setup() {
        service = new ServiceStub();
        controller = new CantonController(service);

        serverModelStore = new TestModelStore();
        controller.setServerDolphin(new DefaultServerDolphin(serverModelStore, new ServerConnector()));
    }

    @Test
    public void testInitializeBasePMs(){
        //given

        //when
        controller.initializeBasePMs();
        Canton p = controller.getPersonProxy();

        //then
        assertNotNull(p);
        assertFalse(p.isDirty());
    }

    private class ServiceStub implements SomeService, DTOMixin {
        int saveCounter;

        @Override
        public DTO loadSomeEntity() {
            return createDTO(PMDescription.CANTON);
        }

        @Override
        public void save(List<DTO> dtos) {
            saveCounter++;
        }
    }

    private class TestModelStore extends ServerModelStore{
        TestModelStore(){
            setCurrentResponse(new ArrayList<>());
        }
    }
}