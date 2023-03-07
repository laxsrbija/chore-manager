package net.lazars.chores.adapter.db.entity;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.lazars.chores.core.model.Permission;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "users", schemaVersion = "1.0")
public class UserDocument {

  @Id
  private String id;

  private String name;

  private String email;

  private String encodedPassword;

  private String image;

  private List<String> householdIds;

  private List<Permission> permissions;
}
