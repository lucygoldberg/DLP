package com.proofpoint.dlp.detector;

import com.proofpoint.dlp.entity.DetectorType;

public interface IDetector {
    boolean detect(String text);
    DetectorType getType();
}
