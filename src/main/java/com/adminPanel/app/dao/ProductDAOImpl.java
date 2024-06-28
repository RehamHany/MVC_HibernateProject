package com.adminPanel.app.dao;

import com.adminPanel.app.model.Product;
import com.adminPanel.app.model.ProductDetails;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO
{
    @Autowired
    public SessionFactory sessionFactory;


    @Override
    public Product insert(ProductDetails productDetails) {
        try {
            Session session = sessionFactory.getCurrentSession();
            productDetails.setExpirationDate(productDetails.getExpirationDate());

            Product product = new Product(productDetails.getName());
            product.setProductDetails(productDetails);
            session.persist(product);
            return product;
        }catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Product findById(int id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.get(Product.class , id);
        }catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }


    public Product findByProductDetails(ProductDetails productDetails) {
        try {
            Session session = sessionFactory.getCurrentSession();
            ProductDetails productDetails2  = session.get(ProductDetails.class , productDetails.getId());
            return productDetails2.getProduct();
        }catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }


    public void deleteById(int theId) {

        try {
            Session session = sessionFactory.getCurrentSession();
            Product product = session.get(Product.class , theId);

           
            Query query = session.createQuery("delete from Product  where id=:productId");
            query.setParameter("productId" , theId);
            query.executeUpdate();

           
             query = session.createQuery("delete from ProductDetails  where id=:productDetailsId");
            query.setParameter("productDetailsId" , product.getProductDetails().getId());
            query.executeUpdate();
        }catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(ProductDetails newProduct) {
        try {
            Session session = sessionFactory.getCurrentSession();

            
            ProductDetails oldProductDetails = session.get(ProductDetails.class , newProduct.getId());
            Product oldProduct = oldProductDetails.getProduct();

            
            oldProduct.setName(newProduct.getName());
            oldProductDetails.setName(newProduct.getName());
            oldProductDetails.setExpirationDate(newProduct.getExpirationDate());
            oldProductDetails.setManufacturer(newProduct.getManufacturer());
            oldProductDetails.setPrice(newProduct.getPrice());
            oldProductDetails.setAvailable(newProduct.getAvailable());
            oldProductDetails.setProduct(newProduct.getProduct());

            
            oldProduct.setProductDetails(oldProductDetails);

            session.update(oldProduct);
        }catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }


    public List<Product> getAllProducts() {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("FROM Product");
            return (List<Product>) query.list();
        }catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }


    public List<Product> findByName(String searchKey) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Product where name=:productName");
        query.setParameter("productName" , searchKey);
        return query.list();
    }
}
