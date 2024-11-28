import java.math.BigInteger;
import java.security.SecureRandom;

public class Deffie {
    public static final BigInteger P = new BigInteger("23");
    public static final BigInteger G = new BigInteger("5");
    public static void main(String[] args) {
        
        // K = (B^a mod P): secreted Key computed by Alice
        // K = (A^b mod P): secreted Key computer by  Bob

        // B = G^b mod P
        // A = G^a mod P

        // B: bob public key , a : Alice private Key, P : Prime Number 
        
        BigInteger alicePrivateKey = generatePrivateKey();
        BigInteger bobPrivateKey = generatePrivateKey();
        System.out.println("A : " + alicePrivateKey + "   B: " + bobPrivateKey);

        BigInteger alicePublicKey = generatePublicKey(alicePrivateKey);
        BigInteger bobPublicKey = generatePublicKey(bobPrivateKey);
        System.out.println("A : "+ alicePublicKey + "  B : " + bobPublicKey);

        BigInteger aliceSecretKey = generateSharedSecretKey(alicePrivateKey, bobPublicKey);
        BigInteger bobSecretKey = generateSharedSecretKey(bobPrivateKey, alicePublicKey);
        
        System.out.println("Secret Key Alice got : "+ aliceSecretKey);
        System.out.println("Secret Key Bob got : " + bobSecretKey);
    }  

    public static  BigInteger generatePrivateKey(){
        SecureRandom random = new SecureRandom();
        return new BigInteger(32, random);
    }

    public static BigInteger generatePublicKey(BigInteger privateKey){
        return G.modPow(privateKey, P);
    }

    public static BigInteger generateSharedSecretKey(BigInteger privateKey, BigInteger publicKey){
        return publicKey.modPow(privateKey, P);
    }
}
