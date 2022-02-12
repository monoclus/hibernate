package at.alley.hibernate_work.entities;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;
import java.util.stream.Collectors;

public class TestDataLoader {
    public TestDataLoader() {
    }

    public Map<Long, BookEntity> loadData(String resource) {
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(resource);
        BookEntities bookEntities = yaml.load(inputStream);
        return bookEntities.getBooks().stream().collect(Collectors.toMap(BookEntity::getId, k -> k));
    }
}
