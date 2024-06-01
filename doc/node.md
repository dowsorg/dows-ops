
1.首先用下面的命令更新包库:
sudo apt update
2.使用curl命令安装NVM:
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.7/install.sh | bash
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.38.0/install.sh | bash
注意:如果没有curl，可以通过运行命令来安装该实用程序:sudo apt install curl
或者，你可以使用wget并运行以下命令:
wget -q0- https://raw.githubusercontent.com/nvm-sh/nvm/v0.38.0/install.sh | bash
3.关闭并重新打开终端，让系统识别更改或运行命令:
source ~/.bashrc
4.然后，验证是否成功安装了NVM:
nvm --version
5.在升级Node.js之前，检查你在系统上运行的是哪个版本:
nvm ls
6.现在你可以通过以下方式查看新发布的版本:
nvm ls-remote
7.要安装最新版本，对特定的Node.js版本使用nvm命令:
nvm install [version.number]

curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.7/install.sh | bash
source ~/.bashrc
nvm --version
nvm install $NODE_VERSION
nvm use $NODE_VERSION



npm config set prefix "E:\workspaces\nvm\nodejs\node_global"
npm config set cache "E:\workspaces\nvm\nodejs\node_cache"

之后需要配置一下node_global的环境变量，在环境变量中找到path---->新建----->node_global的文件位置。进行配置。

由于npm是国外的服务器，所以下载比较慢，我们需要去安装淘宝镜像。

输入npm install -g cnpm --registry=https://registry.npm.taobao.org(注意此命令需要再管理员打开控制台)

然后使用npm install -g yarn 来进行yarn安装，如果你之前没有使用过yarn到这里恭喜你已经安装成功了。



如果在命令提示符（cmd）环境中可以识别 yarn -v 命令，但在 PowerShell 环境中无法识别，可能是因为 PowerShell 的执行策略限制了运行脚本的能力。

PowerShell 有一个执行策略（Execution Policy）的设置，用于控制允许运行哪些类型的脚本。默认情况下，PowerShell 的执行策略设置为 “Restricted”，即不允许运行任何脚本。

要在 PowerShell 中运行 yarn -v 命令，你需要将执行策略设置为允许运行脚本。你可以使用以下步骤来更改 PowerShell 的执行策略：

1、 以管理员身份打开 PowerShell 终端。
2、 运行以下命令以查看当前的执行策略设置：
Get-ExecutionPolicy
1
3、 如果返回结果为 “Restricted”，则表示当前策略不允许运行脚本。你可以使用以下命令来更改执行策略：
Set-ExecutionPolicy RemoteSigned
1
这将允许运行本地签名的脚本。






安装quasar
npm i -g @quasar/cli
