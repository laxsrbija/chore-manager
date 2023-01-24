package net.lazars.chores.adapter.db.entity;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "objects", schemaVersion = "1.0")
public class ItemEntity {

  @Id
  private String id;

  private String name;

  private String image;

  private String categoryId;
}
