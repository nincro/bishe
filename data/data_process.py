#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Oct 24 16:25:36 2019

@author: ninn
"""

class Processer:
    origin_apath = ""
    origin_suffix = ""
    
    target_apath = ""
    target_suffix = ""
    
    imglist_adir = ""
    
    
    def process(self):
        return
    pass

class Preprocesser(Processer):
    
    def _meanNormalize(self, origin_adir, target_adir):
        import os, utils
        target_apath = os.path.dirname(target_adir)
        utils.mkapath(target_apath)
        
        import cv2
        print("[.]{}".format(origin_adir))
        try: origin = cv2.imread(origin_adir)
        except:
            print("[x]{}".format(origin_adir))
            return
        print("[o]{}".format(origin_adir))
        
        import numpy as np
        maxv = np.max(origin)
#        print("maxv:",maxv)
        minv = np.min(origin)
#        print("minv:",minv)
        rangev = maxv - minv
        diff = 255-rangev
#        print("rangev:",rangev)
        newimg = origin / 255 * rangev + diff
#        print(origin)
#        print(newimg)
#        exit(0)
        
        print("[.] write to {}".format(target_adir))
        try: cv2.imwrite(target_adir, newimg)
        except:
            print("[x] write to {}".format(target_adir))
        print("[o] write to {}".format(target_adir))
        
        
        
        return
    
    def normalize(self):
        import os
        with open(self.imglist_adir) as fd:
            for rdir in fd:
                basename = os.path.basename(rdir).split(".")[0]
                origin_rdir = basename + self.origin_suffix
                target_rdir = basename + self.target_suffix
                origin_adir = os.path.join(self.origin_apath, origin_rdir)
                target_adir = os.path.join(self.target_apath, target_rdir)
                self._meanNormalize(origin_adir, target_adir)
        return
    pass

class PreprocesserCASIAIrisV2(Preprocesser):
    origin_apath = "/home/ninn/bishe/iris/git/Iris_Osiris/data/CASIA-IrisV2/"
    origin_suffix = ".bmp"
    target_apath = "/home/ninn/bishe/iris/git/Iris_Osiris/data/CASIA-IrisV2-meanNormalized/"
    target_suffix = origin_suffix
    imglist_adir = "/home/ninn/bishe/iris/git/Iris_Osiris/data/list-CASIA-IrisV2.txt"
    
    def process(self):
        self.normalize()
        return
    
    pass

class CASIADistanceResizer(Preprocesser):
    origin_apath = "/home/ninn/bishe/iris/git/Iris_Osiris/data/CASIA-Iris-Distance/"
    origin_suffix = ".jpg"
    target_apath = "/home/ninn/bishe/iris/git/Iris_Osiris/data/CASIA-Iris-Distance/resized/"
    target_suffix = origin_suffix
    
    def _resize(self, a_adir, target_adir, w=640, h=480):
        import cv2
        print("[.] read {}".format(a_adir))
        try:
            a = cv2.imread(a_adir)
        except:
            print("[x] read {}".format(a_adir))
            return
        print("[o] read {}".format(a_adir))
            
        target = cv2.resize(a, (w,h), interpolation=cv2.INTER_CUBIC)
        print("[.] write {}".format(target_adir))
        try:
            cv2.imwrite(target_adir, target)
        except:
            print("[x] write {}".format(target_adir))
            return
        print("[o] write {}".format(target_adir))
        
        
    
    def process(self):
        import utils
        utils.mkapath(self.target_apath)
        import os,glob
        if self.imglist_adir == "":
            
            absrdir = "*"+self.origin_suffix
            absadir = os.path.join(self.origin_apath, absrdir)
            adirs = glob.glob(absadir)
            for adir in adirs:
                basename = os.path.basename(adir).split(".")[0]
                a_adir = adir
                target_rdir = basename+self.target_suffix
                target_adir = os.path.join(self.target_apath, target_rdir)
                self._resize(a_adir, target_adir)
        
        return
    pass

def process(processer: Processer):
    processer.process()
    return

if __name__ == '__main__':
    processer = CASIADistanceResizer()
    process(processer)