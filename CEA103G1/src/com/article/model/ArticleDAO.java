package com.article.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.follow.model.FollowDAO;
import com.follow.model.FollowVO;
import com.member.model.MemberDAO;
import com.member.model.MemberVO;
import com.personal_system_notify.model.Personal_System_NotifyDAO;
import com.personal_system_notify.model.Personal_System_NotifyVO;



public class ArticleDAO implements ArticleDAO_Interface{
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
			"INSERT INTO ARTICLE (bd_cl_no,mbr_no,art_rel_time,art_title,art_cont,likes,art_stat,replies,art_first_img) VALUES (?,?, ?, ?, ?, ?,?,?,?)";
		private static final String GET_ALL_STMT_FRONT = 
			"SELECT art_no,bd_cl_no,mbr_no,art_rel_time,art_title,art_cont,likes,art_stat,replies,art_first_img FROM ARTICLE where art_stat = 0 order by art_no desc";
		private static final String GET_ALL_STMT_BACK = 
			"SELECT art_no,bd_cl_no,mbr_no,art_rel_time,art_title,art_cont,likes,art_stat,replies,art_first_img FROM ARTICLE order by art_no";
		private static final String GET_ONE_STMT = 
			"SELECT art_no,bd_cl_no,mbr_no,art_rel_time,art_title,art_cont,likes,art_stat,replies,art_first_img FROM ARTICLE where art_no = ?";
		private static final String DELETE = 
			"DELETE FROM ARTICLE where art_no = ?";
		private static final String HIDE = 
				"UPDATE ARTICLE set art_stat = 1 where art_no=?";
		private static final String UPDATE = 
				"UPDATE ARTICLE set bd_cl_no=?, mbr_no=?, art_rel_time=? ,art_title=? ,art_cont=? ,likes=? ,art_stat=?, replies=? art_first_img=? where art_no = ?";
		private static final String PLUS_LIKE = 
				"UPDATE ARTICLE set Likes = Likes + 1 where art_no = ?";
		private static final String MINUS_LIKE = 
				"UPDATE ARTICLE set Likes = Likes - 1 where art_no = ?";
		private static final String PLUS_REPLY = 
				"UPDATE ARTICLE set replies = replies + 1 where art_no = ?";
		private static final String MINUS_REPLY = 
				"UPDATE ARTICLE set replies = replies - 1 where art_no = ?";
		private static final String GET_BY_BD_CL_NO_FRONT =
			"SELECT art_no,bd_cl_no,mbr_no,art_rel_time,art_title,art_cont,likes,art_stat,replies,art_first_img FROM ARTICLE where bd_cl_no = ? and art_stat = 0";
		private static final String GET_BY_BD_CL_NO_BACK =
				"SELECT art_no,bd_cl_no,mbr_no,art_rel_time,art_title,art_cont,likes,art_stat,replies,art_first_img FROM ARTICLE where bd_cl_no = ?";
		private static final String GET_LAST =
				"select art_no from campion.ARTICLE order by art_no desc limit 1";
		private static final String GET_BY_MBR_NO =
				"SELECT art_no,bd_cl_no,mbr_no,art_rel_time,art_title,art_cont,likes,art_stat,replies,art_first_img FROM ARTICLE where mbr_no = ?";
	@Override
	public void insert(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, articleVO.getBd_cl_no());
			pstmt.setInt(2, articleVO.getMbr_no());
			pstmt.setTimestamp(3, articleVO.getArt_rel_time());
			pstmt.setString(4, articleVO.getArt_title());
			pstmt.setString(5, articleVO.getArt_cont());
			pstmt.setInt(6, articleVO.getLikes());
			pstmt.setInt(7, articleVO.getArt_stat());
			pstmt.setInt(8, articleVO.getReplies());
			pstmt.setString(9, articleVO.getArt_first_img());
			pstmt.executeUpdate();

			//雅凰嘗試連動新增系統通知
			//取得會員VO，以獲得作者名字
			MemberDAO memberDAO = new MemberDAO();
			MemberVO memberVO = memberDAO.findByPrimaryKey(articleVO.getMbr_no());
			
			//取得追蹤者的會員編號
			FollowDAO followDAO = new FollowDAO();
			List<FollowVO> followVOList = followDAO.findByflwed_mbr_no(articleVO.getMbr_no());
			
