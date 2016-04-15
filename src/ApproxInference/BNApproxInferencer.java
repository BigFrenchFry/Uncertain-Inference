package ApproxInference;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import core.*;
import parser.*;

public class BNApproxInferencer {
	public static void main(String[] argv){
		RejectionSampling wat = new RejectionSampling();
		GibbsAsk wat2 = new GibbsAsk();
		if(argv[1].contains(".xml")){
			XMLBIFParser x = new XMLBIFParser();
			try {
				int samples = Integer.parseInt(argv[0]);
				BayesianNetwork bn = x.readNetworkFromFile(argv[1]);
				Assignment e = new Assignment();
				for(int i = 3; i < argv.length; i+=2){
					e.put(bn.getVariableByName(argv[i]), argv[i+1]);
				}
				Distribution dist = wat.ask(samples,bn, bn.getVariableByName(argv[2]), e);
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
				x = new BIFParser(new FileInputStream(argv[1]));
				int samples = Integer.parseInt(argv[0]);
				//System.out.println(x.parseNetwork());
				BayesianNetwork bn = x.parseNetwork();
				Assignment e = new Assignment();
				for(int i = 3; i < argv.length; i+=2){
					e.put(bn.getVariableByName(argv[i]), argv[i+1]);
				} 
				Distribution dist = wat.ask(samples, bn, bn.getVariableByName(argv[2]), e);
				
				System.out.println(dist);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}