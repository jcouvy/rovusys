// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package rovusystem;

import java.util.ArrayList;

public abstract class Subject {

    private ArrayList<Observer> observers = new ArrayList<Observer>();
    private ERequest request;

    public Subject() {
    	request = ERequest.EXPLORE;
    }
    
    public ERequest getRequest() {
        return request;
    }
    
    public void setRequest(ERequest req) {
    	request = req;
    }

    public void notifyObservers() {
        for (Observer obs : observers)
            obs.update();
    }

    public void attach(Observer obs) {
        observers.add(obs);
    }

    public void detach(Observer obs) {
        observers.remove(obs);
    }

};
