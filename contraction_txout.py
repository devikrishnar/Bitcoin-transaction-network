import csv

in_file = "new_txin.csv"

reader = csv.reader(open(in_file,"rb"))
d = {}
tx = {}
userid = 0
for row in reader:
	ad = row[1]
	userid = row[3]
	if d.has_key(ad) == False:
		d[ad] = userid
	ctx = row[0]
	if tx.has_key(ctx) == False:
		tx[ctx] = ""

out_file = "new_txout.csv"
writer = csv.writer(open(out_file,"wb"))
in_file = "txout.csv"
reader = csv.reader(open(in_file,"rb"))

c1=0
c2=0

for row in reader:
	if long(row[0]) < 171:
		continue
	if tx.has_key(row[0]) == False:
		continue
	if d.has_key(row[1]) == True:
		row.append(d.get(row[1])) 
		c1 = c1 + 1
	else:
		row.append(long(userid)+1)
		userid = long(userid)+1
		c2 = c2 + 1
	writer.writerow(row)

print c1
print c2
