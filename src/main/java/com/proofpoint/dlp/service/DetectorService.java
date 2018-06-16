package com.proofpoint.dlp.service;

import com.proofpoint.dlp.detector.IDetector;
import com.proofpoint.dlp.entity.DetectorType;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class DetectorService {
    private List<IDetector> detectors;
    public String getSensitiveTypes(String text) {
        if (StringUtils.isEmpty(text)) {
            return "";
        }
        return detectors.stream()
                .filter(detector -> detector.detect(text))
                .map(IDetector::getType)
                .map(DetectorType::toString)
                .collect(Collectors.joining(","));
    }

    public void setDetectors(List<IDetector> detectors) {
        this.detectors = detectors;
    }
}
