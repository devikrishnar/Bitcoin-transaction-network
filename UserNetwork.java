import java.io.*;
import java.util.*;
import java.lang.*;

class Transaction{
	String id;
	long amount;
	ArrayList<String> receivingUserId;
	String sendingUserId;
	String timestamp;

	public Transaction(String id,long amount,String receivingUserId,String sendingUserId,String timestamp){
		this.id = id;
		this.amount = amount;
		this.receivingUserId = new ArrayList<String>();
		this.receivingUserId.add(receivingUserId);
		this.sendingUserId = sendingUserId;
		this.timestamp = timestamp;
	}
	
	public void addReceiver(String Id){
		this.receivingUserId.add(Id);
	}
	public String getSenderId(){
		return sendingUserId;
	} 
	public ArrayList<String> getReceiverId(){
		return receivingUserId;
	}
	public String getTransactionId(){
		return id;
	}
	public double getAmount(){
		return (double)(amount)/10000;
	}
}

class User{
	ArrayList<String> incomingTransaction;
	ArrayList<String> outgoingTransaction;
	Set<String> userSet;
	long degree;

	public User(){
		incomingTransaction = new ArrayList<String>();
		outgoingTransaction = new ArrayList<String>();
		userSet = new HashSet<String>();
		degree = 0;
	}

	public void calculateDegree(){
		degree = userSet.size();
	}

	public void addIncomingTransaction(String t,HashMap<String,Transaction> tNetwork){
		incomingTransaction.add(t);
		userSet.add(tNetwork.get(t).getSenderId());
	}
	public void addOutgoingTransaction(String t,HashMap<String,Transaction> tNetwork){
		outgoingTransaction.add(t);
		userSet.addAll(tNetwork.get(t).getReceiverId());
	}
	public void printUser(){
		System.out.println("New User Started");
		for(String x:incomingTransaction){
			System.out.println(x);
		}
	}
	
	
}

public class UserNetwork{
	public static void main(String[] a){
		HashMap<String,User> userNetwork = new HashMap<String,User>();
		HashMap<String,Transaction> transactionNetwork = new HashMap<String,Transaction>();
		User sender,receiver;
		Transaction t;
		String csvFile = "user_network.csv";
        	BufferedReader br = null;
        	String line = "";
        	String csvSplitBy = ",";

        	try {

            		br = new BufferedReader(new FileReader(csvFile));
            		while ((line = br.readLine()) != null) {

                		String[] row = line.split(csvSplitBy);
                		String tId = row[0];
                		Long amount = Long.parseLong(row[3]);
                		String sendingUserId = row[1];
                		String receivingUserId = row[2];
                		String timestamp = row[4];
                		
                		if(transactionNetwork.containsKey(tId) == false){
                			t = new Transaction(tId,amount,receivingUserId,sendingUserId,timestamp);
                			transactionNetwork.put(tId,t);
                		}
                		else{
                			t = transactionNetwork.get(tId);
                			t.addReceiver(receivingUserId);
                			transactionNetwork.put(tId,t);
                		}
                		
                		if(userNetwork.containsKey(sendingUserId) == true){
                			sender = userNetwork.get(sendingUserId);
                			sender.addOutgoingTransaction(tId,transactionNetwork);
                			userNetwork.put(sendingUserId,sender);
                		}
                		else{
                			sender = new User();
                			sender.addOutgoingTransaction(tId,transactionNetwork);
                			userNetwork.put(sendingUserId,sender);
                		}
                		
                		if(userNetwork.containsKey(receivingUserId) == true){
                			receiver = userNetwork.get(receivingUserId);
                			receiver.addIncomingTransaction(tId,transactionNetwork);
                			userNetwork.put(receivingUserId,receiver);
                		}
                		else{
				   	receiver = new User();
				   	receiver.addIncomingTransaction(tId,transactionNetwork);
				   	userNetwork.put(receivingUserId,receiver);
                		}

				System.out.println(tId);		
                		
            		}
            		User temp = userNetwork.get("1");
			temp.printUser();

        	}catch (FileNotFoundException e) {
            		e.printStackTrace();
        	}catch (IOException e) {
            		e.printStackTrace();
        	}finally {
            		if (br != null) {
                		try {
                    			br.close();
                		} catch (IOException e) {
                    			e.printStackTrace();
                		}
            		}
        	}

	}
}
