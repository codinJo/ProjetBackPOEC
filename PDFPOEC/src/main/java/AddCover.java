/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.itextpdf.forms.PdfPageFormCopier;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
 
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.Stateless;
/**
 *
 * @author imperath
 */
@Stateless
public class AddCover {
    public static final String DEST
            = "./src/main/java/pdfs/add_cover4.pdf";
    public static final String COVER
            = "./src/main/java/pdfs/couverture.pdf";
    public static final String RESOURCE
            = "./src/main/java/pdfs/test.pdf";
    
    public void addCover() throws IOException {
        System.out.println("youpi ca va dedans !!!");
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddCover().manipulatePdf(DEST);
    }
     
    public void manipulatePdf(String dest) throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(RESOURCE), new PdfWriter(dest));
        PdfDocument cover = new PdfDocument(new PdfReader(COVER));
        cover.copyPagesTo(1, 1, pdfDoc, 1, new PdfPageFormCopier());
        cover.close();
        pdfDoc.close();
    }
}
