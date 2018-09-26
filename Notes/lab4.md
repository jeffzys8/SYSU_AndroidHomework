# Lab4


## BroadCast
https://developer.android.com/guide/components/broadcasts

BroadCast 
- 据课件讲就是“类似Implicit Intent”
- 官网上是这么描述的：<blockquote> The broadcast message itself is wrapped in an Intent object whose action string identifies the event that occurred (for example android.intent.action.AIRPLANE_MODE). </blockquote> 
  就有点BroadCast从属于Intent的感觉

# 静态广播接收静态注册无效

（BroadcastReceiver）——Android8.0

- 问题原因：

  Android8.0在AndroidManifest.xml文件中静态注册广播接收失效是由于官方对耗电量的优化，避免APP滥用广播的一种处理方式。除了少部分的广播仍支持静态注册（如开机广播），其余的都会出现失效的情况。

- 解决方案：
  - 在APP的Activity中对广播接收进行动态注册即可完成。
  - 添加Component

# Notification 不显示

Starting in Android 8.0 (API level 26), all notifications must be assigned to a channel. 

https://developer.android.com/training/notify-user/channels

# 奇怪的API bug
Binary XML file line #11: Error inflating class android.support.design.widget.FloatingActionButton

问题的起因是：我一开始AVD的版本是28，结果发现Android 8.0 的通知功能非常恶心，所以想改到19，结果就出现了这个Bug。

研究了很久，最后安装了API 24和AVD 24以后才成功实现通知。。

# Drawable 转 Bitmap

https://blog.csdn.net/zw904448290/article/details/53068914/

# EventBus

https://blog.csdn.net/u012317510/article/details/78935720