package com.place_order.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
<<<<<<< HEAD
<<<<<<< .merge_file_a02712
import javax.naming.*;
import javax.sql.DataSource;

=======
import java.text.SimpleDateFormat;

import javax.naming.*;
import javax.sql.DataSource;

import com.follow.model.FollowDAO;
import com.follow.model.FollowVO;
import com.member.model.MemberDAO;
import com.member.model.MemberVO;
import com.personal_system_notify.model.Personal_System_NotifyDAO;
import com.personal_system_notify.model.Personal_System_NotifyVO;
>>>>>>> .merge_file_a15944
=======
import javax.naming.*;
import javax.sql.DataSource;

>>>>>>> parent of 09af1b2 (Ê≠£Âú®ËôïÁêÜË®ÇÁáü‰ΩçÁöÑÁ≥ªÁµ±ÈÄöÁü•ÔºåÊôÇÈñìÂà∞‰∫ÜÂÖàpush)
import com.place_order_details.model.Place_Order_DetailsDAO;
import com.place_order_details.model.Place_Order_DetailsVO;

public class Place_OrderDAO implements Place_OrderDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Campion");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String GET_ONE_STMT = "SELECT * FROM PLACE_ORDER where PLC_ORD_NO = ?";
	private static final String GET_BYCAMP_STMT = "SELECT PLC_ORD_NO FROM PLACE_ORDER where CAMP_NO = ?";
	private static final String GET_BYMEMBER_STMT = "SELECT * FROM PLACE_ORDER where MBR_NO = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM PLACE_ORDER order by PLC_ORD_NO";
	private static final String INSERT_STMT = "INSERT INTO PLACE_ORDER (mbr_no,camp_no,ckin_date,ckout_date,plc_amt,plc_ord_sum,ex_ppl,pay_meth,pay_stat,used_pt,receipt,rmk) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE PLACE_ORDER set pay_stat=?,ckin_stat=? where PLC_ORD_NO = ?";
	private static final String DELETE = "DELETE FROM PLACE_ORDER where PLC_ORD_NO = ?";

	public Place_OrderVO findByPrimaryKey(Integer plc_ord_no) {

		Place_OrderVO place_orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, plc_ord_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				place_orderVO = new Place_OrderVO();
				place_orderVO.setPlc_ord_no(rs.getInt("plc_ord_no"));
				place_orderVO.setMbr_no(rs.getInt("mbr_no"));
				place_orderVO.setCamp_no(rs.getInt("camp_no"));
				place_orderVO.setPlc_ord_time(rs.getTimestamp("plc_ord_time"));
				place_orderVO.setCkin_date(rs.getDate("ckin_date"));
				place_orderVO.setCkout_date(rs.getDate("ckout_date"));
				place_orderVO.setPlc_amt(rs.getInt("plc_amt"));
				place_orderVO.setPlc_ord_sum(rs.getInt("plc_ord_sum"));
				place_orderVO.setEx_ppl(rs.getInt("ex_ppl"));
				place_orderVO.setPay_meth(rs.getInt("pay_meth"));
				place_orderVO.setPay_stat(rs.getInt("pay_stat"));
				place_orderVO.setUsed_pt(rs.getInt("used_pt"));
				place_orderVO.setCkin_stat(rs.getInt("ckin_stat"));
				place_orderVO.setReceipt(rs.getInt("receipt"));
				place_orderVO.setRmk(rs.getString("rmk"));
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
		return place_orderVO;
	}

	public List<Place_OrderVO> findByCamp(Integer camp_no) {
		List<Place_OrderVO> list = new ArrayList<Place_OrderVO>();
		Place_OrderVO place_orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BYCAMP_STMT);
			pstmt.setInt(1, camp_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				place_orderVO = new Place_OrderVO();
				place_orderVO.setPlc_ord_no(rs.getInt("plc_ord_no"));
				place_orderVO.setMbr_no(rs.getInt("mbr_no"));
				place_orderVO.setCamp_no(rs.getInt("camp_no"));
				place_orderVO.setPlc_ord_time(rs.getTimestamp("plc_ord_time"));
				place_orderVO.setCkin_date(rs.getDate("ckin_date"));
				place_orderVO.setCkout_date(rs.getDate("ckout_date"));
				place_orderVO.setPlc_amt(rs.getInt("plc_amt"));
				place_orderVO.setPlc_ord_sum(rs.getInt("plc_ord_sum"));
				place_orderVO.setEx_ppl(rs.getInt("ex_ppl"));
				place_orderVO.setPay_meth(rs.getInt("pay_meth"));
				place_orderVO.setPay_stat(rs.getInt("pay_stat"));
				place_orderVO.setUsed_pt(rs.getInt("used_pt"));
				place_orderVO.setCkin_stat(rs.getInt("ckin_stat"));
				place_orderVO.setReceipt(rs.getInt("receipt"));
				place_orderVO.setRmk(rs.getString("rmk"));
				list.add(place_orderVO);
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
	public List<Place_OrderVO> findByMember(Integer mbr_no) {
		List<Place_OrderVO> list = new ArrayList<Place_OrderVO>();
		Place_OrderVO place_orderVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BYMEMBER_STMT);
			pstmt.setInt(1, mbr_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				place_orderVO = new Place_OrderVO();
				place_orderVO.setPlc_ord_no(rs.getInt("plc_ord_no"));
				place_orderVO.setMbr_no(rs.getInt("mbr_no"));
				place_orderVO.setCamp_no(rs.getInt("camp_no"));
				place_orderVO.setPlc_ord_time(rs.getTimestamp("plc_ord_time"));
				place_orderVO.setCkin_date(rs.getDate("ckin_date"));
				place_orderVO.setCkout_date(rs.getDate("ckout_date"));
				place_orderVO.setPlc_amt(rs.getInt("plc_amt"));
				place_orderVO.setPlc_ord_sum(rs.getInt("plc_ord_sum"));
				place_orderVO.setEx_ppl(rs.getInt("ex_ppl"));
				place_orderVO.setPay_meth(rs.getInt("pay_meth"));
				place_orderVO.setPay_stat(rs.getInt("pay_stat"));
				place_orderVO.setUsed_pt(rs.getInt("used_pt"));
				place_orderVO.setCkin_stat(rs.getInt("ckin_stat"));
				place_orderVO.setReceipt(rs.getInt("receipt"));
				place_orderVO.setRmk(rs.getString("rmk"));
				list.add(place_orderVO);
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

	public List<Place_OrderVO> getAll() {
		List<Place_OrderVO> list = new ArrayList<Place_OrderVO>();
		Place_OrderVO place_orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				place_orderVO = new Place_OrderVO();
				place_orderVO.setPlc_ord_no(rs.getInt("plc_ord_no"));
				place_orderVO.setMbr_no(rs.getInt("mbr_no"));
				place_orderVO.setCamp_no(rs.getInt("camp_no"));
				place_orderVO.setPlc_ord_time(rs.getTimestamp("plc_ord_time"));
				place_orderVO.setCkin_date(rs.getDate("ckin_date"));
				place_orderVO.setCkout_date(rs.getDate("ckout_date"));
				place_orderVO.setPlc_amt(rs.getInt("plc_amt"));
				place_orderVO.setPlc_ord_sum(rs.getInt("plc_ord_sum"));
				place_orderVO.setEx_ppl(rs.getInt("ex_ppl"));
				place_orderVO.setPay_meth(rs.getInt("pay_meth"));
				place_orderVO.setPay_stat(rs.getInt("pay_stat"));
				place_orderVO.setUsed_pt(rs.getInt("used_pt"));
				place_orderVO.setCkin_stat(rs.getInt("ckin_stat"));
				place_orderVO.setReceipt(rs.getInt("receipt"));
				place_orderVO.setRmk(rs.getString("rmk"));
				list.add(place_orderVO);
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
	public Place_OrderVO insert(Place_OrderVO place_orderVO, List<Place_Order_DetailsVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 1°¥≥]©w©Û pstm.executeUpdate()§ß´e
			con = ds.getConnection();
			con.setAutoCommit(false);

			// •˝∑sºW≥°™˘
			String cols[] = { "PLC_ORD_NO" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setInt(1, place_orderVO.getMbr_no());
			pstmt.setInt(2, place_orderVO.getCamp_no());
			pstmt.setDate(3, place_orderVO.getCkin_date());
			pstmt.setDate(4, place_orderVO.getCkout_date());
			pstmt.setInt(5, place_orderVO.getPlc_amt());
			pstmt.setInt(6, place_orderVO.getPlc_ord_sum());
			pstmt.setInt(7, place_orderVO.getEx_ppl());
			pstmt.setInt(8, place_orderVO.getPay_meth());
			pstmt.setInt(9, place_orderVO.getPay_stat());
			pstmt.setInt(10, place_orderVO.getUsed_pt());
			pstmt.setInt(11, place_orderVO.getReceipt());
			pstmt.setString(12, place_orderVO.getRmk());
			Statement stmt = con.createStatement();
			stmt.executeUpdate("set auto_increment_offset=1;"); // ¶€ºW•D¡‰-™Ï©l≠»
			stmt.executeUpdate("set auto_increment_increment=1;"); // ¶€ºW•D¡‰-ªººW
			pstmt.executeUpdate();
			// ±∏®˙πÔ¿≥™∫¶€ºW•D¡‰≠»
			String next_plc_ord_no = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_plc_ord_no = rs.getString(1);
			} else {
				System.out.println("•º®˙±o¶€ºW•D¡‰≠»");
			}
			rs.close();
			place_orderVO.setPlc_ord_no(new Integer(next_plc_ord_no));
			Place_Order_DetailsDAO dao = new Place_Order_DetailsDAO();
			for (Place_Order_DetailsVO aDetail : list) {
				aDetail.setPlc_ord_no(new Integer(next_plc_ord_no));
				dao.insert2(aDetail, con);
			}

			// 2°¥≥]©w©Û pstm.executeUpdate()§ß´·
			con.commit();
			con.setAutoCommit(true);

<<<<<<< HEAD
<<<<<<< .merge_file_a02712
=======
			//∂Æ∞ƒπ¡∏’≥s∞ ∑sºW®t≤Œ≥q™æ
			//´ÿ•ﬂ≠q≥Ê™∫∑|≠˚∑sºW≥q™æ
			Personal_System_NotifyVO personal_System_NotifyVO = new Personal_System_NotifyVO();
			personal_System_NotifyVO.setMbr_no(place_orderVO.getMbr_no());
			personal_System_NotifyVO.setNtfy_cont("±z™∫≠q≥Ê§w¶®•ﬂ");
			personal_System_NotifyVO.setNtfy_stat(0);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ntfy_time = sdf.format(new java.util.Date());
			personal_System_NotifyVO.setNtfy_time(ntfy_time);
				
			Personal_System_NotifyDAO personal_System_NotifyDAO = new Personal_System_NotifyDAO();
			personal_System_NotifyDAO.insertWithArticle(personal_System_NotifyVO, con);
			//∂Æ∞ƒπ¡∏’≥s∞ ∑sºW®t≤Œ≥q™æ
			
>>>>>>> .merge_file_a15944
=======
>>>>>>> parent of 09af1b2 (Ê≠£Âú®ËôïÁêÜË®ÇÁáü‰ΩçÁöÑÁ≥ªÁµ±ÈÄöÁü•ÔºåÊôÇÈñìÂà∞‰∫ÜÂÖàpush)
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3°¥≥]©w©Û∑Ì¶≥exceptionµo•ÕÆ…§ßcatch∞œ∂Ù§∫
					System.err.print("Transaction is being ");
					System.err.println("rolled back-•—-dept");
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
		return place_orderVO;
	}

	@Override
	public void update(Place_OrderVO place_orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, place_orderVO.getPay_stat());
			pstmt.setInt(2, place_orderVO.getCkin_stat());
			pstmt.setInt(3, place_orderVO.getPlc_ord_no());

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
	public void delete(Integer plc_ord_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, plc_ord_no);

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
}
