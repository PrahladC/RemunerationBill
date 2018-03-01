import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Rem_CreatPdf {
	
	public String GetData(JTable table, int row_index, int col_index) {  return (String) table.getValueAt(row_index, col_index); }
	public void show(String msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging
	
	FormFill FF;
	
	public Rem_CreatPdf(FormFill ff){
		this.FF = ff;
	}
	
	public void creatPdf(){
//		FF = new FormFill();
		try{
			
			int ltmargin = 36, ht = 25, Line1AtHt = 585;
			double amount = 0, total = 0;
		    String nose = FF.NOSE.getText();
		    String remperstu = FF.RemPerStu.getText();
		    if(remperstu.isEmpty() || nose.isEmpty()) { 
		       amount = 0; 
		    }
		    else amount = Double.valueOf(remperstu)*Double.valueOf(nose);
		    total = Math.ceil(amount);
            String Total = String.valueOf(total);
            
			Document document = new Document();
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
					                      "H.S.C. PRACTICAL EXAMINATION FEBRUARY/JULY\nBILL OF REMUNERATION OF INTERNAL/EXTERNAL EXAMINER", bold);
			PdfPCell c0 = new PdfPCell(p);
			c0.setHorizontalAlignment(Element.ALIGN_CENTER);
			c0.setBorder(Rectangle.NO_BORDER);
			table.addCell(c0);
			document.add(table);			
		    PdfContentByte canvas = writer.getDirectContent();
		    CMYKColor blackColor = new CMYKColor(0.f, 0.f, 0.f, 1.f);
		    canvas.setColorStroke(blackColor);		        

/*	        Rectangle rect0 = new Rectangle(36, 36, 559, 806);
		    rect0.setBorder(Rectangle.BOX);
	        rect0.setBorderWidth(1);
		    rect0.setBorder(Rectangle.BOX);
	        rect0.setBorderWidth(1);
	        canvas.rectangle(rect0);                */

	        Rectangle rect1 = new Rectangle(475, 786, 559, 806);
		    rect1.setBorder(Rectangle.BOX);
	        rect1.setBorderWidth(1);
	        canvas.rectangle(rect1);
	        ColumnText ct = new ColumnText(canvas);
	        ct.setSimpleColumn(476, 786, 559, 810);
	        ct.addElement(new Paragraph(" C - 2 / P2 - 13"));
	        ct.go();
	        
	        Rectangle rect2 = new Rectangle(ltmargin+45, Line1AtHt-18*ht+5, ltmargin+120, Line1AtHt-15*ht);
		    rect2.setBorder(Rectangle.BOX);
	        rect2.setBorderWidth(1);
		    rect2.setBorder(Rectangle.BOX);
	        rect2.setBorderWidth(1);
	        canvas.rectangle(rect2);
	        
	        String address[] = {"The Divisional Secretary,","Maharashtra State Board of Secondary","& Higher Secondary Education",
		              "Mumbai Divisional Board,", "Vashi, Navi Mumbai  400703"};
	        Font font = new Font(FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
	        for (int i = 0; i < address.length; i++) {
	            document.add(new Paragraph("   "+address[i], font));
	        }
	        String line1 = "Name Shri/Smt/Miss";  int lengthline1 = line1.length();
	        String line2 = "Subject";
	        String line3 = "Practical Examination February / July";
	        String line4 = "at the";
	        String line5 = "IndexNo.of Jr.College"; 
	        String line6 = "Amount due, to me as an EXTERNAL/INTERNAL Examiner";
	        String line7 = "at the Examination center-Number ofdays of examination";	        
	        String line8 = "Actual Total number of candidates examined by me";
	        String line9 = "Excluding Absentees";
	        String line10 = "Rs.  "+remperstu+" per candidate.      "+nose + "  x  "+ remperstu+"  =  "+ amount ; 
	        String line11 = "(Minimum of Rs.50/- irrespective of the number of candidates)";
	        String line12 = "I hereby undertake to refund if any amount paid to me in excess of the amount due";
	        String line13 = "Name of Jr. College where teaching";
	        String line14 = "certified that the Examiner has actually examined the No. of candidates mentioned above";
	        String line15 = "counter signature of the Head of the institution with stamp";
	         	        
	        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(line1, font), ltmargin+10, Line1AtHt, 0);
	        canvas.moveTo(170, Line1AtHt-2);    canvas.lineTo(559, Line1AtHt-2);
		    
		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(line2, font), ltmargin+10, Line1AtHt-ht, 0);
		    canvas.moveTo(90, Line1AtHt-ht-2);    canvas.lineTo(250, Line1AtHt-ht-2);

		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(line3, font), ltmargin+10, Line1AtHt-2*ht, 0);
		    canvas.moveTo(260, Line1AtHt-2*ht-2);    canvas.lineTo(320, Line1AtHt-2*ht-2);

		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(line4, font), ltmargin+10, Line1AtHt-3*ht, 0);
		    canvas.moveTo(80, Line1AtHt-3*ht-2);    canvas.lineTo(345, Line1AtHt-3*ht-2);

		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(line5, font), 350, Line1AtHt-3*ht, 0);
		    canvas.moveTo(475, Line1AtHt-3*ht-2);    canvas.lineTo(559, Line1AtHt-3*ht-2);
		    
		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(line6, font), ltmargin+10, Line1AtHt-11*ht/2, 0);
		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(line7, font), ltmargin+10, Line1AtHt-13*ht/2, 0);
		    canvas.moveTo(370, Line1AtHt-(13*ht/2)-2);    canvas.lineTo(400, Line1AtHt-(13*ht/2)-2);
		    
		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(line8, font), ltmargin+10, Line1AtHt-15*ht/2, 0);
		    canvas.moveTo(350, Line1AtHt-(15*ht/2)-2);    canvas.lineTo(400, Line1AtHt-(15*ht/2)-2);
		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(line9, font), ltmargin+10, Line1AtHt-17*ht/2, 0);
		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(line10, font), ltmargin+10, Line1AtHt-19*ht/2, 0);
		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(line11, font), ltmargin+10, Line1AtHt-21*ht/2+5, 0);
		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(line12, font), ltmargin+10, Line1AtHt-25*ht/2, 0);
		   		    
		    canvas.moveTo(ltmargin, Line1AtHt-4*ht);        canvas.lineTo(559, Line1AtHt-4*ht);
		    canvas.moveTo(ltmargin, Line1AtHt-5*ht+5);    canvas.lineTo(559, Line1AtHt-5*ht+5);
		    canvas.moveTo(ltmargin, Line1AtHt-11*ht);      canvas.lineTo(559, Line1AtHt-11*ht);
		    canvas.moveTo(ltmargin, Line1AtHt-12*ht+5);   canvas.lineTo(559, Line1AtHt-12*ht+5);
		    canvas.moveTo(400, Line1AtHt-4*ht);           canvas.lineTo(400, Line1AtHt-12*ht+5);     //  Vertical Line
		    canvas.moveTo(360, Line1AtHt-11*ht);           canvas.lineTo(360, Line1AtHt-12*ht+5);  //  Small Vertical Line
		    canvas.moveTo(325, Line1AtHt-14*ht+5);   canvas.lineTo(559, Line1AtHt-14*ht+5);   //  Above signature
		    int k = 0 ;
		    for(int i = 0; i < 4; i++){
		    	canvas.moveTo(325, (Line1AtHt-(15+i)*ht-5)+(k*5));   canvas.lineTo(559, (Line1AtHt-(15+i)*ht-5)+(k*5));   //  address line 1	
		    	k++;                                                                             //  address 4 lines
		    }
		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(line13, font), ltmargin+10, Line1AtHt-19*ht, 0);  
		    canvas.moveTo(250, Line1AtHt-19*ht-2);   canvas.lineTo(559, Line1AtHt-19*ht-2);

		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(line5, font), ltmargin+10, Line1AtHt-20*ht, 0);  
		    canvas.moveTo(175, Line1AtHt-20*ht-2);   canvas.lineTo(275, Line1AtHt-20*ht-2);
		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(line14, font), ltmargin+10, Line1AtHt-21*ht+5, 0);  
		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(line15, font), ltmargin+10, Line1AtHt-22*ht+10, 0);  		    
		    
		    ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, new Paragraph("Particulars", font), 210, Line1AtHt-5*ht+10, 0);
		    ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, new Paragraph("Amount", font), 475, Line1AtHt-5*ht+10, 0);
		    ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, new Paragraph("Total", font), 380, Line1AtHt-12*ht+10, 0);
		    ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, new Paragraph("Full Postal", font), 260, Line1AtHt-15*ht-10, 0);
		    ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, new Paragraph("Residential Address", font), 260, Line1AtHt-31*ht/2-10, 0);

