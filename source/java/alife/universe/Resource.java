package alife.universe;

public class Resource {
	public enum ResourceType {
		FOOD
	}
	
	private long amount;
	private ResourceType type;
	
	public long getAmount() {
		return amount;
	}
	
	public ResourceType getType() {
		return type;
	}
}
