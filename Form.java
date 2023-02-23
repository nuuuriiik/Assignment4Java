import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public abstract class Form {
    private String enteredId;
    private String enteredLogin;
    private String enteredPassword;
    private String salt;


    //getters and setters
    public String getEnteredId() {
        return enteredId;
    }
    public void setEnteredId(String enteredId) {
        this.enteredId = enteredId;
    }
    public String getEnteredLogin() {
        return enteredLogin;
    }
    public void setEnteredLogin(String enteredLogin) {
        this.enteredLogin = enteredLogin;
    }
    public String getEnteredPassword() {
        return enteredPassword;
    }
    public void setEnteredPassword(String getEnteredPassword) {
        this.enteredPassword = getEnteredPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    String jdbcUrl = "jdbc:postgresql://localhost:5432/aitu";
    String userName = "postgres";
    String MasterPassword = "1111";
    protected boolean doesUserExist(String individualNumber, String login) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return true;
    }
}
