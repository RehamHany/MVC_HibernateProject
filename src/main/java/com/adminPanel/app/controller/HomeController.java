package com.adminPanel.app.controller;

import com.adminPanel.app.dao.ProductDAO;
import com.adminPanel.app.model.Product;
import com.adminPanel.app.model.ProductDetails;
import com.adminPanel.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController
{

    @Autowired
    private ProductService productService;

    @GetMapping( value = "/")
    public String showHomePage(Model productModel) throws ParseException {
        List<Product> productList = productService.getAllProducts();
        productModel.addAttribute("productsList" , productList);
        return "homePage";
    }

    @GetMapping("/addProduct")
    public String showAddNewProduct(Model model)
    {
        model.addAttribute("productModel" , new ProductDetails());
        return "addProductForm";
    }

    @PostMapping( "/processAddProduct" )
    public String processAddProduct(@ModelAttribute("productModel") ProductDetails productDetails , BindingResult bindingResult , Model productModel)
    {
        if(bindingResult.hasErrors())
            return "addProductForm";
        productService.insert(productDetails);
        List<Product> productList = productService.getAllProducts();
        productModel.addAttribute("productsList" , productList);
        return "redirect:/";
    }

    @GetMapping("/updateProduct")
    public String showUpdateForm(@RequestParam("productId") int id ,  Model model)
    {
        //get the product related to the id
        Product product = productService.findById(id);

        //set the product details related to the product with the id and add it to the model
        ProductDetails productDetails = product.getProductDetails();
        productDetails.setProduct(product);
        model.addAttribute("productModel" , productDetails);
        return "updateDetailsForm";
    }

    @PostMapping( "/processUpdateProduct" )
    public String processUpdateProduct(@ModelAttribute("productModel") ProductDetails productDetails ,  Model productModel)
    {
        productService.update(productDetails);
        List<Product> productList = productService.getAllProducts();
        productModel.addAttribute("productsList" , productList);
        return "redirect:/";
    }


    @GetMapping("/deleteProduct")
    public String deleteCustomer(@RequestParam("productId") int id) {

        
        productService.deleteById(id);

        return "redirect:/";
    }

    @GetMapping("/showDetails")
    public String showProductDetails(@RequestParam("productId") int id ,  Model model) {

        //get the product related to the id
        Product product = productService.findById(id);

        //set the product details related to the product with the id and add it to the model
        ProductDetails productDetails = product.getProductDetails();
        productDetails.setProduct(product);
        model.addAttribute("productModel" , productDetails);
        return "showDetailsForm";
    }

    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/searchProduct")
    public String searchProducts(@RequestParam("searchKey") String searchKey , Model productModel)
    {
        List<Product> productList = productService.findByName(searchKey);
        productModel.addAttribute("productsList" , productList);
        return "homePage";
    }
}
