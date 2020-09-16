import java.util.Arrays;
import java.util.Random;

public class TrainLine {

	private TrainStation leftTerminus;
	private TrainStation rightTerminus;
	private String lineName;
	private boolean goingRight;
	public TrainStation[] lineMap;
	public static Random rand;

	public TrainLine(TrainStation leftTerminus, TrainStation rightTerminus, String name, boolean goingRight) {
		this.leftTerminus = leftTerminus;
		this.rightTerminus = rightTerminus;
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus.setRightTerminal();
		this.leftTerminus.setTrainLine(this);
		this.rightTerminus.setTrainLine(this);
		this.lineName = name;
		this.goingRight = goingRight;

		this.lineMap = this.getLineArray();
	}

	public TrainLine(TrainStation[] stationList, String name, boolean goingRight)
	/*
	 * Constructor for TrainStation input: stationList - An array of TrainStation
	 * containing the stations to be placed in the line name - Name of the line
	 * goingRight - boolean indicating the direction of travel
	 */
	{
		TrainStation leftT = stationList[0];
		TrainStation rightT = stationList[stationList.length - 1];

		stationList[0].setRight(stationList[stationList.length - 1]);
		stationList[stationList.length - 1].setLeft(stationList[0]);

		this.leftTerminus = stationList[0];
		this.rightTerminus = stationList[stationList.length - 1];
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus.setRightTerminal();
		this.leftTerminus.setTrainLine(this);
		this.rightTerminus.setTrainLine(this);
		this.lineName = name;
		this.goingRight = goingRight;

		for (int i = 1; i < stationList.length - 1; i++) {
			this.addStation(stationList[i]);
		}

		this.lineMap = this.getLineArray();
	}

	public TrainLine(String[] stationNames, String name, boolean goingRight) {
		                             /*
									 * Constructor for TrainStation. input: stationNames - An array of String
									 * containing the name of the stations to be placed in the line name - Name of
									 * the line goingRight - boolean indicating the direction of travel
									 */
		TrainStation leftTerminus = new TrainStation(stationNames[0]);
		TrainStation rightTerminus = new TrainStation(stationNames[stationNames.length - 1]);

		leftTerminus.setRight(rightTerminus);
		rightTerminus.setLeft(leftTerminus);

		this.leftTerminus = leftTerminus;
		this.rightTerminus = rightTerminus;
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus.setRightTerminal();
		this.leftTerminus.setTrainLine(this);
		this.rightTerminus.setTrainLine(this);
		this.lineName = name;
		this.goingRight = goingRight;
		for (int i = 1; i < stationNames.length - 1; i++) {
			this.addStation(new TrainStation(stationNames[i]));
		}

		this.lineMap = this.getLineArray();

	}

	// adds a station at the last position before the right terminus
	public void addStation(TrainStation stationToAdd) {
		TrainStation rTer = this.rightTerminus;
		TrainStation beforeTer = rTer.getLeft();
		rTer.setLeft(stationToAdd);
		stationToAdd.setRight(rTer);
		beforeTer.setRight(stationToAdd);
		stationToAdd.setLeft(beforeTer);

		stationToAdd.setTrainLine(this);

		this.lineMap = this.getLineArray();
	}

	public String getName() {
		return this.lineName;
	}
	
		public int getSize() {
	    int count = 0 ;
	    TrainStation station;
	    
	
	    	 for(station=this.getLeftTerminus(); station!=null;station=station.getRight(), count++) {
	    		
	    	 }
	    	
	    	 return count;
	    }
		 
	

	public void reverseDirection() {
		this.goingRight = !this.goingRight;
	}

	// You can modify the header to this method to handle an exception. You cannot make any other change to the header.
	public TrainStation travelOneStation(TrainStation current, TrainStation previous) throws StationNotFoundException {
	
    TrainStation y = findStation(current.getName());
    
    if (y == null) {
    	throw new StationNotFoundException("Station not found");
    }else {

	if(current.hasConnection && previous != current.getTransferStation()) {
		previous = current;
		current = current.getTransferStation();
		return current;
	}else {
		current  = getNext(current);
		return current;
	}
    }
		
	}

	// You can modify the header to this method to handle an exception. You cannot make any other change to the header.
	public TrainStation getNext(TrainStation station) throws StationNotFoundException {
		if (findStation(station.getName()) == null) {
			throw new StationNotFoundException("Station not found");
	    }
		else {
			TrainStation current = station;
			
			findStation(station.getName());
			 
			if(goingRight==true && current.isRightTerminal()) {
				goingRight=false;
			}
			
		    if(goingRight==false&& current.isLeftTerminal()) {
			goingRight=true;
		}
			
				if(goingRight==true) {
					current=station.getRight();
					
				}else current=station.getLeft();
				
				
				
			
			
			return current;
			
			
		}
		
		
	    }
	
	

	// You can modify the header to this method to handle an exception. You cannot make any other change to the header.
	public TrainStation findStation(String name) {
		TrainStation station = this.leftTerminus;
         
		while(station.getRight()!=null) {
        	 if(station.getName().equals(name)) {
        		 return station; 
        	 }else {
        		 station=station.getRight();
        		  }
		
		}
		
        if(station.isRightTerminal()) {
        	if(station.getName().equals(name)) return station;
        		
        	} 
        	throw new StationNotFoundException("Station name not found");
        
         }
		
         
        	 
        	 
        
	

	public void sortLine() {
		int counter= 0;
		boolean continu = true;
		while(continu) {
			continu=false;
			counter ++;
			TrainStation temp = this.leftTerminus;
			for(int i=0;i<lineMap.length-counter;i++) {
	
				if((temp.getName()).compareTo(temp.getRight().getName())>0) {
				    Swap(temp,temp.getRight());
				    temp=temp.getLeft();  //what wud happen if this happened again??
				    continu=true;
		}  temp=temp.getRight();
			} 
			//if(temp.getRight()==null) continu=false;
}
		this.lineMap=this.getLineArray();//??do i need to do this???
	}
		

