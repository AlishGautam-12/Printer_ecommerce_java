package controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.model.Category;

public class CategoryDao {
	private Connection con;

	public CategoryDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean saveCategory(Category category) {
		boolean flag = false;

		try {
			String query = "insert into category(name) values(?)";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setString(1, category.getCategoryName());
			

			psmt.executeUpdate();
			flag = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public List<Category> getAllCategories() {

		List<Category> list = new ArrayList<>();
		try {

			String query = "select * from category";
			Statement statement = this.con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				Category category = new Category();
				category.setCategoryId(rs.getInt("cid"));
				category.setCategoryName(rs.getString("name"));
				

				list.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	public Category getCategoryById(int cid) {
		Category category = new Category();
		try {
			String query = "select * from category where cid = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setInt(1, cid);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				category.setCategoryId(rs.getInt("cid"));
				category.setCategoryName(rs.getString("name"));
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}
	public String getCategoryName(int catId) {
		String category = "";
		try {
			String query = "select * from category where cid = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setInt(1, catId);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				category = rs.getString("name");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}
	
	public boolean updateCategory(Category cat) {
		try {
			String query = "update category set name=? where cid=?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setString(1, cat.getCategoryName());
			
			psmt.setInt(2, cat.getCategoryId());
			
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void deleteCategory(int cid) {
		try {
			String query = "delete from category where cid = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setInt(1, cid);
			
			psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int categoryCount() {
		int count = 0;
		try {
			String query = "select count(*) from category";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			count = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
}
