package com.huawei.netlab.ui.views;

import java.awt.MenuItem;
import java.io.File;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.huawei.netlab.ui.actions.EditorAction;
import com.huawei.netlab.ui.editor.FileEditor;
import com.huawei.netlab.ui.listener.FileTreeListener;
import com.huawei.netlab.ui.listener.FileTreeViewListener;
import com.huawei.netlab.ui.provider.FileTreeContentProvider;
import com.huawei.netlab.ui.provider.FileTreeLabelProvider;


public class FileView extends ViewPart {

	public static final String ID = "com.huawei.netlab.ui.views.FileView";
	
	private TreeViewer fileTreeView = null;
	private TreeEditor fileTreeEditor=null;
	
	private EditorAction editorAction;
	
	public FileView() {
		editorAction=new EditorAction();
		editorAction.setText("Open Editor");
	}

	@Override
	public void createPartControl(Composite parent) {
		
		fileTreeView = new TreeViewer(parent, SWT.NONE);
		
        fileTreeView.setContentProvider(new FileTreeContentProvider());
        fileTreeView.setLabelProvider(new FileTreeLabelProvider());
        
        fileTreeView.setInput(File.listRoots());
        
        fileTreeView.addSelectionChangedListener(new FileTreeViewListener());
        fileTreeView.addDoubleClickListener(new FileEditor());

        fileTreeEditor=new TreeEditor(fileTreeView.getTree());
        fileTreeView.getTree().addSelectionListener(new FileTreeListener(this));
        
        addContextMenu();
	}

	@Override
	public void setFocus() {
		fileTreeView.getControl().setFocus();
	}
	/**
	 * ����Ҽ������Ĳ˵�
	 */
	private void addContextMenu() {
		MenuManager menuManager = new MenuManager("PopMenu");
		menuManager.add(editorAction);
		
		//Menu menu = menuManager.createContextMenu(fileTreeView.getControl());
		Menu menu = menuManager.createContextMenu(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		fileTreeView.getControl().setMenu(menu);
		getViewSite().registerContextMenu(menuManager, fileTreeView);
		
		
	}

	public TreeViewer getFileTreeView() {
		return fileTreeView;
	}

	public TreeEditor getFileTreeEditor() {
		return fileTreeEditor;
	}
	
}
