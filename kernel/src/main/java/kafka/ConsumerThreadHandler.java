package kafka;

import com.fasterxml.jackson.core.type.TypeReference;
import common.util.TemplateUtil;
import db.DataSourceContextHolder;
import model.jimdata.data.JimData;
import model.zmibinfo.ZMibInfo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import service.jimdata.JimDataService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/4/17/017.
 */
public class ConsumerThreadHandler implements Runnable {
    private ConsumerRecord consumerRecord;

    private List<ZMibInfo> list;


    JimDataService jimDataService;


    public ConsumerThreadHandler(ConsumerRecord consumerRecord,List<ZMibInfo> list, JimDataService jimDataService){
        this.consumerRecord = consumerRecord;
        this.list = list;
        this.jimDataService = jimDataService;

    }

/*  public static void main(String[] args) {
        String str = "{\"hostname\":\"DESKTOP-90P52LI\",\"@timestamp\":\"2018-04-20T10:18:00.660Z\",\"NT_CPUINFO\":{\"win32_Processor\":{\"NumberOfLogicalProcessors\":\"4\",\"MaxClockSpeed\":\"2394\",\"NumberOfCores\":\"2\",\"LoadPercentage\":\"16\",\"DataWidth\":\"64\",\"Name\":\"Intel(R) Core(TM) i5-4210U CPU @ 1.70GHz\"}},\"NT_NETINTINFO\":{\"win32_PerfRawData_Tcpip_NetworkInterface\":{\"BytesTotalPersec\":\"19907\",\"CurrentBandwidth\":\"100000\",\"BytesSentPersec\":\"16259\",\"BytesReceivedPersec\":\"3648\",\"Name\":\"本地连接* 12\"}},\"NT_PHYMEMINFO\":{\"win32_ComputerSystem\":{\"TotalPhysicalMemory\":\"8317550592\"},\"win32_PerfFormattedData_PerfOS_Memory\":{\"AvailableMBytes\":\"2691\"},\"Win32_OperatingSystem\":{\"FreePhysicalMemory\":\"2764888\",\"TotalVisibleMemorySize\":\"8122608\"}},\"host\":\"192.168.99.1\",\"NT_USERINFO\":{\"win32_UserAccount\":{\"Status\":\"Degraded\",\"LocalAccount\":\"True\",\"Domain\":\"DESKTOP-90P52LI\",\"Name\":\"WDAGUtilityAccount\"}},\"NT_PROCESSINFO\":{\"win32_PerfFormattedData_PerfProc_Process\":{\"IOReadBytesPersec\":\"0\",\"IOWriteBytesPersec\":\"0\",\"PercentProcessorTime\":\"0\",\"WorkingSet\":\"4911104\"},\"win32_Process\":{\"CreationDate\":\"20180420101805.020190+480\",\"ThreadCount\":\"10\",\"ProcessId\":\"12736\",\"Name\":\"cscript.exe\"},\"win32_OperatingSystem\":{\"TotalVisibleMemorySize\":\"8122608\"}},\"NT_SWAPINFO\":{\"Win32_PageFileUsage\":{\"CurrentUsage\":\"186\",\"AllocatedBaseSize\":\"6698\"}},\"NT_DISKINFO\":{\"win32_PerfFormattedData_PerfDisk_LogicalDisk\":{\"DiskBytesPersec\":\"0\",\"DiskReadBytesPersec\":\"0\",\"DiskWriteBytesPersec\":\"0\",\"Name\":\"HarddiskVolume1\"}},\"NT_SYSINFO\":{\"win32_OperatingSystem\":{\"CSName\":\"DESKTOP-90P52LI\",\"Status\":\"OK\",\"CSDVersion\":\"\",\"OSArchitecture\":\"64 位\",\"NumberOfUsers\":\"2\",\"Manufacturer\":\"Microsoft Corporation\",\"NumberOfLicensedUsers\":\"\",\"NumberOfProcesses\":\"182\",\"Caption\":\"Microsoft Windows 10 教育版\"}}}";

        *//*Map map = TemplateUtil.genObjFormJson(str, Map.class);
        System.out.println(map.get("win32_ComputerSystem"));*//*
        //JSONObject jsonObject = JSONObject.fromObject(joStr);
  }*/


    @Override
    public void run() {
        System.out.println("Consumer Message:"+consumerRecord.value()+",Partition:"+consumerRecord.partition()+"Offset:"+consumerRecord.offset());

        String str =(String) consumerRecord.value();
        Map map = TemplateUtil.genObjFormJson(str, new TypeReference<Map<String,String>>(){});
        String message = (String)map.get("message");

        //System.out.println(valueMessage);
        List<JimData> jimDataList = getJimDataList(message);


        for (JimData j : jimDataList){
            DataSourceContextHolder.setDbType("jim_data");
            System.out.println("开始插入-----------------------------------------");
            jimDataService.insert(j);
            DataSourceContextHolder.setDbType("jim");
        }
    }

    /**
     * 匹配正则获取list
     * @param managers
     * @return
     */
    public List<JimData> getJimDataList(String managers){
        List<JimData> ls=new ArrayList<JimData>();
        for(ZMibInfo z : list){
            Pattern pattern = Pattern.compile(z.getCalculationFormula());
            Matcher matcher = pattern.matcher(managers);
            String value = "";
            while(matcher.find()){
                value =(String)matcher.group();
            }
            if(value != ""){
                 JimData j = new JimData();
                 j.setIndexName(z.getIndexGroup());

                 j.setIndexValue(value);
                 ls.add(j);
            }

        }
        return ls;
    }
}
