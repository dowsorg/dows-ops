#start /b "D:\Program Files\Nacos\2.1.0\bin\" startup.cmd
#start /b "E:\workspace\redis\Redis-x64-3.2.100\" redis-server.exe
#代码结尾不加pause的原因是，执行完关闭窗口，因为不需要该窗口保留着，免得手动关闭。
#1. start 用来启动一个应用
#2. cmd /k 表示cmd后面的命令执行完后不关闭窗口。如果要在执行完成后关闭窗口可以用/c 
#3. cd /d 表示运行到该目录下
#4. 使用choice命令来延时3秒，也可用ping命令作延时，ping 127.0.0.1 -n 5
#在命令窗口输入choice/? 以查看更多choice命令的用法.

cd /d "D:\Program Files\Nacos\2.1.0\bin\"
start cmd /k startup.cmd
choice /t 3 /d y
cd /d E:\workspace\redis\Redis-x64-3.2.100
start /b redis-server.exe