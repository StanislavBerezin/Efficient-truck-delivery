package assignment2;

public class Store {

	
	String storeName;
	double storeCapital;
	static final Store instance = new Store();
    
    //private constructor to avoid client applications to use constructor
    private Store(){
    	this.storeCapital = 100000;
    }

    public static Store getInstance(){
        return instance;
    }
    

    
}
