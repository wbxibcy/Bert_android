import mysql.connector

# 创建连接
cnx = mysql.connector.connect(
    host="localhost",
    user="root",
    password="123456"
)

# 创建游标
cursor = cnx.cursor()

# 创建数据库
create_database_query = "CREATE DATABASE mytest"
cursor.execute(create_database_query)

# 选择数据库
use_database_query = "USE mytest"
cursor.execute(use_database_query)

# 创建表格
create_table_query = """
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    age INT
)
"""
cursor.execute(create_table_query)

# 插入数据
insert_data_query = """
INSERT INTO user (name, age)
VALUES (%s, %s)
"""
data = [("John", 25), ("Jane", 30), ("Mark", 35)]
cursor.executemany(insert_data_query, data)

# 提交更改
cnx.commit()

# 执行查询
select_query = "SELECT * FROM user"
cursor.execute(select_query)
result = cursor.fetchall()

# 打印结果
for row in result:
    print(row)

# 关闭游标和连接
cursor.close()
cnx.close()
