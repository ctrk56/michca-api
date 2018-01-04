package com.ctrk.michca.adk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompetitionDetails extends Base {
	private String title;
	private String subtitle;
	private String image_thumb_url;
	private String image_profile_url;
	private String image_display_url;
	private String image_mini_url;
	private String cover_image_url;
	private String image_url;
	private boolean published;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getImage_thumb_url() {
		return image_thumb_url;
	}

	public void setImage_thumb_url(String image_thumb_url) {
		this.image_thumb_url = image_thumb_url;
	}

	public String getImage_profile_url() {
		return image_profile_url;
	}

	public void setImage_profile_url(String image_profile_url) {
		this.image_profile_url = image_profile_url;
	}

	public String getImage_display_url() {
		return image_display_url;
	}

	public void setImage_display_url(String image_display_url) {
		this.image_display_url = image_display_url;
	}

	public String getImage_mini_url() {
		return image_mini_url;
	}

	public void setImage_mini_url(String image_mini_url) {
		this.image_mini_url = image_mini_url;
	}

	public String getCover_image_url() {
		return cover_image_url;
	}

	public void setCover_image_url(String cover_image_url) {
		this.cover_image_url = cover_image_url;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

}
