import java.awt.*;
import java.awt.event.ActionEvent;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.swing.*;

public class Execution {
    JFrame frame = new JFrame();
    JLabel loginlabel = new JLabel();
    JTextField logintext = new JTextField();
    JLabel loginerror=new JLabel("*must contain a capital letter, and a digit*");
    JLabel idlabel = new JLabel("Enter ID:");
    JTextField idtext = new JTextField();
    JLabel iderror = new JLabel("*The individual number must contain 12 digits*");
    JLabel passwordlabel = new JLabel("Enter password:");
    JTextField passwordtext = new JTextField();
    JLabel passerror = new JLabel("*must be longer than 7 letters, contain a capital letter, and a digit*");
    JButton Signup = new JButton("Sing up");
    JButton Signin = new JButton("Sign in");
    JButton Up2 = new JButton();
    JButton In2 = new JButton();
    JLabel haveacc = new JLabel();
    JLabel forgetpass = new JLabel("Forget password?");

    SignUpForm sUp = new SignUpForm();
    SignInForm sIn = new SignInForm();

    public void Start() {
        frame.add(loginlabel);
        frame.add(logintext);
        frame.add(idlabel);
        frame.add(idtext);
        frame.add(passwordlabel);
        frame.add(passwordtext);
        frame.add(Signup);
        frame.add(Signin);
        frame.add(haveacc);
        frame.add(Up2);
        frame.add(In2);
        frame.add(forgetpass);

        loginerror.setVisible(false);
        iderror.setVisible(false);
        passerror.setVisible(false);

        loginerror.setBounds(40, 70, 250, 20);
        loginerror.setFont(new Font("TimesRoman", Font.PLAIN, 10));
        loginerror.setForeground(Color.RED);
        frame.add(loginerror);

        iderror.setBounds(40, 140, 250, 20);
        iderror.setFont(new Font("TimesRoman", Font.PLAIN, 10));
        iderror.setForeground(Color.RED);
        frame.add(iderror);

        passerror.setBounds(40, 210, 300, 20);
        passerror.setFont(new Font("TimesRoman", Font.PLAIN, 10));
        passerror.setForeground(Color.RED);
        frame.add(passerror);

        frame.setLayout(null);
        frame.setVisible(true);

        SignUpPos();
    }

    private void SignUpActionPerformed(java.awt.event.ActionEvent evt) {
        SignUpPos();
    }

    private void SignInActionPerformed(java.awt.event.ActionEvent evt) {
        SignInPos();
    }

    private void SignUpPos() {
        frame.setTitle("Sign-up frame");

        forgetpass.setVisible(true);
        idlabel.setVisible(true);
        idtext.setVisible(true);
        Signin.setVisible(false);
        Signup.setVisible(true);
        In2.setVisible(false);
        Up2.setVisible(true);

        loginlabel.setText("Enter login:");
        loginlabel.setBounds(40, 20, 150, 20);
        logintext.setText("");
        logintext.setBounds(40, 40, 220, 30);
        logintext.addActionListener(actionlogin);

        idlabel.setBounds(40, 90, 150, 20);
        idtext.setText("");
        idtext.setBounds(40, 110, 220, 30);
        idtext.addActionListener(actionid);

        passwordlabel.setBounds(40, 160, 150, 20);
        passwordtext.setText("");
        passwordtext.setBounds(40, 180, 220, 30);
        passwordtext.addActionListener(actionpass);

        Signup.setBounds(100, 230, 85, 20);
        Signup.addActionListener(this::CreateUser);

        haveacc.setText("Already have an account?");
        haveacc.setBounds(80, 280, 220, 20);
        Up2.setText("Sign in");
        Up2.setBounds(100, 300, 85, 20);

        Up2.addActionListener(this::SignInActionPerformed);

        frame.setSize(300, 400);
    }

    private void SignInPos() {
        frame.setTitle("Sign-in frame");

        loginerror.setVisible(false);
        iderror.setVisible(false);
        passerror.setVisible(false);
        idlabel.setVisible(false);
        idtext.setVisible(false);
        Signup.setVisible(false);
        Signin.setVisible(true);
        Up2.setVisible(false);
        In2.setVisible(true);
        forgetpass.setVisible(true);

        loginlabel.setText("Enter login or ID:");
        loginlabel.setBounds(40, 20, 150, 20);
        logintext.setText("");
        logintext.setBounds(40, 40, 220, 30);

        passwordlabel.setBounds(40, 80, 150, 20);
        passwordtext.setText("");
        passwordtext.setBounds(40, 100, 220, 30);

        Signin.setBounds(100, 150, 85, 20);
        Signin.addActionListener(this::Authorization);

        forgetpass.setBounds(100,180, 200, 20);

        haveacc.setText("Don't have an account?");
        haveacc.setBounds(80, 280, 200, 20);

        In2.setText("Sign up");
        In2.setBounds(100, 300, 85, 20);

        In2.addActionListener(this::SignUpActionPerformed);

        frame.setSize(300, 400);
    }
    Action actionlogin = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            loginerror.setVisible(!sUp.isValidLogin(logintext.getText()));
        }
    };

    Action actionid = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            iderror.setVisible(!sUp.isValidID(idtext.getText()));
        }
    };

    Action actionpass = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            passerror.setVisible(!sUp.isValidPassword(passwordtext.getText()));
        }
    };

    private void CreateUser(ActionEvent actionEvent) {
        sUp.setEnteredLogin(logintext.getText());
        sUp.setEnteredId(idtext.getText());
        sUp.setEnteredPassword(passwordtext.getText());
        try {
            if (sUp.createUser()) {
                JOptionPane.showMessageDialog(frame, "You have successfully registered", "Success", JOptionPane.PLAIN_MESSAGE);
            } else {
                iderror.setVisible(!sUp.isValidID(idtext.getText()));
                passerror.setVisible(!sUp.isValidPassword(passwordtext.getText()));
                loginerror.setVisible(!sUp.isValidLogin(logintext.getText()));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private void Authorization(ActionEvent actionEvent) {
        sIn.setLoginOrId(logintext.getText());
        sIn.setEnteredPassword(passwordtext.getText());
        try {
            if (sIn.Authorization()) {
                JOptionPane.showMessageDialog(frame, "You have successfully logged in", "Success", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Incorrect login or password", "Error", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}

