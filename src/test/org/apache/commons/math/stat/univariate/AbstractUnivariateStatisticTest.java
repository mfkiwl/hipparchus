/*
 * 
 * Copyright (c) 2004 The Apache Software Foundation. All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *  
 */
package org.apache.commons.math.stat.univariate;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.commons.math.stat.univariate.moment.Mean;

/**
 * Tests for AbstractUnivariateStatistic 
 *
 * @version $Revision: 1.2 $ $Date: 2004/04/12 05:26:10 $
 */
public class AbstractUnivariateStatisticTest extends TestCase {
    
    public AbstractUnivariateStatisticTest(String name) {
        super(name);
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(AbstractUnivariateStatisticTest.class);
        suite.setName("AbstractUnivariateStatistic Tests");
        return suite;
    }
    
    protected double[] testArray = {0, 1, 2, 3, 4, 5};
    protected double[] nullArray = null;
    protected double[] singletonArray = {0};
    protected Mean testStatistic = new Mean();
    
    public void testTestPositive() {
        for (int j = 0; j < 5; j++) {
            for (int i = 1; i < (7 - j); i++) {
                assertTrue(testStatistic.test(testArray, 0, i));
            }  
        }
        assertTrue(testStatistic.test(singletonArray, 0, 1));
    }
    
    public void testTestNegative() {
        assertFalse(testStatistic.test(singletonArray, 0, 0));
        assertFalse(testStatistic.test(testArray, 0, 0));
        try {
            testStatistic.test(singletonArray, 2, 1);  // start past end
        } catch (IllegalArgumentException ex) {
            // expected
        }
        try {
            testStatistic.test(testArray, 0, 7);  // end past end
        } catch (IllegalArgumentException ex) {
            // expected
        }
        try {
            testStatistic.test(testArray, -1, 1);  // start negative
        } catch (IllegalArgumentException ex) {
            // expected
        }
        try {
            testStatistic.test(testArray, 0, -1);  // length negative
        } catch (IllegalArgumentException ex) {
            // expected
        }
        try {
            testStatistic.test(nullArray, 0, 1);  // null array
        } catch (IllegalArgumentException ex) {
            // expected
        }      
    } 
}
