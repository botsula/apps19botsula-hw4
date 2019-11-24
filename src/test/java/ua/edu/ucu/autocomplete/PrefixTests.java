package ua.edu.ucu.autocomplete;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.tries.RWayTrie;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

public class PrefixTests {

    public PrefixMatches triepm;

    @Before
    public void init() {
        triepm = new PrefixMatches(new RWayTrie());
        triepm.load("alligator", "allibaba", "anaconda", "alu", "gedozipam", "geometry");
    }

    @Test
    public void testTrie(){

        assertTrue(triepm.contains("alligator"));
        triepm.delete("alligator");
        assertFalse(triepm.contains("alligator"));
        assertFalse(triepm.delete("panda"));
        assertEquals(triepm.size(), 5);
        System.out.println(triepm.contains("anaconda"));
        Iterable<String> res2 = triepm.wordsWithPrefix("al", 2);
        String[] expectedResult2 = new String[]{"alu", "allibaba"};
        assertThat(res2, containsInAnyOrder(expectedResult2));

        String[] expectedResult = {"allibaba", "alu"};
        Iterable<String> res = triepm.wordsWithPrefix("al");
        assertThat(res, containsInAnyOrder(expectedResult));
    }
}
