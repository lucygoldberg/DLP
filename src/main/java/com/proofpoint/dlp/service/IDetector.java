package com.proofpoint.dlp.service;

public interface IDetector {
    boolean detect(String text);
    DetectorType getType();
}
