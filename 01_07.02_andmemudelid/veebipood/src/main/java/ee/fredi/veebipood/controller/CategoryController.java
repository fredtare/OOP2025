package ee.fredi.veebipood.controller;

import ee.fredi.veebipood.entity.Category;
import ee.fredi.veebipood.entity.Category;
import ee.fredi.veebipood.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    //localhosts:8080/categories
    @GetMapping("categories")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }


    @PostMapping("categories") //POSTMAN rakendus
    public List<Category> addCategory(@RequestBody Category category) {
        if (category.getId() != null) {
            throw new RuntimeException("ERROR_CANNOT_ADD_W_ID");
        }

        //salvesta kategooria kui on id
        categoryRepository.save(category);

        return categoryRepository.findAll();
    }

    //DELETE localhost:8080/categories/id
    @DeleteMapping("categories/{id}")
    public List<Category> deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return categoryRepository.findAll();
    }


    @GetMapping("categories/{id}")
    public Category  getCategory(@PathVariable Long id){
            categoryRepository.findById(id);
            return categoryRepository.findById(id).orElseThrow();
        }
}
