package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponce.ApiResponce;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getProduct(){
        ArrayList<Product> products=productService.getProducts();
        return ResponseEntity.status(200).body(products);
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid Product product, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isadded=productService.addProduct(product);
        if (isadded){
        return ResponseEntity.status(200).body(new ApiResponce("product added"));}
        return ResponseEntity.status(400).body(new ApiResponce("cannot added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable int id, @RequestBody @Valid Product product, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isupdated=productService.updateProduct(id,product);
        if(isupdated){
            return ResponseEntity.status(200).body(new ApiResponce("product updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponce("not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable int id){
        boolean isdeleted=productService.deleteProduct(id);
        if(isdeleted){
            return ResponseEntity.status(200).body(new ApiResponce("product deleted"));
        }
        return ResponseEntity.status(400).body("not found");
    }

    @GetMapping("/bycategory/{categoryid}/{min}")
    public ResponseEntity bycategory(@PathVariable int categoryid,@PathVariable int min){
        ArrayList<Product> c=productService.bycategory(categoryid,min);
        if(c==null){
             return ResponseEntity.status(400).body(new ApiResponce("not found"));
        }
             return ResponseEntity.status(200).body(c);
    }



}
