import org.junit.Test;

/**
 * @Auther: 徐林啸
 * @Date: 2019/7/2 22:37
 * @Description:
 */
public class TestJunit {

    @Test
    public void t(){
        Thread t1= new Thread(){
            public void run(){
                int seconds =0;
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.printf("已经玩了LOL %d 秒%n", seconds++);
                }
            }
        };
        t1.start();
    }

}