	public TrainStation[] getLineArray() {
    TrainStation array[] = new TrainStation[getSize()];
    TrainStation station = null;
    int i = 0;
    
  for( station=this.getLeftTerminus(); station!=null;station=station.getRight()) {
        array[i]=station;
        i++;   	
  }		
	 
    
		return array;
	}

	private TrainStation[] shuffleArray(TrainStation[] array) {
		Random rand = new Random();
		rand.setSeed(11);
		for (int i = 0; i < array.length; i++) {
			int randomIndexToSwap = rand.nextInt(array.length);
			TrainStation temp = array[randomIndexToSwap];
			array[randomIndexToSwap] = array[i];
			array[i] = temp;
		}
		this.lineMap = array;
		return array;
	}

	public void shuffleLine() {

		// you are given a shuffled array of trainStations to start with
		TrainStation[] lineArray = this.getLineArray();
		TrainStation[] shuffledArray = shuffleArray(lineArray);//shuffled array is basically the updated array from getLinearray shuffled
      
       //Exception case head
		TrainStation stationhead = shuffledArray[0];
		this.leftTerminus = stationhead;
		stationhead.setNonTerminal();
		stationhead.setLeftTerminal();
		stationhead.setRight(shuffledArray[1]);
		stationhead.setLeft(null);
		
		//Exceptio case tail
		TrainStation stationend = shuffledArray[shuffledArray.length-1];
		this.rightTerminus = stationend;
		stationend.setNonTerminal();
		stationend.setRightTerminal();
		stationend.setLeft(shuffledArray[shuffledArray.length-2]);
		stationend.setRight(null);
		
		//all the stations in between
		TrainStation currentstation= null;
		for(int i=1;i<shuffledArray.length-1;i++) {
		currentstation = shuffledArray[i];
		currentstation.setNonTerminal();
		currentstation.setLeft(shuffledArray[i-1]);//setting my left pointer
		currentstation.setRight(shuffledArray[i+1]);//setting my right pointer
	   }
		
		this.lineMap = shuffledArray;
		
		
	   

	}

	public String toString() {
		TrainStation[] lineArr = this.getLineArray();
		String[] nameArr = new String[lineArr.length];
		for (int i = 0; i < lineArr.length; i++) {
			nameArr[i] = lineArr[i].getName();
		}
		return Arrays.deepToString(nameArr);
	}

	public boolean equals(TrainLine line2) {

		// check for equality of each station
		TrainStation current = this.leftTerminus;
		TrainStation curr2 = line2.leftTerminus;

		try {
			while (current != null) {
				if (!current.equals(curr2))
					return false;
				else {
					current = current.getRight();
					curr2 = curr2.getRight();
				}
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public TrainStation getLeftTerminus() {
		return this.leftTerminus;
	}

	public TrainStation getRightTerminus() {
		return this.rightTerminus;
	}
	
	
	private  void Swap (TrainStation current, TrainStation next) {
	//	System.out.println("Initial  state next left: "+(next.getLeft()));
	//	System.out.println("Initial state next right : "+(next.getRight()));
		
	//	System.out.println("Initial state current left : "+(current.getLeft()));
	//	System.out.println("Initial state current right: "+(current.getRight()));	
		    
		 current.setRight(next.getRight());
			next.setLeft(current.getLeft());
			current.setLeft(next);
			next.setRight(current);
		
			
		//	System.out.println("NEW state next left: "+(next.getLeft()));
		//	System.out.println("NEW state next right : "+(next.getRight()));
			
		//	System.out.println("NEW state current left: "+(current.getLeft()));	
		//	System.out.println("NEW state current right: "+(current.getRight()));	
	
			
			if(next.getLeft()==null) { //when orange becomes head
				leftTerminus=next;//setting head to new head (after swap)
				next.setNonTerminal();//A ka mashfara
				next.setLeftTerminal(); //Telling the node it is the head
			}else { 
				next.getLeft().setRight(next);//MAking sure the left node points to me instead of it's old right node
				next.setNonTerminal();//the case when durian is not the tail, tell it it is not the tail
				
			}
			
			
			if(current.getRight()==null) {      //when apple becomes tail   
				rightTerminus=current;//setting to tail
				current.setNonTerminal();//A ka mashfara
				current.setRightTerminal();
			
			}else {	
				current.getRight().setLeft(current);
				current.setNonTerminal(); //the case when apple is not the head,tell it it is not the head
			}
			
			
			
				
				
			}
	
	/*   public static void main(String[] args) {
		TrainStation A1 = new TrainStation("1.BOOM");
		TrainStation A2 = new TrainStation("3.AOOM");
		TrainStation A3 = new TrainStation("2.KABOOM");
		TrainStation A4 = new TrainStation("5.NOORJAM");
		
		TrainStation[] linemap = {A1,A2,A3,A4};
		TrainLine tt = new TrainLine(linemap,"fuck",true);
	 System.out.println("the initial shit"+ tt);
	 // tt.shuffleLine();
	// System.out.println("the shuffled shit"+tt);
	// tt.Swap(A3,A4);
	// System.out.println("the swapped shit"+tt);
		tt.sortLine();
		System.out.println("the sorted shit"+tt);
		
	
		
			
			
			}*/
			
		
		
	
	
	
}

 

//Exception for when searching a line for a station and not finding any station of the right name.
class StationNotFoundException extends RuntimeException {
	String name;

	public StationNotFoundException(String n) {
		name = n;
	}

	public String toString() {
		return "StationNotFoundException[" + name + "]";
	}
}

