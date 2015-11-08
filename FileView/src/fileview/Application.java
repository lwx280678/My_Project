package fileview;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

/**
 * 对于普通Java应用程序来说，总是有一个main()方法，应用程序总是从main()开始运行。Application类似于Java应用程序的main()，
 * 即RCP应用程序运行开始于Application，Application是RCP应用程序的入口。
 * 该类负责创建一个工作台（Workbench），并且与ApplicationWorkbenchAdvisor类连接
 * 该类实现了IApplication接口,启动RCP应用程序之后该类首先被加载。 如果系统需要登录，通常是在这个类中插入登录模块
 * 通常情况下这个类不需要改动从plugin.xml可以获知，Application实际上是Eclipse
 * org.eclipse.core.runtime.applications运行时扩展点的一个入口 This class controls all
 * aspects of the application's execution
 * 
 * 
 * 
 * NOIP2005 青蛙过河（状态压缩DP）
2013年10月14日 ⁄ 综合 ⁄ 共 3279字	 ⁄ 字号 小 中 大 ⁄ 评论关闭

过河（NOIp2005）

【问题描述】

       在河上有一座独木桥，一只青蛙想沿着独木桥从河的一侧跳到另一侧。在桥上有一些石子，青蛙很讨厌踩在这些石子上。由于桥的长度和青蛙一次跳过的距离都是正整数，我们可以把独木桥上青蛙可能到达的点看成数轴上的一串整点：0，1，……，L（其中L是桥的长度）。坐标为0的点表示桥的起点，坐标为L的点表示桥的终点。青蛙从桥的起点开始，不停的向终点方向跳跃。一次跳跃的距离是S到T之间的任意正整数（包括S,T）。当青蛙跳到或跳过坐标为L的点时，就算青蛙已经跳出了独木桥。

       题目给出独木桥的长度L，青蛙跳跃的距离范围S,T，桥上石子的位置。你的任务是确定青蛙要想过河，最少需要踩到的石子数。

【输入文件】

       第一行一个正整数L（1 <= L <= 10^9），表示独木桥的长度。第二行有三个正整数S，T，M，分别表示青蛙一次跳跃的最小距离，最大距离，及桥上石子的个数，其中1 <= S <= T <= 10，1 <= M <= 100。第三行有M个不同的正整数分别表示这M个石子在数轴上的位置（数据保证桥的起点和终点处没有石子）。所有相邻的整数之间用一个空格隔开。

【输出文件】

       一个整数，表示青蛙过河最少需要踩到的石子数。

样例输入

10
2 3 5
2 3 5 6 7
样例输出
2



分析：

      首先注意下数据量，maxL=10^9，这是一个即使是线性算法也已经逼近运行时限的规模。

所以要准备好进行极限优化。

    显而易见，最纯朴的想法是尝试搜索，从一个点扩展出所有可能跳到的点，依此类推，

最后DFS寻找由顶至下经过石子最少的路径即可。但注意到树的宽度有可能达到maxJump=10，

深度也和L成正比，故蛮力搜索不现实。

    可不可以进行优化呢？我们注意到，DFS效率低下的一个很重要原因是，递归遍历的路

径有许多次其实是同一条路径，这些重复工作消耗了太多时间。于是我们自然而然想到要寻

找记忆化的算法。

    我们发现，这个问题实际上包含最优子结构，只要青蛙能站在某一个位置，以它的智商

绝对会从前面k个可能的位置中，寻找这么一个位置，这个位置所处在的跳跃路径上所经过的

石子是最少的，从这个位置跳跃而来。显然，这个性质是递归定义的。 额外的，我们看到，

当青蛙站在某条路径上的某个位置，那么它此后跳跃的路径（当然是可能的路径）的选择并

不受之前的结果所干涉，因此问题具备无后效性。

 

   可以动规了，下面直接给出转移方程。 

   

   DP[n]=min{DP[k]|n-maxJump<=k<=n-minJump}+t

   t=0||t=1

  

    即，设一个位置可以到达，那么必从前面可能的位置选择一个，该位置所处的路径上经

过石子最少。若当前位置有石子，dp结果再加一。

    这是一个O(n)的顺推，但是，如果minJump-maxJump=1-10，常数C就会太大造成超时。能

不能再优化呢？仔细观察，发现石子最多有100粒，在整个10^9长度中分布相当稀疏，这是一

个很重要的特点！假设 minJuump!=maxJump，那么若空白位置足够长，dp的结果最终都会趋

向于一个稳定值，这个值是前面所有可能的dp结果中最小的那个(请读者自己想象，若跳跃的

步长可以选择，即跳到某个位置的起跳点可以选择的话，在足够远的未来，一个聪明的青蛙

肯定会在一条最优的路径上屁颠着~ 汗)。

  例如在这一数据

   25

   4 5 5

   2 3 5 6 7

  0~4位置的初始dp指依次为 [0,0,1,1,0]，它将有如下变化，并且趋于稳定值0：

 (0~5)=[0,0,1,1,0,1]

 (1~6)=[0,1,1,0,1,1]

 (2~7)=[1,1,0,1,1,1]

 (3~8)=[1,0,1,1,1,0]

 (4~9)=[0,1,1,1,0,0]

 (5~10)=[1,1,1,0,0,2]

 (6~11)=[1,1,0,0,2,2]

 (7~12)=[1,0,0,2,2,0]

 (8~13)=[0,0,2,2,0,0]

 (9~14)=[0,2,2,0,0,0]

 (10~15)=[2,2,0,0,0,2]

 (11~16)=[2,0,0,0,2,0]

 (12~17)=[0,0,0,2,0,0]

 (13~18)=[0,0,2,0,0,0]

 (14~19)=[0,2,0,0,0,0]

 (15~20)=[2,0,0,0,0,0]

 ......

 

     为了利用这个性质，我们先确定要使用的数据结构。显而易见的，对第i个位置的计算，

只依赖于距离i一定距离的数个已知结果。因此，我们只需关心相对坐标即可，于是使用队列

保存i之前一定距离内的dp结果，计算完毕后 dp[i-maxJump] 出队，dp[i]入队，再利用该队

列计算dp[i+1]，如此往复循环。

  现在回到之前的讨论，当一个队列中的结果已经达到稳态，那么对下一次计算，它只能产

生两种结果：如果计算位置没有石子，那么队列仍然保持稳定；如果有石子，那么加一的结果

将使得队列再次出现不稳定。 多么令人振奋的结论！

    这意味着什么呢？这意味着当你发现队列已经进入稳定的时候，后面所有对不存在石子的

位置进行的计算都是可以忽略的，你不妨直接计算到下一个未访问的且有石子的位置即可。

    很多人已经看到了，这是一个状态压缩+DP的做法。

  

   算法终止: 1.dp已计算完所有石子并且结果进入稳定态

             2. 队列已经稳定并且下一个落地的位置所有可能的起点都在桥的终点或之外

            

   读者可以自行分析中止的条件，剩下的只需要考虑细节问题就可以了，只要思路清晰本

题就不会太难过。算法的实际运行效率没有问题，单个极限数据只消耗了45ms。

另一个理解思路：



此题乍一看上去好像很简单，因为只要沿着青蛙跳的方向，逐步递推即可。大致代码如下：

Fori:=1 To L+t-1 Do  （仔细想想为什么？）

Forj:=s To t Do

If i这个位子有石头Then If f[i]>f[i-j]+1 Then f[i]:=f[i-j]+1

           Else If f[i]>f[i-j]  Then f[i]:=f[i-j];

        但是有个地方我们忽略了，那就是数据规模。L最大有10^9，第一个For就已经无法承受庞大的时间限制和空间限制了。所以，这种方法需要进行改进。而改进的方法，就是状态压缩。

        我们可以发现题目的一个玄机：虽然L很大，但是M却很小，也就是说：石子很稀疏。


石子稀疏对我们解题有什么帮助呢？我们来看一下下面的推断：

第一种情况：      

      当s=t时，很简单，青蛙踩到的石头肯定是s的倍数，那么只要统计一下所有石子中有多少是s的倍数，输出即可。

第二种情况：s<t

我们先来看一组数据。s=4，t=5。






从数据中我们看到，12以后的点全部都是可以到达的了。如果s=4，t=5，在一段100000的距离中没有石头，其实12以后的点都是不用递推就知道肯定能到达的。那么我们用原始的方法做就会浪费很大的资源。

        所以当s=4，t=5时，如果一段没有石头的区间长度在4*5=20以外，那么我们只要递推前20就可以了，因为20后面的情况是一样的（仔细想想为什么？）。

假设s=3，t=5，那么11=3+4+4就也可以到达了。所以，只有当t=s+1时，连续的点的起始位置才能尽量后面。最坏情况就是s=9，t=10了（仔细想想为什么？）。

       跟前面的s=4，t=5的情况一样，其实s=9，t=10时只要一段没有石头的区间长度在90之外，我们都把它当做90对待就可以了。

        因此，我们每次对于一段没有石头的区间长度为x，如果x<=t(t-1)，我们仍然把它当做x来处理；相反，当x>t(t-1)时，我们就把它当做t(t-1)处理。这样，最大的复杂度就是t(t-1)*（石头个数+1）=90*101=9090，比之前的复杂度大大降低。

        这种方法叫状态压缩，我们这题用的方法叫离散化。至此，过河完美AC！
 */
