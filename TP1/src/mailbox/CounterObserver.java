package mailbox;

import mailbox.MailBox;
import patternObserver.Observer;
import patternObserver.Subject;

import javax.swing.*;

public class CounterObserver extends JLabel implements Observer
{
    public CounterObserver()
    {
        MailBox mailBox = MailBox.getInstance();
        this.setText("Nombre de mails : " + mailBox.getNbreMail());
    }

    @Override
    public void update(Subject subject)
    {
        MailBox mailBox = MailBox.getInstance();
        this.setText("Nombre de mails : " + mailBox.getNbreMail());
    }
}
