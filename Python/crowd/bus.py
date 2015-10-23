#_*_coding:utf-8_*_

reader = open('SPTCC-20150419.csv','r')
dataSet = {}
#ͳ������
for line in reader:
    string = line.strip("\n").split(",")
    if string[4] != '����':
        continue
    else:
        temp = int(string[2][:2]) - 6
        if string[3] not in dataSet:
            ls = []
            for i in xrange(6,25):
                ls.append(0)
            ls[temp] = 1
            dataSet[string[3]] = ls
        else:
            dataSet[string[3]][temp] += 1
print "read over"
reader.close()
#���
writer = open("������.txt","w")
for key,value in dataSet.items():
    writer.write(key+"\t")
    writer.write(str(value)+"\n")
writer.close()
print "write over"
