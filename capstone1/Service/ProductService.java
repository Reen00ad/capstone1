package com.example.capstone1.Service;

import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {
    ArrayList<Product> products=new ArrayList<>();


    private final CategoryService categoryService;


    public ArrayList<Product> getProducts() {
        return products;
    }

    public boolean addProduct(Product product) {
        if(categoryService.check(product.getCategoryid())){
        products.add(product);
        return true;}
        else
            return false;
    }



    public boolean updateProduct(int id, Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.set(i, product);
                return true;

        }}
        return false;
    }

    public boolean deleteProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }
    public boolean check(int id ){
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getId()==id){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Product> bycategory(int categoryid,int min){
        ArrayList<Product> product1=new ArrayList<>();
        for(Product p:products){
            if(categoryService.check(categoryid)==true){

            if(p.getCategoryid()==categoryid && p.getPrice() >= min){
                    product1.add(p);
            }}
        }
        return product1;

}





}
