## 写在前面
已经毕业一年多了，很久没来这里看自己的东西了，收到不少网友关于项目如何部署的帖子，我以为只要代码放上去，即使环境有点偏差，大家也能调试出来，毕竟简单。
为了方便网友部署，博主手把手亲自教。如果觉得有用的可以点个赞，另外也可以+V Red-Salvation     ，一起讨论计算机科学与技术的哪些知识，包含计算机网络、c/Java/php程序语言、数据库设计、web程序开发、
软件系统业务研究、linux应用、shell自动化编程等知识；逐步深入IT信息行业，成为软件行业的拥护者。
##### 微信捐赠
![e1dba6cd6e63a43c164f1cb8da4384f](https://github.com/loneli-maker/collogeProject/assets/79433596/b01a7604-9b92-4ac8-ba8a-b5dbac5bf436)

# collogecourse
江苏交通学院：大学生创新创业管理系统。这是一个SpingBoot框架写的管理系统，针对大学生创新创业项目的管理，完成了项目的申报、指导、评审等过程。 项目设计技术点：Java、SSM、SpringBoot、MybatisPlus、Jquery、Ajax、Thymeleaf模板、Apache Poi办公文件导入导出。
#### 功能介绍
系统功能模块简单，




#### 安装教程
### 本地手工部署
#### idea安装与配置
   1、下载idea工具，工具里面可以直接下载JDK，或者导入JDK1.8。
   ![image](https://github.com/loneli-maker/collogeProject/assets/79433596/fac0886d-f66f-4632-99c2-c5e066e2e52c)


#### mysql数据库安装并导入数据
1、安装mysql5.7,网上自行百度
2、创建数据库，名称study,导入数据库文件sql.txt，把后缀改为sql，或者直接复制sql内容去执行。
![image](https://github.com/loneli-maker/collogeProject/assets/79433596/9559d5c7-d5d5-4057-b31c-69fbf0fcf4d8)

3、检查application.properties，里面的信息自己去改，包括端口号、数据库名称、mysql用户名和密码，这个很简单的就不多说。
![image](https://github.com/loneli-maker/collogeProject/assets/79433596/6e84bb5c-eacb-438f-b5cc-a0f133e0f60a)

![image](https://github.com/loneli-maker/collogeProject/assets/79433596/6b7ab8a9-9ede-45cc-9e0a-960abc9d94b9)


#### 使用说明
1、登陆界面，如果CSS有点变形，可能是bootstrap样式或者前端模板有点问题，第二次登陆就是正常的，感兴趣的也可以自己去改前端页面，反正不用动后端接口就行
![image](https://github.com/loneli-maker/collogeProject/assets/79433596/12ab320e-5a8e-49ec-a681-7d57786d4a72)
### 各模块功能
1、学生登录首页和申报项目
![7b5af7197172086beb56f1d158504dc](https://github.com/loneli-maker/collogeProject/assets/79433596/b536b6ef-a052-4c9c-8882-e1465c34f34d)
![image](https://github.com/loneli-maker/collogeProject/assets/79433596/20cc1b8f-e31d-49bf-b696-4d145c63ea26)
2、教师开设项目课题、指导学生申报的项目。
![image](https://github.com/loneli-maker/collogeProject/assets/79433596/361b23c7-887c-431c-955e-7aa9bd9542b7)
3、评审员对项目进行打分
![image](https://github.com/loneli-maker/collogeProject/assets/79433596/1a948358-7d85-4dc9-a00a-6ce6dc139f59)


4、管理员可以管理学生、老师、评审员的基本信息；可以分配学生申报的项目给哪些评审员评审，也可以根据评审员所打的分 判断是否通过该创新创业项目。其他小功能可自行摸索。
![image](https://github.com/loneli-maker/collogeProject/assets/79433596/d86d10c3-ae85-4a94-a9ee-3a638641414e)
![image](https://github.com/loneli-maker/collogeProject/assets/79433596/120972bb-35a7-4d6b-841e-dcbba0a6dc5a)
![f7340e647a81f5a3dfd2c76a35fec99](https://github.com/loneli-maker/collogeProject/assets/79433596/cf3e7d0c-889a-449e-830a-c66d6e9c7f28)





#### 写在最后
由于该系统的数据库设计的太烂了。过于急躁，业务逻辑结构太混乱了。导致系统功能不丰富。该系统二次开发的收益不大，建议重新设计数据表，可拷贝该项目的增删改查各层接口（服务端的通用API）、工具类等，用postman工具去调试，也可以拷贝前端代码之类的，也是网上找的什么管理系统的模版。可为你节省大量时间。对web开发（Java web、php web感兴趣的可私信我。共同学习，觉得有用的可以点个赞，上传不易，感谢捐赠。）

