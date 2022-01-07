import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.spec.IvParameterSpec;


public class Sender2 {
	
	SecretKey secretKey;
	IvParameterSpec iv;

	
	public Sender2(SecretKey secretKey, IvParameterSpec iv) {
		this.secretKey = secretKey;
		this.iv = iv;
	}
	
	public byte[] send(String msg) throws NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException {

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
		byte[] cipherText = cipher.doFinal(msg.getBytes("UTF-8"));
		
		return cipherText;
	}
}