/////    A  L  L    S  M  A  L  L    F  O  N  T     W  O  R  D  S		  
		    
	        Font font2 = new Font(FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
            String str1 = "( In capital Letters )";
            String str2 = "( Place of Examination )";
            String str3 = "( In Words )";
            String str4 = "( In Figure )";
            String str5 = "Recieved Payment";
            String str6 = "Signature of Payee";
            String revenue[] = {"On revenue stamp","when the amount","exceeds Rs.5000/-"};
            String str7 = "( Signature )";

            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(str1, font2), 5*ltmargin, Line1AtHt-12, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(str2, font2), 5*ltmargin, Line1AtHt-3*ht-12, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(str3, font2), ltmargin, Line1AtHt-11*ht+5, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(str4, font2), 14*ltmargin+5, Line1AtHt-11*ht+5, 0);		  
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(str5, font2), ltmargin+45, Line1AtHt-15*ht+5, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(str6, font2), ltmargin+45, Line1AtHt-18*ht-5, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(str7, font2), 425, Line1AtHt-14*ht-5, 0);
            
            int r = 0; 
	          for( int i = 0; i < revenue.length; i++)
	          {
	            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(revenue[i], font2), ltmargin+45, (Line1AtHt-16*ht+5)-r, 0);
	            r = r+15;
	          }                                 

