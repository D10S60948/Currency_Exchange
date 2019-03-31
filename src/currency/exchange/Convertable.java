package currency.exchange;

public interface Convertable {
	public double toLocalCurrency(double amount);
	public double fromLocalCurrency(double amount);
}
