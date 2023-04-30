import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {
		  
		  	DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
	        File file = new File("books.xml");
	        Document document = documentBuilder.parse(file);
	       	        
	        XPathFactory xpathFactory = XPathFactory.newInstance();
	        XPath xpath = xpathFactory.newXPath();
	      
	        Scanner myObj = new Scanner(System.in);  
	        System.out.println("Enter Book ID:");

	        String bookID = myObj.nextLine(); 
	        String xpathExpression = "//catalog//book[@id='"+bookID+"']";
	       
	        XPathExpression xPathExpression = xpath.compile(xpathExpression);
	        NodeList nodes = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);
	        
	        boolean exist = false;
	        for (int i = 0; i < nodes.getLength(); i++) {
	            if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
	                Element element = (Element) nodes.item(i);
	                String author = element.getElementsByTagName("author")
	                        .item(0).getTextContent();
	                String title = element.getElementsByTagName("title")
	                        .item(0).getTextContent();
	                String genre = element.getElementsByTagName("genre")
	                        .item(0).getTextContent();
	                String price = element.getElementsByTagName("price")
	                        .item(0).getTextContent();
	                String publish_date = element.getElementsByTagName("publish_date")
	                        .item(0).getTextContent() + "\n";
	                
	                String description = element.getElementsByTagName("description")
	                        .item(0).getTextContent() + "\n";
	                
	                System.out.println(String.format(
	                        "[author=%s, title=%s, genre=%s, price=%s, publish_date=%s, description=%s]",
	                        author, title, genre, price, publish_date,description));
	                exist=true;
	                break;
	            }
	        }
	        if (!exist) {
				System.out.println("NO BOOK WITH THIS ID !!!");
			}

	}

}
