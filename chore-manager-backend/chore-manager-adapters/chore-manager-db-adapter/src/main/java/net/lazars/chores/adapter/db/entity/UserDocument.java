package net.lazars.chores.adapter.db.entity;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "users", schemaVersion = "1.0")
public class UserDocument {

  @Id
  private String id;

  private String name;

  private String email;

  private String image;
}
