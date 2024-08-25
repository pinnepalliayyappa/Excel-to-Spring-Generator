package {{packageName}};

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class {{className}}Service {

    @Autowired
    private {{className}}Repository repository;

    public List<{{className}}> findAll() {
        return repository.findAll();
    }

    public {{className}} save({{className}} entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public {{className}} findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}