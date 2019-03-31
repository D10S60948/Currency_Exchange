package currency.exchange;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Vector;

import org.w3c.dom.NodeList;

/**
 * Represents the currencies table.
 * @version 1.4
 * @since 1.0
 */
public class Table implements Updateable
{
	private Vector<Currency> currency;
	private Vector<String> columnNames;
	private Vector<Vector> data;
	private Date lastUpdateDate;
	private Time lastUpdateTime;

	/**
	 * Creates a table.
	 * After initialisation will be adjusted to represent currencies in it.
	 * This table attributes will be the currency's symbol and the currency's rate.
	 */
	public Table()
	{		
	    data = new Vector<Vector>();
		columnNames = new Vector<String>();
	    columnNames.addElement("Symbol");
	    columnNames.addElement("Rate");
	    currency = new Vector<Currency>();
	}

	
	/**
	 * Represents date in accordance to the Gregorian calendar.
	 * Is being used by the outer class 'Table'.
	 */
	class Date
	{
		private int day;
		private int month;
		private int year;
		
		/**
		 * Creates a 'Date' object.
		 * @param day: Represents the day in the month (range of 1-31).
		 * @param month: Represents the month (range of 1-12).
		 * @param year: Represents the year in the month (range of 1-31).
		 */
		public Date(int day, int month, int year) 
		{
			setDay(day);
			setMonth(month);
			setYear(year);
		}
		
		/**
		 * The variable setters. Being used to set the year, month and day.
		 * @param year: An int containing the year to be set.
		 * @param month: An int containing the month to be set.
		 * @param day: An int containing the day to be set.
		 */
		public void setYear(int year) {
			this.year = year;
		}
		public void setMonth(int month) {
			this.month = month;
		}
		public void setDay(int day) {
			this.day = day;
		}
		
		/**
		 * Where a string is required and the object name appears with no additional characters,
		 * this method will be executed and will show the date in the following format:
		 * 'dd/mm/yyyy'.
		 */
		@Override
		public String toString() 
		{
			return Integer.toString(this.day) + '/' + Integer.toString(this.month) + '/' + Integer.toString(this.year);
		}
	}
	/**
	 * Represents the time.
	 * Is being used by the outer class 'Table'.
	 */
	class Time
	{
		private int hour;
		private int minute;
		
		/**
		 * Creates a time object.
		 * @param hour: An int containing the hour with a number between 0 to 23.
		 * @param minute: An int containing the minute with a number between 0 to 59. 
		 */
		public Time(int hour, int minute) 
		{
			this.hour = hour;
			this.minute = minute;
		}
		
		/**
		 * 	Variable getters. Being used in order to get the hour and minute values.	
		 * @return of getHour() is an int represents the hour.
		 * @return of getMinute() is an int represents the minute.
		 */
		public int getHour() {
			return hour;
		}
		public int getMinute() {
			return minute;
		}
		/**
		 * 	Variable setters. Being used in order to set the hour and minute members.	
		 * @param hour: An int containing the hour to be set.
		 * @return minute: An int containing the minute to be set.
		 */
		public void setHour(int hour) {
			this.hour = hour;
		}
		public void setMinute(int minute) {
			this.minute = minute;
		}

		/**
		 * Adds zeros in order to present time properly.
		 * example: '15:09' instead of '15:9'.
		 */
		public String normalizeTime(int time)
		{
			if(time<10)
				return "0" + Integer.toString(time);
			return Integer.toString(time);
		}
		
		/**
		 * Where a string is required and the object name appears with no additional characters,
		 * this method will be executed and will show the time in the following format:
		 * 'hh:mm'.
		 */
		@Override
		public String toString() 
		{
			return normalizeTime(getHour()) + ':' + normalizeTime(getMinute());
		}
		
	}

	/**
	 * Gets the last date the table was updated.
	 * @return A date represents the last update date.
	 */
	public Date getLastUpdateDate() 
	{
		return lastUpdateDate;
	}
	/**
	 * Gets the last time the table was updated.
	 * @return A time represents the last update time.
	 */
	public Time getLastUpdateTime() 
	{
		return lastUpdateTime;
	}
	/**
	 * Get the list containing the names (headers) of the columns.
	 * @return a vector of strings that each string represents a name of a column.
	 */
	public Vector<String> getColumnsName() {
		return columnNames;
	}
	/**
	 * Gets the data.
	 * @return a vector of vectors (i.e. matrix) containing all table data.
	 */
	public Vector<Vector> getData() {
		return data;
	}
	/**
	 * Gets the currency list
	 * @return a vector of currencies.
	 */
	public Vector<Currency> getCurrency() {
		return currency;
	}
	
	/**
	 * Implementation of the 'Updateable' interface.
	 * Sets the table with the online's currently updated data. 
	 */
	public void update()
	{
		// Retrieving the XML's updated data.
		ParseXML xml = new ParseXML();
		NodeList codes = xml.getCodes();
		NodeList rates = xml.getRates();
		int length = codes.getLength();
		
		// Setting the date and time accordingly to the running time.
		LocalDateTime updated = LocalDateTime.now();
		lastUpdateDate = new Date(updated.getDayOfMonth(), updated.getMonthValue(), updated.getYear());
		lastUpdateTime = new Time(updated.getHour(), updated.getMinute());
		LocalDateTime.now().getHour();
		
		// Clearing the previous data so only the updated data will appear.  
		currency.clear();
		data.clear();
		
		// Setting the table with the updated retrieved data.
		for(int i=0; i<length; i++)
	    {
			Vector<String> row = new Vector<String>();
			String code = codes.item(i).getFirstChild().getNodeValue(),
					rate = rates.item(i).getFirstChild().getNodeValue();
	        currency.add(new Currency(code, Double.parseDouble(rate)));
	        row.addElement(code);
	        row.addElement(rate);
	        data.addElement(row);
	    };
	}
	
	/**
	 * Saves all the last updated data into a log file.
	 * The log file name is 'table_logs' and it is a text file.
	 * @throws IOException
	 */
	public void saveData()
			throws IOException
 	{				
 		FileOutputStream fo = null;
 		
 		try
 		{
 			fo = new FileOutputStream("table_logs.txt");

 			// Writing to the log file the date and headers
			fo.write(("Update time: " + this.getLastUpdateDate() + " | " + this.getLastUpdateTime() + "\r\n\r\n").getBytes());
			fo.write(("SYMBOL\t\t" + "RATE\r\n" + 
						"-------------\t\t--------\r\n").getBytes());
			
			Iterator<Currency> value = this.currency.iterator(); 
			Currency curr;  
			
	        // Writing to the log file the values after iterating through the vector  
	        while (value.hasNext()) { 
	        	curr = value.next();
	            fo.write((curr.getSymbol() + "\t\t" + ((String.valueOf(curr.getRate()))) + "\r\n").getBytes()); 
	        } 		 			
 			
 		}
 		catch(IOException e)
 		{
 			e.printStackTrace();
 		}
 		finally
 		{
 			//Making sure to close the file's output stream.	 
 			if(fo!=null)
 				try {
 					fo.close();
 				}
 				catch(IOException er)
 				{
 					er.printStackTrace();
 				}
 		}
 	}
}
