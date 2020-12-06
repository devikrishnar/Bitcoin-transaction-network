import csv

in_file = "txin.csv"
out_file = "mapping.csv"

reader = csv.reader(open(in_file,"rb"))
writer = csv.writer(open(out_file,"wb"))
userid = 0
new_userid = 0
for row in reader:
	if row[0] != userid:
		userid = row[0]
		new_userid = new_userid + 1
	row.append(new_userid)
	writer.writerow(row)

	
	

