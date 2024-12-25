package controllers.auths;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class DigitalSignatureUtil {

    private KeyPair keyPair;

    // Phương thức tạo cặp khóa public và private
    public void generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // Độ dài khóa 2048 bits
        keyPair = keyPairGenerator.generateKeyPair();
    }

    // Phương thức lấy Public Key dưới dạng chuỗi Base64
    public String getPublicKey() {
        PublicKey publicKey = keyPair.getPublic();
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    // Phương thức lấy Private Key dưới dạng chuỗi Base64
    public String getPrivateKey() {
        PrivateKey privateKey = keyPair.getPrivate();
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    // Phương thức ký điện tử
    public String signData(String data) throws Exception {
        if (keyPair == null) {
            throw new IllegalStateException("Key pair is not generated yet!");
        }

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(keyPair.getPrivate());
        signature.update(data.getBytes());
        byte[] digitalSignature = signature.sign();

        return Base64.getEncoder().encodeToString(digitalSignature);
    }

    // Phương thức kiểm tra chữ ký
    public boolean verifySignature(String data, String signatureStr) throws Exception {
        if (keyPair == null) {
            throw new IllegalStateException("Key pair is not generated yet!");
        }

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(keyPair.getPublic());
        signature.update(data.getBytes());

        byte[] digitalSignature = Base64.getDecoder().decode(signatureStr);
        return signature.verify(digitalSignature);
    }

}
