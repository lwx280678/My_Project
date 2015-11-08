package fileview;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.internal.util.PrefUtil;

/**
 * ApplicationWorkbenchWindowAdvisor类负责应用程序窗口生命周期的管理，该类扩展自WorkbenchWindowAdvisor
 * 每一个应用程序都需要一个WorkbenchWindowAdvisor来控制窗口界面的UI元素。开发人员可以控制窗口创建时的大小、标题、位置等等，
 * 也可以添加创建、打开、还原或关闭工作台窗口时调用的方法。
 * 
 * 该类继承了WorkbenchWindowAdvisor类，主要是对工作台窗口进行控制 包括状态栏，工具栏，透视图栏，快速试图栏，窗口尺寸，标题等等
 * 该类还有一个重要的作用就是创建ActionBarAdvisor类 这个类的方法在整个工作台的生命周期中起着重要作用
 * 
 * 重要方法： 1.createEmptyWindowContents（Composite composite）：返回当窗口没有页面显示时要显示的控件
 * 2.createWindowContents（Shell shell）：创建窗口内容 
 * 3.getWindowConfigurer（）：返回工作台窗口配置
 * 4.openIntro（）：打开初始内容 
 * 5.postWindowClose（）：在窗口关闭之后调用
 * 6.postWindowCreate（）：在窗口创建以后，打开之前调用 或者 窗口恢复到保存状态后，在执行postWindowRestor方法之后调用
 * 7.postWindowRestor（）：在窗口被恢复到保存状态之后，打开之前调用
 * 8.perWindowOpen（）：在窗口被打开之前调用
 * 9.preWindowShellClose（）：在窗口的Shell被关闭之前调用
 * 
 * @author asus
 * 
 */
public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public ApplicationWorkbenchWindowAdvisor(
			IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	public ActionBarAdvisor createActionBarAdvisor(
			IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}

	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(600, 400));
		configurer.setShowPerspectiveBar(true);
		configurer.setShowMenuBar(true);
		configurer.setShowCoolBar(true);
		configurer.setShowStatusLine(true);
		 //设置快速视图栏
        configurer.setShowFastViewBars(true);   
		configurer.setTitle("Hello RCP");

		IPreferenceStore preferenceStore = PrefUtil.getAPIPreferenceStore();
		preferenceStore.setValue(
				IWorkbenchPreferenceConstants.SHOW_TRADITIONAL_STYLE_TABS,
				false);
		preferenceStore.setValue(
				IWorkbenchPreferenceConstants.DOCK_PERSPECTIVE_BAR,
				IWorkbenchPreferenceConstants.TOP_RIGHT);
	}

	@Override
	public void postWindowCreate() {
		// 设置窗口自动居中
		Shell shell = getWindowConfigurer().getWindow().getShell();
		Rectangle screenSize = Display.getDefault().getClientArea();
		Rectangle frameSize = shell.getBounds();
		shell.setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
	}

}
