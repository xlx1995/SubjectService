package com.xlx.zk.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Auther: 徐林啸
 * @Date: 2019/7/16 23:11
 * @Description:
 */
@Slf4j
public class ZkClient implements Watcher{

    /**
     * 连接地址
     */
    @Autowired
    private String address;

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * session会话
     */
    private static final Integer SESSION_TIMEOUT = 5000;
    /**
     * 信号量,阻塞程序执行,用户必须等待zookeeper连接成功,发送成功信号
     */

    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);

    private  ZooKeeper zk;

    public void createConnection() {
        try {
            zk = new ZooKeeper(address, SESSION_TIMEOUT, this);
            log.info("开始连接zookeeper");
        } catch (IOException e) {
            log.error("fail to connection zookeeper, ex[{}]", new Object[]{e});
        }

    }

    /**
     * 事件处理
     *
     * @param watchedEvent
     */
    @Override
    public void process(WatchedEvent watchedEvent) {
        //1、获取事件状态
        Watcher.Event.KeeperState state = watchedEvent.getState();
        //2、获取事件类型
        Watcher.Event.EventType type = watchedEvent.getType();
        //获取节点地址
        String path = watchedEvent.getPath();
        //3、判断是否连接
        if (Watcher.Event.KeeperState.SyncConnected == state) {
            //4、判断类型
            if (Watcher.Event.EventType.None == type) {
                /*try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                COUNT_DOWN_LATCH.countDown();
                log.info("###zookeeper建立连接成功###");
            } else if (Watcher.Event.EventType.NodeCreated == type) {
                log.info("###Watcher监听的对应数据节点被创建###, 当前新增节点：" + path);
            } else if (Watcher.Event.EventType.NodeDataChanged == type) {
                log.info("###Watcher监听的对应数据节点的数据内容发生变更###, 当前节点：" + path + "，被修改...");
            } else if (Watcher.Event.EventType.NodeChildrenChanged == type) {
                log.info("###Wather监听的对应数据节点的子节点列表发生变更###, 当前子节点：" + path + "，被修改...");
            } else if (Watcher.Event.EventType.NodeDeleted == type) {
                log.info("###Watcher监听的对应数据节点被删除###, 当前节点：" + path + "，被删除...");
            }
        }
    }

    /**
     * 创建持久化节点
     *
     * @param path
     * @param data
     */
    public  boolean createNode(String path, String data) {
        try {
            this.exists(path, true);
            //阻塞，当等于0的时候释放
            COUNT_DOWN_LATCH.await();
            zk.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            log.info("###新增节点信息path:" + path + " data:" + data);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 修改持久化节点
     *
     * @param path
     * @param data
     */
    public  boolean updateNode(String path, String data) {
        try {
            this.exists(path, true);
            //阻塞，当等于0的时候释放
            COUNT_DOWN_LATCH.await();
            //zk的数据版本是从0开始计数的。如果客户端传入的是-1，则表示zk服务器需要基于最新的数据进行更新。如果对zk的数据节点的更新操作没有原子性要求则可以使用-1.
            //version参数指定要更新的数据的版本, 如果version和真实的版本不同, 更新操作将失败. 指定version为-1则忽略版本检查
            zk.setData(path, data.getBytes(), -1);
            log.info("###修改节点信息path:" + path + " data:" + data);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除持久化节点
     *
     * @param path
     */
    public  boolean deleteNode(String path) {
        try {
            this.exists(path, true);
            //阻塞，当等于0的时候释放
            COUNT_DOWN_LATCH.await();
            //version参数指定要更新的数据的版本, 如果version和真实的版本不同, 更新操作将失败. 指定version为-1则忽略版本检查
            zk.delete(path, -1);
            log.info("###删除节点信息path:" + path);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断指定节点是否存在
     *
     * @param path
     * @param needWatch
     * @return
     */
    public  Stat exists(String path, boolean needWatch) {
        try {
            return zk.exists(path, needWatch);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取路径下所有子节点
     * @param path
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public  List<String> getChildren(String path) throws KeeperException, InterruptedException {
        List<String> children = this.zk.getChildren(path, false);
        return children;
    }


    /**
     * 关闭服务
     */
    public void close() {
        try {
            if (this.zk != null) {
                log.info("###zookeeper服务已关闭");
                this.zk.close();
            }
        } catch (Exception e) {
            log.error("###zookeeper服务错误关闭，ex{}",e);
        }
    }

}
