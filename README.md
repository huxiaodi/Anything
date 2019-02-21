# 德邻E宝

查看java日志

tail -f /app/config/logs/ebpayrun.log

后端JAVA发布.
生产服务器
172.20.120.36
172.20.120.37

```
jar包目录
/app/config/jars

启动命令
sh /app/config/jars/startup.sh

关闭命令
sh /app/config/jars/shutdown.sh

重启命令
sh /app/config/jars/restart.sh

日志位置
tail -f /app/logs/dleb/app.log -n 99
tail -f /app/logs/dleb/error.log -n 99


conf文件夹-平安银行见证宝配置文件

ebpay.jar-德邻e宝springboot包


```

前端代码发布
```
前台发布代码目录
/app/config/vuestatic

打包命令
npm run build

把生成之后的dist目录中的文件拷贝到/app/config/vuestatic
```

