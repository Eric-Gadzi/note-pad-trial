import java.awt.Color;

import java.io.*;

import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.StyledEditorKit.ForegroundAction;

public class New_File_Editor extends JFrame implements ActionListener, fontsizeInterface, ColorInterface,FontTypeInterface {

	JMenuBar mBar;
	JMenuBar baseBar;
	
	JTextField number;
	
	Container content;
	
	JMenu  fileMenu;
	JMenu  editMenu;
	
	JMenuItem open;
	JMenuItem save;
	JMenuItem quit;
	
	JMenuItem cutItem;
	JMenuItem pasteItem;
	JMenuItem copyItem;
	JMenuItem selectItem;
	
	JMenu cutMenu;
	
	String ScrachPad;
	
	JTextArea display;
	
	JMenu fontMenu;
	
	JMenu fontStyleMenu;
	JMenu fontSizeMenu;
	JMenu fontColorMenu;
		
	int size = 15;
	String FontType;

	
	Vector recentCuts = new Vector();
	FontSizes fontsize = new FontSizes();

	Font font;
	
	Colors color = new Colors();
	
	int column = 0;
	JLabel columnCount = new JLabel("col: "+ column);
	
	
	New_File_Editor(){
		setVisible(true);
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		display = new JTextArea();
		
		number = new JTextField(20);
				
	
		mBar = new JMenuBar();
		content = getContentPane();
		content.add(mBar, "North");
		content.add(display, "Center");
		mBar.setBackground(Color.green);
		mBar.setFont(new Font("Time New Romans", Font.BOLD,getFont().getSize()));
		
		
		fileMenu = new JMenu("File");
		editMenu = new JMenu("Edit");
		
		
		mBar.add(fileMenu);
		mBar.add(editMenu);
		
		open = new JMenuItem("open    cntrl + O");
		save = new JMenuItem("save    cntrl + S");
		quit = new JMenuItem("quit    cntrl + Q");
		
		open.addActionListener(this);
		save.addActionListener(this);
		quit.addActionListener(this);
		
		fileMenu.add(open);
		fileMenu.add(save);
		fileMenu.addSeparator();
		fileMenu.add(quit);
		
		cutItem = new JMenuItem("Cut");
		copyItem = new JMenuItem("Copy");
		pasteItem = new JMenuItem("paste");
		selectItem = new JMenuItem("Select All");
		
		cutMenu = new JMenu("Recent Cuts");
		
		cutItem.addActionListener(this);
		copyItem.addActionListener(this);
		pasteItem.addActionListener(this);
		selectItem.addActionListener(this);
		
		cutItem.addActionListener(this);
		
		editMenu.add(copyItem);
		editMenu.add(cutItem);
		
		
		
		editMenu.add(pasteItem);
		editMenu.add(selectItem);
		
		editMenu.addSeparator();
		
		editMenu.add(cutMenu);
		
		this.getContentPane().add(new JScrollPane(display));
		
		fontMenu = new JMenu("Font");
		mBar.add(fontMenu);
		
		fontStyleMenu = new JMenu("Font");
		fontMenu.add(fontStyleMenu);
		fontMenu.addSeparator();
		fontMenu.addActionListener(this);
		
		fontSizeMenu = new JMenu("Font Size");
		fontSizeMenu.add(number);
		fontSizeMenu.addActionListener(this);
		fontMenu.add(fontSizeMenu);
		fontMenu.addSeparator();
		
		fontColorMenu = new JMenu("Font Color");
		fontColorMenu.addActionListener(this);
		fontMenu.add(fontColorMenu);
		
		baseBar = new JMenuBar();
		content.add(baseBar, "South");
		baseBar.add(columnCount);
		
		
		for (int i = 0; i < new fontType().fontTypeMethiod(this).length; i++) {
			 fontStyleMenu.add(new fontType().fontTypeMethiod(this)[i]);
		}
		for (int i = 0; i < 20 ; i++) {
			fontSizeMenu.add(fontsize.fontmethod(this)[i]);
		}
		
		for(int i =0; i < color.Colorsnames.length; i++) {
			fontColorMenu.add(color.colorMethod(this)[i]);
			if(i == color.Colorsnames.length - 2) {
				fontColorMenu.addSeparator();
			}
		}
		this.validate();
	}

	
	public void addRecentCut(String cut) {
		recentCuts.setSize(10);
		recentCuts.insertElementAt(cut, 0);
		cutMenu.removeAll();
		for (int i = 0; i <recentCuts.size();  i++) {
			JMenuItem item = new JMenuItem((String)recentCuts.elementAt(i));
			item.addActionListener(this);
			cutMenu.add(item);	
		}
	
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		 column += display.getLineCount();
		 columnCount.setText("Col : "+ column);
		 System.out.println(column);
		
	
		
			 
			 
		 JMenuItem source = (JMenuItem) e.getSource();
			
		if(source == cutItem) {
			ScrachPad = display.getSelectedText();
			display.replaceRange("", display.getSelectionStart(), display.getSelectionEnd());
			addRecentCut(ScrachPad);
		}
		
		else if(source == copyItem) {
			ScrachPad = display.getSelectedText();
		}
		
		else if(source == pasteItem) {
			display.insert(ScrachPad, display.getCaretPosition());
			
		}
		else if(source == selectItem) {
			display.selectAll();
			
		}
		else if(source == quit) {
			dispose();
		}
		
		else if(source == save) {
		//JFileChooser saver = new JFileChooser();
		File myFile = new File("C:\\Users\\ERIC GADZI\\Desktop\\thisfile.txt");
		try {
			FileWriter writ = new FileWriter(myFile);
			writ.write(display.getText());
			
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		 try {
		      File myObj = new File("filename.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e1) {
		      System.out.println("An error occurred.");
		      e1.printStackTrace();
		    }
		
		}
		else {
			ScrachPad = ((JMenuItem)e.getSource()).getActionCommand();
		}
		
	

	}

	public static void main(String[] args) {
		new New_File_Editor();
		//System.out.println(Runtime.getRuntime().totalMemory());
	}


	@Override
	public void fontsizes(int size) {
		this.size = size;
		this.font = new Font(FontType, Font.PLAIN, size);
		display.setFont(this.font);
	}


	@Override
	public void colorReturn(Color color) {
		display.setForeground(color);
	}


	@Override
	public void fonttype(String font) {
	this.FontType = font ;
	this.font = new Font(FontType, Font.PLAIN, size);
	display.setFont(this.font);
	}
}




class fontType extends JMenuItem implements ActionListener{
	String fontstyle[] = {"Serif", "SansSerif","Monospaced","Dialog","DialogInput", "Arial", "Calibri", "Vernada", "Sagoe Script"};
	JMenuItem fontstyleItem[] = new JMenuItem[fontstyle.length];
	FontTypeInterface fti;
	
	
	JMenuItem [] fontTypeMethiod(FontTypeInterface fti) {
		this.fti = fti;
		for (int i = 0; i < fontstyle.length; i++) {
				 fontstyleItem[i] = new JMenuItem(fontstyle[i]);
			//	 fontStyleMenu.add(fontstyleItem[i]);
				 fontstyleItem[i].addActionListener(this);
				
				
				 
		}
		 return fontstyleItem;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String font = ((JMenuItem)e.getSource()).getText();
		
		if(e.getSource() instanceof JMenuItem) {
			fti.fonttype(font);
		}
	}	

}



abstract interface FontTypeInterface {
	void fonttype(String font);
}