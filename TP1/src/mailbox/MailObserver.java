package mailbox;

import patternObserver.Observer;
import patternObserver.Subject;

import javax.swing.*;

public class MailObserver extends JLabel implements Observer
{
    public MailObserver()
    {
        this.setText("Aucun nouveau message");
    }

    @Override
    public void update(Subject subject)
    {
        MailBox mailBox = MailBox.getInstance();
        Mail lastMail = mailBox.getLastMail();

        if (lastMail != null)
        {
            this.setText(
                    "Last mail : \n" +
                            "Subject : " + lastMail.getSubject() + "\n" +
                            "Body : " + lastMail.getBody() + "\n"
            );
        }
        else
        {
            this.setText("Aucun nouveau message");
        }
    }
}
