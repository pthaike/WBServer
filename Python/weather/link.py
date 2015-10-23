
rain = open("rain.txt","r")
link = open("link.txt","w")

dataSet = []
for r in rain:
    r = r.strip("\n").split("\t")
    data = r[0][:8]
    if data in dataSet:
        continue
    dataSet.append(data)
    traffic = open("tra.txt","r")
    for t in traffic:
        t = t.strip("\n").split("\t")
        if r[0][:8] == t[0]:
            data = data + "\t" + t[1] 
    data += '\n'
    traffic.close()
    link.write(data)

print "over"
            