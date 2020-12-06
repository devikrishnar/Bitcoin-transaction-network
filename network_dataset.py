import csv
in_file1 = "new_txin.csv"
in_file2 = "new_txout.csv"
reader1 = csv.reader(open(in_file1,"rb"))
reader2 = csv.reader(open(in_file2,"rb"))
d = {}
userid = 0
for row in reader:
	tid = row[0]
	userid = row[3]
	if d.has_key(ad) == False:
		d[tid] = userid


