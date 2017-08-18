# BaseProject
基于MVP封装的框架 轻量好用
***
## 框架用法(如果涉及到复杂数据处理 model可参照项目中的相应BaseModel自行封装)
### 1 根据界面写View接口 继承IBaseView或者IBaseRefreshListView
```
  public interface ILoginView extends IBaseView {
    // TODO:  todo something 
  } 
```
### 2 写Presenter 继承BasePresenter
```
  public class LoginPresenter extends BasePresenter<ILoginView> {
    // TODO:  todo something
  }
```
### 3 写activity或者fragment 继承MVPBaseActivity或者MVPBaseFragment
```
  public class LoginActivity extends MVPBaseActivity<ILoginView, LoginPresenter> implements ILoginView {
  
    private boolean isFromLogout;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initTitle() {

        ImmerseStatusBar.setImmerseStatusBar(this);
        setMiddleTitle("登录");
    }

    @Override
    protected void initData() {

        getExtraData();
    }

    private void getExtraData() {
        if (getIntent() != null) {
            isFromLogout = getIntent().getBooleanExtra(ISFROMLOGOUT, false);
        }
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public MultiStateView getMultiStateView() {
        return null;
    }

    @Override
    public void getNetData() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }
}
```
```
  public class DemoFragment extends MVPBaseFragment<IDemoView, DemoPresenter> implements IDemoView {

    @Override
    protected DemoPresenter createPresenter() {
        return null;
    }

    @Override
    protected View getRootView(LayoutInflater inflater) {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    public MultiStateView getMultiStateView() {
        return null;
    }

    @Override
    public void getNetData() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }
}
```
***
## 使用Jumper进行Activity之间的跳转
### 初始化Jumper
```
  Jumper JUMPER = Reflection.newProxy(Jumper.class, new JumperInvokeHandler(BaseProjectApplication.getInstance()));
```
### 将需要的跳转的目标activity配置到Jumper中
```
  public interface Jumper extends IntentNames {

    @ActivityInfo(clz = LoginActivity.class)
    MyIntentHandler gotoLogin(@Extra(RADIO_INDEX) int id, @Extra(ISFROMLOGOUT) boolean isfromlogout);
}
```
### 调用JUMPER实现Activity之间的跳转
```
JUMPER.gotoLogin(1, true).startActivity(mContext);
```
```
JUMPER.gotoLogin(1, true).startActivityForResult(this, LOGIN_REQUEST);
```
***
## View和Presenter之间通讯
### Activity中调用Presenter中的方法
1. 参照上述Activity的写法, 在Activity或者Fragment中可以直接拿到对应的Presenter的引用: mPresenter
2. 将必要参数传递给Presenter, 直接调用Presenter中的方法, 对应Presenter中需要重写该方法拿到相应参数: 
```
setExtralData(Object... objects);
```
### Presenter中调用View的方法
1. 参照上述Presenter的写法, 在Presenter中可以通过getView()的方法拿到对应View的弱引用, 当View销毁的时候, getView()拿到的也随之成为空对象, 因此如果自行封装网络请求的话需要在Activity或者Fragment的onDestory()生命周期方法中终止请求或者在请求完成的会调用进行判空处理, 避免出现空指针的现象
2. 如果涉及到点击Button请求网络等类似需求时需要显示loading框进行过度, 可以直接调用IBaseView中的
```
setWaitingDialogStatus(boolean isShow);
```
***
## 网络请求
目前正在优化, 可参照项目中NetApi进行自行封装
***
## 将下拉刷新和上拉加载封装进去
目前正在进行中
