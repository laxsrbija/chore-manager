package net.lazars.chores.adapter.db.config;

import io.jsondb.JsonDBTemplate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import net.lazars.chores.adapter.db.entity.CategoryEntity;
import net.lazars.chores.adapter.db.entity.ItemEntity;
import net.lazars.chores.adapter.db.entity.TaskEntity;
import net.lazars.chores.adapter.db.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class JsondbConfiguration {
  private static final String ENTITIES_PACKAGE = UserEntity.class.getPackageName();

  @Value("${chores.db.store:/tmp}")
  private String storeDirectory;

  private static void registerCollections(final JsonDBTemplate jsonDBTemplate) {
    final List<Class<?>> classes = List.of(
        UserEntity.class,
        TaskEntity.class,
        CategoryEntity.class,
        ItemEntity.class);

    for (final Class<?> clazz : classes) {
      if (!jsonDBTemplate.collectionExists(clazz)) {
        jsonDBTemplate.createCollection(clazz);
      }
    }
  }

  @Bean
  public JsonDBTemplate getJsonDBTemplate() {
    final JsonDBTemplate jsonDBTemplate = new JsonDBTemplate(storeDirectory, ENTITIES_PACKAGE);
    registerCollections(jsonDBTemplate);

    return jsonDBTemplate;
  }
}
