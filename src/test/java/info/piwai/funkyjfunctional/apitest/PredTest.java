/*
 * Copyright 2011 Pierre-Yves Ricau (py.ricau at gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package info.piwai.funkyjfunctional.apitest;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;
import static info.piwai.funkyjfunctional.Funky.withPred;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import info.piwai.funkyjfunctional.Pred;

import java.util.List;

import org.junit.Test;

/**
 * @author Pierre-Yves Ricau (py.ricau at gmail.com)
 */
public class PredTest {

	@Test
	public void testFilter() throws Exception {
		List<Integer> values = asList(16, 21);

		class Adult extends Pred<Integer> {{r = t > 18;}}

		List<Integer> adults = newArrayList(filter(values, withPred(Adult.class)));

		assertFalse(adults.contains(16));
		assertTrue(adults.contains(21));
	}
	
	@Test
	public void testStaticFilter() {
		staticFilter();
	}

	private static void staticFilter() {
		List<Integer> values = asList(16, 21);

		class Adult extends Pred<Integer> {{r = t > 18;}}
		
		List<Integer> adults = newArrayList(filter(values, withPred(Adult.class)));

		assertFalse(adults.contains(16));
		assertTrue(adults.contains(21));
	}
	
    @Test(expected=ArithmeticException.class)
    public void testThrows() {
        class Fails extends Pred<Object> {{ r = 42 / 0 > 69;}}
        withPred(Fails.class).apply(null);
    }
}
