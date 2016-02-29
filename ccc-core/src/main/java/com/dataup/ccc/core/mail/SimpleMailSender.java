
package com.dataup.ccc.core.mail;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import autonavi.online.framework.property.PropertiesConfig;
import autonavi.online.framework.property.PropertiesConfigUtil;

/**
 * 
 * @ClassName: SimpleMailSender 
 * @Description: 发送邮件
 * @author zhanqiao.huang
 * @date 2015年9月10日 下午5:53:17
 */
@Component 
public class SimpleMailSender {
	
	private final Logger logger = LogManager.getLogger(SimpleMailSender.class);
	private static PropertiesConfig pc = null;

	/**
	 * 
	 */
	public SimpleMailSender()throws Exception {
		pc = PropertiesConfigUtil.getPropertiesConfigInstance();
	}
	/**
	 * 获取邮件的公共设置信息
	 * @Methods Name getMailSenderInfo
	 * @Create In 2014-2-14 By hongliang.gao
	 * @return MailSenderInfo
	 */
	public MailSenderInfo getMailSenderInfo() {

		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		return mailInfo;

	}
	
	/**
	 * 发送邮件(支持附件)
	 * @param address  接受邮件邮箱地址，多个地址用分号(;)分割
	 * @param subject  邮件主题
	 * @param content  邮件内容
	 * @param attachs 附件地址数组
	 * @throws Exception 
	 * @author guohaitao
	 */
	public boolean sendMail(String address, String subject, String content,String attachs[]) throws Exception{
		MailSenderInfo mailInfo = new MailSenderInfo();

		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session session = Session
				.getDefaultInstance(pro, authenticator);
		try {
			// 构造MimeMessage 并设定基本的值
			MimeMessage mailMessage = new MimeMessage(session);
			Address from = new InternetAddress(mailInfo.getFromAddress(), (String) pc.getProperty("mailSendName"));
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			
			String[] toAddressArray=address.split(";");
			if (toAddressArray.length > 1){
			}
			
			Address[] toArray=null;

			if(toAddressArray.length > 1){
				toArray=new Address[toAddressArray.length];
				for(int i=0;i<toAddressArray.length;i++){
					Address toAdd=new InternetAddress(toAddressArray[i]);
					toArray[i]=toAdd;
				}
				mailMessage.setRecipients(Message.RecipientType.TO, toArray);
			}else{
			    Address to = new InternetAddress(address);
				mailMessage.setRecipient(Message.RecipientType.TO, to);
			}	
			mailMessage.setSubject(MimeUtility.encodeWord(subject, "UTF-8", "Q"));
			
			Multipart mp = new MimeMultipart();

			// 向Multipart添加正文
			MimeBodyPart mbpContent = new MimeBodyPart();
			mbpContent.setContent(content, "text/html;charset=utf-8");
			mp.addBodyPart(mbpContent);
			
			// 为邮件添加附件
            if (attachs != null && attachs.length > 0) {
                File file = null;
                for (int i = 0; i < attachs.length; i++) {
                    MimeBodyPart mbpFile = new MimeBodyPart();
                    file = new File(attachs[i]);
    				FileDataSource fds = new FileDataSource(file);
    				mbpFile.setDataHandler(new DataHandler(fds));
    				mbpFile.setFileName(new String(fds.getName().getBytes(),"utf-8"));
    				mp.addBodyPart(mbpFile);
                }
            }
			mailMessage.setContent(mp);
			mailMessage.setSentDate(new Date());
			mailMessage.saveChanges() ;
			
			Transport transport = session.getTransport("smtp");
			transport.connect(mailInfo.getMailServerHost(), mailInfo.getFromAddress(), mailInfo.getPassword());
			transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			logger.error("发送邮件异常", e);
		}
		return true;
	}
	
	/**
	 * 
	 * @Methods Name sendMail 发送邮件
	 * @Create In 2012-7-12 By hongliang.gao
	 * @param address
	 *            接受邮件邮箱地址，多个地址用分号(;)分割
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件内容
	 * @param contentType
	 *            邮件格式 0：html格式； 1 文本格式
	 * @return boolean
	 * @throws Exception 
	 */
	public boolean sendMail(String address, String subject, String content,
			int contentType) throws Exception {

		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();

		String[] toAddressArray=address.split(";");
		if (toAddressArray.length == 1){
			mailInfo.setToAddress(toAddressArray[0]);			
		} else if (toAddressArray.length > 1){
			mailInfo.setToAddressArray(toAddressArray);			
		}
		mailInfo.setSubject(subject);
		mailInfo.setContent(content);
		
		try{
			if (contentType == 0) {
				return sendHtmlMail(mailInfo); // 发送html格式
			} else {
				return sendTextMail(mailInfo);// 发送文体格式
			}			
		}catch (Exception e){
			logger.error("发送邮件异常", e);
		}
		return false;
	}

