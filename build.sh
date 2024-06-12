#!/bin/bash
# 此脚本用于提交 git commit, 以及打包完整项目并同步

set -ex

# get parameters
git_path=${1:-"."}
option=${2:-"commit"}

# check parameters
if [ ! -d "$git_path" ]; then
    echo "git path not exist"
    exit 1
fi

if [ "$option" != "commit" ] && [ "$option" != "pack" ]; then
    echo "option must be commit or pack"
    exit 1
fi

# define functions
function git_commit() {
    # git commit
    git -C "$git_path" add .
    git -C "$git_path" commit -m "update $(date)"
}

function zip_package() {
    # define variables
    pkg_file=$(basename "$git_path")_$(date +%Y%m%d%H%M%S).zip
    pkg_file_path="./output/$pkg_file"

    # remove old package
    rm -f "$pkg_file"

    # zip packing and overwrite old package
    zip -r "$pkg_file" "$git_path"
    mv "$pkg_file" "$pkg_file_path"
}

# main
# choose function to execute depend on option using case statement
case $option in
commit)
    git_commit
    ;;
pack)
    zip_package
    ;;
esac