package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;


/**
 * The Class GUI_DefaultTable.
 */
public class GUI_DefaultTable extends JPanel {

	/** The table model. */
	public DefaultTableModel tableModel;
	
	/** The table. */
	public JTable table;
	
	/**
	 * Instantiates a new gU i_ default table.
	 *
	 * @param data the data
	 * @param columnNames the column names
	 */
	public GUI_DefaultTable(Object[][] data, String[] columnNames) {
		
    	super(new GridLayout(1,0));
    	
    	
        this.setOpaque(true);

        tableModel = new DefaultTableModel(data,columnNames);
        
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT|Font.BOLD , 15));
        header.setForeground(Color.WHITE);
        for (int i = 0; i < table.getColumnCount()-1; i++)
        {
		        TableColumn col = table.getColumnModel().getColumn(i);  
		        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();    
		        dtcr.setHorizontalAlignment(SwingConstants.CENTER);  
		        col.setCellRenderer(dtcr); 
        } 
        table.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT , 15));
        table.setFillsViewportHeight(true);
		
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
        
        
	}
}
