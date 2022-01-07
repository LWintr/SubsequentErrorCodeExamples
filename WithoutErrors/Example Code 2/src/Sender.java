import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.spec.GCMParameterSpec;


public class Sender {
	
	SecretKey secretKey;
	GCMParameterSpec spec;

	
	public Sender(SecretKey secretKey, byte[] src) {
		this.secretKey = secretKey;
		this.spec = new GCMParameterSpec(128, src);
	}
	
	public byte[] send(String msg) throws NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException {

		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, spec);
		byte[] cipherText = cipher.doFinal(msg.getBytes("UTF-8"));
		
		return cipherText;
	}
}
