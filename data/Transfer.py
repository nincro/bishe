#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Oct 17 13:29:15 2019

@author: ninn
"""

class Transferer:
    origin_apath = ""
    target_apath = ""
    
        

    def transfer(self, copy=True):
        import utils
        utils.mkapath(self.target_apath)
        import os,shutil
        dirs = [self.origin_apath]
        while len(dirs)>0 :
            tmpdir = dirs.pop()
            if os.path.isdir(tmpdir):
                tmpdirs = os.listdir(tmpdir)
                tmproot = tmpdir
                for tmpdir in tmpdirs:
                    dirs.append(os.path.join(tmproot, tmpdir))
            else:
                basename = os.path.basename(tmpdir)
                if basename.endswith(".bmp"):
                    origin_adir = os.path.join(self.origin_apath, tmpdir)
                    target_adir = os.path.join(self.target_apath, basename)
                    if copy:
                        self._copyto(origin_adir, target_adir)
                    else:
                        self._moveto(origin_adir, target_adir)
        return
    def _copyto(self, origin_adir, target_adir):
        import shutil
        print("[.]{}".format(origin_adir))
        try:
            shutil.copyfile(origin_adir, target_adir)
            print("[o]{}".format(origin_adir))
        except:
            print("[x]{}".format(origin_adir))
        return
    def _moveto(self, origin_adir, target_adir):
        import shutil
        print("[.]{}".format(origin_adir))
        try:
            shutil.move(origin_adir, target_adir)
            print("[o]{}".format(origin_adir))
        except() as e:
            print("[x]{}".format(origin_adir))
            print(e)
        return

class CASIAV1(Transferer):
    origin_apath = "/home/ninn/bishe/iris/git/Iris_Osiris/data/CASIA-IrisV1/"
    target_apath = "/home/ninn/bishe/gan/git/pix2pix-tensorflow/CASIA-IrisV1/origin/"

class CASIAV2Mover(Transferer):
    origin_apath = "/home/ninn/bishe/iris/git/Iris_Osiris/data/CASIA-IrisV2/"
    target_apath = "/home/ninn/bishe/iris/git/Iris_Osiris/data/CASIA-IrisV2/"

    def transfer(self):
        super().transfer(copy=False)
        return

if __name__ == '__main__':
    data = CASIAV2Mover()
    data.transfer()
