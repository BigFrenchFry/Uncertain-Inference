package Inference;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import core.*;
import parser.*;

public class BNInferencer {
	public static void main(String[] argv){
		XMLBIFParser x = new XMLBIFParser();
		EnumerationAsk wat = new EnumerationAsk();
		try {
			BayesianNetwork bn = x.readNetworkFromFile(argv[0]);
			Assignment e = new Assignment();
			for(int i = 2; i < argv.length; i+=2){
				e.put(bn.getVariableByName(argv[i]), argv[i+1]);
			}
			Distribution dist = wat.ask(bn, bn.getVariableByName(argv[1]), e);
			System.out.println(dist);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

}