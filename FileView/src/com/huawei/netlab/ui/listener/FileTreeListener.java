package com.huawei.netlab.ui.listener;

import java.io.File;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import com.huawei.netlab.ui.util.FileUtil;
import com.huawei.netlab.ui.views.FileView;

public class FileTreeListener implements SelectionListener {

	public FileView fileView;

	public FileTreeListener() {
	}

	public FileTreeListener(FileView fileView) {
		this.fileView = fileView;
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent event) {
		// 释放之前编辑的控件
		final TreeEditor fileTreeEditor = fileView.getFileTreeEditor();
		fileTreeEditor.horizontalAlignment = SWT.LEFT;
		fileTreeEditor.grabHorizontal = true;// 设置水平抢占为true，很重要，因为会显示可编辑的状态
		fileTreeEditor.minimumWidth = 30;
		Control oldEditor = fileTreeEditor.getEditor();
		if (oldEditor != null) {
			oldEditor.dispose();
		}
		// 获得触发事件的treeItem，如果为空就返回
		TreeItem item = (TreeItem) event.item;
		if (null == item) {
			return;
		}
		// 创建一个文本框，作为编辑节点时输入的文字
		final Text newEditor = new Text(fileView.getFileTreeView().getTree(),
				SWT.MULTI | SWT.BORDER);
		// 将树节点的值赋给文本框
		newEditor.setText(item.getText());
		
		final IStructuredSelection selection = (IStructuredSelection)fileView.getFileTreeView().getSelection();

		// 当文本框的值改变时，也相应的改变树节点的值
		newEditor.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
				Text text = (Text) fileTreeEditor.getEditor();
				fileTreeEditor.getItem().setText(text.getText());
				
				setTextToFile(text);
			}
			
			/**
			 * 将编辑好的树同步到对应的文件中
			 * @param text
			 */
			private void setTextToFile(Text text){
				Object firstElement = selection.getFirstElement();
				String fileName = text.getText();				
				if (firstElement instanceof File) {
					File file=(File)firstElement;
					if (file.isDirectory()) {
						File file2 = new File(file.getParent()+fileName);
						file.renameTo(file2);
					}else {//修改文件
						File file2 = new File(fileName);
						file.renameTo(file2);
					}
					
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		// 选中所有文本
		newEditor.selectAll();
		// 并将焦点设为文本框
		newEditor.setFocus();
		// 将树节点与文本框绑定，这个方法很重要
		fileTreeEditor.setEditor(newEditor, item);

	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
