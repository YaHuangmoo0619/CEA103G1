package com.message_record.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Message_recordDAO implements Message_recordDAO_interface {

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
		"INSERT INTO campion.message_record (msg_no,emp_no,mbr_no,msg_cont,msg_time) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT msg_no,emp_no,mbr_no,msg_cont,msg_time FROM campion.message_record order by msg_no";
	private static final String GET_ONE_STMT = 
		"SELECT msg_no,emp_no,mbr_no,msg_cont,msg_time FROM campion.message_record where msg_no = ?";
	private static final String DELETE = 
		"DELETE FROM campion.message_record where msg_no = ?";
	private static final String UPDATE = 
		"UPDATE campion.message_record set emp_no=?, mbr_no=?, msg_cont=?, msg_time=? where msg_no = ?";
	
	@Override
	public void insert(Message_recordVO message_recordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, message_recordVO.getMsg_no());
			pstmt.setInt(2, message_recordVO.getEmp_no());
			pstmt.setInt(3, message_recordVO.getMbr_no());
			pstmt.setString(4, message_recordVO.getMsg_cont());
			pstmt.setTimestamp(5, message_recordVO.getMsg_time());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
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
	public void update(Message_recordVO message_recordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, message_recordVO.getMsg_no());
			pstmt.setInt(2, message_recordVO.getEmp_no());
			pstmt.setInt(3, message_recordVO.getMbr_no());
			pstmt.setString(4, message_recordVO.getMsg_cont());
			pstmt.setTimestamp(5, message_recordVO.getMsg_time());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
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
	public void delete(Integer msg_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, msg_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
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
	public Message_recordVO findByPrimaryKey(Integer msg_no) {

		Message_recordVO message_recordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, msg_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				message_recordVO = new Message_recordVO();
				message_recordVO.setMsg_no(rs.getInt("msg_no"));
				message_recordVO.setEmp_no(rs.getInt("emp_no"));
				message_recordVO.setMbr_no(rs.getInt("mbr_no"));
				message_recordVO.setMsg_cont(rs.getString("msg_cont"));
				message_recordVO.setMsg_time(rs.getTimestamp("msg_time"));
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
		return message_recordVO;
	}

	@Override
	public List<Message_recordVO> getAll() {
		List<Message_recordVO> list = new ArrayList<Message_recordVO>();
		Message_recordVO message_recordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// Message_recordVO ¤]ºÙ¬° Domain objects
				message_recordVO = new Message_recordVO();
				message_recordVO.setMsg_no(rs.getInt("msg_no"));
				message_recordVO.setEmp_no(rs.getInt("emp_no"));
				message_recordVO.setMbr_no(rs.getInt("mbr_no"));
				message_recordVO.setMsg_cont(rs.getString("msg_cont"));
				message_recordVO.setMsg_time(rs.getTimestamp("msg_time"));
				list.add(message_recordVO); // Store the row in the list
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

}
