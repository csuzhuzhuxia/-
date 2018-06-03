# CrossEvent

一款越野赛事的app，包括java后台，比较小，没有用一些框架，但是还是进行了一些封装
包含注册，登陆，个人主页，赛事查看，赛事报名，赛事进行，社区（模仿qq空间，尚未完善）

运行顺序：
1.先创建数据库，数据库名为webgis,再执行crossEvent.sql文件，本人使用postgresql数据库，如使用mysql数据库，就不用添加spatial-ref-sys表，在sql进行相应的注释
2.运行CrossCountry 文件夹内的java后台web项目，本人使用IDEA构建，是web项目，启动tomcat服务器
3.运行crossCountryEvents文件内的安卓项目，本人使用AS（android studio)构建的项目，进行测试，CrossCountryApplication内的有一个ip的静态String变量，即服务器的ip地址，大家根据运行的是模拟器还是wifi条件下的真机来进行修改（提示，模拟器中的电脑ip地址为：10.0.2.2）


##祝大家运行成功，哈哈哈。
##祝福大家运行成功，一起学习，然后欢迎小可爱来加我微信，qq来讨论。  微信zrs18373150759  qq:2956054174  
