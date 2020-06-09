package mailbox;

import java.util.Random;

import javax.swing.*;

public class Main
{
	private void init()
	{
		System.out.println("init");

		JFrame f = new JFrame();
		f.setTitle("Test de JFrame"); 
	    f.setSize(300,200); 
		
		JPanel p = new JPanel();

		CounterObserver counterObserver = new CounterObserver();
		MailObserver mailObserver = new MailObserver();

		MailBox mailBox = MailBox.getInstance();
		mailBox.attach(counterObserver);
		mailBox.attach(mailObserver);

		p.add(counterObserver);
		p.add(mailObserver);

		f.add(p);

		f.setVisible(true);
		f.dispose();
		System.out.println("init");
		f.show();
	}

	private void runScenario()
	{
		MailBox.getInstance().addMail(new Mail("ESIR" + new Random().nextInt(), "tp fini"));
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		System.out.println("init");
		Main m= new Main();
		m.init();

		while (true)
		{
			Thread.sleep(3000);
			m.runScenario();
		}
	}
	

}
