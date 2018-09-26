# Lab3 Notes


## Intent

-
    |Explicit显式|Implicit隐式|
    |-|-|
    |显示Intent指定应用|隐式Intent不指定应用|
    |例如页面的转换|例如要打开地图|

- **Intent Filter**: 隐式Intent的时候确定调用哪个/哪些 App，在App的Manifest File中声明，<br>
  **官方定义：** An intent filter is an expression in an app's manifest file that specifies the type of intents that the component would like to receive

- <u>**Caution**: Service不要弄Intent Filter，因为用户对Service不可视，API21+会报错</u>


- **Intent携带的信息**
  
  | Name | WhatFor |How to|
  | - | - | - |
  |Component name|可选，有的话即为explicit| 声明包名，setComponent(), setClass(), setClassName(), 或者在Intent构造函数声明.
  |? Action|即将进行的操作|?有一些默认的，比如Action_View, Action_Send, 通过startActivity()打开| 
  |Data||
  |Category||
  |Extras||
  |Flags||

## ListView
- 使用Adaptor填充数据

- 点击事件：i指列表位置，l指id，在ArrayAdaptor和SimpleAdaptor中是一样的
  
## RecyclerView
- ViewHolder设计模式（可不可以叫做“显示控件工厂模式”）<br> 比如一个屏幕有10个Item，则可能会有12个ViewHolders，然后由Adaptor统一管理数据<br>The adapter also binds the view holders to their data. It does this by assigning the view holder to a position, and calling the adapter's onBindViewHolder() method.
  

## XML绘图

[学习链接](https://blog.csdn.net/yangshangwei/article/details/51190974)