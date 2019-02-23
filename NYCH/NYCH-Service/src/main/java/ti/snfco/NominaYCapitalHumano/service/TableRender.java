package ti.snfco.NominaYCapitalHumano.service;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableRender extends DefaultTableCellRenderer{
	
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row, int column) {
		
		if(value instanceof JButton) {
			JButton btn  = (JButton)value;
			return btn;
		}
		
		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}

}