			//每個追蹤者的會員編號都建立一則通知
			for(FollowVO followVO : followVOList) {
				Personal_System_NotifyVO personal_System_NotifyVO = new Personal_System_NotifyVO();
				personal_System_NotifyVO.setMbr_no(followVO.getFlw_mbr_no());
				personal_System_NotifyVO.setNtfy_cont("您追蹤的"+memberVO.getName()+"發布了新的文章「" + articleVO.getArt_title() + "」");
				personal_System_NotifyVO.setNtfy_stat(0);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String ntfy_time = sdf.format(new java.util.Date());
				personal_System_NotifyVO.setNtfy_time(ntfy_time);
				
				Personal_System_NotifyDAO personal_System_NotifyDAO = new Personal_System_NotifyDAO();
				personal_System_NotifyDAO.insertWithArticle(personal_System_NotifyVO, con);
			}
			//雅凰嘗試連動新增系統通知
			
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
	public void update(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, articleVO.getBd_cl_no());
			pstmt.setInt(2, articleVO.getMbr_no());
			pstmt.setTimestamp(3, articleVO.getArt_rel_time());
			pstmt.setString(4, articleVO.getArt_title());
			pstmt.setString(5, articleVO.getArt_cont());
			pstmt.setInt(6, articleVO.getLikes());
			pstmt.setInt(7, articleVO.getArt_stat());
			pstmt.setInt(8, articleVO.getReplies());
			pstmt.setString(9, articleVO.getArt_first_img());
			pstmt.setInt(10, articleVO.getArt_no());
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
	public void delete(Integer art_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, art_no);

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
	public ArticleVO findByPrimaryKey(Integer art_no) {
		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, art_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				articleVO = new ArticleVO();
				articleVO.setArt_no(rs.getInt("art_no"));
				articleVO.setBd_cl_no(rs.getInt("bd_cl_no"));
				articleVO.setMbr_no(rs.getInt("mbr_no"));
				articleVO.setArt_rel_time(rs.getTimestamp("art_rel_time"));
				articleVO.setArt_title(rs.getString("art_title"));
				articleVO.setArt_cont(rs.getString("art_cont"));
				articleVO.setLikes(rs.getInt("likes"));
				articleVO.setArt_stat(rs.getInt("art_stat"));
				articleVO.setReplies(rs.getInt("replies"));
				articleVO.setArt_first_img(rs.getString("art_first_img"));
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
		return articleVO;
	}

