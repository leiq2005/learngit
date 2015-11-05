package com.stronglei.u;

import org.junit.Test;

import junit.framework.TestCase;

public class AcTest2 extends TestCase {
	@Test
	public void test() {
		Ac ac = new Ac();
		int r = ac.add(3, 5);
		assertEquals(7, r);
	}
}
