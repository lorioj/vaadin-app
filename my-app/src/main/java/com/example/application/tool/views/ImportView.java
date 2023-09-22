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
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
public class ImportView extends VerticalLayout implements Receiver{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3581336225363787982L;

	private Anchor anchorDownloadTemplate = new Anchor("templates/Employee_Input.xlsx",
			new Button("Download Template", VaadinIcon.DOWNLOAD.create()));
	private Upload upload = new Upload(this);
//
	private Button buttonImport = new Button("Import", VaadinIcon.UPLOAD.create());

	private ByteArrayOutputStream os = new ByteArrayOutputStream();

	private NumberFormat numFormat = new DecimalFormat("0");
	
	private List<ImportEntity> list = new ArrayList<>();
	private Grid<ImportEntity> grid = new Grid<ImportEntity>(ImportEntity.class);
	
	@Autowired
	private UserService userService;

	public ImportView() {
		add(anchorDownloadTemplate);
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
//					Cell cellEmployeeNo = row.getCell(0); // A
					Cell cellFirstName = row.getCell(1); // B
//					Cell cellLastName = row.getCell(2); // C
////					Cell cellPasscode = row.getCell(3); // D
////					Cell cellPassword = row.getCell(4); // E
//					Cell cellPlantCode = row.getCell(3); // F
//					Cell cellMainLineCode = row.getCell(4); // G
//					Cell cellSubLineCode = row.getCell(5); // H
//					Cell cellProductCode = row.getCell(6); // I

//					String employeeNo = getStringValue(cellEmployeeNo);
					String firstName = getStringValue(cellFirstName);
//					String lastName = getStringValue(cellLastName);
////					String passcode = getStringValue(cellPasscode);
////					String password = getStringValue(cellPassword);
//					String plant = getStringValue(cellPlantCode);
//					String mainLine = getStringValue(cellMainLineCode);
//					String subLine = getStringValue(cellSubLineCode);
//					String product = getStringValue(cellProductCode);

//					employees.add(ImportEmployee.builder().employeeNo(employeeNo).firstName(firstName)
//							.lastName(lastName).plantCode(plant).mainLineCode(mainLine).subLineCode(subLine)
//							.productCode(product).build());
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
