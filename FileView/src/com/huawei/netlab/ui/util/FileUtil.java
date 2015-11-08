package com.huawei.netlab.ui.util;

import java.io.File;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import com.huawei.netlab.ui.views.FileView;
@SuppressWarnings("all")
public class FileUtil {

	public static void setTextToFile(Text text) {
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IViewPart findView=activePage.findView("com.huawei.netlab.ui.views.FileView");
		String fileName = text.getText();
		if (findView instanceof FileView) {
			FileView fileView=(FileView)findView;
			IStructuredSelection selection = (IStructuredSelection)fileView.getFileTreeView().getSelection();
			Object firstElement = selection.getFirstElement();
			if (firstElement instanceof File) {
				File file=(File)firstElement;
				File file2 = new File(file.getParent()+fileName);
				file.renameTo(file2);
			}
		}
	}

}
