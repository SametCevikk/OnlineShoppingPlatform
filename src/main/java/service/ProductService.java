package service;

import dao.ProductDAO;

public class ProductService {

    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO){
        this.productDAO= new ProductDAO();
    }
}