	@Override
	public List<ArticleVO> getAll_Front() {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_FRONT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// articleVO 也稱為 Domain objects
				articleVO = new ArticleVO();
				articleVO.setArt_no(rs.getInt("art_no"));
				articleVO.setBd_cl_no(rs.getInt("bd_cl_no"));
				articleVO.setMbr_no(rs.getInt("mbr_no"));
				articleVO.setArt_rel_time(rs.getTimestamp("art_rel_time"));
				articleVO.setArt_title(rs.getString("art_title"));
				articleVO.setArt_cont(rs.getString("art_cont"));
				articleVO.setLikes(rs.getInt("likes"));
				articleVO.setArt_stat(rs.getInt("art_stat"));
				articleVO.setReplies(rs.getInt("replies"));
				articleVO.setArt_first_img(rs.getString("art_first_img"));
				list.add(articleVO); // Store the row in the list
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
	public List<ArticleVO> getAll_Back() {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_BACK);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// articleVO 也稱為 Domain objects
				articleVO = new ArticleVO();
				articleVO.setArt_no(rs.getInt("art_no"));
				articleVO.setBd_cl_no(rs.getInt("bd_cl_no"));
				articleVO.setMbr_no(rs.getInt("mbr_no"));
				articleVO.setArt_rel_time(rs.getTimestamp("art_rel_time"));
				articleVO.setArt_title(rs.getString("art_title"));
				articleVO.setArt_cont(rs.getString("art_cont"));
				articleVO.setLikes(rs.getInt("likes"));
				articleVO.setArt_stat(rs.getInt("art_stat"));
				articleVO.setReplies(rs.getInt("replies"));
				articleVO.setArt_first_img(rs.getString("art_first_img"));
				list.add(articleVO); // Store the row in the list
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
	public List<ArticleVO> findByBd_cl_no_front(Integer bd_cl_no) {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_BD_CL_NO_FRONT); //使用者看到的前台，只會顯示文章狀態為「顯示」的文章
			pstmt.setInt(1, bd_cl_no);
			rs = pstmt.executeQuery();


			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArt_no(rs.getInt("art_no"));
				articleVO.setBd_cl_no(rs.getInt("bd_cl_no"));
				articleVO.setMbr_no(rs.getInt("mbr_no"));
				articleVO.setArt_rel_time(rs.getTimestamp("art_rel_time"));
				articleVO.setArt_title(rs.getString("art_title"));
				articleVO.setArt_cont(rs.getString("art_cont"));
				articleVO.setLikes(rs.getInt("likes"));
				articleVO.setArt_stat(rs.getInt("art_stat"));
				articleVO.setReplies(rs.getInt("replies"));
				articleVO.setArt_first_img(rs.getString("art_first_img"));
				list.add(articleVO); // Store the row in the list
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
	public List<ArticleVO> findByBd_cl_no_back(Integer bd_cl_no) {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_BD_CL_NO_BACK); //後台，會顯示所有文章
			pstmt.setInt(1, bd_cl_no);
			rs = pstmt.executeQuery();


			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArt_no(rs.getInt("art_no"));
				articleVO.setBd_cl_no(rs.getInt("bd_cl_no"));
				articleVO.setMbr_no(rs.getInt("mbr_no"));
				articleVO.setArt_rel_time(rs.getTimestamp("art_rel_time"));
				articleVO.setArt_title(rs.getString("art_title"));
				articleVO.setArt_cont(rs.getString("art_cont"));
				articleVO.setLikes(rs.getInt("likes"));
				articleVO.setArt_stat(rs.getInt("art_stat"));
				articleVO.setReplies(rs.getInt("replies"));
				articleVO.setArt_first_img(rs.getString("art_first_img"));
				list.add(articleVO); // Store the row in the list
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
	
	
	

	public void hide(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(HIDE);

			pstmt.setInt(1, articleVO.getArt_no());
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
		
	} //end of hide
	
	
	
	@Override
	public void plus_like(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(PLUS_LIKE);

			pstmt.setInt(1, articleVO.getArt_no());
			pstmt.executeUpdate();
			
			//雅凰嘗試連動新增系統通知
			ArticleVO articleVONotify = findByPrimaryKey(articleVO.getArt_no());
			Personal_System_NotifyVO personal_System_NotifyVO = new Personal_System_NotifyVO();
//			System.out.println("articleVO.getMbr_no()="+articleVONotify.getMbr_no());
			personal_System_NotifyVO.setMbr_no(articleVONotify.getMbr_no());
			personal_System_NotifyVO.setNtfy_cont("您的文章「" + articleVONotify.getArt_title() + "」有新的按讚");
			personal_System_NotifyVO.setNtfy_stat(0);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ntfy_time = sdf.format(new java.util.Date());
			personal_System_NotifyVO.setNtfy_time(ntfy_time);
			
			Personal_System_NotifyDAO personal_System_NotifyDAO = new Personal_System_NotifyDAO();
			personal_System_NotifyDAO.insertWithLike(personal_System_NotifyVO, con);
			//雅凰嘗試連動新增系統通知
			
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
		
	} //end of plus_like
	
	
	
	
	@Override
	public void minus_like(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(MINUS_LIKE);

			pstmt.setInt(1, articleVO.getArt_no());
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
		
	} //end of minus_like

	
	
	
	@Override
	public void plus_reply(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(PLUS_REPLY);

			pstmt.setInt(1, articleVO.getArt_no());
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
		
	} //end of plus_reply
	
	
	
	@Override
	public void minus_reply(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(MINUS_REPLY);

			pstmt.setInt(1, articleVO.getArt_no());
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
		
	} //end of minus_reply

	@Override
	public ArticleVO findLast() {
		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LAST);


			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				articleVO = new ArticleVO();
				articleVO.setArt_no(rs.getInt("art_no"));
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
		return articleVO;
	}

	@Override
	public List<ArticleVO> findByMbr_no(Integer mbr_no) {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_MBR_NO); //某個人發的所有文章
			pstmt.setInt(1, mbr_no);
			rs = pstmt.executeQuery();


			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArt_no(rs.getInt("art_no"));
				articleVO.setBd_cl_no(rs.getInt("bd_cl_no"));
				articleVO.setMbr_no(rs.getInt("mbr_no"));
				articleVO.setArt_rel_time(rs.getTimestamp("art_rel_time"));
				articleVO.setArt_title(rs.getString("art_title"));
				articleVO.setArt_cont(rs.getString("art_cont"));
				articleVO.setLikes(rs.getInt("likes"));
				articleVO.setArt_stat(rs.getInt("art_stat"));
				articleVO.setReplies(rs.getInt("replies"));
				articleVO.setArt_first_img(rs.getString("art_first_img"));
				list.add(articleVO); // Store the row in the list
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
	

