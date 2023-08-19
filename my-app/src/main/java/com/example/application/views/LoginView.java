package com.example.application.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.application.model.User;
import com.example.application.service.SystemService;
import com.example.application.util.VaadinUtil;
import com.example.application.views.helloworld.HelloWorldView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.PostConstruct;


@Route(value = "login")
@PageTitle("Login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver{
	
	private LoginForm loginForm = new LoginForm();
	
	private LoginI18n loginI18n = LoginI18n.createDefault();
	
	@Autowired
	private SystemService systemService;
	
	public LoginView() {
		addClassName("login-page");
		add(loginForm);
		
		loginForm.setForgotPasswordButtonVisible(false);
		loginForm.addLoginListener(event -> {
			try {
				loginForm.setError(false);
				User u = systemService.authenticate(event.getUsername(), event.getPassword());
				
				VaadinUtil.setUser(u);
				
				UI.getCurrent().navigate(HelloWorldView.class);
			} catch (RuntimeException e) {
				loginForm.setError(true);
			}
		});
		
		setSizeFull();
		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER);
	}
	
	@PostConstruct
	private void init() {
		loginI18n.getForm().setTitle("Login Page");
		loginForm.setI18n(loginI18n);
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		User u = VaadinUtil.getUser();
		if (u != null) {
			event.forwardTo(HelloWorldView.class);
		}
	}

}
