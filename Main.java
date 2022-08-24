
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        int x=0;
        
        GraphMatrix g;
         
         boolean application = true ;       
        
         g = new GraphMatrix(240);
         g.readfromFile("Creator.txt");
         System.out.println(g); 
         //g.readfromFile("Creator.txt");
        Scanner scanner  = new Scanner(System.in);
        
        System.out.println("Wellcome to the path visualisation : ");
         while(x<8){
             System.out.println("1.Is there a path");
             System.out.println("2.Print all paths shorter or equal to a length");
             System.out.println("3.Lenght of shortest path");
             System.out.println("4.Number of paths ");
             System.out.println("5.Print BFS path");
             System.out.println("6.Print DFS path");
             System.out.println("7.Number of vertices in component");
             System.out.println("8.Exit");
             System.out.print("Enter number of option:");
             x= scanner.nextInt();
             System.out.println(" ");
             switch(x){
                 case 1:
                    System.out.println("Enter the first name : ");
         
                    String name1 = scanner.next();

                    System.out.println("Enter the second name : ");

                    String name2 = scanner.next();

                    System.out.println(g.isThereAPath(name1, name2));
                    break;
                 case 2:
                    System.out.println("Enter path length : ");
         
                    int length = scanner.nextInt();

                    System.out.println("Enter number of vertex  : ");

                    int noVertex = scanner.nextInt();

                    System.out.println("Enter the name : ");

                    String name = scanner.next();

                    g.AllPathsShorterThanEqualTo(length, noVertex, name);
                    break;
                 case 3:
                    System.out.println("Enter the first name : ");
         
                    String name3 = scanner.next();

                    System.out.println("Enter the second name : ");

                    String name4 = scanner.next();

                    System.out.println(g.ShortestPathLengthFromTo(name3, name4)); 
                    break;
                 case 4:
                    System.out.println("Enter the first name : ");
         
                    String name6 = scanner.next();

                    System.out.println("Enter the second name : ");

                    String name7 = scanner.next();

                    System.out.println(g.noOfAllPaths(name6, name7));
                    break;
                 case 5:
                    System.out.println("Enter the first name : ");
         
                    String name8 = scanner.next();

                    System.out.println("Enter the second name : ");

                    String name9 = scanner.next();

                    g.BFSfromTo(name8, name9);
                    
                    System.out.println("\nBFS");
         
                    break;
                 case 6:
                    System.out.println("Enter the first name : ");
         
                    String name10 = scanner.next();

                    System.out.println("Enter the second name : ");
                            
                    String name11 = scanner.next();

                    g.DFSfromTo(name10, name11);

                    System.out.println("\nDFS");
                    break;
                 case 7:
                    System.out.println("Enter a name : ");
         
                    String n = scanner.next();

                    g.NoOfVerticesInComponent(n);
                    break;
                 case 8:
                     System.out.println("Goodbye :)");
                     //exit=true;
                     break;
             }
         }
        
 
         

         
         
         
         
          
         
         //System.out.println("Enter a name for seeing pathways : ");
         
         //String name5 = scanner.next();
         
         //g.otherpathways(name5);

         
         
         
         
          
         
         
         
          
         
         
         
             //System.out.println("Do you want to continue (Y or N) ?");
             
             //String answer = scanner.next();
             
             //if (answer.equals("Y")){
                 
             //    application =  true;
                 
             //}
             
             //else if (answer.equals("N")) {
                 
             //    application = false;
                 
             //}
         
        //}
    }
        
}
