package tcioextractor;

public class Parameter {
	ParameterType type;
	private String value;
	public Parameter(String value) {
		this.value=value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
