package com.github.dmtk.utils;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import com.github.dmtk.entity.Measurement;
import java.util.List;


public class ExcelExport {
    
    
        
          
    public File exportExperiments (List<Measurement> rows) throws IOException {
        return writeExperiments(createNewFile(), rows);
    }
    
        
    private File createNewFile () {
        String directory = "mytemp";
        if (!(Files.isDirectory(Paths.get(directory)))) {
            new File(directory).mkdirs();
        }

        String time = ((Long) Calendar.getInstance().getTimeInMillis()).toString();
        String filePath = directory + File.separator + "Report" + time + ".xls"; 
        return new File(filePath);
    }
        
    public File writeExperiments(File f, List<Measurement> report) throws IOException {

        HSSFWorkbook workbook = null;
        HSSFSheet sheet = null;

        if (f.exists()) {
            f.delete();
            f.createNewFile();
        }

        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet("Events");
        writeExperimentsTable(sheet, report);

        return writeExcelFile(f, workbook);
    }
    
    
    private File writeExcelFile (File file, HSSFWorkbook workbook) throws IOException{
                
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            workbook.write(out);
            return file;
        } finally {
            if (out != null) {
                try {
                out.close();
                } catch (IOException e){
                    //TODO 
                    
                }
            }
        }
    }

    
        
    private void writeExperimentsTable(Sheet sheet, List<Measurement> experiments) {
        Row firstRow = sheet.createRow(0);
        firstRow.createCell(0).setCellValue("Network events report");
        firstRow.createCell(4).setCellValue("File was created by System : ");
        firstRow.createCell(8).setCellValue(dateToString(Calendar.getInstance().getTime()));
        
        Row secRow = sheet.createRow(1);
        secRow.createCell(0).setCellValue("Events");
        
        Row tableHeader = sheet.createRow(2);
        tableHeader.createCell(0).setCellValue("Id");
        tableHeader.createCell(1).setCellValue("Value");
        tableHeader.createCell(2).setCellValue("Date");
        int rowNum = 3;
        for (Iterator<Measurement> it = experiments.iterator(); it.hasNext();) {
            Measurement dataRow = it.next();
            Row row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(dataRow.getId());
            row.createCell(1).setCellValue(dataRow.getValue());
            row.createCell(2).setCellValue(dateToString(dataRow.getDate()));
            rowNum++;
        }
    }

    public String dateToString(Date date) { //maybe private?
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.US);
        return dateFormat.format(date);
    }

}

