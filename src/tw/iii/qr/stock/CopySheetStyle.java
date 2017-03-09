package tw.iii.qr.stock;

import java.util.Iterator;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CopySheetStyle {
	public CopySheetStyle() {
	}

	// public void copyBookCellStyle(XSSFWorkbook fromBook,XSSFWorkbook toBook){
	// for(short i=0;i<fromBook.getNumCellStyles();i++){
	// XSSFCellStyle fromStyle=fromBook.getCellStyleAt(i);
	// XSSFCellStyle toStyle=toBook.getCellStyleAt(i);
	// if(toStyle==null){
	// toStyle=toBook.createCellStyle();
	// }
	// copyCellStyle(fromStyle,toStyle);
	// }
	// }
	/**
	 * 复制一个单元格样式到目的单元格样式
	 * 
	 * @param xssfCellStyle
	 * @param newstyle
	 */
	public void copyCellStyle(XSSFCellStyle xssfCellStyle, XSSFCellStyle newstyle) {
		newstyle.setAlignment(xssfCellStyle.getAlignment());
		// 边框和边框颜色
		newstyle.setBorderBottom(xssfCellStyle.getBorderBottom());
		newstyle.setBorderLeft(xssfCellStyle.getBorderLeft());
		newstyle.setBorderRight(xssfCellStyle.getBorderRight());
		newstyle.setBorderTop(xssfCellStyle.getBorderTop());
		newstyle.setTopBorderColor(xssfCellStyle.getTopBorderColor());
		newstyle.setBottomBorderColor(xssfCellStyle.getBottomBorderColor());
		newstyle.setRightBorderColor(xssfCellStyle.getRightBorderColor());
		newstyle.setLeftBorderColor(xssfCellStyle.getLeftBorderColor());

		// 背景和前景
		newstyle.setFillBackgroundColor(xssfCellStyle.getFillBackgroundColor());
		newstyle.setFillForegroundColor(xssfCellStyle.getFillForegroundColor());

		newstyle.setDataFormat(xssfCellStyle.getDataFormat());
		newstyle.setFillPattern(xssfCellStyle.getFillPattern());
		// toStyle.setFont(fromStyle.getFont(null));
		newstyle.setHidden(xssfCellStyle.getHidden());
		newstyle.setIndention(xssfCellStyle.getIndention());// 首行缩进
		newstyle.setLocked(xssfCellStyle.getLocked());
		newstyle.setRotation(xssfCellStyle.getRotation());// 旋转
		newstyle.setVerticalAlignment(xssfCellStyle.getVerticalAlignment());
		newstyle.setWrapText(xssfCellStyle.getWrapText());

	}

	/**
	 * Sheet复制
	 * 
	 * @param fromSheet
	 * @param toSheet
	 * @param copyValueFlag
	 */
	public void copySheet(XSSFWorkbook wb, XSSFSheet fromSheet, XSSFSheet toSheet) {
		// 合并区域处理
		mergerRegion(fromSheet, toSheet);
		for (Iterator rowIt = fromSheet.rowIterator(); rowIt.hasNext();) {
			XSSFRow tmpRow = (XSSFRow) rowIt.next();
			toSheet.setColumnWidth(tmpRow.getRowNum(), fromSheet.getColumnWidth(tmpRow.getRowNum()));
			XSSFRow newRow = toSheet.createRow(tmpRow.getRowNum());
			newRow.setHeightInPoints(tmpRow.getHeightInPoints());
			// 行复制
			copyRow(wb, tmpRow, newRow);
		}
	}

	/**
	 * 行复制功能
	 * 
	 * @param tmpRow
	 * @param newRow
	 */
	public void copyRow(XSSFWorkbook wb, XSSFRow tmpRow, XSSFRow newRow) {
		for (Iterator cellIt = tmpRow.cellIterator(); cellIt.hasNext();) {
			XSSFCell tmpCell = (XSSFCell) cellIt.next();

			XSSFCell newCell = newRow.createCell(tmpCell.getColumnIndex());

			copyCell(wb, tmpCell, newCell);
		}
	}

	/**
	 * 复制原有sheet的合并单元格到新创建的sheet
	 * 
	 * @param sheetCreat
	 *            新创建sheet
	 * @param sheet
	 *            原有的sheet
	 */
	public void mergerRegion(XSSFSheet fromSheet, XSSFSheet toSheet) {
		int sheetMergerCount = fromSheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergerCount; i++) {
			CellRangeAddress mergedRegionAt = fromSheet.getMergedRegion(i);
			toSheet.addMergedRegion(mergedRegionAt);
		}
	}

	/**
	 * 复制单元格
	 * 
	 * @param srcCell
	 * @param distCell
	 * @param copyValueFlag
	 *            true则连同cell的内容一起复制
	 */
	public void copyCell(XSSFWorkbook wb, XSSFCell srcCell, XSSFCell distCell) {
		XSSFCellStyle newstyle = wb.createCellStyle();
		copyCellStyle(srcCell.getCellStyle(), newstyle);
		//
		distCell.setCellStyle(newstyle);

		//

	}

}
