### 写在前面
已经毕业一年多了，很久没来这里看自己的东西了，收到不少网友关于项目如何部署的帖子，我以为只要代码放上去，即使环境有点偏差，大家也能调试出来，毕竟简单。 为了方便网友部署，博主手把手亲自教。如果觉得有用的可以点个赞，另外也可以+V bashTough ，提供系统架构讲解资料，ER图等资料。一起讨论计算机科学与技术的哪些知识，包含计算机网络、c/Java/php程序语言、数据库设计、web程序开发、 软件系统业务研究、linux应用、shell自动化编程等知识；逐步深入IT信息行业，成为软件行业的拥护者。

## collogecourse
皇家理工大学：大学生创新创业管理系统。这是一个SpingBoot框架写的管理系统，针对大学生创新创业项目的管理，完成了项目的申报、指导、评审等过程。 项目设计技术点：Java、SSM、SpringBoot、MybatisPlus、Jquery、Ajax、Thymeleaf模板、Apache Poi办公文件导入导出。

### 功能介绍
系统功能模块简单(可以个性化定制需求）。
#### 更新说明（2025年3月28日更新，本次更新包含登录界面样式更改、学生自主申报功能增加、最新公告模块新增、评审员打分界面字段优化等，具体更改合并截图如下：
1、最新公告模块功能
![输入图片说明](photo/0328-3.png)
![输入图片说明](photo/0328-6.png)
#### 更新说明（2024年10月22日更新，本次更新为历史性改变，因为增加了数据表字段，优化了SQL语句，文档上传极为重要，故必须改变。后面会继续更新如下功能：
1、老师的二次指导及总结功能。
2、评审员参考学生终版项目文档和指导老师总结做出点评及打分功能。
3、部分页面优化，markdown框架引用，待读取消息功能等。
以上这些功能基本满足了该系统的业务功能。感谢支持！ :fire: ）
1、学生申报界面新增项目文档上传功能（word文档上传），学生选择不小于50兆的word文档作为自己的电子项目计划书给指导老师看，如下图：
![学生自主申报](photo/0328-4.png)
![学生辅助申报](photo/0328-5.png)
2、教师查看学生申报项目情况时，可下载学生上传的word文档，考虑到文档名可能过长，文档名以项目计划书前10位+省略号+后缀名组成，如下图：
![image](https://github.com/loneli-maker/collogeProject/blob/master/photo/教师待指导项目界面.png)
![管理员项目详情界面](https://github.com/loneli-maker/collogeProject/blob/master/photo/管理员项目详情截图.png)
3、部分BUG修复，页面美观性更改，页面布局调整，裁剪了多余的换行及空白部分等。
![项目信息导出功能截图](https://github.com/loneli-maker/collogeProject/blob/master/photo/导出功能截图.png)

#### 更新说明（2024年8月31日更新）
1、登录界面新增管理员注册按钮，由于大多数人不知道管理员的默认账号，故新增这个功能。点击超级用户注册弹出模态框：请输入超级用户密令，密令输入正确才可以注册管理员账号，如下图：
![输入图片说明](photo/08311.jpg)
![输入图片说明](photo/08312.jpg)
2、修复了部分BUG，包含管理员模块删除学生报错，提示500页面，分配评审员报错等BUG。

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
![输入图片说明](photo/0328-1.png)

2、配置文件里的路径需要改，绝对路劲都需要改成你自己的，photo所属的绝对路劲，复制你的路劲/photo，photo不要动，要不然修改头像、图片之类的会空指针。根据打印日志看问题，修改路径。

### 各模块功能
1、学生登录首页和申报项目
![7b5af7197172086beb56f1d158504dc](https://github.com/loneli-maker/collogeProject/assets/79433596/b536b6ef-a052-4c9c-8882-e1465c34f34d)
![image](https://github.com/loneli-maker/collogeProject/assets/79433596/20cc1b8f-e31d-49bf-b696-4d145c63ea26)
2、教师开设项目课题、指导学生申报的项目。
![image](https://github.com/loneli-maker/collogeProject/assets/79433596/361b23c7-887c-431c-955e-7aa9bd9542b7)
3、评审员对项目进行打分 image
![输入图片说明](photo/0328-2.png)
4、管理员可以管理学生、老师、评审员的基本信息；可以分配学生申报的项目给哪些评审员评审，也可以根据评审员所打的分 判断是否通过该创新创业项目。其他小功能可自行摸索。
![image](https://github.com/loneli-maker/collogeProject/assets/79433596/d86d10c3-ae85-4a94-a9ee-3a638641414e)
![image](https://github.com/loneli-maker/collogeProject/assets/79433596/120972bb-35a7-4d6b-841e-dcbba0a6dc5a)
![f7340e647a81f5a3dfd2c76a35fec99](https://github.com/loneli-maker/collogeProject/assets/79433596/cf3e7d0c-889a-449e-830a-c66d6e9c7f28)
### 写在最后
对计算机软件行业感兴趣的，如web开发（Java web、php web）、系统运维（工厂linux服务器运维、docker+K8S运维）、系统实施（服务器搭建部署、需求收集、软件交付物制作）等感兴趣的可私信我。共同学习，觉得有用的可以点个赞，上传不易，感谢捐赠。）
