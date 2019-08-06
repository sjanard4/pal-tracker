package io.pivotal.pal.tracker;

import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InMemoryTimeEntryRepository implements TimeEntryRepository {


    Map<Long,TimeEntry> timeEntryMap = new HashMap<Long,TimeEntry>();
    long id = 1;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {

        TimeEntry newTimeEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours());

        timeEntryMap.put(id, newTimeEntry);
        id++;
        return newTimeEntry;
    }


    @Override
    public TimeEntry find(long timeEntryId) {
        return (TimeEntry)timeEntryMap.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
       List<TimeEntry> result = new ArrayList(timeEntryMap.values());
       return result;
    }



    @Override
    public boolean delete(long timeEntryId) {
        timeEntryMap.remove(timeEntryId);
        return true;

    }


    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {

        if (timeEntryMap.containsKey(id)) {

            TimeEntry updatedTimeEntry = new TimeEntry(
                    id,
                    timeEntry.getProjectId(),
                    timeEntry.getUserId(),
                    timeEntry.getDate(),
                    timeEntry.getHours());
            timeEntryMap.replace(id, updatedTimeEntry);
            return updatedTimeEntry;
        } else {
            return null;
        }


    }


}
