package net.shelly.taskManager.service;

import lombok.extern.slf4j.Slf4j;
import net.shelly.taskManager.entity.TaskEntry;
import net.shelly.taskManager.entity.User;
import net.shelly.taskManager.repository.TaskEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class TaskEntryService {

    @Autowired
    private  TaskEntryRepo taskEntryRepo;

    @Autowired
    private UserService userService;

    public void createTask (TaskEntry taskEntry){

        taskEntryRepo.save(taskEntry);

    }

    public void createTask(TaskEntry taskEntry, String userName){
        try {

            User user =userService.findByUserName(userName);
            TaskEntry saved = taskEntryRepo.save(taskEntry);//isse jo entry save hui thi wo entry meko mil jyegi isse saved me
            user.getTaskEntries().add(saved);//list hai uske andar save kra di
            userService.saveEntry(user);//ab user me save kra di

        } catch (Exception e) {
            log.error("Exception",e);
            throw new RuntimeException("something went wrong with the save entry",e);
        }
    }

    public List<TaskEntry> getAll() {

        return taskEntryRepo.findAll();
    }

    public Optional<TaskEntry> findById(ObjectId id){//??????????????????????optional
        return taskEntryRepo.findById(id);

    }


    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed=false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getTaskEntries().removeIf(x -> x.getId().equals(id));//remove if se true return hota hai
            if (removed) {
                userService.saveEntry(user);//same id pe save chalate hai to update ho jata hai mongodb me
                taskEntryRepo.deleteById(id);

            }
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting the entry.e");
        }
        return removed;
    }
}
