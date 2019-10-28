class SuffixChanger:
	origin_apath = ""
	origin_suffix = ""

	target_apath = ""
	target_suffix = ""

	list_adir = ""
	
	def change(self):
		
		import utils
		utils.mk
		
		import os,shutil
		with open(self.list_adir, "r") as fd:
			for rdir in fd:
				basename = os.path.basename(rdir).split(".")[0]
				origin_rdir = basename+self.origin_suffix
				origin_adir = os.path.join(self.origin_apath,origin_rdir)
				target_rdir = basename+self.target_suffix
				target_adir = os.path.join(self.target_apath, target_rdir)
				shutil.copy(origin_adir, target_adir)

class CASIAIrisV1(SuffixChanger):
	origin_apath = "/home/ninn/bishe/gan/git/pix2pix-tensorflow/CASIA-IrisV1/facades_test/images/"
	origin_suffix = "-outputs.png"
	target_apath = "/home/ninn/bishe/iris/git/Iris_Osiris/data/CASIA-IrisV1-pix2pix/"
	target_suffix = ""