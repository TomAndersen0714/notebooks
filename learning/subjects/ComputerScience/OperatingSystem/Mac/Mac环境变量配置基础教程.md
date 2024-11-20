# Mac 环境变量配置基础教程

MacOS 下一般配置有多个 Shell，如 Bash、ZSH 等，不同的 Shell 其创建 Terminal 时使用的环境变量配置文件也不尽相同，但一般都会读取并执行脚本文件 `/etc/profile` 来加载系统级环境变量，而用户级别环境变量，一般都会在各自 Shell 的 Home 路径下配置，即 `Bash` 的 `~/.bash_profile`，`ZSh` 的 `~/.zprofile`，以及 SH 的 `~/.profile` 等。

## 配置系统环境变量

为了尽量统一控制系统级别环境变量，同时支持可插拔，因此本文参考了 Ubuntu 的 Bash Shell 加载方式，通过修改 `/etc/profile` 脚本来实现。

在 `/etc/profile` 脚本中增加下列内容（如果没有写入权限，建议先用 sudo 来进行 chmod +w，之后再执行 chmod -w 还原权限）：
```bash
if [ -d /etc/profile.d ]; then
  for i in /etc/profile.d/*.sh; do
    if [ -r $i ]; then
      . $i
    fi
  done
  unset i
fi
```

然后创建 `/etc/profile.d` 文件夹，并在其中增加需要加载环境变量的 sh 脚本，如：
```bash
# /etc/profile.d/
$ tree /etc/profile.d/
/etc/profile.d/
└── env.sh

# env.sh
eval "$(/opt/homebrew/bin/brew shellenv)"
```

## 修改用户环境变量

用户级别环境变量，一般都会在各自 Shell 的 Home 路径下配置，即 `Bash` 的 `~/.bash_profile`，`ZSh` 的 `~/.zprofile`，以及 SH 的 `~/.profile` 等。

因为不同 Shell 的用户路径和环境变量配置脚本不同，因此可能无法实现统一配置，目前还是需要在各自的脚本文件中分别多次配置。

当然，也可以将相同的配置抽离成一个公共的脚本文件，每次在配置时，都执行其脚本即可。

## 验证结果

关闭所有 Terminal 后重启 Shell，然后验证环境变量是否配置成功。

```bash
brew help
```