package com.example.application.util;

import com.example.application.model.User;
import com.vaadin.flow.server.VaadinSession;

public class VaadinUtil {
	public static void setUser(User user) {
		VaadinSession.getCurrent().setAttribute("user", user);
	}

	public static User getUser() {
		return (User) VaadinSession.getCurrent().getAttribute("user");
	}

	public static void closeSession() {
		VaadinSession.getCurrent().close();
	}

}
