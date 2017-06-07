package myapp.presentationmodel.presentationstate;

import org.opendolphin.core.BasePresentationModel;

import myapp.util.veneer.EnumAttributeFX;
import myapp.util.Language;
import myapp.util.veneer.StringAttributeFX;
import myapp.util.veneer.PresentationModelVeneer;
public class ApplicationState extends PresentationModelVeneer {

    public ApplicationState(BasePresentationModel pm) {
        super(pm);
    }

    public final StringAttributeFX         applicationTitle = new StringAttributeFX(getPresentationModel() , ApplicationStateAtt.APPLICATION_TITLE);
}
