import java.util.ArrayList;
import java.util.Stack;

/**
 * HanoiTowers
 */
public class HanoiProblem {
    
    public static class HanoiTowers{        
        public int numberOfDisks;
        private int numberOfTowers=3;
        private ArrayList<Stack<Integer>> towers=new ArrayList<>();
        private int movements;

        public HanoiTowers(int numberOfDisks){
            this.numberOfDisks=numberOfDisks;
            this.movements=0;
            createTowers(numberOfTowers);
            fillInitialTower();           
        }

        private void createTowers(int numberOfTowers){
            for (int i = 0; i < numberOfTowers; i++) {
                this.towers.add(new Stack<Integer>()); 
            }            
        }

        private void fillInitialTower() {
            Stack <Integer> initialTower=towers.get(0);
            for (int disk_index = this.numberOfDisks; disk_index >0; disk_index--) {
                initialTower.push(disk_index);
            }
        }

        public void printState(){
            /*   |   || 2 ||   |
                 |   || 9 ||   |
                 | 1 || 2 ||   |
                ----------------- */
            int tallerTowerHeight= getTallerTowerHeight();

            for (int level = tallerTowerHeight; level>0; level--){
                for (int towerIndex = 0; towerIndex  < this.numberOfTowers; towerIndex++){
                    if(towers.get(towerIndex).size()-level>=0)
                        System.out.print("| "+towers.get(towerIndex).get(level-1)+" |");
                    else
                        System.out.print("|   |");                   
                }
                System.out.println();
            }            
            System.out.println("-----------------");
        }

        private int getTallerTowerHeight() {
            int tallerTowerHeight=0;
            for (int towerIndex = 0; towerIndex  < this.numberOfTowers; towerIndex++)             
                if(towers.get(towerIndex).size()>tallerTowerHeight)
                    tallerTowerHeight=towers.get(towerIndex).size();
            return tallerTowerHeight;            
        }
        
        public void moveDisk(int origin, int destiny){
            if(validMovement(origin,destiny)){
                int disk=towers.get(origin).pop();
                towers.get(destiny).push(disk);
                this.movements++;
                System.out.println("Disk "+disk+" moved from tower "+origin+" to tower "+destiny);
            }           
            
        }
        
        private boolean validMovement(int origin, int destiny) {
            if(origin > numberOfTowers-1 || origin < 0 || destiny > numberOfTowers-1 || destiny < 0 ){
                System.out.println("Illegal movement: Tower aout of range");                
            }else if(towers.get(destiny).size()>0&&towers.get(origin).peek()>towers.get(destiny).peek()){
                System.out.println("Illegal movement: Disk "+towers.get(origin).peek()+" is bigger than disk "+towers.get(destiny).peek());      
            }else{
                return true;
            }
            return false;            
        } 

        public int getMovementsCount() {
            return this.movements;
        }
       
    }

    public static void move1_fromOriginToIntermediate(int origin,int intermediate, int destiny,int towerHeight, HanoiTowers towers) {
        if(towerHeight-1>2){
            move1_fromOriginToIntermediate(origin, destiny, intermediate, towerHeight-1, towers);
            move2_fromOriginToDestiny(origin, intermediate, towers);
            move3_fromIntermediateToOrigin(origin, destiny, intermediate, towerHeight-1, towers);
        }else{
            towers.moveDisk(origin, destiny);
            towers.printState();
            towers.moveDisk(origin, intermediate);
            towers.printState();
            towers.moveDisk(destiny, intermediate);
            towers.printState();
        }
    }

    public static void move2_fromOriginToDestiny(int origin, int destiny, HanoiTowers towers) {        
            towers.moveDisk(origin, destiny);
            towers.printState();       
    }

    public static void move3_fromIntermediateToOrigin(int origin,int intermediate, int destiny,int towerHeight, HanoiTowers towers) {
        if(towerHeight-1>2){
            move1_fromOriginToIntermediate(intermediate, origin, destiny, towerHeight-1, towers);
            move2_fromOriginToDestiny(intermediate, destiny, towers);
            move3_fromIntermediateToOrigin(intermediate, origin, destiny, towerHeight-1, towers);
        }else{
            towers.moveDisk(intermediate, origin);
            towers.printState();
            towers.moveDisk(intermediate, destiny);
            towers.printState();
            towers.moveDisk(origin, destiny);
            towers.printState();
        }
    }

    public static void solve_problem(HanoiTowers towers) {
        towers.printState();
        move1_fromOriginToIntermediate(0, 1, 2, towers.numberOfDisks, towers);
        move2_fromOriginToDestiny(0, 2, towers);
        move3_fromIntermediateToOrigin(0, 1, 2, towers.numberOfDisks, towers);
    }
    
    public static void main(String[] args) {
        HanoiTowers game=new HanoiTowers(6);
        solve_problem(game);
        System.out.println("Total movements: "+game.getMovementsCount());
    }
}