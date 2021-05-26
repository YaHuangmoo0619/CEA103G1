package com.article_reply.model;

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



import com.article.model.ArticleDAO;
import com.article.model.ArticleVO;
import com.article_likes.model.Article_LikesDAO;
import com.article_likes.model.Article_LikesVO;
import com.follow.model.FollowDAO;
import com.follow.model.FollowVO;
import com.member.model.MemberDAO;
import com.member.model.MemberVO;
import com.personal_system_notify.model.Personal_System_NotifyDAO;
import com.personal_system_notify.model.Personal_System_NotifyVO;



public class Article_ReplyDAO implements Article_ReplyDAO_Interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Campion");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO ARTICLE_REPLY (art_no,mbr_no,rep_cont,rep_time,rep_stat,likes) VALUES (?,?, ?, ?, ?,?)";
	private static final String GET_ALL_STMT = "SELECT art_rep_no,art_no,mbr_no,rep_cont,rep_time,rep_stat,likes FROM ARTICLE_REPLY order by art_rep_no";
	private static final String GET_ALL_REPLY_IN_ONE_ARTICLE_STMT = "Select art_rep_no,art_no,mbr_no,rep_cont,rep_time,rep_stat,likes FROM ARTICLE_REPLY WHERE art_no = ?";
	private static final String GET_ONE_STMT = "SELECT art_rep_no,art_no,mbr_no,rep_cont,rep_time,rep_stat,likes FROM ARTICLE_REPLY where art_rep_no = ?";
	private static final String DELETE = "DELETE FROM ARTICLE_REPLY where art_rep_no = ?";
	private static final String UPDATE = "UPDATE ARTICLE_REPLY set art_no=?, mbr_no=? ,rep_cont=? ,rep_time=? ,rep_stat=? ,likes=? where art_rep_no = ?";
	private static final String HIDE = "UPDATE ARTICLE_REPLY set rep_stat = 1 where art_rep_no=?";
	@Override
	public void insert(Article_ReplyVO article_ReplyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, article_ReplyVO.getArt_no());
			pstmt.setInt(2, article_ReplyVO.getMbr_no());
			pstmt.setString(3, article_ReplyVO.getRep_cont());
			pstmt.setTimestamp(4, article_ReplyVO.getRep_time());
			pstmt.setInt(5, article_ReplyVO.getRep_stat());
			pstmt.setInt(6, article_ReplyVO.getLikes());
			pstmt.executeUpdate();

			//���Ĺ��ճs�ʷs�W�t�γq��
			//���o�峹VO�A�H��o�峹���D
			ArticleDAO articleDAO = new ArticleDAO();
			ArticleVO articleVO = articleDAO.findByPrimaryKey(article_ReplyVO.getArt_no());
			
			Personal_System_NotifyVO personal_System_NotifyVO = new Personal_System_NotifyVO();
			personal_System_NotifyVO.setMbr_no(articleVO.getMbr_no());
			personal_System_NotifyVO.setNtfy_cont("�z���峹�u" + articleVO.getArt_title() + "�v���e���s���^��");
			personal_System_NotifyVO.setNtfy_stat(0);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ntfy_time = sdf.format(new java.util.Date());
			personal_System_NotifyVO.setNtfy_time(ntfy_time);
			
			Personal_System_NotifyDAO personal_System_NotifyDAO = new Personal_System_NotifyDAO();
			personal_System_NotifyDAO.insertWithArticle(personal_System_NotifyVO, con);
			
			//���o���gVO�A�H��o���g�̦W�r�A�C�ӫ��g�̪��|���s�����إߤ@�h�q��
			Article_LikesDAO article_LikesDAO = new Article_LikesDAO();
			List<Article_LikesVO> article_LikesVOList = article_LikesDAO.findByArt_no(article_ReplyVO.getArt_no());
			
			for(Article_LikesVO article_LikesVO : article_LikesVOList) {
				Personal_System_NotifyVO personal_System_NotifyVOLike = new Personal_System_NotifyVO();
				personal_System_NotifyVOLike.setMbr_no(article_LikesVO.getMbr_no());
				personal_System_NotifyVOLike.setNtfy_cont("�z���g���峹�u" + articleVO.getArt_title() + "�v���s���^��");
				personal_System_NotifyVOLike.setNtfy_stat(0);
				SimpleDateFormat sdfLike = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String ntfy_timeLike = sdfLike.format(new java.util.Date());
				personal_System_NotifyVOLike.setNtfy_time(ntfy_timeLike);
				
				personal_System_NotifyDAO.insertWithArticle(personal_System_NotifyVOLike, con);
			}
			//���Ĺ��ճs�ʷs�W�t�γq��
			

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
	public void update(Article_ReplyVO article_ReplyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);


			pstmt.setInt(1, article_ReplyVO.getArt_no());
			pstmt.setInt(2, article_ReplyVO.getMbr_no());
			pstmt.setString(3, article_ReplyVO.getRep_cont());
			pstmt.setTimestamp(4, article_ReplyVO.getRep_time());
			pstmt.setInt(5, article_ReplyVO.getRep_stat());
			pstmt.setInt(6, article_ReplyVO.getLikes());
			pstmt.setInt(7, article_ReplyVO.getArt_rep_no());
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
	public void delete(Integer art_rep_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, art_rep_no);

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
	public Article_ReplyVO findByPrimaryKey(Integer art_rep_no) {
		Article_ReplyVO article_ReplyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, art_rep_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				article_ReplyVO = new Article_ReplyVO();
				article_ReplyVO.setArt_rep_no(rs.getInt("art_rep_no"));
				article_ReplyVO.setArt_no(rs.getInt("art_no"));
				article_ReplyVO.setMbr_no(rs.getInt("mbr_no"));
				article_ReplyVO.setRep_cont(rs.getString("rep_cont"));
				article_ReplyVO.setRep_time(rs.getTimestamp("rep_time"));
				article_ReplyVO.setRep_stat(rs.getInt("rep_stat"));
				article_ReplyVO.setLikes(rs.getInt("likes"));
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
		return article_ReplyVO;
	}

	@Override
	public List<Article_ReplyVO> getAll() {
		List<Article_ReplyVO> list = new ArrayList<Article_ReplyVO>();
		Article_ReplyVO article_ReplyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// article_ReplyVO �]�٬� Domain objects
				article_ReplyVO = new Article_ReplyVO();
				article_ReplyVO.setArt_rep_no(rs.getInt("art_rep_no"));
				article_ReplyVO.setArt_no(rs.getInt("art_no"));
				article_ReplyVO.setMbr_no(rs.getInt("mbr_no"));
				article_ReplyVO.setRep_cont(rs.getString("rep_cont"));
				article_ReplyVO.setRep_time(rs.getTimestamp("rep_time"));
				article_ReplyVO.setRep_stat(rs.getInt("rep_stat"));
				article_ReplyVO.setLikes(rs.getInt("likes"));
				list.add(article_ReplyVO); // Store the row in the list
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
	public List<Article_ReplyVO> findByArt_no(Integer art_no) {
		List<Article_ReplyVO> list = new ArrayList<Article_ReplyVO>();
		Article_ReplyVO article_replyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ALL_REPLY_IN_ONE_ARTICLE_STMT);

			pstmt.setInt(1, art_no);

			rs = pstmt.executeQuery();



			while (rs.next()) {
				article_replyVO = new Article_ReplyVO();
				article_replyVO.setArt_rep_no(rs.getInt("art_rep_no"));
				article_replyVO.setArt_no(rs.getInt("art_no"));
				article_replyVO.setMbr_no(rs.getInt("mbr_no"));
				article_replyVO.setRep_cont(rs.getString("rep_cont"));
				article_replyVO.setRep_time(rs.getTimestamp("rep_time"));
				article_replyVO.setRep_stat(rs.getInt("rep_stat"));
				article_replyVO.setLikes(rs.getInt("likes"));
				list.add(article_replyVO);
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
		//System.out.println("artno in DAO 7 :" + art_no);
		return list;
	}

	@Override
	public void hide(Article_ReplyVO article_ReplyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(HIDE);

			pstmt.setInt(1, article_ReplyVO.getArt_rep_no());
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

	
	
	}


