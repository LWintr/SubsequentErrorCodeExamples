import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Main {

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException {

		System.out.println("Starting transmission 2");
		
		SecretKey sk = generateKey();
		byte[] gcmSpecSource = new byte[128];
		SecureRandom.getInstance("NativePRNGBlocking").nextBytes(gcmSpecSource);
		
		Sender sender = new Sender(sk, gcmSpecSource);
		Receiver receiver = new Receiver(sk, gcmSpecSource);

		byte[] channel = sender.send("Sending you my Secret");
		receiver.receive(channel);
		
		generateGCMSpec1(gcmSpecSource);

		System.out.println("Finished transmission 2");
	}
	
	public static SecretKey generateKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		SecureRandom secureRandom = new SecureRandom();
		int keyBitSize = 128;
		keyGenerator.init(keyBitSize, secureRandom);
		return keyGenerator.generateKey();
	}
	
	public static GCMParameterSpec generateGCMSpec1(byte[] src) {
		return new GCMParameterSpec(128, src);
	}	
}