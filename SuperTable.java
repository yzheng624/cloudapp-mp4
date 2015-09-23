import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;

import org.apache.hadoop.hbase.TableName;

import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;

import org.apache.hadoop.hbase.util.Bytes;

public class SuperTable {

    String TABLE_NAME = "powers";

   public static void main(String[] args) throws IOException {

      // Instantiate Configuration class
       Configuration conf = HBaseConfiguration.create();

      // Instaniate HBaseAdmin class
       HBaseAdmin admin = HBaseAdmin(conf);
      
      // Instantiate table descriptor class
       HTableDescriptor descriptor = new HTableDescriptor(TableName.valueOf(TABLE_NAME));

      // Add column families to table descriptor
       descriptor.addFamily(new HColumnDescriptor("personal"));
       descriptor.addFamily(new HColumnDescriptor("professional"));

      // Execute the table through admin
       admin.createTable(descriptor);

      // Instantiating HTable class
       HTable table = new HTable(conf, TABLE_NAME);
     
      // Repeat these steps as many times as necessary

	      // Instantiating Put class
              // Hint: Accepts a row name

      	      // Add values using add() method
              // Hints: Accepts column family name, qualifier/row name ,value

       Put put = new Put("row1");
       put.add(Bytes.toBytes("personal"),
               Bytes.toBytes("hero"), Bytes.toBytes("superman"));
       put.add(Bytes.toBytes("personal"),
               Bytes.toBytes("power"), Bytes.toBytes("strength"));
       put.add(Bytes.toBytes("professional"),
               Bytes.toBytes("name"), Bytes.toBytes("clark"));
       put.add(Bytes.toBytes("professional"),
               Bytes.toBytes("xp"), Bytes.toBytes("100"));
       table.put(put);

       put = new Put("row2");
       put.add(Bytes.toBytes("personal"),
               Bytes.toBytes("hero"), Bytes.toBytes("batman"));
       put.add(Bytes.toBytes("personal"),
               Bytes.toBytes("power"), Bytes.toBytes("money"));
       put.add(Bytes.toBytes("professional"),
               Bytes.toBytes("name"), Bytes.toBytes("bruce"));
       put.add(Bytes.toBytes("professional"),
               Bytes.toBytes("xp"), Bytes.toBytes("50"));
       table.put(put);

       put = new Put("row3");
       put.add(Bytes.toBytes("personal"),
               Bytes.toBytes("hero"), Bytes.toBytes("wolverine"));
       put.add(Bytes.toBytes("personal"),
               Bytes.toBytes("power"), Bytes.toBytes("healing"));
       put.add(Bytes.toBytes("professional"),
               Bytes.toBytes("name"), Bytes.toBytes("logan"));
       put.add(Bytes.toBytes("professional"),
               Bytes.toBytes("xp"), Bytes.toBytes("75"));
       table.put(put);

      // Save the table
	
      // Close table
       // table.close();

      // Instantiate the Scan class
       Scan scan = new Scan();
     
      // Scan the required columns
       scan.addColumn(Bytes.toBytes("personal"), Bytes.toBytes("hero"));

      // Get the scan result
       ResultScanner scanner = table.getScanner(scan);

      // Read values from scan result
      // Print scan result
       for (Result result : scanner) {
           System.out.print(result);
       }
 
      // Close the scanner
       scanner.close();
   
      // Htable closer
        table.close();
   }
}

