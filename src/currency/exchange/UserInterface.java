package currency.exchange;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Represents the user's graphical interface 
 * @version 1.7
 * @since 1.0
 */
public class UserInterface implements Updateable
{
	private JFrame frame;
    private JPanel panelLeft, panelCenter;
    private JTable table;
    private Table tableData;
    private JScrollPane scrollPaneLeft, scrollPaneRight, scrollPaneCenter;
    private JList<Currency> convertFrom, convertTo;
    private JTextField amountToConvert;
    private JFormattedTextField convertedAmount;
    private JButton convertButton;
    private DefaultTableModel dm;
    private JLabel from, to, toConvert, summary, lastUpdated;
    
    /**
     * Creates the user interface.
     */
    public UserInterface()
    {
    	setFrame(new JFrame("Currencies covertor"));
		
    	// Setting the table
    	setTableData(new Table());
    	setTable(new JTable(tableData.getData(), tableData.getColumnsName()));
    	setScrollPaneLeft(new JScrollPane(table));
		setDm((DefaultTableModel)table.getModel());

    	// Setting the graphics for the conversion part 
    	convertFrom = new JList<>();
    	convertTo = new JList<>();
    	convertButton = new JButton("Convert");
    	scrollPaneRight = new JScrollPane(convertTo);
    	scrollPaneCenter = new JScrollPane(convertFrom);
    	amountToConvert = new JTextField();
    	convertedAmount = new JFormattedTextField();
    	convertedAmount.setEditable(false);       
    	from = new JLabel("convert from");
    	from.setForeground(Color.white);
    	to = new JLabel("convert to");
    	to.setForeground(Color.white);
    	toConvert = new JLabel("Amount to convert");
    	toConvert.setForeground(Color.white);
    	summary = new JLabel();
    	summary.setForeground(Color.white);
    	lastUpdated = new JLabel();
    	lastUpdated.setForeground(Color.white);
    	
    	// Setting the font appearance 
        Font font = new Font(null, Font.BOLD, 17);
        convertedAmount.setFont(font);
        amountToConvert.setFont(font);
        summary.setFont(font);

        // Setting the panels appearance
    	panelLeft = new JPanel();
    	panelCenter = new JPanel();
    	
    	panelLeft.add(scrollPaneLeft);
    	panelLeft.setBackground(new Color(64,116,177));
    	panelCenter.setBackground(new Color(64,116,177));
    	panelCenter.setLayout(null);
    	
    	panelCenter.add(from);
    	panelCenter.add(scrollPaneCenter);
    	panelCenter.add(to);
    	panelCenter.add(scrollPaneRight);
    	panelCenter.add(toConvert);
    	panelCenter.add(amountToConvert);
    	panelCenter.add(convertButton);
    	panelCenter.add(convertedAmount);
    	panelCenter.add(summary);
    	panelCenter.add(lastUpdated);
    	
    	// Setting elements' positions 
    	scrollPaneLeft.setBounds(0, 20, 100, 0);
    	scrollPaneCenter.setBounds(60, 100, 60, 100);
    	from.setBounds(60, 50, 100, 50);
    	scrollPaneRight.setBounds(200, 100, 60, 100);
    	to.setBounds(200, 50, 100, 50);
    	toConvert.setBounds(340, 50, 120, 50);
    	amountToConvert.setBounds(340, 100, 100, 30);
    	convertButton.setBounds(50,280, 410, 40);
    	convertedAmount.setBounds(175,410, 150, 30);
    	summary.setBounds(50, 485, 400, 30);
    	lastUpdated.setBounds(2,2,250,30);
    	
    	// Making sure the program is ending once the interface's window is getting closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Setting layouts
        frame.setLayout(new BorderLayout(10,50));        
        frame.add(panelLeft,BorderLayout.WEST);
        frame.add(panelCenter);
    	
        // Setting the frame/window appearance
        frame.setSize(1000, 600);
    	frame.setVisible(true);
    }

