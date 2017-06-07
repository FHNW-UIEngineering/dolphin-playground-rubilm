package myapp.presentationmodel.canton;

import myapp.presentationmodel.PMDescription;
import myapp.util.AttributeDescription;
import myapp.util.ValueType;

public enum EnergyAtt implements AttributeDescription {
    ID(ValueType.ID),
    PLZ(ValueType.INT),
    ORT(ValueType.STRING),
    ANLAGENSCHLUESSEL(ValueType.STRING);

    private final ValueType valueType;

    EnergyAtt(ValueType type) {
        valueType = type;
    }

    @Override
    public ValueType getValueType() {
        return valueType;
    }

    @Override
    public PMDescription getPMDescription() {
        return PMDescription.ENERGY;
    }
}
