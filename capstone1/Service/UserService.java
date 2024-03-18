package com.example.capstone1.Service;

import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Product> wishlists=new ArrayList<>();


    private final MerchantStockService merchantStockService;
    private final ProductService productService;
    private final MerchantService merchantService;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean updateUser(int id, User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(int id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    public Boolean userbuy(int userid,int productid,int merchantid){
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId()==userid){
                for (int j = 0; j <merchantService.merchants.size() ; j++) {
                    if(merchantService.merchants.get(j).getId()==merchantid){
                        for (int k = 0; k < productService.products.size(); k++) {
                            if(productService.products.get(k).getId()==productid){
                                if(productService.products.get(k).getPrice() < users.get(i).getBalance()){
                                    int buy=merchantStockService.merchantStocks.get(k).getStock() -1;
                                    merchantStockService.merchantStocks.get(k).setStock(buy);
                                    double newbalance=users.get(i).getBalance()-productService.products.get(j).getPrice();
                                    users.get(i).setBalance(newbalance);
                                    return true;
                                }
                            }return false;
                        }
                    }
                }
            }
        }


        return false;
    }

    public ArrayList<Product> wishlist(int userid,int productid){
       // ArrayList<Product> wishlists=new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            for (Product p :productService.products){
        if(productService.check(productid)==true){
        if(p.getId()==productid && users.get(i).getId()==userid){

                wishlists.add(p);
            }}
        }}
        return wishlists;

    }

}




