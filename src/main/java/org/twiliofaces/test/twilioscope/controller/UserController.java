package org.twiliofaces.test.twilioscope.controller;

import java.io.Serializable;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;

import org.twiliofaces.annotations.TwilioScope;
import org.twiliofaces.annotations.notification.CallSid;
import org.twiliofaces.annotations.notification.From;
import org.twiliofaces.annotations.notification.RecordingUrl;
import org.twiliofaces.extension.TwilioScoped;
import org.twiliofaces.test.twilioscope.model.User;
import org.twiliofaces.test.twilioscope.repository.UserRepository;

@TwilioScope
@Named
public class UserController implements TwilioScoped, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	UserRepository userRepository;

	@Inject
	@CallSid
	Instance<String> callSid;

	@Inject
	@From
	Instance<String> from;

	@Inject
	@RecordingUrl
	Instance<String> recordingUrl;

	private User user;

	int count = 0;

	public UserController() {
	}

	public boolean isNewUser() {
		System.out.println(getFrom() + " " + getCallSid());
		if (getFrom() != null && userRepository.exist(getFrom())) {
			return false;
		} else {
			this.user = new User(getFrom());
			return true;
		}

	}

	public String getCallSid() {
		return callSid.get();
	}

	public String getFrom() {
		return from.get();
	}

	public String getRecordingUrl() {
		return recordingUrl.get();
	}

	public void saveUser() {
		getUser().setMsgUrl(getRecordingUrl());
		userRepository.save(getUser());
		log();
	}

	public void log() {
		count++;
		System.out.println("CALL SID: " + getCallSid() + " count: " + count);
	}

	public User getUser() {
		return user;
	}

}