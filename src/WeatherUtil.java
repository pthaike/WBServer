import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
class WeatherUtil {
	public HashMap<String, String> weather = new HashMap<String, String>();
    public static HashMap<String, String> cityCode = new HashMap<String, String>();
    public int weathercode = -1;
    public String weatherresult = null;
    private final String[] dictionaryStrings = { "�����", "�ȴ��籩", "쫷�", "ǿ������",
            "������", "С���ѩ", "��ӱ���", "ѩ�ӱ���", "����", "ëë��", "����", "����", "����", "Сѩ",
            "����Сѩ", "�ߴ�ѩ", "ѩ", "����", "���ѩ", "��", "��", "����", "���̵�", "���", "�з�",
            "����", "����", "ҹ������", "��������", "ҹ�����", "�������", "ҹ������", "����", "ת��",
            "ת��", "��б���", "��", "������", "������", "������", "������", "��ѩ", "��ѩ", "��ѩ",
            "����", "��", "��ѩ", "����" };
    public WeatherUtil() {
        initCitys();
//        for(int i= 0; i < dictionaryStrings.length; i++){
//        	System.out.println(i+": "+dictionaryStrings[i]);
//        }
    }

    /* ��ʼ�����д��� */
    private void initCitys() {
        cityCode.put("����", "0008");
        cityCode.put("�Ϻ�", "0116");
        cityCode.put("���", "0133");
        cityCode.put("�Ϸ�", "0448");
        cityCode.put("�人", "0138");
        cityCode.put("����", "0044");
        cityCode.put("����", "0031");
        cityCode.put("����", "0017");
        cityCode.put("�ϲ�", "0097");
        
        cityCode.put("���", "0049");
        cityCode.put("����", "0064");
        cityCode.put("����", "0512");
        cityCode.put("֣��", "0165");
        cityCode.put("���ͺ���", "0249");
        cityCode.put("��³ľ��", "0135");
        cityCode.put("��ɳ", "0013");
        cityCode.put("����", "0259");
        cityCode.put("����", "0037");
        
        cityCode.put("����", "0080");
        cityCode.put("����", "0502");
        cityCode.put("�Ͼ�", "0512");
        cityCode.put("֣��", "0100");
        cityCode.put("�ɶ�", "0016");
        cityCode.put("ʯ��ׯ", "0122");
        cityCode.put("����", "0039");
        cityCode.put("̫ԭ", "0129");
        cityCode.put("����", "0076");
        
        cityCode.put("����", "0119");
        cityCode.put("����", "0141");
        cityCode.put("����", "0010");
        cityCode.put("����", "0079");
        cityCode.put("����", "0236");
    }
    private Document getWeatherXML(String cityCode) throws IOException {
        URL url = new URL("http://weather.yahooapis.com/forecastrss?p=CHXX"
                + cityCode + "&u=c");
        URLConnection connection = url.openConnection();
        System.out.println(cityCode);
        Document Doc = stringToElement(connection.getInputStream());
        return Doc;
    }
    /* �����ȡ��������ϢXML�ĵ� */
    private void saveXML(Document Doc, String Path) {
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transFactory.newTransformer();
            DOMSource domSource = new DOMSource(Doc);
            File file = new File(Path);
            FileOutputStream out = new FileOutputStream(file);
            StreamResult xmlResult = new StreamResult(out);
            transformer.transform(domSource, xmlResult);
        } catch (Exception e) {
            System.out.println("�����ļ�����");
        }
    }
    /* ��ȡ������Ϣ */
    public String getWeather(String city) {
        String result = null;
        try {
            Document doc = getWeatherXML(WeatherUtil.cityCode.get(city));
        	//Document doc = getWeatherXML("0116");
            NodeList nodeList = doc.getElementsByTagName("channel");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                NodeList nodeList1 = node.getChildNodes();
                for (int j = 0; j < nodeList1.getLength(); j++) {
                    Node node1 = nodeList1.item(j);
                    //System.out.println(node1.getNodeName());
                    if(node1.getNodeName().equalsIgnoreCase("yweather:wind")){
                    	NamedNodeMap nm = node1.getAttributes();
                    	String chill = nm.getNamedItem("chill").getNodeValue();
                    	String direction = nm.getNamedItem("direction").getNodeValue();
                    	String speed = nm.getNamedItem("speed").getNodeValue();
                    	weather.put("chill", chill);
                    	weather.put("direction", direction);
                    	weather.put("speed", speed);
                    	//System.out.println("-------->"+chill+"==="+direction+"===>"+speed);
                    }
                    if (node1.getNodeName().equalsIgnoreCase("item")) {
                        NodeList nodeList2 = node1.getChildNodes();
                        for (int k = 0; k < nodeList2.getLength(); k++) {
                            Node node2 = nodeList2.item(k);
                            if (node2.getNodeName().equalsIgnoreCase(
                                    "yweather:forecast")) {
                                NamedNodeMap nodeMap = node2.getAttributes();
                                Node weekday = nodeMap.getNamedItem("day");
                                Node lowNode = nodeMap.getNamedItem("low");
                                Node highNode = nodeMap.getNamedItem("high");
                                Node codeNode = nodeMap.getNamedItem("code");
                                weathercode = Integer.parseInt(codeNode.getNodeValue());
//                                if (code >= 0 && code <=18 ||code >= 35 && code <=43 || code >=45 && code <=47){
//                                	badFlag = true;
//                                }
                                String day = "����";
                                if (result == null) {
                                    result = "";
                                } else {
                                    day = "\n����";
                                }
                                result = result
                                        + weekday.getNodeValue()
                                        + " "
                                        + dictionaryStrings[Integer
                                                .parseInt(codeNode
                                                        .getNodeValue())]
                                        + "\t����¶ȣ�" + lowNode.getNodeValue()
                                        + "�� \t����¶ȣ�" + highNode.getNodeValue()
                                        + "�� \n";
                            }
                        }
                    }
                }
            }
            //saveXML(doc, "C:\\Users\\ygui\\Desktop\\Weather.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        weatherresult = result;
        return result;
    }
    public Document stringToElement(InputStream input) {
    	
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            Document doc = db.parse(input);
            return doc;
        } catch (Exception e) {
            return null;
        }
    }
    
    
    public String getWeatherresult() {
		return weatherresult;
	}

	public void setWeatherresult(String weatherresult) {
		this.weatherresult = weatherresult;
	}

	public HashMap<String, String> getWeather() {
		return weather;
	}

	public void setWeather(HashMap<String, String> weather) {
		this.weather = weather;
	}

	public int getWeathercode() {
		return weathercode;
	}

	public void setWeathercode(int weathercode) {
		this.weathercode = weathercode;
	}

	public String[] getDictionaryStrings() {
		return dictionaryStrings;
	}

	public static void main(String arg[]) {
    	WeatherUtil info = new WeatherUtil();
        String weather = info.getWeather("����");
        System.out.println(weather);
    }
}