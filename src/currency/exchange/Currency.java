package currency.exchange;

/**
 * Represents a currency.
 * @version 1.2
 * @since 1.0
 */
public class Currency implements Convertable
{
	private String symbol;
	private double rate;
	
	/**
	 * Creates a currency object with the specified symbol (name)
	 * @param symbol: the currency's symbol (name/code), which is also the class's identifier.
	 * @param rate: the current rate of the currency (versus the Israeli Shekel).
	 * In case no rate was not given it creates the currency with value rate zero.
	 */
	public Currency(String symbol, double rate) 
	{
		setSymbol(symbol);
		setRate(rate);
	}
	public Currency(String symbol) 
	{
		this(symbol,0.0);
	}
	
	
	/**
	 * Gets the currency's current rate.
	 * @return A double representing the currency's current rate.
	 */
	public double getRate() {
		return rate;
	}
	/**
	 * Gets the currency's Symbol.
	 * @return A string representing the currency's symbol.
	 */
	public String getSymbol() {
		return symbol;
	}
	
	/**
	 * Sets the currency's rate.
	 * @param rate: A double containing the currency's last known rate.
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}
	/**
	 * Sets the currency's symbol.
	 * @param symbol: A string containing the currency's symbol. 
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * Returns the amount converted from the object's currency to ILS(Israeli Shekel).
	 * Since the rate is in comparison to the ILS, the returned amount is in ILS.
	 * @param amountToConvert: Amount to convert. Represented in the object's currency. 
	 * @return The equivalent amount represented in ILS.
	 */
	public double toLocalCurrency(double amountToConvert)
	{
		return getRate()*amountToConvert;
	}
	/**
	 * Given the amount in ILS, it returns the equivalent amount in object's currency.
	 * @param shekelsToConvert: Amount to convert. Represented in the ILS. 
	 * @return The equivalent amount represented in the object's currency.
	 */	
	public double fromLocalCurrency(double shekelsToConvert)
	{
		return shekelsToConvert/getRate();
	}	
	
	/**
	 * Where a string is required and the object name appears with no additional characters
	 * this method will be executed and will show the currency's symbol instead.
	 */
	@Override
	public String toString() {
		return getSymbol();
	}
}