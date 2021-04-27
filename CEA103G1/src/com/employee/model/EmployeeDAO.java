package com.employee.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EmployeeDAO implements EmployeeDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Campion");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO campion.employee (acc, pwd, name, email) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT emp_no, acc, pwd, name, email, emp_stat FROM campion.employee order by emp_no";
	private static final String GET_ONE_STMT = "SELECT emp_no, acc, pwd, name, email, emp_stat FROM campion.employee where emp_no = ?";
	private static final String GET_ALL_NAME_EMP_NO_STMT = "SELECT emp_no, acc, pwd, name, email, emp_stat FROM campion.employee where name = ?";
	private static final String GET_ALL_FUNCTION_EMP_NO_STMT = "SELECT e.emp_no, e.acc, e.pwd, e.name, email, e.emp_stat "
			+ "FROM campion.EMPLOYEE e " + "join campion.Authority a " + "on e.EMP_NO = a.EMP_NO "
			+ "join campion.FUNCTION f " + "on a.FX_NO = f.FX_NO " + "where f.fx_no = ?";
	private static final String DELETE = "DELETE FROM campion.employee where emp_no = ?";
	private static final String UPDATE = "UPDATE campion.employee set acc=?, pwd=?, name=?, email=?, emp_stat=? where emp_no = ?";

	@Override
	public void insert(EmployeeVO employeeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, employeeVO.getAcc());
			pstmt.setString(2, employeeVO.getPwd());
			pstmt.setString(3, employeeVO.getName());
			pstmt.setString(4, employeeVO.getEmail());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
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

	@Override
	public void update(EmployeeVO employeeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, employeeVO.getAcc());
			pstmt.setString(2, employeeVO.getPwd());
			pstmt.setString(3, employeeVO.getName());
			pstmt.setString(4, employeeVO.getEmail());
			pstmt.setInt(5, employeeVO.getEmp_stat());
			pstmt.setInt(6, employeeVO.getEmp_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
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

	@Override
	public void delete(Integer emp_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, emp_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
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

	@Override
	public EmployeeVO findByPrimaryKey(Integer emp_no) {

		EmployeeVO employeeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, emp_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				employeeVO = new EmployeeVO();
				employeeVO.setEmp_no(rs.getInt("emp_no"));
				employeeVO.setAcc(rs.getString("acc"));
				employeeVO.setPwd(showPwdformDatabase(rs.getString("pwd")));
				employeeVO.setName(rs.getString("name"));
				employeeVO.setEmail(rs.getString("email"));
				employeeVO.setEmp_stat(rs.getInt("emp_stat"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return employeeVO;
	}

	@Override
	public List<EmployeeVO> getAll() {

		List<EmployeeVO> list = new LinkedList<EmployeeVO>();
		EmployeeVO employeeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				employeeVO = new EmployeeVO();
				employeeVO.setEmp_no(rs.getInt("emp_no"));
				employeeVO.setAcc(rs.getString("acc"));
				employeeVO.setPwd(showPwdformDatabase(rs.getString("pwd")));
				employeeVO.setName(rs.getString("name"));
				employeeVO.setEmail(rs.getString("email"));
				employeeVO.setEmp_stat(rs.getInt("emp_stat"));
				list.add(employeeVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<EmployeeVO> getNameEmp_no(String name) {
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		EmployeeVO employeeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_NAME_EMP_NO_STMT);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				employeeVO = new EmployeeVO();
				employeeVO.setEmp_no(rs.getInt("emp_no"));
				employeeVO.setAcc(rs.getString("acc"));
				employeeVO.setPwd(showPwdformDatabase(rs.getString("pwd")));
				employeeVO.setName(rs.getString("name"));
				employeeVO.setEmail(rs.getString("email"));
				employeeVO.setEmp_stat(rs.getInt("emp_stat"));
				list.add(employeeVO);
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<EmployeeVO> getFunctionEmp_no(Integer fx_no) {
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		EmployeeVO employeeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_FUNCTION_EMP_NO_STMT);
			pstmt.setInt(1, fx_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				employeeVO = new EmployeeVO();
				employeeVO.setEmp_no(rs.getInt("emp_no"));
				employeeVO.setAcc(rs.getString("acc"));
				employeeVO.setPwd(showPwdformDatabase(rs.getString("pwd")));
				employeeVO.setName(rs.getString("name"));
				employeeVO.setEmail(rs.getString("email"));
				employeeVO.setEmp_stat(rs.getInt("emp_stat"));
				list.add(employeeVO);
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	// 可顯示正確的密碼
	public String showPwdformDatabase(String newPwd) {
		char[] forShow = newPwd.toCharArray();
		char[] gobackCode = new char[newPwd.length()];
		for (int i = 0; i < newPwd.length(); i++) {
			if (i < 2) {
				gobackCode[i + ((newPwd.length() - 2))] = forShow[i];
			} else {
				gobackCode[i - 2] = forShow[i];
			}
		}
		String showPwd = String.valueOf(gobackCode);
		return showPwd;
	}
}
