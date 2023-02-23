import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;

public class SignInForm extends Form {
    private String loginOrId;

    public void setLoginOrId(String loginOrId) {
        this.loginOrId = loginOrId;
    }

    public String getLoginOrId() {
        return loginOrId;
    }

    @Override
    protected boolean doesUserExist(String individualNumber, String login) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, userName, MasterPassword);
            Statement statement = con.createStatement();
            ResultSet checkExistence = statement.executeQuery("SELECT * FROM users WHERE login = '" + login + "' OR iin = '" + individualNumber + "';");
            if (checkExistence.next()) {
                return true;
            } else {
                con.close();
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error in connecting to postgres server");
            throw new RuntimeException(e);
        }
    }

    public boolean Authorization() throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (doesUserExist(getLoginOrId(), getLoginOrId())) {
            try {
                Connection con = DriverManager.getConnection(jdbcUrl, userName, MasterPassword);
                Statement statement = con.createStatement();
                String sql = "";
                setEnteredLogin(getLoginOrId());
                sql = "SELECT password, salt FROM users WHERE login = '" + getLoginOrId() + "' OR iin = '" + getLoginOrId() + "';";
                ResultSet resultSet = statement.executeQuery(sql);
                String password = " ", salt = " ";
                while (resultSet.next()) {
                    password = resultSet.getString(1);
                    salt = resultSet.getString(2);
                }
                PasswordEncryptionService passwordEncryptionService = new PasswordEncryptionService();
                return passwordEncryptionService.authenticate(getEnteredPassword(), password, salt);
            } catch (SQLException e) {
                System.out.println("Error in connecting to postgres server");
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
