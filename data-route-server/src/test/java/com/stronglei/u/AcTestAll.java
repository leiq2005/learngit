package com.stronglei.u;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class AcTestAll extends TestSuite {
	public static Test suite() {
        TestSuite suite = new TestSuite("leiq Test");
        suite.addTestSuite(AcTest.class);
        suite.addTestSuite(AcTest2.class);
        return suite;
    }
    public static void main(String args[]){
        TestRunner.run(suite());
    } 
}
