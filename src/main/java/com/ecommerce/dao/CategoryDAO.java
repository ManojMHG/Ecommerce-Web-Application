package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.model.Category;
import com.ecommerce.util.DBConnection;

public class CategoryDAO {

    public boolean addCategory(
            Category category
    ) {

        boolean status = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "INSERT INTO categories "
                    + "(category_name, description) "
                    + "VALUES (?, ?)";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setString(
                    1,
                    category.getCategoryName()
            );

            preparedStatement.setString(
                    2,
                    category.getDescription()
            );

            int rowsInserted =
                    preparedStatement.executeUpdate();

            status =
                    rowsInserted > 0;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    public List<Category> getAllCategories() {

        List<Category> categoryList =
                new ArrayList<>();

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM categories";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while(resultSet.next()) {

                Category category =
                        new Category();

                category.setCategoryId(
                        resultSet.getInt(
                                "category_id"
                        )
                );

                category.setCategoryName(
                        resultSet.getString(
                                "category_name"
                        )
                );

                category.setDescription(
                        resultSet.getString(
                                "description"
                        )
                );

                categoryList.add(category);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return categoryList;
    }
    
    
    public Category getCategoryById(
            int categoryId
    ) {

        Category category = null;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM categories "
                    + "WHERE category_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(
                    1,
                    categoryId
            );

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if(resultSet.next()) {

                category =
                        new Category();

                category.setCategoryId(
                        resultSet.getInt(
                                "category_id"
                        )
                );

                category.setCategoryName(
                        resultSet.getString(
                                "category_name"
                        )
                );

                category.setDescription(
                        resultSet.getString(
                                "description"
                        )
                );
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return category;
    }
    
    
    public boolean updateCategory(
            Category category
    ) {

        boolean status = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "UPDATE categories "
                    + "SET category_name = ?, "
                    + "description = ? "
                    + "WHERE category_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setString(
                    1,
                    category.getCategoryName()
            );

            preparedStatement.setString(
                    2,
                    category.getDescription()
            );

            preparedStatement.setInt(
                    3,
                    category.getCategoryId()
            );

            int rowsUpdated =
                    preparedStatement.executeUpdate();

            status =
                    rowsUpdated > 0;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return status;
    }
    
    public boolean deleteCategory(
            int categoryId
    ) {

        boolean status = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "DELETE FROM categories "
                    + "WHERE category_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(
                    1,
                    categoryId
            );

            int rowsDeleted =
                    preparedStatement.executeUpdate();

            status =
                    rowsDeleted > 0;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return status;
    }
    
    
}