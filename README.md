# 欢迎使用 BaseDemo

------

BaseDemo采用MVP + 响应式编程(Rx)，主要包括三个Module：

> * BaseProject
> * RxExtention
> * Demo

![cmd-markdown-logo](http://img.hb.aicdn.com/5023865d0cfe43d95305e420833e41cbdad7de9a16647-5tXnEU_fw658)

其中：
BaseProject封装了常用的网络请求(基于Retrofit和OkHttp)、Activity栈管理(通过ActivityLifecycleCallbacks)、图片管理(基于Glide)、基础组件、工具类等；

RxExtention增加了RxJava、RxAndroid，对BaseProject进行扩展；如果打算不使用Rx，可以不引用该Module；

Demo中以登录、注册、商品列表三块功能为例，展示了具体使用；XXXXContract作为契约接口，定义了各层需要实现的方法。

------

## BaseProject

基础工程，需注意：

### 1. 网络请求&响应拦截

RequestInterceptor为请求拦截，ReceivedInterceptor为响应拦截，共通的拦截操作可在此处理，比如：添加统一的Header，计算并添加签名(鉴权)等。

### 2. 数据模型&解析

RequestBean为数据请求的基础模型，按项目实际返回结果调整；
如需调整数据解析，请修改FastJsonResponseBodyConverter。

## RxExtention

扩展工程，需注意：

### 1. 生命周期

RxBasePresenter为Presenter基类，订阅了Activity/Framgment生命周期标记位(详见RxMvpBaseActivity中RxLifecycle的使用)；
使用withRxLifecycle()方法(配合takeUntil操作符)，可以设置在任一生命周期自动解绑，默认在onDestory时解绑，避免内存泄漏。

### 2. onError的共通处理

“登录失效”或是其它需要统一处理的Error，可以在RxActionImpl中定义；并在withOnError()方法中设置相应的逻辑。

## Demo

需注意：

### 1. 初始化配置

在AppApplication中进行参数映射、配置拦截、工具初始化等操作。

### 2. 数据仓库

data文件下为各模块的数据仓库，如：CommRepository、UserRepository，不同模块的数据独立管理；XXXXImpl定义了仓库对外开放的接口。

## 其它

> * gradle.properties统一管理项目版本、编译版本、引用库的版本
> * keystore.properties(docs文件夹下)配置签名文件地址、别名及密码
> * key文件夹下放置签名文件
> * Release版本需避免代码混淆的部分，请在主工程的proguard-rules.pro(常用的指令及规则可参考BaseProject工程的proguard-rules.pro)






