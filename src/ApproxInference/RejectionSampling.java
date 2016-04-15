package ApproxInference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import core.Assignment;
import core.BayesianNetwork;
import core.Distribution;
import core.RandomVariable;

public class RejectionSampling {

	public Distribution ask(int N, BayesianNetwork bn, RandomVariable X, Assignment e) {
		Distribution counts = new Distribution(X);
		counts.initialize(X);
		for(int i = 0; i < N; i++){
			Assignment x = priorSample(bn);
			Set<Entry<RandomVariable, Object>> evidence = e.entrySet();
 
            boolean flag = true;
            
			for(Entry<RandomVariable, Object> var : evidence){
				if(!var.getValue().equals(x.get(var.getKey()))){
					flag = false;
					break;
				} 
				
			}
			if(flag){
				counts.put(x.get(X), counts.get(x.get(X))+1);
			}

		}
		counts.normalize();
		return counts;
	}

	public Assignment priorSample(BayesianNetwork bn){
		Assignment x = new Assignment();
		List<RandomVariable> sortedList = bn.getVariableListTopologicallySorted();
		
		for(int i = 0; i < bn.size(); i++){ //for each random variable in bn, in topological order
        //so we grabbed a random variable X[i], set its value weighted randomly given parents 
			RandomVariable Xi = sortedList.get(i);
			ArrayList<Double> weights = new ArrayList<Double>(); 
			for(int j = 0; j < Xi.getDomain().size(); j++){
				x.set(Xi, Xi.getDomain().get(j));
				weights.add(j, bn.getCPTForVariable(Xi).get(x));
			}
			double r = Math.random();
			double sum = 0;
			for(int k = 0; k < weights.size(); k++){
				sum += weights.get(k);
				if(r <= sum){
					x.put(Xi, Xi.getDomain().get(k));
					break;
				}
			} 
		}
		
		return x;
	}
}
