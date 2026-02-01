package net.shelly.taskManager.controller;

import net.shelly.taskManager.entity.TaskEntry;
import net.shelly.taskManager.entity.User;
import net.shelly.taskManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user ){
        if (user.getTaskEntries() == null) {
            user.setTaskEntries(new ArrayList<>());
        }

        userService.saveNewUser(user);
    }

     @GetMapping("/health-check")
    public String healthCheck(){
        return "ok-running";
    }
}
