package com.tje.websocket;

import java.util.*;
import com.tje.model.*;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class BroadCastController extends TextWebSocketHandler {
	
	class WebSocketClientInfo {
		private WebSocketSession session;
		private ChatMember chatMember;
		public WebSocketClientInfo() {}
		public WebSocketClientInfo(WebSocketSession session, ChatMember chatMember) {
			this.session = session;
			this.chatMember = chatMember;
		}
		public WebSocketSession getSession() {
			return session;
		}
		public void setSession(WebSocketSession session) {
			this.session = session;
		}
		public ChatMember getChatMember() {
			return chatMember;
		}
		public void setChatMember(ChatMember chatMember) {
			this.chatMember = chatMember;
		}
	}
	
	class ChatMember {
		private String nickname;
		private boolean isConn;
		private String sessionId;
		public ChatMember() {}
		public ChatMember(String nickname, boolean isConn, String sessionId) {
			this.nickname = nickname;
			this.isConn = isConn;
			this.sessionId = sessionId;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public boolean isConn() {
			return isConn;
		}
		public void setConn(boolean isConn) {
			this.isConn = isConn;
		}
		public String getSessionId() {
			return sessionId;
		}
		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}
	}
	
	// Admin 객체
	class Admin extends ChatMember {
		private String nickname;
		private boolean isConn;
		private String sessionId;
		public Admin() {}
		public Admin(String nickname, boolean isConn, String sessionId) {
			this.nickname = nickname;
			this.isConn = isConn;
			this.sessionId = sessionId;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public boolean isConn() {
			return isConn;
		}
		public void setConn(boolean isConn) {
			this.isConn = isConn;
		}
		public String getSessionId() {
			return sessionId;
		}
		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}
	}
	// Client 객체
	class Client extends ChatMember {
		private String nickname;
		private boolean isConn;
		private String sessionId;
		public Client() {}
		public Client(String nickname, boolean isConn, String sessionId) {
			this.nickname = nickname;
			this.isConn = isConn;
			this.sessionId = sessionId;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public boolean isConn() {
			return isConn;
		}
		public void setConn(boolean isConn) {
			this.isConn = isConn;
		}
		public String getSessionId() {
			return sessionId;
		}
		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}
	}
	
	
	
	// 세션 아이디와 소켓정보를 저장하는 MAP 객체
	private LinkedHashMap<String, WebSocketClientInfo> sessionMap = new LinkedHashMap<>();
	// Admin 상태를 저장하는 MAP 객체. 순서를 유지하기 위해 LinkedHashMap을 사용한다
	private LinkedHashMap<Admin, WebSocketClientInfo> adminMap = new LinkedHashMap<BroadCastController.Admin, WebSocketClientInfo>();
	// Client 상태를 저장하는 MAP 객체 
	private LinkedHashMap<Client, WebSocketClientInfo> clientMap = new LinkedHashMap<BroadCastController.Client, WebSocketClientInfo>();
	// Admin과 Client의 짝을 지어주는 MAP 객체
	private LinkedHashMap<WebSocketSession, WebSocketSession> chatMatch = new LinkedHashMap<WebSocketSession, WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("Admin");
		boolean noAdmin = true;
		boolean noClient = true;
		//
		Map<String, Object> map = session.getAttributes();
		Member login_member = (Member)map.get("login_member");
		//
		String member_id = login_member.getMember_id(); // 잘 받아와짐
		String nickname = login_member.getNickname(); // 잘 받아와짐
		
		if(member_id.equals("admin")) {
			// admin이 접속할 시 clientMap에서 isConn이 false인 상태인 클라이언트와 같이 sessionMap에 저장한다.
			Admin admin = new Admin(nickname, false, session.getId());
			WebSocketClientInfo wscInfo = new WebSocketClientInfo(session, admin);
			adminMap.put(admin, wscInfo);
			
			for( Client c : clientMap.keySet() ) {
				if( c.isConn() == false ) {
					admin.setConn(true);
					c.setConn(true);
					sessionMap.put(admin.getSessionId(), adminMap.get(admin));
					sessionMap.put(c.getSessionId(), clientMap.get(c));
					chatMatch.put(adminMap.get(admin).getSession(), clientMap.get(c).getSession());
					chatMatch.put(clientMap.get(c).getSession(), adminMap.get(admin).getSession());
					adminMap.get(admin).getSession().sendMessage(new TextMessage("<p align='center' style='color: silver;'>클라이언트와 연결되었습니다.</p>"));
					adminMap.get(admin).getSession().sendMessage(new TextMessage("<p align='center' style='color: silver;'>대화시작</p>"));
					clientMap.get(c).getSession().sendMessage(new TextMessage("<p align='center' style='color: silver;'>관리자와 연결되었습니다.</p>"));
					clientMap.get(c).getSession().sendMessage(new TextMessage("<p align='center' style='color: silver;'>대화시작</p>"));
					noClient = false;
					break;
				}
			}
			if( noClient ) {
				adminMap.get(admin).getSession().sendMessage(new TextMessage("<p align='center' style='color: silver;'>상담 가능한 클라이언트가 없습니다.</p>"));
			}
			System.out.println("admin.nickName : " + nickname +
					", admin.isConn : " + admin.isConn() + 
					", admin.sessionId : " + admin.getSessionId() + 
					", wscInfo.session : " + wscInfo.getSession() + 
					", wscInfo.chatMember : " + wscInfo.getChatMember().getClass());
			for( String s : sessionMap.keySet() ) {
				System.out.println("key : " + s +
						", val.session" + sessionMap.get(s).getSession() +
						", val.chatMember.nick" + sessionMap.get(s).getChatMember().getNickname() + 
						", val.chatMember.isConn" + sessionMap.get(s).getChatMember().isConn());
				
			}
		} else {
			// client가 접속할 시 adminMap에서 isConn이 false인 상태인 어드민과 같이 sessionMap에 저장한다.
			Client client = new Client(nickname, false, session.getId());
			WebSocketClientInfo wscInfo = new WebSocketClientInfo(session, client);
			clientMap.put(client, wscInfo);
			
			for( Admin a : adminMap.keySet() ) {
				if( a.isConn() == false ) {
					client.setConn(true);
					a.setConn(true);
					sessionMap.put(client.getSessionId(), clientMap.get(client));
					sessionMap.put(a.getSessionId(), adminMap.get(a));
					chatMatch.put(clientMap.get(client).getSession(), adminMap.get(a).getSession());
					chatMatch.put(adminMap.get(a).getSession(), clientMap.get(client).getSession());
					clientMap.get(client).getSession().sendMessage(new TextMessage("<p align='center' style='color: silver;'>관리자와 연결되었습니다.</p>"));
					clientMap.get(client).getSession().sendMessage(new TextMessage("<p align='center' style='color: silver;'>대화시작</p>"));
					adminMap.get(a).getSession().sendMessage(new TextMessage("<p align='center' style='color: silver;'>클라이언트와 연결되었습니다.</p>"));
					adminMap.get(a).getSession().sendMessage(new TextMessage("<p align='center' style='color: silver;'>대화시작</p>"));
					noAdmin = false;
					break;
				}
			}
			if( noAdmin ) {
				clientMap.get(client).getSession().sendMessage(new TextMessage("<p align='center' style='color: silver;'>상담 가능한 관리자가 없습니다.</p>"));
			}
			System.out.println("client.nickName : " + nickname +
					", client.isConn : " + client.isConn() + 
					", client.sessionId : " + client.getSessionId() + 
					", wscInfo.session : " + wscInfo.getSession() + 
					", wscInfo.chatMember : " + wscInfo.getChatMember().getClass());
			for( String s : sessionMap.keySet() ) {
				System.out.println("key : " + s +
						", val.session" + sessionMap.get(s).getSession() +
						", val.chatMember.nick" + sessionMap.get(s).getChatMember().getNickname() + 
						", val.chatMember.isConn" + sessionMap.get(s).getChatMember().isConn());
				
			}
		}
	}
	
	// 웹 소켓 클라이언트가 서버측으로 데이터를 전송할 때 실행되는 메소드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		/*
		// 연결 되자마자 닉네임이 바로 전달되어 닉네임 설정
		String id = session.getId();
		if( sessionMap.get(id).getNickname() == null ) {
			sessionMap.get(id).setNickname(nickname); // 해당 세션의 WebSocketClientInfo를 완성했다.
			StringBuffer idAndNicknames = new StringBuffer("newClient:");
			idAndNicknames.append(String.format("%s (%s)", nickname, id)); // (닉네임) (세션아이디) 형태로 저장. 접속할때마다 append
			session.sendMessage(new TextMessage(idAndNicknames.toString()));
			sendClientNicknames(id);
			return;
		}
		// 닉네임이 설정된 이후
		// 클라이언트가 전송한 데이터 출력
		 * */
		// System.out.printf("%s로부터 [%s]를 받음\n", client.getNickname(), message.getPayload());
		WebSocketSession receiverSession = chatMatch.get(session);
		// StringTokenizer st = new StringTokenizer(message.getPayload(), ": ");
		// String sender = st.nextToken();
		String sender = sessionMap.get(session.getId()).getChatMember().getNickname();
		System.out.println(sender);
		String receiver = sessionMap.get(receiverSession.getId()).getChatMember().getNickname();
		System.out.println(receiver);
		String msg = message.getPayload(); // 메세지
		System.out.println(msg);
		receiverSession.sendMessage(new TextMessage("<p align='left' style='color: black; font-size: 20px;'>" + sender + " : " + msg + "</p>"));
		
		/*
		if( !target.equals("all") ) {
			sessionMap.get(target).getSession().sendMessage(
					new TextMessage("(" + sessionMap.get(id).getNickname() + "님으로부터 쪽지 : " + msg));
		} else {
			// 모든 클라이언트에게 전송
			for( String key : sessionMap.keySet() ) {
				sessionMap.get(key).getSession().sendMessage(new TextMessage(sessionMap.get(id).getNickname() + " : " + message.getPayload()));
			}
		}
		*/
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println(session.getId() + "가 연결 종료됨");
		// 나간 사람의 정보는 모두 지운다.
		String sessionId = session.getId();
		if( sessionMap.get(sessionId).chatMember.getClass().equals(Admin.class) ) { // 나간 사람의 ChatMember 타입이 Admin이면
			System.out.println("admin의 커넥션 종료");
			// sessionMap에서 현재 종료된 session의 id로 admin을 가져온다.
			Admin admin = (Admin)sessionMap.get(sessionId).chatMember;
			System.out.println("admin close 부분의 Admin 객체 생성 후 admin.nickname : " + admin.getNickname());
			// sessionMap에서 admin을 지운다.
			sessionMap.remove(sessionId);
			// adminMap에서 admin을 지운다.
			adminMap.remove(admin);
			// 클라이언트의 웹소켓 세션을 가져오기
			WebSocketSession clientSession = chatMatch.get(session);
			// chatMap에서 둘의 session을 지운다. ( 그 전에 각각 상대가 연결을 종료했다는 메세지를 보낸다. )
			clientSession.sendMessage(new TextMessage("<p align='center' style='color: silver;'>상대가 연결을 종료했습니다.</p><p align='center' style='color: silver;'>새로운 상담을 원하면 다시 연결해주세요.</p>"));
			clientSession.sendMessage(new TextMessage("<p align='center' style='color: silver;'>대화종료</p>"));
			chatMatch.remove(session); // admin, client의 쌍
			chatMatch.remove(clientSession); // client, admin의 쌍
			// sessionMap과 clientMap 에서 클라이언트를 지운다.
			Client cl = (Client)sessionMap.get(clientSession.getId()).chatMember;
			sessionMap.remove(clientSession.getId());
			clientMap.remove(cl);
			
		} else { // 나간 사람이 Client
			System.out.println("client의 커넥션 종료");
			// sessionMap에서 현재 종료된 session의 id로 client을 가져온다.
			Client client = (Client)sessionMap.get(sessionId).chatMember;
			sessionMap.remove(sessionId);
			clientMap.remove(client);
			// 어드민의 웹소켓 세션을 가져오기
			WebSocketSession adminSession = chatMatch.get(session);
			// chatMap에서 둘의 session을 지운다.
			adminSession.sendMessage(new TextMessage("<p align='center' style='color: silver;'>상대가 연결을 종료했습니다.</p>"));
			adminSession.sendMessage(new TextMessage("<p align='center' style='color: silver;'>대화종료</p>"));
			chatMatch.remove(session); // client, admin의 쌍
			chatMatch.remove(adminSession); // admin, client의 쌍
			// 어드민의 isConn을 false로 바꾼다. 그러기위해 Admin를 찾는다.
			sessionMap.get(adminSession.getId()).chatMember.setConn(false);
			
			
			// 클라이언트가 나갔으면 다시 false가 된 admin을 대기중인 클라이언트가 있는지 찾아 연결해준다.
			//
			Admin admin = (Admin)sessionMap.get(adminSession.getId()).chatMember;
			boolean noClient = true;
			for( Client c : clientMap.keySet() ) {
				if( c.isConn() == false ) {
					admin.setConn(true);
					c.setConn(true);
					sessionMap.put(admin.getSessionId(), adminMap.get(admin));
					sessionMap.put(c.getSessionId(), clientMap.get(c));
					chatMatch.put(adminMap.get(admin).getSession(), clientMap.get(c).getSession());
					chatMatch.put(clientMap.get(c).getSession(), adminMap.get(admin).getSession());
					adminMap.get(admin).getSession().sendMessage(new TextMessage("<p align='center' style='color: silver;'>클라이언트와 연결되었습니다.</p>"));
					adminMap.get(admin).getSession().sendMessage(new TextMessage("<p align='center' style='color: silver;'>대화시작<p>"));
					clientMap.get(c).getSession().sendMessage(new TextMessage("<p align='center' style='color: silver;'>관리자와 연결되었습니다.</p>"));
					clientMap.get(c).getSession().sendMessage(new TextMessage("<p align='center' style='color: silver;'>대화시작</p>"));
					noClient = false;
					break;
				}
			}
			if( noClient ) {
				adminMap.get(admin).getSession().sendMessage(new TextMessage("<p align='center' style='color: silver;'>상담 가능한 클라이언트가 없습니다.</p>"));
			}
			System.out.println("admin.nickName : " + admin.getNickname() +
					", admin.isConn : " + admin.isConn() + 
					", admin.sessionId : " + admin.getSessionId() + 
					", wscInfo.session : " + sessionMap.get(adminSession) + 
					", wscInfo.chatMember : " + sessionMap.get(adminSession).getChatMember().getClass());
			for( String s : sessionMap.keySet() ) {
				System.out.println("key : " + s +
						", val.session" + sessionMap.get(s).getSession() +
						", val.chatMember.nick" + sessionMap.get(s).getChatMember().getNickname() + 
						", val.chatMember.isConn" + sessionMap.get(s).getChatMember().isConn());
				
			}
		};
	}
}