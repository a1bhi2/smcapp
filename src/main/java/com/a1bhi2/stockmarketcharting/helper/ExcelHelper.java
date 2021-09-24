package com.a1bhi2.stockmarketcharting.helper;


import com.a1bhi2.stockmarketcharting.model.StockPriceEntity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Company Code", "Stock Exchange", "Price Per Share(in Rs)", "Date","Time" };
    static String SHEET = "StockPrice";


    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<StockPriceEntity> excelToStockPrice(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<StockPriceEntity> stockPrices = new ArrayList<StockPriceEntity>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                StockPriceEntity stockPrice = new StockPriceEntity();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            stockPrice.setCompanyCode((String) currentCell.getStringCellValue());
                            break;

                        case 1:
                            stockPrice.setStockExchange(currentCell.getStringCellValue());
                            break;

                        case 2:
                            stockPrice.setCurrentPrice((double) currentCell.getNumericCellValue());
                            break;

                        case 3:
                            Date  date = currentCell.getDateCellValue();

                            stockPrice.setDate(date);
                            break;

                        case 4:
                            stockPrice.setTime(currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                stockPrices.add(stockPrice);
            }

            workbook.close();

            return stockPrices;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
