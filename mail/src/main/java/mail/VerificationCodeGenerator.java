package mail;

public class VerificationCodeGenerator {
    public static String generate() {
        int random = (int) (Math.random() * 100000);
        String format = "%06d";
        return String.format(format, random);
    }
}
