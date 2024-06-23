package controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.model.contactInfo;

public class contactInfoDao {

    private Connection con;

    public contactInfoDao(Connection con) {
        super();
        this.con = con;
    }

    public boolean saveInfo(contactInfo info) {
        boolean flag = false;

        try {
            String query = "INSERT INTO contactinfo(phoneNumber, email, message) VALUES(?, ?, ?)";
            PreparedStatement psmt = this.con.prepareStatement(query);
            psmt.setString(1, info.getPhoneNumber());
            psmt.setString(2, info.getEmail());
            psmt.setString(3, info.getMessage());

            int rowsAffected = psmt.executeUpdate();
            if (rowsAffected > 0) {
                flag = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
