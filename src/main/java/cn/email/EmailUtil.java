package cn.email;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class EmailUtil {
    private static String defaultSenderName = "";//默认的发件人用户名，defaultEntity能用到

    private static String defaultSenderPass = "";//默认的发件人用户名，defaultEntity能用到

    private static String defaultSmtpHost = "";//默认的发件人用户名，defaultEntity能用到

    private String smtpHost;// 邮件服务器地址

    private String sendUserName;// 发件人的用户名

    private String sendUserPass;// 发件人密码

    private MimeMessage mimeMessage; //邮件对象

    private Session session;

    private Properties properties; //主要存放SMTP服务器地址等参数。

    private Multipart mp;

    private LinkedList<FileDataSource> files = new LinkedList<FileDataSource>();

    private void init() {
        if (properties == null) {
            properties = System.getProperties();
        }
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.auth", "true");
        session = session.getDefaultInstance(properties, null);
        session.setDebug(true);
        // 用session对象来创建并初始化邮件对象
        mimeMessage = new MimeMessage(session);
        mp = new MimeMultipart();
    }

    public EmailUtil(String smtpHost, String sendUserName, String sendUserPass, String to, String cc, String mailSubject, String mailBody, List<String> attachments) {
        super();
        this.smtpHost = smtpHost;
        this.sendUserName = sendUserName;
        this.sendUserPass = sendUserPass;
        init();
        setFrom(sendUserName);
        setTo(to);
        setCC(cc);
        setBody(mailBody);
        setSubject(mailSubject);
        if (attachments != null) {  
            for (String attachment : attachments) {  
                addFileAffix(attachment);  
            }  
        }  
    }

    private boolean addFileAffix(String filename) {
        try {  
            if (filename != null && filename.length() > 0) {  
                BodyPart bp = new MimeBodyPart();  
                FileDataSource fileds = new FileDataSource(filename);  
                bp.setDataHandler(new DataHandler(fileds));  
                bp.setFileName(MimeUtility.encodeText(fileds.getName(), "utf-8", null)); // 解决附件名称乱码  
                mp.addBodyPart(bp);// 添加附件  
                files.add(fileds);  
            }  
        } catch (Exception e) {  
            System.err.println("增加邮件附件：" + filename + "发生错误！" + e);  
            return false;  
        }  
        return true;  
    }

    private boolean setBody(String mailBody) {
        try {  
            BodyPart bp = new MimeBodyPart();  
            bp.setContent("<meta http-equiv=Content-Type content=text/html; charset=UTF-8>" + mailBody, "text/html;charset=UTF-8");  
            // 在组件上添加邮件文本  
            mp.addBodyPart(bp);  
        } catch (Exception e) {  
            System.err.println("设置邮件正文时发生错误！" + e);  
            return false;  
        }  
        return true;  
    }

    private boolean setSubject(String subject) {
        try {  
            mimeMessage.setSubject(subject);  
        } catch (Exception e) {  
            return false;  
        }  
        return true; 
    }

    private boolean setFrom(String from) {
        try {  
            mimeMessage.setFrom(new InternetAddress(from));  
        } catch (Exception e) {  
            return false;  
        }  
        return true;  
    }

    private boolean setCC(String cc) {
        if (cc == null) {  
            return false;  
        }  
        try {  
            mimeMessage.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));  
        } catch (Exception e) {  
            return false;  
        }  
        return true;  
    }

    private boolean setTo(String to) {
        if (to == null)  
            return false;  
        try {  
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));  
        } catch (Exception e) {  
            return false;  
        }  
        return true; 
    }
    public static EmailUtil entity(String smtpHost, String sendUserName, String sendUserPass, String to, String cc, String mailSubject, String mailBody,  
            List<String> attachments){
        return new EmailUtil(smtpHost, sendUserName, sendUserPass, to, cc, mailSubject, mailBody, attachments);
    }
    
    public boolean send() throws MessagingException{
        mimeMessage.setContent(mp);  
        mimeMessage.saveChanges();  
        System.out.println("正在发送邮件....");  
        Transport transport = session.getTransport("smtp");  
        // 连接邮件服务器并进行身份验证  
        transport.connect(smtpHost, sendUserName, sendUserPass);  
        // 发送邮件  
        transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));  
        System.out.println("发送邮件成功！");  
        transport.close();  
        return true; 
    }
}
