package com.proofpoint.dlp;

import com.proofpoint.dlp.controller.ControllerTest;
import com.proofpoint.dlp.detector.impl.CreditCardDetectorTest;
import com.proofpoint.dlp.detector.impl.IBANDetectorTest;
import com.proofpoint.dlp.detector.impl.SSNDetectorTest;
import com.proofpoint.dlp.service.DetectorServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SSNDetectorTest.class,
        IBANDetectorTest.class,
        CreditCardDetectorTest.class,
        DetectorServiceTest.class,
        ControllerTest.class})
public class AllTests {
}
