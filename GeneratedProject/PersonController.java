package {{packageName}};

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/{{classNameLower}}s")
public class {{className}}Controller {

    @Autowired
    private {{className}}Service service;

    @GetMapping
    public List<{{className}}> getAll() {
        return service.findAll();
    }

    @PostMapping
    public {{className}} create(@RequestBody {{className}} entity) {
        return service.save(entity);
    }

    @GetMapping("/{id}")
    public {{className}} getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}