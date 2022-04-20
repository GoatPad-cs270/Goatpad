package finalPackage;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JTextArea;

public abstract class Print implements Printable {
	public static void printFile(JTextArea textArea)
	{
	  PrinterJob printJob = PrinterJob.getPrinterJob();
	  PageFormat format = printJob.defaultPage();
	  Paper paper = format.getPaper();
	  paper.setImageableArea(18, 0, 180, 840);// Paper format for receipt printer
	  format.setPaper(paper);
	  printJob.setPrintable( (Printable) format);
	  if (!( printJob.printDialog()))
	  {
	    try
	    {
	      printJob.print();
	    }
	    catch (PrinterException pe)
	    {
	      System.out.print("Error printing: " );
	    }
	  }
	}
}
