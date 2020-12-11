package agenda;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Description : An agenda that stores events
 */
public class Agenda {
    
    private ArrayList<Event> listEvent;
    
    public Agenda(){
        this.listEvent=new ArrayList();
    }
    
    
    /**
     * Adds an event to this agenda
     *
     * @param e the event to add
     */
    public void addEvent(Event e) {                
        this.listEvent.add(e);
    }

    /**
     * Computes the events that occur on a given day
     *
     * @param day the day to test
     * @return and iteraror to the events that occur on that day
     */
    public List<Event> eventsInDay(LocalDate day) {
        ArrayList<Event> liste = new ArrayList();
        for(Event e:listEvent){
            if(e.isInDay(day)){
                liste.add(e);
            }
        }
        return liste;
    }
    
    public List<Event> findByTitle(String titre){
        ArrayList<Event> liste = new ArrayList<>();
        
        for(Event e:listEvent){
            if(e.getTitle().equals(titre)){
                liste.add(e);
            }
        }
        
        return liste;
    }
    
    
    public boolean isFreeFor(Event event){
        for(Event e:listEvent){
            if((event.getStart().isAfter(e.getStart())&&event.getStart().isBefore(e.getStart().plus(e.getDuration())))||
               (event.getStart().plus(event.getDuration()).isAfter(e.getStart())&&event.getStart().plus(event.getDuration()).isBefore(e.getStart().plus(e.getDuration())))||
                event.getStart().isEqual(e.getStart())|| 
                event.getStart().plus(event.getDuration()).isEqual(e.getStart().plus(e.getDuration()))){
                return false;
            }
        }
        
        
        return true;
    }
}
