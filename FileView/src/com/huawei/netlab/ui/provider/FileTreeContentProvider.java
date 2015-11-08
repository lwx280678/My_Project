package com.huawei.netlab.ui.provider;

import java.io.File;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class FileTreeContentProvider implements ITreeContentProvider {

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof File)
        {
            File f = (File) parentElement;
            if (f.isDirectory())
            {
                return f.listFiles();
            }
            return new Object[0];
        }
        return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof File)
        {
            File f = (File) element;
            return f.getParentFile();
        }
        return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof File)
        {
            File f = (File) element;
            if (f.isDirectory())
            {
                return true;
            }
            return false;
        }
        return false;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof File[])
        {
			
            return (File[]) inputElement;
        }
        return new Object[0];
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	
}
