package net.lazars.chores.adapter.db.entity;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "categories", schemaVersion = "1.0")
public class CategoryEntity {

  @Id
  private String id;

  private String name;

  private String image;
}
