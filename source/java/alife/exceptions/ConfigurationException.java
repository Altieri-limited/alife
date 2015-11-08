package alife.exceptions;

public class ConfigurationException extends RuntimeException {
	private static final long serialVersionUID = 8499201959730983807L;

	public ConfigurationException(String message) {
		super(message);
	}
}
