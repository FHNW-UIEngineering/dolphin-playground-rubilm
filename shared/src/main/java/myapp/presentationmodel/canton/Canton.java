package myapp.presentationmodel.canton;

import org.opendolphin.core.BasePresentationModel;

import myapp.util.veneer.IntegerAttributeFX;
import myapp.util.veneer.LongAttributeFX;
import myapp.util.veneer.StringAttributeFX;
import myapp.util.veneer.PresentationModelVeneer;

public class Canton extends PresentationModelVeneer {
    public Canton(BasePresentationModel pm) {
        super(pm);
    }

    public final LongAttributeFX    id      = new LongAttributeFX(getPresentationModel()   , CantonAtt.ID);
    public final IntegerAttributeFX   plz    = new IntegerAttributeFX (getPresentationModel() , CantonAtt.PLZ);
    public final StringAttributeFX ort     = new StringAttributeFX(getPresentationModel(), CantonAtt.ORT);
    public final StringAttributeFX anlagenschluessel = new StringAttributeFX(getPresentationModel(), CantonAtt.ANLAGENSCHLUESSEL);
}
