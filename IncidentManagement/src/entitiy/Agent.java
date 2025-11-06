package entitiy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import observer.AgentAvailabilityObserver;

public class Agent implements Identifyable{

    private final String id;
    private final String name;
    private final Set<String> specializations;
    private final List<Issue> workHistory;
    private boolean isAvailable;
    private final List<AgentAvailabilityObserver> observers;

    public Agent(String id, String name, Set<String> specializations) {
        this.id = id;
        this.name = name;
        this.specializations = specializations;
        this.isAvailable = true;
        this.workHistory = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<String> getSpecializations() {
        return specializations;
    }

    public List<Issue> getWorkHistory() {
        return workHistory;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
        if(isAvailable()){
            notifyObservers();
        }
    }

    public List<AgentAvailabilityObserver> getObservers() {
        return observers;
    }

    public void notifyObservers() {
        for(AgentAvailabilityObserver obs : observers) {
            obs.onAgentAvailable(this);
        }
    }
}
