package com.example.application.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.example.application.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExportService extends AbstractExcelExport {

	public InputStream createExportEntityExcelList(List<User> list) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		
		Workbook wb = new XSSFWorkbook();
//		Sheet s1 = wb.createSheet("Users");
		XSSFSheet s1 = (XSSFSheet) wb.createSheet("SheetName");

		if (list.size() > 0) {

			Font font0 = wb.createFont();
			font0.setBold(true);
			font0.setColor(IndexedColors.WHITE.getIndex());

			CellStyle styleBold = wb.createCellStyle();
			styleBold.setFont(font0);

			CellStyle styleLeft = wb.createCellStyle();
			styleLeft.setAlignment(HorizontalAlignment.LEFT);

			CellStyle styleRight = wb.createCellStyle();
			styleRight.setAlignment(HorizontalAlignment.RIGHT);

			CellStyle styleCenter = wb.createCellStyle();
			styleCenter.setAlignment(HorizontalAlignment.CENTER);

			Font font1 = wb.createFont();
			font1.setColor(IndexedColors.WHITE.getIndex());

			String rgbS = "4472C4";//HEADER
			byte[] rgbB;
			XSSFColor color = null;
			
			String oddColor = "D9E1F2";
			byte[] rgbBOdd;
			XSSFColor colorOdd = null;
			
			String evenColor = "B4C6E7";
			byte[] rgbBeven;
			XSSFColor colorEven = null;
			try {
				rgbB = Hex.decodeHex(rgbS);
				color = new XSSFColor(rgbB, null);
				
				rgbBOdd = Hex.decodeHex(oddColor);
				colorOdd = new XSSFColor(rgbBOdd, null);
				
				rgbBeven = Hex.decodeHex(evenColor);
				colorEven = new XSSFColor(rgbBeven, null);
			} catch (DecoderException e) {
				log.error(e.getMessage(), e);
			} 
			
			XSSFCellStyle h = (XSSFCellStyle) wb.createCellStyle();
			h.setFillForegroundColor(color);
			h.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			h.setFont(font0);
			
			XSSFCellStyle oddStyle = (XSSFCellStyle) wb.createCellStyle();
			oddStyle.setFillForegroundColor(colorOdd);
			oddStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			XSSFCellStyle evenStyle = (XSSFCellStyle) wb.createCellStyle();
			evenStyle.setFillForegroundColor(colorEven);
			evenStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			DataFormat format = wb.createDataFormat();
			
			XSSFCellStyle oddStyleNumber = (XSSFCellStyle) wb.createCellStyle();
			oddStyleNumber.setFillForegroundColor(colorOdd);
			oddStyleNumber.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			oddStyleNumber.setDataFormat(format.getFormat("0.00000000000000000000"));
			
			XSSFCellStyle evenStyleNumber = (XSSFCellStyle) wb.createCellStyle();
			evenStyleNumber.setFillForegroundColor(colorEven);
			evenStyleNumber.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			evenStyleNumber.setDataFormat(format.getFormat("0.00000000000000000000"));
			
			int rowIdx = 0;
			Row row = null;
			String range;

			row = s1.createRow(rowIdx);
			row.createCell(0).setCellValue("Name");
			row.createCell(1).setCellValue("Value");

			for (int x = 0; x < 2; x++) {
				row.getCell(x).setCellStyle(h);
			}

			for (User entity : list) {
				rowIdx++;
				row = s1.createRow(rowIdx);
				row.createCell(0).setCellValue(entity.getName());
				row.createCell(1).setCellValue(0.023);
				
				if (rowIdx % 2 == 0) {
					row.getCell(0).setCellStyle(oddStyle);
					row.getCell(1).setCellStyle(oddStyleNumber);
				} else {
					row.getCell(0).setCellStyle(evenStyle);
					row.getCell(1).setCellStyle(evenStyleNumber);
				}
			}

			s1.setColumnWidth(1, 256 * 18);
			s1.setColumnWidth(1, 256 * 25);
			range = toAlphabetic(s1.getRow(0).getPhysicalNumberOfCells() - 1) + rowIdx;
			log.info("Row: " + (s1.getRow(0).getPhysicalNumberOfCells() - 1) + " Range: " + range);
			s1.setAutoFilter(CellRangeAddress.valueOf("A1:" + range));
//			s1.createFreezePane(0, 1);
//			s1.setZoom(90);

		}
		
		try {
			wb.write(os);
			wb.close();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return new ByteArrayInputStream(os.toByteArray());
	}
	
	public static String toAlphabetic(int i) {
		if (i < 0) {
			return "-" + toAlphabetic(-i - 1);
		}

		int quot = i / 26;
		int rem = i % 26;
		char letter = (char) ((int) 'A' + rem);
		if (quot == 0) {
			return "" + letter;
		} else {
			return toAlphabetic(quot - 1) + letter;
		}
	}

}
