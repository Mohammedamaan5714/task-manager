package net.shelly.taskManager.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "task_entry")
@NoArgsConstructor   // ⭐ THIS IS IMPORTANT
@AllArgsConstructor
public class TaskEntry {

    @Id
    private ObjectId id;
    @JsonProperty("id")
    public String getIdAsString() {
        return id != null ? id.toHexString() : null;
    }
    @NotNull
    public String title;
    public String content;


}
