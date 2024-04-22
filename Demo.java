import java.util.ArrayList;

public class Demo
{

   public static void main(String [] args)
   {
      
      // demo an ArrayList of CellStatus elements
      
      // create
      ArrayList<CellStatus> cells = new ArrayList<CellStatus>(5);
      
      // fill
      for (int i = 0; i < 5; i++)
         cells.add(CellStatus.NOTHING);
      
      //display
      for (CellStatus cs : cells)
         System.out.println(cs);
      
      // change some values   
      cells.set(0,CellStatus.AIRCRAFT_CARRIER);
      cells.set(1,CellStatus.AIRCRAFT_CARRIER_HIT);
      cells.set(2,CellStatus.AIRCRAFT_CARRIER_SUNK);

      System.out.println("***************************");
      
      for (CellStatus cs : cells)
         System.out.println(cs);

      System.out.println("***************************");
      
      System.out.println("displaying for computer board");   
      for (CellStatus cs : cells)
         System.out.println(cs.toString().charAt(0));

      System.out.println("***************************");

      System.out.println("displaying for player board");   
      for (CellStatus cs : cells)
         System.out.println(cs.toString().charAt(1));

      // create ArrayList of ArrayLists (think of this as the rows)
      
      ArrayList<ArrayList<CellStatus>> grid = new ArrayList<>(5); 

      System.out.println("***************************");
   
      // now create the columns
        for (int i = 0; i < 5; i++) 
        {
            ArrayList<CellStatus> temp = new ArrayList<>(5);
            for (int j = 0; j < 5; j++)
                temp.add(CellStatus.NOTHING);
            grid.add(temp);
        }
   
      // to get A1
      CellStatus cs = grid.get(0).get(0);
      System.out.println(cs);
      
      // to set B3
      int row = 1;
      int col = 2;
      grid.get(row).set(col, CellStatus.NOTHING_HIT);
      cs = grid.get(row).get(col);
      System.out.println(cs);
      
      
      
   }

}