package net.lazars.chores.adapter.redis.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("items")
public class ItemDocument {

  @Id private String id;

  private String name;

  private String image;

  private String categoryId;
}
