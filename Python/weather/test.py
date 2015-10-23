#_*_coding:utf-8_*_
#从气象数据中抽取超过警戒线的数据
rfile = open("data.txt","r")
weather = open("weather.txt","w")
wind = open("wind.txt","w")
rain = open("rain.txt","w")

for line in rfile:
    line2 = line.strip("\n").split("\t")
    if line2[3]!="////" and float(line2[3])>=30.0:
        weather.write(line2[0]+"\t"+line2[1]+'\t'+line2[2]+'\t'+line2[3]+'\n')
    if line2[5]!='////' and float(line2[5])>=10:
        wind.write(line2[0]+"\t"+line2[1]+'\t'+line2[2]+'\t'+line2[5]+'\n')
    if line2[6]!='////' and float(line2[6])>=10:
        rain.write(line2[0]+"\t"+line2[1]+'\t'+line2[2]+'\t'+line2[6]+'\n')
print "over"
    