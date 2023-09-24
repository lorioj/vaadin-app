package com.example.application.tool.views;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.application.helper.ImportEntity;
import com.example.application.service.UserService;
import com.example.application.util.VaadinUtil;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Receiver;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Import")
@Route(value = "import", layout = MainLayout.class)
public class ImportView extends VerticalLayout implements Receiver {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3581336225363787982L;

	private Upload upload = new Upload(this);

	private Button buttonImport = new Button("Import", VaadinIcon.UPLOAD.create());

	private ByteArrayOutputStream os = new ByteArrayOutputStream();

	private NumberFormat numFormat = new DecimalFormat("0");

	private List<ImportEntity> list = new ArrayList<>();
	private Grid<ImportEntity> grid = new Grid<ImportEntity>(ImportEntity.class);

	@Autowired
	private UserService userService;

	public ImportView() {
		add(upload);
		add(new Label("Data Preview"));
		addAndExpand(grid);
		add(buttonImport);

		grid.removeAllColumns();
		grid.addThemeVariants(GridVariant.LUMO_COMPACT, GridVariant.LUMO_ROW_STRIPES);
		grid.addColumn("name").setHeader("First Name (B)");

		upload.setAcceptedFileTypes("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		upload.addSucceededListener(event -> {
			loadExcel();
		});

		buttonImport.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		buttonImport.setEnabled(false);
		buttonImport.addClickListener(event -> {
			runImport();
		});

	}

	private void runImport() {
		userService.findAll();
		grid.setItems(list);
		VaadinUtil.showSuccess("Import complete.");
		buttonImport.setEnabled(false);
	}

	private void loadExcel() {
		try {

			list.clear();

			InputStream is = new ByteArrayInputStream(os.toByteArray());
			Workbook workbook = new XSSFWorkbook(is);
			Sheet sheet = workbook.getSheetAt(0);

			for (int rowNum = sheet.getFirstRowNum(); rowNum <= sheet.getLastRowNum(); rowNum++) {
				if (rowNum == 0) {
//					log.warn("Skipping first row");
					continue; // skip first row
				}
				Row row = sheet.getRow(rowNum);

				if (row != null) {
					Cell cellFirstName = row.getCell(1); // B

					String firstName = getStringValue(cellFirstName);

				} else {
					// skip empty rows
//					log.warn("Skipping empty row");
				}
			}
			workbook.close();
			grid.setItems(list);
			buttonImport.setEnabled(true);
			VaadinUtil.showSuccess("Employee template loaded successfully.");
		} catch (IOException e) {
//			log.error(e.getMessage(), e);
			VaadinUtil.showError(e.getMessage());
		}
	}

	private String getStringValue(Cell cell) {
		if (cell != null) {
			if (cell.getCellType() == CellType.STRING) {
				return cell.getStringCellValue();
			}
			if (cell.getCellType() == CellType.NUMERIC) {
				return numFormat.format(cell.getNumericCellValue());
			}
		}
		return null;
	}

	@Override
	public OutputStream receiveUpload(String fileName, String mimeType) {
		os.reset();
		return os;
	}

}
