package packageDeTravail;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.IOException;
import javax.ejb.Stateless;

@Stateless
public class Merge {
 
    public static final String[] FILE_A = {
        "./src/main/java/pdfs/pdf1.pdf",
        "./src/main/java/pdfs/pdf2.pdf",
        "./src/main/java/pdfs/pdf3.pdf"
    };
//    public static final String[] FILE_B = {
//            "./target/test/resources/sandbox/fonts/testB1.pdf", "./target/test/resources/sandbox/fonts/testB2.pdf",
//            "./target/test/resources/sandbox/fonts/testB3.pdf"
//    };
//    public static final String[] FILE_C = {
//            "./target/test/resources/sandbox/fonts/testC1.pdf", "./target/test/resources/sandbox/fonts/testC2.pdf",
//            "./target/test/resources/sandbox/fonts/testC3.pdf"
//    };
    public static final String[] CONTENT = {
            "abcdefgh", "ijklmnopq", "rstuvwxyz"
    };
    public static final String MERGED_A1 =
            "./src/main/java/pdfs/mergedA1";
    public static final String MERGED_A2 =
            "./src/main/java/pdfs/mergedA2";
//    public static final String MERGED_B1 =
//            "./target/test/resources/sandbox/fonts/testB_merged1.pdf";
//    public static final String MERGED_B2 =
//            "./target/test/resources/sandbox/fonts/testB_merged2.pdf";
//    public static final String MERGED_C1 =
//            "./target/test/resources/sandbox/fonts/testC_merged1.pdf";
//    public static final String MERGED_C2 =
//            "./target/test/resources/sandbox/fonts/testC_merged2.pdf";
 
    // we will check via CompareTool only one result file.
    public static final String DEST =
            MERGED_A1;
 
    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new Merge().manipulatePdf(DEST);
    }
 
    public void createPdf(String filename, String text, boolean embedded, boolean subset) throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(filename));
        try (Document doc = new Document(pdfDoc)) {
            doc.add(new Paragraph(text));
        }
    }
 
    public void mergeFiles(String[] files, String result, boolean isSmartModeOn) throws IOException {
        PdfWriter writer = new PdfWriter(result);
        writer.setSmartMode(isSmartModeOn);
        try (PdfDocument pdfDoc = new PdfDocument(writer)) {
            pdfDoc.initializeOutlines();
            for (String file : files) {
                try (PdfDocument addedDoc = new PdfDocument(new PdfReader(file))) {
                    addedDoc.copyPagesTo(1, addedDoc.getNumberOfPages(), pdfDoc);
                }
            }
        }
    }
 
    //@Override
    protected void manipulatePdf(String dest) throws Exception {
        Merge app = new Merge();
 
        for (int i = 0; i < FILE_A.length; i++) {
            app.createPdf(FILE_A[i], CONTENT[i], true, true);
        }
        app.mergeFiles(FILE_A, MERGED_A1, false);
        app.mergeFiles(FILE_A, MERGED_A2, true);
 
//        for (int i = 0; i < FILE_B.length; i++) {
//            app.createPdf(FILE_B[i], CONTENT[i], true, false);
//        }
//        app.mergeFiles(FILE_B, MERGED_B1, false);
//        app.mergeFiles(FILE_B, MERGED_B2, true);
// 
//        for (int i = 0; i < FILE_C.length; i++) {
//            app.createPdf(FILE_C[i], CONTENT[i], false, false);
//        }
//        app.mergeFiles(FILE_C, MERGED_C1, true);
    }
 
}