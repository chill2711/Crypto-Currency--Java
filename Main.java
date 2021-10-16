
/**
 * Block Chain CPSC 3555 
 * Creates the block chain along with hash and transaction information
 *
 * @author Coleman Hill
 * @version July 18, 2021
 */
import java.lang.String;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.*;
import java.util.Scanner;

public class Main
{

    public static void Main(){

        Scanner scan = new Scanner(System.in);
        String x;
        int i;

        ArrayList<Block> blockchain = new ArrayList <Block>();
        String [] tmp = new String [1];

        System.out.println("*************************");

        //creation of genesis block
        String [] genesisTransactions = {"Santoshi sent 2 BTC to Robert","Bob sent 25 BTC to Alice","John sent 10 BTC to Cole"};
        Block genesisBlock = new Block(0,genesisTransactions);
        //add to block chain
        blockchain.add(new Block(0,genesisTransactions));

        //COMMENT OUT for testing
        // System.out.println("The hash of genesis block: ");
        // System.out.println(genesisBlock.getBlockHash());

        //creation of 2nd block
        String [] block2Transactions = {"Santoshi sent 2 BTC to Alex","Bob sent 25 BTC to Morgan","John sent 3 BTC to Microsoft"};
        Block block2 = new Block(genesisBlock.getBlockHash(),block2Transactions);
        //add to block chain
        blockchain.add(new Block(genesisBlock.getBlockHash(),block2Transactions));

        //COMMENT OUT for testing
        // System.out.println("The hash of Block 2: ");
        // System.out.println(block2.getBlockHash());

        //creation of 3rd block
        String [] block3Transactions = {"Santoshi sent 2 BTC to Cliff","Brent sent 25 BTC to Sheldon","Daniel sent 3 BTC to Rod"};
        Block block3 = new Block(block2.getBlockHash(),block3Transactions);
        //add to block chain
        blockchain.add(new Block(block2.getBlockHash(),block3Transactions));

        //COMMENT OUT for testing
        // System.out.println("The hash of Block 3: ");
        // System.out.println(block3.getBlockHash());

        while(true){
            System.out.println("1. Add a block: ");
            System.out.println("2. Display a block: ");
            System.out.println("3. Edit a block: ");
            System.out.println("4. Display the whole blockchain: ");

            i = scan.nextInt();
            scan.nextLine();

            if( i ==1 ){
                //Add a block
                //arraylist to store strings entered
                ArrayList<String> list = new ArrayList<String>();
                while(true){
                    System.out.println("Enter the transaction to store in the block or write " + " 'done' to end writing to the block:");
                    x = scan.nextLine();
                    if(x.equals("done")){
                        String [] block4Transactions = new String[list.size()];
                        for(int j = 0; j < block4Transactions.length; j++){
                            block4Transactions[j] = list.get(j);
                        }
                        //creation of new block pointing to previous blockhash & adding to block chain
                        Block block4 = new Block(block3.getBlockHash(),block4Transactions);
                        //add to block chain
                        blockchain.add(new Block(block3.getBlockHash(),block4Transactions));
                        break;    
                    }
                    else{
                        list.add(x);
                    }
                }
            }

            if ( i == 2){
                //Display a block
                System.out.println("Enter the block number you would like to view: ");
                i = scan.nextInt();
                scan.nextLine();

                if( i < blockchain.size()){
                    System.out.println(blockchain.get(i));
                    System.out.println("Block " + i);
                    System.out.println("Previous Hash " + blockchain.get(i).getPreviousHash());
                    System.out.println("Current Hash " + blockchain.get(i).getBlockHash());
                    System.out.println("Transactions " + blockchain.get(i).getTransaction());

                }
                else{
                    System.out.println("The block does not exist in this Blockchain.");
                }
                break;
            }

            if( i == 3){
                //Edit a block
                System.out.println("Enter which block number you would like to edit: ");
                i = scan.nextInt();
                scan.nextLine();
                if ( i < blockchain.size()){
                    System.out.println("Enter the transaction to add to the block");
                    tmp[0] = scan.nextLine();
                    blockchain.set(i,new Block(0,tmp));
                    System.out.println("Is the blockchain valid after changing one block?");
                    if(blockchain.get(i).getPreviousHash() != blockchain.get(i).getBlockHash()){
                        System.out.println("False,the blockchain is not valid.");
                    }
                    else{
                        System.out.println("True, the blockchain is valid.");
                    }
                }
            }

            if ( i == 4){
                //Display the whole blockchain
                System.out.println("Complete blockchain: ");

                for(int j = 0; j < blockchain.size(); j++){
                    System.out.println("Block "+ j + " " + blockchain.get(j));
                }
                break;

            }

        }
    }

}
