package com.proofpoint.dlp.detector.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IBANDetectorTest {
    @Autowired
    private IBANDetector ibanDetector;
    @Test
    public void testIBANPattern1()  {
        String text = "Send payment to DE44 5001 0517 5407 3249 31";
        Assert.assertTrue(ibanDetector.detect(text));
    }
    public void testIBANPattern1_1()  {
        String text = "Send payment to DE44500105175407324931";
        Assert.assertTrue(ibanDetector.detect(text));
    }
    @Test
    public void testIBANPattern2()  {
        String text = "Send payment to GB29 NWBK 6016 1331 9268 19";
        Assert.assertTrue(ibanDetector.detect(text));
    }
    @Test
    public void testIBANPattern3()  {
        String text = "Send payment to SA03 8000 0000 6080 1016 7519";
        Assert.assertTrue(ibanDetector.detect(text));
    }
    @Test
    public void testIBANPattern4()  {
        String text = "The random number is SA03 8000 0000 6080 1016 751. Send payment to SA03 8000 0000 6080 1016 7519";
        Assert.assertTrue(ibanDetector.detect(text));
    }
    @Test
    public void testIBANPattern_false1()  {
        String text = "Send payment to aa44 5001 0517 5407 3249 31";
        Assert.assertFalse(ibanDetector.detect(text));
    }
    @Test
    public void testIBANPattern_false2()  {
        String text = "Send payment to GB44_5001_0517_5407_3249_31";
        Assert.assertFalse(ibanDetector.detect(text));
    }
}
