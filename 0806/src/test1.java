public class test1 {
	public void main(String[] args) {
		int result = 0;
		int result2 = 0;
		int result3 = 0;
		for(int x = 2; x < 10; x += 3) {
			for(int y = 1; y < 4; y++) {
				result = x * y;
				result2 = (x+1) * y;
				result3 = (x+2) * y;	
				System.out.print(x + "*" + y + "=" + result + "\t");
				System.out.print((x+1) + "*" + y + "=" + result2 + "\t");
				if((x+2) < 10) {
					System.out.print((x+2) + "*" + y + "=" + result3 + "\n");
				} else {
					System.out.println();
				}
			}
			System.out.println();
		}
	} // end of main
} // end of class
