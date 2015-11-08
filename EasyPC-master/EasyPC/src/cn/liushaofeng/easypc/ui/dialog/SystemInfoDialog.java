package cn.liushaofeng.easypc.ui.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import cn.liushaofeng.easypc.util.SystemUtil;

/**
 * the dialog to show system information
 * @author liushaofeng
 * @date 2015-5-2
 * @version 1.0.0
 */
public class SystemInfoDialog extends Dialog
{
    private static final String TAB_ITEM_OVERVIEW = "Overview";//$NON-NLS-1$
    private static final String TAB_ITEM_CPU = "CPU";//$NON-NLS-1$
    private static final String TAB_ITEM_MAIN_BOARD = "Main Board";//$NON-NLS-1$
    private static final String TAB_ITEM_MEMORY = "Memory Info";//$NON-NLS-1$
    private static final String TAB_ITEM_GRAPHICS = "Graphics Info";//$NON-NLS-1$
    private static final String TAB_ITEM_MONITOR = "Monitor Info";//$NON-NLS-1$
    private static final String TAB_ITEM_DISK = "Disk Info";//$NON-NLS-1$
    private static final String TAB_ITEM_NETWORK_CARD = "Network Card";//$NON-NLS-1$
    private static final String TAB_ITEM_AUDIO = "Audio Info";//$NON-NLS-1$
    private static final String TAB_ITEM_OTHER = "Other Info";//$NON-NLS-1$

    private static final String TAB_ITEM_OVERVIEW_SYSTEM_MODE = "System Mode:";//$NON-NLS-1$
    private static final String TAB_ITEM_OVERVIEW_OPERATE_SYSTEM = "Operating System:";//$NON-NLS-1$
    private static final String TAB_ITEM_OVERVIEW_CPU = "CPU:";//$NON-NLS-1$
    private static final String TAB_ITEM_OVERVIEW_MAIN_BOARD = "Main Board:";//$NON-NLS-1$
    private static final String TAB_ITEM_OVERVIEW_MEMORY = "Memory:";//$NON-NLS-1$
    private static final String TAB_ITEM_OVERVIEW_MAIN_DISK = "Main Disk:";//$NON-NLS-1$
    private static final String TAB_ITEM_OVERVIEW_GRAPHICS = "Graphics:";//$NON-NLS-1$
    private static final String TAB_ITEM_OVERVIEW_MONITOR = "Monitor:";//$NON-NLS-1$
    private static final String TAB_ITEM_OVERVIEW_AUDIO_CARD = "Audio Card:";//$NON-NLS-1$
    private static final String TAB_ITEM_OVERVIEW_NETWORD_CARD = "Netword Card:";//$NON-NLS-1$

    /**
     * default constructor
     * @param parentShell patent shell
     */
    public SystemInfoDialog(Shell parentShell)
    {
        super(parentShell);
    }

    @Override
    protected void configureShell(Shell newShell)
    {
        newShell.setText("Show System Information");//$NON-NLS-1$
        super.configureShell(newShell);
    }

    @Override
    protected boolean isResizable()
    {
        return true;
    }

    @Override
    protected Control createDialogArea(Composite parent)
    {
        CTabFolder tabFolder = new CTabFolder(parent, SWT.BORDER);
        tabFolder.setSimple(true);
        tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

        CTabItem overviewItem = new CTabItem(tabFolder, SWT.None);
        overviewItem.setText(TAB_ITEM_OVERVIEW);
        overviewItem.setControl(getOverviewContent(tabFolder));

        CTabItem cpuItem = new CTabItem(tabFolder, SWT.None);
        cpuItem.setText(TAB_ITEM_CPU);

        CTabItem mainBoardItem = new CTabItem(tabFolder, SWT.None);
        mainBoardItem.setText(TAB_ITEM_MAIN_BOARD);

        CTabItem memoryItem = new CTabItem(tabFolder, SWT.None);
        memoryItem.setText(TAB_ITEM_MEMORY);

        CTabItem graphicsItem = new CTabItem(tabFolder, SWT.None);
        graphicsItem.setText(TAB_ITEM_GRAPHICS);

        CTabItem monitorItem = new CTabItem(tabFolder, SWT.None);
        monitorItem.setText(TAB_ITEM_MONITOR);

        CTabItem diskItem = new CTabItem(tabFolder, SWT.None);
        diskItem.setText(TAB_ITEM_DISK);

        CTabItem networkItem = new CTabItem(tabFolder, SWT.None);
        networkItem.setText(TAB_ITEM_NETWORK_CARD);

        CTabItem audioItem = new CTabItem(tabFolder, SWT.None);
        audioItem.setText(TAB_ITEM_AUDIO);

        CTabItem otherItem = new CTabItem(tabFolder, SWT.None);
        otherItem.setText(TAB_ITEM_OTHER);

        return parent;
    }

    private Composite getOverviewContent(CTabFolder tabFolder)
    {
        Composite composite = new Composite(tabFolder, SWT.NONE);
        composite.setLayout(new GridLayout(2, false));

        Label systemNameLabel = new Label(composite, SWT.NONE);
        systemNameLabel.setText(TAB_ITEM_OVERVIEW_SYSTEM_MODE);

        Label systemValLabel = new Label(composite, SWT.NONE);

        Label osNameLabel = new Label(composite, SWT.NONE);
        osNameLabel.setText(TAB_ITEM_OVERVIEW_OPERATE_SYSTEM);

        Label osValLabel = new Label(composite, SWT.NONE);
        osValLabel.setText(SystemUtil.getOSName());

        Label cputNameLabel = new Label(composite, SWT.NONE);
        cputNameLabel.setText(TAB_ITEM_OVERVIEW_CPU);

        Label cpuValLabel = new Label(composite, SWT.NONE);
        cpuValLabel.setText(SystemUtil.getCPUName());

        Label mainboardNameLabel = new Label(composite, SWT.NONE);
        mainboardNameLabel.setText(TAB_ITEM_OVERVIEW_MAIN_BOARD);

        Label mainboardValLabel = new Label(composite, SWT.NONE);

        Label memoryNameLabel = new Label(composite, SWT.NONE);
        memoryNameLabel.setText(TAB_ITEM_OVERVIEW_MEMORY);

        Label memoryValLabel = new Label(composite, SWT.NONE);

        Label diskNameLabel = new Label(composite, SWT.NONE);
        diskNameLabel.setText(TAB_ITEM_OVERVIEW_MAIN_DISK);

        Label diskValLabel = new Label(composite, SWT.NONE);

        Label graphicsNameLabel = new Label(composite, SWT.NONE);
        graphicsNameLabel.setText(TAB_ITEM_OVERVIEW_GRAPHICS);

        Label graphicsValLabel = new Label(composite, SWT.NONE);

        Label monitorNameLabel = new Label(composite, SWT.NONE);
        monitorNameLabel.setText(TAB_ITEM_OVERVIEW_MONITOR);

        Label monitorValLabel = new Label(composite, SWT.NONE);

        Label audioNameLabel = new Label(composite, SWT.NONE);
        audioNameLabel.setText(TAB_ITEM_OVERVIEW_AUDIO_CARD);

        Label audioValLabel = new Label(composite, SWT.NONE);

        Label networkNameLabel = new Label(composite, SWT.NONE);
        networkNameLabel.setText(TAB_ITEM_OVERVIEW_NETWORD_CARD);

        Label networkValLabel = new Label(composite, SWT.NONE);

        return composite;
    }
}
