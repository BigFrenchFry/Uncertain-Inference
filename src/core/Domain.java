/*
 * File: Domain.java
 * Creator: George Ferguson
 * Created: Sun Mar 25 15:07:31 2012
 * Time-stamp: <Wed Apr  3 19:34:23 EDT 2013 ferguson>
 */
package core;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Domain is an ArrayList containing an ordered set of possible values for the random variable
 */
public class Domain extends ArrayList<Object> {

    public static final long serialVersionUID = 1L;

    public Domain() {
    	super();
    }

    public Domain(int size) {
    	super(size);
    }

    public Domain(Object... elements) {
    	this();
    	for (Object o : elements) {
    		add(o);
    	}
    }

    public Domain(Collection<Object> collection) {
    	this();
    	for (Object o : collection) {
    		add(o);
    	}
    }
}