public class MainSetAsTree{

	@org.jmlspecs.annotation.SkipRac
    public static void main(String[] args) {
    org.jmlspecs.utils.Utils.useExceptions = true;
    	
		
    SetAsTree s = new SetAsTree(5);
	System.out.println(s);
	s.insert(10);
	System.out.println(s);
	s.insert(1);
	System.out.println(s);
	s.delete(5);
	System.out.println(s);
    }
}
