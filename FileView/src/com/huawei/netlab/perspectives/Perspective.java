package com.huawei.netlab.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.huawei.netlab.ui.views.FileView;
import com.huawei.netlab.ui.views.TableView;

/**
 * Perspective类主要负责界面布局的安排。
 * 
 * @author asus
 * 
 */
public class Perspective implements IPerspectiveFactory {

	public static final String ID = "com.huawei.netlab.perspectives.Perspective";

	public void createInitialLayout(IPageLayout layout) {

		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(true);

		layout.addStandaloneView(FileView.ID, true, IPageLayout.LEFT, 0.3f,
				editorArea);
		layout.addStandaloneView(TableView.ID, true, IPageLayout.BOTTOM, 0.3f,
				editorArea);

	}
}
