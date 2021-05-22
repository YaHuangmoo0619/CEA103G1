package com.product_picture.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member_mail.model.Member_mailDAO;
import com.member_mail.model.Member_mailVO;
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
	private static final String GET_All_PROD_STMT = 
			"SELECT prod_pic_no,prod_no,prod_pic,prod_pic_time FROM campion.product_picture where prod_no = ?";
	private static final String INSERT_STMT = 
			"INSERT INTO campion.product_picture (prod_no,prod_pic,prod_pic_time) VALUES (?, ?, ?)";
	private static final String DELETE_FROM_PROD = 
			"DELETE FROM campion.product_picture where prod_no = ?";
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
				// empVo 也稱為 Domain objects
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

	@Override
	public List<Product_pictureVO> findByProd_no(Integer prod_no) {

		List<Product_pictureVO> list = new ArrayList<Product_pictureVO>();
		Product_pictureVO product_pictureVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_All_PROD_STMT);
			
			pstmt.setInt(1, prod_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				product_pictureVO = new Product_pictureVO();
				product_pictureVO.setProd_pic_no(rs.getInt("prod_pic_no"));
				product_pictureVO.setProd_no(rs.getInt("prod_no"));
				product_pictureVO.setProd_pic(rs.getString("prod_pic"));
				product_pictureVO.setProd_pic_time(rs.getTimestamp("prod_pic_time"));
				list.add(product_pictureVO);
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
		return list;
	}

	@Override
	public void insert(Product_pictureVO product_pictureVO, Connection con) {
		
//		System.out.println("picDAO-insert?");
		
		PreparedStatement pstmt = null;

		try {
			
//			System.out.println("findingNull");
			pstmt = con.prepareStatement(INSERT_STMT);

//			System.out.println(product_pictureVO.getProd_no());
			pstmt.setInt(1, product_pictureVO.getProd_no());
//			System.out.println(product_pictureVO.getProd_pic());
			pstmt.setString(2, product_pictureVO.getProd_pic());
//			System.out.println(new Timestamp(new Date().getTime()));
			pstmt.setTimestamp(3, new Timestamp(new Date().getTime()));

//			System.out.println("put data");
			pstmt.executeUpdate();
//			System.out.println("finished");
			
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-product_picture-insert");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
		}

	}

	@Override
	public void updateFromProd(Integer prod_no,Set<Product_pictureVO> set,Connection con) {

//		System.out.println("picDAO-delete?");
		
		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(DELETE_FROM_PROD);

			pstmt.setInt(1, prod_no);

			pstmt.executeUpdate();
			
			for(Product_pictureVO product_pictureVO : set) {
				insert(product_pictureVO, con);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-product_picture-delete");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}
}
