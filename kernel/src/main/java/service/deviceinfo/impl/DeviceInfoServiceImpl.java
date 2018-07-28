package service.deviceinfo.impl;

import dao.BaseMapper;
import model.deviceinfo.DeviceInfo;
import model.venderdetailinfo.VenderDetailInfo;
import model.venderinfo.VenderInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.springframework.stereotype.Service;
import service.deviceinfo.DeviceInfoService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import service.venderdetailinfo.VenderDetailInfoService;
import service.venderinfo.VenderInfoService;

@Service("DeviceInfoService")
public class DeviceInfoServiceImpl implements DeviceInfoService{
    private final Logger L = LoggerFactory.getLogger(DeviceInfoServiceImpl.class);
    private static final String NAMESPACE = DeviceInfo.class.getName();
    private Snmp snmp = null;
    private Address targetAddress = null;

    @Resource
    private BaseMapper<DeviceInfo> baseMapper;

    @Resource
    private VenderDetailInfoService venderDetailInfoService;

    @Resource
    private VenderInfoService venderInfoService;

    @Override
    public int insert(DeviceInfo deviceInfo) {
        deviceInfo.setStatmentId(NAMESPACE + ".insert");
        return baseMapper.insert(deviceInfo);
    }
    
    @Override
    public int delete(DeviceInfo deviceInfo) {
        deviceInfo.setStatmentId(NAMESPACE + ".delete");
        return baseMapper.delete(deviceInfo);
    }

    @Override
    public int update(DeviceInfo deviceInfo) {
        deviceInfo.setStatmentId(NAMESPACE + ".update");
        return baseMapper.update(deviceInfo);
    }

    @Override
    public List<DeviceInfo> selectList(DeviceInfo deviceInfo) {
        deviceInfo.setStatmentId(NAMESPACE + ".selectList");
        return baseMapper.selectList(deviceInfo);
    }

    @Override
    public DeviceInfo selectOne(DeviceInfo deviceInfo) {
        deviceInfo.setStatmentId(NAMESPACE + ".selectOne");
        return baseMapper.selectOne(deviceInfo);
    }

    @Override
    public DeviceInfo snmpPoll(DeviceInfo deviceInfo) {
        int version = Integer.valueOf(deviceInfo.getSnmpVersion());

        try {
            initComm(deviceInfo.getManageIp(), deviceInfo.getSnmpPort());
            Vector<VariableBinding> recVBs = sendPDU(version, deviceInfo.getCommunityStringRo());
            if(recVBs != null){
                for (int i = 0; i < recVBs.size(); i++) {
                    VariableBinding recVB = recVBs.elementAt(i);
                }
                deviceInfo.setDeviceName(String.valueOf(recVBs.elementAt(0).getVariable()));
                deviceInfo.setObjectId(String.valueOf(recVBs.elementAt(1).getVariable()));
                deviceInfo.setSoftwareVersion(String.valueOf(recVBs.elementAt(2).getVariable()));

                VenderDetailInfo venderDetailInfo = new VenderDetailInfo();
                venderDetailInfo.setObjectId(String.valueOf(recVBs.elementAt(1).getVariable()));
                VenderDetailInfo vender = venderDetailInfoService.selectOne(venderDetailInfo);
                if(vender != null ){
                    deviceInfo.setDeviceType(vender.getDeviceType());
                    deviceInfo.setDeviceVersion(vender.getDeviceVersion());
                    VenderInfo venderInfo = new VenderInfo();
                    venderInfo.setId(vender.getVenderId());
                    VenderInfo newVenderInfo = venderInfoService.selectOne(venderInfo);
                    deviceInfo.setFirm(newVenderInfo.getVenderName());
                }

            }

        }catch (IOException e){
            L.error("网络管理--探测设备SNMP信息异常！", e);
        }
        return deviceInfo;
    }

    public void initComm(String host, String port) throws IOException {
        // 设置Agent方的IP和端口
        targetAddress = GenericAddress.parse("udp:"+host+"/"+port);
        TransportMapping transport = new DefaultUdpTransportMapping();
        snmp = new Snmp(transport);
        transport.listen();
    }

    public  Vector<VariableBinding> sendPDU(int version, String community) throws IOException {
        // 设置 target
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(community));
        target.setAddress(targetAddress);
        // 通信不成功时的重试次数
        target.setRetries(2);
        // 超时时间
        target.setTimeout(1500);
        target.setVersion(version);
        PDU pdu = new PDU();
        //sysName
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.5.0")));
        //sysObjectID
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.2.0")));
        //sysDescr
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.1.0")));
//        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0")));
//        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.4.0")));
//        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.1.0")));
//
//        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.2.2.1.2")));
//        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.2.2.1.8")));
//
//        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.2.1")));//IFINDEX
//        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.2.2.1.2")));//IFDESCR
//        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.2.2.1.3")));//IFTYPE
//        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.2.2.1.8")));//IFOPERSTATUS

        // MIB的访问方式
        pdu.setType(PDU.GET);
        ResponseEvent respEvnt = snmp.send(pdu, target);
        Vector<VariableBinding> recVBs = null;
        if (respEvnt != null && respEvnt.getResponse() != null) {
            recVBs = (Vector<VariableBinding>) respEvnt.getResponse().getVariableBindings();
        }
        return recVBs;
    }

    @Override
    public int checkIpExist(DeviceInfo deviceInfo) {
        deviceInfo.setStatmentId(NAMESPACE + ".selectOne");
        DeviceInfo info = baseMapper.selectOne(deviceInfo);
        if(info == null){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public List<DeviceInfo> getDeviceByGroupId(DeviceInfo deviceInfo) {
        DeviceInfo device = new DeviceInfo();
        if(deviceInfo.getGroupId() != null && deviceInfo.getGroupId().contains(",")){
            String groupIds[] = deviceInfo.getGroupId().substring(0,deviceInfo.getGroupId().length()-1).split(",");
            List groupIdList = new ArrayList();
            for(String groupId : groupIds){
                groupIdList.add(groupId);
            }
            device.setGroupIds(groupIdList);
            device.setStatmentId(NAMESPACE + ".getDeviceByGroupId");
            device.setPaging(false);
            return baseMapper.selectList(device);
        }else {
            deviceInfo.setStatmentId(NAMESPACE + ".selectList");
            return baseMapper.selectList(deviceInfo);
        }
    }

}