public class Application implements IApplication {

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.
	 * IApplicationContext)
	 */
	public Object start(IApplicationContext context) throws Exception {
		Display display = PlatformUI.createDisplay();
		try {
			// 启动之前先进行登录操作
			if (!login()) {
				return IApplication.EXIT_OK; // 没有登陆成功就正常退出
			}
			// 启动工作台，从而打开应用程序窗口
			int returnCode = PlatformUI.createAndRunWorkbench(display,
					new ApplicationWorkbenchAdvisor());
			if (returnCode == PlatformUI.RETURN_RESTART) {
				return IApplication.EXIT_RESTART;
			} else {
				return IApplication.EXIT_OK;
			}
		} finally {
			display.dispose();
		}

	}

	// 登录验证
	public boolean login() {
		boolean flag = false;// 标志
		int tryCount = 0;// 登录的次数
		while (!flag) {
			LoginDialog loginDialog = new LoginDialog(null);// null是可以的！
			Object obj = new Object();
			loginDialog.setLogin(obj);// 这里很重要
			if (tryCount == 3) {// 尝试登录了三次没有成功就退出
				// loginDialog.setErrorMessage("Wrong Login Information!");
				return false;
			}
			int res = loginDialog.open();// 注意，可能用户只是点击了关闭！
			if (res == IDialogConstants.ABORT_ID
					|| res == IDialogConstants.CANCEL_ID) {
				return false; // 关闭或者退出了就直接退出程序
			}
			if (res == IDialogConstants.OK_ID) {// Dialog返回之前会设置好内部的doctor对象
				// obj = DBUtil.getLoginInfo(obj);//
				// 此时右边的doctor对象有了username和password
			}
			// doctor肯定不为null(每次都是new Doctor())，那么就要看doctor的属性
			if (obj != null) {// 如果用户输入了name和password之后，但是信息无效就会提示这个或者用户直接点击了关闭登录
				// MessageDialog.openInformation(null,
				// "Error Information", "Wrong Login Information!");
				// loginDialog.setErrorMessage("Wrong Login Information!");
				flag = true;// 这种情况下才是登录成功，否则继续进行登录
			} else {
				tryCount++;
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		if (workbench == null)
			return;
		final Display display = workbench.getDisplay();
		display.syncExec(new Runnable() {
			public void run() {
				if (!display.isDisposed())
					workbench.close();
			}
		});
	}
}
