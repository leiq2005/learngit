package com.stronglei.u;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;

public class AcTest extends TestCase{

	@Test
	public void test() {
		Ac ac = new Ac();
		int r = ac.add(1, 2);
		assertEquals(3, r,0);
	}

}
