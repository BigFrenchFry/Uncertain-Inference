package Inference;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import core.*;
import Parser.*;

public class BNApproxInferencer {
	public static void main(String[] argv){
		XMLBIFParser x = new XMLBIFParser();
		GibbsAsk wat = new GibbsAsk();
		try {
            int samples = Integer.parseInt(argv[0]);
			BayesianNetwork bn = x.readNetworkFromFile(argv[1]);
			Assignment e = new Assignment();
			for(int i = 3; i < argv.length; i+=2){
				e.put(bn.getVariableByName(argv[i]), argv[i+1]);
			}
			Distribution dist = wat.ask(bn, bn.getVariableByName(argv[2]), e);
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