    /**
     * All the graphical elements getters
     * @return A UI element. Each method returns one of the UI element.
     */
    public JFrame getFrame() {
		return frame;
	}
	public JPanel getPanelLeft() {
		return panelLeft;
	}
	public JPanel getPanelCenter() {
		return panelCenter;
	}
	public JTable getTable() {
		return table;
	}
	public Table getTableData() {
		return tableData;
	}
	public JScrollPane getScrollPaneLeft() {
		return scrollPaneLeft;
	}
	public JScrollPane getScrollPaneRight() {
		return scrollPaneRight;
	}
	public JScrollPane getScrollPaneCenter() {
		return scrollPaneCenter;
	}
	public JList<Currency> getConvertFrom() {
		return convertFrom;
	}
	public JList<Currency> getConvertTo() {
		return convertTo;
	}
	public JTextField getAmountToConvert() {
		return amountToConvert;
	}
	public JFormattedTextField getConvertedAmount() {
		return convertedAmount;
	}
	public JButton getConvertButton() {
		return convertButton;
	}
	public DefaultTableModel getDm() {
		return dm;
	}
	public JLabel getFrom() {
		return from;
	}
	public JLabel getTo() {
		return to;
	}
	public JLabel getToConvert() {
		return toConvert;
	}
	public JLabel getSummary() {
		return summary;
	}
	public JLabel getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * All elements setter. Each method sets an element according to the value of the received argument. 
	 * @params UI elements containing the value wish to be set.
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	public void setPanelLeft(JPanel panelLeft) {
		this.panelLeft = panelLeft;
	}
	public void setPanelCenter(JPanel panelCenter) {
		this.panelCenter = panelCenter;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public void setTableData(Table tableData) {
		this.tableData = tableData;
	}
	public void setScrollPaneLeft(JScrollPane scrollPaneLeft) {
		this.scrollPaneLeft = scrollPaneLeft;
	}
	public void setScrollPaneRight(JScrollPane scrollPaneRight) {
		this.scrollPaneRight = scrollPaneRight;
	}
	public void setScrollPaneCenter(JScrollPane scrollPaneCenter) {
		this.scrollPaneCenter = scrollPaneCenter;
	}
	public void setConvertFrom(JList<Currency> convertFrom) {
		this.convertFrom = convertFrom;
	}
	public void setConvertTo(JList<Currency> convertTo) {
		this.convertTo = convertTo;
	}
	public void setAmountToConvert(JTextField amountToConvert) {
		this.amountToConvert = amountToConvert;
	}
	public void setConvertedAmount(JFormattedTextField convertedAmount) {
		this.convertedAmount = convertedAmount;
	}
	public void setConvertButton(JButton convertButton) {
		this.convertButton = convertButton;
	}
	public void setDm(DefaultTableModel dm) {
		this.dm = dm;
	}
	public void setFrom(JLabel from) {
		this.from = from;
	}
	public void setTo(JLabel to) {
		this.to = to;
	}
	public void setToConvert(JLabel toConvert) {
		this.toConvert = toConvert;
	}
	public void setSummary(JLabel summary) {
		this.summary = summary;
	}
	public void setLastUpdated(JLabel lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	/**
     * Implementation if the 'Updateable' interface.
     * Whenever called it changes the user interface to be up to date.
     */
    public void update()
    {
    	// Retrieving the updated data and updating the user's interface
    	tableData.update();		
    	dm.fireTableDataChanged(); 
    	// Setting the currencies list and the update date
    	convertFrom.setListData(tableData.getCurrency());
    	convertTo.setListData(tableData.getCurrency());
		lastUpdated.setText("Last update:  " + tableData.getLastUpdateDate() + "  |  " + tableData.getLastUpdateTime());
		
		// Saving the updated data in a log file		
		try {
			tableData.saveData();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
