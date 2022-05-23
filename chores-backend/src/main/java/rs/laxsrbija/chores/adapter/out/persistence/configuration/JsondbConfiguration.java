package rs.laxsrbija.chores.adapter.out.persistence.configuration;

import io.jsondb.JsonDBTemplate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.laxsrbija.chores.adapter.out.persistence.entity.CategoryEntity;
import rs.laxsrbija.chores.adapter.out.persistence.entity.ObjectEntity;
import rs.laxsrbija.chores.adapter.out.persistence.entity.TaskEntity;
import rs.laxsrbija.chores.adapter.out.persistence.entity.UserEntity;

@Slf4j
@Configuration
public class JsondbConfiguration {

  private static final String STORE_DIRECTORY = "/c/chores/store";
  private static final String ENTITIES_PACKAGE = UserEntity.class.getPackageName();

  private static void registerCollections(final JsonDBTemplate jsonDBTemplate) {
    final List<Class<?>> classes = List.of(UserEntity.class, TaskEntity.class, CategoryEntity.class,
        ObjectEntity.class);

    for (final Class<?> clazz : classes) {
      if (!jsonDBTemplate.collectionExists(clazz)) {
        jsonDBTemplate.createCollection(clazz);
      }
    }
  }

  @Bean
  public JsonDBTemplate getJsonDBTemplate() {
    final JsonDBTemplate jsonDBTemplate = new JsonDBTemplate(STORE_DIRECTORY, ENTITIES_PACKAGE);
    registerCollections(jsonDBTemplate);

    return jsonDBTemplate;
  }
}
