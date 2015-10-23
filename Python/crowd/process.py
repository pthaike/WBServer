#coding:gbk

reader = open("结果/星期日ave.txt","r")

dataSet = {}

for line in reader:
    string = line.strip('\n').split('\t')
    sl = string[1].strip('[').strip(']').strip('\n').split(',')
   
    m = int(sl[0]) + 1
    for i in xrange(1,len(sl)):
        if m < int(sl[i]):
            m = int(sl[i])
    for i in xrange(len(sl)):
        sl[i] = int(sl[i])*1.0 / m
        sl[i] = int(sl[i]*100)
    dataSet[string[0]] = sl

writer = open("结果/日.txt","w")
for key,value in dataSet.items():
    writer.write(key + '\t' + str(value) + '\n')
print ("write over")