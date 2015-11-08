package com.huawei.netlab.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.widgets.Event;

public class EditorAction extends Action {

	public EditorAction(){}
	
	@Override
	public void run() {
		System.out.println("1111111111111111");
		
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		super.setText(text);
	}

	

}
