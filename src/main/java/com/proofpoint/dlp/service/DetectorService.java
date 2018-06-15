package com.proofpoint.dlp.service;

import java.util.List;
import java.util.stream.Collectors;

public class DetectorService {
    private List<IDetector> detectors;
    public List<String> getSensitiveTypes(String text) {
        return detectors.stream().filter(detector -> detector.detect(text)).map(IDetector::getType).collect(Collectors.toList());
    }

    public void setDetectors(List<IDetector> detectors) {
        this.detectors = detectors;
    }
}
