package koem.com.cmm.util;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.lowagie.text.BadElementException;
//import com.lowagie.text.Cell;
//import com.lowagie.text.Document;
//import com.lowagie.text.DocumentException;
//import com.lowagie.text.Element;
//import com.lowagie.text.Font;
//import com.lowagie.text.Paragraph;
//import com.lowagie.text.Table;
//import com.lowagie.text.pdf.BaseFont;
//import com.lowagie.text.pdf.PdfPCell;
//import com.lowagie.text.pdf.PdfPTable;
//import com.lowagie.text.pdf.PdfWriter;

public class StringUtil {
	
	@SuppressWarnings("static-access")
	public static String right(String strValue, int intRightLength) {
		int length = strValue.length();
		
		if(length > intRightLength)
		{
			strValue = strValue.substring((length - intRightLength));
		}
			
		return strValue;
	}
/*	
	public static Paragraph toKor(int fontsize, String orig)
	{
		Font FontKorean = null;
		BaseFont bfKorean = null;
		
		try {
			bfKorean = BaseFont.createFont("HYGoThic-Medium", "UniKS-UCS2-H", BaseFont.CACHED);
			FontKorean = new Font(bfKorean, fontsize, Font.NORMAL); 
			
		} catch (DocumentException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
		
		return new Paragraph(orig,FontKorean);
	}
	
	public static Paragraph toKor(String orig)
	{
		Font FontKorean = null;
		BaseFont bfKorean = null;
		
		try {
			bfKorean = BaseFont.createFont("HYGoThic-Medium", "UniKS-UCS2-H", BaseFont.CACHED);
			FontKorean = new Font(bfKorean, 10, Font.NORMAL); 
			
		} catch (DocumentException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
		
		return new Paragraph(orig,FontKorean);
	}
	
	public static Cell getCell(String name)
	{
		Cell c = null;
		try {
			c = new Cell(StringUtil.toKor(name));
		} catch (BadElementException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public static Cell getCell(String name , String align)
	{
		Cell c = null;
		try {
			c = new Cell(StringUtil.toKor(name));
			c.setVerticalAlignment(Element.ALIGN_MIDDLE);
			if("R".equals(align)){
				c.setHorizontalAlignment(Element.ALIGN_RIGHT);
			}
			if("C".equals(align)){
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
			}
			if("L".equals(align)){
				c.setHorizontalAlignment(Element.ALIGN_LEFT);
			}
			
		} catch (BadElementException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public static PdfPCell getPCell(String name)
	{
		PdfPCell c = null;
		try {
			c = new PdfPCell(StringUtil.toKor(name));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	
	
	public static DataOutput Write(Document document, ByteArrayOutputStream buffer,
			HttpServletRequest request, HttpServletResponse response)
	{
		DataOutput output = null;
		
		try{
			output = new DataOutputStream( response.getOutputStream() );
			
			byte[] bytes = buffer.toByteArray();
			response.setContentLength(bytes.length);
			for( int i = 0; i < bytes.length; i++ ) { output.writeByte( bytes[i] ); }
			
			response.flushBuffer();
		}
		catch(Exception e)
		{
			
		} 
		return output;
	}
	
	public static Table PrintTable(int rows, int cols, String align,
			int width, int padding, int spacing, int border, Color color)
	{
		Table t = null;
		try {
			t = new Table(cols,rows);
			t.setAlignment(align);
			t.setWidth(width);
			
			t.setBorderColor(color);
			t.setPadding(padding);
			t.setSpacing(spacing);
			t.setBorderWidth(border);
			
		} catch (BadElementException e) {
			
			e.printStackTrace();
		}
		return t;
	}
	
	public static PdfPTable PrintTable(int cols, int align,
			 float width)
	{
		PdfPTable t = null;
		try {
			t = new PdfPTable(cols);
			t.setHorizontalAlignment(align);
			t.setTotalWidth(width);			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return t;
	}	
	
	public static PdfPTable printPTHeader(PdfPTable t, String headers[] )
	{
		for(int i=0;i<headers.length;i++)
		{
			PdfPCell c1;
			try {
				c1 = new PdfPCell(StringUtil.toKor(headers[i]));
				
				t.addCell(c1);
				

			} catch (Exception e) {
				e.printStackTrace();
			}
			

		}
		
		return t;
	}	
	
	public static Table printTHeader(Table t, String headers[] )
	{
		for(int i=0;i<headers.length;i++)
		{
			Cell c1;
			try {
				c1 = new Cell(StringUtil.toKor(headers[i]));

				if(i==0)
				c1.setHeader(true);
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				t.addCell(c1);
				
				if(i==(headers.length-1))
					t.endHeaders();	
			} catch (BadElementException e) {
				e.printStackTrace();
			}
			

		}
		
		return t;
	}
	
	public static ByteArrayOutputStream getBuffer(Document document)
	{
		//Document document = new Document();
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance( document, buffer );
		} catch (DocumentException e) {
			
			e.printStackTrace();
		}
		
		return buffer;
	}
*/	
}
