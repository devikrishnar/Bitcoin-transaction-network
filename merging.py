import csv

in_file = "new_txin.csv"

reader = csv.reader(open(in_file,"rb"))

send_addr = {}
for row in reader:
	userid = row[3]
	txid = row[0]
	if send_addr.has_key(txid) == False:
		send_addr[txid] = userid

reader = csv.reader(open("txtime.csv","rb"))
time_stamp = {}
for row in reader:
	ti = row[1]
	txid = row[0]
	if time_stamp.has_key(txid) == False:
		time_stamp[txid] = ti

reader = csv.reader(open("new_txout.csv","rb"))
writer = csv.writer(open("user_network.csv","wb"))
i=0

for row in reader:
	txid = row[0]
	recv_user = row[3]
	amount = row[2]
	if send_addr.has_key(txid) == True:
		send_user = send_addr.get(txid)
		time = time_stamp.get(txid)
		current_row = [txid,send_user,recv_user,amount,time]
		writer.writerow(current_row)
		i = i + 1


