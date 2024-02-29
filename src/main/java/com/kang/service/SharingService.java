package com.kang.service;


public interface SharingService {

    boolean checkFirstTime(String userId, String activityId);

    String generateSharingUrl(String userId, String activityId);

    String getSharingUrl(String userId, String activityId);
}
