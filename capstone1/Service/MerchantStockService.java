package com.example.capstone1.Service;

import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    private final ProductService productService;
    private final MerchantService merchantService;



    public ArrayList<MerchantStock> getMerchantStocks() {
        return merchantStocks;
    }

    public boolean addMerchantStock(MerchantStock merchantStock) {
       if(merchantService.check(merchantStock.getMerchantid())&&merchantService.check(merchantStock.getProductid())){
        merchantStocks.add(merchantStock);
       return true;}
       else
           return false;
    }

    public boolean updateMerchantStock(int id, MerchantStock merchantStock) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == id) {
                merchantStocks.set(i, merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchantStock(int id) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == id) {
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean addstock(int productid,int merchantid ,int add) {


           for(MerchantStock ms :merchantStocks){
                 if(ms.getProductid()==productid && ms.getMerchantid()==merchantid){
                     ms.setStock(ms.getStock()+add);
                     return true;
                 }
             }

         return false;
}


    public boolean check(int id ){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if(merchantStocks.get(i).getId()==id){
                return true;
            }
        }
        return false;
    }
    public ArrayList<MerchantStock> bystock(int stock){
        ArrayList<MerchantStock> stocks=new ArrayList<>();
        for(MerchantStock m:merchantStocks){
                if(m.getStock()>=stock){
                    stocks.add(m);
                }}

        return stocks;
    }

}
