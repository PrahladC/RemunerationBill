import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JTable;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Rem_CreatPdf {
	
	public String GetData(JTable table, int row_index, int col_index) {  return (String) table.getValueAt(row_index, col_index); }

//	FormFill FF = new FormFill();
	
	public void creatPdf(){
		Document document = new Document();
		try{
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Remuneration Bill.pdf"));
			document.open();
			
			PdfPTable table = new PdfPTable(1);
			table.setWidthPercentage(100);
			table.setSpacingBefore(5f);
			table.setSpacingAfter(5f);
			float[] colWidth0 = {5f};
			table.setWidths(colWidth0);
			Font bold = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD);

			Paragraph p = new Paragraph("\n\nMAHARASHTRA STATE BOARD OF SECONDARY & HIGHER SECONDARY EDUCATION\n" +
					                      "MUMBAI DIVISIONAL BOARD, VASHI, NAVIMUMBAI  400703\n" +
					                      "H.S.C. PRACTICAL EXAMINATION FEBRUARY/OCTOBER\nBILL OF REMUNERATION OF INTERNAL/EXTERNAL EXAMINER", bold);
//			p.getFont().setSize(14);
			PdfPCell c0 = new PdfPCell(p);
			c0.setHorizontalAlignment(Element.ALIGN_CENTER);
			c0.setBorder(Rectangle.NO_BORDER);
			table.addCell(c0);
			document.add(table);			
		    PdfContentByte canvas = writer.getDirectContent();
		    CMYKColor blackColor = new CMYKColor(0.f, 0.f, 0.f, 1.f);
		    canvas.setColorStroke(blackColor);		        

//		    canvas.moveTo(559, 806);
//	        canvas.lineTo(559, 786);
//	        canvas.lineTo(450, 786);
//	        canvas.lineTo(450, 806);		        

	        Rectangle rect1 = new Rectangle(475, 786, 559, 806);
	        Rectangle rect0 = new Rectangle(36, 36, 559, 806);
		    rect0.setBorder(Rectangle.BOX);
	        rect0.setBorderWidth(1);
		    rect1.setBorder(Rectangle.BOX);
	        rect1.setBorderWidth(1);
	        canvas.rectangle(rect0);
	        canvas.rectangle(rect1);
	        ColumnText ct = new ColumnText(canvas);
	        ct.setSimpleColumn(476, 786, 559, 810);
	        ct.addElement(new Paragraph(" C - 2 / P2 - 13"));
	        ct.go();
	        
	        String address[] = {"The Divisional Secretary,","Maharashtra State Board of Secondary","& Higher Secondary Education",
		              "Mumbai Divisional Board,", "Vashi, Navi Mumbai  400703"};

	        for (int i = 0; i < address.length; i++) {
	            document.add(new Paragraph("   "+address[i]));
	        }
	        document.add(new Paragraph("\n   Name Shri/Smt/Miss"));
	        canvas.moveTo(160, 590);
		    canvas.lineTo(559, 590);
		    document.add(new Paragraph("\n   Name Shri/Smt/Miss"));
		    rect0.setBorder(Rectangle.BOX);
	        rect0.setBorderWidth(1);
	        canvas.rectangle(rect0);
	        
	        canvas.closePathStroke();       			
			document.close();
			writer.close();
		}
		catch (DocumentException e){
			e.printStackTrace();
		}
		
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}

		
	}
