package com.product.model;

import java.util.*;
import java.sql.*;

import javax.naming.*;
import javax.sql.DataSource;

import com.member_mail.model.Member_mailDAO;
import com.member_mail.model.Member_mailVO;
import com.product_picture.model.Product_pictureDAO;
import com.product_picture.model.Product_pictureVO;
import com.service_mail_picture.model.Service_mail_pictureDAO;
import com.service_mail_picture.model.Service_mail_pictureVO;

public class ProductDAO implements ProductDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Campion");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
		"insert into PRODUCT (PROD_CAT_NO, PROD_STAT, PROD_NAME, PROD_PC, PROD_STG, PROD_INFO, PROD_BND, PROD_CLR, PROD_SIZE, SHIP_METH) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
		"update PRODUCT set PROD_CAT_NO = ?, PROD_STAT = ?, PROD_NAME = ?, PROD_PC = ?, PROD_STG = ?, PROD_INFO = ?, PROD_BND = ?, PROD_CLR = ?, PROD_SIZE = ?, SHIP_METH = ? where PROD_NO = ?";
	//雅凰改的
	private static final String UPDATE_FOR_UP_OR_DOWN = 
			"update PRODUCT set PROD_STAT = ? where PROD_NO = ?";
	private static final String GET_ALL_STAT_STMT = 
			"select * from PRODUCT where PROD_STAT != 9 order by PROD_STAT desc";
	//雅凰改的
	private static final String DELETE = 
		"update PRODUCT set PROD_STAT = 9 where PROD_NO = ?";
	private static final String GET_ONE_STMT = 
		"select * from PRODUCT where PROD_NO = ? and PROD_STAT != 9";
	private static final String GET_SHOP_STMT = 
		"select * from PRODUCT where PROD_STAT = 1 order by PROD_NO";
	private static final String GET_ALL_STMT = 
		"select * from PRODUCT where PROD_STAT != 9 order by PROD_NO";

	@Override
	public void insert(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, productVO.getProd_cat_no());
			pstmt.setInt(2, productVO.getProd_stat());
			pstmt.setString(3, productVO.getProd_name());
			pstmt.setInt(4, productVO.getProd_pc());
			pstmt.setInt(5, productVO.getProd_stg());
			pstmt.setString(6, productVO.getProd_info());
			pstmt.setString(7, productVO.getProd_bnd());
			pstmt.setString(8, productVO.getProd_clr());
			pstmt.setString(9, productVO.getProd_size());
			pstmt.setInt(10, productVO.getShip_meth());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

		} finally {
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

	}

	@Override
	public void update(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, productVO.getProd_cat_no());
			pstmt.setInt(2, productVO.getProd_stat());
			pstmt.setString(3, productVO.getProd_name());
			pstmt.setInt(4, productVO.getProd_pc());
			pstmt.setInt(5, productVO.getProd_stg());
			pstmt.setString(6, productVO.getProd_info());
			pstmt.setString(7, productVO.getProd_bnd());
			pstmt.setString(8, productVO.getProd_clr());
			pstmt.setString(9, productVO.getProd_size());
			pstmt.setInt(10, productVO.getShip_meth());
			pstmt.setInt(11, productVO.getProd_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
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

	}

	@Override
	public void delete(Integer prod_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, prod_no);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

		} finally {
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

	}
	
	@Override
	public ProductVO findByPrimaryKey(Integer prod_no) {

		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, prod_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProd_no(rs.getInt("PROD_NO"));
				productVO.setProd_cat_no(rs.getInt("PROD_CAT_NO"));
				productVO.setProd_stat(rs.getInt("PROD_STAT"));
				productVO.setProd_name(rs.getString("PROD_NAME"));
				productVO.setProd_pc(rs.getInt("PROD_PC"));
				productVO.setProd_stg(rs.getInt("PROD_STG"));
				productVO.setProd_info(rs.getString("PROD_INFO"));
				productVO.setProd_bnd(rs.getString("PROD_BND"));
				productVO.setProd_clr(rs.getString("PROD_CLR"));
				productVO.setProd_size(rs.getString("PROD_SIZE"));
				productVO.setShip_meth(rs.getInt("SHIP_METH"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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
		return productVO;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProd_no(rs.getInt("PROD_NO"));
				productVO.setProd_cat_no(rs.getInt("PROD_CAT_NO"));
				productVO.setProd_stat(rs.getInt("PROD_STAT"));
				productVO.setProd_name(rs.getString("PROD_NAME"));
				productVO.setProd_pc(rs.getInt("PROD_PC"));
				productVO.setProd_stg(rs.getInt("PROD_STG"));
				productVO.setProd_info(rs.getString("PROD_INFO"));
				productVO.setProd_bnd(rs.getString("PROD_BND"));
				productVO.setProd_clr(rs.getString("PROD_CLR"));
				productVO.setProd_size(rs.getString("PROD_SIZE"));
				productVO.setShip_meth(rs.getInt("SHIP_METH"));
				list.add(productVO); 
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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
	public List<ProductVO> getShop() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SHOP_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProd_no(rs.getInt("PROD_NO"));
				productVO.setProd_cat_no(rs.getInt("PROD_CAT_NO"));
				productVO.setProd_stat(rs.getInt("PROD_STAT"));
				productVO.setProd_name(rs.getString("PROD_NAME"));
				productVO.setProd_pc(rs.getInt("PROD_PC"));
				productVO.setProd_stg(rs.getInt("PROD_STG"));
				productVO.setProd_info(rs.getString("PROD_INFO"));
				productVO.setProd_bnd(rs.getString("PROD_BND"));
				productVO.setProd_clr(rs.getString("PROD_CLR"));
				productVO.setProd_size(rs.getString("PROD_SIZE"));
				productVO.setShip_meth(rs.getInt("SHIP_METH"));
				list.add(productVO); 
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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
	
	public List<ProductVO> getAll(Map<String, String[]> map) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "SELECT * FROM PRODUCT"
		          + jdbc_CQ_Product.get_WhereCondition(map)
		          + "order by prod_no";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProd_no(rs.getInt("PROD_NO"));
				productVO.setProd_cat_no(rs.getInt("PROD_CAT_NO"));
				productVO.setProd_stat(rs.getInt("PROD_STAT"));
				productVO.setProd_name(rs.getString("PROD_NAME"));
				productVO.setProd_pc(rs.getInt("PROD_PC"));
				productVO.setProd_stg(rs.getInt("PROD_STG"));
				productVO.setProd_info(rs.getString("PROD_INFO"));
				productVO.setProd_bnd(rs.getString("PROD_BND"));
				productVO.setProd_clr(rs.getString("PROD_CLR"));
				productVO.setProd_size(rs.getString("PROD_SIZE"));
				productVO.setShip_meth(rs.getInt("SHIP_METH"));
				list.add(productVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	//雅凰加的
	@Override
	public void updateUpOrDown(Integer prod_stat, Integer prod_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_FOR_UP_OR_DOWN);

			pstmt.setInt(1, prod_stat);
			pstmt.setInt(2, prod_no);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
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
	}

	@Override
	public List<ProductVO> getAllStat() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STAT_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProd_no(rs.getInt("PROD_NO"));
				productVO.setProd_cat_no(rs.getInt("PROD_CAT_NO"));
				productVO.setProd_stat(rs.getInt("PROD_STAT"));
				productVO.setProd_name(rs.getString("PROD_NAME"));
				productVO.setProd_pc(rs.getInt("PROD_PC"));
				productVO.setProd_stg(rs.getInt("PROD_STG"));
				productVO.setProd_info(rs.getString("PROD_INFO"));
				productVO.setProd_bnd(rs.getString("PROD_BND"));
				productVO.setProd_clr(rs.getString("PROD_CLR"));
				productVO.setProd_size(rs.getString("PROD_SIZE"));
				productVO.setShip_meth(rs.getInt("SHIP_METH"));
				list.add(productVO); 
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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
	public void updateWithPic(ProductVO productVO, Set<Product_pictureVO> set) {

//		System.out.println("dao?");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, productVO.getProd_cat_no());
			pstmt.setInt(2, productVO.getProd_stat());
			pstmt.setString(3, productVO.getProd_name());
			pstmt.setInt(4, productVO.getProd_pc());
			pstmt.setInt(5, productVO.getProd_stg());
			pstmt.setString(6, productVO.getProd_info());
			pstmt.setString(7, productVO.getProd_bnd());
			pstmt.setString(8, productVO.getProd_clr());
			pstmt.setString(9, productVO.getProd_size());
			pstmt.setInt(10, productVO.getShip_meth());
			pstmt.setInt(11, productVO.getProd_no());
//			System.out.println("productDAO="+productVO.getProd_no());

			pstmt.executeUpdate();
			
			Product_pictureDAO product_pictureDAO = new Product_pictureDAO();
//			System.out.println("set.size()="+set.size());

			product_pictureDAO.updateFromProd(productVO.getProd_no(), set , con);
			
			con.commit();
			con.setAutoCommit(true);
			// Handle any SQL errors
		}catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-productDAO");
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void insert(ProductVO productVO,Set<Product_pictureVO> set) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			
			con.setAutoCommit(false);
			
			String cols[] = {"prod_no"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);

			pstmt.setInt(1, productVO.getProd_cat_no());
			pstmt.setInt(2, productVO.getProd_stat());
			pstmt.setString(3, productVO.getProd_name());
			pstmt.setInt(4, productVO.getProd_pc());
			pstmt.setInt(5, productVO.getProd_stg());
			pstmt.setString(6, productVO.getProd_info());
			pstmt.setString(7, productVO.getProd_bnd());
			pstmt.setString(8, productVO.getProd_clr());
			pstmt.setString(9, productVO.getProd_size());
			pstmt.setInt(10, productVO.getShip_meth());

			pstmt.executeUpdate();
//			System.out.println("whatswrong");
			String next_prod_no = null;
			ResultSet rs = pstmt.getGeneratedKeys();
//			System.out.println(rs);
			if(rs.next()) {
				next_prod_no = rs.getString(1);
			}
			rs.close();
//			System.out.println(next_prod_no);
			Product_pictureDAO product_pictureDAO = new Product_pictureDAO();
//			System.out.println("set.size()="+set.size());

			for(Product_pictureVO product_pictureVO : set) {
				product_pictureVO.setProd_no(new Integer(next_prod_no));
				product_pictureDAO.insert(product_pictureVO, con);
			}
//			System.out.println("whatswrongwithyou");
			con.commit();
//			System.out.println("whatswrongwithyou2");
			con.setAutoCommit(true);
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-productDAO");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

		} finally {
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
	}
	//雅凰加的
}