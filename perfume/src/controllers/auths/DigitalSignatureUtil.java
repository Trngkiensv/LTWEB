package controllers.auths;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class DigitalSignatureUtil {
	 // Phương thức tạo cặp khóa public và private
    public KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // Độ dài khóa 2048 bits
        return keyPairGenerator.generateKeyPair();
    }

    // Phương thức lấy Public Key dưới dạng chuỗi Base64
    public String getPublicKey(KeyPair keyPair) {
        PublicKey publicKey = keyPair.getPublic();
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    // Phương thức lấy Private Key dưới dạng chuỗi Base64
    public String getPrivateKey(KeyPair keyPair) {
        PrivateKey privateKey = keyPair.getPrivate();
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    // Phương thức ký điện tử
    public String signData(String data, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes());
        byte[] digitalSignature = signature.sign();

        return Base64.getEncoder().encodeToString(digitalSignature);
    }

    // Phương thức kiểm tra chữ ký
    public boolean verifySignature(String data, String signatureStr, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(data.getBytes());

        byte[] digitalSignature = Base64.getDecoder().decode(signatureStr);
        return signature.verify(digitalSignature);
    }
    
    public PrivateKey convertStringToPrivateKey(String privateKeyStr) throws Exception {
        // Giải mã chuỗi Base64 thành mảng byte
        byte[] keyBytes = Base64.getDecoder().decode(privateKeyStr);
        
        // Sử dụng định dạng PKCS8 để tạo PrivateKey
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA"); // Đảm bảo thuật toán khớp với key
        return keyFactory.generatePrivate(keySpec);
    }
    
    public PublicKey convertStringToPublicKey(String publicKeyStr) throws Exception {
        // Giải mã chuỗi Base64 thành mảng byte
        byte[] keyBytes = Base64.getDecoder().decode(publicKeyStr);

        // Sử dụng định dạng X.509 để tạo PublicKey
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA"); // Đảm bảo thuật toán khớp với key
        return keyFactory.generatePublic(keySpec);
    }
    
//    public static void main(String[] args) {
//		DigitalSignatureUtil digi = new DigitalSignatureUtil();
//		KeyPair keypair = null;
//		try {
//			keypair = digi.generateKeyPair();
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if (keypair != null) {
//	        System.out.println("Private Key: " + digi.getPrivateKey(keypair));
//	        System.out.println("Public Key: " + digi.getPublicKey(keypair));
//	    } else {
//	        System.out.println("KeyPair generation failed.");
//	    }
//	}
}
