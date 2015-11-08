package com.huawei.netlab.ui.views;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

import com.huawei.netlab.ui.providers.TableContentProvider;
import com.huawei.netlab.ui.providers.TableLabelProvider;
import com.huawei.netlab.ui.sorter.TableSorter;

public class TableView extends ViewPart {

	public static final String ID = "com.huawei.netlab.ui.views.TableView";

	private CheckboxTableViewer tableView;
	private final String[] columnName = { "文件名", "类型", "大小", "时间" };

	@Override
	public void createPartControl(Composite parent) {
		tableView = CheckboxTableViewer
				.newCheckList(parent, SWT.FULL_SELECTION);

		createTableTitle();

		tableView.getTable().setHeaderVisible(true);
		tableView.getTable().setLinesVisible(false);

		tableView.setContentProvider(new TableContentProvider(getViewSite()));
		tableView.setLabelProvider(new TableLabelProvider(getViewSite()));

		tableView.setSorter(new TableSorter());

	}

	private void createTableTitle() {
		for (int i = 0; i < columnName.length; i++) {
			new TableColumn(tableView.getTable(), SWT.LEFT)
					.setText(columnName[i]);
			tableView.getTable().getColumn(i).pack();
		}
	}

	@Override
	public void setFocus() {

	}
}
