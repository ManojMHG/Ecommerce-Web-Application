package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.model.Product;
import com.ecommerce.util.DBConnection;

public class ProductDAO {

    public List<Product> getAllProducts() {

        List<Product> productList = new ArrayList<>();

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM products";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while (resultSet.next()) {

                Product product = new Product();

                product.setProductId(
                        resultSet.getInt("product_id")
                );

                product.setCategoryId(
                        resultSet.getInt("category_id")
                );

                product.setProductName(
                        resultSet.getString("product_name")
                );

                product.setDescription(
                        resultSet.getString("description")
                );

                product.setPrice(
                        resultSet.getDouble("price")
                );

                product.setStockQuantity(
                        resultSet.getInt("stock_quantity")
                );

                product.setImageUrl(
                        resultSet.getString("image_url")
                );

                product.setBrand(
                        resultSet.getString("brand")
                );

                productList.add(product);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return productList;
    }
    
    public boolean addProduct(Product product) {

        boolean status = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "INSERT INTO products "
                    + "(category_id, product_name, description, "
                    + "price, stock_quantity, image_url, brand) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(
                    1,
                    product.getCategoryId()
            );

            preparedStatement.setString(
                    2,
                    product.getProductName()
            );

            preparedStatement.setString(
                    3,
                    product.getDescription()
            );

            preparedStatement.setDouble(
                    4,
                    product.getPrice()
            );

            preparedStatement.setInt(
                    5,
                    product.getStockQuantity()
            );

            preparedStatement.setString(
                    6,
                    product.getImageUrl()
            );

            preparedStatement.setString(
                    7,
                    product.getBrand()
            );

            int rowsInserted =
                    preparedStatement.executeUpdate();

            if (rowsInserted > 0) {

                status = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }
    
    public Product getProductById(int productId) {

        Product product = null;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM products "
                    + "WHERE product_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, productId);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if (resultSet.next()) {

                product = new Product();

                product.setProductId(
                        resultSet.getInt("product_id")
                );

                product.setCategoryId(
                        resultSet.getInt("category_id")
                );

                product.setProductName(
                        resultSet.getString("product_name")
                );

                product.setDescription(
                        resultSet.getString("description")
                );

                product.setPrice(
                        resultSet.getDouble("price")
                );

                product.setStockQuantity(
                        resultSet.getInt("stock_quantity")
                );

                product.setImageUrl(
                        resultSet.getString("image_url")
                );

                product.setBrand(
                        resultSet.getString("brand")
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return product;
    }
    
    public boolean updateProduct(Product product) {

        boolean status = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "UPDATE products "
                    + "SET category_id = ?, "
                    + "product_name = ?, "
                    + "description = ?, "
                    + "price = ?, "
                    + "stock_quantity = ?, "
                    + "image_url = ?, "
                    + "brand = ? "
                    + "WHERE product_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(
                    1,
                    product.getCategoryId()
            );

            preparedStatement.setString(
                    2,
                    product.getProductName()
            );

            preparedStatement.setString(
                    3,
                    product.getDescription()
            );

            preparedStatement.setDouble(
                    4,
                    product.getPrice()
            );

            preparedStatement.setInt(
                    5,
                    product.getStockQuantity()
            );

            preparedStatement.setString(
                    6,
                    product.getImageUrl()
            );

            preparedStatement.setString(
                    7,
                    product.getBrand()
            );

            preparedStatement.setInt(
                    8,
                    product.getProductId()
            );

            int rowsUpdated =
                    preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {

                status = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }
    
    public boolean deleteProduct(int productId) {

        boolean status = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "DELETE FROM products "
                    + "WHERE product_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, productId);

            int rowsDeleted =
                    preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {

                status = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }
    
    public List<Product> searchProducts(
            String keyword
    ) {

        List<Product> productList =
                new ArrayList<>();

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM products "
                    + "WHERE product_name LIKE ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setString(
                    1,
                    "%" + keyword + "%"
            );

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while (resultSet.next()) {

                Product product =
                        new Product();

                product.setProductId(
                        resultSet.getInt("product_id")
                );

                product.setCategoryId(
                        resultSet.getInt("category_id")
                );

                product.setProductName(
                        resultSet.getString("product_name")
                );

                product.setDescription(
                        resultSet.getString("description")
                );

                product.setPrice(
                        resultSet.getDouble("price")
                );

                product.setStockQuantity(
                        resultSet.getInt("stock_quantity")
                );

                product.setImageUrl(
                        resultSet.getString("image_url")
                );

                product.setBrand(
                        resultSet.getString("brand")
                );

                productList.add(product);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return productList;
    }
    
    public List<Product> getProductsByCategory(
            int categoryId
    ) {

        List<Product> productList =
                new ArrayList<>();

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM products "
                    + "WHERE category_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, categoryId);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while (resultSet.next()) {

                Product product =
                        new Product();

                product.setProductId(
                        resultSet.getInt("product_id")
                );

                product.setCategoryId(
                        resultSet.getInt("category_id")
                );

                product.setProductName(
                        resultSet.getString("product_name")
                );

                product.setDescription(
                        resultSet.getString("description")
                );

                product.setPrice(
                        resultSet.getDouble("price")
                );

                product.setStockQuantity(
                        resultSet.getInt("stock_quantity")
                );

                product.setImageUrl(
                        resultSet.getString("image_url")
                );

                product.setBrand(
                        resultSet.getString("brand")
                );

                productList.add(product);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return productList;
    }
    
    public boolean updateStock(
            int productId,
            int quantityPurchased
    ) {

        boolean status = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "UPDATE products "
                    + "SET stock_quantity = "
                    + "stock_quantity - ? "
                    + "WHERE product_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(
                    1,
                    quantityPurchased
            );

            preparedStatement.setInt(
                    2,
                    productId
            );

            int rowsUpdated =
                    preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {

                status = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }
    
}
