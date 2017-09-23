package life.happyholiday.models;

import java.util.Date;

/**
 * Created by tliy916e on 16/9/17.
 */

public class EventModel {

    private String title;
    private int attendingCount;
    private int vacancy;
    private Date startDate;
    private Date endDate;

    public EventModel() {
    }

    public EventModel(String title, int attendingCount, int vacancy, Date startDate, Date endDate) {
        this.title = title;
        this.attendingCount = attendingCount;
        this.vacancy = vacancy;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAttendingCount() {
        return attendingCount;
    }

    public void setAttendingCount(int attendingCount) {
        this.attendingCount = attendingCount;
    }

    public int getVacancy() {
        return vacancy;
    }

    public void setVacancy(int vacancy) {
        this.vacancy = vacancy;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}