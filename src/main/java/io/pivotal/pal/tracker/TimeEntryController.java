package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {


    TimeEntryRepository timeEntryRepository = null;


    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping(path="/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {

        TimeEntry newTimeEntry = timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity(newTimeEntry, HttpStatus.CREATED);
    }


    @GetMapping(value="/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long timeEntryId) {

        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
        if(timeEntry == null) {
            return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.OK);
    }


    @GetMapping(value="/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
      return new ResponseEntity<List<TimeEntry>> (timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping(value="/time-entries/{id}")
    public ResponseEntity update(@PathVariable("id") long timeEntryId, @RequestBody TimeEntry expected) {

        TimeEntry expectedTimeEntry = timeEntryRepository.update(timeEntryId, expected);
        if (expectedTimeEntry == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<TimeEntry>(expectedTimeEntry, HttpStatus.OK);


    }

    @DeleteMapping(value="/time-entries/{id}")
    public ResponseEntity delete( @PathVariable("id") long timeEntryId) {

        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
