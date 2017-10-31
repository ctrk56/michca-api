package com.ctrk.michca.adk;

public class UserWrapper {
	
	private String _id;

	private User user;

	public UserWrapper(User user2, String id) {
		this.user = user2;
		this.set_id(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
}
