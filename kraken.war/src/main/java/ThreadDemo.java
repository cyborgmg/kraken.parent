public class ThreadDemo implements Runnable {

   ThreadDemo() {
      // main thread
      Thread currThread = Thread.currentThread();
      
      // thread created
      Thread t = new Thread(this, "Admin Thread");
   
      System.out.println("current thread = " + currThread);
      System.out.println("thread created = " + t);
      
      // this will call run() function
      t.start();
   }

   @Override
public void run() {
      System.out.println("This is run() method");
   }

   public static void main(final String args[]) {
      new ThreadDemo();
   }
} 