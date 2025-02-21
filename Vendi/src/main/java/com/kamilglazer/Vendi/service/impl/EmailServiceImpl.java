package com.kamilglazer.Vendi.service.impl;


import com.kamilglazer.Vendi.exception.MailSendError;
import com.kamilglazer.Vendi.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendVerificationEmail(String userEmail, String token) {
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
            helper.setTo(userEmail);
            helper.setSubject("Vendi verification email");

            String htmlContent = "<div style='font-family: Arial, sans-serif; text-align: center; padding: 20px; color: #333;'>"
                    + "<h2>Welcome to Vendi!</h2>"
                    + "<hr>"
                    + "<p>To start using your account, please verify your email address.</p>"
                    + "<a href='https://your-app.com/register' "
                    + "style='display: inline-block; padding: 12px 24px; font-size: 16px; font-weight: bold; "
                    + "color: white; background-color: #007bff; text-decoration: none; border-radius: 5px;'>"
                    + "Verify Your Email</a>"
                    + "<h3 style='margin-top: 30px;'>Why verify?</h3>"
                    + "<p style='color: #666;'>Verifying your email ensures security and allows you to use all features seamlessly.</p>"
                    + "<p>If the button doesn't work, paste this link into your browser:</p>"
                    + "<p style='word-break: break-word; color: #007bff;'>"
                    + "<a href='https://your-app.com/register' style='color: #007bff;'>"
                    + "https://your-app.com/register</a></p>"
                    + "<p>Thanks,<br>The Vendi Team</p>"
                    + "</div>";
            helper.setText(htmlContent,true);
            javaMailSender.send(message);
        }catch (Exception e){
            throw new MailSendError("Error while sending verification email");
        }
    }
}
