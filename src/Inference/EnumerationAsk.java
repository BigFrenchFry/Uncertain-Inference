package Inference;
import java.util.List;

import core.Assignment;
import core.BayesianNetwork;
import core.Distribution;
import core.RandomVariable;

public class EnumerationAsk implements Inferencer {
	
	public Distribution ask(BayesianNetwork bn, RandomVariable X, Assignment e) {
		Distribution Q = new Distribution(X);
		for(int i = 0; i < X.getDomain().size(); i++){  
			//REMEMBER TO PASS IN THE CLOOOOOOOOOOOOOOOOOOOOONE
			Assignment copy = e.copy();
			copy.set(X, X.getDomain().get(i));
			Q.put(X.getDomain().get(i), enumerateAll(bn, copy, 0));
		}
		Q.normalize();
		return Q; 
	}
	
	public double enumerateAll(BayesianNetwork bn, Assignment e, int index){
		List<RandomVariable> vars = bn.getVariableListTopologicallySorted();
		if(index >= vars.size()){
			return 1;
		}
		
		RandomVariable Y = vars.get(index);
		if(e.containsKey(Y)){ // if Y has a value (is assigned in our bayes net)
			e = e.copy();
			return bn.getProb(Y, e)*enumerateAll(bn, e, index+1);
		} else {
			double sum = 0;
			for(int i = 0; i < Y.getDomain().size(); i++){
				e.put(Y, Y.getDomain().get(i));
				Assignment ni = e.copy(); //maybe you should fix this? But hey unless it's reeeeeeally slow, it's whatever.
				double prob = bn.getProb(Y, ni);
				double enumAll = enumerateAll(bn, ni, index+1);
				sum += prob*enumAll;
			}
			return sum;
		}
	}
}
















