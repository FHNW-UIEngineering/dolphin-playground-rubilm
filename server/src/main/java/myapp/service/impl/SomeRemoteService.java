package myapp.service.impl;

import java.util.Random;

import org.opendolphin.core.server.DTO;

import myapp.presentationmodel.canton.EnergyAtt;
import myapp.service.SomeService;
import myapp.util.DTOMixin;

public class SomeRemoteService implements SomeService, DTOMixin {
    String[] anlagenschluessels = {"E21669010901020020010000000000001"
                                    , "E118840197244AAAAA100001997600001"
                                    , "E2191501SOXX000000010025807100010"};

    String[] orts = {"Bergtheim"
            , "Buetthard"
            , "Gelchsheim"};

    int[] plzs = {97241
            , 97244
            , 97255};

    @Override
    public DTO loadSomeEntity() {
        long id = createNewId();

        Random random = new Random();
        int randomIndex = random.nextInt(3);

        String anlagenschluessel = anlagenschluessels[randomIndex];
        String ort = orts[randomIndex];
        int plz = plzs[randomIndex];

        return new DTO(createSlot(EnergyAtt.ID      , id     , id),
                       createSlot(EnergyAtt.ORT    , ort   , id),
                       createSlot(EnergyAtt.PLZ     , plz    , id),
                       createSlot(EnergyAtt.ANLAGENSCHLUESSEL, anlagenschluessel, id));
    }
}
