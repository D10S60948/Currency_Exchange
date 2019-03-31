/** 
 * @authors: Avishai Nudelman (ID 300012614) && Elad Itzhak (ID 201414283)
 * 
 * This program provides an interface which enables the user to see the updated 
 * exchange rates and to convert amounts from one currency to another according 
 * to those rates.
 * The currencies' exchange rates are being determined according to the 'Bank of Israel' 
 * XML rates publishes and are being updated every 3 hours.
 */

package currency.exchange;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ConvertApp 
{
	public static void main(String[] args) 
	{
		// Creating the user interface
		UserInterface app = new UserInterface();
		
		/**
		 * This anonymous inner class is responsible for the program's data (the currencies and
		 * their rates) being up to date by running a separate thread that every 3 hours gets the 
		 * updated 'Bank of Israel' currencies exchange XML and saves it data. 
		 */
		Thread t = new Thread() {
    	    @Override
    	    public void run() 
    	    {
    	        while(true) 
    	        {
    	        	// Updating the user interface
    	        	app.update();
					
    	            // Getting data updated every 3 hours
    	            try {
						Thread.sleep(1000*60*60*3);	
					} 
    	            /**
    	             * Thrown when a thread is waiting, sleeping, or otherwise occupied,
    	             * and the thread is interrupted, either before or during the activity
    	             */
    	            catch (InterruptedException e) {
						e.printStackTrace();
					}		
    	            
    	        }
    	    }
    	};
    	// Executing the run of the separate thread
    	t.start();
		
		/**
		 *  Actions taken once the 'Convert' button is being pressed. 
		 */
		app.getConvertButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				double amountToConvert, convertedAmount;
				Currency currencyToConvertFrom, currencyToConvertTo;
				
				// Making the currencies rates present only 3 digits after the decimal point.
		        DecimalFormat numberFormat = new DecimalFormat("#.000");
		        
		        // Getting user's inputs
				amountToConvert = Double.parseDouble(app.getAmountToConvert().getText());
				app.getConvertTo().setSelectedIndex(app.getConvertTo().getSelectedIndex());
				currencyToConvertFrom = app.getConvertFrom().getSelectedValue();
				currencyToConvertTo = app.getConvertTo().getSelectedValue();
				
				// Calculating the conversion
				convertedAmount = currencyToConvertTo.fromLocalCurrency(currencyToConvertFrom.toLocalCurrency(amountToConvert));
				
				// Calculation's outcome
				app.getConvertedAmount().setValue(numberFormat.format(convertedAmount));
				app.getSummary().setText(String.valueOf(numberFormat.format(amountToConvert)) + " "+
						currencyToConvertFrom.getSymbol() + " are equal to "+
						String.valueOf(numberFormat.format(convertedAmount))+ " " + 
						currencyToConvertTo.getSymbol());
			}
	    } );
		
	}
	
	public int test()
	{
		return 8;
	}
}