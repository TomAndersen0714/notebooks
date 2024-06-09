#!/bin/bash
# 此脚本用于git commit, 以及打包完整项目并同步

set -ex

# git repo path
git_path=$1

# default git path
pkg_path=$(basename "$git_path")

# check git path
if [ ! -d "$git_path" ]; then
  echo "git path not exists"
  exit 1
fi

# git commit
git -C "$git_path" add .
git -C "$git_path" commit -m "update $(date)"

# package
zip -r "$pkg_path.zip" "$git_path"