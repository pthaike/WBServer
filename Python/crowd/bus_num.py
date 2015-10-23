#coding:gbk

reader = open("info.txt","r")
dataSet = {}

for line in reader:
    string = line.strip("\n").split("\t")
    if string[1] not in dataSet:
        dataSet[string[1]] = 1
    else:
        dataSet[string[1]] += 1
print "read over"
reader.close()

writer = open("车数.txt","w")
for key,value in dataSet.items():
    writer.write(key+"\t")
    writer.write(str(value)+"\n")
writer.close()
print "write over"