package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class GettheWeather {

	public static void getWeather(weatherBean wBean) throws IOException {

	
		String URLtoSend = "http://api.openweathermap.org/data/2.5/weather?q=" + wBean.getCityStr() + ","
				+ wBean.getCountryStr() + "&APPID=e2e5f99d92e8e6fb1b77706dd575d795&mode=xml";

		URL line_api_url = new URL(URLtoSend);

		HttpURLConnection linec = (HttpURLConnection) line_api_url.openConnection();
		linec.setDoInput(true);
		linec.setDoOutput(true);
		linec.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(linec.getInputStream()));

		
		String inputLine;

	
		String ApiResponse = "";

		
		while ((inputLine = in.readLine()) != null) {

			ApiResponse += inputLine;
		}
		in.close();

		
		System.out.println(ApiResponse);

		
		Document doc = convertStringToXMLDocument(ApiResponse);

	
		doc.getDocumentElement().normalize();
		
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());


		NodeList nList = doc.getElementsByTagName("temperature");

		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			
			Node node = nList.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE) {

			
				Element eElement = (Element) node;
				
				String XMLtemperature = eElement.getAttribute("value");
			
				wBean.setTemperatureStr(XMLtemperature);
			}
		}

	
				NodeList n2List = doc.getElementsByTagName("city");

				
				for (int temp = 0; temp < n2List.getLength(); temp++) {
					
					Node node = n2List.item(temp);
					if (node.getNodeType() == Node.ELEMENT_NODE) {

			
						Element cElement = (Element) node;
						
						NodeList XMLtimezone = cElement.getElementsByTagName("timezone");
					
						wBean.setTimeStr(XMLtimezone.item(0).getFirstChild().getTextContent());
					}
				}
		
		
	}

	
	private static Document convertStringToXMLDocument(String xmlString) {
	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	
		DocumentBuilder builder = null;
		try {
	
			builder = factory.newDocumentBuilder();

		
			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}