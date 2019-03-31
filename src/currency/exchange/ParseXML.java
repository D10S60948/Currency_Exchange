package currency.exchange;

import java.io.IOException;
import java.net.URL;
import java.io.InputStream;
import java.net.HttpURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Represents the XML parsing part.
 * @version 1.0
 */
public class ParseXML 
{
	InputStream is;
    HttpURLConnection con;
    URL url;
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    Document doc;
    NodeList codes, rates;
    int length;
        
    /**
     * Creates a XML parser.
     * After being initialised it contains the 'Bank Of Israel' published currencies
     * and their rates. 
     */
	public ParseXML()
    {
    	setIs(null);
    	setCon(null);
    	
        try
        {
        	// Setting the connection        	
            setURL(new URL("https://www.boi.org.il/currency.xml"));
            setCon((HttpURLConnection)url.openConnection());
            con.setRequestMethod("GET");
            con.connect();
            
            // Preparing to get the retrieved data			            
            setIs(con.getInputStream());
            setFactory(DocumentBuilderFactory.newInstance());
            setBuilder(factory.newDocumentBuilder());
            setDoc(builder.parse(is));
            
            // Getting the data
            setCodes(doc.getElementsByTagName("CURRENCYCODE"));
            setRates(doc.getElementsByTagName("RATE"));
            setLength(codes.getLength());
        }
        /**
         * Signals that an I/O exception of some sort has occurred. 
         * This class is the general class of exceptions produced by failed or interrupted I/O operations.
         */
        catch(IOException e) {
            e.printStackTrace();
        }
        /**
         * Indicates a serious configuration error.
         * Can be thrown from the 'newDocumentBuilder()' call.
         */
        catch(ParserConfigurationException e) {
            e.printStackTrace();
        }
        /**
         * This class can contain basic error or warning information from the XML parser.
         * Can be thrown from the 'parse(is)' call. 
         */
        catch(SAXException e) {
            e.printStackTrace();
        }
        finally
        {		
        	// Closing input file and cutting off connecting	
        	if(is!=null)
        	{
        		try {
        			is.close();
        		}
        		catch(IOException e) {
        			e.printStackTrace();
        		}
        	}
        	if(con!=null)
        	{
        		con.disconnect();
        	}
        }
    }
    
    /**
     * Gets the input stream.
     * @return InputStream object that represents the input stream.
     */
	public InputStream getIs() {
		return is;
	}
	/**
     * Gets the http URL connection.
     * @return HttpURLConnection object that represents http URL connection.
     */
	public HttpURLConnection getCon() {
		return con;
	}
	/**
     * Gets the URL.
     * @return URL object that represents a URL.
     */
	public URL getURL() {
		return url;
	}
	/**
     * Gets the document builder.
     * @return DocumentBuilderFactory object which helps to build the document.
     */
	public DocumentBuilderFactory getFactory() {
		return factory;
	}
	/**
     * Gets the document builder.
     * @return DocumentBuilder object which builds the document.
     */
	public DocumentBuilder getBuilder() {
		return builder;
	}
	/**
     * Gets the document.
     * @return Document object that represents a document.
     */
	public Document getDoc() {
		return doc;
	}
	/**
     * Gets the amount of codes i.e. the amount of currencies to be built.
     * @return An int that represents the amount of currencies in XML.
     */
	public int getLength() {
		return length;
	}
	/**
     * Gets the currencies codes.
     * @return NodeList object that represents the XML currencies' codes.
     */
    public NodeList getCodes() {
		return codes;
	}
    /**
     * Gets the currencies rates.
     * @return NodeList object that represents the XML currencies' rates.
     */
    public NodeList getRates() {
		return rates;
	}
    
    /**
	 * Sets the connection.
	 * @param con: HttpURLConnection object containing the connection. 
	 */
	public void setCon(HttpURLConnection con) {
		this.con = con;
	}
	/**
	 * Sets the input stream.
	 * @param is: An InputStream object containing the input stream. 
	 */
	public void setIs(InputStream is) {
		this.is = is;
	}
	/**
	 * Sets the URL.
	 * @param url: A URL object containing the URL details. 
	 */
	public void setURL(URL url) {
		this.url = url;
	}
	/**
	 * Sets the document building factory.
	 * @param is: A DocumentBuilderFactory object containing the document builder factory.. 
	 */
	public void setFactory(DocumentBuilderFactory factory) {
		this.factory = factory;
	}
	/**
	 * Sets the document builder.
	 * @param is: A DocumentBuilder object containing the document builder. 
	 */
	public void setBuilder(DocumentBuilder builder) {
		this.builder = builder;
	}
	/**
	 * Sets the document.
	 * @param is: A Document object containing the document. 
	 */
	public void setDoc(Document doc) {
		this.doc = doc;
	}
	/**
	 * Sets the number of codes in the XML file.
	 * @param is: An int containing the amount of codes in the XML file. 
	 */
	public void setLength(int length) {
		this.length = length;
	}
	/**
	 * Sets the codes list.
	 * @param is: A NodeList containing list of all codes in the XML file. 
	 */
	public void setCodes(NodeList codes) {
		this.codes = codes;
	}
	/**
	 * Sets the rates list.
	 * @param is: A NodeList containing list of all rates in the XML file. 
	 */
	public void setRates(NodeList rates) {
		this.rates = rates;
	}
}

