package org.xitikit.cloud.wijee.test;

import org.junit.Test;
import org.xitikit.cloud.wijee.ReplacementPairBuilder;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.xitikit.cloud.wijee.ReplacementPairBuilder.ReplacementPair;
import static org.xitikit.cloud.wijee.ReplacementPairBuilder.start;

/**
 * Copyright Xitikit.org ${year}
 *
 * @author J. Keith Hoopes
 */
public class ReplacementPairBuilderTest{

    @Test
    public void test() throws Exception{

        ReplacementPairBuilder builder = start();
        assertNotNull("'start()' should always return a builder.", builder);

        ReplacementPair[] values;

        values = start().finish();
        assertTrue("'finish()' should never return null", values != null && values.length == 0);

        values = start()
            .add("${A_T}", "val")
            .finish();
        assertTrue("[1]: 'finish()' should return an array equal in length top the number of times 'add' was called", values != null && values.length == 1);

        values = start()
            .add("${A_T}", "val")
            .add("${TWO}", "sponge")
            .finish();
        assertTrue("[1]: 'finish()' should return an array equal in length top the number of times 'add' was called",values != null && values.length == 2);

        ReplacementPair p = values[0];
        assertTrue(p != null);
        assertTrue("[1]: token should be equal to first parameter","${A_T}".equals(p.token));
        assertTrue("[1]: replacement should be equal to second parameter","val".equals(p.replacement));

        p = values[1];
        assertTrue(p != null);
        assertTrue("[2]: token should be equal to first parameter","${TWO}".equals(p.token));
        assertTrue("[2]: replacement should be equal to second parameter","sponge".equals(p.replacement));
    }
}