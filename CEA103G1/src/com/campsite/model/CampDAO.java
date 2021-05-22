package com.campsite.model;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.naming.*;
import javax.sql.DataSource;

import com.campsite_feature.model.Camp_FeatureDAO;
import com.campsite_feature.model.Camp_FeatureVO;
import com.campsite_picture.model.Camp_PictureDAO;
import com.campsite_picture.model.Camp_PictureVO;
import com.place.model.PlaceDAO;
import com.place.model.PlaceVO;
import com.place_order_details.model.Place_Order_DetailsDAO;
import com.place_order_details.model.Place_Order_DetailsVO;

public class CampDAO implements CampDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Campion");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String GET_ONE_STMT = "SELECT * FROM campsite where camp_no = ?";
	private static final String GET_DIST_STMT = "SELECT * FROM campsite where dist_no = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM campsite order by camp_no";
	private static final String INSERT_STMT = "INSERT INTO campsite (cso_no,dist_no,camp_name,campInfo,note,config,height,wireless,pet,facility,operate_Date,park,address,latitude,longitude) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE campsite set dist_no=?,camp_name=?,campInfo=?,note=?,config=?,height=?,wireless=?,pet=?,facility=?,operate_Date=?,park=?,address=?,latitude=?,longitude=? where camp_no = ?";
	private static final String UPDATE2 = "UPDATE campsite set campsite_status=?,review_status=? where camp_no = ?";
	private static final String UPDATE3 = "UPDATE campsite set dist_no=?,camp_name=?,campInfo=?,note=?,height=?,wireless=?,pet=?,facility=?,operate_Date=?,park=?,address=?,latitude=?,longitude=? where camp_no = ?";
	private static final String DELETE = "DELETE FROM campsite where camp_no = ?";

	public CampVO findByPrimaryKey(Integer camp_no) {

		CampVO campVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, camp_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				campVO = new CampVO();
				campVO.setCamp_no(rs.getInt("camp_no"));
				campVO.setCso_no(rs.getInt("cso_no"));
				campVO.setDist_no(rs.getInt("dist_no"));
				campVO.setCamp_name(rs.getString("camp_name"));
				campVO.setCampsite_Status(rs.getInt("campsite_Status"));
				campVO.setCampInfo(rs.getString("campinfo"));
				campVO.setNote(rs.getString("note"));
				campVO.setConfig(rs.getBytes("config"));
				campVO.setReview_Status(rs.getInt("review_Status"));
				campVO.setHeight(rs.getString("height"));
				campVO.setWireless(rs.getString("wireless"));
				campVO.setPet(rs.getInt("pet"));
				campVO.setFacility(rs.getString("facility"));
				campVO.setOperate_Date(rs.getInt("operate_Date"));
				campVO.setPark(rs.getString("park"));
				campVO.setAddress(rs.getString("address"));
				campVO.setLatitude(rs.getDouble("latitude"));
				campVO.setLongitude(rs.getDouble("longitude"));
				campVO.setTotal_Stars(rs.getInt("total_Stars"));
				campVO.setTotal_Cmnt(rs.getInt("total_Cmnt"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return campVO;
	}
	
	public List<Integer> findByDist(Integer dist_no) {
		
		List<Integer> list = new ArrayList<Integer>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DIST_STMT);
			pstmt.setInt(1, dist_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(rs.getInt("camp_no"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public List<CampVO> getAll() {
		List<CampVO> list = new ArrayList<CampVO>();
		CampVO campVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				campVO = new CampVO();
				campVO.setCamp_no(rs.getInt("camp_no"));
				campVO.setCso_no(rs.getInt("cso_no"));
				campVO.setDist_no(rs.getInt("dist_no"));
				campVO.setCamp_name(rs.getString("camp_name"));
				campVO.setCampsite_Status(rs.getInt("campsite_Status"));
				campVO.setCampInfo(rs.getString("campinfo"));
				campVO.setNote(rs.getString("note"));
				campVO.setConfig(rs.getBytes("config"));
				campVO.setReview_Status(rs.getInt("review_Status"));
				campVO.setHeight(rs.getString("height"));
				campVO.setWireless(rs.getString("wireless"));
				campVO.setPet(rs.getInt("pet"));
				campVO.setFacility(rs.getString("facility"));
				campVO.setOperate_Date(rs.getInt("operate_Date"));
				campVO.setPark(rs.getString("park"));
				campVO.setAddress(rs.getString("address"));
				campVO.setLatitude(rs.getDouble("latitude"));
				campVO.setLongitude(rs.getDouble("longitude"));
				campVO.setTotal_Stars(rs.getInt("total_Stars"));
				campVO.setTotal_Cmnt(rs.getInt("total_Cmnt"));
				list.add(campVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(CampVO campVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setInt(1, campVO.getDist_no());
			pstmt.setString(2, campVO.getCamp_name());
			pstmt.setString(3, campVO.getCampInfo());
			pstmt.setString(4, campVO.getNote());
			pstmt.setBytes(5, campVO.getConfig());
			pstmt.setString(6, campVO.getHeight());
			pstmt.setString(7, campVO.getWireless());
			pstmt.setInt(8, campVO.getPet());
			pstmt.setString(9, campVO.getFacility());
			pstmt.setInt(10, campVO.getOperate_Date());
			pstmt.setString(11, campVO.getPark());
			pstmt.setString(12, campVO.getAddress());
			pstmt.setDouble(13, campVO.getLatitude());
			pstmt.setDouble(14, campVO.getLongitude());
			pstmt.setInt(15, campVO.getCamp_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update3(CampVO campVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE3);
			
			
			pstmt.setInt(1, campVO.getDist_no());
			pstmt.setString(2, campVO.getCamp_name());
			pstmt.setString(3, campVO.getCampInfo());
			pstmt.setString(4, campVO.getNote());
			pstmt.setString(5, campVO.getHeight());
			pstmt.setString(6, campVO.getWireless());
			pstmt.setInt(7, campVO.getPet());
			pstmt.setString(8, campVO.getFacility());
			pstmt.setInt(9, campVO.getOperate_Date());
			pstmt.setString(10, campVO.getPark());
			pstmt.setString(11, campVO.getAddress());
			pstmt.setDouble(12, campVO.getLatitude());
			pstmt.setDouble(13, campVO.getLongitude());
			pstmt.setInt(14, campVO.getCamp_no());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update2(CampVO campVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE2);
			
			
			pstmt.setInt(1, campVO.getCampsite_Status());
			pstmt.setInt(2, campVO.getReview_Status());
			pstmt.setInt(3, campVO.getCamp_no());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer camp_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, camp_no);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void insert(CampVO campVO, List<Camp_FeatureVO> camp_featurelist, List<PlaceVO> placelist, List<Camp_PictureVO> camp_picturelist) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 1●設定於 pstm.executeUpdate()之前
			con = ds.getConnection();
			con.setAutoCommit(false);

			// 先新增部門
			String cols[] = { "CAMP_NO" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setInt(1, campVO.getCso_no());
			pstmt.setInt(2, campVO.getDist_no());
			pstmt.setString(3, campVO.getCamp_name());
			pstmt.setString(4, campVO.getCampInfo());
			pstmt.setString(5, campVO.getNote());
			pstmt.setBytes(6, campVO.getConfig());
			pstmt.setString(7, campVO.getHeight());
			pstmt.setString(8, campVO.getWireless());
			pstmt.setInt(9, campVO.getPet());
			pstmt.setString(10, campVO.getFacility());
			pstmt.setInt(11, campVO.getOperate_Date());
			pstmt.setString(12, campVO.getPark());
			pstmt.setString(13, campVO.getAddress());
			pstmt.setDouble(14, campVO.getLatitude());
			pstmt.setDouble(15, campVO.getLongitude());	
//			pstmt.executeUpdate();
			
			Statement stmt = con.createStatement();
			stmt.executeUpdate("set auto_increment_offset=1;"); // 自增主鍵-初始值
			stmt.executeUpdate("set auto_increment_increment=1;"); // 自增主鍵-遞增
			pstmt.executeUpdate();
			// 掘取對應的自增主鍵值
			String next_camp_no = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_camp_no = rs.getString(1);
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增員工
			PlaceDAO dao1 = new PlaceDAO();
			for (PlaceVO aPlace : placelist) {
				aPlace.setCamp_no(new Integer(next_camp_no));
				dao1.insert2(aPlace, con);
			}
			Camp_FeatureDAO dao2 = new Camp_FeatureDAO();
			for (Camp_FeatureVO aCamp_Feature : camp_featurelist) {
				aCamp_Feature.setCamp_no(new Integer(next_camp_no));
				dao2.insert2(aCamp_Feature, con);
			}
			Camp_PictureDAO dao3 = new Camp_PictureDAO();
			if(!(camp_picturelist == null) && !(camp_picturelist.size()==0)) {
				for (Camp_PictureVO aCamp_Picture : camp_picturelist) {
					aCamp_Picture.setCamp_no(new Integer(next_camp_no));
					dao3.insert2(aCamp_Picture, con);
				}
			}
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);

			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

}
