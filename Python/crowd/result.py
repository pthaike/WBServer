#coding:gbk

reader1 = open("星期日.txt","r")
reader2 = open("车数.txt","r")
writer = open("星期日ave.txt","a")


dataSet = {}

for line2 in reader2:
    string2 = line2.strip('\n').split('\t')
    dataSet[string2[0]] = string2[1]
    
for line1 in reader1:
    string1 = line1.strip('\n').split('\t')
    if string1[0] not in dataSet:
        continue
    else:
        l = string1[1].strip('[').strip(']').split(',')
        tmp = dataSet[string1[0]]
        for i in xrange(len(l)):
            l[i] = int(l[i]) / int(tmp)
        writer.write(string1[0] + '\t' + str(l) + '\n')

print "write over"
    