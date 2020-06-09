package ex1.patternObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject
{
    protected List<Observer> observerList;

    public Subject()
    {
        observerList = new ArrayList<Observer>();
    }

    public void attach(Observer observer)
    {
        observerList.add(observer);
    }

    public void detach(Observer observer)
    {
        observerList.remove(observer);
    }

    public void notifyObservers()
    {
        for(Observer observer : observerList)
        {
            observer.update(this);
        }
    }
}
