#coding:utf-8
#处理交通事故的文件

traffic = open("traffic.txt","r")
tra = open("tra.txt","w")

for line in traffic:
    line2 = line.strip("\n").split("\t")
    line3 = line2[4].split("/")
    line3[2] = line3[2].split(" ")[0]   
    if int(line3[1]) < 10:
        line3[1] = '0' + line3[1]
    if int(line3[2]) < 10:
        line3[2] = '0' + line3[2]
    data = line3[0] + line3[1] + line3[2]
    
    tra.write(data + '\t' + line2[3] + '\n')
print "over"