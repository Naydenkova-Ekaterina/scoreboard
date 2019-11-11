package shipping.bean;

import shipping.model.Cargo;
import shipping.model.Driver;
import shipping.model.Order;
import shipping.model.Wagon;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class ScoreboardBean {

    @EJB
    DataBean dataBean;

    public void update() {
        dataBean.update();
    }

    public List<Wagon> getWagons() {
        return dataBean.getWagons();
    }

    public List<Driver> getDrivers() {
        return dataBean.getDrivers();
    }

    public List<Order> getOrders() {
        return dataBean.getOrders();
    }

    public List<Cargo> getCargoes() {
        return dataBean.getCargoes();
    }

    public int getNumberOfDrivers() {
        return dataBean.getNumberOfDrivers();
    }

    public int getNumberOfFreeDrivers() {
        return dataBean.getNumberOfFreeDrivers();
    }

    public int getNumberOfDriversWithOrder() {
        return dataBean.getNumberOfDriversWithOrder();
    }

    public int getNumberOfWagonsWithOrder() {
        return dataBean.getNumberOfWagonsWithOrder();
    }

    public int getNumberOfFreeWagons() {
        return dataBean.getNumberOfFreeWagons();
    }

    public int getNumberOfWagons() {
        return dataBean.getNumberOfWagons();
    }

    public int getNumberOfServiceableWagons() {
        return dataBean.getNumberOfServiceableWagons();
    }

    public int getNumberOfFaultyWagons() {
        return dataBean.getNumberOfFaultyWagons();
    }

}
