package com.huawei.netlab.ui.provider;

import java.io.File;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import fileview.Activator;


public class FileTreeLabelProvider implements ILabelProvider {

	@Override
	public Image getImage(Object element) {
		if (element instanceof File)
        {
            File f = (File) element;
            return Activator.getImage(f);
        }
        return null;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof File)
        {
            File f = (File) element;
            if (f.getParentFile() == null)
            {
                return f.getPath();//当为盘目录时，要显示路径
            }
            return f.getName();
        }
        return null;
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

}
