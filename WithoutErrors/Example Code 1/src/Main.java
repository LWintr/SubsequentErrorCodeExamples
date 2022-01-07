import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.spec.IvParameterSpec;

public class Main {

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException {

		System.out.println("Starting transmission 1");
		
		SecretKey sk = generateKey();
		IvParameterSpec iv = generateIv();
		
		Sender sender = new Sender(sk, iv);
		Sender2 sender2 = new Sender2(sk, iv);
		Receiver receiver = new Receiver(sk, iv);

		byte[] channel = sender.send("Sending you my Secret");
		receiver.receive(channel);
		
		channel = sender2.send("Second secret");
		receiver.receive(channel);

		System.out.println("Finished transmission 1");
	}
	
	
	
	public static SecretKey generateKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		SecureRandom secureRandom = new SecureRandom();
		int keyBitSize = 128;
		keyGenerator.init(keyBitSize, secureRandom);
		return keyGenerator.generateKey();
	}
	
	public static IvParameterSpec generateIv() {
		byte[] ivBytes = new byte[16];
		new SecureRandom().nextBytes(ivBytes);
		return new IvParameterSpec(ivBytes);
	}

}