	/**
	 * 以文本格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件的信息
	 * @throws Exception 
	 */
	public boolean sendTextMail(MailSenderInfo mailInfo) throws Exception {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		try {
			// 设置字符编码格式，解决在linux系统上的主题乱码问题
			System.setProperty("mail.mime.charset","UTF-8");
//			System.setProperty("mail.smtp.starttls.enable","true");
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress(),(String) pc.getProperty("mailSendName"));
//			Address from = new InternetAddress(mailInfo.getFromAddress(),"数字政通");
			
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			
			// 创建邮件的接收者地址，并设置到邮件消息中
						
			// Message.RecipientType.TO属性表示接收者的类型为TO
			RecipientType recipientType = mailInfo.getRecipientType();
			if(recipientType == null){
				recipientType = Message.RecipientType.TO;
			}

			Address[] toArray=null;

			if(mailInfo.getToAddressArray()!=null&&mailInfo.getToAddressArray().length>0)
			{
				toArray=new Address[mailInfo.getToAddressArray().length];
				for(int i=0;i<mailInfo.getToAddressArray().length;i++){
				Address toAdd=new InternetAddress(mailInfo.getToAddressArray()[i]);
				toArray[i]=toAdd;
				}
				mailMessage.setRecipients(recipientType, toArray);
			}
			else{
			    Address to = new InternetAddress(mailInfo.getToAddress());
				mailMessage.setRecipient(recipientType, to);
			}			
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// 设置邮件消息的主要内容
			String mailContent = mailInfo.getContent();
			mailMessage.setText(mailContent);
			mailMessage.saveChanges();
			// 发送邮件
//			Transport.send(mailMessage, mailMessage.getRecipients(Message.RecipientType.TO));
			Transport.send(mailMessage);
		} catch (MessagingException ex) {
			logger.error("发送邮件异常", ex);
			return false;
		}
		return true;
	}

	/** */
	/**
	 * 以HTML格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件信息
	 * @throws MessagingException 
	 */
	public boolean sendHtmlMail(MailSenderInfo mailInfo) throws MessagingException {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try {

			// 设置字符编码格式，解决在linux系统上的主题乱码问题
			System.setProperty("mail.mime.charset", "UTF-8");
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = null;
			try {
				from = new InternetAddress(mailInfo.getFromAddress(),
						(String) pc.getProperty("mailSendName"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);

			// 创建邮件的接收者地址，并设置到邮件消息中

			// Message.RecipientType.TO属性表示接收者的类型为TO
			RecipientType recipientType = mailInfo.getRecipientType();
			if(recipientType == null){
				recipientType = Message.RecipientType.TO;
			}
			
			Address[] toArray = null;
			if (mailInfo.getToAddressArray() != null
					&& mailInfo.getToAddressArray().length > 0) {
				toArray = new Address[mailInfo.getToAddressArray().length];
				for (int i = 0; i < mailInfo.getToAddressArray().length; i++) {
					Address toAdd = new InternetAddress(mailInfo.getToAddressArray()[i]);
					toArray[i] = toAdd;
				}
				mailMessage.setRecipients(recipientType, toArray);
			} else {
				Address to = new InternetAddress(mailInfo.getToAddress());
				mailMessage.setRecipient(recipientType, to);
			}
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart("related");
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			// 如果有图片加入图片
			if (mailInfo.getPicturesPath() != null
					&& mailInfo.getPicturesPath().size() != 0) {
				String picPath = "";
				for (int i = 0; i < mailInfo.getPicturesPath().size(); i++) {
					picPath = (String) mailInfo.getPicturesPath().get(i);
					FileDataSource fds = new FileDataSource(picPath);
					MimeBodyPart pic = new MimeBodyPart();
					pic.setFileName(fds.getName());
					pic.setText("This is a beautiful car !");
					pic.setDataHandler(new DataHandler(fds));
					pic.setHeader("Content-ID", "<" + fds.getName() + ">");
					mainPart.addBodyPart(pic);

				}
			}

			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			logger.error("发送邮件异常", ex);
		}
		return false;
	}
	public static void main(String[] args) throws Exception{   
        //这个类主要是设置邮件   
     MailSenderInfo mailInfo = new MailSenderInfo();    
     mailInfo.setMailServerHost("smtp.dataup.cn"); 
     mailInfo.setMailServerPort("25");    
     mailInfo.setValidate(true);    
     mailInfo.setUserName("zhanqiao.huang@dataup.cn");    
     mailInfo.setPassword("nopass.2");//您的邮箱密码    
     mailInfo.setFromAddress("zhanqiao.huang@dataup.cn");   
     mailInfo.setToAddress("490337026@qq.com");
     mailInfo.setSubject("今天写了个邮件发送功能，测试下哈");
     mailInfo.setContent("好好学习，天天向上");
//     String[] attachs = {"E:/test.txt"};
        //这个类主要来发送邮件   
     SimpleMailSender sms = new SimpleMailSender();   
//     sms.sendMail("litao.deng@alibaba-inc.com", "设置邮箱标题 测试", "设置邮箱内容 测试", attachs);
     sms.sendTextMail(mailInfo);//发送文体格式    
//         sms.sendHtmlMail(mailInfo);//发送html格式   
   } 
}
