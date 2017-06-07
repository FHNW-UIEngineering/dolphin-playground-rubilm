package myapp;

import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import myapp.presentationmodel.canton.CantonCommands;
import org.opendolphin.core.Dolphin;
import org.opendolphin.core.client.ClientDolphin;

import myapp.presentationmodel.BasePmMixin;
import myapp.presentationmodel.canton.Canton;
import myapp.presentationmodel.presentationstate.ApplicationState;
import myapp.util.Language;
import myapp.util.ViewMixin;
import myapp.util.veneer.AttributeFX;

/**
 * Implementation of the view details, event handling, and binding.
 *
 * @author Dieter Holz
 *
 * todo : Replace it with your application UI
 */
class RootPane extends GridPane implements ViewMixin, BasePmMixin {
    // clientDolphin is the single entry point to the PresentationModel-Layer
    private final ClientDolphin clientDolphin;

    private Label headerLabel;

    private Label idLabel;
    private Label idField;

    private Label ortLabel;
    private TextField ortField;

    private Label plzLabel;
    private TextField plzField;

    private Label     anlageLabel;
    private TextField anlageField;

    private Button nextButton;

    private final Canton cantonProxy;

    //always needed
    private final ApplicationState ps;

    RootPane(ClientDolphin clientDolphin) {
        this.clientDolphin = clientDolphin;
        ps = getApplicationState();
        cantonProxy = getCantonProxy();

        init();
    }

    @Override
    public Dolphin getDolphin() {
        return clientDolphin;
    }

    @Override
    public void initializeSelf() {
        addStylesheetFiles("/fonts/fonts.css", "/myapp/myApp.css");
        getStyleClass().add("rootPane");
    }

    @Override
    public void initializeParts() {
        headerLabel = new Label();
        headerLabel.getStyleClass().add("heading");

        idLabel = new Label();
        idField = new Label();

        ortLabel = new Label();
        ortField = new TextField();
        ortField.setDisable(true);

        plzLabel = new Label();
        plzField = new TextField();
        plzField.setDisable(true);

        anlageLabel = new Label();
        anlageField = new TextField();
        anlageField.setDisable(true);

        nextButton    = new Button("NEXT");
    }

    @Override
    public void layoutParts() {
        ColumnConstraints grow = new ColumnConstraints();
        grow.setHgrow(Priority.ALWAYS);

        getColumnConstraints().setAll(new ColumnConstraints(), grow);
        setVgrow(headerLabel, Priority.ALWAYS);

        add(headerLabel    , 0, 0, 5, 1);
        add(idLabel        , 0, 1);
        add(idField        , 1, 1, 4, 1);
        add(ortLabel, 0, 2);
        add(ortField, 1, 2, 4, 1);
        add(plzLabel, 0, 3);
        add(plzField, 1, 3, 4, 1);
        add(anlageLabel   , 0, 4);
        add(anlageField, 1, 4, 4, 1);
        add(new HBox(5, nextButton), 0, 5, 5, 1);
    }

    @Override
    public void setupEventHandlers() {
        // all events either send a command (needs to be registered in a controller on the server side)
        // or set a value on an Attribute
        nextButton.setOnAction(   $ -> clientDolphin.send(CantonCommands.LOAD_CANTON));
    }

    @Override
    public void setupValueChangedListeners() {

    }

    @Override
    public void setupBindings() {
        setupBindings_VeneerBased();
    }

    private void setupBindings_VeneerBased(){
        headerLabel.textProperty().bind(cantonProxy.ort.valueProperty());

        idLabel.textProperty().bind(cantonProxy.id.labelProperty());
        idField.textProperty().bind(cantonProxy.id.valueProperty().asString());

        setupBinding(ortLabel, ortField, cantonProxy.ort);
        setupBinding(plzLabel, plzField, cantonProxy.plz);
        setupBinding(anlageLabel, anlageField, cantonProxy.anlagenschluessel);
    }

    private void setupBinding(Label label, TextField field, AttributeFX attribute) {
        setupBinding(label, attribute);

        field.textProperty().bindBidirectional(attribute.userFacingStringProperty());
        field.tooltipProperty().bind(Bindings.createObjectBinding(() -> new Tooltip(attribute.getValidationMessage()),
                                                                  attribute.validationMessageProperty()
                                                                 ));
    }


    private void setupBinding(Label label, AttributeFX attribute){
        label.textProperty().bind(Bindings.createStringBinding(() -> attribute.getLabel() + (attribute.isMandatory() ? " *" : "  "),
                                                               attribute.labelProperty(),
                                                               attribute.mandatoryProperty()));
    }
}
