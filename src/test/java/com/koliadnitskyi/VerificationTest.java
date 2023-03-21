package com.koliadnitskyi;

import org.junit.Assert;
import org.junit.Test;

public class VerificationTest {
    @Test
    public void languageControlTest() {
        Verification tmp = new Verification();
        String str = "Привет бродяги. Джава это круть";
        boolean expected = true;
        boolean actual = tmp.languageControl(str);
        Assert.assertEquals(expected, actual);
    }
}
