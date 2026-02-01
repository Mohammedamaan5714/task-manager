package net.shelly.taskManager.repository;

import net.shelly.taskManager.entity.TaskEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TaskEntryRepo extends MongoRepository<TaskEntry, ObjectId> {
}
