package myapp;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import myapp.presentationmodel.canton.EnergyCommands;
import org.opendolphin.core.Dolphin;
import org.opendolphin.core.client.ClientDolphin;

import myapp.presentationmodel.BasePmMixin;
import myapp.presentationmodel.canton.Energy;
import myapp.presentationmodel.presentationstate.ApplicationState;
import myapp.util.ViewMixin;
import myapp.util.veneer.AttributeFX;

class RootPane extends GridPane implements ViewMixin, BasePmMixin {
    private final ClientDolphin clientDolphin;

    private Label headerLabel;

    private Label idLabel;
    private TextField idField;

    private Label ortLabel;
    private TextField ortField;

    private Label plzLabel;
    private TextField plzField;

    private Label     anlageLabel;
    private TextField anlageField;

    private Button nextButton;

    private final Energy energyProxy;

    //always needed
    private final ApplicationState ps;

    RootPane(ClientDolphin clientDolphin) {
        this.clientDolphin = clientDolphin;
        ps = getApplicationState();
        energyProxy = getEnergyProxy();

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
        idField = new TextField();
        idField.setDisable(true);

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
        nextButton.setOnAction(   $ -> clientDolphin.send(EnergyCommands.LOAD_ENERGY));
    }

    @Override
    public void setupValueChangedListeners() {

    }

    @Override
    public void setupBindings() {
        setupBindings_VeneerBased();
    }

    private void setupBindings_VeneerBased(){
        headerLabel.textProperty().bind(energyProxy.ort.valueProperty());

        idLabel.textProperty().bind(energyProxy.id.labelProperty());
        idField.textProperty().bind(energyProxy.id.valueProperty().asString());

        setupBinding(ortLabel, ortField, energyProxy.ort);
        setupBinding(plzLabel, plzField, energyProxy.plz);
        setupBinding(anlageLabel, anlageField, energyProxy.anlagenschluessel);
    }

    private void setupBinding(Label label, TextField field, AttributeFX attribute) {
        setupBinding(label, attribute);

        field.textProperty().bindBidirectional(attribute.userFacingStringProperty());
    }


    private void setupBinding(Label label, AttributeFX attribute){
        label.textProperty().bind(Bindings.createStringBinding(() -> attribute.getLabel() + (attribute.isMandatory() ? " *" : "  "),
                                                               attribute.labelProperty(),
                                                               attribute.mandatoryProperty()));
    }
}
