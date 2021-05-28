
package com.member_mail.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

import com.article.model.ArticleDAO;
import com.article.model.ArticleVO;
import com.article_likes.model.Article_LikesDAO;
import com.article_likes.model.Article_LikesVO;
import com.follow.model.FollowDAO;
import com.follow.model.FollowVO;
import com.member.model.MemberService;
import com.member_mail.model.Member_mailForWS;
import com.member_mail.model.Member_mailService;
import com.member_mail.model.Member_mailVO;
import com.personal_system_notify.model.Personal_System_NotifyDAO;
import com.personal_system_notify.model.Personal_System_NotifyService;
import com.personal_system_notify.model.Personal_System_NotifyVO;
import com.place_order.model.Place_OrderDAO;
import com.place_order.model.Place_OrderVO;

@ServerEndpoint ("/Member_mailNotify/{userName}")
public class Member_mailNotify {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	
	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) {
		//記錄有誰登入
		final Session mySession = userSession;
		sessionsMap.put(userName, userSession);
		
		//準備用service找VO
		Member_mailService member_mailSvc = new Member_mailService();
		Personal_System_NotifyService personal_system_notifySvc = new Personal_System_NotifyService();
		
		//找出未讀的
		Map<String,String[]> mapMail = new LinkedHashMap<String,String[]>();
		mapMail.put("mail_read_stat",new String[] {"0"});
		Set<Member_mailVO> setMail = member_mailSvc.getWhereCondition(mapMail);
		//---
		Map<String,String[]> mapNotify = new LinkedHashMap<String,String[]>();
		mapNotify.put("ntfy_stat",new String[] {"0"});
		Set<Personal_System_NotifyVO> setNotify = personal_system_notifySvc.getWhereCondition(mapNotify);
		
		//給予累計數字的初值為零
		int countNoReadMail = 0;
		int countNoReadNotify = 0;
		
		//開始計數
		for(Member_mailVO vo : setMail) {
//			System.out.println(vo.getRcpt_no());
			if(userName.equals(vo.getRcpt_no().toString()) && "0".equals(vo.getMail_stat().toString())) {
				countNoReadMail++;
			}
		}
		for(Personal_System_NotifyVO vo : setNotify) {
//			System.out.println(vo.getMbr_no());
			if(userName.equals(vo.getMbr_no().toString()) && "0".equals(vo.getNtfy_stat().toString())) {
				countNoReadNotify++;
			}
		}
		try {
			//將結果轉為JSONObject
			Member_mailForWS member_mailForWS = new Member_mailForWS();
			member_mailForWS.setCountNoReadMail(countNoReadMail);
			member_mailForWS.setCountNoReadNotify(countNoReadNotify);
			String jsonStr = new JSONObject(member_mailForWS).toString();
			
			//傳送JSON格式字串給前端連線的WebSocket
			mySession.getBasicRemote().sendText(jsonStr);
//			System.out.println(Integer.valueOf(countNoReadMail).toString());
//			System.out.println(userName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@OnMessage
	public void onMessage(String numberAndNote) {
//		System.out.println("onMessage="+rcpt_no);
//		System.out.println("size="+sessionsMap.size());
		
		//將參數分成數字及識別字串
		String number = numberAndNote.split("/")[0];
		String note = numberAndNote.split("/")[1];
		
		//準備用service找VO
		Member_mailService member_mailSvc = new Member_mailService();
		Personal_System_NotifyService personal_system_notifySvc = new Personal_System_NotifyService();
		
		if(note.equals("mail") || note.equals("like")) {
		
			for(String key : sessionsMap.keySet()) {
//				System.out.println(sessionsMap.size()+"/"+key+"/"+number);
				if(key.equals(number)) {
//					System.out.println(key+"in");
	//				Member_mailService member_mailSvc = new Member_mailService();
	//				Map<String,String[]> map = new LinkedHashMap<String,String[]>();
	//				map.put("mail_read_stat",new String[] {"0"});
	//				Set<Member_mailVO> set = member_mailSvc.getWhereCondition(map);
	//				List<Integer> mail_noList = new ArrayList<Integer>();
	//				int countNoRead = 0;
	//				for(Member_mailVO vo : set) {
	////					System.out.println(vo.getRcpt_no());
	//					if(rcpt_no.equals(vo.getRcpt_no().toString()) && "0".equals(vo.getMail_stat().toString())) {
	//						mail_noList.add(vo.getMail_no());
	//						countNoRead++;
	//					}
	//				}
					
					//找出未讀的
					Map<String,String[]> mapMail = new LinkedHashMap<String,String[]>();
					mapMail.put("mail_read_stat",new String[] {"0"});
					Set<Member_mailVO> setMail = member_mailSvc.getWhereCondition(mapMail);
					//---
					Map<String,String[]> mapNotify = new LinkedHashMap<String,String[]>();
					mapNotify.put("ntfy_stat",new String[] {"0"});
//					mapNotify.put("ntfy_cont",new String[] {"新的按讚"});
					Set<Personal_System_NotifyVO> setNotify = personal_system_notifySvc.getWhereCondition(mapNotify);
					
					//準備裝符合條件的編號
					List<Integer> mail_noList = new ArrayList<Integer>();
					List<Integer> ntfy_noList = new ArrayList<Integer>();
					
					//給予累計數字的初值為零
					int countNoReadMail = 0;
					int countNoReadNotify = 0;
					
					//開始計數並加入符合條件的編號
					for(Member_mailVO vo : setMail) {
	//					System.out.println(vo.getRcpt_no());
						if(number.equals(vo.getRcpt_no().toString()) && "0".equals(vo.getMail_stat().toString())) {
							mail_noList.add(vo.getMail_no());
							countNoReadMail++;
						}
					}
					for(Personal_System_NotifyVO vo : setNotify) {
	//					System.out.println(vo.getMbr_no());
						if(number.equals(vo.getMbr_no().toString()) && "0".equals(vo.getNtfy_stat().toString())) {
							ntfy_noList.add(vo.getNtfy_no());
							countNoReadNotify++;
						}
					}
					
	//				//找出新增的VO
					Member_mailVO member_mailVO = new Member_mailVO();
					if(mail_noList.size() != 0 ) {
	//					System.out.println("mail_noList.size()="+mail_noList.size());
						member_mailVO = member_mailSvc.getOneMember_mail(mail_noList.get(mail_noList.size()-1));
					}
					Personal_System_NotifyVO personal_system_notifyVO = new Personal_System_NotifyVO();
					if(ntfy_noList.size() != 0 ) {
	//					System.out.println("ntfy_noList.size()="+ntfy_noList.size());
						personal_system_notifyVO = personal_system_notifySvc.getOnePersonal_System_Notify(ntfy_noList.get(ntfy_noList.size()-1));
	//					System.out.println("personal_system_notifyVO="+personal_system_notifyVO);
					}
					
					try {
						Member_mailForWS member_mailForWS = new Member_mailForWS();
						MemberService memberSvc = new MemberService();
						if(member_mailVO != null) {
							member_mailForWS.setMail_no(member_mailVO.getMail_no());
							member_mailForWS.setSend_no(memberSvc.getOneMember(member_mailVO.getSend_no()).getName());
							member_mailForWS.setRcpt_no(memberSvc.getOneMember(member_mailVO.getRcpt_no()).getName());
							member_mailForWS.setMail_read_stat(member_mailVO.getMail_read_stat());
							member_mailForWS.setMail_stat(member_mailVO.getMail_stat());
							member_mailForWS.setMail_cont(member_mailVO.getMail_cont());
							member_mailForWS.setMail_time(member_mailVO.getMail_time().substring(0, 10));
						}
						if(personal_system_notifyVO != null) {
	//						System.out.println("personal_system_notifyVO.getNtfy_no()="+personal_system_notifyVO.getNtfy_no());
							member_mailForWS.setNtfy_no(personal_system_notifyVO.getNtfy_no());
							member_mailForWS.setMbr_no(personal_system_notifyVO.getMbr_no());
							member_mailForWS.setNtfy_stat(personal_system_notifyVO.getNtfy_stat());
							member_mailForWS.setNtfy_cont(personal_system_notifyVO.getNtfy_cont());
							member_mailForWS.setNtfy_time(personal_system_notifyVO.getNtfy_time());
						}
						
						member_mailForWS.setCountNoReadMail(countNoReadMail);
						member_mailForWS.setCountNoReadNotify(countNoReadNotify);
						
						String jsonStr = new JSONObject(member_mailForWS).toString();
	//					System.out.println("jsonStr="+jsonStr);
						sessionsMap.get(key).getBasicRemote().sendText(jsonStr);
	//					sessionsMap.get(key).getBasicRemote().sendText(Integer.valueOf(countNoRead).toString());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else {
//					System.out.println(key+"out");
					continue;
					
				}
			}
		}else if(note.equals("article")){
			List<FollowVO> followVOList = new FollowDAO().findByflwed_mbr_no(Integer.valueOf(number));
			
			for(FollowVO followVO : followVOList) {
				for(String key : sessionsMap.keySet()) {
					String numberFollow = followVO.getFlw_mbr_no().toString();
//					System.out.println(sessionsMap.size()+"/"+key+"/"+numberFollow);
					if(key.equals(numberFollow)) {
//						System.out.println(key+"in");
						//找出未讀的
						Map<String,String[]> mapMail = new LinkedHashMap<String,String[]>();
						mapMail.put("mail_read_stat",new String[] {"0"});
						Set<Member_mailVO> setMail = member_mailSvc.getWhereCondition(mapMail);
						//---
						Map<String,String[]> mapNotify = new LinkedHashMap<String,String[]>();
						mapNotify.put("ntfy_stat",new String[] {"0"});
//						mapNotify.put("ntfy_cont",new String[] {"追蹤的"});
						Set<Personal_System_NotifyVO> setNotify = personal_system_notifySvc.getWhereCondition(mapNotify);
						
						//準備裝符合條件的編號
						List<Integer> ntfy_noList = new ArrayList<Integer>();
						
						//給予累計數字的初值為零
						int countNoReadMail = 0;
						int countNoReadNotify = 0;
						
						//開始計數並加入符合條件的編號
						for(Member_mailVO vo : setMail) {
		//					System.out.println(vo.getRcpt_no());
							if(numberFollow.equals(vo.getRcpt_no().toString()) && "0".equals(vo.getMail_stat().toString())) {
								countNoReadMail++;
							}
						}
						for(Personal_System_NotifyVO vo : setNotify) {
		//					System.out.println(vo.getMbr_no());
							if(numberFollow.equals(vo.getMbr_no().toString()) && "0".equals(vo.getNtfy_stat().toString())) {
								ntfy_noList.add(vo.getNtfy_no());
								countNoReadNotify++;
							}
						}
						
		//				//找出新增的VO

						Personal_System_NotifyVO personal_system_notifyVO = new Personal_System_NotifyVO();
						if(ntfy_noList.size() != 0 ) {
		//					System.out.println("ntfy_noList.size()="+ntfy_noList.size());
							personal_system_notifyVO = personal_system_notifySvc.getOnePersonal_System_Notify(ntfy_noList.get(ntfy_noList.size()-1));
		//					System.out.println("personal_system_notifyVO="+personal_system_notifyVO);
						}
						
						try {
							Member_mailForWS member_mailForWS = new Member_mailForWS();

							if(personal_system_notifyVO != null) {
		//						System.out.println("personal_system_notifyVO.getNtfy_no()="+personal_system_notifyVO.getNtfy_no());
								member_mailForWS.setNtfy_no(personal_system_notifyVO.getNtfy_no());
								member_mailForWS.setMbr_no(personal_system_notifyVO.getMbr_no());
								member_mailForWS.setNtfy_stat(personal_system_notifyVO.getNtfy_stat());
								member_mailForWS.setNtfy_cont(personal_system_notifyVO.getNtfy_cont());
								member_mailForWS.setNtfy_time(personal_system_notifyVO.getNtfy_time());
							}
							
							member_mailForWS.setCountNoReadMail(countNoReadMail);
							member_mailForWS.setCountNoReadNotify(countNoReadNotify);
							
							String jsonStr = new JSONObject(member_mailForWS).toString();
//							System.out.println("jsonStr="+jsonStr);
							sessionsMap.get(key).getBasicRemote().sendText(jsonStr);
		//					sessionsMap.get(key).getBasicRemote().sendText(Integer.valueOf(countNoRead).toString());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}else {
//						System.out.println(key+"out");
						continue;
						
					}
				}
			}
		}else if(note.equals("reply")){

			//推播留言通知給按讚者
			Article_LikesDAO article_LikesDAO = new Article_LikesDAO();
			List<Article_LikesVO> article_LikesVOList = article_LikesDAO.findByArt_no(Integer.valueOf(number));
			
			for(Article_LikesVO article_LikesVO : article_LikesVOList) {
				for(String key : sessionsMap.keySet()) {
					String numberLike = article_LikesVO.getMbr_no().toString();
//					System.out.println(sessionsMap.size()+"/"+key+"/"+numberLike);
					if(key.equals(numberLike)) {
//						System.out.println(key+"in");
						//找出未讀的
						Map<String,String[]> mapMail = new LinkedHashMap<String,String[]>();
						mapMail.put("mail_read_stat",new String[] {"0"});
						Set<Member_mailVO> setMail = member_mailSvc.getWhereCondition(mapMail);
						//---
						Map<String,String[]> mapNotify = new LinkedHashMap<String,String[]>();
						mapNotify.put("ntfy_stat",new String[] {"0"});
//						mapNotify.put("ntfy_cont",new String[] {"按讚的文章"});
						Set<Personal_System_NotifyVO> setNotify = personal_system_notifySvc.getWhereCondition(mapNotify);
						
						//準備裝符合條件的編號
						List<Integer> ntfy_noList = new ArrayList<Integer>();
						
						//給予累計數字的初值為零
						int countNoReadMail = 0;
						int countNoReadNotify = 0;
						
						//開始計數並加入符合條件的編號
						for(Member_mailVO vo : setMail) {
//							System.out.println(vo.getRcpt_no());
							if(numberLike.equals(vo.getRcpt_no().toString()) && "0".equals(vo.getMail_stat().toString())) {
								countNoReadMail++;
							}
						}
						for(Personal_System_NotifyVO vo : setNotify) {
//							System.out.println(vo.getMbr_no());
							if(numberLike.equals(vo.getMbr_no().toString()) && "0".equals(vo.getNtfy_stat().toString())) {
								ntfy_noList.add(vo.getNtfy_no());
								countNoReadNotify++;
							}
						}
						
		//				//找出新增的VO

						Personal_System_NotifyVO personal_system_notifyVO = new Personal_System_NotifyVO();
						if(ntfy_noList.size() != 0 ) {
		//					System.out.println("ntfy_noList.size()="+ntfy_noList.size());
							personal_system_notifyVO = personal_system_notifySvc.getOnePersonal_System_Notify(ntfy_noList.get(ntfy_noList.size()-1));
		//					System.out.println("personal_system_notifyVO="+personal_system_notifyVO);
						}
						
						try {
							Member_mailForWS member_mailForWS = new Member_mailForWS();

							if(personal_system_notifyVO != null) {
		//						System.out.println("personal_system_notifyVO.getNtfy_no()="+personal_system_notifyVO.getNtfy_no());
								member_mailForWS.setNtfy_no(personal_system_notifyVO.getNtfy_no());
								member_mailForWS.setMbr_no(personal_system_notifyVO.getMbr_no());
								member_mailForWS.setNtfy_stat(personal_system_notifyVO.getNtfy_stat());
								member_mailForWS.setNtfy_cont(personal_system_notifyVO.getNtfy_cont());
								member_mailForWS.setNtfy_time(personal_system_notifyVO.getNtfy_time());
							}
							
							member_mailForWS.setCountNoReadMail(countNoReadMail);
							member_mailForWS.setCountNoReadNotify(countNoReadNotify);
							
							String jsonStr = new JSONObject(member_mailForWS).toString();
//							System.out.println("jsonStr="+jsonStr);
							sessionsMap.get(key).getBasicRemote().sendText(jsonStr);
		//					sessionsMap.get(key).getBasicRemote().sendText(Integer.valueOf(countNoRead).toString());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}else {
//						System.out.println(key+"out");
						continue;
						
					}
				}
			}
			
			//推播留言通知給作者
			ArticleDAO articleDAO = new ArticleDAO();
			ArticleVO articleVO = articleDAO.findByPrimaryKey(Integer.valueOf(number));
			String authorNo = articleVO.getMbr_no().toString();
			
			for(String key : sessionsMap.keySet()) {
//				System.out.println(sessionsMap.size()+"/"+key+"/"+authorNo);
				if(key.equals(authorNo)) {
//					System.out.println(key+"in");
					//找出未讀的
					Map<String,String[]> mapMail = new LinkedHashMap<String,String[]>();
					mapMail.put("mail_read_stat",new String[] {"0"});
					Set<Member_mailVO> setMail = member_mailSvc.getWhereCondition(mapMail);
					//---
					Map<String,String[]> mapNotify = new LinkedHashMap<String,String[]>();
					mapNotify.put("ntfy_stat",new String[] {"0"});
//					mapNotify.put("ntfy_cont",new String[] {"內容"});
					Set<Personal_System_NotifyVO> setNotify = personal_system_notifySvc.getWhereCondition(mapNotify);
					
					//準備裝符合條件的編號
					List<Integer> ntfy_noList = new ArrayList<Integer>();
					
					//給予累計數字的初值為零
					int countNoReadMail = 0;
					int countNoReadNotify = 0;
					
					//開始計數並加入符合條件的編號
					for(Member_mailVO vo : setMail) {
	//					System.out.println(vo.getRcpt_no());
						if(authorNo.equals(vo.getRcpt_no().toString()) && "0".equals(vo.getMail_stat().toString())) {
							countNoReadMail++;
						}
					}
					for(Personal_System_NotifyVO vo : setNotify) {
	//					System.out.println(vo.getMbr_no());
						if(authorNo.equals(vo.getMbr_no().toString()) && "0".equals(vo.getNtfy_stat().toString())) {
//							ntfy_noList.add(vo.getNtfy_no());
							countNoReadNotify++;
						}
					}
					
					//取出作者文章的留言通知
					mapNotify.put("ntfy_cont",new String[] {"內容"});
					Set<Personal_System_NotifyVO> setNotifyAuthor = personal_system_notifySvc.getWhereCondition(mapNotify);
					for(Personal_System_NotifyVO vo : setNotifyAuthor) {
	//					System.out.println(vo.getMbr_no());
						if(authorNo.equals(vo.getMbr_no().toString()) && "0".equals(vo.getNtfy_stat().toString())) {
							ntfy_noList.add(vo.getNtfy_no());
						}
					}
					
	//				//找出新增的VO

					Personal_System_NotifyVO personal_system_notifyVO = new Personal_System_NotifyVO();
					if(ntfy_noList.size() != 0 ) {
	//					System.out.println("ntfy_noList.size()="+ntfy_noList.size());
						personal_system_notifyVO = personal_system_notifySvc.getOnePersonal_System_Notify(ntfy_noList.get(ntfy_noList.size()-1));
	//					System.out.println("personal_system_notifyVO="+personal_system_notifyVO);
					}
					
					try {
						Member_mailForWS member_mailForWS = new Member_mailForWS();

						if(personal_system_notifyVO != null) {
	//						System.out.println("personal_system_notifyVO.getNtfy_no()="+personal_system_notifyVO.getNtfy_no());
							member_mailForWS.setNtfy_no(personal_system_notifyVO.getNtfy_no());
							member_mailForWS.setMbr_no(personal_system_notifyVO.getMbr_no());
							member_mailForWS.setNtfy_stat(personal_system_notifyVO.getNtfy_stat());
							member_mailForWS.setNtfy_cont(personal_system_notifyVO.getNtfy_cont());
							member_mailForWS.setNtfy_time(personal_system_notifyVO.getNtfy_time());
						}
						
						member_mailForWS.setCountNoReadMail(countNoReadMail);
						member_mailForWS.setCountNoReadNotify(countNoReadNotify);
						
						String jsonStr = new JSONObject(member_mailForWS).toString();
//						System.out.println("jsonStr="+jsonStr);
						sessionsMap.get(key).getBasicRemote().sendText(jsonStr);
	//					sessionsMap.get(key).getBasicRemote().sendText(Integer.valueOf(countNoRead).toString());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else {
//					System.out.println(key+"out");
					continue;
					
				}
			}
		}else if(note.equals("placeOrder")){

			//推播留言通知給訂營位的會員
			Place_OrderDAO place_OrderDAO = new Place_OrderDAO();
			Place_OrderVO place_OrderVO = place_OrderDAO.findByPrimaryKey(Integer.valueOf(number));
			String mbr_no = place_OrderVO.getMbr_no().toString();
			
			for(String key : sessionsMap.keySet()) {
//				System.out.println(sessionsMap.size()+"/"+key+"/"+authorNo);
				if(key.equals(mbr_no)) {
//					System.out.println(key+"in");
					//找出未讀的
					Map<String,String[]> mapMail = new LinkedHashMap<String,String[]>();
					mapMail.put("mail_read_stat",new String[] {"0"});
					Set<Member_mailVO> setMail = member_mailSvc.getWhereCondition(mapMail);
					//---
					Map<String,String[]> mapNotify = new LinkedHashMap<String,String[]>();
					mapNotify.put("ntfy_stat",new String[] {"0"});
//					mapNotify.put("ntfy_cont",new String[] {"內容"});
					Set<Personal_System_NotifyVO> setNotify = personal_system_notifySvc.getWhereCondition(mapNotify);
					
					//準備裝符合條件的編號
					List<Integer> ntfy_noList = new ArrayList<Integer>();
					
					//給予累計數字的初值為零
					int countNoReadMail = 0;
					int countNoReadNotify = 0;
					
					//開始計數並加入符合條件的編號
					for(Member_mailVO vo : setMail) {
	//					System.out.println(vo.getRcpt_no());
						if(mbr_no.equals(vo.getRcpt_no().toString()) && "0".equals(vo.getMail_stat().toString())) {
							countNoReadMail++;
						}
					}
					for(Personal_System_NotifyVO vo : setNotify) {
	//					System.out.println(vo.getMbr_no());
						if(mbr_no.equals(vo.getMbr_no().toString()) && "0".equals(vo.getNtfy_stat().toString())) {
//							ntfy_noList.add(vo.getNtfy_no());
							countNoReadNotify++;
						}
					}
					
					//取出作者文章的留言通知
					mapNotify.put("ntfy_cont",new String[] {"訂單"});
					Set<Personal_System_NotifyVO> setNotifyAuthor = personal_system_notifySvc.getWhereCondition(mapNotify);
					for(Personal_System_NotifyVO vo : setNotifyAuthor) {
	//					System.out.println(vo.getMbr_no());
						if(mbr_no.equals(vo.getMbr_no().toString()) && "0".equals(vo.getNtfy_stat().toString())) {
							ntfy_noList.add(vo.getNtfy_no());
						}
					}
					
	//				//找出新增的VO

					Personal_System_NotifyVO personal_system_notifyVO = new Personal_System_NotifyVO();
					if(ntfy_noList.size() != 0 ) {
	//					System.out.println("ntfy_noList.size()="+ntfy_noList.size());
						personal_system_notifyVO = personal_system_notifySvc.getOnePersonal_System_Notify(ntfy_noList.get(ntfy_noList.size()-1));
	//					System.out.println("personal_system_notifyVO="+personal_system_notifyVO);
					}
					
					try {
						Member_mailForWS member_mailForWS = new Member_mailForWS();

						if(personal_system_notifyVO != null) {
	//						System.out.println("personal_system_notifyVO.getNtfy_no()="+personal_system_notifyVO.getNtfy_no());
							member_mailForWS.setNtfy_no(personal_system_notifyVO.getNtfy_no());
							member_mailForWS.setMbr_no(personal_system_notifyVO.getMbr_no());
							member_mailForWS.setNtfy_stat(personal_system_notifyVO.getNtfy_stat());
							member_mailForWS.setNtfy_cont(personal_system_notifyVO.getNtfy_cont());
							member_mailForWS.setNtfy_time(personal_system_notifyVO.getNtfy_time());
						}
						
						member_mailForWS.setCountNoReadMail(countNoReadMail);
						member_mailForWS.setCountNoReadNotify(countNoReadNotify);
						
						String jsonStr = new JSONObject(member_mailForWS).toString();
//						System.out.println("jsonStr="+jsonStr);
						sessionsMap.get(key).getBasicRemote().sendText(jsonStr);
	//					sessionsMap.get(key).getBasicRemote().sendText(Integer.valueOf(countNoRead).toString());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else {
//					System.out.println(key+"out");
					continue;
					
				}
			}
		}
	}
}

