package com.zzp.test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * Zookeeper����
 *
 * @create    2016��3��10��
 */
public class TestWatcher {
	
    // �Ự��ʱʱ�䣬����Ϊ��ϵͳĬ��ʱ��һ��
    private static final int SESSION_TIMEOUT = 30 * 1000;

    // ���� ZooKeeper ʵ��
    private ZooKeeper zk;

    // ���� Watcher ʵ��
    private Watcher wh = new Watcher() {
        /**
         * Watched�¼�
         */
        public void process(WatchedEvent event) {
            System.out.println("WatchedEvent >>> " + event.toString());
        }
    };

    // ��ʼ�� ZooKeeper ʵ��
    private void createZKInstance() throws Exception {
        // ���ӵ�ZK���񣬶�������ö��ŷָ�д
//        zk = new ZooKeeper("192.168.19.130:2181,192.168.19.130:2182,192.168.19.130:2183", TestWatcher.SESSION_TIMEOUT, this.wh);
        zk = new ZooKeeper("120.78.155.58:2183", TestWatcher.SESSION_TIMEOUT, this.wh);
    }

    private void ZKOperations() throws IOException, InterruptedException, KeeperException {
        System.out.println("\n1. ���� ZooKeeper �ڵ� (znode �� zoo2, ���ݣ� myData2 ��Ȩ�ޣ� OPEN_ACL_UNSAFE ���ڵ����ͣ� Persistent");
        zk.create("/zoo2", "myData2".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        System.out.println("\n2. �鿴�Ƿ񴴽��ɹ��� ");
        System.out.println(new String(zk.getData("/zoo2", this.wh, null)));// ���Watch

        // ǰ��һ����������˶�/zoo2�ڵ�ļ��ӣ����������/zoo2�����޸ĵ�ʱ�򣬻ᴥ��Watch�¼���
        System.out.println("\n3. �޸Ľڵ����� ");
        zk.setData("/zoo2", "shanhy20160310".getBytes(), -1);

        // �����ٴν����޸ģ��򲻻ᴥ��Watch�¼��������������֤ZK��һ�����ԡ�һ���Դ�������Ҳ����˵����һ�μ��ӣ�ֻ����´β�����һ�����á�
        System.out.println("\n3-1. �ٴ��޸Ľڵ����� ");
        zk.setData("/zoo2", "shanhy20160310-ABCD".getBytes(), -1);

        System.out.println("\n4. �鿴�Ƿ��޸ĳɹ��� ");
        System.out.println(new String(zk.getData("/zoo2", false, null)));

        System.out.println("\n5. ɾ���ڵ� ");
        zk.delete("/zoo2", -1);

        System.out.println("\n6. �鿴�ڵ��Ƿ�ɾ���� ");
        System.out.println(" �ڵ�״̬�� [" + zk.exists("/zoo2", false) + "]");
    }

    private void ZKClose() throws InterruptedException {
        zk.close();
    }

    public static void main(String[] args) throws Exception {
    	TestWatcher dm = new TestWatcher();
        dm.createZKInstance();
        dm.ZKOperations();
        dm.ZKClose();
    }
}
