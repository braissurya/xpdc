package com.sma.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.sma.model.User;

/**
 * Daftar user yang terlogin didalam session
 * 
 * @author Yusuf
 * @since Jun 20, 2013 (10:15:26 AM)
 *
 */
@Component //@Component berarti otomatis register sebagai bean, tanpa perlu didefinisikan di spring xml
public class SessionRegistry implements Serializable {

	private static final long serialVersionUID = 8979774872352286984L;
	
	public Map<String, User> userMap;

	/**
	 * Apakah user berada pada daftar user yang sedang login?
	 * 
	 * @param user
	 * @return
	 */
	public boolean contains(User user){
		return userMap.containsKey(user.getUsername());
	}
	
	/**
	 * Matikan session user
	 * @param currentUser
	 */
	public void kick(User currentUser, HttpSession session) {
		//buang dari daftar
		userMap.remove(currentUser.getUsername().toUpperCase().trim());
		//invalidate session
		if(session != null) session.invalidate();
	}

	public void put(User currentUser) {
		userMap.put(currentUser.getUsername().toUpperCase().trim(), currentUser);
	}

	//Constructor + Getter Setter
	public SessionRegistry() {
		this.userMap = new HashMap<String, User>();
	}

	public Map<String, User> getUserMap() {return userMap;}
	public void setUserMap(Map<String, User> userMap) {this.userMap = userMap;}

}