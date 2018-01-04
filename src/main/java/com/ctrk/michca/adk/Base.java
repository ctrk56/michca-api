package com.ctrk.michca.adk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Base {
	private String id;
	private String _id;
	private String michcaId;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
		this.michcaId = id;
	}
	public String getMichcaId() {
		return michcaId;
	}
	public void setMichcaId(String michcaId) {
		if(michcaId != null) {
			this.michcaId = michcaId;
			this.id = michcaId;
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
}
