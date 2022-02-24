package com.github.pjfanning.poi.sample;

import com.github.pjfanning.xlsx.SharedStringsImplementationType;
import com.github.pjfanning.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;

public class StreamingRead {

    public static void main(String[] args) throws Exception {
        String filename = null;
        if (args.length < 1) {
            System.out.println("To read your own file, use:");
            System.out.println("  StreamingRead <xlsx file>");
            System.out.println();
            filename = "Sample-Sales-Data.xlsx";
            System.out.println("Using " + filename + " as default input");
        } else {
            filename = args[0];
        }

        File xlsxFile = new File(filename);
        if (!xlsxFile.exists()) {
            System.err.println("Not found or not a file: " + xlsxFile.getPath());
            return;
        }

        try (Workbook workbook = StreamingReader.builder()
                .rowCacheSize(100)
                .bufferSize(4096)
                .setSharedStringsImplementationType(SharedStringsImplementationType.TEMP_FILE_BACKED)
                .setEncryptSstTempFile(true)
                .open(xlsxFile)) {
            for (Sheet sheet : workbook) {
                System.out.println("Sheet: " + sheet.getSheetName());
                for (Row r : sheet) {
                    for (Cell c : r) {
                        System.out.print('"');
                        System.out.print(c.getStringCellValue());
                        System.out.print("\",");
                    }
                    System.out.println();
                }
            }
        }
    }
}