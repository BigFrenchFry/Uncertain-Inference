package ApproxInference;
import java.util.ArrayList;
import java.util.Random;

import core.Assignment;
import core.BayesianNetwork;
import core.BayesianNetwork.Node;
import core.Distribution;
import core.RandomVariable;


public class GibbsAsk {
	
	Random rand = new Random();

    private Object sampleCondDist (BayesianNetwork bn, RandomVariable z, Assignment x) {
    	
    	Distribution xCounts = new Distribution(z);
        xCounts.initialize(z);
        
        double sum = 0;
        Assignment xCopy = x.copy();
        for (Object o : z.getDomain()){
        	xCopy.set(z, o);
        	double product = bn.getProb(z, xCopy);
        	for (Node node : bn.getNodeForVariable(z).children){
        		product *= bn.getProb(node.variable, xCopy);
        	}
        	xCounts.put(o,product);
        }
        xCounts.normalize();
        
        for (Object o : z.getDomain()){
        	xCopy.set(z, o);
        	sum += xCounts.get(o);
        	
        	if (rand.nextDouble() <= sum){
        		return o;
        	}
        }
        return null;
    }

	public Distribution ask(int N, BayesianNetwork bn, RandomVariable X, Assignment e) {
         
        // Initialize local variables
        // N: Vector of counts for each value of X
        // It's pretty shady using a Distribution like this, but I didn't want to 
        //   import another map.
        Distribution xCounts = new Distribution(X);
        xCounts.initialize(X);

        // Z: nonevidence variables in bn (all variables less query & evidence)
        ArrayList<RandomVariable> nonEVars = 
            new ArrayList<RandomVariable>(bn.getVariableListTopologicallySorted());
        // 99% certain you can't use "Entry" like this
        for (RandomVariable k : e.keySet()) {
            if (nonEVars.contains(k)){
                nonEVars.remove(k);
            }
        }
        //nonEVars.remove(X);

        // x: state of network, copied from e
        Assignment state = e.copy();
        for (RandomVariable z : nonEVars) {
            state.put(z,z.getDomain().get(rand.nextInt(z.getDomain().size())));
        }

		for (int i = 0; i < N; i++) {  
			for (RandomVariable z : nonEVars) {
                // set value of Z_j in state by sampling from P(Z_j | mb(Z_j))
                Object value = sampleCondDist(bn, z, state);
                state.put(z, value); 
                xCounts.put(state.get(X), (xCounts.get(state.get(X))+1.0)); 
            }
	    }	
        // might have to change Q from freqs. to rel. freqs. 
        // Q.normalize() might do just that
		xCounts.normalize();
		return xCounts; 
	}
}
