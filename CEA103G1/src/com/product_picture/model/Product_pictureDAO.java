package com.product_picture.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.service_mail_picture.model.Service_mail_pictureVO;

public class Product_pictureDAO implements Product_pictureDAO_interface{


	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Campion");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String GET_ONE_STMT = 
			"SELECT prod_pic_no,prod_no,prod_pic,prod_pic_time FROM campion.product_picture where prod_pic_no = ?";
	
//	public void insert(Product_pictureVO product_pictureVO);
//    public void update(Product_pictureVO product_pictureVO);
//    public void delete(Integer prod_pic_no);
	@Override
	public Product_pictureVO findByPrimaryKey(Integer prod_pic_no) {

		Product_pictureVO product_pictureVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, prod_pic_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo ¤]ºÙ¬° Domain objects
				product_pictureVO = new Product_pictureVO();
				product_pictureVO.setProd_pic_no(rs.getInt("prod_pic_no"));
				product_pictureVO.setProd_no(rs.getInt("prod_no"));
				product_pictureVO.setProd_pic(rs.getString("prod_pic"));
				product_pictureVO.setProd_pic_time(rs.getTimestamp("prod_pic_time"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
    	
    	
    	return product_pictureVO ;
    }
//    public List<Product_pictureVO> getAll();
}
