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
		// �ͷ�֮ǰ�༭�Ŀؼ�
		final TreeEditor fileTreeEditor = fileView.getFileTreeEditor();
		fileTreeEditor.horizontalAlignment = SWT.LEFT;
		fileTreeEditor.grabHorizontal = true;// ����ˮƽ��ռΪtrue������Ҫ����Ϊ����ʾ�ɱ༭��״̬
		fileTreeEditor.minimumWidth = 30;
		Control oldEditor = fileTreeEditor.getEditor();
		if (oldEditor != null) {
			oldEditor.dispose();
		}
		// ��ô����¼���treeItem�����Ϊ�վͷ���
		TreeItem item = (TreeItem) event.item;
		if (null == item) {
			return;
		}
		// ����һ���ı�����Ϊ�༭�ڵ�ʱ���������
		final Text newEditor = new Text(fileView.getFileTreeView().getTree(),
				SWT.MULTI | SWT.BORDER);
		// �����ڵ��ֵ�����ı���
		newEditor.setText(item.getText());
		
		final IStructuredSelection selection = (IStructuredSelection)fileView.getFileTreeView().getSelection();

		// ���ı����ֵ�ı�ʱ��Ҳ��Ӧ�ĸı����ڵ��ֵ
		newEditor.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
				Text text = (Text) fileTreeEditor.getEditor();
				fileTreeEditor.getItem().setText(text.getText());
				
				setTextToFile(text);
			}
			
			/**
			 * ���༭�õ���ͬ������Ӧ���ļ���
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
					}else {//�޸��ļ�
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

		// ѡ�������ı�
		newEditor.selectAll();
		// ����������Ϊ�ı���
		newEditor.setFocus();
		// �����ڵ����ı���󶨣������������Ҫ
		fileTreeEditor.setEditor(newEditor, item);

	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
