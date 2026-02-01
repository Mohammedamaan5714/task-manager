package net.shelly.taskManager.controller;

import net.shelly.taskManager.entity.TaskEntry;
import net.shelly.taskManager.entity.User;
import net.shelly.taskManager.service.TaskEntryService;
import net.shelly.taskManager.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/entry")
public class TaskEntryController {

    @Autowired
    private TaskEntryService taskEntryService;

    @Autowired
    private UserService userService;

//    @PostMapping
//    public TaskEntry createTask(@RequestBody TaskEntry taskEntry){
//        taskEntryService.createTask(taskEntry);
//        return taskEntry;
//    }

//    @GetMapping
//    public ResponseEntity<?> getAll(){
//        List<TaskEntry> all = taskEntryService.getAll();
//        if (all !=null & !all.isEmpty()){
//            return new ResponseEntity<>(all, HttpStatus.ACCEPTED);
//        }
//        else{
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//    } //for getting all entries

    @GetMapping
    public ResponseEntity<?> getAlltaskEntriesOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user =userService.findByUserName(userName);
        List<TaskEntry> all = user.getTaskEntries();
        if (all !=null & !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<TaskEntry> createEntry(@RequestBody TaskEntry myEntry){

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName=authentication.getName();
            taskEntryService.createTask(myEntry,userName);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("/id/{myid}")
    public ResponseEntity<TaskEntry>  getJournalById(@PathVariable ObjectId myid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user=userService.findByUserName(userName);
        List <TaskEntry> collect =user.getTaskEntries().stream().filter(x->x.getId().equals(myid)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<TaskEntry> journalEntry = taskEntryService.findById(myid);
            if (journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/id/{myid}")
    public ResponseEntity<?> deleteTaskById(@PathVariable ObjectId myid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean removed=taskEntryService.deleteById(myid,userName);
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/id/{myid}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId myid,@RequestBody TaskEntry newEntry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User user=userService.findByUserName(userName);
        List <TaskEntry> collect =user.getTaskEntries().stream().filter(x->x.getId().equals(myid)).collect(Collectors.toList());

        if(!collect.isEmpty()) {
            Optional<TaskEntry> journalEntry=taskEntryService.findById(myid);

            if (journalEntry.isPresent()) {
                TaskEntry old = journalEntry.get();
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                taskEntryService.createTask(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @DeleteMapping("/deleteById/{myid}")
//    public ResponseEntity<?> deleteById(@PathVariable ObjectId myid){
//        Optional<TaskEntry> taskEntry=taskEntryService.findById(myid);
//        boolean removed=taskEntryService.deleteById(myid);
//        if (removed){
//            return new ResponseEntity<>(removed, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }


}
