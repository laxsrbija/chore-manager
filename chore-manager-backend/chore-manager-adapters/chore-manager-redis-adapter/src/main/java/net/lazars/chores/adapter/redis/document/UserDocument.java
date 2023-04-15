package net.lazars.chores.adapter.redis.document;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.lazars.chores.core.model.Permission;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@AllArgsConstructor
@RedisHash("users")
public class UserDocument {

  @Id private String id;

  private String name;

  private String email;

  private String encodedPassword;

  private String image;

  private List<String> householdIds;

  private List<Permission> permissions;
}
