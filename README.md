## 写在前面
已经毕业一年多了，很久没来这里看自己的东西了，收到不少网友关于项目如何部署的帖子，我以为只要代码放上去，即使环境有点偏差，大家也能调试出来，毕竟简单。
为了方便网友部署，博主手把手亲自教。如果觉得有用的可以点个赞，另外也可以+V BashTough     ，一起讨论计算机科学与技术的哪些知识，包含计算机网络、c/Java/php程序语言、数据库设计、web程序开发、
软件系统业务研究、linux应用、shell自动化编程等知识；逐步深入IT信息行业，成为软件行业的拥护者。
##### 微信捐赠
![c9052308ff408dfd121a89bd09ab0e5](https://github.com/loneli-maker/collogeProject/assets/79433596/895b2830-f532-4e67-b88b-e7d7ec5d5346)

# collogecourse
南昌交通学院：大学生创新创业管理系统。这是一个SpingBoot框架写的管理系统，针对大学生创新创业项目的管理，完成了项目的申报、指导、评审等过程。 项目设计技术点：Java、SSM、SpringBoot、MybatisPlus、Jquery、Ajax、Thymeleaf模板、Apache Poi办公文件导入导出。
#### 功能介绍
系统功能模块简单，




#### 安装教程
### 本地手工部署
#### idea安装与配置
   1、下载idea工具，工具里面可以直接下载JDK，或者导入JDK1.8。
   ![image](https://github.com/loneli-maker/collogeProject/assets/79433596/fac0886d-f66f-4632-99c2-c5e066e2e52c)

   2、下载maven(下载3.6版本是最好的，别下最新的，最新的有些依赖可能找不到，如果pom.xml文件里仍有依赖报红，多加载几次，结合csdn、博客园解决，一般不会卡太久），并配置阿里云镜像，配置仓库路径，配置环境变量。
   ![image](https://github.com/loneli-maker/collogeProject/assets/79433596/5ecba29f-5ca6-41ed-a554-edba21dfebc7)

![image](https://github.com/loneli-maker/collogeProject/assets/79433596/d53a08fb-5f04-4d8a-9b5f-1c02ef67b8ae)


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

2、配置文件里的路径需要改，绝对路劲都需要改成你自己的，photo所属的绝对路劲，复制你的路劲/photo，photo不要动，要不然修改头像、图片之类的会空指针。根据打印日志看问题，修改路径。
![image](https://github.com/loneli-maker/collogeProject/assets/79433596/cc1c1625-e44d-4c3b-adf9-c44a65209861)

![image](https://github.com/loneli-maker/collogeProject/assets/79433596/1718e36b-ea34-4a65-b3cb-9736349febe0)
### 各模块功能
1、学生申报项目
![image](https://github.com/loneli-maker/collogeProject/assets/79433596/20cc1b8f-e31d-49bf-b696-4d145c63ea26)
2、教师开设项目课题、指导学生申报的项目。
![image](https://github.com/loneli-maker/collogeProject/assets/79433596/361b23c7-887c-431c-955e-7aa9bd9542b7)
3、评审员对项目进行打分
![image](https://github.com/loneli-maker/collogeProject/assets/79433596/1a948358-7d85-4dc9-a00a-6ce6dc139f59)


4、管理员可以管理学生、老师、评审员的基本信息；可以分配学生申报的项目给哪些评审员评审，也可以根据评审员所打的分 判断是否通过该创新创业项目。其他小功能可自行摸索。
![image](https://github.com/loneli-maker/collogeProject/assets/79433596/d86d10c3-ae85-4a94-a9ee-3a638641414e)
![image](https://github.com/loneli-maker/collogeProject/assets/79433596/8d8b4f6a-845b-4782-9ea7-eb286725c22e)



#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
