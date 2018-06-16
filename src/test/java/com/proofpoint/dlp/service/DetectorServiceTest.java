package com.proofpoint.dlp.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.proofpoint.dlp.entity.DetectorType.CreditCard;
import static com.proofpoint.dlp.entity.DetectorType.IBAN;
import static com.proofpoint.dlp.entity.DetectorType.SSN;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DetectorServiceTest {
    @Autowired
    private DetectorService detectorService;
    @Test
    public void testDetectorService() {
        String text = "The random number is SA03 8000 0000 6080 1016 751. Send payment to SA03 8000 0000 6080 1016 7519. My social security number is 123-45-6789. My credit card number is 4012-8888-8888-1881";
        String sensitiveTypes = detectorService.getSensitiveTypes(text);
        Assert.assertEquals(SSN + "," + IBAN + "," + CreditCard, sensitiveTypes);
    }
}
