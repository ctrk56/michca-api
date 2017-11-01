package com.ctrk.michca.adk;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserWrapper {
	
	public UserWrapper() {}
	
	@JsonInclude(Include.NON_NULL)
	private String _id;

    @JsonProperty("user")
	private User user;

	public UserWrapper(User user2, String id) {
		this.user = user2;
		if(id != null) {
			this._id = id;
		}
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
