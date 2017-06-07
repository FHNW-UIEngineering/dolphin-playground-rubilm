package myapp.presentationmodel.energy;

import org.opendolphin.core.BasePresentationModel;

import myapp.util.veneer.IntegerAttributeFX;
import myapp.util.veneer.LongAttributeFX;
import myapp.util.veneer.StringAttributeFX;
import myapp.util.veneer.PresentationModelVeneer;

public class Energy extends PresentationModelVeneer {
    public Energy(BasePresentationModel pm) {
        super(pm);
    }

    public final LongAttributeFX    id      = new LongAttributeFX(getPresentationModel()   , EnergyAtt.ID);
    public final IntegerAttributeFX   plz    = new IntegerAttributeFX (getPresentationModel() , EnergyAtt.PLZ);
    public final StringAttributeFX ort     = new StringAttributeFX(getPresentationModel(), EnergyAtt.ORT);
    public final StringAttributeFX anlagenschluessel = new StringAttributeFX(getPresentationModel(), EnergyAtt.ANLAGENSCHLUESSEL);
}
