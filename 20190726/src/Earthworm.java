import java.util.Scanner;

public class Earthworm {
	
	static int map [][] = {
			{0,0,0,0,0,0,0,0,0,0},
			{0,1,1,1,1,1,1,1,1,0},
			{0,1,2,1,1,1,1,2,1,0},
			{0,1,1,1,1,1,1,1,1,0},
			{0,1,1,1,1,2,1,1,1,0},
			{0,1,1,1,1,1,1,1,1,0},
			{0,1,2,1,1,1,1,1,2,0},
			{0,1,1,1,1,1,1,1,1,0},
			{0,1,1,1,2,1,1,1,1,0},
			{0,0,0,0,0,0,0,0,1,0}
	};
	public static void main(String[] args) {		
		//view();
		move();
	}
	
	public static boolean view (int ax, int ay, int bx, int by) {
		
		if(map[ay][ax] == 0) {
			ax = bx;
			ay = by;
		} if(map[ay][ax] == 2) {
			map[ay][ax] = 1;
		}
		
		boolean result = true;
		
		for(int y = 0; y < map.length; y++) {
			for(int x = 0; x < map[y].length; x++) {
				if(map[y][x] == 0) {
					System.out.print(" ■ ");
				} else if(map[y][x] == 2) {
					System.out.print(" ● ");
				} else if(y == ay && x == ax) {
					System.out.print(" ＠ ");
				} else {
					System.out.print(" □ ");
				}
			}
			System.out.println("");
		}
		
		return result;
	}
	
	public static void move() {
		
		int ax = 1;
		int ay = 1;
		int bx = 1;
		int by = 1;
		
		view(ax, ay, bx, by);
				
		
		while(true) {
			
			Scanner sc = new Scanner(System.in);
			String k = sc.next();
			
			switch(k.toUpperCase()) {		
			case "W" : 
				ay--;
				break;
			case "S" :
				ay++;
				break;
			case "A" :
				ax--;
				break;
			case "D" :
				ax++;
				break;
			default :
				System.out.println("잘못누르셨습니다-_-");
				 break;
			}
			
			if(view(ax, ay, bx, by)) {
				bx = ax;
				by = ay;
			} else {
				ax = bx; 
				ay = by;
			};

		}
	}
}
