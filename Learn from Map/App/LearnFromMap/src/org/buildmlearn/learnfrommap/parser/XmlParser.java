package org.buildmlearn.learnfrommap.parser;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.buildmlearn.learnfrommap.questionmodule.Question;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.Context;

public class XmlParser {
	private Context mContext;

	public XmlParser(Context mContext)
	{
		this.mContext = mContext;
	}
	

	
	public ArrayList<Question> fetchQuestions()
	{
		String code;
		String type;
		String format;
		String answer;
		boolean location;
		String relation;
		ArrayList<Question> questions = new ArrayList<Question>();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(mContext.getAssets().open("test.xml"));
			NodeList nList = doc.getElementsByTagName("question");
			for(int i=0; i<nList.getLength(); i++)
			{
				Node nNode = nList.item(i);
				Element element = (Element)nNode;
				code = element.getElementsByTagName("code").item(0).getTextContent();
				type = element.getElementsByTagName("type").item(0).getTextContent();
				format = element.getElementsByTagName("format").item(0).getTextContent();
				relation = element.getElementsByTagName("relationship").item(0).getTextContent();
				answer = element.getElementsByTagName("answer").item(0).getTextContent();
				location = Boolean.parseBoolean(element.getElementsByTagName("location").item(0).getTextContent());
				Question question = new Question(code, type, format, answer, location, relation);
				questions.add(question);
				//Log.e("TEXT XML", format);
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return questions;
	}
	
	
}
