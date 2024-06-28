package com.adminPanel.app.service;

import com.adminPanel.app.dao.ProductDAO;
import com.adminPanel.app.model.Product;
import com.adminPanel.app.model.ProductDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductDAO productDAO;


    public ProductDetails insert(ProductDetails productDetails) {
        if(!productDetails.getName().isEmpty()) {
            productDAO.insert(productDetails);
            return productDetails;
        }
        else
            throw new NullPointerException();
    }


    public Product findById(int id) {
        return productDAO.findById(id);
    }

    public void deleteById(int id) {
         productDAO.deleteById(id);
    }


    public ProductDetails update(ProductDetails productDetails) {
            //search for the related product if exist or not
            Product product = productDAO.findByProductDetails(productDetails);
            if (product != null)
            {
                productDAO.update(productDetails);
            }
            else
                throw new NullPointerException();
        return productDetails;
    }


    @Transactional
    public List<Product> getAllProducts() {
        try {
            return productDAO.getAllProducts();
        }catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    @Transactional
    public List<Product> findByName(String searchKey) {
        return productDAO.findByName(searchKey);
    }
}
