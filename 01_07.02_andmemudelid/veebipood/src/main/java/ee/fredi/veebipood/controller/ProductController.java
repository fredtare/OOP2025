package ee.fredi.veebipood.controller;

import ee.fredi.veebipood.entity.Product;
import ee.fredi.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController //sellega tehakse api päringuid
public class ProductController {

    @Autowired
    ProductRepository productRepository; //ühendab productrepositoriga
    // kui programm k'ivitub ta loob selle controlleri ja autopistab tema sisse ka productcontrolleri koos kõikide vajalike ühendustega.


    //need siin all on API otspunktid

    //localhosts:8080/products
    @GetMapping("products")
    public List<Product> getProducts() {
        return productRepository.findAll(); //selekt * from extends JpaRepository<product>
    }

    @PostMapping("products") //POSTMAN rakendus
    public List<Product> addProduct(@RequestBody Product product){
        if (product.getId() != null) {
            throw new RuntimeException("ERROR_CANNOT_ADD_W_ID"); //selles stiilis erroreid on lihtsam tõlkida frontendikatel
        }
        if (product.getPrice() <= 0) {
            throw new RuntimeException("ERROR_PRICE_MUST_BE_POSITIVE");
        }
        productRepository.save(product); //prductrepository viitab ProductRepositorile mis h'ndlib su sqli ise
        return productRepository.findAll();
    }
    //DELETE localhost:8080/products/id
    @DeleteMapping("products/{id}")
    public List<Product> deleteProduct(@PathVariable Long id){ //kui long on väikse tähega on primiiivne väärtus, hoiavad ainult väärust. Suure tähega on klass. Klass sisaldab ka funke
        //repository ka dikteerib et peab olema primaarvõti klass mitte väärtus
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

    @PutMapping("products") //kui id lisada külge, siis ta editib. Ilma IDta ta lisab. Kasuta seda muutmiseks, postmap lisamiseks
    public List<Product> editProduct(@RequestBody Product product){
        if (product.getId() == null ){
            throw new RuntimeException("ERROR_CANNOT_ADD_WO_ID");
        }
        if (product.getPrice() <= 0) {
            throw new RuntimeException("ERROR_PRICE_MUST_BE_POSITIVE");
        }
        productRepository.save(product); //prductrepository viitab ProductRepositorile mis h'ndlib su sqli ise
        return productRepository.findAll();
    }

    @GetMapping("products/{id}") //tagastab ühe toote
    public Product  getProduct(@PathVariable Long id){
        productRepository.findById(id);
        return productRepository.findById(id).orElseThrow();
    }

    //kui on 2+ param peaks kasutama RequestParam
    //PATCH localhost:8080/products?id=SINUVÄÄRTUS&field=SINUVÄÄRTUS&value=SINUVÄÄRTUS
    @PatchMapping("products")
    public List<Product> editProductValue(@RequestParam Long id, String field, String value){
        if (id == null ){
            throw new RuntimeException("ERROR_CANNOT_ADD_WO_ID");
        }
        Product product = productRepository.findById(id).orElseThrow();
        switch (field) {
            case "name" -> product.setName(value);
            case "price" -> {
                if (Double.parseDouble(value) <= 0) {
                    throw new RuntimeException("ERROR_PRICE_MUST_BE_POSITIVE");
                }
                product.setPrice(Double.parseDouble(value));
            }
            case "image" -> product.setImage(value);
            case "active" -> product.setActive(Boolean.parseBoolean(value));

        }
        productRepository.save(product); //v]tab id j'rgi toote ja salvestab andmebaasi
        return productRepository.findAll();
    }
    //
//    @GetMapping("/category-products")
//    public List<Product> getCategoryProducts(@RequestParam Long categoryId){
//
//           List<Product> products = productRepository.findAll();
//           List<Product> filteredProducts = new ArrayList<>();
//
//           for(Product productIndex: products) {  //tyyp, sinu pandud nimi, mis arrayst
//
//               if(productIndex. getCategory().getId().equals(categoryId));
//                filteredProducts.add(productIndex);
//           }
//
//           return filteredProducts;
//    }
    //== kontrollib kas vasak ja parem on identne
    //.equals kontrollib nende väärtuste identsust. Kas klass ka vastab samamoodi mitte ainult mälupesa sisu

    //sama mis seal üleval aga tegime product repos uue sql requesti.
    @GetMapping("/category-products")
    public Page<Product> getCategoryProducts(@RequestParam Long categoryId, Pageable pageable){
        if (categoryId == -1){
            return productRepository.findAll(pageable); //return l]petab funktsiooni, else blokki pole vaja
        }
        return productRepository.findByCategory_Id(categoryId, pageable);
    }


}//lõpu logiline
