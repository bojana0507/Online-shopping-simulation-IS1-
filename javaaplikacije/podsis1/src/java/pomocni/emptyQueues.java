package pomocni;
        
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;

public class emptyQueues {
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private static ConnectionFactory connectionFactory;
    
    @Resource(lookup = "serverqueue")
    private static Queue queue0;
    
    @Resource(lookup = "podsis1queue")
    private static Queue queue1;
    
    @Resource(lookup = "podsis2queue")
    private static Queue queue2;
    
    @Resource(lookup = "podsis3queue")
    private static Queue queue3;
    
    public static void main(String[] args) {
        JMSContext context = connectionFactory.createContext();
        JMSConsumer consumer = context.createConsumer(queue0);
        int i = 0;
        while (true){
            System.out.println(i++);
            consumer.receive();
        }
    }
}
