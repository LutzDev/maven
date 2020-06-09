package de.hfu;
import static de.hfu.Util.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilTest {
	
	/*
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	*/
	
	 @Test
    public void testIstErstesHalbjahr()
    {
    	final int eingabe = 4;
    	final Boolean sollWert = true;
    	assertTrue(istErstesHalbjahr(eingabe));
    }

}
