
#### 第2章　搭建android开发环境
* 2.4创建第一android应用程序



#### 第3章　android应用程序结构
* 3.2activity
    *  intent 来加两个activity传输数据


#### 第4章用户界面开发
* 4.3布局  
  * FrameLayoutDemo
  * LinearLayoutDemo  
  * RelativeLayoutDemo  
  * TableLayoutDemo（已不适用）  
  * AbsoluteLayoutDemo  
  * WebViewDemo  


* 4.4常用widget组件
  * WidgetDemo  
    * 按钮（Button）  
    * 文本框（TextView）  
    * 编辑框（EditView）  
    * 多项选择按钮（CheckBox）  
    * 单项选择按钮（RadioGroup）  
    * 下拉列表（Sprinner）  
    * 自动完成文本（AutoCompleteTextView）  
    * 日期选择器（DatePicker）和时间选择器（TimePicker）  
    * 进度条（ProgressBar）  
    * 滚动视图（ScrollView）  
    * 拖动条（SeekBar）  
    * 评价条（RatingBar）  
    * 图片视图（ImageView）和图片按扭（ImageButton）  
    * 图片切换器（ImageSwitcher）和图库（Gallery）  
    * 网格视图（GridView）  
    * 标签（Tab）  
		
* 4.5menu和ActionBar
  * MenusDemo
    * Options Menu 溢出菜单  
    * Context Menu 上下文菜单  
    * Sub Menu	子菜单  

* 4.6bitmap  
  * BitmapDemo

* 4.7对话框(dialog)  
  * DialogDemo（已不适用）

* 4.8toast和notification  
  * NotificationDemo  
	 代码部分改为  
	 ```
     Notification.Builder builder=new Notification.Builder(context);
     builder.setSmallIcon(icon);
     builder.setTicker(tickerText);
     builder.setWhen(when);
     builder.setContentTitle("My notification");
     builder.setContentText("点击这个notification，可以跳转到NoteActivity.");
     Intent notificationIntent = new Intent(context, NoteActivity.class);
     PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
     builder.setContentIntent(contentIntent);
     notification=builder.getNotification();
     notification.defaults=notification.DEFAULT_SOUND;
     mNotificationManager.notify(NOTIFICATION_ID, notification);
     ```


* 4.9界面事件响应  
  * EventDemo  
    * onKeyDown(已不适用)  
    * onKeyUp(已不适用)  

#### 第5章　电话和短售应用程序开发  
* 5.2拨号程序  
  * phoneDemo  

* 5.3短信程序  
  * receiveMessageDemo  
  * sendMessageDemo  

* 5.4照相机程序  
  * CameraDemo  

#### 第6章　多媒体开发  
* (已不适用MediaRecorder组件）

#### 第7章　数据存储
* 7.1sharedpreferences  
  * SharedPreferencesDemo

* 7.2文件存储  
  * FileDemo （有实现OnClickListener类的办法方便同个按钮使办法好）  

* 7.3sqlite  
  * MyDbDemo(MyDbProvider.java,MyFriendsDB.java和AndroidManifest.xml的<uses-permission> <provider android:name="MyDbProvider">都是没用的)（重点）

* 7.4contentprovider  
  * ContactsCPDemo  
  * UseDbProvider（还没搞明白）  

#### 第8章　网络编程
* 8.1http通信  
  * GETDemo(原生态的HTTP好)(1.要把网络请求改不子线程中，2.要用handle 来更新组件)  
  * POSTDemo(原生态的HTTP好)(1.要把网络请求改不子线程中，2.要用handle 来更新组件)  
* 8.2 socket通信  
  * SocketClientDemo（注意原文件是错误过时的权限，应设为<uses-permission android:name="android.permission.INTERNET" />)  
  * SocketServerDemo（注意原文件是错误过时的权限，应设为<uses-permission android:name="android.permission.INTERNET" />)  

* 8.3 bluetooth通信  
* 8.4 wifi通信  
* 8.5nfc  
* 8.6usb  
* 8.7sip  
#### 第9章　位置服务  
　9.1获取位置信息  
　9.2使用google地图服务  
　9.3传感器  
　9.4运动传感器  
　9.5位置传感器  
　9.6环境传感器  
　9.7小结  
　9.8思考题  
#### 第10章　绘图  
　10.12d绘图  
　10.2drawable  
　10.33d绘图  
　10.4硬件加速  
　10.5renderscript  
　10.6小结  
　10.7思考题  
#### 第11章　android的国际化与本地化  
　11.1国际化与本地化  
　11.2手机区域设置  
　11.3未本地化的应用程序  
　11.4本地化的应用程序  
　11.5小结  
　11.6思考题  
#### 第12摩应用程序发布  
　12.1应用程序发布的步骤  
　12.2为什么要为应用程序签名  
　12.3android的签名策略  
　12.4导出未签名应用程序  
　12.5生成签名文件  
　12.6为应用程序签名.  
　12.7使用zipalign工具优化应用程序  
　12.8发布到google play store  
　12.9小结  
　12.10思考题  
#### 第13章　android 4.1来了，4.2也来了  
　13.1android 4.1简介  
　13.2android 4.1下载与安装  
　13.3android 4.2也来了  
　13.4小结  