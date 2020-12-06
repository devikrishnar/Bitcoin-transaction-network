file1 = open("txlinks.txt","r")
file2 = open("new_txlinks.txt","w")
row = 0;
for line in file1:
	st = line.split("\t")
	if long(st[0]) <= 30048982 and long(st[1]) <= 30048982:
		file2.write(line)
		row = row + 1
print row 
