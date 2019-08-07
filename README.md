# mySpringboot
## springboot的资料管理
#### *本项目采用maven作为版本管理工具，以springboot为核心进行开发，数据库使用mysql，dao层使用jpa实现，模板引擎用thymeleaf，结合前端用当下流行的restful架构开发。
-  登录、注册 、验证都由shiro框架管理
-  缓存层用redis
-  虽然没有高的并发量，但还是配置了nginx

### java源代码包结构
- pojo 实体
- config 配置
- exception 异常处理
- realm shiro相关
- dao DAO类
- interceptor 拦截器
- web 控制层
- service Service层
- test 测试类
- util 工具类
### webapp 目录
- css css文件
- img 图片资源
- js js文件
### templates
- thymeleaf 模板文件
