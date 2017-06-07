package myapp.presentationmodel.energy;

public interface EnergyCommands {
	String LOAD_ENERGY = unique("loadEnergy");

	static String unique(String key) {
		return EnergyCommands.class.getName() + "." + key;
	}

}
