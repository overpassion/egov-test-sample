import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;

public class Monitoring {

    public static void main(String[] args)  {

        try{
            OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

            for(int i=0;i<100;i++){
                System.out.println("***********************************************************");
                System.out.println("CPU Usage : " + String.format("%.2f", osBean.getSystemCpuLoad() * 100));
                System.out.println("Memory Free Space : " + String.format("%.2f", (double)osBean.getFreePhysicalMemorySize()/1024/1024/1024  ));
                System.out.println("Memory Total Space : " + String.format("%.2f", (double)osBean.getTotalPhysicalMemorySize()/1024/1024/1024  ));

                Thread.sleep(1000);
            }

        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

}