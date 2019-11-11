package shipping.bean;

import lombok.Getter;
import shipping.model.Cargo;
import shipping.model.Driver;
import shipping.model.Order;
import shipping.model.Wagon;
import org.apache.log4j.Logger;
import shipping.queue.Listener;
import shipping.queue.Loader;

import shipping.queue.MqUtil;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Singleton
@Getter
public class DataBean {

    private static final Logger logger = Logger.getLogger(DataBean.class);
    MqUtil mqConsumer = new MqUtil();
    private Loader dataLoader = new Loader();
    private Listener listener = Listener.getListener();

    private List<Cargo> cargoes;
    private List<Driver> drivers;
    private List<Wagon> wagons;
    private List<Order> orders;

    private int numberOfWagons;
    private int numberOfWagonsWithOrder;
    private int numberOfFreeWagons;
    private int numberOfServiceableWagons;
    private int numberOfFaultyWagons;
    private int numberOfDrivers;
    private int numberOfFreeDrivers;
    private int numberOfDriversWithOrder;

    public void update() {

        logger.debug("DataBean update.");

        if (!listener.isFirstData()) {
            logger.debug("DataBean first data load.");
            setDataLists();
            clearData();
            calcWagonsNumbers();
            calcDriversNumbers();
            listener.setFirstData(true);
        }

        if (!listener.isCorrectData()) {
            logger.debug("DataBean data should be updated.");
            setDataLists();
            clearData();
            calcWagonsNumbers();
            calcDriversNumbers();
            listener.setCorrectData(true);
        }
    }

    public void setDataLists(){
        cargoes = dataLoader.getCargoes();
        wagons = dataLoader.getWagons();
        drivers = dataLoader.getDrivers();
        orders = dataLoader.getOrders();
    }

    public void clearData() {
        logger.debug("DataBean clearData.");
        numberOfDrivers = 0;
        numberOfWagons = 0;
        numberOfFreeWagons = 0;
        numberOfFreeDrivers = 0;
        numberOfWagonsWithOrder = 0;
        numberOfDriversWithOrder = 0;
        numberOfServiceableWagons = 0;
        numberOfFaultyWagons = 0;
    }

    @PostConstruct
    public void init() throws IOException, TimeoutException {
        logger.debug("DataBean init.");
        mqConsumer.start();
        update();
    }

    @PreDestroy
    public void destroy() throws IOException, TimeoutException {
        logger.debug("DataBean destroy.");
        mqConsumer.stop();
    }

    public void calcDriversNumbers() {
        logger.debug("DataBean calcDriversNumbers.");

//        for (Driver driver : drivers) {
//            if (driver.getStatus().contains("rest")) {
//                numberOfFreeDrivers++;
//            } else {
//                numberOfDriversWithOrder++;
//            }
//        }
//        numberOfDrivers = drivers.size();
        numberOfDrivers =  10;
    }

    public void calcWagonsNumbers() {
        logger.debug("DataBean calcWagonsNumbers.");

//        for (Wagon wagon : wagons) {
////            if (wagon.getOrder()) {
////                numberOfFreeWagons++;
////                continue;
////            }
//            if (wagon.getStatus().contains("faulty")) {
//                numberOfFaultyWagons++;
//                continue;
//            }
//            if (wagon.getStatus().contains("serviceable")) {
//                numberOfServiceableWagons++;
//                continue;
//            }
//       //     numberOfWagonsWithOrder++;
//        }
//        numberOfWagons = wagons.size();
        numberOfWagons= 10;
    }

}
