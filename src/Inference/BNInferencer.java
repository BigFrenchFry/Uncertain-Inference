package Inference;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import core.*;
import Parser.*;

public class BNInferencer {
	public static void main(String[] argv){
		EnumerationAsk wat = new EnumerationAsk();
		
		if(argv[0].contains(".xml")){
			XMLBIFParser x = new XMLBIFParser();
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
		} else {
			BIFParser x;
			try {
				x = new BIFParser(new FileInputStream(argv[0]));
				//System.out.println(x.parseNetwork());
				BayesianNetwork bn = x.parseNetwork();
				Assignment e = new Assignment();
				for(int i = 2; i < argv.length; i+=2){
					e.put(bn.getVariableByName(argv[i]), argv[i+1]);
				} 
				Distribution dist = wat.ask(bn, bn.getVariableByName(argv[1]), e);
				
				System.out.println(dist);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}

}

