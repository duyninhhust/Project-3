package vn.edu.hust.project3.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import vn.edu.hust.project3.config.Constant;
import vn.edu.hust.project3.dto.ContactMail;
import vn.edu.hust.project3.exception.EmailSendingException;
import vn.edu.hust.project3.model.BillPhone;
import vn.edu.hust.project3.model.User;
import vn.edu.hust.project3.service.MailService;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    private final TemplateEngine templateEngine;

    @Override
    @Async
    public void sentContactMail(ContactMail mail) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");

            mimeMessageHelper.setSubject(mail.getSubject());
//            mimeMessageHelper.setFrom(mail.getEmail());
            mimeMessageHelper.setTo(Constant.EMAIL);
            mimeMessageHelper.setText(mail.getMessage() + "\n\n" + mail.getEmail());
            javaMailSender.send(mimeMessageHelper.getMimeMessage());

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    @Override
    @Async
    public void sendOrderMail(User user) {

        Map<String, Object> params = new HashMap<>();
        params.put("firstName", user.getFirstName());
        params.put("lastName", user.getLastName());

        String htmlEmail = createAttachHtml(params);
        log.info("Create Html: {}", htmlEmail);
        try {
            MimeMessage mimeMessage = new MimeMessage(Session.getInstance(new Properties()));
            mimeMessage.setSubject("Order Success");
            mimeMessage.addRecipients(Message.RecipientType.TO, user.getEmail());
            mimeMessage.addHeader("Content-Type", "text/html; charset=UTF-8");
            BodyPart content = new MimeBodyPart();
            content.setContent(htmlEmail, "text/html; charset=UTF-8");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(content);
            mimeMessage.setContent(multipart);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e){
            throw new EmailSendingException("Could not create MimeMessage", e);
        }
    }

    private String createAttachHtml(Map<String, Object> params) {
        Context context = new Context();
        params.forEach(context::setVariable);

        long start = System.currentTimeMillis();
        String htmlEmail = templateEngine.process("OrderMail", context);
        log.info("Took {} ms to create AttachHtml", System.currentTimeMillis() - start);

        return htmlEmail;
    }
}
