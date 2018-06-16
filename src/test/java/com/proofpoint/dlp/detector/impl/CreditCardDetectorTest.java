package com.proofpoint.dlp.detector.impl;

import com.proofpoint.dlp.detector.impl.CreditCardDetector;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditCardDetectorTest {
    @Autowired
    private CreditCardDetector creditCardDetector;
    @Test
    public void testIBANPattern1()  {
        String text = "My credit card number is 4012 8888 8888 1881";
        Assert.assertTrue(creditCardDetector.detect(text));
    }
    @Test
    public void testIBANPattern2()  {
        String text = "My credit card number is 4012-8888-8888-1881";
        Assert.assertTrue(creditCardDetector.detect(text));
    }
    @Test
    public void testIBANPattern3()  {
        String text = "My credit card number is 4012888888881881";
        Assert.assertTrue(creditCardDetector.detect(text));
    }
    @Test
    public void testIBANPattern4()  {
        String text = "The random number is 4012888888881882. My credit card number is 4012888888881881";
        Assert.assertTrue(creditCardDetector.detect(text));
    }
    @Test
    public void testIBANPattern_false1()  {
        String text = "My number is 4012888888881881";
        Assert.assertFalse(creditCardDetector.detect(text));
    }
    @Test
    public void testIBANPattern_false2()  {
        String text = "My credit card number is 4012-8888-8888-188";
        Assert.assertFalse(creditCardDetector.detect(text));
    }
}
