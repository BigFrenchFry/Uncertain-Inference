package Inference;
import java.util.List;
import java.util.ArrayList;

import core.Assignment;
import core.BayesianNetwork;
import core.Distribution;
import core.RandomVariable;

public class GibbsAsk implements Inferencer {
	
    private ArrayList<RandomVariable> getMB(BayesianNetwork bn, RandomVariable z) {

        ArrayList<RandomVariable> mb = new ArrayList<RandomVariable>();
        //mb.addAll(bn.getParents(bn,z));
        //ArrayList<RandomVariable> children = new ArrayList<RandomVariable>();
        //children.addAll(bn.getChildren(bn,z);
        //for (RandomVariable child : children) {
            //mb.addAllU(bn.getParents(bn, child); // addAllU is add all union
        //}
        //mb.addAll(children);
        return mb; 
    }

    private String sampleCondDist (BayesianNetwork bn, RandomVariable z,
            ArrayList<RandomVariable> mb) {

        // Replace "String" with "Object" casted to type of z's domain
        String output;

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
        Distribution xCounts =
            new ArrayList<Integer>(bn.getVariableList().size());
        
        // Z: nonevidence variables in bn (all variables less query & evidence)
        ArrayList<RandomVariable> nonEVars = 
            new ArrayList<RandomVariable>(bn.getVariableListTopologicallySorted());
        // 99% certain you can't use "Entry" like this
        for (Entry<RandomVariable, Object> en : e) {
            if (nonEVars.contains(e.getKey())){
                nonEVars.remove(e.getKey());
            }
        }
        nonEVars.remove(X);

        // x: state of network, copied from e
        Assignment state = e.clone;

		for (int i = 0; i < N; i++) {  
			for (RandomVariable z : nonEvars) {
                // set value of Z_j in state by sampling from P(Z_j | mb(Z_j))
                state.put(z) = sampleCondDist(bn, z, getMB(bn, z)); 
                xCounts.replace(z, (xCounts.get(z)+1.0)); // sooper shady 
            }
		}
        // might have to change Q from freqs. to rel. freqs. 
		Q.normalize();
		return Q; 
	}
}
















