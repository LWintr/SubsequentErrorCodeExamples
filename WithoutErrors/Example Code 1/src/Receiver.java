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

public class Receiver {
	
	SecretKey secretKey;
	IvParameterSpec iv;

	
	public Receiver(SecretKey secretKey, IvParameterSpec iv) {
		this.secretKey = secretKey;
		this.iv = iv;
	}
	
	public void receive(byte[] cipherText) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
		
		byte[] decryptedText = cipher.doFinal(cipherText);
		System.out.println("Receiver: I received: "+new String(decryptedText, "UTF-8"));
	}
}
