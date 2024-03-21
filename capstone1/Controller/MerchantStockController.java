package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponce.ApiResponce;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchantstock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity getMerchantStock(){
        ArrayList<MerchantStock> merchantStocks=merchantStockService.getMerchantStocks();
        return ResponseEntity.status(200).body(merchantStocks);
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
       boolean isadded= merchantStockService.addMerchantStock(merchantStock);
        if(isadded){
        return ResponseEntity.status(200).body(new ApiResponce("MerchantStock added"));}
        return ResponseEntity.status(400).body(new ApiResponce("cannot added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable int id, @RequestBody @Valid MerchantStock merchantStock,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isupdated=merchantStockService.updateMerchantStock(id,merchantStock);
        if(isupdated){
            return ResponseEntity.status(200).body(new ApiResponce("MerchantStock updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponce("not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable int id){
        boolean isdeleted=merchantStockService.deleteMerchantStock(id);
        if(isdeleted){
            return ResponseEntity.status(200).body(new ApiResponce("MerchantStock deleted"));
        }
        return ResponseEntity.status(400).body("not found");
    }

    @PutMapping("addstock/{productid}/{merchantid}/{add}")
    private ResponseEntity addstock(@PathVariable int productid, @PathVariable int merchantid,@PathVariable int add){

       boolean isadd= merchantStockService.addstock(productid,merchantid,add);
        if(isadd){
        return ResponseEntity.status(200).body(new ApiResponce("stock change"));}
        return ResponseEntity.status(400).body(new ApiResponce("stock not change"));
    }
    @GetMapping("/bystock/{stock}")
    public ResponseEntity bystock(@PathVariable int stock){
        ArrayList<MerchantStock> s=merchantStockService.bystock(stock);
        if(s==null){
            return ResponseEntity.status(400).body(new ApiResponce("not found"));
        }
        return ResponseEntity.status(200).body(s);
    }

}
