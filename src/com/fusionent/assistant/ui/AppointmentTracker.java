package com.fusionent.assistant.ui;

import java.awt.Dimension;
import java.awt.Panel;

import javax.swing.JTable;

public class AppointmentTracker extends Panel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected JTable appointmentTable;
	public AppointmentTracker() {
		appointmentTable = setupTable();
		appointmentTable.setPreferredSize(new Dimension(200,200));
		this.add(appointmentTable);
		this.setPreferredSize(new Dimension(200,200));
		this.setVisible(true);
	}
	
	protected JTable setupTable(){
		String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};

		Object[][] data = {
			{"Kathy", "Smith",
			"Snowboarding", new Integer(5), new Boolean(false)},
			{"John", "Doe",
			"Rowing", new Integer(3), new Boolean(true)},
			{"Sue", "Black",
			"Knitting", new Integer(2), new Boolean(false)},
			{"Jane", "White",
			"Speed reading", new Integer(20), new Boolean(true)},
			{"Joe", "Brown",
			"Pool", new Integer(10), new Boolean(false)}
		};

		return new JTable(data, columnNames);
	}
}
