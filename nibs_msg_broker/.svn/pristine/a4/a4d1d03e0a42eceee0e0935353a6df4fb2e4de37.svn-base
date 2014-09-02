package com.nicetcm.nibsplus.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

public class ExcelRowParser {

    private HSSFSheet sheet = null;
    private ArrayList<String> arrHeader = null;
    private int headerSize;

    public ExcelRowParser(HSSFSheet sheet) {
        this.sheet = sheet;
        this.arrHeader = new ArrayList<String>();
    }

    public ExcelRowParser(HSSFSheet sheet, int headerSize) {
        this.sheet = sheet;
        this.arrHeader = new ArrayList<String>();
        this.headerSize = headerSize;
    }

    public List<Map<String, String>> getArrSheet(int firstDataNum) throws Exception {
        List<Map<String, String>> returnResult = new ArrayList<Map<String, String>>();

        //BLoger.debug(classObj,"sheet.getLastRowNum(): " + sheet.getLastRowNum());

        Iterator iter = this.sheet.rowIterator();


        for (int i = 0; iter.hasNext(); i++) {
            //BLoger.debug(classObj,"i: " + i);
            HSSFRow row = (HSSFRow) iter.next();
            Iterator cellIter = row.cellIterator();
            Map<String, String> mapRow = new HashMap<String, String>();

            boolean isReadDataCell = false;

            for (int j = 0; cellIter.hasNext(); j++) {
//                BLoger.debug(classObj,"jjj: " + j);
                HSSFCell cell = (HSSFCell) cellIter.next();

                //데이터가 몇라인부터 시작하는지 설정한다.
                if(i >= firstDataNum - 1) {
                    isReadDataCell = true;
                } else {
                    isReadDataCell = false;
                }


                if(i == 0) {
                    //BLoger.debug(classObj,cell.getCellType());
                    arrHeader.add(this.getCellValue(cell)); // 헤더 문자열 저장
                    //BLoger.debug(classObj,this.getCellValue(cell));
                    //System.out.println(this.getCellValue(cell));
                }

                //공백CELL을 만날경우 결과물을 return하고 종료한다.
//                BLoger.debug(classObj,"this.getCellValue(cell): " + this.getCellValue(cell));
                if((j == 0 && "".equals(this.getCellValue(cell))) || (j == 0 && "KF_JBF_END".equals(this.getCellValue(cell)))) {
//                    BLoger.debug(classObj,"공백CELL을 만날경우 결과물을 return하고 종료한다.");
                    return returnResult;
                }

                if(isReadDataCell){
                    //CELL데이터 저장
                    //BLoger.debug(classObj,"헤더 사이즈" + arrHeader.size());
                    if(j < arrHeader.size()) { //헤더열 이상의 CELL은 무시한다.
                    //if(j < headerSize) { //헤더열 이상의 CELL은 무시한다.
                        //System.out.println("[" + cell.getCellType() + "] " + this.getCellValue(cell));
                        mapRow.put(arrHeader.get(j), this.getCellValue(cell)); // 값 문자열 저장
                    }
                }
            }

            if(isReadDataCell) {
                //로우데이터 저장
//                BLoger.debug(classObj,"add");
                returnResult.add(mapRow);
            }
        }

        return returnResult;

    }// end getArrSheet()

    private String getCellValue(HSSFCell cell) throws Exception {
        String value = "";

        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_FORMULA:
//            value = cell.getCellFormula().trim();
            //System.out.println(cell.getRichStringCellValue());
            value = cell.getRichStringCellValue().toString().trim();
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            value = String.valueOf(Math.floor(cell.getNumericCellValue()));
            if(value.endsWith(".0")) {
                value = value.substring(0,(value.length() -2 ));
            }
            break;
        case HSSFCell.CELL_TYPE_STRING:
            value = cell.getRichStringCellValue().toString().trim();
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            value = "";
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            value = String.valueOf(cell.getBooleanCellValue());
            throw new Exception("BOOLEAN cell error!");
        case HSSFCell.CELL_TYPE_ERROR:
            value = String.valueOf(cell.getErrorCellValue());
            throw new Exception("ERROR cell error!");
        default: throw new Exception("default cell error!");
        }

        return value;
    }

}
