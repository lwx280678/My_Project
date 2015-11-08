package cn.liushaofeng.easypc.app;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.internal.util.PrefUtil;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor
{

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer)
    {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer)
    {
        return new ApplicationActionBarAdvisor(configurer);
    }

    public void preWindowOpen()
    {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(600, 400));
        configurer.setShowPerspectiveBar(true);
        configurer.setShowMenuBar(true);
        configurer.setShowCoolBar(true);
        configurer.setShowStatusLine(true);

        // 瀹氬埗搴旂敤绋嬪簭鐨勫瑙�        IPreferenceStore preferenceStore = PrefUtil.getAPIPreferenceStore();
        // 璁剧疆閫夐」鍗＄殑鏍峰紡锛屼笉鏄煩褰㈢殑杈规锛岃�鏄姬褰㈢殑
//        preferenceStore.setValue(IWorkbenchPreferenceConstants.SHOW_TRADITIONAL_STYLE_TABS, false);
        // 璁剧疆閫忚鍥炬寜閽殑浣嶇疆锛岄粯璁ゆ槸宸︿笂瑙掞紝鏀逛负鏀剧疆鍦ㄥ彸涓婅
//        preferenceStore.setValue(IWorkbenchPreferenceConstants.DOCK_PERSPECTIVE_BAR,
//            IWorkbenchPreferenceConstants.TOP_RIGHT);
    }
}
