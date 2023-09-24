package com.example.application.tool.views;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.application.model.User;
import com.example.application.service.ExportService;
import com.example.application.service.UserService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PageTitle("Export")
@Route(value = "export", layout = MainLayout.class)
public class ExportView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8963108169012098824L;
	private Button buttonExport = new Button("Export", VaadinIcon.UPLOAD.create());

	private Anchor downloaderList;

	private List<User> listUser;

	@Autowired
	private ExportService exportService;

	@Autowired
	private UserService userService;

	public ExportView() {

		downloaderList = new Anchor();
		downloaderList.add(buttonExport);
		buttonExport.addClickListener(o -> {
			loadResults();
		});

		initializedDownloaders();
		add(downloaderList);

	}

	@PostConstruct
	private void init() {
		loadResults();
		log.info(listUser.toString());
	}
	
	private void loadResults() {
		listUser = userService.findAll();
	}

	private void initializedDownloaders() {
		downloaderList.setHref(createStreamResource());
	}

	private StreamResource createStreamResource() {
		String filename = "Users" + ".xlsx";
		StreamResource resource = new StreamResource(filename, () -> createInputStream());
		return resource;
	}

	private InputStream createInputStream() {
		return exportService.createExportEntityExcelList(listUser);
	}

	

}
