#!/bin/bash
# 此脚本用于git commit, 以及打包完整项目并同步

set -ex

# get parameters
git_path=${1:-"."}
option=${2:-"commit"}

# check parameters
if [ ! -d "$git_path" ]; then
    echo "git path not exist"
    exit 1
fi

if [ "$option" != "commit" ] && [ "$option" != "zip" ]; then
    echo "option must be commit or zip"
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
    pkg_path=$(basename "$git_path")

    # remove old package
    rm -f "$pkg_path.zip"

    # zip packing and overwrite old package
    zip -r "$pkg_path.zip" "$git_path"
}

# choose function to execute depend on option using case statement
case $option in
commit)
    git_commit
    ;;
zip)
    zip_package
    ;;
esac