package configs;

import java.io.File;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLConfigsReader {

	public static HashMap<String, String> parse(String XMLFile) throws Exception {
		File file = new File(XMLFile);
		
		if(!file.exists())
			throw new Exception("Configs XML file do not exist or invalid path is provided.");
		
		try {
			Document doc;
			HashMap<String, String> configMap = new HashMap<>();
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
			traverse(doc.getDocumentElement(), "", configMap);
			
			return configMap;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
    }

    private static void traverse(Element element, String parentPath, HashMap<String, String> map) {
        String tagName = element.getTagName();
        String fullPath = parentPath + ((tagName.equals("configs")) ? "" : tagName);

        NodeList children = element.getChildNodes();
        if (children.getLength() == 1 && children.item(0).getNodeType() == Node.TEXT_NODE) {
            String value = children.item(0).getNodeValue().trim();
            map.put(fullPath, value);
        } else {
            for (int i = 0; i < children.getLength(); i++) {
                Node node = children.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                	if(tagName.equals("configs"))
                		traverse((Element) node, fullPath, map);
                	else
                		traverse((Element) node, fullPath + ".", map);
                }
            }
        }
    }
}
