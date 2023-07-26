package com.hiringportal.service.email;

import java.util.Date;

public interface EmailService {
    void sendEmailVerification(String name, String to, String token);
    void sendEmailTest(String name, String to, String token, Date before);
}
