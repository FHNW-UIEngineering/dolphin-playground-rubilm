package myapp.presentationmodel.canton;

public interface CantonCommands {
	String LOAD_CANTON = unique("loadCanton");

	static String unique(String key) {
		return CantonCommands.class.getName() + "." + key;
	}

}
