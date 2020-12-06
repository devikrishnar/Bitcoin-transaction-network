import csv

reader = csv.reader(open("user_network.csv","rb"))

income = 0
outcome = 0
for row in reader:
	if row[1]=="1":
		outcome = outcome + long(row[3])
	if row[2]=="1":
		income = income + long(row[3])
		
print "income = " + str(income)
print "outcome = " + str(outcome)
