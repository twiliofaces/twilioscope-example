package org.twiliofaces.test.twilioscope.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.twiliofaces.test.twilioscope.model.User;

@ApplicationScoped
@Named
public class UserRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, User> users;

	public void save(User user) {
		getUsers().put(user.getNumber(), user);
	}

	public boolean exist(String number) {
		return getUsers().containsKey(number);
	}

	public User find(String number) {
		if (exist(number))
			return getUsers().get(number);
		return null;
	}

	public Map<String, User> getUsers() {
		if (this.users == null)
			this.users = new ConcurrentHashMap<String, User>();
		return users;
	}

	public void setUsers(Map<String, User> users) {
		this.users = users;
	}

	@Produces
	@Named
	public List<User> getAllUsers() {
		if (getUsers() != null)
			return new ArrayList(getUsers().values());
		return new ArrayList<User>();
	}

}
