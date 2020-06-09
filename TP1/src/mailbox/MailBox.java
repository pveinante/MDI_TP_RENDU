package mailbox;

import patternObserver.Subject;

import java.util.ArrayList;
import java.util.List;

public class MailBox extends Subject
{
	private static MailBox instance = null;
	List<Mail> mail = new ArrayList<Mail>();

	private MailBox()
	{

	}
	
	public static MailBox getInstance()
	{
		if (instance==null)
			instance = new MailBox();
		return instance;
	}

	public void addMail(Mail m)
	{
		this.mail.add(m);
		this.notifyObservers();
	}

	public Mail getLastMail()
	{
		if (!this.isEmpty())
		{
			return this.mail.get(mail.size()-1);
		}
		else
		{
			return null;
		}
	}

	public Integer getNbreMail()
	{
		return this.mail.size();
	}

	public boolean isEmpty()
	{
		return this.mail.isEmpty();
	}
}
