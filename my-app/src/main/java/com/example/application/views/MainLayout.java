package com.example.application.views;

import org.vaadin.lineawesome.LineAwesomeIcon;

import com.example.application.master.views.RabbitView;
import com.example.application.master.views.UserView;
import com.example.application.model.User;
import com.example.application.tool.views.ExportView;
import com.example.application.tool.views.ImportView;
import com.example.application.util.VaadinUtil;
import com.example.application.views.about.AboutView;
import com.example.application.views.helloworld.HelloWorldView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;

import jakarta.annotation.PostConstruct;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout implements BeforeEnterObserver, AfterNavigationObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private H2 viewTitle;

	private User user;

	public MainLayout() {
		setPrimarySection(Section.DRAWER);
		addDrawerContent();
		addHeaderContent();
	}

	private void addHeaderContent() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidthFull();
		layout.setSpacing(false);
		layout.setAlignItems(FlexComponent.Alignment.CENTER);

		DrawerToggle toggle = new DrawerToggle();
		toggle.setAriaLabel("Menu toggle");

		viewTitle = new H2();
		viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

		Button logout = new Button(null, VaadinIcon.SIGN_OUT.create());
		logout.addClassName("header-sign-out");
		logout.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		logout.addClickListener(event -> {
			VaadinUtil.closeSession();
			UI.getCurrent().getPage().reload();
		});

		layout.add(toggle);
		layout.addAndExpand(viewTitle);
		layout.add(logout);
		addToNavbar(true, layout);

	}

	private void addDrawerContent() {
		H1 appName = new H1("e-Leporidae");
		appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
		Header header = new Header(appName);

		Scroller scroller = new Scroller(createNavigation());

		addToDrawer(header, scroller, createFooter());
	}

	private SideNav createNavigation() {
		SideNav nav = new SideNav();

		nav.addItem(new SideNavItem("Hello World", HelloWorldView.class, LineAwesomeIcon.GLOBE_SOLID.create()));
		nav.addItem(new SideNavItem("About", AboutView.class, LineAwesomeIcon.FILE.create()));
		nav.addItem(new SideNavItem("Master Data"));
		nav.addItem(new SideNavItem("User", UserView.class, LineAwesomeIcon.USER.create()));
		nav.addItem(new SideNavItem("Rabbit", RabbitView.class, LineAwesomeIcon.ANDROID.create()));
		
		
		nav.addItem(new SideNavItem("Import", ImportView.class, LineAwesomeIcon.FILE_IMPORT_SOLID.create()));
		nav.addItem(new SideNavItem("Export", ExportView.class, LineAwesomeIcon.FILE_EXPORT_SOLID.create()));
		return nav;
	}

	private Footer createFooter() {
		Footer layout = new Footer();

		return layout;
	}

//    @Override
//    protected void afterNavigation() {
//        super.afterNavigation();
//        viewTitle.setText(getCurrentPageTitle());
//    }

	private String getCurrentPageTitle() {
		PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
		return title == null ? "" : title.value();
	}

	@PostConstruct
	private void init() {
		user = VaadinUtil.getUser();
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		if (user == null) {
			event.forwardTo(LoginView.class);
		} else {
		}
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		viewTitle.setText(getCurrentPageTitle());
	}
}
