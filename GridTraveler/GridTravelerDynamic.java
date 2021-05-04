import java.time.Instant;
import java.util.HashMap;

/**
 * GridTravelerDynamic
 */
public class GridTravelerDynamic {

    static int calculatePaths(int m, int n){
        if(m==0&&n==0){
            return 1;          
        }else{
            int r=0,d=0;
            if(m>0)
                r=calculatePaths(m-1, n);
            if(n>0)
                d=calculatePaths(m, n-1);
            return (r+d);
        }
    }

    static int calculatePathsDynamic(int m, int n, HashMap<String,Integer> pathMap){
        String key= m+","+n;
        String Invertedkey= n+","+m;

        if(m==0&&n==0) return 1;
        if(m<0||n<0) return 0;              
        if(pathMap.containsKey(key))
            return pathMap.get(key);
        
        pathMap.put(key, calculatePathsDynamic(m-1, n, pathMap)+calculatePathsDynamic(m, n-1, pathMap));
        pathMap.put(Invertedkey, pathMap.get(key));
                  
        return (pathMap.get(key));
        
    }

    static void howManyPaths(int m,int n){
        HashMap<String,Integer> pathsMap=new HashMap<>();        
        Instant start=Instant.now();
        int totalPaths=calculatePaths(m,n);     
        Instant end=Instant.now();
        System.out.println("Number of paths: "+totalPaths+"\ttime: "+(end.toEpochMilli()-start.toEpochMilli())+" milli.");
        start=Instant.now();
        totalPaths=calculatePathsDynamic(m, n, pathsMap);
        end=Instant.now();        
        System.out.println("Number of paths Dynamic: "+totalPaths+"\ttime: "+(end.toEpochMilli()-start.toEpochMilli())+" milli.");
        
    }

    public static void main(String[] args) {
        System.out.println("Grid Traveler");
        
        int m=17;
        int n=17;

        howManyPaths(m-1,n-1);
    }
}