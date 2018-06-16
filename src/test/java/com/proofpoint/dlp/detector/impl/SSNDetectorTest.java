package com.proofpoint.dlp.detector.impl;

import com.proofpoint.dlp.detector.impl.SSNDetector;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SSNDetectorTest {
    @Autowired
    private SSNDetector ssnDetector;
    @Test
    public void testSSNPattern1() {
        String text = "My social security number is 123-45-6789";
        //String ssnPattern = ".*(?i)(?:Social Security|Social Security#|Soc Sec|SSN|SSNS|SSN#|SS#).*(?:\\d{3}-\\d{2}-\\d{4}|\\d{3}\\s\\d{2}\\s\\d{4}|\\d{9}).*";
        Assert.assertTrue(ssnDetector.detect(text));
    }
    @Test
    public void testSSNPattern2() {
        String text = "My social security number is 123456789";
        Assert.assertTrue(ssnDetector.detect(text));
    }
    @Test
    public void testSSNPattern3() {
        String text = "My social security number is 123 45 6789";
        Assert.assertTrue(ssnDetector.detect(text));
    }
    @Test
    public void testSSNPattern_false1() {
        String text = "My  number is 123 45 6789";
        Assert.assertFalse(ssnDetector.detect(text));
    }

    @Test
    public void testSSNPattern_false2() {
        String text = "My social security number is 123_45_6789";
        Assert.assertFalse(ssnDetector.detect(text));
    }
}
