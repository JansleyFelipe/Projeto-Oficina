package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connection.ConnectionFactory;
import DBO.User;

public class UserDAO {
    private Connection connection;

    public UserDAO() throws Exception {
        try {
            this.connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            throw new Exception("Error connecting to the database!");
        }
    }

    public boolean existUser(String mail) throws Exception {
        try {
            String sql = "SELECT * FROM users WHERE mail = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, mail);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new Exception("Error fetching user!");
        }
    }

    public void insertUser(User user) throws Exception {
        if(user == null) {
            throw new Exception("User not provided!");
        }

        try {
            String sql = "INSERT INTO users(mail, name, pass, coin) VALUES(?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getMail());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getPass());
            stmt.setInt(4, 1000);

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new Exception("Error inserting user!");
        }
    }

    public void updateUser(User user) throws Exception {
        if(user == null) {
            throw new Exception("User not provided!");
        }
        if(!existUser(user.getMail())) {
            throw new Exception("User not registered!");
        }

        try {
            String sql = "UPDATE users SET name=?, pass=?, coin=? WHERE mail=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPass());
            stmt.setInt(3, user.getCoin());
            stmt.setString(4, user.getMail());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new Exception("Error updating user data!");
        }
    }

    public boolean login(String mail, String pass) throws Exception {
        try {
            String sql = "SELECT * FROM users WHERE mail = ? AND pass = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, mail);
            stmt.setString(2, pass);
            ResultSet rs = stmt.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            throw new Exception("Error fetching user!");
        }
    }

    public User getUser(String mail) throws Exception {
        try {
            String sql = "SELECT * FROM users WHERE mail = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, mail);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            User user = new User();
            user.setMail(rs.getString("mail"));
            user.setName(rs.getString("name"));
            user.setPass(rs.getString("pass"));
            user.setCoin(rs.getInt("coin"));
            user.setBlocktime(rs.getTimestamp("blocktime"));
            
            return user;
        } catch(SQLException e) {
            throw new Exception("Error fetching user!");
        }
    }
    
    public void blockUser(User user) throws Exception {
        if(user == null) {
            throw new Exception("User not provided!");
        }
        if(!existUser(user.getMail())) {
            throw new Exception("User not registered!");
        }

        try {
            String sql = "UPDATE users SET blocktime=CURRENT_TIMESTAMP WHERE mail=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getMail());
            
            stmt.executeUpdate();
            stmt.close();  
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public void unblockUser(User user) throws Exception {
        if(user == null) {
            throw new Exception("User not provided!");
        }
        if(!existUser(user.getMail())) {
            throw new Exception("User not registered!");
        }
        
        try {
            String sql = "UPDATE users SET blocktime=NULL, coin=200 WHERE mail=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getMail());
            
            stmt.executeUpdate();
            stmt.close();  
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
    }
}
