package Inference;
import java.util.List;
import java.util.ArrayList;
import java.util.Map.Entry;

import core.Assignment;
import core.BayesianNetwork;
import core.Distribution;
import core.RandomVariable;

public class GibbsAsk {
	
    private ArrayList<RandomVariable> getMB(BayesianNetwork bn, RandomVariable z) {

        ArrayList<RandomVariable> mb = new ArrayList<RandomVariable>();
        mb.addAll(bn.getNodeForVariable(z).parents);
        ArrayList<RandomVariable> children = new ArrayList<RandomVariable>();
        children.addAll(bn.getNodeForVariable(z).children);
        for (RandomVariable child : children) {
            ArrayList<RandomVariable> babyDaddies = new ArrayList<RandomVariable>();
            babyDaddy.addAll(bn.getNodeForVariable(child).parents);
            for (RandomVariable dad : babyDaddies) {
                if (!mb.contains(dad)) {
                    mb.add(dad);
                }
            }
        }
        mb.addAll(children);
        return mb; 
    }

    private Object sampleCondDist (BayesianNetwork bn, RandomVariable z,
            ArrayList<RandomVariable> mb) {

        Object output;

        Assignment 
        // Somehow make a CPT for z using the markov blanket (P(z | mb)) 
        // Somehow get a switch on domain elements' probabilities  to route a randomly 
        //  generated number to return the right domain element

        return output;
    }

	public Distribution ask(BayesianNetwork bn, RandomVariable X, Assignment e, int N) {
         
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
        nonEVars.remove(X);

        // x: state of network, copied from e
        Assignment state = e.clone;
        Random rand = new Random();
        for (RandomVariable z : nonEVars) {
            state.put(z,z.getDomain().get((int) rand.nextDouble()*z.getDomain().size()));
        }

		for (int i = 0; i < N; i++) {  
			for (RandomVariable z : nonEvars) {
                // set value of Z_j in state by sampling from P(Z_j | mb(Z_j))
                Object value = sampleCondDist(bn, z, getMB(bn, z));
                state.put(z, value); 
                xCounts.replace(value, (xCounts.get(value)+1.0)); 
            }
	    }	
        // might have to change Q from freqs. to rel. freqs. 
        // Q.normalize() might do just that
		Q.normalize();
		return Q; 
	}
}
















