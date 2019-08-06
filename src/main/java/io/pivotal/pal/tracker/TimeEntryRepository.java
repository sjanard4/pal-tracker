package io.pivotal.pal.tracker;

import org.springframework.context.annotation.Bean;

import java.util.List;


public interface TimeEntryRepository {


    TimeEntry create(TimeEntry timeEntry);
    TimeEntry find(long timeEntryId);

    List<TimeEntry> list();


    boolean delete(long timeEntryId);

    TimeEntry update(long id, TimeEntry timeEntry);
}
