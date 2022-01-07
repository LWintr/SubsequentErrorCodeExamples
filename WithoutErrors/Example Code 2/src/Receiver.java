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

public class Receiver {
	
	SecretKey secretKey;
	GCMParameterSpec spec;

	
	public Receiver(SecretKey secretKey, byte[] src) {
		this.secretKey = secretKey;
		this.spec = new GCMParameterSpec(128, src);
	}
	
	public void receive(byte[] cipherText) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		cipher.init(Cipher.DECRYPT_MODE, secretKey, spec);
		
		byte[] decryptedText = cipher.doFinal(cipherText);
		System.out.println("Receiver: I received: "+new String(decryptedText, "UTF-8"));
	}
}
