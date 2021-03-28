/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PVC;

import static java.lang.Integer.min;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextInputDialog;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import java.io.*; 
import java.time.Duration;
import java.time.Instant;
import java.util.*; 
import java.util.LinkedList; 
import static javafx.application.Application.launch;
import javafx.stage.Stage;
/** 
 * main 
 * **/ 
public class PVC extends Application {
    Pane root2 = new Pane();  
     int seq[];
     int seqf [];
    public int i;
    public Noeud selectedNoeud;
     ArrayList<Noeud> myNodes = new ArrayList<Noeud>();
     //ArrayList<Line> mesArretes = new ArrayList<Line>(); 
     int graph [][];
     Line lines [][];
     // La matrix contenant le graph. Taille N*N
    int result[][]; // Le graph résultat de kruskal
    int count[]; // Le vecteur qui contient le nombre d'arete liées à chaque noeud
    int clear = 0;
    int arrete=0;
     
   double tk;
   double  th = 0;
   
     double coor[][] = new double[2][100] ;
     
     
     private int V;   // No. of vertices 
  
    int time = 0; 
    static final int NIL = -1; 
  
    // Constructor 
   
  
    //Function to add an edge into the graph 
    void addEdge(int i, int j,int weight) 
    { 
        graph[i][j]=weight;
        
    }   
    
    
    
  
    int kruskal() {
        long deb, fin;
        deb = System.nanoTime();
        int N=myNodes.size();
    int nbArete = 0, sum = 0;
    int targetX, targetY, min, change;
    while (nbArete < N-1) {
        change = 0;
        targetX = 0;
        targetY = 0;
        min = 1000000; // Un nombre assez grand pour prendre la première valeur trouvée
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if ((graph[i][j] < min) && (count[i] + count[j] < 2) && (graph[i][j] != result[i][j])) {
                    change = 1;
                    min = graph[i][j];
                    targetX = i;
                    targetY = j;
                }
            }
        }
        if (change==1) {
            nbArete++;
            count[targetX]++;
            count[targetY]++;
            result[targetX][targetY] = min;
            sum += min;
        }
    }
    targetX = targetY = -1;
    for (int i = 0; i < N; i++) {
        if (count[i] == 1) {
            if (targetX == -1) {
                targetX = i;
            }
            else {
                targetY = i;
            }
        }
    }
    result[targetX][targetY] = graph[targetX][targetY];
    sum += graph[targetX][targetY];
    fin = System.nanoTime();
    
   tk = (double) (fin-deb)/1000000;
  
    
    return sum;
}


    int tsp(int[][] graph, boolean[] v,  
                   int currPos, int n,  
                   int count, int cost, int ans,int seq[],int seqf[])  
    { 
  
       
        // If last node is reached and it has a link 
        // to the starting node i.e the source then 
        // keep the minimum value out of the total cost 
        // of traversal and "ans" 
        // Finally return to check for more possible values 
         long deb1 = System.nanoTime();
        if (count == n && graph[currPos][0] > 0)  
            { if(ans> cost + graph[currPos][0]){
                     for(int k=0;k<n;k++){
                seqf[k]=seq[k]; 

            }
            ans = Math.min(ans, cost + graph[currPos][0]); 
            
            }
            return ans; 
        } 
  
        // BACKTRACKING STEP 
        // Loop to traverse the adjacency list 
        // of currPos node and increasing the count 
        // by 1 and cost by graph[currPos,i] value 
        for (int i = 0; i < n; i++)  
        { 
            if (v[i] == false && graph[currPos][i] > 0)  
            { 
  
                // Mark as visited 
                v[i] = true; 
                seq[count]= i;
                ans = tsp(graph, v, i, n, count + 1, 
                          cost + graph[currPos][i], ans,seq,seqf); 
  
                // Mark ith node as unvisited 
                v[i] = false; 
            } 
        } 
        long fin1 = System.nanoTime();
        th = (double)(fin1-deb1)/1000000;
        return ans; 
    } 
    
    int voy_com(){
        // n is the number of nodes i.e. V 
  
     
        int n = myNodes.size(); 
        result=new int[myNodes.size()][myNodes.size()];
        count=new int[myNodes.size()];
        String chaine="--------methode exhaustive--------\n";        
        
        // Boolean array to check if a node 
        // has been visited or not 
        boolean[] v = new boolean[n]; 
  
        // Mark 0th node as visited 
        v[0] = true; 
        int ans = Integer.MAX_VALUE; 
         seq =new int[n];
         seqf =new int[n];
       
        // Find the minimum weight Hamiltonian Cycle 
        ans = tsp(graph, v, 0, n, 1, 0, ans,seq,seqf); 
        chaine+="La distance minimal est :"+Integer.toString(ans)+" \n La sequence est: ";
        for(int k=0;k<n;k++){
            //System.out.println(seqf[k]+1); 
            if (k!=0) chaine+="->";
            chaine+=Integer.toString(seqf[k]+1);
             //(lines[seqf[k]][seqf[k]+1]).setFill(Color.rgb(255,206,135));
            if (k!=(n-1)){
                  myNodes.get(k).circle.setFill(Color.rgb(255,206,135));
                  lines [seqf[k]][seqf[k+1]] =new Line(coor[0][seqf[k]],coor[1][seqf[k]],coor[0][seqf[k+1]],coor[1][seqf[k+1]]);
                 ( lines [seqf[k]][seqf[k+1]] ).setStroke(Color.GREENYELLOW);
                  root2.getChildren().add(lines [seqf[k]][seqf[k+1]]);
              }
            else 
            {
                  myNodes.get(k).circle.setFill(Color.rgb(255,206,135));
                  lines[seqf[0]][seqf[k]] =new Line(coor[0][seqf[0]],coor[1][seqf[0]],coor[0][seqf[k]],coor[1][seqf[k]]);
                 ( lines [seqf[0]][seqf[k]] ).setStroke(Color.GREENYELLOW);
                 root2.getChildren().add(lines [seqf[0]][seqf[k]]);
            }
                           
             
        }
  
        // ans is the minimum weight Hamiltonian Cycle 
        chaine+="\n";
        System.out.println("\n"); 
        chaine+="le temps d'execution est :"+th+ " ms\n";
        //System.out.println(ans); 
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(chaine);
      

        alert.showAndWait();
        return ans;
    }
    
    void kvoy(){
    int n = myNodes.size(); 
        result=new int[myNodes.size()][myNodes.size()];
        count=new int[myNodes.size()];
        int res=kruskal();
        String chaine="--------methode vorace--------\n";        
        chaine+="La distance minimal est :"+Integer.toString(res)+" \n La sequence est: 1";

         int s=0; int h=0;   int trouv=0;      int r[]=new int[n];  r[0]=1;       
        for(int cpt=0;cpt<n;cpt++){
            trouv=0;
            h=0;
            while(( trouv !=1) &&h<n){
                if(result[s][h]>0 && r[h]!=1){
                    trouv=1;
                    r[h]=1;
                    chaine+="->";
                    chaine+=(h+1);
                  
                       myNodes.get(s).circle.setFill(Color.rgb(255,206,135));
                  lines [s][h] =new Line(coor[0][s],coor[1][s],coor[0][h],coor[1][h]);
                 ( lines [s][h] ).setStroke(Color.GREENYELLOW);
                  root2.getChildren().add(lines [s][h]);
                    s=h;
                }
               h++;
            }
              h=0;
            while(( trouv !=1) &&h<n){
                if(result[h][s]>0 && r[h]!=1){
                    trouv=1;
                    r[h]=1;
                    chaine+="->";
                    chaine+=(h+1);
                          myNodes.get(s).circle.setFill(Color.rgb(255,206,135));
                  lines [s][h] =new Line(coor[0][s],coor[1][s],coor[0][h],coor[1][h]);
                 ( lines [s][h] ).setStroke(Color.GREENYELLOW);
                  root2.getChildren().add(lines [s][h]);
                    
                    s=h;
                }
                h++;
            }
               if(  cpt==(n-1)) 
                  {
                       myNodes.get(s).circle.setFill(Color.rgb(255,206,135));
                  lines [0][s] =new Line(coor[0][0],coor[1][0],coor[0][s],coor[1][s]);
                 ( lines [0][s] ).setStroke(Color.GREENYELLOW);
                  root2.getChildren().add(lines [0][s]);
                  }
        
        }  
        
        
        chaine+="\n";
        chaine+="le temps d'execution est :"+tk+ " ms\n";
        chaine+="\n";
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(chaine);
        alert.showAndWait();
        
    }
    
     
     
    @Override
    public void start(Stage primaryStage) {
              
         
      selectedNoeud=null;
   
        
        HBox root = new HBox(10); 
        root.setStyle("-fx-background-color: #ffffff;");
        //root.setPadding(new Insets(10,10,10,10));
        
        
        VBox root1 = new VBox(20); root1.setAlignment(Pos.CENTER);
        root1.setPrefWidth(630);
        root1.setStyle("-fx-background-color: #d3d3d3; -fx-border-color: #d3d3d3; -fx-border-width: 3px;");
        
        //Pane root2 = new Pane();  
        root2.setPrefWidth(1200); root.setAlignment(Pos.CENTER);
        root2.setStyle(" -fx-background-color: #fcfcfc;  -fx-border-color:  #add8e6;  -fx-border-width:5px;");
        

                // Group
         ToggleGroup group = new ToggleGroup();

        
         RadioButton button1 = new RadioButton("noeud");
         button1.setToggleGroup(group);
         button1.setSelected(true);
         button1.setStyle("-fx-font: 16px \"Serif\";\n" +"    -fx-padding: 0;"); 

         
         RadioButton button2 = new RadioButton("arrete");
         button2.setToggleGroup(group);
         button2.setStyle("-fx-font: 16px \"Serif\";\n" +"    -fx-padding: 0;"); 
         
         RadioButton button3 = new RadioButton("Methode Exacte");
         button3.setToggleGroup(group);
         button3.setStyle("-fx-font: 16px \"Serif\";\n" +"    -fx-padding: 0;"); 
         
         RadioButton button4 = new RadioButton("Methode Approch�e");
         button4.setToggleGroup(group);
         button4.setStyle("-fx-font: 16px \"Serif\";\n" +"    -fx-padding: 0;");

         RadioButton button5 = new RadioButton("Clear");
         button5.setToggleGroup(group);
         button5.setStyle("-fx-font: 16px \"Serif\";\n" +"    -fx-padding: 0;");

         root1.getChildren().addAll(button1,button2,button3,button4,button5);
        
        root.getChildren().add(root1);
        root.getChildren().add(root2);
        
        
        root2.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                 
                if(( (RadioButton)group.getSelectedToggle()).getText()== "noeud"){
                    System.out.println("mouse click detected! " + mouseEvent.getSource());                  

                    myNodes.add(new Noeud(mouseEvent.getX(),mouseEvent.getY(),15,root2, Color.rgb(255,255,255),i ));
                        coor[0][i]=mouseEvent.getX()+15;
                             coor[1][i]=mouseEvent.getY()+15;
                        
                       
                    i ++;
                    
               
                }
            }
        });
        button2.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent e) ->{
                        graph=new int[myNodes.size()][myNodes.size()];
                        lines=new Line[myNodes.size()][myNodes.size()];
                        arrete=1;
                       
                    }
                    );
        
        button3.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent e) ->{
            clear = 1;
 
                        voy_com();
                       
                    }
                    );
       
        button4.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent e) ->{
            clear = 2;
       
            kvoy();
                        
                    }
                    );
        button5.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent e) ->{
                      if(clear == 2){
                                  int s=0; int h=0;   int trouv=0;      int r[]=new int[myNodes.size()];  r[0]=1;       
        for(int cpt=0;cpt<myNodes.size();cpt++){
            trouv=0;
            h=0;
            while(( trouv !=1) &&h<myNodes.size()){
                if(result[s][h]>0 && r[h]!=1){
                    trouv=1;
                    r[h]=1;
                  
                  
                       myNodes.get(s).circle.setFill(Color.rgb(255,206,135));
                  lines [s][h] =new Line(coor[0][s],coor[1][s],coor[0][h],coor[1][h]);
                 ( lines [s][h] ).setStroke(Color.BLACK);
                  root2.getChildren().add(lines [s][h]);
                    s=h;
                }
               h++;
            }
              h=0;
            while(( trouv !=1) &&h<myNodes.size()){
                if(result[h][s]>0 && r[h]!=1){
                    trouv=1;
                    r[h]=1;
                
                          myNodes.get(s).circle.setFill(Color.rgb(255,206,135));
                  lines [s][h] =new Line(coor[0][s],coor[1][s],coor[0][h],coor[1][h]);
                 ( lines [s][h] ).setStroke(Color.BLACK);
                  root2.getChildren().add(lines [s][h]);
                    
                    s=h;
                }
                h++;
            }
               if(  cpt==(myNodes.size()-1)) 
                  {
                       myNodes.get(s).circle.setFill(Color.rgb(255,206,135));
                  lines [0][s] =new Line(coor[0][0],coor[1][0],coor[0][s],coor[1][s]);
                 ( lines [0][s] ).setStroke(Color.BLACK);
                  root2.getChildren().add(lines [0][s]);
                  }
        
        } 
        
                      }
                      if (clear == 1){
                                                      for(int k=0;k<myNodes.size();k++){
            
            if (k!=(myNodes.size()-1)){
                  myNodes.get(k).circle.setFill(Color.rgb(255,100,100));
                  lines [seqf[k]][seqf[k+1]] =new Line(coor[0][seqf[k]],coor[1][seqf[k]],coor[0][seqf[k+1]],coor[1][seqf[k+1]]);
                 ( lines [seqf[k]][seqf[k+1]] ).setStroke(Color.BLACK);
                  root2.getChildren().add(lines [seqf[k]][seqf[k+1]]);
              }
            else 
            {
                  myNodes.get(k).circle.setFill(Color.rgb(255,100,100));
                  lines[seqf[0]][seqf[k]] =new Line(coor[0][seqf[0]],coor[1][seqf[0]],coor[0][seqf[k]],coor[1][seqf[k]]);
                 ( lines [seqf[0]][seqf[k]] ).setStroke(Color.BLACK);
                 root2.getChildren().add(lines [seqf[0]][seqf[k]]);
            }
         
           

        } 
                      
                      }
                        
                    }
                    );
       
        Scene scene = new Scene(root, 900, 500);
        primaryStage.setTitle("PVC ");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
 
    class Noeud {
        public int numNoeud;
        public Circle circle ;
        public Text text;
        public StackPane c;
        double xn;
        double yn;
      
        public LinkedList<Integer> adj=new LinkedList<Integer>();
                
        public Noeud(double x, double y,double radius, Pane parent, Color fill , int i)
        {
               
                numNoeud=i;
                xn=x;yn=y;
                circle = new Circle(x, y, radius,fill);
           
                circle.setFill(Color.rgb(135,206,235)); 
                //circle.setStrokeWidth(7); 
                circle.setId("cercle"+i);

                text= new Text(Integer.toString(i+1)); 
                
                text.setBoundsType(TextBoundsType.VISUAL);
                text.setFont(Font.font("Verdana",20));
                text.setFill(Color.rgb(71,72,77));
                
                this.c = new StackPane();
                this.c.setAlignment(Pos.CENTER);
                this.c.setId("noeud"+(i));
                this.c.getChildren().addAll(circle,text);

                
                     parent.getChildren().add(this.c); 
                    this.c.setLayoutX(x); this.c.setLayoutY(y);
                    
                       this.c.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent e) ->{
                            selectedNoeud=this;
                            selectedNoeud.circle.setFill(Color.rgb(255,100,100));
                            int z= 0;
                          for( z= (selectedNoeud.numNoeud);z < (myNodes.size()-1) ;z++){
                          int weight=1;
                            
                                Label weight2 = new Label();
                                weight2.setLayoutX(((selectedNoeud.xn) + (coor[0][z+1])) / 2);
                                weight2.setLayoutY(((selectedNoeud.yn) + (coor[1][z+1])) / 2);

                                TextInputDialog dialog = new TextInputDialog("0");
                                dialog.setTitle(null);
                                dialog.setHeaderText("Enter Weight of the Edge between  :"+(selectedNoeud.numNoeud+1) + "et "+(z+2));
                                dialog.setContentText(null);

                                Optional<String> result = dialog.showAndWait();
                                if (result.isPresent()) {
                                    weight2.setText(result.get());
                                } else {
                                    weight2.setText("0");
                                }
                                parent.getChildren().add(weight2);
                                weight=Integer.valueOf(weight2.getText());
                                 selectedNoeud.addEdge(z+1,selectedNoeud.numNoeud,weight);
                             lines[selectedNoeud.numNoeud][z+1]=new Line(selectedNoeud.xn+15, selectedNoeud.yn+15, coor[0][z+1] ,coor[1][z+1]);

                             lines[selectedNoeud.numNoeud][z+1]=lines[selectedNoeud.numNoeud][z+1];
                                parent.getChildren().add(lines[selectedNoeud.numNoeud][z+1]);
                                
                           selectedNoeud.circle.setFill(Color.rgb(135,206,235));
                          }
                            
                       
                    }
                    ); 
            } 
        
        
             void addEdge(int i,int j, int weight) 
            { 
                
                graph[i][j]=weight;
                graph[j][i]=weight;

            } 
        
        @Override
        public String toString(){
            return String.valueOf(this.numNoeud);
        } 
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

