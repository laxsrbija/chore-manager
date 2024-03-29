package net.lazars.chores.adapter.redis.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@AllArgsConstructor
@RedisHash("categories")
public class CategoryDocument {

  @Id private String id;

  private String name;

  private String householdId;
}
