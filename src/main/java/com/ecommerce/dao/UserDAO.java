package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ecommerce.model.User;
import com.ecommerce.util.DBConnection;
import com.ecommerce.util.PasswordUtil;

public class UserDAO {

    public boolean registerUser(User user) {

        boolean status = false;

        try {

            Connection connection = DBConnection.getConnection();

            String query =
                    "INSERT INTO users(name, email, password, phone, address, role) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setString(1, user.getName());

            preparedStatement.setString(2, user.getEmail());

            preparedStatement.setString(
                    3,
                    com.ecommerce.util.PasswordUtil
                            .hashPassword(
                                    user.getPassword()
                            )
            );

            preparedStatement.setString(4, user.getPhone());

            preparedStatement.setString(5, user.getAddress());

            preparedStatement.setString(6, user.getRole());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {

                status = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }
    
 
    
    public User validateUser(
            String email,
            String password
    ) {
    	   System.out.println("validateUser called");

        User user = null;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM users "
                    + "WHERE email = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            query
                    );

            preparedStatement.setString(
                    1,
                    email
            );

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if(resultSet.next()) {

                String hashedPassword =
                        resultSet.getString(
                                "password"
                        );
                
                System.out.println("Entered Password = " + password);

                System.out.println("Hash = " + hashedPassword);

                System.out.println(
                        "Match = "
                        + PasswordUtil.checkPassword(
                                password,
                                hashedPassword
                        )
                );

                boolean validPassword =
                        com.ecommerce.util.PasswordUtil
                        .checkPassword(
                                password,
                                hashedPassword
                        );

                if(validPassword) {

                    user = new User();

                    user.setUserId(
                            resultSet.getInt(
                                    "user_id"
                            )
                    );

                    user.setName(
                            resultSet.getString(
                                    "name"
                            )
                    );

                    user.setEmail(
                            resultSet.getString(
                                    "email"
                            )
                    );

                    user.setPassword(
                            hashedPassword
                    );

                    user.setPhone(
                            resultSet.getString(
                                    "phone"
                            )
                    );

                    user.setAddress(
                            resultSet.getString(
                                    "address"
                            )
                    );

                    user.setRole(
                            resultSet.getString(
                                    "role"
                            )
                    );

                    user.setOtpEnabled(
                            resultSet.getBoolean(
                                    "otp_enabled"
                            )
                    );
                }
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return user;
    }
    
    public boolean updateProfile(
            User user
    ) {

        boolean status = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "UPDATE users "
                    + "SET name = ?, "
                    + "email = ?, "
                    + "phone = ?, "
                    + "address = ?, "
                    + "otp_enabled = ? "
                    + "WHERE user_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setString(
                    1,
                    user.getName()
            );

            preparedStatement.setString(
                    2,
                    user.getEmail()
            );

            preparedStatement.setString(
                    3,
                    user.getPhone()
            );

            preparedStatement.setString(
                    4,
                    user.getAddress()
            );

            preparedStatement.setBoolean(
                    5,
                    user.isOtpEnabled()
            );

            preparedStatement.setInt(
                    6,
                    user.getUserId()
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
    
    public boolean updatePassword(
            int userId,
            String password
    ) {

        boolean status = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "UPDATE users "
                    + "SET password = ? "
                    + "WHERE user_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setString(
                    1,
                    password
            );

            preparedStatement.setInt(
                    2,
                    userId
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
    
    public boolean updateOTPSetting(
            int userId,
            boolean otpEnabled
    ) {

        boolean status = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "UPDATE users "
                    + "SET otp_enabled = ? "
                    + "WHERE user_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            query
                    );

            preparedStatement.setBoolean(
                    1,
                    otpEnabled
            );

            preparedStatement.setInt(
                    2,
                    userId
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
    
    public User getUserByEmail(
            String email
    ) {

        User user = null;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM users "
                    + "WHERE email = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            query
                    );

            preparedStatement.setString(
                    1,
                    email
            );

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if(resultSet.next()) {

                user = new User();

                user.setUserId(
                        resultSet.getInt(
                                "user_id"
                        )
                );

                user.setName(
                        resultSet.getString(
                                "name"
                        )
                );

                user.setEmail(
                        resultSet.getString(
                                "email"
                        )
                );

                user.setPassword(
                        resultSet.getString(
                                "password"
                        )
                );

                user.setPhone(
                        resultSet.getString(
                                "phone"
                        )
                );

                user.setAddress(
                        resultSet.getString(
                                "address"
                        )
                );

                user.setRole(
                        resultSet.getString(
                                "role"
                        )
                );

                user.setOtpEnabled(
                        resultSet.getBoolean(
                                "otp_enabled"
                        )
                );
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return user;
    }
    
    public java.util.List<User> getAllUsers() {

        java.util.List<User> userList =
                new java.util.ArrayList<>();

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM users";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            query
                    );

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while(resultSet.next()) {

                User user =
                        new User();

                user.setUserId(
                        resultSet.getInt(
                                "user_id"
                        )
                );

                user.setName(
                        resultSet.getString(
                                "name"
                        )
                );

                user.setEmail(
                        resultSet.getString(
                                "email"
                        )
                );

                user.setPhone(
                        resultSet.getString(
                                "phone"
                        )
                );

                user.setRole(
                        resultSet.getString(
                                "role"
                        )
                );

                userList.add(user);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return userList;
    }
    
    
    public boolean deleteUser(
            int userId
    ) {

        boolean status = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "DELETE FROM users "
                    + "WHERE user_id = ? "
                    + "AND role <> 'ADMIN'";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            query
                    );

            preparedStatement.setInt(
                    1,
                    userId
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