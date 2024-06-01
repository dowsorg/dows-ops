# java_init.bat
# 注意文件换行符是windows系统下的(CR LF),文件编码是ANSI
# path变量追加这个可以拓展到tomcat,mysql等使用

@echo off
set regpath=HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Control\Session Manager\Environment

set java=E:\workspaces\java\jdk\jdk-17.0.10

set git=E:\workspaces\java\git\2.43.0

set maven=E:\workspaces\java\maven\apache-maven-3.9.6

set go=D:\Program Files\Go\go1.19
set gopath=E:\workspace\go

set nvm=E:\workspaces\nvm
set nvm_symlink=E:\workspaces\nvm\nodejs
set npm = E:\workspaces\nvm\nodejs\npm

echo.
echo ************************************************************
echo *                                                          *
echo *                   系统环境变量设置             *
echo *                                                          *
echo ************************************************************
echo.
echo === 准备设置环境变量: JAVA_HOME=%java%
echo.
echo === 准备设置环境变量: CLASSPATH=.;%%JAVA_HOME%%\lib;%%JAVA_HOME%%\lib\tools.jar;%%JAVA_HOME%%%\lib\dt.jar;
echo.
echo === 准备设置环境变量: PATH=%%JAVA_HOME%%\bin;%%JAVA_HOME%%\jre\bin
echo === 注意: PATH会追加在最前面
echo.
set /P EN=请确认后按 回车键 开始设置!
echo.
echo.
echo === 新创建环境变量 ===
setx "JAVA_HOME" "%java%" 
setx "GIT_HOME" "%git%"
setx "MYSQL_HOME" "%mysql%"
setx "MAVEN_HOME" "%maven%"
setx "GO_HOME" "%go%"
setx "NVM_HOME" %nvm%
setx "NVM_SYMLINK" %nvm_symlink%
#setx "NPM_HOME" %npm%

echo.
echo.
echo === 新创建环境变量 CLASSPATH=%%JAVA_HOME%%\lib;%%JAVA_HOME%%\lib\tools.jar;%%JAVA_HOME%%\lib\dt.jar;
setx "CLASSPATH" ".;%%JAVA_HOME%%\lib;%%JAVA_HOME%%\lib\tools.jar;%%JAVA_HOME%%\lib\dt.jar;"
setx "GOPATH" "%gopath%"
echo.
echo.
echo === 新追加环境变量(追加到最前面) PATH=%%JAVA_HOME%%\bin
#::wmic ENVIRONMENT where "name='path' and username='<system>'" set VariableValue="%%JAVA_HOME%%\bin;%%JAVA_HOME%%\jre\bin;%path%"

# set path_=%Path%
# setx "PATH" "%%JAVA_HOME%%\bin;%%JAVA_HOME%%\jre\bin;%%GIT_HOME%%\bin;%%MYSQL_HOME%%\bin;%%MAVEN_HOME%%\bin;%path_%;"
setx "PATH" "%%JAVA_HOME%%\bin;%%JAVA_HOME%%\jre\bin;%%GIT_HOME%%;%%GIT_HOME%%\bin;%%GIT_HOME%%\cmd;%%MYSQL_HOME%%\bin;%%MAVEN_HOME%%\bin;%%GO_HOME%%\bin;%%NVM_HOME%%;;%%NPM_HOM%%;%%NVM_SYMLINK%%;

echo.
echo === 请按任意键退出! 
pause>nul