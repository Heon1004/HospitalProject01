package util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator{ //인증수행을 도와주는 클래스
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("seongheon1041@gmail.com","alsuclwm1!");
	}
}
