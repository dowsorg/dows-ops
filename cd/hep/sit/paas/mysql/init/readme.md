注意
只有容器首次启动时才会执行/docker-entrypoint-initdb.d下的文件
若非首次启动，删除挂在的data目录，再新建data