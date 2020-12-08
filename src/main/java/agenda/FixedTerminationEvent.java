package agenda;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Description : A repetitive event that terminates after a given date, or after
 * a given number of occurrences
 */
public class FixedTerminationEvent extends RepetitiveEvent {

    public LocalDate dateFin;
    public long occurence;
    
    
    /**
     * Constructs a fixed terminationInclusive event ending at a given date
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param terminationInclusive the date when this event ends
     */
    public FixedTerminationEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency, LocalDate terminationInclusive) {
        super(title, start, duration, frequency);
        this.dateFin=terminationInclusive;
        this.occurence=calculeOccurence();

    }

    /**
     * Constructs a fixed termination event ending after a number of iterations
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param numberOfOccurrences the number of occurrences of this repetitive event
     */
    public FixedTerminationEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency, long numberOfOccurrences) {
        super(title, start, duration, frequency);
        this.occurence=numberOfOccurrences;
        this.dateFin=calculeDateFin();
    }

    private LocalDate calculeDateFin(){
        switch(this.frequence) {
            case DAYS:
                return this.getStart().plus(this.getNumberOfOccurrences()-1,ChronoUnit.DAYS).toLocalDate();
            case WEEKS:
                return this.getStart().plus(this.getNumberOfOccurrences()-1,ChronoUnit.WEEKS).toLocalDate();
            case MONTHS:
                return this.getStart().plus(this.getNumberOfOccurrences()-1,ChronoUnit.MONTHS).toLocalDate();
            default:
                return this.getStart().toLocalDate();
        }
    }
    
    
    private long calculeOccurence(){      
        return this.getStart().toLocalDate().until(dateFin, frequence)+1;
    }
    
    /**
     *
     * @return the termination date of this repetitive event
     */
    public LocalDate getTerminationDate() {
        return dateFin;   
    }

    public long getNumberOfOccurrences() {
        return occurence;
    }
    
    @Override
    public String toString(){
        return this.getTitle()+" "+this.getStart()+" "+this.getDuration()+" "+this.getFrequency()+this.getNumberOfOccurrences()+" "+this.getTerminationDate();
    }
        
}
