package com.example.application.util;

import com.example.application.model.User;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
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
	
	public static void showSuccess(String text) {
		Notification.show(text, 3000, Position.BOTTOM_STRETCH).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
	}
	
	public static void showError(String text) {
		Notification.show(text, 5000, Position.BOTTOM_STRETCH).addThemeVariants(NotificationVariant.LUMO_ERROR);
	}

}
