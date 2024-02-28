
import java.util.Scanner;
import static javaapplication103.JavaApplication103.convertToString;
  class BoxEncryption {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();
        System.out.print("Enter a secret key: ");
        String key = scanner.nextLine();
        
        // Convert to binary
       String binaryPlaintext = convertToBinary(plaintext);
       String binaryKey = convertToBinary(key);
       
       
        // Complement the plaintext
        String complementedText = complement(binaryPlaintext);
        // Encrypt the complemented text using an S-box
        String encryptedText = SBOXencrypt(complementedText);
        // XOR encrypt the text
        String xorText = Encrypt(encryptedText, binaryKey);
        System.out.println(" Encrypted Text: " + xorText);
        
        String decryptedText = Decrypt(encryptedText, key);
        
        String decryptedPlaintext = convertToString(decryptedText);
        System.out.println("Decrypted Text: " + decryptedPlaintext);
        scanner.close();
    }
    
    
    
    public static String convertToBinary(String text) {
        StringBuilder binaryText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            String binaryChar = Integer.toBinaryString(c);
            binaryText.append(String.format("%8s", binaryChar).replace(' ', '0'));
        }
        return binaryText.toString();
    }
    // Complement the text
    public static String complement(String text) {
        StringBuilder complementedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            complementedText.append((char) (~c));
        }
        return complementedText.toString();
    }
    // Encrypt the text using an S-box
    public static String SBOXencrypt(String plaintext) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            char encryptedChar = sBox((char) (c));
            encryptedText.append(encryptedChar);
        }
        return encryptedText.toString();
    }
    // S-box function 
    public static char sBox(char input) {
        // Example S-box implementation
        char[] sBox = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', '*', '%', '$', '{', '}', '#', '@', '<', '>', '?' };
        int index = input % sBox.length;
        return sBox[index];
    }
    // XOR encrypt the text
    public static String Encrypt(String text, String key) {
    StringBuilder encryptedText = new StringBuilder();
    for (int i = 0; i < text.length(); i++) {
        char c = text.charAt(i);
        char keyChar = key.charAt(i % key.length());
        char encryptedChar = (char) (c ^ keyChar);
        encryptedText.append(encryptedChar);
    }
    
    
    return encryptedText.toString();
}
     public static String Decrypt(String text, String key) {
        return Encrypt(text, key); 
    }
}