/////   ALL  DATA  FROM  FORM  FILL  From  Here	          
	          
		    Font font1 = new Font(FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
		    String examinerName = FF.ExaminerName.getText().toUpperCase();     //   Examiner's Name
		    String sub = FF.subject.getText().toUpperCase();                                 //   Subject
		    String yr = FF.year.getText();
		    String coe = FF.COE.getText();
		    String indexofec = FF.IndexOfEC.getText();
		    String node = FF.NODE.getText();
		    String[] AddLines = { FF.AddLine1.getText(), FF.AddLine2.getText(), FF.AddLine3.getText(), FF.AddLine4.getText() };
            String collnemofexaminer = FF.CollNemOfExaminer.getText();
            String collindexnum = FF.ColIndCdOfExmner.getText();
            		    
		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(examinerName, font1), 175, Line1AtHt, 0);
		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(sub, font1), 95, Line1AtHt-ht, 0);
		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(yr, font1), 265, Line1AtHt-2*ht, 0);
		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(coe, font1), 85, Line1AtHt-3*ht, 0);
		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(indexofec, font1), 480, Line1AtHt-3*ht, 0);
		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(node, font1), 380, Line1AtHt-(13*ht/2), 0);
		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(nose, font1), 360, Line1AtHt-(15*ht/2), 0);   
//		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(remperstu, font1), ltmargin+30, Line1AtHt-19*ht/2, 0);   
		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(Total, font1), 450, Line1AtHt-6*ht, 0);   
		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(Total, font1), 450, Line1AtHt-12*ht+10, 0);   
	
		    for(int i = 0; i < 4; i++){
			    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(AddLines[i], font1), 330, (Line1AtHt-14*ht+18)-r, 0);
			    r = r+ 20;                                             //  address  lines
		    }
		    
		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(collnemofexaminer, font1), 255, Line1AtHt-19*ht, 0);   
		    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Paragraph(collindexnum, font1), 177, Line1AtHt-20*ht, 0);
		    	         
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
