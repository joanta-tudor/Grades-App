package observer;

import java.util.ArrayList;
import java.util.List;

public class Observable {
    List<Observer> list=new ArrayList<>() ;

    public void notifyObservers()
    {
        for (Observer ob:list)
            ob.update();
    }

    public void addObserver(Observer ob)
    {
        list.add(ob);
    }
}
