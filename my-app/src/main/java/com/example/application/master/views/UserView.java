package com.example.application.master.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.application.model.User;
import com.example.application.service.UserService;
import com.example.application.util.VaadinUtil;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("User")
@Route(value = "user", layout = MainLayout.class)
public class UserView extends VerticalLayout implements AfterNavigationObserver {

	private HorizontalLayout layoutFilter = new HorizontalLayout();
	private ComboBox<User> fieldUser = new ComboBox<>("User");
	private TextField fieldSearch = new TextField("User");
	private Button buttonSearch = new Button("Search", VaadinIcon.SEARCH.create());

	private Grid<User> grid = new Grid<>();

	private HorizontalLayout layoutActions = new HorizontalLayout();
	private Button buttonNew = new Button("New", VaadinIcon.PLUS.create());

	private UserViewDialog dialogUser = new UserViewDialog();

	@Autowired
	private UserService userService;

	public UserView() {
		add(layoutFilter);
		addAndExpand(grid);
		add(layoutActions);

		layoutFilter.setAlignItems(Alignment.BASELINE);
		layoutFilter.add(fieldUser, fieldSearch, buttonSearch);
		
		buttonSearch.addClickListener(event -> {
			loadResult();
		});

		fieldUser.setClearButtonVisible(true);
		fieldUser.setPlaceholder("Any");
//		fieldUser.addValueChangeListener(event -> loadResult());

		fieldSearch.setWidth("300px");
		fieldSearch.setClearButtonVisible(true);
		fieldSearch.setPlaceholder("Name, Username");
		fieldSearch.setValueChangeMode(ValueChangeMode.LAZY);
//		fieldSearch.addValueChangeListener(event -> loadResult());

		grid.addThemeVariants(GridVariant.LUMO_COMPACT, GridVariant.LUMO_COLUMN_BORDERS, GridVariant.LUMO_ROW_STRIPES);
		grid.addColumn(o -> o.isActive() ? "🟢" : "🔴").setTextAlign(ColumnTextAlign.CENTER).setFlexGrow(0)
				.setHeader("Active").setWidth("60px");
		grid.addColumn(o -> o.getName()).setHeader("Name").setFlexGrow(0).setWidth("200px").setResizable(true);
		grid.addColumn(o -> o.getUsername()).setHeader("Username").setFlexGrow(0).setWidth("200px").setResizable(true);
		grid.addColumn(o -> o.getPassword()).setHeader("Password").setFlexGrow(0).setWidth("200px").setResizable(true);
		grid.addComponentColumn(o -> createActions(o)).setHeader("Actions").setFlexGrow(0).setAutoWidth(true).setResizable(true);

		buttonNew.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		buttonNew.addClickListener(event -> {
			dialogUser.setEntity(new User());
			dialogUser.open();
		});

		layoutActions.add(buttonNew);

	}

	private void loadResult() {
		grid.setItems(userService.findByName(getText()));
	}
	
	private String getText() {
		return fieldSearch.getValue() == null ? "%" : "%" + fieldSearch.getValue() + "%";
	}
	
	private Component createActions(User o) {
		HorizontalLayout layout = new HorizontalLayout();
		Button buttonEdit = new Button(VaadinIcon.EDIT.create());
		Button buttonDelete = new Button(VaadinIcon.TRASH.create());
		
		buttonEdit.addThemeVariants(ButtonVariant.LUMO_SMALL);
		buttonEdit.addClickListener(event -> {
			dialogUser.open();
			dialogUser.setEntity(o);
		});
		
		buttonDelete.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_ERROR);
		buttonDelete.addClickListener(event -> {
			userService.delete(o);
			VaadinUtil.showSuccess("Successfully deleted.");
			loadResult();
		});
		
		layout.setSpacing(false);
		layout.setMargin(false);
		layout.add(buttonEdit,buttonDelete);
		
		return layout;
	}
	
	
	
	

	private class UserViewDialog extends Dialog {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private VerticalLayout layoutRoot = new VerticalLayout();
		private FormLayout layoutForm = new FormLayout();

		private Binder<User> binder = new Binder<>(User.class);

		private TextField name = new TextField("Name");

		private TextField username = new TextField("Username");

		private TextField password = new TextField("Password");

		private Checkbox active = new Checkbox("Active");

		private Button buttonSave = new Button("Save");

		private User entity;

		public UserViewDialog() {
			add(layoutRoot);

			layoutRoot.add(new H3("User Details"), layoutForm);

			layoutForm.setWidth("300px");
			layoutForm.setResponsiveSteps(new ResponsiveStep("150px", 1), new ResponsiveStep("300px", 2));

			layoutForm.add(name, 2);
			layoutForm.add(username, 2);
			layoutForm.add(password, 2);
			layoutForm.add(active, 2);
			
			buttonSave.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
			buttonSave.addClickListener(event -> {
				onSave();
			});
			layoutRoot.add(buttonSave);

			binder.forMemberField(name).asRequired();
			binder.forMemberField(username).asRequired();
			binder.forMemberField(password).asRequired();
			binder.forMemberField(active);
			binder.bindInstanceFields(this);

		}

		private void setEntity(User entity) {
			this.entity = entity;
			binder.readBean(entity);
		}

		private void onSave() {
			try {
				binder.writeBean(entity);
				userService.save(entity);
				VaadinUtil.showSuccess("Successfully saved.");
				close();
				loadResult();
			} catch (ValidationException e) {
				VaadinUtil.showError("Please complete the required fields");
			} catch (Exception e) {
				VaadinUtil.showError(e.getMessage());
			}
		}

	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		fieldUser.setItems(userService.findAll());

		loadResult();

	}

}
