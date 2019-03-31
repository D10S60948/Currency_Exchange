package currency.exchange;

/**
 * Each class uses this interface is updateable and  
 * execution of the update method makes the subject to be up to do.
 * No return values and no arguments but only the object being updated.
 */
public interface Updateable {
	public void update();
}
