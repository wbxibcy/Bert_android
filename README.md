# Bert_android

一个平平无奇的情感分析app，app端使用kotlin语言和Jatpack compose框架，后端使用python和flask框架，情感分析模型为bert模型
## 使用方法

后端命令：

```shell
export FLASK_APP=flask_tra
flask run -h 0.0.0.0
```

*！*需要根据ip地址来更改安卓端代码

MainActivity.kt中的BASE_URL需要更改

## 追加

使用了`docker`拉取了**MySQL**数据库(第一选择是TiDB，但是项目依旧不维护了，因此作罢)

```shell
docker pull mysql:latest
docker run -itd --name mysql --restart=always -p 3306:3306 -v /d/work/little-toy/Bert_android/data/mysql/log:/var/log/mysql -v /d/work/little-toy/Bert_android/data/mysql/data:/var/lib/mysql -v /d/work/little-toy/Bert_android/data/mysql/conf:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=123456 mysql
```

