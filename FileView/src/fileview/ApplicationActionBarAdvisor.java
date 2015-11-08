package fileview;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

/**
 * Advisor是建议者，用户主界面宽度、高度、图标、菜单、工具栏、颜色、操作等等配置方案需要Advisor来提出“建议”.
 * 
 * ApplicationActionBarAdvisor.java类负责定义窗口的行为，该类扩展自ActionBarAdvisor。具体地说，
 * 该类用于构建菜单栏、工具栏和状态行。ActionBarAdvisor自然是负责菜单栏、工具栏和状态行的创建和注册。
 * 
 * @author asus
 * 
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	
	private Action saveAction=null;
	private Action exitAction=null;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	protected void makeActions(IWorkbenchWindow window) {
	}

	protected void fillMenuBar(IMenuManager menuBar) {
	}

	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		super.fillCoolBar(coolBar);
	}

}
