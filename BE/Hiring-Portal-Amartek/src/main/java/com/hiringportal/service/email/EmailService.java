package com.hiringportal.service.email;

public interface EmailService {
    void sendEmailVerification(String name, String to, String token);
    void sendEmailTest(String name, String to, String token);
}
