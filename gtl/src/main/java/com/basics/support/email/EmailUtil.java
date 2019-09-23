package com.basics.support.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
	// 邮件发送协议
	private final static String PROTOCOL = "smtp";

	// SMTP邮件服务器
	private final static String HOST = "smtp.163.com";

	// SMTP邮件服务器默认端口
	private final static String PORT = "25";

	// 是否要求身份认证
	private final static String IS_AUTH = "false";

	// 是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息）
	private final static String IS_ENABLED_DEBUG_MOD = "false";

	// 发件人
	private static String from = "wjy13161413191@163.com";

	// 初始化连接邮件服务器的会话信息
	private static Properties props = null;

	static {
		props = new Properties();
		props.setProperty("mail.transport.protocol", PROTOCOL);
		props.setProperty("mail.smtp.host", HOST);
		props.setProperty("mail.smtp.port", PORT);
		props.setProperty("mail.smtp.auth", IS_AUTH);
		props.setProperty("mail.debug", IS_ENABLED_DEBUG_MOD);
	}

	/**
	 * 发送简单的文本邮件
	 */
	public static boolean sendTextEmail(String to, String code) throws Exception {
		try {
			// 创建Session实例对象
			Session session1 = Session.getDefaultInstance(props);

			// 创建MimeMessage实例对象
			MimeMessage message = new MimeMessage(session1);
			// 防止成为垃圾邮件，披上outlook的马甲
			message.addHeader("X-Mailer", "Microsoft Outlook Express 6.00.2900.2869");
			// 设置发件人
			message.setFrom(new InternetAddress(from));
			// 设置邮件主题
			message.setSubject("MNC verification code");
			// 设置收件人
			message.setRecipient(RecipientType.TO, new InternetAddress(to));
			// 设置发送时间
			message.setSentDate(new Date());
			// 设置纯文本内容为邮件正文
			message.setText("Your validation code is " + code + ", The validity period of the validation code is 10 minutes. Please retrieve it after expiration!" + "【MNC】");
			// 保存并生成最终的邮件内容
			message.saveChanges();

			// 获得Transport实例对象
			Transport transport = session1.getTransport();
			// 打开连接
			transport.connect("wjy13161413191@163.com", "WangJiyu2018");
			// 将message对象传递给transport对象，将邮件发送出去
			transport.sendMessage(message, message.getAllRecipients());
			// 关闭连接
			transport.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) throws Exception {
		sendTextEmail("enhua1218@naver.com", "88888");
	}

	/**
	 * 向邮件服务器提交认证信息
	 */
	static class MyAuthenticator extends Authenticator {

		private String username = "wjy13161413191@163.com";

		private String password = "WangJiyu2018";

		public MyAuthenticator() {
			super();
		}

		public MyAuthenticator(String username, String password) {
			super();
			this.username = username;
			this.password = password;
		}

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {

			return new PasswordAuthentication(username, password);
		}
	}
}
