#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Oct 17 13:28:42 2019

@author: ninn
"""
import sys
root_rpath = sys.argv[1]
file_rdir = sys.argv[2]
import os
assert os.path.exists(root_rpath)

root_apath = os.path.join(os.getcwd(), root_rpath)
with open(file_rdir, "w+") as fd:
    dirs = [root_apath]
    while len(dirs)>0:
        root_adir = dirs.pop()
        for tmp_rdir in os.listdir(root_adir):
            tmp_adir = os.path.join(root_adir, tmp_rdir)
            if os.path.isdir(tmp_adir):
                dirs.append(tmp_adir)
            else:
                if tmp_rdir.endswith("jpg") or tmp_rdir.endswith("bmp"):
                    import shutil
                    target_adir = os.path.join(root_apath, tmp_rdir)
                    shutil.move(tmp_adir, target_adir)
                    fd.write("{}\n".format(tmp_rdir))


    