file1 = open("txin.txt","r")
file2 = open("txout.txt","r")

u1 = 0
u2 = 0

for line in file1:
	s = line.split("\t")
	u1 = s[0]

for line in file2:
	s= line.split("\t")
	u2 = s[0]
	
print u1
print u2
